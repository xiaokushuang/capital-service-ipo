import request from '@/utils/request'

// export function getGqList(query) {
//   return request({
//     url: '/companyProfile/gqList',
//     method: 'get',
//     params: query
//   })
// }
export function getGqTable(query) {
  return request({
    url: '/companyProfile/gqTable',
    method: 'get',
    params: query
  })
}
// IPO公司概览返回类
export function getCaseDetail() {
  var query = {
    id:'97952444248336801'
  }
  return request({
    url: '/ipo/api/companyOverview/caseDetail',
    method: 'get',
    params: query
  })
}
// 资本市场接口描述
export function getMarketData() {
  var query = {
    id:'97952444248336801'
  }
  return request({
    url: '/ipo/api/companyOverview/marketData',
    method: 'get',
    params: query
  })
}
// 股东信息接口描述
export function getShareHolderData() {
  var query = {
    id:'97952444248336801'
  }
  return request({
    url: '/ipo/api/companyOverview/shareHolderData',
    method: 'get',
    params: query
  })
}
// 主要竞争对手接口描述
export function getCompetitorData() {
  var query = {
    id:'97952444248336801'
  }
  return request({
    url: '/ipo/api/companyOverview/competitorData',
    method: 'get',
    params: query
  })
}
// 中介机构接口
export function getIntermediaryOrgDataList(query) {
  return request({
    url: '/companyProfile/intermediaryOrgDataList',
    method: 'get',
    params: query
  })
}