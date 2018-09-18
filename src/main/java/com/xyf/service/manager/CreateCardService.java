package com.xyf.service.manager;

import com.xyf.common.MyResponse;
import com.xyf.dto.CreateCardInfoDTO;

public interface CreateCardService {

    /**
     * 立即办卡记录用户信息
     * 
     * @param dto
     * @return
     */
    MyResponse CreateCardInfo(CreateCardInfoDTO dto);
}
