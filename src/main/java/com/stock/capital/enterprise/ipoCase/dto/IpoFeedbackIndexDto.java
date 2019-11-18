package com.stock.capital.enterprise.ipoCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stock.core.annotation.IndexField;
import com.stock.core.dto.Indexable;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class IpoFeedbackIndexDto extends Indexable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("letter_question_label_content_t")
    @IndexField(name="letter_question_label_content_t", description="函件问题标签内容")
    private String questionLabelContent;

    @Field("letter_answer_label_content_t")
    @IndexField(name="letter_answer_label_content_t", description="函件回答标签内容")
    private String answerLabelContent;

    @Field("letter_sortno_l")
    @IndexField(name = "letter_sortno_l", description = "排序")
    private Long sortNo;

    @Field("letter_question_id_t")
    @IndexField(name = "letter_question_id_t", description = "回答id")
    private String questionId;

    @Field("letter_letter_type_txt")
    @IndexField(name = "letter_letter_type_txt", description = "函件类别")
    private List<String> letterType;

    @Field("letter_letter_type_name_t")
    @IndexField(name = "letter_letter_type_name_t", description = "函件类别名称")
    private String letterTypeName;

    @Field("letter_question_class_id_txt")
    @IndexField(name = "letter_question_class_id_txt", description = "")
    private List<String> questionClassId;

    @Field("letter_letter_id_t")
    @IndexField(name = "letter_letter_id_t", description = "函件id")
    private String letterId;

    @Field("letter_letter_name_t")
    @IndexField(name = "letter_letter_name_t", description = "函件名称")
    private String letterName;

    @Field("letter_question_content_t")
    @IndexField(name = "letter_question_content_t", description = "问题内容")
    private String questContents;

    @Field("letter_answer_content_t")
    @IndexField(name = "letter_answer_content_t", description = "回答内容")
    private String answersContents;

    @Field("letter_company_id_t")
    @IndexField(name = "letter_company_id_t", description = "公司id")
    private String companyId;

    @Field("letter_company_name_t")
    @IndexField(name = "letter_company_name_t", description = "公司名称")
    private String companyName;

    @Field("letter_letter_org_t")
    @IndexField(name = "letter_letter_org_t", description = "发函单位")
    private String letterOrg;

    @Field("letter_return_code_t")
    @IndexField(name = "letter_return_code_t", description = "")
    private String returnCode;

    @Field("letter_return_name_t")
    @IndexField(name = "letter_return_name_t", description = "")
    private String returnCodeName;

    @Field("letter_industry_no_txt")
    @IndexField(name = "letter_industry_no_txt", description = "行业")
    private List<String> industryNo;

    @Field("letter_industry_name_txt")
    @IndexField(name = "letter_industry_name_txt", description = "行业名称")
    private List<String> industryName;

    @Field("letter_belong_plate_t")
    @IndexField(name = "letter_belong_plate_t", description = "板块")
    private String belongPlateCode;

    @Field("letter_belong_palate_name_t")
    @IndexField(name="letter_belong_palate_name_t", description="所属板块汉字名称")
    private String belongPlateName;

    @Field("letter_area_no_txt")
    @IndexField(name="letter_area_no_txt", description="公司所属地区")
    private List<String> areaNos;

    @Field("letter_provinces_name_t")
    @IndexField(name="letter_provinces_name_t", description="公司所属地区汉字名称")
    private String provincesName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Field("letter_letter_date_dt")
    @IndexField(name="letter_letter_date_dt", description="发函日期")
    private Date letterDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Field("letter_letter_return_date_dt")
    @IndexField(name="letter_letter_return_date_dt", description="回函日期")
    private Date letterReturnDate;

    //2018-02-27 需求3851 by wangyu start
    @Field("company_area_txt")
    @IndexField(name="company_area_txt", description="公司所在地区")
    private List<String> companyArea;
    //2018-02-27 需求3851 by wangyu end

    //需求4111 2018/4/10 by liuh Start
    //问题类型id和name
    private String questionTypeInfo;

    //2018/5/5 需求4194 by liuh Start
    //公司全称
    @Field("letter_company_zh_name_t")
    @IndexField(name="letter_company_zh_name_t", description="函件公司全称")
    private String companyZhName;

    // 适用板块
    @Field("letter_letter_apply_module_txt")
    @IndexField(name="letter_letter_apply_module_txt", description="适用板块")
    private List<String> letterApplyModule;

    // 函件新问题类型
    @Field("letter_question_class_new_id_txt")
    @IndexField(name="letter_question_class_new_id_txt", description="函件新问题类型")
    private List<String> questionClassNewId;

    // 并购重组问询主体类型
    @Field("letter_rela_body_id_txt")
    @IndexField(name="letter_rela_body_id_txt", description="并购重组问询主体类型")
    private List<String> relaBodyId;

    // 问询主体关联问题类型组合
    @Field("letter_rela_body_question_id_txt")
    @IndexField(name="letter_rela_body_question_id_txt", description="问询主体关联问题类型组合")
    private List<String> relaBodyQuestionId;

    // 函件新问题类型名称（分板块存）
    @Field("letter_question_class_name_txt")
    @IndexField(name="letter_question_class_name_txt", description="函件新问题类型名称")
    private List<String> questionClassName;

    // 公司东财代码
    @Field("letter_company_rela_code_t")
    @IndexField(name="letter_company_rela_code_t", description="函件公司东财代码")
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
