package com.xyf.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyf.common.MD5Utils;
import com.xyf.common.MyResponse;
import com.xyf.common.SMSUtils;
import com.xyf.common.constant.MoneyLogConstant;
import com.xyf.dao.*;
import com.xyf.dto.*;
import com.xyf.entity.MoneyLog;
import com.xyf.entity.ShipLog;
import com.xyf.entity.User;
import com.xyf.entity.UserBank;
import com.xyf.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


@Service(value = "userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserDao userDao;
    @Autowired
    private CreateCardDao createCardDao;
    @Autowired
    private BankDao bankDao;
    @Autowired
    private MoneyLogDao moneyLogDao;
    @Autowired
    private ShipLogDao shipLogDao;
    @Autowired
    private UserBankDao userBankDao;

    @Override
    public MyResponse addUser(AddUserDTO dto) {
        dto.setPassword(MD5Utils.encryptMD5(dto.getPassword()));
        dto.setCreateTime(new Date());
        if (dto.getSuperior1() != 0) {
            User superUser = userDao.getSuperior(dto.getSuperior1());
            dto.setSuperior2(superUser.getSuperior1());
            dto.setSuperior3(superUser.getSuperior2());
        }
        int i = userDao.insert(dto);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("添加用户失败，请重试！", 0);
    }

    @Override
    public MyResponse verifyUsername(VerifyUsernameDTO dto) {
        User user = userDao.verifyUsername(dto);
        if (user != null) {
            return new MyResponse("用户名已存在，请重新输入！", 0);
        }
        return new MyResponse();
    }

    @Override
    public MyResponse login(LoginDTO dto) {
        dto.setPassword(MD5Utils.encryptMD5(dto.getPassword()));
        User user = userDao.login(dto);
        if (user == null) {
            return new MyResponse("用户名或密码错误，请重新输入！", 0);
        }
        return new MyResponse(user);
    }


    @Override
    public MyResponse phoneLogin(PhoneDTO dto) {
        String randomVcode = SMSUtils.createRandomVcode();
        int i = SMSUtils.sendSMS(dto.getPhone(), randomVcode, "");
        if (i == 0) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(5 * 60);
            session.setAttribute("verificationCode", randomVcode);
            log.info("验证码：" + randomVcode);
            return new MyResponse("发送成功！", 1);
        }
        return new MyResponse("发送失败！", 0);
    }

    @Override
    public MyResponse checkCode(PhoneLoginDTO dto) {
        String verificationCode = (String) request.getSession().getAttribute("verificationCode");
        if (StringUtils.equals(dto.getCode(), verificationCode)) {
            User user = userDao.getUserByPhone(dto.getPhone());
            return new MyResponse(user);
        }
        return new MyResponse("验证码错误或以超时，请重试！", 0);
    }


    /**
     * 效验验证码
     *
     * @param code
     * @return
     */
    @Override
    public MyResponse checkSMSCode(String code) {
        String verificationCode = (String) request.getSession().getAttribute("verificationCode");
        if (StringUtils.equals(code, verificationCode)) {
            return new MyResponse(true);
        }
        return new MyResponse("验证码错误或以超时，请重试！", 0);
    }

    /**
     * 查询用户信息
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse selectUserInfo(UserPhoneDTO dto) {
        User user = userDao.selectUserInfo(dto);
        if (user != null) {
            return new MyResponse(user);
        }
        return new MyResponse(user);
    }

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Override
    public MyResponse users(PageDTO dto) {
        PageHelper.startPage(dto.getPageNumber(), dto.getPageSize());
        List<User> users = userDao.users(dto);
        PageInfo result = new PageInfo(users);
        return new MyResponse(result);
    }

    /**
     * 预计收益和实际收益
     * 预计收益：
     * 获取用户一共办了几张卡，将每个银行办卡成功奖励相加计算
     * 实际收益：
     * 自己办卡成功后的收益 + 推荐别人办卡成功后的奖励收益
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse expectedReturn(IdDTO dto) {
        Map result = new HashMap(4);
        User user = userDao.getUserById(dto.getUserId());
        if(user == null){
            return new MyResponse("用户不存在",0);
        }
        double yjuy = createCardDao.yjuyById(dto.getUserId());
        result.put("expectedReturn", String.format("%.2f", yjuy));

        // List<CreateCardInfo> users = createCardDao.getUserByPhone(dto.getPhone());
        // double expectedReturn = 0;
        // for (int i = 0; i < users.size(); i++) {
        //     expectedReturn += bankDao.getBonusByName(users.get(i).getBankName());
        // }


        // double sjuy = moneyLogDao.sjuy(dto.getPhone());
        result.put("actualIncome", user.getCreateCardBonus());
        return new MyResponse(result);
    }

    // 提现分类
    private boolean tx(CashEarningsDTO dto) {
        switch (dto.getTxType()) {
            case 11:
                // 办卡收益
                userDao.minCreateCardBonus(dto);
                return true;
            case 12:
                // 推广收益
                userDao.minUserBonus(dto);
                return true;
            case 13:
                // 自己刷卡收益提现
                userDao.minuseCardBonus(dto);
                return true;
            case 14:
                // 下级刷卡收益提现
                userDao.minCashBackBonus(dto);
                return true;
            default:
                return false;
        }
    }

    /**
     * 提现收益
     * 获取用户上级，提现金额必须超过120元，扣除20%作为平台收益及推广收益
     * 20%收益归属：
     * 钻石级别收益为80%，金牌级别为40%，银牌级别为20%
     * 只有自身等级高于下级等级才能拿到收益
     * 以上级最高等级为封顶,高等级收益减去低级收益为自身收益，剩下的归平台所有
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse cashEarnings(CashEarningsDTO dto) {
        if (dto.getTxType() == 0) {
            return new MyResponse("提现类型不可为空", 0);
        }
        User u = userDao.getUserById(dto.getUserId());
        if(u == null){
            return new MyResponse("用户不存在", 0);
        }
        // 20% 手续费交给平台和合伙人.自身得 80%
        double money = dto.getCashEarningsMoney() * 0.2;
        dto.setCashEarningsMoney(dto.getCashEarningsMoney() * 0.8);
        if(!tx(dto)){
            return new MyResponse("提现类型参数错误", 0);
        }

        // TODO 添加提现日志


        ArrayList ids = new ArrayList();
        ids.add(u.getSuperior1());
        ids.add(u.getSuperior2());
        ids.add(u.getSuperior3());
        // 获取为代理的上级用户
        List<User> users = userDao.getUsersByIds(ids);
        User u1 = users.get(0);
        User u2 = users.get(1);
        User u3 = users.get(2);
        List<MoneyLog> moneyLogs = new ArrayList<>();
        if (u1.getLevel() == 1) {
            if (u2.getLevel() == 2) {
                if (u3.getLevel() == 3) {
                    dto.setUserId(u3.getUserId());
                    dto.setCashEarningsMoney(money * 0.4);
                    userDao.updateCashBackBonus(dto);
                    MoneyLog yonghulog3 = new MoneyLog(u3.getUserId(), money * 0.4, MoneyLogConstant.YONGHU, dto.getTxType(), u.getUserId(), 0, 0D);
                    MoneyLog yonghulog2 = new MoneyLog(u2.getUserId(), money * 0.2, MoneyLogConstant.YONGHU, dto.getTxType(), u.getUserId(), 0, 0D);
                    MoneyLog yonghulog1 = new MoneyLog(u1.getUserId(), money * 0.2, MoneyLogConstant.YONGHU, dto.getTxType(), u.getUserId(), 0, 0D);
                    MoneyLog yonghu = new MoneyLog(u.getUserId(), dto.getCashEarningsMoney() * 0.8, MoneyLogConstant.YONGHU, dto.getTxType(), u.getUserId(), 0, 0D);
                    MoneyLog pingtailog = new MoneyLog(0, money * 0.2, MoneyLogConstant.PINGTAI, dto.getTxType(), u.getUserId(), 0, 0D);
                    moneyLogs = Arrays.asList(yonghu, yonghulog1, yonghulog2, yonghulog3, pingtailog);
                    moneyLogDao.addList(moneyLogs);
                    return new MyResponse();
                }
                dto.setUserId(u2.getUserId());
                dto.setCashEarningsMoney(money * 0.2);
                userDao.updateCashBackBonus(dto);
                MoneyLog yonghulog2 = new MoneyLog(u2.getUserId(), money * 0.2, MoneyLogConstant.YONGHU, dto.getTxType(), u.getUserId(), 0, 0D);
                MoneyLog yonghulog1 = new MoneyLog(u1.getUserId(), money * 0.2, MoneyLogConstant.YONGHU, dto.getTxType(), u.getUserId(), 0, 0D);
                MoneyLog yonghu = new MoneyLog(u.getUserId(), dto.getCashEarningsMoney() * 0.8, MoneyLogConstant.YONGHU, dto.getTxType(), u.getUserId(), 0, 0D);
                MoneyLog pingtailog = new MoneyLog(0, money * 0.6, MoneyLogConstant.PINGTAI, dto.getTxType(), u.getUserId(), 0, 0D);
                moneyLogs = Arrays.asList(yonghu, yonghulog1, yonghulog2, pingtailog);
                moneyLogDao.addList(moneyLogs);
                return new MyResponse();
            }
            dto.setUserId(u1.getUserId());
            dto.setCashEarningsMoney(money * 0.2);
            userDao.updateCashBackBonus(dto);
            MoneyLog yonghulog1 = new MoneyLog(u1.getUserId(), money * 0.2, MoneyLogConstant.YONGHU, dto.getTxType(), u.getUserId(), 0, 0D);
            MoneyLog yonghu = new MoneyLog(u.getUserId(), dto.getCashEarningsMoney() * 0.8, MoneyLogConstant.YONGHU, dto.getTxType(), u.getUserId(), 0, 0D);
            MoneyLog pingtailog = new MoneyLog(0, money * 0.8, MoneyLogConstant.PINGTAI, dto.getTxType(), u.getUserId(), 0, 0D);
            moneyLogs = Arrays.asList(yonghu, yonghulog1, pingtailog);
            moneyLogDao.addList(moneyLogs);
            return new MyResponse();
        }

        if (u2.getLevel() == 2) {
            if (u3.getLevel() == 3) {
                dto.setUserId(u3.getUserId());
                dto.setCashEarningsMoney(money * 0.4);
                userDao.updateCashBackBonus(dto);
                MoneyLog yonghulog3 = new MoneyLog(u3.getUserId(), money * 0.4, MoneyLogConstant.YONGHU, dto.getTxType(), u.getUserId(), 0, 0D);
                MoneyLog yonghulog2 = new MoneyLog(u2.getUserId(), money * 0.4, MoneyLogConstant.YONGHU, dto.getTxType(), u.getUserId(), 0, 0D);
                MoneyLog yonghu = new MoneyLog(u.getUserId(), dto.getCashEarningsMoney() * 0.8, MoneyLogConstant.YONGHU, dto.getTxType(), u.getUserId(), 0, 0D);
                MoneyLog pingtailog = new MoneyLog(0, money * 0.2, MoneyLogConstant.PINGTAI, dto.getTxType(), u.getUserId(), 0, 0D);
                moneyLogs = Arrays.asList(yonghu, yonghulog2, yonghulog3, pingtailog);
                moneyLogDao.addList(moneyLogs);
                return new MyResponse();
            }
            dto.setUserId(u2.getUserId());
            dto.setCashEarningsMoney(money * 0.4);
            userDao.updateCashBackBonus(dto);
            MoneyLog yonghulog2 = new MoneyLog(u2.getUserId(), money * 0.4, MoneyLogConstant.YONGHU, dto.getTxType(), u.getUserId(), 0, 0D);
            MoneyLog yonghu = new MoneyLog(u.getUserId(), dto.getCashEarningsMoney() * 0.8, MoneyLogConstant.YONGHU, dto.getTxType(), u.getUserId(), 0, 0D);
            MoneyLog pingtailog = new MoneyLog(0, money * 0.6, MoneyLogConstant.PINGTAI, dto.getTxType(), u.getUserId(), 0, 0D);
            moneyLogs = Arrays.asList(yonghu, yonghulog2, pingtailog);
            moneyLogDao.addList(moneyLogs);
            return new MyResponse();
        }

        if (u3.getLevel() == 3) {
            dto.setUserId(u3.getUserId());
            dto.setCashEarningsMoney(money * 0.8);
            userDao.updateCashBackBonus(dto);
            MoneyLog yonghulog3 = new MoneyLog(u3.getUserId(), money * 0.8, MoneyLogConstant.YONGHU, dto.getTxType(), u.getUserId(), 0, 0D);
            MoneyLog yonghu = new MoneyLog(u.getUserId(), dto.getCashEarningsMoney() * 0.8, MoneyLogConstant.YONGHU, dto.getTxType(), u.getUserId(), 0, 0D);
            MoneyLog pingtailog = new MoneyLog(0, money * 0.2, MoneyLogConstant.PINGTAI, dto.getTxType(), u.getUserId(), 0, 0D);
            moneyLogs = Arrays.asList(yonghu, yonghulog3, pingtailog);
            moneyLogDao.addList(moneyLogs);
            return new MyResponse();
        }
        return new MyResponse("操作失败", 0);
    }

    /**
     * 立即办卡激活奖励
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse initCard(InitCardDTO dto) {
        User user = userDao.getUserById(dto.getUserId());
        Map map = new HashMap();
        map.put("userId", user.getSuperior1());
        map.put("money", dto.getSuperiorBonus());
        userDao.updatCereateCardBonusByPId(map);
        map.put("userId", dto.getUserId());
        map.put("money", dto.getBankBonus());
        userDao.updatCereateCardBonusByPId(map);
        map.put("status", 1);
        createCardDao.updateStatus(map);
        MoneyLog u = new MoneyLog(dto.getUserId(), dto.getBankBonus() + 0D, MoneyLogConstant.YONGHU, MoneyLogConstant.LIJIBANKA, dto.getUserId(), 0, 0D);
        MoneyLog u1 = new MoneyLog(user.getSuperior1(), dto.getSuperiorBonus() + 0D, MoneyLogConstant.YONGHU, MoneyLogConstant.LIJIBANKA, dto.getUserId(), 0, 0D);
        moneyLogDao.addList(Arrays.asList(u, u1));
        return new MyResponse();
    }

    /**
     * 用户提现日志
     *
     * @param shipLog
     * @return
     */
    @Override
    public MyResponse addShipLog(ShipLog shipLog) {
        int i = shipLogDao.addShipLog(shipLog);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("操作失败！", 0);
    }

    /**
     * 用户添加提现银行
     *
     * @param userBank
     * @return
     */
    @Override
    public MyResponse addBank(UserBank userBank) {
        int count = userBankDao.count(userBank);
        if (count > 0) {
            userBank.setIsDefault(1);
        }
        int i = userBankDao.addBank(userBank);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("操作失败！", 0);
    }

    /**
     * 用户修改提现银行
     *
     * @param userBank
     * @return
     */
    @Override
    public MyResponse editBank(UserBank userBank) {
        int i = userBankDao.editBank(userBank);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("操作失败！", 0);
    }

    /**
     * 用户删除提现银行
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse delBank(TYIDDTO dto) {
        int i = userBankDao.delBank(dto);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("操作失败！", 0);
    }

    /**
     * 用户提现银行列表
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse bankList(IdDTO dto) {
        List<UserBank> result = userBankDao.bankList(dto);
        return new MyResponse(result);
    }

    /**
     * 更改默认提现银行
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse editDefaultBank(IdsDTO dto) {
        userBankDao.editDefaultBank0(dto);
        int i = userBankDao.editDefaultBank(dto);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("操作失败！", 0);
    }

    /**
     * 查询用户默认提现银行
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse selectDefaultBank(IdDTO dto) {
        UserBank userBank = userBankDao.selectDefaultBank(dto);
        return new MyResponse(userBank);
    }

    /**
     * 效验电话是否存在
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse verifyPhone(PhoneDTO dto) {
        int i = userDao.verifyPhone(dto);
        if (i > 0) {
            return new MyResponse();
        }
        return new MyResponse("电话号码已存在", 0);
    }


    /**
     * 修改密码
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse updatePassword(LoginDTO dto) {
        dto.setPassword(MD5Utils.encryptMD5(dto.getPassword()));
        int i = userDao.updatePassword(dto);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("修改密码失败，请重试！", 0);
    }

}
