package com.xyf.dao;


import com.xyf.dto.*;
import com.xyf.entity.User;

import java.util.List;

public interface UserDao {


    int insert(AddUserDTO dto);

    User verifyUsername(VerifyUsernameDTO dto);

    User login(LoginDTO dto);

    User getUserByPhone(String phone);

    int updatePassword(LoginDTO dto);

    User getSuperior(Integer superior1);

    int updateUserLevel(InitPartnerDTO dto);

    User getUserById(Integer superior1);

    int updateUserBonus(InitPartnerDTO dto);

    User selectUserInfo(UserPhoneDTO dto);

    List<User> users();
}