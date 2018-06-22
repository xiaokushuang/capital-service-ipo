package com.stock.capital.enterprise.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class LawIndexTagExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LawIndexTagExample() {
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

        public Criteria andTagNameIsNull() {
            addCriterion("tag_name is null");
            return (Criteria) this;
        }

        public Criteria andTagNameIsNotNull() {
            addCriterion("tag_name is not null");
            return (Criteria) this;
        }

        public Criteria andTagNameEqualTo(String value) {
            addCriterion("tag_name =", value, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameNotEqualTo(String value) {
            addCriterion("tag_name <>", value, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameGreaterThan(String value) {
            addCriterion("tag_name >", value, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameGreaterThanOrEqualTo(String value) {
            addCriterion("tag_name >=", value, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameLessThan(String value) {
            addCriterion("tag_name <", value, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameLessThanOrEqualTo(String value) {
            addCriterion("tag_name <=", value, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameLike(String value) {
            addCriterion("tag_name like", value, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameNotLike(String value) {
            addCriterion("tag_name not like", value, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameIn(List<String> values) {
            addCriterion("tag_name in", values, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameNotIn(List<String> values) {
            addCriterion("tag_name not in", values, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameBetween(String value1, String value2) {
            addCriterion("tag_name between", value1, value2, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameNotBetween(String value1, String value2) {
            addCriterion("tag_name not between", value1, value2, "tagName");
            return (Criteria) this;
        }

        public Criteria andKeyTypeIsNull() {
            addCriterion("key_type is null");
            return (Criteria) this;
        }

        public Criteria andKeyTypeIsNotNull() {
            addCriterion("key_type is not null");
            return (Criteria) this;
        }

        public Criteria andKeyTypeEqualTo(String value) {
            addCriterion("key_type =", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeNotEqualTo(String value) {
            addCriterion("key_type <>", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeGreaterThan(String value) {
            addCriterion("key_type >", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("key_type >=", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeLessThan(String value) {
            addCriterion("key_type <", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeLessThanOrEqualTo(String value) {
            addCriterion("key_type <=", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeLike(String value) {
            addCriterion("key_type like", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeNotLike(String value) {
            addCriterion("key_type not like", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeIn(List<String> values) {
            addCriterion("key_type in", values, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeNotIn(List<String> values) {
            addCriterion("key_type not in", values, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeBetween(String value1, String value2) {
            addCriterion("key_type between", value1, value2, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeNotBetween(String value1, String value2) {
            addCriterion("key_type not between", value1, value2, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyContentIsNull() {
            addCriterion("key_content is null");
            return (Criteria) this;
        }

        public Criteria andKeyContentIsNotNull() {
            addCriterion("key_content is not null");
            return (Criteria) this;
        }

        public Criteria andKeyContentEqualTo(String value) {
            addCriterion("key_content =", value, "keyContent");
            return (Criteria) this;
        }

        public Criteria andKeyContentNotEqualTo(String value) {
            addCriterion("key_content <>", value, "keyContent");
            return (Criteria) this;
        }

        public Criteria andKeyContentGreaterThan(String value) {
            addCriterion("key_content >", value, "keyContent");
            return (Criteria) this;
        }

        public Criteria andKeyContentGreaterThanOrEqualTo(String value) {
            addCriterion("key_content >=", value, "keyContent");
            return (Criteria) this;
        }

        public Criteria andKeyContentLessThan(String value) {
            addCriterion("key_content <", value, "keyContent");
            return (Criteria) this;
        }

        public Criteria andKeyContentLessThanOrEqualTo(String value) {
            addCriterion("key_content <=", value, "keyContent");
            return (Criteria) this;
        }

        public Criteria andKeyContentLike(String value) {
            addCriterion("key_content like", value, "keyContent");
            return (Criteria) this;
        }

        public Criteria andKeyContentNotLike(String value) {
            addCriterion("key_content not like", value, "keyContent");
            return (Criteria) this;
        }

        public Criteria andKeyContentIn(List<String> values) {
            addCriterion("key_content in", values, "keyContent");
            return (Criteria) this;
        }

        public Criteria andKeyContentNotIn(List<String> values) {
            addCriterion("key_content not in", values, "keyContent");
            return (Criteria) this;
        }

        public Criteria andKeyContentBetween(String value1, String value2) {
            addCriterion("key_content between", value1, value2, "keyContent");
            return (Criteria) this;
        }

        public Criteria andKeyContentNotBetween(String value1, String value2) {
            addCriterion("key_content not between", value1, value2, "keyContent");
            return (Criteria) this;
        }

        public Criteria andKeyAndContentIsNull() {
            addCriterion("key_and_content is null");
            return (Criteria) this;
        }

        public Criteria andKeyAndContentIsNotNull() {
            addCriterion("key_and_content is not null");
            return (Criteria) this;
        }

        public Criteria andKeyAndContentEqualTo(String value) {
            addCriterion("key_and_content =", value, "keyAndContent");
            return (Criteria) this;
        }

        public Criteria andKeyAndContentNotEqualTo(String value) {
            addCriterion("key_and_content <>", value, "keyAndContent");
            return (Criteria) this;
        }

        public Criteria andKeyAndContentGreaterThan(String value) {
            addCriterion("key_and_content >", value, "keyAndContent");
            return (Criteria) this;
        }

        public Criteria andKeyAndContentGreaterThanOrEqualTo(String value) {
            addCriterion("key_and_content >=", value, "keyAndContent");
            return (Criteria) this;
        }

        public Criteria andKeyAndContentLessThan(String value) {
            addCriterion("key_and_content <", value, "keyAndContent");
            return (Criteria) this;
        }

        public Criteria andKeyAndContentLessThanOrEqualTo(String value) {
            addCriterion("key_and_content <=", value, "keyAndContent");
            return (Criteria) this;
        }

        public Criteria andKeyAndContentLike(String value) {
            addCriterion("key_and_content like", value, "keyAndContent");
            return (Criteria) this;
        }

        public Criteria andKeyAndContentNotLike(String value) {
            addCriterion("key_and_content not like", value, "keyAndContent");
            return (Criteria) this;
        }

        public Criteria andKeyAndContentIn(List<String> values) {
            addCriterion("key_and_content in", values, "keyAndContent");
            return (Criteria) this;
        }

        public Criteria andKeyAndContentNotIn(List<String> values) {
            addCriterion("key_and_content not in", values, "keyAndContent");
            return (Criteria) this;
        }

        public Criteria andKeyAndContentBetween(String value1, String value2) {
            addCriterion("key_and_content between", value1, value2, "keyAndContent");
            return (Criteria) this;
        }

        public Criteria andKeyAndContentNotBetween(String value1, String value2) {
            addCriterion("key_and_content not between", value1, value2, "keyAndContent");
            return (Criteria) this;
        }

        public Criteria andKeyNotContentIsNull() {
            addCriterion("key_not_content is null");
            return (Criteria) this;
        }

        public Criteria andKeyNotContentIsNotNull() {
            addCriterion("key_not_content is not null");
            return (Criteria) this;
        }

        public Criteria andKeyNotContentEqualTo(String value) {
            addCriterion("key_not_content =", value, "keyNotContent");
            return (Criteria) this;
        }

        public Criteria andKeyNotContentNotEqualTo(String value) {
            addCriterion("key_not_content <>", value, "keyNotContent");
            return (Criteria) this;
        }

        public Criteria andKeyNotContentGreaterThan(String value) {
            addCriterion("key_not_content >", value, "keyNotContent");
            return (Criteria) this;
        }

        public Criteria andKeyNotContentGreaterThanOrEqualTo(String value) {
            addCriterion("key_not_content >=", value, "keyNotContent");
            return (Criteria) this;
        }

        public Criteria andKeyNotContentLessThan(String value) {
            addCriterion("key_not_content <", value, "keyNotContent");
            return (Criteria) this;
        }

        public Criteria andKeyNotContentLessThanOrEqualTo(String value) {
            addCriterion("key_not_content <=", value, "keyNotContent");
            return (Criteria) this;
        }

        public Criteria andKeyNotContentLike(String value) {
            addCriterion("key_not_content like", value, "keyNotContent");
            return (Criteria) this;
        }

        public Criteria andKeyNotContentNotLike(String value) {
            addCriterion("key_not_content not like", value, "keyNotContent");
            return (Criteria) this;
        }

        public Criteria andKeyNotContentIn(List<String> values) {
            addCriterion("key_not_content in", values, "keyNotContent");
            return (Criteria) this;
        }

        public Criteria andKeyNotContentNotIn(List<String> values) {
            addCriterion("key_not_content not in", values, "keyNotContent");
            return (Criteria) this;
        }

        public Criteria andKeyNotContentBetween(String value1, String value2) {
            addCriterion("key_not_content between", value1, value2, "keyNotContent");
            return (Criteria) this;
        }

        public Criteria andKeyNotContentNotBetween(String value1, String value2) {
            addCriterion("key_not_content not between", value1, value2, "keyNotContent");
            return (Criteria) this;
        }

        public Criteria andCompanySearchCodeIsNull() {
            addCriterion("company_search_code is null");
            return (Criteria) this;
        }

        public Criteria andCompanySearchCodeIsNotNull() {
            addCriterion("company_search_code is not null");
            return (Criteria) this;
        }

        public Criteria andCompanySearchCodeEqualTo(String value) {
            addCriterion("company_search_code =", value, "companySearchCode");
            return (Criteria) this;
        }

        public Criteria andCompanySearchCodeNotEqualTo(String value) {
            addCriterion("company_search_code <>", value, "companySearchCode");
            return (Criteria) this;
        }

        public Criteria andCompanySearchCodeGreaterThan(String value) {
            addCriterion("company_search_code >", value, "companySearchCode");
            return (Criteria) this;
        }

        public Criteria andCompanySearchCodeGreaterThanOrEqualTo(String value) {
            addCriterion("company_search_code >=", value, "companySearchCode");
            return (Criteria) this;
        }

        public Criteria andCompanySearchCodeLessThan(String value) {
            addCriterion("company_search_code <", value, "companySearchCode");
            return (Criteria) this;
        }

        public Criteria andCompanySearchCodeLessThanOrEqualTo(String value) {
            addCriterion("company_search_code <=", value, "companySearchCode");
            return (Criteria) this;
        }

        public Criteria andCompanySearchCodeLike(String value) {
            addCriterion("company_search_code like", value, "companySearchCode");
            return (Criteria) this;
        }

        public Criteria andCompanySearchCodeNotLike(String value) {
            addCriterion("company_search_code not like", value, "companySearchCode");
            return (Criteria) this;
        }

        public Criteria andCompanySearchCodeIn(List<String> values) {
            addCriterion("company_search_code in", values, "companySearchCode");
            return (Criteria) this;
        }

        public Criteria andCompanySearchCodeNotIn(List<String> values) {
            addCriterion("company_search_code not in", values, "companySearchCode");
            return (Criteria) this;
        }

        public Criteria andCompanySearchCodeBetween(String value1, String value2) {
            addCriterion("company_search_code between", value1, value2, "companySearchCode");
            return (Criteria) this;
        }

        public Criteria andCompanySearchCodeNotBetween(String value1, String value2) {
            addCriterion("company_search_code not between", value1, value2, "companySearchCode");
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

        public Criteria andPublishedStartDateIsNull() {
            addCriterion("published_start_date is null");
            return (Criteria) this;
        }

        public Criteria andPublishedStartDateIsNotNull() {
            addCriterion("published_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andPublishedStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("published_start_date =", value, "publishedStartDate");
            return (Criteria) this;
        }

        public Criteria andPublishedStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("published_start_date <>", value, "publishedStartDate");
            return (Criteria) this;
        }

        public Criteria andPublishedStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("published_start_date >", value, "publishedStartDate");
            return (Criteria) this;
        }

        public Criteria andPublishedStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("published_start_date >=", value, "publishedStartDate");
            return (Criteria) this;
        }

        public Criteria andPublishedStartDateLessThan(Date value) {
            addCriterionForJDBCDate("published_start_date <", value, "publishedStartDate");
            return (Criteria) this;
        }

        public Criteria andPublishedStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("published_start_date <=", value, "publishedStartDate");
            return (Criteria) this;
        }

        public Criteria andPublishedStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("published_start_date in", values, "publishedStartDate");
            return (Criteria) this;
        }

        public Criteria andPublishedStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("published_start_date not in", values, "publishedStartDate");
            return (Criteria) this;
        }

        public Criteria andPublishedStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("published_start_date between", value1, value2, "publishedStartDate");
            return (Criteria) this;
        }

        public Criteria andPublishedStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("published_start_date not between", value1, value2, "publishedStartDate");
            return (Criteria) this;
        }

        public Criteria andPublishedEndDateIsNull() {
            addCriterion("published_end_date is null");
            return (Criteria) this;
        }

        public Criteria andPublishedEndDateIsNotNull() {
            addCriterion("published_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andPublishedEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("published_end_date =", value, "publishedEndDate");
            return (Criteria) this;
        }

        public Criteria andPublishedEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("published_end_date <>", value, "publishedEndDate");
            return (Criteria) this;
        }

        public Criteria andPublishedEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("published_end_date >", value, "publishedEndDate");
            return (Criteria) this;
        }

        public Criteria andPublishedEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("published_end_date >=", value, "publishedEndDate");
            return (Criteria) this;
        }

        public Criteria andPublishedEndDateLessThan(Date value) {
            addCriterionForJDBCDate("published_end_date <", value, "publishedEndDate");
            return (Criteria) this;
        }

        public Criteria andPublishedEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("published_end_date <=", value, "publishedEndDate");
            return (Criteria) this;
        }

        public Criteria andPublishedEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("published_end_date in", values, "publishedEndDate");
            return (Criteria) this;
        }

        public Criteria andPublishedEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("published_end_date not in", values, "publishedEndDate");
            return (Criteria) this;
        }

        public Criteria andPublishedEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("published_end_date between", value1, value2, "publishedEndDate");
            return (Criteria) this;
        }

        public Criteria andPublishedEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("published_end_date not between", value1, value2, "publishedEndDate");
            return (Criteria) this;
        }

        public Criteria andInvalidStartDateIsNull() {
            addCriterion("invalid_start_date is null");
            return (Criteria) this;
        }

        public Criteria andInvalidStartDateIsNotNull() {
            addCriterion("invalid_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("invalid_start_date =", value, "invalidStartDate");
            return (Criteria) this;
        }

        public Criteria andInvalidStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("invalid_start_date <>", value, "invalidStartDate");
            return (Criteria) this;
        }

        public Criteria andInvalidStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("invalid_start_date >", value, "invalidStartDate");
            return (Criteria) this;
        }

        public Criteria andInvalidStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("invalid_start_date >=", value, "invalidStartDate");
            return (Criteria) this;
        }

        public Criteria andInvalidStartDateLessThan(Date value) {
            addCriterionForJDBCDate("invalid_start_date <", value, "invalidStartDate");
            return (Criteria) this;
        }

        public Criteria andInvalidStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("invalid_start_date <=", value, "invalidStartDate");
            return (Criteria) this;
        }

        public Criteria andInvalidStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("invalid_start_date in", values, "invalidStartDate");
            return (Criteria) this;
        }

        public Criteria andInvalidStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("invalid_start_date not in", values, "invalidStartDate");
            return (Criteria) this;
        }

        public Criteria andInvalidStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("invalid_start_date between", value1, value2, "invalidStartDate");
            return (Criteria) this;
        }

        public Criteria andInvalidStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("invalid_start_date not between", value1, value2, "invalidStartDate");
            return (Criteria) this;
        }

        public Criteria andInvalidEndDateIsNull() {
            addCriterion("invalid_end_date is null");
            return (Criteria) this;
        }

        public Criteria andInvalidEndDateIsNotNull() {
            addCriterion("invalid_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("invalid_end_date =", value, "invalidEndDate");
            return (Criteria) this;
        }

        public Criteria andInvalidEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("invalid_end_date <>", value, "invalidEndDate");
            return (Criteria) this;
        }

        public Criteria andInvalidEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("invalid_end_date >", value, "invalidEndDate");
            return (Criteria) this;
        }

        public Criteria andInvalidEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("invalid_end_date >=", value, "invalidEndDate");
            return (Criteria) this;
        }

        public Criteria andInvalidEndDateLessThan(Date value) {
            addCriterionForJDBCDate("invalid_end_date <", value, "invalidEndDate");
            return (Criteria) this;
        }

        public Criteria andInvalidEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("invalid_end_date <=", value, "invalidEndDate");
            return (Criteria) this;
        }

        public Criteria andInvalidEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("invalid_end_date in", values, "invalidEndDate");
            return (Criteria) this;
        }

        public Criteria andInvalidEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("invalid_end_date not in", values, "invalidEndDate");
            return (Criteria) this;
        }

        public Criteria andInvalidEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("invalid_end_date between", value1, value2, "invalidEndDate");
            return (Criteria) this;
        }

        public Criteria andInvalidEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("invalid_end_date not between", value1, value2, "invalidEndDate");
            return (Criteria) this;
        }

        public Criteria andApplicationScopeIsNull() {
            addCriterion("application_scope is null");
            return (Criteria) this;
        }

        public Criteria andApplicationScopeIsNotNull() {
            addCriterion("application_scope is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationScopeEqualTo(String value) {
            addCriterion("application_scope =", value, "applicationScope");
            return (Criteria) this;
        }

        public Criteria andApplicationScopeNotEqualTo(String value) {
            addCriterion("application_scope <>", value, "applicationScope");
            return (Criteria) this;
        }

        public Criteria andApplicationScopeGreaterThan(String value) {
            addCriterion("application_scope >", value, "applicationScope");
            return (Criteria) this;
        }

        public Criteria andApplicationScopeGreaterThanOrEqualTo(String value) {
            addCriterion("application_scope >=", value, "applicationScope");
            return (Criteria) this;
        }

        public Criteria andApplicationScopeLessThan(String value) {
            addCriterion("application_scope <", value, "applicationScope");
            return (Criteria) this;
        }

        public Criteria andApplicationScopeLessThanOrEqualTo(String value) {
            addCriterion("application_scope <=", value, "applicationScope");
            return (Criteria) this;
        }

        public Criteria andApplicationScopeLike(String value) {
            addCriterion("application_scope like", value, "applicationScope");
            return (Criteria) this;
        }

        public Criteria andApplicationScopeNotLike(String value) {
            addCriterion("application_scope not like", value, "applicationScope");
            return (Criteria) this;
        }

        public Criteria andApplicationScopeIn(List<String> values) {
            addCriterion("application_scope in", values, "applicationScope");
            return (Criteria) this;
        }

        public Criteria andApplicationScopeNotIn(List<String> values) {
            addCriterion("application_scope not in", values, "applicationScope");
            return (Criteria) this;
        }

        public Criteria andApplicationScopeBetween(String value1, String value2) {
            addCriterion("application_scope between", value1, value2, "applicationScope");
            return (Criteria) this;
        }

        public Criteria andApplicationScopeNotBetween(String value1, String value2) {
            addCriterion("application_scope not between", value1, value2, "applicationScope");
            return (Criteria) this;
        }

        public Criteria andUnitsIsNull() {
            addCriterion("units is null");
            return (Criteria) this;
        }

        public Criteria andUnitsIsNotNull() {
            addCriterion("units is not null");
            return (Criteria) this;
        }

        public Criteria andUnitsEqualTo(String value) {
            addCriterion("units =", value, "units");
            return (Criteria) this;
        }

        public Criteria andUnitsNotEqualTo(String value) {
            addCriterion("units <>", value, "units");
            return (Criteria) this;
        }

        public Criteria andUnitsGreaterThan(String value) {
            addCriterion("units >", value, "units");
            return (Criteria) this;
        }

        public Criteria andUnitsGreaterThanOrEqualTo(String value) {
            addCriterion("units >=", value, "units");
            return (Criteria) this;
        }

        public Criteria andUnitsLessThan(String value) {
            addCriterion("units <", value, "units");
            return (Criteria) this;
        }

        public Criteria andUnitsLessThanOrEqualTo(String value) {
            addCriterion("units <=", value, "units");
            return (Criteria) this;
        }

        public Criteria andUnitsLike(String value) {
            addCriterion("units like", value, "units");
            return (Criteria) this;
        }

        public Criteria andUnitsNotLike(String value) {
            addCriterion("units not like", value, "units");
            return (Criteria) this;
        }

        public Criteria andUnitsIn(List<String> values) {
            addCriterion("units in", values, "units");
            return (Criteria) this;
        }

        public Criteria andUnitsNotIn(List<String> values) {
            addCriterion("units not in", values, "units");
            return (Criteria) this;
        }

        public Criteria andUnitsBetween(String value1, String value2) {
            addCriterion("units between", value1, value2, "units");
            return (Criteria) this;
        }

        public Criteria andUnitsNotBetween(String value1, String value2) {
            addCriterion("units not between", value1, value2, "units");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(String value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(String value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(String value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(String value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(String value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(String value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLike(String value) {
            addCriterion("flag like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotLike(String value) {
            addCriterion("flag not like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<String> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<String> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(String value1, String value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(String value1, String value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andKeyTitleIsNull() {
            addCriterion("key_title is null");
            return (Criteria) this;
        }

        public Criteria andKeyTitleIsNotNull() {
            addCriterion("key_title is not null");
            return (Criteria) this;
        }

        public Criteria andKeyTitleEqualTo(String value) {
            addCriterion("key_title =", value, "keyTitle");
            return (Criteria) this;
        }

        public Criteria andKeyTitleNotEqualTo(String value) {
            addCriterion("key_title <>", value, "keyTitle");
            return (Criteria) this;
        }

        public Criteria andKeyTitleGreaterThan(String value) {
            addCriterion("key_title >", value, "keyTitle");
            return (Criteria) this;
        }

        public Criteria andKeyTitleGreaterThanOrEqualTo(String value) {
            addCriterion("key_title >=", value, "keyTitle");
            return (Criteria) this;
        }

        public Criteria andKeyTitleLessThan(String value) {
            addCriterion("key_title <", value, "keyTitle");
            return (Criteria) this;
        }

        public Criteria andKeyTitleLessThanOrEqualTo(String value) {
            addCriterion("key_title <=", value, "keyTitle");
            return (Criteria) this;
        }

        public Criteria andKeyTitleLike(String value) {
            addCriterion("key_title like", value, "keyTitle");
            return (Criteria) this;
        }

        public Criteria andKeyTitleNotLike(String value) {
            addCriterion("key_title not like", value, "keyTitle");
            return (Criteria) this;
        }

        public Criteria andKeyTitleIn(List<String> values) {
            addCriterion("key_title in", values, "keyTitle");
            return (Criteria) this;
        }

        public Criteria andKeyTitleNotIn(List<String> values) {
            addCriterion("key_title not in", values, "keyTitle");
            return (Criteria) this;
        }

        public Criteria andKeyTitleBetween(String value1, String value2) {
            addCriterion("key_title between", value1, value2, "keyTitle");
            return (Criteria) this;
        }

        public Criteria andKeyTitleNotBetween(String value1, String value2) {
            addCriterion("key_title not between", value1, value2, "keyTitle");
            return (Criteria) this;
        }

        public Criteria andKeyAndTitleIsNull() {
            addCriterion("key_and_title is null");
            return (Criteria) this;
        }

        public Criteria andKeyAndTitleIsNotNull() {
            addCriterion("key_and_title is not null");
            return (Criteria) this;
        }

        public Criteria andKeyAndTitleEqualTo(String value) {
            addCriterion("key_and_title =", value, "keyAndTitle");
            return (Criteria) this;
        }

        public Criteria andKeyAndTitleNotEqualTo(String value) {
            addCriterion("key_and_title <>", value, "keyAndTitle");
            return (Criteria) this;
        }

        public Criteria andKeyAndTitleGreaterThan(String value) {
            addCriterion("key_and_title >", value, "keyAndTitle");
            return (Criteria) this;
        }

        public Criteria andKeyAndTitleGreaterThanOrEqualTo(String value) {
            addCriterion("key_and_title >=", value, "keyAndTitle");
            return (Criteria) this;
        }

        public Criteria andKeyAndTitleLessThan(String value) {
            addCriterion("key_and_title <", value, "keyAndTitle");
            return (Criteria) this;
        }

        public Criteria andKeyAndTitleLessThanOrEqualTo(String value) {
            addCriterion("key_and_title <=", value, "keyAndTitle");
            return (Criteria) this;
        }

        public Criteria andKeyAndTitleLike(String value) {
            addCriterion("key_and_title like", value, "keyAndTitle");
            return (Criteria) this;
        }

        public Criteria andKeyAndTitleNotLike(String value) {
            addCriterion("key_and_title not like", value, "keyAndTitle");
            return (Criteria) this;
        }

        public Criteria andKeyAndTitleIn(List<String> values) {
            addCriterion("key_and_title in", values, "keyAndTitle");
            return (Criteria) this;
        }

        public Criteria andKeyAndTitleNotIn(List<String> values) {
            addCriterion("key_and_title not in", values, "keyAndTitle");
            return (Criteria) this;
        }

        public Criteria andKeyAndTitleBetween(String value1, String value2) {
            addCriterion("key_and_title between", value1, value2, "keyAndTitle");
            return (Criteria) this;
        }

        public Criteria andKeyAndTitleNotBetween(String value1, String value2) {
            addCriterion("key_and_title not between", value1, value2, "keyAndTitle");
            return (Criteria) this;
        }

        public Criteria andKeyNotTitleIsNull() {
            addCriterion("key_not_title is null");
            return (Criteria) this;
        }

        public Criteria andKeyNotTitleIsNotNull() {
            addCriterion("key_not_title is not null");
            return (Criteria) this;
        }

        public Criteria andKeyNotTitleEqualTo(String value) {
            addCriterion("key_not_title =", value, "keyNotTitle");
            return (Criteria) this;
        }

        public Criteria andKeyNotTitleNotEqualTo(String value) {
            addCriterion("key_not_title <>", value, "keyNotTitle");
            return (Criteria) this;
        }

        public Criteria andKeyNotTitleGreaterThan(String value) {
            addCriterion("key_not_title >", value, "keyNotTitle");
            return (Criteria) this;
        }

        public Criteria andKeyNotTitleGreaterThanOrEqualTo(String value) {
            addCriterion("key_not_title >=", value, "keyNotTitle");
            return (Criteria) this;
        }

        public Criteria andKeyNotTitleLessThan(String value) {
            addCriterion("key_not_title <", value, "keyNotTitle");
            return (Criteria) this;
        }

        public Criteria andKeyNotTitleLessThanOrEqualTo(String value) {
            addCriterion("key_not_title <=", value, "keyNotTitle");
            return (Criteria) this;
        }

        public Criteria andKeyNotTitleLike(String value) {
            addCriterion("key_not_title like", value, "keyNotTitle");
            return (Criteria) this;
        }

        public Criteria andKeyNotTitleNotLike(String value) {
            addCriterion("key_not_title not like", value, "keyNotTitle");
            return (Criteria) this;
        }

        public Criteria andKeyNotTitleIn(List<String> values) {
            addCriterion("key_not_title in", values, "keyNotTitle");
            return (Criteria) this;
        }

        public Criteria andKeyNotTitleNotIn(List<String> values) {
            addCriterion("key_not_title not in", values, "keyNotTitle");
            return (Criteria) this;
        }

        public Criteria andKeyNotTitleBetween(String value1, String value2) {
            addCriterion("key_not_title between", value1, value2, "keyNotTitle");
            return (Criteria) this;
        }

        public Criteria andKeyNotTitleNotBetween(String value1, String value2) {
            addCriterion("key_not_title not between", value1, value2, "keyNotTitle");
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