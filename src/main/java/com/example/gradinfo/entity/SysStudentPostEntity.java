package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_student_post", schema = "GradInfoV3", catalog = "")
public class SysStudentPostEntity {
    private String studentPostId;
    private String studentId;
    private String spPostNumber;
    private String spNdocs;
    private String spConfu;
    private String spAdmit;
    private String spReadmit;
    private String spTransdate;
    private String spMajor;
    private String spObj;
    private Double spRgunits;
    private Double spEarnunits;
    private Double spGpaApply;
    private Double spGpaAll;
    private Integer spCreditLimits;

    @Id
    @Column(name = "student_post_id")
    public String getStudentPostId() {
        return studentPostId;
    }

    public void setStudentPostId(String studentPostId) {
        this.studentPostId = studentPostId;
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
    @Column(name = "sp_post_number")
    public String getSpPostNumber() {
        return spPostNumber;
    }

    public void setSpPostNumber(String spPostNumber) {
        this.spPostNumber = spPostNumber;
    }

    @Basic
    @Column(name = "sp_ndocs")
    public String getSpNdocs() {
        return spNdocs;
    }

    public void setSpNdocs(String spNdocs) {
        this.spNdocs = spNdocs;
    }

    @Basic
    @Column(name = "sp_confu")
    public String getSpConfu() {
        return spConfu;
    }

    public void setSpConfu(String spConfu) {
        this.spConfu = spConfu;
    }

    @Basic
    @Column(name = "sp_admit")
    public String getSpAdmit() {
        return spAdmit;
    }

    public void setSpAdmit(String spAdmit) {
        this.spAdmit = spAdmit;
    }

    @Basic
    @Column(name = "sp_readmit")
    public String getSpReadmit() {
        return spReadmit;
    }

    public void setSpReadmit(String spReadmit) {
        this.spReadmit = spReadmit;
    }

    @Basic
    @Column(name = "sp_transdate")
    public String getSpTransdate() {
        return spTransdate;
    }

    public void setSpTransdate(String spTransdate) {
        this.spTransdate = spTransdate;
    }

    @Basic
    @Column(name = "sp_major")
    public String getSpMajor() {
        return spMajor;
    }

    public void setSpMajor(String spMajor) {
        this.spMajor = spMajor;
    }

    @Basic
    @Column(name = "sp_obj")
    public String getSpObj() {
        return spObj;
    }

    public void setSpObj(String spObj) {
        this.spObj = spObj;
    }

    @Basic
    @Column(name = "sp_rgunits")
    public Double getSpRgunits() {
        return spRgunits;
    }

    public void setSpRgunits(Double spRgunits) {
        this.spRgunits = spRgunits;
    }

    @Basic
    @Column(name = "sp_earnunits")
    public Double getSpEarnunits() {
        return spEarnunits;
    }

    public void setSpEarnunits(Double spEarnunits) {
        this.spEarnunits = spEarnunits;
    }

    @Basic
    @Column(name = "sp_gpa_apply")
    public Double getSpGpaApply() {
        return spGpaApply;
    }

    public void setSpGpaApply(Double spGpaApply) {
        this.spGpaApply = spGpaApply;
    }

    @Basic
    @Column(name = "sp_gpa_all")
    public Double getSpGpaAll() {
        return spGpaAll;
    }

    public void setSpGpaAll(Double spGpaAll) {
        this.spGpaAll = spGpaAll;
    }

    @Basic
    @Column(name = "sp_credit_limits")
    public Integer getSpCreditLimits() {
        return spCreditLimits;
    }

    public void setSpCreditLimits(Integer spCreditLimits) {
        this.spCreditLimits = spCreditLimits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysStudentPostEntity that = (SysStudentPostEntity) o;
        return Objects.equals(studentPostId, that.studentPostId) && Objects.equals(studentId, that.studentId) && Objects.equals(spPostNumber, that.spPostNumber) && Objects.equals(spNdocs, that.spNdocs) && Objects.equals(spConfu, that.spConfu) && Objects.equals(spAdmit, that.spAdmit) && Objects.equals(spReadmit, that.spReadmit) && Objects.equals(spTransdate, that.spTransdate) && Objects.equals(spMajor, that.spMajor) && Objects.equals(spObj, that.spObj) && Objects.equals(spRgunits, that.spRgunits) && Objects.equals(spEarnunits, that.spEarnunits) && Objects.equals(spGpaApply, that.spGpaApply) && Objects.equals(spGpaAll, that.spGpaAll) && Objects.equals(spCreditLimits, that.spCreditLimits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentPostId, studentId, spPostNumber, spNdocs, spConfu, spAdmit, spReadmit, spTransdate, spMajor, spObj, spRgunits, spEarnunits, spGpaApply, spGpaAll, spCreditLimits);
    }
}
