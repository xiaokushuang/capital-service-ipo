package com.stock.capital.enterprise.common.constant;

/**
 * 业务共同静态变量
 *
 * @author BAIYANG
 */
public interface Global {
    /**
     * 分隔符：逗号
     */
    public static final String SEPARATOR_COMMA = ",";
    
    /**
     * IPO在融资 —— 沪主板
     */
    public static final String OPO_BOARD_00 = "00";
    /**
     * IPO在融资 —— 深主板
     */
    public static final String OPO_BOARD_01 = "01";
    /**
     * IPO在融资 —— 中小板
     */
    public static final String OPO_BOARD_02 = "02";
    /**
     * IPO在融资 —— 创业板
     */
    public static final String OPO_BOARD_03 = "03";
	/**
     * 用户ID
     */
    public static final String PERSON_ID = "personId";
      /**
     * 披露要点类型
     */
    public static final String DECLARE_PONDER_TYPE = "1";
	/**
     * 报批材料类型
     */
    public static final String DECLARE_MATERIAL_TYPE = "0";

    /**
     * 股票代码
     */
    public static final String COMPANY_CODE = "companyCode";
    
    /**
     * 收藏的来源——易董企业端
     */
    public static final String FAVOURITE_SOURCE_ENTERPRISE = "1";
    
    /**
     * 公司ID
     */
    public static final String COMPANY_ID = "companyId";
}
