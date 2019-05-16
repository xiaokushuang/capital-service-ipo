package com.stock.capital.enterprise.ipoCase.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("IPO函件为题标签类")
public class IpoSplitDto implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty("拆分详情")
  private String splitMemo;

  @ApiModelProperty("文件id")
  private String splitFileId;

  @ApiModelProperty("文件名字")
  private String splitFileName;

  @ApiModelProperty("所在资本市场")
  private String splitMarket;

  @ApiModelProperty("上市公司")
  private String companyName;

  @ApiModelProperty("直接或间接持有发行人股份比例（%）")
  private BigDecimal shareProportion;

  public String getSplitMemo() {
    return splitMemo;
  }

  public void setSplitMemo(String splitMemo) {
    this.splitMemo = splitMemo;
  }

  public String getSplitFileId() {
    return splitFileId;
  }

  public void setSplitFileId(String splitFileId) {
    this.splitFileId = splitFileId;
  }

  public String getSplitFileName() {
    return splitFileName;
  }

  public void setSplitFileName(String splitFileName) {
    this.splitFileName = splitFileName;
  }

  public String getSplitMarket() {
    return splitMarket;
  }

  public void setSplitMarket(String splitMarket) {
    this.splitMarket = splitMarket;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public BigDecimal getShareProportion() {
    if (shareProportion == null){
      return shareProportion;
    }
    return shareProportion.stripTrailingZeros();
  }

  public void setShareProportion(BigDecimal shareProportion) {
    this.shareProportion = shareProportion;
  }
}
