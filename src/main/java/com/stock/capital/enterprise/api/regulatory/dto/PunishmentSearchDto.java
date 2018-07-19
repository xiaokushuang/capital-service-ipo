package com.stock.capital.enterprise.api.regulatory.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stock.core.annotation.FieldAnnotation;
import com.stock.core.dto.Indexable;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class PunishmentSearchDto extends Indexable implements Serializable {

	private static final long serialVersionUID = -5283286219691106934L;
        @FieldAnnotation(name = "处罚明细主键ID",value = "punishment_violate_id_t")
        @Field("punishment_violate_id_t")
        private String violateId;

        @FieldAnnotation(name = "处罚明细排序权重",value = "punishment_violate_sort_t")
        @Field("punishment_violate_sort_t")
        private String violateSort;

        @FieldAnnotation(name = "处罚公司证券代码",value = "punishment_company_code_t")
        @Field("punishment_company_code_t")
        private String companyCode;

        @FieldAnnotation(name = "处罚公司名称",value = "punishment_company_name_t")
        @Field("punishment_company_name_t")
        private String companyName;

        @FieldAnnotation(name = "处罚人",value = "punishment_person_t")
        @Field("punishment_person_t")
        private String person;

        @FieldAnnotation(name = "处罚人身份内码",value = "punishment_person_identity_txt")
        @Field("punishment_person_identity_txt")
        private List<String> violatePersonIdtList;

        @FieldAnnotation(name = "处罚人身份",value = "punishment_person_identity_t")
        @Field("punishment_person_identity_t")
        private String violatePersonIdtLabel;

        @FieldAnnotation(name = "违规类型内码（价值在线）",value = "punishment_violate_type_vo_txt")
        @Field("punishment_violate_type_vo_txt")
        private List<String> violateTypeVOList;

        @FieldAnnotation(name = "违规类型",value = "punishment_violate_type_vo_t")
        @Field("punishment_violate_type_vo_t")
        private String violateTypeVOLabel;

        @FieldAnnotation(name = "处罚类型内码",value = "punishment_punish_type_txt")
        @Field("punishment_punish_type_txt")
        private List<String> punishTypeList;

        @FieldAnnotation(name = "处罚类型",value = "punishment_punish_type_t")
        @Field("punishment_punish_type_t")
        private String punishTypeLabel;

        @FieldAnnotation(name = "没收金额",value = "punishment_confiscate_amount_d")
        @Field("punishment_confiscate_amount_d")
        private Double confiscateAmount;

        @FieldAnnotation(name = "没收货币种类",value = "punishment_confiscate_amount_type_t")
        @Field("punishment_confiscate_amount_type_t")
        private String confiscateAmountType;

        @FieldAnnotation(name = "处罚金额",value = "punishment_punish_amount_d")
        @Field("punishment_punish_amount_d")
        private Double punishAmount;

        @FieldAnnotation(name = "处罚金额种类",value = "punishment_punish_amount_type_t")
        @Field("punishment_punish_amount_type_t")
        private String punishAmountType;

        @FieldAnnotation(name = "禁入年限",value = "punishment_limit_years_i")
        @Field("punishment_limit_years_i")
        private Integer limitYears;

        @FieldAnnotation(name = "更新日期",value = "punishment_update_time_dt")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
        @Field("punishment_update_time_dt")
        private Date updateTime;

        @FieldAnnotation(name = "更新日期字符串",value = "punishment_update_time_str_s")
        @Field("punishment_update_time_str_s")
        private String updateTimeStr;
        
        @FieldAnnotation(name = "发布日期",value = "punishment_release_time_dt")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
        @Field("punishment_release_time_dt")
        private Date releaseTime;

        @FieldAnnotation(name = "发布日期字符串",value = "punishment_release_time_str_s")
        @Field("punishment_release_time_str_s")
        private String releaseTimeStr;
        
        @FieldAnnotation(name = "是否撤销",value = "punishment_is_revoke_t")
        @Field("punishment_is_revoke_t")
        private String isRevoke;
        
        @Field("punishment_violate_type_pvo_txt")
        private List<String> violateTypePvoList;
    
        @Field("punishment_punish_type_p_txt")
        private List<String> punishTypePList;

        @Field("punishment_is_neeq_t")
        private String isNeeq;

        private String punishType;

        private String violateTypeVO;

        @Field("punishment_company_sort_s")
        private String companySort;
    
        @Field("punishment_person_sort_s")
        private String personSort;
    
        @Field("punishment_person_identity_sort_s")
        private String violatePersonIdtLabelSort;
    
        @Field("violation_violate_type_vo_label_sort_s")
        private String violateTypeVOLabelSort;
    
        @Field("violation_punish_type_label_sort_s")
        private String punishTypeLabelSort;
    
        @Field("punishment_is_revoke_sort_s")
        private String isRevokeSort;
     
        @Field("punishment_com_belongs_plate_t")
        private String comBelongsPlate;
    
        @Field("punishment_com_belongs_plate_name_s")
        private String comBelongsPlateName;
    
        @Field("punishment_com_industry_code_txt")
        private List<String> comIndustryCodeList;
    
        @Field("punishment_com_industry_name_t")
        private String comIndustryName;
    
        @Field("punishment_com_city_txt")
        private List<String> comCityList;
    
        @Field("punishment_com_city_name_t")
        private String comCityName;
    
        @Field("punishment_p_industry_name_s")
        private String pIndustryName;
    
        @Field("punishment_p_com_city_name_s")
        private String pComCityName;

        //2018-02-27 需求3851 by wangyu start
        @Field("company_area_txt")
        private List<String> companyArea;
        //2018-02-27 需求3851 by wangyu end
        
	public String getIsNeeq() {
		return isNeeq;
	}

	public void setIsNeeq(String isNeeq) {
		this.isNeeq = isNeeq;
	}

	public String getViolateId() {
		return violateId;
	}

	public void setViolateId(String violateId) {
		this.violateId = violateId;
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

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public List<String> getViolatePersonIdtList() {
		return violatePersonIdtList;
	}

	public void setViolatePersonIdtList(List<String> violatePersonIdtList) {
		this.violatePersonIdtList = violatePersonIdtList;
	}

	public String getViolatePersonIdtLabel() {
		return violatePersonIdtLabel;
	}

	public void setViolatePersonIdtLabel(String violatePersonIdtLabel) {
		this.violatePersonIdtLabel = violatePersonIdtLabel;
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

	public Double getConfiscateAmount() {
		return confiscateAmount;
	}

	public void setConfiscateAmount(Double confiscateAmount) {
		this.confiscateAmount = confiscateAmount;
	}

	public Double getPunishAmount() {
		return punishAmount;
	}

	public void setPunishAmount(Double punishAmount) {
		this.punishAmount = punishAmount;
	}

	public Integer getLimitYears() {
		return limitYears;
	}

	public void setLimitYears(Integer limitYears) {
		this.limitYears = limitYears;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getConfiscateAmountType() {
		return confiscateAmountType;
	}

	public void setConfiscateAmountType(String confiscateAmountType) {
		this.confiscateAmountType = confiscateAmountType;
	}

	public String getPunishAmountType() {
		return punishAmountType;
	}

	public void setPunishAmountType(String punishAmountType) {
		this.punishAmountType = punishAmountType;
	}

	public String getViolateSort() {
		return violateSort;
	}

	public void setViolateSort(String violateSort) {
		this.violateSort = violateSort;
	}

	public String getIsRevoke() {
		return isRevoke;
	}

	public void setIsRevoke(String isRevoke) {
		this.isRevoke = isRevoke;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getUpdateTimeStr() {
                return updateTimeStr;
        }
    
        public void setUpdateTimeStr(String updateTimeStr) {
                this.updateTimeStr = updateTimeStr;
        }
    
        public String getReleaseTimeStr() {
                return releaseTimeStr;
        }
    
        public void setReleaseTimeStr(String releaseTimeStr) {
                this.releaseTimeStr = releaseTimeStr;
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

	public String getViolateTypeVOLabel() {
		return violateTypeVOLabel;
	}

	public void setViolateTypeVOLabel(String violateTypeVOLabel) {
		this.violateTypeVOLabel = violateTypeVOLabel;
	}

	public String getViolateTypeVO() {
		return violateTypeVO;
	}

	public void setViolateTypeVO(String violateTypeVO) {
		this.violateTypeVO = violateTypeVO;
	}


	    // 2017/09/27 需求1833 任务2000 by weishisheng start
	    /**
	     * companySort.
	     *
	     * @return  the companySort
	     *
	     */
	    public String getCompanySort() {
	        return companySort;
	    }

	    /**
	     * companySort.
	     *
	     * @param   companySort    the companySort to set
	     *
	     */
	    public void setCompanySort(String companySort) {
	        this.companySort = companySort;
	    }

	    /**
	     * personSort.
	     *
	     * @return  the personSort
	     *
	     */
	    public String getPersonSort() {
	        return personSort;
	    }

	    /**
	     * personSort.
	     *
	     * @param   personSort    the personSort to set
	     *
	     */
	    public void setPersonSort(String personSort) {
	        this.personSort = personSort;
	    }

	    /**
	     * violatePersonIdtLabelSort.
	     *
	     * @return  the violatePersonIdtLabelSort
	     *
	     */
	    public String getViolatePersonIdtLabelSort() {
	        return violatePersonIdtLabelSort;
	    }

	    /**
	     * violatePersonIdtLabelSort.
	     *
	     * @param   violatePersonIdtLabelSort    the violatePersonIdtLabelSort to set
	     *
	     */
	    public void setViolatePersonIdtLabelSort(String violatePersonIdtLabelSort) {
	        this.violatePersonIdtLabelSort = violatePersonIdtLabelSort;
	    }

	    /**
	     * violateTypeVOLabelSort.
	     *
	     * @return  the violateTypeVOLabelSort
	     *
	     */
	    public String getViolateTypeVOLabelSort() {
	        return violateTypeVOLabelSort;
	    }

	    /**
	     * violateTypeVOLabelSort.
	     *
	     * @param   violateTypeVOLabelSort    the violateTypeVOLabelSort to set
	     *
	     */
	    public void setViolateTypeVOLabelSort(String violateTypeVOLabelSort) {
	        this.violateTypeVOLabelSort = violateTypeVOLabelSort;
	    }

	    /**
	     * punishTypeLabelSort.
	     *
	     * @return  the punishTypeLabelSort
	     *
	     */
	    public String getPunishTypeLabelSort() {
	        return punishTypeLabelSort;
	    }

	    /**
	     * punishTypeLabelSort.
	     *
	     * @param   punishTypeLabelSort    the punishTypeLabelSort to set
	     *
	     */
	    public void setPunishTypeLabelSort(String punishTypeLabelSort) {
	        this.punishTypeLabelSort = punishTypeLabelSort;
	    }

	    /**
	     * isRevokeSort.
	     *
	     * @return  the isRevokeSort
	     *
	     */
	    public String getIsRevokeSort() {
	        return isRevokeSort;
	    }

	    /**
	     * isRevokeSort.
	     *
	     * @param   isRevokeSort    the isRevokeSort to set
	     *
	     */
	    public void setIsRevokeSort(String isRevokeSort) {
	        this.isRevokeSort = isRevokeSort;
	    }
	    // 2017/09/27 需求1833 任务2000 by weishisheng end

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

        public List<String> getComIndustryCodeList() {
            return comIndustryCodeList;
        }

        public void setComIndustryCodeList(List<String> comIndustryCodeList) {
            this.comIndustryCodeList = comIndustryCodeList;
        }

        public String getComIndustryName() {
            return comIndustryName;
        }

        public void setComIndustryName(String comIndustryName) {
            this.comIndustryName = comIndustryName;
        }

        public List<String> getComCityList() {
            return comCityList;
        }

        public void setComCityList(List<String> comCityList) {
            this.comCityList = comCityList;
        }

        public String getComCityName() {
            return comCityName;
        }

        public void setComCityName(String comCityName) {
            this.comCityName = comCityName;
        }

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

        public List<String> getCompanyArea() {
            return companyArea;
        }

        public void setCompanyArea(List<String> companyArea) {
            this.companyArea = companyArea;
        }
}
