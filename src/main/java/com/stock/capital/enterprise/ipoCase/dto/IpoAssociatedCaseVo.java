package com.stock.capital.enterprise.ipoCase.dto;

public class IpoAssociatedCaseVo {

  private static final long serialVersionUID = 1L;

  public String proSort;// 序号

  public String caseId;// 案例id

  public String progressName;//标题

//  public String orgCode;// 东财内码

  public String iecResult;//案例审核结果

  public String processTime;//案例日期

//  public String releaseFlag;//是否发布

  public String openFlag;// 是否开放

  public String getProSort() {
    return proSort;
  }

  public void setProSort(String proSort) {
    this.proSort = proSort;
  }

  public String getCaseId() {
    return caseId;
  }

  public void setCaseId(String caseId) {
    this.caseId = caseId;
  }

  public String getProgressName() {
    return progressName;
  }

  public void setProgressName(String progressName) {
    this.progressName = progressName;
  }

  public String getIecResult() {
    return iecResult;
  }

  public void setIecResult(String iecResult) {
    this.iecResult = iecResult;
  }

  public String getProcessTime() {
    return processTime;
  }

  public void setProcessTime(String processTime) {
    this.processTime = processTime;
  }

  public String getOpenFlag() {
    return openFlag;
  }

  public void setOpenFlag(String openFlag) {
    this.openFlag = openFlag;
  }
}
