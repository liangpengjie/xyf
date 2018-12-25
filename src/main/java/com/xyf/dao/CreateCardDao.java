package com.xyf.dao;

import com.xyf.dto.CreateCardInfoDTO;
import com.xyf.dto.Page;
import com.xyf.dto.TYIDDTO;
import com.xyf.entity.manager.CreateCardInfo;

import java.util.List;
import java.util.Map;

public interface CreateCardDao {
    int CreateCardInfo(CreateCardInfoDTO dto);

    List<CreateCardInfo> getUserByPhone(String phone);

    List<CreateCardInfo> createCardInfoList(Page page);

    void updateStatus(Map map);

    int delete(TYIDDTO dto);

    double yjuy(String phone);

    double sjuy(String phone);

    double yjuyById(Integer userId);
}
