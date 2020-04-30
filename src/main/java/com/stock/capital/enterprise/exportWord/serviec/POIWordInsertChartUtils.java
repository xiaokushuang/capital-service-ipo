package com.stock.capital.enterprise.exportWord.serviec;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.PresetColor;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
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
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.TOP);
//        设置左部名称
//        if (!yTitles.isEmpty() && yTitles.size() == 1){
//            leftAxis.setTitle(yTitles.get(0));
//        }else{
//            TODO  多个可能的处理逻辑

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
                    if ("0".equals(ifStack) ){
                        solidFillSeries(bar,i,PresetColor.CORNFLOWER_BLUE);
                    }
                }
            }
            bar.setVaryColors(true);
            bar.setBarDirection(BarDirection.COL);
//        堆积柱状图
            if ("1".equals(ifStack)){
//                leftAxis.setTitle("单位：万元");
                bar.setBarGrouping(BarGrouping.STACKED);
                chart.getCTChart().getPlotArea().getBarChartArray(0).addNewOverlap().setVal((byte)100);
                XDDFChartLegend legend = chart.getOrAddLegend();
                legend.setPosition(LegendPosition.BOTTOM);
                legend.setOverlay(false);
            }
            chart.plot(bar);
            if ("0".equals(ifStack)){
                for (int s = 0 ; s < yValues.size(); s++) {
                    chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(s).addNewDLbls();
                    chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(s).getDLbls()
                            .addNewDLblPos().setVal(org.openxmlformats.schemas.drawingml.x2006.chart.STDLblPos.CTR);
                    chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(s).getDLbls().addNewShowVal().setVal(true);
                    chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(s).getDLbls().addNewShowLegendKey().setVal(false);
                    chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(s).getDLbls().addNewShowCatName().setVal(false);
                    chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(s).getDLbls().addNewShowSerName().setVal(false);
                }
            }

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
            XDDFChartLegend legend = chart.getOrAddLegend();
            legend.setPosition(LegendPosition.BOTTOM);
            legend.setOverlay(false);
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
            XDDFChartLegend legend = chart.getOrAddLegend();
            legend.setPosition(LegendPosition.BOTTOM);
            legend.setOverlay(false);
        }

        if ("1".equals(ifStack)){
            CTChart ctChart = chart.getCTChart();
            CTTitle ctTitle = ctChart.addNewTitle();
            ctTitle.addNewOverlay().setVal(false);// true时与饼图重叠
            ctTitle.addNewTx().addNewRich().addNewBodyPr();
            CTTextBody rich = ctTitle.getTx().getRich();
            rich.addNewLstStyle();
            CTRegularTextRun newR = rich.addNewP().addNewR();
            newR.addNewRPr().setB(false);
            newR.getRPr().setSz(1100);
            newR.setT("                                                                                     单位：万元");
        }

        if ("0".equals(ifStack)){
            chart.setTitleText(chartTitle);
        }
        chart.setTitleOverlay(false);
        chart.setAutoTitleDeleted(false);
    }

    private  void solidFillSeries(XDDFChartData data, int index, PresetColor color) {
        XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(color));
        XDDFChartData.Series series = data.getSeries().get(index);
        XDDFShapeProperties properties = series.getShapeProperties();
        if (properties == null) {
            properties = new XDDFShapeProperties();
        }
        properties.setFillProperties(fill);
        series.setShapeProperties(properties);
    }

    public void setSimpleBarDataLine(XWPFChart chart,String ifStack, String chartTitle,String bottomTitle, List<String> yTitles,List<List<String>> xTitles,List<List<Double>> yValues) {
        XDDFChartAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        bottomAxis.setTitle(bottomTitle);
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);

        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
        leftAxis.setMajorTickMark(AxisTickMark.OUT);
        leftAxis.setCrossBetween(AxisCrossBetween.BETWEEN);

//        final int numOfPoints = xTitles.size();
//        分类
//        final String categoryDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, COLUMN_LANGUAGES, COLUMN_LANGUAGES));
//        String[] categories = xTitles.toArray(new String[0]);
//        final XDDFDataSource<?> categoriesData = XDDFDataSourcesFactory.fromArray(categories, categoryDataRange, COLUMN_LANGUAGES);

        XDDFLineChartData bar = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);
        if (!xTitles.isEmpty()){
            for (int i = 0;i<xTitles.size();i++){
                List<String> xValue = xTitles.get(i);
                int numOfPoints = xValue.size();
                String categoryDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, COLUMN_LANGUAGES, COLUMN_LANGUAGES));
                String[] categories = xValue.toArray(new String[0]);
                XDDFDataSource<?> categoriesData = XDDFDataSourcesFactory.fromArray(categories, categoryDataRange, COLUMN_LANGUAGES);
                if (!yValues.isEmpty()){
//                    for (int j = 0;j<yValues.size();j++) {
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
        }
        bar.setVaryColors(true);
        chart.plot(bar);
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.BOTTOM);
        legend.setOverlay(false);
        chart.setTitleText(chartTitle);
        chart.setTitleOverlay(false);
        chart.setAutoTitleDeleted(false);
    }
}
