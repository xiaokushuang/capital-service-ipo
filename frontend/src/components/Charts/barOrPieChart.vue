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
      pieChartTitle:'',
      // id:'97952444248599350',
      caseId:this.$store.state.caseId,
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
    // echartClick(){
    //   debugger
     
    // },
     // 初始化数据
      initTableData() {
            // 动态传id
        const param = {
          id:this.caseId
        }
        getTableData(param).then(response => {
          if(response.data.result){
            // 如果请求到数据之后再初始化柱形图
            var dataList = response.data.result
                 this.initBarChart(dataList);
                //  debugger;
                //  最开始初始化饼状图，默认传的是第三年的数据
                 this.initPieChart(dataList.mainIncomeInfoList,'','',dataList.thirdYearForIncome);
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
          this.lengendData.push(dataList.mainIncomeInfoList[i].businessName);
          this.barYY = [];
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
   // 点击柱状图获取相应数据
      this.barChart.on("click", function(params) {
        // debugger
        // 获取整条柱子的信息
        var series = this.getOption().series
        // 点击柱子初始化饼状图
        _self.initPieChart(series,params.name,params.dataIndex,'');
      });
       this.barChart.setOption({
           title: {
                text: "最近3年主营业务趋势",
                textStyle: {
                    color: "#333333",
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
initPieChart(dataList,nameTempO,num,flag) {
  this.pieData = [];
  this.pieChart = this.$echarts.init(document.getElementById("pieChart"));
   var nameTemp = ''
  //  用nameTempO这个标识来实现点击柱子切换标题
  if(nameTempO){
    nameTemp = nameTempO;
  }
  // debugger
  // 用flag这个标识来区别是【初始化的数据】的饼状图还是【点击切换】的饼状图
  if(flag){
    // 默认饼状图展示最后一年的数据
    nameTemp = flag;
    for (var i = 0; i < dataList.length; i++) {
      var obj = {
        name:"",
        value:""
      };
      // debugger
      obj.name = dataList[i].businessName
      obj.value = dataList[i].thirdYearAmount;
      this.pieData.push(obj);
    }
  }else{
    // 点击柱子切换饼状图的数据源
    for (var i = 0; i < dataList.length; i++) {
      var obj = {
        name:"",
        value:""
      };
      obj.name = dataList[i].name
      obj.value = dataList[i].data[num];
      this.pieData.push(obj);
    }
  }
  var option = {
         title: {
                text: "• "+nameTemp+" _ 主营业务分布",
                textStyle: {
                    color: "#333",
                    fontWeight: "normal",
                    fontSize: 14
                },
                left: 'center',
                top:50,
                // padding:'60%',
         },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
         legend: {
                    selectedMode:false,//取消图例上的点击事件
                    orient: "vertical",
                    x: "360", // 'center' | 'left' | {number},
                    y: "200", // 'center' | 'bottom' | {number}
                    data: this.lengendData,
                    itemWidth: 10, // 图例图形宽度
                    itemHeight: 10, // 图例图形高度
                    textStyle: {
                    color: "#333" // 图例文字颜色
                    }
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
			}
      this.pieChart.setOption(option)
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

