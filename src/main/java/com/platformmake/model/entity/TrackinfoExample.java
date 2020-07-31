package com.platformmake.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TrackinfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table trackinfo
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table trackinfo
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table trackinfo
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trackinfo
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public TrackinfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trackinfo
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trackinfo
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trackinfo
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trackinfo
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trackinfo
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trackinfo
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trackinfo
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trackinfo
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
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
     * This method corresponds to the database table trackinfo
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trackinfo
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table trackinfo
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
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

        public Criteria andTrackstateIsNull() {
            addCriterion("trackstate is null");
            return (Criteria) this;
        }

        public Criteria andTrackstateIsNotNull() {
            addCriterion("trackstate is not null");
            return (Criteria) this;
        }

        public Criteria andTrackstateEqualTo(Integer value) {
            addCriterion("trackstate =", value, "trackstate");
            return (Criteria) this;
        }

        public Criteria andTrackstateNotEqualTo(Integer value) {
            addCriterion("trackstate <>", value, "trackstate");
            return (Criteria) this;
        }

        public Criteria andTrackstateGreaterThan(Integer value) {
            addCriterion("trackstate >", value, "trackstate");
            return (Criteria) this;
        }

        public Criteria andTrackstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("trackstate >=", value, "trackstate");
            return (Criteria) this;
        }

        public Criteria andTrackstateLessThan(Integer value) {
            addCriterion("trackstate <", value, "trackstate");
            return (Criteria) this;
        }

        public Criteria andTrackstateLessThanOrEqualTo(Integer value) {
            addCriterion("trackstate <=", value, "trackstate");
            return (Criteria) this;
        }

        public Criteria andTrackstateIn(List<Integer> values) {
            addCriterion("trackstate in", values, "trackstate");
            return (Criteria) this;
        }

        public Criteria andTrackstateNotIn(List<Integer> values) {
            addCriterion("trackstate not in", values, "trackstate");
            return (Criteria) this;
        }

        public Criteria andTrackstateBetween(Integer value1, Integer value2) {
            addCriterion("trackstate between", value1, value2, "trackstate");
            return (Criteria) this;
        }

        public Criteria andTrackstateNotBetween(Integer value1, Integer value2) {
            addCriterion("trackstate not between", value1, value2, "trackstate");
            return (Criteria) this;
        }

        public Criteria andWorkingCountIsNull() {
            addCriterion("working_count is null");
            return (Criteria) this;
        }

        public Criteria andWorkingCountIsNotNull() {
            addCriterion("working_count is not null");
            return (Criteria) this;
        }

        public Criteria andWorkingCountEqualTo(Integer value) {
            addCriterion("working_count =", value, "workingCount");
            return (Criteria) this;
        }

        public Criteria andWorkingCountNotEqualTo(Integer value) {
            addCriterion("working_count <>", value, "workingCount");
            return (Criteria) this;
        }

        public Criteria andWorkingCountGreaterThan(Integer value) {
            addCriterion("working_count >", value, "workingCount");
            return (Criteria) this;
        }

        public Criteria andWorkingCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("working_count >=", value, "workingCount");
            return (Criteria) this;
        }

        public Criteria andWorkingCountLessThan(Integer value) {
            addCriterion("working_count <", value, "workingCount");
            return (Criteria) this;
        }

        public Criteria andWorkingCountLessThanOrEqualTo(Integer value) {
            addCriterion("working_count <=", value, "workingCount");
            return (Criteria) this;
        }

        public Criteria andWorkingCountIn(List<Integer> values) {
            addCriterion("working_count in", values, "workingCount");
            return (Criteria) this;
        }

        public Criteria andWorkingCountNotIn(List<Integer> values) {
            addCriterion("working_count not in", values, "workingCount");
            return (Criteria) this;
        }

        public Criteria andWorkingCountBetween(Integer value1, Integer value2) {
            addCriterion("working_count between", value1, value2, "workingCount");
            return (Criteria) this;
        }

        public Criteria andWorkingCountNotBetween(Integer value1, Integer value2) {
            addCriterion("working_count not between", value1, value2, "workingCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountIsNull() {
            addCriterion("qualified_count is null");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountIsNotNull() {
            addCriterion("qualified_count is not null");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountEqualTo(Integer value) {
            addCriterion("qualified_count =", value, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountNotEqualTo(Integer value) {
            addCriterion("qualified_count <>", value, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountGreaterThan(Integer value) {
            addCriterion("qualified_count >", value, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("qualified_count >=", value, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountLessThan(Integer value) {
            addCriterion("qualified_count <", value, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountLessThanOrEqualTo(Integer value) {
            addCriterion("qualified_count <=", value, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountIn(List<Integer> values) {
            addCriterion("qualified_count in", values, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountNotIn(List<Integer> values) {
            addCriterion("qualified_count not in", values, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountBetween(Integer value1, Integer value2) {
            addCriterion("qualified_count between", value1, value2, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountNotBetween(Integer value1, Integer value2) {
            addCriterion("qualified_count not between", value1, value2, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andCratetimeIsNull() {
            addCriterion("cratetime is null");
            return (Criteria) this;
        }

        public Criteria andCratetimeIsNotNull() {
            addCriterion("cratetime is not null");
            return (Criteria) this;
        }

        public Criteria andCratetimeEqualTo(Date value) {
            addCriterionForJDBCDate("cratetime =", value, "cratetime");
            return (Criteria) this;
        }

        public Criteria andCratetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("cratetime <>", value, "cratetime");
            return (Criteria) this;
        }

        public Criteria andCratetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("cratetime >", value, "cratetime");
            return (Criteria) this;
        }

        public Criteria andCratetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("cratetime >=", value, "cratetime");
            return (Criteria) this;
        }

        public Criteria andCratetimeLessThan(Date value) {
            addCriterionForJDBCDate("cratetime <", value, "cratetime");
            return (Criteria) this;
        }

        public Criteria andCratetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("cratetime <=", value, "cratetime");
            return (Criteria) this;
        }

        public Criteria andCratetimeIn(List<Date> values) {
            addCriterionForJDBCDate("cratetime in", values, "cratetime");
            return (Criteria) this;
        }

        public Criteria andCratetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("cratetime not in", values, "cratetime");
            return (Criteria) this;
        }

        public Criteria andCratetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("cratetime between", value1, value2, "cratetime");
            return (Criteria) this;
        }

        public Criteria andCratetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("cratetime not between", value1, value2, "cratetime");
            return (Criteria) this;
        }

        public Criteria andUptimeIsNull() {
            addCriterion("uptime is null");
            return (Criteria) this;
        }

        public Criteria andUptimeIsNotNull() {
            addCriterion("uptime is not null");
            return (Criteria) this;
        }

        public Criteria andUptimeEqualTo(Date value) {
            addCriterionForJDBCDate("uptime =", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("uptime <>", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeGreaterThan(Date value) {
            addCriterionForJDBCDate("uptime >", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("uptime >=", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeLessThan(Date value) {
            addCriterionForJDBCDate("uptime <", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("uptime <=", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeIn(List<Date> values) {
            addCriterionForJDBCDate("uptime in", values, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("uptime not in", values, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("uptime between", value1, value2, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("uptime not between", value1, value2, "uptime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table trackinfo
     *
     * @mbg.generated do_not_delete_during_merge Sun Jul 26 23:40:19 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table trackinfo
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
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