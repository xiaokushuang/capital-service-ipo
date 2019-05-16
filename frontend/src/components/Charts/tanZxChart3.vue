<template>
    <div>
         <div class="className" id="tanZxChart3" style="height:300px;width:100%"></div>        
    </div>
</template>

<script>
import echarts from 'echarts'
// 引入点击放大镜弹出来的表头年限数据
import { getSelectMainIndexList } from '@/api/ipoCase/tableDemo'
export default {
  name:'tanZxChart3',
  data() {
    return {
        tableTitle:null,
        zxChartX:[],
        zxChartY:[],
        caseId:this.$store.state.app.caseId,
        unitName:''
    }
  },
  props:{
    zxChartData:{
      type:Object,
      default:10
    },
    zxChartTitle:{
      type:Object,
      default:10
    },
  },
  mounted(){
    this.$nextTick(()=>{
      this.initTableData()
    })
  },
  methods: {
    unitNameMethods(){
        if(this.zxChartData.itemName=='流动比率' || this.zxChartData.itemName=='速动比率'){
            this.unitName = '(倍)'
        }
         if(this.zxChartData.itemName=='资产负债率' || this.zxChartData.itemName=='无形资产占净资产的比例' || this.zxChartData.itemName=='加权平均净资产收益率' ){
            this.unitName = '(%)'
        }
         if(this.zxChartData.itemName=='应收账款周转率'){
            this.unitName = '(次)'
        }
         if(this.zxChartData.itemName=='息税折旧摊销前利润/负债合计'){
            this.unitName = '(万元)'
        }
         if(this.zxChartData.itemName=='基本每股收益' || this.zxChartData.itemName=='扣除非经常性损益后基本每股收益'){
            this.unitName = '(元/股)'
        }
    },
    initChart() {
      this.unitNameMethods()
      let tanZxChart3 = echarts.init(document.getElementById('tanZxChart3'))
       tanZxChart3.setOption({
           title: {
                text: ''
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                data:this.zxChartX
            },
            
            yAxis: {
                      type: 'value',
                      name:this.unitName,
                      nameLocation:'end',
                      nameGap:'20',
                      nameTextStyle:{
                        padding: [0, 48, 0, 0]
                      }
                    },
            series:this.zxChartY
      })
    },
      // 初始化数据
    initTableData() {
        this.zxChartX =[this.zxChartTitle.firstYearDate,this.zxChartTitle.secondYearDate,this.zxChartTitle.thirdYearDate,this.zxChartTitle.forthYearDate]
        this.zxChartY = [
                      {
                          name:this.zxChartData.itemName,
                          type:'line',
                          data:[this.zxChartData.firstYearValue, this.zxChartData.secondYearValue, this.zxChartData.thirdYearValue,this.zxChartData.forthYearValue],
                          areaStyle: {
                            shadowColor: ['#ffefeb','#fff8f6'],
                            opacity: 0.1
                          }
                      },
                  ]
            this.initChart()
    },
  },
  // 动态实时监听刷新折线图的数据
  watch:{
    zxChartData: {  
　　　　handler(newValue, oldValue) {  
        this.zxChartData = newValue
        //折线图数据的初始化 
        this.initTableData()
　　　　},  

　　　　deep: true,  //对象内部的属性监听，也叫深度监听
       immediate: true //immediate表示在watch中首次绑定的时候，是否执行handler，值为true则表示在watch中声明的时候，就立即执行handler方法，值为false，则和一般使用watch一样，在数据发生变化的时候才执行handler
　　},
    zxChartTitle: {  
　　　　handler(newValue, oldValue) {  
        this.zxChartTitle = newValue
        //折线图数据的初始化 
        this.initTableData()
　　　　},  
　　　　deep: true,  //对象内部的属性监听，也叫深度监听
       immediate: true //immediate表示在watch中首次绑定的时候，是否执行handler，值为true则表示在watch中声明的时候，就立即执行handler方法，值为false，则和一般使用watch一样，在数据发生变化的时候才执行handler
　　}    
  },
}
</script>
