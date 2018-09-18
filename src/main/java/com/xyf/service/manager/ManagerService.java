package com.xyf.service.manager;

import com.xyf.common.MyResponse;
import com.xyf.dto.InitPartnerDTO;
import com.xyf.dto.*;


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
}
