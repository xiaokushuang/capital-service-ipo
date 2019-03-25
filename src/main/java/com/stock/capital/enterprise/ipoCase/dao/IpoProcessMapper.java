package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.TreeTypeProgressDto;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpoProcessMapper {
    /**
     * 查询进程树
     */
    TreeTypeProgressDto selectProcessList(String id);

    /**
     * 查询股份公司设立时间
     */
    String getPublishDate(String id);
}
