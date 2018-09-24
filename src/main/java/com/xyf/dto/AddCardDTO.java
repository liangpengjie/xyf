package com.xyf.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author 木木
 * @date 2018/8/26 11:29
 * @description
 */
public class AddCardDTO {

    @NotNull
    private String cardNumber;
    @NotNull
    private String bankName;
    @NotNull
    private String bankId;
    @NotNull
    private Integer cardUserId;
    @NotNull
    @Min(1)
    @Max(28)
    private int statementDate;
    @NotNull
    @Min(1)
    @Max(28)
    private int repaymentDate;

    /** 状态  0：未激活  1：激活    2：废弃 */
    private Integer status = 1;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    private Date createTime;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getCardUserId() {
        return cardUserId;
    }

    public void setCardUserId(Integer cardUserId) {
        this.cardUserId = cardUserId;
    }

    public int getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(int statementDate) {
        this.statementDate = statementDate;
    }

    public int getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(int repaymentDate) {
        this.repaymentDate = repaymentDate;
    }
}
