import {
  searchformeary
} from '@/api/ipo'
import {
  searchBond
} from '@/api/ipo'
import {
  searchIndex
} from '@/api/ipo'
import {
  SFClassification
} from '@/api/ipo'
import {
  SFCRegion
} from '@/api/ipo'
import {
  plateInfo
} from '@/api/ipo'
import * as auth from '@/utils/auth'

const ipo = {
  state: {
    ipodata1: [],
    ipodata2: [],
    ipodata3: [],
    ipoBondData1: [],
    ipoBondData2: [],
    ipoBondData3: [],
    ipoSearchData: [],
    ipoSFClass: [],
    ipoRegion: [],
    ipoPlateInfo: []
  },

  mutations: {
    SET_IPO_TYPE: (state, code) => {
      state[code.type] = code.data
    },
    SET_IPO_BOND_TYPE: (state, code) => {
      state[code.type] = code.data
    },
    SET_IPO_SEARCH: (state, code) => {
      state[code.type] = code.data
    },
    SET_IPO_SFCLASS: (state, code) => {
      state[code.type] = code.data
    },
    SET_IPO_SFREGION: (state, code) => {
      state[code.type] = code.data
    },
    SET_IPO_PLATE: (state, code) => {
      state[code.type] = code.data
    }
  },

  actions: {
    ipoGet({
      commit
    }, param) {
      return new Promise((resolve, reject) => {
        const type = param.type
        param.data = null
        searchformeary(param).then((response) => {
          // console.log(response)
          param.data = response.data.result
          if (typeof param.data === 'object') {
            commit('SET_IPO_TYPE', param)
            // console.log(1)
            // console.log(param.data)
          }
          resolve()
        }).catch((error) => {
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
      })
    },
    ipoBondGet({
      commit
    }, param) {
      return new Promise((resolve, reject) => {
        const type = param.type
        param.data = null
        searchBond(param).then((response) => {
          // console.log(response)
          param.data = response.data.result
          if (typeof param.data === 'object') {
            commit('SET_IPO_BOND_TYPE', param)
            // console.log(2)
            // console.log(param.data)
          }
          resolve()
        }).catch((error) => {
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
      })
    },

    ipoSearchGet({
      commit
    }, param) {
      return new Promise((resolve, reject) => {
        const type = param.type
        param.data = null
        searchIndex(param).then((response) => {
          // console.log(response)
          param.data = response.data.result
          if (typeof param.data === 'object') {
            param.type = 'ipoSearchData'
            commit('SET_IPO_SEARCH', param)
            // console.log(3)
            // console.log(param)
          }
          resolve()
        }).catch((error) => {
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
      })
    },
    // 行业分类
    ipoSFClassGet({
      commit
    }, order) {
      return new Promise((resolve, reject) => {
        const param = order || {}
        // const type = param.type
        // param.data = null
        // debugger
        SFClassification(param).then((response) => {
          param.data = response.data.data
          // console.log(response)
          if (typeof param.data === 'object') {
            param.type = 'ipoSFClass'
            commit('SET_IPO_SFCLASS', param)
            // console.log(4)
            // console.log(param)
          }
          resolve()
        }).catch((error) => {
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
      })
    },
    // 所有地区
    ipoSFCRegionGet({
      commit
    }, order) {
      return new Promise((resolve, reject) => {
        const param = order || {}
        SFCRegion(param).then((response) => {
          param.data = response.data.data
          // console.log(response)
          if (typeof param.data === 'object') {
            param.type = 'ipoRegion'
            commit('SET_IPO_SFREGION', param)
            // console.log(5)
            // console.log(param.data)
          }
          resolve()
        }).catch((error) => {
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
      })
    },
    // 板块信息
    ipoPlateInfoGet({
      commit
    }, param) {
      return new Promise((resolve, reject) => {
        const type = param.type
        param.data = null
        plateInfo(param).then((response) => {
          // console.log(response)
          param.data = response.data.data
          if (typeof param.data === 'object') {
            param.type = 'ipoPlateInfo'
            commit('SET_IPO_PLATE', param)
            // console.log(6)
            // console.log(param.data)
          }
          resolve()
        }).catch((error) => {
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
      })
    },
  },
  getters: {
    getIpo1: state => state.ipodata1,
    getIpo2: state => state.ipodata2,
    getIpo3: state => state.ipodata3,
    getBondIpo1: state => state.ipoBondData1,
    getBondIpo2: state => state.ipoBondData2,
    getBondIpo3: state => state.ipoBondData3,
    getSearchIpo: state => state.ipoSearchData,
    getSFClass: state => state.ipoSFClass,
    getSFCRegion: state => state.ipoRegion,
    getPlateInfo: state => state.ipoPlateInfo
  }
}

export default ipo
