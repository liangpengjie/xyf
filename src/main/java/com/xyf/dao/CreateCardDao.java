package com.xyf.dao;

import com.xyf.dto.CreateCardInfoDTO;
import com.xyf.entity.manager.CreateCardInfo;

import java.util.List;

public interface CreateCardDao {
    int CreateCardInfo(CreateCardInfoDTO dto);

    List<CreateCardInfo> getUserByPhone(String phone);
}
