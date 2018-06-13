package com.stock.capital.enterprise.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeclareFileExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeclareFileExample() {
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

        public Criteria andFileTitleIsNull() {
            addCriterion("file_title is null");
            return (Criteria) this;
        }

        public Criteria andFileTitleIsNotNull() {
            addCriterion("file_title is not null");
            return (Criteria) this;
        }

        public Criteria andFileTitleEqualTo(String value) {
            addCriterion("file_title =", value, "fileTitle");
            return (Criteria) this;
        }

        public Criteria andFileTitleNotEqualTo(String value) {
            addCriterion("file_title <>", value, "fileTitle");
            return (Criteria) this;
        }

        public Criteria andFileTitleGreaterThan(String value) {
            addCriterion("file_title >", value, "fileTitle");
            return (Criteria) this;
        }

        public Criteria andFileTitleGreaterThanOrEqualTo(String value) {
            addCriterion("file_title >=", value, "fileTitle");
            return (Criteria) this;
        }

        public Criteria andFileTitleLessThan(String value) {
            addCriterion("file_title <", value, "fileTitle");
            return (Criteria) this;
        }

        public Criteria andFileTitleLessThanOrEqualTo(String value) {
            addCriterion("file_title <=", value, "fileTitle");
            return (Criteria) this;
        }

        public Criteria andFileTitleLike(String value) {
            addCriterion("file_title like", value, "fileTitle");
            return (Criteria) this;
        }

        public Criteria andFileTitleNotLike(String value) {
            addCriterion("file_title not like", value, "fileTitle");
            return (Criteria) this;
        }

        public Criteria andFileTitleIn(List<String> values) {
            addCriterion("file_title in", values, "fileTitle");
            return (Criteria) this;
        }

        public Criteria andFileTitleNotIn(List<String> values) {
            addCriterion("file_title not in", values, "fileTitle");
            return (Criteria) this;
        }

        public Criteria andFileTitleBetween(String value1, String value2) {
            addCriterion("file_title between", value1, value2, "fileTitle");
            return (Criteria) this;
        }

        public Criteria andFileTitleNotBetween(String value1, String value2) {
            addCriterion("file_title not between", value1, value2, "fileTitle");
            return (Criteria) this;
        }

        public Criteria andExtensionIsNull() {
            addCriterion("extension is null");
            return (Criteria) this;
        }

        public Criteria andExtensionIsNotNull() {
            addCriterion("extension is not null");
            return (Criteria) this;
        }

        public Criteria andExtensionEqualTo(String value) {
            addCriterion("extension =", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionNotEqualTo(String value) {
            addCriterion("extension <>", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionGreaterThan(String value) {
            addCriterion("extension >", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionGreaterThanOrEqualTo(String value) {
            addCriterion("extension >=", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionLessThan(String value) {
            addCriterion("extension <", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionLessThanOrEqualTo(String value) {
            addCriterion("extension <=", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionLike(String value) {
            addCriterion("extension like", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionNotLike(String value) {
            addCriterion("extension not like", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionIn(List<String> values) {
            addCriterion("extension in", values, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionNotIn(List<String> values) {
            addCriterion("extension not in", values, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionBetween(String value1, String value2) {
            addCriterion("extension between", value1, value2, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionNotBetween(String value1, String value2) {
            addCriterion("extension not between", value1, value2, "extension");
            return (Criteria) this;
        }

        public Criteria andNewspaperIsNull() {
            addCriterion("newspaper is null");
            return (Criteria) this;
        }

        public Criteria andNewspaperIsNotNull() {
            addCriterion("newspaper is not null");
            return (Criteria) this;
        }

        public Criteria andNewspaperEqualTo(String value) {
            addCriterion("newspaper =", value, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperNotEqualTo(String value) {
            addCriterion("newspaper <>", value, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperGreaterThan(String value) {
            addCriterion("newspaper >", value, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperGreaterThanOrEqualTo(String value) {
            addCriterion("newspaper >=", value, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperLessThan(String value) {
            addCriterion("newspaper <", value, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperLessThanOrEqualTo(String value) {
            addCriterion("newspaper <=", value, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperLike(String value) {
            addCriterion("newspaper like", value, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperNotLike(String value) {
            addCriterion("newspaper not like", value, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperIn(List<String> values) {
            addCriterion("newspaper in", values, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperNotIn(List<String> values) {
            addCriterion("newspaper not in", values, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperBetween(String value1, String value2) {
            addCriterion("newspaper between", value1, value2, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperNotBetween(String value1, String value2) {
            addCriterion("newspaper not between", value1, value2, "newspaper");
            return (Criteria) this;
        }

        public Criteria andInternetIsNull() {
            addCriterion("internet is null");
            return (Criteria) this;
        }

        public Criteria andInternetIsNotNull() {
            addCriterion("internet is not null");
            return (Criteria) this;
        }

        public Criteria andInternetEqualTo(String value) {
            addCriterion("internet =", value, "internet");
            return (Criteria) this;
        }

        public Criteria andInternetNotEqualTo(String value) {
            addCriterion("internet <>", value, "internet");
            return (Criteria) this;
        }

        public Criteria andInternetGreaterThan(String value) {
            addCriterion("internet >", value, "internet");
            return (Criteria) this;
        }

        public Criteria andInternetGreaterThanOrEqualTo(String value) {
            addCriterion("internet >=", value, "internet");
            return (Criteria) this;
        }

        public Criteria andInternetLessThan(String value) {
            addCriterion("internet <", value, "internet");
            return (Criteria) this;
        }

        public Criteria andInternetLessThanOrEqualTo(String value) {
            addCriterion("internet <=", value, "internet");
            return (Criteria) this;
        }

        public Criteria andInternetLike(String value) {
            addCriterion("internet like", value, "internet");
            return (Criteria) this;
        }

        public Criteria andInternetNotLike(String value) {
            addCriterion("internet not like", value, "internet");
            return (Criteria) this;
        }

        public Criteria andInternetIn(List<String> values) {
            addCriterion("internet in", values, "internet");
            return (Criteria) this;
        }

        public Criteria andInternetNotIn(List<String> values) {
            addCriterion("internet not in", values, "internet");
            return (Criteria) this;
        }

        public Criteria andInternetBetween(String value1, String value2) {
            addCriterion("internet between", value1, value2, "internet");
            return (Criteria) this;
        }

        public Criteria andInternetNotBetween(String value1, String value2) {
            addCriterion("internet not between", value1, value2, "internet");
            return (Criteria) this;
        }

        public Criteria andFilingIsNull() {
            addCriterion("filing is null");
            return (Criteria) this;
        }

        public Criteria andFilingIsNotNull() {
            addCriterion("filing is not null");
            return (Criteria) this;
        }

        public Criteria andFilingEqualTo(String value) {
            addCriterion("filing =", value, "filing");
            return (Criteria) this;
        }

        public Criteria andFilingNotEqualTo(String value) {
            addCriterion("filing <>", value, "filing");
            return (Criteria) this;
        }

        public Criteria andFilingGreaterThan(String value) {
            addCriterion("filing >", value, "filing");
            return (Criteria) this;
        }

        public Criteria andFilingGreaterThanOrEqualTo(String value) {
            addCriterion("filing >=", value, "filing");
            return (Criteria) this;
        }

        public Criteria andFilingLessThan(String value) {
            addCriterion("filing <", value, "filing");
            return (Criteria) this;
        }

        public Criteria andFilingLessThanOrEqualTo(String value) {
            addCriterion("filing <=", value, "filing");
            return (Criteria) this;
        }

        public Criteria andFilingLike(String value) {
            addCriterion("filing like", value, "filing");
            return (Criteria) this;
        }

        public Criteria andFilingNotLike(String value) {
            addCriterion("filing not like", value, "filing");
            return (Criteria) this;
        }

        public Criteria andFilingIn(List<String> values) {
            addCriterion("filing in", values, "filing");
            return (Criteria) this;
        }

        public Criteria andFilingNotIn(List<String> values) {
            addCriterion("filing not in", values, "filing");
            return (Criteria) this;
        }

        public Criteria andFilingBetween(String value1, String value2) {
            addCriterion("filing between", value1, value2, "filing");
            return (Criteria) this;
        }

        public Criteria andFilingNotBetween(String value1, String value2) {
            addCriterion("filing not between", value1, value2, "filing");
            return (Criteria) this;
        }

        public Criteria andSharesBIsNull() {
            addCriterion("shares_b is null");
            return (Criteria) this;
        }

        public Criteria andSharesBIsNotNull() {
            addCriterion("shares_b is not null");
            return (Criteria) this;
        }

        public Criteria andSharesBEqualTo(String value) {
            addCriterion("shares_b =", value, "sharesB");
            return (Criteria) this;
        }

        public Criteria andSharesBNotEqualTo(String value) {
            addCriterion("shares_b <>", value, "sharesB");
            return (Criteria) this;
        }

        public Criteria andSharesBGreaterThan(String value) {
            addCriterion("shares_b >", value, "sharesB");
            return (Criteria) this;
        }

        public Criteria andSharesBGreaterThanOrEqualTo(String value) {
            addCriterion("shares_b >=", value, "sharesB");
            return (Criteria) this;
        }

        public Criteria andSharesBLessThan(String value) {
            addCriterion("shares_b <", value, "sharesB");
            return (Criteria) this;
        }

        public Criteria andSharesBLessThanOrEqualTo(String value) {
            addCriterion("shares_b <=", value, "sharesB");
            return (Criteria) this;
        }

        public Criteria andSharesBLike(String value) {
            addCriterion("shares_b like", value, "sharesB");
            return (Criteria) this;
        }

        public Criteria andSharesBNotLike(String value) {
            addCriterion("shares_b not like", value, "sharesB");
            return (Criteria) this;
        }

        public Criteria andSharesBIn(List<String> values) {
            addCriterion("shares_b in", values, "sharesB");
            return (Criteria) this;
        }

        public Criteria andSharesBNotIn(List<String> values) {
            addCriterion("shares_b not in", values, "sharesB");
            return (Criteria) this;
        }

        public Criteria andSharesBBetween(String value1, String value2) {
            addCriterion("shares_b between", value1, value2, "sharesB");
            return (Criteria) this;
        }

        public Criteria andSharesBNotBetween(String value1, String value2) {
            addCriterion("shares_b not between", value1, value2, "sharesB");
            return (Criteria) this;
        }

        public Criteria andMastIsNull() {
            addCriterion("mast is null");
            return (Criteria) this;
        }

        public Criteria andMastIsNotNull() {
            addCriterion("mast is not null");
            return (Criteria) this;
        }

        public Criteria andMastEqualTo(String value) {
            addCriterion("mast =", value, "mast");
            return (Criteria) this;
        }

        public Criteria andMastNotEqualTo(String value) {
            addCriterion("mast <>", value, "mast");
            return (Criteria) this;
        }

        public Criteria andMastGreaterThan(String value) {
            addCriterion("mast >", value, "mast");
            return (Criteria) this;
        }

        public Criteria andMastGreaterThanOrEqualTo(String value) {
            addCriterion("mast >=", value, "mast");
            return (Criteria) this;
        }

        public Criteria andMastLessThan(String value) {
            addCriterion("mast <", value, "mast");
            return (Criteria) this;
        }

        public Criteria andMastLessThanOrEqualTo(String value) {
            addCriterion("mast <=", value, "mast");
            return (Criteria) this;
        }

        public Criteria andMastLike(String value) {
            addCriterion("mast like", value, "mast");
            return (Criteria) this;
        }

        public Criteria andMastNotLike(String value) {
            addCriterion("mast not like", value, "mast");
            return (Criteria) this;
        }

        public Criteria andMastIn(List<String> values) {
            addCriterion("mast in", values, "mast");
            return (Criteria) this;
        }

        public Criteria andMastNotIn(List<String> values) {
            addCriterion("mast not in", values, "mast");
            return (Criteria) this;
        }

        public Criteria andMastBetween(String value1, String value2) {
            addCriterion("mast between", value1, value2, "mast");
            return (Criteria) this;
        }

        public Criteria andMastNotBetween(String value1, String value2) {
            addCriterion("mast not between", value1, value2, "mast");
            return (Criteria) this;
        }

        public Criteria andFileEncodeIsNull() {
            addCriterion("file_encode is null");
            return (Criteria) this;
        }

        public Criteria andFileEncodeIsNotNull() {
            addCriterion("file_encode is not null");
            return (Criteria) this;
        }

        public Criteria andFileEncodeEqualTo(String value) {
            addCriterion("file_encode =", value, "fileEncode");
            return (Criteria) this;
        }

        public Criteria andFileEncodeNotEqualTo(String value) {
            addCriterion("file_encode <>", value, "fileEncode");
            return (Criteria) this;
        }

        public Criteria andFileEncodeGreaterThan(String value) {
            addCriterion("file_encode >", value, "fileEncode");
            return (Criteria) this;
        }

        public Criteria andFileEncodeGreaterThanOrEqualTo(String value) {
            addCriterion("file_encode >=", value, "fileEncode");
            return (Criteria) this;
        }

        public Criteria andFileEncodeLessThan(String value) {
            addCriterion("file_encode <", value, "fileEncode");
            return (Criteria) this;
        }

        public Criteria andFileEncodeLessThanOrEqualTo(String value) {
            addCriterion("file_encode <=", value, "fileEncode");
            return (Criteria) this;
        }

        public Criteria andFileEncodeLike(String value) {
            addCriterion("file_encode like", value, "fileEncode");
            return (Criteria) this;
        }

        public Criteria andFileEncodeNotLike(String value) {
            addCriterion("file_encode not like", value, "fileEncode");
            return (Criteria) this;
        }

        public Criteria andFileEncodeIn(List<String> values) {
            addCriterion("file_encode in", values, "fileEncode");
            return (Criteria) this;
        }

        public Criteria andFileEncodeNotIn(List<String> values) {
            addCriterion("file_encode not in", values, "fileEncode");
            return (Criteria) this;
        }

        public Criteria andFileEncodeBetween(String value1, String value2) {
            addCriterion("file_encode between", value1, value2, "fileEncode");
            return (Criteria) this;
        }

        public Criteria andFileEncodeNotBetween(String value1, String value2) {
            addCriterion("file_encode not between", value1, value2, "fileEncode");
            return (Criteria) this;
        }

        public Criteria andFileCodeIsNull() {
            addCriterion("file_code is null");
            return (Criteria) this;
        }

        public Criteria andFileCodeIsNotNull() {
            addCriterion("file_code is not null");
            return (Criteria) this;
        }

        public Criteria andFileCodeEqualTo(String value) {
            addCriterion("file_code =", value, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeNotEqualTo(String value) {
            addCriterion("file_code <>", value, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeGreaterThan(String value) {
            addCriterion("file_code >", value, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeGreaterThanOrEqualTo(String value) {
            addCriterion("file_code >=", value, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeLessThan(String value) {
            addCriterion("file_code <", value, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeLessThanOrEqualTo(String value) {
            addCriterion("file_code <=", value, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeLike(String value) {
            addCriterion("file_code like", value, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeNotLike(String value) {
            addCriterion("file_code not like", value, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeIn(List<String> values) {
            addCriterion("file_code in", values, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeNotIn(List<String> values) {
            addCriterion("file_code not in", values, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeBetween(String value1, String value2) {
            addCriterion("file_code between", value1, value2, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeNotBetween(String value1, String value2) {
            addCriterion("file_code not between", value1, value2, "fileCode");
            return (Criteria) this;
        }

        public Criteria andDisabledFlagIsNull() {
            addCriterion("disabled_flag is null");
            return (Criteria) this;
        }

        public Criteria andDisabledFlagIsNotNull() {
            addCriterion("disabled_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDisabledFlagEqualTo(String value) {
            addCriterion("disabled_flag =", value, "disabledFlag");
            return (Criteria) this;
        }

        public Criteria andDisabledFlagNotEqualTo(String value) {
            addCriterion("disabled_flag <>", value, "disabledFlag");
            return (Criteria) this;
        }

        public Criteria andDisabledFlagGreaterThan(String value) {
            addCriterion("disabled_flag >", value, "disabledFlag");
            return (Criteria) this;
        }

        public Criteria andDisabledFlagGreaterThanOrEqualTo(String value) {
            addCriterion("disabled_flag >=", value, "disabledFlag");
            return (Criteria) this;
        }

        public Criteria andDisabledFlagLessThan(String value) {
            addCriterion("disabled_flag <", value, "disabledFlag");
            return (Criteria) this;
        }

        public Criteria andDisabledFlagLessThanOrEqualTo(String value) {
            addCriterion("disabled_flag <=", value, "disabledFlag");
            return (Criteria) this;
        }

        public Criteria andDisabledFlagLike(String value) {
            addCriterion("disabled_flag like", value, "disabledFlag");
            return (Criteria) this;
        }

        public Criteria andDisabledFlagNotLike(String value) {
            addCriterion("disabled_flag not like", value, "disabledFlag");
            return (Criteria) this;
        }

        public Criteria andDisabledFlagIn(List<String> values) {
            addCriterion("disabled_flag in", values, "disabledFlag");
            return (Criteria) this;
        }

        public Criteria andDisabledFlagNotIn(List<String> values) {
            addCriterion("disabled_flag not in", values, "disabledFlag");
            return (Criteria) this;
        }

        public Criteria andDisabledFlagBetween(String value1, String value2) {
            addCriterion("disabled_flag between", value1, value2, "disabledFlag");
            return (Criteria) this;
        }

        public Criteria andDisabledFlagNotBetween(String value1, String value2) {
            addCriterion("disabled_flag not between", value1, value2, "disabledFlag");
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

        public Criteria andDeclareNoIsNull() {
            addCriterion("declare_no is null");
            return (Criteria) this;
        }

        public Criteria andDeclareNoIsNotNull() {
            addCriterion("declare_no is not null");
            return (Criteria) this;
        }

        public Criteria andDeclareNoEqualTo(String value) {
            addCriterion("declare_no =", value, "declareNo");
            return (Criteria) this;
        }

        public Criteria andDeclareNoNotEqualTo(String value) {
            addCriterion("declare_no <>", value, "declareNo");
            return (Criteria) this;
        }

        public Criteria andDeclareNoGreaterThan(String value) {
            addCriterion("declare_no >", value, "declareNo");
            return (Criteria) this;
        }

        public Criteria andDeclareNoGreaterThanOrEqualTo(String value) {
            addCriterion("declare_no >=", value, "declareNo");
            return (Criteria) this;
        }

        public Criteria andDeclareNoLessThan(String value) {
            addCriterion("declare_no <", value, "declareNo");
            return (Criteria) this;
        }

        public Criteria andDeclareNoLessThanOrEqualTo(String value) {
            addCriterion("declare_no <=", value, "declareNo");
            return (Criteria) this;
        }

        public Criteria andDeclareNoLike(String value) {
            addCriterion("declare_no like", value, "declareNo");
            return (Criteria) this;
        }

        public Criteria andDeclareNoNotLike(String value) {
            addCriterion("declare_no not like", value, "declareNo");
            return (Criteria) this;
        }

        public Criteria andDeclareNoIn(List<String> values) {
            addCriterion("declare_no in", values, "declareNo");
            return (Criteria) this;
        }

        public Criteria andDeclareNoNotIn(List<String> values) {
            addCriterion("declare_no not in", values, "declareNo");
            return (Criteria) this;
        }

        public Criteria andDeclareNoBetween(String value1, String value2) {
            addCriterion("declare_no between", value1, value2, "declareNo");
            return (Criteria) this;
        }

        public Criteria andDeclareNoNotBetween(String value1, String value2) {
            addCriterion("declare_no not between", value1, value2, "declareNo");
            return (Criteria) this;
        }

        public Criteria andMeetingFileIdIsNull() {
            addCriterion("meeting_file_id is null");
            return (Criteria) this;
        }

        public Criteria andMeetingFileIdIsNotNull() {
            addCriterion("meeting_file_id is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingFileIdEqualTo(String value) {
            addCriterion("meeting_file_id =", value, "meetingFileId");
            return (Criteria) this;
        }

        public Criteria andMeetingFileIdNotEqualTo(String value) {
            addCriterion("meeting_file_id <>", value, "meetingFileId");
            return (Criteria) this;
        }

        public Criteria andMeetingFileIdGreaterThan(String value) {
            addCriterion("meeting_file_id >", value, "meetingFileId");
            return (Criteria) this;
        }

        public Criteria andMeetingFileIdGreaterThanOrEqualTo(String value) {
            addCriterion("meeting_file_id >=", value, "meetingFileId");
            return (Criteria) this;
        }

        public Criteria andMeetingFileIdLessThan(String value) {
            addCriterion("meeting_file_id <", value, "meetingFileId");
            return (Criteria) this;
        }

        public Criteria andMeetingFileIdLessThanOrEqualTo(String value) {
            addCriterion("meeting_file_id <=", value, "meetingFileId");
            return (Criteria) this;
        }

        public Criteria andMeetingFileIdLike(String value) {
            addCriterion("meeting_file_id like", value, "meetingFileId");
            return (Criteria) this;
        }

        public Criteria andMeetingFileIdNotLike(String value) {
            addCriterion("meeting_file_id not like", value, "meetingFileId");
            return (Criteria) this;
        }

        public Criteria andMeetingFileIdIn(List<String> values) {
            addCriterion("meeting_file_id in", values, "meetingFileId");
            return (Criteria) this;
        }

        public Criteria andMeetingFileIdNotIn(List<String> values) {
            addCriterion("meeting_file_id not in", values, "meetingFileId");
            return (Criteria) this;
        }

        public Criteria andMeetingFileIdBetween(String value1, String value2) {
            addCriterion("meeting_file_id between", value1, value2, "meetingFileId");
            return (Criteria) this;
        }

        public Criteria andMeetingFileIdNotBetween(String value1, String value2) {
            addCriterion("meeting_file_id not between", value1, value2, "meetingFileId");
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