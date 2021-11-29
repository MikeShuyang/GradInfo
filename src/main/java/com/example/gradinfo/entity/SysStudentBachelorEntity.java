package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_student_bachelor", schema = "GradInfoV3", catalog = "")
public class SysStudentBachelorEntity {
    private int sbId;
    private String studentId;
    private String sbCeeb;
    private String sbName;
    private String sbDateEarned;
    private String sbDeanOverride;

    @Id
    @Column(name = "sb_id")
    public int getSbId() {
        return sbId;
    }

    public void setSbId(int sbId) {
        this.sbId = sbId;
    }

    @Basic
    @Column(name = "student_id")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "sb_ceeb")
    public String getSbCeeb() {
        return sbCeeb;
    }

    public void setSbCeeb(String sbCeeb) {
        this.sbCeeb = sbCeeb;
    }

    @Basic
    @Column(name = "sb_name")
    public String getSbName() {
        return sbName;
    }

    public void setSbName(String sbName) {
        this.sbName = sbName;
    }

    @Basic
    @Column(name = "sb_date_earned")
    public String getSbDateEarned() {
        return sbDateEarned;
    }

    public void setSbDateEarned(String sbDateEarned) {
        this.sbDateEarned = sbDateEarned;
    }

    @Basic
    @Column(name = "sb_dean_override")
    public String getSbDeanOverride() {
        return sbDeanOverride;
    }

    public void setSbDeanOverride(String sbDeanOverride) {
        this.sbDeanOverride = sbDeanOverride;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysStudentBachelorEntity that = (SysStudentBachelorEntity) o;
        return sbId == that.sbId && Objects.equals(studentId, that.studentId) && Objects.equals(sbCeeb, that.sbCeeb) && Objects.equals(sbName, that.sbName) && Objects.equals(sbDateEarned, that.sbDateEarned) && Objects.equals(sbDeanOverride, that.sbDeanOverride);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sbId, studentId, sbCeeb, sbName, sbDateEarned, sbDeanOverride);
    }
}
