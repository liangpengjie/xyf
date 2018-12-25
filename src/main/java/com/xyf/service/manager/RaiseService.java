package com.xyf.service.manager;

import com.xyf.common.MyResponse;
import com.xyf.dto.Page;
import com.xyf.entity.Raise;

/**
 * @author lmumuj
 * @date 2018-12-19 18:38
 */
public interface RaiseService {
    MyResponse list(Page pageDTO);

    MyResponse add(Raise raise);

    MyResponse edit(Raise raise);

    MyResponse del(Raise raise);
}
