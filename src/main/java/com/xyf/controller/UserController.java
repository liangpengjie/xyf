package com.xyf.controller;

import com.xyf.common.MyResponse;
import com.xyf.dto.*;
import com.xyf.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping(value = "web/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public MyResponse addUser(@RequestBody @Valid AddUserDTO dto) {
        return userService.addUser(dto);
    }

    @PostMapping("/verifyUsername")
    public MyResponse verifyUsername(@RequestBody @Valid VerifyUsernameDTO dto) {
        return userService.verifyUsername(dto);
    }

    /**
     * 账号密码登录
     * @param dto
     * @return
     */
    @PostMapping("/baselogin")
    public MyResponse login(@RequestBody @Valid LoginDTO dto) {
        return userService.login(dto);
    }

    /**
     * 发送验证码
     * @return
     */
    @PostMapping("/sendSMS")
    public MyResponse phoneLogin(@RequestBody @Valid PhoneDTO dto) {
        return userService.phoneLogin(dto);
    }

    /**
     * 电话登陆
     * @param dto
     * @return
     */
    @PostMapping("/phoneLogin")
    public MyResponse checkCode(@RequestBody @Valid PhoneLoginDTO dto) {
        return userService.checkCode(dto);
    }

    /**
     * 效验验证码
     * @param code
     * @return
     */
    @PostMapping("/checkCode")
    public MyResponse checkSMSCode(@RequestBody @NotNull String code) {
        return userService.checkSMSCode(code);
    }


    /**
     * 根据电话修改密码
     * @param dto
     * @return
     */
    @PostMapping("/updatePassword")
    public MyResponse checkSMSCode(@RequestBody @Valid LoginDTO dto) {
        return userService.updatePassword(dto);
    }


    /**
     * 查询用户
     * @param dto
     * @return
     */
    @PostMapping("/selectUserInfo")
    public MyResponse selectUserInfo(@RequestBody @Valid UserPhoneDTO dto) {
        return userService.selectUserInfo(dto);
    }

    /**
     * 所有用户信息
     * @return
     */
    @PostMapping("/users")
    public MyResponse users(@RequestBody @Valid PageDTO dto) {
        return userService.users(dto);
    }
}
