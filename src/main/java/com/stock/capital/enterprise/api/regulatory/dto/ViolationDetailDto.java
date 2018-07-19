package com.stock.capital.enterprise.api.regulatory.dto;

import java.io.Serializable;
import java.util.List;

public class ViolationDetailDto implements Serializable {

    private static final long serialVersionUID = -5283286219691106934L;

    private String id;

    private String title;

    private String companyCode;

    private String companyName;

    private String belongTrade;

    private String violateTypeVO;

    private String punishOrg;

    private String liveNode;

    private String liveNodeLabel;

    private String adoptCondition;
    //lanyuxin 2017-08-14 begin
    private String contentNohtml;
    
    public String getContentNohtml() {
		return contentNohtml;
	}

	public void setContentNohtml(String contentNohtml) {
		this.contentNohtml = contentNohtml;
	}
   
 
	//lanyuxin 2017-08-14 end

 

	

	private List<ViolationDetailNodeDto> processNodeList;

    private List<ViolationDetailPersonDto> personList;

    private List<ViolationDetailLinkDto> lawsList;

    private List<ViolationDetailLinkDto> casesList;

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

    public String getBelongTrade() {
        return belongTrade;
    }

    public void setBelongTrade(String belongTrade) {
        this.belongTrade = belongTrade;
    }

    public String getViolateTypeVO() {
        return violateTypeVO;
    }

    public void setViolateTypeVO(String violateTypeVO) {
        this.violateTypeVO = violateTypeVO;
    }

    public String getPunishOrg() {
        return punishOrg;
    }

    public void setPunishOrg(String punishOrg) {
        this.punishOrg = punishOrg;
    }

    public String getLiveNode() {
        return liveNode;
    }

    public void setLiveNode(String liveNode) {
        this.liveNode = liveNode;
    }

    public String getAdoptCondition() {
        return adoptCondition;
    }

    public void setAdoptCondition(String adoptCondition) {
        this.adoptCondition = adoptCondition;
    }

    public List<ViolationDetailNodeDto> getProcessNodeList() {
        return processNodeList;
    }

    public void setProcessNodeList(List<ViolationDetailNodeDto> processNodeList) {
        this.processNodeList = processNodeList;
    }

    public List<ViolationDetailPersonDto> getPersonList() {
        return personList;
    }

    public void setPersonList(List<ViolationDetailPersonDto> personList) {
        this.personList = personList;
    }

    public List<ViolationDetailLinkDto> getLawsList() {
        return lawsList;
    }

    public void setLawsList(List<ViolationDetailLinkDto> lawsList) {
        this.lawsList = lawsList;
    }

    public List<ViolationDetailLinkDto> getCasesList() {
        return casesList;
    }

    public void setCasesList(List<ViolationDetailLinkDto> casesList) {
        this.casesList = casesList;
    }

    public String getLiveNodeLabel() {
        return liveNodeLabel;
    }

    public void setLiveNodeLabel(String liveNodeLabel) {
        this.liveNodeLabel = liveNodeLabel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
