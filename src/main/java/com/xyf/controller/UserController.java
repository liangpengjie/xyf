package com.xyf.controller;

import com.xyf.common.MyResponse;
import com.xyf.dto.*;
import com.xyf.entity.ShipLog;
import com.xyf.entity.UserBank;
import com.xyf.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;


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
     * 效验电话是否存在
     * @return
     */
    @PostMapping("/verifyPhone")
    public MyResponse verifyPhone(@RequestBody @Valid PhoneDTO dto) {
        return userService.verifyPhone(dto);
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
    public MyResponse checkSMSCode(@RequestBody @NotNull Map<String,String> code) {
        if (code.get("code") == null) {
            return new MyResponse("code为必填参数",0);
        }
        return userService.checkSMSCode(code.get("code"));
    }


    /**
     * 根据电话修改密码
     * @param dto sdf
     * @return
     */
    @PostMapping("/updatePassword")
    public MyResponse checkSMSCode(@RequestBody @Valid LoginDTO dto) {
        return userService.updatePassword(dto);
    }


    /**
     * 查询用户
     * @param dto userPhoneDto
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

    /**
     * 预计收益和实际收益
     * @return
     */
    @PostMapping("/expectedReturn")
    public MyResponse expectedReturn(@RequestBody @Valid IdDTO dto) {
        return userService.expectedReturn(dto);
    }

    /**
     * 提现收益
     * @return
     */
    @PostMapping("/cashEarnings")
    public MyResponse cashEarnings(@RequestBody @Valid CashEarningsDTO dto) {
        return userService.cashEarnings(dto);
    }

    /**
     * 立即办卡激活奖励
     * @return
     */
    @PostMapping("/initCard")
    public MyResponse initCard(@RequestBody @Valid InitCardDTO dto) {
        return userService.initCard(dto);
    }


    /**
     * 用户添加提现日志
     * @return
     */
    @PostMapping("/addShipLog")
    public MyResponse addShipLog(@RequestBody @Valid ShipLog shipLog) {
        return userService.addShipLog(shipLog);
    }

    /**
     * 用户添加提现银行
     * @return
     */
    @PostMapping("/addBank")
    public MyResponse addBank(@RequestBody @Valid UserBank userBank) {
        return userService.addBank(userBank);
    }

    /**
     * 用户修改提现银行
     * @return
     */
    @PostMapping("/editBank")
    public MyResponse editBank(@RequestBody @Valid UserBank userBank) {
        return userService.editBank(userBank);
    }

    /**
     * 用户删除提现银行
     * @return
     */
    @PostMapping("/delBank")
    public MyResponse delBank(@RequestBody @Valid TYIDDTO dto) {
        return userService.delBank(dto);
    }

    /**
     * 用户提现银行列表
     * @return
     */
    @PostMapping("/bankList")
    public MyResponse bankList(@RequestBody @Valid IdDTO dto) {
        return userService.bankList(dto);
    }

    /**
     * 更改默认提现银行
     * @return
     */
    @PostMapping("/editDefaultBank")
    public MyResponse editDefaultBank(@RequestBody @Valid IdsDTO dto) {
        return userService.editDefaultBank(dto);
    }

    /**
     * 查询用户默认提现银行
     * @return
     */
    @PostMapping("/selectDefaultBank")
    public MyResponse selectDefaultBank(@RequestBody @Valid IdDTO dto) {
        return userService.selectDefaultBank(dto);
    }
}
