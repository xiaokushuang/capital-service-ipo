import request from '@/utils/request'

export function getTableData() {
  return request({
    url: '/demo/tableData',
    method: 'get'
  })
}
