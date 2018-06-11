package com.stock.capital.enterprise.declare.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps; 
import com.stock.capital.enterprise.declare.dto.DeclareTypeTreeDto;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.TreeDto;
import com.stock.core.dto.UserInfo;
import com.stock.core.file.FileServer;
import com.stock.core.log.LogEvent;
import com.stock.core.rest.RestClient;
import com.stock.core.service.BaseService;
import com.stock.core.util.BeanUtil;
import com.stock.core.util.CompressUtil;
import com.stock.core.util.DateUtil;
import com.stock.core.util.JsonUtil;
import com.stock.core.util.WebUtil;
import com.stock.core.workflow.dao.WfHiProcInstMapper;
import com.stock.core.workflow.entity.WfHiProcInst;
import com.stock.core.workflow.entity.WfHiProcInstExample;

/**
 * 信息披露画面Service
 *
 * @author xuehui
 */
@Service
public class DeclareService extends BaseService {
    /**
     * 文件上传存储目录
     */
	  /**
     * 接口地址前缀
     */
    @Value("${api.baseUrl}")
    private String apiBaseUrl;
//    private String apiBaseUrl = "http://dev-sz.valueonline.cn/capital-cloud-api/";
 
    /**
     * 接口调用
     */
    @Autowired
    private RestClient restClient;
    
    @Autowired
    private RestTemplate restTemplate;
  

    @Autowired
    DeclareService declareService;
   
    
    /**
     * 根据stockBoard获取业务分类树数据
     *
     * @param stockBoard
     */
    public List<DeclareTypeTreeDto> getDeclareType(String stockBoard) {
        // DeclareTypeExample dte = new DeclareTypeExample();
        // List<DeclareTypeTreeDto> declareTypeTreeList = new ArrayList<DeclareTypeTreeDto>();
        // DeclareTypeTreeDto dttd = null;
        // dte.createCriteria().andTypeNoEqualTo(StockBoard.getStockBlock(companyCode));
        // List<DeclareType> declareTypeList = declareTypeMapper.selectByExample(dte);
        // if(declareTypeList.size()>0){
        // for(DeclareType declareType:declareTypeList){
        // dttd = new DeclareTypeTreeDto();
        // BeanUtil.copy(declareType, dttd);
        // dttd.setShowTypeName("("+declareType.getDeclareTypeNo()+")"+declareType.getDeclareTypeName());
        // declareTypeTreeList.add(dttd);
        // }
        // }
        String url = apiBaseUrl + "declareInfo/postDeclareTypeTree";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        //公司所属板块
        params.add("companyCode", "00");
        ParameterizedTypeReference<List<DeclareTypeTreeDto>> responseType = new ParameterizedTypeReference<List<DeclareTypeTreeDto>>() {
        };
        List<DeclareTypeTreeDto> list =   restClient.post(url, params, responseType);
        return restClient.post(url, params, responseType);
    }
}
