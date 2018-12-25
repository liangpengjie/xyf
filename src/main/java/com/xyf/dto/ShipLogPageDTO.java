package com.xyf.dto;

import javax.validation.constraints.NotNull;

/**
 * @author lmumuj
 * @date 2018-12-01 14:31
 */
public class ShipLogPageDTO {

    @NotNull
    private Integer page;
    @NotNull
    private Integer pageSize;
    // 1 ,已转账 0.未转账
    @NotNull
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
