package com.stock.capital.enterprise.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViolateManageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ViolateManageExample() {
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

        public Criteria andViolateTitleIsNull() {
            addCriterion("violate_title is null");
            return (Criteria) this;
        }

        public Criteria andViolateTitleIsNotNull() {
            addCriterion("violate_title is not null");
            return (Criteria) this;
        }

        public Criteria andViolateTitleEqualTo(String value) {
            addCriterion("violate_title =", value, "violateTitle");
            return (Criteria) this;
        }

        public Criteria andViolateTitleNotEqualTo(String value) {
            addCriterion("violate_title <>", value, "violateTitle");
            return (Criteria) this;
        }

        public Criteria andViolateTitleGreaterThan(String value) {
            addCriterion("violate_title >", value, "violateTitle");
            return (Criteria) this;
        }

        public Criteria andViolateTitleGreaterThanOrEqualTo(String value) {
            addCriterion("violate_title >=", value, "violateTitle");
            return (Criteria) this;
        }

        public Criteria andViolateTitleLessThan(String value) {
            addCriterion("violate_title <", value, "violateTitle");
            return (Criteria) this;
        }

        public Criteria andViolateTitleLessThanOrEqualTo(String value) {
            addCriterion("violate_title <=", value, "violateTitle");
            return (Criteria) this;
        }

        public Criteria andViolateTitleLike(String value) {
            addCriterion("violate_title like", value, "violateTitle");
            return (Criteria) this;
        }

        public Criteria andViolateTitleNotLike(String value) {
            addCriterion("violate_title not like", value, "violateTitle");
            return (Criteria) this;
        }

        public Criteria andViolateTitleIn(List<String> values) {
            addCriterion("violate_title in", values, "violateTitle");
            return (Criteria) this;
        }

        public Criteria andViolateTitleNotIn(List<String> values) {
            addCriterion("violate_title not in", values, "violateTitle");
            return (Criteria) this;
        }

        public Criteria andViolateTitleBetween(String value1, String value2) {
            addCriterion("violate_title between", value1, value2, "violateTitle");
            return (Criteria) this;
        }

        public Criteria andViolateTitleNotBetween(String value1, String value2) {
            addCriterion("violate_title not between", value1, value2, "violateTitle");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIsNull() {
            addCriterion("company_code is null");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIsNotNull() {
            addCriterion("company_code is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeEqualTo(String value) {
            addCriterion("company_code =", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotEqualTo(String value) {
            addCriterion("company_code <>", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeGreaterThan(String value) {
            addCriterion("company_code >", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("company_code >=", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLessThan(String value) {
            addCriterion("company_code <", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLessThanOrEqualTo(String value) {
            addCriterion("company_code <=", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLike(String value) {
            addCriterion("company_code like", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotLike(String value) {
            addCriterion("company_code not like", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIn(List<String> values) {
            addCriterion("company_code in", values, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotIn(List<String> values) {
            addCriterion("company_code not in", values, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeBetween(String value1, String value2) {
            addCriterion("company_code between", value1, value2, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotBetween(String value1, String value2) {
            addCriterion("company_code not between", value1, value2, "companyCode");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIsNull() {
            addCriterion("business_type is null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIsNotNull() {
            addCriterion("business_type is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeEqualTo(String value) {
            addCriterion("business_type =", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotEqualTo(String value) {
            addCriterion("business_type <>", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeGreaterThan(String value) {
            addCriterion("business_type >", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeGreaterThanOrEqualTo(String value) {
            addCriterion("business_type >=", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLessThan(String value) {
            addCriterion("business_type <", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLessThanOrEqualTo(String value) {
            addCriterion("business_type <=", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLike(String value) {
            addCriterion("business_type like", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotLike(String value) {
            addCriterion("business_type not like", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIn(List<String> values) {
            addCriterion("business_type in", values, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotIn(List<String> values) {
            addCriterion("business_type not in", values, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeBetween(String value1, String value2) {
            addCriterion("business_type between", value1, value2, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotBetween(String value1, String value2) {
            addCriterion("business_type not between", value1, value2, "businessType");
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

        public Criteria andPunishTimeBeginIsNull() {
            addCriterion("punish_time_begin is null");
            return (Criteria) this;
        }

        public Criteria andPunishTimeBeginIsNotNull() {
            addCriterion("punish_time_begin is not null");
            return (Criteria) this;
        }

        public Criteria andPunishTimeBeginEqualTo(String value) {
            addCriterion("punish_time_begin =", value, "punishTimeBegin");
            return (Criteria) this;
        }

        public Criteria andPunishTimeBeginNotEqualTo(String value) {
            addCriterion("punish_time_begin <>", value, "punishTimeBegin");
            return (Criteria) this;
        }

        public Criteria andPunishTimeBeginGreaterThan(String value) {
            addCriterion("punish_time_begin >", value, "punishTimeBegin");
            return (Criteria) this;
        }

        public Criteria andPunishTimeBeginGreaterThanOrEqualTo(String value) {
            addCriterion("punish_time_begin >=", value, "punishTimeBegin");
            return (Criteria) this;
        }

        public Criteria andPunishTimeBeginLessThan(String value) {
            addCriterion("punish_time_begin <", value, "punishTimeBegin");
            return (Criteria) this;
        }

        public Criteria andPunishTimeBeginLessThanOrEqualTo(String value) {
            addCriterion("punish_time_begin <=", value, "punishTimeBegin");
            return (Criteria) this;
        }

        public Criteria andPunishTimeBeginLike(String value) {
            addCriterion("punish_time_begin like", value, "punishTimeBegin");
            return (Criteria) this;
        }

        public Criteria andPunishTimeBeginNotLike(String value) {
            addCriterion("punish_time_begin not like", value, "punishTimeBegin");
            return (Criteria) this;
        }

        public Criteria andPunishTimeBeginIn(List<String> values) {
            addCriterion("punish_time_begin in", values, "punishTimeBegin");
            return (Criteria) this;
        }

        public Criteria andPunishTimeBeginNotIn(List<String> values) {
            addCriterion("punish_time_begin not in", values, "punishTimeBegin");
            return (Criteria) this;
        }

        public Criteria andPunishTimeBeginBetween(String value1, String value2) {
            addCriterion("punish_time_begin between", value1, value2, "punishTimeBegin");
            return (Criteria) this;
        }

        public Criteria andPunishTimeBeginNotBetween(String value1, String value2) {
            addCriterion("punish_time_begin not between", value1, value2, "punishTimeBegin");
            return (Criteria) this;
        }

        public Criteria andPunishTimeEndIsNull() {
            addCriterion("punish_time_end is null");
            return (Criteria) this;
        }

        public Criteria andPunishTimeEndIsNotNull() {
            addCriterion("punish_time_end is not null");
            return (Criteria) this;
        }

        public Criteria andPunishTimeEndEqualTo(String value) {
            addCriterion("punish_time_end =", value, "punishTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPunishTimeEndNotEqualTo(String value) {
            addCriterion("punish_time_end <>", value, "punishTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPunishTimeEndGreaterThan(String value) {
            addCriterion("punish_time_end >", value, "punishTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPunishTimeEndGreaterThanOrEqualTo(String value) {
            addCriterion("punish_time_end >=", value, "punishTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPunishTimeEndLessThan(String value) {
            addCriterion("punish_time_end <", value, "punishTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPunishTimeEndLessThanOrEqualTo(String value) {
            addCriterion("punish_time_end <=", value, "punishTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPunishTimeEndLike(String value) {
            addCriterion("punish_time_end like", value, "punishTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPunishTimeEndNotLike(String value) {
            addCriterion("punish_time_end not like", value, "punishTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPunishTimeEndIn(List<String> values) {
            addCriterion("punish_time_end in", values, "punishTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPunishTimeEndNotIn(List<String> values) {
            addCriterion("punish_time_end not in", values, "punishTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPunishTimeEndBetween(String value1, String value2) {
            addCriterion("punish_time_end between", value1, value2, "punishTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPunishTimeEndNotBetween(String value1, String value2) {
            addCriterion("punish_time_end not between", value1, value2, "punishTimeEnd");
            return (Criteria) this;
        }

        public Criteria andIgnoranceFlagIsNull() {
            addCriterion("ignorance_flag is null");
            return (Criteria) this;
        }

        public Criteria andIgnoranceFlagIsNotNull() {
            addCriterion("ignorance_flag is not null");
            return (Criteria) this;
        }

        public Criteria andIgnoranceFlagEqualTo(String value) {
            addCriterion("ignorance_flag =", value, "ignoranceFlag");
            return (Criteria) this;
        }

        public Criteria andIgnoranceFlagNotEqualTo(String value) {
            addCriterion("ignorance_flag <>", value, "ignoranceFlag");
            return (Criteria) this;
        }

        public Criteria andIgnoranceFlagGreaterThan(String value) {
            addCriterion("ignorance_flag >", value, "ignoranceFlag");
            return (Criteria) this;
        }

        public Criteria andIgnoranceFlagGreaterThanOrEqualTo(String value) {
            addCriterion("ignorance_flag >=", value, "ignoranceFlag");
            return (Criteria) this;
        }

        public Criteria andIgnoranceFlagLessThan(String value) {
            addCriterion("ignorance_flag <", value, "ignoranceFlag");
            return (Criteria) this;
        }

        public Criteria andIgnoranceFlagLessThanOrEqualTo(String value) {
            addCriterion("ignorance_flag <=", value, "ignoranceFlag");
            return (Criteria) this;
        }

        public Criteria andIgnoranceFlagLike(String value) {
            addCriterion("ignorance_flag like", value, "ignoranceFlag");
            return (Criteria) this;
        }

        public Criteria andIgnoranceFlagNotLike(String value) {
            addCriterion("ignorance_flag not like", value, "ignoranceFlag");
            return (Criteria) this;
        }

        public Criteria andIgnoranceFlagIn(List<String> values) {
            addCriterion("ignorance_flag in", values, "ignoranceFlag");
            return (Criteria) this;
        }

        public Criteria andIgnoranceFlagNotIn(List<String> values) {
            addCriterion("ignorance_flag not in", values, "ignoranceFlag");
            return (Criteria) this;
        }

        public Criteria andIgnoranceFlagBetween(String value1, String value2) {
            addCriterion("ignorance_flag between", value1, value2, "ignoranceFlag");
            return (Criteria) this;
        }

        public Criteria andIgnoranceFlagNotBetween(String value1, String value2) {
            addCriterion("ignorance_flag not between", value1, value2, "ignoranceFlag");
            return (Criteria) this;
        }

        public Criteria andProfileIsNull() {
            addCriterion("profile is null");
            return (Criteria) this;
        }

        public Criteria andProfileIsNotNull() {
            addCriterion("profile is not null");
            return (Criteria) this;
        }

        public Criteria andProfileEqualTo(String value) {
            addCriterion("profile =", value, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileNotEqualTo(String value) {
            addCriterion("profile <>", value, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileGreaterThan(String value) {
            addCriterion("profile >", value, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileGreaterThanOrEqualTo(String value) {
            addCriterion("profile >=", value, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileLessThan(String value) {
            addCriterion("profile <", value, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileLessThanOrEqualTo(String value) {
            addCriterion("profile <=", value, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileLike(String value) {
            addCriterion("profile like", value, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileNotLike(String value) {
            addCriterion("profile not like", value, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileIn(List<String> values) {
            addCriterion("profile in", values, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileNotIn(List<String> values) {
            addCriterion("profile not in", values, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileBetween(String value1, String value2) {
            addCriterion("profile between", value1, value2, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileNotBetween(String value1, String value2) {
            addCriterion("profile not between", value1, value2, "profile");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryTypeIsNull() {
            addCriterion("disciplinary_type is null");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryTypeIsNotNull() {
            addCriterion("disciplinary_type is not null");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryTypeEqualTo(String value) {
            addCriterion("disciplinary_type =", value, "disciplinaryType");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryTypeNotEqualTo(String value) {
            addCriterion("disciplinary_type <>", value, "disciplinaryType");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryTypeGreaterThan(String value) {
            addCriterion("disciplinary_type >", value, "disciplinaryType");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryTypeGreaterThanOrEqualTo(String value) {
            addCriterion("disciplinary_type >=", value, "disciplinaryType");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryTypeLessThan(String value) {
            addCriterion("disciplinary_type <", value, "disciplinaryType");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryTypeLessThanOrEqualTo(String value) {
            addCriterion("disciplinary_type <=", value, "disciplinaryType");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryTypeLike(String value) {
            addCriterion("disciplinary_type like", value, "disciplinaryType");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryTypeNotLike(String value) {
            addCriterion("disciplinary_type not like", value, "disciplinaryType");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryTypeIn(List<String> values) {
            addCriterion("disciplinary_type in", values, "disciplinaryType");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryTypeNotIn(List<String> values) {
            addCriterion("disciplinary_type not in", values, "disciplinaryType");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryTypeBetween(String value1, String value2) {
            addCriterion("disciplinary_type between", value1, value2, "disciplinaryType");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryTypeNotBetween(String value1, String value2) {
            addCriterion("disciplinary_type not between", value1, value2, "disciplinaryType");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryOrgIsNull() {
            addCriterion("disciplinary_org is null");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryOrgIsNotNull() {
            addCriterion("disciplinary_org is not null");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryOrgEqualTo(String value) {
            addCriterion("disciplinary_org =", value, "disciplinaryOrg");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryOrgNotEqualTo(String value) {
            addCriterion("disciplinary_org <>", value, "disciplinaryOrg");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryOrgGreaterThan(String value) {
            addCriterion("disciplinary_org >", value, "disciplinaryOrg");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryOrgGreaterThanOrEqualTo(String value) {
            addCriterion("disciplinary_org >=", value, "disciplinaryOrg");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryOrgLessThan(String value) {
            addCriterion("disciplinary_org <", value, "disciplinaryOrg");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryOrgLessThanOrEqualTo(String value) {
            addCriterion("disciplinary_org <=", value, "disciplinaryOrg");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryOrgLike(String value) {
            addCriterion("disciplinary_org like", value, "disciplinaryOrg");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryOrgNotLike(String value) {
            addCriterion("disciplinary_org not like", value, "disciplinaryOrg");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryOrgIn(List<String> values) {
            addCriterion("disciplinary_org in", values, "disciplinaryOrg");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryOrgNotIn(List<String> values) {
            addCriterion("disciplinary_org not in", values, "disciplinaryOrg");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryOrgBetween(String value1, String value2) {
            addCriterion("disciplinary_org between", value1, value2, "disciplinaryOrg");
            return (Criteria) this;
        }

        public Criteria andDisciplinaryOrgNotBetween(String value1, String value2) {
            addCriterion("disciplinary_org not between", value1, value2, "disciplinaryOrg");
            return (Criteria) this;
        }

        public Criteria andViolateTypeIsNull() {
            addCriterion("violate_type is null");
            return (Criteria) this;
        }

        public Criteria andViolateTypeIsNotNull() {
            addCriterion("violate_type is not null");
            return (Criteria) this;
        }

        public Criteria andViolateTypeEqualTo(String value) {
            addCriterion("violate_type =", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeNotEqualTo(String value) {
            addCriterion("violate_type <>", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeGreaterThan(String value) {
            addCriterion("violate_type >", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("violate_type >=", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeLessThan(String value) {
            addCriterion("violate_type <", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeLessThanOrEqualTo(String value) {
            addCriterion("violate_type <=", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeLike(String value) {
            addCriterion("violate_type like", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeNotLike(String value) {
            addCriterion("violate_type not like", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeIn(List<String> values) {
            addCriterion("violate_type in", values, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeNotIn(List<String> values) {
            addCriterion("violate_type not in", values, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeBetween(String value1, String value2) {
            addCriterion("violate_type between", value1, value2, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeNotBetween(String value1, String value2) {
            addCriterion("violate_type not between", value1, value2, "violateType");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlIsNull() {
            addCriterion("original_url is null");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlIsNotNull() {
            addCriterion("original_url is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlEqualTo(String value) {
            addCriterion("original_url =", value, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlNotEqualTo(String value) {
            addCriterion("original_url <>", value, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlGreaterThan(String value) {
            addCriterion("original_url >", value, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlGreaterThanOrEqualTo(String value) {
            addCriterion("original_url >=", value, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlLessThan(String value) {
            addCriterion("original_url <", value, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlLessThanOrEqualTo(String value) {
            addCriterion("original_url <=", value, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlLike(String value) {
            addCriterion("original_url like", value, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlNotLike(String value) {
            addCriterion("original_url not like", value, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlIn(List<String> values) {
            addCriterion("original_url in", values, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlNotIn(List<String> values) {
            addCriterion("original_url not in", values, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlBetween(String value1, String value2) {
            addCriterion("original_url between", value1, value2, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlNotBetween(String value1, String value2) {
            addCriterion("original_url not between", value1, value2, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andNewsUrlIsNull() {
            addCriterion("news_url is null");
            return (Criteria) this;
        }

        public Criteria andNewsUrlIsNotNull() {
            addCriterion("news_url is not null");
            return (Criteria) this;
        }

        public Criteria andNewsUrlEqualTo(String value) {
            addCriterion("news_url =", value, "newsUrl");
            return (Criteria) this;
        }

        public Criteria andNewsUrlNotEqualTo(String value) {
            addCriterion("news_url <>", value, "newsUrl");
            return (Criteria) this;
        }

        public Criteria andNewsUrlGreaterThan(String value) {
            addCriterion("news_url >", value, "newsUrl");
            return (Criteria) this;
        }

        public Criteria andNewsUrlGreaterThanOrEqualTo(String value) {
            addCriterion("news_url >=", value, "newsUrl");
            return (Criteria) this;
        }

        public Criteria andNewsUrlLessThan(String value) {
            addCriterion("news_url <", value, "newsUrl");
            return (Criteria) this;
        }

        public Criteria andNewsUrlLessThanOrEqualTo(String value) {
            addCriterion("news_url <=", value, "newsUrl");
            return (Criteria) this;
        }

        public Criteria andNewsUrlLike(String value) {
            addCriterion("news_url like", value, "newsUrl");
            return (Criteria) this;
        }

        public Criteria andNewsUrlNotLike(String value) {
            addCriterion("news_url not like", value, "newsUrl");
            return (Criteria) this;
        }

        public Criteria andNewsUrlIn(List<String> values) {
            addCriterion("news_url in", values, "newsUrl");
            return (Criteria) this;
        }

        public Criteria andNewsUrlNotIn(List<String> values) {
            addCriterion("news_url not in", values, "newsUrl");
            return (Criteria) this;
        }

        public Criteria andNewsUrlBetween(String value1, String value2) {
            addCriterion("news_url between", value1, value2, "newsUrl");
            return (Criteria) this;
        }

        public Criteria andNewsUrlNotBetween(String value1, String value2) {
            addCriterion("news_url not between", value1, value2, "newsUrl");
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

        public Criteria andIsPublishIsNull() {
            addCriterion("is_publish is null");
            return (Criteria) this;
        }

        public Criteria andIsPublishIsNotNull() {
            addCriterion("is_publish is not null");
            return (Criteria) this;
        }

        public Criteria andIsPublishEqualTo(String value) {
            addCriterion("is_publish =", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishNotEqualTo(String value) {
            addCriterion("is_publish <>", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishGreaterThan(String value) {
            addCriterion("is_publish >", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishGreaterThanOrEqualTo(String value) {
            addCriterion("is_publish >=", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishLessThan(String value) {
            addCriterion("is_publish <", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishLessThanOrEqualTo(String value) {
            addCriterion("is_publish <=", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishLike(String value) {
            addCriterion("is_publish like", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishNotLike(String value) {
            addCriterion("is_publish not like", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishIn(List<String> values) {
            addCriterion("is_publish in", values, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishNotIn(List<String> values) {
            addCriterion("is_publish not in", values, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishBetween(String value1, String value2) {
            addCriterion("is_publish between", value1, value2, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishNotBetween(String value1, String value2) {
            addCriterion("is_publish not between", value1, value2, "isPublish");
            return (Criteria) this;
        }

        public Criteria andLinkCasesIsNull() {
            addCriterion("link_cases is null");
            return (Criteria) this;
        }

        public Criteria andLinkCasesIsNotNull() {
            addCriterion("link_cases is not null");
            return (Criteria) this;
        }

        public Criteria andLinkCasesEqualTo(String value) {
            addCriterion("link_cases =", value, "linkCases");
            return (Criteria) this;
        }

        public Criteria andLinkCasesNotEqualTo(String value) {
            addCriterion("link_cases <>", value, "linkCases");
            return (Criteria) this;
        }

        public Criteria andLinkCasesGreaterThan(String value) {
            addCriterion("link_cases >", value, "linkCases");
            return (Criteria) this;
        }

        public Criteria andLinkCasesGreaterThanOrEqualTo(String value) {
            addCriterion("link_cases >=", value, "linkCases");
            return (Criteria) this;
        }

        public Criteria andLinkCasesLessThan(String value) {
            addCriterion("link_cases <", value, "linkCases");
            return (Criteria) this;
        }

        public Criteria andLinkCasesLessThanOrEqualTo(String value) {
            addCriterion("link_cases <=", value, "linkCases");
            return (Criteria) this;
        }

        public Criteria andLinkCasesLike(String value) {
            addCriterion("link_cases like", value, "linkCases");
            return (Criteria) this;
        }

        public Criteria andLinkCasesNotLike(String value) {
            addCriterion("link_cases not like", value, "linkCases");
            return (Criteria) this;
        }

        public Criteria andLinkCasesIn(List<String> values) {
            addCriterion("link_cases in", values, "linkCases");
            return (Criteria) this;
        }

        public Criteria andLinkCasesNotIn(List<String> values) {
            addCriterion("link_cases not in", values, "linkCases");
            return (Criteria) this;
        }

        public Criteria andLinkCasesBetween(String value1, String value2) {
            addCriterion("link_cases between", value1, value2, "linkCases");
            return (Criteria) this;
        }

        public Criteria andLinkCasesNotBetween(String value1, String value2) {
            addCriterion("link_cases not between", value1, value2, "linkCases");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeIsNull() {
            addCriterion("dispatch_time is null");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeIsNotNull() {
            addCriterion("dispatch_time is not null");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeEqualTo(Date value) {
            addCriterion("dispatch_time =", value, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeNotEqualTo(Date value) {
            addCriterion("dispatch_time <>", value, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeGreaterThan(Date value) {
            addCriterion("dispatch_time >", value, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("dispatch_time >=", value, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeLessThan(Date value) {
            addCriterion("dispatch_time <", value, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeLessThanOrEqualTo(Date value) {
            addCriterion("dispatch_time <=", value, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeIn(List<Date> values) {
            addCriterion("dispatch_time in", values, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeNotIn(List<Date> values) {
            addCriterion("dispatch_time not in", values, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeBetween(Date value1, Date value2) {
            addCriterion("dispatch_time between", value1, value2, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeNotBetween(Date value1, Date value2) {
            addCriterion("dispatch_time not between", value1, value2, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andBulletinTimeIsNull() {
            addCriterion("bulletin_time is null");
            return (Criteria) this;
        }

        public Criteria andBulletinTimeIsNotNull() {
            addCriterion("bulletin_time is not null");
            return (Criteria) this;
        }

        public Criteria andBulletinTimeEqualTo(Date value) {
            addCriterion("bulletin_time =", value, "bulletinTime");
            return (Criteria) this;
        }

        public Criteria andBulletinTimeNotEqualTo(Date value) {
            addCriterion("bulletin_time <>", value, "bulletinTime");
            return (Criteria) this;
        }

        public Criteria andBulletinTimeGreaterThan(Date value) {
            addCriterion("bulletin_time >", value, "bulletinTime");
            return (Criteria) this;
        }

        public Criteria andBulletinTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("bulletin_time >=", value, "bulletinTime");
            return (Criteria) this;
        }

        public Criteria andBulletinTimeLessThan(Date value) {
            addCriterion("bulletin_time <", value, "bulletinTime");
            return (Criteria) this;
        }

        public Criteria andBulletinTimeLessThanOrEqualTo(Date value) {
            addCriterion("bulletin_time <=", value, "bulletinTime");
            return (Criteria) this;
        }

        public Criteria andBulletinTimeIn(List<Date> values) {
            addCriterion("bulletin_time in", values, "bulletinTime");
            return (Criteria) this;
        }

        public Criteria andBulletinTimeNotIn(List<Date> values) {
            addCriterion("bulletin_time not in", values, "bulletinTime");
            return (Criteria) this;
        }

        public Criteria andBulletinTimeBetween(Date value1, Date value2) {
            addCriterion("bulletin_time between", value1, value2, "bulletinTime");
            return (Criteria) this;
        }

        public Criteria andBulletinTimeNotBetween(Date value1, Date value2) {
            addCriterion("bulletin_time not between", value1, value2, "bulletinTime");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleIsNull() {
            addCriterion("bulletin_title is null");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleIsNotNull() {
            addCriterion("bulletin_title is not null");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleEqualTo(String value) {
            addCriterion("bulletin_title =", value, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleNotEqualTo(String value) {
            addCriterion("bulletin_title <>", value, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleGreaterThan(String value) {
            addCriterion("bulletin_title >", value, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleGreaterThanOrEqualTo(String value) {
            addCriterion("bulletin_title >=", value, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleLessThan(String value) {
            addCriterion("bulletin_title <", value, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleLessThanOrEqualTo(String value) {
            addCriterion("bulletin_title <=", value, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleLike(String value) {
            addCriterion("bulletin_title like", value, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleNotLike(String value) {
            addCriterion("bulletin_title not like", value, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleIn(List<String> values) {
            addCriterion("bulletin_title in", values, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleNotIn(List<String> values) {
            addCriterion("bulletin_title not in", values, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleBetween(String value1, String value2) {
            addCriterion("bulletin_title between", value1, value2, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleNotBetween(String value1, String value2) {
            addCriterion("bulletin_title not between", value1, value2, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBelongTradeIsNull() {
            addCriterion("belong_trade is null");
            return (Criteria) this;
        }

        public Criteria andBelongTradeIsNotNull() {
            addCriterion("belong_trade is not null");
            return (Criteria) this;
        }

        public Criteria andBelongTradeEqualTo(String value) {
            addCriterion("belong_trade =", value, "belongTrade");
            return (Criteria) this;
        }

        public Criteria andBelongTradeNotEqualTo(String value) {
            addCriterion("belong_trade <>", value, "belongTrade");
            return (Criteria) this;
        }

        public Criteria andBelongTradeGreaterThan(String value) {
            addCriterion("belong_trade >", value, "belongTrade");
            return (Criteria) this;
        }

        public Criteria andBelongTradeGreaterThanOrEqualTo(String value) {
            addCriterion("belong_trade >=", value, "belongTrade");
            return (Criteria) this;
        }

        public Criteria andBelongTradeLessThan(String value) {
            addCriterion("belong_trade <", value, "belongTrade");
            return (Criteria) this;
        }

        public Criteria andBelongTradeLessThanOrEqualTo(String value) {
            addCriterion("belong_trade <=", value, "belongTrade");
            return (Criteria) this;
        }

        public Criteria andBelongTradeLike(String value) {
            addCriterion("belong_trade like", value, "belongTrade");
            return (Criteria) this;
        }

        public Criteria andBelongTradeNotLike(String value) {
            addCriterion("belong_trade not like", value, "belongTrade");
            return (Criteria) this;
        }

        public Criteria andBelongTradeIn(List<String> values) {
            addCriterion("belong_trade in", values, "belongTrade");
            return (Criteria) this;
        }

        public Criteria andBelongTradeNotIn(List<String> values) {
            addCriterion("belong_trade not in", values, "belongTrade");
            return (Criteria) this;
        }

        public Criteria andBelongTradeBetween(String value1, String value2) {
            addCriterion("belong_trade between", value1, value2, "belongTrade");
            return (Criteria) this;
        }

        public Criteria andBelongTradeNotBetween(String value1, String value2) {
            addCriterion("belong_trade not between", value1, value2, "belongTrade");
            return (Criteria) this;
        }

        public Criteria andViolateTypeValueIsNull() {
            addCriterion("violate_type_value is null");
            return (Criteria) this;
        }

        public Criteria andViolateTypeValueIsNotNull() {
            addCriterion("violate_type_value is not null");
            return (Criteria) this;
        }

        public Criteria andViolateTypeValueEqualTo(String value) {
            addCriterion("violate_type_value =", value, "violateTypeValue");
            return (Criteria) this;
        }

        public Criteria andViolateTypeValueNotEqualTo(String value) {
            addCriterion("violate_type_value <>", value, "violateTypeValue");
            return (Criteria) this;
        }

        public Criteria andViolateTypeValueGreaterThan(String value) {
            addCriterion("violate_type_value >", value, "violateTypeValue");
            return (Criteria) this;
        }

        public Criteria andViolateTypeValueGreaterThanOrEqualTo(String value) {
            addCriterion("violate_type_value >=", value, "violateTypeValue");
            return (Criteria) this;
        }

        public Criteria andViolateTypeValueLessThan(String value) {
            addCriterion("violate_type_value <", value, "violateTypeValue");
            return (Criteria) this;
        }

        public Criteria andViolateTypeValueLessThanOrEqualTo(String value) {
            addCriterion("violate_type_value <=", value, "violateTypeValue");
            return (Criteria) this;
        }

        public Criteria andViolateTypeValueLike(String value) {
            addCriterion("violate_type_value like", value, "violateTypeValue");
            return (Criteria) this;
        }

        public Criteria andViolateTypeValueNotLike(String value) {
            addCriterion("violate_type_value not like", value, "violateTypeValue");
            return (Criteria) this;
        }

        public Criteria andViolateTypeValueIn(List<String> values) {
            addCriterion("violate_type_value in", values, "violateTypeValue");
            return (Criteria) this;
        }

        public Criteria andViolateTypeValueNotIn(List<String> values) {
            addCriterion("violate_type_value not in", values, "violateTypeValue");
            return (Criteria) this;
        }

        public Criteria andViolateTypeValueBetween(String value1, String value2) {
            addCriterion("violate_type_value between", value1, value2, "violateTypeValue");
            return (Criteria) this;
        }

        public Criteria andViolateTypeValueNotBetween(String value1, String value2) {
            addCriterion("violate_type_value not between", value1, value2, "violateTypeValue");
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

        public Criteria andIfAdoptIsNull() {
            addCriterion("if_adopt is null");
            return (Criteria) this;
        }

        public Criteria andIfAdoptIsNotNull() {
            addCriterion("if_adopt is not null");
            return (Criteria) this;
        }

        public Criteria andIfAdoptEqualTo(String value) {
            addCriterion("if_adopt =", value, "ifAdopt");
            return (Criteria) this;
        }

        public Criteria andIfAdoptNotEqualTo(String value) {
            addCriterion("if_adopt <>", value, "ifAdopt");
            return (Criteria) this;
        }

        public Criteria andIfAdoptGreaterThan(String value) {
            addCriterion("if_adopt >", value, "ifAdopt");
            return (Criteria) this;
        }

        public Criteria andIfAdoptGreaterThanOrEqualTo(String value) {
            addCriterion("if_adopt >=", value, "ifAdopt");
            return (Criteria) this;
        }

        public Criteria andIfAdoptLessThan(String value) {
            addCriterion("if_adopt <", value, "ifAdopt");
            return (Criteria) this;
        }

        public Criteria andIfAdoptLessThanOrEqualTo(String value) {
            addCriterion("if_adopt <=", value, "ifAdopt");
            return (Criteria) this;
        }

        public Criteria andIfAdoptLike(String value) {
            addCriterion("if_adopt like", value, "ifAdopt");
            return (Criteria) this;
        }

        public Criteria andIfAdoptNotLike(String value) {
            addCriterion("if_adopt not like", value, "ifAdopt");
            return (Criteria) this;
        }

        public Criteria andIfAdoptIn(List<String> values) {
            addCriterion("if_adopt in", values, "ifAdopt");
            return (Criteria) this;
        }

        public Criteria andIfAdoptNotIn(List<String> values) {
            addCriterion("if_adopt not in", values, "ifAdopt");
            return (Criteria) this;
        }

        public Criteria andIfAdoptBetween(String value1, String value2) {
            addCriterion("if_adopt between", value1, value2, "ifAdopt");
            return (Criteria) this;
        }

        public Criteria andIfAdoptNotBetween(String value1, String value2) {
            addCriterion("if_adopt not between", value1, value2, "ifAdopt");
            return (Criteria) this;
        }

        public Criteria andAdoptConditionIsNull() {
            addCriterion("adopt_condition is null");
            return (Criteria) this;
        }

        public Criteria andAdoptConditionIsNotNull() {
            addCriterion("adopt_condition is not null");
            return (Criteria) this;
        }

        public Criteria andAdoptConditionEqualTo(String value) {
            addCriterion("adopt_condition =", value, "adoptCondition");
            return (Criteria) this;
        }

        public Criteria andAdoptConditionNotEqualTo(String value) {
            addCriterion("adopt_condition <>", value, "adoptCondition");
            return (Criteria) this;
        }

        public Criteria andAdoptConditionGreaterThan(String value) {
            addCriterion("adopt_condition >", value, "adoptCondition");
            return (Criteria) this;
        }

        public Criteria andAdoptConditionGreaterThanOrEqualTo(String value) {
            addCriterion("adopt_condition >=", value, "adoptCondition");
            return (Criteria) this;
        }

        public Criteria andAdoptConditionLessThan(String value) {
            addCriterion("adopt_condition <", value, "adoptCondition");
            return (Criteria) this;
        }

        public Criteria andAdoptConditionLessThanOrEqualTo(String value) {
            addCriterion("adopt_condition <=", value, "adoptCondition");
            return (Criteria) this;
        }

        public Criteria andAdoptConditionLike(String value) {
            addCriterion("adopt_condition like", value, "adoptCondition");
            return (Criteria) this;
        }

        public Criteria andAdoptConditionNotLike(String value) {
            addCriterion("adopt_condition not like", value, "adoptCondition");
            return (Criteria) this;
        }

        public Criteria andAdoptConditionIn(List<String> values) {
            addCriterion("adopt_condition in", values, "adoptCondition");
            return (Criteria) this;
        }

        public Criteria andAdoptConditionNotIn(List<String> values) {
            addCriterion("adopt_condition not in", values, "adoptCondition");
            return (Criteria) this;
        }

        public Criteria andAdoptConditionBetween(String value1, String value2) {
            addCriterion("adopt_condition between", value1, value2, "adoptCondition");
            return (Criteria) this;
        }

        public Criteria andAdoptConditionNotBetween(String value1, String value2) {
            addCriterion("adopt_condition not between", value1, value2, "adoptCondition");
            return (Criteria) this;
        }

        public Criteria andLiveNodeIsNull() {
            addCriterion("live_node is null");
            return (Criteria) this;
        }

        public Criteria andLiveNodeIsNotNull() {
            addCriterion("live_node is not null");
            return (Criteria) this;
        }

        public Criteria andLiveNodeEqualTo(String value) {
            addCriterion("live_node =", value, "liveNode");
            return (Criteria) this;
        }

        public Criteria andLiveNodeNotEqualTo(String value) {
            addCriterion("live_node <>", value, "liveNode");
            return (Criteria) this;
        }

        public Criteria andLiveNodeGreaterThan(String value) {
            addCriterion("live_node >", value, "liveNode");
            return (Criteria) this;
        }

        public Criteria andLiveNodeGreaterThanOrEqualTo(String value) {
            addCriterion("live_node >=", value, "liveNode");
            return (Criteria) this;
        }

        public Criteria andLiveNodeLessThan(String value) {
            addCriterion("live_node <", value, "liveNode");
            return (Criteria) this;
        }

        public Criteria andLiveNodeLessThanOrEqualTo(String value) {
            addCriterion("live_node <=", value, "liveNode");
            return (Criteria) this;
        }

        public Criteria andLiveNodeLike(String value) {
            addCriterion("live_node like", value, "liveNode");
            return (Criteria) this;
        }

        public Criteria andLiveNodeNotLike(String value) {
            addCriterion("live_node not like", value, "liveNode");
            return (Criteria) this;
        }

        public Criteria andLiveNodeIn(List<String> values) {
            addCriterion("live_node in", values, "liveNode");
            return (Criteria) this;
        }

        public Criteria andLiveNodeNotIn(List<String> values) {
            addCriterion("live_node not in", values, "liveNode");
            return (Criteria) this;
        }

        public Criteria andLiveNodeBetween(String value1, String value2) {
            addCriterion("live_node between", value1, value2, "liveNode");
            return (Criteria) this;
        }

        public Criteria andLiveNodeNotBetween(String value1, String value2) {
            addCriterion("live_node not between", value1, value2, "liveNode");
            return (Criteria) this;
        }

        public Criteria andPunishOrganizationIsNull() {
            addCriterion("punish_organization is null");
            return (Criteria) this;
        }

        public Criteria andPunishOrganizationIsNotNull() {
            addCriterion("punish_organization is not null");
            return (Criteria) this;
        }

        public Criteria andPunishOrganizationEqualTo(String value) {
            addCriterion("punish_organization =", value, "punishOrganization");
            return (Criteria) this;
        }

        public Criteria andPunishOrganizationNotEqualTo(String value) {
            addCriterion("punish_organization <>", value, "punishOrganization");
            return (Criteria) this;
        }

        public Criteria andPunishOrganizationGreaterThan(String value) {
            addCriterion("punish_organization >", value, "punishOrganization");
            return (Criteria) this;
        }

        public Criteria andPunishOrganizationGreaterThanOrEqualTo(String value) {
            addCriterion("punish_organization >=", value, "punishOrganization");
            return (Criteria) this;
        }

        public Criteria andPunishOrganizationLessThan(String value) {
            addCriterion("punish_organization <", value, "punishOrganization");
            return (Criteria) this;
        }

        public Criteria andPunishOrganizationLessThanOrEqualTo(String value) {
            addCriterion("punish_organization <=", value, "punishOrganization");
            return (Criteria) this;
        }

        public Criteria andPunishOrganizationLike(String value) {
            addCriterion("punish_organization like", value, "punishOrganization");
            return (Criteria) this;
        }

        public Criteria andPunishOrganizationNotLike(String value) {
            addCriterion("punish_organization not like", value, "punishOrganization");
            return (Criteria) this;
        }

        public Criteria andPunishOrganizationIn(List<String> values) {
            addCriterion("punish_organization in", values, "punishOrganization");
            return (Criteria) this;
        }

        public Criteria andPunishOrganizationNotIn(List<String> values) {
            addCriterion("punish_organization not in", values, "punishOrganization");
            return (Criteria) this;
        }

        public Criteria andPunishOrganizationBetween(String value1, String value2) {
            addCriterion("punish_organization between", value1, value2, "punishOrganization");
            return (Criteria) this;
        }

        public Criteria andPunishOrganizationNotBetween(String value1, String value2) {
            addCriterion("punish_organization not between", value1, value2, "punishOrganization");
            return (Criteria) this;
        }

        public Criteria andNewOctMarketFlgIsNull() {
            addCriterion("new_oct_market_flg is null");
            return (Criteria) this;
        }

        public Criteria andNewOctMarketFlgIsNotNull() {
            addCriterion("new_oct_market_flg is not null");
            return (Criteria) this;
        }

        public Criteria andNewOctMarketFlgEqualTo(String value) {
            addCriterion("new_oct_market_flg =", value, "newOctMarketFlg");
            return (Criteria) this;
        }

        public Criteria andNewOctMarketFlgNotEqualTo(String value) {
            addCriterion("new_oct_market_flg <>", value, "newOctMarketFlg");
            return (Criteria) this;
        }

        public Criteria andNewOctMarketFlgGreaterThan(String value) {
            addCriterion("new_oct_market_flg >", value, "newOctMarketFlg");
            return (Criteria) this;
        }

        public Criteria andNewOctMarketFlgGreaterThanOrEqualTo(String value) {
            addCriterion("new_oct_market_flg >=", value, "newOctMarketFlg");
            return (Criteria) this;
        }

        public Criteria andNewOctMarketFlgLessThan(String value) {
            addCriterion("new_oct_market_flg <", value, "newOctMarketFlg");
            return (Criteria) this;
        }

        public Criteria andNewOctMarketFlgLessThanOrEqualTo(String value) {
            addCriterion("new_oct_market_flg <=", value, "newOctMarketFlg");
            return (Criteria) this;
        }

        public Criteria andNewOctMarketFlgLike(String value) {
            addCriterion("new_oct_market_flg like", value, "newOctMarketFlg");
            return (Criteria) this;
        }

        public Criteria andNewOctMarketFlgNotLike(String value) {
            addCriterion("new_oct_market_flg not like", value, "newOctMarketFlg");
            return (Criteria) this;
        }

        public Criteria andNewOctMarketFlgIn(List<String> values) {
            addCriterion("new_oct_market_flg in", values, "newOctMarketFlg");
            return (Criteria) this;
        }

        public Criteria andNewOctMarketFlgNotIn(List<String> values) {
            addCriterion("new_oct_market_flg not in", values, "newOctMarketFlg");
            return (Criteria) this;
        }

        public Criteria andNewOctMarketFlgBetween(String value1, String value2) {
            addCriterion("new_oct_market_flg between", value1, value2, "newOctMarketFlg");
            return (Criteria) this;
        }

        public Criteria andNewOctMarketFlgNotBetween(String value1, String value2) {
            addCriterion("new_oct_market_flg not between", value1, value2, "newOctMarketFlg");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberIsNull() {
            addCriterion("document_number is null");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberIsNotNull() {
            addCriterion("document_number is not null");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberEqualTo(String value) {
            addCriterion("document_number =", value, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberNotEqualTo(String value) {
            addCriterion("document_number <>", value, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberGreaterThan(String value) {
            addCriterion("document_number >", value, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberGreaterThanOrEqualTo(String value) {
            addCriterion("document_number >=", value, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberLessThan(String value) {
            addCriterion("document_number <", value, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberLessThanOrEqualTo(String value) {
            addCriterion("document_number <=", value, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberLike(String value) {
            addCriterion("document_number like", value, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberNotLike(String value) {
            addCriterion("document_number not like", value, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberIn(List<String> values) {
            addCriterion("document_number in", values, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberNotIn(List<String> values) {
            addCriterion("document_number not in", values, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberBetween(String value1, String value2) {
            addCriterion("document_number between", value1, value2, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberNotBetween(String value1, String value2) {
            addCriterion("document_number not between", value1, value2, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andRegionIsNull() {
            addCriterion("region is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("region is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(String value) {
            addCriterion("region =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(String value) {
            addCriterion("region <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(String value) {
            addCriterion("region >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(String value) {
            addCriterion("region >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(String value) {
            addCriterion("region <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(String value) {
            addCriterion("region <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLike(String value) {
            addCriterion("region like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotLike(String value) {
            addCriterion("region not like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<String> values) {
            addCriterion("region in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<String> values) {
            addCriterion("region not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(String value1, String value2) {
            addCriterion("region between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(String value1, String value2) {
            addCriterion("region not between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andPunishPropertyIsNull() {
            addCriterion("punish_property is null");
            return (Criteria) this;
        }

        public Criteria andPunishPropertyIsNotNull() {
            addCriterion("punish_property is not null");
            return (Criteria) this;
        }

        public Criteria andPunishPropertyEqualTo(String value) {
            addCriterion("punish_property =", value, "punishProperty");
            return (Criteria) this;
        }

        public Criteria andPunishPropertyNotEqualTo(String value) {
            addCriterion("punish_property <>", value, "punishProperty");
            return (Criteria) this;
        }

        public Criteria andPunishPropertyGreaterThan(String value) {
            addCriterion("punish_property >", value, "punishProperty");
            return (Criteria) this;
        }

        public Criteria andPunishPropertyGreaterThanOrEqualTo(String value) {
            addCriterion("punish_property >=", value, "punishProperty");
            return (Criteria) this;
        }

        public Criteria andPunishPropertyLessThan(String value) {
            addCriterion("punish_property <", value, "punishProperty");
            return (Criteria) this;
        }

        public Criteria andPunishPropertyLessThanOrEqualTo(String value) {
            addCriterion("punish_property <=", value, "punishProperty");
            return (Criteria) this;
        }

        public Criteria andPunishPropertyLike(String value) {
            addCriterion("punish_property like", value, "punishProperty");
            return (Criteria) this;
        }

        public Criteria andPunishPropertyNotLike(String value) {
            addCriterion("punish_property not like", value, "punishProperty");
            return (Criteria) this;
        }

        public Criteria andPunishPropertyIn(List<String> values) {
            addCriterion("punish_property in", values, "punishProperty");
            return (Criteria) this;
        }

        public Criteria andPunishPropertyNotIn(List<String> values) {
            addCriterion("punish_property not in", values, "punishProperty");
            return (Criteria) this;
        }

        public Criteria andPunishPropertyBetween(String value1, String value2) {
            addCriterion("punish_property between", value1, value2, "punishProperty");
            return (Criteria) this;
        }

        public Criteria andPunishPropertyNotBetween(String value1, String value2) {
            addCriterion("punish_property not between", value1, value2, "punishProperty");
            return (Criteria) this;
        }

        public Criteria andYearsIsNull() {
            addCriterion("years is null");
            return (Criteria) this;
        }

        public Criteria andYearsIsNotNull() {
            addCriterion("years is not null");
            return (Criteria) this;
        }

        public Criteria andYearsEqualTo(String value) {
            addCriterion("years =", value, "years");
            return (Criteria) this;
        }

        public Criteria andYearsNotEqualTo(String value) {
            addCriterion("years <>", value, "years");
            return (Criteria) this;
        }

        public Criteria andYearsGreaterThan(String value) {
            addCriterion("years >", value, "years");
            return (Criteria) this;
        }

        public Criteria andYearsGreaterThanOrEqualTo(String value) {
            addCriterion("years >=", value, "years");
            return (Criteria) this;
        }

        public Criteria andYearsLessThan(String value) {
            addCriterion("years <", value, "years");
            return (Criteria) this;
        }

        public Criteria andYearsLessThanOrEqualTo(String value) {
            addCriterion("years <=", value, "years");
            return (Criteria) this;
        }

        public Criteria andYearsLike(String value) {
            addCriterion("years like", value, "years");
            return (Criteria) this;
        }

        public Criteria andYearsNotLike(String value) {
            addCriterion("years not like", value, "years");
            return (Criteria) this;
        }

        public Criteria andYearsIn(List<String> values) {
            addCriterion("years in", values, "years");
            return (Criteria) this;
        }

        public Criteria andYearsNotIn(List<String> values) {
            addCriterion("years not in", values, "years");
            return (Criteria) this;
        }

        public Criteria andYearsBetween(String value1, String value2) {
            addCriterion("years between", value1, value2, "years");
            return (Criteria) this;
        }

        public Criteria andYearsNotBetween(String value1, String value2) {
            addCriterion("years not between", value1, value2, "years");
            return (Criteria) this;
        }

        public Criteria andCustomNumberIsNull() {
            addCriterion("custom_number is null");
            return (Criteria) this;
        }

        public Criteria andCustomNumberIsNotNull() {
            addCriterion("custom_number is not null");
            return (Criteria) this;
        }

        public Criteria andCustomNumberEqualTo(String value) {
            addCriterion("custom_number =", value, "customNumber");
            return (Criteria) this;
        }

        public Criteria andCustomNumberNotEqualTo(String value) {
            addCriterion("custom_number <>", value, "customNumber");
            return (Criteria) this;
        }

        public Criteria andCustomNumberGreaterThan(String value) {
            addCriterion("custom_number >", value, "customNumber");
            return (Criteria) this;
        }

        public Criteria andCustomNumberGreaterThanOrEqualTo(String value) {
            addCriterion("custom_number >=", value, "customNumber");
            return (Criteria) this;
        }

        public Criteria andCustomNumberLessThan(String value) {
            addCriterion("custom_number <", value, "customNumber");
            return (Criteria) this;
        }

        public Criteria andCustomNumberLessThanOrEqualTo(String value) {
            addCriterion("custom_number <=", value, "customNumber");
            return (Criteria) this;
        }

        public Criteria andCustomNumberLike(String value) {
            addCriterion("custom_number like", value, "customNumber");
            return (Criteria) this;
        }

        public Criteria andCustomNumberNotLike(String value) {
            addCriterion("custom_number not like", value, "customNumber");
            return (Criteria) this;
        }

        public Criteria andCustomNumberIn(List<String> values) {
            addCriterion("custom_number in", values, "customNumber");
            return (Criteria) this;
        }

        public Criteria andCustomNumberNotIn(List<String> values) {
            addCriterion("custom_number not in", values, "customNumber");
            return (Criteria) this;
        }

        public Criteria andCustomNumberBetween(String value1, String value2) {
            addCriterion("custom_number between", value1, value2, "customNumber");
            return (Criteria) this;
        }

        public Criteria andCustomNumberNotBetween(String value1, String value2) {
            addCriterion("custom_number not between", value1, value2, "customNumber");
            return (Criteria) this;
        }

        public Criteria andLinkNumberIsNull() {
            addCriterion("link_number is null");
            return (Criteria) this;
        }

        public Criteria andLinkNumberIsNotNull() {
            addCriterion("link_number is not null");
            return (Criteria) this;
        }

        public Criteria andLinkNumberEqualTo(Integer value) {
            addCriterion("link_number =", value, "linkNumber");
            return (Criteria) this;
        }

        public Criteria andLinkNumberNotEqualTo(Integer value) {
            addCriterion("link_number <>", value, "linkNumber");
            return (Criteria) this;
        }

        public Criteria andLinkNumberGreaterThan(Integer value) {
            addCriterion("link_number >", value, "linkNumber");
            return (Criteria) this;
        }

        public Criteria andLinkNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("link_number >=", value, "linkNumber");
            return (Criteria) this;
        }

        public Criteria andLinkNumberLessThan(Integer value) {
            addCriterion("link_number <", value, "linkNumber");
            return (Criteria) this;
        }

        public Criteria andLinkNumberLessThanOrEqualTo(Integer value) {
            addCriterion("link_number <=", value, "linkNumber");
            return (Criteria) this;
        }

        public Criteria andLinkNumberIn(List<Integer> values) {
            addCriterion("link_number in", values, "linkNumber");
            return (Criteria) this;
        }

        public Criteria andLinkNumberNotIn(List<Integer> values) {
            addCriterion("link_number not in", values, "linkNumber");
            return (Criteria) this;
        }

        public Criteria andLinkNumberBetween(Integer value1, Integer value2) {
            addCriterion("link_number between", value1, value2, "linkNumber");
            return (Criteria) this;
        }

        public Criteria andLinkNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("link_number not between", value1, value2, "linkNumber");
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