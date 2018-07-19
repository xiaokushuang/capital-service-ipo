package com.stock.capital.enterprise.api.regulatory.dto;

import java.io.Serializable;
import java.util.List;

public class ViolationDetailNodeDto implements Serializable {

    private static final long serialVersionUID = -5283286219691106934L;

    private String id;

    private String title;

    private String content;

    private String eventDate;

    private String liveNode;

    private String sort;

    private String nodeName;

    private String nodeValue;

    private List<ViolationDetailLinkDto> urlList;
    //	2017-08-14  lanyuxin bengin
    private String contentnohtml;

    public String getContentnohtml() {
		return contentnohtml;
	}

	public void setContentnohtml(String contentnohtml) {
		this.contentnohtml = contentnohtml;
	}

	//	2017-08-14  lanyuxin end
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getLiveNode() {
        return liveNode;
    }

    public void setLiveNode(String liveNode) {
        this.liveNode = liveNode;
    }

    public List<ViolationDetailLinkDto> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<ViolationDetailLinkDto> urlList) {
        this.urlList = urlList;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

}
