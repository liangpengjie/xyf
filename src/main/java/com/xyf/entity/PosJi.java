package com.xyf.entity;

import java.io.Serializable;

public class PosJi implements Serializable {

    private Integer posId;
    private String posName;
    /** 费率*/
    private double rate;
    /** 扣率*/
    private double deductionate;
    /** 详情描述*/
    private String detilText;
    /** 详情图片*/
    private String detilImg;
    /** 使用说明,外站链接*/
    private String instructions4Use;
    /** 标识符地址*/
    private String identifier;

    public Integer getPosId() {
        return posId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

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

    public double getDeductionate() {
        return deductionate;
    }

    public void setDeductionate(double deductionate) {
        this.deductionate = deductionate;
    }

    public String getDetilText() {
        return detilText;
    }

    public void setDetilText(String detilText) {
        this.detilText = detilText;
    }

    public String getDetilImg() {
        return detilImg;
    }

    public void setDetilImg(String detilImg) {
        this.detilImg = detilImg;
    }

    public String getInstructions4Use() {
        return instructions4Use;
    }

    public void setInstructions4Use(String instructions4Use) {
        this.instructions4Use = instructions4Use;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
