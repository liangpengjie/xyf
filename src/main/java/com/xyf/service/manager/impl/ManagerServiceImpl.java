package com.xyf.service.manager.impl;

import com.xyf.common.MD5Utils;
import com.xyf.common.MyResponse;
import com.xyf.dao.ManagerDao;
import com.xyf.dto.ManagerDTO;
import com.xyf.entity.manager.ManagerUser;
import com.xyf.service.manager.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value = "managerService")
public class ManagerServiceImpl implements ManagerService {
    private static final Logger log = LoggerFactory.getLogger(ManagerServiceImpl.class);

    @Autowired
    private ManagerDao managerDao;

    /**
     * 添加管理员
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse add(ManagerDTO dto) {
        dto.setPassword(MD5Utils.encryptMD5(dto.getPassword()));
        int i = managerDao.add(dto);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("添加管理员失败，请重试！", 0);
    }

    /**
     * 账号密码登录
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse login(ManagerDTO dto) {
        dto.setPassword(MD5Utils.encryptMD5(dto.getPassword()));
        ManagerUser user = managerDao.login(dto);
        if (user == null) {
            return new MyResponse("用户名或密码错误，请重新输入！", 0);
        }
        return new MyResponse(user);
    }

    /**
     * 修改密码
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse edit(ManagerDTO dto) {
        dto.setPassword(MD5Utils.encryptMD5(dto.getPassword()));
        int i = managerDao.updatePassword(dto);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("修改密码失败，请重试！", 0);
    }
}
