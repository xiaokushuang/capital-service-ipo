<template>
<div>
    <div class='firstModule' v-if="paperData.processName == '022'">
        <p class="pClass" v-if="paperData.extend3!=null &&paperData.extend3!=''">
            <span class="d300">审核结果:</span>
            <span class="d301" v-if="paperData.extend3 == '未通过'" style="color:red;">{{paperData.extend3}}</span>
            <span class="d301" v-else>{{paperData.extend3}}</span>
        </p>
        <p class="pClass" v-if="paperData.extend4!=null &&paperData.extend4!=''">
            <span class="d300">问题类别:</span><span class="d301">{{paperData.extend4}}</span>
        </p>
        <p v-if="paperData.extend2!=null && paperData.extend2!= ''" class="pClass">
            <span v-if="paperData.extend2.length > 50">
                    <el-row v-if="this.allViewFlag ==  '0'">
                        <div class="divOverFollow" :title="paperData.extend2"><span class="d300">审核意见:</span><span class="d301">{{paperData.extend2.substring(0,50)}}</span>...<a style="color:#0066FF;" @click="allView()">点击展开</a></div>
                    </el-row>
                    <el-row v-else-if="this.allViewFlag ==  '1'">
                        <div class="wordWrap" :title="paperData.extend2"><span class="d300">审核意见:</span><span class="d301">{{paperData.extend2}}</span><a style="color:#0066FF;" @click="closeView()">点击收起</a></div>
                    </el-row>
                </span>
            <span v-else>
                    <div class="wordWrap" :title="paperData.extend2"><span class="d300">审核意见:</span><span class="d301">{{paperData.extend2}}</span></div>
            </span>
        </p>
        <p class="pClass" v-if="pers[0]!=undefined && pers[0]!=''&& pers[0].name !='' && pers[0].name !=''">
            <span class="pClass d300" >审核委员: </span>
            <span v-for="(item,index) in pers">
            <span v-if="item != null">
                <span class="pClass d301" v-if="index != pers.length-1">{{item.name}}、</span>
            <span class="pClass d301" v-if="index == pers.length-1">{{item.name}}</span>

        <!-- </p> -->
        <!-- <span v-if="item.intro!=null && item.intro!=''" class="pClass">
                    <span v-if="item.intro.length > 33">
                        <el-row v-if="this.viewFlag ==  '0'">
                            <div class="divOverFollow" :title="item.intro">简历:{{item.intro.substring(0,38)}}</div>
                            <a style="color:#0066FF;" @click="oView()">点击展开</a>
                        </el-row>
                        <el-row v-else-if="this.viewFlag ==  '1'">
                            <div :title="item.intro">简历:{{item.intro}}</div>
                            <a style="color:#0066FF;" @click="cView()">点击收起</a>
                        </el-row>
                    </span>
        <span v-else>
                        <div :title="item.intro">简历:{{item.intro}}</div>
                    </span>
        </span> -->
        </span>
        </span>
        </p>
    </div>
    <div v-if="paperData.processName == '024'">
        <!-- <p style="font-family: 'PingFangSC-Regular', 'PingFang SC';font-weight: 400;font-style: normal;font-size: 14px; color: #0066FF;">
            <span class="spanClass d301" v-if="paperData.relaName.length > 38" :title="paperData.relaName" @click="openUrl(paperData)">{{paperData.relaName.substring(0,38)}}...</span>
            <span class="spanClass d301" v-else :title="paperData.relaName" @click="openUrl(paperData)">{{paperData.relaName}}</span>
        </p> -->
        <p class="pClass" v-if="paperData.extend1">
            <span class="d300">募集资金金额:</span><span class="d301">{{paperData.extend1 ? moneystr(formatNumberMethod(paperData.extend1),"元"): '- -'}}</span>
        </p>
        <p class="pClass" v-if="paperData.extend2!=null&&paperData.extend2!=''">
            <span class="d300" >配套融资发行股份数:</span><span class="d301">{{paperData.extend2 ? moneystr(formatNumberMethod(parseInt(removeCommas(paperData.extend2))/10000),"股"): '- -'}}</span>
        </p>
        <!-- <span v-if="oppos" class="pClass">
                <p>
                    <span>发行对象&购买资产发行股份数(股):</span>
        </p>
        <p v-for="(item,index) in  oppos" :key="index">
            <el-row v-if="item"> {{item.oppoName}} &nbsp; {{item.releaseStock}}</el-row>
        </p>
        </span> -->
    </div>
     <p v-if="paperData.relaName !=null &&  paperData.relaName != ''" style="font-family: 'PingFangSC-Regular', 'PingFang SC';font-weight: 400;font-style: normal;font-size: 14px;">
            <!--<span class="spanClass" v-if="paperData.relaName.length > 38" :title="paperData.relaName" @click="openUrl(paperData)">{{paperData.relaName.substring(0,38)}}...</span>-->
            <!--<span class="spanClass" v-else :title="paperData.relaName" @click="openUrl(paperData)">{{paperData.relaName}}</span>-->
            <span class="spanClass"  @click="openUrl(paperData)">{{paperData.relaName}}</span>
        </p>
</div>
</template>

<script>
export default {
    data() {
        return {
            lableData: [], //标签数据
            paperData: {
                relaName: "",
                processName: ""
            }, //数据
            oppos: {}, //公告证监会批复 发行股数
            pers: {
                intro: ""
            }, //审核委员
            allViewFlag: "0", //查看全文标志
            viewFlag: "0",
            test: "",
        };
    },
    name: "secondModule",
    props: {
        boxData: []
    },
    methods: {
        allView() {
            //点击展开
            this.allViewFlag = "1";
        },
        closeView() {
            //点击关闭
            this.allViewFlag = "0";
        },
        oView() {
            //点击展开
            this.viewFlag = "1";
        },
        cView() {
            //点击关闭
            this.viewFlag = "0";
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
        getData(obj) {
            if (obj.oppos) {
                this.oppos = obj.oppos;
            }
            if (obj.pers) {
                this.pers = obj.pers;
            }
            this.paperData = obj.details[0];
            if (this.paperData.processName == "022") {
                this.paperData.extend1 = this.getLabText(
                    this.paperData.extend1,
                    "AUDIT_COMMITTEE"
                );
                this.paperData.extend3 = this.getLabText(
                    this.paperData.extend3,
                    "AUDIT_RESULT"
                );
                this.paperData.extend4 = this.getLabText(
                    this.paperData.extend4,
                    "QUESTION_TYPE"
                );
            }
        },
        formatNumberMethod(number) {
            if (number == null || number == '') {
                number = 0;
            }
            return parseFloat(Number(number).toFixed(2));
        },
        radiostr(num,unit){
        if(Number(num)){
          var regexp=/(?:\.0*|(\.\d+?)0+)$/;
          num = Number(num).toFixed(2).replace(regexp,'$1');
        }
        if(num && unit){
          return num + unit;
        }else if(num == 0){
          return num;
        }else if(num){
          return num;
        }else{
          num = "- -";
        }
        return num ;
      },
      moneystr(num,moneyType){
        if(Number(num)){
          var regexp=/(?:\.0*|(\.\d+?)0+)$/;
          num = Number(num).toFixed(2).replace(regexp,'$1');
          var arr = num.split('.');
          var tmp = arr[0].replace(/\B(?=(\d{3})+$)/g, ',').replace(/,$/g, '');
          if(arr[1] != undefined){
            num = tmp + '.' + arr[1];
          }else{
            num = tmp
          }
        }
        if(num && moneyType){
          return num+"万"+moneyType;
        }else if(num == 0){
          return num;
        }else if(num){
          return num;
        }else{
          num = "- -";
        }
        return num ;
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
     margin-top:12px;
    margin-bottom:12px;
    font-size: 14px;
    color: #333;
}

.moreNoticeCss {
    font-family: "PingFangSC-Regular", "PingFang SC";
    font-weight: 400;
    font-style: normal;
    font-size: 12px;
    color: #999999;
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
.wordWrap{
    word-break: break-all;
    word-wrap: break-word;
}
</style>
