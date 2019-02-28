import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'font-awesome/css/font-awesome.min.css'
// 引入echarts
import echarts from 'echarts'
Vue.prototype.$echarts = echarts 
import '@/styles/index.scss' // global css
import App from './App'
import router from './router'
import store from './store'

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
if(process.env.NODE_ENV === 'development'){
  require('./mock')
}
// es6Promise .polyfill();

// 滚动条锚点定位
// import vueScrollwatch from "vue-scrollwatch"
// Vue.use(vueScrollwatch)


import VueMoment from 'vue-moment'
import moment from 'moment-timezone'
 
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

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  i18n,
  template: '<App/>',
  components: { App }
})
