<template>
    <div>
         <div class="className" id="zxChart" style="height:300px;width:100%"></div>
    </div>
</template>

<script>
import echarts from 'echarts'
import { getAssetsTableData } from '@/api/tableDemo'
export default {
 
  data() {
    return {
        zxChart:null,
        zxChartY:[],
        zxChartX:[]
  
    }
  },
  created(){
    //   请求数据
    // this.initTableData()
    // vm
  },
  mounted() {
    this.initTableData()
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
        getAssetsTableData().then(response => {
        //   this.tableTitle = response.data.assetsList[0]
        //   this.tableContent = response.data.assetsList.slice(1)
            if(response.data.assetsList.length > 0){
                // 如果请求到数据之后再初始化折线图
                // console.log(response.data.assetsList[0])
                // console.log(response.data.assetsList.slice(1))
                 this.initChart(response.data.assetsList)
                //  console.log(this.zxChartX)
                //  console.log(this.zxChartY)
            }else {
            alert(response.data.errorMsg)
          }
        })
      },
    //   初始化折线图
    initChart(dataList) {
       const _self = this
        if (this.zxChart) {
          this.zxChart.dispose()
          this.zxChart = null
        }
      this.zxChart = echarts.init(document.getElementById('zxChart'))
    //  循环获取数据
      for (var i = 0; i < dataList.slice(1).length; i++) {
        //    console.log(dataList.slice(1)[i].project)
          this.zxChartY.push(
                                {
                                    name:dataList.slice(1)[i].project,
                                    type:'line',
                                    stack: '总量',
                                    data:[dataList.slice(1)[i].count1,dataList.slice(1)[i].count2,dataList.slice(1)[i].count3,dataList.slice(1)[i].count4,]
                                },
                             )
                             
            this.zxChartX = [dataList[0].year1,dataList[0].year2,dataList[0].year3,dataList[0].year4,]
           
      }
       this.zxChart.setOption({
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
