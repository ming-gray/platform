package com.platformmake.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrderinfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table orderinfo
     *
     * @mbg.generated Fri Jul 24 19:21:12 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table orderinfo
     *
     * @mbg.generated Fri Jul 24 19:21:12 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table orderinfo
     *
     * @mbg.generated Fri Jul 24 19:21:12 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbg.generated Fri Jul 24 19:21:12 CST 2020
     */
    public OrderinfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbg.generated Fri Jul 24 19:21:12 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbg.generated Fri Jul 24 19:21:12 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbg.generated Fri Jul 24 19:21:12 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbg.generated Fri Jul 24 19:21:12 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbg.generated Fri Jul 24 19:21:12 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbg.generated Fri Jul 24 19:21:12 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbg.generated Fri Jul 24 19:21:12 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbg.generated Fri Jul 24 19:21:12 CST 2020
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
     * This method corresponds to the database table orderinfo
     *
     * @mbg.generated Fri Jul 24 19:21:12 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbg.generated Fri Jul 24 19:21:12 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table orderinfo
     *
     * @mbg.generated Fri Jul 24 19:21:12 CST 2020
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

        public Criteria andOrdidIsNull() {
            addCriterion("ordid is null");
            return (Criteria) this;
        }

        public Criteria andOrdidIsNotNull() {
            addCriterion("ordid is not null");
            return (Criteria) this;
        }

        public Criteria andOrdidEqualTo(Integer value) {
            addCriterion("ordid =", value, "ordid");
            return (Criteria) this;
        }

        public Criteria andOrdidNotEqualTo(Integer value) {
            addCriterion("ordid <>", value, "ordid");
            return (Criteria) this;
        }

        public Criteria andOrdidGreaterThan(Integer value) {
            addCriterion("ordid >", value, "ordid");
            return (Criteria) this;
        }

        public Criteria andOrdidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ordid >=", value, "ordid");
            return (Criteria) this;
        }

        public Criteria andOrdidLessThan(Integer value) {
            addCriterion("ordid <", value, "ordid");
            return (Criteria) this;
        }

        public Criteria andOrdidLessThanOrEqualTo(Integer value) {
            addCriterion("ordid <=", value, "ordid");
            return (Criteria) this;
        }

        public Criteria andOrdidIn(List<Integer> values) {
            addCriterion("ordid in", values, "ordid");
            return (Criteria) this;
        }

        public Criteria andOrdidNotIn(List<Integer> values) {
            addCriterion("ordid not in", values, "ordid");
            return (Criteria) this;
        }

        public Criteria andOrdidBetween(Integer value1, Integer value2) {
            addCriterion("ordid between", value1, value2, "ordid");
            return (Criteria) this;
        }

        public Criteria andOrdidNotBetween(Integer value1, Integer value2) {
            addCriterion("ordid not between", value1, value2, "ordid");
            return (Criteria) this;
        }

        public Criteria andOrdsourceIsNull() {
            addCriterion("ordsource is null");
            return (Criteria) this;
        }

        public Criteria andOrdsourceIsNotNull() {
            addCriterion("ordsource is not null");
            return (Criteria) this;
        }

        public Criteria andOrdsourceEqualTo(String value) {
            addCriterion("ordsource =", value, "ordsource");
            return (Criteria) this;
        }

        public Criteria andOrdsourceNotEqualTo(String value) {
            addCriterion("ordsource <>", value, "ordsource");
            return (Criteria) this;
        }

        public Criteria andOrdsourceGreaterThan(String value) {
            addCriterion("ordsource >", value, "ordsource");
            return (Criteria) this;
        }

        public Criteria andOrdsourceGreaterThanOrEqualTo(String value) {
            addCriterion("ordsource >=", value, "ordsource");
            return (Criteria) this;
        }

        public Criteria andOrdsourceLessThan(String value) {
            addCriterion("ordsource <", value, "ordsource");
            return (Criteria) this;
        }

        public Criteria andOrdsourceLessThanOrEqualTo(String value) {
            addCriterion("ordsource <=", value, "ordsource");
            return (Criteria) this;
        }

        public Criteria andOrdsourceLike(String value) {
            addCriterion("ordsource like", value, "ordsource");
            return (Criteria) this;
        }

        public Criteria andOrdsourceNotLike(String value) {
            addCriterion("ordsource not like", value, "ordsource");
            return (Criteria) this;
        }

        public Criteria andOrdsourceIn(List<String> values) {
            addCriterion("ordsource in", values, "ordsource");
            return (Criteria) this;
        }

        public Criteria andOrdsourceNotIn(List<String> values) {
            addCriterion("ordsource not in", values, "ordsource");
            return (Criteria) this;
        }

        public Criteria andOrdsourceBetween(String value1, String value2) {
            addCriterion("ordsource between", value1, value2, "ordsource");
            return (Criteria) this;
        }

        public Criteria andOrdsourceNotBetween(String value1, String value2) {
            addCriterion("ordsource not between", value1, value2, "ordsource");
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

        public Criteria andProordnumIsNull() {
            addCriterion("proordnum is null");
            return (Criteria) this;
        }

        public Criteria andProordnumIsNotNull() {
            addCriterion("proordnum is not null");
            return (Criteria) this;
        }

        public Criteria andProordnumEqualTo(Integer value) {
            addCriterion("proordnum =", value, "proordnum");
            return (Criteria) this;
        }

        public Criteria andProordnumNotEqualTo(Integer value) {
            addCriterion("proordnum <>", value, "proordnum");
            return (Criteria) this;
        }

        public Criteria andProordnumGreaterThan(Integer value) {
            addCriterion("proordnum >", value, "proordnum");
            return (Criteria) this;
        }

        public Criteria andProordnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("proordnum >=", value, "proordnum");
            return (Criteria) this;
        }

        public Criteria andProordnumLessThan(Integer value) {
            addCriterion("proordnum <", value, "proordnum");
            return (Criteria) this;
        }

        public Criteria andProordnumLessThanOrEqualTo(Integer value) {
            addCriterion("proordnum <=", value, "proordnum");
            return (Criteria) this;
        }

        public Criteria andProordnumIn(List<Integer> values) {
            addCriterion("proordnum in", values, "proordnum");
            return (Criteria) this;
        }

        public Criteria andProordnumNotIn(List<Integer> values) {
            addCriterion("proordnum not in", values, "proordnum");
            return (Criteria) this;
        }

        public Criteria andProordnumBetween(Integer value1, Integer value2) {
            addCriterion("proordnum between", value1, value2, "proordnum");
            return (Criteria) this;
        }

        public Criteria andProordnumNotBetween(Integer value1, Integer value2) {
            addCriterion("proordnum not between", value1, value2, "proordnum");
            return (Criteria) this;
        }

        public Criteria andOrddlIsNull() {
            addCriterion("orddl is null");
            return (Criteria) this;
        }

        public Criteria andOrddlIsNotNull() {
            addCriterion("orddl is not null");
            return (Criteria) this;
        }

        public Criteria andOrddlEqualTo(Date value) {
            addCriterionForJDBCDate("orddl =", value, "orddl");
            return (Criteria) this;
        }

        public Criteria andOrddlNotEqualTo(Date value) {
            addCriterionForJDBCDate("orddl <>", value, "orddl");
            return (Criteria) this;
        }

        public Criteria andOrddlGreaterThan(Date value) {
            addCriterionForJDBCDate("orddl >", value, "orddl");
            return (Criteria) this;
        }

        public Criteria andOrddlGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("orddl >=", value, "orddl");
            return (Criteria) this;
        }

        public Criteria andOrddlLessThan(Date value) {
            addCriterionForJDBCDate("orddl <", value, "orddl");
            return (Criteria) this;
        }

        public Criteria andOrddlLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("orddl <=", value, "orddl");
            return (Criteria) this;
        }

        public Criteria andOrddlIn(List<Date> values) {
            addCriterionForJDBCDate("orddl in", values, "orddl");
            return (Criteria) this;
        }

        public Criteria andOrddlNotIn(List<Date> values) {
            addCriterionForJDBCDate("orddl not in", values, "orddl");
            return (Criteria) this;
        }

        public Criteria andOrddlBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("orddl between", value1, value2, "orddl");
            return (Criteria) this;
        }

        public Criteria andOrddlNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("orddl not between", value1, value2, "orddl");
            return (Criteria) this;
        }

        public Criteria andOrdstateIsNull() {
            addCriterion("ordstate is null");
            return (Criteria) this;
        }

        public Criteria andOrdstateIsNotNull() {
            addCriterion("ordstate is not null");
            return (Criteria) this;
        }

        public Criteria andOrdstateEqualTo(Integer value) {
            addCriterion("ordstate =", value, "ordstate");
            return (Criteria) this;
        }

        public Criteria andOrdstateNotEqualTo(Integer value) {
            addCriterion("ordstate <>", value, "ordstate");
            return (Criteria) this;
        }

        public Criteria andOrdstateGreaterThan(Integer value) {
            addCriterion("ordstate >", value, "ordstate");
            return (Criteria) this;
        }

        public Criteria andOrdstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("ordstate >=", value, "ordstate");
            return (Criteria) this;
        }

        public Criteria andOrdstateLessThan(Integer value) {
            addCriterion("ordstate <", value, "ordstate");
            return (Criteria) this;
        }

        public Criteria andOrdstateLessThanOrEqualTo(Integer value) {
            addCriterion("ordstate <=", value, "ordstate");
            return (Criteria) this;
        }

        public Criteria andOrdstateIn(List<Integer> values) {
            addCriterion("ordstate in", values, "ordstate");
            return (Criteria) this;
        }

        public Criteria andOrdstateNotIn(List<Integer> values) {
            addCriterion("ordstate not in", values, "ordstate");
            return (Criteria) this;
        }

        public Criteria andOrdstateBetween(Integer value1, Integer value2) {
            addCriterion("ordstate between", value1, value2, "ordstate");
            return (Criteria) this;
        }

        public Criteria andOrdstateNotBetween(Integer value1, Integer value2) {
            addCriterion("ordstate not between", value1, value2, "ordstate");
            return (Criteria) this;
        }

        public Criteria andQuacomIsNull() {
            addCriterion("quacom is null");
            return (Criteria) this;
        }

        public Criteria andQuacomIsNotNull() {
            addCriterion("quacom is not null");
            return (Criteria) this;
        }

        public Criteria andQuacomEqualTo(Integer value) {
            addCriterion("quacom =", value, "quacom");
            return (Criteria) this;
        }

        public Criteria andQuacomNotEqualTo(Integer value) {
            addCriterion("quacom <>", value, "quacom");
            return (Criteria) this;
        }

        public Criteria andQuacomGreaterThan(Integer value) {
            addCriterion("quacom >", value, "quacom");
            return (Criteria) this;
        }

        public Criteria andQuacomGreaterThanOrEqualTo(Integer value) {
            addCriterion("quacom >=", value, "quacom");
            return (Criteria) this;
        }

        public Criteria andQuacomLessThan(Integer value) {
            addCriterion("quacom <", value, "quacom");
            return (Criteria) this;
        }

        public Criteria andQuacomLessThanOrEqualTo(Integer value) {
            addCriterion("quacom <=", value, "quacom");
            return (Criteria) this;
        }

        public Criteria andQuacomIn(List<Integer> values) {
            addCriterion("quacom in", values, "quacom");
            return (Criteria) this;
        }

        public Criteria andQuacomNotIn(List<Integer> values) {
            addCriterion("quacom not in", values, "quacom");
            return (Criteria) this;
        }

        public Criteria andQuacomBetween(Integer value1, Integer value2) {
            addCriterion("quacom between", value1, value2, "quacom");
            return (Criteria) this;
        }

        public Criteria andQuacomNotBetween(Integer value1, Integer value2) {
            addCriterion("quacom not between", value1, value2, "quacom");
            return (Criteria) this;
        }

        public Criteria andApcIsNull() {
            addCriterion("apc is null");
            return (Criteria) this;
        }

        public Criteria andApcIsNotNull() {
            addCriterion("apc is not null");
            return (Criteria) this;
        }

        public Criteria andApcEqualTo(Integer value) {
            addCriterion("apc =", value, "apc");
            return (Criteria) this;
        }

        public Criteria andApcNotEqualTo(Integer value) {
            addCriterion("apc <>", value, "apc");
            return (Criteria) this;
        }

        public Criteria andApcGreaterThan(Integer value) {
            addCriterion("apc >", value, "apc");
            return (Criteria) this;
        }

        public Criteria andApcGreaterThanOrEqualTo(Integer value) {
            addCriterion("apc >=", value, "apc");
            return (Criteria) this;
        }

        public Criteria andApcLessThan(Integer value) {
            addCriterion("apc <", value, "apc");
            return (Criteria) this;
        }

        public Criteria andApcLessThanOrEqualTo(Integer value) {
            addCriterion("apc <=", value, "apc");
            return (Criteria) this;
        }

        public Criteria andApcIn(List<Integer> values) {
            addCriterion("apc in", values, "apc");
            return (Criteria) this;
        }

        public Criteria andApcNotIn(List<Integer> values) {
            addCriterion("apc not in", values, "apc");
            return (Criteria) this;
        }

        public Criteria andApcBetween(Integer value1, Integer value2) {
            addCriterion("apc between", value1, value2, "apc");
            return (Criteria) this;
        }

        public Criteria andApcNotBetween(Integer value1, Integer value2) {
            addCriterion("apc not between", value1, value2, "apc");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table orderinfo
     *
     * @mbg.generated do_not_delete_during_merge Fri Jul 24 19:21:12 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table orderinfo
     *
     * @mbg.generated Fri Jul 24 19:21:12 CST 2020
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