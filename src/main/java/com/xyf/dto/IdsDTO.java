package com.xyf.dto;

import javax.validation.constraints.NotNull;

/**
 * @author lmumuj
 * @date 2018-11-27 16:59
 */
public class IdsDTO {
    @NotNull
    private Integer id;
    @NotNull
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
