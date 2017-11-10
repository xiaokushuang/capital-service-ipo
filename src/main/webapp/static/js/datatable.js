// 所有datatable初始化事件
$(document).ready(function() {
	// 默认设置
	$.extend( $.fn.dataTable.defaults, {
		searching: false,
		pagingType: "full",
		scrollX: true,
		scrollY: false,
		dom: "rt<'table-footer clearfix'<'DT-label col-xs-5'i><'DT-per-page col-xs-3'l><'DT-pagination col-xs-4'p>>",
		orderMulti: false,
		ajax: {
			data:initDataTableParam
		},
		order: [],
		lengthMenu: [[ 10, 20, 50, 100 ],['10','20','50','100']],
		language: {
			"sProcessing": "处理中...",
			"sLengthMenu": "每页显示 _MENU_ 项数据",
			"sZeroRecords": "没有符合条件的结果",
			"sInfo": "显示第 _START_ 至 _END_ 项数据，共 _TOTAL_ 项",
			"sInfoEmpty": "显示第 0 至 0 项数据，共 0 项",
			"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
			
			"sInfoPostFix": "",
			"sSearch": "搜索:",
			"sUrl": "",
			"sEmptyTable": "没有符合条件的查询结果",
			"sLoadingRecords": "载入中...",
			"sInfoThousands": ",",
			"oPaginate": {
				"sFirst": "首页",
				"sPrevious": "上页",
				"sNext": "下页",
				"sLast": "末页"
			},
			"oAria": {
				"sSortAscending": ": 以升序排列此列",
				"sSortDescending": ": 以降序排列此列"
			}
		}
	});
	// 绑定datatable事件
	$(document).on("preXhr.dt", preXhrEventListener)
		.on("draw.dt", drawEventListener)
		.on("page.dt", pageEventListener)
		.on("length.dt",lengthEventListener)
		.on("xhr.dt",xhrEventListener);

	$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
		$($(this).attr('href')).find('.table-primary table').css('width','100%')
		$($(this).attr('href')).find('.table-primary table').dataTable().api().columns.adjust();

	})
});
/**
 * 数据请求加载完成后
 * @param e
 * @param settings
 * @param json
 * @param xhr
 */
function xhrEventListener(e,settings,json,xhr){
	var startRow = settings._iDisplayStart;
	var total = json.recordsTotal;
	var displaylength = settings._iDisplayLength;
	if((startRow>=displaylength) && (startRow+1)>total){
		settings._iDisplayStart = startRow-displaylength;
	}
}
/**
 * xmlhttprequest请求前监听
 * @param e
 * @param setting
 * @param data
 */
function preXhrEventListener(e, setting, data) {
	if (setting.serverQueryType != undefined || setting.serverQueryType != null) {
		data.toPageState = setting.serverQueryType;
	} else if ($(e.target).data("query_refresh") == true && data.draw != 1) {
		data.toPageState = "refresh";
	} else if ($(e.target).data("query_click") != true && data.draw != 1) {
		data.toPageState = "order";
		var order = $(e.target).dataTable().api().order();
		var columnIndex = order[0][0];
		var columnName = setting.aoColumns[columnIndex].data["orderColumn"];
		data.orderByName = columnName;
		data.orderByOrder = order[0][1].toUpperCase();
	} else if (data.draw == 1 && setting.oInit.toPageState != undefined) {
		data.toPageState = setting.oInit.toPageState;
	}
	if ($(e.target).data("query_condition") != null && $(e.target).data("query_condition") != undefined) {
        setting.ajax.data = $(e.target).data("query_condition")+"&"+$.param(data);
	}
	setting.serverQueryType = null;
	$(e.target).data("query_click", null);
	//$(e.target).data("query_condition", null);
	$(e.target).data("query_refresh", null);
}
/**
 * 绘制完成监听
 * @param e
 * @param setting
 */
function drawEventListener(e, setting) {
	$("tbody tr", $(e.target)).each(function(rowIndex){
		$("td", $(this)).each(function(columnIndex){
			if(setting.aoColumns[columnIndex]) {
				var cssStyle = setting.aoColumns[columnIndex].cssStyle;
				if (cssStyle != "null" && cssStyle != null && cssStyle != undefined) {
					var styles = cssStyle.split(";");
					for (var style in styles) {
						$(this).css(styles[style].split(":")[0], styles[style].split(":")[1]);
					}
				}
			}
		});
	});
	$(e.target).dataTable().api().columns.adjust();
	//绘制表格后调用Ellipsis方法
	$('[data-ellipsis="true"]').Ellipsis({
		maxLine: 2
	})
	dataTableLoadAfter();
}
/**
 * 分页监听
 * @param e
 * @param setting
 */
function pageEventListener(e, setting) {
	setting.serverQueryType = "page";
}
/**
 * 改变记录数监听
 * @param e
 * @param setting
 * @param len
 */
function lengthEventListener(e, setting, len) {
	setting.serverQueryType = "length";
}
/**
 * ajaxTable按新查询条件重新查询数据
 * @param tableId 表格ID
 * @param url 查询URL
 * @param queryInfo 查询参数
 */
function ajaxTableQuery(tableId, url, queryInfo) {
	ajaxPostTableQuery(tableId, url, queryInfo);
}

function dataTableLoadAfter() {
}
/**
 * ajaxTable按新查询条件重新查询数据(POST查询参数)
 * @param tableId 表格ID
 * @param url 查询URL
 * @param queryInfo 查询参数
 */
function ajaxPostTableQuery(tableId, url, queryInfo) {
	// 区分是否用户重新查询
	$("#"+tableId).data("query_click", true);
	$("#"+tableId).data("query_condition", queryInfo);
	$("#"+tableId).dataTable().api().ajax.url(contextPath+url).load();
	$("#"+tableId).dataTable().api().columns.adjust();
}
/**
 * ajaxTable按原有查询条件重新查询数据
 * @param tableId 表格ID
 * @param resetPaging 重置分页
 */
function ajaxTableReload(tableId, resetPaging) {
	$("#"+tableId).data("query_refresh", true);
	$("#"+tableId).dataTable().api().ajax.reload(null, resetPaging);
}

function initDataTableParam(d){
	
}
