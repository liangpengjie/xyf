package com.xyf.service.manager;

import com.xyf.common.MyResponse;
import com.xyf.dto.InitPartnerDTO;
import com.xyf.dto.ListDTO;
import com.xyf.dto.LoginDTO;
import com.xyf.dto.ManagerDTO;
import com.xyf.entity.manager.ManagerUser;

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
}
