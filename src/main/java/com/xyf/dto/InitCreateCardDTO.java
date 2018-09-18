package com.xyf.dto;

import javax.validation.constraints.NotNull;

public class InitCreateCardDTO {

    @NotNull
    private String phone;
    @NotNull
    private double money;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
