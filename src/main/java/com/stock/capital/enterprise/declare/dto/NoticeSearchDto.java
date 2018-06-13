package com.stock.capital.enterprise.declare.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.stock.core.dto.TreeDto;

import org.springframework.format.annotation.DateTimeFormat;

import com.stock.core.dto.OptionDto;

public class NoticeSearchDto implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 3389546457665861218L;

    private String title;

    private String stockcode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private List<String> boardSelect;

    private List<String> typeSelect;

    private List<TreeDto> boardSelectMul;

    private List<TreeDto> typeSelectMul;
    //lanyuxin 2017-08-17 begin;
    private List<String> areasSelect;
    
    private String areasSelectShow;
    
    private String industrySelectShow;
    //xing.j 2017/12/04 start bug7045
    private String typeSelectShow;
    
    private String boardSelectShow;
    
	public String getBoardSelectShow() {
		return boardSelectShow;
	}

	public void setBoardSelectShow(String boardSelectShow) {
		this.boardSelectShow = boardSelectShow;
	}

	public String getTypeSelectShow() {
		return typeSelectShow;
	}

	public void setTypeSelectShow(String typeSelectShow) {
		this.typeSelectShow = typeSelectShow;
	}
	//xing.j 2017/12/04 end bug7045

	public String getIndustrySelectShow() {
		return industrySelectShow;
	}

	public void setIndustrySelectShow(String industrySelectShow) {
		this.industrySelectShow = industrySelectShow;
	}

	public List<String> getAreasSelect() {
		return areasSelect;
	}

	public void setAreasSelect(List<String> areasSelect) {
		this.areasSelect = areasSelect;
	}

    public String getAreasSelectShow() {
		return areasSelectShow;
	}

	public void setAreasSelectShow(String areasSelectShow) {
		this.areasSelectShow = areasSelectShow;
	}
	//lanyuxin 2017-08-17 end;
	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode;
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

    public List<String> getBoardSelect() {
        return boardSelect;
    }

    public void setBoardSelect(List<String> boardSelect) {
        this.boardSelect = boardSelect;
    }

    public List<String> getTypeSelect() {
        return typeSelect;
    }

    public void setTypeSelect(List<String> typeSelect) {
        this.typeSelect = typeSelect;
    }

    public List<TreeDto> getBoardSelectMul() {
        return boardSelectMul;
    }

    public void setBoardSelectMul(List<TreeDto> boardSelectMul) {
        this.boardSelectMul = boardSelectMul;
    }

    public List<TreeDto> getTypeSelectMul() {
        return typeSelectMul;
    }

    public void setTypeSelectMul(List<TreeDto> typeSelectMul) {
        this.typeSelectMul = typeSelectMul;
    }
    
    //xing.j add 2017/12/03 start 
    private String dateRange;

	public String getDateRange() {
		return dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}
	//xing.j add 2017/12/03 start
    
}