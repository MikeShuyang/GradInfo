package com.example.gradinfo.entity;

import javax.persistence.*;

@Entity
@Table(name = "sys_thesis_committee", schema = "GradInfoV3", catalog = "")
public class SysThesisCommitteeEntity {
    private int tcId;
    private String studentPostId;
    private String thesisCommitteeName;
    private String thesisCommitteeChar;
    private SysStudentPostEntity sysStudentPostByStudentPostId;

    @Id
    @Column(name = "tc_id", nullable = false)
    public int getTcId() {
        return tcId;
    }

    public void setTcId(int tcId) {
        this.tcId = tcId;
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
    @Column(name = "thesis_committee_name", nullable = true, length = 128)
    public String getThesisCommitteeName() {
        return thesisCommitteeName;
    }

    public void setThesisCommitteeName(String thesisCommitteeName) {
        this.thesisCommitteeName = thesisCommitteeName;
    }

    @Basic
    @Column(name = "thesis_committee_char", nullable = true, length = 128)
    public String getThesisCommitteeChar() {
        return thesisCommitteeChar;
    }

    public void setThesisCommitteeChar(String thesisCommitteeChar) {
        this.thesisCommitteeChar = thesisCommitteeChar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysThesisCommitteeEntity that = (SysThesisCommitteeEntity) o;

        if (tcId != that.tcId) return false;
        if (studentPostId != null ? !studentPostId.equals(that.studentPostId) : that.studentPostId != null)
            return false;
        if (thesisCommitteeName != null ? !thesisCommitteeName.equals(that.thesisCommitteeName) : that.thesisCommitteeName != null)
            return false;
        if (thesisCommitteeChar != null ? !thesisCommitteeChar.equals(that.thesisCommitteeChar) : that.thesisCommitteeChar != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tcId;
        result = 31 * result + (studentPostId != null ? studentPostId.hashCode() : 0);
        result = 31 * result + (thesisCommitteeName != null ? thesisCommitteeName.hashCode() : 0);
        result = 31 * result + (thesisCommitteeChar != null ? thesisCommitteeChar.hashCode() : 0);
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
