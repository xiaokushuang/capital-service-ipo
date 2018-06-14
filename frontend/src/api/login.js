import request from '@/utils/request'

export function loginByUsername(userName, password) {
  const data = {
    userName,
    password
  }
  return request({
    url: '/htrt/login',
    method: 'post',
    params: data
  })
}

export function logout() {
  return request({
    url: '/htrt/logout',
    method: 'post'
  })
}

export function getUserInfo(token) {
  return request({
    url: '/htrt/getUserInfo', url: '/htrt/getUserInfo',
    method: 'post',
    params: {token}
  })
}

export function generateRoutes() {
  return request({
    url: '/htrt/getRoutes',
    method: 'post'
  })
}

export function testto(info){
  return request({
    url:`/htrt/testto?data=${info}`,
    method:'get'
  })
}
