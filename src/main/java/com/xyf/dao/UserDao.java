package com.xyf.dao;


import com.xyf.dto.*;
import com.xyf.entity.User;

import java.util.List;
import java.util.Map;

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

    List<User> users(PageDTO dto);

    List<User> getUsersByIds(List list);

    void updatereateCardBonusByPhone(Map map);

    void updateUseCardBonus(Map map);

    List<User> selectSubordinate(Map map);

    void updatCereateCardBonusByPId(Map map1);

    List<User> selectLevelId(Integer userId);

    void updateCashBackBonus(CashEarningsDTO dto);

    void updateLever(InitPartnerDTO dto);

    int verifyPhone(PhoneDTO dto);

    void minCashBackBonus(CashEarningsDTO dto);

    void updateCashBackBonusByMap(Map map);

    void minCreateCardBonus(CashEarningsDTO dto);

    void minUserBonus(CashEarningsDTO dto);

    void minuseCardBonus(CashEarningsDTO dto);
}