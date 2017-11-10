package com.stock.capital.enterprise.regulatory.dto;

import java.io.Serializable;

public class StatisticsResultDto implements Serializable {

    private static final long serialVersionUID = -5285486619691106934L;
    /**
     * 值
     */
    private String value;
    /**
     * 键
     */
    private String label;
    /**
     * 深主板
     */
    private int szbCount;
    /**
     * 沪主板
     */
    private int hzbCount;
    /**
     * 中小板
     */
    private int zxbCount;
    /**
     * 创业板
     */
    private int cybCount;
    /**
     * 合计总数
     */
    private int totalCount;
    /**
     * 比例
     */
    private double percent;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getSzbCount() {
        return szbCount;
    }

    public void setSzbCount(int szbCount) {
        this.szbCount = szbCount;
    }

    public int getHzbCount() {
        return hzbCount;
    }

    public void setHzbCount(int hzbCount) {
        this.hzbCount = hzbCount;
    }

    public int getZxbCount() {
        return zxbCount;
    }

    public void setZxbCount(int zxbCount) {
        this.zxbCount = zxbCount;
    }

    public int getCybCount() {
        return cybCount;
    }

    public void setCybCount(int cybCount) {
        this.cybCount = cybCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

}
