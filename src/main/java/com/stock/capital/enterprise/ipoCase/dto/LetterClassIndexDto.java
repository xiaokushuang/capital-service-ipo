package com.stock.capital.enterprise.ipoCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stock.capital.enterprise.common.constant.Global;
import com.stock.core.annotation.FieldAnnotation;
import com.stock.core.annotation.IndexField;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@FieldAnnotation(name = "监管函件", value = Global.INDEX_MODULE_LETTER)
public class LetterClassIndexDto extends PageOperationDto implements Serializable {

    private static final long serialVersionUID = -4487297977174664577L;

    @ApiModelProperty(value = "返回前端判断是否收藏", dataType = "String")
    private String favourFlag;
    
    @ApiModelProperty(value = "返回法规统计数量", dataType = "String")
    private String lawCount;

    @ApiModelProperty(value = "函件类别名称", dataType = "String")
    @FieldAnnotation(name = "函件类别名称", value = "letter_letter_type_name_t")
    //@Field("letter_letter_type_name_t")
    @IndexField(name="letter_letter_type_name_t", description="函件类别名称")
    private String letterTypeName;

    @ApiModelProperty(value = "函件类别列表", dataType = "List<String>")
    @FieldAnnotation(name = "函件类别列表", value = "letter_letter_type_txt")
    //@Field("letter_letter_type_txt")
    @IndexField(name="letter_letter_type_txt", description="函件类别列表")
    private List<String> letterTypeId;

    @ApiModelProperty(value = "函件类别", dataType = "String")
    @FieldAnnotation(name = "函件类别", value = "letter_letter_type_t")
    //@Field("letter_letter_type_t")
    @IndexField(name="letter_letter_type_t", description="函件类别")
    private String letterType;

    @ApiModelProperty(value = "函件问题类型", dataType = "List<String>")
    @FieldAnnotation(name = "函件问题类型", value = "letter_question_class_id_txt")
    //@Field("letter_question_class_id_txt")
    @IndexField(name="letter_question_class_id_txt", description="函件问题类型")
    private List<String> questionClassId;

    @ApiModelProperty(value = "函件主键", dataType = "String")
    @FieldAnnotation(name = "函件主键", value = "letter_letter_id_t")
    //@Field("letter_letter_id_t")
    @IndexField(name="letter_letter_id_t", description="函件主键")
    private String letterId;

    @ApiModelProperty(value = "函件名称", dataType = "String")
    @FieldAnnotation(name = "函件名称", value = "letter_letter_name_t")
    //@Field("letter_letter_name_t")
    @IndexField(name="letter_letter_name_t", description="函件名称")
    private String letterName;

    @ApiModelProperty(value = "函件问题内容", dataType = "String")
    @FieldAnnotation(name = "函件问题内容", value = "letter_question_content_t")
    //@Field("letter_question_content_t")
    @IndexField(name="letter_question_content_t", description="函件问题内容")
    private String questContents;

    @ApiModelProperty(value = "函件回答内容", dataType = "String")
    @FieldAnnotation(name = "函件回答内容", value = "letter_answer_content_t")
    //@Field("letter_answer_content_t")
    @IndexField(name="letter_answer_content_t", description="函件回答内容")
    private String answersContents;

    @ApiModelProperty(value = "函件公司编码", dataType = "String")
    @FieldAnnotation(name = "函件公司编码", value = "letter_company_id_t")
    //@Field("letter_company_id_t")
    @IndexField(name="letter_company_id_t", description="函件公司编码")
    private String companyId;

    @ApiModelProperty(value = "函件公司名称", dataType = "String")
    @FieldAnnotation(name = "函件公司名称", value = "letter_company_name_t")
    //@Field("letter_company_name_t")
    @IndexField(name="letter_company_name_t", description="函件公司名称")
    private String companyName;

    @ApiModelProperty(value = "函件发函单位", dataType = "String")
    @FieldAnnotation(name = "函件发函单位", value = "letter_letter_org_t")
    //@Field("letter_letter_org_t")
    @IndexField(name="letter_letter_org_t", description="函件发函单位")
    private String letterOrg;

    @ApiModelProperty(value = "函件回函标志", dataType = "String")
    @FieldAnnotation(name = "函件回函标志", value = "letter_return_code_t")
    //@Field("letter_return_code_t")
    @IndexField(name="letter_return_code_t", description="函件回函标志")
    private String returnCode;

    @ApiModelProperty(value = "函件回函标志汉字名称", dataType = "String")
    @FieldAnnotation(name = "函件回函标志汉字名称", value = "letter_return_name_t")
    //@Field("letter_return_name_t")
    @IndexField(name="letter_return_name_t", description="函件回函标志汉字名称")
    private String returnCodeName;

    @ApiModelProperty(value = "所属行业", dataType = "List<String>")
    @FieldAnnotation(name = "所属行业", value = "letter_industry_no_txt")
    //@Field("letter_industry_no_txt")
    @IndexField(name="letter_industry_no_txt", description="所属行业")
    private List<String> industryNo;

    @ApiModelProperty(value = "所属行业汉字名称", dataType = "List<String>")
    @FieldAnnotation(name = "所属行业汉字名称", value = "letter_industry_name_txt")
    //@Field("letter_industry_name_txt")
    @IndexField(name="letter_industry_name_txt", description="所属行业汉字名称")
    private List<String> industryName;

    @ApiModelProperty(value = "所属板块", dataType = "String")
    @FieldAnnotation(name = "所属板块", value = "letter_belong_plate_t")
    //@Field("letter_belong_plate_t")
    @IndexField(name="letter_belong_plate_t", description="所属板块")
    private String belongPlateCode;

    @ApiModelProperty(value = "所属板块汉字名称", dataType = "String")
    @FieldAnnotation(name = "所属板块汉字名称", value = "letter_belong_palate_name_t")
    //@Field("letter_belong_palate_name_t")
    @IndexField(name="letter_belong_palate_name_t", description="所属板块汉字名称")
    private String belongPlateName;

    @ApiModelProperty(value = "公司所属地区", dataType = "List<String>")
    @FieldAnnotation(name = "公司所属地区", value = "letter_area_no_txt")
    //@Field("letter_area_no_txt")
    @IndexField(name="letter_area_no_txt", description="公司所属地区")
    private List<String> areaNos;

    @ApiModelProperty(value = "公司所属地区汉字名称", dataType = "String")
    @FieldAnnotation(name = "公司所属地区汉字名称", value = "letter_provinces_name_t")
    //@Field("letter_provinces_name_t")
    @IndexField(name="letter_provinces_name_t", description="公司所属地区汉字名称")
    private String provincesName;

    @ApiModelProperty(value = "发函日期", dataType = "ListDate")
    @FieldAnnotation(name = "发函日期", value = "letter_letter_date_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //@Field("letter_letter_date_dt")
    @IndexField(name="letter_letter_date_dt", description="发函日期")
    private Date letterDate;

    @ApiModelProperty(value = "回函日期", dataType = "Date")
    @FieldAnnotation(name = "回函日期", value = "letter_letter_return_date_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //@Field("letter_letter_return_date_dt")
    @IndexField(name="letter_letter_return_date_dt", description="回函日期")
    private Date letterReturnDate;

    @ApiModelProperty(value = "函件公司全称", dataType = "String")
    @FieldAnnotation(name = "函件公司全称", value = "letter_company_zh_name_t")
    //@Field("letter_company_zh_name_t")
    @IndexField(name="letter_company_zh_name_t", description="函件公司全称")
    private String companyFullName;

    @ApiModelProperty(value = "公司所在地区", dataType = "List<String>")
    //@Field("company_area_txt")
    @IndexField(name="company_area_txt", description="公司所在地区")
    private List<String> companyArea;

    @ApiModelProperty(value = "问ID", dataType = "String")
    //@Field("letter_question_id_t")
    @IndexField(name="letter_question_id_t", description="问ID")
    private String questionId;

    @ApiModelProperty(value = "发函日期String类型", dataType = "String")
    private String letterDateStr;

    @ApiModelProperty(value = "回函日期String类型", dataType = "String")
    private String letterReturnDateStr;

    @ApiModelProperty(value = "函件公司全称", dataType = "String")
    private String companyZhName;

    @ApiModelProperty(value = "问名称", dataType = "String")
    private String questionName;

    @ApiModelProperty(value = "函件公司东财代码", dataType = "String")
    @FieldAnnotation(name = "函件公司东财代码", value = "letter_company_rela_code_t")
    //@Field("letter_company_rela_code_t")
    @IndexField(name="letter_company_rela_code_t", description="函件公司东财代码")
    private String companyRelaCode;
    
    @ApiModelProperty(value = "适用板块", dataType = "List<String>")
    @FieldAnnotation(name = "适用板块", value = "letter_letter_apply_module_txt")
    //@Field("letter_letter_apply_module_txt")
    @IndexField(name="letter_letter_apply_module_txt", description="适用板块")
    private List<String> letterApplyModule;
    
    @ApiModelProperty(value = "函件新问题类型", dataType = "List<String>")
    @FieldAnnotation(name = "函件新问题类型", value = "letter_question_class_new_id_txt")
    //@Field("letter_question_class_new_id_txt")
    @IndexField(name="letter_question_class_new_id_txt", description="函件新问题类型")
    private List<String> questionClassNewId;
    
    @ApiModelProperty(value = "并购重组问询主体类型", dataType = "List<String>")
    @FieldAnnotation(name = "并购重组问询主体类型", value = "letter_rela_body_id_txt")
    //@Field("letter_rela_body_id_txt")
    @IndexField(name="letter_rela_body_id_txt", description="并购重组问询主体类型")
    private List<String> relaBodyId;
    
    @ApiModelProperty(value = "问询主体关联问题类型组合", dataType = "List<String>")
    @FieldAnnotation(name = "问询主体关联问题类型组合", value = "letter_rela_body_question_id_txt")
    //@Field("letter_rela_body_question_id_txt")
    @IndexField(name="letter_rela_body_question_id_txt", description="问询主体关联问题类型组合")
    private List<String> relaBodyQuestionId;

    @ApiModelProperty(value = "函件新问题类型名称", dataType = "List<String>")
    @FieldAnnotation(name = "函件新问题类型名称", value = "letter_question_class_name_txt")
    //@Field("letter_question_class_name_txt")
    @IndexField(name="letter_question_class_name_txt", description="函件新问题类型名称")
    private List<String> questionClassName;

    @ApiModelProperty(value = "函件问题标签内容", dataType = "String")
    @FieldAnnotation(name = "函件问题标签内容", value = "letter_question_label_content_t")
    //@Field("letter_question_label_content_t")
    @IndexField(name="letter_question_label_content_t", description="函件问题标签内容")
    private String questionLabelContent;

    @ApiModelProperty(value = "函件回答标签内容", dataType = "String")
    @FieldAnnotation(name = "函件回答标签内容", value = "letter_answer_label_content_t")
    //@Field("letter_answer_label_content_t")
    @IndexField(name="letter_answer_label_content_t", description="函件回答标签内容")
    private String answerLabelContent;

    @ApiModelProperty(value = "函件文号", dataType = "String")
    @FieldAnnotation(name = "函件文号", value = "letter_file_no_t")
    //@Field("letter_file_no_t")
    @IndexField(name="letter_file_no_t", description="函件文号")
    private String letterFileNo;

    @ApiModelProperty(value = "IPO案例通过结果", dataType = "String")
    @FieldAnnotation(name = "IPO案例通过结果", value = "letter_ipo_case_status_t")
    //@Field("letter_ipo_case_status_t")
    @IndexField(name="letter_ipo_case_status_t", description="IPO案例通过结果")
    private String ipoCaseStatus;
    
    @ApiModelProperty(value = "问题类型精读", dataType = "List<String>")
    @FieldAnnotation(name = "问题类型精读", value = "letter_question_type_count_txt")
    //@Field("letter_question_type_count_txt")
    @IndexField(name="letter_question_type_count_txt", description="问题类型精读")
    private List<String> questionTypeCount;
    
    @ApiModelProperty(value = "关联法规", dataType = "List<String>")
    @FieldAnnotation(name = "关联法规", value = "letter_laws_id_txt")
    //@Field("letter_laws_id_txt")
    @IndexField(name="letter_laws_id_txt", description="关联法规")
    private List<String> lawsId;
    
    @ApiModelProperty(value = "关联法规精读", dataType = "List<String>")
    @FieldAnnotation(name = "关联法规精读", value = "letter_laws_id_count_txt")
    //@Field("letter_laws_id_count_txt")
    @IndexField(name="letter_laws_id_count_txt", description="关联法规精读")
    private List<String> lawsIdCount;
    
    @ApiModelProperty(value = "导言", dataType = "List<String>")
    @FieldAnnotation(name = "导言", value = "letter_introduction_txt")
    //@Field("letter_introduction_txt")
    @IndexField(name="letter_introduction_txt", description="导言")
    private List<String> introduction;
    
    @ApiModelProperty(value = "结语", dataType = "List<String>")
    @FieldAnnotation(name = "结语", value = "letter_conclusion_txt")
    //@Field("letter_conclusion_txt")
    @IndexField(name="letter_conclusion_txt", description="结语")
    private List<String> conclusion;
    
    // 导言-问答索引-纯文本拼接-企业端无效
    ////@Field("letter_introduction_t")
    //private String introduction;
    
    // 导言段落
    @ApiModelProperty(value = "导言段落", dataType = "List<String>")
    @FieldAnnotation(name = "导言段落", value = "letter_introduction_paragraph_txt")
    //@Field("letter_introduction_paragraph_txt")
    @IndexField(name="letter_introduction_paragraph_txt", description="导言段落")
    private List<String> introductionParagraph;
    
    // 导言每句  
    @ApiModelProperty(value = "导言每句", dataType = "List<String>")
    @FieldAnnotation(name = "导言每句", value = "letter_introduction_sentence_txt")
    //@Field("letter_introduction_sentence_txt")
    @IndexField(name="letter_introduction_sentence_txt", description="导言每句")
    private List<String> introductionSentence;
    
    // 问题段落 
    @ApiModelProperty(value = "问题段落", dataType = "List<String>")
    @FieldAnnotation(name = "问题段落", value = "letter_question_paragraph_txt")
    //@Field("letter_question_paragraph_txt")
    @IndexField(name="letter_question_paragraph_txt", description="问题段落")
    private List<String> questionParagraph;
    
    // 问题每句
    @ApiModelProperty(value = "问题每句", dataType = "List<String>")
    @FieldAnnotation(name = "问题每句", value = "letter_question_sentence_txt")
    //@Field("letter_question_sentence_txt")
    @IndexField(name="letter_question_sentence_txt", description="问题每句")
    private List<String> questionSentence;
    
    // 全文（问题和答复）段落 
    @ApiModelProperty(value = "全文段落", dataType = "List<String>")
    @FieldAnnotation(name = "全文段落", value = "letter_qa_paragraph_txt")
    //@Field("letter_qa_paragraph_txt")
    @IndexField(name="letter_qa_paragraph_txt", description="全文段落")
    private List<String> qaParagraph;
    
    // 全文（问题和答复）每句
    @ApiModelProperty(value = "全文每句", dataType = "List<String>")
    @FieldAnnotation(name = "全文每句", value = "letter_qa_sentence_txt")
    //@Field("letter_qa_sentence_txt")
    @IndexField(name="letter_qa_sentence_txt", description="全文每句")
    private List<String> qaSentence;

    // 问题的二级标题展示字段
    @ApiModelProperty(value = "问题标题", dataType = "List<String>")
    @FieldAnnotation(name = "问题标题", value = "letter_question_title_txt")
    //@Field("letter_question_title_txt")
    @IndexField(name="letter_question_title_txt", description="问题标题")
    private List<String> questionTitle;
    
    // ipo拟上市板块
    @ApiModelProperty(value = "ipo拟上市板块", dataType = "String")
    @FieldAnnotation(name = "ipo拟上市板块", value = "ipo_plate_module_t")
    //@Field("ipo_plate_module_t")
    @IndexField(name="ipo_plate_module_t", description="ipo拟上市板块")
    private String ipoPlateModule;
    
    // 发函单位码值
    @ApiModelProperty(value = "发函单位码值", dataType = "String")
    @FieldAnnotation(name = "发函单位码值", value = "letter_letter_org_code_value_t")
    //@Field("letter_letter_org_code_value_t")
    @IndexField(name="letter_letter_org_code_value_t", description="发函单位码值")
    private String letterOrgCodeValue;
    
    // 发行人选择的上市条件
    @ApiModelProperty(value = "发行人选择的上市条件", dataType = "List<String>")
    @FieldAnnotation(name = "发行人选择的上市条件", value = "letter_Issue_condition_txt")
    //@Field("letter_Issue_condition_txt")
    @IndexField(name="letter_Issue_condition_txt", description="发行人选择的上市条件")
    private List<String> IssueCondition;
    
    // 登录其他资本市场类型
    @ApiModelProperty(value = "登录其他资本市场", dataType = "List<String>")
    @FieldAnnotation(name = "登录其他资本市场id", value = "letter_ipo_other_market_info_type_txt")
    //@Field("letter_ipo_other_market_info_type_txt")
    @IndexField(name="letter_ipo_other_market_info_type_txt", description="登录其他资本市场id")
    private List<String> ipoOtherMarketInfotype;
    
    // 函件ipo板块是否开放
    @ApiModelProperty(value = "函件ipo板块是否开放", dataType = "String")
    @FieldAnnotation(name = "函件ipo板块是否开放", value = "letter_ipo_case_module_is_open_t")
    //@Field("letter_ipo_case_module_is_open_t")
    @IndexField(name="letter_ipo_case_module_is_open_t", description="函件ipo板块是否开放")
    private String ipoCaseModuleIsOpen;

    // 补充问题
    @ApiModelProperty(value = "补充问题", dataType = "String")
    @FieldAnnotation(name = "补充问题", value = "letter_supplement_flag_t")
    //@Field("letter_supplement_flag_t")
    @IndexField(name="letter_supplement_flag_t", description="补充问题")
    private String supplementFlag;

    // 证券代码
    @ApiModelProperty(value = "证券代码", dataType = "String")
    @FieldAnnotation(name = "证券代码", value = "letter_ipo_case_company_code_t")
    //@Field("letter_ipo_case_company_code_t")
    @IndexField(name="letter_ipo_case_company_code_t", description="证券代码")
    private String ipoCaseCompanyCode;

    // 证券简称
    @ApiModelProperty(value = "证券简称", dataType = "String")
    @FieldAnnotation(name = "证券简称", value = "letter_ipo_case_company_zh_name_t")
    //@Field("letter_ipo_case_company_zh_name_t")
    @IndexField(name="letter_ipo_case_company_zh_name_t", description="证券简称")
    private String ipoCaseCompanyZhName;

    //公司标志 1新三板 2港股 3正常
    @ApiModelProperty(value = "公司标志", dataType = "String")
    @FieldAnnotation(name = "公司标志", value = "letter_company_info_sign_flag_t")
    //@Field("letter_company_info_sign_flag_t")
    @IndexField(name="letter_company_info_sign_flag_t", description="公司标志")
    private String companyInfoSignFlag;

    // ipo拟上市板块名称
    @ApiModelProperty(value = "ipo拟上市板块名称", dataType = "String")
    @FieldAnnotation(name = "ipo拟上市板块名称", value = "ipo_plate_module_name_t")
    //@Field("ipo_plate_module_name_t")
    @IndexField(name="ipo_plate_module_name_t", description="ipo拟上市板块名称")
    private String ipoPlateModuleName;


    @ApiModelProperty(value = "函件案例id", dataType = "String")
    @FieldAnnotation(name = "函件案例id", value = "letter_case_id_t")
    //@Field("letter_case_id_t")
    @IndexField(name="letter_case_id_t", description="函件案例id")
    private String letterCaseId;

    public String getLetterCaseId() {
        return letterCaseId;
    }

    public void setLetterCaseId(String letterCaseId) {
        this.letterCaseId = letterCaseId;
    }

    public String getIpoPlateModule() {
        return ipoPlateModule;
    }

    public void setIpoPlateModule(String ipoPlateModule) {
        this.ipoPlateModule = ipoPlateModule;
    }

    public String getLetterOrgCodeValue() {
        return letterOrgCodeValue;
    }

    public void setLetterOrgCodeValue(String letterOrgCodeValue) {
        this.letterOrgCodeValue = letterOrgCodeValue;
    }

    public List<String> getIssueCondition() {
        return IssueCondition;
    }

    public void setIssueCondition(List<String> issueCondition) {
        IssueCondition = issueCondition;
    }

    public List<String> getIpoOtherMarketInfotype() {
        return ipoOtherMarketInfotype;
    }

    public void setIpoOtherMarketInfotype(List<String> ipoOtherMarketInfotype) {
        this.ipoOtherMarketInfotype = ipoOtherMarketInfotype;
    }

    public String getIpoCaseModuleIsOpen() {
        return ipoCaseModuleIsOpen;
    }

    public void setIpoCaseModuleIsOpen(String ipoCaseModuleIsOpen) {
        this.ipoCaseModuleIsOpen = ipoCaseModuleIsOpen;
    }

    public List<String> getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(List<String> questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getLetterFileNo() {
        return letterFileNo;
    }

    public void setLetterFileNo(String letterFileNo) {
        this.letterFileNo = letterFileNo;
    }

    public String getIpoCaseStatus() {
        return ipoCaseStatus;
    }

    public void setIpoCaseStatus(String ipoCaseStatus) {
        this.ipoCaseStatus = ipoCaseStatus;
    }

    public List<String> getQuestionTypeCount() {
        return questionTypeCount;
    }

    public void setQuestionTypeCount(List<String> questionTypeCount) {
        this.questionTypeCount = questionTypeCount;
    }

    public List<String> getLawsId() {
        return lawsId;
    }

    public void setLawsId(List<String> lawsId) {
        this.lawsId = lawsId;
    }

    public List<String> getLawsIdCount() {
        return lawsIdCount;
    }

    public void setLawsIdCount(List<String> lawsIdCount) {
        this.lawsIdCount = lawsIdCount;
    }

    public List<String> getIntroduction() {
        return introduction;
    }

    public void setIntroduction(List<String> introduction) {
        this.introduction = introduction;
    }

    public List<String> getConclusion() {
        return conclusion;
    }

    public void setConclusion(List<String> conclusion) {
        this.conclusion = conclusion;
    }

    public List<String> getIntroductionParagraph() {
        return introductionParagraph;
    }

    public void setIntroductionParagraph(List<String> introductionParagraph) {
        this.introductionParagraph = introductionParagraph;
    }

    public List<String> getIntroductionSentence() {
        return introductionSentence;
    }

    public void setIntroductionSentence(List<String> introductionSentence) {
        this.introductionSentence = introductionSentence;
    }

    public List<String> getQuestionParagraph() {
        return questionParagraph;
    }

    public void setQuestionParagraph(List<String> questionParagraph) {
        this.questionParagraph = questionParagraph;
    }

    public List<String> getQuestionSentence() {
        return questionSentence;
    }

    public void setQuestionSentence(List<String> questionSentence) {
        this.questionSentence = questionSentence;
    }

    public List<String> getQaParagraph() {
        return qaParagraph;
    }

    public void setQaParagraph(List<String> qaParagraph) {
        this.qaParagraph = qaParagraph;
    }

    public List<String> getQaSentence() {
        return qaSentence;
    }

    public void setQaSentence(List<String> qaSentence) {
        this.qaSentence = qaSentence;
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

    public String getLetterReturnDateStr() {
        return letterReturnDateStr;
    }

    public void setLetterReturnDateStr(String letterReturnDateStr) {
        this.letterReturnDateStr = letterReturnDateStr;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getCompanyZhName() {
        return companyZhName;
    }

    public void setCompanyZhName(String companyZhName) {
        this.companyZhName = companyZhName;
    }

    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLetterTypeName() {
        return letterTypeName;
    }

    public void setLetterTypeName(String letterTypeName) {
        this.letterTypeName = letterTypeName;
    }

    public String getLawCount() {
        return lawCount;
    }

    public void setLawCount(String lawCount) {
        this.lawCount = lawCount;
    }

    public String getFavourFlag() {
        return favourFlag;
    }

    public void setFavourFlag(String favourFlag) {
        this.favourFlag = favourFlag;
    }

    public List<String> getLetterTypeId() {
        return letterTypeId;
    }

    public void setLetterTypeId(List<String> letterTypeId) {
        this.letterTypeId = letterTypeId;
    }

    public String getLetterType() {
        return letterType;
    }

    public void setLetterType(String letterType) {
        this.letterType = letterType;
    }

    public List<String> getCompanyArea() {
        return companyArea;
    }

    public void setCompanyArea(List<String> companyArea) {
        this.companyArea = companyArea;
    }

    public String getLetterDateStr() {
        return letterDateStr;
    }

    public void setLetterDateStr(String letterDateStr) {
        this.letterDateStr = letterDateStr;
    }

    public String getCompanyRelaCode() {
        return companyRelaCode;
    }

    public void setCompanyRelaCode(String companyRelaCode) {
        this.companyRelaCode = companyRelaCode;
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

    public String getSupplementFlag() {
        return supplementFlag;
    }

    public void setSupplementFlag(String supplementFlag) {
        this.supplementFlag = supplementFlag;
    }

    public String getIpoCaseCompanyCode() {
        return ipoCaseCompanyCode;
    }

    public void setIpoCaseCompanyCode(String ipoCaseCompanyCode) {
        this.ipoCaseCompanyCode = ipoCaseCompanyCode;
    }

    public String getIpoCaseCompanyZhName() {
        return ipoCaseCompanyZhName;
    }

    public void setIpoCaseCompanyZhName(String ipoCaseCompanyZhName) {
        this.ipoCaseCompanyZhName = ipoCaseCompanyZhName;
    }

    public String getCompanyInfoSignFlag() {
        return companyInfoSignFlag;
    }

    public void setCompanyInfoSignFlag(String companyInfoSignFlag) {
        this.companyInfoSignFlag = companyInfoSignFlag;
    }

    public String getIpoPlateModuleName() {
        return ipoPlateModuleName;
    }

    public void setIpoPlateModuleName(String ipoPlateModuleName) {
        this.ipoPlateModuleName = ipoPlateModuleName;
    }
}
