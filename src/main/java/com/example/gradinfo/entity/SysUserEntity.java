package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "sys_user", schema = "GradInfoV3", catalog = "")
public class SysUserEntity {
    private String userId;
    private String userCode;
    private String userName;
    private String userPassword;
    private String userOper;
    private String userSuper;
    private String userEventAccessLevel;
    private Collection<SysUserPostAccessEntity> sysUserPostAccessesByUserId;

    @Id
    @Column(name = "user_id", nullable = false, length = 36)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_code", nullable = true, length = 32)
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Basic
    @Column(name = "user_name", nullable = true, length = 64)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_password", nullable = true, length = 32)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "user_oper", nullable = true, length = 36)
    public String getUserOper() {
        return userOper;
    }

    public void setUserOper(String userOper) {
        this.userOper = userOper;
    }

    @Basic
    @Column(name = "user_super", nullable = true, length = 1)
    public String getUserSuper() {
        return userSuper;
    }

    public void setUserSuper(String userSuper) {
        this.userSuper = userSuper;
    }

    @Basic
    @Column(name = "user_event_access_level", nullable = true, length = 10)
    public String getUserEventAccessLevel() {
        return userEventAccessLevel;
    }

    public void setUserEventAccessLevel(String userEventAccessLevel) {
        this.userEventAccessLevel = userEventAccessLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysUserEntity that = (SysUserEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (userCode != null ? !userCode.equals(that.userCode) : that.userCode != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userPassword != null ? !userPassword.equals(that.userPassword) : that.userPassword != null) return false;
        if (userOper != null ? !userOper.equals(that.userOper) : that.userOper != null) return false;
        if (userSuper != null ? !userSuper.equals(that.userSuper) : that.userSuper != null) return false;
        if (userEventAccessLevel != null ? !userEventAccessLevel.equals(that.userEventAccessLevel) : that.userEventAccessLevel != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + (userOper != null ? userOper.hashCode() : 0);
        result = 31 * result + (userSuper != null ? userSuper.hashCode() : 0);
        result = 31 * result + (userEventAccessLevel != null ? userEventAccessLevel.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sysUserByUserId")
    public Collection<SysUserPostAccessEntity> getSysUserPostAccessesByUserId() {
        return sysUserPostAccessesByUserId;
    }

    public void setSysUserPostAccessesByUserId(Collection<SysUserPostAccessEntity> sysUserPostAccessesByUserId) {
        this.sysUserPostAccessesByUserId = sysUserPostAccessesByUserId;
    }
}
