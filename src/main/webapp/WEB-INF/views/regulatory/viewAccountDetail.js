//# sourceURL=viewAccountDetail.js
$(document).ready(function() {
	//在审项目数据明细excel导出
	$("#exportDetail").click(function() {
		var param = "&quasiListedLand="+$('#quasiListedLand').val()
		+"&label="+$('#label').val()
//		+"&belongsPlate="+$('#belongsPlate').val()
//		+"&approveStatus="+$('#approveStatus').val()
		+"&access_token="+accessToken;
		window.open(contextPath + "/regulatory_statistics/ipoCommendDetailExport?flag=3"+param);
	});
	
})