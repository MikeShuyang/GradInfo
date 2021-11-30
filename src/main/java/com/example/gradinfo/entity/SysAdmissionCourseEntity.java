package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_admission_course", schema = "GradInfoV3", catalog = "")
public class SysAdmissionCourseEntity {
    private String adCourseId;
    private String studentPostId;
    private String adCourseNumber;
    private String adCourseName;
    private Double adCourseUnits;
    private String adCourseTerm;
    private String adCourseGrade;
    private Double adCourseGpts;
    private Byte adCourseApplyStatus;
    private String adCourseApplyCode;
    private String adCourseOper;
    private String adCourseTransdate;

    @Id
    @Column(name = "ad_course_id")
    public String getAdCourseId() {
        return adCourseId;
    }

    public void setAdCourseId(String adCourseId) {
        this.adCourseId = adCourseId;
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
    @Column(name = "ad_course_number")
    public String getAdCourseNumber() {
        return adCourseNumber;
    }

    public void setAdCourseNumber(String adCourseNumber) {
        this.adCourseNumber = adCourseNumber;
    }

    @Basic
    @Column(name = "ad_course_name")
    public String getAdCourseName() {
        return adCourseName;
    }

    public void setAdCourseName(String adCourseName) {
        this.adCourseName = adCourseName;
    }

    @Basic
    @Column(name = "ad_course_units")
    public Double getAdCourseUnits() {
        return adCourseUnits;
    }

    public void setAdCourseUnits(Double adCourseUnits) {
        this.adCourseUnits = adCourseUnits;
    }

    @Basic
    @Column(name = "ad_course_term")
    public String getAdCourseTerm() {
        return adCourseTerm;
    }

    public void setAdCourseTerm(String adCourseTerm) {
        this.adCourseTerm = adCourseTerm;
    }

    @Basic
    @Column(name = "ad_course_grade")
    public String getAdCourseGrade() {
        return adCourseGrade;
    }

    public void setAdCourseGrade(String adCourseGrade) {
        this.adCourseGrade = adCourseGrade;
    }

    @Basic
    @Column(name = "ad_course_gpts")
    public Double getAdCourseGpts() {
        return adCourseGpts;
    }

    public void setAdCourseGpts(Double adCourseGpts) {
        this.adCourseGpts = adCourseGpts;
    }

    @Basic
    @Column(name = "ad_course_apply_status", nullable = false, columnDefinition = "TINYINT(1)")
    public Byte getAdCourseApplyStatus() {
        return adCourseApplyStatus;
    }

    public void setAdCourseApplyStatus(Byte adCourseApplyStatus) {
        this.adCourseApplyStatus = adCourseApplyStatus;
    }

    @Basic
    @Column(name = "ad_course_apply_code")
    public String getAdCourseApplyCode() {
        return adCourseApplyCode;
    }

    public void setAdCourseApplyCode(String adCourseApplyCode) {
        this.adCourseApplyCode = adCourseApplyCode;
    }

    @Basic
    @Column(name = "ad_course_oper")
    public String getAdCourseOper() {
        return adCourseOper;
    }

    public void setAdCourseOper(String adCourseOper) {
        this.adCourseOper = adCourseOper;
    }

    @Basic
    @Column(name = "ad_course_transdate")
    public String getAdCourseTransdate() {
        return adCourseTransdate;
    }

    public void setAdCourseTransdate(String adCourseTransdate) {
        this.adCourseTransdate = adCourseTransdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysAdmissionCourseEntity that = (SysAdmissionCourseEntity) o;
        return Objects.equals(adCourseId, that.adCourseId) && Objects.equals(studentPostId, that.studentPostId) && Objects.equals(adCourseNumber, that.adCourseNumber) && Objects.equals(adCourseName, that.adCourseName) && Objects.equals(adCourseUnits, that.adCourseUnits) && Objects.equals(adCourseTerm, that.adCourseTerm) && Objects.equals(adCourseGrade, that.adCourseGrade) && Objects.equals(adCourseGpts, that.adCourseGpts) && Objects.equals(adCourseApplyStatus, that.adCourseApplyStatus) && Objects.equals(adCourseApplyCode, that.adCourseApplyCode) && Objects.equals(adCourseOper, that.adCourseOper) && Objects.equals(adCourseTransdate, that.adCourseTransdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adCourseId, studentPostId, adCourseNumber, adCourseName, adCourseUnits, adCourseTerm, adCourseGrade, adCourseGpts, adCourseApplyStatus, adCourseApplyCode, adCourseOper, adCourseTransdate);
    }
}
