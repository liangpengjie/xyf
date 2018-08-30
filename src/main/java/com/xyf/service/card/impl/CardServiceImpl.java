package com.xyf.service.card.impl;

import com.xyf.common.MyResponse;
import com.xyf.dao.CardDao;
import com.xyf.dto.AddCardDTO;
import com.xyf.service.card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

/**
 * @author 木木
 * @date 2018/8/26 11:40
 * @description
 */
@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardDao cardDao;

    @Override
    public MyResponse addCard(@Valid AddCardDTO dto) {
        int i = cardDao.addCard(dto);
        if(i == 1){
            return new MyResponse();
        }
        return new MyResponse("添加信用卡失败，请重试",0);
    }
}
