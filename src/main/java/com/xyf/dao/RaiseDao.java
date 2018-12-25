package com.xyf.dao;

import com.xyf.dto.Page;
import com.xyf.entity.Raise;

import java.util.List;

/**
 * @author lmumuj
 * @date 2018-12-19 18:39
 */
public interface RaiseDao {
    List<Raise> findList(Page pageDTO);

    int add(Raise raise);

    int selectById(Integer id);

    int edit(Raise raise);

    int del(Raise raise);
}
