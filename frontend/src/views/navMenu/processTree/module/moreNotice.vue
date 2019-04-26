<template>
    <div class="moreNotice" >
         <el-table
            :data="moreNoticeList[0]"
             height="330"
            tooltip-effect="dark"
            style="width: 100%"
            @selection-change="handleSelectionChange">
            <el-table-column
            prop="relaId"
            type="selection"
            align="center"
            width="75">
            </el-table-column>
            <el-table-column
            label="公告日期"
            align="left"
            width="100">
            <template slot-scope="scope">{{ scope.row.publishTime }}</template>
            </el-table-column>
            <el-table-column
            style="color:#0099CC"
            prop="relationFileTitle"
            label="公告名称"
            min-width="30%"
            align="left"
            >
            <template slot-scope="scope">
              <span style=" color:#14bcf5">
                 <a v-if="scope.row.letterId!=''" @click="openLetterDetail(scope.row)">{{scope.row.relationFileTitle}}</a>
              </span>
            </template>

            </el-table-column>
            <el-table-column
            label="操作"
            width="50"
            align="left"
            show-overflow-tooltip>
            <template slot-scope="scope">
              <i @click="handleDown(scope.row)" class="el-icon-download" style="font-size:20px !important;cursor:pointer"></i>
            </template>
            </el-table-column>
        </el-table>
        <div class="downloadAnnouncement">
            <span @click="handleDownAll()" class="downloadAnnouncementSpan" v-if="this.multipleSelection.length==0">下载所选公告</span>
            <span @click="handleDownAll()" class="downloadAnnouncementSpan" v-else style="border:1px solid #0099CC">下载所选公告<span style="color:#0099CC">{{this.multipleSelection.length}}</span></span>
        </div>
        <el-dialog
        :modal="false"
         class="noGonggao"
          :visible.sync="dialogVisible"
          width="30%"
          :before-close="handleClose">
          <span style="display: inline-block; margin-left: 83px; margin-top: 23px;">所选公告暂不支持下载</span>
          <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
          </span>
        </el-dialog>
    </div>
</template>
<script>
import {getDownloadFileData} from '@/api/ipoCase/companyProfile'
import {checkFile} from '@/api/ipoCase/companyProfile'
export default {
    name:'moreNotice',
    data() {
      return {
        multipleSelection: [],
        dialogVisible:false,
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
        checkFile(param).then(res => {
          let result = res.data.result;
          if(result === '1'){
            let url = window.location.href;
            let token = this.$store.state.app.token
            url = url.substr(0,url.indexOf("ui"));
            url = url + 'ipo/ipoProcess/downloadFile?access_token='+token+
                  '&fileId='+ fileIdLabel + '&fileType='+ fileType;
            window.open(url);
          }else{
            this.dialogVisible = true
          }

        })
      },
        initDownloadFileData1(fileId,fileType) {
        // 动态传id
        const param = {
          fileId:fileId,
          fileType:fileType
        }
          checkFile(param).then(res => {
            let result = res.data.result;
            if(result === '1'){
              let url = window.location.href;
              let token = this.$store.state.app.token
              url = url.substr(0,url.indexOf("ui"));
              url = url + 'ipo/ipoProcess/downloadFile?access_token='+token+
                    '&fileId='+ fileId + '&fileType='+ fileType;
              window.open(url);
            }else{
               this.dialogVisible = true
            }

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
      handleClose(done) {
        this.dialogVisible = false
      }
    }
}
</script>
<style scoped lang="scss">
.downloadAnnouncement{
    width:100%;
    background:white;
    .downloadAnnouncementSpan{
        display: inline-block;
        padding: 3px 10px;
        margin-top: 16px;
        margin-left: 0px;
        border: 1px solid #cacaca;
        border-radius: 2px;
        cursor: pointer;
        color: rgb(51, 51, 51);
    }
}
</style>

