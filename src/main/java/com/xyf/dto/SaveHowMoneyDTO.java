package com.xyf.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 算算省多少
 */
public class SaveHowMoneyDTO {

    @NotNull
    private String posName;
    /** 费率*/
    @NotNull
    @Min(0)
    @Max(value = 1,message = "费率不能大于1")
    private double rate;
    /** 每月消费额*/
    @NotNull
    @Min(0)
    private double money;
    /** 手续费*/
    @NotNull
    @Min(0)
    private int count;

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
