package com.xyf.dao;

import com.xyf.dto.CreateCardInfoDTO;
import com.xyf.entity.manager.CreateCardInfo;

public interface CreateCardDao {
    int CreateCardInfo(CreateCardInfoDTO dto);

    CreateCardInfo getUserByPhone(String phone);
}
