import request from '@/utils/request'
// 【moke】
// export function getRightModuleData() {
//   return request({
//     url: '/rightModule/rightModuleDataList',
//     method: 'get'
//   })
// }

// 真接口
export function getRightModuleData(query) {
  return request({
    url: '/ipo/api/ipoProcess/selectProcessList?id=97952444248599350',
    method: 'get',
    params: query
  })
}