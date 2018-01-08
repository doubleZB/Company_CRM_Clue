package com.sunll.quartz.entity.sms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmsExample {
    /**   tableName: sms   **/
    protected String orderByClause;

    /**   tableName: sms   **/
    protected boolean distinct;

    /**   tableName: sms   **/
    protected List<Criteria> oredCriteria;

    public SmsExample() {
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

    /** sms **/
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

        public Criteria andSmsTextIsNull() {
            addCriterion("sms_text is null");
            return (Criteria) this;
        }

        public Criteria andSmsTextIsNotNull() {
            addCriterion("sms_text is not null");
            return (Criteria) this;
        }

        public Criteria andSmsTextEqualTo(String value) {
            addCriterion("sms_text =", value, "smsText");
            return (Criteria) this;
        }

        public Criteria andSmsTextNotEqualTo(String value) {
            addCriterion("sms_text <>", value, "smsText");
            return (Criteria) this;
        }

        public Criteria andSmsTextGreaterThan(String value) {
            addCriterion("sms_text >", value, "smsText");
            return (Criteria) this;
        }

        public Criteria andSmsTextGreaterThanOrEqualTo(String value) {
            addCriterion("sms_text >=", value, "smsText");
            return (Criteria) this;
        }

        public Criteria andSmsTextLessThan(String value) {
            addCriterion("sms_text <", value, "smsText");
            return (Criteria) this;
        }

        public Criteria andSmsTextLessThanOrEqualTo(String value) {
            addCriterion("sms_text <=", value, "smsText");
            return (Criteria) this;
        }

        public Criteria andSmsTextLike(String value) {
            addCriterion("sms_text like", value, "smsText");
            return (Criteria) this;
        }

        public Criteria andSmsTextNotLike(String value) {
            addCriterion("sms_text not like", value, "smsText");
            return (Criteria) this;
        }

        public Criteria andSmsTextIn(List<String> values) {
            addCriterion("sms_text in", values, "smsText");
            return (Criteria) this;
        }

        public Criteria andSmsTextNotIn(List<String> values) {
            addCriterion("sms_text not in", values, "smsText");
            return (Criteria) this;
        }

        public Criteria andSmsTextBetween(String value1, String value2) {
            addCriterion("sms_text between", value1, value2, "smsText");
            return (Criteria) this;
        }

        public Criteria andSmsTextNotBetween(String value1, String value2) {
            addCriterion("sms_text not between", value1, value2, "smsText");
            return (Criteria) this;
        }

        public Criteria andSmsDateIsNull() {
            addCriterion("sms_date is null");
            return (Criteria) this;
        }

        public Criteria andSmsDateIsNotNull() {
            addCriterion("sms_date is not null");
            return (Criteria) this;
        }

        public Criteria andSmsDateEqualTo(Date value) {
            addCriterion("sms_date =", value, "smsDate");
            return (Criteria) this;
        }

        public Criteria andSmsDateNotEqualTo(Date value) {
            addCriterion("sms_date <>", value, "smsDate");
            return (Criteria) this;
        }

        public Criteria andSmsDateGreaterThan(Date value) {
            addCriterion("sms_date >", value, "smsDate");
            return (Criteria) this;
        }

        public Criteria andSmsDateGreaterThanOrEqualTo(Date value) {
            addCriterion("sms_date >=", value, "smsDate");
            return (Criteria) this;
        }

        public Criteria andSmsDateLessThan(Date value) {
            addCriterion("sms_date <", value, "smsDate");
            return (Criteria) this;
        }

        public Criteria andSmsDateLessThanOrEqualTo(Date value) {
            addCriterion("sms_date <=", value, "smsDate");
            return (Criteria) this;
        }

        public Criteria andSmsDateIn(List<Date> values) {
            addCriterion("sms_date in", values, "smsDate");
            return (Criteria) this;
        }

        public Criteria andSmsDateNotIn(List<Date> values) {
            addCriterion("sms_date not in", values, "smsDate");
            return (Criteria) this;
        }

        public Criteria andSmsDateBetween(Date value1, Date value2) {
            addCriterion("sms_date between", value1, value2, "smsDate");
            return (Criteria) this;
        }

        public Criteria andSmsDateNotBetween(Date value1, Date value2) {
            addCriterion("sms_date not between", value1, value2, "smsDate");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIsNull() {
            addCriterion("business_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIsNotNull() {
            addCriterion("business_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessIdEqualTo(Integer value) {
            addCriterion("business_id =", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotEqualTo(Integer value) {
            addCriterion("business_id <>", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdGreaterThan(Integer value) {
            addCriterion("business_id >", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("business_id >=", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLessThan(Integer value) {
            addCriterion("business_id <", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLessThanOrEqualTo(Integer value) {
            addCriterion("business_id <=", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIn(List<Integer> values) {
            addCriterion("business_id in", values, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotIn(List<Integer> values) {
            addCriterion("business_id not in", values, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdBetween(Integer value1, Integer value2) {
            addCriterion("business_id between", value1, value2, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotBetween(Integer value1, Integer value2) {
            addCriterion("business_id not between", value1, value2, "businessId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(Integer value) {
            addCriterion("type_id =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(Integer value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(Integer value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(Integer value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<Integer> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<Integer> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("type_id not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andSpecificIdIsNull() {
            addCriterion("specific_id is null");
            return (Criteria) this;
        }

        public Criteria andSpecificIdIsNotNull() {
            addCriterion("specific_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificIdEqualTo(String value) {
            addCriterion("specific_id =", value, "specificId");
            return (Criteria) this;
        }

        public Criteria andSpecificIdNotEqualTo(String value) {
            addCriterion("specific_id <>", value, "specificId");
            return (Criteria) this;
        }

        public Criteria andSpecificIdGreaterThan(String value) {
            addCriterion("specific_id >", value, "specificId");
            return (Criteria) this;
        }

        public Criteria andSpecificIdGreaterThanOrEqualTo(String value) {
            addCriterion("specific_id >=", value, "specificId");
            return (Criteria) this;
        }

        public Criteria andSpecificIdLessThan(String value) {
            addCriterion("specific_id <", value, "specificId");
            return (Criteria) this;
        }

        public Criteria andSpecificIdLessThanOrEqualTo(String value) {
            addCriterion("specific_id <=", value, "specificId");
            return (Criteria) this;
        }

        public Criteria andSpecificIdLike(String value) {
            addCriterion("specific_id like", value, "specificId");
            return (Criteria) this;
        }

        public Criteria andSpecificIdNotLike(String value) {
            addCriterion("specific_id not like", value, "specificId");
            return (Criteria) this;
        }

        public Criteria andSpecificIdIn(List<String> values) {
            addCriterion("specific_id in", values, "specificId");
            return (Criteria) this;
        }

        public Criteria andSpecificIdNotIn(List<String> values) {
            addCriterion("specific_id not in", values, "specificId");
            return (Criteria) this;
        }

        public Criteria andSpecificIdBetween(String value1, String value2) {
            addCriterion("specific_id between", value1, value2, "specificId");
            return (Criteria) this;
        }

        public Criteria andSpecificIdNotBetween(String value1, String value2) {
            addCriterion("specific_id not between", value1, value2, "specificId");
            return (Criteria) this;
        }

        public Criteria andSmsStatusIsNull() {
            addCriterion("sms_status is null");
            return (Criteria) this;
        }

        public Criteria andSmsStatusIsNotNull() {
            addCriterion("sms_status is not null");
            return (Criteria) this;
        }

        public Criteria andSmsStatusEqualTo(String value) {
            addCriterion("sms_status =", value, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusNotEqualTo(String value) {
            addCriterion("sms_status <>", value, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusGreaterThan(String value) {
            addCriterion("sms_status >", value, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusGreaterThanOrEqualTo(String value) {
            addCriterion("sms_status >=", value, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusLessThan(String value) {
            addCriterion("sms_status <", value, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusLessThanOrEqualTo(String value) {
            addCriterion("sms_status <=", value, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusLike(String value) {
            addCriterion("sms_status like", value, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusNotLike(String value) {
            addCriterion("sms_status not like", value, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusIn(List<String> values) {
            addCriterion("sms_status in", values, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusNotIn(List<String> values) {
            addCriterion("sms_status not in", values, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusBetween(String value1, String value2) {
            addCriterion("sms_status between", value1, value2, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusNotBetween(String value1, String value2) {
            addCriterion("sms_status not between", value1, value2, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusIsNull() {
            addCriterion("show_status is null");
            return (Criteria) this;
        }

        public Criteria andShowStatusIsNotNull() {
            addCriterion("show_status is not null");
            return (Criteria) this;
        }

        public Criteria andShowStatusEqualTo(String value) {
            addCriterion("show_status =", value, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusNotEqualTo(String value) {
            addCriterion("show_status <>", value, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusGreaterThan(String value) {
            addCriterion("show_status >", value, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusGreaterThanOrEqualTo(String value) {
            addCriterion("show_status >=", value, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusLessThan(String value) {
            addCriterion("show_status <", value, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusLessThanOrEqualTo(String value) {
            addCriterion("show_status <=", value, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusLike(String value) {
            addCriterion("show_status like", value, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusNotLike(String value) {
            addCriterion("show_status not like", value, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusIn(List<String> values) {
            addCriterion("show_status in", values, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusNotIn(List<String> values) {
            addCriterion("show_status not in", values, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusBetween(String value1, String value2) {
            addCriterion("show_status between", value1, value2, "showStatus");
            return (Criteria) this;
        }

        public Criteria andShowStatusNotBetween(String value1, String value2) {
            addCriterion("show_status not between", value1, value2, "showStatus");
            return (Criteria) this;
        }

        public Criteria andFieldoneIsNull() {
            addCriterion("fieldone is null");
            return (Criteria) this;
        }

        public Criteria andFieldoneIsNotNull() {
            addCriterion("fieldone is not null");
            return (Criteria) this;
        }

        public Criteria andFieldoneEqualTo(String value) {
            addCriterion("fieldone =", value, "fieldone");
            return (Criteria) this;
        }

        public Criteria andFieldoneNotEqualTo(String value) {
            addCriterion("fieldone <>", value, "fieldone");
            return (Criteria) this;
        }

        public Criteria andFieldoneGreaterThan(String value) {
            addCriterion("fieldone >", value, "fieldone");
            return (Criteria) this;
        }

        public Criteria andFieldoneGreaterThanOrEqualTo(String value) {
            addCriterion("fieldone >=", value, "fieldone");
            return (Criteria) this;
        }

        public Criteria andFieldoneLessThan(String value) {
            addCriterion("fieldone <", value, "fieldone");
            return (Criteria) this;
        }

        public Criteria andFieldoneLessThanOrEqualTo(String value) {
            addCriterion("fieldone <=", value, "fieldone");
            return (Criteria) this;
        }

        public Criteria andFieldoneLike(String value) {
            addCriterion("fieldone like", value, "fieldone");
            return (Criteria) this;
        }

        public Criteria andFieldoneNotLike(String value) {
            addCriterion("fieldone not like", value, "fieldone");
            return (Criteria) this;
        }

        public Criteria andFieldoneIn(List<String> values) {
            addCriterion("fieldone in", values, "fieldone");
            return (Criteria) this;
        }

        public Criteria andFieldoneNotIn(List<String> values) {
            addCriterion("fieldone not in", values, "fieldone");
            return (Criteria) this;
        }

        public Criteria andFieldoneBetween(String value1, String value2) {
            addCriterion("fieldone between", value1, value2, "fieldone");
            return (Criteria) this;
        }

        public Criteria andFieldoneNotBetween(String value1, String value2) {
            addCriterion("fieldone not between", value1, value2, "fieldone");
            return (Criteria) this;
        }

        public Criteria andFieldtwoIsNull() {
            addCriterion("fieldtwo is null");
            return (Criteria) this;
        }

        public Criteria andFieldtwoIsNotNull() {
            addCriterion("fieldtwo is not null");
            return (Criteria) this;
        }

        public Criteria andFieldtwoEqualTo(String value) {
            addCriterion("fieldtwo =", value, "fieldtwo");
            return (Criteria) this;
        }

        public Criteria andFieldtwoNotEqualTo(String value) {
            addCriterion("fieldtwo <>", value, "fieldtwo");
            return (Criteria) this;
        }

        public Criteria andFieldtwoGreaterThan(String value) {
            addCriterion("fieldtwo >", value, "fieldtwo");
            return (Criteria) this;
        }

        public Criteria andFieldtwoGreaterThanOrEqualTo(String value) {
            addCriterion("fieldtwo >=", value, "fieldtwo");
            return (Criteria) this;
        }

        public Criteria andFieldtwoLessThan(String value) {
            addCriterion("fieldtwo <", value, "fieldtwo");
            return (Criteria) this;
        }

        public Criteria andFieldtwoLessThanOrEqualTo(String value) {
            addCriterion("fieldtwo <=", value, "fieldtwo");
            return (Criteria) this;
        }

        public Criteria andFieldtwoLike(String value) {
            addCriterion("fieldtwo like", value, "fieldtwo");
            return (Criteria) this;
        }

        public Criteria andFieldtwoNotLike(String value) {
            addCriterion("fieldtwo not like", value, "fieldtwo");
            return (Criteria) this;
        }

        public Criteria andFieldtwoIn(List<String> values) {
            addCriterion("fieldtwo in", values, "fieldtwo");
            return (Criteria) this;
        }

        public Criteria andFieldtwoNotIn(List<String> values) {
            addCriterion("fieldtwo not in", values, "fieldtwo");
            return (Criteria) this;
        }

        public Criteria andFieldtwoBetween(String value1, String value2) {
            addCriterion("fieldtwo between", value1, value2, "fieldtwo");
            return (Criteria) this;
        }

        public Criteria andFieldtwoNotBetween(String value1, String value2) {
            addCriterion("fieldtwo not between", value1, value2, "fieldtwo");
            return (Criteria) this;
        }
    }

    /**  tableName: sms   **/
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /** sms **/
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