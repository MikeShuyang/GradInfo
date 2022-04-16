package com.example.gradinfo.entity;

import javax.persistence.*;

@Entity
@Table(name = "sys_exam_committee", schema = "GradInfoV3", catalog = "")
public class SysExamCommitteeEntity {
    private int ecId;
    private String studentPostId;
    private String examCommitteeName;
    private String examComitteeChar;
    private String examCommitteeChar;
    private SysStudentPostEntity sysStudentPostByStudentPostId;

    @Id
    @Column(name = "ec_id", nullable = false)
    public int getEcId() {
        return ecId;
    }

    public void setEcId(int ecId) {
        this.ecId = ecId;
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
    @Column(name = "exam_committee_name", nullable = true, length = 128)
    public String getExamCommitteeName() {
        return examCommitteeName;
    }

    public void setExamCommitteeName(String examCommitteeName) {
        this.examCommitteeName = examCommitteeName;
    }

    @Basic
    @Column(name = "exam_comittee_char", nullable = true, length = 128)
    public String getExamComitteeChar() {
        return examComitteeChar;
    }

    public void setExamComitteeChar(String examComitteeChar) {
        this.examComitteeChar = examComitteeChar;
    }

    @Basic
    @Column(name = "exam_committee_char", nullable = true, length = 128)
    public String getExamCommitteeChar() {
        return examCommitteeChar;
    }

    public void setExamCommitteeChar(String examCommitteeChar) {
        this.examCommitteeChar = examCommitteeChar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysExamCommitteeEntity that = (SysExamCommitteeEntity) o;

        if (ecId != that.ecId) return false;
        if (studentPostId != null ? !studentPostId.equals(that.studentPostId) : that.studentPostId != null)
            return false;
        if (examCommitteeName != null ? !examCommitteeName.equals(that.examCommitteeName) : that.examCommitteeName != null)
            return false;
        if (examComitteeChar != null ? !examComitteeChar.equals(that.examComitteeChar) : that.examComitteeChar != null)
            return false;
        if (examCommitteeChar != null ? !examCommitteeChar.equals(that.examCommitteeChar) : that.examCommitteeChar != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ecId;
        result = 31 * result + (studentPostId != null ? studentPostId.hashCode() : 0);
        result = 31 * result + (examCommitteeName != null ? examCommitteeName.hashCode() : 0);
        result = 31 * result + (examComitteeChar != null ? examComitteeChar.hashCode() : 0);
        result = 31 * result + (examCommitteeChar != null ? examCommitteeChar.hashCode() : 0);
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
