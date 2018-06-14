package com.stock.capital.enterprise.regulatory.dto;

import java.io.Serializable;

public class ViolationFavourDto implements Serializable {

    private static final long serialVersionUID = -5283286219691106934L;

    private String id;

    private String companyCode;

    private String companyName;

    private String title;

    private String titleHL;

    private String punishOrg;

    private String violatePerson;

    private String punishTypeLabel;

    private String releaseTime;

    private String favourTime;
    
  //lanyuxin 2017-08-01 begin  
    private String content;

    public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
   //lanyuxin 2017-08-01 end

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleHL() {
        return titleHL;
    }

    public void setTitleHL(String titleHL) {
        this.titleHL = titleHL;
    }

    public String getPunishOrg() {
        return punishOrg;
    }

    public void setPunishOrg(String punishOrg) {
        this.punishOrg = punishOrg;
    }

    public String getViolatePerson() {
        return violatePerson;
    }

    public void setViolatePerson(String violatePerson) {
        this.violatePerson = violatePerson;
    }

    public String getPunishTypeLabel() {
        return punishTypeLabel;
    }

    public void setPunishTypeLabel(String punishTypeLabel) {
        this.punishTypeLabel = punishTypeLabel;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getFavourTime() {
        return favourTime;
    }

    public void setFavourTime(String favourTime) {
        this.favourTime = favourTime;
    }

}
