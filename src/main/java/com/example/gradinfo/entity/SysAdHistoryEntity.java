package com.example.gradinfo.entity;

import javax.persistence.*;

@Entity
@Table(name = "sys_ad_history", schema = "GradInfoV3", catalog = "")
public class SysAdHistoryEntity {
    private int adHistoryId;
    private String adHistoryCourseOper;
    private String adHistoryCourseName;
    private Byte adHistoryCourseApplyStatus;
    private String adHistoryCourseTransdate;
    private String adCourseId;
    private SysAdmissionCourseEntity sysAdmissionCourseByAdCourseId;

    @Id
    @Column(name = "ad_history_id", nullable = false)
    public int getAdHistoryId() {
        return adHistoryId;
    }

    public void setAdHistoryId(int adHistoryId) {
        this.adHistoryId = adHistoryId;
    }

    @Basic
    @Column(name = "ad_history_course_oper", nullable = true, length = 36)
    public String getAdHistoryCourseOper() {
        return adHistoryCourseOper;
    }

    public void setAdHistoryCourseOper(String adHistoryCourseOper) {
        this.adHistoryCourseOper = adHistoryCourseOper;
    }

    @Basic
    @Column(name = "ad_history_course_name", nullable = true, length = 128)
    public String getAdHistoryCourseName() {
        return adHistoryCourseName;
    }

    public void setAdHistoryCourseName(String adHistoryCourseName) {
        this.adHistoryCourseName = adHistoryCourseName;
    }

    @Basic
    @Column(name = "ad_history_course_apply_status", nullable = true)
    public Byte getAdHistoryCourseApplyStatus() {
        return adHistoryCourseApplyStatus;
    }

    public void setAdHistoryCourseApplyStatus(Byte adHistoryCourseApplyStatus) {
        this.adHistoryCourseApplyStatus = adHistoryCourseApplyStatus;
    }

    @Basic
    @Column(name = "ad_history_course_transdate", nullable = true, length = 20)
    public String getAdHistoryCourseTransdate() {
        return adHistoryCourseTransdate;
    }

    public void setAdHistoryCourseTransdate(String adHistoryCourseTransdate) {
        this.adHistoryCourseTransdate = adHistoryCourseTransdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysAdHistoryEntity that = (SysAdHistoryEntity) o;

        if (adHistoryId != that.adHistoryId) return false;
        if (adHistoryCourseOper != null ? !adHistoryCourseOper.equals(that.adHistoryCourseOper) : that.adHistoryCourseOper != null)
            return false;
        if (adHistoryCourseName != null ? !adHistoryCourseName.equals(that.adHistoryCourseName) : that.adHistoryCourseName != null)
            return false;
        if (adHistoryCourseApplyStatus != null ? !adHistoryCourseApplyStatus.equals(that.adHistoryCourseApplyStatus) : that.adHistoryCourseApplyStatus != null)
            return false;
        if (adHistoryCourseTransdate != null ? !adHistoryCourseTransdate.equals(that.adHistoryCourseTransdate) : that.adHistoryCourseTransdate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = adHistoryId;
        result = 31 * result + (adHistoryCourseOper != null ? adHistoryCourseOper.hashCode() : 0);
        result = 31 * result + (adHistoryCourseName != null ? adHistoryCourseName.hashCode() : 0);
        result = 31 * result + (adHistoryCourseApplyStatus != null ? adHistoryCourseApplyStatus.hashCode() : 0);
        result = 31 * result + (adHistoryCourseTransdate != null ? adHistoryCourseTransdate.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "ad_course_id", nullable = true, length = 36)
    public String getAdCourseId() {
        return adCourseId;
    }

    public void setAdCourseId(String adCourseId) {
        this.adCourseId = adCourseId;
    }

    @ManyToOne
    @JoinColumn(name = "ad_course_id", referencedColumnName = "ad_course_id" ,insertable = false,updatable = false)
    public SysAdmissionCourseEntity getSysAdmissionCourseByAdCourseId() {
        return sysAdmissionCourseByAdCourseId;
    }

    public void setSysAdmissionCourseByAdCourseId(SysAdmissionCourseEntity sysAdmissionCourseByAdCourseId) {
        this.sysAdmissionCourseByAdCourseId = sysAdmissionCourseByAdCourseId;
    }
}
