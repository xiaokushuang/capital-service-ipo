package com.stock.capital.enterprise.declare.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat; 
import com.stock.capital.enterprise.common.entity.DeclareIdea;
import com.stock.capital.enterprise.common.entity.DeclareType;

public class DeclareInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3158223421913988790L;

	private String id;

	private String declareName;

	private String declareNo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date disclosureDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date appraveDate;
	
	private List<String> delDeclareType;
	
	private List<String> typeIdList;
	
	private List<String> delFileId;
	
	private List<String> delAttId;
	
	private List<DeclareType> declareTypes;

	private String context;

	private String associatedMeeting;
	
	private String meetingName;

	private String capitalPlus;

	private String declareStatus;
	
	private String declareApprovalOpinion;
	
	public String getDeclareApprovalOpinion() {
        return declareApprovalOpinion;
    }

    public void setDeclareApprovalOpinion(String declareApprovalOpinion) {
        this.declareApprovalOpinion = declareApprovalOpinion;
    }

    private List<DeclareFileDto> fileList;
	
	private List<NoticeHelperDto> helperList;
	
	private List<String> personIdList;
	
	/**
     * 自我心得
     */
    private List<DeclareIdea> declareIdeaList;

	private String companyCode;

	private String createUser;

	private Date createTime;

	private String updateUser;

	private Date updateTime;

	private String status;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startPublishDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endPublishDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date deStartPublishDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date deEndPublishDate;
    //审批领导名
    private String taskPerName;
    //审批领导id
    private String taskPerId;
    //是否领导审批
    private String leaderViewFlag;
    //信披历史查询
    private String deContent;
    //公司公告查询
    private String content;
    //已选业务类型
    private Map<String,String> declareTypeMap;
    //最大信披号
    private String maxDeclareNo;
    
    private String changeFlag;
    
    private String meetingIdFlag;
    
    private String pageFlag;
    
    //  需求2449 审批工作流共通化 by weishisheng 2018/1/9 start
    /**
     * 信披ID
     */
    private String businessId;
    
    /**
     * 业务类别：XP:信披
     */
    private String categoryId;
    
    /**
     * 审批截止时间
     */
    private String approvalEndTime;
    
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

	public String getDeclareName() {
		return declareName;
	}

	public void setDeclareName(String declareName) {
		this.declareName = declareName;
	}

	public String getDeclareNo() {
		return declareNo;
	}

	public void setDeclareNo(String declareNo) {
		this.declareNo = declareNo;
	}

	public Date getDisclosureDate() {
		return disclosureDate;
	}

	public void setDisclosureDate(Date disclosureDate) {
		this.disclosureDate = disclosureDate;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getAssociatedMeeting() {
		return associatedMeeting;
	}

	public void setAssociatedMeeting(String associatedMeeting) {
		this.associatedMeeting = associatedMeeting;
	}

	public String getCapitalPlus() {
		return capitalPlus;
	}

	public void setCapitalPlus(String capitalPlus) {
		this.capitalPlus = capitalPlus;
	}

	public String getDeclareStatus() {
		return declareStatus;
	}

	public void setDeclareStatus(String declareStatus) {
		this.declareStatus = declareStatus;
	}

	public List<DeclareFileDto> getFileList() {
		return fileList;
	}

	public void setFileList(List<DeclareFileDto> fileList) {
		this.fileList = fileList;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
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

	public Date getStartPublishDate() {
		return startPublishDate;
	}

	public void setStartPublishDate(Date startPublishDate) {
		this.startPublishDate = startPublishDate;
	}

	public Date getEndPublishDate() {
		return endPublishDate;
	}

	public void setEndPublishDate(Date endPublishDate) {
		this.endPublishDate = endPublishDate;
	}

	public String getTaskPerName() {
		return taskPerName;
	}

	public void setTaskPerName(String taskPerName) {
		this.taskPerName = taskPerName;
	}

	public String getTaskPerId() {
		return taskPerId;
	}

	public void setTaskPerId(String taskPerId) {
		this.taskPerId = taskPerId;
	}

	public String getLeaderViewFlag() {
		return leaderViewFlag;
	}

	public void setLeaderViewFlag(String leaderViewFlag) {
		this.leaderViewFlag = leaderViewFlag;
	}

	public List<String> getPersonIdList() {
		return personIdList;
	}

	public void setPersonIdList(List<String> personIdList) {
		this.personIdList = personIdList;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public Date getDeStartPublishDate() {
		return deStartPublishDate;
	}

	public void setDeStartPublishDate(Date deStartPublishDate) {
		this.deStartPublishDate = deStartPublishDate;
	}

	public Date getDeEndPublishDate() {
		return deEndPublishDate;
	}

	public void setDeEndPublishDate(Date deEndPublishDate) {
		this.deEndPublishDate = deEndPublishDate;
	}

	public List<String> getDelDeclareType() {
		return delDeclareType;
	}

	public void setDelDeclareType(List<String> delDeclareType) {
		this.delDeclareType = delDeclareType;
	}

	public List<String> getDelFileId() {
		return delFileId;
	}

	public void setDelFileId(List<String> delFileId) {
		this.delFileId = delFileId;
	}

	public String getDeContent() {
		return deContent;
	}

	public void setDeContent(String deContent) {
		this.deContent = deContent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<DeclareType> getDeclareTypes() {
		return declareTypes;
	}

	public void setDeclareTypes(List<DeclareType> declareTypes) {
		this.declareTypes = declareTypes;
	}

	public List<NoticeHelperDto> getHelperList() {
		return helperList;
	}

	public void setHelperList(List<NoticeHelperDto> helperList) {
		this.helperList = helperList;
	}

	public Map<String, String> getDeclareTypeMap() {
		return declareTypeMap;
	}

	public void setDeclareTypeMap(Map<String, String> declareTypeMap) {
		this.declareTypeMap = declareTypeMap;
	}

	public String getMaxDeclareNo() {
		return maxDeclareNo;
	}

	public void setMaxDeclareNo(String maxDeclareNo) {
		this.maxDeclareNo = maxDeclareNo;
	}

	public List<String> getDelAttId() {
		return delAttId;
	}

	public void setDelAttId(List<String> delAttId) {
		this.delAttId = delAttId;
	}

	public List<String> getTypeIdList() {
		return typeIdList;
	}

	public void setTypeIdList(List<String> typeIdList) {
		this.typeIdList = typeIdList;
	}

	public String getChangeFlag() {
		return changeFlag;
	}

	public void setChangeFlag(String changeFlag) {
		this.changeFlag = changeFlag;
	}

	public List<DeclareIdea> getDeclareIdeaList() {
		return declareIdeaList;
	}

	public void setDeclareIdeaList(List<DeclareIdea> declareIdeaList) {
		this.declareIdeaList = declareIdeaList;
	}

	public String getMeetingIdFlag() {
		return meetingIdFlag;
	}

	public void setMeetingIdFlag(String meetingIdFlag) {
		this.meetingIdFlag = meetingIdFlag;
	}

	public String getPageFlag() {
		return pageFlag;
	}

	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}

	public Date getAppraveDate() {
		return appraveDate;
	}

	public void setAppraveDate(Date appraveDate) {
		this.appraveDate = appraveDate;
	}

   //  需求2449 审批工作流共通化 by weishisheng 2018/1/9 start
    /**
     * businessId.
     *
     * @return  the businessId
     *
     */
    public String getBusinessId() {
        return businessId;
    }

    /**
     * businessId.
     *
     * @param   businessId    the businessId to set
     *
     */
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    /**
     * categoryId.
     *
     * @return  the categoryId
     *
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * categoryId.
     *
     * @param   categoryId    the categoryId to set
     *
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * approvalEndTime.
     *
     * @return  the approvalEndTime
     *
     */
    public String getApprovalEndTime() {
        return approvalEndTime;
    }

    /**
     * approvalEndTime.
     *
     * @param   approvalEndTime    the approvalEndTime to set
     *
     */
    public void setApprovalEndTime(String approvalEndTime) {
        this.approvalEndTime = approvalEndTime;
    }

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
