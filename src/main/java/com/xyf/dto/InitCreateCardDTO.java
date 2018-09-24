package com.xyf.dto;

import javax.validation.constraints.NotNull;

public class InitCreateCardDTO {

    @NotNull
    private String phone;
    /** 自身办卡奖励*/
    @NotNull
    private Double bankBonus;
    /** 直接推荐人奖励*/
    @NotNull
    private double superiorBonus;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getBankBonus() {
        return bankBonus;
    }

    public void setBankBonus(Double bankBonus) {
        this.bankBonus = bankBonus;
    }

    public double getSuperiorBonus() {
        return superiorBonus;
    }

    public void setSuperiorBonus(double superiorBonus) {
        this.superiorBonus = superiorBonus;
    }
}
