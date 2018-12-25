package com.xyf.service.manager;

import com.xyf.common.MyResponse;
import com.xyf.dto.CreateCardInfoDTO;
import com.xyf.dto.Page;
import com.xyf.dto.TYIDDTO;

public interface CreateCardService {

    /**
     * 立即办卡记录用户信息
     * 
     * @param dto
     * @return
     */
    MyResponse CreateCardInfo(CreateCardInfoDTO dto);

    /**
     * 办卡用户信息列表
     * @return
     */
    MyResponse createCardInfoList(Page page);

    /**
     * 删除办卡信息
     * @param dto
     * @return
     */
    MyResponse delete(TYIDDTO dto);
}
