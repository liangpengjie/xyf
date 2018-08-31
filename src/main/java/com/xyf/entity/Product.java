package com.xyf.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 木木
 * @date 2018/8/31 11:52
 * @description
 */
public class Product implements Serializable {

    private Integer productId;
    private Integer userId;
    private String productName;
    /** 产品收益 */
    private double revenue;
    /** 状态   0：失效    1：有效(激活时默认有效)*/
    private Integer status;
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 预留字段 */
    private String back1;
    private String back2;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBack1() {
        return back1;
    }

    public void setBack1(String back1) {
        this.back1 = back1;
    }

    public String getBack2() {
        return back2;
    }

    public void setBack2(String back2) {
        this.back2 = back2;
    }
}
