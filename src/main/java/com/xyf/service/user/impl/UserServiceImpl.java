package com.xyf.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyf.common.MD5Utils;
import com.xyf.common.MyResponse;
import com.xyf.common.SMSUtils;
import com.xyf.dao.BankDao;
import com.xyf.dao.CreateCardDao;
import com.xyf.dao.UserDao;
import com.xyf.dto.*;
import com.xyf.entity.User;
import com.xyf.entity.manager.CreateCardInfo;
import com.xyf.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service(value = "userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserDao userDao;
    @Autowired
    private CreateCardDao createCardDao;
    @Autowired
    private BankDao bankDao;

    @Override
    public MyResponse addUser(AddUserDTO dto) {
        dto.setPassword(MD5Utils.encryptMD5(dto.getPassword()));
        dto.setCreateTime(new Date());
        if (dto.getSuperior1() != 0) {
            User superUser = userDao.getSuperior(dto.getSuperior1());
            dto.setSuperior2(superUser.getSuperior1());
            dto.setSuperior3(superUser.getSuperior2());
        }
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
            session.setMaxInactiveInterval(5 * 60);
            session.setAttribute("verificationCode", randomVcode);
            log.info("验证码：" + randomVcode);
            return new MyResponse("发送成功！", 1);
        }
        return new MyResponse("发送失败！", 0);
    }

    @Override
    public MyResponse checkCode(PhoneLoginDTO dto) {
        String verificationCode = (String) request.getSession().getAttribute("verificationCode");
        if (StringUtils.equals(dto.getCode(), verificationCode)) {
            User user = userDao.getUserByPhone(dto.getPhone());
            return new MyResponse(user);
        }
        return new MyResponse("验证码错误或以超时，请重试！", 0);
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
        if (StringUtils.equals(code, verificationCode)) {
            return new MyResponse(true);
        }
        return new MyResponse("验证码错误或以超时，请重试！", 0);
    }

    /**
     * 查询用户信息
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse selectUserInfo(UserPhoneDTO dto) {
        User user = userDao.selectUserInfo(dto);
        if (user != null) {
            return new MyResponse(user);
        }
        return new MyResponse(user);
    }

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Override
    public MyResponse users(PageDTO dto) {
        PageHelper.startPage(dto.getPageNumber(), dto.getPageSize());
        List<User> users = userDao.users(dto);
        PageInfo result = new PageInfo(users);
        return new MyResponse(result);
    }

    /**
     * 预计收益和实际收益
     * 预计收益：
     *      获取用户一共办了几张卡，将每个银行办卡成功奖励相加计算
     * 实际收益：
     *      自己办卡成功后的收益 + 推荐别人办卡成功后的奖励收益
     * @param dto
     * @return
     */
    @Override
    public MyResponse expectedReturn(PhoneDTO dto) {
        List<CreateCardInfo> users = createCardDao.getUserByPhone(dto.getPhone());
        double expectedReturn = 0;
        for (int i = 0; i < users.size(); i++) {
            expectedReturn+= bankDao.getBonusByName(users.get(i).getBankName());
        }
        Map result = new HashMap(4);
        result.put("expectedReturn",expectedReturn);
        User user = userDao.getUserByPhone(dto.getPhone());
        result.put("actualIncome",expectedReturn+user.getCreateCardBonus());
        return new MyResponse(result);
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
