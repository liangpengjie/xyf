package com.xyf.entity;

import java.io.Serializable;
import java.util.Date;

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
    // 囤货台数
    private String goodsNumber;
    //余额
    private Double userBalance;
    // 推广奖金
    private Double userBonus;
    //推荐办卡奖金
    private Double createCardBonus;
    // 刷卡奖金
    private Double useCardBonus;
    // 刷卡返现收益
    private Double cashBackBonus;
    // 一级上线
    private Integer superior1;
    // 二级上线
    private Integer superior2;
    // 三级上线
    private Integer superior3;
    //  用户等级    0：普通用户  9:合伙人  1：银牌代理     2：金牌代理    3： 钻石代理
    private Integer level;
    private Date createTime;

    public Double getCashBackBonus() {
        return cashBackBonus;
    }

    public void setCashBackBonus(Double cashBackBonus) {
        this.cashBackBonus = cashBackBonus;
    }

    public Double getCreateCardBonus() {
        return createCardBonus;
    }

    public void setCreateCardBonus(Double createCardBonus) {
        this.createCardBonus = createCardBonus;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getUseCardBonus() {
        return useCardBonus;
    }

    public void setUseCardBonus(Double useCardBonus) {
        this.useCardBonus = useCardBonus;
    }

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

}
