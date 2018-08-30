package com.xyf.dto;

import javax.validation.constraints.NotNull;

/**
 * @author 木木
 * @date 2018/8/26 10:10
 * @description
 */
public class VerifyUsernameDTO {

    @NotNull
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
