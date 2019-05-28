package com.stock.capital.enterprise.ipoCase.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
@ApiModel("IPO拆分文件类")
public class IpoFileDto implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty("文件id")
  private String splitFileId;

  @ApiModelProperty("文件名字")
  private String splitFileName;

  @ApiModelProperty("文件路径")
  private String filePath;

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

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
}
