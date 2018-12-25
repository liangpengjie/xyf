package com.xyf.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author lmumuj
 * @date 2018-12-01 15:44
 */
public class MoneyLogVo {

    private Integer id;

    private Integer userId;

    private Double money;

    private Integer status;

    private Integer type;

    private String fromUserName;

    private Integer posId = 0;
    private Double useMoney = 0D;

    @JsonFormat(pattern = "yyyy-MM-dd mm:HH:ss")
    private Date time;

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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Integer getPosId() {
        return posId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public Double getUseMoney() {
        return useMoney;
    }

    public void setUseMoney(Double useMoney) {
        this.useMoney = useMoney;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
