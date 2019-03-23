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
    url:'/ipo/companyOverview/caseDetail',
    method: 'get',
    params: query
  })
}
// 资本市场接口描述
export function getMarketData(query) {
  return request({
    url: '/ipo/companyOverview/marketData',
    method: 'get',
    params: query
  })
}
// 股东信息接口描述
export function getShareHolderData(query) {
  return request({
    url:'/ipo/companyOverview/shareHolderData',
    method: 'get',
    params: query
  })
}
// 主要竞争对手接口描述
export function getCompetitorData(query) {
  return request({
    url: '/ipo/companyOverview/competitorData',
    method: 'get',
    params: query
  })
}

// 中介机构接口
export function getIntermediaryOrgDataList(query) {
  return request({
    url: '/ipo/companyOverview/intermediaryOrgData',
    method: 'get',
    params: query
  })
}
// 募集资金接口
export function getRaiseMoneyTableList(query) {
  return request({
    url: '/ipo/ipoInvest/selectInvestItem',
    method: 'get',
    params: query
  })
}

// 反馈意见初始化一级标签接口
export function getSelectFeedbackList(query) {
  return request({
    url: '/ipo/ipoFeedback/selectFeedbackList',
    method: 'get',
    params: query
  })
}

// 反馈意见问题列表接口【点击单选和多选筛选相应的】
export function getSelectQuestionListByLetterId(query) {
  return request({
    url: '/ipo/ipoFeedback/selectQuestionListByLetterId',
    method: 'get',
    params: query
  })
}
// 反馈意见二级标签列表接口
export function getSelectSecondLabelList(query) {
  return request({
    url: '/ipo/ipoFeedback/selectSecondLabelList',
    method: 'get',
    params: query
  })
}
// 审核结果及关注问题
export function geSelectFeedbackList(query) {
  return request({
    url: '/ipo/ipoExamine/selectFeedbackList',
    method: 'get',
    params: query
  })
}
// 审核结果及关注问题的委员详情
export function geSelectMemberList(query) {
  return request({
    url: '/ipo/ipoExamine/selectMemberList',
    method: 'get',
    params: query
  })
}