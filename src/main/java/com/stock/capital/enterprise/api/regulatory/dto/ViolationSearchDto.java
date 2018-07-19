package com.stock.capital.enterprise.api.regulatory.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stock.capital.enterprise.common.constant.Global;
import com.stock.core.annotation.FieldAnnotation;
import com.stock.core.dto.Indexable;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@FieldAnnotation(name = "公告", value = Global.INDEX_MODULE_VIOLATE)
public class ViolationSearchDto extends Indexable implements Serializable {

    private static final long serialVersionUID = -5283286219691106934L;

    @FieldAnnotation(name = "违规案例主键ID", value = "violation_violate_id_t")
    @Field("violation_violate_id_t")
    private String violateId;

    @FieldAnnotation(name = "案例标题", value = "violation_title_t")
    @Field("violation_title_t")
    private String title;

    @FieldAnnotation(name = "案例内容", value = "violation_content_t")
    @Field("violation_content_t")
    private String content;

    @FieldAnnotation(name = "违规公司证券代码", value = "violation_company_code_t")
    @Field("violation_company_code_t")
    private String companyCode;

    @FieldAnnotation(name = "违规公司名称", value = "violation_company_name_t")
    @Field("violation_company_name_t")
    private String companyName;

    @FieldAnnotation(name = "违规人", value = "violation_violate_person_t")
    @Field("violation_violate_person_t")
    private String violatePerson;

    @FieldAnnotation(name = "案例进程的标题", value = "violation_process_title_txt")
    @Field("violation_process_title_txt")
    private List<String> violationProcessTitleList;

    @FieldAnnotation(name = "案例进程的内容", value = "violation_process_content_txt")
    @Field("violation_process_content_txt")
    private List<String> violationProcessContentList;

    @FieldAnnotation(name = "违规人身份", value = "violation_violate_identity_txt")
    @Field("violation_violate_identity_txt")
    private List<String> violatePersonIdtList;

    @FieldAnnotation(name = "违规类型内码", value = "violation_violate_type_txt")
    @Field("violation_violate_type_txt")
    private List<String> violateTypeList;

    @FieldAnnotation(name = "违规类型内码（价值在线）", value = "violation_violate_type_vo_txt")
    @Field("violation_violate_type_vo_txt")
    private List<String> violateTypeVOList;

    @FieldAnnotation(name = "处罚类型内码", value = "violation_punish_type_txt")
    @Field("violation_punish_type_txt")
    private List<String> punishTypeList;

    @FieldAnnotation(name = "处罚机构", value = "violation_punish_org_t")
    @Field("violation_punish_org_t")
    private String punishOrg;

    @FieldAnnotation(name = "案例排序权重", value = "violation_sort_t")
    @Field("violation_sort_t")
    private String sort;

    @FieldAnnotation(name = "申辩情况内码", value = "violation_adopt_condition_t")
    @Field("violation_adopt_condition_t")
    private String adoptCondition;

    @FieldAnnotation(name = "更新日期", value = "violation_update_time_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Field("violation_update_time_dt")
    private Date updateTime;

    @FieldAnnotation(name = "更新日期字符串", value = "violation_update_time_str_s")
    @Field("violation_update_time_str_s")
    private String updateTimeStr;

    @FieldAnnotation(name = "发布日期", value = "violation_release_time_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Field("violation_release_time_dt")
    private Date releaseTime;

    @FieldAnnotation(name = "发布日期字符串", value = "violation_release_time_str_s")
    @Field("violation_release_time_str_s")
    private String releaseTimeStr;

    @FieldAnnotation(name = "关联法规", value = "violation_relative_laws_txt")
    @Field("violation_relative_laws_txt")
    private List<String> relativeLawsList;

    @Field("violation_violate_type_pvo_txt")
    private List<String> violateTypePvoList;

    @Field("violation_punish_type_p_txt")
    private List<String> punishTypePList;

    @Field("violation_punish_organization_t")
    private String punishOrganization;

    @Field("violation_process_title_txt")
    private List<String> processTitleList;

    @Field("violation_process_content_txt")
    private List<String> processContentList;

    @Field("violation_relative_laws_item_txt")
    private List<String> relativeLawsItemList;

    @Field("violation_com_belongs_plate_t")
    private String comBelongsPlate;

    @Field("violation_com_belongs_plate_name_s")
    private String comBelongsPlateName;

    @Field("violation_com_industry_code_txt")
    private List<String> comIndustryCodeList;

    @Field("violation_com_industry_name_t")
    private String comIndustryName;

    @Field("violation_com_city_txt")
    private List<String> comCityList;

    @Field("violation_com_city_name_t")
    private String comCityName;

    @Field("violation_is_neeq_t")
    private String isNeeq;

    // 2017/04/05 BUG3396 by wangchao START
    @Field("violation_punish_type_label_t")
    private String punishTypeLabel;

    private String punishType;
    // 2017/04/05 BUG3396 by wangchao END

    // 2017/09/26 需求1833 by weishisheng START
    @Field("violation_title_sort_s")
    private String titleSort;

    @Field("violation_company_sort_s")
    private String companySort;

    @Field("violation_punish_org_sort_s")
    private String punishOrgSort;

    @Field("violation_violate_person_sort_s")
    private String violatePersonSort;

    @Field("violation_punish_type_sort_s")
    private String punishTypeSort;
    // 2017/09/26 需求1833 by weishisheng END

    @Field("violation_p_industry_name_s")
    private String pIndustryName;

    @Field("violation_p_com_city_name_s")
    private String pComCityName;

    @Field("violation_violate_person_cnt_l")
    private Long violatePersonCnt;

    //2018-02-27 需求3851 by wangyu start
    @Field("company_area_txt")
    private List<String> companyArea;
    //2018-02-27 需求3851 by wangyu end
    
    public List<String> getComIndustryCodeList() {
        return comIndustryCodeList;
    }

    public String getPunishTypeLabel() {
        return punishTypeLabel;
    }

    public void setPunishTypeLabel(String punishTypeLabel) {
        this.punishTypeLabel = punishTypeLabel;
    }

    public String getPunishType() {
        return punishType;
    }

    public void setPunishType(String punishType) {
        this.punishType = punishType;
    }

    public void setComIndustryCodeList(List<String> comIndustryCodeList) {
        this.comIndustryCodeList = comIndustryCodeList;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public String getIsNeeq() {
        return isNeeq;
    }

    public void setIsNeeq(String isNeeq) {
        this.isNeeq = isNeeq;
    }

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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getViolatePerson() {
        return violatePerson;
    }

    public void setViolatePerson(String violatePerson) {
        this.violatePerson = violatePerson;
    }

    public List<String> getViolatePersonIdtList() {
        return violatePersonIdtList;
    }

    public void setViolatePersonIdtList(List<String> violatePersonIdtList) {
        this.violatePersonIdtList = violatePersonIdtList;
    }

    public List<String> getViolateTypeVOList() {
        return violateTypeVOList;
    }

    public void setViolateTypeVOList(List<String> violateTypeVOList) {
        this.violateTypeVOList = violateTypeVOList;
    }

    public List<String> getPunishTypeList() {
        return punishTypeList;
    }

    public void setPunishTypeList(List<String> punishTypeList) {
        this.punishTypeList = punishTypeList;
    }

    public String getPunishOrg() {
        return punishOrg;
    }

    public void setPunishOrg(String punishOrg) {
        this.punishOrg = punishOrg;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getAdoptCondition() {
        return adoptCondition;
    }

    public void setAdoptCondition(String adoptCondition) {
        this.adoptCondition = adoptCondition;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getViolateId() {
        return violateId;
    }

    public void setViolateId(String violateId) {
        this.violateId = violateId;
    }

    public List<String> getProcessTitleList() {
        return processTitleList;
    }

    public void setProcessTitleList(List<String> processTitleList) {
        this.processTitleList = processTitleList;
    }

    public List<String> getProcessContentList() {
        return processContentList;
    }

    public void setProcessContentList(List<String> processContentList) {
        this.processContentList = processContentList;
    }

    public List<String> getRelativeLawsList() {
        return relativeLawsList;
    }

    public void setRelativeLawsList(List<String> relativeLawsList) {
        this.relativeLawsList = relativeLawsList;
    }

    public String getComBelongsPlate() {
        return comBelongsPlate;
    }

    public void setComBelongsPlate(String comBelongsPlate) {
        this.comBelongsPlate = comBelongsPlate;
    }

    public String getComBelongsPlateName() {
        return comBelongsPlateName;
    }

    public void setComBelongsPlateName(String comBelongsPlateName) {
        this.comBelongsPlateName = comBelongsPlateName;
    }

    public String getComIndustryName() {
        return comIndustryName;
    }

    public void setComIndustryName(String comIndustryName) {
        this.comIndustryName = comIndustryName;
    }



    public String getComCityName() {
        return comCityName;
    }

    public void setComCityName(String comCityName) {
        this.comCityName = comCityName;
    }

    public String getPunishOrganization() {
        return punishOrganization;
    }

    public void setPunishOrganization(String punishOrganization) {
        this.punishOrganization = punishOrganization;
    }

    public List<String> getRelativeLawsItemList() {
        return relativeLawsItemList;
    }

    public void setRelativeLawsItemList(List<String> relativeLawsItemList) {
        this.relativeLawsItemList = relativeLawsItemList;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getReleaseTimeStr() {
        return releaseTimeStr;
    }

    public void setReleaseTimeStr(String releaseTimeStr) {
        this.releaseTimeStr = releaseTimeStr;
    }

    // 2017/09/26 需求1833 by weishisheng START
    /**
     * titleSort.
     *
     * @return the titleSort
     *
     */
    public String getTitleSort() {
        return titleSort;
    }

    /**
     * titleSort.
     *
     * @param titleSort
     *            the titleSort to set
     *
     */
    public void setTitleSort(String titleSort) {
        this.titleSort = titleSort;
    }

    /**
     * companySort.
     *
     * @return the companySort
     *
     */
    public String getCompanySort() {
        return companySort;
    }

    /**
     * companySort.
     *
     * @param companySort
     *            the companySort to set
     *
     */
    public void setCompanySort(String companySort) {
        this.companySort = companySort;
    }

    /**
     * punishOrgSort.
     *
     * @return the punishOrgSort
     *
     */
    public String getPunishOrgSort() {
        return punishOrgSort;
    }

    /**
     * punishOrgSort.
     *
     * @param punishOrgSort
     *            the punishOrgSort to set
     *
     */
    public void setPunishOrgSort(String punishOrgSort) {
        this.punishOrgSort = punishOrgSort;
    }

    /**
     * violatePersonSort.
     *
     * @return the violatePersonSort
     *
     */
    public String getViolatePersonSort() {
        return violatePersonSort;
    }

    /**
     * violatePersonSort.
     *
     * @param violatePersonSort
     *            the violatePersonSort to set
     *
     */
    public void setViolatePersonSort(String violatePersonSort) {
        this.violatePersonSort = violatePersonSort;
    }

    /**
     * punishTypeSort.
     *
     * @return the punishTypeSort
     *
     */
    public String getPunishTypeSort() {
        return punishTypeSort;
    }

    /**
     * punishTypeSort.
     *
     * @param punishTypeSort
     *            the punishTypeSort to set
     *
     */
    public void setPunishTypeSort(String punishTypeSort) {
        this.punishTypeSort = punishTypeSort;
    }

    // 2017/09/26 需求1833 by weishisheng END

    public String getpIndustryName() {
        return pIndustryName;
    }

    public void setpIndustryName(String pIndustryName) {
        this.pIndustryName = pIndustryName;
    }

    public String getpComCityName() {
        return pComCityName;
    }

    public void setpComCityName(String pComCityName) {
        this.pComCityName = pComCityName;
    }

    public Long getViolatePersonCnt() {
        return violatePersonCnt;
    }

    public void setViolatePersonCnt(Long violatePersonCnt) {
        this.violatePersonCnt = violatePersonCnt;
    }

    public List<String> getViolateTypePvoList() {
        return violateTypePvoList;
    }

    public void setViolateTypePvoList(List<String> violateTypePvoList) {
        this.violateTypePvoList = violateTypePvoList;
    }

    public List<String> getPunishTypePList() {
        return punishTypePList;
    }

    public void setPunishTypePList(List<String> punishTypePList) {
        this.punishTypePList = punishTypePList;
    }

	public List<String> getViolationProcessTitleList() {
		return violationProcessTitleList;
	}

	public List<String> getViolationProcessContentList() {
		return violationProcessContentList;
	}

	public List<String> getViolateTypeList() {
		return violateTypeList;
	}

	public List<String> getComCityList() {
		return comCityList;
	}

	public void setViolationProcessTitleList(List<String> violationProcessTitleList) {
		this.violationProcessTitleList = violationProcessTitleList;
	}

	public void setViolationProcessContentList(
			List<String> violationProcessContentList) {
		this.violationProcessContentList = violationProcessContentList;
	}

	public void setViolateTypeList(List<String> violateTypeList) {
		this.violateTypeList = violateTypeList;
	}

	public void setComCityList(List<String> comCityList) {
		this.comCityList = comCityList;
	}
    
        public List<String> getCompanyArea() {
            return companyArea;
        }

        public void setCompanyArea(List<String> companyArea) {
            this.companyArea = companyArea;
        }
}
