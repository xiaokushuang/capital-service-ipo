<template>
  <div :class="className" :id="id" :style="{height:height,width:width}"></div>
</template>

<script>
import echarts from "echarts";

export default {
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
    initChart() {
      // 设置数据
      this.chart = echarts.init(document.getElementById(this.id));
      var myChartColor = ['#ca2428', '#2e444e', '#7ccbab', '#ffa128', '#227d99', '#ff5f3a','#3badda','#DDDDDD'];
      var newArray = new Array(7);
      this.chartData.map((obj,idx)=>{
        if(obj.label == "已受理"){
          newArray[0]= obj;
        }
        if(obj.label == "已反馈"){
          newArray[1]= obj;
        }
        if(obj.label == "预先披露更新"){
          newArray[2]= obj;
        }
        if(obj.label == "已通过发审会"){
          newArray[3]= obj;
        }
        if(obj.label == "中止审查"){
          newArray[4]= obj;
        }
        if(obj.label == "已提交发审会讨论，暂缓表决"){
          newArray[5]= obj;
        }
        if(obj.label == "提交注册"){
          newArray[6]= obj;
        }
        /*if(obj.label == "注册生效"){
          newArray[7]= obj;
        }*/
      });
      var lst  = new Array(7);
      lst = newArray;
      // 设置数据
      var labels = ['沪主板', '中小板', '创业板',{
          value: '科创板',
          textStyle: {
              color: '#0099cc'
          }
      },]; // 横坐标标签
    	// 细分业务标签
      var itemLabel = [];
      var series = [];
      for (var i = 0; i < lst.length; i++) {
        if ((lst[i].hzbCount > 0 || lst[i].zxbCount > 0 || lst[i].cybCount > 0 || lst[i].kcCount >= 0) && i <= lst.length - 1) {
          if(lst[i].label!='终止审查' && lst[i].label!="预披露更新" && lst[i].label!="已上发审会，暂缓表决"){
            lst[i].label = this.lableTurnToName(lst[i].label);
            var value = [];
            value[0] = lst[i].hzbCount;
            value[1] = lst[i].zxbCount;
            value[2] = lst[i].cybCount;
            value[3] = lst[i].kcCount;
            var tmp = {
              name : lst[i].label,
              type : 'bar',
              stack : lst[i].label,
              itemStyle : {
                normal : {
    //							color : myChartColor[i % 4]
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
            //var lable = lableTurnName(lst[i].label);
            itemLabel.push(lst[i].label);
          }
        }
      }
      // 设置柱状图
      this.chart.clear();
      var option = {
        calculable : false,
        legend: { // 细分业务标签
          top: 'top',
              data: itemLabel,
              /*textStyle: {
                    color: "#0099cc"
                }*/
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
            var str = '<p style="margin-top:10px;text-align:center;">' + tar.name + '</p>';
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
          //top : '5%',标签过多换行重叠表格
          left : '1%',
          right : '4%',
          top : '25%',
          bottom:'0%',
          containLabel : true
        },
        xAxis : [{
          type : 'category',
          data : labels,
          splitLine : {
            show : false
          }/*,
          axisLabel: {
            show: true,
            textStyle: {
                        color: "#0099cc"
                    }
                }*/
        }
        ],
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
      this.chart.setOption(option);
    },
    lableTurnToName(lable){
      if(lable=="已受理"){
        lable = "已受理(已受理)";
      }
      if(lable=="已反馈"){
        lable = "已反馈(已问询)";
      }
      if(lable=="已通过发审会"){
        lable = "已通过发审会(上市委会议通过)";
      }
      if(lable=="中止审查"){
        lable = "中止审查(中止)";
      }
      if(lable=="注册生效"){
        lable = "注册生效";
      }
      if(lable=="提交注册"){
        lable = "提交注册";
      }
      return lable;
    }
  }
}
</script>
