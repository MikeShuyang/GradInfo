package com.example.gradinfo.entity;

import javax.persistence.*;

@Entity
@Table(name = "sys_degree_check", schema = "GradInfoV3", catalog = "")
public class SysDegreeCheckEntity {
    private int degreeCheckId;
    private String studentPostId;
    private String degreeName;
    private String degreeAdmissionTerm;
    private String degreeGraduationTerm;
    private String degreeCatalogYear;
    private String degreeForeignLanguage;
    private Byte degreeCheckCompleted;
    private SysStudentPostEntity sysStudentPostByStudentPostId;

    @Id
    @Column(name = "degree_check_id", nullable = false)
    public int getDegreeCheckId() {
        return degreeCheckId;
    }

    public void setDegreeCheckId(int degreeCheckId) {
        this.degreeCheckId = degreeCheckId;
    }

    @Basic
    @Column(name = "student_post_id", nullable = false, length = 36)
    public String getStudentPostId() {
        return studentPostId;
    }

    public void setStudentPostId(String studentPostId) {
        this.studentPostId = studentPostId;
    }

    @Basic
    @Column(name = "degree_name", nullable = false, length = 36)
    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    @Basic
    @Column(name = "degree_admission_term", nullable = true, length = 5)
    public String getDegreeAdmissionTerm() {
        return degreeAdmissionTerm;
    }

    public void setDegreeAdmissionTerm(String degreeAdmissionTerm) {
        this.degreeAdmissionTerm = degreeAdmissionTerm;
    }

    @Basic
    @Column(name = "degree_graduation_term", nullable = true, length = 5)
    public String getDegreeGraduationTerm() {
        return degreeGraduationTerm;
    }

    public void setDegreeGraduationTerm(String degreeGraduationTerm) {
        this.degreeGraduationTerm = degreeGraduationTerm;
    }

    @Basic
    @Column(name = "degree_catalog_year", nullable = true, length = 5)
    public String getDegreeCatalogYear() {
        return degreeCatalogYear;
    }

    public void setDegreeCatalogYear(String degreeCatalogYear) {
        this.degreeCatalogYear = degreeCatalogYear;
    }

    @Basic
    @Column(name = "degree_foreign_language", nullable = true, length = 1)
    public String getDegreeForeignLanguage() {
        return degreeForeignLanguage;
    }

    public void setDegreeForeignLanguage(String degreeForeignLanguage) {
        this.degreeForeignLanguage = degreeForeignLanguage;
    }

    @Basic
    @Column(name = "degree_check_completed", nullable = true)
    public Byte getDegreeCheckCompleted() {
        return degreeCheckCompleted;
    }

    public void setDegreeCheckCompleted(Byte degreeCheckCompleted) {
        this.degreeCheckCompleted = degreeCheckCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysDegreeCheckEntity that = (SysDegreeCheckEntity) o;

        if (degreeCheckId != that.degreeCheckId) return false;
        if (studentPostId != null ? !studentPostId.equals(that.studentPostId) : that.studentPostId != null)
            return false;
        if (degreeName != null ? !degreeName.equals(that.degreeName) : that.degreeName != null) return false;
        if (degreeAdmissionTerm != null ? !degreeAdmissionTerm.equals(that.degreeAdmissionTerm) : that.degreeAdmissionTerm != null)
            return false;
        if (degreeGraduationTerm != null ? !degreeGraduationTerm.equals(that.degreeGraduationTerm) : that.degreeGraduationTerm != null)
            return false;
        if (degreeCatalogYear != null ? !degreeCatalogYear.equals(that.degreeCatalogYear) : that.degreeCatalogYear != null)
            return false;
        if (degreeForeignLanguage != null ? !degreeForeignLanguage.equals(that.degreeForeignLanguage) : that.degreeForeignLanguage != null)
            return false;
        if (degreeCheckCompleted != null ? !degreeCheckCompleted.equals(that.degreeCheckCompleted) : that.degreeCheckCompleted != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = degreeCheckId;
        result = 31 * result + (studentPostId != null ? studentPostId.hashCode() : 0);
        result = 31 * result + (degreeName != null ? degreeName.hashCode() : 0);
        result = 31 * result + (degreeAdmissionTerm != null ? degreeAdmissionTerm.hashCode() : 0);
        result = 31 * result + (degreeGraduationTerm != null ? degreeGraduationTerm.hashCode() : 0);
        result = 31 * result + (degreeCatalogYear != null ? degreeCatalogYear.hashCode() : 0);
        result = 31 * result + (degreeForeignLanguage != null ? degreeForeignLanguage.hashCode() : 0);
        result = 31 * result + (degreeCheckCompleted != null ? degreeCheckCompleted.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "student_post_id", referencedColumnName = "student_post_id", nullable = false, insertable = false, updatable = false)
    public SysStudentPostEntity getSysStudentPostByStudentPostId() {
        return sysStudentPostByStudentPostId;
    }

    public void setSysStudentPostByStudentPostId(SysStudentPostEntity sysStudentPostByStudentPostId) {
        this.sysStudentPostByStudentPostId = sysStudentPostByStudentPostId;
    }
}
