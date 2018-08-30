package com.xyf.entity;

import java.io.Serializable;

/**
 * @author 木木
 * @date 2018/8/25 18:14
 * @description
 */
public class User implements Serializable {

    private Integer userId;
    private String username;
    private String password;
    private String phone;
    private String address;
    //囤货台数
    private String goodsNumber;
    //余额
    private Double userBalance;
    //奖金
    private Double userBonus;
    //一级上线
    private Integer superior1;
    //二级上线
    private Integer superior2;
    //三级上线
    private Integer superior3;
    //四级上线
    private Integer superior4;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(Double userBalance) {
        this.userBalance = userBalance;
    }

    public Double getUserBonus() {
        return userBonus;
    }

    public void setUserBonus(Double userBonus) {
        this.userBonus = userBonus;
    }

    public Integer getSuperior1() {
        return superior1;
    }

    public void setSuperior1(Integer superior1) {
        this.superior1 = superior1;
    }

    public Integer getSuperior2() {
        return superior2;
    }

    public void setSuperior2(Integer superior2) {
        this.superior2 = superior2;
    }

    public Integer getSuperior3() {
        return superior3;
    }

    public void setSuperior3(Integer superior3) {
        this.superior3 = superior3;
    }

    public Integer getSuperior4() {
        return superior4;
    }

    public void setSuperior4(Integer superior4) {
        this.superior4 = superior4;
    }
}
