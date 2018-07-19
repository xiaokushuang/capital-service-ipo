/**
 *
 */
package com.stock.capital.enterprise.api.regulatory.dao;

import com.stock.capital.enterprise.api.regulatory.dto.*;
import com.stock.core.dto.OptionDto;

import java.util.List;
import java.util.Map;

public interface ViolationBizMapper {

    List<OptionDto> getCodeList(Map<String, Object> params);

    List<Map<String, Object>> getPunishClassList(Map<String, Object> params);

    List<Map<String, Object>> getTypeList(Map<String, Object> params);
    
    List<Map<String, Object>> getViolateParentList(Map<String, Object> params);
    
    List<Map<String, Object>> getViolateChildrenList(Map<String, Object> params);
    
    // 需求3851 by weishisheng 2018/03/19 start
    List<Map<String, Object>> getBelongsToSRBList(Map<String, Object> params);
    // 需求3851 by weishisheng 2018/03/19 end
    
    List<Map<String, Object>> getBrokerCodeList(Map<String, Object> params);

    List<ViolationFavourDto> getFavourList(Map<String, Object> params);

    int getFavourListTotalCount(Map<String, Object> params);

    ViolationDetailDto getDetail(Map<String, Object> params);

    List<ViolationDetailNodeDto> getDetailProcessNodeList(Map<String, Object> params);

    List<ViolationDetailLinkDto> getDetailProcessNodeUrlList(Map<String, Object> params);

    List<ViolationDetailPersonDto> getDetailPersonList(Map<String, Object> params);

    List<ViolationDetailLinkDto> getDetailLawsList(Map<String, Object> params);

    List<ViolationDetailLinkDto> getDetailCasesList(Map<String, Object> params);
    
    String getTypeNameById(Map<String, String> params);
    String getCodeNameById(Map<String, String> params);
    String getPunishOrganizationById(Map<String, String> params);
    
    List<String> getViolateClass1ParentNo(Map<String, String> params);
    List<String> getViolateClass2ParentNo(Map<String, String> params);
}
