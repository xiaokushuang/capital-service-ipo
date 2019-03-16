import request from '@/utils/request'

export function getTableData() {
  return request({
    url: '/demo/tableData',
    method: 'get'
  })
}
// 主营业务收入构成【柱状图饼图】
// export function getTableData() {
//   var query = {
//     id:'97952444248336801'
//   }
//   return request({
//     url: '/ipo/api/companyOverview/incomeData',
//     method: 'get',
//     params: query
//   })
// }
// 报告期主要供应商及客户接口描述
export function getSupplierCustomerData() {
  var query = {
    id:'97952444248336801'
  }
  return request({
    url: '/ipo/api/companyOverview/supplierCustomerData',
    method: 'get',
    params: query
  })
}

export function getAssetsTableData() {
  return request({
    url: '/demo/assetsTableData',
    method: 'get'
  })
  
}
export function getCompareTableData() {
  return request({
    url: '/demo/compareTableData',
    method: 'get'
  })
  
}
export function getIssueMoneyTableData() {
  return request({
    url: '/demo/issueMoneyTableData',
    method: 'get'
  })
  
}
// 发行情况
export function getIssueData() {
  return request({
    url: '/issueData/IssueDataList',
    method: 'get'
  })
}
export function getIssueFeeData() {
  return request({
    url: '/issueFeeData/IssueFeeDataList',
    method: 'get'
  })
}
// 资产与负债情况财务信息接口 
export function getAssetsOrDebtData() {
  return request({
    url: '/finance/assetsOrDebtData',
    method: 'get'
  })
}


