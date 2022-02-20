package com.example.gradinfo.entity;

import javax.persistence.*;

@Entity
@Table(name = "sys_transfer_history", schema = "GradInfoV3", catalog = "")
public class SysTransferHistoryEntity {
    private int trHistoryId;
    private String trCourseId;
    private String trHistoryCourseOper;
    private String trHistoryCourseName;
    private Byte trHistoryCourseApplyStatus;
    private String trHistoryCourseTransdate;
    private SysTransferCourseEntity sysTransferCourseByTrCourseId;

    @Id
    @Column(name = "tr_history_id", nullable = false)
    public int getTrHistoryId() {
        return trHistoryId;
    }

    public void setTrHistoryId(int trHistoryId) {
        this.trHistoryId = trHistoryId;
    }

    @Basic
    @Column(name = "tr_course_id", nullable = true, length = 36)
    public String getTrCourseId() {
        return trCourseId;
    }

    public void setTrCourseId(String trCourseId) {
        this.trCourseId = trCourseId;
    }

    @Basic
    @Column(name = "tr_history_course_oper", nullable = true, length = 36)
    public String getTrHistoryCourseOper() {
        return trHistoryCourseOper;
    }

    public void setTrHistoryCourseOper(String trHistoryCourseOper) {
        this.trHistoryCourseOper = trHistoryCourseOper;
    }

    @Basic
    @Column(name = "tr_history_course_name", nullable = true, length = 128)
    public String getTrHistoryCourseName() {
        return trHistoryCourseName;
    }

    public void setTrHistoryCourseName(String trHistoryCourseName) {
        this.trHistoryCourseName = trHistoryCourseName;
    }

    @Basic
    @Column(name = "tr_history_course_apply_status", nullable = true)
    public Byte getTrHistoryCourseApplyStatus() {
        return trHistoryCourseApplyStatus;
    }

    public void setTrHistoryCourseApplyStatus(Byte trHistoryCourseApplyStatus) {
        this.trHistoryCourseApplyStatus = trHistoryCourseApplyStatus;
    }

    @Basic
    @Column(name = "tr_history_course_transdate", nullable = true, length = 255)
    public String getTrHistoryCourseTransdate() {
        return trHistoryCourseTransdate;
    }

    public void setTrHistoryCourseTransdate(String trHistoryCourseTransdate) {
        this.trHistoryCourseTransdate = trHistoryCourseTransdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysTransferHistoryEntity that = (SysTransferHistoryEntity) o;

        if (trHistoryId != that.trHistoryId) return false;
        if (trCourseId != null ? !trCourseId.equals(that.trCourseId) : that.trCourseId != null) return false;
        if (trHistoryCourseOper != null ? !trHistoryCourseOper.equals(that.trHistoryCourseOper) : that.trHistoryCourseOper != null)
            return false;
        if (trHistoryCourseName != null ? !trHistoryCourseName.equals(that.trHistoryCourseName) : that.trHistoryCourseName != null)
            return false;
        if (trHistoryCourseApplyStatus != null ? !trHistoryCourseApplyStatus.equals(that.trHistoryCourseApplyStatus) : that.trHistoryCourseApplyStatus != null)
            return false;
        if (trHistoryCourseTransdate != null ? !trHistoryCourseTransdate.equals(that.trHistoryCourseTransdate) : that.trHistoryCourseTransdate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trHistoryId;
        result = 31 * result + (trCourseId != null ? trCourseId.hashCode() : 0);
        result = 31 * result + (trHistoryCourseOper != null ? trHistoryCourseOper.hashCode() : 0);
        result = 31 * result + (trHistoryCourseName != null ? trHistoryCourseName.hashCode() : 0);
        result = 31 * result + (trHistoryCourseApplyStatus != null ? trHistoryCourseApplyStatus.hashCode() : 0);
        result = 31 * result + (trHistoryCourseTransdate != null ? trHistoryCourseTransdate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "tr_course_id", referencedColumnName = "tr_course_id" ,insertable = false,updatable = false)
    public SysTransferCourseEntity getSysTransferCourseByTrCourseId() {
        return sysTransferCourseByTrCourseId;
    }

    public void setSysTransferCourseByTrCourseId(SysTransferCourseEntity sysTransferCourseByTrCourseId) {
        this.sysTransferCourseByTrCourseId = sysTransferCourseByTrCourseId;
    }
}
