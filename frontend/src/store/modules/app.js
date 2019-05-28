import Cookies from 'js-cookie'

const app = {
  state: {
    sidebar: {
      opened: !!+Cookies.get('sidebarStatus')
    },
    language: Cookies.get('language') || 'en',
    parentCookie:{
      title:null,
      target:null,
      type:null,
    },//易董记录用户操作功能
    parentCookieFlag:false,
    token:null,
    info:null,
    companyId:null,
    caseId:null
  },
  mutations: {
    TOGGLE_SIDEBAR: state => {
      if (state.sidebar.opened) {
        Cookies.set('sidebarStatus', 0)
      } else {
        Cookies.set('sidebarStatus', 1)
      }
      state.sidebar.opened = !state.sidebar.opened
    },
    SET_LANGUAGE: (state, language) => {
      state.language = language
      Cookies.set('language', language)
    },
    SET_PARENT_COOKIE: (state,data)=>{
        state.parentCookie.title = data.title;
        state.parentCookie.target = data.target;
        state.parentCookie.type = data.type;
    },
    SET_PARENT_COOKIE_FLAG: (state,data)=>{
      state.parentCookieFlag = data;
    },
    SET_PARENT_COOKIE_TYPE: (state,data)=>{
      state.parentCookie.type = data;
    },
    SET_TOKEN:(state,data) =>{
      state.token = data.token;
      state.info = data.info;
      state.companyId = data.companyId;
      state.caseId = data.caseId;
    },
    // 添加日志功能
    CREATE_MESSAGE: (state,data) => {
      Object.keys(data).map((obj)=>{
        state.parentCookie[obj] = data[obj]
      })
  },

  },
  actions: {
    toggleSideBar({commit}) {
      commit('TOGGLE_SIDEBAR')
    },
    setLanguage({commit}, language) {
      commit('SET_LANGUAGE', language)
    }
  }
}

export default app
