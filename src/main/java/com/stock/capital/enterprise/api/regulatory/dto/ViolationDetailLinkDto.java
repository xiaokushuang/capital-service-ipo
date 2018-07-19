package com.stock.capital.enterprise.api.regulatory.dto;

import java.io.Serializable;

public class ViolationDetailLinkDto implements Serializable {

    private static final long serialVersionUID = -5283286219691106934L;

    private String id;

    private String title;

    private String url;

    private String linkId;

    private String linkNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getLinkNum() {
        return linkNum;
    }

    public void setLinkNum(String linkNum) {
        this.linkNum = linkNum;
    }

}
