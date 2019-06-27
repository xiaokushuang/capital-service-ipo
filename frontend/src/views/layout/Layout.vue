<template>
  <div class="app-wrapper" :class="{hideSidebar:!sidebar.opened}">
    <!-- <sidebar class="sidebar-container"></sidebar>
    <div class="main-container">
      <navigator></navigator>
      <tags-view></tags-view>
      <app-main></app-main>
    </div> -->
      <app-main></app-main>
  </div>
</template>

<script>
  import {AppMain} from './components'
  import {setToken, getToken} from '@/utils/auth'

  export default {
    data(){
      return{
        tenantInfo:'',//日志
      }
    },
    name: 'layout',
    components: {
      AppMain,
    },
    computed: {
      sidebar() {
        return this.$store.state.app.sidebar
      }
    },
    beforeCreate() {
      if(this.$route.query!="undefined"){
            this.$store.commit('SET_TOKEN',{token:this.$route.query['access_token'],info:this.$route.query['tenant_info'],companyId:this.$route.query['companyId'],caseId:this.$route.query['caseId']})
      }
    },
    created() {
      // 日志--------------------功能头
      let param = {
        client_type: 'pc',//手机或pc
        recordType: 'menu',//跳转页面方式:
        recordModule: 'IPO案例',//跳转模块
        recordTab: "ipo案例详情页",//跳转tab
        recordTabChild: null,//跳转子集tab
        popTitle: null//弹窗title
      }
      this.$store.commit('CREATE_MESSAGE', param)
      // 日志------------------功能尾
      let _this = this;
      //监听消息回复父级页面消息
      if(window.parent.length > 0){
          window.addEventListener('message', function (e) {
          //console.log(document.body.offsetHeight)
          window.parent.postMessage({
            type: 'times',
            height: document.body.offsetHeight,
            //param:param
          }, '*')
          if (e.data['type'] == 'times') {
            e.data.bodyClass != undefined && _this.$store.commit('BODY_CLASS', e.data.bodyClass)
          }
        });
      }
    },
    mounted(){
      this.chartOne()
      let t = this
      document.body.addEventListener('mousedown',function(e){
        t.$store.commit('SET_PARENT_COOKIE',{title:t.$route.meta.title,target:e.target.innerText,type:'func'})
        t.$store.commit('SET_PARENT_COOKIE_FLAG',true)
      })
    },
    methods:{
      chartOne(){
          if(this.$route.query!="undefined"){
              //if(getToken()=='undefined'){
                setToken(this.$route.query['access_token'])
              //}
          }
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/mixin.scss";

  .app-wrapper {
    @include clearfix;
    position: relative;
    height: 100%;
    width: 100%;
  }
</style>
