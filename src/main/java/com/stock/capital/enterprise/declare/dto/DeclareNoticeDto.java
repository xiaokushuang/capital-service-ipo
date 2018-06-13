package com.stock.capital.enterprise.declare.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DeclareNoticeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1464166566297279180L;
	
	private String fileTitle;
	
	private String id;
	
	private String fileName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date disclosureDate;

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDisclosureDate() {
		return disclosureDate;
	}

	public void setDisclosureDate(Date disclosureDate) {
		this.disclosureDate = disclosureDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
