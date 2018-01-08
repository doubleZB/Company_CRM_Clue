package com.sunll.center.entity;

import java.util.ArrayList;
import java.util.List;

public class PredialtaskCustomerExample {
    /**   tableName: predialtask_customer   **/
    protected String orderByClause;

    /**   tableName: predialtask_customer   **/
    protected boolean distinct;

    /**   tableName: predialtask_customer   **/
    protected List<Criteria> oredCriteria;

    public PredialtaskCustomerExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /** predialtask_customer **/
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

        public Criteria andBindTaskIdIsNull() {
            addCriterion("bind_task_id is null");
            return (Criteria) this;
        }

        public Criteria andBindTaskIdIsNotNull() {
            addCriterion("bind_task_id is not null");
            return (Criteria) this;
        }

        public Criteria andBindTaskIdEqualTo(Integer value) {
            addCriterion("bind_task_id =", value, "bindTaskId");
            return (Criteria) this;
        }

        public Criteria andBindTaskIdNotEqualTo(Integer value) {
            addCriterion("bind_task_id <>", value, "bindTaskId");
            return (Criteria) this;
        }

        public Criteria andBindTaskIdGreaterThan(Integer value) {
            addCriterion("bind_task_id >", value, "bindTaskId");
            return (Criteria) this;
        }

        public Criteria andBindTaskIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("bind_task_id >=", value, "bindTaskId");
            return (Criteria) this;
        }

        public Criteria andBindTaskIdLessThan(Integer value) {
            addCriterion("bind_task_id <", value, "bindTaskId");
            return (Criteria) this;
        }

        public Criteria andBindTaskIdLessThanOrEqualTo(Integer value) {
            addCriterion("bind_task_id <=", value, "bindTaskId");
            return (Criteria) this;
        }

        public Criteria andBindTaskIdIn(List<Integer> values) {
            addCriterion("bind_task_id in", values, "bindTaskId");
            return (Criteria) this;
        }

        public Criteria andBindTaskIdNotIn(List<Integer> values) {
            addCriterion("bind_task_id not in", values, "bindTaskId");
            return (Criteria) this;
        }

        public Criteria andBindTaskIdBetween(Integer value1, Integer value2) {
            addCriterion("bind_task_id between", value1, value2, "bindTaskId");
            return (Criteria) this;
        }

        public Criteria andBindTaskIdNotBetween(Integer value1, Integer value2) {
            addCriterion("bind_task_id not between", value1, value2, "bindTaskId");
            return (Criteria) this;
        }

        public Criteria andCustomCallerIsNull() {
            addCriterion("custom_caller is null");
            return (Criteria) this;
        }

        public Criteria andCustomCallerIsNotNull() {
            addCriterion("custom_caller is not null");
            return (Criteria) this;
        }

        public Criteria andCustomCallerEqualTo(String value) {
            addCriterion("custom_caller =", value, "customCaller");
            return (Criteria) this;
        }

        public Criteria andCustomCallerNotEqualTo(String value) {
            addCriterion("custom_caller <>", value, "customCaller");
            return (Criteria) this;
        }

        public Criteria andCustomCallerGreaterThan(String value) {
            addCriterion("custom_caller >", value, "customCaller");
            return (Criteria) this;
        }

        public Criteria andCustomCallerGreaterThanOrEqualTo(String value) {
            addCriterion("custom_caller >=", value, "customCaller");
            return (Criteria) this;
        }

        public Criteria andCustomCallerLessThan(String value) {
            addCriterion("custom_caller <", value, "customCaller");
            return (Criteria) this;
        }

        public Criteria andCustomCallerLessThanOrEqualTo(String value) {
            addCriterion("custom_caller <=", value, "customCaller");
            return (Criteria) this;
        }

        public Criteria andCustomCallerLike(String value) {
            addCriterion("custom_caller like", value, "customCaller");
            return (Criteria) this;
        }

        public Criteria andCustomCallerNotLike(String value) {
            addCriterion("custom_caller not like", value, "customCaller");
            return (Criteria) this;
        }

        public Criteria andCustomCallerIn(List<String> values) {
            addCriterion("custom_caller in", values, "customCaller");
            return (Criteria) this;
        }

        public Criteria andCustomCallerNotIn(List<String> values) {
            addCriterion("custom_caller not in", values, "customCaller");
            return (Criteria) this;
        }

        public Criteria andCustomCallerBetween(String value1, String value2) {
            addCriterion("custom_caller between", value1, value2, "customCaller");
            return (Criteria) this;
        }

        public Criteria andCustomCallerNotBetween(String value1, String value2) {
            addCriterion("custom_caller not between", value1, value2, "customCaller");
            return (Criteria) this;
        }
    }

    /**  tableName: predialtask_customer   **/
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /** predialtask_customer **/
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