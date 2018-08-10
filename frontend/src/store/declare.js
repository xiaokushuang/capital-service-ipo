import { getZtree } from '@/api/declare'
import { getData } from '@/api/declare'
import { getFileData } from '@/api/declare'
import { getLawsByTable } from '@/api/declare'
import * as auth from '@/utils/auth'
import { MultidimensionalData } from '@/utils/index'
import { OneDimensionalDataVariableMultidimensionalData } from '@/utils/index'
const declare = {
    state: {
        declare_tree: [],
        table_data:[],
        file_data:[],
        law_data:[],
    },

    mutations: {
        SET_TREE_TYPE: (state, code) => { 
            state[code.type] = code.data
        },
        SET_TABLE_DATA:(state, code)=>{
            state[code.type] = code.data
        },
        SET_FILE_DATAS:(state, code)=>{
            state[code.type] = code.data
        },
        SET_LAWS_DATA:(state, code)=>{
            state[code.type] = code.data
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
                      param.data = OneDimensionalDataVariableMultidimensionalData(res.data.data)
                    // param.data =  res.data
                    param.type = "declare_tree" 
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
                    param.type = "table_data"
                    commit('SET_TABLE_DATA', param)
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
                    param.type = "file_data"
                    commit('SET_FILE_DATAS', param)
                    resolve()
                }).catch((error) => {
                    reject(error)
                })
            })
        },
        getLawsData({
            commit
        }, param) {
            return new Promise((resolve, reject) => {
                const type = param.type
                param.data = null 
                getLawsByTable(param).then((res) => { 
                    console.log(res)  
                    param.data = res.data.result
                    param.type = "law_data"
                    commit('SET_LAWS_DATA', param)
                    resolve()
                }).catch((error) => {
                    reject(error)
                })
            })
        },//
    },
    getters: {
        getDeclareData: state => state.declare_tree,
        getTableData1:state => state.table_data,
        getFileDatas:state => state.file_data,
        getLawsDataToTab:state => state.law_data,
    }
}

export default declare
