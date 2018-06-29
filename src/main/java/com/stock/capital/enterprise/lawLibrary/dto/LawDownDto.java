package com.stock.capital.enterprise.lawLibrary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; 
import com.stock.capital.enterprise.common.entity.LawsManageWithBLOBs;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by XueHui on 2017/6/12 0012.
 */
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class LawDownDto extends LawsManageWithBLOBs implements Serializable {


    private static final long serialVersionUID = 2126545876375323302L;
    private String modifyDate;

    private List<InvalidLawsDto> invalidLaws;

    private Map<String,String> attFiles;

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public List<InvalidLawsDto> getInvalidLaws() {
        return invalidLaws;
    }

    public void setInvalidLaws(List<InvalidLawsDto> invalidLaws) {
        this.invalidLaws = invalidLaws;
    }

    public Map<String, String> getAttFiles() {
        return attFiles;
    }

    public void setAttFiles(Map<String, String> attFiles) {
        this.attFiles = attFiles;
    }
}
