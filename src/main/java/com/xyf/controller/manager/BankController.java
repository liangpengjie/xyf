package com.xyf.controller.manager;

import com.xyf.common.MyResponse;
import com.xyf.dto.DeleteBankDTO;
import com.xyf.entity.manager.Bank;
import com.xyf.service.manager.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/web/api/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    /**
     * 添加银行
     * @return
     */
    @PostMapping("/add")
    public MyResponse add(@RequestBody @Valid Bank bank) {
        return bankService.add(bank);
    }



    /**
     * 修改银行信息
     * @return
     */
    @PostMapping("/edit")
    public MyResponse edit(@RequestBody @Valid Bank bank) {
        return bankService.edit(bank);
    }

    /**
     * 删除银行信息
     * @return
     */
    @PostMapping("/delete")
    public MyResponse edit(@RequestBody @Valid DeleteBankDTO dto) {
        return bankService.delete(dto);
    }

    /**
     * 查看银行信息
     * @return
     */
    @PostMapping("/list")
    public MyResponse list() {
        return bankService.list();
    }
}
