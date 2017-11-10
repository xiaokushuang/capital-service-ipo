//@ sourceURL=query.js
$(document).ready(function() {
    // 查询
    $("#btnQuery").bind("click", function() {
    	ajaxTableQuery("table_id", "/sample/ajaxQuery", $("#sampleDto").formSerialize());
    });
    // 新建
    $("#btnAdd").bind("click", function() {
        submitForm("/sample/add");
    });
});
// 渲染操作列 data:后台传过来的整行数据
function renderColumnOperation(data, type, row, meta) {
	return '<a href="#">'+data.name+'</a>';
}
function renderColumnOperation2(data, type, row, meta) {
	return '<a href="#">'+data.name+'</a>';
}