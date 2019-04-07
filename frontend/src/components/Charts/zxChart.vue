<template>
    <div>
         <div class="className" :id="'zxChart' + zxIndex " style="height:300px;width:100%"></div>
    </div>
</template>

<script>
import echarts from 'echarts'
import { getMaoChartTableData } from '@/api/ipoCase/tableDemo'
export default {

  data() {
    return {
        caseId:this.$store.state.app.caseId,
        zxChart:null,
        zxChartY:[],
        zxChartX:[]
    }
  },
  props:["zxIndex"],
  created(){
    //   请求数据
    this.initTableData()
  },
  mounted() {
  },
  updated() {

  },
beforeDestroy() {
    if (!this.zxChart) {
    return
    }
    this.zxChart.dispose()
    this.zxChart = null
},
  methods: {
     // 初始化数据
      initTableData() {
         // 动态传id
        const param = {
          id:this.caseId
        }
        getMaoChartTableData(param).then(response => {
            if(response.data.result){
                // 如果请求到数据之后再初始化折线图
                 this.initChart(response.data.result)
            }
        })
      },
      // 动态实时监听刷新折线图的数据
      watch:{
        zxIndex: {  
    　　　　handler(newValue, oldValue)
             {  
              this.zxIndex = newValue
              //折线图数据的初始化
              this.initTableData()
      　　　　},  
      　　　　deep: true,  //对象内部的属性监听，也叫深度监听
            immediate: true //immediate表示在watch中首次绑定的时候，是否执行handler，值为true则表示在watch中声明的时候，就立即执行handler方法，值为false，则和一般使用watch一样，在数据发生变化的时候才执行handler
    　}  
    },
    //   初始化折线图
    initChart(dataList) {
       const _self = this
        if (this.zxChart) {
          this.zxChart.dispose()
          this.zxChart = null
        }
      this.zxChart = echarts.init(document.getElementById('zxChart' + this.zxIndex))
    //  循环获取数据
        // for (var i = 0; i < dataList.length; i++) {
        for(var i = 0;i < this.zxIndex + 1;i++){
          this.zxChartY = []
          for(var j = 0;j < dataList[i].industryCompareRateDetailList.length;j++){
              this.zxChartY.push(
                                {
                                    name:dataList[i].industryCompareRateDetailList[j].companyName,
                                    type:'line',
                                    data:[dataList[i].industryCompareRateDetailList[j].firstYearRate,dataList[i].industryCompareRateDetailList[j].secondYearRate,dataList[i].industryCompareRateDetailList[j].thirdYearRate]
                                },
                             )

             this.zxChartX = [dataList[i].firstYear,dataList[i].secondYear,dataList[i].thirdYear]

          }
        }
       this.zxChart.setOption({
           title: {
                text: ''
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
              selectedMode:'multiple'
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
           yAxis:[
                {
                    axisLabel:{
                        formatter:'{value} %'
                    }
                }
            ],
            series: this.zxChartY
      })
    }

  }
}
</script>
