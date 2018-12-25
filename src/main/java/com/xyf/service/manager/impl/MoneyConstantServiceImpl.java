package com.xyf.service.manager.impl;

import com.xyf.common.MyResponse;
import com.xyf.dao.MoneyConstantDao;
import com.xyf.entity.manager.MoneyConstant;
import com.xyf.service.manager.MoneyConstantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lmumuj
 * @date 2018-12-24 14:55
 */
@Service("moneyConstantService")
public class MoneyConstantServiceImpl implements MoneyConstantService {

    @Autowired
    private MoneyConstantDao moneyConstantDao;

    @Override
    public MyResponse find() {
        MoneyConstant moneyConstant = moneyConstantDao.find();
        return new MyResponse(moneyConstant);
    }

    @Override
    public MyResponse edit(MoneyConstant moneyConstant) {
        int i  = moneyConstantDao.edit(moneyConstant);
        if(i == 1 ){
            return new MyResponse();
        }
        return new MyResponse("操作失败",0);
    }
}
