package com.stock.capital.enterprise.api.regulatory.dto;

import java.io.Serializable;

public class LabelRtnFileDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6244936899984730585L;
	/**
     * 回函附件Id
     */
    private String id;
    /**
     * 附件文件Id
     */
    private String fileId;
    /**
     * 附件文件名
     */
    private String fileName;
    /**
     * 附件url
     */
    private String fileUrl;
    
    public String getFileUrl() {
        return fileUrl;
    }
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFileId() {
        return fileId;
    }
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    
}

