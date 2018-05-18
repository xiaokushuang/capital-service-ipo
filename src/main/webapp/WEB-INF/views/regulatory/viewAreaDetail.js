//# sourceURL=viewAreaDetail.js
$(document).ready(function() {
	//在审项目数据明细excel导出
	$("#exportDetail").click(function() {
		var param = "?registAddr="+$('#registAddr').val()
				+"&lastUpadteTime="+$('#lastUpadteTime').val()
				+"&belongsPlate="+$('#belongsPlate').val()
				+"&approveStatus="+$('#approveStatus').val()
				+"&access_token="+accessToken;
		window.open(contextPath + "/regulatory_statistics/ipoDetailExport"+param);
	});
	
})