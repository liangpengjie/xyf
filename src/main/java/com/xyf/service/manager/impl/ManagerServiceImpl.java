package com.xyf.service.manager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyf.common.MD5Utils;
import com.xyf.common.MapUtils;
import com.xyf.common.MyResponse;
import com.xyf.common.constant.MoneyLogConstant;
import com.xyf.dao.*;
import com.xyf.dto.*;
import com.xyf.entity.ImgsUrl;
import com.xyf.entity.MoneyLog;
import com.xyf.entity.ProductsLog;
import com.xyf.entity.User;
import com.xyf.entity.manager.CreateCardInfo;
import com.xyf.entity.manager.ManagerUser;
import com.xyf.service.manager.ManagerService;
import com.xyf.vo.ShipLogVo;
import com.xyf.vo.YHUseCardVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service(value = "managerService")
// @Transactional(rollbackFor = Exception.class)
public class ManagerServiceImpl implements ManagerService {
    private static final Logger log = LoggerFactory.getLogger(ManagerServiceImpl.class);

    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CreateCardDao createCardDao;
    @Autowired
    private ProductsLogDao productsLogDao;
    @Autowired
    private MoneyLogDao moneyLogDao;
    @Autowired
    private ShipLogDao shipLogDao;
    @Autowired
    private ImgsUrlDao imgsUrlDao;

    /**
     * 添加管理员
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse add(ManagerDTO dto) {
        try {
            dto.setPassword(MD5Utils.encryptMD5(dto.getPassword()));
            int i = managerDao.add(dto);
            if (i == 1) {
                return new MyResponse();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new MyResponse("添加管理员失败，请重试！", 0);
    }

    /**
     * 账号密码登录
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse login(ManagerDTO dto) {
        dto.setPassword(MD5Utils.encryptMD5(dto.getPassword()));
        ManagerUser user = managerDao.login(dto);
        if (user == null) {
            return new MyResponse("用户名或密码错误，请重新输入！", 0);
        }
        return new MyResponse(user);
    }


    /**
     * 修改密码
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse edit(ManagerDTO dto) {
        dto.setPassword(MD5Utils.encryptMD5(dto.getPassword()));
        int i = managerDao.updatePassword(dto);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("修改密码失败，请重试！", 0);
    }

    /**
     * 删除管理员
     *
     * @param manager
     * @return
     */
    @Override
    public MyResponse delete(ManagerUser manager) {
        int i = managerDao.delete(manager);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("删除失败，请重试！", 0);
    }

    /**
     * 后台录入数据合伙人激活
     * 用户等级    0：普通用户    1：银牌代理     2：金牌代理    3： 钻石代理
     *
     * @param dto
     * @return
     */
    @Transactional
    @Override
    public MyResponse initPartner(InitPartnerDTO dto) {
        try {
            User user = userDao.getUserByPhone(dto.getPhone());
            // 如果用户已经是合伙人，只改变用户等级
            if (user.getIsPartner() == 1) {
                userDao.updateLever(dto);
                return new MyResponse("操作成功", 1);
            }
            //更改用户等级并返现余额199
            dto.setBonus(199D);
            int i = userDao.updateUserLevel(dto);
            productsLogDao.updateTerminal(dto);
            /*
             * 一级上级  （只有自身等级比下级合伙人高才可以拿到推合伙人奖励，普通用户不拿奖励）
             */
            if (user.getSuperior1() > 0) {
                User superior1 = userDao.getSuperior(user.getSuperior1());
                //如果一级上级为钻石代理则钻石代理拿80元
                if (superior1.getLevel() == 3) {
                    dto.setPhone(superior1.getPhone());
                    dto.setMonery(80);
                    int i1 = userDao.updateUserBonus(dto);
                    return new MyResponse("操作成功", 1);
                }
                //如果一级上级为金牌代理则拿70元
                if (superior1.getLevel() == 2) {
                    dto.setMonery(70);
                    int i1 = userDao.updateUserBonus(dto);
                    //获取二级上级
                    User superior2 = userDao.getSuperior(user.getSuperior2());
                    //如果二级上级为钻石代理则拿10元
                    if (superior2.getLevel() == 3) {
                        dto.setPhone(superior2.getPhone());
                        dto.setMonery(10);
                        int i2 = userDao.updateUserBonus(dto);
                    }
                    return new MyResponse("操作成功", 1);
                }
                //如果一级上级为银牌代理则拿60元
                if (superior1.getLevel() == 1) {
                    dto.setMonery(60);
                    int i1 = userDao.updateUserBonus(dto);
                    //获取二级上级
                    User superior2 = userDao.getSuperior(user.getSuperior2());
                    //如果二级上级为钻石代理则拿20元
                    if (superior2.getLevel() == 3) {
                        dto.setPhone(superior2.getPhone());
                        dto.setMonery(20);
                        int i2 = userDao.updateUserBonus(dto);
                    }
                    //如果二级上级为金牌代理则拿10元
                    if (superior2.getLevel() == 2) {
                        dto.setPhone(superior2.getPhone());
                        dto.setMonery(10);
                        int i2 = userDao.updateUserBonus(dto);
                        // 获取三级上级
                        User superior3 = userDao.getSuperior(user.getSuperior3());
                        if (superior3.getLevel() == 3) {
                            dto.setPhone(superior3.getPhone());
                            dto.setMonery(10);
                            int i3 = userDao.updateUserBonus(dto);
                        }
                    }
                    //如果二级上级为银牌代理则不拿，判断三级上级等级
                    if (superior2.getLevel() == 1) {
                        // 获取三级上级
                        User superior3 = userDao.getSuperior(user.getSuperior3());
                        // 如果三级上级是钻石代理则拿20元
                        if (superior3.getLevel() == 3) {
                            dto.setPhone(superior3.getPhone());
                            dto.setMonery(20);
                            int i3 = userDao.updateUserBonus(dto);
                        }
                        // 如果三级上级是金牌代理则拿10元
                        if (superior3.getLevel() == 3) {
                            dto.setPhone(superior3.getPhone());
                            dto.setMonery(10);
                            int i3 = userDao.updateUserBonus(dto);
                        }
                    }

                    return new MyResponse("操作成功", 1);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new MyResponse("操作失败", 0);
        }
        return new MyResponse("操作成功", 1);
    }

    /**
     * 后台录入刷卡奖励
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse initUserCradBonus(ListDTO dto) {
        // 传入数据为空
        if (dto.getUseCardList() == null && dto.getUseCardList().isEmpty()) {
            return new MyResponse();
        }
        List<UseCardDTO> useCardList = dto.getUseCardList();
        List<MoneyLog> moneyLogs = new ArrayList<MoneyLog>();
        boolean flag = false;
        for (UseCardDTO useCardDTO : useCardList) {
            User user = userDao.getUserByPhone(useCardDTO.getPhone());
            /**
             * 上级刷卡奖励
             */
            Map map = new HashMap();
            double moneys = 0;
            // 一级上级为0.5收益
            if (user.getSuperior1() > 0 && user.getSuperior1() != 9) {
                map.put("userId", user.getSuperior1());
                map.put("money", useCardDTO.getUseCardCount() * 0.5);
                userDao.updateCashBackBonusByMap(map);
                MoneyLog moneyLog = new MoneyLog(
                        user.getSuperior1(), Double.valueOf(df.format(useCardDTO.getUseCardCount() * 0.5)), MoneyLogConstant.YONGHU, MoneyLogConstant.SHUAKA,
                        user.getUserId(), 0, 0D);
                moneyLogs.add(moneyLog);
            }
            // 二级上级为0.4收益
            if (user.getSuperior2() > 0 && user.getSuperior2() != 9) {
                map.put("userId", user.getSuperior2());
                map.put("money", useCardDTO.getUseCardCount() * 0.4);
                userDao.updateCashBackBonusByMap(map);
                MoneyLog moneyLog = new MoneyLog(
                        user.getSuperior2(), Double.valueOf(df.format(useCardDTO.getUseCardCount() * 0.4)), MoneyLogConstant.YONGHU, MoneyLogConstant.SHUAKA,
                        user.getUserId(), 0, 0D);
                moneyLogs.add(moneyLog);
            }
            // 三级上级为0.3收益
            if (user.getSuperior3() > 0 && user.getSuperior3() != 9) {
                map.put("userId", user.getSuperior3());
                map.put("money", useCardDTO.getUseCardCount() * 0.3);
                userDao.updateCashBackBonusByMap(map);
                MoneyLog moneyLog = new MoneyLog(
                        user.getSuperior3(), Double.valueOf(df.format(useCardDTO.getUseCardCount() * 0.3)), MoneyLogConstant.YONGHU, MoneyLogConstant.SHUAKA,
                        user.getUserId(), 0, 0D);
                moneyLogs.add(moneyLog);
            }
            /**
             * 自身刷卡奖励
             * 后台录入，用户电话，每次刷卡金额，pos机类型，刷卡次数，自身刷卡奖励（刷卡金额*返率=扣率-费率），并计算上级奖励
             */
            for (int i = 0; i < useCardDTO.getMoneys().size(); i++) {
                // 自身刷卡奖励（刷卡金额*返率=扣率-费率）
                double onemoney = useCardDTO.getMoneys().get(i) * (useCardDTO.getDeductionate() - useCardDTO.getRate());
                MoneyLog moneyLog = new MoneyLog(
                        user.getUserId(), Double.valueOf(df.format(onemoney)), MoneyLogConstant.YONGHU, MoneyLogConstant.SHUAKA,
                        user.getUserId(), useCardDTO.getPosId(), useCardDTO.getMoneys().get(i));
                // if(moneyLogs.size() <= useCardDTO.getMoneys().size()){
                moneyLogs.add(moneyLog);
                // }
                moneys += onemoney;
            }
            if (moneys > 0) {
                flag = true;
                map.put("userId", user.getUserId());
                map.put("money", moneys);
                userDao.updateUseCardBonus(map);
            }
        }
        if (flag) {
            moneyLogDao.addList(moneyLogs);
        }
        return new MyResponse();
    }

    private static final DecimalFormat df = new DecimalFormat("######0.00000000");

    /**
     * 根据当前用户查看直属下级
     *
     * @param map
     * @return
     */
    @Override
    public MyResponse selectSubordinate(Map map) {
        List<User> result = userDao.selectSubordinate(map);
        return new MyResponse(result);
    }

    /**
     * 合伙人登陆后台
     *
     * @param dto
     * @return`
     */
    @Override
    public MyResponse userLogin(LoginDTO dto) {
        User user = userDao.login(dto);
        if (user == null) {
            return new MyResponse("用户名或密码错误，请重新输入", 0);
        }
        Map<String, Object> result = MapUtils.objectToMap(user);
        result.put("isManager", false);
        return new MyResponse(result);
    }

    /**
     * 购买 pos 机
     *
     * @param productsLog
     * @return
     */
    @Override
    public MyResponse buyPosji(ProductsLog productsLog) {
        int i = productsLogDao.insert(productsLog);
        MoneyLog log = new MoneyLog(0, 199D, MoneyLogConstant.PINGTAI, MoneyLogConstant.BUYPOSJI, productsLog.getUserId(), 0, 0D);
        moneyLogDao.add(log);
        return new MyResponse();
    }

    /**
     * 购买 POS 机用户聊表（已激活合伙人的不统计）
     *
     * @return
     */
    @Override
    public MyResponse buyPosjiUserList(StatusDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        List<User> list = productsLogDao.list(dto);
        PageInfo result = new PageInfo(list);
        return new MyResponse(result);
    }

    /**
     * pos 机发货列表
     *
     * @return
     */
    @Override
    public MyResponse shipList(StatusDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        List<Map> map = productsLogDao.shipList(dto);
        PageInfo result = new PageInfo(map);
        return new MyResponse(result);
    }


    /**
     * 平台收益记录
     *
     * @return
     */
    @Override
    public MyResponse ptuy(Page page) {
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<MoneyLog> list = moneyLogDao.ptList();
        PageInfo result = new PageInfo(list);
        return new MyResponse(result);
    }

    /**
     * 用户收益记录
     *
     * @return
     */
    @Override
    public MyResponse ysuy(IdDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        List<MoneyLog> list = moneyLogDao.uhList(dto);
        PageInfo result = new PageInfo(list);
        return new MyResponse(result);
    }

    /**
     * pos 发货状态改变
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse shipstatus(TYIDDTO dto) {
        int i = productsLogDao.updateStatus(dto);
        if (i == 1) {
            return new MyResponse();
        } else {
            return new MyResponse("操作失败", 0);
        }
    }

    /**
     * 用户刷卡记录
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse yhUseCard(IdDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        List<YHUseCardVO> list = moneyLogDao.yhUseCard(dto);
        PageInfo result = new PageInfo(list);
        return new MyResponse(result);
    }

    /**
     * 后台查看提现记录
     *
     * @param shipLogPageDTO
     * @return
     */
    @Override
    public MyResponse shipLogList(ShipLogPageDTO shipLogPageDTO) {
        PageHelper.startPage(shipLogPageDTO.getPage(), shipLogPageDTO.getPageSize());
        List<ShipLogVo> list = shipLogDao.shipLogList(shipLogPageDTO);
        PageInfo result = new PageInfo(list);
        return new MyResponse(result);
    }

    /**
     * 更改提现状态
     *
     * @param id
     * @return
     */
    @Override
    public MyResponse editShipLogStatus(TYIDDTO id) {
        int i = shipLogDao.editShipLogStatus(id);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("操作失败", 0);
    }

    /**
     * 奖励说明 图片地址
     *
     * @return
     */
    @Override
    public MyResponse imgsUrlList() {
        List<ImgsUrl> result = imgsUrlDao.list();
        return new MyResponse(result);
    }

    /**
     * 修改图片地址
     *
     * @param imgsUrl
     * @return
     */
    @Override
    public MyResponse editImgsUrl(ImgsUrl imgsUrl) {
        int i = imgsUrlDao.editImgsUrl(imgsUrl);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("操作失败", 0);
    }

    /**
     * excel 交易结果处理
     *
     * @param file
     * @return
     */
    @Override
    public MyResponse excel(MultipartFile file) {

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        InputStream is = null;
        Workbook wb = null;
        try {
            is = file.getInputStream();
            if (".xls".equals(suffixName)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(suffixName)) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = null;
            }
        } catch (Exception e) {
            log.error("excelException", e);
        }
        Sheet sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        Row row = sheet.getRow(0);

        // // @ExcelProperty(index = 5)
        // private Double tranMoney;
        //
        // // @ExcelProperty(index = 7)
        // // 交易日期
        // private String tranDate;
        //
        // // @ExcelProperty(index = 10)
        // // 终端号
        // private String terminalNumber;
        //
        // // @ExcelProperty(index = 12)
        // // 交易结果
        // private String success;
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            if(row.getCell(12).toString().equals("成功")){

            }



        }
        return null;
    }

        /**
         * 用户办卡成功返现奖励
         */
        @Override
        public MyResponse initCreateCard (ListDTO dto){
            if (dto.getInitCreateCardList() == null && dto.getInitCreateCardList().isEmpty()) {
                return new MyResponse();
            }
            List<InitCreateCardDTO> initCreateCardList = dto.getInitCreateCardList();
            for (InitCreateCardDTO initCreateCardDTO : initCreateCardList) {
                // 获取是否记录用信息
                List<CreateCardInfo> createCardInfos = createCardDao.getUserByPhone(initCreateCardDTO.getPhone());
                if (createCardInfos == null) {
                    continue;
                }
                for (CreateCardInfo createCardInfo : createCardInfos) {
                    // 根据电话修改用户立即办卡奖励
                    Map map = new HashMap();
                    map.put("phone", createCardInfo.getPhone());
                    map.put("money", initCreateCardDTO.getBankBonus());
                    Integer userId = createCardInfo.getUserId();
                    map.put("userId", userId);
                    userDao.updatereateCardBonusByPhone(map);
                    // 修改推荐人奖励
                    User user = userDao.getUserByPhone(initCreateCardDTO.getPhone());
                    Map map1 = new HashMap();
                    map.put("userId", user.getSuperior1());
                    map.put("money", initCreateCardDTO.getSuperiorBonus());
                    userDao.updatCereateCardBonusByPId(map1);
                }
            }
            return new MyResponse();
        }
    }
