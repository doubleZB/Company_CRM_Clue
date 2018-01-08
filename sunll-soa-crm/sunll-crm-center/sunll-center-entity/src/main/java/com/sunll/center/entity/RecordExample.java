package com.sunll.center.entity;

import com.github.pagehelper.StringUtil;
import org.apache.commons.lang.math.NumberUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RecordExample {
    /**   tableName: record   **/
    protected String orderByClause;

    /**   tableName: record   **/
    protected boolean distinct;

    /**   tableName: record   **/
    protected List<Criteria> oredCriteria;

    public RecordExample() {
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

    /** record **/
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

        public Criteria andBindPhoneIsNull() {
            addCriterion("bind_phone is null");
            return (Criteria) this;
        }

        public Criteria andBindPhoneIsNotNull() {
            addCriterion("bind_phone is not null");
            return (Criteria) this;
        }

        public Criteria andBindPhoneEqualTo(Integer value) {
            addCriterion("bind_phone =", value, "bindPhone");
            return (Criteria) this;
        }

        public Criteria andBindPhoneNotEqualTo(Integer value) {
            addCriterion("bind_phone <>", value, "bindPhone");
            return (Criteria) this;
        }

        public Criteria andBindPhoneGreaterThan(Integer value) {
            addCriterion("bind_phone >", value, "bindPhone");
            return (Criteria) this;
        }

        public Criteria andBindPhoneGreaterThanOrEqualTo(Integer value) {
            addCriterion("bind_phone >=", value, "bindPhone");
            return (Criteria) this;
        }

        public Criteria andBindPhoneLessThan(Integer value) {
            addCriterion("bind_phone <", value, "bindPhone");
            return (Criteria) this;
        }

        public Criteria andBindPhoneLessThanOrEqualTo(Integer value) {
            addCriterion("bind_phone <=", value, "bindPhone");
            return (Criteria) this;
        }

        public Criteria andBindPhoneIn(List<Integer> values) {
            addCriterion("bind_phone in", values, "bindPhone");
            return (Criteria) this;
        }

        public Criteria andBindPhoneNotIn(List<Integer> values) {
            addCriterion("bind_phone not in", values, "bindPhone");
            return (Criteria) this;
        }

        public Criteria andBindPhoneBetween(Integer value1, Integer value2) {
            addCriterion("bind_phone between", value1, value2, "bindPhone");
            return (Criteria) this;
        }

        public Criteria andBindPhoneNotBetween(Integer value1, Integer value2) {
            addCriterion("bind_phone not between", value1, value2, "bindPhone");
            return (Criteria) this;
        }

        public Criteria andRecordPhoneIsNull() {
            addCriterion("record_phone is null");
            return (Criteria) this;
        }

        public Criteria andRecordPhoneIsNotNull() {
            addCriterion("record_phone is not null");
            return (Criteria) this;
        }

        public Criteria andRecordPhoneEqualTo(String value) {
            addCriterion("record_phone =", value, "recordPhone");
            return (Criteria) this;
        }

        public Criteria andRecordPhoneNotEqualTo(String value) {
            addCriterion("record_phone <>", value, "recordPhone");
            return (Criteria) this;
        }

        public Criteria andRecordPhoneGreaterThan(String value) {
            addCriterion("record_phone >", value, "recordPhone");
            return (Criteria) this;
        }

        public Criteria andRecordPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("record_phone >=", value, "recordPhone");
            return (Criteria) this;
        }

        public Criteria andRecordPhoneLessThan(String value) {
            addCriterion("record_phone <", value, "recordPhone");
            return (Criteria) this;
        }

        public Criteria andRecordPhoneLessThanOrEqualTo(String value) {
            addCriterion("record_phone <=", value, "recordPhone");
            return (Criteria) this;
        }

        public Criteria andRecordPhoneLike(String value) {
            addCriterion("record_phone like", value, "recordPhone");
            return (Criteria) this;
        }

        public Criteria andRecordPhoneNotLike(String value) {
            addCriterion("record_phone not like", value, "recordPhone");
            return (Criteria) this;
        }

        public Criteria andRecordPhoneIn(List<String> values) {
            addCriterion("record_phone in", values, "recordPhone");
            return (Criteria) this;
        }

        public Criteria andRecordPhoneNotIn(List<String> values) {
            addCriterion("record_phone not in", values, "recordPhone");
            return (Criteria) this;
        }

        public Criteria andRecordPhoneBetween(String value1, String value2) {
            addCriterion("record_phone between", value1, value2, "recordPhone");
            return (Criteria) this;
        }

        public Criteria andRecordPhoneNotBetween(String value1, String value2) {
            addCriterion("record_phone not between", value1, value2, "recordPhone");
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

        public Criteria andStarttimeIsNull() {
            addCriterion("starttime is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNotNull() {
            addCriterion("starttime is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeEqualTo(Date value) {
            addCriterion("starttime =", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotEqualTo(Date value) {
            addCriterion("starttime <>", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThan(Date value) {
            addCriterion("starttime >", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("starttime >=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThan(Date value) {
            addCriterion("starttime <", value, "starttime");
            return (Criteria) this;
        }

        //add by szq for zhangkexin
        public Criteria multiColumnOrClause(String value1) {
            addCriterion("( caller = " + value1 + " OR called = " + value1+" )");
            return (Criteria)this;
        }

        //add by szq for zhangkexin List<map>
        public Criteria multiColumnOrClauseLst(List<Map> lst) {
            if(lst!=null){
                String cond = " ( ";
                String accountMobile ="";
                for(int i=0;i<lst.size();i++){
                    if(lst.get(i).get("mobile")!=null){
                        accountMobile = lst.get(i).get("mobile").toString();
                        if(StringUtil.isNotEmpty(accountMobile)&& NumberUtils.isNumber(accountMobile)){
                            cond = cond + " caller = " + accountMobile + " OR called = " + accountMobile;
                        }
                    }
                }
                cond = cond + " ) ";
                if(!" (  ) ".equals(cond)){
                    addCriterion(cond);
                }
            }
            return (Criteria)this;
        }

        public Criteria andStarttimeLessThanOrEqualTo(Date value) {
            addCriterion("starttime <=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIn(List<Date> values) {
            addCriterion("starttime in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotIn(List<Date> values) {
            addCriterion("starttime not in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeBetween(Date value1, Date value2) {
            addCriterion("starttime between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotBetween(Date value1, Date value2) {
            addCriterion("starttime not between", value1, value2, "starttime");
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

        public Criteria andFilepathIsNull() {
            addCriterion("filepath is null");
            return (Criteria) this;
        }

        public Criteria andFilepathIsNotNull() {
            addCriterion("filepath is not null");
            return (Criteria) this;
        }

        public Criteria andFilepathEqualTo(String value) {
            addCriterion("filepath =", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotEqualTo(String value) {
            addCriterion("filepath <>", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathGreaterThan(String value) {
            addCriterion("filepath >", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathGreaterThanOrEqualTo(String value) {
            addCriterion("filepath >=", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathLessThan(String value) {
            addCriterion("filepath <", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathLessThanOrEqualTo(String value) {
            addCriterion("filepath <=", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathLike(String value) {
            addCriterion("filepath like", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotLike(String value) {
            addCriterion("filepath not like", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathIn(List<String> values) {
            addCriterion("filepath in", values, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotIn(List<String> values) {
            addCriterion("filepath not in", values, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathBetween(String value1, String value2) {
            addCriterion("filepath between", value1, value2, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotBetween(String value1, String value2) {
            addCriterion("filepath not between", value1, value2, "filepath");
            return (Criteria) this;
        }
    }

    /**  tableName: record   **/
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /** record **/
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