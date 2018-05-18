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
    /**
     * 注册地
     */
    private String registAddr;
    
    /**
     * 已受理
     */
    private double applied;
    /**
     * 已反馈
     */
    private double reviewed;
    
    /**
     * 预先披露更新
     */
    private double preUpdate;
    /**
     * 中止审查
     */
    private double endYet;
    
    /**
     * 已上发审会，暂缓表决
     */
    private double processing;
    /**
     * 已通过发审会
     */
    private double passed;
    
    /**
     * 合计
     */
    private double areaCount;
    /**
     * 数据截止时间
     */
    private String lastUpadteTime;

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

    public String getRegistAddr() {
        return registAddr;
    }

    public void setRegistAddr(String registAddr) {
        this.registAddr = registAddr;
    }

    public double getApplied() {
        return applied;
    }

    public void setApplied(double applied) {
        this.applied = applied;
    }

    public double getReviewed() {
        return reviewed;
    }

    public void setReviewed(double reviewed) {
        this.reviewed = reviewed;
    }

    public double getPreUpdate() {
        return preUpdate;
    }

    public void setPreUpdate(double preUpdate) {
        this.preUpdate = preUpdate;
    }

    public double getEndYet() {
        return endYet;
    }

    public void setEndYet(double endYet) {
        this.endYet = endYet;
    }

    public double getProcessing() {
        return processing;
    }

    public void setProcessing(double processing) {
        this.processing = processing;
    }

    public double getPassed() {
        return passed;
    }

    public void setPassed(double passed) {
        this.passed = passed;
    }

    public double getAreaCount() {
        return areaCount;
    }

    public void setAreaCount(double areaCount) {
        this.areaCount = areaCount;
    }

    public String getLastUpadteTime() {
        return lastUpadteTime;
    }

    public void setLastUpadteTime(String lastUpadteTime) {
        this.lastUpadteTime = lastUpadteTime;
    }

}
