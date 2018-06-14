import {
  getUserInfo,
  loginByUsername,
  logout,
  testto
} from '@/api/login'
import * as auth from '@/utils/auth'
import index from 'vuex'
import { Input } from 'element-ui'
import {
  Message
} from 'element-ui'
import {
  RSA_PKCS1_PSS_PADDING
} from 'constants'

const user = {
  state: {
    userName: '',
    status: '',
    code: '',
    accessToken: auth.getToken(),
    refreshToken: auth.getrefreshToken(),
    name: '',
    avatar: '',
    introduction: '',
    roles: [], // 登录账号
    setting: {
      articlePlatform: []
    },
    routename: '',
    routeTitle: '首页'
  },

  mutations: {
    SET_CODE: (state, code) => {
      state.code = code
    },
    SET_ACCESS_TOKEN: (state, token) => {
      state.accessToken = token
    },
    SET_REFRESH_TOKEN: (state, token) => {
      state.refreshToken = token
    },
    SET_INTRODUCTION: (state, introduction) => {
      state.introduction = introduction
    },
    SET_SETTING: (state, setting) => {
      state.setting = setting
    },
    SET_STATUS: (state, status) => {
      state.status = status
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_ROUTE_NAME: (state, name) => {
      state.routename = name
    },
    SET_ROUTE_TITLE: (state, name) => {
      state.routeTitle = name
    },
    SET_USER: (state, userName) => {
      state.userName = userName
    }
  },

  actions: {
    // 用户名登录
    loginByUsername({
      commit
    }, userInfo) {
      const userName = userInfo.userName.trim()
      return new Promise(
        (resolve, reject) => {
          loginByUsername(userName, userInfo.password)
            .then(
              (response) => {
                console.log(response)
                const data = response.data
                commit('SET_USER', userName)
                commit('SET_ACCESS_TOKEN', data.result.accessToken)
                commit('SET_REFRESH_TOKEN', data.result.refreshToken)
                auth.setToken(data.result.accessToken)
                auth.setrefreshToken(data.result.refreshToken)
                auth.setPermissions(data.result.permissions)
                resolve()
              })
            .catch(
              (error) => {
                console.log(error)
                if (error && error.response && error.response.status === 401) {
                  if (error.response.data && error.response.data.message) {
                    Message({
                      message: error.response.data.message,
                      type: 'error',
                      duration: 5 * 1000
                    })
                  }
                }
                reject(error)
              })
        }
      )
    },

    // 获取用户信息
    GetUserInfo({
      commit,
      state
    }) {
      return new Promise((resolve, reject) => {
        getUserInfo(state.token).then(response => {
          if (!response.data) { // 由于mockjs 不支持自定义状态码只能这样hack
            reject('error')
          }
          const data = response.data
          commit('SET_ROLES', data.roles)
          commit('SET_NAME', data.name)
          commit('SET_AVATAR', data.avatar)
          commit('SET_INTRODUCTION', data.introduction)
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 登出
    LogOut({
      commit,
      state
    }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_ACCESS_TOKEN', '')
          commit('SET_REFRESH_TOKEN', '')
          commit('SET_ROLES', [])
          auth.clearAllCookie()
          resolve()
        }).catch((error) => {
          if (error && error.response && error.response.status === 401) {
            commit('SET_ACCESS_TOKEN', '')
            commit('SET_REFRESH_TOKEN', '')
            commit('SET_ROLES', [])
            auth.clearAllCookie()
          } else {
            console.log(error)
          }
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({
      commit
    }) {
      return new Promise(resolve => {
        commit('SET_ACCESS_TOKEN', '')
        commit('SET_REFRESH_TOKEN', '')
        auth.clearAllCookie()
        resolve()
      })
    },

    // 动态修改权限
    ChangeRoles({
      commit
    }, role) {
      return new Promise(resolve => {
        // commit('SET_TOKEN', role)
        // setToken(role)
        getUserInfo(role).then(response => {
          const data = response.data
          commit('SET_ROLES', data.roles)
          commit('SET_NAME', data.name)
          commit('SET_AVATAR', data.avatar)
          commit('SET_INTRODUCTION', data.introduction)
          resolve()
        })
      })
    },

    // 测试自动完成组建，构建数据
    complate({
      commit
    }, role) {
      return new Promise(resolve => {
        testto(role).then(res => {
          resolve(res.data)
        })
      })
    }
  }
}

export default user
