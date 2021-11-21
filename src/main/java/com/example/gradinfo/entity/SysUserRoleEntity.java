package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_user_role", schema = "GradInfoV3", catalog = "")
public class SysUserRoleEntity {
    private int userRoleId;
    private String userId;
    private String roleId;

    @Id
    @Column(name = "user_role_id")
    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "role_id")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserRoleEntity that = (SysUserRoleEntity) o;
        return userRoleId == that.userRoleId && Objects.equals(userId, that.userId) && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRoleId, userId, roleId);
    }
}
