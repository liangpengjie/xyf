package com.xyf.service.manager.impl;

import com.xyf.common.MyResponse;
import com.xyf.dao.PosJiDao;
import com.xyf.entity.PosJi;
import com.xyf.service.manager.PosJiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "posJiService")
@Transactional(rollbackFor = Exception.class)
public class PosJiServiceImpl implements PosJiService {

    @Autowired
    private PosJiDao posJiDao;

    @Override
    public MyResponse add(PosJi posJi) {
        int i = posJiDao.add(posJi);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("操作失败，请重试！", 0);
    }

    @Override
    public MyResponse edit(PosJi posJi) {
        int i = posJiDao.edit(posJi);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("操作失败，请重试！", 0);
    }

    @Override
    public MyResponse delete(PosJi posJi) {
        int i = posJiDao.delete(posJi);
        if (i == 1) {
            return new MyResponse();
        }
        return new MyResponse("操作失败，请重试！", 0);
    }

    @Override
    public MyResponse list() {
        List<PosJi> posJiList= posJiDao.list();
        return new MyResponse(posJiList);
    }

    @Override
    public MyResponse selectById(PosJi posJi) {
        PosJi result = posJiDao.selectById(posJi);
        return new MyResponse(result);
    }
}

