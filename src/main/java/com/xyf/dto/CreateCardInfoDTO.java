package com.xyf.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class CreateCardInfoDTO {

    @NotNull
    private int userId;
    @NotNull
    private String realName;
    @NotNull
    private String idNumber;
    @NotNull
    private String phone;
    @NotNull
    private String bankName;

    private Date createTime;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        if (this.createTime == null) {
            return new Date();
        } else {
            return createTime;
        }
    }


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
