package com.stock.capital.enterprise.financeStatistics.dto;

public class Param {
  private String param; 
  
  private String typeId;
  
  private String typeName; 

  private String fromPaper;
  
  private String length;
  
  private String orderByName;
  
  private String orderByOrder;
  

public String getFromPaper() {
	return fromPaper;
}

public void setFromPaper(String fromPaper) {
	this.fromPaper = fromPaper;
}

public String getLength() {
	return length;
}

public void setLength(String length) {
	this.length = length;
}

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

public String getParam() {
	return param;
}

public void setParam(String param) {
	this.param = param;
}

public String getTypeId() {
	return typeId;
}

public void setTypeId(String typeId) {
	this.typeId = typeId;
}

public String getTypeName() {
	return typeName;
}

public void setTypeName(String typeName) {
	this.typeName = typeName;
}
  
  
}
