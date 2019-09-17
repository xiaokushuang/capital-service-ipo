package com.stock.capital.enterprise.ipoCoachCase.service;

import com.stock.capital.enterprise.ipoCase.dto.IpoProgressDto;
import com.stock.capital.enterprise.ipoCoachCase.dao.IpoCoachCaseDetailBizMapper;
import com.stock.capital.enterprise.ipoCoachCase.dto.IntermediaryOrgDto;
import com.stock.capital.enterprise.ipoCoachCase.dto.IpoCoachCaseDto;
import com.stock.capital.enterprise.ipoCoachCase.dto.OtherMarketInfoDto;
import com.stock.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = {Exception.class})
public class IpoCoachCaseDetailService extends BaseService {

    @Autowired
    IpoCoachCaseDetailBizMapper ipoCoachCaseDetailBizMapper;

    public IpoCoachCaseDto queryCoachTitleInfo(String id) {
        IpoCoachCaseDto ipoCoachCaseDto= new IpoCoachCaseDto();
        ipoCoachCaseDto = ipoCoachCaseDetailBizMapper.queryCoachBasicInfo(id);
//        List<IpoProgressDto> intermediaryOrgDtoList = ipoCoachCaseDetailBizMapper.queryCoachProgress(id);
        return ipoCoachCaseDto;
    }

    public IpoCoachCaseDto queryCoachBasicInfo(String id) {
        IpoCoachCaseDto ipoCoachCaseDto= new IpoCoachCaseDto();
        ipoCoachCaseDto = ipoCoachCaseDetailBizMapper.queryCoachBasicInfo(id);
        //查询中介机构数据
        List<IntermediaryOrgDto> intermediaryOrgDtoList = ipoCoachCaseDetailBizMapper.selectOrgByBid(id);
        if (intermediaryOrgDtoList != null && intermediaryOrgDtoList.size() > 0) {
            ipoCoachCaseDto.setIntermediaryOrgList(intermediaryOrgDtoList);
        }
        //查询其他资本市场情况数据
        List<OtherMarketInfoDto> otherMarketInfoList =
                ipoCoachCaseDetailBizMapper.selectMarketByBid(id);
        if (otherMarketInfoList != null && !otherMarketInfoList.isEmpty() && otherMarketInfoList.size() > 0) {
            ipoCoachCaseDto.setOtherMarketInfoList(otherMarketInfoList);
        }
        return ipoCoachCaseDto;
    }
}
