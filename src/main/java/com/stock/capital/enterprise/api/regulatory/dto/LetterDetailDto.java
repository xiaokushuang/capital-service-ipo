package com.stock.capital.enterprise.api.regulatory.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stock.capital.enterprise.common.entity.Letter;
import com.stock.capital.enterprise.common.entity.LetterDeclareMap;
import com.stock.capital.enterprise.common.entity.ViolateManage;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LetterDetailDto implements Serializable {

	private static final long serialVersionUID = 4567684623953684047L;

	private String id;

	// 函件标题
	private String letterName;
	// 公司代码
	private String companyCode;
	// 公司简称
	private String companyName;
	// 发函单位
	private String letterOrg;
	// 函件文号
	private String letterFileNo;
	// 函件类型
	private String letterType;
	// 发函日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date letterDate;
	// 回函日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date returnDate;
	// 问题回答列表
	List<LabelAskAnswerDto> askAndAnswerDtoList;
	// 来函列表
	private List<LabelFileLetterDto> labelFileList;
	// 回函列表
	private List<LetterRtnDto> letterReturn;
	// 相关函件列表
	private List<Letter> relevantLetters;
	// 相关公告列表
	private List<LetterDeclareMap> declareList;
	// 相关规则列表
	private List<LetterRuleDto> relevantRules;
	// 相关案例列表
	private List<ViolateManage> relevantViolates;
	// 导言结束语列表
	private List<LabelXmlStructureDto> labelXml;
	// 导言标题列表
        private List<LabelXmlStructureDto> labelTitle;
	
	public List<LabelXmlStructureDto> getLabelTitle() {
            return labelTitle;
        }

        public void setLabelTitle(List<LabelXmlStructureDto> labelTitle) {
            this.labelTitle = labelTitle;
        }

    public List<LabelXmlStructureDto> getLabelXml() {
            return labelXml;
        }
    
        public void setLabelXml(List<LabelXmlStructureDto> labelXml) {
            this.labelXml = labelXml;
        }
    
        public String getId() {
    		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLetterName() {
		return letterName;
	}

	public void setLetterName(String letterName) {
		this.letterName = letterName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLetterOrg() {
		return letterOrg;
	}

	public void setLetterOrg(String letterOrg) {
		this.letterOrg = letterOrg;
	}

	public String getLetterFileNo() {
		return letterFileNo;
	}

	public void setLetterFileNo(String letterFileNo) {
		this.letterFileNo = letterFileNo;
	}

	public String getLetterType() {
		return letterType;
	}

	public void setLetterType(String letterType) {
		this.letterType = letterType;
	}

	public Date getLetterDate() {
		return letterDate;
	}

	public void setLetterDate(Date letterDate) {
		this.letterDate = letterDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public List<LabelAskAnswerDto> getAskAndAnswerDtoList() {
		return askAndAnswerDtoList;
	}

	public void setAskAndAnswerDtoList(
			List<LabelAskAnswerDto> askAndAnswerDtoList) {
		this.askAndAnswerDtoList = askAndAnswerDtoList;
	}

	public List<LabelFileLetterDto> getLabelFileList() {
		return labelFileList;
	}

	public void setLabelFileList(List<LabelFileLetterDto> labelFileList) {
		this.labelFileList = labelFileList;
	}

	public List<LetterRtnDto> getLetterReturn() {
		return letterReturn;
	}

	public void setLetterReturn(List<LetterRtnDto> letterReturn) {
		this.letterReturn = letterReturn;
	}

	public List<Letter> getRelevantLetters() {
		return relevantLetters;
	}

	public void setRelevantLetters(List<Letter> relevantLetters) {
		this.relevantLetters = relevantLetters;
	}

	public List<LetterDeclareMap> getDeclareList() {
		return declareList;
	}

	public void setDeclareList(List<LetterDeclareMap> declareList) {
		this.declareList = declareList;
	}

	public List<LetterRuleDto> getRelevantRules() {
		return relevantRules;
	}

	public void setRelevantRules(List<LetterRuleDto> relevantRules) {
		this.relevantRules = relevantRules;
	}

	public List<ViolateManage> getRelevantViolates() {
		return relevantViolates;
	}

	public void setRelevantViolates(List<ViolateManage> relevantViolates) {
		this.relevantViolates = relevantViolates;
	}

}
