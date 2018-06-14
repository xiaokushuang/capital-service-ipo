package com.stock.capital.enterprise.declare.dto;

import java.io.Serializable;
import java.util.List;

public class QueryNoticeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4095039084196178875L;
	
	private String queryField;
	
	private List<String> typeIdList;

	public String getQueryField() {
		return queryField;
	}

	public void setQueryField(String queryField) {
		this.queryField = queryField;
	}

	public List<String> getTypeIdList() {
		return typeIdList;
	}

	public void setTypeIdList(List<String> typeIdList) {
		this.typeIdList = typeIdList;
	}
	
}
