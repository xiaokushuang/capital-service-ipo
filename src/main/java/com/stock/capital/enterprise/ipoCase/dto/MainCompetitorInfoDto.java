package com.stock.capital.enterprise.ipoCase.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * MainCompetitorInfoDto.
 *
 * @author user.
 * @version 0.0.1.
 * @serial 2019/1/30 : base version.
 */
public class MainCompetitorInfoDto implements Serializable {

    private static final long serialVersionUID = 8222081268927381852L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("案例id")
    private String bid;

    @ApiModelProperty("公司代码")
    private String companyCode;

    @ApiModelProperty("证券简称")
    private String companyName;

    @ApiModelProperty("简要情况")
    private String situation;

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

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }
}
