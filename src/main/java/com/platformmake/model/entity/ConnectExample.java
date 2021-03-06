package com.platformmake.model.entity;

import java.util.ArrayList;
import java.util.List;

public class ConnectExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table connect
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table connect
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table connect
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table connect
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public ConnectExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table connect
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table connect
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table connect
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table connect
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table connect
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table connect
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table connect
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
     * This method corresponds to the database table connect
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
     * This method corresponds to the database table connect
     *
     * @mbg.generated Sun Jul 26 23:40:19 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table connect
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
     * This class corresponds to the database table connect
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

        public Criteria andYieldIsNull() {
            addCriterion("yield is null");
            return (Criteria) this;
        }

        public Criteria andYieldIsNotNull() {
            addCriterion("yield is not null");
            return (Criteria) this;
        }

        public Criteria andYieldEqualTo(Integer value) {
            addCriterion("yield =", value, "yield");
            return (Criteria) this;
        }

        public Criteria andYieldNotEqualTo(Integer value) {
            addCriterion("yield <>", value, "yield");
            return (Criteria) this;
        }

        public Criteria andYieldGreaterThan(Integer value) {
            addCriterion("yield >", value, "yield");
            return (Criteria) this;
        }

        public Criteria andYieldGreaterThanOrEqualTo(Integer value) {
            addCriterion("yield >=", value, "yield");
            return (Criteria) this;
        }

        public Criteria andYieldLessThan(Integer value) {
            addCriterion("yield <", value, "yield");
            return (Criteria) this;
        }

        public Criteria andYieldLessThanOrEqualTo(Integer value) {
            addCriterion("yield <=", value, "yield");
            return (Criteria) this;
        }

        public Criteria andYieldIn(List<Integer> values) {
            addCriterion("yield in", values, "yield");
            return (Criteria) this;
        }

        public Criteria andYieldNotIn(List<Integer> values) {
            addCriterion("yield not in", values, "yield");
            return (Criteria) this;
        }

        public Criteria andYieldBetween(Integer value1, Integer value2) {
            addCriterion("yield between", value1, value2, "yield");
            return (Criteria) this;
        }

        public Criteria andYieldNotBetween(Integer value1, Integer value2) {
            addCriterion("yield not between", value1, value2, "yield");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table connect
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
     * This class corresponds to the database table connect
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