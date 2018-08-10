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
    
    private String weekStopYet;//最近一周 终止审查
    
    private String stopYet;//终止审查
    
    /**
     * 行业
     */
    private String belongTrade;
    /**
     * 拟上市地
     */
    private String quasiListedLand;
    /**
     * 公司名称
     */
    private String appCompany;
    /**
     * 会计事务所名称
     */
    private String accountantOffice;
    /**
     * 律师事务所名称
     */
    private String lawFirm;
    /**
     * 审核状态
     */
    private String approveStatus;
    /**
     * 是否参加抽签
     */
    private String hasedRandomInspection;
    /**
     * 保荐机构
     */
    private String recommendOrganization;
    
    
    public String getRecommendOrganization() {
		return recommendOrganization;
	}

	public void setRecommendOrganization(String recommendOrganization) {
		this.recommendOrganization = recommendOrganization;
	}

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

    public String getWeekStopYet() {
        return weekStopYet;
    }

    public void setWeekStopYet(String weekStopYet) {
        this.weekStopYet = weekStopYet;
    }

    public String getStopYet() {
        return stopYet;
    }

    public void setStopYet(String stopYet) {
        this.stopYet = stopYet;
    }

    public String getBelongTrade() {
        return belongTrade;
    }

    public void setBelongTrade(String belongTrade) {
        this.belongTrade = belongTrade;
    }
    
    public String getQuasiListedLand() {
		return quasiListedLand;
	}

	public void setQuasiListedLand(String quasiListedLand) {
		this.quasiListedLand = quasiListedLand;
	}

	public String getAppCompany() {
		return appCompany;
	}

	public void setAppCompany(String appCompany) {
		this.appCompany = appCompany;
	}

	public String getAccountantOffice() {
		return accountantOffice;
	}

	public void setAccountantOffice(String accountantOffice) {
		this.accountantOffice = accountantOffice;
	}

	public String getLawFirm() {
		return lawFirm;
	}

	public void setLawFirm(String lawFirm) {
		this.lawFirm = lawFirm;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getHasedRandomInspection() {
		return hasedRandomInspection;
	}

	public void setHasedRandomInspection(String hasedRandomInspection) {
		this.hasedRandomInspection = hasedRandomInspection;
	}
}
