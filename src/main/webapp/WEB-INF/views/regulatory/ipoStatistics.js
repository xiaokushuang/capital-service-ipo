//# sourceURL=ipoStatistics.js
var myChart;
//需求4399 2018/5/24 by liuh Start
//var myChartColor = ['#C03533', '#2F4553', '#91C7AF', '#F6A631'];
var myChartColor = ['#ca2428', '#2e444e', '#7ccbab', '#ffa128', '#227d99', '', '#ff5f3a'];
var myHistoryChart;
$(document).ready(function() {
	eChartInit();
	historyEChartInit();
	dataInit();
	pageInit();
	
	tSelectDataInit();
	
	// 清空
	$("#clearAllOption").click(function() {
		$('.t-select').tselectClear();
		document.getElementById("queryForm").reset();
		$('input[name="registAddr"]').val('');
		$('input[name="industry"]').val('');
		search();
	});
	
});

function initDataTableParam(d){
	return $.extend( {}, d, {
	        "access_token": $('#tokenValue').val()
	      } );
}

// 数据获取初始化
function dataInit() {
	// IPO在审项目数据统计
	ajaxData('/regulatory_statistics/getIPOReviewingStts', null, reviewingSttsCallBack);
	// 保荐机构统计
//	ajaxData('/regulatory_statistics/getIPORecommendOrgStts', null, recommendOrgTableSetting);
	// 会计师事务所统计
//	ajaxData('/regulatory_statistics/getIPOAccountantOfficeStts', null, accountantOfficeTableSetting);
	// 律师事务所统计
//	ajaxData('/regulatory_statistics/getIPOLawFirmStts', null, lawFirmTableSetting);
	// 截止日期
//	ajaxData('/regulatory_statistics/getIPOLastTime', null, updateTimeSetting);
	
	// tab页点击事件
	$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
		//iframeObj = e.target;
		if(e.target.hash == "#tab1"){
			$("#recommendOrg").dataTable().api().columns.adjust();
		} else if(e.target.hash == "#tab2") {
			$("#lawsfirm").dataTable().api().columns.adjust();
		} else {
			$("#accountantOffice").dataTable().api().columns.adjust();
		}
	})
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
	// 细分业务标签
	var itemLabel = [];
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
//						color : myChartColor[i % 4]
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
			itemLabel.push(lst[i].label);
		}
	}
	// 设置柱状图
	myChart.clear();
	var option = {
		calculable : false,
		legend: { // 细分业务标签
			top: 'top',
	        data: itemLabel
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
			top : '5%',
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
			var backgroud = '';
			var label = '<td class="left">' + lst[i].label + '</td>';
			if (i == lst.length - 1) {
				label = '<td style="text-align: center;">' + lst[i].label + '</td>';
				backgroud = 'style="background: #E8E8E8;"';
			}
			var total = lst[i].hzbCount + lst[i].zxbCount + lst[i].cybCount;
			var str = '<tr '+ backgroud +'>' + label + '<td>' + lst[i].hzbCount + '</td>' + '<td>' + lst[i].zxbCount + '</td>' + '<td>' + lst[i].cybCount + '</td>' + '<td>' + total + '</td>' +'</tr>';
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

function search(){
	ajaxTableQuery("recommendOrg", "/regulatory_statistics/getIPORecommendOrgStts"
			,$("#queryForm").formSerialize()+'&access_token='+$('#tokenValue').val());
	ajaxTableQuery("lawsfirm", "/regulatory_statistics/getIPOLawFirmStts"
			,$("#queryForm").formSerialize()+'&access_token='+$('#tokenValue').val());
	ajaxTableQuery("accountantOffice", "/regulatory_statistics/getIPOAccountantOfficeStts"
			, $("#queryForm").formSerialize()+'&access_token='+$('#tokenValue').val());
}

//序号
function renderColumnIndex(data, type, row, meta) {
	return meta.row+1;
}

//市场占比
function renderColumnPercent(data, type, row, meta) {
	return data.percent + "%";
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
//需求4399 2018/5/24 by liuh end
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
		        confine: true,
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
		    title: {
		        top: '10px',
		        left: 'center',
		        text: 'IPO在审企业合计数变化情况',
		    },
		    legend: {
		        top: '35px',
		        data:['合计','沪主板','创业板','中小板']
		    },
//		    toolbox: {
//		    	top: '10px',
//		    	right: '5px',
//		        show: true,
//		        feature: {
//		            dataZoom: {
//		                yAxisIndex: 'none'
//		            },
//		            restore: {},
//		            saveAsImage: {}
//		        }
//		    },
			grid : {
				left : '3px',
				right : '30px',
				bottom : '40px',
				containLabel : true
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
		        end: 100
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
		                    color: myChartColor[0]
		                }
		            },
		            areaStyle: {
		                normal: {
		                    color: myChartColor[0]
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
		                    color: myChartColor[1]
		                }
		            },
		            areaStyle: {
		                normal: {
		                    color: myChartColor[1]
		                }
		            },
		            data: []
		        },
		        {
		            name:'创业板',
		            type:'line',
		            smooth:true,
		            symbol: 'rect',
		            sampling: 'average',
		            itemStyle: {
		                normal: {
		                    color: myChartColor[2]
		                }
		            },
		            areaStyle: {
		                normal: {
		                    color: myChartColor[2]
		                }
		            },
		            data: []
		        },
		        {
		            name:'中小板',
		            type:'line',
		            smooth:true,
		            symbol: 'triangle',
		            sampling: 'average',
		            itemStyle: {
		                normal: {
		                    color: myChartColor[3]
		                }
		            },
		            areaStyle: {
		                normal: {
		                    color: myChartColor[3]
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



function hzbCountAllInfo(label,quasiListedLand) {
	debugger;
	var data = {
			label : label,
			quasiListedLand : quasiListedLand
	};
	var accessToken = $('#tokenValue').val();
	popWin(label, "/regulatory_statistics/viewCommendDetail?access_token="+accessToken, data, "95%","98%");
}

/*--------------保荐机构------------------*/
//查询当前保荐机构下所有沪主板公司
function renderColumnhzbCount(data, type, row, meta){
    var operationStr = "";
    var quasiListedLand = "00";
    	operationStr += '<a href="javascript:void(0)" onclick="viewCommendDetail(\'' + data.label
		+ '\',\'' + quasiListedLand+ '\')">' + data.hzbCount + '</a>';
    	return operationStr;
}

//查询当前保荐机构下所有中小板公司
function renderColumnzxbCount(data, type, row, meta){
    var operationStr = "";
    var quasiListedLand = "02";
    	operationStr += '<a href="javascript:void(0)" onclick="viewCommendDetail(\'' + data.label
		+ '\',\'' + quasiListedLand+ '\')">' + data.zxbCount + '</a>';
    	return operationStr;
}

//查询当前保荐机构下所有创业板公司
function renderColumncybCount(data, type, row, meta){
    var operationStr = "";
    var quasiListedLand = "03";
    	operationStr += '<a href="javascript:void(0)" onclick="viewCommendDetail(\'' + data.label
		+ '\',\'' + quasiListedLand+ '\')">' + data.cybCount + '</a>';
    	return operationStr;
}

//查询当前保荐机构下所有公司
function renderColumnTotalCount(data, type, row, meta){
    var operationStr = "";
    var quasiListedLand = "";
    	operationStr += '<a href="javascript:void(0)" onclick="viewCommendDetail(\'' + data.label
		+ '\',\'' + quasiListedLand+ '\')">' + data.totalCount + '</a>';
    	return operationStr;
}

function viewCommendDetail(label,quasiListedLand) {
	debugger;
	var data = {
			label : label,
			quasiListedLand : quasiListedLand
	};
	var accessToken = $('#tokenValue').val();
	popWin(label, "/regulatory_statistics/viewCommendDetail?access_token="+accessToken, data, "95%","98%");
}

/*--------------律师事务所------------------*/
//查询当前律师事务所下所有沪主板公司
function renderColumnLawhzbCount(data, type, row, meta){
    var operationStr = "";
    var quasiListedLand = "00";
    	operationStr += '<a href="javascript:void(0)" onclick="viewLawDetail(\'' + data.label
		+ '\',\'' + quasiListedLand+ '\')">' + data.hzbCount + '</a>';
    	return operationStr;
}

//查询当前律师事务所下所有中小板公司
function renderColumnLawzxbCount(data, type, row, meta){
    var operationStr = "";
    var quasiListedLand = "02";
    	operationStr += '<a href="javascript:void(0)" onclick="viewLawDetail(\'' + data.label
		+ '\',\'' + quasiListedLand+ '\')">' + data.zxbCount + '</a>';
    	return operationStr;
}

//查询当前律师事务所下所有创业板公司
function renderColumnLawcybCount(data, type, row, meta){
    var operationStr = "";
    var quasiListedLand = "03";
    	operationStr += '<a href="javascript:void(0)" onclick="viewLawDetail(\'' + data.label
		+ '\',\'' + quasiListedLand+ '\')">' + data.cybCount + '</a>';
    	return operationStr;
}

//查询当前律师事务所下所有公司
function renderColumnLawTotalCount(data, type, row, meta){
    var operationStr = "";
    var quasiListedLand = "";
    	operationStr += '<a href="javascript:void(0)" onclick="viewLawDetail(\'' + data.label
		+ '\',\'' + quasiListedLand+ '\')">' + data.totalCount + '</a>';
    	return operationStr;
}

function viewLawDetail(label,quasiListedLand) {
	debugger;
	var data = {
			label : label,
			quasiListedLand : quasiListedLand
	};
	var accessToken = $('#tokenValue').val();
	popWin(label, "/regulatory_statistics/viewLawDetail?access_token="+accessToken, data, "95%","98%");
}
/*--------------会计师事务所------------------*/
//查询当前会计师事务所下所有沪主板公司
function renderColumnAccounthzbCount(data, type, row, meta){
  var operationStr = "";
  var quasiListedLand = "00";
  	operationStr += '<a href="javascript:void(0)" onclick="viewAccountDetail(\'' + data.label
		+ '\',\'' + quasiListedLand+ '\')">' + data.hzbCount + '</a>';
  	return operationStr;
}

//查询当前会计师事务所下所有中小板公司
function renderColumnAccountzxbCount(data, type, row, meta){
  var operationStr = "";
  var quasiListedLand = "02";
  	operationStr += '<a href="javascript:void(0)" onclick="viewAccountDetail(\'' + data.label
		+ '\',\'' + quasiListedLand+ '\')">' + data.zxbCount + '</a>';
  	return operationStr;
}

//查询当前会计师事务所下所有创业板公司
function renderColumnAccountcybCount(data, type, row, meta){
  var operationStr = "";
  var quasiListedLand = "03";
  	operationStr += '<a href="javascript:void(0)" onclick="viewAccountDetail(\'' + data.label
		+ '\',\'' + quasiListedLand+ '\')">' + data.cybCount + '</a>';
  	return operationStr;
}

//查询当前会计师事务所下所有公司
function renderColumnAccountTotalCount(data, type, row, meta){
  var operationStr = "";
  var quasiListedLand = "";
  	operationStr += '<a href="javascript:void(0)" onclick="viewAccountDetail(\'' + data.label
		+ '\',\'' + quasiListedLand+ '\')">' + data.totalCount + '</a>';
  	return operationStr;
}

function viewAccountDetail(label,quasiListedLand) {
	debugger;
	var data = {
			label : label,
			quasiListedLand : quasiListedLand
	};
	var accessToken = $('#tokenValue').val();
	popWin(label, "/regulatory_statistics/viewAccountDetail?access_token="+accessToken, data, "95%","98%");
}
