package com.stock.capital.enterprise.api.regulatory.dto;

import java.io.Serializable;

public class RegulatoryFavourDto implements Serializable {

    private static final long serialVersionUID = -5283286219691106934L;

    private String id;

    private String url;

    private String infoTitle;

    private String infoTitleHL;

    private String infoSource;

    private String releaseTime;

    private String favourTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public String getInfoTitleHL() {
        return infoTitleHL;
    }

    public void setInfoTitleHL(String infoTitleHL) {
        this.infoTitleHL = infoTitleHL;
    }

    public String getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getFavourTime() {
        return favourTime;
    }

    public void setFavourTime(String favourTime) {
        this.favourTime = favourTime;
    }

}
