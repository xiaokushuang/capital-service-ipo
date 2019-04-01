import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import errorLog from './modules/errorLog'
import permission from './modules/permission'
import tagsView from './modules/tagsView'
import user from './modules/user'
import getters from './getters'
import ipo from './ipoData'
import declare from './declare'
Vue.use(Vuex);

const store = new Vuex.Store({
  state:{
    userCompanyId:''
  },
  modules: {
    app,
    errorLog,
    permission,
    tagsView,
    user,
    ipo,
    declare
  },
  getters,

});

export default store
