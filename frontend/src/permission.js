import router from './router'
import store from './store'
import {
  Message
} from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import {
  getToken
} from '@/utils/auth' // getToken from cookie

NProgress.configure({
  showSpinner: false
}) // NProgress Configuration

// permissiom judge function
function hasPermission(roles, permissionRoles) {
  if (roles.indexOf('admin') >= 0) return true // admin permission passed directly
  if (!permissionRoles) return true
  return roles.some(role => permissionRoles.indexOf(role) >= 0)
}

const whiteList = ['/login', '/authredirect'] // no redirect whitelist

router.beforeEach((to, from, next) => {
  NProgress.start() // start progress bar
  //if (getToken()) { // determine if there has token
  console.log(to)
  store.dispatch('addVisitedViews', to)
  store.commit('SET_ROUTE_NAME', to.name) //tab菜单切换路由控制按钮状态
  store.commit('SET_ROUTE_TITLE', to.meta.title) //模块title存储store中供给AppMain获取
  /* has token*/
  if (to.path === '/login') {
    next({
      path: '/'
    })
    NProgress.done() // if current page is dashboard will not trigger	afterEach hook, so manually handle it
  } else {
    // if (store.getters.roles.length === 0) { // 判断当前用户是否已拉取完user_info信息
    //   store.dispatch('GetUserInfo').then(res => { // 拉取user_info
    //     const roles = res.data.roles // note: roles must be a array! such as: ['editor','develop']
    //     store.dispatch('GenerateRoutes', {
    //       roles
    //     }).then(() => { // 根据roles权限生成可访问的路由表
    //       //console.log(store.getters.addRouters)
    //       router.addRoutes(store.getters.addRouters) // 动态添加可访问路由表
    //       //console.log(to)
    //       next({ ...to,
    //         replace: true
    //       }) // hack方法 确保addRoutes已完成 ,set the replace: true so the navigation will not leave a history record
    //     })
    //   }).catch(() => {
    //     store.dispatch('FedLogOut').then(() => {
    //       Message.error('Verification failed, please login again')
    //       next({
    //         path: '/login'
    //       })
    //     })
    //   })
    // } else {
    //   // 没有动态改变权限的需求可直接next() 删除下方权限判断 ↓
    //   if (hasPermission(store.getters.roles, to.meta.roles)) {
    //     //store.dispatch('routeName',to.name);
    //     store.commit('SET_ROUTE_NAME', to.name)
    //     next() //
    //   } else {
    //     next({
    //       path: '/401',
    //       replace: true,
    //       query: {
    //         noGoBack: true
    //       }
    //     })
    //   }
    //   // 可删 ↑
    // }

    if (store.getters.addRouters.length === 0) { //临时没有权限的方法，上面注释的别删
      console.log(store.getters.addRouters)
      store.dispatch('GenerateRoutes', {
        roles: 'admin'
      }).then(() => { // 根据roles权限生成可访问的路由表
        //console.log(store.getters.addRouters)
        router.addRoutes(store.getters.addRouters) // 动态添加可访问路由表
        //console.log(to)
        next({ ...to,
          replace: true
        }) // hack方法 确保addRoutes已完成 ,set the replace: true so the navigation will not leave a history record
        store.commit('SET_ROLES', ['admin'])
      })
    } else {
      next()
    }




  }
  // } else {
  //   /* has no token*/
  //   next()
  //   if (whiteList.indexOf(to.path) !== -1) { // 在免登录白名单，直接进入
  //     next()
  //   } else {
  //     next('/login') // 否则全部重定向到登录页
  //     NProgress.done() // if current page is login will not trigger afterEach hook, so manually handle it
  //   }
  // }
})

router.afterEach(() => {
  NProgress.done() // finish progress bar
})
