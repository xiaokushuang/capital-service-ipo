package com.stock.capital.enterprise.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeclareTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeclareTypeExample() {
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

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("parent_id like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("parent_id not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
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

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andTypeNoIsNull() {
            addCriterion("type_no is null");
            return (Criteria) this;
        }

        public Criteria andTypeNoIsNotNull() {
            addCriterion("type_no is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNoEqualTo(String value) {
            addCriterion("type_no =", value, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoNotEqualTo(String value) {
            addCriterion("type_no <>", value, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoGreaterThan(String value) {
            addCriterion("type_no >", value, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoGreaterThanOrEqualTo(String value) {
            addCriterion("type_no >=", value, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoLessThan(String value) {
            addCriterion("type_no <", value, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoLessThanOrEqualTo(String value) {
            addCriterion("type_no <=", value, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoLike(String value) {
            addCriterion("type_no like", value, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoNotLike(String value) {
            addCriterion("type_no not like", value, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoIn(List<String> values) {
            addCriterion("type_no in", values, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoNotIn(List<String> values) {
            addCriterion("type_no not in", values, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoBetween(String value1, String value2) {
            addCriterion("type_no between", value1, value2, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoNotBetween(String value1, String value2) {
            addCriterion("type_no not between", value1, value2, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeClassIsNull() {
            addCriterion("type_class is null");
            return (Criteria) this;
        }

        public Criteria andTypeClassIsNotNull() {
            addCriterion("type_class is not null");
            return (Criteria) this;
        }

        public Criteria andTypeClassEqualTo(String value) {
            addCriterion("type_class =", value, "typeClass");
            return (Criteria) this;
        }

        public Criteria andTypeClassNotEqualTo(String value) {
            addCriterion("type_class <>", value, "typeClass");
            return (Criteria) this;
        }

        public Criteria andTypeClassGreaterThan(String value) {
            addCriterion("type_class >", value, "typeClass");
            return (Criteria) this;
        }

        public Criteria andTypeClassGreaterThanOrEqualTo(String value) {
            addCriterion("type_class >=", value, "typeClass");
            return (Criteria) this;
        }

        public Criteria andTypeClassLessThan(String value) {
            addCriterion("type_class <", value, "typeClass");
            return (Criteria) this;
        }

        public Criteria andTypeClassLessThanOrEqualTo(String value) {
            addCriterion("type_class <=", value, "typeClass");
            return (Criteria) this;
        }

        public Criteria andTypeClassLike(String value) {
            addCriterion("type_class like", value, "typeClass");
            return (Criteria) this;
        }

        public Criteria andTypeClassNotLike(String value) {
            addCriterion("type_class not like", value, "typeClass");
            return (Criteria) this;
        }

        public Criteria andTypeClassIn(List<String> values) {
            addCriterion("type_class in", values, "typeClass");
            return (Criteria) this;
        }

        public Criteria andTypeClassNotIn(List<String> values) {
            addCriterion("type_class not in", values, "typeClass");
            return (Criteria) this;
        }

        public Criteria andTypeClassBetween(String value1, String value2) {
            addCriterion("type_class between", value1, value2, "typeClass");
            return (Criteria) this;
        }

        public Criteria andTypeClassNotBetween(String value1, String value2) {
            addCriterion("type_class not between", value1, value2, "typeClass");
            return (Criteria) this;
        }

        public Criteria andSuspendingFlagIsNull() {
            addCriterion("suspending_flag is null");
            return (Criteria) this;
        }

        public Criteria andSuspendingFlagIsNotNull() {
            addCriterion("suspending_flag is not null");
            return (Criteria) this;
        }

        public Criteria andSuspendingFlagEqualTo(String value) {
            addCriterion("suspending_flag =", value, "suspendingFlag");
            return (Criteria) this;
        }

        public Criteria andSuspendingFlagNotEqualTo(String value) {
            addCriterion("suspending_flag <>", value, "suspendingFlag");
            return (Criteria) this;
        }

        public Criteria andSuspendingFlagGreaterThan(String value) {
            addCriterion("suspending_flag >", value, "suspendingFlag");
            return (Criteria) this;
        }

        public Criteria andSuspendingFlagGreaterThanOrEqualTo(String value) {
            addCriterion("suspending_flag >=", value, "suspendingFlag");
            return (Criteria) this;
        }

        public Criteria andSuspendingFlagLessThan(String value) {
            addCriterion("suspending_flag <", value, "suspendingFlag");
            return (Criteria) this;
        }

        public Criteria andSuspendingFlagLessThanOrEqualTo(String value) {
            addCriterion("suspending_flag <=", value, "suspendingFlag");
            return (Criteria) this;
        }

        public Criteria andSuspendingFlagLike(String value) {
            addCriterion("suspending_flag like", value, "suspendingFlag");
            return (Criteria) this;
        }

        public Criteria andSuspendingFlagNotLike(String value) {
            addCriterion("suspending_flag not like", value, "suspendingFlag");
            return (Criteria) this;
        }

        public Criteria andSuspendingFlagIn(List<String> values) {
            addCriterion("suspending_flag in", values, "suspendingFlag");
            return (Criteria) this;
        }

        public Criteria andSuspendingFlagNotIn(List<String> values) {
            addCriterion("suspending_flag not in", values, "suspendingFlag");
            return (Criteria) this;
        }

        public Criteria andSuspendingFlagBetween(String value1, String value2) {
            addCriterion("suspending_flag between", value1, value2, "suspendingFlag");
            return (Criteria) this;
        }

        public Criteria andSuspendingFlagNotBetween(String value1, String value2) {
            addCriterion("suspending_flag not between", value1, value2, "suspendingFlag");
            return (Criteria) this;
        }

        public Criteria andFollowClassIdIsNull() {
            addCriterion("follow_class_id is null");
            return (Criteria) this;
        }

        public Criteria andFollowClassIdIsNotNull() {
            addCriterion("follow_class_id is not null");
            return (Criteria) this;
        }

        public Criteria andFollowClassIdEqualTo(String value) {
            addCriterion("follow_class_id =", value, "followClassId");
            return (Criteria) this;
        }

        public Criteria andFollowClassIdNotEqualTo(String value) {
            addCriterion("follow_class_id <>", value, "followClassId");
            return (Criteria) this;
        }

        public Criteria andFollowClassIdGreaterThan(String value) {
            addCriterion("follow_class_id >", value, "followClassId");
            return (Criteria) this;
        }

        public Criteria andFollowClassIdGreaterThanOrEqualTo(String value) {
            addCriterion("follow_class_id >=", value, "followClassId");
            return (Criteria) this;
        }

        public Criteria andFollowClassIdLessThan(String value) {
            addCriterion("follow_class_id <", value, "followClassId");
            return (Criteria) this;
        }

        public Criteria andFollowClassIdLessThanOrEqualTo(String value) {
            addCriterion("follow_class_id <=", value, "followClassId");
            return (Criteria) this;
        }

        public Criteria andFollowClassIdLike(String value) {
            addCriterion("follow_class_id like", value, "followClassId");
            return (Criteria) this;
        }

        public Criteria andFollowClassIdNotLike(String value) {
            addCriterion("follow_class_id not like", value, "followClassId");
            return (Criteria) this;
        }

        public Criteria andFollowClassIdIn(List<String> values) {
            addCriterion("follow_class_id in", values, "followClassId");
            return (Criteria) this;
        }

        public Criteria andFollowClassIdNotIn(List<String> values) {
            addCriterion("follow_class_id not in", values, "followClassId");
            return (Criteria) this;
        }

        public Criteria andFollowClassIdBetween(String value1, String value2) {
            addCriterion("follow_class_id between", value1, value2, "followClassId");
            return (Criteria) this;
        }

        public Criteria andFollowClassIdNotBetween(String value1, String value2) {
            addCriterion("follow_class_id not between", value1, value2, "followClassId");
            return (Criteria) this;
        }

        public Criteria andFollowDateIntervalIsNull() {
            addCriterion("follow_date_interval is null");
            return (Criteria) this;
        }

        public Criteria andFollowDateIntervalIsNotNull() {
            addCriterion("follow_date_interval is not null");
            return (Criteria) this;
        }

        public Criteria andFollowDateIntervalEqualTo(Short value) {
            addCriterion("follow_date_interval =", value, "followDateInterval");
            return (Criteria) this;
        }

        public Criteria andFollowDateIntervalNotEqualTo(Short value) {
            addCriterion("follow_date_interval <>", value, "followDateInterval");
            return (Criteria) this;
        }

        public Criteria andFollowDateIntervalGreaterThan(Short value) {
            addCriterion("follow_date_interval >", value, "followDateInterval");
            return (Criteria) this;
        }

        public Criteria andFollowDateIntervalGreaterThanOrEqualTo(Short value) {
            addCriterion("follow_date_interval >=", value, "followDateInterval");
            return (Criteria) this;
        }

        public Criteria andFollowDateIntervalLessThan(Short value) {
            addCriterion("follow_date_interval <", value, "followDateInterval");
            return (Criteria) this;
        }

        public Criteria andFollowDateIntervalLessThanOrEqualTo(Short value) {
            addCriterion("follow_date_interval <=", value, "followDateInterval");
            return (Criteria) this;
        }

        public Criteria andFollowDateIntervalIn(List<Short> values) {
            addCriterion("follow_date_interval in", values, "followDateInterval");
            return (Criteria) this;
        }

        public Criteria andFollowDateIntervalNotIn(List<Short> values) {
            addCriterion("follow_date_interval not in", values, "followDateInterval");
            return (Criteria) this;
        }

        public Criteria andFollowDateIntervalBetween(Short value1, Short value2) {
            addCriterion("follow_date_interval between", value1, value2, "followDateInterval");
            return (Criteria) this;
        }

        public Criteria andFollowDateIntervalNotBetween(Short value1, Short value2) {
            addCriterion("follow_date_interval not between", value1, value2, "followDateInterval");
            return (Criteria) this;
        }

        public Criteria andBeforeDaysIsNull() {
            addCriterion("before_days is null");
            return (Criteria) this;
        }

        public Criteria andBeforeDaysIsNotNull() {
            addCriterion("before_days is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeDaysEqualTo(Short value) {
            addCriterion("before_days =", value, "beforeDays");
            return (Criteria) this;
        }

        public Criteria andBeforeDaysNotEqualTo(Short value) {
            addCriterion("before_days <>", value, "beforeDays");
            return (Criteria) this;
        }

        public Criteria andBeforeDaysGreaterThan(Short value) {
            addCriterion("before_days >", value, "beforeDays");
            return (Criteria) this;
        }

        public Criteria andBeforeDaysGreaterThanOrEqualTo(Short value) {
            addCriterion("before_days >=", value, "beforeDays");
            return (Criteria) this;
        }

        public Criteria andBeforeDaysLessThan(Short value) {
            addCriterion("before_days <", value, "beforeDays");
            return (Criteria) this;
        }

        public Criteria andBeforeDaysLessThanOrEqualTo(Short value) {
            addCriterion("before_days <=", value, "beforeDays");
            return (Criteria) this;
        }

        public Criteria andBeforeDaysIn(List<Short> values) {
            addCriterion("before_days in", values, "beforeDays");
            return (Criteria) this;
        }

        public Criteria andBeforeDaysNotIn(List<Short> values) {
            addCriterion("before_days not in", values, "beforeDays");
            return (Criteria) this;
        }

        public Criteria andBeforeDaysBetween(Short value1, Short value2) {
            addCriterion("before_days between", value1, value2, "beforeDays");
            return (Criteria) this;
        }

        public Criteria andBeforeDaysNotBetween(Short value1, Short value2) {
            addCriterion("before_days not between", value1, value2, "beforeDays");
            return (Criteria) this;
        }

        public Criteria andAfterDaysIsNull() {
            addCriterion("after_days is null");
            return (Criteria) this;
        }

        public Criteria andAfterDaysIsNotNull() {
            addCriterion("after_days is not null");
            return (Criteria) this;
        }

        public Criteria andAfterDaysEqualTo(Short value) {
            addCriterion("after_days =", value, "afterDays");
            return (Criteria) this;
        }

        public Criteria andAfterDaysNotEqualTo(Short value) {
            addCriterion("after_days <>", value, "afterDays");
            return (Criteria) this;
        }

        public Criteria andAfterDaysGreaterThan(Short value) {
            addCriterion("after_days >", value, "afterDays");
            return (Criteria) this;
        }

        public Criteria andAfterDaysGreaterThanOrEqualTo(Short value) {
            addCriterion("after_days >=", value, "afterDays");
            return (Criteria) this;
        }

        public Criteria andAfterDaysLessThan(Short value) {
            addCriterion("after_days <", value, "afterDays");
            return (Criteria) this;
        }

        public Criteria andAfterDaysLessThanOrEqualTo(Short value) {
            addCriterion("after_days <=", value, "afterDays");
            return (Criteria) this;
        }

        public Criteria andAfterDaysIn(List<Short> values) {
            addCriterion("after_days in", values, "afterDays");
            return (Criteria) this;
        }

        public Criteria andAfterDaysNotIn(List<Short> values) {
            addCriterion("after_days not in", values, "afterDays");
            return (Criteria) this;
        }

        public Criteria andAfterDaysBetween(Short value1, Short value2) {
            addCriterion("after_days between", value1, value2, "afterDays");
            return (Criteria) this;
        }

        public Criteria andAfterDaysNotBetween(Short value1, Short value2) {
            addCriterion("after_days not between", value1, value2, "afterDays");
            return (Criteria) this;
        }

        public Criteria andValidFlagIsNull() {
            addCriterion("valid_flag is null");
            return (Criteria) this;
        }

        public Criteria andValidFlagIsNotNull() {
            addCriterion("valid_flag is not null");
            return (Criteria) this;
        }

        public Criteria andValidFlagEqualTo(String value) {
            addCriterion("valid_flag =", value, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagNotEqualTo(String value) {
            addCriterion("valid_flag <>", value, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagGreaterThan(String value) {
            addCriterion("valid_flag >", value, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagGreaterThanOrEqualTo(String value) {
            addCriterion("valid_flag >=", value, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagLessThan(String value) {
            addCriterion("valid_flag <", value, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagLessThanOrEqualTo(String value) {
            addCriterion("valid_flag <=", value, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagLike(String value) {
            addCriterion("valid_flag like", value, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagNotLike(String value) {
            addCriterion("valid_flag not like", value, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagIn(List<String> values) {
            addCriterion("valid_flag in", values, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagNotIn(List<String> values) {
            addCriterion("valid_flag not in", values, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagBetween(String value1, String value2) {
            addCriterion("valid_flag between", value1, value2, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagNotBetween(String value1, String value2) {
            addCriterion("valid_flag not between", value1, value2, "validFlag");
            return (Criteria) this;
        }

        public Criteria andOftenFlagIsNull() {
            addCriterion("often_flag is null");
            return (Criteria) this;
        }

        public Criteria andOftenFlagIsNotNull() {
            addCriterion("often_flag is not null");
            return (Criteria) this;
        }

        public Criteria andOftenFlagEqualTo(Integer value) {
            addCriterion("often_flag =", value, "oftenFlag");
            return (Criteria) this;
        }

        public Criteria andOftenFlagNotEqualTo(Integer value) {
            addCriterion("often_flag <>", value, "oftenFlag");
            return (Criteria) this;
        }

        public Criteria andOftenFlagGreaterThan(Integer value) {
            addCriterion("often_flag >", value, "oftenFlag");
            return (Criteria) this;
        }

        public Criteria andOftenFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("often_flag >=", value, "oftenFlag");
            return (Criteria) this;
        }

        public Criteria andOftenFlagLessThan(Integer value) {
            addCriterion("often_flag <", value, "oftenFlag");
            return (Criteria) this;
        }

        public Criteria andOftenFlagLessThanOrEqualTo(Integer value) {
            addCriterion("often_flag <=", value, "oftenFlag");
            return (Criteria) this;
        }

        public Criteria andOftenFlagIn(List<Integer> values) {
            addCriterion("often_flag in", values, "oftenFlag");
            return (Criteria) this;
        }

        public Criteria andOftenFlagNotIn(List<Integer> values) {
            addCriterion("often_flag not in", values, "oftenFlag");
            return (Criteria) this;
        }

        public Criteria andOftenFlagBetween(Integer value1, Integer value2) {
            addCriterion("often_flag between", value1, value2, "oftenFlag");
            return (Criteria) this;
        }

        public Criteria andOftenFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("often_flag not between", value1, value2, "oftenFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagIsNull() {
            addCriterion("bill_flag is null");
            return (Criteria) this;
        }

        public Criteria andBillFlagIsNotNull() {
            addCriterion("bill_flag is not null");
            return (Criteria) this;
        }

        public Criteria andBillFlagEqualTo(Integer value) {
            addCriterion("bill_flag =", value, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagNotEqualTo(Integer value) {
            addCriterion("bill_flag <>", value, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagGreaterThan(Integer value) {
            addCriterion("bill_flag >", value, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("bill_flag >=", value, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagLessThan(Integer value) {
            addCriterion("bill_flag <", value, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagLessThanOrEqualTo(Integer value) {
            addCriterion("bill_flag <=", value, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagIn(List<Integer> values) {
            addCriterion("bill_flag in", values, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagNotIn(List<Integer> values) {
            addCriterion("bill_flag not in", values, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagBetween(Integer value1, Integer value2) {
            addCriterion("bill_flag between", value1, value2, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("bill_flag not between", value1, value2, "billFlag");
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