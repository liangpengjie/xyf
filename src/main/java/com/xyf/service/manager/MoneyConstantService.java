package com.xyf.service.manager;

import com.xyf.common.MyResponse;
import com.xyf.entity.manager.MoneyConstant;

/**
 * @author lmumuj
 * @date 2018-12-24 14:55
 */
public interface MoneyConstantService {

    MyResponse find();

    MyResponse edit(MoneyConstant moneyConstant);
}
