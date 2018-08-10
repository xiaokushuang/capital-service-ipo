package com.stock.capital.enterprise.declare.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat; 
import com.stock.capital.enterprise.common.entity.LawXmlStructureDetail;


public class DecalreRelationLawDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3377749583158674535L;
	
	private String lawId;
	
	private String lawName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date published;
	
	private String sourceDepartment;
	
	private String lawInvalid;
	
	private String lawStatus;
	
	private Integer count;
	
	private List<LawXmlStructureDetail> lawItemList;
	
	private String nodeId;
	
	private String lawGrade;
	
	private String typeName;
	
	private String typeId;
	
	

	public String getLawGrade() {
		return lawGrade;
	}

	public void setLawGrade(String lawGrade) {
		this.lawGrade = lawGrade;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getLawId() {
		return lawId;
	}

	public void setLawId(String lawId) {
		this.lawId = lawId;
	}

	public String getLawName() {
		return lawName;
	}

	public void setLawName(String lawName) {
		this.lawName = lawName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getSourceDepartment() {
		return sourceDepartment;
	}

	public void setSourceDepartment(String sourceDepartment) {
		this.sourceDepartment = sourceDepartment;
	}

	public String getLawInvalid() {
		return lawInvalid;
	}

	public void setLawInvalid(String lawInvalid) {
		this.lawInvalid = lawInvalid;
	}

	public String getLawStatus() {
		return lawStatus;
	}

	public void setLawStatus(String lawStatus) {
		this.lawStatus = lawStatus;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public List<LawXmlStructureDetail> getLawItemList() {
		return lawItemList;
	}

	public void setLawItemList(List<LawXmlStructureDetail> lawItemList) {
		this.lawItemList = lawItemList;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

}
