package com.platformmake.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class WorkinfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public WorkinfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andWorkidIsNull() {
            addCriterion("workid is null");
            return (Criteria) this;
        }

        public Criteria andWorkidIsNotNull() {
            addCriterion("workid is not null");
            return (Criteria) this;
        }

        public Criteria andWorkidEqualTo(Integer value) {
            addCriterion("workid =", value, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidNotEqualTo(Integer value) {
            addCriterion("workid <>", value, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidGreaterThan(Integer value) {
            addCriterion("workid >", value, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidGreaterThanOrEqualTo(Integer value) {
            addCriterion("workid >=", value, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidLessThan(Integer value) {
            addCriterion("workid <", value, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidLessThanOrEqualTo(Integer value) {
            addCriterion("workid <=", value, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidIn(List<Integer> values) {
            addCriterion("workid in", values, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidNotIn(List<Integer> values) {
            addCriterion("workid not in", values, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidBetween(Integer value1, Integer value2) {
            addCriterion("workid between", value1, value2, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidNotBetween(Integer value1, Integer value2) {
            addCriterion("workid not between", value1, value2, "workid");
            return (Criteria) this;
        }

        public Criteria andCretimeIsNull() {
            addCriterion("cretime is null");
            return (Criteria) this;
        }

        public Criteria andCretimeIsNotNull() {
            addCriterion("cretime is not null");
            return (Criteria) this;
        }

        public Criteria andCretimeEqualTo(Date value) {
            addCriterion("cretime =", value, "cretime");
            return (Criteria) this;
        }

        public Criteria andCretimeNotEqualTo(Date value) {
            addCriterion("cretime <>", value, "cretime");
            return (Criteria) this;
        }

        public Criteria andCretimeGreaterThan(Date value) {
            addCriterion("cretime >", value, "cretime");
            return (Criteria) this;
        }

        public Criteria andCretimeGreaterThanOrEqualTo(Date value) {
            addCriterion("cretime >=", value, "cretime");
            return (Criteria) this;
        }

        public Criteria andCretimeLessThan(Date value) {
            addCriterion("cretime <", value, "cretime");
            return (Criteria) this;
        }

        public Criteria andCretimeLessThanOrEqualTo(Date value) {
            addCriterion("cretime <=", value, "cretime");
            return (Criteria) this;
        }

        public Criteria andCretimeIn(List<Date> values) {
            addCriterion("cretime in", values, "cretime");
            return (Criteria) this;
        }

        public Criteria andCretimeNotIn(List<Date> values) {
            addCriterion("cretime not in", values, "cretime");
            return (Criteria) this;
        }

        public Criteria andCretimeBetween(Date value1, Date value2) {
            addCriterion("cretime between", value1, value2, "cretime");
            return (Criteria) this;
        }

        public Criteria andCretimeNotBetween(Date value1, Date value2) {
            addCriterion("cretime not between", value1, value2, "cretime");
            return (Criteria) this;
        }

        public Criteria andUpdtimeIsNull() {
            addCriterion("updtime is null");
            return (Criteria) this;
        }

        public Criteria andUpdtimeIsNotNull() {
            addCriterion("updtime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdtimeEqualTo(Date value) {
            addCriterion("updtime =", value, "updtime");
            return (Criteria) this;
        }

        public Criteria andUpdtimeNotEqualTo(Date value) {
            addCriterion("updtime <>", value, "updtime");
            return (Criteria) this;
        }

        public Criteria andUpdtimeGreaterThan(Date value) {
            addCriterion("updtime >", value, "updtime");
            return (Criteria) this;
        }

        public Criteria andUpdtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updtime >=", value, "updtime");
            return (Criteria) this;
        }

        public Criteria andUpdtimeLessThan(Date value) {
            addCriterion("updtime <", value, "updtime");
            return (Criteria) this;
        }

        public Criteria andUpdtimeLessThanOrEqualTo(Date value) {
            addCriterion("updtime <=", value, "updtime");
            return (Criteria) this;
        }

        public Criteria andUpdtimeIn(List<Date> values) {
            addCriterion("updtime in", values, "updtime");
            return (Criteria) this;
        }

        public Criteria andUpdtimeNotIn(List<Date> values) {
            addCriterion("updtime not in", values, "updtime");
            return (Criteria) this;
        }

        public Criteria andUpdtimeBetween(Date value1, Date value2) {
            addCriterion("updtime between", value1, value2, "updtime");
            return (Criteria) this;
        }

        public Criteria andUpdtimeNotBetween(Date value1, Date value2) {
            addCriterion("updtime not between", value1, value2, "updtime");
            return (Criteria) this;
        }

        public Criteria andWorkcountIsNull() {
            addCriterion("workcount is null");
            return (Criteria) this;
        }

        public Criteria andWorkcountIsNotNull() {
            addCriterion("workcount is not null");
            return (Criteria) this;
        }

        public Criteria andWorkcountEqualTo(Integer value) {
            addCriterion("workcount =", value, "workcount");
            return (Criteria) this;
        }

        public Criteria andWorkcountNotEqualTo(Integer value) {
            addCriterion("workcount <>", value, "workcount");
            return (Criteria) this;
        }

        public Criteria andWorkcountGreaterThan(Integer value) {
            addCriterion("workcount >", value, "workcount");
            return (Criteria) this;
        }

        public Criteria andWorkcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("workcount >=", value, "workcount");
            return (Criteria) this;
        }

        public Criteria andWorkcountLessThan(Integer value) {
            addCriterion("workcount <", value, "workcount");
            return (Criteria) this;
        }

        public Criteria andWorkcountLessThanOrEqualTo(Integer value) {
            addCriterion("workcount <=", value, "workcount");
            return (Criteria) this;
        }

        public Criteria andWorkcountIn(List<Integer> values) {
            addCriterion("workcount in", values, "workcount");
            return (Criteria) this;
        }

        public Criteria andWorkcountNotIn(List<Integer> values) {
            addCriterion("workcount not in", values, "workcount");
            return (Criteria) this;
        }

        public Criteria andWorkcountBetween(Integer value1, Integer value2) {
            addCriterion("workcount between", value1, value2, "workcount");
            return (Criteria) this;
        }

        public Criteria andWorkcountNotBetween(Integer value1, Integer value2) {
            addCriterion("workcount not between", value1, value2, "workcount");
            return (Criteria) this;
        }

        public Criteria andWorkstateIsNull() {
            addCriterion("workstate is null");
            return (Criteria) this;
        }

        public Criteria andWorkstateIsNotNull() {
            addCriterion("workstate is not null");
            return (Criteria) this;
        }

        public Criteria andWorkstateEqualTo(Integer value) {
            addCriterion("workstate =", value, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateNotEqualTo(Integer value) {
            addCriterion("workstate <>", value, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateGreaterThan(Integer value) {
            addCriterion("workstate >", value, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("workstate >=", value, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateLessThan(Integer value) {
            addCriterion("workstate <", value, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateLessThanOrEqualTo(Integer value) {
            addCriterion("workstate <=", value, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateIn(List<Integer> values) {
            addCriterion("workstate in", values, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateNotIn(List<Integer> values) {
            addCriterion("workstate not in", values, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateBetween(Integer value1, Integer value2) {
            addCriterion("workstate between", value1, value2, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateNotBetween(Integer value1, Integer value2) {
            addCriterion("workstate not between", value1, value2, "workstate");
            return (Criteria) this;
        }

        public Criteria andPlanidIsNull() {
            addCriterion("planid is null");
            return (Criteria) this;
        }

        public Criteria andPlanidIsNotNull() {
            addCriterion("planid is not null");
            return (Criteria) this;
        }

        public Criteria andPlanidEqualTo(Integer value) {
            addCriterion("planid =", value, "planid");
            return (Criteria) this;
        }

        public Criteria andPlanidNotEqualTo(Integer value) {
            addCriterion("planid <>", value, "planid");
            return (Criteria) this;
        }

        public Criteria andPlanidGreaterThan(Integer value) {
            addCriterion("planid >", value, "planid");
            return (Criteria) this;
        }

        public Criteria andPlanidGreaterThanOrEqualTo(Integer value) {
            addCriterion("planid >=", value, "planid");
            return (Criteria) this;
        }

        public Criteria andPlanidLessThan(Integer value) {
            addCriterion("planid <", value, "planid");
            return (Criteria) this;
        }

        public Criteria andPlanidLessThanOrEqualTo(Integer value) {
            addCriterion("planid <=", value, "planid");
            return (Criteria) this;
        }

        public Criteria andPlanidIn(List<Integer> values) {
            addCriterion("planid in", values, "planid");
            return (Criteria) this;
        }

        public Criteria andPlanidNotIn(List<Integer> values) {
            addCriterion("planid not in", values, "planid");
            return (Criteria) this;
        }

        public Criteria andPlanidBetween(Integer value1, Integer value2) {
            addCriterion("planid between", value1, value2, "planid");
            return (Criteria) this;
        }

        public Criteria andPlanidNotBetween(Integer value1, Integer value2) {
            addCriterion("planid not between", value1, value2, "planid");
            return (Criteria) this;
        }

        public Criteria andEqidIsNull() {
            addCriterion("eqid is null");
            return (Criteria) this;
        }

        public Criteria andEqidIsNotNull() {
            addCriterion("eqid is not null");
            return (Criteria) this;
        }

        public Criteria andEqidEqualTo(Integer value) {
            addCriterion("eqid =", value, "eqid");
            return (Criteria) this;
        }

        public Criteria andEqidNotEqualTo(Integer value) {
            addCriterion("eqid <>", value, "eqid");
            return (Criteria) this;
        }

        public Criteria andEqidGreaterThan(Integer value) {
            addCriterion("eqid >", value, "eqid");
            return (Criteria) this;
        }

        public Criteria andEqidGreaterThanOrEqualTo(Integer value) {
            addCriterion("eqid >=", value, "eqid");
            return (Criteria) this;
        }

        public Criteria andEqidLessThan(Integer value) {
            addCriterion("eqid <", value, "eqid");
            return (Criteria) this;
        }

        public Criteria andEqidLessThanOrEqualTo(Integer value) {
            addCriterion("eqid <=", value, "eqid");
            return (Criteria) this;
        }

        public Criteria andEqidIn(List<Integer> values) {
            addCriterion("eqid in", values, "eqid");
            return (Criteria) this;
        }

        public Criteria andEqidNotIn(List<Integer> values) {
            addCriterion("eqid not in", values, "eqid");
            return (Criteria) this;
        }

        public Criteria andEqidBetween(Integer value1, Integer value2) {
            addCriterion("eqid between", value1, value2, "eqid");
            return (Criteria) this;
        }

        public Criteria andEqidNotBetween(Integer value1, Integer value2) {
            addCriterion("eqid not between", value1, value2, "eqid");
            return (Criteria) this;
        }

        public Criteria andProidIsNull() {
            addCriterion("proid is null");
            return (Criteria) this;
        }

        public Criteria andProidIsNotNull() {
            addCriterion("proid is not null");
            return (Criteria) this;
        }

        public Criteria andProidEqualTo(Integer value) {
            addCriterion("proid =", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidNotEqualTo(Integer value) {
            addCriterion("proid <>", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidGreaterThan(Integer value) {
            addCriterion("proid >", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidGreaterThanOrEqualTo(Integer value) {
            addCriterion("proid >=", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidLessThan(Integer value) {
            addCriterion("proid <", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidLessThanOrEqualTo(Integer value) {
            addCriterion("proid <=", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidIn(List<Integer> values) {
            addCriterion("proid in", values, "proid");
            return (Criteria) this;
        }

        public Criteria andProidNotIn(List<Integer> values) {
            addCriterion("proid not in", values, "proid");
            return (Criteria) this;
        }

        public Criteria andProidBetween(Integer value1, Integer value2) {
            addCriterion("proid between", value1, value2, "proid");
            return (Criteria) this;
        }

        public Criteria andProidNotBetween(Integer value1, Integer value2) {
            addCriterion("proid not between", value1, value2, "proid");
            return (Criteria) this;
        }

        public Criteria andWorksttimeIsNull() {
            addCriterion("worksttime is null");
            return (Criteria) this;
        }

        public Criteria andWorksttimeIsNotNull() {
            addCriterion("worksttime is not null");
            return (Criteria) this;
        }

        public Criteria andWorksttimeEqualTo(Date value) {
            addCriterionForJDBCDate("worksttime =", value, "worksttime");
            return (Criteria) this;
        }

        public Criteria andWorksttimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("worksttime <>", value, "worksttime");
            return (Criteria) this;
        }

        public Criteria andWorksttimeGreaterThan(Date value) {
            addCriterionForJDBCDate("worksttime >", value, "worksttime");
            return (Criteria) this;
        }

        public Criteria andWorksttimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("worksttime >=", value, "worksttime");
            return (Criteria) this;
        }

        public Criteria andWorksttimeLessThan(Date value) {
            addCriterionForJDBCDate("worksttime <", value, "worksttime");
            return (Criteria) this;
        }

        public Criteria andWorksttimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("worksttime <=", value, "worksttime");
            return (Criteria) this;
        }

        public Criteria andWorksttimeIn(List<Date> values) {
            addCriterionForJDBCDate("worksttime in", values, "worksttime");
            return (Criteria) this;
        }

        public Criteria andWorksttimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("worksttime not in", values, "worksttime");
            return (Criteria) this;
        }

        public Criteria andWorksttimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("worksttime between", value1, value2, "worksttime");
            return (Criteria) this;
        }

        public Criteria andWorksttimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("worksttime not between", value1, value2, "worksttime");
            return (Criteria) this;
        }

        public Criteria andWorkentimeIsNull() {
            addCriterion("workentime is null");
            return (Criteria) this;
        }

        public Criteria andWorkentimeIsNotNull() {
            addCriterion("workentime is not null");
            return (Criteria) this;
        }

        public Criteria andWorkentimeEqualTo(Date value) {
            addCriterionForJDBCDate("workentime =", value, "workentime");
            return (Criteria) this;
        }

        public Criteria andWorkentimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("workentime <>", value, "workentime");
            return (Criteria) this;
        }

        public Criteria andWorkentimeGreaterThan(Date value) {
            addCriterionForJDBCDate("workentime >", value, "workentime");
            return (Criteria) this;
        }

        public Criteria andWorkentimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("workentime >=", value, "workentime");
            return (Criteria) this;
        }

        public Criteria andWorkentimeLessThan(Date value) {
            addCriterionForJDBCDate("workentime <", value, "workentime");
            return (Criteria) this;
        }

        public Criteria andWorkentimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("workentime <=", value, "workentime");
            return (Criteria) this;
        }

        public Criteria andWorkentimeIn(List<Date> values) {
            addCriterionForJDBCDate("workentime in", values, "workentime");
            return (Criteria) this;
        }

        public Criteria andWorkentimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("workentime not in", values, "workentime");
            return (Criteria) this;
        }

        public Criteria andWorkentimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("workentime between", value1, value2, "workentime");
            return (Criteria) this;
        }

        public Criteria andWorkentimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("workentime not between", value1, value2, "workentime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table workinfo
     *
     * @mbg.generated do_not_delete_during_merge Thu Jul 23 09:40:58 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}