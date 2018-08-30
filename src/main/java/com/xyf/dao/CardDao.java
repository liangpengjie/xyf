package com.xyf.dao;

import com.xyf.dto.AddCardDTO;

import javax.validation.Valid;

/**
 * @author 木木
 * @date 2018/8/26 11:40
 * @description
 */
public interface CardDao {
    int addCard(@Valid AddCardDTO dto);
}
