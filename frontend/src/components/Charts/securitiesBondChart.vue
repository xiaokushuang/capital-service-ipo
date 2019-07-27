<template>
  <div :class="className" :id="id" :style="{height:height,width:width}"></div>
</template>

<script>
import echarts from "echarts";

export default {
  //props: ['className','id','width','height','chartData'],
  name: "Chart",
  props: {
    className: {
      type: String,
      default: "chart"
    },
    id: {
      type: String,
      default: "id"
    },
    width: {
      type: String,
      default: "100%"
    },
    height: {
      type: String,
      default: "100%"
    },
    chartData: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      chart: null,
      defaultIpo: this.ipodata || []
    };
  },
  mounted() {
    //this.initChart()
    setTimeout(function (){
      window.onresize = function () {
        let chart = echarts.init(document.querySelector('#one'));
        chart.resize();
      }
    },200);
    this.chart = null;
    // console.log(this.chartData)
  },
  watch: {
    chartData(n, o) {
      this.initChart();
      // console.log('数据变了')
      //console.log(this.chartData)
    }
  },
  beforeDestroy() {
    if (!this.chart) {
      return;
    }
    this.chart.dispose();
    this.chart = null;
  },
  methods: {
    initChart() {
      //日期
      var arrDate = [];
      //募集资金
      var arr = [];
      //发行家数
      var arrNum = [];
      this.chartData.map(function(obj) {
        arrDate.push(obj.date);
        if (obj.dataSum.length == 1) {
          arr.push(obj.dataSum[0].value);
          arrNum.push(obj.dataSum[0].num);

          var str =
            "<tr><td>" +
            obj.date +
            "</td><td>" +
            obj.dataSum[0].value +
            "</td><td>" +
            obj.dataSum[0].num +
            "</td></tr>";
          // $('#bondTab1').append(str);
        } else {
          arr.push(0);
          arrNum.push(0);

          var str =
            "<tr><td>" + obj.date + "</td><td>0.0000</td><td>0</td></tr>";
          // $('#bondTab1').append(str);
        }
      });

      this.chart = echarts.init(document.getElementById(this.id));
      const xData = (function() {
        const data = [];
        for (let i = 1; i < 13; i++) {
          data.push(i + "month");
        }
        return data;
      })();
      try{
      this.chart.setOption({
        tooltip: {
          x: "left",
          trigger: "axis",
          /*axisPointer: {
			            type: 'shadow'
			        },*/
          padding: [10, 10, 10, 20],
          textStyle: {
            align: "left"
          }
        },
        legend: {
          x: "right",
          selected: {
            募集资金: true,
            发行家数:true
          },
          padding:[10, 25, 15, 20],
          data: ["募集资金", "发行家数"]
        },
        xAxis: [
          {
            type: "category",
            data: arrDate,
            axisPointer: {
              type: "shadow"
            },
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis: [
          {
            type: "value",
            name: "金额（亿元）",
            /*min: 0,
			            max: 200000,
			            interval: 20000,*/
            axisLabel: {
              formatter: function (value, index) {  //y轴千分位格式化
                return value.toString().replace(/(\d{1,3})(?=(\d{3})+$)/g,'$1,');      
              }
            }
          },
          {
            type: "value",
            name: "数量（家数）",
            /*min: 0,
			            max: 15000,
			            interval: 3000,*/
            axisLabel: {
              formatter: function (value, index) {  //y轴千分位格式化
                return value.toString().replace(/(\d{1,3})(?=(\d{3})+$)/g,'$1,');      
              }
            }
          }
        ],
        series: [
          {
            name: "募集资金",
            type: "bar",
            /*itemStyle:{
			                normal:{
			                	color:'#6AB0B8',
			                 }  
			            }, */
            data: arr
          },
          {
            name: "发行家数",
            type: "line",
            /*itemStyle:{
			                normal:{
			                	color:'#D48265',
			                     lineStyle:{    
			                         color:'#D48265'
			                     }    
			                 }  
			            },  */
            symbolSize: 5,
            smooth: false,
            showAllSymbol: true,
            yAxisIndex: 1,
            label: {
              normal: {
                show: true
              }
            },
            data: arrNum
          }
        ]
      });
      } catch(e) {}
    }
  }
};
</script>
