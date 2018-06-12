import request from '@/utils/request'

export function searchformeary(param) {
  return request({
    url: `/ipo/financeStatistics/getChartForFinanceData`,
    method: 'post',
    data: param
  })
}

export function searchBond(param) {
  return request({
    url: `/ipo/financeStatistics/getChartForBondData`,
    method: 'post',
    data: param
  })
}

export function SFClassification() {
  return request({
    url: `/ipo/financeStatistics/getFinanceList`,
    method: 'post'
  })
}

export function SFCRegion() {
  return request({
    url: `/ipo/financeStatistics/getProvincesList`,
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