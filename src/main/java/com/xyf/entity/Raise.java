package com.xyf.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 提额
 *
 * @author lmumuj
 * @date 2018-12-19 17:43
 */
public class Raise implements Serializable {

    private Integer id;
    private Integer userId;
    // 想要提额金额
    private Double wantMoney;
    // 现有可用额度
    private Double currentMoney;
    // 最高额度
    private Double maxMoney;
    private String bankName;
    // 卡片有效日期
    private String validData;

    @JsonFormat(pattern = "yyyy-MM-dd MM:hh:ss")
    private Date data;
    // 0, 未处理; 1也处理
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getWantMoney() {
        return wantMoney;
    }

    public void setWantMoney(Double wantMoney) {
        this.wantMoney = wantMoney;
    }

    public Double getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(Double currentMoney) {
        this.currentMoney = currentMoney;
    }

    public Double getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(Double maxMoney) {
        this.maxMoney = maxMoney;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getValidData() {
        return validData;
    }

    public void setValidData(String validData) {
        this.validData = validData;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}