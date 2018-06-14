import { getZtree } from '@/api/declare'
import { getData } from '@/api/declare'
import { getFileData } from '@/api/declare'
import * as auth from '@/utils/auth'
const declare = {
    state: {
        declare_tree: [],
        table_data:[],
        file_data:[]
    },

    mutations: {
        SET_TREE_TYPE: (state, code) => {
            state.declare_tree = code.declare_tree
        },
        SET_TABLE_DATA:(state, code)=>{
            state.table_data = code.table_data
        },
        SET_FILE_DATAS:(state, code)=>{
            state.file_data = code.file_data
        },
    },

    actions: {
        getDeclare({
            commit
        }, param) {
            return new Promise((resolve, reject) => {
                const type = param.type
                param.data = null 
                getZtree(param).then((res) => { 
                   // console.log(res)
                    // param.data = res.data.result 
                    param.data = res.data
                    commit('SET_TREE_TYPE', param)
                    resolve()
                }).catch((error) => {
                    reject(error)
                })
            })
        },
        getTableData({
            commit
        }, param) {
            return new Promise((resolve, reject) => {
                const type = param.type
                param.data = null 
                getData(param).then((res) => { 
                   // console.log(res)
                    // param.data = res.data.result 
                    param.data = res.data.result
                    commit('SET_TREE_TYPE', param)
                    resolve()
                }).catch((error) => {
                    reject(error)
                })
            })
        },
        getfilesData({
            commit
        }, param) {
            return new Promise((resolve, reject) => {
                const type = param.type
                param.data = null 
                getFileData(param).then((res) => { 
                    // console.log(res)
                    // param.data = res.data.result 
                    param.data = res.data.result
                    commit('SET_TREE_TYPE', param)
                    resolve()
                }).catch((error) => {
                    reject(error)
                })
            })
        },
    },
    getters: {
        getDeclareData: state => state.declare_tree,
        getTableData:state => state.table_data,
        getFileDatas:state => state.file_data,
    }
}

export default declare
