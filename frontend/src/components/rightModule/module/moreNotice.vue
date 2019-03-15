<template>
    <div class="moreNotice">
         <el-table
            ref="multipleTable"
            :data="moreNoticeList"
             height="380"
            tooltip-effect="dark"
            style="width: 100%"
            @selection-change="handleSelectionChange">
            <el-table-column
            type="selection"
            align="center"
            width="100">
            </el-table-column>
            <el-table-column
            label="公告日期"
            align="left"
            width="150">
            <template slot-scope="scope">{{ scope.row.publishTime }}</template>
            </el-table-column>
            <el-table-column
            prop="relationFileTitle"
            label="公告名称"
            align="left"
            width="600">
            </el-table-column>
            <el-table-column
            prop="tableSort"
            label="操作"
            width="100"
            align="center"
            show-overflow-tooltip>
            </el-table-column>
        </el-table>
        <div class="downloadAnnouncement">
            <span>下载所选公告</span>
        </div>
    </div>
</template>
<script>
export default {
    name:'moreNotice',
    data() {
      return {
        multipleSelection: []
      }
    },
    props:["moreNoticeList"],
    // 实时监听数据变化
    watch:{
    moreNoticeList: {  
　　　　handler(newValue, oldValue) {  
        this.moreNoticeList = newValue
　　　 　console.log(this.moreNoticeList)  
　　　　},  
　　　　deep: true,  //对象内部的属性监听，也叫深度监听
       immediate: true //immediate表示在watch中首次绑定的时候，是否执行handler，值为true则表示在watch中声明的时候，就立即执行handler方法，值为false，则和一般使用watch一样，在数据发生变化的时候才执行handler
　　}  
  },
    methods: {
      handleSelectionChange(val) {
        this.multipleSelection = val;
      }
    }
}
</script>
<style scoped lang="scss">
.downloadAnnouncement{
    width:100%;
    background:white;
    span{
        display:inline-block;
        padding:2px;
        margin-top:10px;
        margin-left:50px;
        border:1px solid rgba(202, 202, 202, 1);
        border-radius: 2px;
    }
}
</style>

