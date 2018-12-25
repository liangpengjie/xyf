package com.xyf.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lmumuj
 * @date 2018-11-22 15:59
 */
public class ProductsLog implements Serializable {

    private Integer id;
    @NotNull
    private Integer userId;
    @NotNull
    private String userPhone;
    @NotNull
    private String userAddress;
    private String userName;
    private Integer posId1 = 0;
    private String posName1;
    @JsonFormat(pattern = "yyyy-MM-dd mm:HH:ss")
    private Date createTime;
    // 0,未发货  1,已发货
    private int status;

    // 终端号
    private String terminalNumber;

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getPosName1() {
        return posName1;
    }

    public void setPosName1(String posName1) {
        this.posName1 = posName1;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Integer getPosId1() {
        return posId1;
    }

    public void setPosId1(Integer posId1) {
        this.posId1 = posId1;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
