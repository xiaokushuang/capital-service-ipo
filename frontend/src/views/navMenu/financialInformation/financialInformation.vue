<template>
    <div class="financialInformation">
        <!-- 财务数据 -->
        <div class="financialData">
          <div style="position: absolute;right: 0px;top: 0px" v-if="finCompanyList.length>1">
            <el-popover
              placement="right"
              width="200"
              trigger="click">
                <div v-for="(item,index) in finCompanyList" style="margin-bottom: 10px;cursor: pointer;" class="finCompany" @click="openFinancial(item)">
                  <span v-if="item.companyCode.indexOf('A') == -1">{{item.companyCode}}</span><span>{{item.companyName}}</span>
                </div>
              <span slot="reference" style="color: rgb(20, 188, 245);font-size: 14px;cursor: pointer">深度分析财务情况>></span>
            </el-popover>
          <div style="position: absolute;right: 0px;top: 0px;color: rgb(20, 188, 245);font-size: 14px;cursor: pointer"  v-if="finCompanyList.length == 1" @click="openFinancial(finCompanyList[0])">
            深度分析财务情况>>
          </div>
          </div>
            <!-- 财务总体情况 -->
            <div class="allAssets">
                <div style="margin-top:36px" v-if="allAssetsTableTitle!=null&&allAssetsTableTitle.firstYearDate " class="title">
                    <span class="littleRectangle"></span>
                    <span class="titleText" id="allAssets">财务总体情况</span>
                </div>
                <span v-if="allAssetsTableTitle!=null&&allAssetsTableTitle.firstYearDate "  class="clear">
                    <span style="float: right;font-size: 12px;color: #666666;display:inline-block;margin-bottom:12px">
                       单位：万元
                    </span>
                </span>
                <div class="allAssetsTable" style="margin-top: -10px;">
                    <allAssetsTable v-if="this.allAssetsTableTitle!=null&&this.allAssetsTableTitle.firstYearDate "  :allAssetsTableList="[this.allAssetsTableTitle,this.allAssetsTableContent]"></allAssetsTable>
                </div>
            </div>
                <!-- 资产与负债情况 -->
            <div class="assetsDebts">
                <div style="margin-top:24px;margin-bottom:12px">
                    <div v-if="this.assetsOrDeptTableTitle!=null&&this.assetsOrDeptTableTitle.firstYearDate " class="title">
                        <span class="littleRectangle"></span>
                        <span class="titleText" id="assetsDebts">资产与负债情况</span>
                    </div>
                    <!-- <span  v-if="this.assetsOrDeptTableTitle!=null&&this.assetsOrDeptTableTitle.firstYearDate " style="font-size:16px;color:#333">资产与负债情况</span> -->
                    <span v-if="this.assetsOrDeptTableTitle!=null&&this.assetsOrDeptTableTitle.firstYearDate "  class="clear">
                        <span style="float: right;font-size: 12px;color: #666666;">
                        单位：万元
                        </span>
                    </span>
                </div>
                <div class="assetsTable1">
                    <assetsOrDebtTable v-if="this.assetsOrDeptTableTitle!=null&&this.assetsOrDeptTableTitle.firstYearDate "  :assetsOrDeptTableList="[this.assetsOrDeptTableTitle,this.ipoAssetItemList,this.ipoDebtItemList,this.ipoEquityItemList]"></assetsOrDebtTable>
                </div>
            </div>
            <!-- 收入与利润情况 -->
            <div class="income">
                <div style="margin-top:24px;margin-bottom:12px">
                     <div v-if="this.incomeTableTitle!=null&&this.incomeTableTitle.firstYearDate " class="title">
                        <span class="littleRectangle"></span>
                        <span class="titleText" id="income">收入与利润情况</span>
                    </div>
                    <!-- <span v-if="this.incomeTableTitle!=null&&this.incomeTableTitle.firstYearDate " style="font-size:16px;color:#333">收入与利润情况</span> -->
                    <span v-if="this.incomeTableTitle!=null&&this.incomeTableTitle.firstYearDate " class="clear">
                        <span style="float: right;font-size: 12px;color: #666666;">
                        单位：万元
                        </span>
                    </span>
                </div>
                <div class="assetsTable2">
                    <incomeTable v-if="this.incomeTableTitle!=null&&this.incomeTableTitle.firstYearDate "  :incomeTableList="[this.incomeTableTitle,this.ipoProfitItemList,this.ipoCostItemList,this.ipoReturnOverList]"></incomeTable>
                </div>
            </div>
            <!-- 主要财务指标 -->
            <div class="keyFinancialIndicators">
                 <div v-if="MainIndexTableTitle!=null&&MainIndexTableTitle.firstYearDate "  class="title">
                    <span class="littleRectangle"></span>
                    <span class="titleText" id="keyFinancialIndicators">主要财务指标</span>
                 </div>
                 <div class="allAssetsTable" style="">
                    <keyFinancialIndicatorTable v-if="MainIndexTableTitle!=null&&MainIndexTableTitle.firstYearDate "  :MainIndexTableList={title:this.MainIndexTableTitle,content:this.MainIndexTableContent}></keyFinancialIndicatorTable>
                </div>
            </div>
        </div>
        <!-- 已经到底了 -->
    <div style="text-align: center;
        font-size: 14px;
        color: rgb(136, 136, 136);
        margin-top: 32px;
        padding-top: 37px;
        border-top:1px solid #ebeef5">
        已经到底了
    </div>
    </div>
</template>
<script>
import assetsOrDebtTable from "@/views/tables/assetsOrDebtTable";
import allAssetsTable from "@/views/tables/allAssetsTable";
import incomeTable from "@/views/tables/incomeTable";
import compareTable from "@/views/tables/compareTable";
import keyFinancialIndicatorTable from "@/views/tables/keyFinancialIndicatorTable";
import { openFinancialReVision } from "@/api/ipoCase/companyProfile";
import { queryCompanyForFin } from "@/api/ipoCase/companyProfile";

import echarts from 'echarts'
// 财务总体情况
 import { getSelectFinanceOverList } from '@/api/ipoCase/tableDemo'
//  资产负债
 import { getAssetsOrDebtData } from '@/api/ipoCase/tableDemo'
//  收入与利润
import { getSelectFinanceProfitList } from '@/api/ipoCase/tableDemo'
//  主要财务指标
import { getSelectMainIndexList } from '@/api/ipoCase/tableDemo'
export default {
    name:'financialInformation',
    data(){
        return {
            finCompanyList:'',
            tempOpen:'',
            caseId:this.$store.state.app.caseId,
            maoChartTableData:[],
            // 表格data
            year1:'',
            year2:'',
            year3:'',
            industryCompareRateDetailList:[],
            // 折线图data
            zxChart:null,
            zxChartY:[],
            zxChartX:[],
            // 财务总体情况
             allAssetsTableTitle: {
                forthYearDate:'',
                thirdYearDate:'',
                secondYearDate:'',
                firstYearDate:''
            },
            allAssetsTableContent: null,
            // 主要财务指标
             MainIndexTableTitle: {
                forthYearDate:'',
                thirdYearDate:'',
                secondYearDate:'',
                firstYearDate:''
            },
            MainIndexTableContent: null,
            // 资产负债
             assetsOrDeptTableTitle: {
                forthYearDate:'',
                thirdYearDate:'',
                secondYearDate:'',
                firstYearDate:''
            },
                ipoAssetItemList:[],//资产类项目列表
                ipoDebtItemList:[],//负债类项目列表
                ipoEquityItemList:[],//权益类项目列表
           //收入与利润情况
            incomeTableTitle: {
                forthYearDate:'',
                thirdYearDate:'',
                secondYearDate:'',
                firstYearDate:''
                },
            ipoProfitItemList :[],//收益类项目列表
            ipoCostItemList :[],//成本类项目列表
            ipoReturnOverList :[],//利润类项目列表
        }
    },
    components: {
        allAssetsTable,
        assetsOrDebtTable,
        incomeTable,
        compareTable,
        keyFinancialIndicatorTable
    },
    created(){
         // 日志--------------------功能头
      let param = {
      client_type:'pc',//手机或pc
      recordType:'menu',//跳转页面方式:
      recordModule:'IPO案例',//跳转模块
      recordTab:"ipo案例详情页",//跳转tab
      recordTabChild:null,//跳转子集tab
      popTitle:null//弹窗title
      }
    //   this.$store.commit('CREATE_MESSAGE',param)
      // 日志------------------功能尾
        this.initTableData()
        this.queryCompanyForFin()
    },
    mounted () {
    },
    methods:{
        queryCompanyForFin(){
          const param = {
            id:this.caseId,
          }
          queryCompanyForFin(param).then(res=>{
            if (res.data.result){
              this.finCompanyList = res.data.result
            }
          })
        },
        //打开财务分析
        openFinancial(val){
          //重新登录易董地址
          let ajaxSettings = {
            access_token : this.$route.query.access_token,
            typeCode  : 2,//回购：‘2’其他参照 capital-enterprise  index.jsp
            searchCompanyCode  : val.companyCode,//被查询的公司code
            searchCompanyName  : val.companyName,//被查询的公司简称
            searchReportType :val.finYear+val.finType,//例：2018001
            industry  : "1",
            showAll :"",
            companyType  : "1"
          }
          //转换成json
          this.tempOpen = window.open();
          openFinancialReVision(ajaxSettings).then(res=>{
            if(res.data.result && res.data.result.message){
              let parame = encodeURI(encodeURI("access_token="+this.$route.query.access_token+"&tenantInfo=" + this.$route.query.tenant_info));
              console.log(parame);
              let newLocation = res.data.result.serviceBaseUrl + "ui/finance-report/financialReVision/dupontContainer?" + parame;
              this.tempOpen.location = newLocation;
            }else{
              this.tempOpen.close();
            }
          });
        },
        // 锚点定位
        getPosition(){
           //返回父组件用于锚点定位头
                let titleList = [];
                let allAssets = {
                    id: 'allAssets',
                    name: '财务总体情况',
                    notes: '',
                    important: false,
                    tabId: 'tab-second',
                    noClick: true
                }
                let assetsDebts = {
                    id: 'assetsDebts',
                    name: '资产与负债情况',
                    notes: '',
                    important: false,
                    tabId: 'tab-second',
                    noClick: true
                }
                let income = {
                    id: 'income',
                    name: '收入与利润情况',
                    notes: '',
                    important: false,
                    tabId: 'tab-second',
                    noClick: true
                }
                let keyFinancialIndicators = {
                    id: 'keyFinancialIndicators',
                    name: '主要财务指标',
                    notes: '',
                    important: false,
                    tabId: 'tab-second',
                    noClick: true
                }


                 if(this.allAssetsTableTitle!=null&&this.allAssetsTableTitle.firstYearDate){
                    allAssets.noClick = false;
                 }
                 if(this.assetsOrDeptTableTitle!=null&&this.assetsOrDeptTableTitle.firstYearDate){
                    assetsDebts.noClick = false;
                 }
                 if(this.incomeTableTitle!=null&&this.incomeTableTitle.firstYearDate){
                    income.noClick = false;
                 }
                 if(this.MainIndexTableTitle!=null&&this.MainIndexTableTitle.firstYearDate){
                    keyFinancialIndicators.noClick = false;
                 }
                titleList.push(allAssets)
                titleList.push(assetsDebts)
                titleList.push(income)
                titleList.push(keyFinancialIndicators)

                this.$emit('headCallBack', titleList);
            //返回父组件用于锚点定位尾
        },
        // 初始化数据
        initTableData() {
            // 动态传id
            const param = {
             id:this.caseId
            }
            // 资产总体
             getSelectFinanceOverList(param).then(res => {
                 if(res.data.result&&res.data.result.dateList!=null){
                    this.allAssetsTableTitle = res.data.result.dateList
                 }
                 if(res.data.result&&res.data.result.ipoFinanceOverList!=null&&res.data.result.ipoFinanceOverList.length>0){
                     this.allAssetsTableContent = res.data.result.ipoFinanceOverList
                 this.getPosition()
                 }
            })
            // 资产负债
            getAssetsOrDebtData(param).then(res => {
                    if(res.data.result){
                        this.assetsOrDeptTableTitle = res.data.result.dateList
                        this.getPosition()
                    }
                    if(res.data.result&&res.data.result.ipoAssetItemList!=null&&res.data.result.ipoAssetItemList.length>0){
                        this.ipoAssetItemList = res.data.result.ipoAssetItemList//资产类项目列表
                    }
                    if(res.data.result&&res.data.result.ipoDebtItemList!=null&&res.data.result.ipoDebtItemList.length>0){
                        this.ipoDebtItemList = res.data.result.ipoDebtItemList//负债类项目列表
                    }
                    if(res.data.result&&res.data.result.ipoEquityItemList!=null&&res.data.result.ipoEquityItemList.length>0){
                        this.ipoEquityItemList = res.data.result.ipoEquityItemList//权益类项目列表
                    }

              })
            //   收入与利润
            getSelectFinanceProfitList(param).then(res => {
                    if(res.data.result.dateList){
                        this.incomeTableTitle = res.data.result.dateList
                        this.getPosition()
                    }
                      if(res.data.result&&res.data.result.ipoProfitItemList!=null&&res.data.result.ipoProfitItemList.length>0){
                          this.ipoProfitItemList = res.data.result.ipoProfitItemList//收益类项目列表
                      }
                      if(res.data.result&&res.data.result.ipoCostItemList!=null&&res.data.result.ipoCostItemList.length>0){
                          this.ipoCostItemList = res.data.result.ipoCostItemList//成本类项目列表
                      }
                      if(res.data.result&&res.data.result.ipoReturnOverList!=null&&res.data.result.ipoReturnOverList.length>0){
                          this.ipoReturnOverList = res.data.result.ipoReturnOverList//利润类项目列表
                      }

             })
              //   主要财务指标
            getSelectMainIndexList(param).then(res => {
                 if(res.data.result&&res.data.result.dateList!=null){
                    this.MainIndexTableTitle = res.data.result.dateList
                 }
                 if(res.data.result&&res.data.result.ipoMainIndexList!=null&&res.data.result.ipoMainIndexList.length>0){
                     this.MainIndexTableContent = res.data.result.ipoMainIndexList
                     this.getPosition()
                 }
            })
        },
        // 非空判断
        isNotEmpty(param) {
            // debugger
            if (param != null && param !== undefined && param !== '' && param !== 'null' && param !== 'undefined') {
                return true
            } else {
                return false
            }
        }
    },
}

</script>
<style scoped lang="scss">
  .finCompany:hover{
    color: rgb(20, 188, 245);
  }
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
    .title {
    border-bottom: 1px solid;
    border-bottom-color: #ebebeb;
    height: 42px;
    line-height: 42px;
    background-color: #fafafa;
    display: flex;
    align-items: center;
    margin-top: 30px;
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
    }
</style>


