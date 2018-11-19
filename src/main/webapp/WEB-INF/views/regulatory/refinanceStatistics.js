//# sourceURL=refinanceStatistics.js
var myChart;
var myChartColor = ['#C03533', '#2F4553', '#91C7AF', '#F6A631'];
$(document).ready(function() {
	eChartInit();
	dataInit();
	pageInit();
	
	// demand 5955 start
	tSelectDataInit();
	// demand 5955 end
});

function initDataTableParam(d){
	return $.extend( {}, d, {
	        "access_token": $('#tokenValue').val()
	      } );
}

// 数据获取初始化
function dataInit() {
	// 发行监管部再融资企业审核状态
	ajaxData('/regulatory_statistics/getRefinanceApproveStatusStts', null, refinanceSttsCallBack);
	// 再融资申请类型情况
	ajaxData('/regulatory_statistics/getRefinanceAppTypeStts', null, approveTypeTableSetting);
	// 发行监管部再融资审核保荐机构保荐企业数量
//	ajaxData('/regulatory_statistics/getRefinanceRecommendOrgStts', null, recommendOrgTableSetting);
	// 截止日期
//	ajaxData('/regulatory_statistics/getRefinanceLastTime', null, updateTimeSetting);
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
		var str = '<tr ' + backgroud + '>' + label + '<td><a href="javascript:void(0)" onclick="viewRefinanceDetail(\'' + lst[i].label
		+ '\',\'000\')">' + lst[i].szbCount + '</a></td>' + '<td><a href="javascript:void(0)" onclick="viewRefinanceDetail(\'' + lst[i].label
		+ '\',\'6\')">' + lst[i].hzbCount + '</a></td>' + '<td><a href="javascript:void(0)" onclick="viewRefinanceDetail(\'' + lst[i].label
		+ '\',\'002\')">' + lst[i].zxbCount + '</a></td>' + '<td><a href="javascript:void(0)" onclick="viewRefinanceDetail(\'' + lst[i].label
		+ '\',\'300\')">' + lst[i].cybCount + '</a></td>'
				+ '<td><a href="javascript:void(0)" onclick="viewRefinanceDetail(\'' + lst[i].label
				+ '\',\'\')">' + lst[i].totalCount + '</a></td>' + '<td>' + lst[i].percent + '%</td>' + '</tr>';
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

function viewRefinanceDetail(label, stockCode) {
	debugger;
	var industry = $("#industrySelect").val();
	var registAddr = $("#registAddrSelect").val();
	var data = {
			label : label,
			industry : industry,
			registAddr : registAddr,
			stockCode : stockCode
	};
	var accessToken = $('#tokenValue').val();
	popWin(label, "/regulatory_statistics/queryRefinanceDetail?access_token="+accessToken, data, "95%","98%");
}

//下拉多选初始化
function tSelectDataInit() {
	var tSelectOptions1 = {
			customCallBack : tSelectCustomCallBack,
			submitCallBack : tSelectSubmitCallBack,
			id : 'value',
			pid : '',
			name : 'label',
			value : 'label',
			grade : 1,
			// resultType : 'children',
			style : {},	
			allCheck : true
	};
	var tSelectOptions2 = {
			customCallBack : tSelectCustomCallBack,
			submitCallBack : tSelectSubmitCallBack,
			id : 'id',
			pid : 'parentId',
			name : 'name',
			value : 'name',
			grade : 2,
			resultType : 'children',
			style : {},			
			allCheck : true
		};
	$('#registAddr').tselectInit(null, tSelectOptions1);
	$('#industry').tselectInit(null, tSelectOptions2);
}
//下拉框自定义方法
function tSelectCustomCallBack(t) {
	
}

function tSelectSubmitCallBack(t, d) {
	var newValue = changeAreaParam(d.value);
	$('input[name="' + t.attr('id') + '"]').val(newValue);
	search();
}

//地区特殊处理
function changeAreaParam(listArea){
	var newArea = '';
	var newAreaList = '';
	if(listArea != null && listArea != ''){
		var areaArr = listArea.split(",");
		for (var i = 0; i < areaArr.length; i++) {
			if(areaArr[i] != ''){
				if(areaArr[i] == "广东(不含深圳)"){
					newArea = "广东";
				} else if(areaArr[i] == "辽宁(不含大连)"){
					newArea = "辽宁";
				} else if(areaArr[i] == "浙江(不含宁波)"){
					newArea = "浙江";
				} else if(areaArr[i] == "福建(不含厦门)"){
					newArea = "福建";
				} else if(areaArr[i] == "山东(不含青岛)"){
					newArea = "山东";
				} else{
					newArea = areaArr[i];
				}
			}
			if(newAreaList == ''){
				newAreaList = newArea;
			}else{
				newAreaList = newAreaList + "," + newArea;
			}
		}
	}
	return newAreaList;
}

function search(){
	ajaxTableQuery("recommendOrg", "/regulatory_statistics/getRefinanceRecommendOrgStts1"
			,$("#queryForm").formSerialize()+'&access_token='+$('#tokenValue').val());
}

//序号
function renderColumnIndex(data, type, row, meta) {
	return meta.row+1;
}

//查询当前保荐机构下所有深主板公司
function renderColumnszbCount(data, type, row, meta){
    var operationStr = "";
    var quasiListedLand = "000";
    	operationStr += '<a href="javascript:void(0)" onclick="viewRefinanceDetail(\'' + data.label
		+ '\',\'' + quasiListedLand+ '\')">' + data.szbCount + '</a>';
    	return operationStr;
}

//查询当前保荐机构下所有沪主板公司
function renderColumnhzbCount(data, type, row, meta){
    var operationStr = "";
    var quasiListedLand = "6";
    	operationStr += '<a href="javascript:void(0)" onclick="viewRefinanceDetail(\'' + data.label
		+ '\',\'' + quasiListedLand+ '\')">' + data.hzbCount + '</a>';
    	return operationStr;
}

//查询当前保荐机构下所有中小板公司
function renderColumnzxbCount(data, type, row, meta){
    var operationStr = "";
    var quasiListedLand = "002";
    	operationStr += '<a href="javascript:void(0)" onclick="viewRefinanceDetail(\'' + data.label
		+ '\',\'' + quasiListedLand+ '\')">' + data.zxbCount + '</a>';
    	return operationStr;
}

//查询当前保荐机构下所有创业板公司
function renderColumncybCount(data, type, row, meta){
    var operationStr = "";
    var quasiListedLand = "300";
    	operationStr += '<a href="javascript:void(0)" onclick="viewRefinanceDetail(\'' + data.label
		+ '\',\'' + quasiListedLand+ '\')">' + data.cybCount + '</a>';
    	return operationStr;
}

//查询当前保荐机构下所有公司
function renderColumnTotalCount(data, type, row, meta){
    var operationStr = "";
    var quasiListedLand = "";
    	operationStr += '<a href="javascript:void(0)" onclick="viewRefinanceDetail(\'' + data.label
		+ '\',\'' + quasiListedLand+ '\')">' + data.totalCount + '</a>';
    	return operationStr;
}

//比例
function renderColumnPercent(data, type, row, meta) {
	return data.percent + "%";
}
