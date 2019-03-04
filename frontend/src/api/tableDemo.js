import request from '@/utils/request'

export function getTableData() {
  return request({
    url: '/demo/tableData',
    method: 'get'
  })
}
export function getFifthGysTableData() {
  return request({
    url: '/demo/FifthGysTableData',
    method: 'get'
  })
}
