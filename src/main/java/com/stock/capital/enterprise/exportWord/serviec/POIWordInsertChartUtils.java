package com.stock.capital.enterprise.exportWord.serviec;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Component
public class POIWordInsertChartUtils {


    /**
     * 创建图表后  需要移除最后一个元素,此时chart对象依旧存在,才能对此chart对象操作并通过attach绑定。
     * @param xdoc
     * @return
     */
    public XWPFChart createChart(XWPFDocument xdoc){
        XWPFChart chart = null;
        try {
            chart = xdoc.createChart();
            xdoc.removeBodyElement(xdoc.getBodyElements().size()-1);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chart;
    }

    /**
     * 将chart 关联到 run上   前提调用此工具栏createChart方法创建的chart
     * @param chart
     * @param relationId
     * @param newRun
     * @throws NoSuchMethodException
     */
    public void attachChartToRun(XWPFChart chart,String relationId,XWPFRun newRun) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        java.lang.reflect.Method attach = XWPFChart.class.getDeclaredMethod("attach", String.class, XWPFRun.class);
        attach.setAccessible(true);
        attach.invoke(chart, relationId, newRun);
    }

    private  final int COLUMN_LANGUAGES = 0;

    /**
     *
     * @param chart 图表
     * @param ifStack   是否是堆积柱状图
     * @param chartTitle  图表标题
     * @param bottomTitle x轴总标题
     * @param yTitles y轴标题
     * @param xTitles  x轴平分标题
     * @param yValues 数据
     */
    public void setSimpleBarData(XWPFChart chart,String ifStack, String chartTitle,String bottomTitle, List<String> yTitles,List<String> xTitles,List<List<Double>> yValues){
        XDDFChartAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        bottomAxis.setTitle(bottomTitle);
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
//        设置左部名称
//        if (!yTitles.isEmpty() && yTitles.size() == 1){
//            leftAxis.setTitle(yTitles.get(0));
//        }else{
////            TODO  多个可能的处理逻辑
//            leftAxis.setTitle(yTitles.get(0));
//        }
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
        leftAxis.setMajorTickMark(AxisTickMark.OUT);
        leftAxis.setCrossBetween(AxisCrossBetween.BETWEEN);

        final int numOfPoints = xTitles.size();
//        分类
        final String categoryDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, COLUMN_LANGUAGES, COLUMN_LANGUAGES));
        String[] categories = xTitles.toArray(new String[0]);
        final XDDFDataSource<?> categoriesData = XDDFDataSourcesFactory.fromArray(categories, categoryDataRange, COLUMN_LANGUAGES);
        //创建bar--------!!!!!!
        if ("0".equals(ifStack) || "1".equals(ifStack) ){
            XDDFBarChartData bar = (XDDFBarChartData) chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
            if (!yValues.isEmpty()){
                for (int i = 0;i<yValues.size();i++) {
                    List<Double> yValue = yValues.get(i);
                    Double[] value = yValue.toArray(new Double[0]);
                    final String valuesDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, i+1, i+1));
                    final XDDFNumericalDataSource<? extends Number> valuesData = XDDFDataSourcesFactory.fromArray(value, valuesDataRange, i+1);
                    valuesData.setFormatCode("General");
                    XDDFBarChartData.Series series1 = (XDDFBarChartData.Series) bar.addSeries(categoriesData, valuesData);
                    if (yTitles.size() > 1){
                        series1.setTitle(yTitles.get(i), chart.setSheetTitle(yTitles.get(i), i+1));
                    }else{
                        series1.setTitle(yTitles.get(0), chart.setSheetTitle(yTitles.get(0), i+1));
                    }
                }
            }
            bar.setVaryColors(true);
            bar.setBarDirection(BarDirection.COL);
//        堆积柱状图
            if ("1".equals(ifStack)){
                bar.setBarGrouping(BarGrouping.STACKED);
                chart.getCTChart().getPlotArea().getBarChartArray(0).addNewOverlap().setVal((byte)100);
            }
            chart.plot(bar);
        }else if ("2".equals(ifStack)){//饼图
            XDDFPieChartData bar = (XDDFPieChartData) chart.createData(ChartTypes.PIE, bottomAxis, leftAxis);
            if (!yValues.isEmpty()){
                for (int i = 0;i<yValues.size();i++) {
                    List<Double> yValue = yValues.get(i);
                    Double[] value = yValue.toArray(new Double[0]);
                    final String valuesDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, i+1, i+1));
                    final XDDFNumericalDataSource<? extends Number> valuesData = XDDFDataSourcesFactory.fromArray(value, valuesDataRange, i+1);
                    valuesData.setFormatCode("General");
                    XDDFPieChartData.Series series1 = (XDDFPieChartData.Series) bar.addSeries(categoriesData, valuesData);
                    if (yTitles.size() > 1){
                        series1.setTitle(yTitles.get(i), chart.setSheetTitle(yTitles.get(i), i+1));
                    }else{
                        series1.setTitle(yTitles.get(0), chart.setSheetTitle(yTitles.get(0), i+1));
                    }
                }
            }
            bar.setVaryColors(true);
//            bar.setBarDirection(BarDirection.COL);
            chart.plot(bar);
        }else if ("3".equals(ifStack)){//折线
            XDDFLineChartData bar = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);
            if (!yValues.isEmpty()){
                for (int i = 0;i<yValues.size();i++) {
                    List<Double> yValue = yValues.get(i);
                    Double[] value = yValue.toArray(new Double[0]);
                    final String valuesDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, i+1, i+1));
                    final XDDFNumericalDataSource<? extends Number> valuesData = XDDFDataSourcesFactory.fromArray(value, valuesDataRange, i+1);
                    valuesData.setFormatCode("General");
                    XDDFLineChartData.Series series1 = (XDDFLineChartData.Series) bar.addSeries(categoriesData, valuesData);
                    if (yTitles.size() > 1){
                        series1.setTitle(yTitles.get(i), chart.setSheetTitle(yTitles.get(i), i+1));
                    }else{
                        series1.setTitle(yTitles.get(0), chart.setSheetTitle(yTitles.get(0), i+1));
                    }
                }
            }
            bar.setVaryColors(true);
//            bar.setBarDirection(BarDirection.COL);
            chart.plot(bar);
        }

        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.BOTTOM);
        legend.setOverlay(false);

        chart.setTitleText(chartTitle);
        chart.setTitleOverlay(false);
        chart.setAutoTitleDeleted(false);
    }

}
