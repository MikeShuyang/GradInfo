package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_exam_committee", schema = "GradInfoV3", catalog = "")
public class SysExamCommitteeEntity {
    private int ecId;
    private String studentPostId;
    private String examCommitteeName;
    private String examComitteeChar;

    @Id
    @Column(name = "ec_id")
    public int getEcId() {
        return ecId;
    }

    public void setEcId(int ecId) {
        this.ecId = ecId;
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
    @Column(name = "exam_committee_name")
    public String getExamCommitteeName() {
        return examCommitteeName;
    }

    public void setExamCommitteeName(String examCommitteeName) {
        this.examCommitteeName = examCommitteeName;
    }

    @Basic
    @Column(name = "exam_comittee_char")
    public String getExamComitteeChar() {
        return examComitteeChar;
    }

    public void setExamComitteeChar(String examComitteeChar) {
        this.examComitteeChar = examComitteeChar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysExamCommitteeEntity that = (SysExamCommitteeEntity) o;
        return ecId == that.ecId && Objects.equals(studentPostId, that.studentPostId) && Objects.equals(examCommitteeName, that.examCommitteeName) && Objects.equals(examComitteeChar, that.examComitteeChar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ecId, studentPostId, examCommitteeName, examComitteeChar);
    }
}
