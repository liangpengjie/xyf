package com.xyf.dao;

import com.xyf.dto.DeleteBankDTO;
import com.xyf.entity.manager.Bank;

import java.util.List;

public interface BankDao {
    int add(Bank bank);

    int edit(Bank bank);

    int delete(DeleteBankDTO dto);

    List<Bank> list();
}
