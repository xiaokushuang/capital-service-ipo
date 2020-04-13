<template>
  <div :class="className" :id="id" :style="{height:height,width:width}"></div>
</template>

<script>
import echarts from 'echarts'

export default {
    //props: ['className','id','width','height','chartData'],
    name:'chartTwo',
	props:{
		className:{
			type:String,
			default:'chart'
		},
		id:{
			type:String,
			default:'id'
		},
		width:{
			type:String,
			default:'100%'
		},
		height:{
			type:String,
			default:'100%'
		},
		chartData:{
			type:Array,
			default:()=>[]
		},
	},
  data() {
    return {
			chart:null,
			defaultIpo:this.ipodata||[]
    }
  },
  mounted() {
    //this.initChart()
		this.chart = null
		//console.log(this.chartData)
	},
	watch:{
		chartData(n,o){
			this.initChart()
			// console.log('饼图的---------------数据变了')
			//console.log(this.chartData)
		}
	},
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
        var _this = this;
        var chartOption = {
			    title : {
			        text: '',
			        subtext: '',
			        x:'right'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: function(a){
                        return (a['data']['name']  
                        		+'</br>家数:'+a['data']['num']
                               	+'</br>金额:'+a['data']['value']+'('+a['percent']+'%)'
                               );  
                    },
				    padding: [10,10,10,10],
			        textStyle:{
			        	align:'left'
			        }
			    },
			    series : [
			        {
			            name: '',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '50%'],
			            selectedMode: 'single',
			            selectedOffset: 30,
			            clockwise: true,
			            data:_this.chartData,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
						
        this.chart = echarts.init(document.getElementById(this.id))
        this.chart.setOption(chartOption);
        // console.log(this.chartData)
    }
  }
}
</script>
