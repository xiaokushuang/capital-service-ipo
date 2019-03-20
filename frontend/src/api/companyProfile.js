import request from '@/utils/request'
// [moke]
// export function getGqList(query) {
//   return request({
//     url: '/companyProfile/gqList',
//     method: 'get',
//     params: query
//   })
// }

// export function getGqTable(query) {
//   return request({
//     url: '/companyProfile/gqTable',
//     method: 'get',
//     params: query
//   })
// }

// IPO公司概览返回类[moke]
// export function getCaseDetail() {
//   return request({
//     url: '/finance/selectFinanceOverList',
//     method: 'get'
//   })
// }
// 募集资金接口
// export function getRaiseMoneyTableList(query) {
//   return request({
//     url: '/companyProfile/raiseMoneyTableList',
//     method: 'get',
//     params: query
//   })
// }

// 中介机构moke接口
// export function getIntermediaryOrgDataList(query) {
//   return request({
//     url: '/companyProfile/intermediaryOrgDataList',
//     method: 'get',
//     params: query
//   })
// }

// 真接口
// IPO公司概览返回类
export function getCaseDetail(query) {
  return request({
    // url: '/ipo/api/companyOverview/caseDetail',
    url:'/ipo/api/companyOverview/caseDetail?id=97952444248599344',
    method: 'get',
    params: query
  })
}
// 资本市场接口描述
export function getMarketData(query) {
  return request({
    url: '/ipo/api/companyOverview/marketData',
    method: 'get',
    params: query
  })
}
// 股东信息接口描述
export function getShareHolderData(query) {
  return request({
    // url: '/ipo/api/companyOverview/shareHolderData',
    url:'/ipo/api/companyOverview/shareHolderData?id=97952444248599344',
    method: 'get',
    params: query
  })
}
// 主要竞争对手接口描述
export function getCompetitorData(query) {
  return request({
    url: '/ipo/api/companyOverview/competitorData?id=97952444248599344',
    method: 'get',
    params: query
  })
}

// 中介机构接口
export function getIntermediaryOrgDataList(query) {
  return request({
    url: '/ipo/api/companyOverview/intermediaryOrgData?id=97952444248599344',
    method: 'get',
    params: query
  })
}
// 募集资金接口
export function getRaiseMoneyTableList(query) {
  return request({
    url: '/ipo/api/ipoInvest/selectProcessList',
    method: 'get',
    params: query
  })
}
