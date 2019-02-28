import request from '@/utils/request'

export function getGqList(query) {
  return request({
    url: '/companyProfile/gqList',
    method: 'get',
    params: query
  })
}
export function getGqTable(query) {
  return request({
    url: '/companyProfile/gqTable',
    method: 'get',
    params: query
  })
}