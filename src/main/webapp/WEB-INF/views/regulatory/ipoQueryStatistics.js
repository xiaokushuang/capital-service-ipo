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
		var updateTimeStr = '*统计数据截止至 ' 
			+ lst[0].lastUpadteTime.split("-")[0]+"年"
			+ lst[0].lastUpadteTime.split("-")[1]+"月"
			+ lst[0].lastUpadteTime.split("-")[2]+"日";
		$('#updateTime').html(updateTimeStr);
		//拼接tbody
		for (var i = 0; i < lst.length - 1; i++) {
			var index = i-0+1;
			str = str + '<tr>' 
						+ '<td>' + index + '</td>' 
						+ '<td>' + lst[i].registAddr + '</td>' 
						+ '<td>' + lst[i].applied + '</td>' 
						+ '<td>' + lst[i].reviewed + '</td>' 
						+ '<td>' + lst[i].preUpdate + '</td>' 
						+ '<td>' + lst[i].endYet + '</td>' 
						+ '<td>' + lst[i].processing + '</td>' 
						+ '<td>' + lst[i].passed + '</td>' 
						+ '<td class="count_bgc"><a style="cursor: pointer;" onclick="viewAreaDetail(\''+lst[i].registAddr+'\',\''+lst[i].lastUpadteTime+'\',\'\')">' + lst[i].areaCount + '</a></td>' 
					+ '</tr>';
		}
		//拼接tbody合计行
		var sumIndex = lst.length - 1;
		str = str + '<tr>' 
			+ '<td class="count_bgc"></td>' 
			+ '<td class="count_bgc">合计</td>' 
			+ '<td class="count_bgc"><a style="cursor: pointer;" onclick="viewAreaDetail(\'\',\''+lst[sumIndex].lastUpadteTime+'\',\'00\')">' + lst[sumIndex].applied + '</a></td>'  
			+ '<td class="count_bgc"><a style="cursor: pointer;" onclick="viewAreaDetail(\'\',\''+lst[sumIndex].lastUpadteTime+'\',\'01\')">' + lst[sumIndex].reviewed + '</a></td>'  
			+ '<td class="count_bgc"><a style="cursor: pointer;" onclick="viewAreaDetail(\'\',\''+lst[sumIndex].lastUpadteTime+'\',\'02\')">' + lst[sumIndex].preUpdate + '</a></td>'  
			+ '<td class="count_bgc"><a style="cursor: pointer;" onclick="viewAreaDetail(\'\',\''+lst[sumIndex].lastUpadteTime+'\',\'04\')">' + lst[sumIndex].endYet + '</a></td>'  
			+ '<td class="count_bgc"><a style="cursor: pointer;" onclick="viewAreaDetail(\'\',\''+lst[sumIndex].lastUpadteTime+'\',\'07\')">' + lst[sumIndex].processing + '</a></td>'  
			+ '<td class="count_bgc"><a style="cursor: pointer;" onclick="viewAreaDetail(\'\',\''+lst[sumIndex].lastUpadteTime+'\',\'03\')">' + lst[sumIndex].passed + '</a></td>'  
			+ '<td class="count_bgc"><a style="cursor: pointer;" onclick="viewAreaDetail(\'\',\''+lst[sumIndex].lastUpadteTime+'\',\'\')">' + lst[sumIndex].areaCount + '</a></td>' 
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
			if("合计" != $(($(this)[0])).text()){
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
			}
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
	$('input[name="' + t.attr('id') + '"]').val(d.value);
	// 提交检索
	search();
}

function viewAreaDetail(registAddr,lastUpadteTime,approveStatus){
	if(registAddr == null || registAddr == ''){
		registAddr = $('input[name="registAddr"]').val();
	}
	var param = { registAddr: registAddr, 
				  lastUpadteTime: lastUpadteTime,
				  approveStatus: approveStatus,
				  belongsPlate: $('input[name="belongsPlate"]').val(),
				  industry: $('input[name="industry"]').val()
				};
	popWin("在审项目数据明细","/regulatory_statistics/viewAreaDetail?access_token="+accessToken, param,"95%","550px");
}