package com.xyf.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class InitPartnerDTO {

    @NotNull
    String phone;

    @NotNull
    Integer level;

    //推广奖励
    Integer monery;

    //返现余额
    Double bonus;

    Integer userId;
    String terminalNumber;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Integer getMonery() {
        return monery;
    }

    public void setMonery(Integer monery) {
        this.monery = monery;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
