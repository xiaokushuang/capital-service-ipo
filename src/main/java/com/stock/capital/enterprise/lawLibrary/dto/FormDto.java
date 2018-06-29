package com.stock.capital.enterprise.lawLibrary.dto;

import java.util.List;

public class FormDto {

    private List<CompanyRuleDto> companyRuleList;

    private List<LawFavourNoteDto> favouriteDtoList;

    private List<LawFavourNoteDto> notesDtoList;

    private String count;

    public List<CompanyRuleDto> getCompanyRuleList() {
        return companyRuleList;
    }

    public void setCompanyRuleList(List<CompanyRuleDto> companyRuleList) {
        this.companyRuleList = companyRuleList;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<LawFavourNoteDto> getFavouriteDtoList() {
        return favouriteDtoList;
    }

    public void setFavouriteDtoList(List<LawFavourNoteDto> favouriteDtoList) {
        this.favouriteDtoList = favouriteDtoList;
    }

    public List<LawFavourNoteDto> getNotesDtoList() {
        return notesDtoList;
    }

    public void setNotesDtoList(List<LawFavourNoteDto> notesDtoList) {
        this.notesDtoList = notesDtoList;
    }

}
