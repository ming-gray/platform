package com.platformmake.model.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Workinfo {
	
	//private Planinfo planinfo;
	
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workinfo.workid
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    private Integer workid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workinfo.cretime
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cretime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workinfo.updtime
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workinfo.workcount
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    private Integer workcount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workinfo.workstate
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    private Integer workstate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workinfo.planid
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    private Integer planid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workinfo.eqid
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    private Integer eqid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workinfo.proid
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    private Integer proid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workinfo.worksttime
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date worksttime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workinfo.workentime
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date workentime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workinfo.workid
     *
     * @return the value of workinfo.workid
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public Integer getWorkid() {
        return workid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workinfo.workid
     *
     * @param workid the value for workinfo.workid
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public void setWorkid(Integer workid) {
        this.workid = workid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workinfo.cretime
     *
     * @return the value of workinfo.cretime
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public Date getCretime() {
        return cretime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workinfo.cretime
     *
     * @param cretime the value for workinfo.cretime
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public void setCretime(Date cretime) {
        this.cretime = cretime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workinfo.updtime
     *
     * @return the value of workinfo.updtime
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public Date getUpdtime() {
        return updtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workinfo.updtime
     *
     * @param updtime the value for workinfo.updtime
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public void setUpdtime(Date updtime) {
        this.updtime = updtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workinfo.workcount
     *
     * @return the value of workinfo.workcount
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public Integer getWorkcount() {
        return workcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workinfo.workcount
     *
     * @param workcount the value for workinfo.workcount
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public void setWorkcount(Integer workcount) {
        this.workcount = workcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workinfo.workstate
     *
     * @return the value of workinfo.workstate
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public Integer getWorkstate() {
        return workstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workinfo.workstate
     *
     * @param workstate the value for workinfo.workstate
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public void setWorkstate(Integer workstate) {
        this.workstate = workstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workinfo.planid
     *
     * @return the value of workinfo.planid
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public Integer getPlanid() {
        return planid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workinfo.planid
     *
     * @param planid the value for workinfo.planid
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public void setPlanid(Integer planid) {
        this.planid = planid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workinfo.eqid
     *
     * @return the value of workinfo.eqid
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public Integer getEqid() {
        return eqid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workinfo.eqid
     *
     * @param eqid the value for workinfo.eqid
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public void setEqid(Integer eqid) {
        this.eqid = eqid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workinfo.proid
     *
     * @return the value of workinfo.proid
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public Integer getProid() {
        return proid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workinfo.proid
     *
     * @param proid the value for workinfo.proid
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public void setProid(Integer proid) {
        this.proid = proid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workinfo.worksttime
     *
     * @return the value of workinfo.worksttime
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public Date getWorksttime() {
        return worksttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workinfo.worksttime
     *
     * @param worksttime the value for workinfo.worksttime
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public void setWorksttime(Date worksttime) {
        this.worksttime = worksttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workinfo.workentime
     *
     * @return the value of workinfo.workentime
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public Date getWorkentime() {
        return workentime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workinfo.workentime
     *
     * @param workentime the value for workinfo.workentime
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public void setWorkentime(Date workentime) {
        this.workentime = workentime;
    }

	public Workinfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Workinfo(Integer workid, Date cretime, Date updtime, Integer workcount, Integer workstate, Integer planid,
			Integer eqid, Integer proid, Date worksttime, Date workentime) {
		super();
		this.workid = workid;
		this.cretime = cretime;
		this.updtime = updtime;
		this.workcount = workcount;
		this.workstate = workstate;
		this.planid = planid;
		this.eqid = eqid;
		this.proid = proid;
		this.worksttime = worksttime;
		this.workentime = workentime;
	}

	@Override
	public String toString() {
		return "Workinfo [workid=" + workid + ", cretime=" + cretime + ", updtime=" + updtime + ", workcount="
				+ workcount + ", workstate=" + workstate + ", planid=" + planid + ", eqid=" + eqid + ", proid=" + proid
				+ ", worksttime=" + worksttime + ", workentime=" + workentime + "]";
	}
    
    
    
}