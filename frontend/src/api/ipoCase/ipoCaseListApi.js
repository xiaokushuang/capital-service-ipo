import request from '@/utils/request'

const basePath = '/ipo/search';

export function _getIpoCaseList(_data) {
  return request({
    url: basePath + '/listData',
    method: 'post',
    data: _data
  })
}
//  初始化地区 下拉框数据
export function initAreaSelect(_data) {
  return request({
    url: basePath + '/initAreaSelect',
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

export function getIpoDataOverviewFdDetail(_data) {
  return request({
    url: '/ipo/regulatory_statistics/getIpoDataOverviewFdDetail',
    method: 'post',
    data: _data
  })
}
