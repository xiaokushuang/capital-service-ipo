<template>
    <div class="clear">
         <div class="l" id="barChart" style="height:300px;width:50%"></div>
         <div class="l" id="pieChart" style="height:300px;width:50%"></div>
    </div>
</template>

<script>
import echarts from 'echarts'
import { getTableData } from '@/api/tableDemo'
// import { getMarketData } from "@/api/companyProfile";
export default {
  data() {
    return {
      barChart:null,
      pieChart:null,
      tableTitle: null,
      tableContent: null,
      barChartY : [],
      barChartX : [],
      lengendData:[],
      pieData:[],
      pieChartTitle:''
    }
  },
    created(){
    //   请求数据
    this.initTableData()
  },
  beforeDestroy() {
    if (!this.barChart) {
    return
    }
    this.barChart.dispose()
    this.barChart = null
     if (!this.pieChart) {
    return
    }
    this.pieChart.dispose()
    this.pieChart = null
},
  methods: {
     // 初始化数据
      initTableData() {
        getTableData().then(response => {
          // console.log(response.data.result)
          if(response.data.result){
            // 如果请求到数据之后再初始化折线图
                 this.initBarChart(response.data.result)
                 this.initPieChart(response.data.result)
            }
        })
      },
       //   初始化柱状图
    initBarChart(dataList) {
      // console.log(dataList)
       const _self = this
        if (this.barChart) {
          this.barChart.dispose()
          this.barChart = null
        }
      this.barChart = echarts.init(document.getElementById('barChart'))
       // 点击柱状图获取相应数据
      this.barChart.on("click", function(params) {
        //   绑定饼状图数据
        //  for (var i = 0; i < dataList.slice(1).length; i++) {
        //   this.pieData.push(
        //              {  
        //                 value: [dataList.slice(1)[i].count2], 
        //                 name: [dataList.slice(1)[i].ratio2]
        //              },
        //                  )
            
        //        }  
        // console.log(params);
        this.pieChartTitle = params.name
        // console.log(this.pieChartTitle);
      });
    //  循环获取数据
      for (var i = 0; i < dataList.mainIncomeInfoList.length; i++) {
          this.lengendData = dataList.mainIncomeInfoList.businessName
          //  console.log(dataList.mainIncomeInfoList.length)
          this.barChartY.push(
                                {
                                    name:dataList.mainIncomeInfoList[i].businessName,
                                    type:'bar',
                                    barWidth:'50%',
                                    stack: '总量',
                                    data:[dataList.mainIncomeInfoList[i].thirdYearAmount,dataList.mainIncomeInfoList[i].secondYearAmount,dataList.mainIncomeInfoList[i].firstYearAmount ]
                                },
                             )
            
               }              
            // this.barChartX = [dataList[0].year3,dataList[0].year2,dataList[0].year1]
            this.barChartX = [dataList.thirdYearForIncome,dataList.secondYearForIncome,dataList.onePeriodForIncome]
            console.log(this.barChartY)
            this.pieChartTitle = this.barChartX[2]
            // console.log(this.pieChartTitle);
           
      
       this.barChart.setOption({
           title: {
                text: "最近3年主营业务趋势",
                textStyle: {
                    color: "black",
                    fontWeight: "normal",
                    fontSize: 16
                }
                },
                tooltip: {
                trigger: "axis",
                axisPointer: {
                    // 坐标轴指示器，坐标轴触发有效
                    type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
                }
                },
                 legend: {
                    // padding: [
                    //   200, // 上
                    //   0, // 右
                    //   5, // 下
                    //   0 // 左
                    // ],
                    // orient: "vertical",
                    // x: "300", // 'center' | 'left' | {number},
                    // y: "100", // 'center' | 'bottom' | {number}
                    data: this.lengendData,
                    // itemWidth: 10, // 图例图形宽度
                    // itemHeight: 10, // 图例图形高度
                    // textStyle: {
                    //     color: "#333" // 图例文字颜色
                    // }
                    },
                grid: {
                left: "3%",
                right: "4%",
                bottom: "3%",
                containLabel: true
                },
            xAxis: {
                type: 'category',
                data:this.barChartX
            },
           yAxis: {
                type: "value"
                },
                series: this.barChartY
            })
    },
    // 饼形图
    initPieChart(dataList) {
        console.log(dataList.mainIncomeInfoList)
         const _self = this
        if (this.pieChart) {
          this.pieChart.dispose()
          this.pieChart = null
        }
       this.pieChart = this.$echarts.init(document.getElementById("pieChart"));
        //  循环获取数据
      for (var i = 0; i < dataList.mainIncomeInfoList.length; i++) {
          this.pieData.push(
                     {  
                        value: [dataList.mainIncomeInfoList[i].thirdYearAmount], 
                        name: [dataList.mainIncomeInfoList[i].thirdYearRatio]
                     },
                         )
            
               }  
       this.pieChart.setOption({
        title: { text: this.pieChartTitle+" _ 主营业务分布" },
        // title: { text: this.dianjizhuzi?this.pieChartTitle:this.zhudataListX[3]+" _ 主营业务分布" },
        
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        // legend: {
        //   // padding: [
        //   //   200, // 上
        //   //   0, // 右
        //   //   5, // 下
        //   //   0 // 左
        //   // ],
        //   orient: "vertical",
        //   x: "300", // 'center' | 'left' | {number},
        //   y: "100", // 'center' | 'bottom' | {number}
        //   // data: ["宽带移动通信设备", "集成业务", "技术开发业务", "工程业务"],
        //   itemWidth: 10, // 图例图形宽度
        //   itemHeight: 10, // 图例图形高度
        //   textStyle: {
        //     color: "#333" // 图例文字颜色
        //   }
        // },
        series: [
          {
            name: "访问来源",
            type: "pie",
            radius: "55%",
            center: ["50%", "60%"],
            // data: this.pieChartData,
            data:this.pieData,
            // [

            // { value: 335, name: "49%" },
            // { value: 310, name: "23%" },
            // { value: 234, name: "67%" },
            // { value: 135, name: "54%" }
            // ],
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)"
              }
            }
          }
        ]
      });
    }
 
  }
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
</style>

