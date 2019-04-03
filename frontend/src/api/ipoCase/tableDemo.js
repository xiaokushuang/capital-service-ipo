import request from '@/utils/request'
// 【真接口】

// 主营业务收入构成【柱状图饼图】
export function getTableData(query) {
  return request({
    url: '/ipo/companyOverview/incomeData',
    method: 'get',
    params: query
  })
}

// 报告期主要供应商及客户接口描述
export function getSupplierCustomerData(query) {
  return request({
    url: '/ipo/companyOverview/supplierCustomerData',
    method: 'get',
    params: query
  })
}

// 发行情况

// 发行数据
export function getIssueData(query) {
  return request({
    url: '/ipo/issue/issueData',
    method: 'get',
    params: query
  })
}

// 发行费用
export function getIssueFeeData(query) {
  return request({
    url: '/ipo/issue/issueFeeData',
    method: 'get',
    params: query
  })
}

// 财务信息
// 财务总体情况接口
export function getSelectFinanceOverList(query) {
  return request({
    url: '/ipo/ipoFinance/selectFinanceOverList',
    method: 'get',
    params: query
  })
}
// 资产与负债情况财务信息接口 
export function getAssetsOrDebtData(query) {
  return request({
    url: '/ipo/ipoFinance/selectFinanceList',
    method: 'get',
    params: query
  })
}
// 收入与利润情况接口
export function getSelectFinanceProfitList(query) {
  return request({
    url: '/ipo/ipoFinance/selectFinanceProfitList',
    method: 'get',
    params: query
  })
}

// 毛利率接口
export function getMaoChartTableData(query) {
  return request({
    url: '/ipo/issue/industryRateData',
    method: 'get',
    params: query
  })
}



