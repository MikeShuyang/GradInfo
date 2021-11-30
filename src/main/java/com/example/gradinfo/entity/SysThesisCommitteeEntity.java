package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_thesis_committee", schema = "GradInfoV3", catalog = "")
public class SysThesisCommitteeEntity {
    private int tcId;
    private String studentPostId;
    private String thesisCommitteeName;
    private String thesisCommitteeChar;
    private String thesisCommitteeTitle;

    @Id
    @Column(name = "tc_id")
    public int getTcId() {
        return tcId;
    }

    public void setTcId(int tcId) {
        this.tcId = tcId;
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
    @Column(name = "thesis_committee_name")
    public String getThesisCommitteeName() {
        return thesisCommitteeName;
    }

    public void setThesisCommitteeName(String thesisCommitteeName) {
        this.thesisCommitteeName = thesisCommitteeName;
    }

    @Basic
    @Column(name = "thesis_committee_char")
    public String getThesisCommitteeChar() {
        return thesisCommitteeChar;
    }

    public void setThesisCommitteeChar(String thesisCommitteeChar) {
        this.thesisCommitteeChar = thesisCommitteeChar;
    }

    @Basic
    @Column(name = "thesis_committee_title")
    public String getThesisCommitteeTitle() {
        return thesisCommitteeTitle;
    }

    public void setThesisCommitteeTitle(String thesisCommitteeTitle) {
        this.thesisCommitteeTitle = thesisCommitteeTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysThesisCommitteeEntity that = (SysThesisCommitteeEntity) o;
        return tcId == that.tcId && Objects.equals(studentPostId, that.studentPostId) && Objects.equals(thesisCommitteeName, that.thesisCommitteeName) && Objects.equals(thesisCommitteeChar, that.thesisCommitteeChar) && Objects.equals(thesisCommitteeTitle, that.thesisCommitteeTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tcId, studentPostId, thesisCommitteeName, thesisCommitteeChar, thesisCommitteeTitle);
    }
}
