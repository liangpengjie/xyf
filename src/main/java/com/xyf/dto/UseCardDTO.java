package com.xyf.dto;

import javax.validation.constraints.NotNull;

public class UseCardDTO {

    @NotNull
    private String phone;
    @NotNull
    private int useCardCount;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUseCardCount() {
        return useCardCount;
    }

    public void setUseCardCount(int useCardCount) {
        this.useCardCount = useCardCount;
    }
}
