package com.stock.capital.enterprise.lawLibrary.dto;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/12 0012.
 */
public class InvalidLawsDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String lawEffectDate;

    private String lawSource;

    private String lawSign;

    private String lawsName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLawEffectDate() {
        return lawEffectDate;
    }

    public void setLawEffectDate(String lawEffectDate) {
        this.lawEffectDate = lawEffectDate;
    }

    public String getLawSource() {
        return lawSource;
    }

    public void setLawSource(String lawSource) {
        this.lawSource = lawSource;
    }

    public String getLawSign() {
        return lawSign;
    }

    public void setLawSign(String lawSign) {
        this.lawSign = lawSign;
    }

    public String getLawsName() {
        return lawsName;
    }

    public void setLawsName(String lawsName) {
        this.lawsName = lawsName;
    }
}
