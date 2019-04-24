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
    setTimeout(function (){
      window.onresize = function () {
        let chart = echarts.init(document.querySelector('#two'));
        chart.resize();
      }
    },200);
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
      // document.getElementById('childrenIndTab1').innerHTML = ''
      // var dateArr = [];
      // IPO
      // var array1 = [];
      // 增发
      // var array2 = [];
      // 配股
      // var array3 = [];
      // 发行家数
      // var array4 = [];
      // this.chartData.map(function(obj) {
      // 	// dateArr.push(obj.date);
      // 	// array4.push(obj.dataNum.number);
      // 	if (obj.dataSum.length != 0) {
      // 		var a = 0, b = 0, c = 0;
      // 		obj.dataSum.map(function(idx) {
      // 			// 001 为IPO
      // 			if (idx.name == '001') {
      // 				array1.push(idx.value);
      // 				a = 1;
      // 			}
      // 			// 002 为增发
      // 			if (idx.name == '002') {
      // 				array2.push(idx.value);
      // 				b = 2;
      // 			}
      // 			// 003 为配股
      // 			if (idx.name == '003') {
      // 				array3.push(idx.value);
      // 				c = 3;
      // 			}
      // 		})
      // 		// 区别哪个类型没有拿到值，没有值为0，为了之后统计全部的和。
      // 		if (a == 0) {
      // 			array1.push(0);
      // 		}
      // 		if (b == 0) {
      // 			array2.push(0);
      // 		}
      // 		if (c == 0) {
      // 			array3.push(0);
      // 		}
      // 	} else {
      // 		array1.push(0);
      // 		array2.push(0);
      // 		array3.push(0);
      // 	}
      // })

      this.chart = echarts.init(document.getElementById(this.id));
      const xData = function() {
        const data = [];
        for (let i = 1; i < 13; i++) {
          data.push(i + "month");
        }
        return data;
      };
      try {
        this.chart.setOption({
          title: {
            text: "",
            subtext: "",
            x: "right"
          },
          tooltip: {
            trigger: "item",
            formatter: function(a) {
              return (
                a["data"]["name"] +
                "</br>家数:" +
                a["data"]["num"] +
                "</br>金额:" +
                a["data"]["value"] +
                "(" +
                a["percent"] +
                "%)"
              );
            },
            padding: [10, 10, 10, 10],
            textStyle: {
              align: "left"
            }
          },
          series: [
            {
              name: "",
              type: "pie",
              radius: "55%",
              center: ["50%", "50%"],
              selectedMode: "single",
              selectedOffset: 30,
              clockwise: true,
              data: this.chartData[0].dataSum,
              itemStyle: {
                emphasis: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: "rgba(0, 0, 0, 0.5)"
                }
              }
            }
          ]
        });
      } catch (e) {}
    }
  }
};
</script>
