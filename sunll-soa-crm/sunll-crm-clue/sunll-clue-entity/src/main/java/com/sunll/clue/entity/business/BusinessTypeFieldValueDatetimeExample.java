package com.sunll.clue.entity.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessTypeFieldValueDatetimeExample {
    /**   tableName: business_type_field_value_datetime   **/
    protected String orderByClause;

    /**   tableName: business_type_field_value_datetime   **/
    protected boolean distinct;

    /**   tableName: business_type_field_value_datetime   **/
    protected List<Criteria> oredCriteria;

    public BusinessTypeFieldValueDatetimeExample() {
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

    /** business_type_field_value_datetime **/
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

        public Criteria andBusinessTypeIdIsNull() {
            addCriterion("business_type_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdIsNotNull() {
            addCriterion("business_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdEqualTo(Integer value) {
            addCriterion("business_type_id =", value, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdNotEqualTo(Integer value) {
            addCriterion("business_type_id <>", value, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdGreaterThan(Integer value) {
            addCriterion("business_type_id >", value, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("business_type_id >=", value, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdLessThan(Integer value) {
            addCriterion("business_type_id <", value, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("business_type_id <=", value, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdIn(List<Integer> values) {
            addCriterion("business_type_id in", values, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdNotIn(List<Integer> values) {
            addCriterion("business_type_id not in", values, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("business_type_id between", value1, value2, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("business_type_id not between", value1, value2, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeFieldIdIsNull() {
            addCriterion("business_type_field_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeFieldIdIsNotNull() {
            addCriterion("business_type_field_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeFieldIdEqualTo(Integer value) {
            addCriterion("business_type_field_id =", value, "businessTypeFieldId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeFieldIdNotEqualTo(Integer value) {
            addCriterion("business_type_field_id <>", value, "businessTypeFieldId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeFieldIdGreaterThan(Integer value) {
            addCriterion("business_type_field_id >", value, "businessTypeFieldId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeFieldIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("business_type_field_id >=", value, "businessTypeFieldId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeFieldIdLessThan(Integer value) {
            addCriterion("business_type_field_id <", value, "businessTypeFieldId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeFieldIdLessThanOrEqualTo(Integer value) {
            addCriterion("business_type_field_id <=", value, "businessTypeFieldId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeFieldIdIn(List<Integer> values) {
            addCriterion("business_type_field_id in", values, "businessTypeFieldId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeFieldIdNotIn(List<Integer> values) {
            addCriterion("business_type_field_id not in", values, "businessTypeFieldId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeFieldIdBetween(Integer value1, Integer value2) {
            addCriterion("business_type_field_id between", value1, value2, "businessTypeFieldId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeFieldIdNotBetween(Integer value1, Integer value2) {
            addCriterion("business_type_field_id not between", value1, value2, "businessTypeFieldId");
            return (Criteria) this;
        }

        public Criteria andValueIsNull() {
            addCriterion("value is null");
            return (Criteria) this;
        }

        public Criteria andValueIsNotNull() {
            addCriterion("value is not null");
            return (Criteria) this;
        }

        public Criteria andValueEqualTo(Date value) {
            addCriterion("value =", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotEqualTo(Date value) {
            addCriterion("value <>", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThan(Date value) {
            addCriterion("value >", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThanOrEqualTo(Date value) {
            addCriterion("value >=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThan(Date value) {
            addCriterion("value <", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThanOrEqualTo(Date value) {
            addCriterion("value <=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueIn(List<Date> values) {
            addCriterion("value in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotIn(List<Date> values) {
            addCriterion("value not in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueBetween(Date value1, Date value2) {
            addCriterion("value between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotBetween(Date value1, Date value2) {
            addCriterion("value not between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andIdentifyIdIsNull() {
            addCriterion("identify_id is null");
            return (Criteria) this;
        }

        public Criteria andIdentifyIdIsNotNull() {
            addCriterion("identify_id is not null");
            return (Criteria) this;
        }

        public Criteria andIdentifyIdEqualTo(String value) {
            addCriterion("identify_id =", value, "identifyId");
            return (Criteria) this;
        }

        public Criteria andIdentifyIdNotEqualTo(String value) {
            addCriterion("identify_id <>", value, "identifyId");
            return (Criteria) this;
        }

        public Criteria andIdentifyIdGreaterThan(String value) {
            addCriterion("identify_id >", value, "identifyId");
            return (Criteria) this;
        }

        public Criteria andIdentifyIdGreaterThanOrEqualTo(String value) {
            addCriterion("identify_id >=", value, "identifyId");
            return (Criteria) this;
        }

        public Criteria andIdentifyIdLessThan(String value) {
            addCriterion("identify_id <", value, "identifyId");
            return (Criteria) this;
        }

        public Criteria andIdentifyIdLessThanOrEqualTo(String value) {
            addCriterion("identify_id <=", value, "identifyId");
            return (Criteria) this;
        }

        public Criteria andIdentifyIdLike(String value) {
            addCriterion("identify_id like", value, "identifyId");
            return (Criteria) this;
        }

        public Criteria andIdentifyIdNotLike(String value) {
            addCriterion("identify_id not like", value, "identifyId");
            return (Criteria) this;
        }

        public Criteria andIdentifyIdIn(List<String> values) {
            addCriterion("identify_id in", values, "identifyId");
            return (Criteria) this;
        }

        public Criteria andIdentifyIdNotIn(List<String> values) {
            addCriterion("identify_id not in", values, "identifyId");
            return (Criteria) this;
        }

        public Criteria andIdentifyIdBetween(String value1, String value2) {
            addCriterion("identify_id between", value1, value2, "identifyId");
            return (Criteria) this;
        }

        public Criteria andIdentifyIdNotBetween(String value1, String value2) {
            addCriterion("identify_id not between", value1, value2, "identifyId");
            return (Criteria) this;
        }
    }

    /**  tableName: business_type_field_value_datetime   **/
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /** business_type_field_value_datetime **/
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