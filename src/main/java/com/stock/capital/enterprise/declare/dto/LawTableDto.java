package com.stock.capital.enterprise.declare.dto;

import java.io.Serializable;
import java.util.List;

public class LawTableDto  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3048998412619430636L;

	private int total;
	
	private List<DecalreRelationLawDto> data;

	private List<DecalreRelationLawDto> dataAll;
	
	private int isLose;
	
	
	public List<DecalreRelationLawDto> getDataAll() {
		return dataAll;
	}

	public void setDataAll(List<DecalreRelationLawDto> dataAll) {
		this.dataAll = dataAll;
	}

	public int getIsLose() {
		return isLose;
	}

	public void setIsLose(int isLose) {
		this.isLose = isLose;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<DecalreRelationLawDto> getData() {
		return data;
	}

	public void setData(List<DecalreRelationLawDto> data) {
		this.data = data;
	}
	
	
	
}
