package com.stock.capital.enterprise.declare.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MeetingDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1293074533389763446L;
	
	private String id;
	
	private String meetingName;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date startDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date endDate;
	
	private String meetingType;
	
	private String meetingStatus;
	
	private String meetingTypeName;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date conveneDate;
	
	private String conveneTime;
	
	private String convenePerson;
	
	private Map<String,String> meetingMap;
	
	private Map<String,String> meetingStatusMap;

	private  List<String> meetingIdList;
	
	private List<String> meetingFileList;
	
	private List<MeetingFileDto> MeetingFileDtoList;
	
	private String companyId;
	
	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getMeetingType() {
		return meetingType;
	}

	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}
	
	public String getMeetingStatus() {
		return meetingStatus;
	}

	public void setMeetingStatus(String meetingStatus) {
		this.meetingStatus = meetingStatus;
	}

	public String getMeetingTypeName() {
		return meetingTypeName;
	}

	public void setMeetingTypeName(String meetingTypeName) {
		this.meetingTypeName = meetingTypeName;
	}

	public Map<String, String> getMeetingMap() {
		return meetingMap;
	}

	public void setMeetingMap(Map<String, String> meetingMap) {
		this.meetingMap = meetingMap;
	}

	public Date getConveneDate() {
		return conveneDate;
	}

	public void setConveneDate(Date conveneDate) {
		this.conveneDate = conveneDate;
	}

	public String getConvenePerson() {
		return convenePerson;
	}

	public void setConvenePerson(String convenePerson) {
		this.convenePerson = convenePerson;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, String> getMeetingStatusMap() {
		return meetingStatusMap;
	}

	public void setMeetingStatusMap(Map<String, String> meetingStatusMap) {
		this.meetingStatusMap = meetingStatusMap;
	}

	public List<String> getMeetingIdList() {
		return meetingIdList;
	}

	public void setMeetingIdList(List<String> meetingIdList) {
		this.meetingIdList = meetingIdList;
	}

	public List<String> getMeetingFileList() {
		return meetingFileList;
	}

	public void setMeetingFileList(List<String> meetingFileList) {
		this.meetingFileList = meetingFileList;
	}

	public List<MeetingFileDto> getMeetingFileDtoList() {
		return MeetingFileDtoList;
	}

	public void setMeetingFileDtoList(List<MeetingFileDto> meetingFileDtoList) {
		MeetingFileDtoList = meetingFileDtoList;
	}

	public String getConveneTime() {
		return conveneTime;
	}

	public void setConveneTime(String conveneTime) {
		this.conveneTime = conveneTime;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

}
