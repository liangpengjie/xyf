package com.xyf.dto;

import javax.validation.constraints.NotNull;

/**
 * @author 木木
 * @date 2018/8/30 18:00
 * @description
 */
public class PhoneDTO {

    @NotNull
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
