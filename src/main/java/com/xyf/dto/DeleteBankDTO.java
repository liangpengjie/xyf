package com.xyf.dto;

import javax.validation.constraints.NotNull;

public class DeleteBankDTO {

    @NotNull
    private Integer bankId;

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }
}
