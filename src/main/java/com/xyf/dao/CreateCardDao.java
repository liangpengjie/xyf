package com.xyf.dao;

import com.xyf.dto.CreateCardInfoDTO;
import com.xyf.entity.manager.CreateCardInfo;

import java.util.List;
import java.util.Map;

public interface CreateCardDao {
    int CreateCardInfo(CreateCardInfoDTO dto);

    List<CreateCardInfo> getUserByPhone(String phone);

    List<CreateCardInfo> createCardInfoList();

    void updateStatus(Map map);
}
