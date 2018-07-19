package com.stock.capital.enterprise.api.regulatory.dto;

import java.io.Serializable;
import java.util.List;

public class LetterRtnDto implements Serializable {

    private static final long serialVersionUID = -390203545513245420L;
    /**
     * 回函Id
     */
    private String letterReturnId;
    /**
     * 回函附件
     */
    private List<LabelRtnFileDto> lableReturnFile;
    
    public String getLetterReturnId() {
        return letterReturnId;
    }
    public void setLetterReturnId(String letterReturnId) {
        this.letterReturnId = letterReturnId;
    }
    public List<LabelRtnFileDto> getLableReturnFile() {
        return lableReturnFile;
    }
    public void setLableReturnFile(List<LabelRtnFileDto> lableReturnFile) {
        this.lableReturnFile = lableReturnFile;
    }
    
    
    
}

