package com.xyf.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author lmumuj
 * @date 2018-11-23 16:28
 */
public class ShipLog {

    private Integer id;
    @NotNull
    private Integer userId;
    @NotNull
    private String userName;
    @NotNull
    private String bankName;
    @NotNull
    private String cardId;
    @NotNull
    private Double money;

    private Double userMoney;
    @JsonFormat(pattern = "yyyy-MM-dd mm:HH:ss")
    private Date createTime;
    //0, 未处理 1，已处理
    private Integer status;
    // 交易类型
    @NotNull
    private Integer txType;

    public Integer getTxType() {
        return txType;
    }

    public void setTxType(Integer txType) {
        this.txType = txType;
    }

    public Double getUserMoney() {
        return this.money * 0.8;
    }

    public void setUserMoney(Double userMoney) {
        this.userMoney = this.money * 0.8;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
