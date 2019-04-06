package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("IPO反馈意见问题类")
public class IpoFeedbackQuestionDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("问题id")
    private String questionId;
    @ApiModelProperty("问题")
    private String question;
    @ApiModelProperty("回答")
    private String answer;
    @ApiModelProperty("所属标签")
    private List<String> labelList;
    @ApiModelProperty("带格式问题")
    private String formatQuestion;
    @ApiModelProperty("带格式回答")
    private String formatAnswer;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<String> labelList) {
        this.labelList = labelList;
    }

    public String getFormatQuestion() {
        return formatQuestion;
    }

    public void setFormatQuestion(String formatQuestion) {
        this.formatQuestion = formatQuestion;
    }

    public String getFormatAnswer() {
        return formatAnswer;
    }

    public void setFormatAnswer(String formatAnswer) {
        this.formatAnswer = formatAnswer;
    }
}
