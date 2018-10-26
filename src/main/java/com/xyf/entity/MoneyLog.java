package com.xyf.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "money_log")
public class MoneyLog implements Serializable {

    private static final long serialVersionUID = 1540368481314L;


    /**
    * 主键
    * 
    * isNullAble:0
    */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
    * 用户id
    * isNullAble:0
    */
    private Integer userId;

    /**
    * 金额
    * isNullAble:0
    */
    private Double money;

    /**
    * 状态
    * 1 : 提现到用户
    * 2 ：提现到平台
    */
    private Integer status;

    /**
     * 金额日志类型
     */
    private Integer type;

    /**
     * 奖励来源
     */
    private Integer fromUserId;

    public MoneyLog(Integer userId, Double money, Integer status, Integer type, Integer fromUserId) {
        this.userId = userId;
        this.money = money;
        this.status = status;
        this.type = type;
        this.fromUserId = fromUserId;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public MoneyLog() {
    }

}
