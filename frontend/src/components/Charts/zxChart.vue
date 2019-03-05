<template>
    <div>
         <div class="className" id="zxChart" style="height:300px;width:100%"></div>
    </div>
</template>

<script>
import echarts from 'echarts'

export default {
 
  data() {
    return {
    }
  },
  mounted() {
    this.initChart()
  },
  methods: {
    initChart() {
      let zxChart = echarts.init(document.getElementById('zxChart'))
       zxChart.setOption({
           title: {
                text: ''
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                // data:['百花帮月','企业3','平均值','企业2','企业1']
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
                data: ['2017年6月','2016年6月','2015年6月']
            },
           yAxis:[
                {
                    axisLabel:{
                        formatter:'{value} %'
                    }
                }
            ],
            series: [
                {
                    name:'百花帮月',
                    type:'line',
                    stack: '总量',
                    data:[12, 13, 10]
                },
                {
                    name:'企业3',
                    type:'line',
                    stack: '总量',
                    data:[20, 1, 18]
                },
                {
                    name:'平均值',
                    type:'line',
                    stack: '总量',
                    data:[15, 32, 1]
                },
                {
                    name:'企业2',
                    type:'line',
                    stack: '总量',
                    data:[39, 30, 20]
                },
                {
                    name:'企业1',
                    type:'line',
                    stack: '总量',
                    data:[10, 30, 20]
                }
            ]
      })
    }
  }
}
</script>
