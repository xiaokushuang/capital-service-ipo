package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;

public class CommonSearchDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orderByName;
	
	private String orderByOrder;
	
	public String getOrderByName() {
		return orderByName;
	}

	public void setOrderByName(String orderByName) {
		this.orderByName = orderByName;
	}

	public String getOrderByOrder() {
		return orderByOrder;
	}

	public void setOrderByOrder(String orderByOrder) {
		this.orderByOrder = orderByOrder;
	}

	//页数
	private int page;

	private int startRow;

	//每页数量
	private int limit;

	private String sort;

	/**
	 * Returns value of page
	 * 
	 * @return
	 */
	public int getPage() {
		return page;
	}

	/**
	 * Sets new value of page
	 * 
	 * @param
	 */
	public void setPage(Integer page) {
		this.page = page;
		setStartRow(this.limit * (this.page - 1));
	}

	/**
	 * Returns value of startRow
	 * 
	 * @return
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * Sets new value of startRow
	 * 
	 * @param
	 */
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	/**
	 * Returns value of limit
	 * 
	 * @return
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * Sets new value of limit
	 * 
	 * @param
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * Returns value of sort
	 * 
	 * @return
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * Sets new value of sort
	 * 
	 * @param
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

}
