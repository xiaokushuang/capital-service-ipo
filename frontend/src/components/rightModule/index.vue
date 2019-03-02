<template>
<div v-loading="flagLoading" element-loading-text="给我一点时间" class="zdzc_css" style="margin-top:20px;">
  <div  v-if="boxDataAll.length>0" v-for="boxDataItem in boxDataAll">
        <el-row style="margin-top:10px;padding-left:12px">
            <el-col :span="24" style="border-left:1px solid #0099cc;">
               <!-- 进程名 -->
                <div class="jincheng">
                  <img src="../../assets/images/jinchengjian.png" alt="">
                  <p>{{boxDataItem.jcName}}</p>
                </div>
                <div class="right" v-for="(item,index) in boxDataItem.boxData" :key="item.sort">
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
                        </div>
                        <div :id="'each' + item.sort" style="display:none;">
                            <div :ref='item.sort' :class="'abc'+item.sort"></div>
                        </div>
                       
                        <div style="margin-bottom: 24px;margin-top: 8px;">
                          <!-- 点击查看公告 -->
                            <span >
                               <div href="#" @click="showAndHide('each' + item.sort,item, null)" class="moreNoticeCss" style="cursor: pointer;">查看公告</div>
                            </span>
                          <!-- 点击查看公告后展示的内容 -->

                        </div>
                    </div>
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
export default {
    data() {
        return {
            showLength: 10000,
            moreNoticeDailog: '',
            dialogVisible: false,
            lableData: [],
             boxDataAll:[
                {
                    "jcName": "上市",
                    "boxData":[
                        { sort:'0',processLabel: "方案制定",publishTime:'2018-08-07',chajitian:'21'},
                        { sort:'1',processLabel: "割接方案会审",publishTime:'2018-08-07',chajitian:'21'},
                        { sort:'2',processLabel: "割接审批",publishTime:'2018-08-07',chajitian:'21'},
                        { sort:'3',processLabel: "审批成功",publishTime:'2018-08-07',chajitian:'21'},
                        { sort:'4',processLabel: "方案制定",publishTime:'2018-08-07',chajitian:'21'},
                    ]
                },
                {
                    "jcName": "审核",
                    "boxData":[
                        { sort:'5',processLabel: "方案制定",publishTime:'2018-08-07',chajitian:'21'},
                        { sort:'6',processLabel: "割接方案会审",publishTime:'2018-08-07',chajitian:'21'},
                        { sort:'7',processLabel: "割接审批",publishTime:'2018-08-07',chajitian:'21'},
                        { sort:'8',processLabel: "审批成功",publishTime:'2018-08-07',chajitian:'21'},
                        { sort:'9',processLabel: "方案制定",publishTime:'2018-08-07',chajitian:'21'},
                    ]
                },
                {
                    "jcName": "辅导工作进程",
                    "boxData":[
                        { sort:'10',processLabel: "方案制定",publishTime:'2018-08-07',chajitian:'21'},
                        { sort:'11',processLabel: "割接方案会审",publishTime:'2018-08-07',chajitian:'21'},
                        { sort:'12',processLabel: "割接审批",publishTime:'2018-08-07',chajitian:'21'},
                        { sort:'13',processLabel: "审批成功",publishTime:'2018-08-07',chajitian:'21'},
                        { sort:'14',processLabel: "方案制定",publishTime:'2018-08-07',chajitian:'21'},
                    ]
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
        moreLetterClick(obj) {
            //查看更多公告/函件
            // for()
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
        showAndHideParent(obj, exAllFlag,item) {
            if (
                exAllFlag == '0'
            ) {
                document
                    .getElementById(obj)
                    .setAttribute("style", "display:none;");
                item.flag = 0;

            } else {
                document
                    .getElementById(obj)
                    .setAttribute("style", "display:back;");
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

</style>
