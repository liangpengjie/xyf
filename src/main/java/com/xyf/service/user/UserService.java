package com.xyf.service.user;

import com.xyf.common.MyResponse;
import com.xyf.dto.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public interface UserService {

    /**
     * 注册用户
     * @param dto
     * @return
     */
    MyResponse addUser(AddUserDTO dto);


    /**
     * 效验用户名是否唯一
     * @param dto
     * @return
     */
    MyResponse verifyUsername(VerifyUsernameDTO dto);

    /**
     * 登录
     * @param dto
     * @return
     */
    MyResponse login(LoginDTO dto);

    /**
     * 发送验证码
     * @return
     */
    MyResponse phoneLogin(PhoneDTO dto);

    /**
     * 电话登录
     * @return
     */
    MyResponse checkCode(PhoneLoginDTO dto);

    /**
     * 修改密码
     * @return
     */
    MyResponse updatePassword(LoginDTO dto);

    /**
     * 效验验证码
     * @param code
     * @return
     */
    MyResponse checkSMSCode(@NotNull String code);
}
