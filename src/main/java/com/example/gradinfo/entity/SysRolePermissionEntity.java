package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_role_permission", schema = "GradInfoV3", catalog = "")
public class SysRolePermissionEntity {
    private int rolePermissionId;
    private String roleId;
    private String permissionId;

    @Id
    @Column(name = "role_permission_id")
    public int getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(int rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    @Basic
    @Column(name = "role_id")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "permission_id")
    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRolePermissionEntity that = (SysRolePermissionEntity) o;
        return rolePermissionId == that.rolePermissionId && Objects.equals(roleId, that.roleId) && Objects.equals(permissionId, that.permissionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolePermissionId, roleId, permissionId);
    }
}
