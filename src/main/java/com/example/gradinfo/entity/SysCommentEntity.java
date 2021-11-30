package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_comment", schema = "GradInfoV3", catalog = "")
public class SysCommentEntity {
    private int commentId;
    private String studentPostId;
    private String commentTransdate;
    private String commentContent;
    private String commentOper;

    @Id
    @Column(name = "comment_id")
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "student_post_id")
    public String getStudentPostId() {
        return studentPostId;
    }

    public void setStudentPostId(String studentPostId) {
        this.studentPostId = studentPostId;
    }

    @Basic
    @Column(name = "comment_transdate")
    public String getCommentTransdate() {
        return commentTransdate;
    }

    public void setCommentTransdate(String commentTransdate) {
        this.commentTransdate = commentTransdate;
    }

    @Basic
    @Column(name = "comment_content")
    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @Basic
    @Column(name = "comment_oper")
    public String getCommentOper() {
        return commentOper;
    }

    public void setCommentOper(String commentOper) {
        this.commentOper = commentOper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysCommentEntity that = (SysCommentEntity) o;
        return commentId == that.commentId && Objects.equals(studentPostId, that.studentPostId) && Objects.equals(commentTransdate, that.commentTransdate) && Objects.equals(commentContent, that.commentContent) && Objects.equals(commentOper, that.commentOper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, studentPostId, commentTransdate, commentContent, commentOper);
    }
}
