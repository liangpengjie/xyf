package com.xyf.service.manager;

import com.xyf.common.MyResponse;
import com.xyf.dto.*;
import com.xyf.entity.ImgsUrl;
import com.xyf.entity.ProductsLog;
import com.xyf.entity.manager.ManagerUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


public interface ManagerService {


    /**
     * 添加管理员
     * @param dto
     * @return
     */
    MyResponse add(ManagerDTO dto);

    /**
     * 账号密码登录
     * @param dto
     * @return
     */
    MyResponse login(ManagerDTO dto);

    /**
     * 修改密码
     * @param dto
     * @return
     */
    MyResponse edit(ManagerDTO dto);

    /**
     * 删除管理员
     * @param manager
     * @return
     */
    MyResponse delete(ManagerUser manager);

    /**
     * 后台录入数据合伙人激活
     * @param dto
     * @return
     */
    MyResponse initPartner(InitPartnerDTO dto);

    /**
     * 用户办卡成功返现奖励
     * @param dto
     * @return
     */
    MyResponse initCreateCard(ListDTO dto);

    /**
     * 后台录入刷卡奖励
     * @param dto
     * @return
     */
    MyResponse initUserCradBonus(ListDTO dto);

    /**
     * 根据当前用户查看直属下级
     * @param map
     * @return
     */
    MyResponse selectSubordinate(Map map);

    /**
     * 合伙人登陆后台
     * @param dto
     * @return
     */
    MyResponse userLogin(LoginDTO dto);

    /**
     * 购买 pos 机
     * @param productsLog
     * @return
     */
    MyResponse buyPosji(ProductsLog productsLog);

    /**
     * 购买 POS 机用户聊表（已激活合伙人的不统计）
     * @return
     */
    MyResponse buyPosjiUserList(StatusDTO dto);

    /**
     * pos 机发货列表
     * @return
     */
    MyResponse shipList(StatusDTO dto);

    /**
     * 平台收益记录
     * @return
     */
    MyResponse ptuy(Page page);

    /**
     * 用户收益记录
     * @return
     */
    MyResponse ysuy(IdDTO dto);

    /**
     * pos 发货状态改变
     * @param dto
     * @return
     */
    MyResponse shipstatus(TYIDDTO dto);

    /**
     * 用户刷卡记录
     * @param dto
     * @return
     */
    MyResponse yhUseCard(IdDTO dto);

    /**
     * 后台查看提现记录
     * @param shipLogPageDTO
     * @return
     */
    MyResponse shipLogList(ShipLogPageDTO shipLogPageDTO);

    /**
     * 更改提现状态
     * @param id
     * @return
     */
    MyResponse editShipLogStatus(TYIDDTO id);

    /**
     * 奖励说明 图片地址
     * @return
     */
    MyResponse imgsUrlList();

    /**
     * 修改图片地址
     * @param imgsUrl
     * @return
     */
    MyResponse editImgsUrl(ImgsUrl imgsUrl);

    /**
     * excel 交易结果处理
     * @param file
     * @return
     */
    MyResponse excel(MultipartFile file);
}
