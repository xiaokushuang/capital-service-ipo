import { getZtree } from '@/api/declare'
import * as auth from '@/utils/auth'
const declare = {
    state: {
        declare_tree: [],
    },

    mutations: {
        SET_TREE_TYPE: (state, code) => {
            state.declare_tree = code.declare_tree
        }
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
        }
    },
    getters: {
        //getDeclare: state => state.declare_tree
    }
}

export default declare
