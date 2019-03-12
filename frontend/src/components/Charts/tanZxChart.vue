<template>
    <div>
         <div class="className" id="tanZxChart" style="height:300px;width:100%"></div>        
    </div>
</template>

<script>
import echarts from 'echarts'
// 引入点击放大镜弹出来的表头年限数据
import { getAssetsTableData } from '@/api/tableDemo'
export default {
 
  data() {
    return {
        tableTitle:null,
        zxChartX:[],
        zxChartY:[]
    }
  },
  created(){
       this.initTableData()
    //    console.log(this.zxChartData)
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
                      type: 'value'
                    },
            series:this.zxChartY
      })
    },
     // 初始化数据
      initTableData() {
        getAssetsTableData().then(response => {
            // 获取表头数据
          this.tableTitle = response.data.assetsList[0]
        //   console.log(this.tableTitle)
          this.zxChartX =[this.tableTitle.year1,this.tableTitle.year2,this.tableTitle.year3,this.tableTitle.year4]
           this.zxChartY = [
                       {
                            name:this.zxChartData.project,
                            type:'line',
                            data:[this.zxChartData.count1, this.zxChartData.count2, this.zxChartData.count3,this.zxChartData.count4,this.zxChartData.count5]
                        },
                    ]
          //  console.log(this.zxChartY)
        this.initChart()
        })
      },
  }
}
</script>
