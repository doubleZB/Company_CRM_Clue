package com.sunll.center.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PredialTaskExample {
    /**   tableName: predial_task   **/
    protected String orderByClause;

    /**   tableName: predial_task   **/
    protected boolean distinct;

    /**   tableName: predial_task   **/
    protected List<Criteria> oredCriteria;

    public PredialTaskExample() {
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

    /** predial_task **/
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

        public Criteria andAccountidIsNull() {
            addCriterion("accountid is null");
            return (Criteria) this;
        }

        public Criteria andAccountidIsNotNull() {
            addCriterion("accountid is not null");
            return (Criteria) this;
        }

        public Criteria andAccountidEqualTo(Integer value) {
            addCriterion("accountid =", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidNotEqualTo(Integer value) {
            addCriterion("accountid <>", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidGreaterThan(Integer value) {
            addCriterion("accountid >", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidGreaterThanOrEqualTo(Integer value) {
            addCriterion("accountid >=", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidLessThan(Integer value) {
            addCriterion("accountid <", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidLessThanOrEqualTo(Integer value) {
            addCriterion("accountid <=", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidIn(List<Integer> values) {
            addCriterion("accountid in", values, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidNotIn(List<Integer> values) {
            addCriterion("accountid not in", values, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidBetween(Integer value1, Integer value2) {
            addCriterion("accountid between", value1, value2, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidNotBetween(Integer value1, Integer value2) {
            addCriterion("accountid not between", value1, value2, "accountid");
            return (Criteria) this;
        }

        public Criteria andTasknameIsNull() {
            addCriterion("taskname is null");
            return (Criteria) this;
        }

        public Criteria andTasknameIsNotNull() {
            addCriterion("taskname is not null");
            return (Criteria) this;
        }

        public Criteria andTasknameEqualTo(String value) {
            addCriterion("taskname =", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameNotEqualTo(String value) {
            addCriterion("taskname <>", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameGreaterThan(String value) {
            addCriterion("taskname >", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameGreaterThanOrEqualTo(String value) {
            addCriterion("taskname >=", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameLessThan(String value) {
            addCriterion("taskname <", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameLessThanOrEqualTo(String value) {
            addCriterion("taskname <=", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameLike(String value) {
            addCriterion("taskname like", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameNotLike(String value) {
            addCriterion("taskname not like", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameIn(List<String> values) {
            addCriterion("taskname in", values, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameNotIn(List<String> values) {
            addCriterion("taskname not in", values, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameBetween(String value1, String value2) {
            addCriterion("taskname between", value1, value2, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameNotBetween(String value1, String value2) {
            addCriterion("taskname not between", value1, value2, "taskname");
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

        public Criteria andEndtimeIsNull() {
            addCriterion("endtime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endtime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterion("endtime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterion("endtime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterion("endtime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("endtime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterion("endtime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("endtime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterion("endtime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterion("endtime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterion("endtime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("endtime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andTotalcountIsNull() {
            addCriterion("totalcount is null");
            return (Criteria) this;
        }

        public Criteria andTotalcountIsNotNull() {
            addCriterion("totalcount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalcountEqualTo(Integer value) {
            addCriterion("totalcount =", value, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalcountNotEqualTo(Integer value) {
            addCriterion("totalcount <>", value, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalcountGreaterThan(Integer value) {
            addCriterion("totalcount >", value, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("totalcount >=", value, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalcountLessThan(Integer value) {
            addCriterion("totalcount <", value, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalcountLessThanOrEqualTo(Integer value) {
            addCriterion("totalcount <=", value, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalcountIn(List<Integer> values) {
            addCriterion("totalcount in", values, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalcountNotIn(List<Integer> values) {
            addCriterion("totalcount not in", values, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalcountBetween(Integer value1, Integer value2) {
            addCriterion("totalcount between", value1, value2, "totalcount");
            return (Criteria) this;
        }

        public Criteria andTotalcountNotBetween(Integer value1, Integer value2) {
            addCriterion("totalcount not between", value1, value2, "totalcount");
            return (Criteria) this;
        }

        public Criteria andCallouttypeIsNull() {
            addCriterion("callouttype is null");
            return (Criteria) this;
        }

        public Criteria andCallouttypeIsNotNull() {
            addCriterion("callouttype is not null");
            return (Criteria) this;
        }

        public Criteria andCallouttypeEqualTo(Integer value) {
            addCriterion("callouttype =", value, "callouttype");
            return (Criteria) this;
        }

        public Criteria andCallouttypeNotEqualTo(Integer value) {
            addCriterion("callouttype <>", value, "callouttype");
            return (Criteria) this;
        }

        public Criteria andCallouttypeGreaterThan(Integer value) {
            addCriterion("callouttype >", value, "callouttype");
            return (Criteria) this;
        }

        public Criteria andCallouttypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("callouttype >=", value, "callouttype");
            return (Criteria) this;
        }

        public Criteria andCallouttypeLessThan(Integer value) {
            addCriterion("callouttype <", value, "callouttype");
            return (Criteria) this;
        }

        public Criteria andCallouttypeLessThanOrEqualTo(Integer value) {
            addCriterion("callouttype <=", value, "callouttype");
            return (Criteria) this;
        }

        public Criteria andCallouttypeIn(List<Integer> values) {
            addCriterion("callouttype in", values, "callouttype");
            return (Criteria) this;
        }

        public Criteria andCallouttypeNotIn(List<Integer> values) {
            addCriterion("callouttype not in", values, "callouttype");
            return (Criteria) this;
        }

        public Criteria andCallouttypeBetween(Integer value1, Integer value2) {
            addCriterion("callouttype between", value1, value2, "callouttype");
            return (Criteria) this;
        }

        public Criteria andCallouttypeNotBetween(Integer value1, Integer value2) {
            addCriterion("callouttype not between", value1, value2, "callouttype");
            return (Criteria) this;
        }

        public Criteria andWavfileIsNull() {
            addCriterion("wavfile is null");
            return (Criteria) this;
        }

        public Criteria andWavfileIsNotNull() {
            addCriterion("wavfile is not null");
            return (Criteria) this;
        }

        public Criteria andWavfileEqualTo(String value) {
            addCriterion("wavfile =", value, "wavfile");
            return (Criteria) this;
        }

        public Criteria andWavfileNotEqualTo(String value) {
            addCriterion("wavfile <>", value, "wavfile");
            return (Criteria) this;
        }

        public Criteria andWavfileGreaterThan(String value) {
            addCriterion("wavfile >", value, "wavfile");
            return (Criteria) this;
        }

        public Criteria andWavfileGreaterThanOrEqualTo(String value) {
            addCriterion("wavfile >=", value, "wavfile");
            return (Criteria) this;
        }

        public Criteria andWavfileLessThan(String value) {
            addCriterion("wavfile <", value, "wavfile");
            return (Criteria) this;
        }

        public Criteria andWavfileLessThanOrEqualTo(String value) {
            addCriterion("wavfile <=", value, "wavfile");
            return (Criteria) this;
        }

        public Criteria andWavfileLike(String value) {
            addCriterion("wavfile like", value, "wavfile");
            return (Criteria) this;
        }

        public Criteria andWavfileNotLike(String value) {
            addCriterion("wavfile not like", value, "wavfile");
            return (Criteria) this;
        }

        public Criteria andWavfileIn(List<String> values) {
            addCriterion("wavfile in", values, "wavfile");
            return (Criteria) this;
        }

        public Criteria andWavfileNotIn(List<String> values) {
            addCriterion("wavfile not in", values, "wavfile");
            return (Criteria) this;
        }

        public Criteria andWavfileBetween(String value1, String value2) {
            addCriterion("wavfile between", value1, value2, "wavfile");
            return (Criteria) this;
        }

        public Criteria andWavfileNotBetween(String value1, String value2) {
            addCriterion("wavfile not between", value1, value2, "wavfile");
            return (Criteria) this;
        }

        public Criteria andPlaystrIsNull() {
            addCriterion("playstr is null");
            return (Criteria) this;
        }

        public Criteria andPlaystrIsNotNull() {
            addCriterion("playstr is not null");
            return (Criteria) this;
        }

        public Criteria andPlaystrEqualTo(String value) {
            addCriterion("playstr =", value, "playstr");
            return (Criteria) this;
        }

        public Criteria andPlaystrNotEqualTo(String value) {
            addCriterion("playstr <>", value, "playstr");
            return (Criteria) this;
        }

        public Criteria andPlaystrGreaterThan(String value) {
            addCriterion("playstr >", value, "playstr");
            return (Criteria) this;
        }

        public Criteria andPlaystrGreaterThanOrEqualTo(String value) {
            addCriterion("playstr >=", value, "playstr");
            return (Criteria) this;
        }

        public Criteria andPlaystrLessThan(String value) {
            addCriterion("playstr <", value, "playstr");
            return (Criteria) this;
        }

        public Criteria andPlaystrLessThanOrEqualTo(String value) {
            addCriterion("playstr <=", value, "playstr");
            return (Criteria) this;
        }

        public Criteria andPlaystrLike(String value) {
            addCriterion("playstr like", value, "playstr");
            return (Criteria) this;
        }

        public Criteria andPlaystrNotLike(String value) {
            addCriterion("playstr not like", value, "playstr");
            return (Criteria) this;
        }

        public Criteria andPlaystrIn(List<String> values) {
            addCriterion("playstr in", values, "playstr");
            return (Criteria) this;
        }

        public Criteria andPlaystrNotIn(List<String> values) {
            addCriterion("playstr not in", values, "playstr");
            return (Criteria) this;
        }

        public Criteria andPlaystrBetween(String value1, String value2) {
            addCriterion("playstr between", value1, value2, "playstr");
            return (Criteria) this;
        }

        public Criteria andPlaystrNotBetween(String value1, String value2) {
            addCriterion("playstr not between", value1, value2, "playstr");
            return (Criteria) this;
        }

        public Criteria andShowcallerIsNull() {
            addCriterion("showcaller is null");
            return (Criteria) this;
        }

        public Criteria andShowcallerIsNotNull() {
            addCriterion("showcaller is not null");
            return (Criteria) this;
        }

        public Criteria andShowcallerEqualTo(String value) {
            addCriterion("showcaller =", value, "showcaller");
            return (Criteria) this;
        }

        public Criteria andShowcallerNotEqualTo(String value) {
            addCriterion("showcaller <>", value, "showcaller");
            return (Criteria) this;
        }

        public Criteria andShowcallerGreaterThan(String value) {
            addCriterion("showcaller >", value, "showcaller");
            return (Criteria) this;
        }

        public Criteria andShowcallerGreaterThanOrEqualTo(String value) {
            addCriterion("showcaller >=", value, "showcaller");
            return (Criteria) this;
        }

        public Criteria andShowcallerLessThan(String value) {
            addCriterion("showcaller <", value, "showcaller");
            return (Criteria) this;
        }

        public Criteria andShowcallerLessThanOrEqualTo(String value) {
            addCriterion("showcaller <=", value, "showcaller");
            return (Criteria) this;
        }

        public Criteria andShowcallerLike(String value) {
            addCriterion("showcaller like", value, "showcaller");
            return (Criteria) this;
        }

        public Criteria andShowcallerNotLike(String value) {
            addCriterion("showcaller not like", value, "showcaller");
            return (Criteria) this;
        }

        public Criteria andShowcallerIn(List<String> values) {
            addCriterion("showcaller in", values, "showcaller");
            return (Criteria) this;
        }

        public Criteria andShowcallerNotIn(List<String> values) {
            addCriterion("showcaller not in", values, "showcaller");
            return (Criteria) this;
        }

        public Criteria andShowcallerBetween(String value1, String value2) {
            addCriterion("showcaller between", value1, value2, "showcaller");
            return (Criteria) this;
        }

        public Criteria andShowcallerNotBetween(String value1, String value2) {
            addCriterion("showcaller not between", value1, value2, "showcaller");
            return (Criteria) this;
        }

        public Criteria andTaskstatusIsNull() {
            addCriterion("taskstatus is null");
            return (Criteria) this;
        }

        public Criteria andTaskstatusIsNotNull() {
            addCriterion("taskstatus is not null");
            return (Criteria) this;
        }

        public Criteria andTaskstatusEqualTo(Integer value) {
            addCriterion("taskstatus =", value, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andTaskstatusNotEqualTo(Integer value) {
            addCriterion("taskstatus <>", value, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andTaskstatusGreaterThan(Integer value) {
            addCriterion("taskstatus >", value, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andTaskstatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("taskstatus >=", value, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andTaskstatusLessThan(Integer value) {
            addCriterion("taskstatus <", value, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andTaskstatusLessThanOrEqualTo(Integer value) {
            addCriterion("taskstatus <=", value, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andTaskstatusIn(List<Integer> values) {
            addCriterion("taskstatus in", values, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andTaskstatusNotIn(List<Integer> values) {
            addCriterion("taskstatus not in", values, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andTaskstatusBetween(Integer value1, Integer value2) {
            addCriterion("taskstatus between", value1, value2, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andTaskstatusNotBetween(Integer value1, Integer value2) {
            addCriterion("taskstatus not between", value1, value2, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andConcurrentcallIsNull() {
            addCriterion("concurrentcall is null");
            return (Criteria) this;
        }

        public Criteria andConcurrentcallIsNotNull() {
            addCriterion("concurrentcall is not null");
            return (Criteria) this;
        }

        public Criteria andConcurrentcallEqualTo(Integer value) {
            addCriterion("concurrentcall =", value, "concurrentcall");
            return (Criteria) this;
        }

        public Criteria andConcurrentcallNotEqualTo(Integer value) {
            addCriterion("concurrentcall <>", value, "concurrentcall");
            return (Criteria) this;
        }

        public Criteria andConcurrentcallGreaterThan(Integer value) {
            addCriterion("concurrentcall >", value, "concurrentcall");
            return (Criteria) this;
        }

        public Criteria andConcurrentcallGreaterThanOrEqualTo(Integer value) {
            addCriterion("concurrentcall >=", value, "concurrentcall");
            return (Criteria) this;
        }

        public Criteria andConcurrentcallLessThan(Integer value) {
            addCriterion("concurrentcall <", value, "concurrentcall");
            return (Criteria) this;
        }

        public Criteria andConcurrentcallLessThanOrEqualTo(Integer value) {
            addCriterion("concurrentcall <=", value, "concurrentcall");
            return (Criteria) this;
        }

        public Criteria andConcurrentcallIn(List<Integer> values) {
            addCriterion("concurrentcall in", values, "concurrentcall");
            return (Criteria) this;
        }

        public Criteria andConcurrentcallNotIn(List<Integer> values) {
            addCriterion("concurrentcall not in", values, "concurrentcall");
            return (Criteria) this;
        }

        public Criteria andConcurrentcallBetween(Integer value1, Integer value2) {
            addCriterion("concurrentcall between", value1, value2, "concurrentcall");
            return (Criteria) this;
        }

        public Criteria andConcurrentcallNotBetween(Integer value1, Integer value2) {
            addCriterion("concurrentcall not between", value1, value2, "concurrentcall");
            return (Criteria) this;
        }

        public Criteria andBindFlowIdIsNull() {
            addCriterion("bind_flow_id is null");
            return (Criteria) this;
        }

        public Criteria andBindFlowIdIsNotNull() {
            addCriterion("bind_flow_id is not null");
            return (Criteria) this;
        }

        public Criteria andBindFlowIdEqualTo(Integer value) {
            addCriterion("bind_flow_id =", value, "bindFlowId");
            return (Criteria) this;
        }

        public Criteria andBindFlowIdNotEqualTo(Integer value) {
            addCriterion("bind_flow_id <>", value, "bindFlowId");
            return (Criteria) this;
        }

        public Criteria andBindFlowIdGreaterThan(Integer value) {
            addCriterion("bind_flow_id >", value, "bindFlowId");
            return (Criteria) this;
        }

        public Criteria andBindFlowIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("bind_flow_id >=", value, "bindFlowId");
            return (Criteria) this;
        }

        public Criteria andBindFlowIdLessThan(Integer value) {
            addCriterion("bind_flow_id <", value, "bindFlowId");
            return (Criteria) this;
        }

        public Criteria andBindFlowIdLessThanOrEqualTo(Integer value) {
            addCriterion("bind_flow_id <=", value, "bindFlowId");
            return (Criteria) this;
        }

        public Criteria andBindFlowIdIn(List<Integer> values) {
            addCriterion("bind_flow_id in", values, "bindFlowId");
            return (Criteria) this;
        }

        public Criteria andBindFlowIdNotIn(List<Integer> values) {
            addCriterion("bind_flow_id not in", values, "bindFlowId");
            return (Criteria) this;
        }

        public Criteria andBindFlowIdBetween(Integer value1, Integer value2) {
            addCriterion("bind_flow_id between", value1, value2, "bindFlowId");
            return (Criteria) this;
        }

        public Criteria andBindFlowIdNotBetween(Integer value1, Integer value2) {
            addCriterion("bind_flow_id not between", value1, value2, "bindFlowId");
            return (Criteria) this;
        }
    }

    /**  tableName: predial_task   **/
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /** predial_task **/
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