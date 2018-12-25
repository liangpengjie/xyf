package com.xyf.entity;

import javax.validation.constraints.NotNull;

/**
 * @author lmumuj
 * @date 2018-11-27 10:55
 */
public class UserBank {

    private Integer id;
    @NotNull
    private String bankName;
    @NotNull
    private String cardId;
    @NotNull
    private String userName;
    @NotNull
    private String userId;
    // 1, 默认  0 ，非默认
    private Integer isDefault;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}
