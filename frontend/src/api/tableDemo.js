import request from '@/utils/request'
// 【moke】

// 主营业务收入构成【柱状图饼图】
// export function getTableData() {
//   return request({
//     url: '/demo/tableData',
//     method: 'get'
//   })
// }

// 发行情况

// 发行数据
// export function getIssueData() {
//   return request({
//     url: '/issueData/IssueDataList',
//     method: 'get'
//   })
// }

// 发行费用
// export function getIssueFeeData() {
//   return request({
//     url: '/issueFeeData/IssueFeeDataList',
//     method: 'get'
//   })
// }

// 财务信息
// 财务总体情况接口
// export function getSelectFinanceOverList() {
//   return request({
//     url: '/finance/selectFinanceOverList',
//     method: 'get'
//   })
// }
// 资产与负债情况财务信息接口 
// export function getAssetsOrDebtData() {
//   return request({
//     url: '/finance/assetsOrDebtData',
//     method: 'get'
//   })
// }
// 收入与利润情况接口
// export function getSelectFinanceProfitList() {
//   return request({
//     url: '/finance/selectFinanceProfitList',
//     method: 'get'
//   })
// }

// 毛利率接口
// export function getMaoChartTableData() {
//   return request({
//     url: '/finance/maoChartTableData',
//     method: 'get'
//   })
// }


// 没用头
// export function getAssetsTableData() {
//   return request({
//     url: '/demo/assetsTableData',
//     method: 'get'
//   })
  
// }
// export function getCompareTableData() {
//   return request({
//     url: '/demo/compareTableData',
//     method: 'get'
//   })
  
// }
// export function getIssueMoneyTableData() {
//   return request({
//     url: '/demo/issueMoneyTableData',
//     method: 'get'
//   })
// }
// 没用尾


// 【真接口】

// 主营业务收入构成【柱状图饼图】
export function getTableData(query) {
  return request({
    url: '/ipo/api/companyOverview/incomeData',
    method: 'get',
    params: query
  })
}

// 报告期主要供应商及客户接口描述
// export function getSupplierCustomerData(query) {
//   return request({
//     url: '/ipo/api/companyOverview/supplierCustomerData',
//     method: 'get',
//     params: query
//   })
// }

// 发行情况

// 发行数据
export function getIssueData(query) {
  return request({
    url: '/ipo/api/issue/issueData',
    method: 'get',
    params: query
  })
}

// 发行费用
export function getIssueFeeData(query) {
  return request({
    url: '/ipo/api/issue/issueFeeData',
    method: 'get',
    params: query
  })
}

// 财务信息
// 财务总体情况接口
export function getSelectFinanceOverList(query) {
  return request({
    url: '/ipo/api/ipoFinance/selectFinanceOverList',
    method: 'get',
    params: query
  })
}
// 资产与负债情况财务信息接口 
export function getAssetsOrDebtData(query) {
  return request({
    url: '/ipo/api/ipoFinance/selectFinanceList',
    method: 'get',
    params: query
  })
}
// 收入与利润情况接口
export function getSelectFinanceProfitList(query) {
  return request({
    url: '/ipo/api/ipoFinance/selectFinanceProfitList',
    method: 'get',
    params: query
  })
}

// 毛利率接口
export function getMaoChartTableData(query) {
  return request({
    url: '/ipo/api/issue/industryRateData',
    method: 'get',
    params: query
  })
}



