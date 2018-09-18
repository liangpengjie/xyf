package com.xyf.service.card;


import com.xyf.common.MyResponse;
import com.xyf.dto.AddCardDTO;
import com.xyf.dto.UserPhoneDTO;

import javax.validation.Valid;

/**
 * @author 木木
 * @date 2018/8/26 11:39
 * @description
 */
public interface CardService {

    /**
     * 添加信用卡
     *
     * @param dto
     * @return
     */
    MyResponse addCard(@Valid AddCardDTO dto);

    /**
     * 查询信用卡信息
     *
     * @param dto
     * @return
     */
    MyResponse cardList(UserPhoneDTO dto);
}
