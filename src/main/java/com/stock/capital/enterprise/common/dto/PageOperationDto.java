package com.stock.capital.enterprise.common.dto;

import com.stock.core.dto.Indexable;

import java.io.Serializable;

public class PageOperationDto extends Indexable implements Serializable {

	private static final long serialVersionUID = 1L;

	//控制前台分享是否展示    
	private boolean showShare;
	
	//控制微信分享是否显示-上方
	private boolean erweimaShare;
	
	//控制微信分享是否显示-下方
	private boolean erweimaShare1;
	
	/**
	 * 设置标题被点击后的样式
	 */
	private boolean titleClicked;
	
	/**
	 * 判断是否被收藏
	 */
	private boolean isFavour;
	
	public boolean isErweimaShare() {
		return erweimaShare;
	}

	public void setErweimaShare(boolean erweimaShare) {
		this.erweimaShare = erweimaShare;
	}

	public boolean isErweimaShare1() {
		return erweimaShare1;
	}

	public void setErweimaShare1(boolean erweimaShare1) {
		this.erweimaShare1 = erweimaShare1;
	}

	public boolean isShowShare() {
		return showShare;
	}

	public void setShowShare(boolean showShare) {
		this.showShare = showShare;
	}

	public boolean isTitleClicked() {
		return titleClicked;
	}

	public void setTitleClicked(boolean titleClicked) {
		this.titleClicked = titleClicked;
	}

	public boolean getIsFavour() {
	    return isFavour;
	}
	
	public void setIsFavour(boolean isFavour) {
	    this.isFavour = isFavour;
	}
}
