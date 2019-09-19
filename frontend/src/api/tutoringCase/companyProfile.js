import request from '@/utils/request'
// 头部公司简介接口
export function getHeadData(query) {
  return request({
    url: '/ipo/companyOverview/headData',
    method: 'get',
    params: query
  })
}
// IPO公司概览返回类
export function getCoachBasicInfo(query) {
  return request({
    url:'/ipo/ipoCoachCaseDetail/queryCoachBasicInfo',
    method: 'get',
    params: query
  })
}
// 公司基本信息接口描述
export function getCompanyInfo(query) {
  return request({
    url: '/ipo/ipoCoachCaseDetail/queryCoachTitleInfo',
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
// 检查下载文件是否存在
export function checkFile(query) {
  return request({
    url: '/ipo/ipoProcess/checkFile',
    method: 'get',
    params: query
  })
}

// 收藏/取消收藏
export function clickFavorite(query) {
  return request({
    url: '/ipo/companyOverview/getDetermineWhetherToCollect',
    method: 'get',
    params: query
  })
}

// 笔记点击确定
export function NoteDetermination(query) {
  return request({
    url: '/ipo/companyOverview/getJudgementNoteDetermination',
    method: 'post',
    // params: query  //get方式 通过param传值
		data : query  //post方式 通过data传值
  })
}
