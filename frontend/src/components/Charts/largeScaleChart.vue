<template>
  <div :style="{height:height,width:width}" style="position:relative">
    <div :class="className" :id="id" :style="{height:height,width:width}"></div>
  </div>
</template>

<script>
import echarts from "echarts";

export default {
  name: "largeScaleChart",
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
    chartData2: {
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
    this.chart = null;
  },
  watch: {
    chartData2(n, o) {
      this.initChart();
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
      // 大数量面积图
      this.chart = echarts.init(document.getElementById(this.id));
      var grabUpdateTime = []; // 数据抓取表格中第二行数据的截至日期
      var hzbCount = []; // 00上海证券交易所
      var zxbCount = []; // 02深圳证券交易所(中小板)
      var cybCount = []; // 03深圳证券交易所(创业板)
      var kcCount = []; // 科创板
      this.chartData2.map((obj,p)=>{
        grabUpdateTime.push(obj.value.replace(new RegExp('-','g'), '/'));
        hzbCount.push(obj.hzbCount);
        zxbCount.push(obj.zxbCount);
        cybCount.push(obj.cybCount);
        kcCount.push(obj.kcCount);
      })
      var myChartColor = ['#ca2428', '#2e444e', '#7ccbab', '#ffa128', '#227d99', '#ff5f3a','#3badda','#DDDDDD'];
      this.chart.clear();
      // 自适应
      window.onresize = this.chart.resize;
      // 模板图
      var option = {
        backgroundColor: "white",
        tooltip: {
          trigger: "axis",
          confine: true,
          formatter: function(params) {
            var tar = params[0];
            var str = '<p style="margin-top:10px;text-align:center;">' + tar.name + "</p>";
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
        title: {
          // top: "10px",
          // left: "center",
          // text: "IPO在审企业合计数变化情况"
        },
        legend: {
          top: "-3px",
          data:['沪主板','创业板','中小板','科创板']
        },
        grid: {
          left: "3px",
          right: "30px",
          top:"20px",
          bottom: "106px",
          containLabel: true
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: grabUpdateTime
        },
        yAxis: {
          show: "true",
          type: "value",
          boundaryGap: [0, 0]
        },
        dataZoom: [
          {
            type: "inside",
            start:70,
            end: 100,
          },
          {
            top:'60%',
            start: 0,
            end: 10,
            handleIcon:
              "M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z",
            handleSize: "80%",
            handleStyle: {
              color: "#fff",
              shadowBlur: 3,
              shadowColor: "rgba(0, 0, 0, 0.6)",
              shadowOffsetX: 2,
              shadowOffsetY: 2,
            }
          }
        ],
        series: [
          {
            name: "沪主板",
            type: "line",
            stack: '总量',
            smooth: true,
            symbol: "circle",
            sampling: "average",
            itemStyle: {
              normal: {
                color: myChartColor[0]
              }
            },
            areaStyle: {normal: {}},
            data: hzbCount
          },
          {
            name: "创业板",
            type: "line",
            stack: '总量',
            smooth: true,
            symbol: "rect",
            sampling: "average",
            itemStyle: {
              normal: {
                color: myChartColor[2]
              }
            },
            areaStyle: {normal: {}},
            data: cybCount
          },
          {
            name: "中小板",
            type: "line",
            stack: '总量',
            smooth: true,
            symbol: "triangle",
            sampling: "average",
            itemStyle: {
              normal: {
                color: myChartColor[3]
              }
            },
            areaStyle: {normal: {}},
            data: zxbCount
          },
          {
            name:'科创板',
            type:'line',
            stack: '总量',
            smooth:true,
            symbol: 'diamond',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: '#14bcf5'
                }
            },
            areaStyle: {normal: {}},
            data: kcCount
          },
        ]
      };
      this.chart.setOption(option);
    }
  }
};
</script>
