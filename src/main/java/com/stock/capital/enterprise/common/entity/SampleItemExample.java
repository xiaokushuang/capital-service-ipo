package com.stock.capital.enterprise.common.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SampleItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SampleItemExample() {
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

        public Criteria andSampleIdIsNull() {
            addCriterion("sample_id is null");
            return (Criteria) this;
        }

        public Criteria andSampleIdIsNotNull() {
            addCriterion("sample_id is not null");
            return (Criteria) this;
        }

        public Criteria andSampleIdEqualTo(String value) {
            addCriterion("sample_id =", value, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdNotEqualTo(String value) {
            addCriterion("sample_id <>", value, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdGreaterThan(String value) {
            addCriterion("sample_id >", value, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdGreaterThanOrEqualTo(String value) {
            addCriterion("sample_id >=", value, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdLessThan(String value) {
            addCriterion("sample_id <", value, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdLessThanOrEqualTo(String value) {
            addCriterion("sample_id <=", value, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdLike(String value) {
            addCriterion("sample_id like", value, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdNotLike(String value) {
            addCriterion("sample_id not like", value, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdIn(List<String> values) {
            addCriterion("sample_id in", values, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdNotIn(List<String> values) {
            addCriterion("sample_id not in", values, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdBetween(String value1, String value2) {
            addCriterion("sample_id between", value1, value2, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdNotBetween(String value1, String value2) {
            addCriterion("sample_id not between", value1, value2, "sampleId");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNull() {
            addCriterion("item_name is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("item_name is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("item_name =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("item_name <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("item_name >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("item_name >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("item_name <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("item_name <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("item_name like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("item_name not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("item_name in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("item_name not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("item_name between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("item_name not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemTypeIsNull() {
            addCriterion("item_type is null");
            return (Criteria) this;
        }

        public Criteria andItemTypeIsNotNull() {
            addCriterion("item_type is not null");
            return (Criteria) this;
        }

        public Criteria andItemTypeEqualTo(String value) {
            addCriterion("item_type =", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotEqualTo(String value) {
            addCriterion("item_type <>", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeGreaterThan(String value) {
            addCriterion("item_type >", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeGreaterThanOrEqualTo(String value) {
            addCriterion("item_type >=", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLessThan(String value) {
            addCriterion("item_type <", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLessThanOrEqualTo(String value) {
            addCriterion("item_type <=", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLike(String value) {
            addCriterion("item_type like", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotLike(String value) {
            addCriterion("item_type not like", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeIn(List<String> values) {
            addCriterion("item_type in", values, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotIn(List<String> values) {
            addCriterion("item_type not in", values, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeBetween(String value1, String value2) {
            addCriterion("item_type between", value1, value2, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotBetween(String value1, String value2) {
            addCriterion("item_type not between", value1, value2, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemDateIsNull() {
            addCriterion("item_date is null");
            return (Criteria) this;
        }

        public Criteria andItemDateIsNotNull() {
            addCriterion("item_date is not null");
            return (Criteria) this;
        }

        public Criteria andItemDateEqualTo(Date value) {
            addCriterionForJDBCDate("item_date =", value, "itemDate");
            return (Criteria) this;
        }

        public Criteria andItemDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("item_date <>", value, "itemDate");
            return (Criteria) this;
        }

        public Criteria andItemDateGreaterThan(Date value) {
            addCriterionForJDBCDate("item_date >", value, "itemDate");
            return (Criteria) this;
        }

        public Criteria andItemDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("item_date >=", value, "itemDate");
            return (Criteria) this;
        }

        public Criteria andItemDateLessThan(Date value) {
            addCriterionForJDBCDate("item_date <", value, "itemDate");
            return (Criteria) this;
        }

        public Criteria andItemDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("item_date <=", value, "itemDate");
            return (Criteria) this;
        }

        public Criteria andItemDateIn(List<Date> values) {
            addCriterionForJDBCDate("item_date in", values, "itemDate");
            return (Criteria) this;
        }

        public Criteria andItemDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("item_date not in", values, "itemDate");
            return (Criteria) this;
        }

        public Criteria andItemDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("item_date between", value1, value2, "itemDate");
            return (Criteria) this;
        }

        public Criteria andItemDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("item_date not between", value1, value2, "itemDate");
            return (Criteria) this;
        }

        public Criteria andItemTimeIsNull() {
            addCriterion("item_time is null");
            return (Criteria) this;
        }

        public Criteria andItemTimeIsNotNull() {
            addCriterion("item_time is not null");
            return (Criteria) this;
        }

        public Criteria andItemTimeEqualTo(Date value) {
            addCriterion("item_time =", value, "itemTime");
            return (Criteria) this;
        }

        public Criteria andItemTimeNotEqualTo(Date value) {
            addCriterion("item_time <>", value, "itemTime");
            return (Criteria) this;
        }

        public Criteria andItemTimeGreaterThan(Date value) {
            addCriterion("item_time >", value, "itemTime");
            return (Criteria) this;
        }

        public Criteria andItemTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("item_time >=", value, "itemTime");
            return (Criteria) this;
        }

        public Criteria andItemTimeLessThan(Date value) {
            addCriterion("item_time <", value, "itemTime");
            return (Criteria) this;
        }

        public Criteria andItemTimeLessThanOrEqualTo(Date value) {
            addCriterion("item_time <=", value, "itemTime");
            return (Criteria) this;
        }

        public Criteria andItemTimeIn(List<Date> values) {
            addCriterion("item_time in", values, "itemTime");
            return (Criteria) this;
        }

        public Criteria andItemTimeNotIn(List<Date> values) {
            addCriterion("item_time not in", values, "itemTime");
            return (Criteria) this;
        }

        public Criteria andItemTimeBetween(Date value1, Date value2) {
            addCriterion("item_time between", value1, value2, "itemTime");
            return (Criteria) this;
        }

        public Criteria andItemTimeNotBetween(Date value1, Date value2) {
            addCriterion("item_time not between", value1, value2, "itemTime");
            return (Criteria) this;
        }

        public Criteria andItemCheckboxIsNull() {
            addCriterion("item_checkbox is null");
            return (Criteria) this;
        }

        public Criteria andItemCheckboxIsNotNull() {
            addCriterion("item_checkbox is not null");
            return (Criteria) this;
        }

        public Criteria andItemCheckboxEqualTo(String value) {
            addCriterion("item_checkbox =", value, "itemCheckbox");
            return (Criteria) this;
        }

        public Criteria andItemCheckboxNotEqualTo(String value) {
            addCriterion("item_checkbox <>", value, "itemCheckbox");
            return (Criteria) this;
        }

        public Criteria andItemCheckboxGreaterThan(String value) {
            addCriterion("item_checkbox >", value, "itemCheckbox");
            return (Criteria) this;
        }

        public Criteria andItemCheckboxGreaterThanOrEqualTo(String value) {
            addCriterion("item_checkbox >=", value, "itemCheckbox");
            return (Criteria) this;
        }

        public Criteria andItemCheckboxLessThan(String value) {
            addCriterion("item_checkbox <", value, "itemCheckbox");
            return (Criteria) this;
        }

        public Criteria andItemCheckboxLessThanOrEqualTo(String value) {
            addCriterion("item_checkbox <=", value, "itemCheckbox");
            return (Criteria) this;
        }

        public Criteria andItemCheckboxLike(String value) {
            addCriterion("item_checkbox like", value, "itemCheckbox");
            return (Criteria) this;
        }

        public Criteria andItemCheckboxNotLike(String value) {
            addCriterion("item_checkbox not like", value, "itemCheckbox");
            return (Criteria) this;
        }

        public Criteria andItemCheckboxIn(List<String> values) {
            addCriterion("item_checkbox in", values, "itemCheckbox");
            return (Criteria) this;
        }

        public Criteria andItemCheckboxNotIn(List<String> values) {
            addCriterion("item_checkbox not in", values, "itemCheckbox");
            return (Criteria) this;
        }

        public Criteria andItemCheckboxBetween(String value1, String value2) {
            addCriterion("item_checkbox between", value1, value2, "itemCheckbox");
            return (Criteria) this;
        }

        public Criteria andItemCheckboxNotBetween(String value1, String value2) {
            addCriterion("item_checkbox not between", value1, value2, "itemCheckbox");
            return (Criteria) this;
        }

        public Criteria andItemRadiobuttonIsNull() {
            addCriterion("item_radiobutton is null");
            return (Criteria) this;
        }

        public Criteria andItemRadiobuttonIsNotNull() {
            addCriterion("item_radiobutton is not null");
            return (Criteria) this;
        }

        public Criteria andItemRadiobuttonEqualTo(String value) {
            addCriterion("item_radiobutton =", value, "itemRadiobutton");
            return (Criteria) this;
        }

        public Criteria andItemRadiobuttonNotEqualTo(String value) {
            addCriterion("item_radiobutton <>", value, "itemRadiobutton");
            return (Criteria) this;
        }

        public Criteria andItemRadiobuttonGreaterThan(String value) {
            addCriterion("item_radiobutton >", value, "itemRadiobutton");
            return (Criteria) this;
        }

        public Criteria andItemRadiobuttonGreaterThanOrEqualTo(String value) {
            addCriterion("item_radiobutton >=", value, "itemRadiobutton");
            return (Criteria) this;
        }

        public Criteria andItemRadiobuttonLessThan(String value) {
            addCriterion("item_radiobutton <", value, "itemRadiobutton");
            return (Criteria) this;
        }

        public Criteria andItemRadiobuttonLessThanOrEqualTo(String value) {
            addCriterion("item_radiobutton <=", value, "itemRadiobutton");
            return (Criteria) this;
        }

        public Criteria andItemRadiobuttonLike(String value) {
            addCriterion("item_radiobutton like", value, "itemRadiobutton");
            return (Criteria) this;
        }

        public Criteria andItemRadiobuttonNotLike(String value) {
            addCriterion("item_radiobutton not like", value, "itemRadiobutton");
            return (Criteria) this;
        }

        public Criteria andItemRadiobuttonIn(List<String> values) {
            addCriterion("item_radiobutton in", values, "itemRadiobutton");
            return (Criteria) this;
        }

        public Criteria andItemRadiobuttonNotIn(List<String> values) {
            addCriterion("item_radiobutton not in", values, "itemRadiobutton");
            return (Criteria) this;
        }

        public Criteria andItemRadiobuttonBetween(String value1, String value2) {
            addCriterion("item_radiobutton between", value1, value2, "itemRadiobutton");
            return (Criteria) this;
        }

        public Criteria andItemRadiobuttonNotBetween(String value1, String value2) {
            addCriterion("item_radiobutton not between", value1, value2, "itemRadiobutton");
            return (Criteria) this;
        }

        public Criteria andItemNumIntIsNull() {
            addCriterion("item_num_int is null");
            return (Criteria) this;
        }

        public Criteria andItemNumIntIsNotNull() {
            addCriterion("item_num_int is not null");
            return (Criteria) this;
        }

        public Criteria andItemNumIntEqualTo(Integer value) {
            addCriterion("item_num_int =", value, "itemNumInt");
            return (Criteria) this;
        }

        public Criteria andItemNumIntNotEqualTo(Integer value) {
            addCriterion("item_num_int <>", value, "itemNumInt");
            return (Criteria) this;
        }

        public Criteria andItemNumIntGreaterThan(Integer value) {
            addCriterion("item_num_int >", value, "itemNumInt");
            return (Criteria) this;
        }

        public Criteria andItemNumIntGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_num_int >=", value, "itemNumInt");
            return (Criteria) this;
        }

        public Criteria andItemNumIntLessThan(Integer value) {
            addCriterion("item_num_int <", value, "itemNumInt");
            return (Criteria) this;
        }

        public Criteria andItemNumIntLessThanOrEqualTo(Integer value) {
            addCriterion("item_num_int <=", value, "itemNumInt");
            return (Criteria) this;
        }

        public Criteria andItemNumIntIn(List<Integer> values) {
            addCriterion("item_num_int in", values, "itemNumInt");
            return (Criteria) this;
        }

        public Criteria andItemNumIntNotIn(List<Integer> values) {
            addCriterion("item_num_int not in", values, "itemNumInt");
            return (Criteria) this;
        }

        public Criteria andItemNumIntBetween(Integer value1, Integer value2) {
            addCriterion("item_num_int between", value1, value2, "itemNumInt");
            return (Criteria) this;
        }

        public Criteria andItemNumIntNotBetween(Integer value1, Integer value2) {
            addCriterion("item_num_int not between", value1, value2, "itemNumInt");
            return (Criteria) this;
        }

        public Criteria andItemNumDoubleIsNull() {
            addCriterion("item_num_double is null");
            return (Criteria) this;
        }

        public Criteria andItemNumDoubleIsNotNull() {
            addCriterion("item_num_double is not null");
            return (Criteria) this;
        }

        public Criteria andItemNumDoubleEqualTo(BigDecimal value) {
            addCriterion("item_num_double =", value, "itemNumDouble");
            return (Criteria) this;
        }

        public Criteria andItemNumDoubleNotEqualTo(BigDecimal value) {
            addCriterion("item_num_double <>", value, "itemNumDouble");
            return (Criteria) this;
        }

        public Criteria andItemNumDoubleGreaterThan(BigDecimal value) {
            addCriterion("item_num_double >", value, "itemNumDouble");
            return (Criteria) this;
        }

        public Criteria andItemNumDoubleGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("item_num_double >=", value, "itemNumDouble");
            return (Criteria) this;
        }

        public Criteria andItemNumDoubleLessThan(BigDecimal value) {
            addCriterion("item_num_double <", value, "itemNumDouble");
            return (Criteria) this;
        }

        public Criteria andItemNumDoubleLessThanOrEqualTo(BigDecimal value) {
            addCriterion("item_num_double <=", value, "itemNumDouble");
            return (Criteria) this;
        }

        public Criteria andItemNumDoubleIn(List<BigDecimal> values) {
            addCriterion("item_num_double in", values, "itemNumDouble");
            return (Criteria) this;
        }

        public Criteria andItemNumDoubleNotIn(List<BigDecimal> values) {
            addCriterion("item_num_double not in", values, "itemNumDouble");
            return (Criteria) this;
        }

        public Criteria andItemNumDoubleBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("item_num_double between", value1, value2, "itemNumDouble");
            return (Criteria) this;
        }

        public Criteria andItemNumDoubleNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("item_num_double not between", value1, value2, "itemNumDouble");
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