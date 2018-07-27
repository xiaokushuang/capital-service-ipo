package com.stock.capital.enterprise.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class LetterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LetterExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLetterNameIsNull() {
            addCriterion("letter_name is null");
            return (Criteria) this;
        }

        public Criteria andLetterNameIsNotNull() {
            addCriterion("letter_name is not null");
            return (Criteria) this;
        }

        public Criteria andLetterNameEqualTo(String value) {
            addCriterion("letter_name =", value, "letterName");
            return (Criteria) this;
        }

        public Criteria andLetterNameNotEqualTo(String value) {
            addCriterion("letter_name <>", value, "letterName");
            return (Criteria) this;
        }

        public Criteria andLetterNameGreaterThan(String value) {
            addCriterion("letter_name >", value, "letterName");
            return (Criteria) this;
        }

        public Criteria andLetterNameGreaterThanOrEqualTo(String value) {
            addCriterion("letter_name >=", value, "letterName");
            return (Criteria) this;
        }

        public Criteria andLetterNameLessThan(String value) {
            addCriterion("letter_name <", value, "letterName");
            return (Criteria) this;
        }

        public Criteria andLetterNameLessThanOrEqualTo(String value) {
            addCriterion("letter_name <=", value, "letterName");
            return (Criteria) this;
        }

        public Criteria andLetterNameLike(String value) {
            addCriterion("letter_name like", value, "letterName");
            return (Criteria) this;
        }

        public Criteria andLetterNameNotLike(String value) {
            addCriterion("letter_name not like", value, "letterName");
            return (Criteria) this;
        }

        public Criteria andLetterNameIn(List<String> values) {
            addCriterion("letter_name in", values, "letterName");
            return (Criteria) this;
        }

        public Criteria andLetterNameNotIn(List<String> values) {
            addCriterion("letter_name not in", values, "letterName");
            return (Criteria) this;
        }

        public Criteria andLetterNameBetween(String value1, String value2) {
            addCriterion("letter_name between", value1, value2, "letterName");
            return (Criteria) this;
        }

        public Criteria andLetterNameNotBetween(String value1, String value2) {
            addCriterion("letter_name not between", value1, value2, "letterName");
            return (Criteria) this;
        }

        public Criteria andLetterNameOldIsNull() {
            addCriterion("letter_name_old is null");
            return (Criteria) this;
        }

        public Criteria andLetterNameOldIsNotNull() {
            addCriterion("letter_name_old is not null");
            return (Criteria) this;
        }

        public Criteria andLetterNameOldEqualTo(String value) {
            addCriterion("letter_name_old =", value, "letterNameOld");
            return (Criteria) this;
        }

        public Criteria andLetterNameOldNotEqualTo(String value) {
            addCriterion("letter_name_old <>", value, "letterNameOld");
            return (Criteria) this;
        }

        public Criteria andLetterNameOldGreaterThan(String value) {
            addCriterion("letter_name_old >", value, "letterNameOld");
            return (Criteria) this;
        }

        public Criteria andLetterNameOldGreaterThanOrEqualTo(String value) {
            addCriterion("letter_name_old >=", value, "letterNameOld");
            return (Criteria) this;
        }

        public Criteria andLetterNameOldLessThan(String value) {
            addCriterion("letter_name_old <", value, "letterNameOld");
            return (Criteria) this;
        }

        public Criteria andLetterNameOldLessThanOrEqualTo(String value) {
            addCriterion("letter_name_old <=", value, "letterNameOld");
            return (Criteria) this;
        }

        public Criteria andLetterNameOldLike(String value) {
            addCriterion("letter_name_old like", value, "letterNameOld");
            return (Criteria) this;
        }

        public Criteria andLetterNameOldNotLike(String value) {
            addCriterion("letter_name_old not like", value, "letterNameOld");
            return (Criteria) this;
        }

        public Criteria andLetterNameOldIn(List<String> values) {
            addCriterion("letter_name_old in", values, "letterNameOld");
            return (Criteria) this;
        }

        public Criteria andLetterNameOldNotIn(List<String> values) {
            addCriterion("letter_name_old not in", values, "letterNameOld");
            return (Criteria) this;
        }

        public Criteria andLetterNameOldBetween(String value1, String value2) {
            addCriterion("letter_name_old between", value1, value2, "letterNameOld");
            return (Criteria) this;
        }

        public Criteria andLetterNameOldNotBetween(String value1, String value2) {
            addCriterion("letter_name_old not between", value1, value2, "letterNameOld");
            return (Criteria) this;
        }

        public Criteria andLetterOrgIsNull() {
            addCriterion("letter_org is null");
            return (Criteria) this;
        }

        public Criteria andLetterOrgIsNotNull() {
            addCriterion("letter_org is not null");
            return (Criteria) this;
        }

        public Criteria andLetterOrgEqualTo(String value) {
            addCriterion("letter_org =", value, "letterOrg");
            return (Criteria) this;
        }

        public Criteria andLetterOrgNotEqualTo(String value) {
            addCriterion("letter_org <>", value, "letterOrg");
            return (Criteria) this;
        }

        public Criteria andLetterOrgGreaterThan(String value) {
            addCriterion("letter_org >", value, "letterOrg");
            return (Criteria) this;
        }

        public Criteria andLetterOrgGreaterThanOrEqualTo(String value) {
            addCriterion("letter_org >=", value, "letterOrg");
            return (Criteria) this;
        }

        public Criteria andLetterOrgLessThan(String value) {
            addCriterion("letter_org <", value, "letterOrg");
            return (Criteria) this;
        }

        public Criteria andLetterOrgLessThanOrEqualTo(String value) {
            addCriterion("letter_org <=", value, "letterOrg");
            return (Criteria) this;
        }

        public Criteria andLetterOrgLike(String value) {
            addCriterion("letter_org like", value, "letterOrg");
            return (Criteria) this;
        }

        public Criteria andLetterOrgNotLike(String value) {
            addCriterion("letter_org not like", value, "letterOrg");
            return (Criteria) this;
        }

        public Criteria andLetterOrgIn(List<String> values) {
            addCriterion("letter_org in", values, "letterOrg");
            return (Criteria) this;
        }

        public Criteria andLetterOrgNotIn(List<String> values) {
            addCriterion("letter_org not in", values, "letterOrg");
            return (Criteria) this;
        }

        public Criteria andLetterOrgBetween(String value1, String value2) {
            addCriterion("letter_org between", value1, value2, "letterOrg");
            return (Criteria) this;
        }

        public Criteria andLetterOrgNotBetween(String value1, String value2) {
            addCriterion("letter_org not between", value1, value2, "letterOrg");
            return (Criteria) this;
        }

        public Criteria andLetterDateIsNull() {
            addCriterion("letter_date is null");
            return (Criteria) this;
        }

        public Criteria andLetterDateIsNotNull() {
            addCriterion("letter_date is not null");
            return (Criteria) this;
        }

        public Criteria andLetterDateEqualTo(Date value) {
            addCriterionForJDBCDate("letter_date =", value, "letterDate");
            return (Criteria) this;
        }

        public Criteria andLetterDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("letter_date <>", value, "letterDate");
            return (Criteria) this;
        }

        public Criteria andLetterDateGreaterThan(Date value) {
            addCriterionForJDBCDate("letter_date >", value, "letterDate");
            return (Criteria) this;
        }

        public Criteria andLetterDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("letter_date >=", value, "letterDate");
            return (Criteria) this;
        }

        public Criteria andLetterDateLessThan(Date value) {
            addCriterionForJDBCDate("letter_date <", value, "letterDate");
            return (Criteria) this;
        }

        public Criteria andLetterDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("letter_date <=", value, "letterDate");
            return (Criteria) this;
        }

        public Criteria andLetterDateIn(List<Date> values) {
            addCriterionForJDBCDate("letter_date in", values, "letterDate");
            return (Criteria) this;
        }

        public Criteria andLetterDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("letter_date not in", values, "letterDate");
            return (Criteria) this;
        }

        public Criteria andLetterDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("letter_date between", value1, value2, "letterDate");
            return (Criteria) this;
        }

        public Criteria andLetterDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("letter_date not between", value1, value2, "letterDate");
            return (Criteria) this;
        }

        public Criteria andLetterTypeIsNull() {
            addCriterion("letter_type is null");
            return (Criteria) this;
        }

        public Criteria andLetterTypeIsNotNull() {
            addCriterion("letter_type is not null");
            return (Criteria) this;
        }

        public Criteria andLetterTypeEqualTo(String value) {
            addCriterion("letter_type =", value, "letterType");
            return (Criteria) this;
        }

        public Criteria andLetterTypeNotEqualTo(String value) {
            addCriterion("letter_type <>", value, "letterType");
            return (Criteria) this;
        }

        public Criteria andLetterTypeGreaterThan(String value) {
            addCriterion("letter_type >", value, "letterType");
            return (Criteria) this;
        }

        public Criteria andLetterTypeGreaterThanOrEqualTo(String value) {
            addCriterion("letter_type >=", value, "letterType");
            return (Criteria) this;
        }

        public Criteria andLetterTypeLessThan(String value) {
            addCriterion("letter_type <", value, "letterType");
            return (Criteria) this;
        }

        public Criteria andLetterTypeLessThanOrEqualTo(String value) {
            addCriterion("letter_type <=", value, "letterType");
            return (Criteria) this;
        }

        public Criteria andLetterTypeLike(String value) {
            addCriterion("letter_type like", value, "letterType");
            return (Criteria) this;
        }

        public Criteria andLetterTypeNotLike(String value) {
            addCriterion("letter_type not like", value, "letterType");
            return (Criteria) this;
        }

        public Criteria andLetterTypeIn(List<String> values) {
            addCriterion("letter_type in", values, "letterType");
            return (Criteria) this;
        }

        public Criteria andLetterTypeNotIn(List<String> values) {
            addCriterion("letter_type not in", values, "letterType");
            return (Criteria) this;
        }

        public Criteria andLetterTypeBetween(String value1, String value2) {
            addCriterion("letter_type between", value1, value2, "letterType");
            return (Criteria) this;
        }

        public Criteria andLetterTypeNotBetween(String value1, String value2) {
            addCriterion("letter_type not between", value1, value2, "letterType");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterionForJDBCDate("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateIsNull() {
            addCriterion("return_date is null");
            return (Criteria) this;
        }

        public Criteria andReturnDateIsNotNull() {
            addCriterion("return_date is not null");
            return (Criteria) this;
        }

        public Criteria andReturnDateEqualTo(Date value) {
            addCriterionForJDBCDate("return_date =", value, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("return_date <>", value, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateGreaterThan(Date value) {
            addCriterionForJDBCDate("return_date >", value, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("return_date >=", value, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateLessThan(Date value) {
            addCriterionForJDBCDate("return_date <", value, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("return_date <=", value, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateIn(List<Date> values) {
            addCriterionForJDBCDate("return_date in", values, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("return_date not in", values, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("return_date between", value1, value2, "returnDate");
            return (Criteria) this;
        }

        public Criteria andReturnDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("return_date not between", value1, value2, "returnDate");
            return (Criteria) this;
        }

        public Criteria andLetterStatusIsNull() {
            addCriterion("letter_status is null");
            return (Criteria) this;
        }

        public Criteria andLetterStatusIsNotNull() {
            addCriterion("letter_status is not null");
            return (Criteria) this;
        }

        public Criteria andLetterStatusEqualTo(String value) {
            addCriterion("letter_status =", value, "letterStatus");
            return (Criteria) this;
        }

        public Criteria andLetterStatusNotEqualTo(String value) {
            addCriterion("letter_status <>", value, "letterStatus");
            return (Criteria) this;
        }

        public Criteria andLetterStatusGreaterThan(String value) {
            addCriterion("letter_status >", value, "letterStatus");
            return (Criteria) this;
        }

        public Criteria andLetterStatusGreaterThanOrEqualTo(String value) {
            addCriterion("letter_status >=", value, "letterStatus");
            return (Criteria) this;
        }

        public Criteria andLetterStatusLessThan(String value) {
            addCriterion("letter_status <", value, "letterStatus");
            return (Criteria) this;
        }

        public Criteria andLetterStatusLessThanOrEqualTo(String value) {
            addCriterion("letter_status <=", value, "letterStatus");
            return (Criteria) this;
        }

        public Criteria andLetterStatusLike(String value) {
            addCriterion("letter_status like", value, "letterStatus");
            return (Criteria) this;
        }

        public Criteria andLetterStatusNotLike(String value) {
            addCriterion("letter_status not like", value, "letterStatus");
            return (Criteria) this;
        }

        public Criteria andLetterStatusIn(List<String> values) {
            addCriterion("letter_status in", values, "letterStatus");
            return (Criteria) this;
        }

        public Criteria andLetterStatusNotIn(List<String> values) {
            addCriterion("letter_status not in", values, "letterStatus");
            return (Criteria) this;
        }

        public Criteria andLetterStatusBetween(String value1, String value2) {
            addCriterion("letter_status between", value1, value2, "letterStatus");
            return (Criteria) this;
        }

        public Criteria andLetterStatusNotBetween(String value1, String value2) {
            addCriterion("letter_status not between", value1, value2, "letterStatus");
            return (Criteria) this;
        }

        public Criteria andChargeIsNull() {
            addCriterion("charge is null");
            return (Criteria) this;
        }

        public Criteria andChargeIsNotNull() {
            addCriterion("charge is not null");
            return (Criteria) this;
        }

        public Criteria andChargeEqualTo(String value) {
            addCriterion("charge =", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeNotEqualTo(String value) {
            addCriterion("charge <>", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeGreaterThan(String value) {
            addCriterion("charge >", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeGreaterThanOrEqualTo(String value) {
            addCriterion("charge >=", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeLessThan(String value) {
            addCriterion("charge <", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeLessThanOrEqualTo(String value) {
            addCriterion("charge <=", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeLike(String value) {
            addCriterion("charge like", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeNotLike(String value) {
            addCriterion("charge not like", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeIn(List<String> values) {
            addCriterion("charge in", values, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeNotIn(List<String> values) {
            addCriterion("charge not in", values, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeBetween(String value1, String value2) {
            addCriterion("charge between", value1, value2, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeNotBetween(String value1, String value2) {
            addCriterion("charge not between", value1, value2, "charge");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andLetterPersonIsNull() {
            addCriterion("letter_person is null");
            return (Criteria) this;
        }

        public Criteria andLetterPersonIsNotNull() {
            addCriterion("letter_person is not null");
            return (Criteria) this;
        }

        public Criteria andLetterPersonEqualTo(String value) {
            addCriterion("letter_person =", value, "letterPerson");
            return (Criteria) this;
        }

        public Criteria andLetterPersonNotEqualTo(String value) {
            addCriterion("letter_person <>", value, "letterPerson");
            return (Criteria) this;
        }

        public Criteria andLetterPersonGreaterThan(String value) {
            addCriterion("letter_person >", value, "letterPerson");
            return (Criteria) this;
        }

        public Criteria andLetterPersonGreaterThanOrEqualTo(String value) {
            addCriterion("letter_person >=", value, "letterPerson");
            return (Criteria) this;
        }

        public Criteria andLetterPersonLessThan(String value) {
            addCriterion("letter_person <", value, "letterPerson");
            return (Criteria) this;
        }

        public Criteria andLetterPersonLessThanOrEqualTo(String value) {
            addCriterion("letter_person <=", value, "letterPerson");
            return (Criteria) this;
        }

        public Criteria andLetterPersonLike(String value) {
            addCriterion("letter_person like", value, "letterPerson");
            return (Criteria) this;
        }

        public Criteria andLetterPersonNotLike(String value) {
            addCriterion("letter_person not like", value, "letterPerson");
            return (Criteria) this;
        }

        public Criteria andLetterPersonIn(List<String> values) {
            addCriterion("letter_person in", values, "letterPerson");
            return (Criteria) this;
        }

        public Criteria andLetterPersonNotIn(List<String> values) {
            addCriterion("letter_person not in", values, "letterPerson");
            return (Criteria) this;
        }

        public Criteria andLetterPersonBetween(String value1, String value2) {
            addCriterion("letter_person between", value1, value2, "letterPerson");
            return (Criteria) this;
        }

        public Criteria andLetterPersonNotBetween(String value1, String value2) {
            addCriterion("letter_person not between", value1, value2, "letterPerson");
            return (Criteria) this;
        }

        public Criteria andRsTypeIsNull() {
            addCriterion("rs_type is null");
            return (Criteria) this;
        }

        public Criteria andRsTypeIsNotNull() {
            addCriterion("rs_type is not null");
            return (Criteria) this;
        }

        public Criteria andRsTypeEqualTo(String value) {
            addCriterion("rs_type =", value, "rsType");
            return (Criteria) this;
        }

        public Criteria andRsTypeNotEqualTo(String value) {
            addCriterion("rs_type <>", value, "rsType");
            return (Criteria) this;
        }

        public Criteria andRsTypeGreaterThan(String value) {
            addCriterion("rs_type >", value, "rsType");
            return (Criteria) this;
        }

        public Criteria andRsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("rs_type >=", value, "rsType");
            return (Criteria) this;
        }

        public Criteria andRsTypeLessThan(String value) {
            addCriterion("rs_type <", value, "rsType");
            return (Criteria) this;
        }

        public Criteria andRsTypeLessThanOrEqualTo(String value) {
            addCriterion("rs_type <=", value, "rsType");
            return (Criteria) this;
        }

        public Criteria andRsTypeLike(String value) {
            addCriterion("rs_type like", value, "rsType");
            return (Criteria) this;
        }

        public Criteria andRsTypeNotLike(String value) {
            addCriterion("rs_type not like", value, "rsType");
            return (Criteria) this;
        }

        public Criteria andRsTypeIn(List<String> values) {
            addCriterion("rs_type in", values, "rsType");
            return (Criteria) this;
        }

        public Criteria andRsTypeNotIn(List<String> values) {
            addCriterion("rs_type not in", values, "rsType");
            return (Criteria) this;
        }

        public Criteria andRsTypeBetween(String value1, String value2) {
            addCriterion("rs_type between", value1, value2, "rsType");
            return (Criteria) this;
        }

        public Criteria andRsTypeNotBetween(String value1, String value2) {
            addCriterion("rs_type not between", value1, value2, "rsType");
            return (Criteria) this;
        }

        public Criteria andYnFlagIsNull() {
            addCriterion("yn_flag is null");
            return (Criteria) this;
        }

        public Criteria andYnFlagIsNotNull() {
            addCriterion("yn_flag is not null");
            return (Criteria) this;
        }

        public Criteria andYnFlagEqualTo(String value) {
            addCriterion("yn_flag =", value, "ynFlag");
            return (Criteria) this;
        }

        public Criteria andYnFlagNotEqualTo(String value) {
            addCriterion("yn_flag <>", value, "ynFlag");
            return (Criteria) this;
        }

        public Criteria andYnFlagGreaterThan(String value) {
            addCriterion("yn_flag >", value, "ynFlag");
            return (Criteria) this;
        }

        public Criteria andYnFlagGreaterThanOrEqualTo(String value) {
            addCriterion("yn_flag >=", value, "ynFlag");
            return (Criteria) this;
        }

        public Criteria andYnFlagLessThan(String value) {
            addCriterion("yn_flag <", value, "ynFlag");
            return (Criteria) this;
        }

        public Criteria andYnFlagLessThanOrEqualTo(String value) {
            addCriterion("yn_flag <=", value, "ynFlag");
            return (Criteria) this;
        }

        public Criteria andYnFlagLike(String value) {
            addCriterion("yn_flag like", value, "ynFlag");
            return (Criteria) this;
        }

        public Criteria andYnFlagNotLike(String value) {
            addCriterion("yn_flag not like", value, "ynFlag");
            return (Criteria) this;
        }

        public Criteria andYnFlagIn(List<String> values) {
            addCriterion("yn_flag in", values, "ynFlag");
            return (Criteria) this;
        }

        public Criteria andYnFlagNotIn(List<String> values) {
            addCriterion("yn_flag not in", values, "ynFlag");
            return (Criteria) this;
        }

        public Criteria andYnFlagBetween(String value1, String value2) {
            addCriterion("yn_flag between", value1, value2, "ynFlag");
            return (Criteria) this;
        }

        public Criteria andYnFlagNotBetween(String value1, String value2) {
            addCriterion("yn_flag not between", value1, value2, "ynFlag");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(String value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(String value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(String value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(String value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLike(String value) {
            addCriterion("company_id like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotLike(String value) {
            addCriterion("company_id not like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<String> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<String> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(String value1, String value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(String value1, String value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andLetterValidIsNull() {
            addCriterion("letter_valid is null");
            return (Criteria) this;
        }

        public Criteria andLetterValidIsNotNull() {
            addCriterion("letter_valid is not null");
            return (Criteria) this;
        }

        public Criteria andLetterValidEqualTo(String value) {
            addCriterion("letter_valid =", value, "letterValid");
            return (Criteria) this;
        }

        public Criteria andLetterValidNotEqualTo(String value) {
            addCriterion("letter_valid <>", value, "letterValid");
            return (Criteria) this;
        }

        public Criteria andLetterValidGreaterThan(String value) {
            addCriterion("letter_valid >", value, "letterValid");
            return (Criteria) this;
        }

        public Criteria andLetterValidGreaterThanOrEqualTo(String value) {
            addCriterion("letter_valid >=", value, "letterValid");
            return (Criteria) this;
        }

        public Criteria andLetterValidLessThan(String value) {
            addCriterion("letter_valid <", value, "letterValid");
            return (Criteria) this;
        }

        public Criteria andLetterValidLessThanOrEqualTo(String value) {
            addCriterion("letter_valid <=", value, "letterValid");
            return (Criteria) this;
        }

        public Criteria andLetterValidLike(String value) {
            addCriterion("letter_valid like", value, "letterValid");
            return (Criteria) this;
        }

        public Criteria andLetterValidNotLike(String value) {
            addCriterion("letter_valid not like", value, "letterValid");
            return (Criteria) this;
        }

        public Criteria andLetterValidIn(List<String> values) {
            addCriterion("letter_valid in", values, "letterValid");
            return (Criteria) this;
        }

        public Criteria andLetterValidNotIn(List<String> values) {
            addCriterion("letter_valid not in", values, "letterValid");
            return (Criteria) this;
        }

        public Criteria andLetterValidBetween(String value1, String value2) {
            addCriterion("letter_valid between", value1, value2, "letterValid");
            return (Criteria) this;
        }

        public Criteria andLetterValidNotBetween(String value1, String value2) {
            addCriterion("letter_valid not between", value1, value2, "letterValid");
            return (Criteria) this;
        }

        public Criteria andLabelStatusIsNull() {
            addCriterion("label_status is null");
            return (Criteria) this;
        }

        public Criteria andLabelStatusIsNotNull() {
            addCriterion("label_status is not null");
            return (Criteria) this;
        }

        public Criteria andLabelStatusEqualTo(String value) {
            addCriterion("label_status =", value, "labelStatus");
            return (Criteria) this;
        }

        public Criteria andLabelStatusNotEqualTo(String value) {
            addCriterion("label_status <>", value, "labelStatus");
            return (Criteria) this;
        }

        public Criteria andLabelStatusGreaterThan(String value) {
            addCriterion("label_status >", value, "labelStatus");
            return (Criteria) this;
        }

        public Criteria andLabelStatusGreaterThanOrEqualTo(String value) {
            addCriterion("label_status >=", value, "labelStatus");
            return (Criteria) this;
        }

        public Criteria andLabelStatusLessThan(String value) {
            addCriterion("label_status <", value, "labelStatus");
            return (Criteria) this;
        }

        public Criteria andLabelStatusLessThanOrEqualTo(String value) {
            addCriterion("label_status <=", value, "labelStatus");
            return (Criteria) this;
        }

        public Criteria andLabelStatusLike(String value) {
            addCriterion("label_status like", value, "labelStatus");
            return (Criteria) this;
        }

        public Criteria andLabelStatusNotLike(String value) {
            addCriterion("label_status not like", value, "labelStatus");
            return (Criteria) this;
        }

        public Criteria andLabelStatusIn(List<String> values) {
            addCriterion("label_status in", values, "labelStatus");
            return (Criteria) this;
        }

        public Criteria andLabelStatusNotIn(List<String> values) {
            addCriterion("label_status not in", values, "labelStatus");
            return (Criteria) this;
        }

        public Criteria andLabelStatusBetween(String value1, String value2) {
            addCriterion("label_status between", value1, value2, "labelStatus");
            return (Criteria) this;
        }

        public Criteria andLabelStatusNotBetween(String value1, String value2) {
            addCriterion("label_status not between", value1, value2, "labelStatus");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
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

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
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

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCompanyReplyIsNull() {
            addCriterion("company_reply is null");
            return (Criteria) this;
        }

        public Criteria andCompanyReplyIsNotNull() {
            addCriterion("company_reply is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyReplyEqualTo(String value) {
            addCriterion("company_reply =", value, "companyReply");
            return (Criteria) this;
        }

        public Criteria andCompanyReplyNotEqualTo(String value) {
            addCriterion("company_reply <>", value, "companyReply");
            return (Criteria) this;
        }

        public Criteria andCompanyReplyGreaterThan(String value) {
            addCriterion("company_reply >", value, "companyReply");
            return (Criteria) this;
        }

        public Criteria andCompanyReplyGreaterThanOrEqualTo(String value) {
            addCriterion("company_reply >=", value, "companyReply");
            return (Criteria) this;
        }

        public Criteria andCompanyReplyLessThan(String value) {
            addCriterion("company_reply <", value, "companyReply");
            return (Criteria) this;
        }

        public Criteria andCompanyReplyLessThanOrEqualTo(String value) {
            addCriterion("company_reply <=", value, "companyReply");
            return (Criteria) this;
        }

        public Criteria andCompanyReplyLike(String value) {
            addCriterion("company_reply like", value, "companyReply");
            return (Criteria) this;
        }

        public Criteria andCompanyReplyNotLike(String value) {
            addCriterion("company_reply not like", value, "companyReply");
            return (Criteria) this;
        }

        public Criteria andCompanyReplyIn(List<String> values) {
            addCriterion("company_reply in", values, "companyReply");
            return (Criteria) this;
        }

        public Criteria andCompanyReplyNotIn(List<String> values) {
            addCriterion("company_reply not in", values, "companyReply");
            return (Criteria) this;
        }

        public Criteria andCompanyReplyBetween(String value1, String value2) {
            addCriterion("company_reply between", value1, value2, "companyReply");
            return (Criteria) this;
        }

        public Criteria andCompanyReplyNotBetween(String value1, String value2) {
            addCriterion("company_reply not between", value1, value2, "companyReply");
            return (Criteria) this;
        }

        public Criteria andLetterFileNoIsNull() {
            addCriterion("letter_file_no is null");
            return (Criteria) this;
        }

        public Criteria andLetterFileNoIsNotNull() {
            addCriterion("letter_file_no is not null");
            return (Criteria) this;
        }

        public Criteria andLetterFileNoEqualTo(String value) {
            addCriterion("letter_file_no =", value, "letterFileNo");
            return (Criteria) this;
        }

        public Criteria andLetterFileNoNotEqualTo(String value) {
            addCriterion("letter_file_no <>", value, "letterFileNo");
            return (Criteria) this;
        }

        public Criteria andLetterFileNoGreaterThan(String value) {
            addCriterion("letter_file_no >", value, "letterFileNo");
            return (Criteria) this;
        }

        public Criteria andLetterFileNoGreaterThanOrEqualTo(String value) {
            addCriterion("letter_file_no >=", value, "letterFileNo");
            return (Criteria) this;
        }

        public Criteria andLetterFileNoLessThan(String value) {
            addCriterion("letter_file_no <", value, "letterFileNo");
            return (Criteria) this;
        }

        public Criteria andLetterFileNoLessThanOrEqualTo(String value) {
            addCriterion("letter_file_no <=", value, "letterFileNo");
            return (Criteria) this;
        }

        public Criteria andLetterFileNoLike(String value) {
            addCriterion("letter_file_no like", value, "letterFileNo");
            return (Criteria) this;
        }

        public Criteria andLetterFileNoNotLike(String value) {
            addCriterion("letter_file_no not like", value, "letterFileNo");
            return (Criteria) this;
        }

        public Criteria andLetterFileNoIn(List<String> values) {
            addCriterion("letter_file_no in", values, "letterFileNo");
            return (Criteria) this;
        }

        public Criteria andLetterFileNoNotIn(List<String> values) {
            addCriterion("letter_file_no not in", values, "letterFileNo");
            return (Criteria) this;
        }

        public Criteria andLetterFileNoBetween(String value1, String value2) {
            addCriterion("letter_file_no between", value1, value2, "letterFileNo");
            return (Criteria) this;
        }

        public Criteria andLetterFileNoNotBetween(String value1, String value2) {
            addCriterion("letter_file_no not between", value1, value2, "letterFileNo");
            return (Criteria) this;
        }

        public Criteria andSortNoIsNull() {
            addCriterion("sort_no is null");
            return (Criteria) this;
        }

        public Criteria andSortNoIsNotNull() {
            addCriterion("sort_no is not null");
            return (Criteria) this;
        }

        public Criteria andSortNoEqualTo(Integer value) {
            addCriterion("sort_no =", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotEqualTo(Integer value) {
            addCriterion("sort_no <>", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThan(Integer value) {
            addCriterion("sort_no >", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort_no >=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThan(Integer value) {
            addCriterion("sort_no <", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThanOrEqualTo(Integer value) {
            addCriterion("sort_no <=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoIn(List<Integer> values) {
            addCriterion("sort_no in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotIn(List<Integer> values) {
            addCriterion("sort_no not in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoBetween(Integer value1, Integer value2) {
            addCriterion("sort_no between", value1, value2, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotBetween(Integer value1, Integer value2) {
            addCriterion("sort_no not between", value1, value2, "sortNo");
            return (Criteria) this;
        }

        public Criteria andPostStatusIsNull() {
            addCriterion("post_status is null");
            return (Criteria) this;
        }

        public Criteria andPostStatusIsNotNull() {
            addCriterion("post_status is not null");
            return (Criteria) this;
        }

        public Criteria andPostStatusEqualTo(String value) {
            addCriterion("post_status =", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusNotEqualTo(String value) {
            addCriterion("post_status <>", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusGreaterThan(String value) {
            addCriterion("post_status >", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusGreaterThanOrEqualTo(String value) {
            addCriterion("post_status >=", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusLessThan(String value) {
            addCriterion("post_status <", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusLessThanOrEqualTo(String value) {
            addCriterion("post_status <=", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusLike(String value) {
            addCriterion("post_status like", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusNotLike(String value) {
            addCriterion("post_status not like", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusIn(List<String> values) {
            addCriterion("post_status in", values, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusNotIn(List<String> values) {
            addCriterion("post_status not in", values, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusBetween(String value1, String value2) {
            addCriterion("post_status between", value1, value2, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusNotBetween(String value1, String value2) {
            addCriterion("post_status not between", value1, value2, "postStatus");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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