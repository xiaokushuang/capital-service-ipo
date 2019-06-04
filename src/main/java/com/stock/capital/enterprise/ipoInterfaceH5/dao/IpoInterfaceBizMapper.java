package com.stock.capital.enterprise.ipoInterfaceH5.dao;

import com.stock.capital.enterprise.ipoCase.dto.IpoCaseIndexDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpoInterfaceBizMapper {
    List<IpoCaseIndexDto> otherIpoCase(IpoCaseIndexDto ipoCaseIndexDto);
}
