package com.xyf.dto;

import javax.validation.constraints.NotNull;

public class PageDTO {
    @NotNull
    private int pageNumber;
    @NotNull
    private int pageSize;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
