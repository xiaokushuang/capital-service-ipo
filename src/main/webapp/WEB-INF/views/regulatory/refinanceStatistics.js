//# sourceURL=refinanceStatistics.js
var myChart;
var myChartColor = ['#C03533', '#2F4553', '#91C7AF', '#F6A631'];
$(document).ready(function() {
	eChartInit();
	dataInit();
	pageInit();
});
// 数据获取初始化
function dataInit() {
	// 发行监管部再融资企业审核状态
	ajaxData('/regulatory_statistics/getRefinanceApproveStatusStts', null, refinanceSttsCallBack);
	// 再融资申请类型情况
	ajaxData('/regulatory_statistics/getRefinanceAppTypeStts', null, approveTypeTableSetting);
	// 发行监管部再融资审核保荐机构保荐企业数量
	ajaxData('/regulatory_statistics/getRefinanceRecommendOrgStts', null, recommendOrgTableSetting);
	// 截止日期
	ajaxData('/regulatory_statistics/getRefinanceLastTime', null, updateTimeSetting);
}
function refinanceSttsCallBack(d) {
	// 设置柱状图
	chartSetting(d);
}
// 发行监管部再融资企业审核状态柱状图设置
function chartSetting(lst) {
	// 设置数据
	var values = new Array();
	var labels = new Array();
	for (var i = 0; i < lst.length; i++) {
		if (lst[i].totalCount > 0) {
			values.push(lst[i].totalCount);
			labels.push(lst[i].label);
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
		series : [{
			name : '企业',
			type : 'bar',
			stack : '',
			barWidth : 50,
			itemStyle : {
				normal : {
					color : function(params) {
						return myChartColor[params.dataIndex % 4];
					}
				},
				label : {
					show : true
				}
			},
			data : values
		}]
	};
	myChart.setOption(option);
}
// 再融资申请类型情况表格设置
function approveTypeTableSetting(lst) {
	var tbody = $('#approveType tbody');
	for (var i = 0; i < lst.length; i++) {
		if (lst[i].totalCount > 0) {
			var str = '<tr>' + '<td>' + lst[i].label + '</td>' + '<td>' + lst[i].totalCount + '</td>' + '</tr>';
			tbody.append(str);
		}
	}
}
// 发行监管部再融资审核保荐机构保荐企业数量表格设置
function recommendOrgTableSetting(lst) {
	var tbody = $('#recommendOrg tbody');
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
		var str = '<tr ' + backgroud + '>' + label + '<td>' + lst[i].szbCount + '</td>' + '<td>' + lst[i].hzbCount + '</td>' + '<td>' + lst[i].zxbCount + '</td>' + '<td>' + lst[i].cybCount + '</td>'
				+ '<td>' + lst[i].totalCount + '</td>' + '<td>' + lst[i].percent + '%</td>' + '</tr>';
		tbody.append(str);
	}
}
// 截止日期设置
//function updateTimeSetting(d) {
//	var str = '*统计数据截止至 ' + d;
//	$('#updateTime').html(str);
//}
// 页面控件初始化
function pageInit() {
	//
}
// 统计图初始化
function eChartInit() {
	// 柱状图
	myChart = echarts.init(document.getElementById('refinanceChart'));
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
