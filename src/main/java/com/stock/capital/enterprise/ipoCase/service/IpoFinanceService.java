package com.stock.capital.enterprise.ipoCase.service;

import com.google.common.base.Throwables;

import com.stock.capital.enterprise.ipoCase.dao.IpoFinanceMapper;
import com.stock.capital.enterprise.ipoCase.dto.IpoFinanceDateDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFinanceDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoItemDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoFinanceH5Dto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5FinanceListDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5FinanceResultDto;
import com.stock.core.service.BaseService;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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

    /**
     * 财务信息 资产与负债情况
     */
    public IpoFinanceDto selectFinanceList(String id) {
        IpoFinanceDto resultDto = new IpoFinanceDto();
        IpoFinanceDateDto dateDto = new IpoFinanceDateDto();
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
        //存入时间列表
        dateDto.setFirstYearDate(getDateStr(firstYear));
        dateDto.setSecondYearDate(getDateStr(secondYear));
        dateDto.setThirdYearDate(getDateStr(thirdYear));
        dateDto.setForthYearDate(getDateStr(forthYear));
        resultDto.setDateList(dateDto);
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
            if ("应收账款".equals(forthItemList.get(i).getItemName())) {
                forthItemList.get(i).setItemName("应收票据及应收账款");
            }
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
            if ("应付账款".equals(forthDebtItemList.get(i).getItemName())) {
                forthDebtItemList.get(i).setItemName("应付票据及应付账款");
            }
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

    /**
     * 财务信息 财务总体情况
     */
    public IpoFinanceDto selectFinanceOverList(String id) {
        IpoFinanceDto resultDto = new IpoFinanceDto();
        IpoFinanceDateDto dateDto = new IpoFinanceDateDto();
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
        //存入时间
        dateDto.setFirstYearDate(getDateStr(firstYear));
        dateDto.setSecondYearDate(getDateStr(secondYear));
        dateDto.setThirdYearDate(getDateStr(thirdYear));
        dateDto.setForthYearDate(getDateStr(forthYear));
        resultDto.setDateList(dateDto);
        //根据时间和id查询财务信息，三年一期的 财务总体情况
        List<IpoItemDto> forthItemList = ipoFinanceMapper.selectFinanceOverList(id, forthYear);
        List<IpoItemDto> thirdItemList = ipoFinanceMapper.selectFinanceOverList(id, thirdYear);
        List<IpoItemDto> secondItemList = ipoFinanceMapper.selectFinanceOverList(id, secondYear);
        List<IpoItemDto> firstItemList = ipoFinanceMapper.selectFinanceOverList(id, firstYear);
        //循环一年的 财务总体情况，因为查询顺序相同，直接用角标合并4年数据
        for (int i = 0; i < forthItemList.size(); i++) {
            forthItemList.get(i).setThirdYearValue(thirdItemList.get(i).getForthYearValue());
            forthItemList.get(i).setSecondYearValue(secondItemList.get(i).getForthYearValue());
            forthItemList.get(i).setFirstYearValue(firstItemList.get(i).getForthYearValue());
        }
        //计算所有者权益合计
        IpoItemDto sumEquityDto = new IpoItemDto();
        BigDecimal forthSumEquity = forthItemList.get(2).getForthYearValue().subtract(forthItemList.get(5).getForthYearValue());
        BigDecimal thirdSumEquity = forthItemList.get(2).getThirdYearValue().subtract(forthItemList.get(5).getThirdYearValue());
        BigDecimal secondSumEquity = forthItemList.get(2).getSecondYearValue().subtract(forthItemList.get(5).getSecondYearValue());
        BigDecimal firstSumEquity = forthItemList.get(2).getFirstYearValue().subtract(forthItemList.get(5).getFirstYearValue());
        sumEquityDto.setItemName("所有者权益合计");
        sumEquityDto.setForthYearValue(forthSumEquity);
        sumEquityDto.setThirdYearValue(thirdSumEquity);
        sumEquityDto.setSecondYearValue(secondSumEquity);
        sumEquityDto.setFirstYearValue(firstSumEquity);
        //将所有者权益合计行 放入第3位，前台页面不用排序
        forthItemList.add(7, sumEquityDto);
        //为前台页面统一展示，将0的数据设置为Null
        for (int i = 0; i < 8; i++) {
            if (forthItemList.get(i).getForthYearValue() != null && forthItemList.get(i).getForthYearValue().compareTo(BigDecimal.ZERO) == 0) {
                forthItemList.get(i).setForthYearValue(null);
            }
            if (forthItemList.get(i).getThirdYearValue() != null && forthItemList.get(i).getThirdYearValue().compareTo(BigDecimal.ZERO) == 0) {
                forthItemList.get(i).setThirdYearValue(null);
            }
            if (forthItemList.get(i).getSecondYearValue() != null && forthItemList.get(i).getSecondYearValue().compareTo(BigDecimal.ZERO) == 0) {
                forthItemList.get(i).setSecondYearValue(null);
            }
            if (forthItemList.get(i).getFirstYearValue() != null && forthItemList.get(i).getFirstYearValue().compareTo(BigDecimal.ZERO) == 0) {
                forthItemList.get(i).setFirstYearValue(null);
            }
        }
        //移除财务信息中，三年一期全部为空的项目
        List<IpoItemDto> allItemList = removeNullItem(forthItemList);
        resultDto.setIpoFinanceOverList(allItemList);
        return resultDto;
    }

    /**
     * 财务信息 收入与利润情况
     */
    public IpoFinanceDto selectFinanceProfitList(String id) {
        IpoFinanceDto resultDto = new IpoFinanceDto();
        IpoFinanceDateDto dateDto = new IpoFinanceDateDto();
        Date forthYear = ipoFinanceMapper.getForthYear(id);
        //如果日期为空则说明没有填写主营收入
        if (forthYear == null) {
            return new IpoFinanceDto();
        }
        //计算前3年时间
        Date thirdYear = getLastYearDate(forthYear);
        Date secondYear = getLastYearDate(thirdYear);
        Date firstYear = getLastYearDate(secondYear);
        //存入时间
        dateDto.setFirstYearDate(getDateStr(firstYear));
        dateDto.setSecondYearDate(getDateStr(secondYear));
        dateDto.setThirdYearDate(getDateStr(thirdYear));
        dateDto.setForthYearDate(getDateStr(forthYear));
        resultDto.setDateList(dateDto);
        //根据时间和id查询财务信息，三年一期的 收入类项目
        List<IpoItemDto> forthItemList = ipoFinanceMapper.selectFinanceProfitList(id, forthYear);
        List<IpoItemDto> thirdItemList = ipoFinanceMapper.selectFinanceProfitList(id, thirdYear);
        List<IpoItemDto> secondItemList = ipoFinanceMapper.selectFinanceProfitList(id, secondYear);
        List<IpoItemDto> firstItemList = ipoFinanceMapper.selectFinanceProfitList(id, firstYear);
        //循环一年的 收入类项目 财务信息，因为查询顺序相同，直接用角标合并4年数据
        for (int i = 0; i < forthItemList.size(); i++) {
            forthItemList.get(i).setThirdYearValue(thirdItemList.get(i).getForthYearValue());
            forthItemList.get(i).setSecondYearValue(secondItemList.get(i).getForthYearValue());
            forthItemList.get(i).setFirstYearValue(firstItemList.get(i).getForthYearValue());
        }
        //移除财务信息中，三年一期全部为空的项目
        List<IpoItemDto> profitItemList = removeNullItem(forthItemList);
        resultDto.setIpoProfitItemList(profitItemList);
        //查询三年一期的 成本类项目 财务信息你
        List<IpoItemDto> forthCostItemList = ipoFinanceMapper.selectCostFinanceList(id, forthYear);
        List<IpoItemDto> thirdCostItemList = ipoFinanceMapper.selectCostFinanceList(id, thirdYear);
        List<IpoItemDto> secondCostItemList = ipoFinanceMapper.selectCostFinanceList(id, secondYear);
        List<IpoItemDto> firstCostItemList = ipoFinanceMapper.selectCostFinanceList(id, firstYear);
        for (int i = 0; i < forthCostItemList.size(); i++) {
            forthCostItemList.get(i).setThirdYearValue(thirdCostItemList.get(i).getForthYearValue());
            forthCostItemList.get(i).setSecondYearValue(secondCostItemList.get(i).getForthYearValue());
            forthCostItemList.get(i).setFirstYearValue(firstCostItemList.get(i).getForthYearValue());
        }
        //移除财务信息中，三年一期全部为空的项目
        List<IpoItemDto> costItemList = removeNullItem(forthCostItemList);
        resultDto.setIpoCostItemList(costItemList);
        //查询三年一期的 利润类项目
        List<IpoItemDto> forthReturnItemList = ipoFinanceMapper.selectReturnItemList(id, forthYear);
        List<IpoItemDto> thirdReturnItemList = ipoFinanceMapper.selectReturnItemList(id, thirdYear);
        List<IpoItemDto> secondReturnItemList = ipoFinanceMapper.selectReturnItemList(id, secondYear);
        List<IpoItemDto> firstReturnItemList = ipoFinanceMapper.selectReturnItemList(id, firstYear);
        for (int i = 0; i < forthReturnItemList.size(); i++) {
            forthReturnItemList.get(i).setThirdYearValue(thirdReturnItemList.get(i).getForthYearValue());
            forthReturnItemList.get(i).setSecondYearValue(secondReturnItemList.get(i).getForthYearValue());
            forthReturnItemList.get(i).setFirstYearValue(firstReturnItemList.get(i).getForthYearValue());
        }
        //移除财务信息中，三年一期全部为空的项目
        List<IpoItemDto> returnItemList = removeNullItem(forthReturnItemList);
        resultDto.setIpoReturnOverList(returnItemList);

        return resultDto;
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
     * 将日期转为字符串
     */
    private String getDateStr(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(date);
        return dateStr;
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

    /**
     * 财务信息 主要财务指标
     */
    public IpoFinanceDto selectMainIndexList(String id) {
        IpoFinanceDto resultDto = new IpoFinanceDto();
        IpoFinanceDateDto dateDto = new IpoFinanceDateDto();
        Date forthYear = ipoFinanceMapper.getForthYear(id);
        //如果日期为空则说明没有填写主营收入
        if (forthYear == null) {
            return new IpoFinanceDto();
        }
        //计算前3年时间
        Date thirdYear = getLastYearDate(forthYear);
        Date secondYear = getLastYearDate(thirdYear);
        Date firstYear = getLastYearDate(secondYear);
        //存入时间
        dateDto.setFirstYearDate(getDateStr(firstYear));
        dateDto.setSecondYearDate(getDateStr(secondYear));
        dateDto.setThirdYearDate(getDateStr(thirdYear));
        dateDto.setForthYearDate(getDateStr(forthYear));
        resultDto.setDateList(dateDto);
        //根据时间和id查询财务信息，三年一期的 主要财务指标
        List<IpoItemDto> forthItemList = ipoFinanceMapper.selectMainIndexList(id, forthYear);
        List<IpoItemDto> thirdItemList = ipoFinanceMapper.selectMainIndexList(id, thirdYear);
        List<IpoItemDto> secondItemList = ipoFinanceMapper.selectMainIndexList(id, secondYear);
        List<IpoItemDto> firstItemList = ipoFinanceMapper.selectMainIndexList(id, firstYear);
        //循环一年的 主要财务指标，因为查询顺序相同，直接用角标合并4年数据
        for (int i = 0; i < forthItemList.size(); i++) {
            forthItemList.get(i).setThirdYearValue(thirdItemList.get(i).getForthYearValue());
            forthItemList.get(i).setSecondYearValue(secondItemList.get(i).getForthYearValue());
            forthItemList.get(i).setFirstYearValue(firstItemList.get(i).getForthYearValue());
        }
        //根据时间和id查询财务信息，三年一期的 无形资产占净资产的比例
        List<IpoItemDto> forthRatio = ipoFinanceMapper.selectRatio(id, forthYear);
        List<IpoItemDto> thirdRatio = ipoFinanceMapper.selectRatio(id, thirdYear);
        List<IpoItemDto> secondRatio = ipoFinanceMapper.selectRatio(id, secondYear);
        List<IpoItemDto> firstRatio = ipoFinanceMapper.selectRatio(id, firstYear);
        IpoItemDto itemRatio = new IpoItemDto();
        //分别计算 无形资产与净资产比例
        if (firstRatio.size() == 2) {
            if (null != forthRatio.get(0).getForthYearValue() &&
                    null != forthRatio.get(1).getForthYearValue()) {
                Double ratio =
                        forthRatio.get(0).getForthYearValue()
                                .divide(forthRatio.get(1).getForthYearValue(), 4, BigDecimal.ROUND_HALF_UP)
                                .multiply(new BigDecimal("100")).doubleValue();
                itemRatio.setForthYearValue(BigDecimal.valueOf(ratio));
            }
            if (null != thirdRatio.get(0).getForthYearValue() &&
                    null != thirdRatio.get(1).getForthYearValue()) {
                Double ratio =
                        thirdRatio.get(0).getForthYearValue()
                                .divide(thirdRatio.get(1).getForthYearValue(), 4, BigDecimal.ROUND_HALF_UP)
                                .multiply(new BigDecimal("100")).doubleValue();
                itemRatio.setThirdYearValue(BigDecimal.valueOf(ratio));
            }
            if (null != secondRatio.get(0).getForthYearValue() &&
                    null != secondRatio.get(1).getForthYearValue()) {
                Double ratio =
                        secondRatio.get(0).getForthYearValue()
                                .divide(secondRatio.get(1).getForthYearValue(), 4, BigDecimal.ROUND_HALF_UP)
                                .multiply(new BigDecimal("100")).doubleValue();
                itemRatio.setSecondYearValue(BigDecimal.valueOf(ratio));
            }
            if (null != firstRatio.get(0).getForthYearValue() &&
                    null != firstRatio.get(1).getForthYearValue()) {
                Double ratio =
                        firstRatio.get(0).getForthYearValue()
                                .divide(firstRatio.get(1).getForthYearValue(), 4, BigDecimal.ROUND_HALF_UP)
                                .multiply(new BigDecimal("100")).doubleValue();
                itemRatio.setFirstYearValue(BigDecimal.valueOf(ratio));
            }
        }
        itemRatio.setItemName("无形资产占净资产的比例");
        //移除财务信息中，三年一期全部为空的项目
        forthItemList.add(3, itemRatio);
        List<IpoItemDto> returnItemList = removeNullItem(forthItemList);
        resultDto.setIpoMainIndexList(returnItemList);
        return resultDto;
    }


    /**
     * 查询H5财务信息
     */
    public IpoH5FinanceResultDto selectFinanceOverListH5(String id) {
        IpoH5FinanceResultDto resultDto = new IpoH5FinanceResultDto();
        IpoFinanceDateDto dateDto = new IpoFinanceDateDto();
        //先查询主营业务收入构成里面的时间，确定三年一期时间
        Date forthYear = ipoFinanceMapper.getForthYear(id);
        //如果日期为空则说明没有填写主营收入
        if (forthYear == null) {
            return new IpoH5FinanceResultDto();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String forthYearStr = sdf.format(forthYear);
        String year = String.format("%tY", forthYear);
        String mon = String.format("%tm", forthYear);
        String day = String.format("%td", forthYear);
        if (!"12-31".equals(mon + "-" + day)) {
            try {
                forthYearStr = String.valueOf(Integer.valueOf(year) - 1) + "-12-31";
                forthYear = sdf.parse(forthYearStr);
            } catch (ParseException e) {
                logger.error("ipoH5获取最近一年日期转换错误,caused by:{}", Throwables.getStackTraceAsString(e));
            }
        }
        //计算前3年时间
        Date thirdYear = getLastYearDate(forthYear);
        Date secondYear = getLastYearDate(thirdYear);
        Date firstYear = getLastYearDate(secondYear);
        //存入时间
        dateDto.setFirstYearDate(getDateStr(firstYear).substring(0,4));
        dateDto.setSecondYearDate(getDateStr(secondYear).substring(0,4));
        dateDto.setThirdYearDate(getDateStr(thirdYear).substring(0,4));
        dateDto.setForthYearDate(getDateStr(forthYear).substring(0,4));
        //根据时间和id查询财务信息，三年一期的 财务总体情况
        List<IpoItemDto> forthItemList = ipoFinanceMapper.selectFinanceOverListH5(id, forthYear);
        List<IpoItemDto> thirdItemList = ipoFinanceMapper.selectFinanceOverListH5(id, thirdYear);
        List<IpoItemDto> secondItemList = ipoFinanceMapper.selectFinanceOverListH5(id, secondYear);
        List<IpoItemDto> firstItemList = ipoFinanceMapper.selectFinanceOverListH5(id, firstYear);
        //循环一年的 财务总体情况，因为查询顺序相同，直接用角标合并4年数据
        for (int i = 0; i < forthItemList.size(); i++) {
            forthItemList.get(i).setThirdYearValue(thirdItemList.get(i).getForthYearValue());
            forthItemList.get(i).setSecondYearValue(secondItemList.get(i).getForthYearValue());
            forthItemList.get(i).setFirstYearValue(firstItemList.get(i).getForthYearValue());
        }
        //计算所有者权益合计
        IpoItemDto sumEquityDto = new IpoItemDto();
        BigDecimal forthSumEquity = forthItemList.get(2).getForthYearValue().subtract(forthItemList.get(5).getForthYearValue());
        BigDecimal thirdSumEquity = forthItemList.get(2).getThirdYearValue().subtract(forthItemList.get(5).getThirdYearValue());
        BigDecimal secondSumEquity = forthItemList.get(2).getSecondYearValue().subtract(forthItemList.get(5).getSecondYearValue());
        BigDecimal firstSumEquity = forthItemList.get(2).getFirstYearValue().subtract(forthItemList.get(5).getFirstYearValue());
        sumEquityDto.setItemName("所有者权益合计");
        sumEquityDto.setForthYearValue(forthSumEquity);
        sumEquityDto.setThirdYearValue(thirdSumEquity);
        sumEquityDto.setSecondYearValue(secondSumEquity);
        sumEquityDto.setFirstYearValue(firstSumEquity);
        //将所有者权益合计行 放入第3位，前台页面不用排序
        forthItemList.add(7, sumEquityDto);
        //为前台页面统一展示，将0的数据设置为Null
        for (int i = 0; i < forthItemList.size(); i++) {
            //计算各个复合增长率
            double growthRate = getGrowthRate(forthItemList.get(i));
            forthItemList.get(i).setGrowthRate(growthRate == 0D ? null : new BigDecimal(String.valueOf(growthRate)));
            if ("净利润".equals(forthItemList.get(i).getItemName())) {
                IpoItemDto profitGrowthRateItemDto = new IpoItemDto();
                BigDecimal profitGrowthRate = null;
                if (!(null == forthItemList.get(i).getForthYearValue() || forthItemList.get(i).getForthYearValue().compareTo(BigDecimal.ZERO) == 0
                        || null == forthItemList.get(i).getThirdYearValue() || forthItemList.get(i).getThirdYearValue().compareTo(BigDecimal.ZERO) == 0)) {
                    profitGrowthRate = forthItemList.get(i).getForthYearValue().
                            divide(forthItemList.get(i).getThirdYearValue(), 4, BigDecimal.ROUND_HALF_UP).
                            subtract(new BigDecimal("1")).multiply(new BigDecimal("100"));
                }
                profitGrowthRateItemDto.setItemName("净利润增长率");
                profitGrowthRateItemDto.setForthYearValue(profitGrowthRate);
                forthItemList.add(profitGrowthRateItemDto);
            }
        }
        //移除财务信息中，三年一期全部为空的项目
//        List<IpoItemDto> allItemList = removeNullItem(forthItemList);
        resultDto = transData(forthItemList,dateDto);
        resultDto.setDateList(dateDto);
        return resultDto;
    }

    //计算复合增长率
    private double getGrowthRate(IpoItemDto ipoItemDto) {
        if (null != ipoItemDto.getForthYearValue() && null != ipoItemDto.getSecondYearValue() && ipoItemDto.getSecondYearValue().compareTo(BigDecimal.ZERO) != 0) {
            double param = ipoItemDto.getForthYearValue().divide(ipoItemDto.getSecondYearValue(), 4, BigDecimal.ROUND_HALF_UP).doubleValue();
            double rate = 0;
            DecimalFormat df = new DecimalFormat("#.0000");
            if(param <0){
                if(ipoItemDto.getForthYearValue().compareTo(BigDecimal.ZERO) == -1){
                    param = param * -1;
                    rate = Math.pow(param, 1.0 / 3);
                    Double rateStr = Double.valueOf(df.format(rate));
                    rate = new BigDecimal((rateStr - 1D) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                }else if(ipoItemDto.getForthYearValue().compareTo(BigDecimal.ZERO) == 1){
                    param = param * -1;
                    rate = Math.pow(param, 1.0 / 3);
                    rate = rate * -1;
                    Double rateStr = Double.valueOf(df.format(rate));
                    rate = new BigDecimal(( 1D - rateStr) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                }
            }else{
                rate = Math.pow(param, 1.0 / 3);
                Double rateStr = Double.valueOf(df.format(rate));
                rate = new BigDecimal((rateStr - 1D) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            return rate;
        } else {
            return 0;
        }
    }

    private IpoH5FinanceResultDto transData(List<IpoItemDto> allItemList, IpoFinanceDateDto dateDto) {
        IpoH5FinanceResultDto resultDto = new IpoH5FinanceResultDto();
        List<IpoFinanceH5Dto> mainIndexList = new ArrayList<>();
        List<IpoH5FinanceListDto> debtList = new ArrayList<>();
        for (IpoItemDto dto : allItemList) {
            if ("营业收入".equals(dto.getItemName())) {
                List<IpoFinanceH5Dto> incomeList = insertFinanceData(dto, dateDto);
                IpoFinanceH5Dto incomeRate = new IpoFinanceH5Dto();
                incomeRate.setIssueData(dto.getGrowthRate());
                resultDto.setIncomeList(incomeList);
                resultDto.setIncomeRate(incomeRate);
            } else if ("销售毛利率".equals(dto.getItemName())) {
                dto.setItemName("毛利率");
                List<IpoFinanceH5Dto> grossList = insertFinanceData(dto, dateDto);
                resultDto.setGrossList(grossList);
            } else if ("净利润".equals(dto.getItemName())) {
                List<IpoFinanceH5Dto> profitList = insertFinanceData(dto, dateDto);
                IpoFinanceH5Dto profitRate = new IpoFinanceH5Dto();
                profitRate.setIssueData(dto.getGrowthRate());
                resultDto.setProfitList(profitList);
                resultDto.setProfitRate(profitRate);
            } else if ("销售净利率".equals(dto.getItemName())) {
                dto.setItemName("净利润率");
                List<IpoFinanceH5Dto> profitRateList = insertFinanceData(dto, dateDto);
                resultDto.setProfitRateList(profitRateList);
            } else if ("净资产收益率ROE(加权)".equals(dto.getItemName())) {
                dto.setItemName("加权平均净资产收益率");
                insertMainIndexData(dto, mainIndexList);
            } else if ("净利润增长率".equals(dto.getItemName())) {
                insertMainIndexData(dto, mainIndexList);
            } else if ("资产负债率".equals(dto.getItemName())) {
                insertMainIndexData(dto, mainIndexList);
            } else if ("总资产周转率".equals(dto.getItemName())) {
                insertMainIndexData(dto, mainIndexList);
            } else if ("经营活动产生的现金流量净额/营业收入".equals(dto.getItemName())) {
                dto.setItemName("销售现金比率");
                insertMainIndexData(dto, mainIndexList);
            } else if ("基本每股收益".equals(dto.getItemName())) {
                insertMainIndexData(dto, mainIndexList);
            } else if ("流动资产合计".equals(dto.getItemName())) {
                dto.setItemName("流动资产");
                insertDebtData(dto, debtList);
            } else if ("非流动资产合计".equals(dto.getItemName())) {
                dto.setItemName("非流动资产");
                insertDebtData(dto, debtList);
            } else if ("流动负债合计".equals(dto.getItemName())) {
                dto.setItemName("流动负债");
                insertDebtData(dto, debtList);
            } else if ("非流动负债合计".equals(dto.getItemName())) {
                dto.setItemName("非流动负债");
                insertDebtData(dto, debtList);
            } else if ("非流动负债合计".equals(dto.getItemName())) {
                dto.setItemName("非流动负债");
                insertDebtData(dto, debtList);
            } else if ("所有者权益合计".equals(dto.getItemName())) {
                insertDebtData(dto, debtList);
            } else if ("经营活动产生的现金流量净额".equals(dto.getItemName())) {
//                dto.setItemName("现金流量情况");
                List<IpoFinanceH5Dto> cashFlowList = insertFinanceData(dto, dateDto);
                IpoFinanceH5Dto cashFlowRate = new IpoFinanceH5Dto();
                cashFlowRate.setIssueData(dto.getGrowthRate());
                resultDto.setCashFlowList(cashFlowList);
                resultDto.setCashFlowRate(cashFlowRate);
            } else if ("现金及现金等价物净增加额".equals(dto.getItemName())) {
//                dto.setItemName("现金及现金等价物净增加额（亿元）");
                List<IpoFinanceH5Dto> cashList = insertFinanceData(dto, dateDto);
                IpoFinanceH5Dto cashRate = new IpoFinanceH5Dto();
                cashRate.setIssueData(dto.getGrowthRate());
                resultDto.setCashList(cashList);
                resultDto.setCashRate(cashRate);
            } else if ("资产总计".equals(dto.getItemName())) {
                IpoFinanceH5Dto sumAssetRate = new IpoFinanceH5Dto();
                sumAssetRate.setIssueData(dto.getGrowthRate());
                resultDto.setSumAssetRate(sumAssetRate);
            }
        }
        resultDto.setMainIndexList(mainIndexList);
        resultDto.setDebtList(debtList);
        return resultDto;
    }

    private List<IpoFinanceH5Dto> insertFinanceData(IpoItemDto dto, IpoFinanceDateDto dateDto) {
        List<IpoFinanceH5Dto> incomeList = new ArrayList<>();
        IpoFinanceH5Dto newDto = new IpoFinanceH5Dto();
        newDto.setYear(dateDto.getForthYearDate());
        newDto.setIssueData(dto.getForthYearValue());
        incomeList.add(newDto);
        newDto = new IpoFinanceH5Dto();
        newDto.setYear(dateDto.getThirdYearDate());
        newDto.setIssueData(dto.getThirdYearValue());
        incomeList.add(newDto);
        newDto = new IpoFinanceH5Dto();
        newDto.setYear(dateDto.getSecondYearDate());
        newDto.setIssueData(dto.getSecondYearValue());
        incomeList.add(newDto);
        return incomeList;
    }

    private void insertMainIndexData(IpoItemDto dto, List<IpoFinanceH5Dto> mainIndexList) {
        IpoFinanceH5Dto newDto = new IpoFinanceH5Dto();
        newDto.setName(dto.getItemName());
        newDto.setIssueData(dto.getForthYearValue());
        mainIndexList.add(newDto);
    }

    private void insertDebtData(IpoItemDto dto, List<IpoH5FinanceListDto> debtList) {
        IpoH5FinanceListDto newDto = new IpoH5FinanceListDto();
        newDto.setName(dto.getItemName());
        IpoFinanceH5Dto dataDto = new IpoFinanceH5Dto();
        dataDto.setIssueData(dto.getSecondYearValue());
        newDto.setBeforeYear(dataDto);
        dataDto = new IpoFinanceH5Dto();
        dataDto.setIssueData(dto.getThirdYearValue());
        newDto.setLastYear(dataDto);
        dataDto = new IpoFinanceH5Dto();
        dataDto.setIssueData(dto.getForthYearValue());
        newDto.setTodayYear(dataDto);
        debtList.add(newDto);
    }


}
