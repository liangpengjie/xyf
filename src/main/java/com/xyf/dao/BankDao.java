package com.xyf.dao;

import com.xyf.entity.manager.Bank;

import java.util.List;

public interface BankDao {
    int add(Bank bank);

    int edit(Bank bank);

    int delete(Integer bankId);

    List<Bank> list();
}
