package com.stock.capital.enterprise.declare.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat; 
import com.stock.capital.enterprise.common.entity.DeclareIdea;
import com.stock.core.dto.OptionDto;

public class DeclareTypeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5887172876515287318L;
	
	private List<String> typeIds;
	
	private List<String> fileIds;
	
	private List<DeclareFileDto> declareFileList;
	
	private String isType;
	
	private String meetingId;
	
	private String meetingIdFlag;
	
	private String pageFlag;
	
	private String meetingName;
	
	private String sessionFlag;
	
	private String searchInfo;
	
	private String declareName;

	private String stepFlag;
	
        private String queryField;
	        
	private List<String> typeIdList;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date disclosureDate;
	
        private String disclosureMedia;

        private List<OptionDto> disclosureMediaList;

        /**
     * 自我心得
     */
    private List<DeclareIdea> declareIdeaList;

	public List<String> getTypeIds() {
		return typeIds;
	}

	public void setTypeIds(List<String> typeIds) {
		this.typeIds = typeIds;
	}

	public List<String> getFileIds() {
		return fileIds;
	}

	public void setFileIds(List<String> fileIds) {
		this.fileIds = fileIds;
	}

	public List<DeclareFileDto> getDeclareFileList() {
		return declareFileList;
	}

	public void setDeclareFileList(List<DeclareFileDto> declareFileList) {
		this.declareFileList = declareFileList;
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getSessionFlag() {
		return sessionFlag;
	}

	public void setSessionFlag(String sessionFlag) {
		this.sessionFlag = sessionFlag;
	}

	public String getSearchInfo() {
		return searchInfo;
	}

	public void setSearchInfo(String searchInfo) {
		this.searchInfo = searchInfo;
	}

	public List<DeclareIdea> getDeclareIdeaList() {
		return declareIdeaList;
	}

	public void setDeclareIdeaList(List<DeclareIdea> declareIdeaList) {
		this.declareIdeaList = declareIdeaList;
	}

	public String getIsType() {
		return isType;
	}

	public void setIsType(String isType) {
		this.isType = isType;
	}

	public String getMeetingIdFlag() {
		return meetingIdFlag;
	}

	public void setMeetingIdFlag(String meetingIdFlag) {
		this.meetingIdFlag = meetingIdFlag;
	}

	public String getDeclareName() {
		return declareName;
	}

	public void setDeclareName(String declareName) {
		this.declareName = declareName;
	}

	public String getPageFlag() {
		return pageFlag;
	}

	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}

	public Date getDisclosureDate() {
		return disclosureDate;
	}

	public void setDisclosureDate(Date disclosureDate) {
		this.disclosureDate = disclosureDate;
	}

	public String getStepFlag() {
		return stepFlag;
	}

	public void setStepFlag(String stepFlag) {
		this.stepFlag = stepFlag;
	}

    public String getDisclosureMedia() {
        return disclosureMedia;
    }

    public void setDisclosureMedia(String disclosureMedia) {
        this.disclosureMedia = disclosureMedia;
    }

    public List<OptionDto> getDisclosureMediaList() {
        return disclosureMediaList;
    }

    public void setDisclosureMediaList(List<OptionDto> disclosureMediaList) {
        this.disclosureMediaList = disclosureMediaList;
    }

    public String getQueryField() {
        return queryField;
    }

    public void setQueryField(String queryField) {
        this.queryField = queryField;
    }

    public List<String> getTypeIdList() {
        return typeIdList;
    }

    public void setTypeIdList(List<String> typeIdList) {
        this.typeIdList = typeIdList;
    }
	
}
