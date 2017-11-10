//@ sourceURL=pop.js
$(document).ready(function() {
	// 确定
	$("#btnConfirm").bind("click", function() {
		// 每一种直接给父页面值
		//$("#name", parent.document).val("弹出窗口设置的值");
		// 第二种通过父页面的回调函数传弹出窗口的值
		// 拼弹出窗口的值
		var jsonObj = {
			result : [ {
				name : "a",
				testDate : "2015-01-01"
			}, {
				name : "b",
				testDate : "2015-01-02"
			}, {
				name : "c",
				testDate : "2015-01-03"
			} ]
		};
		closeWinCallBack(jsonObj);
	});
	// 关闭
	$("#btnClose").bind("click", function() {
		closeWin();
	});
});