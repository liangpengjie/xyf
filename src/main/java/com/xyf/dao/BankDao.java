package com.xyf.dao;

import com.xyf.dto.DeleteBankDTO;
import com.xyf.entity.manager.Bank;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BankDao extends Mapper<Bank> {
    int add(Bank bank);

    int edit(Bank bank);

    int deletes(DeleteBankDTO dto);

    List<Bank> list();

    double getBonusByName(String bankName);
}
