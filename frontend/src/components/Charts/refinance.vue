<template>
  <div :class="className" :id="id" :style="{height:height,width:width}"></div>
</template>

<script>
import echarts from "echarts";

export default {
  //props: ['className','id','width','height','chartData'],
  name: "chartTwo",
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
    this.initChart();
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
      this.chart = echarts.init(document.getElementById(this.id));
      var myChartColor = ['#C03533', '#2F4553', '#91C7AF', '#F6A631'];
      // 设置数据
      var values = new Array()
      var labels = new Array()
      this.chartData.map(function(obj) {
        if (obj.totalCount > 0) {
          values.push(obj.totalCount);
          labels.push(obj.label);
        }
      }),
      // 设置柱状图
      this.chart.clear();
      var option = {
        calculable: false,
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow", // 默认为直线，可选为：'line' | 'shadow'
            shadowStyle: {
              opacity: 1
            }
          },
          formatter: function(params) {
            var tar = params[0];
            var str = '<p style="margin-top:10px;">' + tar.name + "</p>";
            str = str + '<p style="text-align:left;">';
            for (var i = 0; i < params.length; i++) {
              tar = params[i];
              str =
                str +
                '<span style="background:' +
                tar.color +
                '; margin-right: 10px;">&nbsp;&nbsp;</span>';
              str = str + tar.seriesName + "数量：" + tar.value + "<br/>";
            }
            str = str + "</p>";
            return str;
          }
        },
        grid: {
          top: "3%",
          left: "1%",
          right: "4%",
          bottom: "3%",
          containLabel: true
        },
        xAxis: [
          {
            type: "category",
            data: labels,
            splitLine: {
              show: false
            }
          }
        ],
        yAxis: [
          {
            type: "value",
            axisLabel: {
              formatter: "{value}"
            },
            splitLine: {
              show: true
            }
          }
        ],
        series: [
          {
            name: "企业",
            type: "bar",
            stack: "",
            barWidth: 50,
            itemStyle: {
              normal: {
                color: function(params) {
                  return myChartColor[params.dataIndex % 4];
                }
              },
              label: {
                show: true
              }
            },
            data: values
          }
        ]
      };
      this.chart.setOption(option);
    }
  }
};
</script>
