package com.xyf.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CashEarningsDTO {

    @NotNull
    private Integer userId;
    /** 提现收益金额*/
    @NotNull
    @Min(120)
    private Double cashEarningsMoney;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getCashEarningsMoney() {
        return cashEarningsMoney;
    }

    public void setCashEarningsMoney(Double cashEarningsMoney) {
        this.cashEarningsMoney = cashEarningsMoney;
    }
}
