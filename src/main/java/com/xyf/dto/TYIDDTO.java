package com.xyf.dto;

import javax.validation.constraints.NotNull;

/**
 * @author lmumuj
 * @date 2018-11-25 13:22
 */
public class TYIDDTO {

    @NotNull
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
