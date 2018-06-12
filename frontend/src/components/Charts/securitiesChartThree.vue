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
      const xData = function() {
        const data = [];
        for (let i = 1; i < 13; i++) {
          data.push(i + "month");
        }
        return data;
      };
      this.chart.setOption({
        backgroundColor: "#fff",
        tooltip: {
          trigger: "item",
          formatter: function(a) {
            if (a["data"] != undefined) {
              if (
                a["data"]["value"] != undefined &&
                a["data"]["num"] != undefined
              ) {
                return (
                  a["data"]["name"] +
                  "</br>金额:" +
                  a["data"]["value"] +
                  "</br>家数:" +
                  a["data"]["num"]
                );
              } else {
                return a["data"]["name"] + "</br>金额: 0" + "</br>家数: 0";
              }
            }
          },
          padding: [10, 10, 10, 10],
          textStyle: {
            align: "left"
          }
        },
        geo: {
          map: "china",
          label: {
            emphasis: {
              show: false
            }
          },
          roam: false,
          selectedMode: "single",
          itemStyle: {
            normal: {
              areaColor: "#f6efa6",
              borderColor: "#fff"
            },
            emphasis: {
              areaColor: "#BD3B3A"
            }
          }
        },
        visualMap: {
          min: 0,
          max: 2000,
          left: "left",
          top: "bottom",
          text: ["高", "低"], // 文本，默认为数值文本
          calculable: true
        },
        series: [
          {
            type: "effectScatter",
            coordinateSystem: "geo",
            // data: this.chartData[0].dataSum
            symbolSize: function(val) {
              return 20;
            },
            hoverAnimation: false,
            label: {
              normal: {
                formatter: "{value}",
                position: "right",
                show: true
              }
            },
            itemStyle: {
              normal: {
                color: "#f9a183"
              }
            },
            zlevel: 1,
            roam: false,
            tooltip: {
              show: false
            }
          },
          {
            name: "name",
            type: "map",
            geoIndex: 0,
            data: this.chartData[0].dataSum
          }
        ]
      });
    }
  }
};
</script>
