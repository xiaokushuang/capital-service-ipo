<template>
  <div v-loading="flagLoading" element-loading-text="给我一点时间" class="allJincheng">
    <div v-for="boxDataItem in treeList" v-if="treeList.length>0" :key="boxDataItem.treeTypeCode">
      <!-- 第一个进程 -->
      <div>
        <el-row style="padding-left:12px">
          <el-col :span="24" style="border-left:1px solid #E9E9E9; margin-bottom: -10px;margin-top:10px;padding-bottom: 10px;">
            <!-- 进程名 -->
            <div class="jincheng">
              <img  v-if="boxDataItem.treeTypeCode" src="../../../assets/images/jinchengjian.png" alt="">
              <p v-if="boxDataItem.treeTypeCode == '00'">辅导工作进程</p>
            </div>
            <div>
              <div v-for="(item,index) in boxDataItem.proList" >
                <div class="right" >
                  <div class="border-box">
                      <span :id="'sign' +  item.proSort" class="circle" v-if="boxDataItem.proList.length">
                           <!-- 展示序号 -->
                              <span :id="'num' +  item.proSort">
                                  {{item.proSort}}
                              </span>
                      </span>
                  </div>
                  <div class="border-right">
                    <div style="height: 19px;">
                      <div style="font-size: 16px; color: #333333;display:inline-block;margin-right: 10px;"
                           v-text='item.progressName'
                           @click="showAndHide(boxDataItem,'each' + item.proSort ,item, 'title')"
                           @mouseenter="onMouseOver('each' +  item.proSort, item, index)"
                           @mouseleave="onMouseOut('each' +  item.proSort, item, index)"
                           class="tinyHand">
                      </div>
                    </div>
                    <div style="font-size: 12px;margin-top: 8px;color: #999;margin-bottom: 12px;">
                      <span  @mouseenter="onMouseOver('each' +  item.proSort, item, index)" style="cursor: pointer;"
                             @mouseleave="onMouseOut('each' +  item.proSort, item, index)" @click="showAndHide(boxDataItem,'each' + item.proSort ,item, 'time')" v-if="item.processTime" v-text='item.processTime'></span>
                      <span  @mouseenter="onMouseOver('each' +  item.proSort, item, index)" style="cursor: pointer;"
                             @mouseleave="onMouseOut('each' +  item.proSort, item, index)" @click="showAndHide(boxDataItem,'each' + item.proSort ,item, 'time')" v-if="item.processTime">&nbsp;&nbsp;</span>
                      <span  @mouseenter="onMouseOver('each' +  item.proSort, item, index)"
                             @mouseleave="onMouseOut('each' +  item.proSort, item, index)" @click="showAndHide(boxDataItem,'each' + item.proSort ,item, 'time')" v-if="item.lastDay" style="display:inline-block;width: 65%;cursor: pointer;">距离上个进程{{item.lastDay}}</span>
                      <!-- 前面图标 -->
                      <div :id="'each' +  item.proSort" style="display:none;">
                        <div :ref=' item.proSort' :class="'abc'+ item.proSort"></div>
                      </div>
                      <p v-if="item.relaList.length===0" class="gonggao"  style="color:#0086A7;font-size:14px;display:none;margin-bottom: 24px;margin-top: 8px;"><a></a></p>
                      <p v-else v-show="item.flag" @click="gonggaoClick(item.relaList[0])" class="gonggao" style="display: block" :id="'more'+  item.proSort">
                        <a>{{item.relaList[0].relationFileTitle}}</a>
                      </p>
                    </div>
                    <div v-if="item.flag&&item.relaList.length>1" style="margin-bottom: 24px;margin-top: 8px;">
                        <span>
                            <span>
                                <a v-if="boxDataItem.treeTypeCode == '02'" @click="moreNoticeClick(boxDataItem,item)" class="moreNoticeCss">查看更多公告 ></a>
                                <a v-else @click="moreNoticeClick(boxDataItem,item)" class="moreNoticeCss">查看更多文件 ></a>
                            </span>
                        </span>
                    </div>
                    <div v-show="item.relaList.length!=0" v-if="!item.flag" style="margin-bottom: 14px;margin-top: 8px;">
                        <span>
                            <span>
                              <!-- 第二个进程展示的是‘查看文件’ -->
                                <div style="margin-bottom: 14px;margin-top: 8px;cursor: pointer"  v-show="item.relaList.length>0" @click="showAndHide(boxDataItem,'each' + item.proSort,item, 'file')" class="moreNoticeCss">查看文件 ></div>
                            </span>
                        </span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- 点击查看更多公告内容弹窗 -->
            <div class="popWindow">
              <el-dialog :title= moreNoticeDailog :visible.sync="dialogVisible" :close-on-click-modal="false" append-to-body id="moreNoticeDailog">
                <div style="background: #cccccc">
                  <moreNotice :moreNoticeList = "[moreNoticeList,fileType]"></moreNotice>
                </div>
              </el-dialog>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
    <div v-else>
      <span>暂无数据</span>
    </div>
  </div>
</template>

<script>
  import Vue from "vue";
  import $ from "jquery";
  import moreNotice from "./module/moreNotice"
  import {getRightModuleData} from '@/api/ipoCase/companyProfile'

  export default {
    name: "processTree",
    props:["treeList"],
    data() {
      return {
        caseId1:this.$store.state.app.caseId,
        treeTypeCode:'',
        // 鼠标移入展示文字
        isShowSpreadText:false,
        // treeList:[],
        //  更多公告数据
        moreNoticeList:[],
        //  图片路径
        zhbj:'url('+ require('../../../assets/images/zhbj.png')+') no-repeat',
        tg:'url('+ require('../../../assets/images/tg.png')+') no-repeat',
        htg:'url('+ require('../../../assets/images/htg.png')+')  no-repeat',
        zcsx:'url('+ require('../../../assets/images/zcsx.png')+')  no-repeat',
        whtg:'url('+ require('../../../assets/images/whtg.png')+') no-repeat',
        qxsh:'url('+ require('../../../assets/images/qxsh.png')+') no-repeat',
        dsh:'url('+ require('../../../assets/images/dsh.png')+') no-repeat',
        wtg:'url('+ require('../../../assets/images/wtg.png')+') no-repeat',
        moreNoticeDailog: '',
        dialogVisible: false,
        showArray: [],

        flagLoading: false,
        //组件
        id: "",
        progressType: "",
        sort: "",
        sortFlag: '0',
        indexShowHidden: false,
        thisIndex: '',
        sortType: '01',
        expandAll:false,
        // 是否是最后一个tab
        lastTab:false,
        fileType:'',
      };
    },
    created(){
      // 日志--------------------功能头
      let param = {
        client_type:'pc',//手机或pc
        recordType:'menu',//跳转页面方式:
        recordModule:'IPO案例',//跳转模块
        recordTab:"ipo案例详情页",//跳转tab
        recordTabChild:null,//跳转子集tab
        popTitle:null//弹窗title
      }
      //   this.$store.commit('CREATE_MESSAGE',param)
      // 日志------------------功能尾
      //   请求数据
      this.initTableData()
      this.flagLoading = true;
    },
    mounted() {
    },
    components: {
      moreNotice
    },
    computed: {

    },
    methods: {
      // 初始化数据
      initTableData(sortType) {
        // 动态传id
        const param = {
          id:this.caseId1,
          sortType:sortType
        }
        getRightModuleData(param).then(res => {
          this.flagLoading = false;
          if(res.data.result&&res.data.result.treeList&&res.data.result.treeList.length>0){
            // this.treeList = res.data.result.treeList
            // this.treeTypeCode = res.data.result.treeList[0].treeTypeCode
          }
        })
      },

      // 弹窗多选框
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      // 点击上面展开全部
      expandAlltoC(exAllFlag) {
        for(let i = 0;i<this.treeList.length;i++){
          if(this.treeList[i].proList&&this.treeList[i].proList.length>0){
            this.treeList[i].proList.map(obj => {
              this.$set(obj,'flag',exAllFlag)
              this.showAndHideParent('each' +  obj.proSort, exAllFlag,obj);
            })
          }
        }
      },
      moreNoticeClick(params,obj) {
        // 弹出更多公告
        // debugger;
        this.dialogVisible = true;
        this.moreNoticeList = obj.relaList
        if (params.treeTypeCode == '02') {
          this.moreNoticeDailog = '相关公告'
          this.fileType = '01'
        } else {
          this.moreNoticeDailog = '相关审核意见'
          this.fileType = '02'
        }

      },
      showAndHideParent(obj, exAllFlag,item) {
        if (exAllFlag == '0' ) {
          document.getElementById(obj).setAttribute("style", "display:none;");
          if(document.getElementById('more' +   item.proSort)) {
            document.getElementById('more' +   item.proSort).setAttribute("style", "display:none;");
            document.getElementById('sign' +  item.proSort).className = 'circle'
          }
        } else {
          document .getElementById(obj).setAttribute("style", "display:block;");
          if( document.getElementById('more' +   item.proSort)) {
            document.getElementById('more' +   item.proSort).setAttribute("style", "display:block;");
            document.getElementById('sign' +  item.proSort).className = 'circle'
          }
        }
      },
      // 点击查看公告
      showAndHide(param,obj,item, type) {
        //  点击title展开收起
        if (type == 'title'){
          item.flag = !item.flag;
          if(item.flag){
            document.getElementById('sign' + item.proSort).className = 'fa circle fa-chevron-up'
          } else{
            document.getElementById('sign' + item.proSort).className = 'fa circle fa-chevron-down'
          }
        }
        //  点击灰色进程时间展开收起
        if (type == 'time'){
          item.flag = !item.flag;
          document.getElementById('num' +  item.proSort).setAttribute("style", "display:none;");
          if(item.flag){
            document.getElementById('sign' + item.proSort).className = 'fa circle fa-chevron-up'
          } else{
            document.getElementById('sign' + item.proSort).className = 'fa circle fa-chevron-down'
          }
        }
        // 点击‘查看文件’
        if(type == "file") {
          item.flag = true;
        }
      },

      // 点击展示的第一条公告名
      gonggaoClick(param){
        debugger;
        window.open(param.baseUrl)
      },
      onMouseOver (obj, item, index) {{
          if(item.flag){
            document.getElementById('sign' + item.proSort).className = 'fa circle fa-chevron-up'
          }
          else{
            document.getElementById('sign' + item.proSort).className = 'fa circle fa-chevron-down'
          }
        }
            document.getElementById('num' + item.proSort).setAttribute("style", "display:none;");
      },
      onMouseOut (obj, item) {
          document.getElementById('sign' + item.proSort).className = 'circle'
          document.getElementById('num' + item.proSort).setAttribute("style", "display:inline-block;");
      },

      orderByProcess(sortType){
        this.treeList[0].proList.reverse()
      }
    }
  };
</script>

<style scoped lang="scss">
  .gonggao{
    color:#14bcf5;
    font-size:14px;
    display:none;
    margin-bottom: 24px;
    margin-top: 8px;
  }
  .gonggao:hover {
    cursor: pointer;
    text-decoration: underline;
    text-decoration-color: #14bcf5;
  }
  .moreNoticeCss {
    font-family: "PingFangSC-Regular", "PingFang SC";
    font-weight: 400;
    font-style: normal;
    font-size: 12px;
    color: #999999;
  }

  .right {
    width: 100%;
    float: left;
    padding-top:10px;
  }
  .right:hover {
    // cursor: pointer;
    width: 100%;
    float: left;
    box-shadow:  0 0px 28px -5px #ccc;
  }

  .border-box {
    width: 20px;
    float: left;
  }

  .border-right {
    float: left;
    width: calc(100% - 20px);
  }

  .circle {
    background: #14bcf5;
    display: block;
    width: 20px;
    height: 20px;
    line-height: 20px;
    text-align: center;
    border-radius: 50%;
    color: #fff;
    font-size: 12px;
    position: relative;
    left: -10px;
  }
  .grayCircle {
    background: #d7d7d7;
    display: block;
    width: 20px;
    height: 20px;
    line-height: 20px;
    text-align: center;
    border-radius: 50%;
    color: #fff;
    font-size: 12px;
    position: relative;
    left: -10px;
  }
  .text {
    width: 100%;
    position: relative;
    font-size: 14px;
  }

  .text-lend {
    width: 100%;
    position: relative;
    line-height: 1.4em;
    height: 2.8em;
    overflow: hidden;
    font-size: 14px;
  }

  .text-lend:after {
    content: "...";
    position: absolute;
    bottom: 0;
    right: 0;
    background: #fff;
    padding-left: 0.2em;
  }

  .text-lend>.text-child,
  .text>.text-child {
    position: absolute;
    right: 20px;
    bottom: 0;
    background: rgba(0, 0, 0, 0.35);
    color: #fff;
    border: 1px solid #ccc;
    width: 100%;
    height: 100%;
    text-align: center;
    line-height: 2.8em;
    cursor: pointer;
    display: none;
  }

  .text-lend:hover .text-child,
  .text:hover .text-child {
    display: block;
  }

  .seo {
    font-weight: 400;
    cursor: pointer;
    font-size: 16px;
    margin: 8px 0;
  }
  .children>.small {
    color: #3399ff;
    text-decoration: underline;
    font-size: 14px;
  }

  .right p {
    margin: 8px 0;
  }

  .href:hover {
    text-decoration: underline;
  }

  .tinyHand:hover {
    cursor: pointer;
  }

  .red {
    background: red;
  }

  // 三个点展开样式
  .sandianClass{
    color: #0099cc;
    font-size: 20px;
    float: right;
    width: 100%;
    text-align: right;
    height: 17px;
    border-top: 1px solid rgb(238, 238, 238);
    border-bottom: 1px solid rgb(238, 238, 238);
    line-height: 6px;
    cursor: pointer;
  }
  .spreadText{
    font-weight: 400;
    width: 116px;
    height: 20px;
    font-style: normal;
    font-size: 13px;
    color: #333333;
    text-align: center;
    border: 1px solid #e4e4e4;
    position: absolute;
    left: 244px;
    top: 200px;
    border-radius: 3px;
    padding: 2px;
  }
  .spreadTextLast{
    font-weight: 400;
    width: 116px;
    height: 20px;
    font-style: normal;
    font-size: 13px;
    color: #333333;
    text-align: center;
    border: 1px solid #e4e4e4;
    position: absolute;
    left: 150px;
    top: 190px;
    border-radius: 3px;
    padding: 2px;
  }
  .spread{
    color: #0099cc;
    font-size: 20px;
    text-align:right;
    height:20px;
    padding-right: 15px;
  }
  /* 进程名样式 */
  .jincheng {
    position: relative;
    top: -6px;
    left: -9px;
    height: 24px;
    p {
      font-family: 'PingFang-SC-Regular', 'PingFang SC';
      font-weight: 400;
      font-style: normal;
      font-size: 14px;
      color: #999999;
      top: -31px;
      left: 32px;
      position: relative;
    }
  }
  .htg{
    font-size: 12px;
    // position: relative;
    // left: 38%;
    // top: -20px;
    color: #4ec8e5;
    padding:5px;
    padding-left: 10px;
    padding-right: 7px;
    padding-right:5px;
    line-height:10px;
    display:inline-block;
  }
  .wtg{
    font-size: 12px;
    // position: relative;
    // left: 38%;
    // top: -20px;
    color: #ea5365;
    padding: 5px;
    padding-left: 10px;
    padding-right: 7px;
    line-height: 10px;
    display: inline-block;
  }
  .tg{
    font-size: 12px;
    // position: relative;
    // left: 38%;
    // top: -20px;
    color: #4ec8e5 ;
    padding:5px;
    padding-left: 10px;
    padding-right: 7px;
    line-height:10px;
    display:inline-block;
  }
  .whtg{
    font-size: 12px;
    // position: relative;
    // left: 38%;
    // top: -20px;
    color: #ea5365;
    padding:5px;
    padding-left: 10px;
    padding-right: 7px;
    line-height:10px;
    display:inline-block;
  }
  .zhbj{
    font-size: 12px;
    // position: relative;
    // left: 38%;
    // top: -20px;
    color: #f9b162;
    padding:5px;
    padding-left: 10px;
    padding-right: 7px;
    line-height:10px;
    display:inline-block;
  }
  .qxsh{
    font-size: 12px;
    // position: relative;
    // left: 38%;
    // top: -20px;
    color: #aabfe2;
    padding:5px;
    padding-left: 10px;
    padding-right: 7px;
    line-height:10px;
    display:inline-block;
  }
  .dsh{
    font-size: 12px;
    // position: relative;
    // left: 38%;
    // top: -20px;
    color: #f98962;
    padding:5px;
    padding-left: 10px;
    padding-right: 7px;
    line-height:10px;
    display:inline-block;
  }
  // 先注释掉，因为之前是点击最后一个tab页，进程树出滚动条
  .processTree{
    margin-top:16px;
  }
  .allJincheng{
    margin-top:16px;
  }
</style>
