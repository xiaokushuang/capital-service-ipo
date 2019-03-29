<template>
    <div>
         <div class="className" id="tanZxChart" style="height:300px;width:100%"></div>        
    </div>
</template>

<script>
import echarts from 'echarts'
// 引入点击放大镜弹出来的表头年限数据
import { getAssetsOrDebtData } from '@/api/tableDemo'
export default {
  name:'tanZxChart',
  data() {
    return {
        tableTitle:null,
        zxChartX:[],
        zxChartY:[],
        caseId:this.$store.state.app.caseId,
    }
  },
  created(){
       
       
  },
  props:["zxChartData"],
  methods: {
    initChart() {
      let tanZxChart = echarts.init(document.getElementById('tanZxChart'))
       tanZxChart.setOption({
           title: {
                text: ''
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                data:this.zxChartX
            },
            yAxis: {
                      type: 'value',
                      name:'万元',
                      nameLocation:'end',
                      nameGap:'20',
                      nameTextStyle:{
                        padding: [0, 48, 0, 0]
                      }
                    },
            series:this.zxChartY
      })
    },
     // 初始化数据
    initTableData() {
          // 动态传id
      const param = {
        id:this.caseId
      }
      getAssetsOrDebtData(param).then(response => {
          // 获取表头数据
        this.tableTitle = response.data.result.dateList
        this.zxChartX =[this.tableTitle.firstYearDate,this.tableTitle.secondYearDate,this.tableTitle.thirdYearDate,this.tableTitle.forthYearDate]
          this.zxChartY = [
                      {
                          name:this.zxChartData.itemName,
                          type:'line',
                          data:[this.zxChartData.firstYearValue, this.zxChartData.secondYearValue, this.zxChartData.thirdYearValue,this.zxChartData.forthYearValue],
                          areaStyle: {
                            shadowColor: ['#ffefeb','#fff8f6'],
                            opacity: 0.1
                          }
                      },
                  ]
      this.initChart()
      })
    },
  },
  // 动态实时监听刷新折线图的数据
  watch:{
    zxChartData: {  
　　　　handler(newValue, oldValue) {  
        this.zxChartData = newValue
        //折线图数据的初始化 
        this.initTableData()
　　　　},  

　　　　deep: true,  //对象内部的属性监听，也叫深度监听
       immediate: true //immediate表示在watch中首次绑定的时候，是否执行handler，值为true则表示在watch中声明的时候，就立即执行handler方法，值为false，则和一般使用watch一样，在数据发生变化的时候才执行handler
　　}  
  },
}
</script>
