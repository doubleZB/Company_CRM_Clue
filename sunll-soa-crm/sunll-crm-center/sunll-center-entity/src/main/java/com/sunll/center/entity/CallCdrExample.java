package com.sunll.center.entity;

import java.util.ArrayList;
import java.util.List;

public class CallCdrExample {
    /**   tableName: call_cdr   **/
    protected String orderByClause;

    /**   tableName: call_cdr   **/
    protected boolean distinct;

    /**   tableName: call_cdr   **/
    protected List<Criteria> oredCriteria;

    public CallCdrExample() {
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

    /** call_cdr **/
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

        public Criteria andBindAccountIsNull() {
            addCriterion("bind_account is null");
            return (Criteria) this;
        }

        public Criteria andBindAccountIsNotNull() {
            addCriterion("bind_account is not null");
            return (Criteria) this;
        }

        public Criteria andBindAccountEqualTo(Integer value) {
            addCriterion("bind_account =", value, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountNotEqualTo(Integer value) {
            addCriterion("bind_account <>", value, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountGreaterThan(Integer value) {
            addCriterion("bind_account >", value, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountGreaterThanOrEqualTo(Integer value) {
            addCriterion("bind_account >=", value, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountLessThan(Integer value) {
            addCriterion("bind_account <", value, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountLessThanOrEqualTo(Integer value) {
            addCriterion("bind_account <=", value, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountIn(List<Integer> values) {
            addCriterion("bind_account in", values, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountNotIn(List<Integer> values) {
            addCriterion("bind_account not in", values, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountBetween(Integer value1, Integer value2) {
            addCriterion("bind_account between", value1, value2, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountNotBetween(Integer value1, Integer value2) {
            addCriterion("bind_account not between", value1, value2, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andCallerIsNull() {
            addCriterion("caller is null");
            return (Criteria) this;
        }

        public Criteria andCallerIsNotNull() {
            addCriterion("caller is not null");
            return (Criteria) this;
        }

        public Criteria andCallerEqualTo(String value) {
            addCriterion("caller =", value, "caller");
            return (Criteria) this;
        }

        public Criteria andCallerNotEqualTo(String value) {
            addCriterion("caller <>", value, "caller");
            return (Criteria) this;
        }

        public Criteria andCallerGreaterThan(String value) {
            addCriterion("caller >", value, "caller");
            return (Criteria) this;
        }

        public Criteria andCallerGreaterThanOrEqualTo(String value) {
            addCriterion("caller >=", value, "caller");
            return (Criteria) this;
        }

        public Criteria andCallerLessThan(String value) {
            addCriterion("caller <", value, "caller");
            return (Criteria) this;
        }

        public Criteria andCallerLessThanOrEqualTo(String value) {
            addCriterion("caller <=", value, "caller");
            return (Criteria) this;
        }

        public Criteria andCallerLike(String value) {
            addCriterion("caller like", value, "caller");
            return (Criteria) this;
        }

        public Criteria andCallerNotLike(String value) {
            addCriterion("caller not like", value, "caller");
            return (Criteria) this;
        }

        public Criteria andCallerIn(List<String> values) {
            addCriterion("caller in", values, "caller");
            return (Criteria) this;
        }

        public Criteria andCallerNotIn(List<String> values) {
            addCriterion("caller not in", values, "caller");
            return (Criteria) this;
        }

        public Criteria andCallerBetween(String value1, String value2) {
            addCriterion("caller between", value1, value2, "caller");
            return (Criteria) this;
        }

        public Criteria andCallerNotBetween(String value1, String value2) {
            addCriterion("caller not between", value1, value2, "caller");
            return (Criteria) this;
        }

        public Criteria andCalledIsNull() {
            addCriterion("called is null");
            return (Criteria) this;
        }

        public Criteria andCalledIsNotNull() {
            addCriterion("called is not null");
            return (Criteria) this;
        }

        public Criteria andCalledEqualTo(String value) {
            addCriterion("called =", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledNotEqualTo(String value) {
            addCriterion("called <>", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledGreaterThan(String value) {
            addCriterion("called >", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledGreaterThanOrEqualTo(String value) {
            addCriterion("called >=", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledLessThan(String value) {
            addCriterion("called <", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledLessThanOrEqualTo(String value) {
            addCriterion("called <=", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledLike(String value) {
            addCriterion("called like", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledNotLike(String value) {
            addCriterion("called not like", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledIn(List<String> values) {
            addCriterion("called in", values, "called");
            return (Criteria) this;
        }

        public Criteria andCalledNotIn(List<String> values) {
            addCriterion("called not in", values, "called");
            return (Criteria) this;
        }

        public Criteria andCalledBetween(String value1, String value2) {
            addCriterion("called between", value1, value2, "called");
            return (Criteria) this;
        }

        public Criteria andCalledNotBetween(String value1, String value2) {
            addCriterion("called not between", value1, value2, "called");
            return (Criteria) this;
        }

        public Criteria andStartStampIsNull() {
            addCriterion("start_stamp is null");
            return (Criteria) this;
        }

        public Criteria andStartStampIsNotNull() {
            addCriterion("start_stamp is not null");
            return (Criteria) this;
        }

        public Criteria andStartStampEqualTo(Integer value) {
            addCriterion("start_stamp =", value, "startStamp");
            return (Criteria) this;
        }

        public Criteria andStartStampNotEqualTo(Integer value) {
            addCriterion("start_stamp <>", value, "startStamp");
            return (Criteria) this;
        }

        public Criteria andStartStampGreaterThan(Integer value) {
            addCriterion("start_stamp >", value, "startStamp");
            return (Criteria) this;
        }

        public Criteria andStartStampGreaterThanOrEqualTo(Integer value) {
            addCriterion("start_stamp >=", value, "startStamp");
            return (Criteria) this;
        }

        public Criteria andStartStampLessThan(Integer value) {
            addCriterion("start_stamp <", value, "startStamp");
            return (Criteria) this;
        }

        public Criteria andStartStampLessThanOrEqualTo(Integer value) {
            addCriterion("start_stamp <=", value, "startStamp");
            return (Criteria) this;
        }

        //add by szq for zhangkexin
        public Criteria multiColumnOrClause(String value1) {
            addCriterion("( caller = " + value1 + " OR called = " + value1+" )");
            return (Criteria)this;
        }

        public Criteria andStartStampIn(List<Integer> values) {
            addCriterion("start_stamp in", values, "startStamp");
            return (Criteria) this;
        }

        public Criteria andStartStampNotIn(List<Integer> values) {
            addCriterion("start_stamp not in", values, "startStamp");
            return (Criteria) this;
        }

        public Criteria andStartStampBetween(Integer value1, Integer value2) {
            addCriterion("start_stamp between", value1, value2, "startStamp");
            return (Criteria) this;
        }

        public Criteria andStartStampNotBetween(Integer value1, Integer value2) {
            addCriterion("start_stamp not between", value1, value2, "startStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampIsNull() {
            addCriterion("answer_stamp is null");
            return (Criteria) this;
        }

        public Criteria andAnswerStampIsNotNull() {
            addCriterion("answer_stamp is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerStampEqualTo(Integer value) {
            addCriterion("answer_stamp =", value, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampNotEqualTo(Integer value) {
            addCriterion("answer_stamp <>", value, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampGreaterThan(Integer value) {
            addCriterion("answer_stamp >", value, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampGreaterThanOrEqualTo(Integer value) {
            addCriterion("answer_stamp >=", value, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampLessThan(Integer value) {
            addCriterion("answer_stamp <", value, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampLessThanOrEqualTo(Integer value) {
            addCriterion("answer_stamp <=", value, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampIn(List<Integer> values) {
            addCriterion("answer_stamp in", values, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampNotIn(List<Integer> values) {
            addCriterion("answer_stamp not in", values, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampBetween(Integer value1, Integer value2) {
            addCriterion("answer_stamp between", value1, value2, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampNotBetween(Integer value1, Integer value2) {
            addCriterion("answer_stamp not between", value1, value2, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampIsNull() {
            addCriterion("end_stamp is null");
            return (Criteria) this;
        }

        public Criteria andEndStampIsNotNull() {
            addCriterion("end_stamp is not null");
            return (Criteria) this;
        }

        public Criteria andEndStampEqualTo(Integer value) {
            addCriterion("end_stamp =", value, "endStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampNotEqualTo(Integer value) {
            addCriterion("end_stamp <>", value, "endStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampGreaterThan(Integer value) {
            addCriterion("end_stamp >", value, "endStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampGreaterThanOrEqualTo(Integer value) {
            addCriterion("end_stamp >=", value, "endStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampLessThan(Integer value) {
            addCriterion("end_stamp <", value, "endStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampLessThanOrEqualTo(Integer value) {
            addCriterion("end_stamp <=", value, "endStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampIn(List<Integer> values) {
            addCriterion("end_stamp in", values, "endStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampNotIn(List<Integer> values) {
            addCriterion("end_stamp not in", values, "endStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampBetween(Integer value1, Integer value2) {
            addCriterion("end_stamp between", value1, value2, "endStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampNotBetween(Integer value1, Integer value2) {
            addCriterion("end_stamp not between", value1, value2, "endStamp");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("duration is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("duration is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(Integer value) {
            addCriterion("duration =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(Integer value) {
            addCriterion("duration <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(Integer value) {
            addCriterion("duration >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(Integer value) {
            addCriterion("duration >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(Integer value) {
            addCriterion("duration <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(Integer value) {
            addCriterion("duration <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<Integer> values) {
            addCriterion("duration in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<Integer> values) {
            addCriterion("duration not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(Integer value1, Integer value2) {
            addCriterion("duration between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(Integer value1, Integer value2) {
            addCriterion("duration not between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andBillsecIsNull() {
            addCriterion("billsec is null");
            return (Criteria) this;
        }

        public Criteria andBillsecIsNotNull() {
            addCriterion("billsec is not null");
            return (Criteria) this;
        }

        public Criteria andBillsecEqualTo(Integer value) {
            addCriterion("billsec =", value, "billsec");
            return (Criteria) this;
        }

        public Criteria andBillsecNotEqualTo(Integer value) {
            addCriterion("billsec <>", value, "billsec");
            return (Criteria) this;
        }

        public Criteria andBillsecGreaterThan(Integer value) {
            addCriterion("billsec >", value, "billsec");
            return (Criteria) this;
        }

        public Criteria andBillsecGreaterThanOrEqualTo(Integer value) {
            addCriterion("billsec >=", value, "billsec");
            return (Criteria) this;
        }

        public Criteria andBillsecLessThan(Integer value) {
            addCriterion("billsec <", value, "billsec");
            return (Criteria) this;
        }

        public Criteria andBillsecLessThanOrEqualTo(Integer value) {
            addCriterion("billsec <=", value, "billsec");
            return (Criteria) this;
        }

        public Criteria andBillsecIn(List<Integer> values) {
            addCriterion("billsec in", values, "billsec");
            return (Criteria) this;
        }

        public Criteria andBillsecNotIn(List<Integer> values) {
            addCriterion("billsec not in", values, "billsec");
            return (Criteria) this;
        }

        public Criteria andBillsecBetween(Integer value1, Integer value2) {
            addCriterion("billsec between", value1, value2, "billsec");
            return (Criteria) this;
        }

        public Criteria andBillsecNotBetween(Integer value1, Integer value2) {
            addCriterion("billsec not between", value1, value2, "billsec");
            return (Criteria) this;
        }

        public Criteria andHangupCauseIsNull() {
            addCriterion("hangup_cause is null");
            return (Criteria) this;
        }

        public Criteria andHangupCauseIsNotNull() {
            addCriterion("hangup_cause is not null");
            return (Criteria) this;
        }

        public Criteria andHangupCauseEqualTo(String value) {
            addCriterion("hangup_cause =", value, "hangupCause");
            return (Criteria) this;
        }

        public Criteria andHangupCauseNotEqualTo(String value) {
            addCriterion("hangup_cause <>", value, "hangupCause");
            return (Criteria) this;
        }

        public Criteria andHangupCauseGreaterThan(String value) {
            addCriterion("hangup_cause >", value, "hangupCause");
            return (Criteria) this;
        }

        public Criteria andHangupCauseGreaterThanOrEqualTo(String value) {
            addCriterion("hangup_cause >=", value, "hangupCause");
            return (Criteria) this;
        }

        public Criteria andHangupCauseLessThan(String value) {
            addCriterion("hangup_cause <", value, "hangupCause");
            return (Criteria) this;
        }

        public Criteria andHangupCauseLessThanOrEqualTo(String value) {
            addCriterion("hangup_cause <=", value, "hangupCause");
            return (Criteria) this;
        }

        public Criteria andHangupCauseLike(String value) {
            addCriterion("hangup_cause like", value, "hangupCause");
            return (Criteria) this;
        }

        public Criteria andHangupCauseNotLike(String value) {
            addCriterion("hangup_cause not like", value, "hangupCause");
            return (Criteria) this;
        }

        public Criteria andHangupCauseIn(List<String> values) {
            addCriterion("hangup_cause in", values, "hangupCause");
            return (Criteria) this;
        }

        public Criteria andHangupCauseNotIn(List<String> values) {
            addCriterion("hangup_cause not in", values, "hangupCause");
            return (Criteria) this;
        }

        public Criteria andHangupCauseBetween(String value1, String value2) {
            addCriterion("hangup_cause between", value1, value2, "hangupCause");
            return (Criteria) this;
        }

        public Criteria andHangupCauseNotBetween(String value1, String value2) {
            addCriterion("hangup_cause not between", value1, value2, "hangupCause");
            return (Criteria) this;
        }

        public Criteria andDirectionIsNull() {
            addCriterion("direction is null");
            return (Criteria) this;
        }

        public Criteria andDirectionIsNotNull() {
            addCriterion("direction is not null");
            return (Criteria) this;
        }

        public Criteria andDirectionEqualTo(String value) {
            addCriterion("direction =", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotEqualTo(String value) {
            addCriterion("direction <>", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionGreaterThan(String value) {
            addCriterion("direction >", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionGreaterThanOrEqualTo(String value) {
            addCriterion("direction >=", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLessThan(String value) {
            addCriterion("direction <", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLessThanOrEqualTo(String value) {
            addCriterion("direction <=", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLike(String value) {
            addCriterion("direction like", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotLike(String value) {
            addCriterion("direction not like", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionIn(List<String> values) {
            addCriterion("direction in", values, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotIn(List<String> values) {
            addCriterion("direction not in", values, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionBetween(String value1, String value2) {
            addCriterion("direction between", value1, value2, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotBetween(String value1, String value2) {
            addCriterion("direction not between", value1, value2, "direction");
            return (Criteria) this;
        }
    }

    /**  tableName: call_cdr   **/
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /** call_cdr **/
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