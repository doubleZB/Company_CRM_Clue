package com.sunll.center.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UsersExample {
    /**   tableName: users   **/
    protected String orderByClause;

    /**   tableName: users   **/
    protected boolean distinct;

    /**   tableName: users   **/
    protected List<Criteria> oredCriteria;

    public UsersExample() {
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

    /** users **/
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

        public Criteria andSipIdIsNull() {
            addCriterion("sip_id is null");
            return (Criteria) this;
        }

        public Criteria andSipIdIsNotNull() {
            addCriterion("sip_id is not null");
            return (Criteria) this;
        }

        public Criteria andSipIdEqualTo(String value) {
            addCriterion("sip_id =", value, "sipId");
            return (Criteria) this;
        }

        public Criteria andSipIdNotEqualTo(String value) {
            addCriterion("sip_id <>", value, "sipId");
            return (Criteria) this;
        }

        public Criteria andSipIdGreaterThan(String value) {
            addCriterion("sip_id >", value, "sipId");
            return (Criteria) this;
        }

        public Criteria andSipIdGreaterThanOrEqualTo(String value) {
            addCriterion("sip_id >=", value, "sipId");
            return (Criteria) this;
        }

        public Criteria andSipIdLessThan(String value) {
            addCriterion("sip_id <", value, "sipId");
            return (Criteria) this;
        }

        public Criteria andSipIdLessThanOrEqualTo(String value) {
            addCriterion("sip_id <=", value, "sipId");
            return (Criteria) this;
        }

        public Criteria andSipIdLike(String value) {
            addCriterion("sip_id like", value, "sipId");
            return (Criteria) this;
        }

        public Criteria andSipIdNotLike(String value) {
            addCriterion("sip_id not like", value, "sipId");
            return (Criteria) this;
        }

        public Criteria andSipIdIn(List<String> values) {
            addCriterion("sip_id in", values, "sipId");
            return (Criteria) this;
        }

        public Criteria andSipIdNotIn(List<String> values) {
            addCriterion("sip_id not in", values, "sipId");
            return (Criteria) this;
        }

        public Criteria andSipIdBetween(String value1, String value2) {
            addCriterion("sip_id between", value1, value2, "sipId");
            return (Criteria) this;
        }

        public Criteria andSipIdNotBetween(String value1, String value2) {
            addCriterion("sip_id not between", value1, value2, "sipId");
            return (Criteria) this;
        }

        public Criteria andSipPasswordIsNull() {
            addCriterion("sip_password is null");
            return (Criteria) this;
        }

        public Criteria andSipPasswordIsNotNull() {
            addCriterion("sip_password is not null");
            return (Criteria) this;
        }

        public Criteria andSipPasswordEqualTo(String value) {
            addCriterion("sip_password =", value, "sipPassword");
            return (Criteria) this;
        }

        public Criteria andSipPasswordNotEqualTo(String value) {
            addCriterion("sip_password <>", value, "sipPassword");
            return (Criteria) this;
        }

        public Criteria andSipPasswordGreaterThan(String value) {
            addCriterion("sip_password >", value, "sipPassword");
            return (Criteria) this;
        }

        public Criteria andSipPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("sip_password >=", value, "sipPassword");
            return (Criteria) this;
        }

        public Criteria andSipPasswordLessThan(String value) {
            addCriterion("sip_password <", value, "sipPassword");
            return (Criteria) this;
        }

        public Criteria andSipPasswordLessThanOrEqualTo(String value) {
            addCriterion("sip_password <=", value, "sipPassword");
            return (Criteria) this;
        }

        public Criteria andSipPasswordLike(String value) {
            addCriterion("sip_password like", value, "sipPassword");
            return (Criteria) this;
        }

        public Criteria andSipPasswordNotLike(String value) {
            addCriterion("sip_password not like", value, "sipPassword");
            return (Criteria) this;
        }

        public Criteria andSipPasswordIn(List<String> values) {
            addCriterion("sip_password in", values, "sipPassword");
            return (Criteria) this;
        }

        public Criteria andSipPasswordNotIn(List<String> values) {
            addCriterion("sip_password not in", values, "sipPassword");
            return (Criteria) this;
        }

        public Criteria andSipPasswordBetween(String value1, String value2) {
            addCriterion("sip_password between", value1, value2, "sipPassword");
            return (Criteria) this;
        }

        public Criteria andSipPasswordNotBetween(String value1, String value2) {
            addCriterion("sip_password not between", value1, value2, "sipPassword");
            return (Criteria) this;
        }

        public Criteria andSipKeyIsNull() {
            addCriterion("sip_key is null");
            return (Criteria) this;
        }

        public Criteria andSipKeyIsNotNull() {
            addCriterion("sip_key is not null");
            return (Criteria) this;
        }

        public Criteria andSipKeyEqualTo(String value) {
            addCriterion("sip_key =", value, "sipKey");
            return (Criteria) this;
        }

        public Criteria andSipKeyNotEqualTo(String value) {
            addCriterion("sip_key <>", value, "sipKey");
            return (Criteria) this;
        }

        public Criteria andSipKeyGreaterThan(String value) {
            addCriterion("sip_key >", value, "sipKey");
            return (Criteria) this;
        }

        public Criteria andSipKeyGreaterThanOrEqualTo(String value) {
            addCriterion("sip_key >=", value, "sipKey");
            return (Criteria) this;
        }

        public Criteria andSipKeyLessThan(String value) {
            addCriterion("sip_key <", value, "sipKey");
            return (Criteria) this;
        }

        public Criteria andSipKeyLessThanOrEqualTo(String value) {
            addCriterion("sip_key <=", value, "sipKey");
            return (Criteria) this;
        }

        public Criteria andSipKeyLike(String value) {
            addCriterion("sip_key like", value, "sipKey");
            return (Criteria) this;
        }

        public Criteria andSipKeyNotLike(String value) {
            addCriterion("sip_key not like", value, "sipKey");
            return (Criteria) this;
        }

        public Criteria andSipKeyIn(List<String> values) {
            addCriterion("sip_key in", values, "sipKey");
            return (Criteria) this;
        }

        public Criteria andSipKeyNotIn(List<String> values) {
            addCriterion("sip_key not in", values, "sipKey");
            return (Criteria) this;
        }

        public Criteria andSipKeyBetween(String value1, String value2) {
            addCriterion("sip_key between", value1, value2, "sipKey");
            return (Criteria) this;
        }

        public Criteria andSipKeyNotBetween(String value1, String value2) {
            addCriterion("sip_key not between", value1, value2, "sipKey");
            return (Criteria) this;
        }

        public Criteria andSipDomainIsNull() {
            addCriterion("sip_domain is null");
            return (Criteria) this;
        }

        public Criteria andSipDomainIsNotNull() {
            addCriterion("sip_domain is not null");
            return (Criteria) this;
        }

        public Criteria andSipDomainEqualTo(String value) {
            addCriterion("sip_domain =", value, "sipDomain");
            return (Criteria) this;
        }

        public Criteria andSipDomainNotEqualTo(String value) {
            addCriterion("sip_domain <>", value, "sipDomain");
            return (Criteria) this;
        }

        public Criteria andSipDomainGreaterThan(String value) {
            addCriterion("sip_domain >", value, "sipDomain");
            return (Criteria) this;
        }

        public Criteria andSipDomainGreaterThanOrEqualTo(String value) {
            addCriterion("sip_domain >=", value, "sipDomain");
            return (Criteria) this;
        }

        public Criteria andSipDomainLessThan(String value) {
            addCriterion("sip_domain <", value, "sipDomain");
            return (Criteria) this;
        }

        public Criteria andSipDomainLessThanOrEqualTo(String value) {
            addCriterion("sip_domain <=", value, "sipDomain");
            return (Criteria) this;
        }

        public Criteria andSipDomainLike(String value) {
            addCriterion("sip_domain like", value, "sipDomain");
            return (Criteria) this;
        }

        public Criteria andSipDomainNotLike(String value) {
            addCriterion("sip_domain not like", value, "sipDomain");
            return (Criteria) this;
        }

        public Criteria andSipDomainIn(List<String> values) {
            addCriterion("sip_domain in", values, "sipDomain");
            return (Criteria) this;
        }

        public Criteria andSipDomainNotIn(List<String> values) {
            addCriterion("sip_domain not in", values, "sipDomain");
            return (Criteria) this;
        }

        public Criteria andSipDomainBetween(String value1, String value2) {
            addCriterion("sip_domain between", value1, value2, "sipDomain");
            return (Criteria) this;
        }

        public Criteria andSipDomainNotBetween(String value1, String value2) {
            addCriterion("sip_domain not between", value1, value2, "sipDomain");
            return (Criteria) this;
        }

        public Criteria andSipCallgroupIsNull() {
            addCriterion("sip_callgroup is null");
            return (Criteria) this;
        }

        public Criteria andSipCallgroupIsNotNull() {
            addCriterion("sip_callgroup is not null");
            return (Criteria) this;
        }

        public Criteria andSipCallgroupEqualTo(String value) {
            addCriterion("sip_callgroup =", value, "sipCallgroup");
            return (Criteria) this;
        }

        public Criteria andSipCallgroupNotEqualTo(String value) {
            addCriterion("sip_callgroup <>", value, "sipCallgroup");
            return (Criteria) this;
        }

        public Criteria andSipCallgroupGreaterThan(String value) {
            addCriterion("sip_callgroup >", value, "sipCallgroup");
            return (Criteria) this;
        }

        public Criteria andSipCallgroupGreaterThanOrEqualTo(String value) {
            addCriterion("sip_callgroup >=", value, "sipCallgroup");
            return (Criteria) this;
        }

        public Criteria andSipCallgroupLessThan(String value) {
            addCriterion("sip_callgroup <", value, "sipCallgroup");
            return (Criteria) this;
        }

        public Criteria andSipCallgroupLessThanOrEqualTo(String value) {
            addCriterion("sip_callgroup <=", value, "sipCallgroup");
            return (Criteria) this;
        }

        public Criteria andSipCallgroupLike(String value) {
            addCriterion("sip_callgroup like", value, "sipCallgroup");
            return (Criteria) this;
        }

        public Criteria andSipCallgroupNotLike(String value) {
            addCriterion("sip_callgroup not like", value, "sipCallgroup");
            return (Criteria) this;
        }

        public Criteria andSipCallgroupIn(List<String> values) {
            addCriterion("sip_callgroup in", values, "sipCallgroup");
            return (Criteria) this;
        }

        public Criteria andSipCallgroupNotIn(List<String> values) {
            addCriterion("sip_callgroup not in", values, "sipCallgroup");
            return (Criteria) this;
        }

        public Criteria andSipCallgroupBetween(String value1, String value2) {
            addCriterion("sip_callgroup between", value1, value2, "sipCallgroup");
            return (Criteria) this;
        }

        public Criteria andSipCallgroupNotBetween(String value1, String value2) {
            addCriterion("sip_callgroup not between", value1, value2, "sipCallgroup");
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

        public Criteria andBindServerIsNull() {
            addCriterion("bind_server is null");
            return (Criteria) this;
        }

        public Criteria andBindServerIsNotNull() {
            addCriterion("bind_server is not null");
            return (Criteria) this;
        }

        public Criteria andBindServerEqualTo(Integer value) {
            addCriterion("bind_server =", value, "bindServer");
            return (Criteria) this;
        }

        public Criteria andBindServerNotEqualTo(Integer value) {
            addCriterion("bind_server <>", value, "bindServer");
            return (Criteria) this;
        }

        public Criteria andBindServerGreaterThan(Integer value) {
            addCriterion("bind_server >", value, "bindServer");
            return (Criteria) this;
        }

        public Criteria andBindServerGreaterThanOrEqualTo(Integer value) {
            addCriterion("bind_server >=", value, "bindServer");
            return (Criteria) this;
        }

        public Criteria andBindServerLessThan(Integer value) {
            addCriterion("bind_server <", value, "bindServer");
            return (Criteria) this;
        }

        public Criteria andBindServerLessThanOrEqualTo(Integer value) {
            addCriterion("bind_server <=", value, "bindServer");
            return (Criteria) this;
        }

        public Criteria andBindServerIn(List<Integer> values) {
            addCriterion("bind_server in", values, "bindServer");
            return (Criteria) this;
        }

        public Criteria andBindServerNotIn(List<Integer> values) {
            addCriterion("bind_server not in", values, "bindServer");
            return (Criteria) this;
        }

        public Criteria andBindServerBetween(Integer value1, Integer value2) {
            addCriterion("bind_server between", value1, value2, "bindServer");
            return (Criteria) this;
        }

        public Criteria andBindServerNotBetween(Integer value1, Integer value2) {
            addCriterion("bind_server not between", value1, value2, "bindServer");
            return (Criteria) this;
        }

        public Criteria andBindHangupIsNull() {
            addCriterion("bind_hangup is null");
            return (Criteria) this;
        }

        public Criteria andBindHangupIsNotNull() {
            addCriterion("bind_hangup is not null");
            return (Criteria) this;
        }

        public Criteria andBindHangupEqualTo(Integer value) {
            addCriterion("bind_hangup =", value, "bindHangup");
            return (Criteria) this;
        }

        public Criteria andBindHangupNotEqualTo(Integer value) {
            addCriterion("bind_hangup <>", value, "bindHangup");
            return (Criteria) this;
        }

        public Criteria andBindHangupGreaterThan(Integer value) {
            addCriterion("bind_hangup >", value, "bindHangup");
            return (Criteria) this;
        }

        public Criteria andBindHangupGreaterThanOrEqualTo(Integer value) {
            addCriterion("bind_hangup >=", value, "bindHangup");
            return (Criteria) this;
        }

        public Criteria andBindHangupLessThan(Integer value) {
            addCriterion("bind_hangup <", value, "bindHangup");
            return (Criteria) this;
        }

        public Criteria andBindHangupLessThanOrEqualTo(Integer value) {
            addCriterion("bind_hangup <=", value, "bindHangup");
            return (Criteria) this;
        }

        public Criteria andBindHangupIn(List<Integer> values) {
            addCriterion("bind_hangup in", values, "bindHangup");
            return (Criteria) this;
        }

        public Criteria andBindHangupNotIn(List<Integer> values) {
            addCriterion("bind_hangup not in", values, "bindHangup");
            return (Criteria) this;
        }

        public Criteria andBindHangupBetween(Integer value1, Integer value2) {
            addCriterion("bind_hangup between", value1, value2, "bindHangup");
            return (Criteria) this;
        }

        public Criteria andBindHangupNotBetween(Integer value1, Integer value2) {
            addCriterion("bind_hangup not between", value1, value2, "bindHangup");
            return (Criteria) this;
        }

        public Criteria andCallAuthIsNull() {
            addCriterion("call_auth is null");
            return (Criteria) this;
        }

        public Criteria andCallAuthIsNotNull() {
            addCriterion("call_auth is not null");
            return (Criteria) this;
        }

        public Criteria andCallAuthEqualTo(Byte value) {
            addCriterion("call_auth =", value, "callAuth");
            return (Criteria) this;
        }

        public Criteria andCallAuthNotEqualTo(Byte value) {
            addCriterion("call_auth <>", value, "callAuth");
            return (Criteria) this;
        }

        public Criteria andCallAuthGreaterThan(Byte value) {
            addCriterion("call_auth >", value, "callAuth");
            return (Criteria) this;
        }

        public Criteria andCallAuthGreaterThanOrEqualTo(Byte value) {
            addCriterion("call_auth >=", value, "callAuth");
            return (Criteria) this;
        }

        public Criteria andCallAuthLessThan(Byte value) {
            addCriterion("call_auth <", value, "callAuth");
            return (Criteria) this;
        }

        public Criteria andCallAuthLessThanOrEqualTo(Byte value) {
            addCriterion("call_auth <=", value, "callAuth");
            return (Criteria) this;
        }

        public Criteria andCallAuthIn(List<Byte> values) {
            addCriterion("call_auth in", values, "callAuth");
            return (Criteria) this;
        }

        public Criteria andCallAuthNotIn(List<Byte> values) {
            addCriterion("call_auth not in", values, "callAuth");
            return (Criteria) this;
        }

        public Criteria andCallAuthBetween(Byte value1, Byte value2) {
            addCriterion("call_auth between", value1, value2, "callAuth");
            return (Criteria) this;
        }

        public Criteria andCallAuthNotBetween(Byte value1, Byte value2) {
            addCriterion("call_auth not between", value1, value2, "callAuth");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andFeeIsNull() {
            addCriterion("fee is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("fee is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(BigDecimal value) {
            addCriterion("fee =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(BigDecimal value) {
            addCriterion("fee <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(BigDecimal value) {
            addCriterion("fee >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fee >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(BigDecimal value) {
            addCriterion("fee <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fee <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<BigDecimal> values) {
            addCriterion("fee in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<BigDecimal> values) {
            addCriterion("fee not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andIsSettleIsNull() {
            addCriterion("is_settle is null");
            return (Criteria) this;
        }

        public Criteria andIsSettleIsNotNull() {
            addCriterion("is_settle is not null");
            return (Criteria) this;
        }

        public Criteria andIsSettleEqualTo(Byte value) {
            addCriterion("is_settle =", value, "isSettle");
            return (Criteria) this;
        }

        public Criteria andIsSettleNotEqualTo(Byte value) {
            addCriterion("is_settle <>", value, "isSettle");
            return (Criteria) this;
        }

        public Criteria andIsSettleGreaterThan(Byte value) {
            addCriterion("is_settle >", value, "isSettle");
            return (Criteria) this;
        }

        public Criteria andIsSettleGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_settle >=", value, "isSettle");
            return (Criteria) this;
        }

        public Criteria andIsSettleLessThan(Byte value) {
            addCriterion("is_settle <", value, "isSettle");
            return (Criteria) this;
        }

        public Criteria andIsSettleLessThanOrEqualTo(Byte value) {
            addCriterion("is_settle <=", value, "isSettle");
            return (Criteria) this;
        }

        public Criteria andIsSettleIn(List<Byte> values) {
            addCriterion("is_settle in", values, "isSettle");
            return (Criteria) this;
        }

        public Criteria andIsSettleNotIn(List<Byte> values) {
            addCriterion("is_settle not in", values, "isSettle");
            return (Criteria) this;
        }

        public Criteria andIsSettleBetween(Byte value1, Byte value2) {
            addCriterion("is_settle between", value1, value2, "isSettle");
            return (Criteria) this;
        }

        public Criteria andIsSettleNotBetween(Byte value1, Byte value2) {
            addCriterion("is_settle not between", value1, value2, "isSettle");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNull() {
            addCriterion("ctime is null");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNotNull() {
            addCriterion("ctime is not null");
            return (Criteria) this;
        }

        public Criteria andCtimeEqualTo(Integer value) {
            addCriterion("ctime =", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotEqualTo(Integer value) {
            addCriterion("ctime <>", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThan(Integer value) {
            addCriterion("ctime >", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ctime >=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThan(Integer value) {
            addCriterion("ctime <", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThanOrEqualTo(Integer value) {
            addCriterion("ctime <=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeIn(List<Integer> values) {
            addCriterion("ctime in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotIn(List<Integer> values) {
            addCriterion("ctime not in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeBetween(Integer value1, Integer value2) {
            addCriterion("ctime between", value1, value2, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotBetween(Integer value1, Integer value2) {
            addCriterion("ctime not between", value1, value2, "ctime");
            return (Criteria) this;
        }
    }

    /**  tableName: users   **/
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /** users **/
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