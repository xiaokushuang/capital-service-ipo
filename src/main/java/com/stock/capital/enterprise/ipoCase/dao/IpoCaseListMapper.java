package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.IpoCaseListBo;
import com.stock.capital.enterprise.ipoCase.dto.IpoFavoriteAndNoteDto;
import com.stock.capital.enterprise.ipoCase.dto.RegTreeDto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IpoCaseListMapper {

    List<RegTreeDto> getTreeTagByCode(String labelCode);

    List<RegTreeDto> getLabelByCode(String labelCode);

    /**
     * touguan库(如果自己数据库好用后期删除)
     * @param intermediaryName
     * @return
     */
    List<Map<String, Object>> queryIntermediary(String intermediaryName);

    /**
     * 本地库
     * @param intermediaryName
     * @return
     */
    List<Map<String, Object>> queryIntermediaryLocal(String intermediaryName);

    int queryAuthByCompanyId(String companyId);

    int queryOverdueAuthByCompanyId(String companyId);

    IpoCaseListBo querylawId();

    List<String> queryIpoFavoriteList(@Param("companyId") String companyId,@Param("userId") String userId);

    List<IpoFavoriteAndNoteDto> getCassNote(IpoFavoriteAndNoteDto dto);

    String isCompanyFlag(String companyCode);

    List<String> getIpoItemCaseIdList(IpoCaseListBo ipoCaseListBo);
}