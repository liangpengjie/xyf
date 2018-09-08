package com.xyf.service.manager.impl;

import com.xyf.common.MD5Utils;
import com.xyf.common.MyResponse;
import com.xyf.dao.BankDao;
import com.xyf.dto.DeleteBankDTO;
import com.xyf.entity.manager.Bank;
import com.xyf.service.manager.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "bankService")
public class BankServiceImpl implements BankService {

    @Autowired
    private BankDao bankDao;
    /**
     * 添加银行
     *
     * @param bank
     * @return
     */
    @Override
    public MyResponse add(Bank bank) {
        int i = bankDao.add(bank);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("添加失败，请重试！", 0);
    }

    /**
     * 修改银行
     *
     * @param bank
     * @return
     */
    @Override
    public MyResponse edit(Bank bank) {
        int i = bankDao.edit(bank);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("修改失败，请重试！", 0);
    }

    /**
     * 删除银行
     *
     * @return
     */
    @Override
    public MyResponse delete(DeleteBankDTO dto) {
        int i = bankDao.delete(dto);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("删除失败，请重试！", 0);
    }

    /**
     * 查看所有银行
     *
     * @return
     */
    @Override
    public MyResponse list() {
        List<Bank> bankList = bankDao.list();
        return new MyResponse(bankList);
    }
}
