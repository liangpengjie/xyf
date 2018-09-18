package com.xyf.dao;

import com.xyf.dto.AddCardDTO;
import com.xyf.dto.UserPhoneDTO;
import com.xyf.entity.Card;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 木木
 * @date 2018/8/26 11:40
 * @description
 */
public interface CardDao {
    int addCard(AddCardDTO dto);

    List<Card> cardList(UserPhoneDTO dto);
}
