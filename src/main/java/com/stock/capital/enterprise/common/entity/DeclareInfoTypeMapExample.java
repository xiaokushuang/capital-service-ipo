package com.stock.capital.enterprise.common.entity;

import java.util.ArrayList;
import java.util.List;

public class DeclareInfoTypeMapExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeclareInfoTypeMapExample() {
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

        public Criteria andDeclareIdIsNull() {
            addCriterion("declare_id is null");
            return (Criteria) this;
        }

        public Criteria andDeclareIdIsNotNull() {
            addCriterion("declare_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeclareIdEqualTo(String value) {
            addCriterion("declare_id =", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdNotEqualTo(String value) {
            addCriterion("declare_id <>", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdGreaterThan(String value) {
            addCriterion("declare_id >", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdGreaterThanOrEqualTo(String value) {
            addCriterion("declare_id >=", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdLessThan(String value) {
            addCriterion("declare_id <", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdLessThanOrEqualTo(String value) {
            addCriterion("declare_id <=", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdLike(String value) {
            addCriterion("declare_id like", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdNotLike(String value) {
            addCriterion("declare_id not like", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdIn(List<String> values) {
            addCriterion("declare_id in", values, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdNotIn(List<String> values) {
            addCriterion("declare_id not in", values, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdBetween(String value1, String value2) {
            addCriterion("declare_id between", value1, value2, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdNotBetween(String value1, String value2) {
            addCriterion("declare_id not between", value1, value2, "declareId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(String value) {
            addCriterion("type_id =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(String value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(String value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(String value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(String value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLike(String value) {
            addCriterion("type_id like", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotLike(String value) {
            addCriterion("type_id not like", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<String> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<String> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(String value1, String value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(String value1, String value2) {
            addCriterion("type_id not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNameIsNull() {
            addCriterion("declare_type_name is null");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNameIsNotNull() {
            addCriterion("declare_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNameEqualTo(String value) {
            addCriterion("declare_type_name =", value, "declareTypeName");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNameNotEqualTo(String value) {
            addCriterion("declare_type_name <>", value, "declareTypeName");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNameGreaterThan(String value) {
            addCriterion("declare_type_name >", value, "declareTypeName");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("declare_type_name >=", value, "declareTypeName");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNameLessThan(String value) {
            addCriterion("declare_type_name <", value, "declareTypeName");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNameLessThanOrEqualTo(String value) {
            addCriterion("declare_type_name <=", value, "declareTypeName");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNameLike(String value) {
            addCriterion("declare_type_name like", value, "declareTypeName");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNameNotLike(String value) {
            addCriterion("declare_type_name not like", value, "declareTypeName");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNameIn(List<String> values) {
            addCriterion("declare_type_name in", values, "declareTypeName");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNameNotIn(List<String> values) {
            addCriterion("declare_type_name not in", values, "declareTypeName");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNameBetween(String value1, String value2) {
            addCriterion("declare_type_name between", value1, value2, "declareTypeName");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNameNotBetween(String value1, String value2) {
            addCriterion("declare_type_name not between", value1, value2, "declareTypeName");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNoIsNull() {
            addCriterion("declare_type_no is null");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNoIsNotNull() {
            addCriterion("declare_type_no is not null");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNoEqualTo(String value) {
            addCriterion("declare_type_no =", value, "declareTypeNo");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNoNotEqualTo(String value) {
            addCriterion("declare_type_no <>", value, "declareTypeNo");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNoGreaterThan(String value) {
            addCriterion("declare_type_no >", value, "declareTypeNo");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNoGreaterThanOrEqualTo(String value) {
            addCriterion("declare_type_no >=", value, "declareTypeNo");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNoLessThan(String value) {
            addCriterion("declare_type_no <", value, "declareTypeNo");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNoLessThanOrEqualTo(String value) {
            addCriterion("declare_type_no <=", value, "declareTypeNo");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNoLike(String value) {
            addCriterion("declare_type_no like", value, "declareTypeNo");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNoNotLike(String value) {
            addCriterion("declare_type_no not like", value, "declareTypeNo");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNoIn(List<String> values) {
            addCriterion("declare_type_no in", values, "declareTypeNo");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNoNotIn(List<String> values) {
            addCriterion("declare_type_no not in", values, "declareTypeNo");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNoBetween(String value1, String value2) {
            addCriterion("declare_type_no between", value1, value2, "declareTypeNo");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNoNotBetween(String value1, String value2) {
            addCriterion("declare_type_no not between", value1, value2, "declareTypeNo");
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