package com.xyf.controller.manager;

import com.xyf.common.MyResponse;
import com.xyf.dto.*;
import com.xyf.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping(value = "web/api/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerServiceService;

    /**
     * 添加管理员
     * @param dto
     * @return
     */
    @PostMapping("/add")
    public MyResponse add(@RequestBody @Valid ManagerDTO dto) {
        return managerServiceService.add(dto);
    }


    /**
     * 账号密码登录
     * @param dto
     * @return
     */
    @PostMapping("/login")
    public MyResponse login(@RequestBody @Valid ManagerDTO dto) {
        return managerServiceService.login(dto);
    }

    /**
     * 修改密码
     * @param dto
     * @return
     */
    @PostMapping("/edit")
    public MyResponse edit(@RequestBody @Valid ManagerDTO dto) {
        return managerServiceService.edit(dto);
    }



}
