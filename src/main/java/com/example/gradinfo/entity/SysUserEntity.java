package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_user", schema = "GradInfoV3Cp", catalog = "")
public class SysUserEntity {
    private String userId;
    private String userCode;
    private String userName;
    private String userPassword;
    private String userOper;
    private String userLocked;

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_code")
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_password")
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "user_oper")
    public String getUserOper() {
        return userOper;
    }

    public void setUserOper(String userOper) {
        this.userOper = userOper;
    }

    @Basic
    @Column(name = "user_locked")
    public String getUserLocked() {
        return userLocked;
    }

    public void setUserLocked(String userLocked) {
        this.userLocked = userLocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserEntity that = (SysUserEntity) o;
        return Objects.equals(userId, that.userId) && Objects.equals(userCode, that.userCode) && Objects.equals(userName, that.userName) && Objects.equals(userPassword, that.userPassword) && Objects.equals(userOper, that.userOper) && Objects.equals(userLocked, that.userLocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userCode, userName, userPassword, userOper, userLocked);
    }
}
