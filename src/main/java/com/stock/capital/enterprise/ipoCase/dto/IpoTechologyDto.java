package com.stock.capital.enterprise.ipoCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class IpoTechologyDto {

  @ApiModelProperty("id")
  private String id;

  @ApiModelProperty("修改状态")
  private String modifyType;

  @ApiModelProperty("修改状态")
  private String editStatus;

  @ApiModelProperty("cid")
  private String bid;

  @ApiModelProperty("研发投入备注")
  private String devRemarks;

  @ApiModelProperty("项目时间第一列")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date projectDateFi;

  @ApiModelProperty("项目时间第二列")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date projectDateSe;

  @ApiModelProperty("项目时间第三列")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date projectDateTh;

  @ApiModelProperty("项目时间第四列")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date projectDateFo;

  @ApiModelProperty("研发投入第一列")
  private BigDecimal devInputFi;

  @ApiModelProperty("研发投入第二列")
  private BigDecimal devInputSe;

  @ApiModelProperty("研发投入第三列")
  private BigDecimal devInputTh;

  @ApiModelProperty("研发投入第四列")
  private BigDecimal devInputFo;

  @ApiModelProperty("营业收入第一列")
  private BigDecimal tradeFi;

  @ApiModelProperty("营业收入第二列")
  private BigDecimal tradeSe;

  @ApiModelProperty("营业收入第三列")
  private BigDecimal tradeTh;

  @ApiModelProperty("营业收入第四列")
  private BigDecimal tradeFo;

  @ApiModelProperty("专利情况备注")
  private String patentRemarks;

  @ApiModelProperty("国内发明专利")
  private Integer domesticInvention;

  @ApiModelProperty("国内实用新型专利")
  private Integer domesticNewtype;

  @ApiModelProperty("国内外观设计专利")
  private Integer domesticDesign;

  @ApiModelProperty("国外发明专利")
  private Integer foreignInvention;

  @ApiModelProperty("国外实用新型专利")
  private Integer foreignNewtype;

  @ApiModelProperty("国外外观设计专利")
  private Integer foreignDesign;

  @ApiModelProperty("核心技术及研发技术人员备注")
  private String coreRemarks;

  @ApiModelProperty("类别时间第一列")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date categoryDateFi;

  @ApiModelProperty("类别时间第二列")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date categoryDateSe;

  @ApiModelProperty("类别时间第三列")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date categoryDateTh;

  @ApiModelProperty("类别时间第四列")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date categoryDateFo;

  @ApiModelProperty("核心技术人员第一列")
  private Integer coreFi;

  @ApiModelProperty("核心技术人员第二列")
  private Integer coreSe;

  @ApiModelProperty("核心技术人员第三列")
  private Integer coreTh;

  @ApiModelProperty("核心技术人员第四列")
  private Integer coreFo;

  @ApiModelProperty("研发技术人员第一列")
  private Integer devFi;

  @ApiModelProperty("研发技术人员第二列")
  private Integer devSe;

  @ApiModelProperty("研发技术人员第三列")
  private Integer devTh;

  @ApiModelProperty("研发技术人员第四列")
  private Integer devFo;

  @ApiModelProperty("公司员工总数第一列")
  private Integer countFi;

  @ApiModelProperty("公司员工总数第二列")
  private Integer countSe;

  @ApiModelProperty("公司员工总数第三列")
  private Integer countTh;

  @ApiModelProperty("公司员工总数第四列")
  private Integer countFo;

  @ApiModelProperty("创建人")
  private String createUser;

  @ApiModelProperty("创建时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date createTime;

  @ApiModelProperty("更新人")
  private String updateUser;

  @ApiModelProperty("更新时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date updateTime;

  @ApiModelProperty("状态")
  private String status;

  private static final long serialVersionUID = 1L;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getModifyType() {
    return modifyType;
  }

  public void setModifyType(String modifyType) {
    this.modifyType = modifyType;
  }

  public String getEditStatus() {
    return editStatus;
  }

  public void setEditStatus(String editStatus) {
    this.editStatus = editStatus;
  }

  public String getBid() {
    return bid;
  }

  public void setBid(String bid) {
    this.bid = bid;
  }

  public String getDevRemarks() {
    return devRemarks;
  }

  public void setDevRemarks(String devRemarks) {
    this.devRemarks = devRemarks;
  }

  public Date getProjectDateFi() {
    return projectDateFi;
  }

  public void setProjectDateFi(Date projectDateFi) {
    this.projectDateFi = projectDateFi;
  }

  public Date getProjectDateSe() {
    return projectDateSe;
  }

  public void setProjectDateSe(Date projectDateSe) {
    this.projectDateSe = projectDateSe;
  }

  public Date getProjectDateTh() {
    return projectDateTh;
  }

  public void setProjectDateTh(Date projectDateTh) {
    this.projectDateTh = projectDateTh;
  }

  public Date getProjectDateFo() {
    return projectDateFo;
  }

  public void setProjectDateFo(Date projectDateFo) {
    this.projectDateFo = projectDateFo;
  }

  public BigDecimal getDevInputFi() {
    return devInputFi;
  }

  public void setDevInputFi(BigDecimal devInputFi) {
    this.devInputFi = devInputFi;
  }

  public BigDecimal getDevInputSe() {
    return devInputSe;
  }

  public void setDevInputSe(BigDecimal devInputSe) {
    this.devInputSe = devInputSe;
  }

  public BigDecimal getDevInputTh() {
    return devInputTh;
  }

  public void setDevInputTh(BigDecimal devInputTh) {
    this.devInputTh = devInputTh;
  }

  public BigDecimal getDevInputFo() {
    return devInputFo;
  }

  public void setDevInputFo(BigDecimal devInputFo) {
    this.devInputFo = devInputFo;
  }

  public BigDecimal getTradeFi() {
    return tradeFi;
  }

  public void setTradeFi(BigDecimal tradeFi) {
    this.tradeFi = tradeFi;
  }

  public BigDecimal getTradeSe() {
    return tradeSe;
  }

  public void setTradeSe(BigDecimal tradeSe) {
    this.tradeSe = tradeSe;
  }

  public BigDecimal getTradeTh() {
    return tradeTh;
  }

  public void setTradeTh(BigDecimal tradeTh) {
    this.tradeTh = tradeTh;
  }

  public BigDecimal getTradeFo() {
    return tradeFo;
  }

  public void setTradeFo(BigDecimal tradeFo) {
    this.tradeFo = tradeFo;
  }

  public String getPatentRemarks() {
    return patentRemarks;
  }

  public void setPatentRemarks(String patentRemarks) {
    this.patentRemarks = patentRemarks;
  }

  public Integer getDomesticInvention() {
    return domesticInvention;
  }

  public void setDomesticInvention(Integer domesticInvention) {
    this.domesticInvention = domesticInvention;
  }

  public Integer getDomesticNewtype() {
    return domesticNewtype;
  }

  public void setDomesticNewtype(Integer domesticNewtype) {
    this.domesticNewtype = domesticNewtype;
  }

  public Integer getDomesticDesign() {
    return domesticDesign;
  }

  public void setDomesticDesign(Integer domesticDesign) {
    this.domesticDesign = domesticDesign;
  }

  public Integer getForeignInvention() {
    return foreignInvention;
  }

  public void setForeignInvention(Integer foreignInvention) {
    this.foreignInvention = foreignInvention;
  }

  public Integer getForeignNewtype() {
    return foreignNewtype;
  }

  public void setForeignNewtype(Integer foreignNewtype) {
    this.foreignNewtype = foreignNewtype;
  }

  public Integer getForeignDesign() {
    return foreignDesign;
  }

  public void setForeignDesign(Integer foreignDesign) {
    this.foreignDesign = foreignDesign;
  }

  public String getCoreRemarks() {
    return coreRemarks;
  }

  public void setCoreRemarks(String coreRemarks) {
    this.coreRemarks = coreRemarks;
  }

  public Date getCategoryDateFi() {
    return categoryDateFi;
  }

  public void setCategoryDateFi(Date categoryDateFi) {
    this.categoryDateFi = categoryDateFi;
  }

  public Date getCategoryDateSe() {
    return categoryDateSe;
  }

  public void setCategoryDateSe(Date categoryDateSe) {
    this.categoryDateSe = categoryDateSe;
  }

  public Date getCategoryDateTh() {
    return categoryDateTh;
  }

  public void setCategoryDateTh(Date categoryDateTh) {
    this.categoryDateTh = categoryDateTh;
  }

  public Date getCategoryDateFo() {
    return categoryDateFo;
  }

  public void setCategoryDateFo(Date categoryDateFo) {
    this.categoryDateFo = categoryDateFo;
  }

  public Integer getCoreFi() {
    return coreFi;
  }

  public void setCoreFi(Integer coreFi) {
    this.coreFi = coreFi;
  }

  public Integer getCoreSe() {
    return coreSe;
  }

  public void setCoreSe(Integer coreSe) {
    this.coreSe = coreSe;
  }

  public Integer getCoreTh() {
    return coreTh;
  }

  public void setCoreTh(Integer coreTh) {
    this.coreTh = coreTh;
  }

  public Integer getCoreFo() {
    return coreFo;
  }

  public void setCoreFo(Integer coreFo) {
    this.coreFo = coreFo;
  }

  public Integer getDevFi() {
    return devFi;
  }

  public void setDevFi(Integer devFi) {
    this.devFi = devFi;
  }

  public Integer getDevSe() {
    return devSe;
  }

  public void setDevSe(Integer devSe) {
    this.devSe = devSe;
  }

  public Integer getDevTh() {
    return devTh;
  }

  public void setDevTh(Integer devTh) {
    this.devTh = devTh;
  }

  public Integer getDevFo() {
    return devFo;
  }

  public void setDevFo(Integer devFo) {
    this.devFo = devFo;
  }

  public Integer getCountFi() {
    return countFi;
  }

  public void setCountFi(Integer countFi) {
    this.countFi = countFi;
  }

  public Integer getCountSe() {
    return countSe;
  }

  public void setCountSe(Integer countSe) {
    this.countSe = countSe;
  }

  public Integer getCountTh() {
    return countTh;
  }

  public void setCountTh(Integer countTh) {
    this.countTh = countTh;
  }

  public Integer getCountFo() {
    return countFo;
  }

  public void setCountFo(Integer countFo) {
    this.countFo = countFo;
  }

  public String getCreateUser() {
    return createUser;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getUpdateUser() {
    return updateUser;
  }

  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
