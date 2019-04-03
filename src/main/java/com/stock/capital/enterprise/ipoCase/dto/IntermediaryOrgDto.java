package com.stock.capital.enterprise.ipoCase.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class IntermediaryOrgDto implements Serializable {

    private static final long serialVersionUID = -6980788804392682330L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("案例id")
    private String bid;

    @ApiModelProperty("中介类型")
    private String intermediaryType; //中介类型 1:保荐机构 2:证券公司 3:律师事务所 4:会计事务所 5:资产评估机构

    @ApiModelProperty("机构类型")
    private String orgType; //机构类型

    @ApiModelProperty("机构名称")
    private String orgName; //机构名称

    @ApiModelProperty("机构代码")
    private String orgCode; //机构代码

    @ApiModelProperty("状态")
    private String validFlag; //状态 0:失效 1:生效

    @ApiModelProperty("代表人")
    private String representPerson; //代表人

    @ApiModelProperty("经办人")
    private String agentPerson; //经办人

    @ApiModelProperty("协办人")
    private String assistPerson; //协办人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getIntermediaryType() {
        return intermediaryType;
    }

    public void setIntermediaryType(String intermediaryType) {
        this.intermediaryType = intermediaryType;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getRepresentPerson() {
        return representPerson;
    }

    public void setRepresentPerson(String representPerson) {
        this.representPerson = representPerson;
    }

    public String getAgentPerson() {
        return agentPerson;
    }

    public void setAgentPerson(String agentPerson) {
        this.agentPerson = agentPerson;
    }

    public String getAssistPerson() {
        return assistPerson;
    }

    public void setAssistPerson(String assistPerson) {
        this.assistPerson = assistPerson;
    }
}
