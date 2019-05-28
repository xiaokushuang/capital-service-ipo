import Cookies from 'js-cookie'

const app = {
  state: {
    sidebar: {
      opened: !!+Cookies.get('sidebarStatus')
    },
    language: Cookies.get('language') || 'en',
    parentCookie:{
      client_type:'',//手机或pc
      recordType:'',//跳转页面方式:
      recordModule:'',//跳转模块
      recordTab:"",//跳转tab
      recordTabChild:"",//跳转子集tab
      popTitle:null,//弹窗title
      title:null,
      eventTargetText:null
    },
    // parentCookie:{
    //   title:null,
    //   target:null,
    //   type:null,
    // },//易董记录用户操作功能
    parentCookieFlag:false,
    token:null,
    info:null,
    companyId:null,
    caseId:null,
    temporaryUpdateFlag:false, //临时修改Cookie标志 一般用来列表页进详情页
    tempParentCookie:{},// 临时记录cookie
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
      state.parentCookie.eventTargetText = data.target;
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
    // 添加日志功能1
    CREATE_MESSAGE: (state,data) => {
      Object.keys(data).map((obj)=>{
        state.parentCookie[obj] = data[obj]
      })
    },
    SET_TEMPORARY_UPDATE_FLAGg: (state,data)=>{
      state.temporaryUpdateFlag = data;
    },
    CREATE_TEMP_MESSAGE: (state,data) => {
      state.temporaryUpdateFlag = true; 
      state.tempParentCookie = JSON.parse(JSON.stringify(state.parentCookie));
      Object.keys(data).map((obj)=>{
        state.tempParentCookie[obj] = data[obj]
      })
    }
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
