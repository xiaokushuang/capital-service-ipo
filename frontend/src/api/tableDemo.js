import request from '@/utils/request'

export function getTableData() {
  return request({
    url: '/demo/tableData',
    method: 'get'
  })
}
export function getFifthGysTableData() {
  return request({
    url: '/demo/fifthGysTableData',
    method: 'get'
  })
  
}
export function getFifthKhTableData() {
  return request({
    url: '/demo/fifthKhTableData',
    method: 'get'
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
