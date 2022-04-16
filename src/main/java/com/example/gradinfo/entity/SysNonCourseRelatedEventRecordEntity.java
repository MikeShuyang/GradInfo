package com.example.gradinfo.entity;

import javax.persistence.*;

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
    private SysEventEntity sysEventByEventId;
    private SysStudentPostEntity sysStudentPostByStudentPostId;

    @Id
    @Column(name = "ncrer_id", nullable = false)
    public int getNcrerId() {
        return ncrerId;
    }

    public void setNcrerId(int ncrerId) {
        this.ncrerId = ncrerId;
    }

    @Basic
    @Column(name = "event_id", nullable = true, length = 36)
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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
    @Column(name = "ncrer_related", nullable = true, length = 20)
    public String getNcrerRelated() {
        return ncrerRelated;
    }

    public void setNcrerRelated(String ncrerRelated) {
        this.ncrerRelated = ncrerRelated;
    }

    @Basic
    @Column(name = "ncrer_date", nullable = true, length = 20)
    public String getNcrerDate() {
        return ncrerDate;
    }

    public void setNcrerDate(String ncrerDate) {
        this.ncrerDate = ncrerDate;
    }

    @Basic
    @Column(name = "ncrer_transdate", nullable = true, length = 20)
    public String getNcrerTransdate() {
        return ncrerTransdate;
    }

    public void setNcrerTransdate(String ncrerTransdate) {
        this.ncrerTransdate = ncrerTransdate;
    }

    @Basic
    @Column(name = "ncrer_oper", nullable = true, length = 20)
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

        if (ncrerId != that.ncrerId) return false;
        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;
        if (studentPostId != null ? !studentPostId.equals(that.studentPostId) : that.studentPostId != null)
            return false;
        if (ncrerRelated != null ? !ncrerRelated.equals(that.ncrerRelated) : that.ncrerRelated != null) return false;
        if (ncrerDate != null ? !ncrerDate.equals(that.ncrerDate) : that.ncrerDate != null) return false;
        if (ncrerTransdate != null ? !ncrerTransdate.equals(that.ncrerTransdate) : that.ncrerTransdate != null)
            return false;
        if (ncrerOper != null ? !ncrerOper.equals(that.ncrerOper) : that.ncrerOper != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ncrerId;
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        result = 31 * result + (studentPostId != null ? studentPostId.hashCode() : 0);
        result = 31 * result + (ncrerRelated != null ? ncrerRelated.hashCode() : 0);
        result = 31 * result + (ncrerDate != null ? ncrerDate.hashCode() : 0);
        result = 31 * result + (ncrerTransdate != null ? ncrerTransdate.hashCode() : 0);
        result = 31 * result + (ncrerOper != null ? ncrerOper.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id", insertable = false, updatable = false)
    public SysEventEntity getSysEventByEventId() {
        return sysEventByEventId;
    }

    public void setSysEventByEventId(SysEventEntity sysEventByEventId) {
        this.sysEventByEventId = sysEventByEventId;
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
