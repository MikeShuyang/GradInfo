package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_ad_history", schema = "GradInfoV3", catalog = "")
public class SysAdHistoryEntity {
    private int adHistoryId;
    private String adCourseId;
    private String adHistoryCourseOper;
    private String adHistoryCourseName;
    private Byte adHistoryCourseApplyStatus;
    private String adHistoryCourseTransdate;


    @Id
    @Column(name = "ad_history_id")
    public int getAdHistoryId() {
        return adHistoryId;
    }

    public void setAdHistoryId(int adHistoryId) {
        this.adHistoryId = adHistoryId;
    }

    @Basic
    @Column(name = "ad_course_id")
    public String getAdCourseId() {
        return adCourseId;
    }

    public void setAdCourseId(String adCourseId) {
        this.adCourseId = adCourseId;
    }

    @Basic
    @Column(name = "ad_history_course_oper")
    public String getAdHistoryCourseOper() {
        return adHistoryCourseOper;
    }

    public void setAdHistoryCourseOper(String adHistoryCourseOper) {
        this.adHistoryCourseOper = adHistoryCourseOper;
    }

    @Basic
    @Column(name = "ad_history_course_name")
    public String getAdHistoryCourseName() {
        return adHistoryCourseName;
    }

    public void setAdHistoryCourseName(String adHistoryCourseName) {
        this.adHistoryCourseName = adHistoryCourseName;
    }

    @Basic
    @Column(name = "ad_history_course_apply_status")
    public Byte getAdHistoryCourseApplyStatus() {
        return adHistoryCourseApplyStatus;
    }

    public void setAdHistoryCourseApplyStatus(Byte adHistoryCourseApplyStatus) {
        this.adHistoryCourseApplyStatus = adHistoryCourseApplyStatus;
    }

    @Basic
    @Column(name = "ad_history_course_transdate")
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
        return adHistoryId == that.adHistoryId && Objects.equals(adCourseId, that.adCourseId) && Objects.equals(adHistoryCourseOper, that.adHistoryCourseOper) && Objects.equals(adHistoryCourseName, that.adHistoryCourseName) && Objects.equals(adHistoryCourseApplyStatus, that.adHistoryCourseApplyStatus) && Objects.equals(adHistoryCourseTransdate, that.adHistoryCourseTransdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adHistoryId, adCourseId, adHistoryCourseOper, adHistoryCourseName, adHistoryCourseApplyStatus, adHistoryCourseTransdate);
    }
}
