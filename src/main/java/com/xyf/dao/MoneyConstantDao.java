package com.xyf.dao;

import com.xyf.entity.manager.MoneyConstant;

/**
 * @author lmumuj
 * @date 2018-12-24 14:56
 */
public interface MoneyConstantDao {
    MoneyConstant find();

    int edit(MoneyConstant moneyConstant);
}
