package com.xyf.service.manager;

import com.xyf.common.MyResponse;
import com.xyf.dto.*;

import javax.validation.constraints.NotNull;


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
}
