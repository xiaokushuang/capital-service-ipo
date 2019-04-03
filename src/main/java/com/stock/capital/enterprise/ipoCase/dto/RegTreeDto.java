package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;
import java.util.List;

/**
 * RegTreeDto.
 *
 * @author chenzhengxiu.
 * @version 0.0.1.
 * @serial 2019/3/21 : base version.
 */
public class RegTreeDto implements Serializable{

    private static final long serialVersionUID = 5692612478317321741L;

    private String labelValue;

    private String labelName;

    private String name;

    private String num;

    private String id;

    private String pId;

    private List<RegTreeDto> children;

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public List<RegTreeDto> getChildren() {
        return children;
    }

    public void setChildren(List<RegTreeDto> children) {
        this.children = children;
    }
}
