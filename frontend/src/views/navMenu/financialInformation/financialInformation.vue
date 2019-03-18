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
                    <!-- <zxChart :ref="zxChart" + index></zxChart> -->
                    <!-- <zxChart :ref = index ></zxChart> -->
                    <!-- <div id="zxChart" style="height:300px;width:100%"></div> -->
                    <!-- <div id="s">333444555</div> -->
                </div>
                <div class="table">
                    <!-- <compareTable></compareTable> -->
                    <!-- 其他公司对比 -->
                     <el-table stripe :data="item.industryCompareRateDetailList" border style="width: 100%;margin-top: 20px">
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
                     <el-table class="averageOrMyself" stripe :data="item.average" border style="width: 100%;margin-top: 20px">
                        <el-table-column align="left" class-name="table_cell" label="企业名称" width="156" >
                             <template slot-scope="scope">
                                <span style="font-weight:bold;color:black">{{isNotEmpty(scope.row.companyName) ? scope.row.companyName: '- -'}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.thirdYear" header-align="center">
                            <template slot-scope="scope">
                                <span style="font-weight:bold ;color:black">{{isNotEmpty(scope.row.thirdAvg) ? scope.row.thirdAvg+'%' : '- -'}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.secondYear" header-align="center" >
                            <template slot-scope="scope">
                                <span style="font-weight:bold ;color:black">{{isNotEmpty(scope.row.secondAvg) ? scope.row.secondAvg+'%' : '- -'}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.firstYear" header-align="center" >
                            <template slot-scope="scope">
                                <span style="font-weight:bold ;color:black">{{isNotEmpty(scope.row.firstAvg) ? scope.row.firstAvg+'%' : '- -'}}</span>
                            </template>
                        </el-table-column>
                    </el-table>
                    <!-- 自己公司 -->
                      <el-table class="averageOrMyself myself" stripe :data="item.myself" border style="width: 100%;margin-top: 20px">
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
      
    //   console.log(document.querySelector('.financialData'))
    //   console.log(document.getElementById('zxChart'))  
    },
    methods:{
        // 初始化折线图
        creatEchart(){
            // console.log(1111)
            // console.log(this.$refs)
            // this.$refs.zxChart.initTableData()
            // for(var i = 0;i<this.maoChartTableData.length;i++){
                // this.$refs[i].1.initTableData()
            // }
        },
          // 初始化折线图
        // creatEchart(){
        //     console.log(1111)
        //     console.log(this.$refs)
        //     this.$refs[0].zxChart.initTableData()
        //     // for(var i = 0;i<this.$refs.zxChart.length;i++){
        //     //     console.log(this.$refs.zxChart.length)
        //     //     console.log(i)
        //     //     this.$refs[0].zxChart.initTableData()
        //     // }
        // },
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
            getMaoChartTableData().then(res => {
                this.maoChartTableData = res.data.result
                // console.log(this.maoChartTableData)
               
                //  if(this.maoChartTableData.length > 0){
                //       // 初始化折线图
                //     this.initChart(this.maoChartTableData)
                //      console.log('iddddd')
                //      console.log(document.getElementById('s'))
                //  }
                 
            }) 
        },
         //   初始化折线图
    // initChart(dataList) {
    //   console.log(dataList[0])
    //    const _self = this
    //     // if (this.zxChart) {
    //     //   this.zxChart.dispose()
    //     //   this.zxChart = null
    //     // }
    //   this.zxChart = echarts.init(document.getElementById('zxChart'))  
    // //  循环获取数据
    //     for (var i = 0; i < dataList.length; i++) {
    //     // for(var i = 0;i < this.zxIndex;i++){
    //       for(var j = 0;j < dataList[i].industryCompareRateDetailList.length;j++){ 
    //           this.zxChartY.push(
    //                             {
    //                                 name:dataList[i].industryCompareRateDetailList[j].companyName,
    //                                 type:'line',
    //                                 stack: '总量',
    //                                 data:[dataList[i].industryCompareRateDetailList[j].firstYearRate,dataList[i].industryCompareRateDetailList[j].secondYearRate,dataList[i].industryCompareRateDetailList[j].thirdYearRate]
    //                             },
    //                          )
                             
    //          this.zxChartX = [dataList[i].firstYear,dataList[i].secondYear,dataList[i].thirdYear]
           
    //       }
    //     }
    //    this.zxChart.setOption({
    //        title: {
    //             text: ''
    //         },
    //         tooltip: {
    //             trigger: 'axis'
    //         },
    //         legend: {
    //         },
    //         grid: {
    //             left: '3%',
    //             right: '4%',
    //             bottom: '3%',
    //             containLabel: true
    //         },
    //         xAxis: {
    //             type: 'category',
    //             data:this.zxChartX
    //         },
    //        yAxis:[
    //             {
    //                 axisLabel:{
    //                     formatter:'{value} %'
    //                 }
    //             }
    //         ],
    //         series: this.zxChartY
    //   })
    // },
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


