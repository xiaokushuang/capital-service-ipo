package com.stock.capital.enterprise.ipoCoachCase.dto;

import java.io.Serializable;

public class IpoRelatedCaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String caseid;
    private String processTime;
    private String iecResult;
    private String iecResultText;
    private String proSort;
    private String openFlag;
    private String caseTitle;
    private String companyZhName;

    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }

    public String getIecResult() {
        return iecResult;
    }

    public void setIecResult(String iecResult) {
        this.iecResult = iecResult;
    }

    public String getProSort() {
        return proSort;
    }

    public void setProSort(String proSort) {
        this.proSort = proSort;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getCompanyZhName() {
        return companyZhName;
    }

    public void setCompanyZhName(String companyZhName) {
        this.companyZhName = companyZhName;
    }

    public String getIecResultText() {
        return iecResultText;
    }

    public void setIecResultText(String iecResultText) {
        this.iecResultText = iecResultText;
    }

    public String getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(String openFlag) {
        this.openFlag = openFlag;
    }
}
