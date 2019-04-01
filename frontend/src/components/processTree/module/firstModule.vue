<template>
<div>
    <div v-if="this.viewStatus == '1'">
        <div class='firstModule' v-if="paperData">
            <p v-if="(paperData.extend1!= null && paperData.extend1!='')||(paperData.processName=='011' && paperData.extend2)" class="pClass">
                <!-- <span>{{this.paperData.processName}}****</span> -->
                <!-- <span v-if="paperData.processName=='007'">董事会届次:{{paperData.extend1 ? paperData.extend1 : '- -'}}</span> -->
                <span v-if="paperData.processName=='011'">
                    <span style="display:inline-block;margin-bottom:12px" v-if="paperData.extend2 && paperData.extend1">
                        <span class="d300" >函件类型:</span><span class="d301">{{paperData.extend2 ? paperData.extend2 : '- -'}}</span>
                </span>
                <span v-else-if="paperData.extend2 && !paperData.extend1" style="display:inline-block;">
                        <span class="d300" >函件类型:</span><span class="d301">{{paperData.extend2 ? paperData.extend2 : '- -'}}</span>
                </span>
                <span v-else></span>
                <br v-if="paperData.extend2"/>
                <span style="display:inline-block;" v-if="paperData.extend1">
                        <span class="d300" v-if="paperData.extend1" >问题数量:</span>
                <span class="d301">{{paperData.extend1 ? paperData.extend1 : '- -'}}个</span>
                </span>
                <span v-else></span>
                </span>
                <!-- </span> -->
                <span v-else-if="paperData.processName=='20'|| paperData.processName=='21'"><span class="d300">问题:</span><span class="d301">{{paperData.extend1 ? paperData.extend1 : '- -'}}个</span></span>
                <span v-else-if="paperData.processName=='012'"><span class="d300">问题数量:</span><span class="d301">{{paperData.extend1 ? paperData.extend1 : '- -'}}个</span></span>
                <span v-else-if="paperData.processName=='013'"><span class="d300">调整类别:</span><span class="d301">{{paperData.extend1 ? paperData.extend1 : '- -'}}</span></span>
                <span v-else-if="paperData.processName=='014'"><span class="d300">说明会类型:</span><span class="d301">{{paperData.extend1 ? paperData.extend1 : '- -'}}</span></span>
                <span v-else-if="paperData.processName=='018'"><span class="d300">部门:</span><span class="d301">{{paperData.extend1 ? paperData.extend1 : '- -'}}</span></span>
                <span v-else-if="paperData.processName=='019'" class="wordWrap"><span class="d300">受理通知书:</span><span class="d301 wordWrap">{{paperData.extend1 ? paperData.extend1 : '- -'}}</span></span>
                <span v-else-if="paperData.processName=='020' || paperData.processName=='021'"><span class="d300">问题数量:</span><span class="d301">{{paperData.extend1 ? paperData.extend1 : '- -'}}个</span></span>
                <span v-else-if="paperData.processName=='025'"><span class="d300">实施情况:</span><span class="d301">{{paperData.extend1 ? paperData.extend1 : '- -'}}</span></span>
                <span v-else-if="paperData.processName=='026'"><span class="d300">标的名称:</span><span class="d301">{{paperData.extend1 ? paperData.extend1 : '- -'}}</span></span>
                <span v-else-if="paperData.processName=='027'"><span class="d300">登记受理日期:</span><span class="d301">{{paperData.extend1 ? paperData.extend1 : '- -'}}</span></span>
                <span v-else-if="paperData.processName=='028'"><span class="d300">登记受理日期:</span><span class="d301">{{paperData.extend1 ? paperData.extend1 : '- -'}}</span></span>
                <span v-else-if="paperData.processName=='029'"><span class="d300">业绩承诺完成情况:</span><span class="d301">{{paperData.extend1 ? paperData.extend1 : '- -'}}</span></span>
                <span v-else></span>
            </p>
            <p v-else></p>

            <p v-if="paperData.extend2!= null && paperData.extend2 != ''" class="off pClass">
                <!-- <span v-if="paperData.processName == '011'"></span> -->
                <span v-if="paperData.processName == '025'">
                        <span v-if="paperData.extend2.length > 50">
                            <el-row v-if="this.allViewFlag ==  '0'">
                                <div class="divOverFollow" :title="paperData.extend2" ><span class="d300">进展简介:</span><span class="d301">{{paperData.extend2.substring(0,50)}}</span>...<a style="color:#0066FF;" @click="allView()">点击展开</a>
        </div>
        </el-row>
        <el-row v-else-if="this.allViewFlag ==  '1'">
            <div class="divOverFollow" :title="paperData.extend2"><span class="d300" >进展简介:</span><span class="d301">{{paperData.extend2}}</span><a style="color:#0066FF;" @click="closeView()">点击收起</a></div>
        </el-row>
        </span>
        <span v-else class="d301">
            <div class="divOverFollow" :title="paperData.extend2"><span class="d300" >进展简介:</span><span class="d301">{{paperData.extend2}}</span>
    </div>
    </span>

    </span>
    <span v-else></span>
    </p>
    <p v-else></p>
    <p v-if="paperData.extend2!= null && paperData.extend2 != ''" class="off pClass">
        <span v-if="paperData.processName == '026'"><span class="d300">工商变更时间:</span>
        <span class="d301">{{paperData.extend2 ? paperData.extend2:'- -'}}</span>
        </span>
        <span v-else-if="paperData.processName=='027'" ><span class="d300">股票上市数量:</span>
        <span class="d301">{{paperData.extend2 ? moneystr(formatNumberMethod((parseInt(removeCommas(paperData.extend2))/10000)),"股"):'- -'}}</span>
        </span>
        <span v-else-if="paperData.processName=='028'"><span class="d300">股票上市数量:</span>
        <span class="d301">{{paperData.extend2 ? moneystr(formatNumberMethod((parseInt(removeCommas(paperData.extend2))/10000)),"股"):'- -'}}</span>
        </span>
        <span v-else></span>
    </p>
    <p v-else></p>

    <!-- <span v-else-if="paperData.processName=='029'">累计减值额:
                        <span>{{paperData.extend2 ? paperData.extend2:'- -'}}</span>
                    </span> -->
    <!-- <span v-else>{{paperData.extend2}}</span> -->
    <p v-if="paperData.extend3!= null && paperData.extend3 != ''" class="off pClass">
        <!-- <span v-if="paperData.processName=='029'">本期应补偿减值额:{{paperData.extend3 ? paperData.extend3:'- -'}}</span> -->
        <span v-if="paperData.processName=='027'"><span class="d300">股票上市时间:</span>
        <span class="d301">{{paperData.extend3 ? paperData.extend3:'- -'}}</span>
        </span>
        <span v-else-if="paperData.processName=='028'"><span class="d300">股票上市时间:</span>
        <span class="d301">{{paperData.extend3 ? paperData.extend3:'- -'}}</span>
        </span>
        <span v-else></span>
    </p>
    <p v-else></p>
    <!-- <p class="off pClass">
                    <span v-if="paperData.processName == '029'">是否商誉减值:</span>
                    {{paperData.extend4}}
                </p> -->
    <p class="off pClass" v-if="paperData.extend5 != null && paperData.extend5 !=''">
        <span v-if="paperData.extend5 != null && paperData.extend5 !=''">
                        <span v-if="paperData.extend5.length > 33">
                            <el-row v-if="this.allViewFlag ==  '0'">
                                <div class="divOverFollow" :title="paperData.extend5">{{paperData.extend5.substring(0,38)}}</div>
                                <a style="color:#0066FF;" @click="allView()">点击展开</a>
                            </el-row>
                            <el-row v-else-if="this.allViewFlag ==  '1'">
                                <div :title="paperData.extend5">{{paperData.extend5}}</div>
                                <a style="color:#0066FF;" @click="closeView()">点击收起</a>
                            </el-row>
                        </span>
        <span v-else>
                            <div :title="paperData.extend5">{{paperData.extend5}}</div>
                        </span>
        </span>
    </p>
    <p v-else></p>
</div>
</div>
<div v-if="this.viewStatus == '0'">
    <div>
        <p class="off pClass" style="margin-top:12px" v-if="arrNullJung(paperData1) != ''">
            <el-table v-if="arrNullJung(paperData1) != ''" :data="arrNullJung(paperData1)" class="tableStyle" :header-cell-style="{background:'rgb(242,242,242)',color:'black'}">
                <el-table-column prop="extend1" label="标的名称">
                </el-table-column>
                <el-table-column prop="extend2" label="工商变更时间" min-width=120 align="center">
                </el-table-column>
            </el-table>
            <!-- <span>标的&工商变更时间:</span><br> -->
            <!-- <span v-for="(item,index) in paperData1" :key="index">
                        <el-row>{{item.extend1}} &nbsp; {{item.extend2}}</el-row>
                    </span> -->
        </p>
        <p v-else></p>
        <div style="font-family: 'PingFangSC-Regular', 'PingFang SC';font-weight: 400;font-style: normal;font-size: 14px;">
            <span v-if="paperData1[0]">
                        <span v-if="paperData1[0].relaName">
            <span class="spanClass" :title="paperData1[0].relaName" @click="openUrl(paperData1[0])">{{paperData1[0].relaName}}</span>
            </span>
            </span>
        </div>
    </div>
</div>
<div v-if="paperData.processName =='011' || paperData.processName =='012' || paperData.processName =='020' || paperData.processName =='021'" style="font-family: 'PingFangSC-Regular', 'PingFang SC';font-weight: 400;font-style: normal;font-size: 14px;">
    <!--<span class="spanClass" v-if="paperData.relaName.length > 38" :title="paperData.relaName" @click="openLetter(paperData)">{{paperData.relaName.substring(0,38)}}...</span>-->
    <!--<span class="spanClass" v-else :title="paperData.relaName" @click="openLetter(paperData)">{{paperData.relaName}}</span>-->
    <span class="spanClass"  @click="openLetter(paperData)">{{paperData.relaName}}</span>
</div>
<div v-else-if="paperData1[0]!=null&&paperData1[0]!='' && paperData1[0].processName=='026'"></div>
<div v-else style="font-family: 'PingFangSC-Regular', 'PingFang SC';font-weight: 400;font-style: normal;font-size: 14px;">
    <span class="spanClass"  @click="openUrl(paperData)">{{paperData.relaName}}</span>
</div>
</div>
</template>

<script>
import {
    mapGetters
} from "vuex";
import {
    iframeDoMessage
} from "@/utils/auth";
export default {
    name: "firstModule",
    data() {
        return {
            //数据
            viewStatus: "",
            lableData: [], //标签数据
            paperData: {
                sort: "",
                relaName: "",
                extend1: "",
                extend2: "",
                extend5: "",
                processName: ""
            },
            paperData1: {},
            allViewFlag: "0" //查看全文标志
        };
    },
    props: {
        // boxData: {
        //     type: Array,
        //     default: () => []
        // }
    },
    methods: {
        openLetter(v) {
            window.open(v.siteBaseUrl + "/letter/viewShare?letterId=" + v.letterId)
        },
        getLabText(v, t) {
          if(v) {
            var valueList = v.split(",");
            var Str = "";
            if (this.lableData.data) {
              var labList = this.lableData.data[t];
              labList.map(function (item) {
                valueList.map(obj => {
                  if (item.labelValue == obj) {
                    Str += "，" + item.labelName;
                  }
                });
              });
            }
            return Str.slice(1, Str.length);
          }
        },
        allView() {
            //点击展开
            this.allViewFlag = "1";
        },
        closeView() {
            //点击关闭
            this.allViewFlag = "0";
        },
        getData(obj) {
            if (obj) {
                if (obj.processName) {
                    if (obj.processName == "026") {
                        if (obj.details) {
                            this.paperData1 = obj.details;
                            this.viewStatus = "0";
                        }
                    } else if (obj.processName == "012") {
                        // || obj.processName == "021"
                        var count = 0;
                        if (obj.details[obj.details.length - 1]) {
                            this.viewStatus = "1";
                            for (var num = 0; num < obj.details.length; num++) {
                                var str = obj.details[num].relaId;
                                if (str.indexOf("letter") != -1) {
                                    count++
                                }
                            }
                            this.paperData = obj.details[obj.details.length - parseInt(count)];
                        }
                    } else {
                        if (obj.details[0]) {
                            this.viewStatus = "1";
                            this.paperData = obj.details[0];
                            if (this.paperData.processName == "011") {
                                this.paperData.extend2 = this.getLabText(
                                    this.paperData.extend2,
                                    "LETTER_TYPE"
                                );
                            } else if (this.paperData.processName == "013") {
                                this.paperData.extend1 = this.getLabText(
                                    this.paperData.extend1,
                                    "ADJUSTMENT_TYPE"
                                );
                            } else if (this.paperData.processName == "014") {
                                this.paperData.extend1 = this.getLabText(
                                    this.paperData.extend1,
                                    "EXPLANATION_TYPE"
                                );
                            } else if (this.paperData.processName == "018") {
                                this.paperData.extend1 = this.getLabText(
                                    this.paperData.extend1,
                                    "AUDIT_ORG"
                                );
                            } else if (this.paperData.processName == "025") {
                                this.paperData.extend1 = this.getLabText(
                                    this.paperData.extend1,
                                    "IMPLEMENT_PROGRESS"
                                );
                            } else if (this.paperData.processName == "029") {
                                this.paperData.extend1 = this.getLabText(
                                    this.paperData.extend1,
                                    "PERFORMANCE_FINISH"
                                );
                                this.paperData.extend4 = this.getLabText(
                                    this.paperData.extend4,
                                    "YES_OR_NO"
                                );
                            }
                        }
                    }
                }
            }
        },
        openUrl(obj) {
            //打开url
            var url = obj.infoUrl;
            var title = obj.relaName;
            if (
                !(navigator.userAgent.indexOf("MSIE") >= 0) &&
                navigator.userAgent.indexOf("Opera") < 0
            ) {
                if (obj.pdfBaseUrl != null && obj.pdfBaseUrl != "") {
                    var _pdfBaseUrl = obj.pdfBaseUrl;
                }

                if (
                    obj.infoUrl
                    .substring(obj.infoUrl.length - 4, obj.infoUrl.length)
                    .toLowerCase() == ".pdf" &&
                    _pdfBaseUrl
                ) {
                    url =
                        _pdfBaseUrl +
                        "web/viewer.html?file=" +
                        _pdfBaseUrl +
                        "pdf/H2_" +
                        obj.relaId +
                        "_1.pdf&originTitle=" +
                        title;
                }
            }
            window.open(encodeURI(url));
        },
        arrNullJung(data) {
            var arr = [];
            for (var num = 0; num < data.length; num++) {
                if ((data[num].extend1 != null && data[num].extend1 != "" && data[num].extend1 != undefined) || (data[num].extend2 != null && data[num].extend2 != "" && data[num].extend2 != undefined)) {
                    arr.push(data[num]);
                }
            }
            return arr;
        },
        formatNumberMethod(number) {
            if (number == null || number == '') {
                number = 0;
            }
            return parseFloat(Number(number).toFixed(2));
        },
        radiostr(num, unit) {
            if (Number(num)) {
                var regexp = /(?:\.0*|(\.\d+?)0+)$/;
                num = Number(num).toFixed(2).replace(regexp, '$1');
            }
            if (num && unit) {
                return num + unit;
            } else if (num == 0) {
                return num;
            } else if (num) {
                return num;
            } else {
                num = "- -";
            }
            return num;
        },
        moneystr(num, moneyType) {
            if (Number(num)) {
                var regexp = /(?:\.0*|(\.\d+?)0+)$/;
                num = Number(num).toFixed(2).replace(regexp, '$1');
                var arr = num.split('.');
                var tmp = arr[0].replace(/\B(?=(\d{3})+$)/g, ',').replace(/,$/g, '');
                if (arr[1] != undefined) {
                    num = tmp + '.' + arr[1];
                } else {
                    num = tmp
                }
            }
            if (num && moneyType) {
                return num + "万" + moneyType;
            } else if (num == 0) {
                return num + "万" + moneyType;
            } else if (num) {
                return num;
            } else {
                num = "- -";
            }
            return num;
        },
        removeCommas(param) {
            if (param != null && param != undefined && param != '') {
                let result = (param + '').replace(/,/g, '')
                result = Number(result)
                return result
            } else {
                return ''
            }
        },
    },
    mounted() {
        this.lableData = this.$root.lableData;
        this.getData(this.$root.items);
    },
    computed: {},
    watch: {}
};
</script>

<style scoped>
.divOverFollow {
    word-break: break-all;
    word-wrap: break-word;
    overflow: hidden;
    /* text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2; */
    /* height: 52px; */
    /* width: 300px; */
}

.pClass {
    margin-top: 0px;
    margin-bottom: 12px;
    font-size: 14px;
    color: #333;
}

.spanClass {
    margin-top: 12px;
    margin-bottom: 12px;
    color: #339fb9;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
}

.spanClass:hover {
    cursor: pointer;
    text-decoration: underline;
    text-decoration-color: #339fb9;
    color: #339fb9;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    /* height: 52px; */
    /* width: 300px; */
}

.d300 {
    font-size: 14px;
    color: #999999
}

.d301 {
    font-size: 14px;
    color: #666666
}

.tableStyle tr {
    height: 40px;
}

.tableStyle th {
    background-color: #f2f2f2;
    height: 40px;
}
.wordWrap{
    word-break: break-all;
    word-wrap: break-word;
}
</style>
