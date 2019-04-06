<template>
    <div>
         <div class="className" id="myChart" style="height:300px;width:100%"></div>        
    </div>
</template>

<script>
import echarts from 'echarts'
export default {
  name:'myChart',
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
      pieChartTitle:'',
      caseId:this.$store.state.app.caseId,
      erArr:[],
      erArr1: 
           [
                ['product', '2012', '2013', '2014', '2015', '2016', '2017'],
                ['Matcha Latte', 41.1, 30.4, 65.1, 53.3, 83.8, 98.7],
                ['Milk Tea', 86.5, 92.1, 85.7, 83.1, 73.4, 55.1],
                ['Cheese Cocoa', 24.1, 67.2, 79.5, 86.4, 65.2, 82.5]
            
            ]
    }
  },
  props:["mainTableList"],
  mounted(){
    this.initTableData()
    
  },
  methods: {
    initTableData(){
      this.barChartX = [this.mainTableList.firstYearForIncome,this.mainTableList.secondYearForIncome,this.mainTableList.thirdYearForIncome,this.mainTableList.onePeriodForIncome]
        for (var i = 0; i < this.mainTableList.mainIncomeInfoList.length; i++) {
          this.barYY = [];
              var a0 = this.mainTableList.mainIncomeInfoList[i].businessName
              var a1 = this.mainTableList.mainIncomeInfoList[i].firstYearAmount;
              var a2 = this.mainTableList.mainIncomeInfoList[i].secondYearAmount;
              var a3 = this.mainTableList.mainIncomeInfoList[i].thirdYearAmount;
              var a4 = this.mainTableList.mainIncomeInfoList[i].onePeriodAmount;
              this.barYY.push(a0)
              this.barYY.push(a1)
              this.barYY.push(a2)
              this.barYY.push(a3)
              this.barYY.push(a4)
              this.erArr.push(this.barYY)
        }
              var barXX = ['business', this.mainTableList.firstYearForIncome, this.mainTableList.secondYearForIncome, this.mainTableList.thirdYearForIncome, this.mainTableList.onePeriodForIncome];
              this.erArr.unshift(barXX)
              console.log(this.erArr)
              this.initChart()
    },
    initChart() {
      let myChart = echarts.init(document.getElementById('myChart'))
      var option = {
        legend: {},
        title: {
                text: "最近3年主营业务趋势",
                padding: [8, 10,106,10],
                bottom:175,
                textStyle: {
                    color: "#333333",
                    fontWeight: "bold",
                    fontSize: 16
                }
                },
         tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
                }
                },
        dataset: {
            source:this.erArr1
        },
        xAxis: {type: 'category'},
         yAxis: {
                type: "value",
                name:'万元',
                nameLocation:'end',
                nameTextStyle:{
                  padding: [0, 30, 0, 0]
                }
                },
        grid: {left:'0%',right:'55%',height:'50%'},
        series: [
            {
            name:'Matcha Latte',
            type:'bar',
            stack: '12',
            data:[320, 332, 301, 334, 390, 330, 320]
            },
            {
            name:'Milk Tea',
            type:'bar',
            stack: '12',
            data:[120, 132, 101, 134, 90, 230, 210]
        },
        {
            name:'Cheese Cocoa',
            type:'bar',
            stack: '12',
            data:[220, 182, 191, 234, 290, 330, 310]
        },
      //  {
      //           type: 'pie',
      //           id: 'pie',
      //           radius: '50%',
      //           center: ['75%', '35%'],
      //           label: {
      //               formatter: '{b}: {@this.mainTableList.onePeriodForIncome} ({d}%)'
      //           },
      //           encode: {
      //               itemName: 'business',
      //               value: this.mainTableList.onePeriodForIncome,
      //               tooltip: this.mainTableList.onePeriodForIncome
      //           }
      //       }
        
            {
                type: 'pie',
                id: 'pie',
                radius: '30%',
                center: ['75%', '35%'],
                label: {
                    formatter: '{b}: {@2012} ({d}%)'
                },
                encode: {
                    itemName: 'product',
                    value: '2012',
                    tooltip: '2012'
                }
            }
        ]
    };
        myChart.on('updateAxisPointer', function (event) {
        var xAxisInfo = event.axesInfo[0];
        if (xAxisInfo) {
            var dimension = xAxisInfo.value + 1;
            myChart.setOption({
                series: {
                    id: 'pie',
                    label: {
                        formatter: '{b}: {@[' + dimension + ']} ({d}%)'
                    },
                    encode: {
                        value: dimension,
                        tooltip: dimension
                    }
                }
            });
        }
    });

    myChart.setOption(option);
    //   var option = {
    //     legend: {},
    //     title: {
    //             text: "最近3年主营业务趋势",
    //             padding: [8, 10,106,10],
    //             bottom:175,
    //             textStyle: {
    //                 color: "#333333",
    //                 fontWeight: "bold",
    //                 fontSize: 16
    //             }
    //             },
    //      tooltip: {
    //             trigger: "axis",
    //             axisPointer: {
    //                 type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
    //             }
    //             },
    //     dataset: {         
    //         source:this.erArr
    //     },
    //          legend: {
    //                 // selectedMode:false,//取消图例上的点击事件
    //                 // orient: "vertical",
    //                 x: "0%", // 'center' | 'left' | {number},
    //                 y: "10%", // 'center' | 'bottom' | {number}
    //                 // data: this.lengendData,
    //                 itemWidth: 10, // 图例图形宽度
    //                 itemHeight: 10, // 图例图形高度
    //                 textStyle: {
    //                 color: "#333" // 图例文字颜色
    //                 }
    //              },
    //     xAxis: {type: 'category'},
    //      yAxis: {
    //             type: "value",
    //             name:'万元',
    //             nameLocation:'end',
    //             nameTextStyle:{
    //               padding: [0, 30, 0, 0]
    //             }
    //             },
    //     grid: {left:'0%',right:'55%',height:'50%'},
    //     series: [
    //         {type: 'bar', smooth: true, seriesLayoutBy: 'row'},
    //         {
    //             type: 'pie',
    //             id: 'pie',
    //             radius: '50%',
    //             center: ['75%', '35%'],
    //             label: {
    //                 formatter: '{b}: {@this.mainTableList.onePeriodForIncome} ({d}%)'
    //             },
    //             encode: {
    //                 itemName: 'business',
    //                 value: this.mainTableList.onePeriodForIncome,
    //                 tooltip: this.mainTableList.onePeriodForIncome
    //             }
    //         }
    //     ]
    // };

    // myChart.on('updateAxisPointer', function (event) {
    //     var xAxisInfo = event.axesInfo[0];
    //     if (xAxisInfo) {
    //         var dimension = xAxisInfo.value + 1;
    //         myChart.setOption({
    //             series: {
    //                 id: 'pie',
    //                 label: {
    //                     formatter: '{b}: {@[' + dimension + ']} ({d}%)'
    //                 },
    //                 encode: {
    //                     value: dimension,
    //                     tooltip: dimension
    //                 }
    //             }
    //         });
    //     }
    // });
    myChart.setOption(option);
    },
  },
}
</script>
