package com.xyf.dto;

import javax.validation.constraints.NotNull;

public class InitCardDTO {

    @NotNull
    private int userId;
    // 推荐办卡奖励
    @NotNull
    private int superiorBonus;
    // 自身办卡奖励
    @NotNull
    private int bankBonus;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSuperiorBonus() {
        return superiorBonus;
    }

    public void setSuperiorBonus(int superiorBonus) {
        this.superiorBonus = superiorBonus;
    }

    public int getBankBonus() {
        return bankBonus;
    }

    public void setBankBonus(int bankBonus) {
        this.bankBonus = bankBonus;
    }
}
