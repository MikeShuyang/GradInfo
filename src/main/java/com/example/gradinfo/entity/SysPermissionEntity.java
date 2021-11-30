package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_permission", schema = "GradInfoV3", catalog = "")
public class SysPermissionEntity {
    private String permissionId;
    private String permissionName;
    private String permissionUrl;
    private String permissionPercode;
    private String permissionParentid;
    private String permissionAvaliable;

    @Id
    @Column(name = "permission_id")
    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Basic
    @Column(name = "permission_name")
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Basic
    @Column(name = "permission_url")
    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    @Basic
    @Column(name = "permission_percode")
    public String getPermissionPercode() {
        return permissionPercode;
    }

    public void setPermissionPercode(String permissionPercode) {
        this.permissionPercode = permissionPercode;
    }

    @Basic
    @Column(name = "permission_parentid")
    public String getPermissionParentid() {
        return permissionParentid;
    }

    public void setPermissionParentid(String permissionParentid) {
        this.permissionParentid = permissionParentid;
    }

    @Basic
    @Column(name = "permission_avaliable")
    public String getPermissionAvaliable() {
        return permissionAvaliable;
    }

    public void setPermissionAvaliable(String permissionAvaliable) {
        this.permissionAvaliable = permissionAvaliable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysPermissionEntity that = (SysPermissionEntity) o;
        return Objects.equals(permissionId, that.permissionId) && Objects.equals(permissionName, that.permissionName) && Objects.equals(permissionUrl, that.permissionUrl) && Objects.equals(permissionPercode, that.permissionPercode) && Objects.equals(permissionParentid, that.permissionParentid) && Objects.equals(permissionAvaliable, that.permissionAvaliable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionId, permissionName, permissionUrl, permissionPercode, permissionParentid, permissionAvaliable);
    }
}
