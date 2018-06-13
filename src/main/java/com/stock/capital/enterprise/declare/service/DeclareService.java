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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps; 
import com.stock.capital.enterprise.common.constant.Global;
import com.stock.capital.enterprise.common.dao.DeclareIdeaMapper; 
import com.stock.capital.enterprise.common.entity.DeclareIdea;
import com.stock.capital.enterprise.common.entity.DeclareIdeaExample;
import com.stock.capital.enterprise.common.entity.MaterialPonderInfo;
import com.stock.capital.enterprise.common.entity.MaterialPonderMap;
import com.stock.capital.enterprise.declare.dto.DecalreRelationLawDto;
import com.stock.capital.enterprise.declare.dto.DeclareFileDto;
import com.stock.capital.enterprise.declare.dto.DeclareTypeTreeDto;
import com.stock.capital.enterprise.declare.dto.NoticeHelperDto;
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
    DeclareIdeaMapper declareIdeaMapper;

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
    
    /**
     * 业务分类帮助数据获取
     *
     * @param typeId
     *            业务分类id
     * @param typeName
     *            业务分类名
     */
    public NoticeHelperDto getHelperData(String typeId, String typeName) {
        NoticeHelperDto nhd = new NoticeHelperDto();
        // MaterialPonderInfoExample example = new MaterialPonderInfoExample();
        // example.createCriteria().andTypeIdEqualTo(typeId);
        // example.setOrderByClause("type,sort");
        // //披露要点和报批材料获取
        // List<MaterialPonderInfo> mlist = materialPonderInfoMapper.selectByExample(example);
        String urls = apiBaseUrl + "declareInfo/postMaterialPonderInfo";
        MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
        param.add("typeId", typeId);
        ParameterizedTypeReference<List<MaterialPonderInfo>> responseType = new ParameterizedTypeReference<List<MaterialPonderInfo>>() {
        };
        List<MaterialPonderInfo> mlist = restClient.post(urls, param, responseType);
        List<String> materialList = new ArrayList<String>();
        List<String> ponderList = new ArrayList<String>();
        for (MaterialPonderInfo mpi : mlist) {
            if (Global.DECLARE_MATERIAL_TYPE.equals(mpi.getType())) {
                materialList.add(mpi.getPointContext());
            } else if (Global.DECLARE_PONDER_TYPE.equals(mpi.getType())) {
                ponderList.add(mpi.getPointContext());
            }
        }
       //tab2画面披露要点取数据(从云端取数据)
        List<MaterialPonderMap> ponderList1 = new ArrayList<MaterialPonderMap>();
        for(int i =0;i<mlist.size();i++){
        	MaterialPonderMap materialPonderMap  = new MaterialPonderMap();
        	if (Global.DECLARE_PONDER_TYPE.equals(mlist.get(i).getType())) {
        		materialPonderMap.setTypeId(mlist.get(i).getTypeId());
            	materialPonderMap.setPointContext(mlist.get(i).getPointContext());
            	materialPonderMap.setType(mlist.get(i).getType());
            	materialPonderMap.setId(mlist.get(i).getId());
            	ponderList1.add(materialPonderMap);
        	}
        }
        nhd.setMaterial(materialList);
        nhd.setPonder(ponderList);
        //tab2画面披露要点取数据
        nhd.setPonderList(ponderList1);
        // 相关法律法规获取
        String url = apiBaseUrl + "laws_manage/declareRelationLaw";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("typeId", typeId);
        ParameterizedTypeReference<List<DecalreRelationLawDto>> responseType1 = new ParameterizedTypeReference<List<DecalreRelationLawDto>>() {
        };
        List<DecalreRelationLawDto> lawRule = restClient.post(url, params, responseType1);
        // 自我心得
        DeclareIdeaExample declareIdeaExample = new DeclareIdeaExample();
        // 获取人员ID
        String personId = "2";//getUserInfo().getInfo().get(Global.PERSON_ID).toString();
        declareIdeaExample.createCriteria().andDeclareTypeIdEqualTo(typeId).andPrivateStatusEqualTo("0");
        declareIdeaExample.or(declareIdeaExample.createCriteria().andDeclareTypeIdEqualTo(typeId)
                .andPersonIdEqualTo(personId).andPrivateStatusEqualTo("1"));
      //  List<DeclareIdea> declareIdeaList = declareIdeaMapper.selectByExample(declareIdeaExample);
        nhd.setTypeId(typeId);
        nhd.setTypeName(typeName);
        nhd.setLawRule(lawRule);
      //  nhd.setDeclareIdeaList(declareIdeaList);
        nhd.setPersonId(personId);
        return nhd;
    }
    /**
     * 取得模板文件
     *
     * @param typeId
     *            业务分类id
     * @param typeName
     *            业务分类名称
     * @param declareTypeNo
     *            业务分类编号
     */
    public List<DeclareFileDto> getTempalteFile(String typeId, String typeName, String declareTypeNo) {
        // DeclareFileExample dfe = new DeclareFileExample();
        // List<DeclareFileDto> dfdList = new ArrayList<DeclareFileDto>();
        // DeclareFileDto dfd = null;
        // AttachmentExample ae = null;
        // List<Attachment> attList = null;
        // dfe.createCriteria().andDeclareIdIsNull().andTypeIdEqualTo(typeId);
        // List<DeclareFile> dfList = declareFileMapper.selectByExample(dfe);
        // if(dfList.size()>0){
        // for(DeclareFile df:dfList){
        // dfd = new DeclareFileDto();
        // BeanUtil.copy(df, dfd);
        // ae = new AttachmentExample();
        // ae.createCriteria().andBusinessIdEqualTo(df.getId());
        // attList = attachmentMapper.selectByExample(ae);
        // if(attList.size()>0){
        // dfd.setAttName(attList.get(0).getAttName());
        // dfd.setFileId(attList.get(0).getId());
        // }
        // dfd.setTypeName(typeName);
        // dfd.setDeclareTypeNo(declareTypeNo);
        // dfdList.add(dfd);
        // }
        // }
        String url = apiBaseUrl + "declareInfo/postDeclareTempalteFile";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("typeId", typeId);
        params.add("typeName", typeName);
        params.add("declareTypeNo", declareTypeNo);
        ParameterizedTypeReference<List<DeclareFileDto>> responseType = new ParameterizedTypeReference<List<DeclareFileDto>>() {
        };
        return restClient.post(url, params, responseType);
    }
   
}
