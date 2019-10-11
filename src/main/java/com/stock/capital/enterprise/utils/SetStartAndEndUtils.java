package com.stock.capital.enterprise.utils;

import com.stock.capital.enterprise.ipoCase.service.IpoCaseListService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

public class SetStartAndEndUtils {
    private static Logger logger = LoggerFactory.getLogger(SetStartAndEndUtils.class);

    /**
     * 通过反射将两个数据的数组转换到对象的字段中
     *
     * @param bo    对象
     * @param filed 字段名
     * @param type  字段类型 double string int date list
     * @param flag  double类型下，是否进行亿元转万元操作  date类型下 判断是否转String
     */
    public static void setStartAndEndByReflex(Object bo, String filed, String type, boolean flag) {
        try {
            Class boClass = bo.getClass();
            Class classType = null;
            if ("double".equals(type)) {
                classType = Double.class;
            } else if ("date".equals(type)) {
                if (flag) {
                    classType = String.class;
                } else {
                    classType = Date.class;
                }
            }
            String getMethodName = "get" + filed.substring(0, 1).toUpperCase() + filed.substring(1, filed.length());
//        get
            Method getFieldMethod = boClass.getMethod(getMethodName, null);
//        setStart名称
            String setMethodNameStart = null;
            String setMethodNameEnd = null;
            String setMethodNameList = null;
            Method setMethodStart = null;
            Method setMethodEnd = null;
            Method setMethodList = null;
            if (!"list".equals(type)) {
                setMethodNameStart = "set" + filed.substring(0, 1).toUpperCase() + filed.substring(1, filed.length()) + "Start";
                //        setEnd名称
                setMethodNameEnd = "set" + filed.substring(0, 1).toUpperCase() + filed.substring(1, filed.length()) + "End";
                //        获取setStart方法
                setMethodStart = boClass.getMethod(setMethodNameStart, classType);
                //        获取setEnd方法
                setMethodEnd = boClass.getMethod(setMethodNameEnd, classType);
            } else {
                setMethodNameList = "set" + filed.substring(0, 1).toUpperCase() + filed.substring(1, filed.length()) + "List";
            }
//            如果是double类型
            if ("double".equals(type)) {
                Double[] fieldValue = (Double[]) getFieldMethod.invoke(bo, null);
                if (fieldValue != null && fieldValue.length > 0) {
                    if (null != fieldValue[0]) {
                        Double start = null;
                        if (flag) {
                            start = highFilterMoney(fieldValue[0]);
                        } else {
                            start = fieldValue[0];
                        }
                        setMethodStart.invoke(bo, start);
                    }
                    if (fieldValue.length > 1 && null != fieldValue[1]) {
                        Double end = null;
                        if (flag) {
                            end = highFilterMoney(fieldValue[1]);
                        } else {
                            end = fieldValue[1];
                        }
                        setMethodEnd.invoke(bo, end);
                    }
                }
            }
            //            如果是date类型
            else if ("date".equals(type)) {
                Date[] fieldValue = (Date[]) getFieldMethod.invoke(bo, null);
                if (fieldValue != null && fieldValue.length > 0) {
                    if (flag) {
                        String from = null;
                        String to = null;

                        DateTime start = new DateTime(fieldValue[0]);
                        from = start.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
                                .withZoneUTC());
                        DateTime end = new DateTime(fieldValue[1]);
                        to = end.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
                                .withZoneUTC());

                        setMethodStart.invoke(bo, from);
                        setMethodEnd.invoke(bo, to);
                    } else {
                        if (null != fieldValue[0]) {
                            setMethodStart.invoke(bo, fieldValue[0]);
                            if (fieldValue.length > 1 && null != fieldValue[1]) {
                                setMethodEnd.invoke(bo, fieldValue[1]);
                            }
                        }
                    }
                }
            } else if ("list".equals(type)) {

            } else if ("int".equals(type)) {
                Integer[] fieldValue = (Integer[]) getFieldMethod.invoke(bo, null);
                if (fieldValue != null && fieldValue.length > 0) {
                    if (null != fieldValue[0]) {
                        setMethodStart.invoke(bo, fieldValue[0]);
                        if (fieldValue.length > 1 && null != fieldValue[1]) {
                            setMethodEnd.invoke(bo, fieldValue[1]);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("设置字段值报错:" + e.getMessage(), new RuntimeException());
        }
    }

    //    亿元转万元
    private static Double highFilterMoney(Double aDouble) {
        return BigDecimal.valueOf(aDouble).multiply(new BigDecimal("10000")).doubleValue();
    }
}
