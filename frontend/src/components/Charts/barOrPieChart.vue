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
      barYY:[],
      barChartX : [],
      lengendData:[],
      pieData:[],
      date:'',
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
          if(response.data.result){
            // 如果请求到数据之后再初始化折线图
                 this.initBarChart(response.data.result)
                 this.initPieChart(response.data.result)
            }
        })
      },
       //   初始化柱状图
    initBarChart(dataList) {
       const _self = this
        if (this.barChart) {
          this.barChart.dispose()
          this.barChart = null
        }
      this.barChart = echarts.init(document.getElementById('barChart'))
      
    //  循环获取柱状图数据
      for (var i = 0; i < dataList.mainIncomeInfoList.length; i++) {
          this.lengendData = dataList.mainIncomeInfoList.businessName
          var a1 = dataList.mainIncomeInfoList[i].firstYearAmount
          var a2 = dataList.mainIncomeInfoList[i].secondYearAmount;
          var a3 = dataList.mainIncomeInfoList[i].thirdYearAmount;
          this.barYY.push(a1)
          this.barYY.push(a2)
          this.barYY.push(a3)
          this.barChartY.push(
                            {
                                name:dataList.mainIncomeInfoList[i].businessName,
                                type:'bar',
                                barWidth:'50%',
                                stack: '总量',
                                data:this.barYY,
                            },
                             )
            
               }      
         this.barChartX = [dataList.firstYearForIncome,dataList.secondYearForIncome,dataList.thirdYearForIncome]
         this.pieChartTitle = this.barChartX[2]
   // 点击柱状图获取相应数据
      this.barChart.on("click", function(params) {
        this.date = params.name
        // console.log(this.date)
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
        console.log(this.pieChartTitle);
      });
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
                    orient: "vertical",
                    // x: "300", // 'center' | 'left' | {number},
                    // y: "0", // 'center' | 'bottom' | {number}
                    data: this.lengendData,
                    itemWidth: 10, // 图例图形宽度
                    itemHeight: 10, // 图例图形高度
                    textStyle: {
                        color: "#333" // 图例文字颜色
                    }
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
        // console.log(this.date)
         const _self = this
        if (this.pieChart) {
          this.pieChart.dispose()
          this.pieChart = null
        }
       this.pieChart = this.$echarts.init(document.getElementById("pieChart"));
        //  循环获取饼状图数据
      for (var i = 0; i < dataList.mainIncomeInfoList.length; i++) {
        if(this.date==='2018-12-31'){
              // 3
          this.pieData.push(
                            {  
                                value: [dataList.mainIncomeInfoList[i].thirdYearAmount], 
                                name: [dataList.mainIncomeInfoList[i].thirdYearRatio+'%']
                            },
                          )
        }
        if(this.date==='2017-12-31'){
           // 2
          this.pieData.push(
          {  
              value: [dataList.mainIncomeInfoList[i].secondYearAmount], 
              name: [dataList.mainIncomeInfoList[i].secondYearRatio+'%']
          },
        )
        }
         if(this.date==='2016-12-31'){
            // 1
          this.pieData.push(
                            {  
                                value: [dataList.mainIncomeInfoList[i].firstYearAmount], 
                                name: [dataList.mainIncomeInfoList[i].firstYearRatio+'%']
                            },
                          )
         }
         else{
            this.pieData.push(
                            {  
                                value: [dataList.mainIncomeInfoList[i].thirdYearAmount], 
                                name: [dataList.mainIncomeInfoList[i].thirdYearRatio+'%']
                            },
                          )
         }
      
      }  
       this.pieChart.setOption({
         
        title: { text: this.pieChartTitle+" _ 主营业务分布" },
        // title: { text: this.dianjizhuzi?this.pieChartTitle:this.zhudataListX[3]+" _ 主营业务分布" },
        
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [
          {
            name: "访问来源",
            type: "pie",
            radius: "55%",
            center: ["50%", "60%"],
            data:this.pieData,
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

