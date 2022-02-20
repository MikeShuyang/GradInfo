package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "sys_event", schema = "GradInfoV3", catalog = "")
public class SysEventEntity {
    private String eventId;
    private String eventCode;
    private String eventDescription;
    private String eventConv;
    private String eventFormat;
    private String eventAccessDept;
    private Double eventMaxUnit;
    private String eventAccessLevel;
    private Collection<SysNonCourseRelatedEventRecordEntity> sysNonCourseRelatedEventRecordsByEventId;

    @Id
    @Column(name = "event_id", nullable = false, length = 36)
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "event_code", nullable = true, length = 36)
    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    @Basic
    @Column(name = "event_description", nullable = true, length = 128)
    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    @Basic
    @Column(name = "event_conv", nullable = true, length = 20)
    public String getEventConv() {
        return eventConv;
    }

    public void setEventConv(String eventConv) {
        this.eventConv = eventConv;
    }

    @Basic
    @Column(name = "event_format", nullable = true, length = 20)
    public String getEventFormat() {
        return eventFormat;
    }

    public void setEventFormat(String eventFormat) {
        this.eventFormat = eventFormat;
    }

    @Basic
    @Column(name = "event_access_dept", nullable = true, length = 20)
    public String getEventAccessDept() {
        return eventAccessDept;
    }

    public void setEventAccessDept(String eventAccessDept) {
        this.eventAccessDept = eventAccessDept;
    }

    @Basic
    @Column(name = "event_max_unit", nullable = true, precision = 0)
    public Double getEventMaxUnit() {
        return eventMaxUnit;
    }

    public void setEventMaxUnit(Double eventMaxUnit) {
        this.eventMaxUnit = eventMaxUnit;
    }

    @Basic
    @Column(name = "event_access_level", nullable = true, length = 10)
    public String getEventAccessLevel() {
        return eventAccessLevel;
    }

    public void setEventAccessLevel(String eventAccessLevel) {
        this.eventAccessLevel = eventAccessLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysEventEntity that = (SysEventEntity) o;

        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;
        if (eventCode != null ? !eventCode.equals(that.eventCode) : that.eventCode != null) return false;
        if (eventDescription != null ? !eventDescription.equals(that.eventDescription) : that.eventDescription != null)
            return false;
        if (eventConv != null ? !eventConv.equals(that.eventConv) : that.eventConv != null) return false;
        if (eventFormat != null ? !eventFormat.equals(that.eventFormat) : that.eventFormat != null) return false;
        if (eventAccessDept != null ? !eventAccessDept.equals(that.eventAccessDept) : that.eventAccessDept != null)
            return false;
        if (eventMaxUnit != null ? !eventMaxUnit.equals(that.eventMaxUnit) : that.eventMaxUnit != null) return false;
        if (eventAccessLevel != null ? !eventAccessLevel.equals(that.eventAccessLevel) : that.eventAccessLevel != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventId != null ? eventId.hashCode() : 0;
        result = 31 * result + (eventCode != null ? eventCode.hashCode() : 0);
        result = 31 * result + (eventDescription != null ? eventDescription.hashCode() : 0);
        result = 31 * result + (eventConv != null ? eventConv.hashCode() : 0);
        result = 31 * result + (eventFormat != null ? eventFormat.hashCode() : 0);
        result = 31 * result + (eventAccessDept != null ? eventAccessDept.hashCode() : 0);
        result = 31 * result + (eventMaxUnit != null ? eventMaxUnit.hashCode() : 0);
        result = 31 * result + (eventAccessLevel != null ? eventAccessLevel.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sysEventByEventId")
    public Collection<SysNonCourseRelatedEventRecordEntity> getSysNonCourseRelatedEventRecordsByEventId() {
        return sysNonCourseRelatedEventRecordsByEventId;
    }

    public void setSysNonCourseRelatedEventRecordsByEventId(Collection<SysNonCourseRelatedEventRecordEntity> sysNonCourseRelatedEventRecordsByEventId) {
        this.sysNonCourseRelatedEventRecordsByEventId = sysNonCourseRelatedEventRecordsByEventId;
    }
}
