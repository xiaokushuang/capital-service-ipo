package com.stock.capital.enterprise.declare.dto;

import java.io.Serializable;
import java.util.List;

import com.stock.capital.enterprise.common.entity.DeclareFile;
import com.stock.capital.enterprise.common.entity.DeclareType;
 

public class DeclareTypeAndFileDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -436643447511152455L;
	
	private DeclareType declateType;
	
	private List<DeclareFile> declareFileList;

	public DeclareType getDeclateType() {
		return declateType;
	}

	public void setDeclateType(DeclareType declateType) {
		this.declateType = declateType;
	}

	public List<DeclareFile> getDeclareFileList() {
		return declareFileList;
	}

	public void setDeclareFileList(List<DeclareFile> declareFileList) {
		this.declareFileList = declareFileList;
	}
	
}
