import {
	searchformeary,
	searchBond,
	searchIndex,
	SFClassification,
	SFCRegion,
	plateInfo,
	IndustryInfo,
	ipoDataOverview,
	ipoDataHistory,
	projectBelong,
	sponsorInstitution,
	ipoAreaData,
	ipoQuery,
	lawOffice,
	accountFirm,
	refinanceApprove,
	refinanceApptype,
	refinanceRecommend,
	companyByCode,
	searchCompanyDetail,
	getAllDropDownList,
	getIpoDataOverviewDetail,
	ipoItemDataQuery,
	getIpoItemDataDetail,
	getIpoItemByCompanyCodeSelectId,
  getFeePaymentFlag,
} from '@/api/ipo'
import {
	MultidimensionalData
} from "@/utils/index"
import {
	getLawsByTable
} from '@/api/declare'
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
		plateInfo: [],
		industryInfo:[],
		ipoDataOverview: [],
		ipoDataHistory: [],
		projectBelong: [],
		sponsorInstitution: [],
		ipoAreaData: [],
		ipoQuery: [],
		lawOffice: [],
		accountFirm: [],
		refinanceApprove: [],
		refinanceApptype: [],
		refinanceRecommend: [],
		companyByCode: [],
		totalFloor: {
			total1: 0,
			total2: 0,
			total3: 0
		},
		removeNote: false, //删除笔记
		// 收藏与笔记属性
		destroyIpoFavorite: false, //点击笔记，销毁收藏页面
		destroyIpoNote: false, //点击收藏，销毁笔记页面
		removeIpoNote: false, //删除笔记
		noteIpoEditFlag: false,
		noteIpoCaseId: '',
		caseIpoNote: '',
	},

	mutations: {
		SET_REMOVE_NOTE(state, code) {
			state.removeNote = code.data
			console.log("值为：" + state.removeNote)
		},
		// 收藏与笔记赋值
		SET_IPO_DESTROY_FAVORITE(state, data) {
			state.destroyIpoFavorite = data;
		},
		SET_IPO_DESTROY_NOTE(state, data) {
			state.destroyIpoNote = data;
		},
		SET_IPO_REMOVE_NOTE: (state, code) => {
			state.removeIpoNote = code
		},
		SET_IPO_CASE_NOTE: (state, code) => {
			for (var key in code) {
				state[key] = code[key]
			}
		},
		SET_IPO_TYPE: (state, code) => {
			state[code.type] = code.data
		},
		SET_IPO_TYPE_TOTAL: (state, data) => {
			var key = Object.keys(data)
			state.totalFloor[key] = data[key]
			console.log(state.totalFloor)
		},
		// 存动态caseId
		SET_TOKEN: (state, code) => {
			state.caseId = code
		},
		// 存日志
		CREATE_MESSAGE: (state, code) => {
			state.info = code
		},
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
						commit('SET_IPO_TYPE', param)
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

		// ipoSearchGet({
		//   commit
		// }, param) {
		//   return new Promise((resolve, reject) => {
		//     const type = param.type
		//     param.data = null
		//     searchIndex(param).then((response) => {
		//       console.log("searech接口", response)
		//       param.data = response.data.result
		//       if (typeof param.data === 'object') {
		//         param.type = 'ipoSearchData'
		//         commit('SET_IPO_TYPE', param)
		//         // console.log(3)
		//         // console.log(param)
		//       }
		//       resolve()
		//     }).catch((error) => {
		//       console.log(error)
		//       if (error && error.response && error.response.status === 401) {
		//         if (error.response.data && error.response.data.message) {
		//           Message({
		//             message: error.response.data.message,
		//             type: 'error',
		//             duration: 5 * 1000
		//           })
		//         }
		//       }
		//       reject(error)
		//     })
		//   })
		// },
		ipoSearchGet({
			commit
		}, queryParam) { //获取融资查询列表数据
			return new Promise((resolve, reject) => {
				searchIndex(queryParam).then(response => {
					resolve(response.data.result);
				}).catch(error => {
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
				SFClassification(param).then((response) => {
					param.data = response.data.data
					// console.log(response)
					if (typeof param.data === 'object') {
						param.type = 'ipoSFClass'
						commit('SET_IPO_TYPE', param)
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
						commit('SET_IPO_TYPE', param)
						// console.log(5)
						// console.log(param.data)
						// console.log(MultidimensionalData(param.data))
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
		}, order) {
			return new Promise((resolve, reject) => {
				const param = order || {}
				plateInfo(param).then((response) => {
					param.data = response.data.data
					// console.log(response)
					if (typeof param.data === 'object') {
						param.type = 'plateInfo'
						commit('SET_IPO_TYPE', param)
						// console.log(5)
						// console.log(param.data)
						// console.log(MultidimensionalData(param.data))
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
		// 行业类别
		ipoindustryInfoGet({
			commit
		}, order) {
			return new Promise((resolve, reject) => {
				let param = {}
				IndustryInfo(order).then((response) => {
					param.data = response.data.data
					// resolve(response.data.data);
					if (typeof param.data === 'object') {
						param.type = 'industryInfo'
						commit('SET_IPO_TYPE', param)
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
		// ipo数据概述
		ipoDataOverviewGet({
			commit
		}, order) {
			return new Promise((resolve, reject) => {
				const param = order || {}
				ipoDataOverview(param).then((response) => {
					param.data = response.data.result
					// console.log(response)
					if (typeof param.data === 'object') {
						param.type = 'ipoDataOverview'
						commit('SET_IPO_TYPE', param)
						// console.log(5)
						// console.log(param.data)
						// console.log(MultidimensionalData(param.data))
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
		// ipo数据历史
		ipoDataHistoryGet({
			commit
		}, order) {
			return new Promise((resolve, reject) => {
				const param = order || {}
				ipoDataHistory(param).then((response) => {
					param.data = response.data.result
					console.log(response)
					if (typeof param.data === 'object') {
						param.type = 'ipoDataHistory'
						commit('SET_IPO_TYPE', param)
						// console.log(5)
						// console.log(param.data)
						// console.log(MultidimensionalData(param.data))
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
		// 行业地区所属
		projectBelongGet({
			commit
		}, order) {
			return new Promise((resolve, reject) => {
				const param = order || {}
				projectBelong(param).then((response) => {
					param.data = response.data.result
					if (typeof param.data === 'object') {
						param.type = 'projectBelong'
						commit('SET_IPO_TYPE', param)
						// console.log(5)
						// console.log(param.data)
						// console.log(MultidimensionalData(param.data))
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
		sponsorInstitutionGet({
			commit
		}, param) {
			return new Promise((resolve, reject) => {
				const type = param.type
				param.data = null
				sponsorInstitution(param).then((response) => {
					// console.log(11111)
					// console.log(response)
					param.data = response.data.result
					if (typeof param.data === 'object') {
						param.type = 'sponsorInstitution'
						commit('SET_IPO_TYPE', param)
						commit('SET_IPO_TYPE_TOTAL', {
							'total1': param.data.total
						})
						// console.log(6)
						// console.log(param.data)
					}
					resolve(response.data.result)
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
		ipoQueryGet({
			commit
		}, order) {
			return new Promise((resolve, reject) => {
				const param = order || {}
				ipoQuery(param).then((response) => {
					param.data = response.data.result
					// console.log(response)
					if (typeof param.data === 'object') {
						param.type = 'ipoQuery'
						commit('SET_IPO_TYPE', param)
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
		//
		ipoAreaDataGet({
			commit
		}, param) {
			return new Promise((resolve, reject) => {
				const type = param.type
				param.data = null
				ipoAreaData(param).then((response) => {
					// console.log(response)
					param.data = response.data.result
					if (typeof param.data === 'object') {
						param.type = 'ipoAreaData'
						commit('SET_IPO_TYPE', param)
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
		lawOfficeGet({
			commit
		}, param) {
			return new Promise((resolve, reject) => {
				const type = param.type
				param.data = null
				lawOffice(param).then((response) => {
					// console.log(response)
					param.data = response.data.result
					if (typeof param.data === 'object') {
						param.type = 'lawOffice'
						commit('SET_IPO_TYPE', param)
						commit('SET_IPO_TYPE_TOTAL', {
							'total2': param.data.total
						})
						// console.log(6)
						// console.log(param.data)
					}
					resolve(response.data.result)
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
		accountFirmGet({
			commit
		}, param) {
			return new Promise((resolve, reject) => {
				const type = param.type
				param.data = null
				accountFirm(param).then((response) => {
					param.data = response.data.result
					if (typeof param.data === 'object') {
						param.type = 'accountFirm'
						commit('SET_IPO_TYPE', param)
						commit('SET_IPO_TYPE_TOTAL', {
							'total3': param.data.total
						})
					}
					resolve(response.data.result)
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
		refinanceApproveGet({
			commit
		}, order) {
			return new Promise((resolve, reject) => {
				const param = order || {}
				refinanceApprove(param).then((response) => {
					param.data = response.data.result
					// console.log(response)
					if (typeof param.data === 'object') {
						param.type = 'refinanceApprove'
						commit('SET_IPO_TYPE', param)
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
		refinanceApptypeGet({
			commit
		}, order) {
			return new Promise((resolve, reject) => {
				const param = order || {}
				refinanceApptype(param).then((response) => {
					param.data = response.data.result
					// console.log(response)
					if (typeof param.data === 'object') {
						param.type = 'refinanceApptype'
						commit('SET_IPO_TYPE', param)
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
		companyByCodeGet({
			commit
		}, param) {
			return new Promise((resolve, reject) => {
				const type = param.type
				param.data = null
				companyByCode(param).then((response) => {
					param.data = response.data.result
					if (typeof param.data === 'object') {
						param.type = 'companyByCode'
						commit('SET_IPO_TYPE', param)
					}
					resolve(response.data.result)
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
		refinanceRecommendGet({
			commit
		}, order) {
			return new Promise((resolve, reject) => {
				const param = order || {}
				// const type = param.type
				// param.data = null
				// debugger
				refinanceRecommend(param).then((response) => {
					param.data = response.data.result
					// console.log(response)
					if (typeof param.data === 'object') {
						param.type = 'refinanceRecommend'
						commit('SET_IPO_TYPE', param)
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
		searchCompanyDetail({
			commit
		}, queryParam) { //获取公司详情列表数据
			return new Promise((resolve, reject) => {
				searchCompanyDetail(queryParam).then(response => {
					resolve(response.data.result);
				}).catch(error => {
					reject(error)
				})
			})
		},
		getAllDropDownList({
			commit
		}, queryParam) { //获取下拉列表数据
			return new Promise((resolve, reject) => {
				getAllDropDownList(queryParam).then(response => {
					resolve(response.data);
				}).catch(error => {
					reject(error)
				})
			})
		},
		getIpoDataOverviewDetail({
			commit
		}, queryParam) { //获取ipo数据概览详情页数据
			return new Promise((resolve, reject) => {
				getIpoDataOverviewDetail(queryParam).then(response => {
					resolve(response.data.result);
				}).catch(error => {
					reject(error)
				})
			})
		},
		ipoItemDataQuery({
			commit
		}, queryParam) { //IPO在审项目数据查询
			return new Promise((resolve, reject) => {
				ipoItemDataQuery(queryParam).then(response => {
					resolve(response.data.result);
				}).catch(error => {
					reject(error)
				})
			})
		},
		getIpoItemDataDetail({
			commit
		}, queryParam) { //获取ipo在审项目详情页数据
			return new Promise((resolve, reject) => {
				getIpoItemDataDetail(queryParam).then(response => {
					resolve(response.data.result);
				}).catch(error => {
					reject(error)
				})
			})
		},
		getIpoItemByCompanyCodeSelectId({
			commit
		}, queryParam) { //获取ipo在审项目详情页数据
			return new Promise((resolve, reject) => {
				getIpoItemByCompanyCodeSelectId(queryParam).then(response => {
					resolve(response.data.result);
				}).catch(error => {
					reject(error)
				})
			})
		},
    getFeePaymentFlag({
			commit
		}, queryParam) { //获取ipo在审项目详情页数据
			return new Promise((resolve, reject) => {
				getFeePaymentFlag(queryParam).then(response => {
					resolve(response.data.result);
				}).catch(error => {
					reject(error)
				})
			})
		},
	},
	getters: {
		removeNote: state => state.removeNote,
		destroyIpoFavorite: state => state.destroyIpoFavorite,
		destroyIpoNote: state => state.destroyIpoNote,
		removeIpoNote: state => state.removeIpoNote,
		noteIpoEditFlag: state => state.noteIpoEditFlag,
		noteIpoCaseId: state => state.noteIpoCaseId,
		caseIpoNote: state => state.caseIpoNote,
		getIpo1: state => state.ipodata1,
		getIpo2: state => state.ipodata2,
		getIpo3: state => state.ipodata3,
		getBondIpo1: state => state.ipoBondData1,
		getBondIpo2: state => state.ipoBondData2,
		getBondIpo3: state => state.ipoBondData3,
		getSearchIpo: state => state.ipoSearchData,
		getSFClass: state => state.ipoSFClass,
		getSFCRegion: state => {
			return MultidimensionalData(state.ipoRegion)
		},
		getIndustryInfo: state => {
			return MultidimensionalData(state.industryInfo)
		},
		getPlateInfo: state => state.plateInfo,
		getDataOverInfo: state => {
			state.ipoDataOverview.map((o, i) => {
				var total = parseInt(o.hzbCount) + parseInt(o.zxbCount) + parseInt(o.cybCount) + parseInt(o.kcCount)
				o.totalAll = total
			})
			return state.ipoDataOverview
		},
		getDataHistory: state => state.ipoDataHistory,
		getProjectBelong: state => state.projectBelong,
		getSponsorInstitution: state => state.sponsorInstitution,
		getIpoAreaData: state => state.ipoAreaData,
		getIpoQuery: state => state.ipoQuery,
		getLowOffice: state => state.lawOffice,
		getAccountFirm: state => state.accountFirm,
		getRefinanceApprove: state => state.refinanceApprove,
		getRefinanceApptype: state => {
			var newArr = []
			state.refinanceApptype.map((o, i) => {
				if (o.totalCount !== 0) {
					newArr.push(o)
				}
			})
			return newArr
		},
		getRefinanceRecommend: state => state.refinanceRecommend,
		getCompanyByCode: state => state.companyByCode,
		getTotalFloor: state => state.totalFloor
	}
}

export default ipo
