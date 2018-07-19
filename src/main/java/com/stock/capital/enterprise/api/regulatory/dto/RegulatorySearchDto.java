package com.stock.capital.enterprise.api.regulatory.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stock.core.annotation.FieldAnnotation;
import com.stock.core.dto.Indexable;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class RegulatorySearchDto extends Indexable implements Serializable {

	private static final long serialVersionUID = -5283286219691106934L;

	@FieldAnnotation(name="监管信息主键ID",value = "regulatory_regulatory_id_t")
	@Field("regulatory_regulatory_id_t")
	private String regulatoryId;

	@FieldAnnotation(name="信息标题",value = "regulatory_title_t")
	@Field("regulatory_title_t")
	private String infoTitle;

	@FieldAnnotation(name = "信息内容",value = "regulatory_content_t")
	@Field("regulatory_content_t")
	private String infoContent;

	@FieldAnnotation(name = "信息来源",value = "regulatory_source_t")
	@Field("regulatory_source_t")
	private String infoSource;

	@FieldAnnotation(name = "是否发布内码",value = "regulatory_publish_flag_t")
	@Field("regulatory_publish_flag_t")
	private String publishFlag;

	@FieldAnnotation(name="是否正文为附件内码",value = "regulatory_file_flag_t")
	@Field("regulatory_file_flag_t")
	private String fileFlag;

	@FieldAnnotation(name="附件链接",value = "regulatory_url_t")
	@Field("regulatory_url_t")
	private String url;

	@FieldAnnotation(name = "发布日期",value = "regulatory_release_time_dt")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Field("regulatory_release_time_dt")
	private Date releaseTime;

	public String getRegulatoryId() {
		return regulatoryId;
	}

	public void setRegulatoryId(String regulatoryId) {
		this.regulatoryId = regulatoryId;
	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}

	public String getInfoContent() {
		return infoContent;
	}

	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}

	public String getInfoSource() {
		return infoSource;
	}

	public void setInfoSource(String infoSource) {
		this.infoSource = infoSource;
	}

	public String getPublishFlag() {
		return publishFlag;
	}

	public void setPublishFlag(String publishFlag) {
		this.publishFlag = publishFlag;
	}

	public String getFileFlag() {
		return fileFlag;
	}

	public void setFileFlag(String fileFlag) {
		this.fileFlag = fileFlag;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
