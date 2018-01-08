package com.sunll.center.entity;

import com.github.pagehelper.StringUtil;
import org.apache.commons.lang.math.NumberUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BillExample {
    /**   tableName: bill   **/
    protected String orderByClause;

    /**   tableName: bill   **/
    protected boolean distinct;

    /**   tableName: bill   **/
    protected List<Criteria> oredCriteria;

    public BillExample() {
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

    /** bill **/
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

        public Criteria andAccountIdIsNull() {
            addCriterion("account_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("account_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(Integer value) {
            addCriterion("account_id =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(Integer value) {
            addCriterion("account_id <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(Integer value) {
            addCriterion("account_id >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("account_id >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(Integer value) {
            addCriterion("account_id <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(Integer value) {
            addCriterion("account_id <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<Integer> values) {
            addCriterion("account_id in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<Integer> values) {
            addCriterion("account_id not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(Integer value1, Integer value2) {
            addCriterion("account_id between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(Integer value1, Integer value2) {
            addCriterion("account_id not between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountPhoneIsNull() {
            addCriterion("account_phone is null");
            return (Criteria) this;
        }

        public Criteria andAccountPhoneIsNotNull() {
            addCriterion("account_phone is not null");
            return (Criteria) this;
        }

        public Criteria andAccountPhoneEqualTo(String value) {
            addCriterion("account_phone =", value, "accountPhone");
            return (Criteria) this;
        }

        public Criteria andAccountPhoneNotEqualTo(String value) {
            addCriterion("account_phone <>", value, "accountPhone");
            return (Criteria) this;
        }

        public Criteria andAccountPhoneGreaterThan(String value) {
            addCriterion("account_phone >", value, "accountPhone");
            return (Criteria) this;
        }

        public Criteria andAccountPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("account_phone >=", value, "accountPhone");
            return (Criteria) this;
        }

        public Criteria andAccountPhoneLessThan(String value) {
            addCriterion("account_phone <", value, "accountPhone");
            return (Criteria) this;
        }

        public Criteria andAccountPhoneLessThanOrEqualTo(String value) {
            addCriterion("account_phone <=", value, "accountPhone");
            return (Criteria) this;
        }

        public Criteria andAccountPhoneLike(String value) {
            addCriterion("account_phone like", value, "accountPhone");
            return (Criteria) this;
        }

        public Criteria andAccountPhoneNotLike(String value) {
            addCriterion("account_phone not like", value, "accountPhone");
            return (Criteria) this;
        }

        public Criteria andAccountPhoneIn(List<String> values) {
            addCriterion("account_phone in", values, "accountPhone");
            return (Criteria) this;
        }

        public Criteria andAccountPhoneNotIn(List<String> values) {
            addCriterion("account_phone not in", values, "accountPhone");
            return (Criteria) this;
        }

        public Criteria andAccountPhoneBetween(String value1, String value2) {
            addCriterion("account_phone between", value1, value2, "accountPhone");
            return (Criteria) this;
        }

        public Criteria andAccountPhoneNotBetween(String value1, String value2) {
            addCriterion("account_phone not between", value1, value2, "accountPhone");
            return (Criteria) this;
        }

        public Criteria andCallerIdNumberIsNull() {
            addCriterion("caller_id_number is null");
            return (Criteria) this;
        }

        public Criteria andCallerIdNumberIsNotNull() {
            addCriterion("caller_id_number is not null");
            return (Criteria) this;
        }

        public Criteria andCallerIdNumberEqualTo(String value) {
            addCriterion("caller_id_number =", value, "callerIdNumber");
            return (Criteria) this;
        }

        //add by szq for zhangkexin
        public Criteria multiColumnOrClause(String value1) {
            addCriterion("( caller_id_number = " + value1 + " OR destination_number = " + value1+" )");
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
                            cond = cond + " caller_id_number = " + accountMobile + " OR destination_number = " + accountMobile;
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

        public Criteria andCallerIdNumberNotEqualTo(String value) {
            addCriterion("caller_id_number <>", value, "callerIdNumber");
            return (Criteria) this;
        }

        public Criteria andCallerIdNumberGreaterThan(String value) {
            addCriterion("caller_id_number >", value, "callerIdNumber");
            return (Criteria) this;
        }

        public Criteria andCallerIdNumberGreaterThanOrEqualTo(String value) {
            addCriterion("caller_id_number >=", value, "callerIdNumber");
            return (Criteria) this;
        }

        public Criteria andCallerIdNumberLessThan(String value) {
            addCriterion("caller_id_number <", value, "callerIdNumber");
            return (Criteria) this;
        }

        public Criteria andCallerIdNumberLessThanOrEqualTo(String value) {
            addCriterion("caller_id_number <=", value, "callerIdNumber");
            return (Criteria) this;
        }

        public Criteria andCallerIdNumberLike(String value) {
            addCriterion("caller_id_number like", value, "callerIdNumber");
            return (Criteria) this;
        }

        public Criteria andCallerIdNumberNotLike(String value) {
            addCriterion("caller_id_number not like", value, "callerIdNumber");
            return (Criteria) this;
        }

        public Criteria andCallerIdNumberIn(List<String> values) {
            addCriterion("caller_id_number in", values, "callerIdNumber");
            return (Criteria) this;
        }

        public Criteria andCallerIdNumberNotIn(List<String> values) {
            addCriterion("caller_id_number not in", values, "callerIdNumber");
            return (Criteria) this;
        }

        public Criteria andCallerIdNumberBetween(String value1, String value2) {
            addCriterion("caller_id_number between", value1, value2, "callerIdNumber");
            return (Criteria) this;
        }

        public Criteria andCallerIdNumberNotBetween(String value1, String value2) {
            addCriterion("caller_id_number not between", value1, value2, "callerIdNumber");
            return (Criteria) this;
        }

        public Criteria andDestinationNumberIsNull() {
            addCriterion("destination_number is null");
            return (Criteria) this;
        }

        public Criteria andDestinationNumberIsNotNull() {
            addCriterion("destination_number is not null");
            return (Criteria) this;
        }

        public Criteria andDestinationNumberEqualTo(String value) {
            addCriterion("destination_number =", value, "destinationNumber");
            return (Criteria) this;
        }

        public Criteria andDestinationNumberNotEqualTo(String value) {
            addCriterion("destination_number <>", value, "destinationNumber");
            return (Criteria) this;
        }

        public Criteria andDestinationNumberGreaterThan(String value) {
            addCriterion("destination_number >", value, "destinationNumber");
            return (Criteria) this;
        }

        public Criteria andDestinationNumberGreaterThanOrEqualTo(String value) {
            addCriterion("destination_number >=", value, "destinationNumber");
            return (Criteria) this;
        }

        public Criteria andDestinationNumberLessThan(String value) {
            addCriterion("destination_number <", value, "destinationNumber");
            return (Criteria) this;
        }

        public Criteria andDestinationNumberLessThanOrEqualTo(String value) {
            addCriterion("destination_number <=", value, "destinationNumber");
            return (Criteria) this;
        }

        public Criteria andDestinationNumberLike(String value) {
            addCriterion("destination_number like", value, "destinationNumber");
            return (Criteria) this;
        }

        public Criteria andDestinationNumberNotLike(String value) {
            addCriterion("destination_number not like", value, "destinationNumber");
            return (Criteria) this;
        }

        public Criteria andDestinationNumberIn(List<String> values) {
            addCriterion("destination_number in", values, "destinationNumber");
            return (Criteria) this;
        }

        public Criteria andDestinationNumberNotIn(List<String> values) {
            addCriterion("destination_number not in", values, "destinationNumber");
            return (Criteria) this;
        }

        public Criteria andDestinationNumberBetween(String value1, String value2) {
            addCriterion("destination_number between", value1, value2, "destinationNumber");
            return (Criteria) this;
        }

        public Criteria andDestinationNumberNotBetween(String value1, String value2) {
            addCriterion("destination_number not between", value1, value2, "destinationNumber");
            return (Criteria) this;
        }

        public Criteria andOriCallerIsNull() {
            addCriterion("ori_caller is null");
            return (Criteria) this;
        }

        public Criteria andOriCallerIsNotNull() {
            addCriterion("ori_caller is not null");
            return (Criteria) this;
        }

        public Criteria andOriCallerEqualTo(String value) {
            addCriterion("ori_caller =", value, "oriCaller");
            return (Criteria) this;
        }

        public Criteria andOriCallerNotEqualTo(String value) {
            addCriterion("ori_caller <>", value, "oriCaller");
            return (Criteria) this;
        }

        public Criteria andOriCallerGreaterThan(String value) {
            addCriterion("ori_caller >", value, "oriCaller");
            return (Criteria) this;
        }

        public Criteria andOriCallerGreaterThanOrEqualTo(String value) {
            addCriterion("ori_caller >=", value, "oriCaller");
            return (Criteria) this;
        }

        public Criteria andOriCallerLessThan(String value) {
            addCriterion("ori_caller <", value, "oriCaller");
            return (Criteria) this;
        }

        public Criteria andOriCallerLessThanOrEqualTo(String value) {
            addCriterion("ori_caller <=", value, "oriCaller");
            return (Criteria) this;
        }

        public Criteria andOriCallerLike(String value) {
            addCriterion("ori_caller like", value, "oriCaller");
            return (Criteria) this;
        }

        public Criteria andOriCallerNotLike(String value) {
            addCriterion("ori_caller not like", value, "oriCaller");
            return (Criteria) this;
        }

        public Criteria andOriCallerIn(List<String> values) {
            addCriterion("ori_caller in", values, "oriCaller");
            return (Criteria) this;
        }

        public Criteria andOriCallerNotIn(List<String> values) {
            addCriterion("ori_caller not in", values, "oriCaller");
            return (Criteria) this;
        }

        public Criteria andOriCallerBetween(String value1, String value2) {
            addCriterion("ori_caller between", value1, value2, "oriCaller");
            return (Criteria) this;
        }

        public Criteria andOriCallerNotBetween(String value1, String value2) {
            addCriterion("ori_caller not between", value1, value2, "oriCaller");
            return (Criteria) this;
        }

        public Criteria andOriDestinationIsNull() {
            addCriterion("ori_destination is null");
            return (Criteria) this;
        }

        public Criteria andOriDestinationIsNotNull() {
            addCriterion("ori_destination is not null");
            return (Criteria) this;
        }

        public Criteria andOriDestinationEqualTo(String value) {
            addCriterion("ori_destination =", value, "oriDestination");
            return (Criteria) this;
        }

        public Criteria andOriDestinationNotEqualTo(String value) {
            addCriterion("ori_destination <>", value, "oriDestination");
            return (Criteria) this;
        }

        public Criteria andOriDestinationGreaterThan(String value) {
            addCriterion("ori_destination >", value, "oriDestination");
            return (Criteria) this;
        }

        public Criteria andOriDestinationGreaterThanOrEqualTo(String value) {
            addCriterion("ori_destination >=", value, "oriDestination");
            return (Criteria) this;
        }

        public Criteria andOriDestinationLessThan(String value) {
            addCriterion("ori_destination <", value, "oriDestination");
            return (Criteria) this;
        }

        public Criteria andOriDestinationLessThanOrEqualTo(String value) {
            addCriterion("ori_destination <=", value, "oriDestination");
            return (Criteria) this;
        }

        public Criteria andOriDestinationLike(String value) {
            addCriterion("ori_destination like", value, "oriDestination");
            return (Criteria) this;
        }

        public Criteria andOriDestinationNotLike(String value) {
            addCriterion("ori_destination not like", value, "oriDestination");
            return (Criteria) this;
        }

        public Criteria andOriDestinationIn(List<String> values) {
            addCriterion("ori_destination in", values, "oriDestination");
            return (Criteria) this;
        }

        public Criteria andOriDestinationNotIn(List<String> values) {
            addCriterion("ori_destination not in", values, "oriDestination");
            return (Criteria) this;
        }

        public Criteria andOriDestinationBetween(String value1, String value2) {
            addCriterion("ori_destination between", value1, value2, "oriDestination");
            return (Criteria) this;
        }

        public Criteria andOriDestinationNotBetween(String value1, String value2) {
            addCriterion("ori_destination not between", value1, value2, "oriDestination");
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

        public Criteria andStartStampEqualTo(Date value) {
            addCriterion("start_stamp =", value, "startStamp");
            return (Criteria) this;
        }

        public Criteria andStartStampNotEqualTo(Date value) {
            addCriterion("start_stamp <>", value, "startStamp");
            return (Criteria) this;
        }

        public Criteria andStartStampGreaterThan(Date value) {
            addCriterion("start_stamp >", value, "startStamp");
            return (Criteria) this;
        }

        public Criteria andStartStampGreaterThanOrEqualTo(Date value) {
            addCriterion("start_stamp >=", value, "startStamp");
            return (Criteria) this;
        }

        public Criteria andStartStampLessThan(Date value) {
            addCriterion("start_stamp <", value, "startStamp");
            return (Criteria) this;
        }

        public Criteria andStartStampLessThanOrEqualTo(Date value) {
            addCriterion("start_stamp <=", value, "startStamp");
            return (Criteria) this;
        }

        public Criteria andStartStampIn(List<Date> values) {
            addCriterion("start_stamp in", values, "startStamp");
            return (Criteria) this;
        }

        public Criteria andStartStampNotIn(List<Date> values) {
            addCriterion("start_stamp not in", values, "startStamp");
            return (Criteria) this;
        }

        public Criteria andStartStampBetween(Date value1, Date value2) {
            addCriterion("start_stamp between", value1, value2, "startStamp");
            return (Criteria) this;
        }

        public Criteria andStartStampNotBetween(Date value1, Date value2) {
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

        public Criteria andAnswerStampEqualTo(Date value) {
            addCriterion("answer_stamp =", value, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampNotEqualTo(Date value) {
            addCriterion("answer_stamp <>", value, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampGreaterThan(Date value) {
            addCriterion("answer_stamp >", value, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampGreaterThanOrEqualTo(Date value) {
            addCriterion("answer_stamp >=", value, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampLessThan(Date value) {
            addCriterion("answer_stamp <", value, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampLessThanOrEqualTo(Date value) {
            addCriterion("answer_stamp <=", value, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampIn(List<Date> values) {
            addCriterion("answer_stamp in", values, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampNotIn(List<Date> values) {
            addCriterion("answer_stamp not in", values, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampBetween(Date value1, Date value2) {
            addCriterion("answer_stamp between", value1, value2, "answerStamp");
            return (Criteria) this;
        }

        public Criteria andAnswerStampNotBetween(Date value1, Date value2) {
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

        public Criteria andEndStampEqualTo(Date value) {
            addCriterion("end_stamp =", value, "endStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampNotEqualTo(Date value) {
            addCriterion("end_stamp <>", value, "endStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampGreaterThan(Date value) {
            addCriterion("end_stamp >", value, "endStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampGreaterThanOrEqualTo(Date value) {
            addCriterion("end_stamp >=", value, "endStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampLessThan(Date value) {
            addCriterion("end_stamp <", value, "endStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampLessThanOrEqualTo(Date value) {
            addCriterion("end_stamp <=", value, "endStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampIn(List<Date> values) {
            addCriterion("end_stamp in", values, "endStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampNotIn(List<Date> values) {
            addCriterion("end_stamp not in", values, "endStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampBetween(Date value1, Date value2) {
            addCriterion("end_stamp between", value1, value2, "endStamp");
            return (Criteria) this;
        }

        public Criteria andEndStampNotBetween(Date value1, Date value2) {
            addCriterion("end_stamp not between", value1, value2, "endStamp");
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

        public Criteria andRateIsNull() {
            addCriterion("rate is null");
            return (Criteria) this;
        }

        public Criteria andRateIsNotNull() {
            addCriterion("rate is not null");
            return (Criteria) this;
        }

        public Criteria andRateEqualTo(Double value) {
            addCriterion("rate =", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotEqualTo(Double value) {
            addCriterion("rate <>", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThan(Double value) {
            addCriterion("rate >", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThanOrEqualTo(Double value) {
            addCriterion("rate >=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThan(Double value) {
            addCriterion("rate <", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThanOrEqualTo(Double value) {
            addCriterion("rate <=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateIn(List<Double> values) {
            addCriterion("rate in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotIn(List<Double> values) {
            addCriterion("rate not in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateBetween(Double value1, Double value2) {
            addCriterion("rate between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotBetween(Double value1, Double value2) {
            addCriterion("rate not between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andIncrementIsNull() {
            addCriterion("increment is null");
            return (Criteria) this;
        }

        public Criteria andIncrementIsNotNull() {
            addCriterion("increment is not null");
            return (Criteria) this;
        }

        public Criteria andIncrementEqualTo(Integer value) {
            addCriterion("increment =", value, "increment");
            return (Criteria) this;
        }

        public Criteria andIncrementNotEqualTo(Integer value) {
            addCriterion("increment <>", value, "increment");
            return (Criteria) this;
        }

        public Criteria andIncrementGreaterThan(Integer value) {
            addCriterion("increment >", value, "increment");
            return (Criteria) this;
        }

        public Criteria andIncrementGreaterThanOrEqualTo(Integer value) {
            addCriterion("increment >=", value, "increment");
            return (Criteria) this;
        }

        public Criteria andIncrementLessThan(Integer value) {
            addCriterion("increment <", value, "increment");
            return (Criteria) this;
        }

        public Criteria andIncrementLessThanOrEqualTo(Integer value) {
            addCriterion("increment <=", value, "increment");
            return (Criteria) this;
        }

        public Criteria andIncrementIn(List<Integer> values) {
            addCriterion("increment in", values, "increment");
            return (Criteria) this;
        }

        public Criteria andIncrementNotIn(List<Integer> values) {
            addCriterion("increment not in", values, "increment");
            return (Criteria) this;
        }

        public Criteria andIncrementBetween(Integer value1, Integer value2) {
            addCriterion("increment between", value1, value2, "increment");
            return (Criteria) this;
        }

        public Criteria andIncrementNotBetween(Integer value1, Integer value2) {
            addCriterion("increment not between", value1, value2, "increment");
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

        public Criteria andFeeEqualTo(Double value) {
            addCriterion("fee =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(Double value) {
            addCriterion("fee <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(Double value) {
            addCriterion("fee >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(Double value) {
            addCriterion("fee >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(Double value) {
            addCriterion("fee <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(Double value) {
            addCriterion("fee <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<Double> values) {
            addCriterion("fee in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<Double> values) {
            addCriterion("fee not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(Double value1, Double value2) {
            addCriterion("fee between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(Double value1, Double value2) {
            addCriterion("fee not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andIsTollIsNull() {
            addCriterion("is_toll is null");
            return (Criteria) this;
        }

        public Criteria andIsTollIsNotNull() {
            addCriterion("is_toll is not null");
            return (Criteria) this;
        }

        public Criteria andIsTollEqualTo(Byte value) {
            addCriterion("is_toll =", value, "isToll");
            return (Criteria) this;
        }

        public Criteria andIsTollNotEqualTo(Byte value) {
            addCriterion("is_toll <>", value, "isToll");
            return (Criteria) this;
        }

        public Criteria andIsTollGreaterThan(Byte value) {
            addCriterion("is_toll >", value, "isToll");
            return (Criteria) this;
        }

        public Criteria andIsTollGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_toll >=", value, "isToll");
            return (Criteria) this;
        }

        public Criteria andIsTollLessThan(Byte value) {
            addCriterion("is_toll <", value, "isToll");
            return (Criteria) this;
        }

        public Criteria andIsTollLessThanOrEqualTo(Byte value) {
            addCriterion("is_toll <=", value, "isToll");
            return (Criteria) this;
        }

        public Criteria andIsTollIn(List<Byte> values) {
            addCriterion("is_toll in", values, "isToll");
            return (Criteria) this;
        }

        public Criteria andIsTollNotIn(List<Byte> values) {
            addCriterion("is_toll not in", values, "isToll");
            return (Criteria) this;
        }

        public Criteria andIsTollBetween(Byte value1, Byte value2) {
            addCriterion("is_toll between", value1, value2, "isToll");
            return (Criteria) this;
        }

        public Criteria andIsTollNotBetween(Byte value1, Byte value2) {
            addCriterion("is_toll not between", value1, value2, "isToll");
            return (Criteria) this;
        }

        public Criteria andBilltypeIsNull() {
            addCriterion("billtype is null");
            return (Criteria) this;
        }

        public Criteria andBilltypeIsNotNull() {
            addCriterion("billtype is not null");
            return (Criteria) this;
        }

        public Criteria andBilltypeEqualTo(Integer value) {
            addCriterion("billtype =", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeNotEqualTo(Integer value) {
            addCriterion("billtype <>", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeGreaterThan(Integer value) {
            addCriterion("billtype >", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("billtype >=", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeLessThan(Integer value) {
            addCriterion("billtype <", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeLessThanOrEqualTo(Integer value) {
            addCriterion("billtype <=", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeIn(List<Integer> values) {
            addCriterion("billtype in", values, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeNotIn(List<Integer> values) {
            addCriterion("billtype not in", values, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeBetween(Integer value1, Integer value2) {
            addCriterion("billtype between", value1, value2, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeNotBetween(Integer value1, Integer value2) {
            addCriterion("billtype not between", value1, value2, "billtype");
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

        public Criteria andDirectionEqualTo(Byte value) {
            addCriterion("direction =", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotEqualTo(Byte value) {
            addCriterion("direction <>", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionGreaterThan(Byte value) {
            addCriterion("direction >", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionGreaterThanOrEqualTo(Byte value) {
            addCriterion("direction >=", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLessThan(Byte value) {
            addCriterion("direction <", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLessThanOrEqualTo(Byte value) {
            addCriterion("direction <=", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionIn(List<Byte> values) {
            addCriterion("direction in", values, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotIn(List<Byte> values) {
            addCriterion("direction not in", values, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionBetween(Byte value1, Byte value2) {
            addCriterion("direction between", value1, value2, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotBetween(Byte value1, Byte value2) {
            addCriterion("direction not between", value1, value2, "direction");
            return (Criteria) this;
        }

        public Criteria andCostRateIsNull() {
            addCriterion("cost_rate is null");
            return (Criteria) this;
        }

        public Criteria andCostRateIsNotNull() {
            addCriterion("cost_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCostRateEqualTo(Float value) {
            addCriterion("cost_rate =", value, "costRate");
            return (Criteria) this;
        }

        public Criteria andCostRateNotEqualTo(Float value) {
            addCriterion("cost_rate <>", value, "costRate");
            return (Criteria) this;
        }

        public Criteria andCostRateGreaterThan(Float value) {
            addCriterion("cost_rate >", value, "costRate");
            return (Criteria) this;
        }

        public Criteria andCostRateGreaterThanOrEqualTo(Float value) {
            addCriterion("cost_rate >=", value, "costRate");
            return (Criteria) this;
        }

        public Criteria andCostRateLessThan(Float value) {
            addCriterion("cost_rate <", value, "costRate");
            return (Criteria) this;
        }

        public Criteria andCostRateLessThanOrEqualTo(Float value) {
            addCriterion("cost_rate <=", value, "costRate");
            return (Criteria) this;
        }

        public Criteria andCostRateIn(List<Float> values) {
            addCriterion("cost_rate in", values, "costRate");
            return (Criteria) this;
        }

        public Criteria andCostRateNotIn(List<Float> values) {
            addCriterion("cost_rate not in", values, "costRate");
            return (Criteria) this;
        }

        public Criteria andCostRateBetween(Float value1, Float value2) {
            addCriterion("cost_rate between", value1, value2, "costRate");
            return (Criteria) this;
        }

        public Criteria andCostRateNotBetween(Float value1, Float value2) {
            addCriterion("cost_rate not between", value1, value2, "costRate");
            return (Criteria) this;
        }

        public Criteria andCostUnitIsNull() {
            addCriterion("cost_unit is null");
            return (Criteria) this;
        }

        public Criteria andCostUnitIsNotNull() {
            addCriterion("cost_unit is not null");
            return (Criteria) this;
        }

        public Criteria andCostUnitEqualTo(Integer value) {
            addCriterion("cost_unit =", value, "costUnit");
            return (Criteria) this;
        }

        public Criteria andCostUnitNotEqualTo(Integer value) {
            addCriterion("cost_unit <>", value, "costUnit");
            return (Criteria) this;
        }

        public Criteria andCostUnitGreaterThan(Integer value) {
            addCriterion("cost_unit >", value, "costUnit");
            return (Criteria) this;
        }

        public Criteria andCostUnitGreaterThanOrEqualTo(Integer value) {
            addCriterion("cost_unit >=", value, "costUnit");
            return (Criteria) this;
        }

        public Criteria andCostUnitLessThan(Integer value) {
            addCriterion("cost_unit <", value, "costUnit");
            return (Criteria) this;
        }

        public Criteria andCostUnitLessThanOrEqualTo(Integer value) {
            addCriterion("cost_unit <=", value, "costUnit");
            return (Criteria) this;
        }

        public Criteria andCostUnitIn(List<Integer> values) {
            addCriterion("cost_unit in", values, "costUnit");
            return (Criteria) this;
        }

        public Criteria andCostUnitNotIn(List<Integer> values) {
            addCriterion("cost_unit not in", values, "costUnit");
            return (Criteria) this;
        }

        public Criteria andCostUnitBetween(Integer value1, Integer value2) {
            addCriterion("cost_unit between", value1, value2, "costUnit");
            return (Criteria) this;
        }

        public Criteria andCostUnitNotBetween(Integer value1, Integer value2) {
            addCriterion("cost_unit not between", value1, value2, "costUnit");
            return (Criteria) this;
        }

        public Criteria andCostFeeIsNull() {
            addCriterion("cost_fee is null");
            return (Criteria) this;
        }

        public Criteria andCostFeeIsNotNull() {
            addCriterion("cost_fee is not null");
            return (Criteria) this;
        }

        public Criteria andCostFeeEqualTo(Float value) {
            addCriterion("cost_fee =", value, "costFee");
            return (Criteria) this;
        }

        public Criteria andCostFeeNotEqualTo(Float value) {
            addCriterion("cost_fee <>", value, "costFee");
            return (Criteria) this;
        }

        public Criteria andCostFeeGreaterThan(Float value) {
            addCriterion("cost_fee >", value, "costFee");
            return (Criteria) this;
        }

        public Criteria andCostFeeGreaterThanOrEqualTo(Float value) {
            addCriterion("cost_fee >=", value, "costFee");
            return (Criteria) this;
        }

        public Criteria andCostFeeLessThan(Float value) {
            addCriterion("cost_fee <", value, "costFee");
            return (Criteria) this;
        }

        public Criteria andCostFeeLessThanOrEqualTo(Float value) {
            addCriterion("cost_fee <=", value, "costFee");
            return (Criteria) this;
        }

        public Criteria andCostFeeIn(List<Float> values) {
            addCriterion("cost_fee in", values, "costFee");
            return (Criteria) this;
        }

        public Criteria andCostFeeNotIn(List<Float> values) {
            addCriterion("cost_fee not in", values, "costFee");
            return (Criteria) this;
        }

        public Criteria andCostFeeBetween(Float value1, Float value2) {
            addCriterion("cost_fee between", value1, value2, "costFee");
            return (Criteria) this;
        }

        public Criteria andCostFeeNotBetween(Float value1, Float value2) {
            addCriterion("cost_fee not between", value1, value2, "costFee");
            return (Criteria) this;
        }

        public Criteria andGatewayidIsNull() {
            addCriterion("gatewayid is null");
            return (Criteria) this;
        }

        public Criteria andGatewayidIsNotNull() {
            addCriterion("gatewayid is not null");
            return (Criteria) this;
        }

        public Criteria andGatewayidEqualTo(Integer value) {
            addCriterion("gatewayid =", value, "gatewayid");
            return (Criteria) this;
        }

        public Criteria andGatewayidNotEqualTo(Integer value) {
            addCriterion("gatewayid <>", value, "gatewayid");
            return (Criteria) this;
        }

        public Criteria andGatewayidGreaterThan(Integer value) {
            addCriterion("gatewayid >", value, "gatewayid");
            return (Criteria) this;
        }

        public Criteria andGatewayidGreaterThanOrEqualTo(Integer value) {
            addCriterion("gatewayid >=", value, "gatewayid");
            return (Criteria) this;
        }

        public Criteria andGatewayidLessThan(Integer value) {
            addCriterion("gatewayid <", value, "gatewayid");
            return (Criteria) this;
        }

        public Criteria andGatewayidLessThanOrEqualTo(Integer value) {
            addCriterion("gatewayid <=", value, "gatewayid");
            return (Criteria) this;
        }

        public Criteria andGatewayidIn(List<Integer> values) {
            addCriterion("gatewayid in", values, "gatewayid");
            return (Criteria) this;
        }

        public Criteria andGatewayidNotIn(List<Integer> values) {
            addCriterion("gatewayid not in", values, "gatewayid");
            return (Criteria) this;
        }

        public Criteria andGatewayidBetween(Integer value1, Integer value2) {
            addCriterion("gatewayid between", value1, value2, "gatewayid");
            return (Criteria) this;
        }

        public Criteria andGatewayidNotBetween(Integer value1, Integer value2) {
            addCriterion("gatewayid not between", value1, value2, "gatewayid");
            return (Criteria) this;
        }
    }

    /**  tableName: bill   **/
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /** bill **/
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