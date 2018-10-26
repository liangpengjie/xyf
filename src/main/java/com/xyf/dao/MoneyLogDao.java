package com.xyf.dao;

import com.xyf.entity.MoneyLog;

import java.util.List;

public interface MoneyLogDao{
    int add(MoneyLog moneyLog);
    int addList(List<MoneyLog> moneyLogs);
}
