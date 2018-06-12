import Vue from 'vue'
import Router from 'vue-router'
/* Layout */
import Layout from '../views/layout/Layout'

const _import = require('./_import_' + process.env.NODE_ENV)
console.log(_import('financeStatistics/financeSearch'))
// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/** note: submenu only apppear when children.length>=1
 *   detail see  https://panjiachen.github.io/vue-element-admin-site/#/router-and-nav?id=sidebar
 **/

/**
 * hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
 *                                if not set alwaysShow, only more than one route under the children
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']     will control the page roles (you can set multiple roles)
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
    noCache: true                if fasle ,the page will no be cached(default is false)
  }
 **/
export const constantRouterMap = [
  { path: '/404', component: _import('errorPage/404'), hidden: true },
  { path: '/401', component: _import('errorPage/401'), hidden: true },
  // {
  //   path: '',
  //   component: Layout,
  //   redirect: 'dashboard',
  //   meta: {title: '首页', icon: 'dashboard'},
  //   children: [
  //     {
  //       path: 'dashboard',
  //       component: _import('index/dashboard'),
  //       name: 'dashboard',
  //       meta: {title: '首页', icon: 'dashboard', noCache: true}
  //     }
  //   ]
  // }
  {// financeStatistics/financeSearch
    path: '',
    component: Layout,
    redirect: 'securitiesInit',
    meta: { title: '首页', icon: 'dashboard' },
    children: [
      {
        path: 'financeSearch',
        component: _import('financeStatistics/financeSearch'),
        name: 'financeSearch',
        meta: { title: '首页', icon: 'dashboard', noCache: true }
      },
      {
        path: 'securitiesInit',
        component: _import('financeStatistics/securitiesInit'),
        name: 'securitiesInit',
        meta: { title: '首页', icon: 'dashboard', noCache: true }
      },
      {
        path: 'companyContrast',
        component: _import('financeStatistics/companyBox'),
        name: 'companyContrast',
        meta: { title: '公司对比', icon: 'dashboard', noCache: true }
      }
    ]
  },
  {
    path: '/financeBond',
    component: Layout,
    redirect: '/financeBond/securitiesBond',
    children: [{
      path: 'securitiesBond',
      component: _import('financeBond/securitiesBond'),
      name: 'securitiesBond',
      meta: { title: '债券发行', icon: 'securitiesBond', noCache: true }
    }]
  }
]

export default new Router(
  {
    // mode: 'history', // require service support
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRouterMap,
    mode: 'history'
  }
)

export const asyncRouterMap = [
  {
    path: '/modules',
    component: Layout,
    redirect: '/modules/tinymce',
    meta: { title: '样例', icon: 'lock', roles: ['admin'] }, // you can set roles in root nav
    // meta: { title: '样例', icon: 'lock', roles: ['admin'], rolePermissions['selectButton'] }, // you can set roles in root nav
    name: 'modules',
    children: [
      {
        path: 'tinymce',
        component: _import('components-demo/tinymce'),
        name: 'tinymce-demo',
        meta: { title: '富文本' }
      },
      {
        path: 'dnd-list',
        component: _import('components-demo/dndList'),
        name: 'dndList-demo',
        meta: { title: '拖拽排序' }
      },
      {
        path: 'splitpane',
        component: _import('components-demo/splitpane'),
        name: 'splitpane-demo',
        meta: { title: '宽高拖动' }
      },
      {
        path: 'avatar-upload',
        component: _import('components-demo/avatarUpload'),
        name: 'avatarUpload-demo',
        meta: { title: '上传头像' }
      },
      {
        path: 'dropzone',
        component: _import('components-demo/dropzone'),
        name: 'dropzone-demo',
        meta: { title: '上传图片' }
      },
      {
        path: 'sticky',
        component: _import('components-demo/sticky'),
        name: 'sticky-demo',
        meta: { title: '吸顶导航' }
      },
      {
        path: 'count-to',
        component: _import('components-demo/countTo'),
        name: 'countTo-demo',
        meta: { title: '计时' }
      },
      {
        path: 'mixin',
        component: _import('components-demo/mixin'),
        name: 'componentMixin-demo',
        meta: { title: '按钮及模块' }
      },
      {
        path: 'back-to-top',
        component: _import('components-demo/backToTop'),
        name: 'backToTop-demo',
        meta: { title: '回到顶部' }
      },
      {
        path: 'keyboard',
        component: _import('charts/keyboard'),
        name: 'keyboardChart',
        meta: { title: 'chart使用1', noCache: true }
      },
      {
        path: 'line',
        component: _import('charts/line'),
        name: 'lineChart',
        meta: { title: '线性chart', noCache: true }
      },
      {
        path: 'mixchart',
        component: _import('charts/mixChart'),
        name: 'mixChart',
        meta: { title: '柱形图', noCache: true }
      },
      {
        path: '/table',
        component: _import('example/table/index'),
        redirect: '/table/complex-table',
        name: 'Table',
        meta: { title: '表格', icon: 'table' },
        children: [
          {
            path: 'dynamic-table',
            component: _import('example/table/dynamicTable/index'),
            name: 'dynamicTable',
            meta: { title: '选项定制' }
          },
          {
            path: 'drag-table',
            component: _import('example/table/dragTable'),
            name: 'dragTable',
            meta: { title: '拖拽排序' }
          },
          {
            path: 'inline-edit-table',
            component: _import('example/table/inlineEditTable'),
            name: 'inlineEditTable',
            meta: { title: '编辑单个' }
          },
          {
            path: 'tree-table',
            component: _import('example/table/treeTable/treeTable'),
            name: 'treeTableDemo',
            meta: { title: '树型表格' }
          },
          {
            path: 'custom-tree-table',
            component: _import('example/table/treeTable/customTreeTable'),
            name: 'customTreeTableDemo',
            meta: { title: '树型表格点击' }
          },
          {
            path: 'complex-table',
            component: _import('example/table/complexTable'),
            name: 'complexTable',
            meta: { title: '表格编辑' }
          }
        ]
      },
      {
        path: 'tab',
        component: _import('example/tab/index'),
        name: 'tab',
        meta: { title: 'tab切换', icon: '' }
      },
      {
        path: 'createform',
        component: _import('form/create'),
        name: 'createForm',
        meta: { title: '布局', icon: '' }
      },
      {
        path: '401',
        component: _import('errorPage/401'),
        name: 'page401',
        meta: { title: 'page401', noCache: true }
      },
      {
        path: '404',
        component: _import('errorPage/404'),
        name: 'page404',
        meta: { title: 'page404', noCache: true }
      },
      {
        path: 'log',
        component: _import('errorLog/index'),
        name: 'errorLog',
        meta: { title: '错误日志', icon: '' }
      },
      {
        path: 'export-excel',
        component: _import('excel/exportExcel'),
        name: 'exportExcel',
        meta: { title: '导出excel' }
      },
      {
        path: 'export-selected-excel',
        component: _import('excel/selectExcel'),
        name: 'selectExcel',
        meta: { title: '导出选择的excel' }
      },
      {
        path: 'upload-excel',
        component: _import('excel/uploadExcel'),
        name: 'uploadExcel',
        meta: { title: '上传excel' }
      },
      {
        path: 'download',
        component: _import('zip/index'),
        name: 'exportZip',
        meta: { title: '导出zip' }
      },
      {
        path: 'theme',
        component: _import('theme/index'),
        name: 'theme',
        meta: { title: '表单元素', icon: '' }
      },
      {
        path: 'i18n',
        component: _import('i18n-demo/index'),
        name: 'i18n',
        meta: { title: '国际化', icon: '' }
      }
    ]
  },
  {
    path: '/tabpage',
    component: Layout,
    meta: { title: '页面切换例子', icon: 'money' },
    children: [
      {
        path: '/tabpage/list',
        component: _import('tabPage/index'),
        name: 'list',
        meta: { title: 'a1', icon: 'dashboard', noCache: true },
        children: [{
          path: 'menu1',
          component: _import('tabPage/module/tab1'),
          name: '1',
          meta: { title: '页面1', icon: 'dashboard', noCache: true }
        },
        {
          path: 'menu2',
          component: _import('tabPage/module/tab2'),
          name: '2',
          meta: { title: '页面2', icon: 'dashboard', noCache: true }
        }
        ]
      }
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]
