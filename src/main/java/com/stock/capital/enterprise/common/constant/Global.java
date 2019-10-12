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
     * IPO  -- -- 科创板
     */
    public static final String OPO_BOARD_61 = "069001001006";
    /**
     *  证监局
     */
    public static final String IPO_SFC = "SFC";
    /**
     * 证监会行业
     */
    public static final String IPO_SFC_INDUSTRY="INDUSTRY_CSRC_2012";
    /**
     * 审核状态
     */
    public static final String IPO_VERIFY_RESULT = "IPO_VERIFY_RESULT";
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

    /**
     * 违规案列TypeCode
     */
    public static final String INDEX_MODULE_VIOLATE = "03";

    /**
     * 收藏的类型——法规
     */
    public static final String FAVOURITE_TYPE_LAWS = "0";
    /**
     * 收藏的类型——资讯
     */
    public static final String FAVOURITE_TYPE_NEWS = "1";
    /**
     * 收藏的类型——问题
     */
    public static final String FAVOURITE_TYPE_QUESTION = "2";
    /**
     * 收藏的类型——监管信息
     */
    public static final String FAVOURITE_TYPE_REGULATORY = "3";
    /**
     * 收藏的类型——违规案例
     */
    public static final String FAVOURITE_TYPE_VIOLATION = "4";
    /**
     * 收藏的来源——官网价值法库
     */
    public static final String FAVOURITE_SOURCE_SITE = "0";
    /**
     * 收藏的来源——易董APP
     */
    public static final String FAVOURITE_SOURCE_ENTERPRISE_APP = "2";

    /**
     * 监管信息索引名称
     */
    public static final String REGULATORY_INDEX_NAME = "regulatory";

    /**
     * 违规案例索引名称
     */
    public static final String VIOLATION_INDEX_NAME = "violation";
    /**
     * 处罚明细索引名称
     */
    public static final String PUNISHMENT_INDEX_NAME = "punishment";
    /**
     * 证券融资查询索引名称
     */
    public static final String FINANCE_INDEX_NAME = "finance";
    /**
     * 中国码值
     */
    public static final String COUNTRY_CN_CN = "472";
    /**
     * 中国香港码值
     */
    public static final String COUNTRY_CN_HK = "516";
    /**
     * 中国台湾码值
     */
    public static final String COUNTRY_CN_TW = "649";

    /**
     * 境外
     */
    public static final String COUNTRY_OUTSIDE = "outside";
    public static final String COUNTRY_INSIDE = "inside";

    /**
     * 案例类型
     */
    public static final String CASE_TYPE_ALL = "all";
    public static final String CASE__TYPE_IPO = "ipo";
    public static final String CASE_TYPE_FD = "ipofd";
    /**
     * ipo案例  使用索引服务器的类型  0 ES 1 solr
     */
    public  static final String SEARCH_SERVER_IPO_CASE_FLAG = "1";


}
