package com.stock.capital.enterprise.declare.dto;

import java.io.Serializable;

public class DeclareTypeTreeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2847652138790896057L;

	 private String id;

    private String parentId;

    private Integer level;

    private String declareTypeName;

    private String declareTypeNo;
    
    private String validFlag;

    private Integer oftenFlag;
    
    private String showTypeName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getDeclareTypeName() {
		return declareTypeName;
	}

	public void setDeclareTypeName(String declareTypeName) {
		this.declareTypeName = declareTypeName;
	}

	public String getDeclareTypeNo() {
		return declareTypeNo;
	}

	public void setDeclareTypeNo(String declareTypeNo) {
		this.declareTypeNo = declareTypeNo;
	}

	public String getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public Integer getOftenFlag() {
		return oftenFlag;
	}

	public void setOftenFlag(Integer oftenFlag) {
		this.oftenFlag = oftenFlag;
	}

	public String getShowTypeName() {
		return showTypeName;
	}

	public void setShowTypeName(String showTypeName) {
		this.showTypeName = showTypeName;
	}
    
}
