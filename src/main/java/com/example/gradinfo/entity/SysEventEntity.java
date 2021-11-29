package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Objects;

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

    @Id
    @Column(name = "event_id")
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "event_code")
    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    @Basic
    @Column(name = "event_description")
    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    @Basic
    @Column(name = "event_conv")
    public String getEventConv() {
        return eventConv;
    }

    public void setEventConv(String eventConv) {
        this.eventConv = eventConv;
    }

    @Basic
    @Column(name = "event_format")
    public String getEventFormat() {
        return eventFormat;
    }

    public void setEventFormat(String eventFormat) {
        this.eventFormat = eventFormat;
    }

    @Basic
    @Column(name = "event_access_dept")
    public String getEventAccessDept() {
        return eventAccessDept;
    }

    public void setEventAccessDept(String eventAccessDept) {
        this.eventAccessDept = eventAccessDept;
    }

    @Basic
    @Column(name = "event_max_unit")
    public Double getEventMaxUnit() {
        return eventMaxUnit;
    }

    public void setEventMaxUnit(Double eventMaxUnit) {
        this.eventMaxUnit = eventMaxUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysEventEntity that = (SysEventEntity) o;
        return Objects.equals(eventId, that.eventId) && Objects.equals(eventCode, that.eventCode) && Objects.equals(eventDescription, that.eventDescription) && Objects.equals(eventConv, that.eventConv) && Objects.equals(eventFormat, that.eventFormat) && Objects.equals(eventAccessDept, that.eventAccessDept) && Objects.equals(eventMaxUnit, that.eventMaxUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventCode, eventDescription, eventConv, eventFormat, eventAccessDept, eventMaxUnit);
    }
}
