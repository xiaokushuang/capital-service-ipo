<template>
<div v-loading="flagLoading" element-loading-text="给我一点时间" class="zdzc_css" style="margin-top:20px;">
  <div class="allJincheng" v-for="boxDataItem in boxDataAll" v-if="boxDataAll.length>0" >
        <el-row style="margin-top:10px;padding-left:12px">
            <el-col :span="24" style="border-left:1px solid rgba(242, 242, 242, 1);">
               <!-- 进程名 -->
                <div class="jincheng">
                  <img src="../../assets/images/jinchengjian.png" alt="">
                  <p>{{boxDataItem.jcName}}</p>
                </div>
                <div class="right" v-for="(item,index) in boxDataItem.boxData" :key="item.sort" v-show="index == 0 || boxDataItem.isSpread" @click="handleSpreadBody($event,boxDataItem)">
                     <div class="border-box">
                        <span v-if="sortFlag == '0'">
                            <span :id="'sign' + item.sort" class="circle" v-if="boxDataItem.boxData.length">
                              {{item.sort}}
                              <!-- {{indexShowHidden && thisIndex == index ? '' : boxDataItem.boxData.length - index}} -->
                            </span>
                        </span>
                        <span v-else>
                            <span :id="'sign' + item.sort" class="circle" v-if="boxDataItem.boxData.length">
                              {{item.sort}}
                              <!-- {{indexShowHidden && thisIndex == index ? '' : index + 1}} -->
                            </span>
                        </span>
                    </div>
                    <div class="border-right">
                        <div style="font-size: 16px; color: #333333;"
                             v-text='item.processLabel'
                             @click="showAndHide('each' + item.sort,item, 'title')"
                             @mouseenter="onMouseOver('each' + item.sort, item, index)"
                             @mouseleave="onMouseOut('each' + item.sort, item, index)"
                             class="tinyHand">
                        </div>
                        <div style="font-size: 12px;margin-top: 8px;color: #999;margin-bottom: 12px;">
                            <span v-text='item.publishTime'></span>
                            &nbsp;&nbsp;
                            <span v-if="item.chajitian != undefined">距离上个进程{{item.chajitian}}天</span>
                            <p class="gonggao" v-show="item.isShowGonggao" style="color:#0086A7;font-size:14px"><a href="#">{{item.gonggao}}</a></p>
                        </div>
                        <div :id="'each' + item.sort" style="display:none;">
                            <div :ref='item.sort' :class="'abc'+item.sort"></div>
                        </div>
                       
                        <div style="margin-bottom: 24px;margin-top: 8px;">
                          <!-- 点击查看公告 -->
                            <span >
                               <!-- <div href="#" @click="showAndHide('each' + item.sort,item, null)" class="moreNoticeCss" style="cursor: pointer;">查看公告</div> -->
                                <div @click.stop="letterClick(item)" class="moreNoticeCss" style="cursor: pointer;" v-if="item.showGonggaoText">查看公告</div>
                                <!-- <div @click.stop="moreLetterClick(item)" class="moreNoticeCss" style="cursor: pointer;" v-if="item.showMoreGonggaoText">查看更多公告</div> -->
                                <div @click="dialogTableVisible = true" class="moreNoticeCss" style="cursor: pointer;" v-if="item.showMoreGonggaoText">查看更多公告</div>
                            </span>
                         
                             <!-- 点击查看更多公告内容弹窗 -->
                            <div class="popWindow">
                                <el-dialog title="招股公告_相关公告" :visible.sync="dialogTableVisible">
                                    <el-table
                                        ref="multipleTable"
                                        :data="tableData3"
                                        tooltip-effect="dark"
                                        style="width: 100%"
                                        @selection-change="handleSelectionChange">
                                        <el-table-column
                                        type="selection" 
                                        width="55">
                                        </el-table-column>
                                        <el-table-column
                                        label="公告日期"                                  
                                        width="120">
                                        <template slot-scope="scope">{{ scope.row.date }}</template>
                                        </el-table-column>
                                        <el-table-column
                                        prop="name"
                                        label="公告名称"
                                        width="120">
                                        </el-table-column>
                                        <el-table-column
                                        
                                            label="操作"
                                        >
                                            <template slot-scope="scope">
                                                <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                                                <el-button type="text" size="small">编辑</el-button>
                                            </template>
                                        </el-table-column>
                                    </el-table>
                                    <button class="DownloadAnnouncement">下载所选公告</button>
                                </el-dialog>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 三个点展开全部 -->
                <div>
                   <p class="spread" @click="handleSpread($event,boxDataItem)" @mouseenter="handleMouseenterSpread(boxDataItem)" @mouseleave="handleMouseleaveSpread(boxDataItem)" >...</p>
                   <span class="spreadText" v-show="boxDataItem.isShowSpreadText">点击展开隐藏节点</span>
                </div>
               
            </el-col>
        </el-row>
    </div>
    <div v-else>
        <span>暂无数据</span>
    </div>
</div>
</template>

<script>
import Vue from "vue";
import $ from "jquery";
export default {
    data() {
        return {
            // 弹窗多选数组
              multipleSelection: [],
            //   弹窗table数据
              tableData3: [{
                date: '2016-05-03',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
                }, {
                date: '2016-05-02',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
                }, {
                date: '2016-05-04',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
                }, {
                date: '2016-05-01',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
                }, {
                date: '2016-05-08',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
                }, {
                date: '2016-05-06',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
                }, {
                date: '2016-05-07',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
                }],
                dialogTableVisible: false,
                showGonggao:true,
                showMoreGonggao:false,
          // 鼠标移入展示文字
            // isShowSpreadText:false,
          // 是否全部展开变量
            // isSpread:false,
            showLength: 10000,
            moreNoticeDailog: '',
            dialogVisible: false,
            lableData: [],
             boxDataAll:[
                {
                    "jcName": "上市",
                    "isSpread":false,
                    isShowSpreadText:false,
                    "boxData":[
                        { sort:'0',processLabel: "方案制定",publishTime:'2018-08-07',chajitian:'21','gonggao':'圳证监局关于对深圳市华股份有限公司的监管关注函',isShowGonggao:false,showGonggaoText:true,showMoreGonggaoText:false},
                        { sort:'1',processLabel: "割接方案会审",publishTime:'2018-08-07',chajitian:'21','gonggao':'圳证监局关于对深圳市华股份有限公司的监管关注函',isShowGonggao:false,showGonggaoText:true,showMoreGonggaoText:false},
                        { sort:'2',processLabel: "割接审批",publishTime:'2018-08-07',chajitian:'21','gonggao':'圳证监局关于对深圳市华股份有限公司的监管关注函',isShowGonggao:false,showGonggaoText:true,showMoreGonggaoText:false},
                        { sort:'3',processLabel: "审批成功",publishTime:'2018-08-07',chajitian:'21','gonggao':'圳证监局关于对深圳市华股份有限公司的监管关注函',isShowGonggao:false,showGonggaoText:true,showMoreGonggaoText:false},
                        { sort:'4',processLabel: "方案制定",publishTime:'2018-08-07',chajitian:'21','gonggao':'圳证监局关于对深圳市华股份有限公司的监管关注函',isShowGonggao:false,showGonggaoText:true,showMoreGonggaoText:false},
                    ],
    
                },
                {
                    "jcName": "审核",
                     "isSpread":false,
                     isShowSpreadText:false,
                    "boxData":[
                        { sort:'5',processLabel: "方案制定",publishTime:'2018-08-07',chajitian:'21','gonggao':'圳证监局关于对深圳市华股份有限公司的监管关注函',isShowGonggao:false,showGonggaoText:true,showMoreGonggaoText:false},
                        { sort:'6',processLabel: "割接方案会审",publishTime:'2018-08-07',chajitian:'21','gonggao':'圳证监局关于对深圳市华股份有限公司的监管关注函',isShowGonggao:false,showGonggaoText:true,showMoreGonggaoText:false},
                        { sort:'7',processLabel: "割接审批",publishTime:'2018-08-07',chajitian:'21','gonggao':'圳证监局关于对深圳市华股份有限公司的监管关注函',isShowGonggao:false,showGonggaoText:true,showMoreGonggaoText:false},
                        { sort:'8',processLabel: "审批成功",publishTime:'2018-08-07',chajitian:'21','gonggao':'圳证监局关于对深圳市华股份有限公司的监管关注函',isShowGonggao:false,showGonggaoText:true,showMoreGonggaoText:false},
                        { sort:'9',processLabel: "方案制定",publishTime:'2018-08-07',chajitian:'21','gonggao':'圳证监局关于对深圳市华股份有限公司的监管关注函',isShowGonggao:false,showGonggaoText:true,showMoreGonggaoText:false},
                    ],
 
                },
                {
                    "jcName": "辅导工作进程",
                    "isSpread":false,
                    isShowSpreadText:false,
                    "boxData":[
                        { sort:'10',processLabel: "方案制定",publishTime:'2018-08-07',chajitian:'21','gonggao':'圳证监局关于对深圳市华股份有限公司的监管关注函',isShowGonggao:false,showGonggaoText:true,showMoreGonggaoText:false},
                        { sort:'11',processLabel: "割接方案会审",publishTime:'2018-08-07',chajitian:'21','gonggao':'圳证监局关于对深圳市华股份有限公司的监管关注函',isShowGonggao:false,showGonggaoText:true,showMoreGonggaoText:false},
                        { sort:'12',processLabel: "割接审批",publishTime:'2018-08-07',chajitian:'21','gonggao':'圳证监局关于对深圳市华股份有限公司的监管关注函',isShowGonggao:false,showGonggaoText:true,showMoreGonggaoText:false},
                        { sort:'13',processLabel: "审批成功",publishTime:'2018-08-07',chajitian:'21','gonggao':'圳证监局关于对深圳市华股份有限公司的监管关注函',isShowGonggao:false,showGonggaoText:true,showMoreGonggaoText:false},
                        { sort:'14',processLabel: "方案制定",publishTime:'2018-08-07',chajitian:'21','gonggao':'圳证监局关于对深圳市华股份有限公司的监管关注函',isShowGonggao:false,showGonggaoText:true,showMoreGonggaoText:false},
                    ],
                    
                },
             ],
            flagLoading: false,
            //组件
            id: "",
            processName: "",
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
    components: {

    },
    computed: {

    },
    methods: {
        // 弹窗多选框
    handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      // 鼠标移入三个点
      handleMouseenterSpread(param){
        param.isShowSpreadText = true
      },
       // 鼠标移出三个点
      handleMouseleaveSpread(param){
        param.isShowSpreadText = false
        // this.isShowSpreadText = false
      },
      // 点击三个点展开隐藏方法
        handleSpread(ev,param){
            // param.isSpread =! param.isSpread
            param.isSpread = true;
            // this.$fouceUpdata()
            // param.showGonggaoText = true;
            // param.showMoreGonggaoText = false;
        },
        // 点击li循环体收起展开内容
        handleSpreadBody(ev,param){
            // param.isSpread =! param.isSpread
            param.isSpread = false;
            param.showGonggaoText = true;
            param.showMoreGonggaoText = false;
            console.log( param.showGonggaoText)
            console.log( param.showMoreGonggaoText)
            // this.$fouceUpdata()
        },
        expandAlltoC(exAllFlag) {
            this.boxData.map(obj => {
                this.showAndHideParent('each' + obj.sort, exAllFlag,obj);
            })
        },
        sortTime(sortName, sort) {
            if (sort == "asc") {
                this.sortFlag = '1'
            } else {
                this.sortFlag = '0'
            }
            this.boxData.reverse();
        },
        moreNoticeClick(obj) {
            //查看更多公告/函件
            this.id = obj.id,
                this.processName = obj.processName,
                this.caseMoreNoticeId = obj.caseId,
                this.sort = obj.sort
            let param = {
                id: obj.id,
                processName: obj.processName,
                caseId: obj.caseId,
                sort: obj.sort
            }
            if (this.$refs.moreNotice != undefined) {
                this.$refs.moreNotice.flagLoading = true;
                this.$refs.moreNotice.tableColumnData(param);
            }
            if (obj.processName == '011' || obj.processName == '020') {
                this.moreNoticeDailog = '相关函件'
            } else {
                this.moreNoticeDailog = '相关公告'
            }
            this.dialogVisible = true;
        },
        // 点击查看公告
        letterClick(param) {
            param.isShowGonggao = true
            param.showGonggaoText = false;
            param.showMoreGonggaoText = true;
            // $('.moreNoticeCss').html('查看更多公告')
            //点击查看更多公告/函件
           
        },
        // 点击查看更多公告
        moreLetterClick(){

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
        showAndHide(obj,item, type) {
            if (
                document.getElementById(obj).getAttribute("style") ===
                "display:none;" ||
                document.getElementById(obj).getAttribute("style") ===
                "display: none;"
            ) {
                document.getElementById(obj).setAttribute("style", "display:back;");
                if (type == 'title') {
                  document.querySelector('#sign' + item.sort).className = 'circle fa fa-chevron-up'
                }
                item.flag = 1;
            } else {
                document.getElementById(obj).setAttribute("style", "display:none;");
                if (type == 'title') {
                  document.querySelector('#sign' + item.sort).className = 'circle fa fa-chevron-down'
                }
                item.flag = 0;
            }
        },
        onMouseOver (obj, item, index) {
          this.indexShowHidden = true
          this.thisIndex = index
          if (document.querySelector('#' + obj).style.display == 'none') {
            document.querySelector('#sign' + item.sort).className = 'circle fa fa-chevron-down'
          } else {
            document.querySelector('#sign' + item.sort).className = 'circle fa fa-chevron-up'
          }
        },
        onMouseOut (obj, item) {
          this.indexShowHidden = false
          if (document.querySelector('#' + obj).style.display != 'none') {
            document.querySelector('#sign' + item.sort).className = 'circle'
          } else {
            document.querySelector('#sign' + item.sort).className = 'circle'
          }
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
        letterPublishTime(item) {
            var temp = item.details;
            for (var num = 0; num < temp.length; num++) {
                var str = String(temp[num].relaId);
                if (str.indexOf("letter") != -1) {
                    return temp[num].publishTime
                }
            }
            return '';
        },
        letterLengthJun(item) {
            var temp = item.details;
            var count = 0;
            for (var num = 0; num < temp.length; num++) {
                var str = String(temp[num].relaId);
                if (str.indexOf("letter") != -1) {
                    count++;
                }
            }
            if (count > 1) {
                return true;
            } else {
                return false;
            }
        },
        letterLengthJun2(item) {
            var temp = item.details;
            var count = 0;
            for (var num = 0; num < temp.length; num++) {
                var str = String(temp[num].relaId);
                if (str.indexOf("letter") != -1) {
                    count++;
                }
            }
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        },
        anLengthJun(item) {
            var temp = item.details;
            var count = 0;
            for (var num = 0; num < temp.length; num++) {
                var str = String(temp[num].relaId);
                if (str.indexOf("AN") != -1) {
                    count++;
                }
            }
            if (count > 1) {
                return true;
            } else {
                return false;
            }
        },
        anLengthJun2(item) {
            var temp = item.details;
            var count = 0;
            for (var num = 0; num < temp.length; num++) {
                var str = String(temp[num].relaId);
                if (str.indexOf("AN") != -1) {
                    count++;
                }
            }
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        },
        LengthJun(item) {
            var temp = item.details;
            var count = 0;
            for (var num = 0; num < temp.length; num++) {
                var str = String(temp[num].relaId);
                if (str.indexOf("AN") != -1 || str.indexOf("letter")) {
                    count++;
                }
            }
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        },
        LengthJun2(item) {
            var temp = item.details;
            var count = 0;
            for (var num = 0; num < temp.length; num++) {
                var str = String(temp[num].relaId);
                if (str.indexOf("AN") != -1 || str.indexOf("letter")) {
                    count++;
                }
            }
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        }
    },
    created() {
        this.flagLoading = false;
    },
    mounted() {
    },
    watch: {}
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
/* 进程名样式 */
.jincheng {
    position: relative;
    top: -15px;
    left: -11px;
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
// 三个点展开样式
.spread{
  color:#0099cc;
  font-size:20px;
  float:right;
  margin-right:50px;
  cursor:pointer;
}
.spreadText{
    font-weight: 400;
    width: 109px;
    height: 20px;
    font-style: normal;
    font-size: 13px;
    color: #333333;
    text-align: center;
    border:1px solid rgba(228, 228, 228, 1);
    position:relative;
    left: 220px;
    top: 45px;
    border-radius:3px;
    padding:2px;
}
.allJincheng{
    border-bottom:1px solid rgba(242, 242, 242, 1);
    border-top:1px solid rgba(242, 242, 242, 1);
}
.DownloadAnnouncement{
    width: 109px;
    height: 30px;
    margin-top:20px;
    margin-left:30px;
    background: inherit;
    background-color: rgba(255, 255, 255, 0);
    box-sizing: border-box;
    border-width: 1px;
    border-style: solid;
    border-color: rgba(202, 202, 202, 1);
    border-radius: 2px;
    -moz-box-shadow: none;
    -webkit-box-shadow: none;
    box-shadow: none;
    font-family: 'PingFang-SC-Regular', 'PingFang SC';
    font-weight: 400;
    font-style: normal;
    font-size: 14px;
}
</style>
