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
    },
  },
  data() {
    return {
      chart: null,
      defaultIpo: this.ipodata || [],
      arrayData:[],//发行家数
    };
  },
  mounted() {
    setTimeout(function (){
      window.onresize = function () {
        let chart = echarts.init(document.querySelector('#one'));
        chart.resize();
      }
    },200);
    this.chart = null;
  },
  watch: {
    chartData(n, o) {
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
    addData(data1,data2) {//数组相加
      let data = [];
      data1.map(function(v, i) {
          data.push(v + data2[i]);
      });
      return data;
    },
    initChart() {
      //const chartData = [{"date":"2017-05-11至2017-05-31","dataSum":[{"num":185,"name":"002","value":326.4369},{"num":20,"name":"001","value":75.4991}],"dataNum":{"number":205}},{"date":"2017-06-01至2017-06-30","dataSum":[{"num":218,"name":"002","value":644.1684},{"num":36,"name":"001","value":171.325},{"num":3,"name":"003","value":45.82}],"dataNum":{"number":257}},{"date":"2017-07-01至2017-07-31","dataSum":[{"num":297,"name":"002","value":610.1968},{"num":36,"name":"001","value":195.8909}],"dataNum":{"number":333}},{"date":"2017-08-01至2017-08-31","dataSum":[{"num":251,"name":"002","value":935.2371},{"num":34,"name":"001","value":168.3949},{"num":2,"name":"003","value":32.9971}],"dataNum":{"number":287}},{"date":"2017-09-01至2017-09-30","dataSum":[{"num":266,"name":"002","value":884.3848},{"num":36,"name":"001","value":171.6571}],"dataNum":{"number":302}},{"date":"2017-10-01至2017-10-31","dataSum":[{"num":189,"name":"002","value":1652.14},{"num":30,"name":"001","value":167.671}],"dataNum":{"number":219}},{"date":"2017-11-01至2017-11-30","dataSum":[{"num":240,"name":"002","value":932.9688},{"num":30,"name":"001","value":202.0221}],"dataNum":{"number":270}},{"date":"2017-12-01至2017-12-31","dataSum":[{"num":315,"name":"002","value":1710.6563},{"num":18,"name":"001","value":127.0581},{"num":1,"name":"003","value":35.6256}],"dataNum":{"number":334}},{"date":"2018-01-01至2018-01-31","dataSum":[{"num":192,"name":"002","value":716.6086},{"num":19,"name":"001","value":234.7241},{"num":3,"name":"003","value":39.5368}],"dataNum":{"number":214}},{"date":"2018-02-01至2018-02-28","dataSum":[{"num":96,"name":"002","value":1276.971},{"num":8,"name":"001","value":111.9098},{"num":2,"name":"003","value":29.7066}],"dataNum":{"number":106}},{"date":"2018-03-01至2018-03-31","dataSum":[{"num":267,"name":"002","value":847.0619},{"num":10,"name":"001","value":51.682},{"num":3,"name":"003","value":18.4641}],"dataNum":{"number":280}},{"date":"2018-04-01至2018-04-30","dataSum":[{"num":235,"name":"002","value":554.0299},{"num":18,"name":"001","value":70.8219},{"num":2,"name":"003","value":47.056}],"dataNum":{"number":255}},{"date":"2018-05-01至2018-05-10","dataSum":[{"num":47,"name":"002","value":68.9331},{"num":5,"name":"001","value":34.0905}],"dataNum":{"number":52}}]
      // 日期数组
      var dateArr = [];
      // IPO
      var array1 = [];
      // 增发
      var array2 = [];
      // 配股
      var array3 = [];
      // 发行家数
      var array4 = [];
      var array5 = [];//ipo下的发行家数
      var array6 = [];//增发下的发行家数
      var array7 = [];//配股下的发行家数

      this.chartData.map(function(obj) {
        dateArr.push(obj.date);
        array4.push(obj.dataNum.number);
        if (obj.dataSum.length != 0) {
          var a = 0,
            b = 0,
            c = 0;
          obj.dataSum.map(function(idx) {
            // 001 为IPO
            if (idx.name == "001") {
              array1.push(idx.value);
              array5.push(idx.num);
              a = 1;
            }
            // 002 为增发
            if (idx.name == "002") {
              array2.push(idx.value);
              array6.push(idx.num);
              b = 2;
            }
            // 003 为配股
            if (idx.name == "003") {
              array3.push(idx.value);
              array7.push(idx.num);
              c = 3;
            }
          });
          // 区别哪个类型没有拿到值，没有值为0，为了之后统计全部的和。
          if (a == 0) {
            array1.push(0);
            array5.push(0);
          }
          if (b == 0) {
            array2.push(0);
            array6.push(0);
          }
          if (c == 0) {
            array3.push(0);
            array7.push(0);
          }
        } else {
          array1.push(0);
          array2.push(0);
          array3.push(0);
          array5.push(0);
          array6.push(0);
          array7.push(0);
        }
      });
      this.arrayData = array4;
      this.chart = echarts.init(document.getElementById(this.id));
      const xData = (function() {
        const data = [];
        for (let i = 1; i < 13; i++) {
          data.push(i + "month");
        }
        return data;
      })();
      // this.chart.setOption({
      var chartOption = {
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow" // 默认为直线，可选为：'line' |
            // 'shadow'
          },
          padding: [10, 10, 10, 20],
          textStyle: {
            align: "left"
          }
        },
        legend: {
          x: "right",
          selected: {
            IPO: true,
            增发: true,
            配股: true,
            发行家数:true
          },
          data: ["IPO", "增发", "配股", "发行家数"]
        },
        grid: {
          // left: "6%",
          // right: "6%",
          bottom: "7%",
          // containLabel: true
        },
        yAxis: [
          {
            type: "value",
            name: "发行金额（亿元）",
            /*min : 0,
								max : 20000,
								interval : 2000,*/
            axisLabel: {
              formatter: function (value, index) {  //y轴千分位格式化
                return value.toString().replace(/(\d{1,3})(?=(\d{3})+$)/g,'$1,');      
              }  
            }
          },
          {
            type: "value",
            name: "发行家数",
            /*min : 0,
									max : 5000,
									interval : 500,*/
            axisLabel: {
              formatter: function (value, index) {  //y轴千分位格式化
                return value.toString().replace(/(\d{1,3})(?=(\d{3})+$)/g,'$1,');      
              }
            }
          }
        ],
        xAxis: {
          type: "category",
          axisTick: {
            alignWithLabel: true
          },
          nameLocation: "center",
          nameTextStyle: {
            align: "center"
          },
          data: dateArr
        },
        series: [
          {
            name: "IPO",
            type: "bar",
            stack: "总量",
            label: {
              normal: {
                show: false,
                position: "insideRight"
              }
            },
            data: array1
          },
          {
            name: "增发",
            type: "bar",
            stack: "总量",
            label: {
              normal: {
                show: false,
                position: "insideRight"
              }
            },
            data: array2
          },
          {
            name: "配股",
            type: "bar",
            stack: "总量",
            label: {
              normal: {
                show: false,
                position: "insideRight"
              }
            },
            data: array3
          },
          {
            name: "发行家数",
            type: "line",
            symbolSize: 5,
            smooth: false,
            showAllSymbol: true,
            yAxisIndex: 1,
            label: {
              normal: {
                show: true
              }
            },
            data: array4
          }
        ]
      }
      this.chart.setOption(chartOption);
      // });
      const _this = this;
      _this.chart.on('legendselectchanged', function (event) {//点击按钮,改变发行家数数据,重新计算数组
        if(event.name != '发行家数') {
          var str1 = 'IPO';
			    var str2 = '增发';
			    var str3 = '配股';
          var str4 = '发行家数';
          var arrayNew = [];
          if(!event.selected[str1] && !event.selected[str2] && !event.selected[str3]) {//都没选中时,设置全为0数组
            chartOption["legend"]["selected"][str1] = false;
            chartOption["legend"]["selected"][str2] = false;
            chartOption["legend"]["selected"][str3] = false;
            arrayNew = Array(_this.arrayData.length).fill(0);
          } else {
            if(event.selected[str1]){
              arrayNew = array5;
              chartOption["legend"]["selected"][str1] = true;
            }else{
              chartOption["legend"]["selected"][str1] = false;
            }
  
            if(event.selected[str2]){
              if(arrayNew.length == 0) {
                arrayNew = array6;
              } else {
                arrayNew = _this.addData(arrayNew,array6);
              }
              chartOption["legend"]["selected"][str2] = true;
            }else{
              chartOption["legend"]["selected"][str2] = false;
            } 
  
            if(event.selected[str3]){
              if(arrayNew.length == 0) {
                arrayNew = array7;
              } else {
                arrayNew = _this.addData(arrayNew,array7);
              }
              chartOption["legend"]["selected"][str3] = true;
            }else{
              chartOption["legend"]["selected"][str3] = false;
            }
          }
          chartOption['series'][3]['data'] = arrayNew;
          _this.chart.setOption(chartOption);
        }
      });
    }
  }
};
</script>
