package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "sys_student", schema = "GradInfoV3", catalog = "")
public class SysStudentEntity {
    private String studentId;
    private String studentName;
    private String studentSex;
    private String studentBirthday;
    private String studentCountry;
    private String studentRace;
    private String studentPhone;
    private String studentEmail;
    private String studentPAddress;
    private String studentTAddress;
    private String studentErPhone;
    private String studentErName;
    private Collection<SysStudentBachelorEntity> sysStudentBachelorsByStudentId;
    private Collection<SysStudentPostEntity> sysStudentPostsByStudentId;

    @Id
    @Column(name = "student_id", nullable = false, length = 36)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "student_name", nullable = true, length = 128)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "student_sex", nullable = true, length = 20)
    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    @Basic
    @Column(name = "student_birthday", nullable = true, length = 20)
    public String getStudentBirthday() {
        return studentBirthday;
    }

    public void setStudentBirthday(String studentBirthday) {
        this.studentBirthday = studentBirthday;
    }

    @Basic
    @Column(name = "student_country", nullable = true, length = 128)
    public String getStudentCountry() {
        return studentCountry;
    }

    public void setStudentCountry(String studentCountry) {
        this.studentCountry = studentCountry;
    }

    @Basic
    @Column(name = "student_race", nullable = true, length = 20)
    public String getStudentRace() {
        return studentRace;
    }

    public void setStudentRace(String studentRace) {
        this.studentRace = studentRace;
    }

    @Basic
    @Column(name = "student_phone", nullable = true, length = 20)
    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    @Basic
    @Column(name = "student_email", nullable = true, length = 128)
    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    @Basic
    @Column(name = "student_p_address", nullable = true, length = 128)
    public String getStudentPAddress() {
        return studentPAddress;
    }

    public void setStudentPAddress(String studentPAddress) {
        this.studentPAddress = studentPAddress;
    }

    @Basic
    @Column(name = "student_t_address", nullable = true, length = 128)
    public String getStudentTAddress() {
        return studentTAddress;
    }

    public void setStudentTAddress(String studentTAddress) {
        this.studentTAddress = studentTAddress;
    }

    @Basic
    @Column(name = "student_er_phone", nullable = true, length = 20)
    public String getStudentErPhone() {
        return studentErPhone;
    }

    public void setStudentErPhone(String studentErPhone) {
        this.studentErPhone = studentErPhone;
    }

    @Basic
    @Column(name = "student_er_name", nullable = true, length = 20)
    public String getStudentErName() {
        return studentErName;
    }

    public void setStudentErName(String studentErName) {
        this.studentErName = studentErName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysStudentEntity that = (SysStudentEntity) o;

        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (studentName != null ? !studentName.equals(that.studentName) : that.studentName != null) return false;
        if (studentSex != null ? !studentSex.equals(that.studentSex) : that.studentSex != null) return false;
        if (studentBirthday != null ? !studentBirthday.equals(that.studentBirthday) : that.studentBirthday != null)
            return false;
        if (studentCountry != null ? !studentCountry.equals(that.studentCountry) : that.studentCountry != null)
            return false;
        if (studentRace != null ? !studentRace.equals(that.studentRace) : that.studentRace != null) return false;
        if (studentPhone != null ? !studentPhone.equals(that.studentPhone) : that.studentPhone != null) return false;
        if (studentEmail != null ? !studentEmail.equals(that.studentEmail) : that.studentEmail != null) return false;
        if (studentPAddress != null ? !studentPAddress.equals(that.studentPAddress) : that.studentPAddress != null)
            return false;
        if (studentTAddress != null ? !studentTAddress.equals(that.studentTAddress) : that.studentTAddress != null)
            return false;
        if (studentErPhone != null ? !studentErPhone.equals(that.studentErPhone) : that.studentErPhone != null)
            return false;
        if (studentErName != null ? !studentErName.equals(that.studentErName) : that.studentErName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (studentName != null ? studentName.hashCode() : 0);
        result = 31 * result + (studentSex != null ? studentSex.hashCode() : 0);
        result = 31 * result + (studentBirthday != null ? studentBirthday.hashCode() : 0);
        result = 31 * result + (studentCountry != null ? studentCountry.hashCode() : 0);
        result = 31 * result + (studentRace != null ? studentRace.hashCode() : 0);
        result = 31 * result + (studentPhone != null ? studentPhone.hashCode() : 0);
        result = 31 * result + (studentEmail != null ? studentEmail.hashCode() : 0);
        result = 31 * result + (studentPAddress != null ? studentPAddress.hashCode() : 0);
        result = 31 * result + (studentTAddress != null ? studentTAddress.hashCode() : 0);
        result = 31 * result + (studentErPhone != null ? studentErPhone.hashCode() : 0);
        result = 31 * result + (studentErName != null ? studentErName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sysStudentByStudentId")
    public Collection<SysStudentBachelorEntity> getSysStudentBachelorsByStudentId() {
        return sysStudentBachelorsByStudentId;
    }

    public void setSysStudentBachelorsByStudentId(Collection<SysStudentBachelorEntity> sysStudentBachelorsByStudentId) {
        this.sysStudentBachelorsByStudentId = sysStudentBachelorsByStudentId;
    }

    @OneToMany(mappedBy = "sysStudentByStudentId")
    public Collection<SysStudentPostEntity> getSysStudentPostsByStudentId() {
        return sysStudentPostsByStudentId;
    }

    public void setSysStudentPostsByStudentId(Collection<SysStudentPostEntity> sysStudentPostsByStudentId) {
        this.sysStudentPostsByStudentId = sysStudentPostsByStudentId;
    }
}
