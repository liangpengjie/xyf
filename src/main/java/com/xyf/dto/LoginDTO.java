package com.xyf.dto;

import javax.validation.constraints.NotNull;

/**
 * @author 木木
 * @date 2018/8/26 10:48
 * @description
 */
public class LoginDTO {

    @NotNull
    private String password;
    @NotNull
    private String phone;

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
}
