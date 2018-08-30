package com.xyf.controller;

import com.xyf.common.MyResponse;
import com.xyf.dto.AddCardDTO;
import com.xyf.service.card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 木木
 * @date 2018/8/26 11:09
 * @description
 */
@RestController
@RequestMapping("/web/api/card")
public class CardController {


    @Autowired
    private CardService cardService;

    @PostMapping("/addCard")
    public MyResponse addCard(@Valid @RequestBody AddCardDTO dto){
        return cardService.addCard(dto);
    }

}
