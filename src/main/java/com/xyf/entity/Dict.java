package com.xyf.entity;

import java.io.Serializable;

/**
 * @Auther: 木木
 * @Date: 2018/8/31 16:28
 * @Description:
 */
public class Dict implements Serializable {

    private Integer id;
    private String name;
    /** 产品收益 */
    private double revenue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}
