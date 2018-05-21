//# sourceURL=ipoStatistics.js
var myChart;
var myHistoryChart;
var myChartColor = ['#C03533', '#2F4553', '#91C7AF', '#F6A631'];
$(document).ready(function() {
	eChartInit();
	historyEChartInit();
	dataInit();
	pageInit();
});
// 数据获取初始化
function dataInit() {
	// IPO在审项目数据统计
	ajaxData('/regulatory_statistics/getIPOReviewingStts', null, reviewingSttsCallBack);
	// 保荐机构统计
	ajaxData('/regulatory_statistics/getIPORecommendOrgStts', null, recommendOrgTableSetting);
	// 会计师事务所统计
	ajaxData('/regulatory_statistics/getIPOAccountantOfficeStts', null, accountantOfficeTableSetting);
	// 律师事务所统计
	ajaxData('/regulatory_statistics/getIPOLawFirmStts', null, lawFirmTableSetting);
	// 截止日期
	ajaxData('/regulatory_statistics/getIPOLastTime', null, updateTimeSetting);
	// IPO在审项目数据统计-历史
	ajaxData('/regulatory_statistics/getIPOHistory', null, historyCallBack);
}
function reviewingSttsCallBack(d) {
	// 设置柱状图
	chartSetting(d);
	// 设置表格
	reviewTableSetting(d);
}
// IPO在审项目柱状图设置
function chartSetting(lst) {
	// 设置数据
	var labels = ['沪主板', '中小板', '创业板']; // 横坐标标签
	var series = [];
	for (var i = 0; i < lst.length; i++) {
		if ((lst[i].hzbCount > 0 || lst[i].zxbCount > 0 || lst[i].cybCount > 0) && i < lst.length - 1) {
			var value = [];
			value[0] = lst[i].hzbCount;
			value[1] = lst[i].zxbCount;
			value[2] = lst[i].cybCount;
			var tmp = {
				name : lst[i].label,
				type : 'bar',
				stack : lst[i].label,
				itemStyle : {
					normal : {
						color : myChartColor[i % 4]
					},
					label : {
						show : true
					}
				},
				data : value
			};
			series.push(tmp);
		}
	}
	// 设置柱状图
	myChart.clear();
	var option = {
		calculable : false,
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
			top : '3%',
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
	myChart.setOption(option);
}
// IPO在审项目表格设置
function reviewTableSetting(lst) {
	var tbody = $('#review tbody');
	for (var i = 0; i < lst.length; i++) {
		if (lst[i].hzbCount > 0 || lst[i].zxbCount > 0 || lst[i].cybCount > 0) {
			var label = '<td class="left">' + lst[i].label + '</td>';
			if (i == lst.length - 1) {
				label = '<td style="text-align: center;">' + lst[i].label + '</td>';
			}
			var str = '<tr>' + label + '<td>' + lst[i].hzbCount + '</td>' + '<td>' + lst[i].zxbCount + '</td>' + '<td>' + lst[i].cybCount + '</td>' + '</tr>';
			tbody.append(str);
		}
	}
}
// 保荐机构统计表格设置
function recommendOrgTableSetting(lst) {
	var tbody = $('#recommendOrg tbody');
	commonTableSetting(tbody, lst);
}
// 会计师事务所统计表格设置
function accountantOfficeTableSetting(lst) {
	var tbody = $('#accountantOffice tbody');
	commonTableSetting(tbody, lst);
}
// 律师事务所统计表格设置
function lawFirmTableSetting(lst) {
	var tbody = $('#lawsfirm tbody');
	commonTableSetting(tbody, lst);
}
// 大表格通用设置
function commonTableSetting(tbody, lst) {
	for (var i = 0; i < lst.length; i++) {
		var backgroud = '';
		var no = i + 1;
		var label = '<td>' + no + '</td><td class="left">' + lst[i].label + '</td>';
		if (i == lst.length - 1) {
			label = '<td></td><td style="text-align: center;">' + lst[i].label + '</td>';
			backgroud = 'style="background: #E8E8E8;"';
		}
		var str = '<tr ' + backgroud + '>' + label + '<td>' + lst[i].hzbCount + '</td>' + '<td>' + lst[i].zxbCount + '</td>' + '<td>' + lst[i].cybCount + '</td>' + '<td>' + lst[i].totalCount
				+ '</td>' + '<td>' + lst[i].percent + '%</td>' + '</tr>';
		tbody.append(str);
	}
}
// 截止日期设置
function updateTimeSetting(d) {
	var str = '*统计数据截止至 ' + d;
	$('#updateTime').html(str);
}
// 页面控件初始化
function pageInit() {
	//
}
// 统计图初始化
function eChartInit() {
	// 柱状图
	myChart = echarts.init(document.getElementById('ipoChart'));
	myChart.on('mouseover', function(params) {
		var d = params.data;
	});
	myChart.on('mouseout', function(params) {
		var d = params.data;
	});
	myChart.on('click', function(params) {
		var d = params.data;
	});
	// 自适应
	window.onresize = myChart.resize;
}

//IPO在审项目数据-历史统计-大数据面积图-处理返回结果
function historyCallBack(result) {
	var grabUpdateTime = [];// 数据抓取表格中第二行数据的截至日期
	var hzbCount = [];// 00上海证券交易所
	var zxbCount = [];// 02深圳证券交易所(中小板)
	var cybCount = [];// 03深圳证券交易所(创业板)
	var totalCount = [];// 批次合计
	for (var prop in result) {
		grabUpdateTime.push(result[prop].value.replaceAll('-','/'));
		hzbCount.push(result[prop].hzbCount);
		zxbCount.push(result[prop].zxbCount);
		cybCount.push(result[prop].cybCount);
		totalCount.push(result[prop].totalCount);
	};
	historyChartSetting(grabUpdateTime,hzbCount,zxbCount,cybCount,totalCount);
}

// ipo历史统计图初始化
function historyEChartInit() {
	// 大数量面积图
	myHistoryChart = echarts.init(document.getElementById('ipoHistoryChart'));
	myHistoryChart.clear();
	// 自适应
	window.onresize = myHistoryChart.resize;
	// 模板图
	var historyOption = {
		    backgroundColor: 'white',
		    tooltip: {
		        trigger: 'axis',
		        position: function (pt) {
		            return [pt[0], '10%'];
		        }
		    },
		    title: {
		        left: 'center',
		        text: 'IPO再审企业统计',
		    },
		    legend: {
		        right: 'right',
		        top: 'center',
		        data:['合计','沪主板','中小板','创业板']
		    },
		    toolbox: {
		        show: true,
		        feature: {
		            dataZoom: {
		                yAxisIndex: 'none'
		            },
		            restore: {},
		            saveAsImage: {}
		        }
		    },
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
		        data: []
		    },
		    yAxis: {
		    	show: 'true',
		        type: 'value',
		        boundaryGap: [0, 0]
		    },
		    dataZoom: [{
		        type: 'inside',
		        start: 0,
		        end: 10
		    }, {
		        start: 0,
		        end: 10,
		        handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
		        handleSize: '80%',
		        handleStyle: {
		            color: '#fff',
		            shadowBlur: 3,
		            shadowColor: 'rgba(0, 0, 0, 0.6)',
		            shadowOffsetX: 2,
		            shadowOffsetY: 2
		        }
		    }],
		    series: [
		        {
		            name:'合计',
		            type:'line',
		            smooth:true,
		            symbol: 'diamond',
		            sampling: 'average',
		            itemStyle: {
		                normal: {
		                    color: 'red'
		                }
		            },
		            areaStyle: {
		                normal: {
		                    color: 'red'
		                }
		            },
		            data: []
		        },     	
		        {
		            name:'沪主板',
		            type:'line',
		            smooth:true,
		            symbol: 'circle',
		            sampling: 'average',
		            itemStyle: {
		                normal: {
		                    color: 'rgba(181,195,52,1)'
		                }
		            },
		            areaStyle: {
		                normal: {
		                    color: 'rgba(181,195,52,1)'
		                }
		            },
		            data: []
		        },
		        {
		            name:'中小板',
		            type:'line',
		            smooth:true,
		            symbol: 'rect',
		            sampling: 'average',
		            itemStyle: {
		                normal: {
		                    color: 'green'
		                }
		            },
		            areaStyle: {
		                normal: {
		                    color: 'green'
		                }
		            },
		            data: []
		        },
		        {
		            name:'创业板',
		            type:'line',
		            smooth:true,
		            symbol: 'triangle',
		            sampling: 'average',
		            itemStyle: {
		                normal: {
		                    color: 'rgba(252,206,16,0.5)'
		                }
		            },
		            areaStyle: {
		                normal: {
		                    color: 'rgba(252,206,16,0.5)'
		                }
		            },
		            data: []
		        },
		    ]
		};
	myHistoryChart.setOption(historyOption);
}

function historyChartSetting(grabUpdateTime,hzbCount,zxbCount,cybCount,totalCount) {
	myHistoryChart.setOption({
        xAxis: {
            data: grabUpdateTime
        },
        // 根据名字对应到相应的系列
	    series: [
					{
					    name:'合计',
					    data: totalCount
					},
			        {
			            name:'沪主板',
			            data: hzbCount
			        },
			        {
			            name:'中小板',
			            data: zxbCount
			        },
			        {
			            name:'创业板',
			            data: cybCount
			        }
			    ]
    });
}
