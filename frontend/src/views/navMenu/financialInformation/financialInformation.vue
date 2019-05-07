<template>
    <div class="financialInformation">
        <!-- 财务数据 -->
        <div class="financialData">
            <!-- 财务总体情况 -->
            <div class="allAssets">
                <div style="margin-top:61px" v-if="allAssetsTableTitle!=null&&allAssetsTableTitle.firstYearDate " class="title">
                    <span class="littleRectangle"></span>
                    <span class="titleText" id="allAssets">财务总体情况</span>
                </div>
                <!-- <span v-if="allAssetsTableTitle!=null&&allAssetsTableTitle.firstYearDate " style="font-size:16px;color:#333">财务总体情况</span> -->
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
                 <div class="title">
                    <span class="littleRectangle"></span>
                    <span class="titleText" id="keyFinancialIndicators">主要财务指标</span>
                 </div>

            </div>
        </div>
    </div>
</template>
<script>
import assetsOrDebtTable from "@/views/tables/assetsOrDebtTable";
import allAssetsTable from "@/views/tables/allAssetsTable";
import incomeTable from "@/views/tables/incomeTable";
import compareTable from "@/views/tables/compareTable";
import echarts from 'echarts'
// 财务总体情况
 import { getSelectFinanceOverList } from '@/api/ipoCase/tableDemo'
//  资产负债
 import { getAssetsOrDebtData } from '@/api/ipoCase/tableDemo'
//  收入与利润
import { getSelectFinanceProfitList } from '@/api/ipoCase/tableDemo'
export default {
    name:'financialInformation',
    data(){
        return {
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
                thirdYearValue:'',
                secondYearValue:'',
                firstYearValue:''
            },
            allAssetsTableContent: null,
            // 资产负债
             assetsOrDeptTableTitle: {
                forthYearDate:'',
                thirdYearValue:'',
                secondYearValue:'',
                firstYearValue:''
            },
                ipoAssetItemList:[],//资产类项目列表
                ipoDebtItemList:[],//负债类项目列表
                ipoEquityItemList:[],//权益类项目列表
           //收入与利润情况
            incomeTableTitle: {
                forthYearDate:'',
                thirdYearValue:'',
                secondYearValue:'',
                firstYearValue:''
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
        compareTable
    },
    created(){
        this.initTableData()
    },
    mounted () {
    },
    methods:{
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
                 if(this.allAssetsTableTitle!=null&&this.allAssetsTableTitle.firstYearDate){
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


