package com.stock.capital.enterprise.ipoCase.dao;

import java.util.List;
import com.stock.capital.enterprise.ipoCase.dto.IssuerIndustryStatusDto;
import org.springframework.stereotype.Repository;

@Repository
public interface IpoIssuerIndustryStatusBizMapper {

    List<IssuerIndustryStatusDto> getindustryStatusData(String bid);
    
    
}
