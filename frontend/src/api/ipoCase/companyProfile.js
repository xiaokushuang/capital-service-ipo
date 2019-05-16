import request from '@/utils/request'
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
// new反馈意见初始化
export function getSelectFeedbackList(query) {
  return request({
    url: '/ipo/ipoFeedback/selectNewFeedbackList',
    method: 'get',
    params: query
  })
}
// new反馈意见问题列表接口
export function getSelectQuestionList(query) {
  return request({
    url: '/ipo/ipoFeedback/selectNewQuestionList',
    method: 'get',
    params: query
  })
}

// 审核结果及关注问题
export function geSelectFeedbackList(query) {
  return request({
    url: '/ipo/ipoExamine/selectNewExamineList',
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
// 进程树接口
export function getRightModuleData(query) {
  return request({
    url: '/ipo/ipoProcess/selectProcessList',
    method: 'get',
    params: query
  })
}
// 进程树下载公告接口
export function getDownloadFileData(query) {
  return request({
    url: '/ipo/ipoProcess/downloadFile',
    method: 'get',
    params: query
  })
}
// 头部公司简介接口
export function getHeadData(query) {
  return request({
    url: '/ipo/companyOverview/headData',
    method: 'get',
    params: query
  })
}
// 检查下载文件是否存在
export function checkFile(query) {
  return request({
                   url: '/ipo/ipoProcess/checkFile',
                   method: 'get',
                   params: query
                 })
}
// 行业与技术接口
// 行业地位
export function getIndustryStatus(query) {
  return request({
    url: '/ipo/companyOverview/industryStatusData',
    method: 'get',
    params: query
  })
}