<template>
  <div class="companyProfile" v-loading="listLoading" element-loading-text="给我一点时间">
    <!-- 公司简介 -->
    <div class="companey">
      <span id="briefIntroduction"></span>
      <div class="briefIntroduction">
        <p v-if="companyProfileList&&companyProfileList.companyName" style="color: #333333; font-weight: bold;font-size: 14px;">
          {{companyProfileList.companyName}}
        </p>
        <ul style="display:flex;margin-bottom: 0;">
          <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">公司简称</span>
            <div v-if="companyProfileList&&companyProfileList.companyShortName" style="color: #333333;float:left;display:inline-block;width: 60%;margin-left: 27px;">{{companyProfileList.companyShortName}}</div>
            <div v-else style="color: #333333;float:left;display:inline-block;width: 60%;margin-left: 27px;">- -</div>
          </li>
          <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">拟上市板块</span>
            <div v-if="companyProfileList&&companyProfileList.plateText"  style="color: #333333;float:left;display:inline-block;width: 55%;margin-left: 13px;">{{companyProfileList.plateText}}</div>
            <div v-else  style="color: #333333;float:left;display:inline-block;width: 55%;margin-left: 13px;">- -</div>
          </li>
          <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">所属证监局</span>
             <div v-if="companyProfileList&&companyProfileList.securitiesRegulatoryText"  style="color: #333333;float:left;display:inline-block;width: 53%;margin-left: 27px;" :title="companyProfileList.securitiesRegulatoryText.length>24 ? companyProfileList.securitiesRegulatoryText:''">{{getContentHy1(companyProfileList.securitiesRegulatoryText)}}</div>
            <div v-else  style="color: #333333;float:left;display:inline-block;width: 53%;margin-left: 27px;">- -</div>
          </li>
           <li class="clear" style="margin-bottom:10px;position:relative" >
            <span class="l">注册地址</span>
            <div style="color: #333333;float:left;display:inline-block;width: 70%;margin-left: 27px;" v-if="companyProfileList&&(companyProfileList.registerCountryText || companyProfileList.registerProvienceText || companyProfileList.registerCityText || companyProfileList.registerAreaText)" >{{addressFilter((companyProfileList.registerCountryText == null ? "" : companyProfileList.registerCountryText) + (companyProfileList.registerProvienceText == null ? "" : companyProfileList.registerProvienceText) + (companyProfileList.registerCityText == null ? "" : companyProfileList.registerCityText) + (companyProfileList.registerAreaText == null ? "" : companyProfileList.registerAreaText))}}</div>
            <div style="color: #333333;float:left;display:inline-block;width: 70%;margin-left: 27px;" v-else >- -</div>
          </li>
        </ul>
        <div>
          <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">法定代表人</span>
            <div  style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 14px;" v-if="companyProfileList&&companyProfileList.corporateRepresentative" >{{companyProfileList.corporateRepresentative}}</div>
            <div style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 14px;"  v-else >- -</div>
          </li>
          <li class="clear" style="position:relative;margin-top:0%;margin-bottom:16px">
            <span class="l" >主营业务</span>
            <div  style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 27px;" v-if="companyProfileList&&companyProfileList.mainBusiness">{{companyProfileList.mainBusiness}}</div>
            <div  style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 27px;" v-else >- -</div>
          </li>
        </div>
      </div>
      <!-- 登录其他资本市场 -->
      <div class="others" v-if="otherMarketInfoList&&otherMarketInfoList.length>0">
        <p style="color:black">登陆其他资本市场</p>
        <ul v-for="item in otherMarketInfoList">
          <li style=" width: 30%;position:relative">
            <span>资本市场</span>&nbsp;&nbsp;
            <div v-if="item.marketTypeText" style="color:black;margin-left: 25%;margin-top: -17.5px;">{{item.marketTypeText}}</div>
            <div v-else style="color:black;margin-left: 25%;margin-top: -17.5px;">- -</div>
          </li>
          <li style=" width: 20%;">
            <span>公司代码</span>&nbsp;&nbsp;
            <span v-if="item.companyCode" style="color:black">{{item.companyCode}}</span>
            <span v-else style="color:black">- -</span>
          </li>
          <li style=" width: 25%;">
            <span>上市日/挂牌日</span>&nbsp;&nbsp;
            <span v-if="item.listTime" style="color:black">{{item.listTime}}</span>
            <span v-else style="color:black">- -</span>
          </li>
          <li style=" width: 25%;">
            <span>退市日/摘牌日</span>&nbsp;&nbsp;
            <span v-if="item.delistTime" style="color:black">{{item.delistTime }}</span>
            <span v-else style="color:black">- -</span>
          </li>
        </ul>
      </div>
    </div>
    <!-- 中介机构 -->
    <div class="IntermediaryInstitutions">
      <div v-if="IntermediaryList&&IntermediaryList.length>0" class="title">
          <span class="littleRectangle"></span>
          <span class="titleText" id="intermediaryInstitutions">中介机构</span>
      </div>
      <IntermediaryInstitutions v-if="IntermediaryList&&IntermediaryList.length>0" :intermediaryOrgList="IntermediaryList"></IntermediaryInstitutions>
    </div>
    <!-- 已经到底了 -->
    <div style="text-align: center;
        font-size: 14px;
        color: rgb(136, 136, 136);
        margin-top: 32px;
        padding-top: 37px;
        border-top: 1px solid #ebeef5">
        已经到底了
    </div>
  </div>
</template>
<script>


import $ from "jquery";
// 中介机构数据
import { getIntermediaryOrgDataList } from '@/api/ipoCase/companyProfile'
import { getCoachBasicInfo } from "@/api/tutoringCase/companyProfile";
// 导入中介机构
import IntermediaryInstitutions from "@/views/tutoringCase/companyProfile/components/IntermediaryInstitutions"
export default {
  name: "companyProfile",
  components: {
    IntermediaryInstitutions
  },

  data() {
    return {
      companyProfileList:{},
      isLogin: true,
      listLoading: false,
      gqTableList: [],
      incomeCompositionTableList1: [],

      raiseMoneyTableList: [],
      // 接口
      structureLabel: [],
      structureUrl:'',
      // 公司简介
      caseId:this.$store.state.app.caseId,
      //其他资本市场
      otherMarketInfoList:[],//其他登陆市场
      // 中介机构
      allArr:[],
      IntermediaryList:[],
      intermediaryOrgList:[],
      moreList:[],
      mainList:[],
    };
  },
  created() {
     // 日志--------------------功能头
      let param = {
      client_type:'pc',//手机或pc
      recordType:'menu',//跳转页面方式:
      recordModule:'IPO案例',//跳转模块
      recordTab:"ipo案例详情页",//跳转tab
      recordTabChild:null,//跳转子集tab
      popTitle:null//弹窗title
      }
      // this.$store.commit('CREATE_MESSAGE',param)
      // 日志------------------功能尾
    this.getData();
  },
  mounted() {
  },
  methods: {
    addressFilter(address){// 地址过滤. 直辖市不显示省
      address = address.replace(/null/g,"");
      var replaceList = ["北京","天津","上海","重庆",];
      for (let i = 0; i < replaceList.length; i++) {
        var replaceStr = replaceList[i];
        var r = new RegExp(replaceStr,"\g");
        var matchResult = address.match(r);
        if (matchResult && matchResult.length > 1){
          address = address.replace(replaceStr, "");
        }
      }
      return address;
    },
    //中介机构数据处理（只保留辅导机构的数据）
    getAllArr(){
      this.allArr = []
      for(let i = 0;i<this.intermediaryOrgList.length;i++){
        // if(this.intermediaryOrgList[i].intermediaryType=='7'){
          this.allArr.push(this.intermediaryOrgList[i])
          this.IntermediaryList = this.allArr
        // }
      }

      console.log('全部符合要求的辅导机构',this.IntermediaryList)
    },
    getContentHy1(title){
     if(title.length>24){
       return title.substring(0,23) + '...'
     }else{
       return title
     }
   },
   getContentHy(title){
     if(title.length>22){
       return title.substring(0,21) + '...'
     }else{
       return title
     }
   },
    getAdress(title){
     if(title.length>20){
       return title.substring(0,19) + '...'
     }else{
       return title
     }
   },
    openLetterDetail(fileId) {
            let url = window.location.href;
            let token = this.$store.state.app.token
            url = url.substr(0,url.indexOf("ui"));
            url = url + 'ipo/ipoProcess/downloadSplitFile?access_token='+token+
                    '&fileId='+ fileId;
              window.open(url);
      },
      // 处理数据
      dealDataList(arr){
        for(var i = 0;i<arr.length;i++){
          // 定义flag
            var firstYearFlag = false;
            var onePeriodFlag = false;
            var secondYearFlag = false;
            var thirdYearFlag = false;
            var subArr = arr[i].supplierCustomerInfoList;
            // 子循环判断条件
          for(var j = 0;j<subArr.length-1;j++){
            // 如果第一年有不为空
            if(this.isNotEmpty(subArr[j].firstYearAmount) || this.isNotEmpty(subArr[j].firstYearContent) || this.isNotEmpty(subArr[j].firstYearRatio)){
               firstYearFlag = true;
            }
            // 第二年
            if(this.isNotEmpty(subArr[j].onePeriodAmount) || this.isNotEmpty(subArr[j].onePeriodContent) || this.isNotEmpty(subArr[j].onePeriodRatio)){
               onePeriodFlag = true;
            }
            // 第三年
            if(this.isNotEmpty(subArr[j].secondYearAmount) || this.isNotEmpty(subArr[j].secondYearContent) || this.isNotEmpty(subArr[j].secondYearRatio)){
               secondYearFlag = true;
            }
            // 第四年
            if(this.isNotEmpty(subArr[j].thirdYearAmount) || this.isNotEmpty(subArr[j].thirdYearContent) || this.isNotEmpty(subArr[j].thirdYearRatio)){
               thirdYearFlag = true;
            }
          }
           arr[i].firstYearFlag = firstYearFlag;
           arr[i].onePeriodFlag = onePeriodFlag;
           arr[i].secondYearFlag = secondYearFlag;
           arr[i].thirdYearFlag = thirdYearFlag;
        }
      },
    getData() {
      // 动态传id
      const param = {
        id:this.caseId,
      }
      getCoachBasicInfo(param).then(res=>{
        this.companyProfileList = res.data.result
        console.log('基本信息',this.companyProfileList)
        if(res.data.result&&res.data.result.otherMarketInfoList&&res.data.result.otherMarketInfoList.length>0){
          this.otherMarketInfoList = res.data.result.otherMarketInfoList
        }
        if(res.data.result&&res.data.result.intermediaryOrgList&&res.data.result.intermediaryOrgList.length>0){
          this.intermediaryOrgList = res.data.result.intermediaryOrgList
          console.log('所有的中介机构',this.intermediaryOrgList)
          if(this.intermediaryOrgList&&this.intermediaryOrgList.length>0){
            this.getAllArr()
          }
        }
        this.getPosition()
      });
    },
    // 鼠标移入公司名
    mouseOverCompanyName(title){
      if(title.length>20){
        $(".companyNameClass").attr("title",title)
      }else{
        $(".companyNameClass").removeAttr("title",title)
      }
    },
    // 鼠标移入采购内容
    mouseOverContent(title){
      if(title.length>16){
        $(".contentClass").attr("title",title)
      }else{
         $(".contentClass").removeAttr("title",title)
      }
    },
     getCompanyName(title){
      if(title.length>21){
        return title.substring(0,20) + '...'
      }else{
        return title
      }
    },
    getContent(title){
      if(title.length>16){
        return title.substring(0,15) + '...'
      }else{
        return title
      }
    },
    //返回父组件用于锚点定位头
    getPosition() {
      // debugger;
          let titleList = [];
          let briefIntroduction = {
            id: 'briefIntroduction',
            name: '公司概览',
            notes: '',
            important: false,
            tabId: 'tab-first',
            noClick: true
          }
          let intermediaryInstitutions = {
              id: 'intermediaryInstitutions',
              name: '辅导机构',
              notes: '',
              important: false,
              tabId: 'tab-first',
              noClick: true
          }
          if(this.companyProfileList&&this.companyProfileList.companyName){
            briefIntroduction.noClick = false;
          }
          if(this.IntermediaryList&&this.IntermediaryList.length>0){
            intermediaryInstitutions.noClick = false;
          }
          titleList.push(briefIntroduction)
          titleList.push(intermediaryInstitutions)
          this.$emit('headCallBack', titleList);
    },
     //点击图片弹出大图
        imgchange(event) {
          window.open(event.currentTarget.src);
       },
      // 非空判断
    isNotEmpty(param) {
      if (param != null && param !== undefined && param !== '' && param !== 'null' && param !== 'undefined') {
        return true
      } else {
        return false
      }
    },
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
.companyProfile {
  padding: 0px 0px;
  .title {
    border-bottom: 1px solid;
    border-bottom-color: #ebebeb;
    height: 42px;
    line-height: 42px;
    background-color: #fafafa;
    display: flex;
    align-items: center;
    margin-top: 32px;
    margin-bottom: 16px;
    .littleRectangle {
      width: 3px;
      height: 18px;
      background-color: #999999;
      display: inline-block;
      margin-right: 12px;
      margin-top: 0px;
      .titleText {
        font-size: 18px;
        color: #333333;
      }
    }
    .hongkuang {
      display: inline-block;
      height: 22px;
      text-align: center;
      border: 1px solid rgba(255, 176, 173, 1);
      font-family: "MicrosoftYaHei", "Microsoft YaHei";
      font-weight: 400;
      font-style: normal;
      font-size: 12px;
      color: #fe5461;
      line-height: 10px;
      margin-left: 10px;
      padding: 4px;
      border-radius: 2px;
    }
  }
  .companey {
    width: 100%;
    background: #f7f7f7;
    font-family: "Arial Normal", "Arial";
    font-weight: 400;
    font-style: normal;
    font-size: 14px;
    color: #999999;
    text-align: left;
    line-height: normal;
    padding: 5px 10px;
    margin-top: 12px;
    .briefIntroduction {
      // border-bottom: 1px solid #ebebeb;
      ul {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        padding-left: 0;
        li {
          width: 33.3%;
          margin-bottom: 10px;
        }
      }
    }
    .others {
      border-top:1px solid #ebebeb;
      ul {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        padding-left: 0;
      }
    }
    .splitListing{
       border-top:1px solid #ebebeb;
       ul {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        padding-left: 0;
        margin-bottom:0px;
        li{
          margin-bottom:0px
        }
      }
    }
  }
}
//表格样式
.el-table thead tr th {
  background: #f2f2f2;
  color: black;
}
.el-table th > .cell {
  position: relative;
  word-wrap: normal;
  text-overflow: ellipsis;
  vertical-align: middle;
  width: 100%;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  text-align: left;
}
// 主要竞争对手样式
.competitorContent {
  .competitorUl {
    li {
       padding: 10px 18px;
       display: flex;
    }
    li:nth-of-type(odd) {
      background: #f9f9f9;
    } //奇数
    li:nth-of-type(even) {
      background: white;
    } //偶数
  }
}
// 中介机构
.InstitutionsDetail {
  ul {
    padding-left: 0;
    width: 100%;
    .InstitutionsDetailLi {
      padding: 20px;
      margin-left: 1%;
      width: 98%;
      height: 130px;
      background: #f0f0f0;
      .image {
        width: 10%;
      }
      .text {
        width: 80%;
        ul {
          width: 100%;
          margin-top: 10px;
          display: flex;
          flex-wrap: wrap;
          li {
            line-height: 20px;
            margin-right: 30px;
            width: 45%;
          }
        }
      }
    }
  }
}
.InstitutionsDetailLi:hover {
  cursor: pointer;
  box-shadow: 0 0px 28px -5px #ccc;
}
.majorBusinesses{
  color:#333333;
  margin-left: 9.4%;
  margin-top: -18.5px;
  line-height: 20px;
}
.industryCsrc{
    color: #333333;
    margin-left: 40.4%;
    margin-top: -7%;
    line-height: 20px;
}
.companyNature{
    color: #333333;
    margin-left: 27.4%;
    margin-top: -7%;
    line-height: 20px;
}
// 放大镜和放小镜样式
.zoom-in{
    cursor: -moz-zoom-in;
    cursor: -webkit-zoom-in;
    cursor: zoom-in;
}
.zoom-out{
    cursor: -moz-zoom-out;
    cursor: -webkit-zoom-out;
    cursor: zoom-out;
}
</style>
