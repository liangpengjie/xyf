package com.xyf.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UseCardDTO {
    /**
     * 后台录入，用户电话，每次刷卡金额，pos机类型，刷卡次数，自身刷卡奖励（刷卡金额*pos机费率 ？？？），并计算上级奖励
     */

    @NotNull
    private String phone;
    @NotNull
    /** 刷卡次数*/
    private int useCardCount;
    /** 每次刷卡金额*/
    @NotNull
    private List<Double> moneys;
    /** pos机id*/
    @NotNull
    private Integer posId;
    /** 费率*/
    @NotNull
    private double rate;
    /** 扣率*/
    @NotNull
    private double deductionate;

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

    public List<Double> getMoneys() {
        return moneys;
    }

    public void setMoneys(List<Double> moneys) {
        this.moneys = moneys;
    }

    public Integer getPosId() {
        return posId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getDeductionate() {
        return deductionate;
    }

    public void setDeductionate(double deductionate) {
        this.deductionate = deductionate;
    }
}
