package com.xyf.controller.manager;

import com.xyf.common.MyResponse;
import com.xyf.entity.manager.MoneyConstant;
import com.xyf.service.manager.MoneyConstantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmumuj
 * @date 2018-12-24 14:52
 */
@RestController
@RequestMapping("web/api/constant")
public class MoneyConstantContraller {

    @Autowired
    private MoneyConstantService moneyConstantService;

    @PostMapping("/find")
    public MyResponse MoneyConstantList(){
        return moneyConstantService.find();
    }

    @PostMapping("/edit")
    public MyResponse MoneyConstantList(@RequestBody MoneyConstant moneyConstant){
        return moneyConstantService.edit(moneyConstant);
    }
}
