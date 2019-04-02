<template>
    <div class="moreNotice">
         <el-table
            :data="moreNoticeList[0]"
             height="380"
            tooltip-effect="dark"
            style="width: 100%"
            @selection-change="handleSelectionChange">
            <el-table-column
            prop="relaId"
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
            style="color:#0099CC"
            prop="relationFileTitle"
            label="公告名称"
            align="left"
            width="600">
            <template slot-scope="scope">
              <span style=" color:#0099CC">
                 <a v-if="scope.row.letterId!=''" @click="openLetterDetail(scope.row)">{{scope.row.relationFileTitle}}</a>
              </span>
            </template>
                
            </el-table-column>
            <el-table-column
            label="操作"
            width="100"
            align="center"
            show-overflow-tooltip>
            <template slot-scope="scope">
              <i @click="handleDown(scope.row)" class="el-icon-download" style="font-size:20px"></i>
            </template>
            </el-table-column>
        </el-table>
        <div class="downloadAnnouncement">
            <span @click="handleDownAll()" class="downloadAnnouncementSpan" v-if="this.multipleSelection.length==0">下载所选公告</span>
            <span @click="handleDownAll()" class="downloadAnnouncementSpan" v-else style="border:1px solid #0099CC">下载所选公告<span style="color:#0099CC">{{this.multipleSelection.length}}</span></span>
        </div>
    </div>
</template>
<script>
import {getDownloadFileData} from '@/api/ipoCase/companyProfile'
export default {
    name:'moreNotice',
    data() {
      return {
        multipleSelection: [],
      }
    },
    props:["moreNoticeList"],
    // 实时监听数据变化
    watch:{
    moreNoticeList: {  
　　　　handler(newValue, oldValue) {  
        this.moreNoticeList = newValue
　　　　},  
　　　　deep: true,  //对象内部的属性监听，也叫深度监听
       immediate: true //immediate表示在watch中首次绑定的时候，是否执行handler，值为true则表示在watch中声明的时候，就立即执行handler方法，值为false，则和一般使用watch一样，在数据发生变化的时候才执行handler
　　}  
  },
    methods: {
       //初始化下载数据
      initDownloadFileData(fileId,fileType) {
        // 动态传id
        let fileIdLabel = '';
        for(let i = 0;i<fileId.length;i++){
            fileIdLabel =  fileId[i].relaId+ "," + fileIdLabel ;
        }
        // 将second多选按钮参数最后一个  ，号去掉
        if(fileIdLabel && fileIdLabel.length >0){
            fileIdLabel = fileIdLabel.substring(0,fileIdLabel.length-1);
        }
        const param = {
          fileId:fileIdLabel,
          fileType:fileType
        }
          console.log(param)
        getDownloadFileData(param).then(res => {
            console.log(res)
        })
      },
        initDownloadFileData1(fileId,fileType) {
        // 动态传id
        const param = {
          fileId:fileId,
          fileType:fileType
        }
          console.log(param)
        getDownloadFileData(param).then(res => {
            console.log(res)
        })
      },
      // 下载公告
      handleDown(v){
        this.initDownloadFileData1(v.relaId,this.moreNoticeList[1])
      },
      // 下载所有公告
      handleDownAll(){
        this.initDownloadFileData(this.multipleSelection,this.moreNoticeList[1])
      },
      handleSelectionChange(val) {
        this.multipleSelection = val
      },
      openLetterDetail(v) {
         window.open(v.baseUrl)
      },
    }
}
</script>
<style scoped lang="scss">
.downloadAnnouncement{
    width:100%;
    background:white;
    .downloadAnnouncementSpan{
        display:inline-block;
        padding:3px;
        margin-top:10px;
        margin-left:50px;
        border:1px solid rgba(202, 202, 202, 1);
        border-radius: 2px;
        cursor:pointer;
    }
}
</style>

