package com.stock.capital.enterprise.lawLibrary.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stock.core.dto.Indexable;

public class SearchDto extends Indexable implements Serializable {

    private static final long serialVersionUID = -5283286219691106934L;

    private String title;

    private String content;
    
    private String keyword;

    private String lawType;
    
    private List<String> lawTypeSearch;

    private List<String> lawSourceSearch;
    
    private String lawSource;
    
    private String typeSort;
    
    private String declareType;

    private List<String> declareTypeList;
    
    private String hintInfo;
    
    private String lawSign;
    
    private String lawGrade;

    private List<String> lawsScopes;

    private String lawShowFlag;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date publishDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lawInvalidDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lawModifyDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startPublishDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endPublishDate;

    private Date lawEffectDate;
    
    private List<String> lawsTypeValList;

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

    public Date getLawInvalidDate() {
		return lawInvalidDate;
	}

	public void setLawInvalidDate(Date lawInvalidDate) {
		this.lawInvalidDate = lawInvalidDate;
	}

	public Date getLawModifyDate() {
		return lawModifyDate;
	}

	public void setLawModifyDate(Date lawModifyDate) {
		this.lawModifyDate = lawModifyDate;
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

    public String getLawType() {
        return lawType;
    }

    public void setLawType(String lawType) {
        this.lawType = lawType;
    }

	public String getLawSource() {
		return lawSource;
	}

	public void setLawSource(String lawSource) {
		this.lawSource = lawSource;
	}

	public String getLawSign() {
		return lawSign;
	}

	public void setLawSign(String lawSign) {
		this.lawSign = lawSign;
	}

	public String getLawGrade() {
		return lawGrade;
	}

	public void setLawGrade(String lawGrade) {
		this.lawGrade = lawGrade;
	}

	public String getDeclareType() {
		return declareType;
	}

	public void setDeclareType(String declareType) {
		this.declareType = declareType;
	}

	public String getHintInfo() {
		return hintInfo;
	}

	public void setHintInfo(String hintInfo) {
		this.hintInfo = hintInfo;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<String> getLawsTypeValList() {
		return lawsTypeValList;
	}

	public void setLawsTypeValList(List<String> lawsTypeValList) {
		this.lawsTypeValList = lawsTypeValList;
	}

    public List<String> getLawsScopes() {
        return lawsScopes;
    }

    public void setLawsScopes(List<String> lawsScopes) {
        this.lawsScopes = lawsScopes;
    }

    public String getLawShowFlag() {
        return lawShowFlag;
    }

    public void setLawShowFlag(String lawShowFlag) {
        this.lawShowFlag = lawShowFlag;
    }

	public List<String> getLawTypeSearch() {
		return lawTypeSearch;
	}

	public void setLawTypeSearch(List<String> lawTypeSearch) {
		this.lawTypeSearch = lawTypeSearch;
	}

	public List<String> getLawSourceSearch() {
		return lawSourceSearch;
	}

	public void setLawSourceSearch(List<String> lawSourceSearch) {
		this.lawSourceSearch = lawSourceSearch;
	}

	public List<String> getDeclareTypeList() {
		return declareTypeList;
	}

	public void setDeclareTypeList(List<String> declareTypeList) {
		this.declareTypeList = declareTypeList;
	}

	public String getTypeSort() {
		return typeSort;
	}

	public void setTypeSort(String typeSort) {
		this.typeSort = typeSort;
	}

}
