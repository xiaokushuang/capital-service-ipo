package com.stock.capital.enterprise.ipoCase.service;

import com.stock.capital.enterprise.ipoCase.dao.IpoFinanceMapper;
import com.stock.capital.enterprise.ipoCase.dto.IpoFinanceDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoItemDto;
import com.stock.core.service.BaseService;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class IpoFinanceService extends BaseService {
    final static Logger logger = LoggerFactory.getLogger(IpoFinanceService.class);
    @Autowired
    private IpoFinanceMapper ipoFinanceMapper;

    public IpoFinanceDto selectFinanceList(String id) {
        IpoFinanceDto resultDto = new IpoFinanceDto();
        //先查询主营业务收入构成里面的时间，确定三年一期时间
        Date forthYear = ipoFinanceMapper.getForthYear(id);
        //如果日期为空则说明没有填写主营收入
        if (forthYear == null) {
            return new IpoFinanceDto();
        }
        //计算前3年时间
        Date thirdYear = getLastYearDate(forthYear);
        Date secondYear = getLastYearDate(thirdYear);
        Date firstYear = getLastYearDate(secondYear);
        List<Date> dateList = new ArrayList();
        dateList.add(forthYear);
        dateList.add(thirdYear);
        dateList.add(secondYear);
        dateList.add(firstYear);
        //获取时间列表
        List<String> dateStrList = getDateStr(dateList);
        resultDto.setDateList(dateStrList);
        //根据时间和id查询财务信息，三年一期的资产类项目
        List<IpoItemDto> forthItemList = ipoFinanceMapper.selectFinanceList(id, forthYear);
        List<IpoItemDto> thirdItemList = ipoFinanceMapper.selectFinanceList(id, thirdYear);
        List<IpoItemDto> secondItemList = ipoFinanceMapper.selectFinanceList(id, secondYear);
        List<IpoItemDto> firstItemList = ipoFinanceMapper.selectFinanceList(id, firstYear);
        //循环一年的 资产类项目 财务信息，因为查询顺序相同，直接用角标合并4年数据
        for (int i = 0; i < forthItemList.size(); i++) {
            forthItemList.get(i).setThirdYearValue(thirdItemList.get(i).getForthYearValue());
            forthItemList.get(i).setSecondYearValue(secondItemList.get(i).getForthYearValue());
            forthItemList.get(i).setFirstYearValue(firstItemList.get(i).getForthYearValue());
        }
        //移除财务信息中，三年一期全部为空的项目
        List<IpoItemDto> assetItemList = removeNullItem(forthItemList);
        resultDto.setIpoAssetItemList(assetItemList);
        //查询三年一期的 负债类项目 财务信息你
        List<IpoItemDto> forthDebtItemList = ipoFinanceMapper.selectDebtFinanceList(id, forthYear);
        List<IpoItemDto> thirdDebtItemList = ipoFinanceMapper.selectDebtFinanceList(id, thirdYear);
        List<IpoItemDto> secondDebtItemList = ipoFinanceMapper.selectDebtFinanceList(id, secondYear);
        List<IpoItemDto> firstDebtItemList = ipoFinanceMapper.selectDebtFinanceList(id, firstYear);
        for (int i = 0; i < forthDebtItemList.size(); i++) {
            forthDebtItemList.get(i).setThirdYearValue(thirdDebtItemList.get(i).getForthYearValue());
            forthDebtItemList.get(i).setSecondYearValue(secondDebtItemList.get(i).getForthYearValue());
            forthDebtItemList.get(i).setFirstYearValue(firstDebtItemList.get(i).getForthYearValue());
        }
        //移除财务信息中，三年一期全部为空的项目
        List<IpoItemDto> debtItemList = removeNullItem(forthDebtItemList);
        resultDto.setIpoDebtItemList(debtItemList);
        //查询三年一期的 权益类项目
        List<IpoItemDto> forthEquityItemList = ipoFinanceMapper.selectEquityItemList(id, forthYear);
        List<IpoItemDto> thirdEquityItemList = ipoFinanceMapper.selectEquityItemList(id, thirdYear);
        List<IpoItemDto> secondEquityItemList = ipoFinanceMapper.selectEquityItemList(id, secondYear);
        List<IpoItemDto> firstEquityItemList = ipoFinanceMapper.selectEquityItemList(id, firstYear);
        for (int i = 0; i < forthEquityItemList.size(); i++) {
            forthEquityItemList.get(i).setThirdYearValue(thirdEquityItemList.get(i).getForthYearValue());
            forthEquityItemList.get(i).setSecondYearValue(secondEquityItemList.get(i).getForthYearValue());
            forthEquityItemList.get(i).setFirstYearValue(firstEquityItemList.get(i).getForthYearValue());
        }
        //移除财务信息中，三年一期全部为空的项目
        List<IpoItemDto> equityItemList = removeNullItem(forthEquityItemList);
        resultDto.setIpoEquityItemList(equityItemList);

        return resultDto;
    }

    public IpoFinanceDto selectFinanceOverList(String id){
        IpoFinanceDto resultDot = new IpoFinanceDto();

        return resultDot;
    }

    /**
     * 获取前一年的12月31日
     */
    private Date getLastYearDate(Date date) {
        Date newDate = null;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, -1);
            int year = calendar.get(Calendar.YEAR);
            newDate = DateUtils.parseDate(String.valueOf(year) + "-12-31", "yyyy-MM-dd");
        } catch (ParseException e) {
            logger.error("财务信息:日期转换错误");
            e.printStackTrace();
        }
        return newDate;
    }

    /**
     * 将日期List转为字符串
     */
    private List<String> getDateStr(List<Date> dateList) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<String> dateStrList = new ArrayList<>();
        for (int i = 0; i < dateList.size(); i++) {
            String dateStr = dateFormat.format(dateList.get(i));
            dateStrList.add(dateStr);
        }
        return dateStrList;
    }

    /**
     * 移除财务信息中，三年一期全部为空的项目
     */
    private List<IpoItemDto> removeNullItem(List<IpoItemDto> itemList) {
        List<IpoItemDto> resultList = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++) {
            IpoItemDto dto = itemList.get(i);
            if (dto.getForthYearValue() != null || dto.getThirdYearValue() != null ||
                    dto.getSecondYearValue() != null || dto.getFirstYearValue() != null) {
                resultList.add(dto);
            }
        }
        return resultList;
    }
}
