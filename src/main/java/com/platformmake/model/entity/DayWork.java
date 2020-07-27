package com.platformmake.model.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DayWork {

	public DayWork(Integer id, Integer workid, Integer eqid, Date sttime, Date entime, Integer workingCount,
			Integer quaCount, Integer unquaCount, Integer comTrack, String mark) {
		super();
		this.id = id;
		this.workid = workid;
		this.eqid = eqid;
		this.sttime = sttime;
		this.entime = entime;
		this.workingCount = workingCount;
		this.quaCount = quaCount;
		this.unquaCount = unquaCount;
		this.comTrack = comTrack;
		this.mark = mark;
	}

	public DayWork() {
		// TODO 自动生成的构造函数存根
	}

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column day_work.id
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column day_work.workid
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    private Integer workid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column day_work.eqid
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    private Integer eqid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column day_work.sttime
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date sttime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column day_work.entime
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date entime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column day_work.working_count
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    private Integer workingCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column day_work.qua_count
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    private Integer quaCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column day_work.unqua_count
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    private Integer unquaCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column day_work.com_track
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    private Integer comTrack;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column day_work.mark
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    private String mark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column day_work.id
     *
     * @return the value of day_work.id
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column day_work.id
     *
     * @param id the value for day_work.id
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column day_work.workid
     *
     * @return the value of day_work.workid
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public Integer getWorkid() {
        return workid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column day_work.workid
     *
     * @param workid the value for day_work.workid
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public void setWorkid(Integer workid) {
        this.workid = workid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column day_work.eqid
     *
     * @return the value of day_work.eqid
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public Integer getEqid() {
        return eqid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column day_work.eqid
     *
     * @param eqid the value for day_work.eqid
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public void setEqid(Integer eqid) {
        this.eqid = eqid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column day_work.sttime
     *
     * @return the value of day_work.sttime
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public Date getSttime() {
        return sttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column day_work.sttime
     *
     * @param sttime the value for day_work.sttime
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public void setSttime(Date sttime) {
        this.sttime = sttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column day_work.entime
     *
     * @return the value of day_work.entime
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public Date getEntime() {
        return entime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column day_work.entime
     *
     * @param entime the value for day_work.entime
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public void setEntime(Date entime) {
        this.entime = entime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column day_work.working_count
     *
     * @return the value of day_work.working_count
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public Integer getWorkingCount() {
        return workingCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column day_work.working_count
     *
     * @param workingCount the value for day_work.working_count
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public void setWorkingCount(Integer workingCount) {
        this.workingCount = workingCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column day_work.qua_count
     *
     * @return the value of day_work.qua_count
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public Integer getQuaCount() {
        return quaCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column day_work.qua_count
     *
     * @param quaCount the value for day_work.qua_count
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public void setQuaCount(Integer quaCount) {
        this.quaCount = quaCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column day_work.unqua_count
     *
     * @return the value of day_work.unqua_count
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public Integer getUnquaCount() {
        return unquaCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column day_work.unqua_count
     *
     * @param unquaCount the value for day_work.unqua_count
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public void setUnquaCount(Integer unquaCount) {
        this.unquaCount = unquaCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column day_work.com_track
     *
     * @return the value of day_work.com_track
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public Integer getComTrack() {
        return comTrack;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column day_work.com_track
     *
     * @param comTrack the value for day_work.com_track
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public void setComTrack(Integer comTrack) {
        this.comTrack = comTrack;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column day_work.mark
     *
     * @return the value of day_work.mark
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public String getMark() {
        return mark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column day_work.mark
     *
     * @param mark the value for day_work.mark
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

	@Override
	public String toString() {
		return "DayWork [id=" + id + ", workid=" + workid + ", eqid=" + eqid + ", sttime=" + sttime + ", entime="
				+ entime + ", workingCount=" + workingCount + ", quaCount=" + quaCount + ", unquaCount=" + unquaCount
				+ ", comTrack=" + comTrack + ", mark=" + mark + "]";
	}
    
    
}