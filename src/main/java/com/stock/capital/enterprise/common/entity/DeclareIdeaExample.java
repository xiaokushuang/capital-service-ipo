package com.stock.capital.enterprise.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeclareIdeaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeclareIdeaExample() {
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

        public Criteria andDeclareTypeIdIsNull() {
            addCriterion("declare_type_id is null");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIdIsNotNull() {
            addCriterion("declare_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIdEqualTo(String value) {
            addCriterion("declare_type_id =", value, "declareTypeId");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIdNotEqualTo(String value) {
            addCriterion("declare_type_id <>", value, "declareTypeId");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIdGreaterThan(String value) {
            addCriterion("declare_type_id >", value, "declareTypeId");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("declare_type_id >=", value, "declareTypeId");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIdLessThan(String value) {
            addCriterion("declare_type_id <", value, "declareTypeId");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIdLessThanOrEqualTo(String value) {
            addCriterion("declare_type_id <=", value, "declareTypeId");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIdLike(String value) {
            addCriterion("declare_type_id like", value, "declareTypeId");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIdNotLike(String value) {
            addCriterion("declare_type_id not like", value, "declareTypeId");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIdIn(List<String> values) {
            addCriterion("declare_type_id in", values, "declareTypeId");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIdNotIn(List<String> values) {
            addCriterion("declare_type_id not in", values, "declareTypeId");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIdBetween(String value1, String value2) {
            addCriterion("declare_type_id between", value1, value2, "declareTypeId");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIdNotBetween(String value1, String value2) {
            addCriterion("declare_type_id not between", value1, value2, "declareTypeId");
            return (Criteria) this;
        }

        public Criteria andIdeaContentIsNull() {
            addCriterion("idea_content is null");
            return (Criteria) this;
        }

        public Criteria andIdeaContentIsNotNull() {
            addCriterion("idea_content is not null");
            return (Criteria) this;
        }

        public Criteria andIdeaContentEqualTo(String value) {
            addCriterion("idea_content =", value, "ideaContent");
            return (Criteria) this;
        }

        public Criteria andIdeaContentNotEqualTo(String value) {
            addCriterion("idea_content <>", value, "ideaContent");
            return (Criteria) this;
        }

        public Criteria andIdeaContentGreaterThan(String value) {
            addCriterion("idea_content >", value, "ideaContent");
            return (Criteria) this;
        }

        public Criteria andIdeaContentGreaterThanOrEqualTo(String value) {
            addCriterion("idea_content >=", value, "ideaContent");
            return (Criteria) this;
        }

        public Criteria andIdeaContentLessThan(String value) {
            addCriterion("idea_content <", value, "ideaContent");
            return (Criteria) this;
        }

        public Criteria andIdeaContentLessThanOrEqualTo(String value) {
            addCriterion("idea_content <=", value, "ideaContent");
            return (Criteria) this;
        }

        public Criteria andIdeaContentLike(String value) {
            addCriterion("idea_content like", value, "ideaContent");
            return (Criteria) this;
        }

        public Criteria andIdeaContentNotLike(String value) {
            addCriterion("idea_content not like", value, "ideaContent");
            return (Criteria) this;
        }

        public Criteria andIdeaContentIn(List<String> values) {
            addCriterion("idea_content in", values, "ideaContent");
            return (Criteria) this;
        }

        public Criteria andIdeaContentNotIn(List<String> values) {
            addCriterion("idea_content not in", values, "ideaContent");
            return (Criteria) this;
        }

        public Criteria andIdeaContentBetween(String value1, String value2) {
            addCriterion("idea_content between", value1, value2, "ideaContent");
            return (Criteria) this;
        }

        public Criteria andIdeaContentNotBetween(String value1, String value2) {
            addCriterion("idea_content not between", value1, value2, "ideaContent");
            return (Criteria) this;
        }

        public Criteria andPersonIdIsNull() {
            addCriterion("person_id is null");
            return (Criteria) this;
        }

        public Criteria andPersonIdIsNotNull() {
            addCriterion("person_id is not null");
            return (Criteria) this;
        }

        public Criteria andPersonIdEqualTo(String value) {
            addCriterion("person_id =", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdNotEqualTo(String value) {
            addCriterion("person_id <>", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdGreaterThan(String value) {
            addCriterion("person_id >", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdGreaterThanOrEqualTo(String value) {
            addCriterion("person_id >=", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdLessThan(String value) {
            addCriterion("person_id <", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdLessThanOrEqualTo(String value) {
            addCriterion("person_id <=", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdLike(String value) {
            addCriterion("person_id like", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdNotLike(String value) {
            addCriterion("person_id not like", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdIn(List<String> values) {
            addCriterion("person_id in", values, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdNotIn(List<String> values) {
            addCriterion("person_id not in", values, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdBetween(String value1, String value2) {
            addCriterion("person_id between", value1, value2, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdNotBetween(String value1, String value2) {
            addCriterion("person_id not between", value1, value2, "personId");
            return (Criteria) this;
        }

        public Criteria andAddTicapitalEnterpriseDevmeIsNull() {
            addCriterion("add_ticapital_enterprise_devme is null");
            return (Criteria) this;
        }

        public Criteria andAddTicapitalEnterpriseDevmeIsNotNull() {
            addCriterion("add_ticapital_enterprise_devme is not null");
            return (Criteria) this;
        }

        public Criteria andAddTicapitalEnterpriseDevmeEqualTo(Date value) {
            addCriterion("add_ticapital_enterprise_devme =", value, "addTicapitalEnterpriseDevme");
            return (Criteria) this;
        }

        public Criteria andAddTicapitalEnterpriseDevmeNotEqualTo(Date value) {
            addCriterion("add_ticapital_enterprise_devme <>", value, "addTicapitalEnterpriseDevme");
            return (Criteria) this;
        }

        public Criteria andAddTicapitalEnterpriseDevmeGreaterThan(Date value) {
            addCriterion("add_ticapital_enterprise_devme >", value, "addTicapitalEnterpriseDevme");
            return (Criteria) this;
        }

        public Criteria andAddTicapitalEnterpriseDevmeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_ticapital_enterprise_devme >=", value, "addTicapitalEnterpriseDevme");
            return (Criteria) this;
        }

        public Criteria andAddTicapitalEnterpriseDevmeLessThan(Date value) {
            addCriterion("add_ticapital_enterprise_devme <", value, "addTicapitalEnterpriseDevme");
            return (Criteria) this;
        }

        public Criteria andAddTicapitalEnterpriseDevmeLessThanOrEqualTo(Date value) {
            addCriterion("add_ticapital_enterprise_devme <=", value, "addTicapitalEnterpriseDevme");
            return (Criteria) this;
        }

        public Criteria andAddTicapitalEnterpriseDevmeIn(List<Date> values) {
            addCriterion("add_ticapital_enterprise_devme in", values, "addTicapitalEnterpriseDevme");
            return (Criteria) this;
        }

        public Criteria andAddTicapitalEnterpriseDevmeNotIn(List<Date> values) {
            addCriterion("add_ticapital_enterprise_devme not in", values, "addTicapitalEnterpriseDevme");
            return (Criteria) this;
        }

        public Criteria andAddTicapitalEnterpriseDevmeBetween(Date value1, Date value2) {
            addCriterion("add_ticapital_enterprise_devme between", value1, value2, "addTicapitalEnterpriseDevme");
            return (Criteria) this;
        }

        public Criteria andAddTicapitalEnterpriseDevmeNotBetween(Date value1, Date value2) {
            addCriterion("add_ticapital_enterprise_devme not between", value1, value2, "addTicapitalEnterpriseDevme");
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

        public Criteria andPrivateStatusIsNull() {
            addCriterion("private_status is null");
            return (Criteria) this;
        }

        public Criteria andPrivateStatusIsNotNull() {
            addCriterion("private_status is not null");
            return (Criteria) this;
        }

        public Criteria andPrivateStatusEqualTo(String value) {
            addCriterion("private_status =", value, "privateStatus");
            return (Criteria) this;
        }

        public Criteria andPrivateStatusNotEqualTo(String value) {
            addCriterion("private_status <>", value, "privateStatus");
            return (Criteria) this;
        }

        public Criteria andPrivateStatusGreaterThan(String value) {
            addCriterion("private_status >", value, "privateStatus");
            return (Criteria) this;
        }

        public Criteria andPrivateStatusGreaterThanOrEqualTo(String value) {
            addCriterion("private_status >=", value, "privateStatus");
            return (Criteria) this;
        }

        public Criteria andPrivateStatusLessThan(String value) {
            addCriterion("private_status <", value, "privateStatus");
            return (Criteria) this;
        }

        public Criteria andPrivateStatusLessThanOrEqualTo(String value) {
            addCriterion("private_status <=", value, "privateStatus");
            return (Criteria) this;
        }

        public Criteria andPrivateStatusLike(String value) {
            addCriterion("private_status like", value, "privateStatus");
            return (Criteria) this;
        }

        public Criteria andPrivateStatusNotLike(String value) {
            addCriterion("private_status not like", value, "privateStatus");
            return (Criteria) this;
        }

        public Criteria andPrivateStatusIn(List<String> values) {
            addCriterion("private_status in", values, "privateStatus");
            return (Criteria) this;
        }

        public Criteria andPrivateStatusNotIn(List<String> values) {
            addCriterion("private_status not in", values, "privateStatus");
            return (Criteria) this;
        }

        public Criteria andPrivateStatusBetween(String value1, String value2) {
            addCriterion("private_status between", value1, value2, "privateStatus");
            return (Criteria) this;
        }

        public Criteria andPrivateStatusNotBetween(String value1, String value2) {
            addCriterion("private_status not between", value1, value2, "privateStatus");
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