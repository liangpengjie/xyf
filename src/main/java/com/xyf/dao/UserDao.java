package com.xyf.dao;


import com.xyf.dto.AddUserDTO;
import com.xyf.dto.LoginDTO;
import com.xyf.dto.VerifyUsernameDTO;
import com.xyf.entity.User;

public interface UserDao {


    int insert(AddUserDTO dto);

    User verifyUsername(VerifyUsernameDTO dto);

    User login(LoginDTO dto);

    User getUserByPhone(String phone);

    int updatePassword(LoginDTO dto);

    User getSuperior(Integer superior1);
}