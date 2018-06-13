package com.stock.capital.enterprise.declare.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DeclareToCloudDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8872687643668546510L;
	
	private String declareId;
	
	private String declareName;
	
	private String declareTypes;
	
	private Date disclosureDate;
	
	private String companyCode;
	
	private String companyName;
	
	private List<DeclareToCloudAttDto> decalreAttList;

	public String getDeclareId() {
		return declareId;
	}

	public void setDeclareId(String declareId) {
		this.declareId = declareId;
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

	public List<DeclareToCloudAttDto> getDecalreAttList() {
		return decalreAttList;
	}

	public void setDecalreAttList(List<DeclareToCloudAttDto> decalreAttList) {
		this.decalreAttList = decalreAttList;
	}

	public String getDeclareTypes() {
		return declareTypes;
	}

	public void setDeclareTypes(String declareTypes) {
		this.declareTypes = declareTypes;
	}

}
