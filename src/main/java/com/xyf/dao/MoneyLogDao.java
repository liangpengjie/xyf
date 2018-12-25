package com.xyf.dao;

import com.xyf.dto.IdDTO;
import com.xyf.entity.MoneyLog;
import com.xyf.vo.YHUseCardVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MoneyLogDao{
    int add(MoneyLog moneyLog);
    int addList(@Param("moneyLogs")List<MoneyLog> moneyLogs);

    List<MoneyLog> ptList();

    List<MoneyLog> uhList(IdDTO dto);

    List<YHUseCardVO> yhUseCard(IdDTO dto);

    double sjuy(String phone);
}
