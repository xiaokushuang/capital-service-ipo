package com.stock.capital.enterprise.ipoCase.service;

import com.stock.capital.enterprise.ipoCase.dao.IpoProcessMapper;
import com.stock.capital.enterprise.ipoCase.dto.TreeTypeProgressDto;
import com.stock.core.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IpoProcessService extends BaseService {

    @Autowired
    private IpoProcessMapper ipoProcessMapper;

    public List<TreeTypeProgressDto> selectProcessList(String id){
        List<TreeTypeProgressDto> treeList = ipoProcessMapper.selectProcessList(id);
        return treeList;
    }

}
