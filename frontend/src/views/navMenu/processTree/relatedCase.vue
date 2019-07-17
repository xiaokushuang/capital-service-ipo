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
                                            <div :style="{'font-size': '14px', 'color': '#14bcf5','display':'inline-block','margin-right':item.progressName.length>=14?'-2px':'12px'}"  class="tinyHand">
                                                <a @click="gonggaoClick(item.caseId,item.openFlag)">{{item.progressName}}</a>
                                             </div>
                                             <!-- 非科创版审核结果 -->
                                            <span v-if="item.iecResult=='获通过'" :style={background:htg}  class="htg" >获通过</span>
                                            <span v-if="item.iecResult=='未获通过'" :style={background:whtg} class="whtg">未获通过</span>
                                            <span v-if="item.iecResult=='暂缓表决'" :style={background:zhbj} class="zhbj">暂缓表决 </span>
                                            <span v-if="item.iecResult=='取消审核'" :style={background:qxsh} class="qxsh">取消审核</span>
                                            <span v-if="item.iecResult=='待上会'" :style={background:dsh} class="dsh">待上会</span>
                                             <!-- 科创版审核结果 -->
                                            <span v-if="item.iecResult=='通过'" :style="{'background':tg}"   class="tg" >通过</span>
                                            <span v-if="item.iecResult=='未通过'" :style="{'background':wtg}"  class="wtg">未通过</span>
                                            <span v-if="item.iecResult=='注册生效'" :style="{'background':zcsx}" class="htg">注册生效 </span>
                                            <span v-if="item.iecResult=='不予注册'" :style="{'background':whtg}" class="whtg">不予注册</span>
                                            <span v-if="item.iecResult=='待上会'" :style="{'background':dsh}"  class="dsh">待上会</span>
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
import {iframeDoMessage} from '@/utils/auth';
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
    },
    components: {
    },
    computed: {

    },
    methods: {
       gonggaoClick(id,flag) {
           debugger;
            if (flag =='1') {
                const _self = this;
                const {href} = _self.$router.resolve({
                name: 'caseDetail',
                query: {caseId: id, access_token: _self.$store.state.app.token,tenant_info:_self.$store.state.app.info}
                });
                this.$open(href);
            } 
            if (flag !=='1') {
                // let url = window.location.href;
                // url = url.replace(this.$route.path, '/ipoPopWin');
                // url = url.split('?')[0]
                let url = 'https://services-dev.valueonline.cn/ui/ipo/ipoPopWin?access_token=a261c9de-5d47-4ba5-a7eb-930375b42cb1&tenant_info=VZ2LMFiWfMrN0HwKnBa8NCiVk2B5CNgaAlLlQ6zMg_BEi3CJU4Dhvi5nHWZALsPpXxekXG09tny9nA-lpZVrUu3CYfAVinF9z3MQR7c63eC9Rog_8ierWBKVANApidx7af25Lp3iaMYdIbRypxc5YYX-uMOn3kGULMPAcvaG-DEAW5fEbkAYVibmziKI8m5XoWasShjhcsgBEEnbi5o_ew%3D%3D&baseUrl=http%3A%2F%2F999600.valueonline.cn&companyId=999600'
                console.log('关联案例弹窗',url)
                iframeDoMessage(window.parent, 'popWinOut', ['提示', url, '427', '217']);
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
    // position: relative;
    // left: 38%;
    // // left: 95%;
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
    // // left: 95%;
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
    // // left: 95%;
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
    // // left: 95%;
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
    // // left: 95%;
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
    // // left: 95%;
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
    // // left: 95%;
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
