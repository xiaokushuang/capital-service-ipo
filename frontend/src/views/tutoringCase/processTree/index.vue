<template>
  <div v-loading="flagLoading" element-loading-text="给我一点时间" class="allJincheng">
    <div v-for="boxDataItem in treeList.treeList" v-if="treeList.treeList.length>0" :key="boxDataItem.treeTypeCode">
      <!-- 第一个进程 -->
      <div>
        <el-row style="padding-left:12px">
          <el-col :span="24" style="border-left:1px solid #E9E9E9; margin-bottom: -10px;margin-top:10px;padding-bottom: 10px;">
            <!-- 进程名 -->
            <div class="jincheng21" v-if="treeList.establishTime&&sortType==='01'">
              <img style="width:21px;height:8px" src="../../../assets/images/jinchengjian.png" alt="">
              <div>
                <p style="font-size: 14px;color: #999;line-height:20px">股份公司设立</p>
                <p style="font-size: 12px;color: #999;line-height:0px">{{treeList.establishTime}}</p>
              </div>
            </div>
            <div class="jincheng11" v-if="sortType==='02'">
              <img src="../../../assets/images/jinchengjian.png" alt="">
              <p>辅导工作进程</p>
            </div>
            <div>
              <div v-for="(item,index) in boxDataItem.proList" class="clear">
                <div class="right" >
                  <div class="border-box">
                      <span :id="'sign' +  index" class="circle" v-if="boxDataItem.proList.length">
                           <!-- 展示序号 -->
                              <span :id="'num' +  index">
                                  <!--{{index+1}}-->
                                {{item.proSort}}
                              </span>
                      </span>
                  </div>
                  <div class="border-right">
                    <div style="height: 19px;">
                      <div style="font-size: 16px; color: #333333;display:inline-block;margin-right: 10px;"
                           v-text='item.progressName'
                           @click="showAndHide(boxDataItem,'each' + index ,item, 'title',index)"
                           @mouseenter="onMouseOver('each' +  index, item, index)"
                           @mouseleave="onMouseOut('each' +  index, item, index)"
                           class="tinyHand">
                      </div>
                    </div>
                    <div style="font-size: 12px;margin-top: 8px;color: #999;margin-bottom: 12px;">
                      <span  @mouseenter="onMouseOver('each' +  index, item, index)" style="cursor: pointer;"
                             @mouseleave="onMouseOut('each' +  index, item, index)" @click="showAndHide(boxDataItem,'each' + index ,item, 'time',index)" v-if="item.processTime" v-text='item.processTime'></span>
                      <span  @mouseenter="onMouseOver('each' +  index, item, index)" style="cursor: pointer;"
                             @mouseleave="onMouseOut('each' +  index, item, index)" @click="showAndHide(boxDataItem,'each' + index ,item, 'time',index)" v-if="item.processTime">&nbsp;&nbsp;</span>
                      <span  @mouseenter="onMouseOver('each' +  index, item, index)"
                             @mouseleave="onMouseOut('each' +  index, item, index)" @click="showAndHide(boxDataItem,'each' + index ,item, 'time',index)" v-if="item.lastDay" style="display:inline-block;width: 65%;cursor: pointer;">距离上个进程{{item.lastDay}}</span>
                      <!-- 前面图标 -->
                      <div :id="'each' +  index" style="display:none;">
                        <div :ref=' index' :class="'abc'+ index"></div>
                      </div>
                      <p v-if="item.relaList.length===0" class="gonggao"  style="color:#0086A7;font-size:14px;display:none;margin-bottom: 24px;margin-top: 8px;"><a></a></p>
                      <p v-else v-show="item.flag" @click="gonggaoClick(item.relaList[0])" class="gonggao" style="display: block" :id="'more'+  index">
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
                  <!-- 第二个进程展示的是‘查看文件’ -->
                    <div v-if="item.relaList.length>0&&item.relaList[0].relationFileTitle!==''&&!item.flag" style="margin-bottom: 14px;margin-top: 8px;cursor: pointer" @click="showAndHide(boxDataItem,'each' + index,item, 'file',index)" class="moreNoticeCss">
                      查看文件 >
                    </div>
                  </div>
                </div>
              </div>
              <!-- 进程名 -->
              <div class="jincheng12" v-if="sortType==='01'">
                <img src="../../../assets/images/jinchengjian.png" alt="">
                <p>辅导工作进程</p>
              </div>
              <div class="jincheng22" v-if="treeList.establishTime&&sortType==='02'">
                <img style="width:21px;height:8px" src="../../../assets/images/jinchengjian.png" alt="">
                <div>
                  <p style="font-size: 14px;color: #999;line-height:20px">股份公司设立</p>
                  <p style="font-size: 12px;color: #999;line-height:0px">{{treeList.establishTime}}</p>
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
        sortType: '02',
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
      // this.initTableData()
      this.flagLoading = true;
    },
    mounted() {
      if(this.treeList.treeList&&this.treeList.treeList.length>0){
        this.flagLoading = false;
      }
    },
    components: {
      moreNotice
    },
    computed: {

    },
    methods: {
      // 弹窗多选框
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      // 点击上面展开全部
      expandAlltoC(exAllFlag) {
        for(let i = 0;i<this.treeList.treeList.length;i++){
          if(this.treeList.treeList[i].proList&&this.treeList.treeList[i].proList.length>0){
            this.treeList.treeList[i].proList.map(obj => {
              this.$set(obj,'flag',exAllFlag)
              this.showAndHideParent('each' +  i, exAllFlag,obj,i);
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
      showAndHideParent(obj, exAllFlag,item,index) {
        if (exAllFlag == '0' ) {
          document.getElementById(obj).setAttribute("style", "display:none;");
          if(document.getElementById('more' +   index)) {
            document.getElementById('more' +   index).setAttribute("style", "display:none;");
            document.getElementById('sign' +  index).className = 'circle'
          }
        } else {
          document .getElementById(obj).setAttribute("style", "display:block;");
          if( document.getElementById('more' +   index)) {
            document.getElementById('more' +   index).setAttribute("style", "display:block;");
            document.getElementById('sign' +  index).className = 'circle'
          }
        }
      },
      // 点击查看公告
      showAndHide(param,obj,item, type,index) {
        //  点击title展开收起
        if (type == 'title'){
          item.flag = !item.flag;
          if(item.flag){
            document.getElementById('sign' + index).className = 'fa circle fa-chevron-up'
          } else{
            document.getElementById('sign' + index).className = 'fa circle fa-chevron-down'
          }
        }
        //  点击灰色进程时间展开收起
        if (type == 'time'){
          item.flag = !item.flag;
          document.getElementById('num' +  index).setAttribute("style", "display:none;");
          if(item.flag){
            document.getElementById('sign' + index).className = 'fa circle fa-chevron-up'
          } else{
            document.getElementById('sign' + index).className = 'fa circle fa-chevron-down'
          }
        }
        // 点击‘查看文件’
        if(type == "file") {
          item.flag = true;
        }
      },

      // 点击展示的第一条公告名
      gonggaoClick(param){
        // debugger;
        window.open(param.baseUrl)
      },
      onMouseOver (obj, item, index) {{
          if(item.flag){
            document.getElementById('sign' + index).className = 'fa circle fa-chevron-up'
          }
          else{
            document.getElementById('sign' + index).className = 'fa circle fa-chevron-down'
          }
        }
            document.getElementById('num' + index).setAttribute("style", "display:none;");
      },
      onMouseOut (obj, item,index) {
          document.getElementById('sign' + index).className = 'circle'
          document.getElementById('num' + index).setAttribute("style", "display:inline-block;");
      },

      orderByProcess(sortType){
        this.treeList.treeList[0].proList.reverse()
        console.log('排序',sortType)
        this.sortType = sortType
      }
    }
  };
</script>

<style scoped lang="scss">
  .l {
    float: left;
  }

  .r {
    float: right;
  }

  .clear:after {
    display: block;
    content: "";
    clear: both;
  }
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
  .jincheng12 {
    position: relative;
    top: 20px;
    left: -9px;
    height: 24px;
    p {
      /*font-family: 'Microsoft YaHe','Helvetica Neue','Helvetica','PingFang SC','Hiragino Sans GB','Arial','sans-serif';*/
      font-weight: 400;
      font-style: normal;
      font-size: 14px;
      color: #999999;
      top: -31px;
      left: 32px;
      position: relative;
    }
  }
  .jincheng11 {
    position: relative;
    top: -6px;
    left: -9px;
    height: 24px;
    p {
      /*font-family: 'Microsoft YaHe','Helvetica Neue','Helvetica','PingFang SC','Hiragino Sans GB','Arial','sans-serif';*/
      font-weight: 400;
      font-style: normal;
      font-size: 14px;
      color: #999999;
      top: -31px;
      left: 32px;
      position: relative;
    }
  }
  .jincheng21 {
    position: relative;
    top: -14px;
    left: -9px;
    height: 41px;
    p {
      /*font-family: 'PingFang-SC-Regular', 'PingFang SC';*/
      /*font-family: 'Microsoft YaHe','Helvetica Neue','Helvetica','PingFang SC','Hiragino Sans GB','Arial','sans-serif';*/
      font-weight: 400;
      font-style: normal;
      font-size: 14px;
      color: #999999;
      top: -31px;
      left: 32px;
      position: relative;
    }
  }
  .jincheng22 {
    position: relative;
    top: 12px;
    left: -9px;
    height: 15px;
    p {
      /*font-family: 'Microsoft YaHe','Helvetica Neue','Helvetica','PingFang SC','Hiragino Sans GB','Arial','sans-serif';*/
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
