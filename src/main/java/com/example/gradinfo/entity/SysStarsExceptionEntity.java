package com.example.gradinfo.entity;

import javax.persistence.*;

@Entity
@Table(name = "sys_stars_exception", schema = "GradInfoV3", catalog = "")
public class SysStarsExceptionEntity {
    private int starsId;
    private String studentPostId;
    private String starsCd;
    private String starsRname;
    private String starsPsname;
    private String starsReqct;
    private String starsReqhrs;
    private String starsCline;
    private String starsDeptRep;
    private String starsDept;
    private String starsOper;
    private String starsTransdate;
    private String starsResponseMessage;
    private SysStudentPostEntity sysStudentPostByStudentPostId;

    @Id
    @Column(name = "stars_id", nullable = false)
    public int getStarsId() {
        return starsId;
    }

    public void setStarsId(int starsId) {
        this.starsId = starsId;
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
    @Column(name = "stars_cd", nullable = true, length = 2)
    public String getStarsCd() {
        return starsCd;
    }

    public void setStarsCd(String starsCd) {
        this.starsCd = starsCd;
    }

    @Basic
    @Column(name = "stars_rname", nullable = true, length = 36)
    public String getStarsRname() {
        return starsRname;
    }

    public void setStarsRname(String starsRname) {
        this.starsRname = starsRname;
    }

    @Basic
    @Column(name = "stars_psname", nullable = true, length = 36)
    public String getStarsPsname() {
        return starsPsname;
    }

    public void setStarsPsname(String starsPsname) {
        this.starsPsname = starsPsname;
    }

    @Basic
    @Column(name = "stars_reqct", nullable = true, length = 36)
    public String getStarsReqct() {
        return starsReqct;
    }

    public void setStarsReqct(String starsReqct) {
        this.starsReqct = starsReqct;
    }

    @Basic
    @Column(name = "stars_reqhrs", nullable = true, length = 36)
    public String getStarsReqhrs() {
        return starsReqhrs;
    }

    public void setStarsReqhrs(String starsReqhrs) {
        this.starsReqhrs = starsReqhrs;
    }

    @Basic
    @Column(name = "stars_cline", nullable = true, length = 36)
    public String getStarsCline() {
        return starsCline;
    }

    public void setStarsCline(String starsCline) {
        this.starsCline = starsCline;
    }

    @Basic
    @Column(name = "stars_dept_rep", nullable = true, length = 36)
    public String getStarsDeptRep() {
        return starsDeptRep;
    }

    public void setStarsDeptRep(String starsDeptRep) {
        this.starsDeptRep = starsDeptRep;
    }

    @Basic
    @Column(name = "stars_dept", nullable = true, length = 36)
    public String getStarsDept() {
        return starsDept;
    }

    public void setStarsDept(String starsDept) {
        this.starsDept = starsDept;
    }

    @Basic
    @Column(name = "stars_oper", nullable = true, length = 36)
    public String getStarsOper() {
        return starsOper;
    }

    public void setStarsOper(String starsOper) {
        this.starsOper = starsOper;
    }

    @Basic
    @Column(name = "stars_transdate", nullable = true, length = 36)
    public String getStarsTransdate() {
        return starsTransdate;
    }

    public void setStarsTransdate(String starsTransdate) {
        this.starsTransdate = starsTransdate;
    }

    @Basic
    @Column(name = "stars_response_message", nullable = true, length = 128)
    public String getStarsResponseMessage() {
        return starsResponseMessage;
    }

    public void setStarsResponseMessage(String starsResponseMessage) {
        this.starsResponseMessage = starsResponseMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysStarsExceptionEntity that = (SysStarsExceptionEntity) o;

        if (starsId != that.starsId) return false;
        if (studentPostId != null ? !studentPostId.equals(that.studentPostId) : that.studentPostId != null)
            return false;
        if (starsCd != null ? !starsCd.equals(that.starsCd) : that.starsCd != null) return false;
        if (starsRname != null ? !starsRname.equals(that.starsRname) : that.starsRname != null) return false;
        if (starsPsname != null ? !starsPsname.equals(that.starsPsname) : that.starsPsname != null) return false;
        if (starsReqct != null ? !starsReqct.equals(that.starsReqct) : that.starsReqct != null) return false;
        if (starsReqhrs != null ? !starsReqhrs.equals(that.starsReqhrs) : that.starsReqhrs != null) return false;
        if (starsCline != null ? !starsCline.equals(that.starsCline) : that.starsCline != null) return false;
        if (starsDeptRep != null ? !starsDeptRep.equals(that.starsDeptRep) : that.starsDeptRep != null) return false;
        if (starsDept != null ? !starsDept.equals(that.starsDept) : that.starsDept != null) return false;
        if (starsOper != null ? !starsOper.equals(that.starsOper) : that.starsOper != null) return false;
        if (starsTransdate != null ? !starsTransdate.equals(that.starsTransdate) : that.starsTransdate != null)
            return false;
        if (starsResponseMessage != null ? !starsResponseMessage.equals(that.starsResponseMessage) : that.starsResponseMessage != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = starsId;
        result = 31 * result + (studentPostId != null ? studentPostId.hashCode() : 0);
        result = 31 * result + (starsCd != null ? starsCd.hashCode() : 0);
        result = 31 * result + (starsRname != null ? starsRname.hashCode() : 0);
        result = 31 * result + (starsPsname != null ? starsPsname.hashCode() : 0);
        result = 31 * result + (starsReqct != null ? starsReqct.hashCode() : 0);
        result = 31 * result + (starsReqhrs != null ? starsReqhrs.hashCode() : 0);
        result = 31 * result + (starsCline != null ? starsCline.hashCode() : 0);
        result = 31 * result + (starsDeptRep != null ? starsDeptRep.hashCode() : 0);
        result = 31 * result + (starsDept != null ? starsDept.hashCode() : 0);
        result = 31 * result + (starsOper != null ? starsOper.hashCode() : 0);
        result = 31 * result + (starsTransdate != null ? starsTransdate.hashCode() : 0);
        result = 31 * result + (starsResponseMessage != null ? starsResponseMessage.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "student_post_id", referencedColumnName = "student_post_id" ,insertable = false,updatable = false)
    public SysStudentPostEntity getSysStudentPostByStudentPostId() {
        return sysStudentPostByStudentPostId;
    }

    public void setSysStudentPostByStudentPostId(SysStudentPostEntity sysStudentPostByStudentPostId) {
        this.sysStudentPostByStudentPostId = sysStudentPostByStudentPostId;
    }
}
