<template>
    <div v-loading="flagLoading" element-loading-text="给我一点时间" class="zdzc_css" style="margin-top:20px;">
        <div class="allJincheng" v-for="boxDataItem in treeList" v-if="treeList.length>0" :key="boxDataItem.treeTypeCode">
            <!-- 第一个进程 -->
            <div>
                <el-row style="padding-left:12px">
                    <el-col :span="24" style="border-left:1px solid #0099cc; margin-bottom: -10px;margin-top:10px;padding-bottom: 10px;">
                         <!-- 进程名 --> 
                        <div class="jincheng">
                            <img src="../../assets/images/jinchengjian.png" alt="">
                            <p v-if="boxDataItem.treeTypeCode == '02'">上市</p>
                            <p v-if="boxDataItem.treeTypeCode == '01'">审核</p>
                            <p v-if="boxDataItem.treeTypeCode == '00'">辅导工作进程</p>
                            <p v-if="boxDataItem.treeTypeCode == '03'">股份公司设立</p>
                        </div>
                        
                        <div v-show="boxDataItem.treeTypeCode != '03'">
                            <!-- <div v-for="(item,index) in boxDataItem.proList" v-show=" boxDataItem.treeTypeCode != '02'||index == 0 || index == boxDataItem.proList.length-1  || boxDataItem.spreadFlag" > -->
                             <div v-if="!lastTab" v-for="(item,index) in boxDataItem.proList" v-show="boxDataItem.treeTypeCode != '02'||index == 0 || index == boxDataItem.proList.length-1  || boxDataItem.spreadFlag" >
                                <div class="right" >
                                    <div class="border-box">
                                        <span v-if="sortFlag == '0'">
                                            <span :id="'sign' + item.proSort + item.progressType + item.progressIndex" class="circle"  v-if="boxDataItem.proList.length">
                                                <!-- 展示序号 【默认时候不加类】-->
                                                <span :id="'num' + item.proSort + item.progressType + item.progressIndex">
                                                    {{indexShowHidden && thisIndex == index ? '' : boxDataItem.proList.length - index}}
                                                </span>
                                            </span>
                                        </span>
                                        <span v-else>
                                            <span :id="'sign' + item.proSort + item.progressType + item.progressIndex" class="circle" v-if="boxDataItem.proList.length">
                                            <!-- 展示序号 -->
                                                <span :id="'num' + item.proSort + item.progressType + item.progressIndex">
                                                    {{indexShowHidden && thisIndex == index ? '' : index + 1}}
                                                </span>
                                            </span>
                                        </span>
                                    </div>
                                    <div class="border-right">
                                        <div style="font-size: 16px; color: #333333;"
                                            v-text='item.progressName'
                                            @click="showAndHide(boxDataItem,'each' + item.proSort+ item.progressType+ item.progressIndex ,item, 'title')"
                                            @mouseenter="onMouseOver('each' + item.proSort + item.progressType + item.progressIndex, item, index)"
                                            @mouseleave="onMouseOut('each' + item.proSort + item.progressType + item.progressIndex, item, index)"
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
                                                background-repeat:no-repeat;
                                                
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
                                            <span class="zhbj" v-if="item.iecResult=='02'" 
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
                                                ">暂缓表决
                                                
                                            </span>
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
                                            <div :id="'each' + item.proSort + item.progressType + item.progressIndex" style="display:none;">
                                                <div :ref='item.proSort + item.progressType + item.progressIndex' :class="'abc'+item.proSort + item.progressType + item.progressIndex"></div>
                                            </div>
                                            <p v-if="item.relaList.length===0" class="gonggao"  style="color:#0086A7;font-size:14px;display:none;margin-bottom: 24px;margin-top: 8px;"><a></a></p>
                                            <p v-else @click="gonggaoClick(item.relaList[0])" class="gonggao" style="display:none;"
                                            :id="'more'+ item.proSort + item.progressType + item.progressIndex"><a>{{item.relaList[0].relationFileTitle}}</a></p>
                                        </div>
                                        <div v-if="item.flag&&item.relaList.length>1" style="margin-bottom: 24px;margin-top: 8px;">
                                            <span>
                                                <span>
                                                    <a v-if="boxDataItem.treeTypeCode == '02'" @click="moreNoticeClick(boxDataItem,item)" class="moreNoticeCss">查看更多公告</a>
                                                    <a v-else @click="moreNoticeClick(boxDataItem,item)" class="moreNoticeCss">查看更多审核意见</a>
                                                </span>
                                            </span>
                                        </div>
                                        <div  v-if="!item.flag" style="margin-bottom: 24px;margin-top: 8px;"> 
                                            <span>  
                                                <span>
                                                    <!-- 第一个进程展示的是‘查看公告’ -->
                                                    <div style="margin-bottom: 24px;margin-top: 8px;cursor: pointer" v-if="boxDataItem.treeTypeCode == '02'"  v-show="item.relaList.length>0" @click="showAndHide(boxDataItem,'each' + item.proSort+ item.progressType + item.progressIndex,item, null)" class="moreNoticeCss">查看公告</div>
                                                    <!-- 第二个进程展示的是‘查看审核意见’ -->
                                                    <div style="margin-bottom: 24px;margin-top: 8px;cursor: pointer" v-else v-show="item.relaList.length>0" @click="showAndHide(boxDataItem,'each' + item.proSort+ item.progressType + item.progressIndex,item, null)" class="moreNoticeCss">查看审核意见</div>
                                                </span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                    <!-- 三个点展开全部  [在第一个和最后一个节点之间] --> 
                                <div v-if="!lastTab">
                                    <div v-if="boxDataItem.treeTypeCode == '02'" >
                                        <p v-show="index == boxDataItem.proList.length-1" class="spread sandianClass" v-if="boxDataItem.spreadFlag" @click="handlePackUp(boxDataItem)" style="font-size:14px;cursor:pointer;line-height:17px"><span>收起</span></p>
                                        <p v-show=" index == 0" class="spread sandianClass" v-else  ><span  @click="handleSpread(boxDataItem)" @mouseenter="handleMouseenterSpread(boxDataItem)" @mouseleave="handleMouseleaveSpread(boxDataItem)">...</span></p>
                                        <span class="spreadText" v-show="boxDataItem.isShowSpreadText && index == 0">点击展开隐藏节点</span>
                                    </div>
                                </div>             
                                <!-- 当点击最后一个tab页时，上市没有展开收起，其他两个有展开收起 -->
                                <div v-if="lastTab">
                                    <div v-if="boxDataItem.treeTypeCode == '00'||boxDataItem.treeTypeCode == '01'" >
                                        <p v-show="index == boxDataItem.proList.length-1" class="spread sandianClass" v-if="boxDataItem.spreadFlag" @click="handlePackUp(boxDataItem)" style="font-size:14px;cursor:pointer;line-height:17px"><span>收起</span></p>
                                        <p v-show=" index == 0" class="spread sandianClass" v-else  ><span  @click="handleSpread(boxDataItem)" @mouseenter="handleMouseenterSpread(boxDataItem)" @mouseleave="handleMouseleaveSpread(boxDataItem)">...</span></p>
                                        <span class="spreadText" v-show="boxDataItem.isShowSpreadText && index == 0">点击展开隐藏节点</span>
                                    </div>
                                </div>
                            </div>
                            <div v-if="lastTab" v-for="(item,index) in boxDataItem.proList" v-show="boxDataItem.treeTypeCode == '02'||index == 0 || index == boxDataItem.proList.length-1  || boxDataItem.spreadFlag" >
                                <div class="right" >
                                    <div class="border-box">
                                        <span v-if="sortFlag == '0'">
                                            <span :id="'sign' + item.proSort + item.progressType + item.progressIndex" class="circle"  v-if="boxDataItem.proList.length">
                                                <!-- 展示序号 【默认时候不加类】-->
                                                <span :id="'num' + item.proSort + item.progressType + item.progressIndex">
                                                    {{indexShowHidden && thisIndex == index ? '' : boxDataItem.proList.length - index}}
                                                </span>
                                            </span>
                                        </span>
                                        <span v-else>
                                            <span :id="'sign' + item.proSort + item.progressType + item.progressIndex" class="circle" v-if="boxDataItem.proList.length">
                                            <!-- 展示序号 -->
                                                <span :id="'num' + item.proSort + item.progressType + item.progressIndex">
                                                    {{indexShowHidden && thisIndex == index ? '' : index + 1}}
                                                </span>
                                            </span>
                                        </span>
                                    </div>
                                    <div class="border-right">
                                        <div style="font-size: 16px; color: #333333;"
                                            v-text='item.progressName'
                                            @click="showAndHide(boxDataItem,'each' + item.proSort+ item.progressType+ item.progressIndex ,item, 'title')"
                                            @mouseenter="onMouseOver('each' + item.proSort + item.progressType + item.progressIndex, item, index)"
                                            @mouseleave="onMouseOut('each' + item.proSort + item.progressType + item.progressIndex, item, index)"
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
                                                background-repeat:no-repeat;
                                                
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
                                            <span class="zhbj" v-if="item.iecResult=='02'" 
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
                                                ">暂缓表决
                                                
                                            </span>
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
                                            <div :id="'each' + item.proSort + item.progressType + item.progressIndex" style="display:none;">
                                                <div :ref='item.proSort + item.progressType + item.progressIndex' :class="'abc'+item.proSort + item.progressType + item.progressIndex"></div>
                                            </div>
                                            <p v-if="item.relaList.length===0" class="gonggao"  style="color:#0086A7;font-size:14px;display:none;margin-bottom: 24px;margin-top: 8px;"><a></a></p>
                                            <p v-else @click="gonggaoClick(item.relaList[0])" class="gonggao" style="display:none;"
                                            :id="'more'+ item.proSort + item.progressType + item.progressIndex"><a>{{item.relaList[0].relationFileTitle}}</a></p>
                                        </div>
                                        <div v-if="item.flag&&item.relaList.length>1" style="margin-bottom: 24px;margin-top: 8px;">
                                            <span>
                                                <span>
                                                    <a v-if="boxDataItem.treeTypeCode == '02'" @click="moreNoticeClick(boxDataItem,item)" class="moreNoticeCss">查看更多公告</a>
                                                    <a v-else @click="moreNoticeClick(boxDataItem,item)" class="moreNoticeCss">查看更多审核意见</a>
                                                </span>
                                            </span>
                                        </div>
                                        <div  v-if="!item.flag" style="margin-bottom: 24px;margin-top: 8px;"> 
                                            <span>  
                                                <span>
                                                    <!-- 第一个进程展示的是‘查看公告’ -->
                                                    <div style="margin-bottom: 24px;margin-top: 8px;cursor: pointer" v-if="boxDataItem.treeTypeCode == '02'"  v-show="item.relaList.length>0" @click="showAndHide(boxDataItem,'each' + item.proSort+ item.progressType + item.progressIndex,item, null)" class="moreNoticeCss">查看公告</div>
                                                    <!-- 第二个进程展示的是‘查看审核意见’ -->
                                                    <div style="margin-bottom: 24px;margin-top: 8px;cursor: pointer" v-else v-show="item.relaList.length>0" @click="showAndHide(boxDataItem,'each' + item.proSort+ item.progressType + item.progressIndex,item, null)" class="moreNoticeCss">查看审核意见</div>
                                                </span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                    <!-- 三个点展开全部  [在第一个和最后一个节点之间] --> 
                                <div v-if="!lastTab">
                                    <div v-if="boxDataItem.treeTypeCode == '02'" >
                                        <p v-show="index == boxDataItem.proList.length-1" class="spread sandianClass" v-if="boxDataItem.spreadFlag" @click="handlePackUp(boxDataItem)" style="font-size:14px;cursor:pointer;line-height:17px"><span>收起</span></p>
                                        <p v-show=" index == 0" class="spread sandianClass" v-else  ><span  @click="handleSpread(boxDataItem)" @mouseenter="handleMouseenterSpread(boxDataItem)" @mouseleave="handleMouseleaveSpread(boxDataItem)">...</span></p>
                                        <span class="spreadText" v-show="boxDataItem.isShowSpreadText && index == 0">点击展开隐藏节点</span>
                                    </div>
                                </div>             
                                <!-- 当点击最后一个tab页时，上市没有展开收起，其他两个有展开收起 -->
                                <div v-if="lastTab">
                                    <div v-if="boxDataItem.treeTypeCode == '00'||boxDataItem.treeTypeCode == '01'" >
                                        <p v-show="index == boxDataItem.proList.length-1" class="spread sandianClass" v-if="boxDataItem.spreadFlag" @click="handlePackUp(boxDataItem)" style="font-size:14px;cursor:pointer;line-height:17px"><span>收起</span></p>
                                        <p v-show=" index == 0" class="spread sandianClass" v-else  ><span  @click="handleSpread(boxDataItem)" @mouseenter="handleMouseenterSpread(boxDataItem)" @mouseleave="handleMouseleaveSpread(boxDataItem)">...</span></p>
                                        <span class="spreadText" v-show="boxDataItem.isShowSpreadText && index == 0">点击展开隐藏节点</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                         <div v-show="boxDataItem.treeTypeCode == '03'" style=" position: relative;top: -12px;left: 22px;">
                            <p style="font-size: 12px;color: #999;line-height:0px">{{boxDataItem.publishDate}}</p>
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
            <!-- <div v-else>
                <span>暂无数据</span>
            </div> -->
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
             treeTypeCode:'',
             // 鼠标移入展示文字
             isShowSpreadText:false,
            //  treeList0:[],
             treeList:[],
            //  更多公告数据
             moreNoticeList:[],
            //  图片路径
            zhbj:'url('+ require('../../assets/images/zhbj.png')+') no-repeat',
            htg:'url('+ require('../../assets/images/htg.png')+') no-repeat',
            whtg:'url('+ require('../../assets/images/whtg.png')+') no-repeat',
            qxsh:'url('+ require('../../assets/images/qxsh.png')+') no-repeat',
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
            thisIndex: '',
            sortType: '01',
            expandAll:false,
            caseId1:this.$store.state.caseId,
            // 是否是最后一个tab
            lastTab:false
        };
    },
    name: "rightModule",
    props: {
        caseId: String,
        // 从ipo组件传过来的属性值
        expand:Boolean
    },
    created(){
         //   请求数据
         this.initTableData()
         this.flagLoading = false;
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
            console.log(res.data.result.treeList)
            this.treeList = res.data.result.treeList
            console.log(this.treeList)
            this.treeTypeCode = res.data.result.treeList[0].treeTypeCode
        })
        // if(this.treeList.length>0){
        //     this.flagLoading = false;
        // }
      },
      handlerData(data) {
        
      },
        // 弹窗多选框
       handleSelectionChange(val) {
        this.multipleSelection = val;
      },
       // 标移入三个点
      handleMouseenterSpread(param){
        this.isShowSpreadText = true
        this.$set(param,'isShowSpreadText',true)
        console.log(param.isShowSpreadText)
      },
       // 鼠标移出三个点
      handleMouseleaveSpread(param){
        this.isShowSpreadText = false
        this.$set(param,'isShowSpreadText',false)
        console.log(param.isShowSpreadText)
      },
      // 点击三个点展开隐藏方法
        handleSpread(param){
            param.spreadFlag = true;
            this.$set(param,'isShowSpreadText',false)
            // this.isShowSpreadText = false
        },
         // 点击收起隐藏方法
        handlePackUp(param){
            param.spreadFlag = false;
        },
        // 点击上面展开全部
        expandAlltoC(exAllFlag) {
            for(let i = 0;i<this.treeList.length;i++){
                if(this.treeList[i].proList&&this.treeList[i].proList.length>0){
                     this.treeList[i].proList.map(obj => {
                        this.showAndHideParent('each' + obj.proSort + obj.progressType + obj.progressIndex, exAllFlag,obj);
                    })
                }
            }
        },
        sortTime(sortType) {
            if(sortType == '02'){
                this.flag = "0";
                sortType = '01';
                this.sortFlag = '1'
            }else {
                this.flag = "1";
                sortType = '02';
                this.sortFlag = '0'
            }
             for(let i = 0;i<this.treeList.length;i++){
                 if(this.treeList[i].proList&&this.treeList[i].proList.length>0){
                     this.treeList[i].proList.reverse();
                    //  this.treeList[i].proList.map((item)=>{
                    //     this.$set(item,'spreadFlag',false)
                    //  })
                 }
            }
            // 
        },
        moreNoticeClick(params,obj) {
            // 弹出更多公告 
            this.dialogVisible = true;
            this.moreNoticeList = obj.relaList
            if (params.treeTypeCode == '02') {
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
                if(  document.getElementById('more' +  item.proSort + item.progressType + item.progressIndex)) {
                     document.getElementById('more' +  item.proSort + item.progressType + item.progressIndex).setAttribute("style", "display:none;");
                     document.getElementById('sign' + item.proSort + item.progressType + item.progressIndex).className = 'circle'
                     document.getElementById('num' + item.proSort + item.progressType + item.progressIndex).setAttribute("style", "display:inline-block;");
                }
            } else {
                if( document.getElementById('more' +  item.proSort + item.progressType + item.progressIndex)) {
                    document.getElementById('more' +  item.proSort + item.progressType + item.progressIndex).setAttribute("style", "display:block;");
                    document.getElementById('sign' + item.proSort + item.progressType + item.progressIndex).className = 'circle'
                    document.getElementById('num' + item.proSort + item.progressType + item.progressIndex).setAttribute("style", "display:inline-block;");
                }
                document .getElementById(obj).setAttribute("style", "display:block;");
               
            }
        },
        // 点击查看公告
         showAndHide(param,obj,item, type) {
             if (type == 'title'){
                 item.flag = true;
               if (document.getElementById('more' +  item.proSort + item.progressType+ item.progressIndex).getAttribute("style") ===
                "display:none;"  ||
                document.getElementById('more' +  item.proSort + item.progressType+ item.progressIndex).getAttribute("style") ===
                "display: none;" ){
                document.getElementById('sign' + item.proSort + item.progressType+ item.progressIndex).className = 'fa circle fa-chevron-up'
                document.getElementById('more' +  item.proSort + item.progressType+ item.progressIndex).setAttribute("style", "display:block;");
                } else {                
                item.flag = false;
                document.getElementById('sign' + item.proSort + item.progressType+ item.progressIndex).className = 'fa circle fa-chevron-down'
                document.getElementById('more' +  item.proSort + item.progressType+ item.progressIndex).setAttribute("style", "display:none;");
                }
             }else {                
                 if (document.getElementById('more' +  item.proSort + item.progressType+ item.progressIndex).getAttribute("style") ===
                "display:none;"  ||
                document.getElementById('more' +  item.proSort + item.progressType+ item.progressIndex).getAttribute("style") ===
                "display: none;" ){
                    item.flag = true;
                    document.getElementById('more' +  item.proSort + item.progressType+ item.progressIndex).setAttribute("style", "display:block;");
                }
              }
        },
     
        // 点击展示的第一条公告名
        gonggaoClick(param){
            window.open(param.baseUrl)
        },
        onMouseOver (obj, item, index) {
            document.getElementById('sign' + item.proSort + item.progressType+ item.progressIndex).className = 'fa circle fa-chevron-down'
            document.getElementById('num' + item.proSort + item.progressType+ item.progressIndex).setAttribute("style", "display:none;");
        },
        onMouseOut (obj, item) {
              document.getElementById('sign' + item.proSort + item.progressType+ item.progressIndex).className = 'circle'
              document.getElementById('num' + item.proSort + item.progressType+ item.progressIndex).setAttribute("style", "display:inline-block;");
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
        //点击最后一个tab页，进程树变化
        treeListMethods(lastTabFlag){
            this.lastTab = lastTabFlag
        }
    },
    watch:{
        // 监听展开全部，收起全部的状态
        expand(flag) {
            this.expandAll = this.expand
             for(let i = 0;i<this.treeList.length;i++){
                 if(this.treeList[i].proList&&this.treeList[i].proList.length>0){
                     this.treeList[i].proList.map((item)=>{
                        this.$set(item,'flag',flag)
                     })
                 }
            }
        }
    }
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
.gonggao{
    color:#0086A7;
    font-size:14px;
    display:none;
    margin-bottom: 24px;
    margin-top: 8px;
}
.gonggao:hover {
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
    line-height: 6px;
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
    top: 230px;
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
    top: -14px;
    left: -9px;
    height: 31px;
    margin-top: 8px;
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
// 标签
// .zhbj{
//     font-size: 14px;
//     position: relative;
//     left: 40%;
//     top: -20px;
//     color: #FF9900;
//     padding:5px;
//     padding-right:5px;
//     line-height:10px;
//     display:inline-block;
//     background-size:100% 100%;
//     border:1px solid #FF9900;
//     border-radius:2px;
//     border-left:none;
//     background-repeat: no-repeat !important;
// }
// .zhbj:before {
//     content: '';
//     width: 0;
//     height: 0;
//     border: 11px solid transparent;
//     border-right-color: #FF9900;
//     position: absolute;
//     left: -2%;
//     top: -6%;
//     margin-left: -20px;
// }
 /*左箭头*/
        // .left{
        //      width: 20px;
        //     height: 20px;
        //     position: absolute;
        //     left: -19px;
        //     top: -2px;
        //     z-index: 3;
        // }
        // .left-arrow1,.left-arrow2{
        //     width:0;
        //     height:0;
        //     display:block;
        //     position:absolute;
        //     left:0;
        //     top:0;
        //     z-index:5;/*兼容ie8-*/
        //     border-top:12px transparent dashed;
        //     border-left:10px transparent dashed;
        //     border-bottom:12px transparent dashed;
        //     border-right:10px white solid;
        //     overflow:hidden;
        // }
        // .left-arrow1{
        //     border-right:10px #FF9900 solid;
        // }
        // .left-arrow2{
        //     left:1px;/*重要*/
        //     border-right:10px white solid;
        // }
</style>
