//# sourceURL=viewCommendDetail.js
$(document).ready(function() {
	//在审项目数据明细excel导出
	$("#exportDetail").click(function() {
		debugger;
		var param = "&quasiListedLand="+$('#quasiListedLand').val()
				+"&label="+$('#label').val()
				+"&industry="+$('#industry').val()
				+"&registAddr="+$('#registAddr').val()
				+"&access_token="+accessToken;
		window.open(contextPath + "/regulatory_statistics/ipoCommendDetailExport?flag=1"+param);
	});
	
})