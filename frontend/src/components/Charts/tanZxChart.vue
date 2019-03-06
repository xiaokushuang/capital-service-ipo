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
       this.initChart()
       this.initTableData()
       
       this.zxChartY = [
                       {
                            name:this.zxChartData.project,
                            type:'line',
                            data:[this.zxChartData.count1, this.zxChartData.count2, this.zxChartData.count3,this.zxChartData.count4,this.zxChartData.count5]
                        },
                    ]
       
  },
  beforeUpdate(){
      console.log(this.zxChartData)
      this.zxChartY = [
                       {
                            name:this.zxChartData.project,
                            type:'line',
                            data:[this.zxChartData.count1, this.zxChartData.count2, this.zxChartData.count3,this.zxChartData.count4,this.zxChartData.count5]
                        },
                    ]
  },
  mounted() {
    // this.initChart()
    this.initTableData()
   
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
                // boundaryGap: false,
                // data:[this.tableTitle.year1,this.tableTitle.year2,this.tableTitle.year3,this.tableTitle.year4]
                data: ["2017年1-6月", "2016年1-6月", "2015年1-6月", "2014年1-6月"]
                // data:this.zxChartX
            },
            yAxis: {
                      type: 'value'
                    },
            series:this.zxChartY
               
           
            // series: [
            //     {
            //         name:'百花帮月',
            //         type:'line',
            //         data:[12, 13, 10]
            //     },
            // ]
      })
    },
     // 初始化数据
      initTableData() {
        getAssetsTableData().then(response => {
            // 获取表头数据
          this.tableTitle = response.data.assetsList[0]
        //   console.log(this.tableTitle)
          this.zxChartX =[this.tableTitle.year1,this.tableTitle.year2,this.tableTitle.year3,this.tableTitle.year4]
        //    console.log(this.zxChartX)
        //   this.tableContent = response.data.assetsList.slice(1)
        this.initChart()
        })
      },
      // 非空判断
      isNotEmpty(param) {
        // debugger
        if (param != null && param !== undefined && param !== '' && param !== 'null' && param !== 'undefined') {
          return true
        } else {
          return false
        }
      },
  }
}
</script>
