package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;
import java.util.List;

/**
 *  省市区标签 dto
 */
public class RepTreeTagDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String labelValue;

    private String labelName;

    private String name;

    private String num;

    private String id;

    private String pId;

    private List<RepTreeTagDto> children;

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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<RepTreeTagDto> getChildren() {
        return children;
    }

    public void setChildren(List<RepTreeTagDto> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
