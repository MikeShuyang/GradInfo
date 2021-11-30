package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_transfer_history", schema = "GradInfoV3", catalog = "")
public class SysTransferHistoryEntity {
    private int trHistoryId;
    private String trCourseId;
    private String trHistoryCourseOper;
    private String trHistoryCourseName;
    private Byte trHistoryCourseApplyStatus;
    private String trHistoryCourseTransdate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tr_history_id")
    public int getTrHistoryId() {
        return trHistoryId;
    }

    public void setTrHistoryId(int trHistoryId) {
        this.trHistoryId = trHistoryId;
    }

    @Basic
    @Column(name = "tr_course_id")
    public String getTrCourseId() {
        return trCourseId;
    }

    public void setTrCourseId(String trCourseId) {
        this.trCourseId = trCourseId;
    }

    @Basic
    @Column(name = "tr_history_course_oper")
    public String getTrHistoryCourseOper() {
        return trHistoryCourseOper;
    }

    public void setTrHistoryCourseOper(String trHistoryCourseOper) {
        this.trHistoryCourseOper = trHistoryCourseOper;
    }

    @Basic
    @Column(name = "tr_history_course_name")
    public String getTrHistoryCourseName() {
        return trHistoryCourseName;
    }

    public void setTrHistoryCourseName(String trHistoryCourseName) {
        this.trHistoryCourseName = trHistoryCourseName;
    }

    @Basic
    @Column(name = "tr_history_course_apply_status")
    public Byte getTrHistoryCourseApplyStatus() {
        return trHistoryCourseApplyStatus;
    }

    public void setTrHistoryCourseApplyStatus(Byte trHistoryCourseApplyStatus) {
        this.trHistoryCourseApplyStatus = trHistoryCourseApplyStatus;
    }

    @Basic
    @Column(name = "tr_history_course_transdate")
    public String gettrHistoryCourseTransdate() {
        return trHistoryCourseTransdate;
    }

    public void settrHistoryCourseTransdate(String trHistoryCourseTransdate) {
        this.trHistoryCourseTransdate = trHistoryCourseTransdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysTransferHistoryEntity that = (SysTransferHistoryEntity) o;
        return trHistoryId == that.trHistoryId && Objects.equals(trCourseId, that.trCourseId) && Objects.equals(trHistoryCourseOper, that.trHistoryCourseOper) && Objects.equals(trHistoryCourseName, that.trHistoryCourseName) && Objects.equals(trHistoryCourseApplyStatus, that.trHistoryCourseApplyStatus) && Objects.equals(trHistoryCourseTransdate, that.trHistoryCourseTransdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trHistoryId, trCourseId, trHistoryCourseOper, trHistoryCourseName, trHistoryCourseApplyStatus, trHistoryCourseTransdate);
    }
}
