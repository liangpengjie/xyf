package com.xyf.entity.manager;

import java.io.Serializable;
import java.util.Date;

public class CacheBack implements Serializable {

    private Integer cacheBackId;
    private Date createTime;
    private Integer userId;
    private Double cacheBackBonus;

    public CacheBack() {
    }

    public CacheBack(Date createTime, Integer userId, Double cacheBackBonus) {
        this.createTime = createTime;
        this.userId = userId;
        this.cacheBackBonus = cacheBackBonus;
    }

    public Integer getCacheBackId() {
        return cacheBackId;
    }

    public void setCacheBackId(Integer cacheBackId) {
        this.cacheBackId = cacheBackId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getCacheBackBonus() {
        return cacheBackBonus;
    }

    public void setCacheBackBonus(Double cacheBackBonus) {
        this.cacheBackBonus = cacheBackBonus;
    }
}
