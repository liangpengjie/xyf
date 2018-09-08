package com.xyf.dto;

import javax.validation.constraints.NotNull;

public class UserPhoneDTO {

    @NotNull
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
