<template>
    <div class="financialInformation">
        <!-- 主要财务数据 -->
        <div class="financialData">
            <div class="title">
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
                    <allAssetsTable></allAssetsTable>
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
                    <assetsOrDebtTable></assetsOrDebtTable>
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
                    <incomeTable></incomeTable>
                </div>
            </div>
        </div>
        <!-- 招股书列示同行业上市公司综合毛利率对比 -->
        <div class="compare">
            <div class="title" >
                <span class="littleRectangle"></span>
                <span class="titleText" id="comparison">招股书列示同行业上市公司综合毛利率对比</span>
            </div>
            <div class="chartTable" v-for="item,index in maoChartTableData" :key="item.id">
                <p style="font-family:'PingFang-SC-Regular', 'PingFang SC';font-weight:400;color:#666666;">{{item.remark}}：</p>
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
                                <span>{{isNotEmpty(scope.row.thirdYearRate) ? scope.row.thirdYearRate+'%' : '- -'}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.secondYear" header-align="center" >
                            <template slot-scope="scope">
                                <span>{{isNotEmpty(scope.row.secondYearRate) ? scope.row.secondYearRate+'%' : '- -'}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.firstYear" header-align="center" >
                            <template slot-scope="scope">
                                <span>{{isNotEmpty(scope.row.firstYearRate) ? scope.row.firstYearRate+'%' : '- -'}}</span>
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
                                <span style="font-weight:bold ;color:black">{{isNotEmpty(scope.row.thirdYearRate) ? scope.row.thirdYearRate+'%' : '- -'}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.secondYear" header-align="center" >
                            <template slot-scope="scope">
                                <span style="font-weight:bold ;color:black">{{isNotEmpty(scope.row.secondYearRate) ? scope.row.secondYearRate+'%' : '- -'}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.firstYear" header-align="center" >
                            <template slot-scope="scope">
                                <span style="font-weight:bold ;color:black">{{isNotEmpty(scope.row.firstYearRate) ? scope.row.firstYearRate+'%' : '- -'}}</span>
                            </template>
                        </el-table-column>
                    </el-table>
                    <!-- 自己公司 -->
                      <el-table class="averageOrMyself myself" stripe :data="item.industryCompareRateDetailList.slice(-1)" border style="width: 100%;margin-top: 20px">
                        <el-table-column prop="companyName" align="left" class-name="table_cell" label="企业名称" width="156" ></el-table-column>
                        <el-table-column align="center" :label="item.thirdYear" header-align="center">
                            <template slot-scope="scope">
                                <span>{{isNotEmpty(scope.row.thirdYearRate) ? scope.row.thirdYearRate+'%' : '- -'}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.secondYear" header-align="center" >
                            <template slot-scope="scope">
                                <span>{{isNotEmpty(scope.row.secondYearRate) ? scope.row.secondYearRate+'%' : '- -'}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.firstYear" header-align="center" >
                            <template slot-scope="scope">
                                <span>{{isNotEmpty(scope.row.firstYearRate) ? scope.row.firstYearRate+'%' : '- -'}}</span>
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
// zxChart代码
import echarts from 'echarts'
// import { getAssetsTableData } from '@/api/tableDemo'
import { getMaoChartTableData } from '@/api/tableDemo'
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
            zxChartX:[]
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
        this.getPosition()
        this.initTableData()
    },
    mounted () {
    },
    methods:{
        // 初始化折线图
        creatEchart(){
            // this.$refs.zxChart.initTableData()
            // for(var i = 0;i<this.maoChartTableData.length;i++){
                // this.$refs[i].1.initTableData()
            // }
        },
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
                    noClick: false
                }
                let comparison = {
                    id: 'comparison',
                    name: '同行业毛利率对比',
                    notes: '',
                    important: false,
                    tabId: 'tab-second',
                    noClick: false
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
                this.maoChartTableData = res.data.result
                console.log(this.maoChartTableData)
                 
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


