package com.stock.capital.enterprise.lawLibrary.dto;

import java.io.Serializable;
import java.util.Date;

public class CodeDto implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1304482970338629576L;

		private String id;

	    private String codeNo;

	    private String codeValue;

	    private String codeName;

	    private String codeDesc;

	    private Integer sortNo;

	    private String codeType;

	    private String validFlag;

	    private String version;

	    private String companyId;

	    private String createUser;

	    private Date createTime;

	    private String updateUser;

	    private Date updateTime;

	    private String status;
	    
	    private String flag;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getCodeNo() {
			return codeNo;
		}

		public void setCodeNo(String codeNo) {
			this.codeNo = codeNo;
		}

		public String getCodeValue() {
			return codeValue;
		}

		public void setCodeValue(String codeValue) {
			this.codeValue = codeValue;
		}

		public String getCodeName() {
			return codeName;
		}

		public void setCodeName(String codeName) {
			this.codeName = codeName;
		}

		public String getCodeDesc() {
			return codeDesc;
		}

		public void setCodeDesc(String codeDesc) {
			this.codeDesc = codeDesc;
		}

		public Integer getSortNo() {
			return sortNo;
		}

		public void setSortNo(Integer sortNo) {
			this.sortNo = sortNo;
		}

		public String getCodeType() {
			return codeType;
		}

		public void setCodeType(String codeType) {
			this.codeType = codeType;
		}

		public String getValidFlag() {
			return validFlag;
		}

		public void setValidFlag(String validFlag) {
			this.validFlag = validFlag;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getCompanyId() {
			return companyId;
		}

		public void setCompanyId(String companyId) {
			this.companyId = companyId;
		}

		public String getCreateUser() {
			return createUser;
		}

		public void setCreateUser(String createUser) {
			this.createUser = createUser;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public String getUpdateUser() {
			return updateUser;
		}

		public void setUpdateUser(String updateUser) {
			this.updateUser = updateUser;
		}

		public Date getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getFlag() {
			return flag;
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}
}