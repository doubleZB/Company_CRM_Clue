package com.sunll.clue.entity.follow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FollowRecordExample {
    /**   tableName: follow_record   **/
    protected String orderByClause;

    /**   tableName: follow_record   **/
    protected boolean distinct;

    /**   tableName: follow_record   **/
    protected List<Criteria> oredCriteria;

    public FollowRecordExample() {
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

    /** follow_record **/
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

        public Criteria andFollowContentIsNull() {
            addCriterion("follow_content is null");
            return (Criteria) this;
        }

        public Criteria andFollowContentIsNotNull() {
            addCriterion("follow_content is not null");
            return (Criteria) this;
        }

        public Criteria andFollowContentEqualTo(String value) {
            addCriterion("follow_content =", value, "followContent");
            return (Criteria) this;
        }

        public Criteria andFollowContentNotEqualTo(String value) {
            addCriterion("follow_content <>", value, "followContent");
            return (Criteria) this;
        }

        public Criteria andFollowContentGreaterThan(String value) {
            addCriterion("follow_content >", value, "followContent");
            return (Criteria) this;
        }

        public Criteria andFollowContentGreaterThanOrEqualTo(String value) {
            addCriterion("follow_content >=", value, "followContent");
            return (Criteria) this;
        }

        public Criteria andFollowContentLessThan(String value) {
            addCriterion("follow_content <", value, "followContent");
            return (Criteria) this;
        }

        public Criteria andFollowContentLessThanOrEqualTo(String value) {
            addCriterion("follow_content <=", value, "followContent");
            return (Criteria) this;
        }

        public Criteria andFollowContentLike(String value) {
            addCriterion("follow_content like", value, "followContent");
            return (Criteria) this;
        }

        public Criteria andFollowContentNotLike(String value) {
            addCriterion("follow_content not like", value, "followContent");
            return (Criteria) this;
        }

        public Criteria andFollowContentIn(List<String> values) {
            addCriterion("follow_content in", values, "followContent");
            return (Criteria) this;
        }

        public Criteria andFollowContentNotIn(List<String> values) {
            addCriterion("follow_content not in", values, "followContent");
            return (Criteria) this;
        }

        public Criteria andFollowContentBetween(String value1, String value2) {
            addCriterion("follow_content between", value1, value2, "followContent");
            return (Criteria) this;
        }

        public Criteria andFollowContentNotBetween(String value1, String value2) {
            addCriterion("follow_content not between", value1, value2, "followContent");
            return (Criteria) this;
        }

        public Criteria andFollowUpIsNull() {
            addCriterion("follow_up is null");
            return (Criteria) this;
        }

        public Criteria andFollowUpIsNotNull() {
            addCriterion("follow_up is not null");
            return (Criteria) this;
        }

        public Criteria andFollowUpEqualTo(Integer value) {
            addCriterion("follow_up =", value, "followUp");
            return (Criteria) this;
        }

        public Criteria andFollowUpNotEqualTo(Integer value) {
            addCriterion("follow_up <>", value, "followUp");
            return (Criteria) this;
        }

        public Criteria andFollowUpGreaterThan(Integer value) {
            addCriterion("follow_up >", value, "followUp");
            return (Criteria) this;
        }

        public Criteria andFollowUpGreaterThanOrEqualTo(Integer value) {
            addCriterion("follow_up >=", value, "followUp");
            return (Criteria) this;
        }

        public Criteria andFollowUpLessThan(Integer value) {
            addCriterion("follow_up <", value, "followUp");
            return (Criteria) this;
        }

        public Criteria andFollowUpLessThanOrEqualTo(Integer value) {
            addCriterion("follow_up <=", value, "followUp");
            return (Criteria) this;
        }

        public Criteria andFollowUpIn(List<Integer> values) {
            addCriterion("follow_up in", values, "followUp");
            return (Criteria) this;
        }

        public Criteria andFollowUpNotIn(List<Integer> values) {
            addCriterion("follow_up not in", values, "followUp");
            return (Criteria) this;
        }

        public Criteria andFollowUpBetween(Integer value1, Integer value2) {
            addCriterion("follow_up between", value1, value2, "followUp");
            return (Criteria) this;
        }

        public Criteria andFollowUpNotBetween(Integer value1, Integer value2) {
            addCriterion("follow_up not between", value1, value2, "followUp");
            return (Criteria) this;
        }

        public Criteria andFollowNextTimeIsNull() {
            addCriterion("follow_next_time is null");
            return (Criteria) this;
        }

        public Criteria andFollowNextTimeIsNotNull() {
            addCriterion("follow_next_time is not null");
            return (Criteria) this;
        }

        public Criteria andFollowNextTimeEqualTo(Date value) {
            addCriterion("follow_next_time =", value, "followNextTime");
            return (Criteria) this;
        }

        public Criteria andFollowNextTimeNotEqualTo(Date value) {
            addCriterion("follow_next_time <>", value, "followNextTime");
            return (Criteria) this;
        }

        public Criteria andFollowNextTimeGreaterThan(Date value) {
            addCriterion("follow_next_time >", value, "followNextTime");
            return (Criteria) this;
        }

        public Criteria andFollowNextTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("follow_next_time >=", value, "followNextTime");
            return (Criteria) this;
        }

        public Criteria andFollowNextTimeLessThan(Date value) {
            addCriterion("follow_next_time <", value, "followNextTime");
            return (Criteria) this;
        }

        public Criteria andFollowNextTimeLessThanOrEqualTo(Date value) {
            addCriterion("follow_next_time <=", value, "followNextTime");
            return (Criteria) this;
        }

        public Criteria andFollowNextTimeIn(List<Date> values) {
            addCriterion("follow_next_time in", values, "followNextTime");
            return (Criteria) this;
        }

        public Criteria andFollowNextTimeNotIn(List<Date> values) {
            addCriterion("follow_next_time not in", values, "followNextTime");
            return (Criteria) this;
        }

        public Criteria andFollowNextTimeBetween(Date value1, Date value2) {
            addCriterion("follow_next_time between", value1, value2, "followNextTime");
            return (Criteria) this;
        }

        public Criteria andFollowNextTimeNotBetween(Date value1, Date value2) {
            addCriterion("follow_next_time not between", value1, value2, "followNextTime");
            return (Criteria) this;
        }

        public Criteria andFollowReminderContentIsNull() {
            addCriterion("follow_reminder_content is null");
            return (Criteria) this;
        }

        public Criteria andFollowReminderContentIsNotNull() {
            addCriterion("follow_reminder_content is not null");
            return (Criteria) this;
        }

        public Criteria andFollowReminderContentEqualTo(String value) {
            addCriterion("follow_reminder_content =", value, "followReminderContent");
            return (Criteria) this;
        }

        public Criteria andFollowReminderContentNotEqualTo(String value) {
            addCriterion("follow_reminder_content <>", value, "followReminderContent");
            return (Criteria) this;
        }

        public Criteria andFollowReminderContentGreaterThan(String value) {
            addCriterion("follow_reminder_content >", value, "followReminderContent");
            return (Criteria) this;
        }

        public Criteria andFollowReminderContentGreaterThanOrEqualTo(String value) {
            addCriterion("follow_reminder_content >=", value, "followReminderContent");
            return (Criteria) this;
        }

        public Criteria andFollowReminderContentLessThan(String value) {
            addCriterion("follow_reminder_content <", value, "followReminderContent");
            return (Criteria) this;
        }

        public Criteria andFollowReminderContentLessThanOrEqualTo(String value) {
            addCriterion("follow_reminder_content <=", value, "followReminderContent");
            return (Criteria) this;
        }

        public Criteria andFollowReminderContentLike(String value) {
            addCriterion("follow_reminder_content like", value, "followReminderContent");
            return (Criteria) this;
        }

        public Criteria andFollowReminderContentNotLike(String value) {
            addCriterion("follow_reminder_content not like", value, "followReminderContent");
            return (Criteria) this;
        }

        public Criteria andFollowReminderContentIn(List<String> values) {
            addCriterion("follow_reminder_content in", values, "followReminderContent");
            return (Criteria) this;
        }

        public Criteria andFollowReminderContentNotIn(List<String> values) {
            addCriterion("follow_reminder_content not in", values, "followReminderContent");
            return (Criteria) this;
        }

        public Criteria andFollowReminderContentBetween(String value1, String value2) {
            addCriterion("follow_reminder_content between", value1, value2, "followReminderContent");
            return (Criteria) this;
        }

        public Criteria andFollowReminderContentNotBetween(String value1, String value2) {
            addCriterion("follow_reminder_content not between", value1, value2, "followReminderContent");
            return (Criteria) this;
        }

        public Criteria andFollowSourceIdIsNull() {
            addCriterion("follow_source_id is null");
            return (Criteria) this;
        }

        public Criteria andFollowSourceIdIsNotNull() {
            addCriterion("follow_source_id is not null");
            return (Criteria) this;
        }

        public Criteria andFollowSourceIdEqualTo(String value) {
            addCriterion("follow_source_id =", value, "followSourceId");
            return (Criteria) this;
        }

        public Criteria andFollowSourceIdNotEqualTo(String value) {
            addCriterion("follow_source_id <>", value, "followSourceId");
            return (Criteria) this;
        }

        public Criteria andFollowSourceIdGreaterThan(String value) {
            addCriterion("follow_source_id >", value, "followSourceId");
            return (Criteria) this;
        }

        public Criteria andFollowSourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("follow_source_id >=", value, "followSourceId");
            return (Criteria) this;
        }

        public Criteria andFollowSourceIdLessThan(String value) {
            addCriterion("follow_source_id <", value, "followSourceId");
            return (Criteria) this;
        }

        public Criteria andFollowSourceIdLessThanOrEqualTo(String value) {
            addCriterion("follow_source_id <=", value, "followSourceId");
            return (Criteria) this;
        }

        public Criteria andFollowSourceIdLike(String value) {
            addCriterion("follow_source_id like", value, "followSourceId");
            return (Criteria) this;
        }

        public Criteria andFollowSourceIdNotLike(String value) {
            addCriterion("follow_source_id not like", value, "followSourceId");
            return (Criteria) this;
        }

        public Criteria andFollowSourceIdIn(List<String> values) {
            addCriterion("follow_source_id in", values, "followSourceId");
            return (Criteria) this;
        }

        public Criteria andFollowSourceIdNotIn(List<String> values) {
            addCriterion("follow_source_id not in", values, "followSourceId");
            return (Criteria) this;
        }

        public Criteria andFollowSourceIdBetween(String value1, String value2) {
            addCriterion("follow_source_id between", value1, value2, "followSourceId");
            return (Criteria) this;
        }

        public Criteria andFollowSourceIdNotBetween(String value1, String value2) {
            addCriterion("follow_source_id not between", value1, value2, "followSourceId");
            return (Criteria) this;
        }

        public Criteria andFollowSourceTypeIsNull() {
            addCriterion("follow_source_type is null");
            return (Criteria) this;
        }

        public Criteria andFollowSourceTypeIsNotNull() {
            addCriterion("follow_source_type is not null");
            return (Criteria) this;
        }

        public Criteria andFollowSourceTypeEqualTo(Integer value) {
            addCriterion("follow_source_type =", value, "followSourceType");
            return (Criteria) this;
        }

        public Criteria andFollowSourceTypeNotEqualTo(Integer value) {
            addCriterion("follow_source_type <>", value, "followSourceType");
            return (Criteria) this;
        }

        public Criteria andFollowSourceTypeGreaterThan(Integer value) {
            addCriterion("follow_source_type >", value, "followSourceType");
            return (Criteria) this;
        }

        public Criteria andFollowSourceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("follow_source_type >=", value, "followSourceType");
            return (Criteria) this;
        }

        public Criteria andFollowSourceTypeLessThan(Integer value) {
            addCriterion("follow_source_type <", value, "followSourceType");
            return (Criteria) this;
        }

        public Criteria andFollowSourceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("follow_source_type <=", value, "followSourceType");
            return (Criteria) this;
        }

        public Criteria andFollowSourceTypeIn(List<Integer> values) {
            addCriterion("follow_source_type in", values, "followSourceType");
            return (Criteria) this;
        }

        public Criteria andFollowSourceTypeNotIn(List<Integer> values) {
            addCriterion("follow_source_type not in", values, "followSourceType");
            return (Criteria) this;
        }

        public Criteria andFollowSourceTypeBetween(Integer value1, Integer value2) {
            addCriterion("follow_source_type between", value1, value2, "followSourceType");
            return (Criteria) this;
        }

        public Criteria andFollowSourceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("follow_source_type not between", value1, value2, "followSourceType");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Integer value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Integer value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Integer value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Integer value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Integer value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Integer> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Integer> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Integer value1, Integer value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Integer value1, Integer value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(Integer value) {
            addCriterion("update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(Integer value) {
            addCriterion("update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(Integer value) {
            addCriterion("update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(Integer value) {
            addCriterion("update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<Integer> values) {
            addCriterion("update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<Integer> values) {
            addCriterion("update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("update_user_id not between", value1, value2, "updateUserId");
            return (Criteria) this;
        }
    }

    /**  tableName: follow_record   **/
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /** follow_record **/
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