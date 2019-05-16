package com.stock.capital.enterprise.ipoCase.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * IssuerIndustryStatusDto.
 *
 * @author shihanwen.
 * @version 0.0.1.
 * @serial 2019/5/14 : base version.
 */

public class IssuerIndustryStatusDto implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty("主键")
  private String id;

  @ApiModelProperty("案例id")
  private String bid;

  @ApiModelProperty("排名范围")
  private String rankingRange;

  @ApiModelProperty("排名指标")
  private String rankingIndicator;

  @ApiModelProperty("排名")
  private String ranking;
  
  @ApiModelProperty("概述")
  private String industryStatusOverview;
  

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
    
    public String getRankingRange() {
        return rankingRange;
    }
    
    public void setRankingRange(String rankingRange) {
        this.rankingRange = rankingRange;
    }
    
    public String getRankingIndicator() {
        return rankingIndicator;
    }
    
    public void setRankingIndicator(String rankingIndicator) {
        this.rankingIndicator = rankingIndicator;
    }
    
    public String getRanking() {
        return ranking;
    }
    
    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

  public String getIndustryStatusOverview() {
    return industryStatusOverview;
  }

  public void setIndustryStatusOverview(String industryStatusOverview) {
    this.industryStatusOverview = industryStatusOverview;
  }
}
