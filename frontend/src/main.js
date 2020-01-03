import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// 引入echarts
//import echarts from 'echarts'
//Vue.prototype.$echarts = echarts
//import 'echarts/map/js/china.js';

import '@/styles/index.scss' // global css
import App from './App'
import router from './router'
import store from './store'
import request from '@/utils/request'
import i18n from './lang' // Internationalization
import './icons' // icon
import './errorLog' // error log
import './extends'
import './permission' // permission control
import './directives' // global directive
// import './mock' // simulation data
import * as filters from './filters' // global filters
import 'vue-orgchart/dist/style.min.css'
import 'babel-polyfill'
// moke开关
// if(process.env.NODE_ENV === 'development'){
//   require('./mock')
// }
// es6Promise .polyfill();

// 滚动条锚点定位
// import vueScrollwatch from "vue-scrollwatch"
// Vue.use(vueScrollwatch)


import VueMoment from 'vue-moment'
import moment from 'moment-timezone'
// 点击图片放大插件
import preview from 'vue-photo-preview'
import 'vue-photo-preview/dist/skin.css'
Vue.use(preview)

Vue.use(VueMoment,{
    moment
})

Vue.use(Element, {
  size: 'medium', // set element-ui default size
  i18n: (key, value) => i18n.t(key, value)
})

// register global utility filters.
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})
// 日志-----头
Vue.prototype.$open = function(url){
  url = url||null;
  // debugger
  let data = store.state.app.parentCookie;
  if(store.state.app.temporaryUpdateFlag){//如果使用临时Cookie
    data = store.state.app.tempParentCookie;
    store.commit('SET_TEMPORARY_UPDATE_FLAGg',false);
  }
  request({
    url: '/log/collect',
    method: 'post',
    data:data
  })
  return window.open(url)
}
// 日志-----尾
Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  i18n,
  template: '<App/>',
  components: { App }
})


Vue.filter('dataInThRule', function (value) {
  if(!value) return value
  if(value === '') return value
  // if(value < 0) return 0;
  let str = parseFloat(value).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,')
  return str;
})
