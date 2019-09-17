package com.stock.capital.enterprise.ipoCoachCase.service;

import com.stock.capital.enterprise.ipoCase.dto.TreeTypeProgressDto;
import com.stock.capital.enterprise.ipoCoachCase.dao.IpoCoachCaseDetailBizMapper;
import com.stock.capital.enterprise.ipoCoachCase.dto.IntermediaryOrgDto;
import com.stock.capital.enterprise.ipoCoachCase.dto.IpoCoachCaseDto;
import com.stock.capital.enterprise.ipoCoachCase.dto.OtherMarketInfoDto;
import com.stock.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.stock.core.util.DateUtil.YYYY_MM_DD;
import static com.stock.core.util.DateUtil.getDate;

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

    public TreeTypeProgressDto queryIpoProcessByCaseId(String caseId) {
        return ipoCoachCaseDetailBizMapper.queryIpoProcessByCaseId(caseId);
    }


    /**
     * 计算日期相差天数
     * @param
     * @return
     */
    public String getTimeDistance(String beginDate , String endDate) {
        Date begin = getDate(beginDate,YYYY_MM_DD);
        Date end = getDate(endDate,YYYY_MM_DD);
        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(begin);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        long beginTime = beginCalendar.getTime().getTime();
        long endTime = endCalendar.getTime().getTime();
        int betweenDays = (int)((endTime - beginTime) / (1000 * 60 * 60 *24));
        endCalendar.add(Calendar.DAY_OF_MONTH, -betweenDays);
        endCalendar.add(Calendar.DAY_OF_MONTH, -1);
        if(beginCalendar.get(Calendar.DAY_OF_MONTH)==endCalendar.get(Calendar.DAY_OF_MONTH))
            return Integer.toString(betweenDays + 1) + "天";
        else
            return Integer.toString(betweenDays + 0) + "天";
    }
}
