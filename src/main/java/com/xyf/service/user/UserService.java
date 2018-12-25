package com.xyf.service.user;

import com.xyf.common.MyResponse;
import com.xyf.dto.*;
import com.xyf.entity.ShipLog;
import com.xyf.entity.UserBank;

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

    /**
     * 查询用户信息
     * @param dto
     * @return
     */
    MyResponse selectUserInfo(UserPhoneDTO dto);

    /**
     * 查询所有用户信息
     * @return
     */
    MyResponse users(PageDTO dto);

    /**
     * 预计收益和实际收益
     * @param dto
     * @return
     */
    MyResponse expectedReturn(IdDTO dto);


    /**
     * 提现收益
     * @param dto
     * @return
     */
    MyResponse cashEarnings(CashEarningsDTO dto);

    /**
     * 立即办卡激活奖励
     * @param dto
     * @return
     */
    MyResponse initCard(InitCardDTO dto);

    /**
     * 用户提现日志
     * @param shipLog
     * @return
     */
    MyResponse addShipLog(ShipLog shipLog);

    /**
     * 用户添加提现银行
     * @param userBank
     * @return
     */
    MyResponse addBank(UserBank userBank);

    /**
     * 用户修改提现银行
     * @param userBank
     * @return
     */
    MyResponse editBank(UserBank userBank);

    /**
     * 用户删除提现银行
     * @param dto
     * @return
     */
    MyResponse delBank(TYIDDTO dto);

    /**
     * 用户提现银行列表
     * @param dto
     * @return
     */
    MyResponse bankList(IdDTO dto);

    /**
     * 更改默认提现银行
     * @param dto
     * @return
     */
    MyResponse editDefaultBank(IdsDTO dto);

    /**
     * 查询用户默认提现银行
     * @param dto
     * @return
     */
    MyResponse selectDefaultBank(IdDTO dto);

    /**
     * 效验电话是否存在
     * @param dto
     * @return
     */
    MyResponse verifyPhone(PhoneDTO dto);
}
