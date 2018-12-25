package com.xyf.dto;

import javax.validation.constraints.NotNull;

/**
 * @author lmumuj
 * @date 2018-11-25 13:09
 */
public class StatusDTO {


    private Integer page = 1;

    private Integer pageSize = 10;
    @NotNull
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
