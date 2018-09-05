package com.xyf.dao;

import com.xyf.dto.ManagerDTO;
import com.xyf.entity.manager.ManagerUser;

public interface ManagerDao {
    int add(ManagerDTO dto);

    ManagerUser login(ManagerDTO dto);

    int updatePassword(ManagerDTO dto);
}
