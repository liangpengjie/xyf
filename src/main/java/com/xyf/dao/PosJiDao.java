package com.xyf.dao;

import com.xyf.entity.PosJi;

import java.util.List;

public interface PosJiDao {
    int add(PosJi posJi);

    int edit(PosJi posJi);

    int delete(PosJi posJi);

    List<PosJi> list();

    PosJi selectById(PosJi posJi);
}
