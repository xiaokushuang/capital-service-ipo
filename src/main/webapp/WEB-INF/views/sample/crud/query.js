//@ sourceURL=query.js
$(document).ready(function() {
    // 查询
    $("#btnQuery").bind("click", function() {
        // 带确认框的提交
    	//submitForm("/sample/query", "是否查询?");
        // 直接提交
        submitForm("/sample/query");
    });
    // 新建
    $("#btnAdd").bind("click", function() {
        submitForm("/sample/add");
    });
    // 弹出提示
    $("#btnAlert").bind("click", function() {
    	popAlert("这是个提示信息");
    });
    // 弹出确认提示
    $("#btnConfirm").bind("click", function() {
    	popConfirm("这个是个确认信息,选择是或否", function(data){
    		alert(data);
    	}, "hehe");
    });
    // 根据URL弹出窗口
    $("#btnPopWin").bind("click", function() {
        popWin("窗口标题1", "/sample/pop", "", "1000px", "600px", myWinCallback, "haha");
    });
    // 弹出本页面内容到弹出窗口
//    $("#btnPopWinWithHtml").bind("click", function() {
//    	popWinWithHtml("myPanel", "窗口标题2", "1000px", "600px");
//    });
    // 下载文件
    $("#btnDownload").bind("click", function() {
    	downloadFile("2");
    });
    // 查看文件
    $("#btnFileView").bind("click", function() {
    	viewFile("2");
    });
    // 编辑文件
    $("#btnFileEdit").bind("click", function() {
    	//第一参数文件ID,第二个是回调函数,第三个是回调函数参数(一般放当前编辑文件的ID也可加其他的,会在回调里再传回)
    	editFile("2", editFileCallback, "2");
    });
    // ajax请求数据
    $("#btnAjaxData").bind("click", function() {
    	ajaxData("/sample/ajaxData", "parameter1=a&parameter2=b", test);
    });
    // 提交后抛出异常
    $("#btnThrowException").bind("click", function() {
    	submitForm("/sample/throwException");
    });
    // WEB签章
    $("#btnWebSign").bind("click", function() {
    	var index = layer.open({
    	    type: 2,
    	    content: contextPath + "/sample/websignInit",
    	    maxmin: false,
    	});
    	layer.full(index);
    });
    // office签章
    $("#btnOfficeSign").bind("click", function() {
    	var index = layer.open({
    	    type: 2,
    	    content: contextPath + "/sample/officesignInit",
    	    maxmin: false,
    	});
    	layer.full(index);
    });
});

// 弹出窗口的回调函数 paraWin:弹出窗口传来的值注意有可能是空因为用户可能没点保存,paraCallBack:调用窗口时自己传的回调参数值
function myWinCallback(paraWin, paraCallBack) {
	alert(paraWin.result[0].name);
    alert(paraCallBack);
}
function test(data) {
	console.log(data);
}
//编辑文件回调(第一个参数是文件编辑后的上传服务器临时目录的文件ID,第二个参数是调用文件编辑自己传的参数这里再传回来)
function editFileCallback(newTempFileId, callPara) {
	alert(newTempFileId);
	alert(callPara);
}