package com.stock.capital.enterprise.declare.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; 
import com.stock.capital.enterprise.common.entity.DeclareType;
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class DeclareInfoSumDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8336833880939099733L;
	
	private String id;
	
	private String declareName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date disclosureDate;
	
	private String meetingName;
	
	private String declareTypes;
	
	private String declareNo;
	
	private String declareStatus;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createTime;
	
	private Integer remainingDate;
	
	private String approvalFlag;
	
	private List<DeclareType> declareTypeList;
	
	private List<DeclareFileDto> declareFileList;

	private List<DisclosureDateDto> disclosureDateList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeclareName() {
		return declareName;
	}

	public void setDeclareName(String declareName) {
		this.declareName = declareName;
	}

	public Date getDisclosureDate() {
		return disclosureDate;
	}

	public void setDisclosureDate(Date disclosureDate) {
		this.disclosureDate = disclosureDate;
	}

	public List<DeclareType> getDeclareTypeList() {
		return declareTypeList;
	}

	public void setDeclareTypeList(List<DeclareType> declareTypeList) {
		this.declareTypeList = declareTypeList;
	}

	public List<DeclareFileDto> getDeclareFileList() {
		return declareFileList;
	}

	public void setDeclareFileList(List<DeclareFileDto> declareFileList) {
		this.declareFileList = declareFileList;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getDeclareTypes() {
		return declareTypes;
	}

	public void setDeclareTypes(String declareTypes) {
		this.declareTypes = declareTypes;
	}

	public String getDeclareNo() {
		return declareNo;
	}

	public void setDeclareNo(String declareNo) {
		this.declareNo = declareNo;
	}

	public String getDeclareStatus() {
		return declareStatus;
	}

	public void setDeclareStatus(String declareStatus) {
		this.declareStatus = declareStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getRemainingDate() {
		return remainingDate;
	}

	public void setRemainingDate(Integer remainingDate) {
		this.remainingDate = remainingDate;
	}

	public String getApprovalFlag() {
		return approvalFlag;
	}

	public void setApprovalFlag(String approvalFlag) {
		this.approvalFlag = approvalFlag;
	}

	public List<DisclosureDateDto> getDisclosureDateList() {
		return disclosureDateList;
	}

	public void setDisclosureDateList(List<DisclosureDateDto> disclosureDateList) {
		this.disclosureDateList = disclosureDateList;
	}
}
