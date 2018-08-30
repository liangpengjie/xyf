package com.xyf.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 木木
 * @date 2018/8/25 18:15
 * @description
 */
public class Card implements Serializable {

    private Integer cardId;
    //卡号
    private String cardNumber;
    //银行名
    private String bankName;
    //插卡人姓名
    private String cardUserName;
    //插卡人id
    private Integer cardUserId;
    //卡余额
    private Double bankBonus;
    //账单日
    private Date statementDate;
    //还款日
    private Date repaymentDate;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardUserName() {
        return cardUserName;
    }

    public void setCardUserName(String cardUserName) {
        this.cardUserName = cardUserName;
    }

    public Integer getCardUserId() {
        return cardUserId;
    }

    public void setCardUserId(Integer cardUserId) {
        this.cardUserId = cardUserId;
    }

    public Double getBankBonus() {
        return bankBonus;
    }

    public void setBankBonus(Double bankBonus) {
        this.bankBonus = bankBonus;
    }

    public Date getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(Date statementDate) {
        this.statementDate = statementDate;
    }

    public Date getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
    }
}
