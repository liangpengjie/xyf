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
    // 提现类型
    private Integer txType = 0;

    public Integer getTxType() {
        return txType;
    }

    public void setTxType(Integer txType) {
        this.txType = txType;
    }

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
