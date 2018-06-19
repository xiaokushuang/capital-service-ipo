package com.stock.capital.enterprise.common.entity;

import java.io.Serializable;

public class LawsManageWithBLOBs extends LawsManage implements Serializable {
    private String lawsOutline;

    private String lawSrcUrl;

    private String lawMarkdownText;

    private String lawGuide;

    private static final long serialVersionUID = 1L;

    public String getLawsOutline() {
        return lawsOutline;
    }

    public void setLawsOutline(String lawsOutline) {
        this.lawsOutline = lawsOutline == null ? null : lawsOutline.trim();
    }

    public String getLawSrcUrl() {
        return lawSrcUrl;
    }

    public void setLawSrcUrl(String lawSrcUrl) {
        this.lawSrcUrl = lawSrcUrl == null ? null : lawSrcUrl.trim();
    }

    public String getLawMarkdownText() {
        return lawMarkdownText;
    }

    public void setLawMarkdownText(String lawMarkdownText) {
        this.lawMarkdownText = lawMarkdownText == null ? null : lawMarkdownText.trim();
    }

    public String getLawGuide() {
        return lawGuide;
    }

    public void setLawGuide(String lawGuide) {
        this.lawGuide = lawGuide == null ? null : lawGuide.trim();
    }
}