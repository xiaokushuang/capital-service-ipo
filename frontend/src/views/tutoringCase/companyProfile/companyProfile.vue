<template>
  <div class="companyProfile" v-loading="listLoading" element-loading-text="给我一点时间">
    <!-- 公司简介 -->
    <div class="companey">
      <span id="briefIntroduction"></span>
      <div class="briefIntroduction">
        <p v-if="companyProfileList.companyProfileList&&companyProfileList.companyProfileList.companyZhName" style="color: #333; font-weight: bold;font-size: 14px;">
          {{companyProfileList.companyProfileList.companyZhName}}
        </p>
        <ul style="display:flex;margin-bottom: 0;">
          <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">拟上市板块</span>
            <div v-if="companyProfileList.companyProfileList&&companyProfileList.companyProfileList.ipoPlate"  style="color: #333333;float:left;display:inline-block;width: 55%;margin-left: 13px;">{{companyProfileList.companyProfileList.ipoPlate}}</div>
            <div v-else  style="color: #333333;float:left;display:inline-block;width: 55%;margin-left: 13px;">- -</div>
          </li>
          <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">所属行业(证监会)</span>
            <!-- <div v-if="companyProfileList.companyProfileList&&companyProfileList.companyProfileList.industryCsrc"  style="color: #333333;float:left;display:inline-block;width: 53%;margin-left: 27px;">{{companyProfileList.companyProfileList.industryCsrc}}</div> -->
             <div v-if="companyProfileList.companyProfileList&&companyProfileList.companyProfileList.industryCsrc"  style="color: #333333;float:left;display:inline-block;width: 53%;margin-left: 27px;" :title="companyProfileList.companyProfileList.industryCsrc.length>24 ? companyProfileList.companyProfileList.industryCsrc:''">{{getContentHy1(companyProfileList.companyProfileList.industryCsrc)}}</div>
            <div v-else  style="color: #333333;float:left;display:inline-block;width: 53%;margin-left: 27px;">- -</div>
          </li>
            <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">所属行业(战略新兴)</span>
            <div v-if="companyProfileList.companyProfileList&&companyProfileList.companyProfileList.strageticIndustries "  style="color: #333333;float:left;display:inline-block;width: 50%;margin-left: 27px;" :title="companyProfileList.companyProfileList.strageticIndustries.length>22 ? companyProfileList.companyProfileList.strageticIndustries:''">{{getContentHy(companyProfileList.companyProfileList.strageticIndustries)}}</div>
            <div v-else  style="color: #333333;float:left;display:inline-block;width: 50%;margin-left: 27px;">- -</div>
          </li>
          <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">公司简称</span>
            <div v-if="companyProfileList.companyProfileList&&companyProfileList.companyProfileList.companyName" style="color: #333333;float:left;display:inline-block;width: 60%;margin-left: 27px;">{{companyProfileList.companyProfileList.companyName}}</div>
            <div v-else style="color: #333333;float:left;display:inline-block;width: 60%;margin-left: 27px;">- -</div>
          </li>
          <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">证券代码</span>
            <div  v-if="companyProfileList.companyProfileList&&companyProfileList.companyProfileList.companyCode"  style="color: #333333;float:left;display:inline-block;width: 60%;line-height:19px;margin-left: 27px;">{{companyProfileList.companyProfileList.companyCode}}</div>
            <div v-else  style="color: #333333;float:left;display:inline-block;width: 60%;line-height:19px;margin-left: 27px;">- -</div>
          </li>
          <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">注册资本</span>
            <div v-if="companyProfileList.companyProfileList&&companyProfileList.companyProfileList.registeredAssets" style="color: #333333;float:left;display:inline-block;width: 60%;margin-left: 27px;">{{companyProfileList.companyProfileList.registeredAssets | dataInThRule}}万元</div>
            <div v-else style="color: #333333;float:left;display:inline-block;width: 60%;margin-left: 27px;">- -</div>
          </li>
           <li class="clear" style="margin-bottom:10px;position:relative" >
            <span class="l">注册地址</span>
            <!-- <div style="color: #333333;float:left;display:inline-block;width: 50%;margin-left: 27px;" v-if="companyProfileList.companyProfileList&&(companyProfileList.companyProfileList.addrProv || companyProfileList.companyProfileList.addrCity || companyProfileList.companyProfileList.addrArea)" >{{companyProfileList.companyProfileList.addrProv}}{{companyProfileList.companyProfileList.addrCity}}{{companyProfileList.companyProfileList.addrArea}}</div> -->
            <div style="color: #333333;float:left;display:inline-block;width: 50%;margin-left: 27px;" v-if="companyProfileList.companyProfileList&&(companyProfileList.companyProfileList.addrCountry || companyProfileList.companyProfileList.addrProv || companyProfileList.companyProfileList.addrCity || companyProfileList.companyProfileList.addrArea)" :title="(companyProfileList.companyProfileList.addrCountry + companyProfileList.companyProfileList.addrProv + companyProfileList.companyProfileList.addrCity + companyProfileList.companyProfileList.addrArea ).length>20 ? (companyProfileList.companyProfileList.addrCountry +companyProfileList.companyProfileList.addrProv + companyProfileList.companyProfileList.addrCity + companyProfileList.companyProfileList.addrArea ):''">{{(getAdress(companyProfileList.companyProfileList.addrCountry == null ? "" : companyProfileList.companyProfileList.addrCountry) + getAdress(companyProfileList.companyProfileList.addrProv == null ? "" : companyProfileList.companyProfileList.addrProv) + (companyProfileList.companyProfileList.addrCity == null ? "" : companyProfileList.companyProfileList.addrCity) + (companyProfileList.companyProfileList.addrArea == null ? "" : companyProfileList.companyProfileList.addrArea))}}</div>
            <div style="color: #333333;float:left;display:inline-block;width: 50%;margin-left: 27px;" v-else >- -</div>
          </li>
          <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">企业性质</span>
            <div v-if="companyProfileList.companyProfileList&&companyProfileList.companyProfileList.companyNature"  style="color: #333333;float:left;display:inline-block;width: 60%;margin-left: 27px;">{{companyProfileList.companyProfileList.companyNature}}</div>
            <div v-else  style="color: #333333;float:left;display:inline-block;width: 60%;margin-left: 27px;">- -</div>
          </li>
          <li v-if="companyProfileList.companyProfileList&&companyProfileList.companyProfileList.placingMechanism "  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">配售机制</span>
            <div  style="color: #333333;float:left;display:inline-block;width: 71%;margin-left: 27px;">{{companyProfileList.companyProfileList.placingMechanism }}</div>
            <!-- <div v-else  style="color: #333333;float:left;display:inline-block;width: 71%;margin-left: 27px;">- -</div> -->
          </li>
        </ul>
        <div>

          <li class="clear" style="margin-bottom:10px;position:relative" >
            <span class="l">控股股东</span>
            <div  style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 27px;" v-if="companyProfileList.companyProfileList&&companyProfileList.companyProfileList.controlShareholder">{{companyProfileList.companyProfileList.controlShareholder}}</div>
            <div style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 27px;" v-else>- -</div>
          </li>
          <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">实际控制人</span>
            <div  style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 14px;" v-if="companyProfileList.companyProfileList&&companyProfileList.companyProfileList.actualController" >{{companyProfileList.companyProfileList.actualController}}</div>
            <div style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 14px;"  v-else >- -</div>
          </li>
          <!-- 发行人选择的上市标准 -->
          <li class="clear" v-if="companyProfileList.companyProfileList&&companyProfileList.companyProfileList.issueCondition !=''" style="margin-bottom:10px;position:relative" >
            <span v-if="companyProfileList.companyProfileList.issueCondition" style="display: inline-block;width: 65px;float:left">发行人选择的上市标准</span>
            <div v-if="companyProfileList.companyProfileList&&companyProfileList.companyProfileList.issueCondition ==''" style="color: #333333;margin-left: 9.4%;margin-top: -4.8%;">- -</div>
            <div v-else style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 1.6%;">
              <div v-if="companyProfileList.companyProfileList.issueCondition&&companyProfileList.companyProfileList.issueCondition.indexOf('101')>-1">预计市值不低于人民币<span style="color:red">10亿元</span>，最近两年净利润均为正且累计净利润不低于人民币<span style="color:red">5000万元</span>，或者预计市值不低于人民币<span style="color:red">10亿元</span>，最近一年净利润为正且营业收入不低于人民币<span style="color:red">1亿元</span></div>
              <div v-if="companyProfileList.companyProfileList.issueCondition&&companyProfileList.companyProfileList.issueCondition.indexOf('102')>-1">预计市值不低于人民币<span style="color:red">15亿元</span>，最近一年营业收入不低于人民币<span style="color:red">2亿元</span>，且最近三年累计研发投入占最近三年累计营业收入的比例不低于<span style="color:red">15%</span></div>
              <div v-if="companyProfileList.companyProfileList.issueCondition&&companyProfileList.companyProfileList.issueCondition.indexOf('103')>-1">预计市值不低于人民币<span style="color:red">20亿元</span>，最近一年营业收入不低于人民币<span style="color:red">3亿元</span>，且最近三年经营活动产生的现金流量净额累计不低于人民币<span style="color:red">1亿元</span></div>
              <div v-if="companyProfileList.companyProfileList.issueCondition&&companyProfileList.companyProfileList.issueCondition.indexOf('104')>-1">预计市值不低于人民币<span style="color:red">30亿元</span>，且最近一年营业收入不低于人民币<span style="color:red">3亿元</span></div>
              <div v-if="companyProfileList.companyProfileList.issueCondition&&companyProfileList.companyProfileList.issueCondition.indexOf('105')>-1">预计市值不低于人民币<span style="color:red">40亿元</span>，主要业务或产品需经国家有关部门批准，市场空间大，目前已取得阶段性成果。医药行业企业需至少有一项核心产品获准开展二期临床试验，其他符合科创板定位的企业需具备明显的技术优势并满足相应条件</div>
              <div v-if="companyProfileList.companyProfileList.issueCondition&&companyProfileList.companyProfileList.issueCondition.indexOf('201')>-1">预计市值不低于人民币<span style="color:red">100亿元</span></div>
              <div v-if="companyProfileList.companyProfileList.issueCondition&&companyProfileList.companyProfileList.issueCondition.indexOf('202')>-1">预计市值不低于人民币<span style="color:red">50亿元</span>，且最近一年营业收入不低于人民币<span style="color:red">5亿元</span></div>
              <div v-if="companyProfileList.companyProfileList.issueCondition&&companyProfileList.companyProfileList.issueCondition.indexOf('301')>-1">预计市值不低于人民币<span style="color:red">100亿元</span></div>
              <div v-if="companyProfileList.companyProfileList.issueCondition&&companyProfileList.companyProfileList.issueCondition.indexOf('302')>-1">预计市值不低于人民币<span style="color:red">50亿元</span>，且最近一年营业收入不低于人民币<span style="color:red">5亿元</span></div>
            </div>
          </li>
          <li class="clear" style="position:relative;margin-top:0%;margin-bottom:16px">
            <span class="l" >主营业务</span>
            <div  style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 27px;" v-if="companyProfileList.companyProfileList&&companyProfileList.companyProfileList.majorBusinesses">{{companyProfileList.companyProfileList.majorBusinesses}}</div>
            <div  style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 27px;" v-else >- -</div>
          </li>
        </div>
      </div>
      <!-- 登录其他资本市场 -->
      <div class="others" v-if="this.otherMarketInfoList&&this.otherMarketInfoList.length>0">
        <p style="color:black">登陆其他资本市场</p>
        <ul v-for="item in otherMarketInfoList">
          <li style=" width: 30%;position:relative">
            <span>资本市场</span>&nbsp;&nbsp;
            <div v-if="item.marketType" style="color:black;margin-left: 25%;margin-top: -17.5px;">{{item.marketType}}</div>
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
      <!-- 拆分上市情况 -->
       <div class="splitListing" v-if="spliteList&&spliteList.length>0">
        <p style="color:black;margin-top:16px">拆分上市情况</p>
        <ul v-for="item in spliteList">
          <li style=" width: 33%;position:relative">
            <span>上市公司</span>&nbsp;&nbsp;
            <div v-if="item.companyName" style="color:black;margin-left: 25%;margin-top: -17.5px;">{{item.companyName}}</div>
            <span v-else style="color:black">- -</span>
          </li>
          <li style=" width: 33%;">
            <span>所在市场</span>&nbsp;&nbsp;
            <span v-if="item.splitMarket" style="color:black">{{item.splitMarket}}</span>
            <span v-else style="color:black">- -</span>
          </li>
          <li style=" width: 33%;">
            <span>直接或间接持有发行人股份</span>&nbsp;&nbsp;
            <span v-if="item.shareProportion" style="color:black">{{item.shareProportion}}%</span>
            <span v-else style="color:black">- -</span>
          </li>
        </ul>
        <ul>
          <li style="width: 100%; margin-bottom:10px">
            <span  class="l">拆分详情</span>&nbsp;&nbsp;
            <span v-if="spliteList[0].splitMemo" style="color:black;float:left;margin-left: 18px;width: 80%">{{spliteList[0].splitMemo}}</span>
            <span v-else style="color:black;float:left;margin-left: 18px;width: 80%">- -</span>
          </li>
          <li v-if="spliteList[0].fileList&&spliteList[0].fileList.length>0" style="width: 100%;margin-bottom:10px "
              v-for="(item,index) in spliteList[0].fileList">
           <span v-if="index == 0">相关文件</span>&nbsp;&nbsp;&nbsp;&nbsp;
           <span v-if="index != 0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
           <span v-if="item.splitFileName"  @click="openLetterDetail(item.splitFileId)" style="color:#3399fe;text-decoration:underline;cursor:pointer;">{{item.splitFileName}}</span>
           <span v-else style="color:black">- -</span>
          </li>
          <li v-if="spliteList[0].fileList.length==0" style="width: 100%;margin-bottom:10px ">
            <span>相关文件</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="color:black">- -</span>
          </li>
        </ul>
      </div>
    </div>
    <!-- 中介机构 -->
    <div class="IntermediaryInstitutions">
        <div v-if="dataFlag" class="title">
          <span class="littleRectangle"></span>
          <span class="titleText" id="intermediaryInstitutions">中介机构</span>
      </div>
      <IntermediaryInstitutions v-if="dataFlag"></IntermediaryInstitutions>
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
import { getMarketData } from "@/api/ipoCase/companyProfile";
import { getShareHolderData } from "@/api/ipoCase/companyProfile";
import { getRaiseMoneyTableList } from "@/api/ipoCase/companyProfile";
import { getSupplierCustomerData } from '@/api/ipoCase/tableDemo';
import { getTableData } from '@/api/ipoCase/tableDemo';
// 中介机构数据
import { getIntermediaryOrgDataList } from '@/api/ipoCase/companyProfile'
// 拆分上市情况
import { getSpliteData } from '@/api/ipoCase/companyProfile'
// 最近一次估值
import { getValuationData } from '@/api/ipoCase/companyProfile'
// 导入主营业务收入构成表格
import mainTable from "@/views/tables/mainTable";
// 导入柱形图和饼图
import barOrPieChart from "@/components/Charts/barOrPieChart";
// 导入中介机构
import IntermediaryInstitutions from "@/views/navMenu/companyProfile/components/IntermediaryInstitutions"
export default {
  name: "companyProfile",
  components: {
    mainTable,
    barOrPieChart,
    IntermediaryInstitutions
  },

  data() {
    return {
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
      // 主要竞争者
      // MajorCompetitors: [],
      // 前五名供应商数据
      supplierMainList:[],
      // 前五名客户
      customerMainList:[],
      // 主营业务收入
      mainTableList:{
        onePeriodForIncome:'',
        thirdYearForIncome:'',
        secondYearForIncome:'',
        firstYearForIncome:''
      },
      // 中介机构
      moreList:[],
      mainList:[],
      dataFlag:false,
      // 拆分上市情况
      spliteList:[],
      // 最近一次估值情况
      recentValuation:[],
      recentValuationFlag:false
    };
  },
  props:["companyProfileList"],
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
      // 其他登录市场
      getMarketData(param).then(res=>{
        if(res.data.result&&res.data.result.length>0){
          this.otherMarketInfoList = res.data.result//其他登录市场
        }
          this.getPosition()
      });
      // 拆分上市情况
      getSpliteData(param).then(res=>{
        if(res.data.result&&res.data.result.length>0){
          this.spliteList = res.data.result
        }
          this.getPosition()
      });
      // 最近一次估值情况
      getValuationData(param).then(res=>{
        if(res.data.result&&res.data.result.length>0&&(res.data.result[0].valuationDate || res.data.result[0].valuationMemo || res.data.result[0].valuationEquity || res.data.result[0].valuationType || res.data.result[0].valuationValue)){
          this.recentValuationFlag = true
          this.recentValuation = res.data.result[0]
        }
          this.getPosition()
      });
      // 股权结构图表格
      getShareHolderData(param).then(res=>{
        if(res.data.result&&res.data.result.length>0){
          this.gqTableList = res.data.result
        }
          this.getPosition()
      });
      getRaiseMoneyTableList(param).then(res=>{
        if(res.data.result&&res.data.result.length>0){
          this.raiseMoneyTableList = res.data.result
        }
          this.getPosition()
      });
      // 供应商
      getSupplierCustomerData(param).then(response => {
        if(response.data.result&&response.data.result.supplierMainList&&response.data.result.supplierMainList.length>0){
          this.supplierMainList = response.data.result.supplierMainList
          this.dealDataList(this.supplierMainList);
          this.getPosition()
        }
         if(response.data.result&&response.data.result.customerMainList&&response.data.result.customerMainList.length>0){
           this.customerMainList = response.data.result.customerMainList
           this.dealDataList(this.customerMainList);
        }
           this.getPosition()
      })

      // 主营业务收入构成
      getTableData(param).then(response => {
        if( response.data.result){
          this.mainTableList = response.data.result
        }
          this.getPosition()
      })
      // 中介机构
       getIntermediaryOrgDataList(param).then(response => {
         if((response.data.result&&response.data.result.mainList&&response.data.result.mainList.length>0 ||(response.data.result&&response.data.result.moreList&&response.data.result.moreList.length>0))){
            this.dataFlag = true
          }
              this.getPosition()
      })


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
              name: '中介机构',
              notes: '',
              important: false,
              tabId: 'tab-first',
              noClick: true
          }
          if(this.companyProfileList.companyProfileList){
             briefIntroduction.noClick = false;
          }

          if(this.dataFlag){
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
    font-size: 13px;
    color: #999999;
    text-align: left;
    line-height: normal;
    padding: 5px 10px;
    margin-top: 32px;
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
