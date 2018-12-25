package com.xyf.service.manager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyf.common.MyResponse;
import com.xyf.dao.RaiseDao;
import com.xyf.dto.Page;
import com.xyf.entity.Raise;
import com.xyf.service.manager.RaiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lmumuj
 * @date 2018-12-19 18:38
 */
@Service("raiseServiceImpl")
public class RaiseServiceImpl implements RaiseService {
    @Autowired
    private RaiseDao raiseDao;

    @Override
    public MyResponse list(Page pageDTO) {
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getPageSize());
        List<Raise> raiseList = raiseDao.findList(pageDTO);
        PageInfo result = new PageInfo(raiseList);
        return new MyResponse(result);
    }

    @Override
    public MyResponse add(Raise raise) {
        int i  = raiseDao.add(raise);
        if(i == 1){
            return new MyResponse();
        }
        return new MyResponse("操作失败",0);
    }

    @Override
    public MyResponse edit(Raise raise) {
        int sum = raiseDao.selectById(raise.getId());
        if(sum == 0){
            return new MyResponse("数据不存在",0);
        }
        int i  = raiseDao.edit(raise);
        if(i == 1){
            return new MyResponse();
        }
        return new MyResponse("操作失败",0);
    }

    @Override
    public MyResponse del(Raise raise) {
        int sum = raiseDao.selectById(raise.getId());
        if(sum == 0){
            return new MyResponse("数据不存在",0);
        }
        int i  = raiseDao.del(raise);
        if(i == 1){
            return new MyResponse();
        }
        return new MyResponse("操作失败",0);
    }
}
