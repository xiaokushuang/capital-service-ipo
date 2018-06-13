package com.stock.capital.enterprise.declare.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DeclareIndexDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2244597587923197151L;
	
	//公司所属板块
	private String stockBoard;
	
    //公司公告查询
    private String content;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startPublishDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endPublishDate;
	
	//信披历史查询
    private String deContent;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date deStartPublishDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date deEndPublishDate;

	
	public String getStockBoard() {
		return stockBoard;
	}

	public void setStockBoard(String stockBoard) {
		this.stockBoard = stockBoard;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getDeContent() {
		return deContent;
	}

	public void setDeContent(String deContent) {
		this.deContent = deContent;
	}

	public Date getDeStartPublishDate() {
		return deStartPublishDate;
	}

	public void setDeStartPublishDate(Date deStartPublishDate) {
		this.deStartPublishDate = deStartPublishDate;
	}

	public Date getDeEndPublishDate() {
		return deEndPublishDate;
	}

	public void setDeEndPublishDate(Date deEndPublishDate) {
		this.deEndPublishDate = deEndPublishDate;
	}
	
	
}
