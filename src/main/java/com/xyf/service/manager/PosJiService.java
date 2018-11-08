package com.xyf.service.manager;

import com.xyf.common.MyResponse;
import com.xyf.dto.SaveHowMoneyDTO;
import com.xyf.entity.PosJi;

public interface PosJiService {
    MyResponse add(PosJi posJi);

    MyResponse edit(PosJi posJi);

    MyResponse delete(PosJi posJi);

    MyResponse list();

    MyResponse selectById(PosJi posJi);

    MyResponse saveHowMoney(SaveHowMoneyDTO dto);
}
