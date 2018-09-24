package com.xyf.entity.manager;

import java.io.Serializable;

public class Bank implements Serializable {

    private Integer bankId;
    private String bankName;
    /** 推荐人返现奖励金额推荐人返现奖励金额,相加计算*/
    private Double bankBonus;
    /** 直接推荐人奖励*/
    private double superiorBonus;
    /** 图片存储路径*/
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getSuperiorBonus() {
        return superiorBonus;
    }

    public void setSuperiorBonus(double superiorBonus) {
        this.superiorBonus = superiorBonus;
    }

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

    public Double getBankBonus() {
        return bankBonus;
    }

    public void setBankBonus(Double bankBonus) {
        this.bankBonus = bankBonus;
    }
}
