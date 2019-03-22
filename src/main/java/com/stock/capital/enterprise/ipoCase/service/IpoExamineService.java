package com.stock.capital.enterprise.ipoCase.service;

import com.stock.capital.enterprise.ipoCase.dao.IpoExamineMapper;
import com.stock.core.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpoExamineService extends BaseService {
    @Autowired
    private IpoExamineMapper ipoExamineMapper;


}
