package com.xyf.service.manager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyf.common.MyResponse;
import com.xyf.dao.CreateCardDao;
import com.xyf.dto.CreateCardInfoDTO;
import com.xyf.dto.Page;
import com.xyf.dto.TYIDDTO;
import com.xyf.entity.manager.CreateCardInfo;
import com.xyf.service.manager.CreateCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * 办卡用户信息列表
     *
     * @return
     */
    @Override
    public MyResponse createCardInfoList(Page page) {
        PageHelper.startPage(page.getPage(),page.getPageSize());
        List<CreateCardInfo> createCardInfoList = createCardDao.createCardInfoList(page);
        PageInfo result = new PageInfo(createCardInfoList);
        return new MyResponse(result);
    }

    /**
     * 删除办卡信息
     *
     * @param dto
     * @return
     */
    @Override
    public MyResponse delete(TYIDDTO dto) {
        int i = createCardDao.delete(dto);
        if(i == 1){
            return new MyResponse();
        }
        return new MyResponse("记录用户信息失败",0);
    }
}
