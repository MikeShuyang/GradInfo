package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Collection;

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
    private String trHistoryId;
    private SysStudentPostEntity sysStudentPostByStudentPostId;
    private SysInstitutionEntity sysInstitutionByInstitutionId;
    private Collection<SysTransferHistoryEntity> sysTransferHistoriesByTrCourseId;

    @Id
    @Column(name = "tr_course_id", nullable = false, length = 36)
    public String getTrCourseId() {
        return trCourseId;
    }

    public void setTrCourseId(String trCourseId) {
        this.trCourseId = trCourseId;
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
    @Column(name = "institution_id", nullable = true, length = 36)
    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    @Basic
    @Column(name = "tr_course_number", nullable = true, length = 36)
    public String getTrCourseNumber() {
        return trCourseNumber;
    }

    public void setTrCourseNumber(String trCourseNumber) {
        this.trCourseNumber = trCourseNumber;
    }

    @Basic
    @Column(name = "tr_course_name", nullable = true, length = 128)
    public String getTrCourseName() {
        return trCourseName;
    }

    public void setTrCourseName(String trCourseName) {
        this.trCourseName = trCourseName;
    }

    @Basic
    @Column(name = "tr_course_units", nullable = true, precision = 0)
    public Double getTrCourseUnits() {
        return trCourseUnits;
    }

    public void setTrCourseUnits(Double trCourseUnits) {
        this.trCourseUnits = trCourseUnits;
    }

    @Basic
    @Column(name = "tr_course_level", nullable = true, length = 20)
    public String getTrCourseLevel() {
        return trCourseLevel;
    }

    public void setTrCourseLevel(String trCourseLevel) {
        this.trCourseLevel = trCourseLevel;
    }

    @Basic
    @Column(name = "tr_course_term", nullable = true, length = 20)
    public String getTrCourseTerm() {
        return trCourseTerm;
    }

    public void setTrCourseTerm(String trCourseTerm) {
        this.trCourseTerm = trCourseTerm;
    }

    @Basic
    @Column(name = "tr_course_grade", nullable = true, length = 20)
    public String getTrCourseGrade() {
        return trCourseGrade;
    }

    public void setTrCourseGrade(String trCourseGrade) {
        this.trCourseGrade = trCourseGrade;
    }

    @Basic
    @Column(name = "tr_course_gpts", nullable = true, precision = 0)
    public Double getTrCourseGpts() {
        return trCourseGpts;
    }

    public void setTrCourseGpts(Double trCourseGpts) {
        this.trCourseGpts = trCourseGpts;
    }

    @Basic
    @Column(name = "tr_course_apply_status", nullable = true)
    public Byte getTrCourseApplyStatus() {
        return trCourseApplyStatus;
    }

    public void setTrCourseApplyStatus(Byte trCourseApplyStatus) {
        this.trCourseApplyStatus = trCourseApplyStatus;
    }

    @Basic
    @Column(name = "tr_course_apply_code", nullable = true, length = 128)
    public String getTrCourseApplyCode() {
        return trCourseApplyCode;
    }

    public void setTrCourseApplyCode(String trCourseApplyCode) {
        this.trCourseApplyCode = trCourseApplyCode;
    }

    @Basic
    @Column(name = "tr_course_oper", nullable = true, length = 36)
    public String getTrCourseOper() {
        return trCourseOper;
    }

    public void setTrCourseOper(String trCourseOper) {
        this.trCourseOper = trCourseOper;
    }

    @Basic
    @Column(name = "tr_course_transdate", nullable = true, length = 20)
    public String getTrCourseTransdate() {
        return trCourseTransdate;
    }

    public void setTrCourseTransdate(String trCourseTransdate) {
        this.trCourseTransdate = trCourseTransdate;
    }

    @Basic
    @Column(name = "tr_history_id", nullable = true, length = 255)
    public String getTrHistoryId() {
        return trHistoryId;
    }

    public void setTrHistoryId(String trHistoryId) {
        this.trHistoryId = trHistoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysTransferCourseEntity that = (SysTransferCourseEntity) o;

        if (trCourseId != null ? !trCourseId.equals(that.trCourseId) : that.trCourseId != null) return false;
        if (studentPostId != null ? !studentPostId.equals(that.studentPostId) : that.studentPostId != null)
            return false;
        if (institutionId != null ? !institutionId.equals(that.institutionId) : that.institutionId != null)
            return false;
        if (trCourseNumber != null ? !trCourseNumber.equals(that.trCourseNumber) : that.trCourseNumber != null)
            return false;
        if (trCourseName != null ? !trCourseName.equals(that.trCourseName) : that.trCourseName != null) return false;
        if (trCourseUnits != null ? !trCourseUnits.equals(that.trCourseUnits) : that.trCourseUnits != null)
            return false;
        if (trCourseLevel != null ? !trCourseLevel.equals(that.trCourseLevel) : that.trCourseLevel != null)
            return false;
        if (trCourseTerm != null ? !trCourseTerm.equals(that.trCourseTerm) : that.trCourseTerm != null) return false;
        if (trCourseGrade != null ? !trCourseGrade.equals(that.trCourseGrade) : that.trCourseGrade != null)
            return false;
        if (trCourseGpts != null ? !trCourseGpts.equals(that.trCourseGpts) : that.trCourseGpts != null) return false;
        if (trCourseApplyStatus != null ? !trCourseApplyStatus.equals(that.trCourseApplyStatus) : that.trCourseApplyStatus != null)
            return false;
        if (trCourseApplyCode != null ? !trCourseApplyCode.equals(that.trCourseApplyCode) : that.trCourseApplyCode != null)
            return false;
        if (trCourseOper != null ? !trCourseOper.equals(that.trCourseOper) : that.trCourseOper != null) return false;
        if (trCourseTransdate != null ? !trCourseTransdate.equals(that.trCourseTransdate) : that.trCourseTransdate != null)
            return false;
        if (trHistoryId != null ? !trHistoryId.equals(that.trHistoryId) : that.trHistoryId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trCourseId != null ? trCourseId.hashCode() : 0;
        result = 31 * result + (studentPostId != null ? studentPostId.hashCode() : 0);
        result = 31 * result + (institutionId != null ? institutionId.hashCode() : 0);
        result = 31 * result + (trCourseNumber != null ? trCourseNumber.hashCode() : 0);
        result = 31 * result + (trCourseName != null ? trCourseName.hashCode() : 0);
        result = 31 * result + (trCourseUnits != null ? trCourseUnits.hashCode() : 0);
        result = 31 * result + (trCourseLevel != null ? trCourseLevel.hashCode() : 0);
        result = 31 * result + (trCourseTerm != null ? trCourseTerm.hashCode() : 0);
        result = 31 * result + (trCourseGrade != null ? trCourseGrade.hashCode() : 0);
        result = 31 * result + (trCourseGpts != null ? trCourseGpts.hashCode() : 0);
        result = 31 * result + (trCourseApplyStatus != null ? trCourseApplyStatus.hashCode() : 0);
        result = 31 * result + (trCourseApplyCode != null ? trCourseApplyCode.hashCode() : 0);
        result = 31 * result + (trCourseOper != null ? trCourseOper.hashCode() : 0);
        result = 31 * result + (trCourseTransdate != null ? trCourseTransdate.hashCode() : 0);
        result = 31 * result + (trHistoryId != null ? trHistoryId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "student_post_id", referencedColumnName = "student_post_id" ,insertable = false,updatable = false)
    public SysStudentPostEntity getSysStudentPostByStudentPostId() {
        return sysStudentPostByStudentPostId;
    }

    public void setSysStudentPostByStudentPostId(SysStudentPostEntity sysStudentPostByStudentPostId) {
        this.sysStudentPostByStudentPostId = sysStudentPostByStudentPostId;
    }

    @ManyToOne
    @JoinColumn(name = "institution_id", referencedColumnName = "institution_id" ,insertable = false,updatable = false)
    public SysInstitutionEntity getSysInstitutionByInstitutionId() {
        return sysInstitutionByInstitutionId;
    }

    public void setSysInstitutionByInstitutionId(SysInstitutionEntity sysInstitutionByInstitutionId) {
        this.sysInstitutionByInstitutionId = sysInstitutionByInstitutionId;
    }

    @OneToMany(mappedBy = "sysTransferCourseByTrCourseId")
    public Collection<SysTransferHistoryEntity> getSysTransferHistoriesByTrCourseId() {
        return sysTransferHistoriesByTrCourseId;
    }

    public void setSysTransferHistoriesByTrCourseId(Collection<SysTransferHistoryEntity> sysTransferHistoriesByTrCourseId) {
        this.sysTransferHistoriesByTrCourseId = sysTransferHistoriesByTrCourseId;
    }
}
