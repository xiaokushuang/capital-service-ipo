package com.stock.capital.enterprise.declare.dto;

import java.io.Serializable;

public class DeclareToCloudAttDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6290874548189177639L;
	
	private String fileName;
	
	private String fileContent;
	
	private String fileType;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
}
