package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("IPO审核反馈类")
public class IpoExamineDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("基本信息列表")
    private List<IpoExamineBaseDto> baseList;
    @ApiModelProperty("问题列表")
    private List<IpoFeedbackQuestionDto> questionList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<IpoExamineBaseDto> getBaseList() {
        return baseList;
    }

    public void setBaseList(List<IpoExamineBaseDto> baseList) {
        this.baseList = baseList;
    }

    public List<IpoFeedbackQuestionDto> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<IpoFeedbackQuestionDto> questionList) {
        this.questionList = questionList;
    }
}
