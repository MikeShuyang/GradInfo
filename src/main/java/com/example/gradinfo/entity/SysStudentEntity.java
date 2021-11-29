package com.example.gradinfo.entity;

import javax.persistence.*;
import java.util.Objects;

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

    @Id
    @Column(name = "student_id")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "student_name")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "student_sex")
    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    @Basic
    @Column(name = "student_birthday")
    public String getStudentBirthday() {
        return studentBirthday;
    }

    public void setStudentBirthday(String studentBirthday) {
        this.studentBirthday = studentBirthday;
    }

    @Basic
    @Column(name = "student_country")
    public String getStudentCountry() {
        return studentCountry;
    }

    public void setStudentCountry(String studentCountry) {
        this.studentCountry = studentCountry;
    }

    @Basic
    @Column(name = "student_race")
    public String getStudentRace() {
        return studentRace;
    }

    public void setStudentRace(String studentRace) {
        this.studentRace = studentRace;
    }

    @Basic
    @Column(name = "student_phone")
    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    @Basic
    @Column(name = "student_email")
    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    @Basic
    @Column(name = "student_p_address")
    public String getStudentPAddress() {
        return studentPAddress;
    }

    public void setStudentPAddress(String studentPAddress) {
        this.studentPAddress = studentPAddress;
    }

    @Basic
    @Column(name = "student_t_address")
    public String getStudentTAddress() {
        return studentTAddress;
    }

    public void setStudentTAddress(String studentTAddress) {
        this.studentTAddress = studentTAddress;
    }

    @Basic
    @Column(name = "student_er_phone")
    public String getStudentErPhone() {
        return studentErPhone;
    }

    public void setStudentErPhone(String studentErPhone) {
        this.studentErPhone = studentErPhone;
    }

    @Basic
    @Column(name = "student_er_name")
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
        return Objects.equals(studentId, that.studentId) && Objects.equals(studentName, that.studentName) && Objects.equals(studentSex, that.studentSex) && Objects.equals(studentBirthday, that.studentBirthday) && Objects.equals(studentCountry, that.studentCountry) && Objects.equals(studentRace, that.studentRace) && Objects.equals(studentPhone, that.studentPhone) && Objects.equals(studentEmail, that.studentEmail) && Objects.equals(studentPAddress, that.studentPAddress) && Objects.equals(studentTAddress, that.studentTAddress) && Objects.equals(studentErPhone, that.studentErPhone) && Objects.equals(studentErName, that.studentErName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentName, studentSex, studentBirthday, studentCountry, studentRace, studentPhone, studentEmail, studentPAddress, studentTAddress, studentErPhone, studentErName);
    }
}
