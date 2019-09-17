<template>
    <div class="processTree">
        <div v-if="proList.length>0">
            <!-- 第一个进程 -->
            <div>
                <el-row style="padding-left:12px">
                    <el-col :span="24" style="border-left:1px solid #E9E9E9; margin-bottom: -10px;margin-top:10px;padding-bottom: 10px;">
                        <div>
                             <div v-for="(item,index) in proList">
                                <div class="right" >
                                    <div class="border-box">
                                        <span>
                                            <!-- <span :id="'sign' +  item.progressIndex" :class="{'circle':item.dateCompare=='1','grayCircle':item.dateCompare=='0'}" v-if="proList.length"> -->
                                                <span v-if="proList.length" class="circle">
                                                <!-- 展示序号 【默认时候不加类】-->
                                                <!-- <span :id="'num' +  item.progressIndex"> -->
                                                <span>
                                                    {{item.proSort}}
                                                </span>
                                            </span>
                                        </span>
                                    </div>
                                    <div class="border-right">
                                        <div style="">
                                            <div :style="{'font-size': '14px', 'color': '#14bcf5','display':'inline-block','margin-right':item.progressName.length>=14?'-4px':'12px'}"  class="tinyHand">
                                                <a @click="gonggaoClick(item.caseId,item.openFlag)">{{item.progressName}}</a>
                                             </div>
                                             <!-- <div :style="{'font-size': '14px', 'color': '#14bcf5','display':'inline-block','margin-right':item.progressName.slice(0,13).length>=14?'-4px':'12px'}"  class="tinyHand">
                                                <a @click="gonggaoClick(item.caseId,item.openFlag)">{{item.progressName.slice(0,13)}}</a>
                                             </div> -->
                                             <!-- 非科创版审核结果 -->
                                            <span v-if="item.iecResult=='00'" :style={background:htg}  class="htg" >获通过</span>
                                            <span v-if="item.iecResult=='01'" :style={background:whtg} class="whtg">未获通过</span>
                                            <span v-if="item.iecResult=='02'" :style={background:zhbj} class="zhbj">暂缓表决 </span>
                                            <span v-if="item.iecResult=='03'" :style={background:qxsh} class="qxsh">取消审核</span>
                                            <span v-if="item.iecResult=='04' || item.iecResult=='09'" :style={background:dsh} class="dsh">待上会</span>
                                             <!-- 科创版审核结果 -->
                                            <span v-if="item.iecResult=='05'" :style="{'background':tg}"   class="tg" >通过</span>
                                            <span v-if="item.iecResult=='06'" :style="{'background':wtg}"  class="wtg">未通过</span>
                                            <span v-if="item.iecResult=='07'" :style="{'background':zcsx}" class="zcsx">注册生效 </span>
                                            <span v-if="item.iecResult=='08'" :style="{'background':whtg}" class="whtg">不予注册</span>
                                            <span v-if="item.iecResult=='10'" :style="{'background':qxsh}"  class="qxsh">取消审议</span>
                                        </div>
                                        <div style="font-size: 12px;margin-top: 8px;color: #999;margin-bottom: 12px;">
                                            <div v-if="item.processTime" v-text='item.processTime'></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </el-col>
                </el-row>
            </div>
        </div>
    </div>
</template>

<script>
import Vue from "vue";
import $ from "jquery";
export default {
    data() {
        return {
             caseId1:this.$store.state.app.caseId,
             treeTypeCode:'',
             // 鼠标移入展示文字
             isShowSpreadText:false,
             treeList:[],
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
    name: "relatedCase",
    props:["proList"],
    created(){
     },
    mounted() {
        console.log('关联案例',this.proList)
    },
    components: {
    },
    computed: {

    },
    methods: {
       gonggaoClick(id,flag) {
            if (id) {
                const _self = this;
                const {href} = _self.$router.resolve({
                name: 'caseDetail',
                query: {caseId: id, access_token: _self.$store.state.app.token,tenant_info:_self.$store.state.app.info}
                });
                this.$open(href);
            } else  {
                this.$emit("noOpenFlag",true)
            }
            
      },
    }
};
</script>

<style scoped lang="scss">
.tinyHand:hover {
    cursor: pointer;
    text-decoration: underline;
    text-decoration-color: #14bcf5;
}

.right {
    width: 100%;
    float: left;
    padding-top:10px;
}
.right:hover {
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
    color: #4ec8e5;
    padding:5px;
    padding-left: 8px;
    padding-right: 7px;
    padding-right:5px;
    line-height:10px;
    display:inline-block;
}
.zcsx{
    font-size: 12px;
    color: #4ec8e5;
    padding:5px;   
    padding-left: 7px;
    padding-right: 7px;
    line-height:10px;
    display:inline-block;
}
.wtg{
    font-size: 12px;
    color: #ea5365;
    padding: 5px;
    padding-left: 7px;
    padding-right: 7px;
    line-height: 10px;
    display: inline-block;
}
.tg{
    font-size: 12px;
    color: #4ec8e5 ;
    padding:5px;
    padding-left: 9px;
    padding-right: 7px;
    line-height:10px;
    display:inline-block;
}
.whtg{
    font-size: 12px;
    color: #ea5365;
    padding:5px;
    padding-left: 7px;
    padding-right: 7px;
    line-height:10px;
    display:inline-block;
}
.zhbj{
    font-size: 12px;
    color: #f9b162;
    padding:5px;   
     padding-left: 7px;
    padding-right: 7px;
    line-height:10px;
    display:inline-block;
}
.qxsh{
    font-size: 12px;
    color: #aabfe2;
    padding:5px;
    padding-left: 7px;
    padding-right: 7px;
    line-height:10px;
    display:inline-block;
}
.dsh{
    font-size: 12px;
    color: #f98962;
    padding:5px;
    padding-left: 7px;
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
