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

	private int isLose;
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
