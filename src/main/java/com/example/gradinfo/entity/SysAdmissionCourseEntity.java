package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Collection;

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
    private Collection<SysAdHistoryEntity> sysAdHistoriesByAdCourseId;
    private SysStudentPostEntity sysStudentPostByStudentPostId;

    @Id
    @Column(name = "ad_course_id", nullable = false, length = 36)
    public String getAdCourseId() {
        return adCourseId;
    }

    public void setAdCourseId(String adCourseId) {
        this.adCourseId = adCourseId;
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
    @Column(name = "ad_course_number", nullable = true, length = 36)
    public String getAdCourseNumber() {
        return adCourseNumber;
    }

    public void setAdCourseNumber(String adCourseNumber) {
        this.adCourseNumber = adCourseNumber;
    }

    @Basic
    @Column(name = "ad_course_name", nullable = true, length = 128)
    public String getAdCourseName() {
        return adCourseName;
    }

    public void setAdCourseName(String adCourseName) {
        this.adCourseName = adCourseName;
    }

    @Basic
    @Column(name = "ad_course_units", nullable = true, precision = 0)
    public Double getAdCourseUnits() {
        return adCourseUnits;
    }

    public void setAdCourseUnits(Double adCourseUnits) {
        this.adCourseUnits = adCourseUnits;
    }

    @Basic
    @Column(name = "ad_course_term", nullable = true, length = 20)
    public String getAdCourseTerm() {
        return adCourseTerm;
    }

    public void setAdCourseTerm(String adCourseTerm) {
        this.adCourseTerm = adCourseTerm;
    }

    @Basic
    @Column(name = "ad_course_grade", nullable = true, length = 20)
    public String getAdCourseGrade() {
        return adCourseGrade;
    }

    public void setAdCourseGrade(String adCourseGrade) {
        this.adCourseGrade = adCourseGrade;
    }

    @Basic
    @Column(name = "ad_course_gpts", nullable = true, precision = 0)
    public Double getAdCourseGpts() {
        return adCourseGpts;
    }

    public void setAdCourseGpts(Double adCourseGpts) {
        this.adCourseGpts = adCourseGpts;
    }

    @Basic
    @Column(name = "ad_course_apply_status", nullable = true)
    public Byte getAdCourseApplyStatus() {
        return adCourseApplyStatus;
    }

    public void setAdCourseApplyStatus(Byte adCourseApplyStatus) {
        this.adCourseApplyStatus = adCourseApplyStatus;
    }

    @Basic
    @Column(name = "ad_course_apply_code", nullable = true, length = 128)
    public String getAdCourseApplyCode() {
        return adCourseApplyCode;
    }

    public void setAdCourseApplyCode(String adCourseApplyCode) {
        this.adCourseApplyCode = adCourseApplyCode;
    }

    @Basic
    @Column(name = "ad_course_oper", nullable = true, length = 36)
    public String getAdCourseOper() {
        return adCourseOper;
    }

    public void setAdCourseOper(String adCourseOper) {
        this.adCourseOper = adCourseOper;
    }

    @Basic
    @Column(name = "ad_course_transdate", nullable = true, length = 20)
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

        if (adCourseId != null ? !adCourseId.equals(that.adCourseId) : that.adCourseId != null) return false;
        if (studentPostId != null ? !studentPostId.equals(that.studentPostId) : that.studentPostId != null)
            return false;
        if (adCourseNumber != null ? !adCourseNumber.equals(that.adCourseNumber) : that.adCourseNumber != null)
            return false;
        if (adCourseName != null ? !adCourseName.equals(that.adCourseName) : that.adCourseName != null) return false;
        if (adCourseUnits != null ? !adCourseUnits.equals(that.adCourseUnits) : that.adCourseUnits != null)
            return false;
        if (adCourseTerm != null ? !adCourseTerm.equals(that.adCourseTerm) : that.adCourseTerm != null) return false;
        if (adCourseGrade != null ? !adCourseGrade.equals(that.adCourseGrade) : that.adCourseGrade != null)
            return false;
        if (adCourseGpts != null ? !adCourseGpts.equals(that.adCourseGpts) : that.adCourseGpts != null) return false;
        if (adCourseApplyStatus != null ? !adCourseApplyStatus.equals(that.adCourseApplyStatus) : that.adCourseApplyStatus != null)
            return false;
        if (adCourseApplyCode != null ? !adCourseApplyCode.equals(that.adCourseApplyCode) : that.adCourseApplyCode != null)
            return false;
        if (adCourseOper != null ? !adCourseOper.equals(that.adCourseOper) : that.adCourseOper != null) return false;
        if (adCourseTransdate != null ? !adCourseTransdate.equals(that.adCourseTransdate) : that.adCourseTransdate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = adCourseId != null ? adCourseId.hashCode() : 0;
        result = 31 * result + (studentPostId != null ? studentPostId.hashCode() : 0);
        result = 31 * result + (adCourseNumber != null ? adCourseNumber.hashCode() : 0);
        result = 31 * result + (adCourseName != null ? adCourseName.hashCode() : 0);
        result = 31 * result + (adCourseUnits != null ? adCourseUnits.hashCode() : 0);
        result = 31 * result + (adCourseTerm != null ? adCourseTerm.hashCode() : 0);
        result = 31 * result + (adCourseGrade != null ? adCourseGrade.hashCode() : 0);
        result = 31 * result + (adCourseGpts != null ? adCourseGpts.hashCode() : 0);
        result = 31 * result + (adCourseApplyStatus != null ? adCourseApplyStatus.hashCode() : 0);
        result = 31 * result + (adCourseApplyCode != null ? adCourseApplyCode.hashCode() : 0);
        result = 31 * result + (adCourseOper != null ? adCourseOper.hashCode() : 0);
        result = 31 * result + (adCourseTransdate != null ? adCourseTransdate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sysAdmissionCourseByAdCourseId")
    public Collection<SysAdHistoryEntity> getSysAdHistoriesByAdCourseId() {
        return sysAdHistoriesByAdCourseId;
    }

    public void setSysAdHistoriesByAdCourseId(Collection<SysAdHistoryEntity> sysAdHistoriesByAdCourseId) {
        this.sysAdHistoriesByAdCourseId = sysAdHistoriesByAdCourseId;
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
