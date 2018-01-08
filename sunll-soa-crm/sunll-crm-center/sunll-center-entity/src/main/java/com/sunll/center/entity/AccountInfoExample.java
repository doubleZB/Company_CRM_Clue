package com.sunll.center.entity;

import java.util.ArrayList;
import java.util.List;

public class AccountInfoExample {
    /**   tableName: account_info   **/
    protected String orderByClause;

    /**   tableName: account_info   **/
    protected boolean distinct;

    /**   tableName: account_info   **/
    protected List<Criteria> oredCriteria;

    public AccountInfoExample() {
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

    /** account_info **/
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

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andContactIsNull() {
            addCriterion("contact is null");
            return (Criteria) this;
        }

        public Criteria andContactIsNotNull() {
            addCriterion("contact is not null");
            return (Criteria) this;
        }

        public Criteria andContactEqualTo(String value) {
            addCriterion("contact =", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotEqualTo(String value) {
            addCriterion("contact <>", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThan(String value) {
            addCriterion("contact >", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThanOrEqualTo(String value) {
            addCriterion("contact >=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThan(String value) {
            addCriterion("contact <", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThanOrEqualTo(String value) {
            addCriterion("contact <=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLike(String value) {
            addCriterion("contact like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotLike(String value) {
            addCriterion("contact not like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactIn(List<String> values) {
            addCriterion("contact in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotIn(List<String> values) {
            addCriterion("contact not in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactBetween(String value1, String value2) {
            addCriterion("contact between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotBetween(String value1, String value2) {
            addCriterion("contact not between", value1, value2, "contact");
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

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andZipIsNull() {
            addCriterion("zip is null");
            return (Criteria) this;
        }

        public Criteria andZipIsNotNull() {
            addCriterion("zip is not null");
            return (Criteria) this;
        }

        public Criteria andZipEqualTo(String value) {
            addCriterion("zip =", value, "zip");
            return (Criteria) this;
        }

        public Criteria andZipNotEqualTo(String value) {
            addCriterion("zip <>", value, "zip");
            return (Criteria) this;
        }

        public Criteria andZipGreaterThan(String value) {
            addCriterion("zip >", value, "zip");
            return (Criteria) this;
        }

        public Criteria andZipGreaterThanOrEqualTo(String value) {
            addCriterion("zip >=", value, "zip");
            return (Criteria) this;
        }

        public Criteria andZipLessThan(String value) {
            addCriterion("zip <", value, "zip");
            return (Criteria) this;
        }

        public Criteria andZipLessThanOrEqualTo(String value) {
            addCriterion("zip <=", value, "zip");
            return (Criteria) this;
        }

        public Criteria andZipLike(String value) {
            addCriterion("zip like", value, "zip");
            return (Criteria) this;
        }

        public Criteria andZipNotLike(String value) {
            addCriterion("zip not like", value, "zip");
            return (Criteria) this;
        }

        public Criteria andZipIn(List<String> values) {
            addCriterion("zip in", values, "zip");
            return (Criteria) this;
        }

        public Criteria andZipNotIn(List<String> values) {
            addCriterion("zip not in", values, "zip");
            return (Criteria) this;
        }

        public Criteria andZipBetween(String value1, String value2) {
            addCriterion("zip between", value1, value2, "zip");
            return (Criteria) this;
        }

        public Criteria andZipNotBetween(String value1, String value2) {
            addCriterion("zip not between", value1, value2, "zip");
            return (Criteria) this;
        }

        public Criteria andBindGroupIsNull() {
            addCriterion("bind_group is null");
            return (Criteria) this;
        }

        public Criteria andBindGroupIsNotNull() {
            addCriterion("bind_group is not null");
            return (Criteria) this;
        }

        public Criteria andBindGroupEqualTo(Integer value) {
            addCriterion("bind_group =", value, "bindGroup");
            return (Criteria) this;
        }

        public Criteria andBindGroupNotEqualTo(Integer value) {
            addCriterion("bind_group <>", value, "bindGroup");
            return (Criteria) this;
        }

        public Criteria andBindGroupGreaterThan(Integer value) {
            addCriterion("bind_group >", value, "bindGroup");
            return (Criteria) this;
        }

        public Criteria andBindGroupGreaterThanOrEqualTo(Integer value) {
            addCriterion("bind_group >=", value, "bindGroup");
            return (Criteria) this;
        }

        public Criteria andBindGroupLessThan(Integer value) {
            addCriterion("bind_group <", value, "bindGroup");
            return (Criteria) this;
        }

        public Criteria andBindGroupLessThanOrEqualTo(Integer value) {
            addCriterion("bind_group <=", value, "bindGroup");
            return (Criteria) this;
        }

        public Criteria andBindGroupIn(List<Integer> values) {
            addCriterion("bind_group in", values, "bindGroup");
            return (Criteria) this;
        }

        public Criteria andBindGroupNotIn(List<Integer> values) {
            addCriterion("bind_group not in", values, "bindGroup");
            return (Criteria) this;
        }

        public Criteria andBindGroupBetween(Integer value1, Integer value2) {
            addCriterion("bind_group between", value1, value2, "bindGroup");
            return (Criteria) this;
        }

        public Criteria andBindGroupNotBetween(Integer value1, Integer value2) {
            addCriterion("bind_group not between", value1, value2, "bindGroup");
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

        public Criteria andBindPartnerIsNull() {
            addCriterion("bind_partner is null");
            return (Criteria) this;
        }

        public Criteria andBindPartnerIsNotNull() {
            addCriterion("bind_partner is not null");
            return (Criteria) this;
        }

        public Criteria andBindPartnerEqualTo(Integer value) {
            addCriterion("bind_partner =", value, "bindPartner");
            return (Criteria) this;
        }

        public Criteria andBindPartnerNotEqualTo(Integer value) {
            addCriterion("bind_partner <>", value, "bindPartner");
            return (Criteria) this;
        }

        public Criteria andBindPartnerGreaterThan(Integer value) {
            addCriterion("bind_partner >", value, "bindPartner");
            return (Criteria) this;
        }

        public Criteria andBindPartnerGreaterThanOrEqualTo(Integer value) {
            addCriterion("bind_partner >=", value, "bindPartner");
            return (Criteria) this;
        }

        public Criteria andBindPartnerLessThan(Integer value) {
            addCriterion("bind_partner <", value, "bindPartner");
            return (Criteria) this;
        }

        public Criteria andBindPartnerLessThanOrEqualTo(Integer value) {
            addCriterion("bind_partner <=", value, "bindPartner");
            return (Criteria) this;
        }

        public Criteria andBindPartnerIn(List<Integer> values) {
            addCriterion("bind_partner in", values, "bindPartner");
            return (Criteria) this;
        }

        public Criteria andBindPartnerNotIn(List<Integer> values) {
            addCriterion("bind_partner not in", values, "bindPartner");
            return (Criteria) this;
        }

        public Criteria andBindPartnerBetween(Integer value1, Integer value2) {
            addCriterion("bind_partner between", value1, value2, "bindPartner");
            return (Criteria) this;
        }

        public Criteria andBindPartnerNotBetween(Integer value1, Integer value2) {
            addCriterion("bind_partner not between", value1, value2, "bindPartner");
            return (Criteria) this;
        }

        public Criteria andBindRateIsNull() {
            addCriterion("bind_rate is null");
            return (Criteria) this;
        }

        public Criteria andBindRateIsNotNull() {
            addCriterion("bind_rate is not null");
            return (Criteria) this;
        }

        public Criteria andBindRateEqualTo(Integer value) {
            addCriterion("bind_rate =", value, "bindRate");
            return (Criteria) this;
        }

        public Criteria andBindRateNotEqualTo(Integer value) {
            addCriterion("bind_rate <>", value, "bindRate");
            return (Criteria) this;
        }

        public Criteria andBindRateGreaterThan(Integer value) {
            addCriterion("bind_rate >", value, "bindRate");
            return (Criteria) this;
        }

        public Criteria andBindRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("bind_rate >=", value, "bindRate");
            return (Criteria) this;
        }

        public Criteria andBindRateLessThan(Integer value) {
            addCriterion("bind_rate <", value, "bindRate");
            return (Criteria) this;
        }

        public Criteria andBindRateLessThanOrEqualTo(Integer value) {
            addCriterion("bind_rate <=", value, "bindRate");
            return (Criteria) this;
        }

        public Criteria andBindRateIn(List<Integer> values) {
            addCriterion("bind_rate in", values, "bindRate");
            return (Criteria) this;
        }

        public Criteria andBindRateNotIn(List<Integer> values) {
            addCriterion("bind_rate not in", values, "bindRate");
            return (Criteria) this;
        }

        public Criteria andBindRateBetween(Integer value1, Integer value2) {
            addCriterion("bind_rate between", value1, value2, "bindRate");
            return (Criteria) this;
        }

        public Criteria andBindRateNotBetween(Integer value1, Integer value2) {
            addCriterion("bind_rate not between", value1, value2, "bindRate");
            return (Criteria) this;
        }

        public Criteria andBindDiscountIsNull() {
            addCriterion("bind_discount is null");
            return (Criteria) this;
        }

        public Criteria andBindDiscountIsNotNull() {
            addCriterion("bind_discount is not null");
            return (Criteria) this;
        }

        public Criteria andBindDiscountEqualTo(Integer value) {
            addCriterion("bind_discount =", value, "bindDiscount");
            return (Criteria) this;
        }

        public Criteria andBindDiscountNotEqualTo(Integer value) {
            addCriterion("bind_discount <>", value, "bindDiscount");
            return (Criteria) this;
        }

        public Criteria andBindDiscountGreaterThan(Integer value) {
            addCriterion("bind_discount >", value, "bindDiscount");
            return (Criteria) this;
        }

        public Criteria andBindDiscountGreaterThanOrEqualTo(Integer value) {
            addCriterion("bind_discount >=", value, "bindDiscount");
            return (Criteria) this;
        }

        public Criteria andBindDiscountLessThan(Integer value) {
            addCriterion("bind_discount <", value, "bindDiscount");
            return (Criteria) this;
        }

        public Criteria andBindDiscountLessThanOrEqualTo(Integer value) {
            addCriterion("bind_discount <=", value, "bindDiscount");
            return (Criteria) this;
        }

        public Criteria andBindDiscountIn(List<Integer> values) {
            addCriterion("bind_discount in", values, "bindDiscount");
            return (Criteria) this;
        }

        public Criteria andBindDiscountNotIn(List<Integer> values) {
            addCriterion("bind_discount not in", values, "bindDiscount");
            return (Criteria) this;
        }

        public Criteria andBindDiscountBetween(Integer value1, Integer value2) {
            addCriterion("bind_discount between", value1, value2, "bindDiscount");
            return (Criteria) this;
        }

        public Criteria andBindDiscountNotBetween(Integer value1, Integer value2) {
            addCriterion("bind_discount not between", value1, value2, "bindDiscount");
            return (Criteria) this;
        }

        public Criteria andBindRateSmsIsNull() {
            addCriterion("bind_rate_sms is null");
            return (Criteria) this;
        }

        public Criteria andBindRateSmsIsNotNull() {
            addCriterion("bind_rate_sms is not null");
            return (Criteria) this;
        }

        public Criteria andBindRateSmsEqualTo(Integer value) {
            addCriterion("bind_rate_sms =", value, "bindRateSms");
            return (Criteria) this;
        }

        public Criteria andBindRateSmsNotEqualTo(Integer value) {
            addCriterion("bind_rate_sms <>", value, "bindRateSms");
            return (Criteria) this;
        }

        public Criteria andBindRateSmsGreaterThan(Integer value) {
            addCriterion("bind_rate_sms >", value, "bindRateSms");
            return (Criteria) this;
        }

        public Criteria andBindRateSmsGreaterThanOrEqualTo(Integer value) {
            addCriterion("bind_rate_sms >=", value, "bindRateSms");
            return (Criteria) this;
        }

        public Criteria andBindRateSmsLessThan(Integer value) {
            addCriterion("bind_rate_sms <", value, "bindRateSms");
            return (Criteria) this;
        }

        public Criteria andBindRateSmsLessThanOrEqualTo(Integer value) {
            addCriterion("bind_rate_sms <=", value, "bindRateSms");
            return (Criteria) this;
        }

        public Criteria andBindRateSmsIn(List<Integer> values) {
            addCriterion("bind_rate_sms in", values, "bindRateSms");
            return (Criteria) this;
        }

        public Criteria andBindRateSmsNotIn(List<Integer> values) {
            addCriterion("bind_rate_sms not in", values, "bindRateSms");
            return (Criteria) this;
        }

        public Criteria andBindRateSmsBetween(Integer value1, Integer value2) {
            addCriterion("bind_rate_sms between", value1, value2, "bindRateSms");
            return (Criteria) this;
        }

        public Criteria andBindRateSmsNotBetween(Integer value1, Integer value2) {
            addCriterion("bind_rate_sms not between", value1, value2, "bindRateSms");
            return (Criteria) this;
        }

        public Criteria andCorpIdiographIsNull() {
            addCriterion("corp_idiograph is null");
            return (Criteria) this;
        }

        public Criteria andCorpIdiographIsNotNull() {
            addCriterion("corp_idiograph is not null");
            return (Criteria) this;
        }

        public Criteria andCorpIdiographEqualTo(String value) {
            addCriterion("corp_idiograph =", value, "corpIdiograph");
            return (Criteria) this;
        }

        public Criteria andCorpIdiographNotEqualTo(String value) {
            addCriterion("corp_idiograph <>", value, "corpIdiograph");
            return (Criteria) this;
        }

        public Criteria andCorpIdiographGreaterThan(String value) {
            addCriterion("corp_idiograph >", value, "corpIdiograph");
            return (Criteria) this;
        }

        public Criteria andCorpIdiographGreaterThanOrEqualTo(String value) {
            addCriterion("corp_idiograph >=", value, "corpIdiograph");
            return (Criteria) this;
        }

        public Criteria andCorpIdiographLessThan(String value) {
            addCriterion("corp_idiograph <", value, "corpIdiograph");
            return (Criteria) this;
        }

        public Criteria andCorpIdiographLessThanOrEqualTo(String value) {
            addCriterion("corp_idiograph <=", value, "corpIdiograph");
            return (Criteria) this;
        }

        public Criteria andCorpIdiographLike(String value) {
            addCriterion("corp_idiograph like", value, "corpIdiograph");
            return (Criteria) this;
        }

        public Criteria andCorpIdiographNotLike(String value) {
            addCriterion("corp_idiograph not like", value, "corpIdiograph");
            return (Criteria) this;
        }

        public Criteria andCorpIdiographIn(List<String> values) {
            addCriterion("corp_idiograph in", values, "corpIdiograph");
            return (Criteria) this;
        }

        public Criteria andCorpIdiographNotIn(List<String> values) {
            addCriterion("corp_idiograph not in", values, "corpIdiograph");
            return (Criteria) this;
        }

        public Criteria andCorpIdiographBetween(String value1, String value2) {
            addCriterion("corp_idiograph between", value1, value2, "corpIdiograph");
            return (Criteria) this;
        }

        public Criteria andCorpIdiographNotBetween(String value1, String value2) {
            addCriterion("corp_idiograph not between", value1, value2, "corpIdiograph");
            return (Criteria) this;
        }

        public Criteria andBindSmsCodelistIsNull() {
            addCriterion("bind_sms_codelist is null");
            return (Criteria) this;
        }

        public Criteria andBindSmsCodelistIsNotNull() {
            addCriterion("bind_sms_codelist is not null");
            return (Criteria) this;
        }

        public Criteria andBindSmsCodelistEqualTo(Integer value) {
            addCriterion("bind_sms_codelist =", value, "bindSmsCodelist");
            return (Criteria) this;
        }

        public Criteria andBindSmsCodelistNotEqualTo(Integer value) {
            addCriterion("bind_sms_codelist <>", value, "bindSmsCodelist");
            return (Criteria) this;
        }

        public Criteria andBindSmsCodelistGreaterThan(Integer value) {
            addCriterion("bind_sms_codelist >", value, "bindSmsCodelist");
            return (Criteria) this;
        }

        public Criteria andBindSmsCodelistGreaterThanOrEqualTo(Integer value) {
            addCriterion("bind_sms_codelist >=", value, "bindSmsCodelist");
            return (Criteria) this;
        }

        public Criteria andBindSmsCodelistLessThan(Integer value) {
            addCriterion("bind_sms_codelist <", value, "bindSmsCodelist");
            return (Criteria) this;
        }

        public Criteria andBindSmsCodelistLessThanOrEqualTo(Integer value) {
            addCriterion("bind_sms_codelist <=", value, "bindSmsCodelist");
            return (Criteria) this;
        }

        public Criteria andBindSmsCodelistIn(List<Integer> values) {
            addCriterion("bind_sms_codelist in", values, "bindSmsCodelist");
            return (Criteria) this;
        }

        public Criteria andBindSmsCodelistNotIn(List<Integer> values) {
            addCriterion("bind_sms_codelist not in", values, "bindSmsCodelist");
            return (Criteria) this;
        }

        public Criteria andBindSmsCodelistBetween(Integer value1, Integer value2) {
            addCriterion("bind_sms_codelist between", value1, value2, "bindSmsCodelist");
            return (Criteria) this;
        }

        public Criteria andBindSmsCodelistNotBetween(Integer value1, Integer value2) {
            addCriterion("bind_sms_codelist not between", value1, value2, "bindSmsCodelist");
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

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
            addCriterion("pid not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andFinalpayIsNull() {
            addCriterion("finalpay is null");
            return (Criteria) this;
        }

        public Criteria andFinalpayIsNotNull() {
            addCriterion("finalpay is not null");
            return (Criteria) this;
        }

        public Criteria andFinalpayEqualTo(Byte value) {
            addCriterion("finalpay =", value, "finalpay");
            return (Criteria) this;
        }

        public Criteria andFinalpayNotEqualTo(Byte value) {
            addCriterion("finalpay <>", value, "finalpay");
            return (Criteria) this;
        }

        public Criteria andFinalpayGreaterThan(Byte value) {
            addCriterion("finalpay >", value, "finalpay");
            return (Criteria) this;
        }

        public Criteria andFinalpayGreaterThanOrEqualTo(Byte value) {
            addCriterion("finalpay >=", value, "finalpay");
            return (Criteria) this;
        }

        public Criteria andFinalpayLessThan(Byte value) {
            addCriterion("finalpay <", value, "finalpay");
            return (Criteria) this;
        }

        public Criteria andFinalpayLessThanOrEqualTo(Byte value) {
            addCriterion("finalpay <=", value, "finalpay");
            return (Criteria) this;
        }

        public Criteria andFinalpayIn(List<Byte> values) {
            addCriterion("finalpay in", values, "finalpay");
            return (Criteria) this;
        }

        public Criteria andFinalpayNotIn(List<Byte> values) {
            addCriterion("finalpay not in", values, "finalpay");
            return (Criteria) this;
        }

        public Criteria andFinalpayBetween(Byte value1, Byte value2) {
            addCriterion("finalpay between", value1, value2, "finalpay");
            return (Criteria) this;
        }

        public Criteria andFinalpayNotBetween(Byte value1, Byte value2) {
            addCriterion("finalpay not between", value1, value2, "finalpay");
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

    /**  tableName: account_info   **/
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /** account_info **/
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