import request from '@/utils/request'

const basePath = '/ipo/search';

export function _getIpoCaseList(_data) {
  return request({
    url: basePath + '/listData',
    method: 'post',
    data: _data
  })
}

export function _getSelectData() {
  return request({
    url: basePath + '/selectData',
    method: 'get'
  })
}

export function _queryIntermediary(_params) {
  return request({
    url: basePath + '/queryIntermediary',
    method: 'get',
    params: _params
  })
}