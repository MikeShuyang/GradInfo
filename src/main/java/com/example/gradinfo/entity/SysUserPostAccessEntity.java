package com.example.gradinfo.entity;

import javax.persistence.*;

@Entity
@Table(name = "sys_user_post_access", schema = "GradInfoV3", catalog = "")
public class SysUserPostAccessEntity {
    private int userPostId;
    private String userId;
    private String postNumber;
    private SysUserEntity sysUserByUserId;

    @Id
    @Column(name = "user_post_id", nullable = false)
    public int getUserPostId() {
        return userPostId;
    }

    public void setUserPostId(int userPostId) {
        this.userPostId = userPostId;
    }

    @Basic
    @Column(name = "user_id", nullable = true, length = 36)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "post_number", nullable = false, length = 36)
    public String getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(String postNumber) {
        this.postNumber = postNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysUserPostAccessEntity that = (SysUserPostAccessEntity) o;

        if (userPostId != that.userPostId) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (postNumber != null ? !postNumber.equals(that.postNumber) : that.postNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userPostId;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (postNumber != null ? postNumber.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",insertable = false,updatable = false)
    public SysUserEntity getSysUserByUserId() {
        return sysUserByUserId;
    }

    public void setSysUserByUserId(SysUserEntity sysUserByUserId) {
        this.sysUserByUserId = sysUserByUserId;
    }
}
