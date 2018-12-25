package com.xyf.dao;

import com.xyf.entity.ImgsUrl;

import java.util.List;

/**
 * @author lmumuj
 * @date 2018-12-02 18:40
 */
public interface ImgsUrlDao {
    List<ImgsUrl> list();

    int editImgsUrl(ImgsUrl imgsUrl);
}
