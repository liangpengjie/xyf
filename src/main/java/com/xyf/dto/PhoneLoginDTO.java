package com.xyf.dto;

import javax.validation.constraints.NotNull;

/**
 * @author 木木
 * @date 2018/8/30 16:27
 * @description
 */
public class PhoneLoginDTO {

    @NotNull
    private String phone;
    @NotNull
    private String code;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
