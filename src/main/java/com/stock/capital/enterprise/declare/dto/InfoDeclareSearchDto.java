package com.stock.capital.enterprise.declare.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stock.core.dto.Indexable;

public class InfoDeclareSearchDto extends Indexable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5337281213631750006L;

	@Field("declare_code_t")
	private String code;

	@Field("declare_title_t")
	private String title;

	@Field("declare_content_t")
	private String content;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Field("declare_publish_date_dt")
	private Date publishDate;
	
	@Field("declare_infoSource_t")
	private String infoSource;
	
	@Field("declare_infoUrl_t")
	private String infoUrl;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startPublishDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endPublishDate;
    
    private String comType;
	
	public String getInfoUrl() {
		return infoUrl;
	}

	public void setInfoUrl(String infoUrl) {
		this.infoUrl = infoUrl;
	}

	public String getInfoSource() {
		return infoSource;
	}

	public void setInfoSource(String infoSource) {
		this.infoSource = infoSource;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getStartPublishDate() {
		return startPublishDate;
	}

	public void setStartPublishDate(Date startPublishDate) {
		this.startPublishDate = startPublishDate;
	}

	public Date getEndPublishDate() {
		return endPublishDate;
	}

	public void setEndPublishDate(Date endPublishDate) {
		this.endPublishDate = endPublishDate;
	}

	public String getComType() {
		return comType;
	}

	public void setComType(String comType) {
		this.comType = comType;
	}

}
