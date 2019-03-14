import request from '@/utils/request'

export function getRightModuleData() {
  return request({
    url: '/rightModule/rightModuleDataList',
    method: 'get'
  })
}
