<template>
  <el-container>
    <el-main>
      <el-tabs v-model="editableTabsValue" type="card" @tab-click="tos">
        <el-tab-pane
          :key="item.name"
          v-for="(item, index) in editableTabs"
          :label="item.title"
          :name="item.name"
          :to="item.to"
        >
        </el-tab-pane>
      </el-tabs>
      <el-card>
        <router-view></router-view>
      </el-card>
    </el-main>
  </el-container>
  
</template>
<script>
  import { mapGetters } from 'vuex'
  export default {
    data() {
      return {
        editableTabsValue: this.get_routename,
        editableTabs: [{
          title: 'Tab 1',
          name: '1',
          content:'静态可忽略1',
          to:'/tabpage/list/menu1'
        }, {
          title: 'Tab 2',
          name: '2',
          content: '静态可忽略2',
          to:'/tabpage/list/menu2'
        }],
      }
    },
    methods: {
      tos(ob){
        let path = this.editableTabs[ob.index].to;
        this.$router.replace(path)  
      }
    },
    created(){
      this.editableTabsValue=this.get_routename
    },
    computed: {
      ...mapGetters([
        'get_routename'
      ])
    },
    watch:{
      get_routename(n,o){
        console.log('路由改变')
        this.editableTabsValue=n
      }
    }
  }
</script>
<style>
  .el-row{
    margin-top:5px;
  }
  .el-main{
    padding:10px;
  }
  .el-tabs__header{
    margin:0;
  }
  .el-card{
    border-radius:0;
    border-top:0;
  }
</style>