package com.xyf.service.card.impl;

import com.xyf.common.MyResponse;
import com.xyf.dao.CardDao;
import com.xyf.dto.AddCardDTO;
import com.xyf.dto.UserPhoneDTO;
import com.xyf.entity.Card;
import com.xyf.service.card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author 木木
 * @date 2018/8/26 11:40
 * @description
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CardServiceImpl implements CardService {

    @Autowired
    private CardDao cardDao;

    @Override
    public MyResponse addCard(@Valid AddCardDTO dto) {
        dto.setCreateTime(new Date());
        int i = cardDao.addCard(dto);
        if(i == 1){
            return new MyResponse();
        }
        return new MyResponse("添加信用卡失败，请重试",0);
    }

    /**
     * 查询信用卡信息
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse cardList(UserPhoneDTO dto) {
        List<Card> cardList = cardDao.cardList(dto);
        return new MyResponse(cardList);
    }

    /**
     * 凭借信用卡id删除信用卡
     *
     * @param card
     * @return
     */
    @Override
    public MyResponse delete(Card card) {
        int i = cardDao.delete(card);
        if(i == 1){
            return new MyResponse();
        }
        return new MyResponse("删除信用卡失败，请重试",0);
    }
}
