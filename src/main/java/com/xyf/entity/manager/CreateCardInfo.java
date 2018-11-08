package com.xyf.entity.manager;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class CreateCardInfo implements Serializable {

    private Integer userId;
    private String idNumber;
    private String phone;
    private String realName;
    private String bankName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Integer status;
    private double superiorBonus;
    private double bankBonus;

    public double getBankBonus() {
        return bankBonus;
    }

    public void setBankBonus(double bankBonus) {
        this.bankBonus = bankBonus;
    }

    public double getSuperiorBonus() {
        return superiorBonus;
    }

    public void setSuperiorBonus(double superiorBonus) {
        this.superiorBonus = superiorBonus;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
