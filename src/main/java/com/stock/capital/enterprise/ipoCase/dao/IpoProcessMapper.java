package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.TreeTypeProgressDto;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpoProcessMapper {
    TreeTypeProgressDto selectProcessList(String id);
}
