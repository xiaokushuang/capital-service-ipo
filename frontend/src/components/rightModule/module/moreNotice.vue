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
            style="color:#0099CC"
            prop="relationFileTitle"
            label="公告名称"
            align="left"
            width="600">
            <template slot-scope="scope">
              <span style=" color:#0099CC">
                 <a v-if="scope.row.letterId!=''" @click="openLetterDetail(scope.row)">{{scope.row.relationFileTitle}}</a>
                 <a v-else  @click="openUrl(scope.row)">{{scope.row.relationFileTitle}}</a>
                <!-- {{ scope.row.relationFileTitle }} -->
              </span>
            </template>
                
            </el-table-column>
            <el-table-column
            label="操作"
            width="100"
            align="center"
            show-overflow-tooltip>
            <template slot-scope="scope">
              <i class="el-icon-download" style="font-size:20px"></i>
            </template>
            </el-table-column>
        </el-table>
        <div class="downloadAnnouncement">
            <span class="downloadAnnouncementSpan" v-if="this.multipleSelection.length==0">下载所选公告</span>
            <span class="downloadAnnouncementSpan" v-else style="border:1px solid #0099CC">下载所选公告<span style="color:#0099CC">{{this.multipleSelection.length}}</span></span>
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
        // debugger
        this.multipleSelection = val;
        console.log(this.multipleSelection.length)
      },
        openLetterDetail(v) {
          // window.open(v.siteBaseUrl+"/letter/viewShare?letterId="+v.letterId)
        },
        openUrl(obj) {
            //打开url
            // var url = obj.infoUrl;
            // var title = obj.relaName;
            // if (
            //     !(navigator.userAgent.indexOf("MSIE") >= 0) &&
            //     navigator.userAgent.indexOf("Opera") < 0
            // ) {
            //     if (this.pdfBaseUrl != null && this.pdfBaseUrl != "") {
            //         var _pdfBaseUrl = this.pdfBaseUrl;
            //     }

            //     if (
            //         obj.infoUrl
            //             .substring(obj.infoUrl.length - 4, obj.infoUrl.length)
            //             .toLowerCase() == ".pdf" &&
            //         _pdfBaseUrl
            //     ) {
            //         url =
            //             _pdfBaseUrl +
            //             "web/viewer.html?file=" +
            //             _pdfBaseUrl +
            //             "pdf/H2_" +
            //             obj.relaId +
            //             "_1.pdf&originTitle=" +
            //             title;
            //     }
            // }
            // window.open(encodeURI(url));
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
    }
    // .el-table__row td:nth-child(3) {
    //    color:#0099CC
    // }
}
</style>

