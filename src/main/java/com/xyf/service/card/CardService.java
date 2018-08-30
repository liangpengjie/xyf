package com.xyf.service.card;


import com.xyf.common.MyResponse;
import com.xyf.dto.AddCardDTO;

import javax.validation.Valid;

/**
 * @author 木木
 * @date 2018/8/26 11:39
 * @description
 */
public interface CardService {
    MyResponse addCard(@Valid AddCardDTO dto);
}
