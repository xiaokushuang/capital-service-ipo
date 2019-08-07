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

export function _queryIpoFavoriteList(_data) {
  return request({
    url: basePath + '/queryIpoFavoriteList',
    method: 'post',
    data: _data
  })
}

export function _getCassNote(_data) {
  return request({
    url: basePath + '/getCassNote',
    method: 'post',
    data: _data
  })
}
