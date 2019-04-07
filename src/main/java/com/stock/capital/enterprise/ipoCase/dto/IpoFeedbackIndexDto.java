package com.stock.capital.enterprise.ipoCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stock.core.dto.Indexable;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class IpoFeedbackIndexDto extends Indexable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("letter_question_label_content_t")
    private String questionLabelContent;

    @Field("letter_answer_label_content_t")
    private String answerLabelContent;

    @Field("letter_sortno_l")
    private Long sortNo;

    @Field("letter_question_id_t")
    private String questionId;

    @Field("letter_letter_type_txt")
    private List<String> letterType;

    @Field("letter_letter_type_name_t")
    private String letterTypeName;

    @Field("letter_question_class_id_txt")
    private List<String> questionClassId;

    @Field("letter_letter_id_t")
    private String letterId;

    @Field("letter_letter_name_t")
    private String letterName;

    @Field("letter_question_content_t")
    private String questContents;

    @Field("letter_answer_content_t")
    private String answersContents;

    @Field("letter_company_id_t")
    private String companyId;

    @Field("letter_company_name_t")
    private String companyName;

    @Field("letter_letter_org_t")
    private String letterOrg;

    @Field("letter_return_code_t")
    private String returnCode;

    @Field("letter_return_name_t")
    private String returnCodeName;

    @Field("letter_industry_no_txt")
    private List<String> industryNo;

    @Field("letter_industry_name_txt")
    private List<String> industryName;

    @Field("letter_belong_plate_t")
    private String belongPlateCode;

    @Field("letter_belong_palate_name_t")
    private String belongPlateName;

    @Field("letter_area_no_txt")
    private List<String> areaNos;

    @Field("letter_provinces_name_t")
    private String provincesName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Field("letter_letter_date_dt")
    private Date letterDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Field("letter_letter_return_date_dt")
    private Date letterReturnDate;

    //2018-02-27 需求3851 by wangyu start
    @Field("company_area_txt")
    private List<String> companyArea;
    //2018-02-27 需求3851 by wangyu end

    //需求4111 2018/4/10 by liuh Start
    //问题类型id和name
    private String questionTypeInfo;

    //2018/5/5 需求4194 by liuh Start
    //公司全称
    @Field("letter_company_zh_name_t")
    private String companyZhName;

    // 适用板块
    @Field("letter_letter_apply_module_txt")
    private List<String> letterApplyModule;

    // 函件新问题类型
    @Field("letter_question_class_new_id_txt")
    private List<String> questionClassNewId;

    // 并购重组问询主体类型
    @Field("letter_rela_body_id_txt")
    private List<String> relaBodyId;

    // 问询主体关联问题类型组合
    @Field("letter_rela_body_question_id_txt")
    private List<String> relaBodyQuestionId;

    // 函件新问题类型名称（分板块存）
    @Field("letter_question_class_name_txt")
    private List<String> questionClassName;

    // 公司东财代码
    @Field("letter_company_rela_code_t")
    private String companyRelaCode;

    public String getCompanyZhName() {
        return companyZhName;
    }

    public void setCompanyZhName(String companyZhName) {
        this.companyZhName = companyZhName;
    }
    //2018/5/5 需求4194 by liuh end

    public String getQuestionTypeInfo() {
        return questionTypeInfo;
    }

    public void setQuestionTypeInfo(String questionTypeInfo) {
        this.questionTypeInfo = questionTypeInfo;
    }
    //需求4111 2018/4/10 by liuh end

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<String> getQuestionClassId() {
        return questionClassId;
    }

    public void setQuestionClassId(List<String> questionClassId) {
        this.questionClassId = questionClassId;
    }

    public String getLetterId() {
        return letterId;
    }

    public void setLetterId(String letterId) {
        this.letterId = letterId;
    }

    public String getLetterName() {
        return letterName;
    }

    public void setLetterName(String letterName) {
        this.letterName = letterName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getLetterOrg() {
        return letterOrg;
    }

    public void setLetterOrg(String letterOrg) {
        this.letterOrg = letterOrg;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnCodeName() {
        return returnCodeName;
    }

    public void setReturnCodeName(String returnCodeName) {
        this.returnCodeName = returnCodeName;
    }

    public String getBelongPlateCode() {
        return belongPlateCode;
    }

    public void setBelongPlateCode(String belongPlateCode) {
        this.belongPlateCode = belongPlateCode;
    }

    public String getBelongPlateName() {
        return belongPlateName;
    }

    public void setBelongPlateName(String belongPlateName) {
        this.belongPlateName = belongPlateName;
    }

    public String getProvincesName() {
        return provincesName;
    }

    public void setProvincesName(String provincesName) {
        this.provincesName = provincesName;
    }

    public Date getLetterDate() {
        return letterDate;
    }

    public void setLetterDate(Date letterDate) {
        this.letterDate = letterDate;
    }

    public Date getLetterReturnDate() {
        return letterReturnDate;
    }

    public void setLetterReturnDate(Date letterReturnDate) {
        this.letterReturnDate = letterReturnDate;
    }

    public List<String> getIndustryNo() {
        return industryNo;
    }

    public void setIndustryNo(List<String> industryNo) {
        this.industryNo = industryNo;
    }

    public List<String> getIndustryName() {
        return industryName;
    }

    public void setIndustryName(List<String> industryName) {
        this.industryName = industryName;
    }

    public List<String> getAreaNos() {
        return areaNos;
    }

    public void setAreaNos(List<String> areaNos) {
        this.areaNos = areaNos;
    }

    public String getQuestContents() {
        return questContents;
    }

    public void setQuestContents(String questContents) {
        this.questContents = questContents;
    }

    public String getAnswersContents() {
        return answersContents;
    }

    public void setAnswersContents(String answersContents) {
        this.answersContents = answersContents;
    }

    public List<String> getLetterType() {
        return letterType;
    }

    public void setLetterType(List<String> letterType) {
        this.letterType = letterType;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public Long getSortNo() {
        return sortNo;
    }

    public void setSortNo(Long sortNo) {
        this.sortNo = sortNo;
    }

    public String getQuestionLabelContent() {
        return questionLabelContent;
    }

    public void setQuestionLabelContent(String questionLabelContent) {
        this.questionLabelContent = questionLabelContent;
    }

    public String getAnswerLabelContent() {
        return answerLabelContent;
    }

    public void setAnswerLabelContent(String answerLabelContent) {
        this.answerLabelContent = answerLabelContent;
    }

    public List<String> getCompanyArea() {
        return companyArea;
    }

    public void setCompanyArea(List<String> companyArea) {
        this.companyArea = companyArea;
    }

    public String getLetterTypeName() {
        return letterTypeName;
    }

    public void setLetterTypeName(String letterTypeName) {
        this.letterTypeName = letterTypeName;
    }

    public List<String> getLetterApplyModule() {
        return letterApplyModule;
    }

    public void setLetterApplyModule(List<String> letterApplyModule) {
        this.letterApplyModule = letterApplyModule;
    }

    public List<String> getQuestionClassNewId() {
        return questionClassNewId;
    }

    public void setQuestionClassNewId(List<String> questionClassNewId) {
        this.questionClassNewId = questionClassNewId;
    }

    public List<String> getRelaBodyId() {
        return relaBodyId;
    }

    public void setRelaBodyId(List<String> relaBodyId) {
        this.relaBodyId = relaBodyId;
    }

    public List<String> getRelaBodyQuestionId() {
        return relaBodyQuestionId;
    }

    public void setRelaBodyQuestionId(List<String> relaBodyQuestionId) {
        this.relaBodyQuestionId = relaBodyQuestionId;
    }

    public List<String> getQuestionClassName() {
        return questionClassName;
    }

    public void setQuestionClassName(List<String> questionClassName) {
        this.questionClassName = questionClassName;
    }

    public String getCompanyRelaCode() {
        return companyRelaCode;
    }

    public void setCompanyRelaCode(String companyRelaCode) {
        this.companyRelaCode = companyRelaCode;
    }
}
