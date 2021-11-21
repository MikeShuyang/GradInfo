package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_role", schema = "GradInfoV3", catalog = "")
public class SysRoleEntity {
    private String roleId;
    private String roleName;
    private String roleAvaliable;

    @Id
    @Column(name = "role_id")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "role_avaliable")
    public String getRoleAvaliable() {
        return roleAvaliable;
    }

    public void setRoleAvaliable(String roleAvaliable) {
        this.roleAvaliable = roleAvaliable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRoleEntity that = (SysRoleEntity) o;
        return Objects.equals(roleId, that.roleId) && Objects.equals(roleName, that.roleName) && Objects.equals(roleAvaliable, that.roleAvaliable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, roleAvaliable);
    }
}
