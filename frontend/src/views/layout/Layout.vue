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
    name: 'layout',
    components: {
      AppMain,
    },
    computed: {
      sidebar() {
        return this.$store.state.app.sidebar
      }
    },
    // 再创建之前，将路由传到store中
    beforeCreate(){
      if(this.$route.query!="undefined"){
        this.$store.commit('SET_TOKEN',{token:this.$route.query['access_token'],info:this.$route.query['tenant_info'],caseId:this.$route.query['caseId']})
      }
    },
    created(){
      let _this = this;
      //监听消息回复父级页面消息
      window.addEventListener('message',function(e){
          //console.log(document.body.offsetHeight)
          window.parent.postMessage({
              type:'times',
              height:document.body.offsetHeight,
              //param:param
          },'*')
          if(e.data['type']=='times'){
            // console.log(e.data.bodyClass)
            e.data.bodyClass != undefined && _this.$store.commit('BODY_CLASS',e.data.bodyClass)
          }
      });
    },
    mounted(){
      this.chartOne()
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
