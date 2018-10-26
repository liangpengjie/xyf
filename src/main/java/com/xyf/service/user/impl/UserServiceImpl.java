package com.xyf.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyf.common.MD5Utils;
import com.xyf.common.MyResponse;
import com.xyf.common.SMSUtils;
import com.xyf.common.constant.MoneyLogConstant;
import com.xyf.dao.BankDao;
import com.xyf.dao.CreateCardDao;
import com.xyf.dao.MoneyLogDao;
import com.xyf.dao.UserDao;
import com.xyf.dto.*;
import com.xyf.entity.MoneyLog;
import com.xyf.entity.User;
import com.xyf.entity.manager.CreateCardInfo;
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
    public MyResponse expectedReturn(PhoneDTO dto) {
        List<CreateCardInfo> users = createCardDao.getUserByPhone(dto.getPhone());
        double expectedReturn = 0;
        for (int i = 0; i < users.size(); i++) {
            expectedReturn += bankDao.getBonusByName(users.get(i).getBankName());
        }
        Map result = new HashMap(4);
        result.put("expectedReturn", expectedReturn);
        User user = userDao.getUserByPhone(dto.getPhone());
        result.put("actualIncome", expectedReturn + user.getCreateCardBonus());
        return new MyResponse(result);
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
        // 20% 手续费交给平台和合伙人.自身得 80%
        double money = dto.getCashEarningsMoney() * 0.8;
        dto.setCashEarningsMoney(money);
        userDao.updateCashBackBonus(dto);

        User u = userDao.getUserById(dto.getUserId());
        ArrayList ids = new ArrayList();
        ids.add(u.getSuperior1());
        ids.add(u.getSuperior2());
        ids.add(u.getSuperior3());
        // 获取为代理的上级用户
        List<User> users = userDao.getUsersByIds(ids);
        User u1 = users.get(0);
        User u2 = users.get(1);
        User u3 = users.get(2);


        // 1级上级为银牌代理，1级代理 20%
        if (u1.getLevel() == 1 && u2.getLevel() != 2 && u2.getLevel() != 3 && u3.getLevel() != 2 && u3.getLevel() != 3) {
            dto.setUserId(u1.getUserId());
            dto.setCashEarningsMoney(money * 0.2);
            userDao.updateCashBackBonus(dto);
            MoneyLog yonghulog = new MoneyLog(u1.getUserId(), money * 0.2, MoneyLogConstant.YONGHU, MoneyLogConstant.TIXIAN, u.getUserId());
            MoneyLog pingtailog = new MoneyLog(0, money * 0.8, MoneyLogConstant.PINGTAI, MoneyLogConstant.TIXIAN, u.getUserId());
            List<MoneyLog> moneyLogs = new ArrayList<>();
            moneyLogs.add(yonghulog);
            moneyLogs.add(pingtailog);
            moneyLogDao.addList(moneyLogs);
        }

        // 1级上级为银牌代理，2级上级为金牌代理   1级代理 20%，2级代理 20%
        if (u1.getLevel() == 1 && u2.getLevel() == 2 && u3.getLevel() != 3) {
            // 修改 u1 的提现奖励
            dto.setUserId(u1.getUserId());
            dto.setCashEarningsMoney(money * 0.2);
            // 修改 u2 的提现奖励
            userDao.updateCashBackBonus(dto);
            dto.setUserId(u2.getUserId());
            userDao.updateCashBackBonus(dto);
            MoneyLog yonghulog = new MoneyLog(u1.getUserId(), money * 0.2, MoneyLogConstant.YONGHU, MoneyLogConstant.TIXIAN, u.getUserId());
            MoneyLog yonghulog2 = new MoneyLog(u2.getUserId(), money * 0.2, MoneyLogConstant.YONGHU, MoneyLogConstant.TIXIAN, u.getUserId());
            MoneyLog pingtailog = new MoneyLog(0, money * 0.6, MoneyLogConstant.PINGTAI, MoneyLogConstant.TIXIAN, u.getUserId());
            List<MoneyLog> moneyLogs = new ArrayList<>();
            moneyLogs.add(yonghulog);
            moneyLogs.add(yonghulog2);
            moneyLogs.add(pingtailog);
            moneyLogDao.addList(moneyLogs);
        }


        // 1级上级为银牌代理，2级上级为金牌代理，3级上级为钻石代理   1级代理 20%，2级代理 20%，3级代理 40%
        if (u1.getLevel() == 1 && u2.getLevel() == 2) {
            // 修改 u1 的提现奖励
            dto.setUserId(u1.getUserId());
            dto.setCashEarningsMoney(money * 0.2);
            // 修改 u2 的提现奖励
            userDao.updateCashBackBonus(dto);
            dto.setUserId(u2.getUserId());
            userDao.updateCashBackBonus(dto);
            // 修改 u3 的提现奖励
            MoneyLog yonghulog = new MoneyLog(u1.getUserId(), money * 0.2, MoneyLogConstant.YONGHU, MoneyLogConstant.TIXIAN, u.getUserId());
            MoneyLog yonghulog2 = new MoneyLog(u2.getUserId(), money * 0.2, MoneyLogConstant.YONGHU, MoneyLogConstant.TIXIAN, u.getUserId());
            MoneyLog pingtailog = new MoneyLog(0, money * 0.8, MoneyLogConstant.PINGTAI, MoneyLogConstant.TIXIAN, u.getUserId());
            List<MoneyLog> moneyLogs = new ArrayList<>();
            moneyLogs.add(yonghulog);
            moneyLogs.add(yonghulog2);
            moneyLogs.add(pingtailog);
            moneyLogDao.addList(moneyLogs);
        }
        // 直接上级为银牌代理
        if (u1.getLevel() == 1) {
            dto.setUserId(u1.getUserId());
            dto.setCashEarningsMoney(money * 0.2);
            userDao.updateCashBackBonus(dto);
            moneyLogDao.add(new MoneyLog(u1.getUserId(), money * 0.2, MoneyLogConstant.YONGHU, MoneyLogConstant.TIXIAN, u.getUserId()));

            MoneyLog yonghulog = new MoneyLog(u3.getUserId(), money * 0.2, MoneyLogConstant.YONGHU, MoneyLogConstant.TIXIAN, u.getUserId());
            MoneyLog pingtailog = new MoneyLog(0, money * 0.8, MoneyLogConstant.PINGTAI, MoneyLogConstant.TIXIAN, u.getUserId());
            List<MoneyLog> moneyLogs = new ArrayList<>();
            moneyLogs.add(yonghulog);
            moneyLogs.add(pingtailog);
            moneyLogDao.addList(moneyLogs);

            // 二级上级为金牌代理
            if (u2.getLevel() == 2) {
                dto.setUserId(u2.getUserId());
                dto.setCashEarningsMoney(money * 0.2);
                userDao.updateCashBackBonus(dto);
                moneyLogDao.add(new MoneyLog(u2.getUserId(), money * 0.2, MoneyLogConstant.YONGHU, MoneyLogConstant.TIXIAN, u.getUserId()));

                // 三级上级为钻石代理
                if (u3.getLevel() == 3) {
                    dto.setUserId(u3.getUserId());
                    dto.setCashEarningsMoney(money * 0.4);
                    userDao.updateCashBackBonus(dto);
                    moneyLogDao.add(new MoneyLog(u3.getUserId(), money * 0.4, MoneyLogConstant.YONGHU, MoneyLogConstant.TIXIAN, u.getUserId()));
                }
            }

            // 三级上级为钻石代理
//            if (u3.getLevel() == 3) {
//                dto.setUserId(u3.getUserId());
//                dto.setCashEarningsMoney(money * 0.6);
//                userDao.updateCashBackBonus(dto);
//                moneyLogDao.add(new MoneyLog(u3.getUserId(), money * 0.6, MoneyLogConstant.YONGHU, MoneyLogConstant.TIXIAN, u.getUserId()));
//
//                MoneyLog yonghulog = new MoneyLog(u3.getUserId(), money * 0.8, MoneyLogConstant.YONGHU, MoneyLogConstant.TIXIAN, u.getUserId());
//                MoneyLog pingtailog = new MoneyLog(0, money * 0.2, MoneyLogConstant.PINGTAI, MoneyLogConstant.TIXIAN, u.getUserId());
//                List<MoneyLog> moneyLogs = new ArrayList<>();
//                moneyLogDao.addList(moneyLogs);
//            }


        }


        // 二级上级为金牌代理
        if (u1.getLevel() == 0 && u1.getLevel() == 9 && u2.getLevel() == 2) {
            dto.setUserId(u2.getUserId());
            dto.setCashEarningsMoney(money * 0.4);
            userDao.updateCashBackBonus(dto);
            MoneyLog yonghulog = new MoneyLog(u2.getUserId(), money * 0.4, MoneyLogConstant.YONGHU, MoneyLogConstant.TIXIAN, u.getUserId());
            moneyLogDao.add(yonghulog);
            // 三级上级为钻石代理
            // 如果有三级上级则平台收益为 20%，没有则为 60%
            if (u3.getLevel() == 3) {
                dto.setUserId(u3.getUserId());
                dto.setCashEarningsMoney(money * 0.4);
                userDao.updateCashBackBonus(dto);
                MoneyLog yonghulog1 = new MoneyLog(u3.getUserId(), money * 0.4, MoneyLogConstant.YONGHU, MoneyLogConstant.TIXIAN, u.getUserId());
                MoneyLog pingtailog = new MoneyLog(0, money * 0.2, MoneyLogConstant.PINGTAI, MoneyLogConstant.TIXIAN, u.getUserId());
                List<MoneyLog> moneyLogs = new ArrayList<>();
                moneyLogs.add(yonghulog1);
                moneyLogs.add(pingtailog);
                moneyLogDao.addList(moneyLogs);
            } else {
                MoneyLog pingtailog = new MoneyLog(0, money * 0.6, MoneyLogConstant.YONGHU, MoneyLogConstant.TIXIAN, u.getUserId());
                moneyLogDao.add(pingtailog);
            }

        }

        // 三级上级为银牌代理，代理拿 20%，平台 80%
        if (u1.getLevel() == 0 && u2.getLevel() == 0 && u1.getLevel() == 9 && u2.getLevel() == 9 && u3.getLevel() == 1) {
            dto.setUserId(u3.getUserId());
            dto.setCashEarningsMoney(money * 0.2);
            userDao.updateCashBackBonus(dto);
            MoneyLog yonghulog = new MoneyLog(u3.getUserId(), money * 0.2, MoneyLogConstant.YONGHU, MoneyLogConstant.TIXIAN, u.getUserId());
            MoneyLog pingtailog = new MoneyLog(0, money * 0.8, MoneyLogConstant.PINGTAI, MoneyLogConstant.TIXIAN, u.getUserId());
            List<MoneyLog> moneyLogs = new ArrayList<>();
            moneyLogs.add(yonghulog);
            moneyLogs.add(pingtailog);
            moneyLogDao.addList(moneyLogs);
        }

        // 三级上级为金牌代理，代理拿 40%，平台 60%
        if (u1.getLevel() == 0 && u2.getLevel() == 0 && u1.getLevel() == 9 && u2.getLevel() == 9 && u3.getLevel() == 2) {
            dto.setUserId(u3.getUserId());
            dto.setCashEarningsMoney(money * 0.4);
            userDao.updateCashBackBonus(dto);
            MoneyLog yonghulog = new MoneyLog(u3.getUserId(), money * 0.4, MoneyLogConstant.YONGHU, MoneyLogConstant.TIXIAN, u.getUserId());
            MoneyLog pingtailog = new MoneyLog(0, money * 0.6, MoneyLogConstant.PINGTAI, MoneyLogConstant.TIXIAN, u.getUserId());
            List<MoneyLog> moneyLogs = new ArrayList<>();
            moneyLogs.add(yonghulog);
            moneyLogs.add(pingtailog);
            moneyLogDao.addList(moneyLogs);
        }

        // 三级上级为钻石代理，代理拿 80%，平台 20%
        if (u1.getLevel() == 0 && u2.getLevel() == 0 && u1.getLevel() == 9 && u2.getLevel() == 9 && u3.getLevel() == 3) {
            dto.setUserId(u3.getUserId());
            dto.setCashEarningsMoney(money * 0.8);
            userDao.updateCashBackBonus(dto);
            MoneyLog yonghulog = new MoneyLog(u3.getUserId(), money * 0.8, MoneyLogConstant.YONGHU, MoneyLogConstant.TIXIAN, u.getUserId());
            MoneyLog pingtailog = new MoneyLog(0, money * 0.2, MoneyLogConstant.PINGTAI, MoneyLogConstant.TIXIAN, u.getUserId());
            List<MoneyLog> moneyLogs = new ArrayList<>();
            moneyLogs.add(yonghulog);
            moneyLogs.add(pingtailog);
            moneyLogDao.addList(moneyLogs);
        }


        return null;
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
