package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("IPO预披露反馈函件类")
public class IpoFeedbackDto implements Serializable {
    @ApiModelProperty("函件id")
    private String letterId;
    @ApiModelProperty("问题包含的一级标签")
    private List<IpoQuestionLabelDto> questionLabelList;
    @ApiModelProperty("问题数量")
    private int questionCount;
    @ApiModelProperty("回答数量")
    private int answerCount;
    @ApiModelProperty("问题列表")
    private List<IpoFeedbackQuestionDto> questionList;

    private static final long serialVersionUID = 1L;

    public String getLetterId() {
        return letterId;
    }

    public void setLetterId(String letterId) {
        this.letterId = letterId;
    }

    public List<IpoQuestionLabelDto> getQuestionLabelList() {
        return questionLabelList;
    }

    public void setQuestionLabelList(List<IpoQuestionLabelDto> questionLabelList) {
        this.questionLabelList = questionLabelList;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public List<IpoFeedbackQuestionDto> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<IpoFeedbackQuestionDto> questionList) {
        this.questionList = questionList;
    }
}
