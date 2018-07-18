<template>
  <div :class="className" :id="id" :style="{height:height,width:width}"></div>
</template>

<script>
import echarts from "echarts";

export default {
  //props: ['className','id','width','height','chartData'],
  name: "datasetChart",
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
    this.chart = null;
    //console.log(this.chartData)
  },
  watch: {
    chartData(n, o) {
      this.initChart();
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
      // 设置数据
      this.chart = echarts.init(document.getElementById(this.id));
      var myChartColor = ['#ca2428', '#2e444e', '#7ccbab', '#ffa128', '#227d99', '', '#ff5f3a'];
      var labels = ['沪主板', '中小板', '创业板']; // 横坐标标签
      // 细分业务标签
      var itemLabel = [];
      var series = [];
      for (var i = 0; i < this.chartData.length; i++) {
        if ((this.chartData[i].hzbCount > 0 || this.chartData[i].zxbCount > 0 || this.chartData[i].cybCount > 0) && i < this.chartData.length - 1) {
          var value = [];
          value[0] = this.chartData[i].hzbCount;
          value[1] = this.chartData[i].zxbCount;
          value[2] = this.chartData[i].cybCount;
          var tmp = {
            name : this.chartData[i].label,
            type : 'bar',
            stack : this.chartData[i].label,
            itemStyle : {
              normal : {
                color : myChartColor[i]
              },
              label : {
                show : true
              }
            },
            data : value
          };
			    series.push(tmp);
          // 细分业务标签
          itemLabel.push(this.chartData[i].label);
		    }
	    }
	    // 设置柱状图
	    this.chart.clear();
      var option = {
        calculable : false,
        legend: { // 细分业务标签
          top: 'top',
            data: itemLabel
          },
        tooltip : {
          trigger : 'axis',
          axisPointer : { // 坐标轴指示器，坐标轴触发有效
            type : 'shadow', // 默认为直线，可选为：'line' | 'shadow'
            shadowStyle : {
              opacity : 1
            }
          },
          formatter : function(params) {
            var tar = params[0];
            var str = '<p style="margin-top:10px;">' + tar.name + '</p>';
            str = str + '<p style="text-align:left;">';
            for (var i = 0; i < params.length; i++) {
              tar = params[i];
              str = str + '<span style="background:' + tar.color + '; margin-right: 10px;">&nbsp;&nbsp;</span>';
              str = str + tar.seriesName + '数量：' + tar.value + '<br/>';
            }
            str = str + '</p>';
            return str;
          }
        },
        grid : {
          top : '5%',
          left : '1%',
          right : '4%',
          bottom : '3%',
          containLabel : true
        },
        xAxis : [{
          type : 'category',
          data : labels,
          splitLine : {
            show : false
          }
        }],
        yAxis : [{
          type : 'value',
          axisLabel : {
            formatter : '{value}'
          },
          splitLine : {
            show : true
          }
        }],
        series : series
      };
      this.chart.setOption(option)
    }
  }
}
</script>
