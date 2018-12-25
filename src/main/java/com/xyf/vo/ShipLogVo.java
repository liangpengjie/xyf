package com.xyf.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author lmumuj
 * @date 2018-12-01 14:37
 */
public class ShipLogVo {
    private Integer id;

    private String userName;

    private String userRealName;

    private String bankName;

    private String cardId;

    private Double money;

    private String userMoney;

    @JsonFormat(pattern = "yyyy-MM-dd mm:HH:ss")
    private Date createTime;
    //0, 未处理 1，已处理
    private Integer status;

    private Integer txType;

    public Integer getTxType() {
        return txType;
    }

    public void setTxType(Integer txType) {
        this.txType = txType;
    }

    public String getUserMoney() {
        return String.format("%.2f", this.money * 0.8);
    }

    // public void setUserMoney(Double money) {
    //     this.userMoney =  String.format("%.2f", this.money * 0.8);
    // }


    public void setUserMoney(String userMoney) {
        this.userMoney = userMoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
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

    public Double getMoney() {
        return (double) Math.round(money * 100) / 100;
    }

    public void setMoney(Double money) {
        this.money = money;
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
