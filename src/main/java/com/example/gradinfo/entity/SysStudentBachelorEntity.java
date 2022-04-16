package com.example.gradinfo.entity;

import javax.persistence.*;

@Entity
@Table(name = "sys_student_bachelor", schema = "GradInfoV3", catalog = "")
public class SysStudentBachelorEntity {
    private int sbId;
    private String studentId;
    private String sbCeeb;
    private String sbName;
    private String sbDateEarned;
    private String sbDeanOverride;
    private SysStudentEntity sysStudentByStudentId;

    @Id
    @Column(name = "sb_id", nullable = false)
    public int getSbId() {
        return sbId;
    }

    public void setSbId(int sbId) {
        this.sbId = sbId;
    }

    @Basic
    @Column(name = "student_id", nullable = true, length = 36)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "sb_ceeb", nullable = true, length = 128)
    public String getSbCeeb() {
        return sbCeeb;
    }

    public void setSbCeeb(String sbCeeb) {
        this.sbCeeb = sbCeeb;
    }

    @Basic
    @Column(name = "sb_name", nullable = true, length = 128)
    public String getSbName() {
        return sbName;
    }

    public void setSbName(String sbName) {
        this.sbName = sbName;
    }

    @Basic
    @Column(name = "sb_date_earned", nullable = true, length = 20)
    public String getSbDateEarned() {
        return sbDateEarned;
    }

    public void setSbDateEarned(String sbDateEarned) {
        this.sbDateEarned = sbDateEarned;
    }

    @Basic
    @Column(name = "sb_dean_override", nullable = true, length = 20)
    public String getSbDeanOverride() {
        return sbDeanOverride;
    }

    public void setSbDeanOverride(String sbDeanOverride) {
        this.sbDeanOverride = sbDeanOverride;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysStudentBachelorEntity that = (SysStudentBachelorEntity) o;

        if (sbId != that.sbId) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (sbCeeb != null ? !sbCeeb.equals(that.sbCeeb) : that.sbCeeb != null) return false;
        if (sbName != null ? !sbName.equals(that.sbName) : that.sbName != null) return false;
        if (sbDateEarned != null ? !sbDateEarned.equals(that.sbDateEarned) : that.sbDateEarned != null) return false;
        if (sbDeanOverride != null ? !sbDeanOverride.equals(that.sbDeanOverride) : that.sbDeanOverride != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sbId;
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + (sbCeeb != null ? sbCeeb.hashCode() : 0);
        result = 31 * result + (sbName != null ? sbName.hashCode() : 0);
        result = 31 * result + (sbDateEarned != null ? sbDateEarned.hashCode() : 0);
        result = 31 * result + (sbDeanOverride != null ? sbDeanOverride.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", insertable = false, updatable = false)
    public SysStudentEntity getSysStudentByStudentId() {
        return sysStudentByStudentId;
    }

    public void setSysStudentByStudentId(SysStudentEntity sysStudentByStudentId) {
        this.sysStudentByStudentId = sysStudentByStudentId;
    }
}
