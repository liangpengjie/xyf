package com.xyf.entity.manager;

import javax.persistence.Id;
import java.io.Serializable;

public class Bank implements Serializable {

    @Id
    private Integer bankId;
    private String bankName;
    /** 推荐人返现奖励金额推荐人返现奖励金额,相加计算*/
    private Double bankBonus;
    /** 直接推荐人奖励*/
    private double superiorBonus;
    /** 图片存储路径*/
    private String img;
    // 立即办卡连接
    private String urlCreateCard;
    // 立即办卡连接
    private String urlPlanQuery;
    // 奖励条件
    private String rewardConditions;
    // 状态 0,不展示  1，展示
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRewardConditions() {
        return rewardConditions;
    }

    public void setRewardConditions(String rewardConditions) {
        this.rewardConditions = rewardConditions;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getSuperiorBonus() {
        return superiorBonus;
    }

    public void setSuperiorBonus(double superiorBonus) {
        this.superiorBonus = superiorBonus;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Double getBankBonus() {
        return bankBonus;
    }

    public void setBankBonus(Double bankBonus) {
        this.bankBonus = bankBonus;
    }

    public String getUrlCreateCard() {
        return urlCreateCard;
    }

    public void setUrlCreateCard(String urlCreateCard) {
        this.urlCreateCard = urlCreateCard;
    }

    public String getUrlPlanQuery() {
        return urlPlanQuery;
    }

    public void setUrlPlanQuery(String urlPlanQuery) {
        this.urlPlanQuery = urlPlanQuery;
    }
}
