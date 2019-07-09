import request from '@/utils/request'

export function companyByCode(param) {
  return request({
    url: '/capital-cloud-api/company_info/portCloudCompanyInfo',
    // url: '/capital-cloud-api/home/companyQuery',
    method: 'post',
    params: param
  })
}
// 证券
export function searchformeary(param) {
  return request({
    url: `/ipo/financeStatistics/getChartForFinanceData`,
    method: 'post',
    data: param
  })
}
// 债券
export function searchBond(param) {
  return request({
    url: `/ipo/financeStatistics/getChartForBondData`,
    method: 'post',
    data: param
  })
}
// 行业分类
export function SFClassification() {
  return request({
    url: `/ipo/financeStatistics/getFinanceList`,
    method: 'post'
  })
}
// 所有地区
export function SFCRegion() {
  return request({
    url: `/ipo/financeStatistics/getProvincesList`,
    method: 'post'
  })
}
// 板块信息
export function plateInfo() {
  return request({
    url: `/ipo/financeStatistics/getStockBoardList`,
    method: 'post'
  })
}
// ipo数据概览
export function ipoDataOverview() {
  return request({
    url: `/ipo/regulatory_statistics/getIPOReviewingStts`,
    method: 'post'
  })
}
// ipo数据历史
export function ipoDataHistory() {
  return request({
    url: `/ipo/regulatory_statistics/getIPOHistory`,
    method: 'post'
  })
}
// 项目所属
export function projectBelong() {
  return request({
    url: `/ipo/declare/getAreaAndIndustry`,
    method: 'post'
  })
}
// IPO在审项目数据查询table
export function ipoAreaData(param) {
  return request({
    url: `/ipo/regulatory_statistics/getIPOAreaDataStts`,
    method: 'post',
    data: param
  })
}
export function getLawsByTable(param) {
  return request({
    url: `/ipo/declare/getRelationLawTable`,
    method: 'post',
    data: param
  })
}
export function refinanceApptype() {
  return request({
    url: `/ipo/regulatory_statistics/getRefinanceAppTypeStts?access_token=9c048686-7e44-4831-aee3-1469d6e83528`,
    method: 'post'
  })
}
export function refinanceRecommend() {
  return request({
    url: `/ipo/regulatory_statistics/getRefinanceRecommendOrgStts?access_token=9c048686-7e44-4831-aee3-1469d6e83528`,
    method: 'post'
  })
}
export function sponsorInstitution(param) {//ipo数据概览-保荐机构
  return request({
    url: `/ipo/regulatory_statistics/getIPORecommendOrgStts1`,
    method: 'post',
    data: param
  })
}
export function lawOffice(param) {//ipo数据概览-律师事务所
  return request({
    url: `/ipo/regulatory_statistics/getIPOLawFirmStts1`,
    method: 'post',
    data: param
  })
}
export function accountFirm(param) {//ipo数据概览-会计事务所
  return request({
    url: `/ipo/regulatory_statistics/getIPOAccountantOfficeStts1`,
    method: 'post',
    data: param
  })
}
export function refinanceApprove() {
  return request({
    url: `/ipo/regulatory_statistics/getRefinanceApproveStatusStts?access_token=9c048686-7e44-4831-aee3-1469d6e83528`,
    method: 'post'
  })
}
export function ipoQuery() {
  return request({
    url: `/ipo/regulatory_statistics/ipoQuery`,
    method: 'post'
  })
}
export function searchIndex(query) {//获取融资查询列表数据
  return request({
    url: `/ipo/financeStatistics/financeSearchData`,
    method: 'post',
    data: query
  })
}

export function getUserInfo(token) {
  return request({
    url: '/htrt/getUserInfo',
    url: '/htrt/getUserInfo',
    method: 'post',
    params: {
      token
    }
  })
}

export function generateRoutes() {
  return request({
    url: '/htrt/getRoutes',
    method: 'post'
  })
}

export function testto(info) {
  return request({
    url: `/htrt/testto?data=${info}`,
    method: 'get'
  })
}

export function financeSearchData(param) {
  return request({
    url: '/ipo/financeSearchData/financeSearchData',
    method: 'post',
    params: param
  })
}

export function searchCompanyDetail(query) {//获取公司详情页数据
  return request({
      url: '/ipo/financeStatistics/searchCompanyDetail',//controller路径
      method: 'post',//请求方式
      data: query,//参数
  })
}

export function getAllDropDownList(query) {//获取下拉列表
  return request({
      url: '/ipo/regulatory_statistics/getAllDropDownList',//controller路径
      method: 'post',//请求方式
      data: query,//参数
  })
}

export function getIpoDataOverviewDetail(query) {//获取ipo数据概览详情页数据
  return request({
      url: '/ipo/regulatory_statistics/getIpoDataOverviewDetail',//controller路径
      method: 'post',//请求方式
      data: query,//参数
  })
}
