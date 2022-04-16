package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Collection;

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
    private String spThesisTitle;
    private Collection<SysAdmissionCourseEntity> sysAdmissionCoursesByStudentPostId;
    private Collection<SysCommentEntity> sysCommentsByStudentPostId;
    private Collection<SysDegreeCheckEntity> sysDegreeChecksByStudentPostId;
    private Collection<SysExamCommitteeEntity> sysExamCommitteesByStudentPostId;
    private Collection<SysNonCourseRelatedEventRecordEntity> sysNonCourseRelatedEventRecordsByStudentPostId;
    private Collection<SysStarsExceptionEntity> sysStarsExceptionsByStudentPostId;
    private SysStudentEntity sysStudentByStudentId;
    private Collection<SysThesisCommitteeEntity> sysThesisCommitteesByStudentPostId;
    private Collection<SysTransferCourseEntity> sysTransferCoursesByStudentPostId;

    @Id
    @Column(name = "student_post_id", nullable = false, length = 36)
    public String getStudentPostId() {
        return studentPostId;
    }

    public void setStudentPostId(String studentPostId) {
        this.studentPostId = studentPostId;
    }

    @Basic
    @Column(name = "student_id", nullable = true, length = 36)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "sp_post_number", nullable = true, length = 36)
    public String getSpPostNumber() {
        return spPostNumber;
    }

    public void setSpPostNumber(String spPostNumber) {
        this.spPostNumber = spPostNumber;
    }

    @Basic
    @Column(name = "sp_ndocs", nullable = true, length = 128)
    public String getSpNdocs() {
        return spNdocs;
    }

    public void setSpNdocs(String spNdocs) {
        this.spNdocs = spNdocs;
    }

    @Basic
    @Column(name = "sp_confu", nullable = true, length = 20)
    public String getSpConfu() {
        return spConfu;
    }

    public void setSpConfu(String spConfu) {
        this.spConfu = spConfu;
    }

    @Basic
    @Column(name = "sp_admit", nullable = true, length = 20)
    public String getSpAdmit() {
        return spAdmit;
    }

    public void setSpAdmit(String spAdmit) {
        this.spAdmit = spAdmit;
    }

    @Basic
    @Column(name = "sp_readmit", nullable = true, length = 20)
    public String getSpReadmit() {
        return spReadmit;
    }

    public void setSpReadmit(String spReadmit) {
        this.spReadmit = spReadmit;
    }

    @Basic
    @Column(name = "sp_transdate", nullable = true, length = 20)
    public String getSpTransdate() {
        return spTransdate;
    }

    public void setSpTransdate(String spTransdate) {
        this.spTransdate = spTransdate;
    }

    @Basic
    @Column(name = "sp_major", nullable = true, length = 36)
    public String getSpMajor() {
        return spMajor;
    }

    public void setSpMajor(String spMajor) {
        this.spMajor = spMajor;
    }

    @Basic
    @Column(name = "sp_obj", nullable = true, length = 128)
    public String getSpObj() {
        return spObj;
    }

    public void setSpObj(String spObj) {
        this.spObj = spObj;
    }

    @Basic
    @Column(name = "sp_rgunits", nullable = true, precision = 0)
    public Double getSpRgunits() {
        return spRgunits;
    }

    public void setSpRgunits(Double spRgunits) {
        this.spRgunits = spRgunits;
    }

    @Basic
    @Column(name = "sp_earnunits", nullable = true, precision = 0)
    public Double getSpEarnunits() {
        return spEarnunits;
    }

    public void setSpEarnunits(Double spEarnunits) {
        this.spEarnunits = spEarnunits;
    }

    @Basic
    @Column(name = "sp_gpa_apply", nullable = true, precision = 0)
    public Double getSpGpaApply() {
        return spGpaApply;
    }

    public void setSpGpaApply(Double spGpaApply) {
        this.spGpaApply = spGpaApply;
    }

    @Basic
    @Column(name = "sp_gpa_all", nullable = true, precision = 0)
    public Double getSpGpaAll() {
        return spGpaAll;
    }

    public void setSpGpaAll(Double spGpaAll) {
        this.spGpaAll = spGpaAll;
    }

    @Basic
    @Column(name = "sp_credit_limits", nullable = true)
    public Integer getSpCreditLimits() {
        return spCreditLimits;
    }

    public void setSpCreditLimits(Integer spCreditLimits) {
        this.spCreditLimits = spCreditLimits;
    }

    @Basic
    @Column(name = "sp_thesis_title", nullable = true, length = 512)
    public String getSpThesisTitle() {
        return spThesisTitle;
    }

    public void setSpThesisTitle(String spThesisTitle) {
        this.spThesisTitle = spThesisTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysStudentPostEntity that = (SysStudentPostEntity) o;

        if (studentPostId != null ? !studentPostId.equals(that.studentPostId) : that.studentPostId != null)
            return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (spPostNumber != null ? !spPostNumber.equals(that.spPostNumber) : that.spPostNumber != null) return false;
        if (spNdocs != null ? !spNdocs.equals(that.spNdocs) : that.spNdocs != null) return false;
        if (spConfu != null ? !spConfu.equals(that.spConfu) : that.spConfu != null) return false;
        if (spAdmit != null ? !spAdmit.equals(that.spAdmit) : that.spAdmit != null) return false;
        if (spReadmit != null ? !spReadmit.equals(that.spReadmit) : that.spReadmit != null) return false;
        if (spTransdate != null ? !spTransdate.equals(that.spTransdate) : that.spTransdate != null) return false;
        if (spMajor != null ? !spMajor.equals(that.spMajor) : that.spMajor != null) return false;
        if (spObj != null ? !spObj.equals(that.spObj) : that.spObj != null) return false;
        if (spRgunits != null ? !spRgunits.equals(that.spRgunits) : that.spRgunits != null) return false;
        if (spEarnunits != null ? !spEarnunits.equals(that.spEarnunits) : that.spEarnunits != null) return false;
        if (spGpaApply != null ? !spGpaApply.equals(that.spGpaApply) : that.spGpaApply != null) return false;
        if (spGpaAll != null ? !spGpaAll.equals(that.spGpaAll) : that.spGpaAll != null) return false;
        if (spCreditLimits != null ? !spCreditLimits.equals(that.spCreditLimits) : that.spCreditLimits != null)
            return false;
        if (spThesisTitle != null ? !spThesisTitle.equals(that.spThesisTitle) : that.spThesisTitle != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentPostId != null ? studentPostId.hashCode() : 0;
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + (spPostNumber != null ? spPostNumber.hashCode() : 0);
        result = 31 * result + (spNdocs != null ? spNdocs.hashCode() : 0);
        result = 31 * result + (spConfu != null ? spConfu.hashCode() : 0);
        result = 31 * result + (spAdmit != null ? spAdmit.hashCode() : 0);
        result = 31 * result + (spReadmit != null ? spReadmit.hashCode() : 0);
        result = 31 * result + (spTransdate != null ? spTransdate.hashCode() : 0);
        result = 31 * result + (spMajor != null ? spMajor.hashCode() : 0);
        result = 31 * result + (spObj != null ? spObj.hashCode() : 0);
        result = 31 * result + (spRgunits != null ? spRgunits.hashCode() : 0);
        result = 31 * result + (spEarnunits != null ? spEarnunits.hashCode() : 0);
        result = 31 * result + (spGpaApply != null ? spGpaApply.hashCode() : 0);
        result = 31 * result + (spGpaAll != null ? spGpaAll.hashCode() : 0);
        result = 31 * result + (spCreditLimits != null ? spCreditLimits.hashCode() : 0);
        result = 31 * result + (spThesisTitle != null ? spThesisTitle.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sysStudentPostByStudentPostId")
    public Collection<SysAdmissionCourseEntity> getSysAdmissionCoursesByStudentPostId() {
        return sysAdmissionCoursesByStudentPostId;
    }

    public void setSysAdmissionCoursesByStudentPostId(Collection<SysAdmissionCourseEntity> sysAdmissionCoursesByStudentPostId) {
        this.sysAdmissionCoursesByStudentPostId = sysAdmissionCoursesByStudentPostId;
    }

    @OneToMany(mappedBy = "sysStudentPostByStudentPostId")
    public Collection<SysCommentEntity> getSysCommentsByStudentPostId() {
        return sysCommentsByStudentPostId;
    }

    public void setSysCommentsByStudentPostId(Collection<SysCommentEntity> sysCommentsByStudentPostId) {
        this.sysCommentsByStudentPostId = sysCommentsByStudentPostId;
    }

    @OneToMany(mappedBy = "sysStudentPostByStudentPostId")
    public Collection<SysDegreeCheckEntity> getSysDegreeChecksByStudentPostId() {
        return sysDegreeChecksByStudentPostId;
    }

    public void setSysDegreeChecksByStudentPostId(Collection<SysDegreeCheckEntity> sysDegreeChecksByStudentPostId) {
        this.sysDegreeChecksByStudentPostId = sysDegreeChecksByStudentPostId;
    }

    @OneToMany(mappedBy = "sysStudentPostByStudentPostId")
    public Collection<SysExamCommitteeEntity> getSysExamCommitteesByStudentPostId() {
        return sysExamCommitteesByStudentPostId;
    }

    public void setSysExamCommitteesByStudentPostId(Collection<SysExamCommitteeEntity> sysExamCommitteesByStudentPostId) {
        this.sysExamCommitteesByStudentPostId = sysExamCommitteesByStudentPostId;
    }

    @OneToMany(mappedBy = "sysStudentPostByStudentPostId")
    public Collection<SysNonCourseRelatedEventRecordEntity> getSysNonCourseRelatedEventRecordsByStudentPostId() {
        return sysNonCourseRelatedEventRecordsByStudentPostId;
    }

    public void setSysNonCourseRelatedEventRecordsByStudentPostId(Collection<SysNonCourseRelatedEventRecordEntity> sysNonCourseRelatedEventRecordsByStudentPostId) {
        this.sysNonCourseRelatedEventRecordsByStudentPostId = sysNonCourseRelatedEventRecordsByStudentPostId;
    }

    @OneToMany(mappedBy = "sysStudentPostByStudentPostId")
    public Collection<SysStarsExceptionEntity> getSysStarsExceptionsByStudentPostId() {
        return sysStarsExceptionsByStudentPostId;
    }

    public void setSysStarsExceptionsByStudentPostId(Collection<SysStarsExceptionEntity> sysStarsExceptionsByStudentPostId) {
        this.sysStarsExceptionsByStudentPostId = sysStarsExceptionsByStudentPostId;
    }

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", insertable = false, updatable = false)
    public SysStudentEntity getSysStudentByStudentId() {
        return sysStudentByStudentId;
    }

    public void setSysStudentByStudentId(SysStudentEntity sysStudentByStudentId) {
        this.sysStudentByStudentId = sysStudentByStudentId;
    }

    @OneToMany(mappedBy = "sysStudentPostByStudentPostId")
    public Collection<SysThesisCommitteeEntity> getSysThesisCommitteesByStudentPostId() {
        return sysThesisCommitteesByStudentPostId;
    }

    public void setSysThesisCommitteesByStudentPostId(Collection<SysThesisCommitteeEntity> sysThesisCommitteesByStudentPostId) {
        this.sysThesisCommitteesByStudentPostId = sysThesisCommitteesByStudentPostId;
    }

    @OneToMany(mappedBy = "sysStudentPostByStudentPostId")
    public Collection<SysTransferCourseEntity> getSysTransferCoursesByStudentPostId() {
        return sysTransferCoursesByStudentPostId;
    }

    public void setSysTransferCoursesByStudentPostId(Collection<SysTransferCourseEntity> sysTransferCoursesByStudentPostId) {
        this.sysTransferCoursesByStudentPostId = sysTransferCoursesByStudentPostId;
    }
}
