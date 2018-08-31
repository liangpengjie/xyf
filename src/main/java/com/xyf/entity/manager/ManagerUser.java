package com.xyf.entity.manager;

import java.io.Serializable;

/**
 * @auther: 木木
 * @date: 2018/8/31 15:47
 * @description:
 */
public class ManagerUser implements Serializable {

    private Integer userId;
    private String username;
    private String password;
    private Integer status;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
