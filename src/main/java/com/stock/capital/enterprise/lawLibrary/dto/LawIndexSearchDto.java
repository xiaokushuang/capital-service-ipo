package com.stock.capital.enterprise.lawLibrary.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stock.capital.enterprise.common.constant.Global;
import com.stock.capital.enterprise.common.dto.PageOperationDto;
import com.stock.core.annotation.Document;
import com.stock.core.annotation.FieldAnnotation;
import com.stock.core.annotation.IndexField;
//import org.apache.solr.client.solrj.beans.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
//@FieldAnnotation(name = "法律法规", value = Global.INDEX_MODULE_LAW)
@Document(indexName = Global.LAWS_INFO_INDEX_NAME)
public class LawIndexSearchDto extends PageOperationDto implements Serializable {

    private static final long serialVersionUID = -5283286219691106934L;

    @FieldAnnotation(name = "法规标题", value = "laws_title_t")
    //@Field("laws_title_t")
    @IndexField(name = "laws_title_t")
    private String title;

    @FieldAnnotation(name = "法规内容", value = "laws_content_t")
    //@Field("laws_content_t")
    @IndexField(name = "laws_content_t")
    private String content;

    //@Field("laws_keyword_t")
    @IndexField(name = "laws_keyword_t")
    private String keyword;

    //@Field("laws_type_t")
    @IndexField(name = "laws_type_t")
    private String lawType;

    @FieldAnnotation(name = "法规位阶Code", value = "laws_type_search_txt")
    //@Field("laws_type_search_txt")
    @IndexField(name = "laws_type_search_txt")
    private List<String> lawTypeSearch;

    @FieldAnnotation(name = "发文单位Code", value = "laws_source_search_txt")
    //@Field("laws_source_search_txt")
    @IndexField(name = "laws_source_search_txt")
    private List<String> lawSourceSearch;

    //@Field("laws_source_t")
    @IndexField(name = "laws_source_t")
    private String lawSource;

    //@Field("laws_type_sort_t")
    @IndexField(name = "laws_type_sort_t")
    private String typeSort;

    //@Field("laws_declare_type_t")
    @IndexField(name = "laws_declare_type_t")
    private String declareType;

    @FieldAnnotation(name = "业务分类Code", value = "laws_search_declare_type_txt")
    //@Field("laws_search_declare_type_txt")
    @IndexField(name = "laws_search_declare_type_txt")
    private List<String> declareTypeList;

    //@Field("laws_hint_info_t")
    @IndexField(name = "laws_hint_info_t")
    private String hintInfo;

    //@Field("laws_sign_t")
    @IndexField(name = "laws_sign_t")
    private String lawSign;

    //@Field("laws_grade_t")
    @IndexField(name = "laws_grade_t")
    private String lawGrade;

    @FieldAnnotation(name = "适用范围Code", value = "laws_scopes_txt")
    //@Field("laws_scopes_txt")
    @IndexField(name = "laws_scopes_txt")
    private List<String> lawsScopes;

    //@Field("laws_show_flag_t")
    @IndexField(name = "laws_show_flag_t")
    private String lawShowFlag;

    @FieldAnnotation(name = "颁布日期", value = "laws_publish_date_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //@Field("laws_publish_date_dt")
    @IndexField(name = "laws_publish_date_dt")
    private Date publishDate;

    @FieldAnnotation(name = "失效时间", value = "laws_invalid_date_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //@Field("laws_invalid_date_dt")
    @IndexField(name = "laws_invalid_date_dt")
    private Date lawInvalidDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //@Field("laws_modify_date_dt")
    @IndexField(name = "laws_modify_date_dt")
    private Date lawModifyDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startPublishDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endPublishDate;

    @FieldAnnotation(name = "生效日期", value = "laws_effect_date_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //@Field("laws_effect_date_dt")
    @IndexField(name = "laws_effect_date_dt")
    private Date lawEffectDate;

    @FieldAnnotation(name = "适用板块", value = "laws_apply_module_t")
    //@Field("laws_apply_module_t")
    @IndexField(name = "laws_apply_module_t")
    private String lawsApplyModule;

    @FieldAnnotation(name = "适用板块list", value = "laws_search_apply_module_txt")
    //@Field("laws_search_apply_module_txt")
    @IndexField(name = "laws_search_apply_module_txt")
    private List<String> lawsApplyModuleList;

    @FieldAnnotation(name = "债券品种", value = "laws_bond_no_t")
    //@Field("laws_bond_no_t")
    @IndexField(name = "laws_bond_no_t")
    private String bondNo;

    @FieldAnnotation(name = "债券品种list", value = "laws_search_bond_no_txt")
    //@Field("laws_search_bond_no_txt")
    @IndexField(name = "laws_search_bond_no_txt")
    private List<String> bondNoList;

    private List<String> lawsTypeValList;

    @FieldAnnotation(name = "法规沿革",value = "laws_evolution_status_t")
    //@Field("laws_evolution_status_t")
    @IndexField(name = "laws_evolution_status_t")
    private String lawEvolutionStatus;

    @FieldAnnotation(name = "是否含有违规案例",value = "laws_violate_flag_t")
    //@Field("laws_violate_flag_t")
    @IndexField(name = "laws_violate_flag_t")
    private String lawViolateFlag;

    @FieldAnnotation(name = "是否含有函件",value = "laws_letter_flag_t")
    //@Field("laws_letter_flag_t")
    @IndexField(name = "laws_letter_flag_t")
    private String lawLetterFlag;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getLawInvalidDate() {
        return lawInvalidDate;
    }

    public void setLawInvalidDate(Date lawInvalidDate) {
        this.lawInvalidDate = lawInvalidDate;
    }

    public Date getLawModifyDate() {
        return lawModifyDate;
    }

    public void setLawModifyDate(Date lawModifyDate) {
        this.lawModifyDate = lawModifyDate;
    }

    public Date getStartPublishDate() {
        return startPublishDate;
    }

    public void setStartPublishDate(Date startPublishDate) {
        this.startPublishDate = startPublishDate;
    }

    public Date getEndPublishDate() {
        return endPublishDate;
    }

    public void setEndPublishDate(Date endPublishDate) {
        this.endPublishDate = endPublishDate;
    }

    public String getLawType() {
        return lawType;
    }

    public void setLawType(String lawType) {
        this.lawType = lawType;
    }

    public String getLawSource() {
        return lawSource;
    }

    public void setLawSource(String lawSource) {
        this.lawSource = lawSource;
    }

    public String getLawSign() {
        return lawSign;
    }

    public void setLawSign(String lawSign) {
        this.lawSign = lawSign;
    }

    public String getLawGrade() {
        return lawGrade;
    }

    public void setLawGrade(String lawGrade) {
        this.lawGrade = lawGrade;
    }

    public String getDeclareType() {
        return declareType;
    }

    public void setDeclareType(String declareType) {
        this.declareType = declareType;
    }

    public String getHintInfo() {
        return hintInfo;
    }

    public void setHintInfo(String hintInfo) {
        this.hintInfo = hintInfo;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<String> getLawsTypeValList() {
        return lawsTypeValList;
    }

    public void setLawsTypeValList(List<String> lawsTypeValList) {
        this.lawsTypeValList = lawsTypeValList;
    }

    public List<String> getLawsScopes() {
        return lawsScopes;
    }

    public void setLawsScopes(List<String> lawsScopes) {
        this.lawsScopes = lawsScopes;
    }

    public String getLawShowFlag() {
        return lawShowFlag;
    }

    public void setLawShowFlag(String lawShowFlag) {
        this.lawShowFlag = lawShowFlag;
    }

    public List<String> getLawTypeSearch() {
        return lawTypeSearch;
    }

    public void setLawTypeSearch(List<String> lawTypeSearch) {
        this.lawTypeSearch = lawTypeSearch;
    }

    public List<String> getLawSourceSearch() {
        return lawSourceSearch;
    }

    public void setLawSourceSearch(List<String> lawSourceSearch) {
        this.lawSourceSearch = lawSourceSearch;
    }

    public List<String> getDeclareTypeList() {
        return declareTypeList;
    }

    public void setDeclareTypeList(List<String> declareTypeList) {
        this.declareTypeList = declareTypeList;
    }

    public String getTypeSort() {
        return typeSort;
    }

    public void setTypeSort(String typeSort) {
        this.typeSort = typeSort;
    }

    public Date getLawEffectDate() {
        return lawEffectDate;
    }

    public void setLawEffectDate(Date lawEffectDate) {
        this.lawEffectDate = lawEffectDate;
    }

    public String getLawsApplyModule() {
        return lawsApplyModule;
    }

    public void setLawsApplyModule(String lawsApplyModule) {
        this.lawsApplyModule = lawsApplyModule;
    }

    public List<String> getLawsApplyModuleList() {
        return lawsApplyModuleList;
    }

    public void setLawsApplyModuleList(List<String> lawsApplyModuleList) {
        this.lawsApplyModuleList = lawsApplyModuleList;
    }

    public String getBondNo() {
        return bondNo;
    }

    public List<String> getBondNoList() {
        return bondNoList;
    }

    public void setBondNo(String bondNo) {
        this.bondNo = bondNo;
    }

    public void setBondNoList(List<String> bondNoList) {
        this.bondNoList = bondNoList;
    }

    public String getLawEvolutionStatus() {
        return lawEvolutionStatus;
    }

    public void setLawEvolutionStatus(String lawEvolutionStatus) {
        this.lawEvolutionStatus = lawEvolutionStatus;
    }

    public String getLawViolateFlag() {
        return lawViolateFlag;
    }

    public void setLawViolateFlag(String lawViolateFlag) {
        this.lawViolateFlag = lawViolateFlag;
    }

    public String getLawLetterFlag() {
        return lawLetterFlag;
    }

    public void setLawLetterFlag(String lawLetterFlag) {
        this.lawLetterFlag = lawLetterFlag;
    }

}
