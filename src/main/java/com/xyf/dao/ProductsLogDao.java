package com.xyf.dao;

import com.xyf.dto.InitPartnerDTO;
import com.xyf.dto.StatusDTO;
import com.xyf.dto.TYIDDTO;
import com.xyf.entity.ProductsLog;
import com.xyf.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author lmumuj
 * @date 2018-11-22 16:34
 */
public interface ProductsLogDao {


    int insert(ProductsLog productsLog);

    int updateStatus(TYIDDTO userId);

    List<User> list(StatusDTO dto);

    List<Map> shipList(StatusDTO dto);

    void updateTerminal(InitPartnerDTO dto);
}
