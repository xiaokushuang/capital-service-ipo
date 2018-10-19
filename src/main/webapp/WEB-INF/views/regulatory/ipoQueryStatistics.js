//# sourceURL=ipoQueryStatistics.js
$(document).ready(function() {
	dataInit();
	sortingInit();
	tSelectDataInit();
	
	//查询
	$("#btnQuery").bind("click",function() {
		search();
	});
	// 清空
	$("#clearAllOptions").click(function() {
		$('.t-select').tselectClear();
		document.getElementById("queryForm").reset();
		$('input[name="belongsPlate"]').val('');
		$('input[name="registAddr"]').val('');
		$('input[name="industry"]').val('');
		$('#ipoOrder').val('');
		reductionSortStyle();
		search();
	});
	
	//获取当前年份
	var date=new Date;
	var year=date.getFullYear(); 
	$("#ipoAreaDetail thead tr:eq(1)").find("th").eq(8).html(year+"年");
});
// 数据获取初始化
function dataInit() {
	// IPO在审项目数据统计
	ajaxData('/regulatory_statistics/getIPOAreaDataStts', null, areaDataSttsCallBack);
}
function areaDataSttsCallBack(d) {
	// 设置表格
	areaDataSetting(d);
}
// IPO在审项目表格设置
function areaDataSetting(lst) {
	var tbody = $('#ipoAreaDetail tbody');
	var str = '';
	if(lst != null && lst.length > 1){
		// 截止日期设置
//		var updateTimeStr = '*统计数据截止至 ' 
//			+ lst[0].lastUpadteTime.split("-")[0]+"年"
//			+ lst[0].lastUpadteTime.split("-")[1]+"月"
//			+ lst[0].lastUpadteTime.split("-")[2]+"日";
//		$('#updateTime').html(updateTimeStr);
		//拼接tbody
		for (var i = 0; i < lst.length - 1; i++) {
			var index = i-0+1;
			var areaName = changeAreaResult(lst[i].registAddr);
			str = str + '<tr>' 
						+ '<td>' + index + '</td>' 
						+ '<td>' + areaName + '</td>' 
						+ '<td><a style="cursor: pointer;" onclick="viewAreaDetail(\''+lst[i].registAddr+'\',\''+lst[i].lastUpadteTime+'\',\'00\',\'unit\')">' + lst[i].applied + '</td>' 
						+ '<td><a style="cursor: pointer;" onclick="viewAreaDetail(\''+lst[i].registAddr+'\',\''+lst[i].lastUpadteTime+'\',\'01\',\'unit\')">' + lst[i].reviewed + '</td>' 
						+ '<td><a style="cursor: pointer;" onclick="viewAreaDetail(\''+lst[i].registAddr+'\',\''+lst[i].lastUpadteTime+'\',\'02\',\'unit\')">' + lst[i].preUpdate + '</td>' 
						+ '<td><a style="cursor: pointer;" onclick="viewAreaDetail(\''+lst[i].registAddr+'\',\''+lst[i].lastUpadteTime+'\',\'04\',\'unit\')">' + lst[i].endYet + '</td>' 
						+ '<td><a style="cursor: pointer;" onclick="viewAreaDetail(\''+lst[i].registAddr+'\',\''+lst[i].lastUpadteTime+'\',\'06\',\'unit\')">' + lst[i].processing + '</td>' 
						+ '<td><a style="cursor: pointer;" onclick="viewAreaDetail(\''+lst[i].registAddr+'\',\''+lst[i].lastUpadteTime+'\',\'03\',\'unit\')">' + lst[i].passed + '</td>' 
						+ '<td class="count_bgc"><a style="cursor: pointer;" onclick="viewAreaDetail(\''+lst[i].registAddr+'\',\''+lst[i].lastUpadteTime+'\',\'\',\'area\')">' + lst[i].areaCount + '</a></td>'
						+ '<td><a style="cursor: pointer;" onclick="viewAreaDetail(\''+lst[i].registAddr+'\',\''+lst[i].lastUpadteTime+'\',\'99\',\'unit\')">' + lst[i].weekStopYet + '</td>' 
						+ '<td><a style="cursor: pointer;" onclick="viewAreaDetail(\''+lst[i].registAddr+'\',\''+lst[i].lastUpadteTime+'\',\'05\',\'unit\')">' + lst[i].stopYet + '</td>' 
					+ '</tr>';
		}
		//拼接tbody合计行
		var sumIndex = lst.length - 1;
		str = str + '<tr>' 
			+ '<td class="count_bgc"></td>' 
			+ '<td class="count_bgc">合计</td>' 
			+ '<td class="count_bgc"><a style="cursor: pointer;" onclick="viewAreaDetail(\'\',\''+lst[sumIndex].lastUpadteTime+'\',\'00\',\'approve\')">' + lst[sumIndex].applied + '</a></td>'  
			+ '<td class="count_bgc"><a style="cursor: pointer;" onclick="viewAreaDetail(\'\',\''+lst[sumIndex].lastUpadteTime+'\',\'01\',\'approve\')">' + lst[sumIndex].reviewed + '</a></td>'  
			+ '<td class="count_bgc"><a style="cursor: pointer;" onclick="viewAreaDetail(\'\',\''+lst[sumIndex].lastUpadteTime+'\',\'02\',\'approve\')">' + lst[sumIndex].preUpdate + '</a></td>'  
			+ '<td class="count_bgc"><a style="cursor: pointer;" onclick="viewAreaDetail(\'\',\''+lst[sumIndex].lastUpadteTime+'\',\'04\',\'approve\')">' + lst[sumIndex].endYet + '</a></td>'  
			+ '<td class="count_bgc"><a style="cursor: pointer;" onclick="viewAreaDetail(\'\',\''+lst[sumIndex].lastUpadteTime+'\',\'06\',\'approve\')">' + lst[sumIndex].processing + '</a></td>'  
			+ '<td class="count_bgc"><a style="cursor: pointer;" onclick="viewAreaDetail(\'\',\''+lst[sumIndex].lastUpadteTime+'\',\'03\',\'approve\')">' + lst[sumIndex].passed + '</a></td>'  
			+ '<td class="count_bgc"><a style="cursor: pointer;" onclick="viewAreaDetail(\'\',\''+lst[sumIndex].lastUpadteTime+'\',\'\',\'all\')">' + lst[sumIndex].areaCount + '</a></td>'
			+ '<td class="count_bgc"><a style="cursor: pointer;" onclick="viewAreaDetail(\'\',\''+lst[sumIndex].lastUpadteTime+'\',\'99\',\'approve\')">' + lst[sumIndex].weekStopYet + '</a></td>'  
			+ '<td class="count_bgc"><a style="cursor: pointer;" onclick="viewAreaDetail(\'\',\''+lst[sumIndex].lastUpadteTime+'\',\'05\',\'approve\')">' + lst[sumIndex].stopYet + '</a></td>'
			+ '</tr>';
	} else{
		//增加终止审查后切记变更colspan值
		str = '<tr><td colspan="9">暂无数据</td></tr>'
	}
	tbody.empty();
	tbody.append(str);
}
//列表排序初始化
function sortingInit() {
	$("#ipoAreaDetail thead").find("tr").eq(1).children().each(function(){
		$(this).click(function(){
			var orderByName = '';
			var orderByOrder = '';
			if($(this).hasClass('sorting')){
				//恢复默认排序图标
				reductionSortStyle();
				//获取排序名称
				orderByName = $(this).data('sort-name');
				orderByOrder = 'asc';
				//修改点击th排序样式
				var className = this.className;
				this.className = className.replace("sorting","sorting_asc");
			}else if($(this).hasClass('sorting_asc')){
				var className = this.className;
				this.className = className.replace("sorting_asc","sorting_desc");
				orderByName = $(this).data('sort-name');
				orderByOrder = 'desc';
			}else if($(this).hasClass('sorting_desc')){
				var className = this.className;
				this.className = className.replace("sorting_desc","sorting_asc");
				orderByName = $(this).data('sort-name');
				orderByOrder = 'asc';
			}
			$("#ipoOrder").val(orderByName +' '+ orderByOrder + ",");
			search();
		})
	});
}
//还原排序样式
function reductionSortStyle(){
	if($("th[class*='sorting_asc']").length >0){
		$("th[class*='sorting_asc']").each(function(i,n){
			var className = n.className;
			n.className = className.replace("sorting_asc","sorting");
		});
	}else if($("th[class*='sorting_desc']").length >0){
		$("th[class*='sorting_desc']").each(function(i,n){
			var className = n.className;
			n.className = className.replace("sorting_desc","sorting");
		});
	}
}

//查询
function search(){
	ajaxData('/regulatory_statistics/getIPOAreaDataStts', $("#queryForm").formSerialize(), areaDataSttsCallBack);
}

//下拉多选初始化
function tSelectDataInit() {
	var tSelectOptions = {
		customCallBack : tSelectCustomCallBack,
		submitCallBack : tSelectSubmitCallBack,
		id : 'value',
		pid : '',
		name : 'label',
		value : 'value',
		grade : 1,
		// resultType : 'children',
		style : {},
		allCheck : true
	};
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
			style : {}
//			selectedIds : industrySelect
	};
	$('#belongsPlate').tselectInit(null, tSelectOptions);
	$('#registAddr').tselectInit(null, tSelectOptions1);
	$('#industry').tselectInit(null, tSelectOptions2);
}
//下拉框自定义方法
function tSelectCustomCallBack(t) {
	
}

function tSelectSubmitCallBack(t, d) {
	var newValue = changeAreaParam(d.value);
	$('input[name="' + t.attr('id') + '"]').val(newValue);
	// 提交检索
	search();
}

function viewAreaDetail(registAddr,lastUpadteTime,approveStatus,viewType){
	var title = '在审项目数据明细';
	if(registAddr == null || registAddr == ''){
		registAddr = $('input[name="registAddr"]').val();
	}
	var newAreaList = changeAreaParam(registAddr);
	var param = { registAddr: newAreaList, 
				  lastUpadteTime: lastUpadteTime,
				  approveStatus: approveStatus,
				  belongsPlate: $('input[name="belongsPlate"]').val(),
				  industry: ''	//$('input[name="industry"]').val()
				};
	var appLabel = '';
	if(approveStatus != null && approveStatus != ''){
		var date=new Date;
		var year=date.getFullYear(); 
		switch (approveStatus) {
	        case '00': appLabel = approveStatus.replace("00", "已受理");
	        break;
	        case '01': appLabel = approveStatus.replace("01", "已反馈");
	        break;
	        case '02': appLabel = approveStatus.replace("02", "预先披露更新");
	        break;
	        case '04': appLabel = approveStatus.replace("04", "中止审查");
	        break;
	        case '06': appLabel = approveStatus.replace("06", "已提交发审会讨论，暂缓表决");
	        break;
	        case '03': appLabel = approveStatus.replace("03", "已通过发审会");
	        break;
	        case '05': appLabel = approveStatus.replace("05", year + "年终止审查");
	        break;
	        case '99': appLabel = approveStatus.replace("99", "最近一周终止审查");
	        break;
		}
	}
	if('unit' == viewType){
		title = title + ' ( ' + registAddr + ' ' + appLabel + ' ) ';
	}else if('area' == viewType){
		title = title + ' ( ' + registAddr + ' ) ';
	}else if('approve' == viewType){
		title = title + ' ( ' + appLabel + ' ) ';
	}else if('all' == viewType){
		title = title;
	}
	popWin(title,"/regulatory_statistics/viewAreaDetail?access_token="+accessToken, param,"95%","450px");
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

//地区返回结果处理
function changeAreaResult(name){
	var resultName = '';
	if(name != null && name != ''){
		if(name == "广东"){
			resultName = "广东(不含深圳)";
		} else if(name == "辽宁"){
			resultName = "辽宁(不含大连)";
		} else if(name == "浙江"){
			resultName = "浙江(不含宁波)";
		} else if(name == "福建"){
			resultName = "福建(不含厦门)";
		} else if(name == "山东"){
			resultName = "山东(不含青岛)";
		} else{
			resultName = name;
		}
	}
	return resultName;
}
