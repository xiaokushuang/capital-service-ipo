
import {
	MultidimensionalData
} from "@/utils/index"
import {
	getLawsByTable
} from '@/api/declare'
import * as auth from '@/utils/auth'

const ipoCase = {
	namespaced: true,
	state: {
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
			debugger;
			state.removeIpoNote = code
		},
		SET_IPO_CASE_NOTE: (state, code) => {
			debugger
			for (var key in code) {
				state[key] = code[key]
			}
		},
	
	},

	actions: {
	
	},
	getters: {
		removeNote: state => state.removeNote,
		destroyIpoFavorite: state => state.destroyIpoFavorite,
		destroyIpoNote: state => state.destroyIpoNote,
		removeIpoNote: state => state.removeIpoNote,
		noteIpoEditFlag: state => state.noteIpoEditFlag,
		noteIpoCaseId: state => state.noteIpoCaseId,
		caseIpoNote: state => state.caseIpoNote,
	}
}

export default ipoCase
