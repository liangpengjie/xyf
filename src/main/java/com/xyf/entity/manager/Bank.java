package com.xyf.entity.manager;

import java.io.Serializable;

public class Bank implements Serializable {

    private Integer bankId;
    private String bankName;
    /** 推荐人返现奖励金额推荐人返现奖励金额,相加计算*/
    private String bankBonucs;

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBonucs() {
        return bankBonucs;
    }

    public void setBankBonucs(String bankBonucs) {
        this.bankBonucs = bankBonucs;
    }
}
