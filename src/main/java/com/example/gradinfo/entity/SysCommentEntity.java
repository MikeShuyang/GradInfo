package com.example.gradinfo.entity;

import javax.persistence.*;

@Entity
@Table(name = "sys_comment", schema = "GradInfoV3", catalog = "")
public class SysCommentEntity {
    private int commentId;
    private String studentPostId;
    private String commentTransdate;
    private String commentContent;
    private String commentOper;
    private SysStudentPostEntity sysStudentPostByStudentPostId;

    @Id
    @Column(name = "comment_id", nullable = false)
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "student_post_id", nullable = true, length = 36)
    public String getStudentPostId() {
        return studentPostId;
    }

    public void setStudentPostId(String studentPostId) {
        this.studentPostId = studentPostId;
    }

    @Basic
    @Column(name = "comment_transdate", nullable = true, length = 20)
    public String getCommentTransdate() {
        return commentTransdate;
    }

    public void setCommentTransdate(String commentTransdate) {
        this.commentTransdate = commentTransdate;
    }

    @Basic
    @Column(name = "comment_content", nullable = true, length = 512)
    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @Basic
    @Column(name = "comment_oper", nullable = true, length = 36)
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

        if (commentId != that.commentId) return false;
        if (studentPostId != null ? !studentPostId.equals(that.studentPostId) : that.studentPostId != null)
            return false;
        if (commentTransdate != null ? !commentTransdate.equals(that.commentTransdate) : that.commentTransdate != null)
            return false;
        if (commentContent != null ? !commentContent.equals(that.commentContent) : that.commentContent != null)
            return false;
        if (commentOper != null ? !commentOper.equals(that.commentOper) : that.commentOper != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + (studentPostId != null ? studentPostId.hashCode() : 0);
        result = 31 * result + (commentTransdate != null ? commentTransdate.hashCode() : 0);
        result = 31 * result + (commentContent != null ? commentContent.hashCode() : 0);
        result = 31 * result + (commentOper != null ? commentOper.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "student_post_id", referencedColumnName = "student_post_id", insertable = false, updatable = false)
    public SysStudentPostEntity getSysStudentPostByStudentPostId() {
        return sysStudentPostByStudentPostId;
    }

    public void setSysStudentPostByStudentPostId(SysStudentPostEntity sysStudentPostByStudentPostId) {
        this.sysStudentPostByStudentPostId = sysStudentPostByStudentPostId;
    }
}
