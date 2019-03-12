package com.stock.capital.enterprise.ipoCase.service;

import com.stock.capital.enterprise.ipoCase.dao.IpoInvestItemMapper;
import com.stock.core.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpoInvestService extends BaseService {
    @Autowired
    private IpoInvestItemMapper ipoInvestItemMapper;
//    public List<>
}
