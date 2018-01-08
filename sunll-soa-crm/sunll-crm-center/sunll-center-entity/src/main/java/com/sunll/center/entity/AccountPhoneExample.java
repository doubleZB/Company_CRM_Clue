package com.sunll.center.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountPhoneExample {
    /**   tableName: account_phone   **/
    protected String orderByClause;

    /**   tableName: account_phone   **/
    protected boolean distinct;

    /**   tableName: account_phone   **/
    protected List<Criteria> oredCriteria;

    public AccountPhoneExample() {
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

    /** account_phone **/
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

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
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

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andBindFlowIsNull() {
            addCriterion("bind_flow is null");
            return (Criteria) this;
        }

        public Criteria andBindFlowIsNotNull() {
            addCriterion("bind_flow is not null");
            return (Criteria) this;
        }

        public Criteria andBindFlowEqualTo(Integer value) {
            addCriterion("bind_flow =", value, "bindFlow");
            return (Criteria) this;
        }

        public Criteria andBindFlowNotEqualTo(Integer value) {
            addCriterion("bind_flow <>", value, "bindFlow");
            return (Criteria) this;
        }

        public Criteria andBindFlowGreaterThan(Integer value) {
            addCriterion("bind_flow >", value, "bindFlow");
            return (Criteria) this;
        }

        public Criteria andBindFlowGreaterThanOrEqualTo(Integer value) {
            addCriterion("bind_flow >=", value, "bindFlow");
            return (Criteria) this;
        }

        public Criteria andBindFlowLessThan(Integer value) {
            addCriterion("bind_flow <", value, "bindFlow");
            return (Criteria) this;
        }

        public Criteria andBindFlowLessThanOrEqualTo(Integer value) {
            addCriterion("bind_flow <=", value, "bindFlow");
            return (Criteria) this;
        }

        public Criteria andBindFlowIn(List<Integer> values) {
            addCriterion("bind_flow in", values, "bindFlow");
            return (Criteria) this;
        }

        public Criteria andBindFlowNotIn(List<Integer> values) {
            addCriterion("bind_flow not in", values, "bindFlow");
            return (Criteria) this;
        }

        public Criteria andBindFlowBetween(Integer value1, Integer value2) {
            addCriterion("bind_flow between", value1, value2, "bindFlow");
            return (Criteria) this;
        }

        public Criteria andBindFlowNotBetween(Integer value1, Integer value2) {
            addCriterion("bind_flow not between", value1, value2, "bindFlow");
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

        public Criteria andStimeIsNull() {
            addCriterion("stime is null");
            return (Criteria) this;
        }

        public Criteria andStimeIsNotNull() {
            addCriterion("stime is not null");
            return (Criteria) this;
        }

        public Criteria andStimeEqualTo(Integer value) {
            addCriterion("stime =", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeNotEqualTo(Integer value) {
            addCriterion("stime <>", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeGreaterThan(Integer value) {
            addCriterion("stime >", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("stime >=", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeLessThan(Integer value) {
            addCriterion("stime <", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeLessThanOrEqualTo(Integer value) {
            addCriterion("stime <=", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeIn(List<Integer> values) {
            addCriterion("stime in", values, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeNotIn(List<Integer> values) {
            addCriterion("stime not in", values, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeBetween(Integer value1, Integer value2) {
            addCriterion("stime between", value1, value2, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeNotBetween(Integer value1, Integer value2) {
            addCriterion("stime not between", value1, value2, "stime");
            return (Criteria) this;
        }

        public Criteria andEtimeIsNull() {
            addCriterion("etime is null");
            return (Criteria) this;
        }

        public Criteria andEtimeIsNotNull() {
            addCriterion("etime is not null");
            return (Criteria) this;
        }

        public Criteria andEtimeEqualTo(Integer value) {
            addCriterion("etime =", value, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeNotEqualTo(Integer value) {
            addCriterion("etime <>", value, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeGreaterThan(Integer value) {
            addCriterion("etime >", value, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("etime >=", value, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeLessThan(Integer value) {
            addCriterion("etime <", value, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeLessThanOrEqualTo(Integer value) {
            addCriterion("etime <=", value, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeIn(List<Integer> values) {
            addCriterion("etime in", values, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeNotIn(List<Integer> values) {
            addCriterion("etime not in", values, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeBetween(Integer value1, Integer value2) {
            addCriterion("etime between", value1, value2, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeNotBetween(Integer value1, Integer value2) {
            addCriterion("etime not between", value1, value2, "etime");
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

        public Criteria andCalloutprefixIsNull() {
            addCriterion("calloutprefix is null");
            return (Criteria) this;
        }

        public Criteria andCalloutprefixIsNotNull() {
            addCriterion("calloutprefix is not null");
            return (Criteria) this;
        }

        public Criteria andCalloutprefixEqualTo(String value) {
            addCriterion("calloutprefix =", value, "calloutprefix");
            return (Criteria) this;
        }

        public Criteria andCalloutprefixNotEqualTo(String value) {
            addCriterion("calloutprefix <>", value, "calloutprefix");
            return (Criteria) this;
        }

        public Criteria andCalloutprefixGreaterThan(String value) {
            addCriterion("calloutprefix >", value, "calloutprefix");
            return (Criteria) this;
        }

        public Criteria andCalloutprefixGreaterThanOrEqualTo(String value) {
            addCriterion("calloutprefix >=", value, "calloutprefix");
            return (Criteria) this;
        }

        public Criteria andCalloutprefixLessThan(String value) {
            addCriterion("calloutprefix <", value, "calloutprefix");
            return (Criteria) this;
        }

        public Criteria andCalloutprefixLessThanOrEqualTo(String value) {
            addCriterion("calloutprefix <=", value, "calloutprefix");
            return (Criteria) this;
        }

        public Criteria andCalloutprefixLike(String value) {
            addCriterion("calloutprefix like", value, "calloutprefix");
            return (Criteria) this;
        }

        public Criteria andCalloutprefixNotLike(String value) {
            addCriterion("calloutprefix not like", value, "calloutprefix");
            return (Criteria) this;
        }

        public Criteria andCalloutprefixIn(List<String> values) {
            addCriterion("calloutprefix in", values, "calloutprefix");
            return (Criteria) this;
        }

        public Criteria andCalloutprefixNotIn(List<String> values) {
            addCriterion("calloutprefix not in", values, "calloutprefix");
            return (Criteria) this;
        }

        public Criteria andCalloutprefixBetween(String value1, String value2) {
            addCriterion("calloutprefix between", value1, value2, "calloutprefix");
            return (Criteria) this;
        }

        public Criteria andCalloutprefixNotBetween(String value1, String value2) {
            addCriterion("calloutprefix not between", value1, value2, "calloutprefix");
            return (Criteria) this;
        }

        public Criteria andIstransparentIsNull() {
            addCriterion("istransparent is null");
            return (Criteria) this;
        }

        public Criteria andIstransparentIsNotNull() {
            addCriterion("istransparent is not null");
            return (Criteria) this;
        }

        public Criteria andIstransparentEqualTo(Byte value) {
            addCriterion("istransparent =", value, "istransparent");
            return (Criteria) this;
        }

        public Criteria andIstransparentNotEqualTo(Byte value) {
            addCriterion("istransparent <>", value, "istransparent");
            return (Criteria) this;
        }

        public Criteria andIstransparentGreaterThan(Byte value) {
            addCriterion("istransparent >", value, "istransparent");
            return (Criteria) this;
        }

        public Criteria andIstransparentGreaterThanOrEqualTo(Byte value) {
            addCriterion("istransparent >=", value, "istransparent");
            return (Criteria) this;
        }

        public Criteria andIstransparentLessThan(Byte value) {
            addCriterion("istransparent <", value, "istransparent");
            return (Criteria) this;
        }

        public Criteria andIstransparentLessThanOrEqualTo(Byte value) {
            addCriterion("istransparent <=", value, "istransparent");
            return (Criteria) this;
        }

        public Criteria andIstransparentIn(List<Byte> values) {
            addCriterion("istransparent in", values, "istransparent");
            return (Criteria) this;
        }

        public Criteria andIstransparentNotIn(List<Byte> values) {
            addCriterion("istransparent not in", values, "istransparent");
            return (Criteria) this;
        }

        public Criteria andIstransparentBetween(Byte value1, Byte value2) {
            addCriterion("istransparent between", value1, value2, "istransparent");
            return (Criteria) this;
        }

        public Criteria andIstransparentNotBetween(Byte value1, Byte value2) {
            addCriterion("istransparent not between", value1, value2, "istransparent");
            return (Criteria) this;
        }

        public Criteria andTransfercallerIsNull() {
            addCriterion("transfercaller is null");
            return (Criteria) this;
        }

        public Criteria andTransfercallerIsNotNull() {
            addCriterion("transfercaller is not null");
            return (Criteria) this;
        }

        public Criteria andTransfercallerEqualTo(String value) {
            addCriterion("transfercaller =", value, "transfercaller");
            return (Criteria) this;
        }

        public Criteria andTransfercallerNotEqualTo(String value) {
            addCriterion("transfercaller <>", value, "transfercaller");
            return (Criteria) this;
        }

        public Criteria andTransfercallerGreaterThan(String value) {
            addCriterion("transfercaller >", value, "transfercaller");
            return (Criteria) this;
        }

        public Criteria andTransfercallerGreaterThanOrEqualTo(String value) {
            addCriterion("transfercaller >=", value, "transfercaller");
            return (Criteria) this;
        }

        public Criteria andTransfercallerLessThan(String value) {
            addCriterion("transfercaller <", value, "transfercaller");
            return (Criteria) this;
        }

        public Criteria andTransfercallerLessThanOrEqualTo(String value) {
            addCriterion("transfercaller <=", value, "transfercaller");
            return (Criteria) this;
        }

        public Criteria andTransfercallerLike(String value) {
            addCriterion("transfercaller like", value, "transfercaller");
            return (Criteria) this;
        }

        public Criteria andTransfercallerNotLike(String value) {
            addCriterion("transfercaller not like", value, "transfercaller");
            return (Criteria) this;
        }

        public Criteria andTransfercallerIn(List<String> values) {
            addCriterion("transfercaller in", values, "transfercaller");
            return (Criteria) this;
        }

        public Criteria andTransfercallerNotIn(List<String> values) {
            addCriterion("transfercaller not in", values, "transfercaller");
            return (Criteria) this;
        }

        public Criteria andTransfercallerBetween(String value1, String value2) {
            addCriterion("transfercaller between", value1, value2, "transfercaller");
            return (Criteria) this;
        }

        public Criteria andTransfercallerNotBetween(String value1, String value2) {
            addCriterion("transfercaller not between", value1, value2, "transfercaller");
            return (Criteria) this;
        }

        public Criteria andChargeModeIsNull() {
            addCriterion("charge_mode is null");
            return (Criteria) this;
        }

        public Criteria andChargeModeIsNotNull() {
            addCriterion("charge_mode is not null");
            return (Criteria) this;
        }

        public Criteria andChargeModeEqualTo(Byte value) {
            addCriterion("charge_mode =", value, "chargeMode");
            return (Criteria) this;
        }

        public Criteria andChargeModeNotEqualTo(Byte value) {
            addCriterion("charge_mode <>", value, "chargeMode");
            return (Criteria) this;
        }

        public Criteria andChargeModeGreaterThan(Byte value) {
            addCriterion("charge_mode >", value, "chargeMode");
            return (Criteria) this;
        }

        public Criteria andChargeModeGreaterThanOrEqualTo(Byte value) {
            addCriterion("charge_mode >=", value, "chargeMode");
            return (Criteria) this;
        }

        public Criteria andChargeModeLessThan(Byte value) {
            addCriterion("charge_mode <", value, "chargeMode");
            return (Criteria) this;
        }

        public Criteria andChargeModeLessThanOrEqualTo(Byte value) {
            addCriterion("charge_mode <=", value, "chargeMode");
            return (Criteria) this;
        }

        public Criteria andChargeModeIn(List<Byte> values) {
            addCriterion("charge_mode in", values, "chargeMode");
            return (Criteria) this;
        }

        public Criteria andChargeModeNotIn(List<Byte> values) {
            addCriterion("charge_mode not in", values, "chargeMode");
            return (Criteria) this;
        }

        public Criteria andChargeModeBetween(Byte value1, Byte value2) {
            addCriterion("charge_mode between", value1, value2, "chargeMode");
            return (Criteria) this;
        }

        public Criteria andChargeModeNotBetween(Byte value1, Byte value2) {
            addCriterion("charge_mode not between", value1, value2, "chargeMode");
            return (Criteria) this;
        }
    }

    /**  tableName: account_phone   **/
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /** account_phone **/
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