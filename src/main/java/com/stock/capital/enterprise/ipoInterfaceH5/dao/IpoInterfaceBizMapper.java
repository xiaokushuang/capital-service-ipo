package com.stock.capital.enterprise.ipoInterfaceH5.dao;

import com.stock.capital.enterprise.ipoCase.dto.IpoCaseIndexDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseListVo;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5CoreDevDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5DetailDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IpoInterfaceBizMapper {
    List<IpoCaseListVo> otherIpoCase(IpoCaseIndexDto ipoCaseIndexDto);

    IpoH5Dto ipoCompanyRank(IpoH5Dto ipoH5Dto);

    List<IpoH5DetailDto> ipoAvg(IpoH5DetailDto ipoH5DetailDto);

    IpoH5DetailDto queryIndustryCsrc(IpoH5DetailDto ipoH5DetailDto);

    List<IpoH5CoreDevDto> getCoreDevFromIpoTech(@Param("id") String id);

    List<IpoCaseListVo> queryIpoCase(Map<String, Object> map);

    List<IpoCaseListVo> otherIpoCaseNoIndustry();
}
