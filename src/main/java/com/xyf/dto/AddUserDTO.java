package com.xyf.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author 木木
 * @date 2018/8/25 12:34
 * @description
 */
public class AddUserDTO {

    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String phone;
    private Integer superior1 = 0;
    private Integer superior2 = 0;
    private Integer superior3 = 0;
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSuperior2() {
        return superior2;
    }

    public void setSuperior2(Integer superior2) {
        this.superior2 = superior2;
    }

    public Integer getSuperior3() {
        return superior3;
    }

    public void setSuperior3(Integer superior3) {
        this.superior3 = superior3;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSuperior1() {
        return superior1;
    }

    public void setSuperior1(Integer superior1) {
        this.superior1 = superior1;
    }
}
