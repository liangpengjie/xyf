package com.xyf.dto;

import javax.validation.constraints.NotNull;

/**
 * @author lmumuj
 * @date 2018-11-23 15:23
 */
public class IdDTO {

    private Integer page = 1;
    private Integer pageSize = 10;

    @NotNull
    private Integer userId;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
