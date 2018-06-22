import request from '@/utils/request'
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
export function plateInfo(param) {
  return request({
    url: `/ipo/financeStatistics/getStockBoardList`,
    method: 'post',
    data: param
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
export function searchIndex(param) {
  return request({
    url: `/ipo/financeStatistics/financeSearchData?financeIndustry=001`,
    method: 'post',
    data: param
  })
}

export function getUserInfo(token) {
  return request({
    url: '/htrt/getUserInfo', url: '/htrt/getUserInfo',
    method: 'post',
    params: {token}
  })
}

export function generateRoutes() {
  return request({
    url: '/htrt/getRoutes',
    method: 'post'
  })
}

export function testto(info){
  return request({
    url:`/htrt/testto?data=${info}`,
    method:'get'
  })
}

export function financeSearchData(param){
  return request({
    url:"/ipo/financeSearchData/financeSearchData",
    method:'post',
    params:param
  })
}