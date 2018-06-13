package com.stock.capital.enterprise.declare.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DeclareApproveDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6372581856397437703L;
	
	private String id;

    private String businessId;

    private String userId;
    
    private String userName;
    
    private String approvalCommitOpinion;

    private String approvalOpinion;

    private String approvalStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date approvalTime;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date approvalEndTime;
    
    private List<DeclareFileDto> fileList;
    
    private	String createUserName;

    private String companyId;

    private String createUser;
    
    public String getApprovalCommitOpinion() {
        return approvalCommitOpinion;
    }

    public void setApprovalCommitOpinion(String approvalCommitOpinion) {
        this.approvalCommitOpinion = approvalCommitOpinion;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String status;
    
    private String taskId;
    
    private String declareId;

    //demand3170 提交时间 byjm.zhang 17/11/10
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date subTime;
    
	public Date getSubTime() {
		return subTime;
	}

	public void setSubTime(Date subTime) {
		this.subTime = subTime;
	}
    //  需求2449 审批工作流共通化 by weishisheng 2018/1/9 start
    /**
     * 审判结果
     */
    private String apprResult;
    //  需求2449 审批工作流共通化 by weishisheng 2018/1/9 end

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getApprovalOpinion() {
		return approvalOpinion;
	}

	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Date getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
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

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Date getApprovalEndTime() {
		return approvalEndTime;
	}

	public void setApprovalEndTime(Date approvalEndTime) {
		this.approvalEndTime = approvalEndTime;
	}

	public List<DeclareFileDto> getFileList() {
		return fileList;
	}

	public void setFileList(List<DeclareFileDto> fileList) {
		this.fileList = fileList;
	}

    public String getDeclareId() {
        return declareId;
    }

    public void setDeclareId(String declareId) {
        this.declareId = declareId;
    }

    //  需求2449 审批工作流共通化 by weishisheng 2018/1/9 start
    /**
     * apprResult.
     *
     * @return  the apprResult
     *
     */
    public String getApprResult() {
        return apprResult;
    }

    /**
     * apprResult.
     *
     * @param   apprResult    the apprResult to set
     *
     */
    public void setApprResult(String apprResult) {
        this.apprResult = apprResult;
    }
    //  需求2449 审批工作流共通化 by weishisheng 2018/1/9 end
}
