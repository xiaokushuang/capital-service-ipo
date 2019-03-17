<template>
    <div>
         <div class="className" id="tanZxChart1" style="height:300px;width:100%"></div>        
    </div>
</template>

<script>
import echarts from 'echarts'
// 引入点击放大镜弹出来的表头年限数据
// import { getAssetsTableData } from '@/api/tableDemo'
import { getSelectFinanceOverList } from '@/api/tableDemo'
export default {
  data() {
    return {
        tableTitle:null,
        zxChartX:[],
        zxChartY:[]
    }
  },
  created(){
       
       
  },
  props:["zxChartData"],
  methods: {
    initChart() {
      // console.log(this.zxChartData)
      let tanZxChart1 = echarts.init(document.getElementById('tanZxChart1'))
       tanZxChart1.setOption({
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
                      type: 'value'
                    },
            series:this.zxChartY
      })
    },
      // 初始化数据
    initTableData() {
      getSelectFinanceOverList().then(response => {
          // 获取表头数据
        this.tableTitle = response.data.result.dateList
        // console.log(this.tableTitle)
        this.zxChartX =[this.tableTitle.firstYearDate,this.tableTitle.secondYearDate,this.tableTitle.thirdYearDate,this.tableTitle.forthYearDate]
          this.zxChartY = [
                      {
                          name:this.zxChartData.itemName,
                          type:'line',
                          data:[this.zxChartData.firstYearValue, this.zxChartData.secondYearValue, this.zxChartData.thirdYearValue,this.zxChartData.forthYearValue]
                      },
                  ]
        //  console.log(this.zxChartY)
      this.initChart()
      })
    },
  },
  // 动态实时监听刷新折线图的数据
  watch:{
    zxChartData: {  
　　　　handler(newValue, oldValue) {  
        this.zxChartData = newValue
　　　 　console.log(this.zxChartData)  
        //折线图数据的初始化 
        this.initTableData()
　　　　},  

　　　　deep: true,  //对象内部的属性监听，也叫深度监听
       immediate: true //immediate表示在watch中首次绑定的时候，是否执行handler，值为true则表示在watch中声明的时候，就立即执行handler方法，值为false，则和一般使用watch一样，在数据发生变化的时候才执行handler
　　}  
  }
}
</script>
