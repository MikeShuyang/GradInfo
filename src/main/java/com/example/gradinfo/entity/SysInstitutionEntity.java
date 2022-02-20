package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "sys_institution", schema = "GradInfoV3", catalog = "")
public class SysInstitutionEntity {
    private String institutionId;
    private String institutionName;
    private String institutionCeeb;
    private String institutionDateEarned;
    private Collection<SysTransferCourseEntity> sysTransferCoursesByInstitutionId;

    @Id
    @Column(name = "institution_id", nullable = false, length = 36)
    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    @Basic
    @Column(name = "institution_name", nullable = true, length = 128)
    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    @Basic
    @Column(name = "institution_ceeb", nullable = true, length = 36)
    public String getInstitutionCeeb() {
        return institutionCeeb;
    }

    public void setInstitutionCeeb(String institutionCeeb) {
        this.institutionCeeb = institutionCeeb;
    }

    @Basic
    @Column(name = "institution_date_earned", nullable = true, length = 20)
    public String getInstitutionDateEarned() {
        return institutionDateEarned;
    }

    public void setInstitutionDateEarned(String institutionDateEarned) {
        this.institutionDateEarned = institutionDateEarned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysInstitutionEntity that = (SysInstitutionEntity) o;

        if (institutionId != null ? !institutionId.equals(that.institutionId) : that.institutionId != null)
            return false;
        if (institutionName != null ? !institutionName.equals(that.institutionName) : that.institutionName != null)
            return false;
        if (institutionCeeb != null ? !institutionCeeb.equals(that.institutionCeeb) : that.institutionCeeb != null)
            return false;
        if (institutionDateEarned != null ? !institutionDateEarned.equals(that.institutionDateEarned) : that.institutionDateEarned != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = institutionId != null ? institutionId.hashCode() : 0;
        result = 31 * result + (institutionName != null ? institutionName.hashCode() : 0);
        result = 31 * result + (institutionCeeb != null ? institutionCeeb.hashCode() : 0);
        result = 31 * result + (institutionDateEarned != null ? institutionDateEarned.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sysInstitutionByInstitutionId")
    public Collection<SysTransferCourseEntity> getSysTransferCoursesByInstitutionId() {
        return sysTransferCoursesByInstitutionId;
    }

    public void setSysTransferCoursesByInstitutionId(Collection<SysTransferCourseEntity> sysTransferCoursesByInstitutionId) {
        this.sysTransferCoursesByInstitutionId = sysTransferCoursesByInstitutionId;
    }
}
