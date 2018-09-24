package com.xyf.service.manager.impl;

import com.xyf.common.MyResponse;
import com.xyf.dao.CreateCardDao;
import com.xyf.dto.CreateCardInfoDTO;
import com.xyf.service.manager.CreateCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("createCardService")
@Transactional(rollbackFor = Exception.class)
public class CreateCardServiceImpl implements CreateCardService {

    @Autowired
    private CreateCardDao createCardDao;

    /**
     * 立即办卡记录用户信息
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse CreateCardInfo(CreateCardInfoDTO dto) {
        int i = createCardDao.CreateCardInfo(dto);
        if(i == 1){
            return new MyResponse();
        }
        return new MyResponse("记录用户信息失败",0);
    }
}