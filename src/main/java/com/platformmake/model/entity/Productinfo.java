package com.platformmake.model.entity;

public class Productinfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column productinfo.proid
     *
     * @mbg.generated Thu Jul 23 10:17:05 CST 2020
     */
    private Integer proid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column productinfo.proname
     *
     * @mbg.generated Thu Jul 23 10:17:05 CST 2020
     */
    private String proname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column productinfo.propic
     *
     * @mbg.generated Thu Jul 23 10:17:05 CST 2020
     */
    private String propic;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column productinfo.proid
     *
     * @return the value of productinfo.proid
     *
     * @mbg.generated Thu Jul 23 10:17:05 CST 2020
     */
    public Integer getProid() {
        return proid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column productinfo.proid
     *
     * @param proid the value for productinfo.proid
     *
     * @mbg.generated Thu Jul 23 10:17:05 CST 2020
     */
    public void setProid(Integer proid) {
        this.proid = proid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column productinfo.proname
     *
     * @return the value of productinfo.proname
     *
     * @mbg.generated Thu Jul 23 10:17:05 CST 2020
     */
    public String getProname() {
        return proname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column productinfo.proname
     *
     * @param proname the value for productinfo.proname
     *
     * @mbg.generated Thu Jul 23 10:17:05 CST 2020
     */
    public void setProname(String proname) {
        this.proname = proname == null ? null : proname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column productinfo.propic
     *
     * @return the value of productinfo.propic
     *
     * @mbg.generated Thu Jul 23 10:17:05 CST 2020
     */
    public String getPropic() {
        return propic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column productinfo.propic
     *
     * @param propic the value for productinfo.propic
     *
     * @mbg.generated Thu Jul 23 10:17:05 CST 2020
     */
    public void setPropic(String propic) {
        this.propic = propic == null ? null : propic.trim();
    }
}