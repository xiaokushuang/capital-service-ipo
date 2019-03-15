<template>
    <div v-loading="flagLoading" element-loading-text="给我一点时间" class="zdzc_css" style="margin-top:20px;">
        <div class="allJincheng" v-for="boxDataItem in treeList" v-if="treeList.length>0" :key="boxDataItem.treeTypeCode">
            <!-- 第一个进程 -->
            <div v-if="boxDataItem.proList.length>0">
                <el-row style="padding-left:12px">
                    <el-col :span="24" style="border-left:1px solid #0099cc; margin-bottom: 12px;">
                         <!-- 进程名 -->
                         
                        <div class="jincheng">
                            <img src="../../assets/images/jinchengjian.png" alt="">
                            <p v-if="boxDataItem.treeTypeCode == '01'">上市</p>
                            <p v-if="boxDataItem.treeTypeCode == '02'">审核</p>
                        </div>
                         <div v-for="(item,index) in boxDataItem.proList" v-show=" !boxDataItem.sandian || index == 0 || index == boxDataItem.proList.length-1  || isSpread"  :key="item.sort" @click="handleSpreadBody(boxDataItem)">
                             <div class="right" >
                                <div class="border-box">
                                    <span v-if="sortFlag == '0'">
                                        <span :id="'sign' + item.sort + item.progressType" class="circle" 
                                        :class="{'fa-chevron-down' : item.showIcon === true,'fa' : item.showIcon === true}"
                                        v-if="boxDataItem.proList.length">
                                         <!-- 展示序号 【默认时候不加类】-->
                                            <span v-if="!item.showIcon">
                                                {{indexShowHidden && thisIndex == index ? '' : boxDataItem.proList.length - index}}
                                            </span>
                                        </span>
                                    </span>
                                    <span v-else>
                                        <span :id="'sign' + item.sort + item.progressType" class="circle" 
                                         :class="{'fa-chevron-down' : item.showIcon === true,'fa' : item.showIcon === true}"
                                        v-if="boxDataItem.proList.length">
                                        <!-- 展示序号 -->
                                            <span v-if="!item.showIcon">
                                                {{indexShowHidden && thisIndex == index ? '' : index + 1}}
                                            </span>
                                        </span>
                                    </span>
                                </div>
                                <div class="border-right">
                                    <div style="font-size: 16px; color: #333333;"
                                        v-text='item.progressName'
                                        @click.stop="showAndHide(boxDataItem,'each' + item.sort+ item.progressType ,item, 'title')"
                                        @mouseenter="onMouseOver('each' + item.sort + item.progressType, item, index)"
                                        @mouseleave="onMouseOut('each' + item.sort + item.progressType, item, index)"
                                        class="tinyHand">
                                    </div>
                                    <!-- 审核结果 -->
                                    <div v-show="item.progressType=='07'">
                                        <span v-if="item.iecResult=='00'"
                                        :style={background:htg} 
                                        style="font-size: 14px;
                                            position: relative;
                                            left: 40%;
                                            top: -20px;
                                            color: #14BCF5;
                                            padding:5px;
                                            padding-right:5px;
                                            line-height:10px;
                                            display:inline-block;
                                            
                                            ">获通过</span>
                                        <span v-if="item.iecResult=='01'"
                                        :style={background:whtg} 
                                        style="font-size: 14px;
                                            position: relative;
                                            left: 40%;
                                            top: -20px;
                                            color: #FE5461;
                                            padding:5px;
                                            padding-right:5px;
                                            line-height:10px;
                                            display:inline-block;
                                        
                                            
                                            ">未获通过</span>
                                        <span v-if="item.iecResult=='02'"
                                        :style={background:zhbj} 
                                        style="font-size: 14px;
                                            position: relative;
                                            left: 40%;
                                            top: -20px;
                                            color: #FF9900;
                                            padding:5px;
                                            padding-right:5px;
                                            line-height:10px;
                                            display:inline-block;
                                        background-size:100% 100%;
                                            
                                        ">暂缓表决</span>
                                        <span v-if="item.iecResult=='03'"
                                        :style={background:qxsh} 
                                        style="font-size: 14px;
                                            position: relative;
                                            left: 40%;
                                            top: -20px;
                                            color: #94A3B4;
                                            padding:5px;
                                            padding-right:5px;
                                            line-height:10px;
                                            display:inline-block;
                                            background-size:100% 100%;
                                            ">取消审核</span>
                                    </div>
                                    <div style="font-size: 12px;margin-top: 8px;color: #999;margin-bottom: 12px;">
                                        <span v-text='item.processTime'></span>
                                        &nbsp;&nbsp;
                                        <span v-if="item.lastDay != undefined">距离上个进程{{item.lastDay}}</span>
                                        <!-- 前面图标 -->
                                        <div :id="'each' + item.sort + item.progressType" style="display:none;">
                                            <div :ref='item.sort + item.progressType' :class="'abc'+item.sort + item.progressType"></div>
                                        </div>
                                        <p @click.stop="gonggaoClick(boxDataItem)" v-if="item.relaList.length===0" v-show="item.flag==1" class="gonggao"  style="color:#0086A7;font-size:14px;display:none"><a href="#"></a></p>
                                        <p @click.stop="gonggaoClick(boxDataItem)"  v-else  v-show="item.flag==1" class="gonggao"  style="color:#0086A7;font-size:14px"><a href="#">{{item.relaList[0].relationFileTitle}}</a></p>
                                    </div>
                                    <div v-if="item.flag==1&&item.relaList.length>1" style="margin-bottom: 24px;margin-top: 8px;">
                                        <span>
                                            <span>
                                                <a v-if="boxDataItem.sandian" href="#" @click.stop="moreNoticeClick(boxDataItem,item)" class="moreNoticeCss">查看更多公告</a>
                                                <a v-else href="#" @click.stop="moreNoticeClick(boxDataItem,item)" class="moreNoticeCss">查看更多审核意见</a>
                                            </span>
                                        </span>
                                    </div>
                                    <div  v-if="item.flag==0" style="margin-bottom: 24px;margin-top: 8px;"> 
                                        <span>  
                                            <span>
                                                <!-- 第一个进程展示的是‘查看公告’ -->
                                                <div v-if="boxDataItem.sandian" v-show="item.relaList.length>0" @click.stop="showAndHide(boxDataItem,'each' + item.sort+ item.progressType,item, null)" href="#" class="moreNoticeCss" style="cursor: pointer;">查看公告</div>
                                                <!-- 第二个进程展示的是‘查看审核意见’ -->
                                                <div v-else v-show="item.relaList.length>0" @click.stop="showAndHide(boxDataItem,'each' + item.sort+ item.progressType,item, null)" href="#" class="moreNoticeCss" style="cursor: pointer;">查看审核意见</div>
                                            </span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            
                                <!-- 三个点展开全部  [在第一个和最后一个节点之间] -->
                            <div v-if="boxDataItem.sandian" >
                                <p v-show="index == boxDataItem.proList.length-1" class="spread" v-if="boxDataItem.spreadFlag" @click="handlePackUp(boxDataItem)" style="font-size:14px;cursor:pointer"><span>收起</span></p>
                                <p v-show=" index == 0" class="spread sandianClass" v-else  ><span  @click="handleSpread(boxDataItem)" @mouseenter="handleMouseenterSpread()" @mouseleave="handleMouseleaveSpread()">...</span></p>
                                <span class="spreadText" v-show="isShowSpreadText && index == 0">点击展开隐藏节点</span>
                            </div>
                            
                        </div>
                         
                        <!-- 点击查看更多公告内容弹窗 -->
                        <div class="popWindow">
                             <el-dialog :title= moreNoticeDailog :visible.sync="dialogVisible" :close-on-click-modal="false" width="73.5%" append-to-body id="moreNoticeDailog">
                                <div style="background: #cccccc">
                                    <moreNotice :moreNoticeList = "moreNoticeList"></moreNotice>
                                </div>
                            </el-dialog>
                        </div>
                    </el-col>
                </el-row>
            </div>
            <div v-else>
                <span>暂无数据</span>
            </div>
        </div>
      
    </div>
</template>

<script>
import Vue from "vue";
import $ from "jquery";
import moreNotice from "./module/moreNotice"
import {getRightModuleData} from '@/api/rightModule'
export default {
    data() {
        return {
            // 新加变量头
             boxData:[],
             boxData1:[],
             treeTypeCode:'',
             // 鼠标移入展示文字
             isShowSpreadText:false,
             treeList0:[],
             treeList:[],
            //  是否全部展开
             isSpread:false,
            //  更多公告数据
             moreNoticeList:[],
            //  图片路径
            zhbj:'url('+ require('../../assets/images/zhbj.png')+')',
            htg:'url('+ require('../../assets/images/htg.png')+')',
            whtg:'url('+ require('../../assets/images/whtg.png')+')',
            qxsh:'url('+ require('../../assets/images/qxsh.png')+')',
            // 新加变量尾
            showLength: 10000,
            moreNoticeDailog: '',
            dialogVisible: false,
            lableData: [],
      
            flagLoading: false,
            //组件
            id: "",
            progressType: "",
            caseMoreNoticeId: "",
            sort: "",
            sortFlag: '0',
            indexShowHidden: false,
            thisIndex: ''
        };
    },
    name: "rightModule",
    props: {
        caseId: String
    },
    created(){
         //   请求数据
         this.initTableData()
         this.flagLoading = false;
     },
    mounted() {
        // console.log(this.isSpread)
    },
    components: {
        moreNotice
    },
    computed: {

    },
    methods: {
     // 初始化数据
      initTableData() {
        getRightModuleData().then(res => {
            this.treeList = res.data.result.treeList
            this.treeTypeCode = res.data.result.treeList[0].treeTypeCode
            this.boxData = res.data.result.treeList[0].proList
            this.boxData1 = res.data.result.treeList[1].proList
            this.treeList0 = res.data.result.treeList[0]
        })
        // if(this.treeList.length>0){
        //     this.flagLoading = false;
        // }
      },
        // 弹窗多选框
        handleSelectionChange(val) {
        this.multipleSelection = val;
      },
       // 标移入三个点
      handleMouseenterSpread(){
        this.isShowSpreadText = true
      },
       // 鼠标移出三个点
      handleMouseleaveSpread(){
        this.isShowSpreadText = false
      },
      // 点击三个点展开隐藏方法
        handleSpread(param){
            this.isSpread = true;
            param.spreadFlag = true;
            this.isShowSpreadText = false
        },
         // 点击收起隐藏方法
        handlePackUp(param){
            this.isSpread = false;
            param.spreadFlag = false;
        },
        // 点击li循环体收起展开内容
        handleSpreadBody(param){
            // if(param.sandian){
            //     param.spreadFlag = !param.spreadFlag;
            //     this.isSpread = !this.isSpread;
            // }
        },
        // 点击上面展开全部
        expandAlltoC(exAllFlag) {
            this.boxData.map(obj => {
                this.showAndHideParent('each' + obj.sort + obj.progressType, exAllFlag,obj);
            })
             this.boxData1.map(obj => {
                this.showAndHideParent('each' + obj.sort + obj.progressType, exAllFlag,obj);
            })
        },
        sortTime(sortName, sort) {
            if (sort == "asc") {
                this.sortFlag = '1'
            } else {
                this.sortFlag = '0'
            }
            this.boxData.reverse();
            this.boxData1.reverse();
        },
        moreNoticeClick(params,obj) {
            // 点击查看更多公告不收起展开的内容
             this.isSpread = true;
             params.spreadFlag = true;
            // 弹出更多公告 
            this.dialogVisible = true;
            this.moreNoticeList = obj.relaList
            if (params.sandian) {
                this.moreNoticeDailog = '相关公告'
            } else {
                this.moreNoticeDailog = '相关审核意见'
            }
       
        },
        showAndHideParent(obj, exAllFlag,item) {
            if (
                exAllFlag == '0'
            ) {
                document.getElementById(obj).setAttribute("style", "display:none;");
                item.flag = 0;

            } else {
                document .getElementById(obj).setAttribute("style", "display:back;");
                item.flag = 1;
            }
        },
        // 点击查看公告
         showAndHide(param,obj,item, type) {

             if (type == 'title'){
                item.flag = !item.flag;
             }else{
                item.flag = 1;
             }
            if (
                document.getElementById(obj).getAttribute("style") ===
                "display:none;" ||
                document.getElementById(obj).getAttribute("style") ===
                "display: none;"
            ) {
                document.getElementById(obj).setAttribute("style", "display:back;");
                if (type == 'title') {
                  document.querySelector('#sign' + item.sort + item.progressType).className = 'circle fa fa-chevron-up'
                }
                item.flag = 1;
            } else {
                document.getElementById(obj).setAttribute("style", "display:none;");
                if (type == 'title') {
                  document.querySelector('#sign' + item.sort+ item.progressType).className = 'circle fa fa-chevron-down'
                }
                item.flag = 0;
            }
        },
     
        // 点击展示的第一条公告名
        gonggaoClick(param){
         // 点击查看第一条公告不收起展开的内容
             this.isSpread = true;
             param.spreadFlag = true;
        },
        onMouseOver (obj, item, index) {
            this.$set(item,'showIcon',true)
        },
        onMouseOut (obj, item) {
              this.$set(item,'showIcon',false)
        },
        sortBy(name, order) {
            return function (obj1, obj2) {
                var a, b;
                var reg1 = /^(a|A)(s|S)(c|C)$/;
                var reg2 = /^(d|D)(e|E)(s|S)(c|C)$/;
                if (typeof obj1 === "object" && typeof obj2 === "object" && obj1 && obj2) {
                    a = obj1[name];
                    b = obj2[name];
                    if (!order || reg1.test(order)) {
                        if (a < b) {
                            return -1;
                        } else if (a > b) {
                            return 1;
                        } else {
                            return 0;
                        }
                    } else if (reg2.test(order)) {
                        if (a < b) {
                            return 1;
                        } else if (a > b) {
                            return -1;
                        } else {
                            return 0;
                        }
                    } else {
                        throw ("sortOrder.error")
                    }
                } else {
                    throw ("sortObject.error")
                }
            }
        },
    },
};
</script>

<style scoped lang="scss">
.allmore {
    cursor: pointer;
    text-align: center;
    color: #0066FF;
    font-size: 12px;
}

.allmore:hover {
    cursor: pointer;
    text-decoration: underline;
    text-decoration-color: #0066FF;
}

.moreNoticeCss {
    width:30%;
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
    cursor: pointer;
    width: 100%;
    float: left; 
    box-shadow:  0 0px 28px -5px #ccc;
}

.border-box {
    /* height:200px; */

    width: 20px;
    float: left;
}

.border-right {
    /* height:200px; */
    float: left;
    width: calc(100% - 20px);
}

.circle {
    background: #0099cc;
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
    /* line-height:1.4em;
        height:2.8em; */
    /* overflow:hidden;  */
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

.off {
    /*font-size: 12px;*/
    /*color: #b9b7b7;*/
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
    line-height: 3px;
    /* margin-right: 50px; */
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
    top: 277px;
    border-radius: 3px;
    padding: 2px;
}
.spread{
    color: #0099cc;
    font-size: 20px;
    text-align:right;
}
/* 进程名样式 */
.jincheng {
    position: relative;
    top: -6px;
    left: -11px;
    margin-top: 13px;
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
</style>
