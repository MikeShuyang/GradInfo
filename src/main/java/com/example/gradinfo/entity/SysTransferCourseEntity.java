package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_transfer_course", schema = "GradInfoV3", catalog = "")
public class SysTransferCourseEntity {
    private String trCourseId;
    private String studentPostId;
    private String institutionId;
    private String trCourseNumber;
    private String trCourseName;
    private Double trCourseUnits;
    private String trCourseLevel;
    private String trCourseTerm;
    private String trCourseGrade;
    private Double trCourseGpts;
    private Byte trCourseApplyStatus;
    private String trCourseApplyCode;
    private String trCourseOper;
    private String trCourseTransdate;

    @Id
    @Column(name = "tr_course_id")
    public String getTrCourseId() {
        return trCourseId;
    }

    public void setTrCourseId(String trCourseId) {
        this.trCourseId = trCourseId;
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
    @Column(name = "institution_id")
    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }


    @Basic
    @Column(name = "tr_course_number")
    public String getTrCourseNumber() {
        return trCourseNumber;
    }

    public void setTrCourseNumber(String trCourseNumber) {
        this.trCourseNumber = trCourseNumber;
    }

    @Basic
    @Column(name = "tr_course_name")
    public String getTrCourseName() {
        return trCourseName;
    }

    public void setTrCourseName(String trCourseName) {
        this.trCourseName = trCourseName;
    }

    @Basic
    @Column(name = "tr_course_units")
    public Double getTrCourseUnits() {
        return trCourseUnits;
    }

    public void setTrCourseUnits(Double trCourseUnits) {
        this.trCourseUnits = trCourseUnits;
    }

    @Basic
    @Column(name = "tr_course_level")
    public String getTrCourseLevel() {
        return trCourseLevel;
    }

    public void setTrCourseLevel(String trCourseLevel) {
        this.trCourseLevel = trCourseLevel;
    }

    @Basic
    @Column(name = "tr_course_term")
    public String getTrCourseTerm() {
        return trCourseTerm;
    }

    public void setTrCourseTerm(String trCourseTerm) {
        this.trCourseTerm = trCourseTerm;
    }

    @Basic
    @Column(name = "tr_course_grade")
    public String getTrCourseGrade() {
        return trCourseGrade;
    }

    public void setTrCourseGrade(String trCourseGrade) {
        this.trCourseGrade = trCourseGrade;
    }

    @Basic
    @Column(name = "tr_course_gpts")
    public Double getTrCourseGpts() {
        return trCourseGpts;
    }

    public void setTrCourseGpts(Double trCourseGpts) {
        this.trCourseGpts = trCourseGpts;
    }

    @Basic
    @Column(name = "tr_course_apply_status")
    public Byte getTrCourseApplyStatus() {
        return trCourseApplyStatus;
    }

    public void setTrCourseApplyStatus(Byte trCourseApplyStatus) {
        this.trCourseApplyStatus = trCourseApplyStatus;
    }

    @Basic
    @Column(name = "tr_course_apply_code")
    public String getTrCourseApplyCode() {
        return trCourseApplyCode;
    }

    public void setTrCourseApplyCode(String trCourseApplyCode) {
        this.trCourseApplyCode = trCourseApplyCode;
    }

    @Basic
    @Column(name = "tr_course_oper")
    public String getTrCourseOper() {
        return trCourseOper;
    }

    public void setTrCourseOper(String trCourseOper) {
        this.trCourseOper = trCourseOper;
    }

    @Basic
    @Column(name = "tr_course_transdate")
    public String getTrCourseTransdate() {
        return trCourseTransdate;
    }

    public void setTrCourseTransdate(String trCourseTransdate) {
        this.trCourseTransdate = trCourseTransdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysTransferCourseEntity that = (SysTransferCourseEntity) o;
        return Objects.equals(trCourseId, that.trCourseId) && Objects.equals(studentPostId, that.studentPostId) && Objects.equals(institutionId, that.institutionId) && Objects.equals(trCourseNumber, that.trCourseNumber) && Objects.equals(trCourseName, that.trCourseName) && Objects.equals(trCourseUnits, that.trCourseUnits) && Objects.equals(trCourseLevel, that.trCourseLevel) && Objects.equals(trCourseTerm, that.trCourseTerm) && Objects.equals(trCourseGrade, that.trCourseGrade) && Objects.equals(trCourseGpts, that.trCourseGpts) && Objects.equals(trCourseApplyStatus, that.trCourseApplyStatus) && Objects.equals(trCourseApplyCode, that.trCourseApplyCode) && Objects.equals(trCourseOper, that.trCourseOper) && Objects.equals(trCourseTransdate, that.trCourseTransdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trCourseId, studentPostId, institutionId, trCourseNumber, trCourseName, trCourseUnits, trCourseLevel, trCourseTerm, trCourseGrade, trCourseGpts, trCourseApplyStatus, trCourseApplyCode, trCourseOper, trCourseTransdate);
    }
}
