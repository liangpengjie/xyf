package com.xyf.dao;

import com.xyf.dto.IdDTO;
import com.xyf.dto.IdsDTO;
import com.xyf.dto.TYIDDTO;
import com.xyf.entity.UserBank;

import java.util.List;

/**
 * @author lmumuj
 * @date 2018-11-27 11:15
 */
public interface UserBankDao {
    int addBank(UserBank userBank);

    int editBank(UserBank userBank);

    int delBank(TYIDDTO dto);

    List<UserBank> bankList(IdDTO dto);

    int editDefaultBank(IdsDTO dto);

    void editDefaultBank0(IdsDTO dto);

    int count(UserBank userBank);

    UserBank selectDefaultBank(IdDTO dto);
}
