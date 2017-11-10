package com.stock.capital.enterprise.regulatory.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.stock.capital.enterprise.regulatory.dto.StatisticsResultDto;
import com.stock.core.dto.JsonResponse;
import com.stock.core.rest.RestClient;
import com.stock.core.service.BaseService;
import com.stock.core.util.DateUtil;

@Service
public class StatisticsService extends BaseService {

    /**
     * 接口调用
     */
    @Autowired
    private RestClient restClient;

    /**
     * 接口地址前缀
     */
    @Value("${api.baseUrl}")
    private String apiBaseUrl;

    /**
     * IPO在审项目数据统计
     *
     * @param letterId
     * @return
     */
    public List<StatisticsResultDto> getIPOReviewingStts() {
        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        String url = apiBaseUrl + "regulatory_statistics/getIPOReviewingStts";
        List<StatisticsResultDto> response = restClient.post(url, "", responseType).getResult();
        return response;
    }

    /**
     * IPO保荐机构统计
     *
     * @param letterId
     * @return
     */
    public List<StatisticsResultDto> getIPORecommendOrgStts() {
        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        String url = apiBaseUrl + "regulatory_statistics/getIPORecommendOrgStts";
        List<StatisticsResultDto> response = restClient.post(url, "", responseType).getResult();
        return response;
    }

    /**
     * IPO会计师事务所统计
     *
     * @param letterId
     * @return
     */
    public List<StatisticsResultDto> getIPOAccountantOfficeStts() {
        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        String url = apiBaseUrl + "regulatory_statistics/getIPOAccountantOfficeStts";
        List<StatisticsResultDto> response = restClient.post(url, "", responseType).getResult();
        return response;
    }

    /**
     * IPO律师事务所统计
     *
     * @param letterId
     * @return
     */
    public List<StatisticsResultDto> getIPOLawFirmStts() {
        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        String url = apiBaseUrl + "regulatory_statistics/getIPOLawFirmStts";
        List<StatisticsResultDto> response = restClient.post(url, "", responseType).getResult();
        return response;
    }

    /**
     * IPO最新一期日期获取
     *
     * @param letterId
     * @return
     */
    public String getIPOLastTime() {
        ParameterizedTypeReference<JsonResponse<String>> responseType = new ParameterizedTypeReference<JsonResponse<String>>() {
        };
        String url = apiBaseUrl + "regulatory_statistics/getIPOLastTime";
        String response = restClient.post(url, "", responseType).getResult();
        if (!StringUtils.isEmpty(response)) {
            Date updateTime = DateUtil.getDate(response, DateUtil.YYYY_MM_DD);
            response = DateUtil.getDateStr(updateTime, DateUtil.YYYY_MM_DD_ZH);
        }
        return response;
    }

    /**
     * 发行监管部再融资企业审核状态
     *
     * @param letterId
     * @return
     */
    public List<StatisticsResultDto> getRefinanceApproveStatusStts() {
        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        String url = apiBaseUrl + "regulatory_statistics/getRefinanceApproveStatusStts";
        List<StatisticsResultDto> response = restClient.post(url, "", responseType).getResult();
        return response;
    }

    /**
     * 再融资申请类型情况
     *
     * @param letterId
     * @return
     */
    public List<StatisticsResultDto> getRefinanceAppTypeStts() {
        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        String url = apiBaseUrl + "regulatory_statistics/getRefinanceAppTypeStts";
        List<StatisticsResultDto> response = restClient.post(url, "", responseType).getResult();
        return response;
    }

    /**
     * 发行监管部再融资审核保荐机构保荐企业数量
     *
     * @param letterId
     * @return
     */
    public List<StatisticsResultDto> getRefinanceRecommendOrgStts() {
        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        String url = apiBaseUrl + "regulatory_statistics/getRefinanceRecommendOrgStts";
        List<StatisticsResultDto> response = restClient.post(url, "", responseType).getResult();
        return response;
    }

    /**
     * 再融资最新一期日期获取
     *
     * @param letterId
     * @return
     */
    public String getRefinanceLastTime() {
        ParameterizedTypeReference<JsonResponse<String>> responseType = new ParameterizedTypeReference<JsonResponse<String>>() {
        };
        String url = apiBaseUrl + "regulatory_statistics/getRefinanceLastTime";
        String response = restClient.post(url, "", responseType).getResult();
        if (!StringUtils.isEmpty(response)) {
            Date updateTime = DateUtil.getDate(response, DateUtil.YYYY_MM_DD);
            response = DateUtil.getDateStr(updateTime, DateUtil.YYYY_MM_DD_ZH);
        }
        return response;
    }

}
