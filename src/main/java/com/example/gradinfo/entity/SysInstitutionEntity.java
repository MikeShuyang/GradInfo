package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_institution", schema = "GradInfoV3", catalog = "")
public class SysInstitutionEntity {
    private String institutionId;
    private String institutionName;
    private String institutionCeeb;
    private String institutionDateEarned;

    @Id
    @Column(name = "institution_id")
    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    @Basic
    @Column(name = "institution_name")
    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    @Basic
    @Column(name = "institution_ceeb")
    public String getInstitutionCeeb() {
        return institutionCeeb;
    }

    public void setInstitutionCeeb(String institutionCeeb) {
        this.institutionCeeb = institutionCeeb;
    }

    @Basic
    @Column(name = "institution_date_earned")
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
        return Objects.equals(institutionId, that.institutionId) && Objects.equals(institutionName, that.institutionName) && Objects.equals(institutionCeeb, that.institutionCeeb) && Objects.equals(institutionDateEarned, that.institutionDateEarned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(institutionId, institutionName, institutionCeeb, institutionDateEarned);
    }
}
