package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.IpoInvestItemDto;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpoInvestItemMapper {

    /**
     * 查询募集资金运用list
     */
    public List<IpoInvestItemDto> selectIpoInvestItem(String id);
}
