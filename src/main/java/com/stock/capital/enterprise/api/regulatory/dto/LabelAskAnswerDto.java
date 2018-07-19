package com.stock.capital.enterprise.api.regulatory.dto;

import java.io.Serializable;

public class LabelAskAnswerDto implements Serializable {

	private static final long serialVersionUID = 2993664712694151053L;
	// 问题ID
	private String askId;
	// 问题内容（带格式）
	private String askContent;
	// 问题内容（不带格式）
	private String askContentFormat;
	// 回答ID
	private String answer;
	// 回答内容（带格式）
	private String answerContent;
	// 回答内容（不带格式）
	private String answerContentFormat;
	// 问题类型
	private String askTypeName;
	
	private String level;
	
	public String getAskId() {
		return askId;
	}
	public void setAskId(String askId) {
		this.askId = askId;
	}
	public String getAskContent() {
		return askContent;
	}
	public void setAskContent(String askContent) {
		this.askContent = askContent;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public String getAskTypeName() {
		return askTypeName;
	}
	public void setAskTypeName(String askTypeName) {
		this.askTypeName = askTypeName;
	}
	public String getAskContentFormat() {
		return askContentFormat;
	}
	public void setAskContentFormat(String askContentFormat) {
		this.askContentFormat = askContentFormat;
	}
	public String getAnswerContentFormat() {
		return answerContentFormat;
	}
	public void setAnswerContentFormat(String answerContentFormat) {
		this.answerContentFormat = answerContentFormat;
	}
        public String getLevel() {
            return level;
        }
        public void setLevel(String level) {
            this.level = level;
        }
	
}
