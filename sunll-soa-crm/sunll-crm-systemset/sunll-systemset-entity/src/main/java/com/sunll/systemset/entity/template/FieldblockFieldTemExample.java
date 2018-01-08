package com.sunll.systemset.entity.template;

import java.util.ArrayList;
import java.util.List;

public class FieldblockFieldTemExample {
    /**   tableName: fieldblock_field_tem   **/
    protected String orderByClause;

    /**   tableName: fieldblock_field_tem   **/
    protected boolean distinct;

    /**   tableName: fieldblock_field_tem   **/
    protected List<Criteria> oredCriteria;

    public FieldblockFieldTemExample() {
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

    /** fieldblock_field_tem **/
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

        public Criteria andFieldBlockTemIdIsNull() {
            addCriterion("field_block_tem_id is null");
            return (Criteria) this;
        }

        public Criteria andFieldBlockTemIdIsNotNull() {
            addCriterion("field_block_tem_id is not null");
            return (Criteria) this;
        }

        public Criteria andFieldBlockTemIdEqualTo(Integer value) {
            addCriterion("field_block_tem_id =", value, "fieldBlockTemId");
            return (Criteria) this;
        }

        public Criteria andFieldBlockTemIdNotEqualTo(Integer value) {
            addCriterion("field_block_tem_id <>", value, "fieldBlockTemId");
            return (Criteria) this;
        }

        public Criteria andFieldBlockTemIdGreaterThan(Integer value) {
            addCriterion("field_block_tem_id >", value, "fieldBlockTemId");
            return (Criteria) this;
        }

        public Criteria andFieldBlockTemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("field_block_tem_id >=", value, "fieldBlockTemId");
            return (Criteria) this;
        }

        public Criteria andFieldBlockTemIdLessThan(Integer value) {
            addCriterion("field_block_tem_id <", value, "fieldBlockTemId");
            return (Criteria) this;
        }

        public Criteria andFieldBlockTemIdLessThanOrEqualTo(Integer value) {
            addCriterion("field_block_tem_id <=", value, "fieldBlockTemId");
            return (Criteria) this;
        }

        public Criteria andFieldBlockTemIdIn(List<Integer> values) {
            addCriterion("field_block_tem_id in", values, "fieldBlockTemId");
            return (Criteria) this;
        }

        public Criteria andFieldBlockTemIdNotIn(List<Integer> values) {
            addCriterion("field_block_tem_id not in", values, "fieldBlockTemId");
            return (Criteria) this;
        }

        public Criteria andFieldBlockTemIdBetween(Integer value1, Integer value2) {
            addCriterion("field_block_tem_id between", value1, value2, "fieldBlockTemId");
            return (Criteria) this;
        }

        public Criteria andFieldBlockTemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("field_block_tem_id not between", value1, value2, "fieldBlockTemId");
            return (Criteria) this;
        }

        public Criteria andFieldTemIdIsNull() {
            addCriterion("field_tem_id is null");
            return (Criteria) this;
        }

        public Criteria andFieldTemIdIsNotNull() {
            addCriterion("field_tem_id is not null");
            return (Criteria) this;
        }

        public Criteria andFieldTemIdEqualTo(Integer value) {
            addCriterion("field_tem_id =", value, "fieldTemId");
            return (Criteria) this;
        }

        public Criteria andFieldTemIdNotEqualTo(Integer value) {
            addCriterion("field_tem_id <>", value, "fieldTemId");
            return (Criteria) this;
        }

        public Criteria andFieldTemIdGreaterThan(Integer value) {
            addCriterion("field_tem_id >", value, "fieldTemId");
            return (Criteria) this;
        }

        public Criteria andFieldTemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("field_tem_id >=", value, "fieldTemId");
            return (Criteria) this;
        }

        public Criteria andFieldTemIdLessThan(Integer value) {
            addCriterion("field_tem_id <", value, "fieldTemId");
            return (Criteria) this;
        }

        public Criteria andFieldTemIdLessThanOrEqualTo(Integer value) {
            addCriterion("field_tem_id <=", value, "fieldTemId");
            return (Criteria) this;
        }

        public Criteria andFieldTemIdIn(List<Integer> values) {
            addCriterion("field_tem_id in", values, "fieldTemId");
            return (Criteria) this;
        }

        public Criteria andFieldTemIdNotIn(List<Integer> values) {
            addCriterion("field_tem_id not in", values, "fieldTemId");
            return (Criteria) this;
        }

        public Criteria andFieldTemIdBetween(Integer value1, Integer value2) {
            addCriterion("field_tem_id between", value1, value2, "fieldTemId");
            return (Criteria) this;
        }

        public Criteria andFieldTemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("field_tem_id not between", value1, value2, "fieldTemId");
            return (Criteria) this;
        }
    }

    /**  tableName: fieldblock_field_tem   **/
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /** fieldblock_field_tem **/
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