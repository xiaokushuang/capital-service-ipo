<template>
    <div class="financialInformation">
        <!-- 主要财务数据 -->
        <div class="financialData">
            <div v-if="this.allAssetsTableTitle.firstYearDate ||  this.assetsOrDeptTableTitle.firstYearDate || this.incomeTableTitle.firstYearDate" class="title">
                <span class="littleRectangle"></span>
                <span class="titleText" id="financialStatementData">主要财务数据</span>
            </div>
            <div class="allAssets">
                <span style="font-size:16px;color:#333">财务总体情况</span>
                <span class="clear">
                    <span style="float: right;font-size: 12px;color: #666666;">
                       单位：万元
                    </span>
                </span>
                <div class="allAssetsTable" style="margin-top: -10px;">
                    <allAssetsTable v-if="this.allAssetsTableTitle.firstYearDate "  :allAssetsTableList="[this.allAssetsTableTitle,this.allAssetsTableContent]"></allAssetsTable>
                </div>
            </div>
            <div class="assets">
                <!-- <p>资产与负债情况</p> -->
                <div style="margin-top:24px;margin-bottom:12px">
                    <span style="font-size:16px;color:#333">资产与负债情况</span>
                    <span class="clear">
                        <span style="float: right;font-size: 12px;color: #666666;">
                        单位：万元
                        </span>
                    </span>
                </div>
                <div class="assetsTable1">
                    <assetsOrDebtTable v-if="this.assetsOrDeptTableTitle.firstYearDate "  :assetsOrDeptTableList="[this.assetsOrDeptTableTitle,this.ipoAssetItemList,this.ipoDebtItemList,this.ipoEquityItemList]"></assetsOrDebtTable>
                </div>
            </div>
            <div class="income">
                <!-- <p>收入与利润情况</p>  -->
                <div style="margin-top:24px;margin-bottom:12px">
                    <span style="font-size:16px;color:#333">收入与利润情况</span>
                    <span class="clear">
                        <span style="float: right;font-size: 12px;color: #666666;">
                        单位：万元
                        </span>
                    </span>
                </div>
                <div class="assetsTable2">
                    <incomeTable v-if="this.incomeTableTitle.firstYearDate "  :incomeTableList="[this.incomeTableTitle,this.ipoProfitItemList,this.ipoCostItemList,this.ipoReturnOverList]"></incomeTable>
                </div>
            </div>
        </div>
        <!-- 招股书列示同行业上市公司综合毛利率对比 -->
        <div class="compare">
            <div v-if="maoChartTableData&&maoChartTableData.length>0" class="title" >
                <span class="littleRectangle"></span>
                <span class="titleText" id="comparison">招股书列示同行业上市公司综合毛利率对比</span>
            </div>
            <div class="chartTable" v-for="item,index in maoChartTableData" :key="item.id">
                <p style="font-family:'PingFang-SC-Regular', 'PingFang SC';font-weight:400;color:#666666;">{{item.remark}}</p>
                <div class="zxChart" style="height:300px;width:100%">
                    <zxChart ref="zxChart" :zxIndex = "index"></zxChart>
                </div>
                <div class="table">
                    <!-- <compareTable></compareTable> -->
                    <!-- 其他公司对比 -->
                     <el-table class="otherCompany" stripe :data="item.industryCompareRateDetailList.slice(0,-2)" border style="width: 100%;margin-top: 20px">
                        <el-table-column prop="companyName" align="left" class-name="table_cell" label="企业名称" width="156" ></el-table-column>
                        <el-table-column align="center" :label="item.thirdYear" header-align="center">
                             <template slot-scope="scope">
                                <span v-if="scope.row.thirdYearRate"> {{scope.row.thirdYearRate | dataInThRule}}%</span>
                                <span v-else> - - </span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.secondYear" header-align="center" >
                             <template slot-scope="scope">
                                <span v-if="scope.row.secondYearRate"> {{scope.row.secondYearRate | dataInThRule}}%</span>
                                <span v-else> - - </span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.firstYear" header-align="center" >
                             <template slot-scope="scope">
                                <span v-if="scope.row.firstYearRate"> {{scope.row.firstYearRate | dataInThRule}}%</span>
                                <span v-else> - - </span>
                            </template>
                        </el-table-column>
                    </el-table>
                    <!-- 平均值 -->
                     <el-table class="average averageOrMyself" stripe :data="item.industryCompareRateDetailList.slice(-2,-1)" border style="width: 100%;margin-top: 20px">
                        <el-table-column align="left" class-name="table_cell" label="企业名称" width="156" >
                             <template slot-scope="scope">
                                <span style="font-weight:bold;color:black">{{isNotEmpty(scope.row.companyName) ? scope.row.companyName: '- -'}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.thirdYear" header-align="center">
                             <template slot-scope="scope">
                                <span style="font-weight:bold;color:black" v-if="scope.row.thirdYearRate"> {{scope.row.thirdYearRate | dataInThRule}}%</span>
                                <span style="font-weight:bold;color:black" v-else> - - </span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.secondYear" header-align="center" >
                             <template slot-scope="scope">
                                <span style="font-weight:bold;color:black" v-if="scope.row.secondYearRate"> {{scope.row.secondYearRate | dataInThRule}}%</span>
                                <span style="font-weight:bold;color:black" v-else> - - </span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.firstYear" header-align="center" >
                             <template slot-scope="scope">
                                <span style="font-weight:bold;color:black" v-if="scope.row.firstYearRate"> {{scope.row.firstYearRate | dataInThRule}}%</span>
                                <span style="font-weight:bold;color:black" v-else> - - </span>
                            </template>
                        </el-table-column>
                    </el-table>
                    <!-- 自己公司 -->
                      <el-table class="averageOrMyself myself" stripe :data="item.industryCompareRateDetailList.slice(-1)" border style="width: 100%;margin-top: 20px">
                        <el-table-column prop="companyName" align="left" class-name="table_cell" label="企业名称" width="156" ></el-table-column>
                        <el-table-column align="center" :label="item.thirdYear" header-align="center">
                             <template slot-scope="scope">
                                <span v-if="scope.row.thirdYearRate"> {{scope.row.thirdYearRate | dataInThRule}}%</span>
                                <span v-else> - - </span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.secondYear" header-align="center" >
                             <template slot-scope="scope">
                                <span v-if="scope.row.secondYearRate"> {{scope.row.secondYearRate | dataInThRule}}%</span>
                                <span v-else> - - </span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.firstYear" header-align="center" >
                             <template slot-scope="scope">
                                <span v-if="scope.row.firstYearRate"> {{scope.row.firstYearRate | dataInThRule}}%</span>
                                <span v-else> - - </span>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import assetsOrDebtTable from "@/views/tables/assetsOrDebtTable";
import allAssetsTable from "@/views/tables/allAssetsTable";
import incomeTable from "@/views/tables/incomeTable";
import zxChart from "@/components/Charts/zxChart";
import compareTable from "@/views/tables/compareTable";
import echarts from 'echarts'
// 财务总体情况
 import { getSelectFinanceOverList } from '@/api/ipoCase/tableDemo'
//  资产负债
 import { getAssetsOrDebtData } from '@/api/ipoCase/tableDemo'
//  收入与利润
import { getSelectFinanceProfitList } from '@/api/ipoCase/tableDemo'
import { getMaoChartTableData } from '@/api/ipoCase/tableDemo'
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
        zxChart,
        compareTable
    },
    created(){
        // this.getPosition()
        this.initTableData()
    },
    mounted () {
    },
    methods:{
        // 锚点定位
        getPosition(){
           //返回父组件用于锚点定位头
                let titleList = [];
                let financialStatementData = {
                    id: 'financialStatementData',
                    name: '主要财务数据',
                    notes: '',
                    important: false,
                    tabId: 'tab-second',
                    noClick: true
                }
                let comparison = {
                    id: 'comparison',
                    name: '同行业毛利率对比',
                    notes: '',
                    important: false,
                    tabId: 'tab-second',
                    noClick: true
                } 
                 if(this.allAssetsTableTitle.firstYearDate ||  this.assetsOrDeptTableTitle.firstYearDate || this.incomeTableTitle.firstYearDate){
                    financialStatementData.noClick = false;
                    }
                 if(this.maoChartTableData&&this.maoChartTableData.length>0){
                    comparison.noClick = false;
                 }             
                titleList.push(financialStatementData)
                titleList.push(comparison)
                this.$emit('headCallBack', titleList);
            //返回父组件用于锚点定位尾
        },
        // 初始化数据
        initTableData() {
            // 动态传id
            const param = {
             id:this.caseId
            }
            getMaoChartTableData(param).then(res => {
                if(res.data.result&&res.data.result.length>0){
                    this.maoChartTableData = res.data.result                 
                }
                this.getPosition()
            }) 
            // 资产总体
             getSelectFinanceOverList(param).then(res => {
                 console.log('zong',res.data.result)
                     this.allAssetsTableTitle = res.data.result.dateList
                 if(res.data.result&&res.data.result.ipoFinanceOverList.length>0){
                     this.allAssetsTableContent = res.data.result.ipoFinanceOverList 
                 }
                 this.getPosition()
            })
            // 资产负债
            getAssetsOrDebtData(param).then(res => {
                
                    console.log('fuzai',res.data.result)
                    this.assetsOrDeptTableTitle = res.data.result.dateList
                    if(res.data.result&&res.data.result.ipoAssetItemList.length>0){
                        this.ipoAssetItemList = res.data.result.ipoAssetItemList//资产类项目列表
                    }
                    if(res.data.result&&res.data.result.ipoDebtItemList.length>0){
                        this.ipoDebtItemList = res.data.result.ipoDebtItemList//负债类项目列表
                    }
                    if(res.data.result&&res.data.result.ipoEquityItemList.length>0){
                        this.ipoEquityItemList = res.data.result.ipoEquityItemList//权益类项目列表
                    }
                    this.getPosition()
                
              })
            //   收入与利润
            getSelectFinanceProfitList(param).then(res => {
                    console.log('shouru',res.data.result)
                    this.incomeTableTitle = res.data.result.dateList
                      if(res.data.result&&res.data.result.ipoProfitItemList.length>0){
                          this.ipoProfitItemList = res.data.result.ipoProfitItemList//收益类项目列表
                      }
                      if(res.data.result&&res.data.result.ipoCostItemList.length>0){
                          this.ipoCostItemList = res.data.result.ipoCostItemList//成本类项目列表
                      }
                      if(res.data.result&&res.data.result.ipoReturnOverList.length>0){
                          this.ipoReturnOverList = res.data.result.ipoReturnOverList//利润类项目列表
                      }
                    this.getPosition()
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


