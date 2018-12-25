package com.xyf.dao;

import com.xyf.dto.ShipLogPageDTO;
import com.xyf.dto.TYIDDTO;
import com.xyf.entity.ProductsLog;
import com.xyf.entity.ShipLog;
import com.xyf.entity.User;
import com.xyf.vo.ShipLogVo;

import java.util.List;
import java.util.Map;

/**
 * @author lmumuj
 * @date 2018-11-22 16:34
 */
public interface ShipLogDao {


    int addShipLog(ShipLog shipLog);

    List<ShipLogVo> shipLogList(ShipLogPageDTO shipLogPageDTO);

    int editShipLogStatus(TYIDDTO id);

}
