package com.stock.capital.enterprise.lawLibrary.dao;

import java.util.List;
import java.util.Map;
 

import com.stock.capital.enterprise.common.entity.CompanyRuleUpdateRecord;
import com.stock.capital.enterprise.lawLibrary.dto.CompanyRuleDto;
import com.stock.capital.enterprise.lawLibrary.dto.RulesClassDto;
import com.stock.core.dto.OptionDto;

public interface CompanyRuleBizMapper {

    public List<CompanyRuleDto> todoTaskQuery(CompanyRuleDto companyRuleDto);

    public List<CompanyRuleDto> queryCompanyRuleAllPage(CompanyRuleDto companyRuleDto);

    public String queryCompanyRuleAllPageCount(CompanyRuleDto companyRuleDto);

    public String queryCompanyRuleNum(CompanyRuleDto companyRuleDto);

    public Integer queryAllFileNum(CompanyRuleDto companyRuleDto);

    public String queryIsHaveFileByClass(RulesClassDto rulesClassDto);

    public void updateByPrimaryKeySelective(CompanyRuleDto companyRule);

    public CompanyRuleDto queryCompanyRuleById(String ruleId);

    public void updateClassNoById(CompanyRuleDto companyRule);

    public List<String> queryEnterpriseNoteLawIds();

    public void deleteEnterpriseNotes(Map<String, Object> deleteLawids);

    public List<String> queryEnterpriseFavIds();

    public void deleteEnterpriseFavors(Map<String, Object> delparams);
    
    public int validateTagName(Map<String,String> param);
    
    public List<OptionDto> getMyTagList(String personId);
    
    public List<CompanyRuleUpdateRecord> getUpdateRecordList(String id);
    
    //需求2177 2017/11/06 by liuhuan START
    //取得发文部门名字
    String getOrgName(String id);
    //需求2177 2017/11/06 by liuhuan END
}