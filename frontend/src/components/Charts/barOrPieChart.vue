<template>
    <div class="clear" id="chartParent">
         <div class="l" id="barChart" style="height:300px;width:55%"></div>
         <div class="l" id="pieChart" style="height:300px;width:45%"></div>
    </div>
</template>

<script>
import echarts from 'echarts'
Vue.prototype.$echarts = echarts
export default {
  data() {
    return {
      barChart:null,
      pieChart:null,
      tableTitle: null,
      tableContent: null,
      barChartY : [],
      barYY:[],
      barChartX : [],
      lengendData:[],
      pieData:[],
      date:'',
      pieChartTitle:'',
      // id:'97952444248599350',
      caseId:this.$store.state.app.caseId,
    }
  },
  props:["mainTableList"],
    mounted()  {
    //   请求数据
      this.initTableData()
  },

  beforeDestroy() {
    if (!this.barChart) {
    return
    }
    this.barChart.dispose()
    this.barChart = null
     if (!this.pieChart) {
    return
    }
    this.pieChart.dispose()
    this.pieChart = null
},
  methods: {
         // 初始化数据
      initTableData() {
          // 如果请求到数据之后再初始化柱形图
                var dataList = this.mainTableList
                 this.initBarChart(dataList);
               // 最开始初始化饼状图，默认传的是第三年的数据
                 this.initPieChart(dataList.mainIncomeInfoList.slice(0,-1),'','',dataList.onePeriodForIncome);
      },
       //   初始化柱状图
    initBarChart(dataList) {
      // debugger;
       const _self = this
        if (this.barChart) {
          this.barChart.dispose()
          this.barChart = null
        }
      this.barChart = echarts.init(document.getElementById('barChart'))

      var keyMap = {"firstYearForIncome": undefined,
                    "secondYearForIncome": undefined,
                    "thirdYearForIncome": undefined,
                    "onePeriodForIncome": undefined};

      for (var i = 0; i < dataList.mainIncomeInfoList.slice(0,-1).length; i++) {
        var a1 = dataList.mainIncomeInfoList.slice(0,-1)[i].firstYearAmount >0 ? dataList.mainIncomeInfoList.slice(0,-1)[i].firstYearAmount : undefined;
        var a2 = dataList.mainIncomeInfoList.slice(0,-1)[i].secondYearAmount >0 ? dataList.mainIncomeInfoList.slice(0,-1)[i].secondYearAmount : undefined;
        var a3 = dataList.mainIncomeInfoList.slice(0,-1)[i].thirdYearAmount >0 ? dataList.mainIncomeInfoList.slice(0,-1)[i].thirdYearAmount : undefined;
        var a4 = dataList.mainIncomeInfoList.slice(0,-1)[i].onePeriodAmount >0 ? dataList.mainIncomeInfoList.slice(0,-1)[i].onePeriodAmount : undefined;
        keyMap.firstYearForIncome = keyMap.firstYearForIncome != undefined ? keyMap.firstYearForIncome : a1 > 0 ? dataList.firstYearForIncome : undefined;
        keyMap.secondYearForIncome = keyMap.secondYearForIncome != undefined ? keyMap.secondYearForIncome : a2 > 0 ? dataList.secondYearForIncome : undefined;
        keyMap.thirdYearForIncome = keyMap.thirdYearForIncome != undefined ? keyMap.thirdYearForIncome : a3 > 0 ? dataList.thirdYearForIncome : undefined;
        keyMap.onePeriodForIncome = keyMap.onePeriodForIncome != undefined ? keyMap.onePeriodForIncome : a4 > 0 ? dataList.onePeriodForIncome : undefined;
      }

    //  循环获取柱状图数据
      for (var i = 0; i < dataList.mainIncomeInfoList.slice(0,-1).length; i++) {
          this.lengendData.push(dataList.mainIncomeInfoList.slice(0,-1)[i].businessName);
          this.barYY = [];
          var a1 = dataList.mainIncomeInfoList.slice(0,-1)[i].firstYearAmount >0 ? dataList.mainIncomeInfoList.slice(0,-1)[i].firstYearAmount : undefined;
          var a2 = dataList.mainIncomeInfoList.slice(0,-1)[i].secondYearAmount >0 ? dataList.mainIncomeInfoList.slice(0,-1)[i].secondYearAmount : undefined;
          var a3 = dataList.mainIncomeInfoList.slice(0,-1)[i].thirdYearAmount >0 ? dataList.mainIncomeInfoList.slice(0,-1)[i].thirdYearAmount : undefined;
          var a4 = dataList.mainIncomeInfoList.slice(0,-1)[i].onePeriodAmount >0 ? dataList.mainIncomeInfoList.slice(0,-1)[i].onePeriodAmount : undefined;
          if (keyMap.firstYearForIncome != undefined) {
            this.barYY.push(a1)
          }
          if (keyMap.secondYearForIncome != undefined) {
            this.barYY.push(a2)
          }
          if (keyMap.thirdYearForIncome != undefined) {
            this.barYY.push(a3)
          }
          if (keyMap.onePeriodForIncome != undefined) {
            this.barYY.push(a4)
          }
          var barBusinessName;
            barBusinessName = dataList.mainIncomeInfoList.slice(0,-1)[i].businessName
          this.barChartY.push(
                                {
                                    name:barBusinessName,
                                    type:'bar',
                                    barWidth:'50%',
                                    stack: '总量',
                                    data:this.barYY,
                                },
                             )

               }
      // this.barChartX = [dataList.firstYearForIncome,dataList.secondYearForIncome,dataList.thirdYearForIncome,dataList.onePeriodForIncome]
      if (keyMap.firstYearForIncome != undefined){
        this.barChartX.push(keyMap.firstYearForIncome);
      }
      if (keyMap.secondYearForIncome != undefined){
        this.barChartX.push(keyMap.secondYearForIncome);
      }
      if (keyMap.thirdYearForIncome != undefined){
        this.barChartX.push(keyMap.thirdYearForIncome);
      }
      if (keyMap.onePeriodForIncome != undefined){
        this.barChartX.push(keyMap.onePeriodForIncome);
      }

   // 点击柱状图获取相应数据
      this.barChart.on("click", function(params) {
        // debugger
        // 获取整条柱子的信息
        var series = this.getOption().series
        // 点击柱子初始化饼状图
        _self.initPieChart(series,params.name,params.dataIndex,'');
      });
       this.barChart.setOption({
            color:[
                '#ea5365', '#f9b162', '#36a2eb', '#4ec8e5', '#f98962',
                '#8780e4', '#d192e0', '#ed7ae2', '#aabfe2', '#5b6878',
            ],
           title: {
                text: "最近3年主营业务趋势",
                padding: [8, 10,106,10],
                bottom:175,
                textStyle: {
                    color: "#333333",
                    fontWeight: "bold",
                    fontSize: 16
                }
                },
            tooltip: {
                trigger: "axis",
                position:function (point, params, dom, rect, size) {
                  dom.style.position = 'fixed';
                  let pos;
                  let posY;
                  let posX;
                  if ((window.navigator.userAgent.toLowerCase().indexOf("trident") > -1 && window.navigator.userAgent.indexOf("rv") > -1)) {
                    pos = document.getElementById("chartParent").getClientRects()[0];
                    posX = pos.left + point[0] + 20;
                    posY = pos.top + point[1];
                  }else {
                    pos = document.getElementById("chartParent").getBoundingClientRect();
                    posY = pos.y + point[1];
                    posX = pos.x + point[0] + 20;
                  }
                  return [posX, posY];
                },
                axisPointer: {
                    type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
                }

            },
            grid: {
                left: "3%",
                right: "4%",
                bottom: "3%",
                containLabel: true
            },
            xAxis: {
                type: 'category',
                data:this.barChartX
            },
           yAxis: {
                type: "value",
                name:'万元',
                nameLocation:'end',
                nameTextStyle:{
                  padding: [0, 30, 0, 0]
                }
                },
                series: this.barChartY
            })
    },
    // 饼形图
initPieChart(dataList,nameTempO,num,flag) {
  this.pieData = [];
  this.pieChart = this.$echarts.init(document.getElementById("pieChart"));
   var nameTemp = ''
  //  用nameTempO这个标识来实现点击柱子切换标题
  if(nameTempO){
    nameTemp = nameTempO;
  }
  // debugger
  // 用flag这个标识来区别是【初始化的数据】的饼状图还是【点击切换】的饼状图
  if(flag){
    // 默认饼状图展示最后一年的数据
    nameTemp = flag;
    for (var i = 0; i < dataList.length; i++) {
      var obj = {
        name:"",
        value:""
      };
        obj.name = dataList[i].businessName
        if(dataList[i].onePeriodAmount<0){
          obj.value = 0
        }else{
          obj.value = dataList[i].onePeriodAmount;
        }
      this.pieData.push(obj);
    }
  }else{
    // 点击柱子切换饼状图的数据源
    for (var i = 0; i < dataList.length; i++) {
      var obj = {
        name:"",
        value:""
      };
      obj.name = dataList[i].name
      if(dataList[i].data[num]<0){
          obj.value = 0
        }else{
          obj.value = dataList[i].data[num];
        }
      // obj.value = dataList[i].data[num];
      this.pieData.push(obj);
    }
  }
  var option = {
         color:[
                '#ea5365', '#f9b162', '#36a2eb', '#4ec8e5', '#f98962',
                '#8780e4', '#d192e0', '#ed7ae2', '#aabfe2', '#5b6878',
            ],
         title: {
                text: "• "+nameTemp+" _ 主营业务分布",
                textStyle: {
                    color: "#333",
                    fontWeight: "bold",
                    fontSize: 14
                },
                left: '10%',
                top:'10px',
                // padding:'60%',
         },
        tooltip: {
          trigger: "item",
          position:function (point, params, dom, rect, size) {
                  dom.style.position = 'fixed';
                  let pos;
                  let posY;
                  let posX;
                  if ((window.navigator.userAgent.toLowerCase().indexOf("trident") > -1 && window.navigator.userAgent.indexOf("rv") > -1)) {
                    pos = document.getElementById("chartParent").getClientRects()[0];
                    posX = pos.left + point[0] + 20;
                    posY = pos.top + point[1];
                  }else {
                    pos = document.getElementById("chartParent").getBoundingClientRect();
                    posY = pos.y + point[1];
                    posX = pos.x + point[0] + 200;
                  }
                  return [posX, posY];
                },
           //百分比显示，模板变量有 {a}、{b}、{c}、{d}，分别表示系列名，数据名，数据值，百分比。{d}数据会根据value值计算百分比
          formatter: "{a}{b} : {c} ({d}%)"
        },
        series: [
          {
            name: "",
            type: "pie",
            radius: "50%",
            center: ["55%", "50%"],
            data:this.pieData,
            label: {
              formatter: function (params) {
                let str = params.name;
                if(str.length>7){
                  str = str.substring(0, 6).concat('...');
                }
                return str;
              }
            },
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)"
              }
            }
          }
        ]
			}
      this.pieChart.setOption(option)
}
  }
}
</script>
<style scoped lang="scss">
.l {
  float: left;
}
.r {
  float: right;
}
.clear:after {
  display: block;
  content: "";
  clear: both;
}
</style>

