package com.stock.capital.enterprise.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserInputIndexExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserInputIndexExample() {
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

        public Criteria andInputIndexIsNull() {
            addCriterion("input_index is null");
            return (Criteria) this;
        }

        public Criteria andInputIndexIsNotNull() {
            addCriterion("input_index is not null");
            return (Criteria) this;
        }

        public Criteria andInputIndexEqualTo(String value) {
            addCriterion("input_index =", value, "inputIndex");
            return (Criteria) this;
        }

        public Criteria andInputIndexNotEqualTo(String value) {
            addCriterion("input_index <>", value, "inputIndex");
            return (Criteria) this;
        }

        public Criteria andInputIndexGreaterThan(String value) {
            addCriterion("input_index >", value, "inputIndex");
            return (Criteria) this;
        }

        public Criteria andInputIndexGreaterThanOrEqualTo(String value) {
            addCriterion("input_index >=", value, "inputIndex");
            return (Criteria) this;
        }

        public Criteria andInputIndexLessThan(String value) {
            addCriterion("input_index <", value, "inputIndex");
            return (Criteria) this;
        }

        public Criteria andInputIndexLessThanOrEqualTo(String value) {
            addCriterion("input_index <=", value, "inputIndex");
            return (Criteria) this;
        }

        public Criteria andInputIndexLike(String value) {
            addCriterion("input_index like", value, "inputIndex");
            return (Criteria) this;
        }

        public Criteria andInputIndexNotLike(String value) {
            addCriterion("input_index not like", value, "inputIndex");
            return (Criteria) this;
        }

        public Criteria andInputIndexIn(List<String> values) {
            addCriterion("input_index in", values, "inputIndex");
            return (Criteria) this;
        }

        public Criteria andInputIndexNotIn(List<String> values) {
            addCriterion("input_index not in", values, "inputIndex");
            return (Criteria) this;
        }

        public Criteria andInputIndexBetween(String value1, String value2) {
            addCriterion("input_index between", value1, value2, "inputIndex");
            return (Criteria) this;
        }

        public Criteria andInputIndexNotBetween(String value1, String value2) {
            addCriterion("input_index not between", value1, value2, "inputIndex");
            return (Criteria) this;
        }

        public Criteria andInputIndexQueryIsNull() {
            addCriterion("input_index_query is null");
            return (Criteria) this;
        }

        public Criteria andInputIndexQueryIsNotNull() {
            addCriterion("input_index_query is not null");
            return (Criteria) this;
        }

        public Criteria andInputIndexQueryEqualTo(String value) {
            addCriterion("input_index_query =", value, "inputIndexQuery");
            return (Criteria) this;
        }

        public Criteria andInputIndexQueryNotEqualTo(String value) {
            addCriterion("input_index_query <>", value, "inputIndexQuery");
            return (Criteria) this;
        }

        public Criteria andInputIndexQueryGreaterThan(String value) {
            addCriterion("input_index_query >", value, "inputIndexQuery");
            return (Criteria) this;
        }

        public Criteria andInputIndexQueryGreaterThanOrEqualTo(String value) {
            addCriterion("input_index_query >=", value, "inputIndexQuery");
            return (Criteria) this;
        }

        public Criteria andInputIndexQueryLessThan(String value) {
            addCriterion("input_index_query <", value, "inputIndexQuery");
            return (Criteria) this;
        }

        public Criteria andInputIndexQueryLessThanOrEqualTo(String value) {
            addCriterion("input_index_query <=", value, "inputIndexQuery");
            return (Criteria) this;
        }

        public Criteria andInputIndexQueryLike(String value) {
            addCriterion("input_index_query like", value, "inputIndexQuery");
            return (Criteria) this;
        }

        public Criteria andInputIndexQueryNotLike(String value) {
            addCriterion("input_index_query not like", value, "inputIndexQuery");
            return (Criteria) this;
        }

        public Criteria andInputIndexQueryIn(List<String> values) {
            addCriterion("input_index_query in", values, "inputIndexQuery");
            return (Criteria) this;
        }

        public Criteria andInputIndexQueryNotIn(List<String> values) {
            addCriterion("input_index_query not in", values, "inputIndexQuery");
            return (Criteria) this;
        }

        public Criteria andInputIndexQueryBetween(String value1, String value2) {
            addCriterion("input_index_query between", value1, value2, "inputIndexQuery");
            return (Criteria) this;
        }

        public Criteria andInputIndexQueryNotBetween(String value1, String value2) {
            addCriterion("input_index_query not between", value1, value2, "inputIndexQuery");
            return (Criteria) this;
        }

        public Criteria andIndexModuleIsNull() {
            addCriterion("index_module is null");
            return (Criteria) this;
        }

        public Criteria andIndexModuleIsNotNull() {
            addCriterion("index_module is not null");
            return (Criteria) this;
        }

        public Criteria andIndexModuleEqualTo(String value) {
            addCriterion("index_module =", value, "indexModule");
            return (Criteria) this;
        }

        public Criteria andIndexModuleNotEqualTo(String value) {
            addCriterion("index_module <>", value, "indexModule");
            return (Criteria) this;
        }

        public Criteria andIndexModuleGreaterThan(String value) {
            addCriterion("index_module >", value, "indexModule");
            return (Criteria) this;
        }

        public Criteria andIndexModuleGreaterThanOrEqualTo(String value) {
            addCriterion("index_module >=", value, "indexModule");
            return (Criteria) this;
        }

        public Criteria andIndexModuleLessThan(String value) {
            addCriterion("index_module <", value, "indexModule");
            return (Criteria) this;
        }

        public Criteria andIndexModuleLessThanOrEqualTo(String value) {
            addCriterion("index_module <=", value, "indexModule");
            return (Criteria) this;
        }

        public Criteria andIndexModuleLike(String value) {
            addCriterion("index_module like", value, "indexModule");
            return (Criteria) this;
        }

        public Criteria andIndexModuleNotLike(String value) {
            addCriterion("index_module not like", value, "indexModule");
            return (Criteria) this;
        }

        public Criteria andIndexModuleIn(List<String> values) {
            addCriterion("index_module in", values, "indexModule");
            return (Criteria) this;
        }

        public Criteria andIndexModuleNotIn(List<String> values) {
            addCriterion("index_module not in", values, "indexModule");
            return (Criteria) this;
        }

        public Criteria andIndexModuleBetween(String value1, String value2) {
            addCriterion("index_module between", value1, value2, "indexModule");
            return (Criteria) this;
        }

        public Criteria andIndexModuleNotBetween(String value1, String value2) {
            addCriterion("index_module not between", value1, value2, "indexModule");
            return (Criteria) this;
        }

        public Criteria andIndexProjectIsNull() {
            addCriterion("index_project is null");
            return (Criteria) this;
        }

        public Criteria andIndexProjectIsNotNull() {
            addCriterion("index_project is not null");
            return (Criteria) this;
        }

        public Criteria andIndexProjectEqualTo(String value) {
            addCriterion("index_project =", value, "indexProject");
            return (Criteria) this;
        }

        public Criteria andIndexProjectNotEqualTo(String value) {
            addCriterion("index_project <>", value, "indexProject");
            return (Criteria) this;
        }

        public Criteria andIndexProjectGreaterThan(String value) {
            addCriterion("index_project >", value, "indexProject");
            return (Criteria) this;
        }

        public Criteria andIndexProjectGreaterThanOrEqualTo(String value) {
            addCriterion("index_project >=", value, "indexProject");
            return (Criteria) this;
        }

        public Criteria andIndexProjectLessThan(String value) {
            addCriterion("index_project <", value, "indexProject");
            return (Criteria) this;
        }

        public Criteria andIndexProjectLessThanOrEqualTo(String value) {
            addCriterion("index_project <=", value, "indexProject");
            return (Criteria) this;
        }

        public Criteria andIndexProjectLike(String value) {
            addCriterion("index_project like", value, "indexProject");
            return (Criteria) this;
        }

        public Criteria andIndexProjectNotLike(String value) {
            addCriterion("index_project not like", value, "indexProject");
            return (Criteria) this;
        }

        public Criteria andIndexProjectIn(List<String> values) {
            addCriterion("index_project in", values, "indexProject");
            return (Criteria) this;
        }

        public Criteria andIndexProjectNotIn(List<String> values) {
            addCriterion("index_project not in", values, "indexProject");
            return (Criteria) this;
        }

        public Criteria andIndexProjectBetween(String value1, String value2) {
            addCriterion("index_project between", value1, value2, "indexProject");
            return (Criteria) this;
        }

        public Criteria andIndexProjectNotBetween(String value1, String value2) {
            addCriterion("index_project not between", value1, value2, "indexProject");
            return (Criteria) this;
        }

        public Criteria andProcessStatusIsNull() {
            addCriterion("process_status is null");
            return (Criteria) this;
        }

        public Criteria andProcessStatusIsNotNull() {
            addCriterion("process_status is not null");
            return (Criteria) this;
        }

        public Criteria andProcessStatusEqualTo(String value) {
            addCriterion("process_status =", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotEqualTo(String value) {
            addCriterion("process_status <>", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusGreaterThan(String value) {
            addCriterion("process_status >", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusGreaterThanOrEqualTo(String value) {
            addCriterion("process_status >=", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusLessThan(String value) {
            addCriterion("process_status <", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusLessThanOrEqualTo(String value) {
            addCriterion("process_status <=", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusLike(String value) {
            addCriterion("process_status like", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotLike(String value) {
            addCriterion("process_status not like", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusIn(List<String> values) {
            addCriterion("process_status in", values, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotIn(List<String> values) {
            addCriterion("process_status not in", values, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusBetween(String value1, String value2) {
            addCriterion("process_status between", value1, value2, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotBetween(String value1, String value2) {
            addCriterion("process_status not between", value1, value2, "processStatus");
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