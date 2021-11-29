package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_non_course_related_event_record", schema = "GradInfoV3", catalog = "")
public class SysNonCourseRelatedEventRecordEntity {
    private int ncrerId;
    private String eventId;
    private String studentPostId;
    private String ncrerRelated;
    private String ncrerDate;
    private String ncrerTransdate;
    private String ncrerOper;

    @Id
    @Column(name = "ncrer_id")
    public int getNcrerId() {
        return ncrerId;
    }

    public void setNcrerId(int ncrerId) {
        this.ncrerId = ncrerId;
    }

    @Basic
    @Column(name = "event_id")
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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
    @Column(name = "ncrer_related")
    public String getNcrerRelated() {
        return ncrerRelated;
    }

    public void setNcrerRelated(String ncrerRelated) {
        this.ncrerRelated = ncrerRelated;
    }

    @Basic
    @Column(name = "ncrer_date")
    public String getNcrerDate() {
        return ncrerDate;
    }

    public void setNcrerDate(String ncrerDate) {
        this.ncrerDate = ncrerDate;
    }

    @Basic
    @Column(name = "ncrer_transdate")
    public String getNcrerTransdate() {
        return ncrerTransdate;
    }

    public void setNcrerTransdate(String ncrerTransdate) {
        this.ncrerTransdate = ncrerTransdate;
    }

    @Basic
    @Column(name = "ncrer_oper")
    public String getNcrerOper() {
        return ncrerOper;
    }

    public void setNcrerOper(String ncrerOper) {
        this.ncrerOper = ncrerOper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysNonCourseRelatedEventRecordEntity that = (SysNonCourseRelatedEventRecordEntity) o;
        return ncrerId == that.ncrerId && Objects.equals(eventId, that.eventId) && Objects.equals(studentPostId, that.studentPostId) && Objects.equals(ncrerRelated, that.ncrerRelated) && Objects.equals(ncrerDate, that.ncrerDate) && Objects.equals(ncrerTransdate, that.ncrerTransdate) && Objects.equals(ncrerOper, that.ncrerOper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ncrerId, eventId, studentPostId, ncrerRelated, ncrerDate, ncrerTransdate, ncrerOper);
    }
}
