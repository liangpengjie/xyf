package com.xyf.service.user.impl;

import com.xyf.common.MD5Utils;
import com.xyf.common.MyResponse;
import com.xyf.common.SMSUtils;
import com.xyf.dao.UserDao;
import com.xyf.dto.*;
import com.xyf.entity.User;
import com.xyf.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Service(value = "userService")
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserDao userDao;

    @Override
    public MyResponse addUser(AddUserDTO dto) {
        dto.setPassword(MD5Utils.encryptMD5(dto.getPassword()));
        int i = userDao.insert(dto);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("添加用户失败，请重试！", 0);
    }

    @Override
    public MyResponse verifyUsername(VerifyUsernameDTO dto) {
        User user = userDao.verifyUsername(dto);
        if (user != null) {
            return new MyResponse("用户名已存在，请重新输入！", 0);
        }
        return new MyResponse();
    }

    @Override
    public MyResponse login(LoginDTO dto) {
        dto.setPassword(MD5Utils.encryptMD5(dto.getPassword()));
        User user = userDao.login(dto);
        if (user == null) {
            return new MyResponse("用户名或密码错误，请重新输入！", 0);
        }
        return new MyResponse(user);
    }


    @Override
    public MyResponse phoneLogin(PhoneDTO dto) {
        String randomVcode = SMSUtils.createRandomVcode();
        int i = SMSUtils.sendSMS(dto.getPhone(), randomVcode, "");
        if (i == 0) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(5*60);
            session.setAttribute("verificationCode",randomVcode);
            log.info("验证码："+ randomVcode);
            return new MyResponse("发送成功！",1);
        }
        return new MyResponse("发送失败！",0);
    }

    @Override
    public MyResponse checkCode(PhoneLoginDTO dto) {
        String verificationCode = (String) request.getSession().getAttribute("verificationCode");
        if(StringUtils.equals(dto.getCode(),verificationCode)){
            User user = userDao.getUserByPhone(dto.getPhone());
            return new MyResponse(user);
        }
        return new MyResponse("验证码错误或以超时，请重试！",0);
    }


    /**
     * 效验验证码
     *
     * @param code
     * @return
     */
    @Override
    public MyResponse checkSMSCode(String code) {
        String verificationCode = (String) request.getSession().getAttribute("verificationCode");
        if(StringUtils.equals(code,verificationCode)){
            return new MyResponse(true);
        }
        return new MyResponse("验证码错误或以超时，请重试！",0);
    }

    /**
     * 修改密码
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse updatePassword(LoginDTO dto) {
        dto.setPassword(MD5Utils.encryptMD5(dto.getPassword()));
        int i = userDao.updatePassword(dto);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("修改密码失败，请重试！", 0);
    }

}
