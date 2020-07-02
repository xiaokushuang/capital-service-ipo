<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/edm-tags" prefix="e"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<e:base />
<e:js />
<style>
.sub-title {
    font-size: 18px;
    font-weight: bold;
    padding: 0px 20px;
}
.chart-content {
    height: 450px;
}

.color-block {
    float: left;
    width: 10px;
    height: 10px;
    margin-top: 5px;
    margin-right: 10px;
}

table {
    width: 100%;
    border-top: 1px solid #DDDDDD;
    border-left: 1px solid #DDDDDD;
    text-align: center;
}

/* table thead th {
    text-align: center;
    border-right: 1px solid #DDDDDD;
    border-bottom: 1px solid #DDDDDD;
    padding: 5px;
} */

table thead th{
    padding: 6px;
    font-weight: normal;
    color: #fff;
}

table tbody tr td {
    padding: 5px;
    border-right: 1px solid #DDDDDD;
    border-bottom: 1px solid #DDDDDD;
}

table tbody tr td.left {
    text-align: left;
    padding-left: 10px;
}
table.dataTable thead .sorting:after {
    opacity: 0.2;
    content: "\f0dc";
}
table.dataTable thead .sorting_asc:after {
    content: "\f0de";
}
table.dataTable thead .sorting_desc:after {
    content: "\f0dd";
}
table.dataTable thead .sorting:after, table.dataTable thead .sorting_asc:after, table.dataTable thead .sorting_desc:after {
    font-family: 'FontAwesome';
    opacity: 0.5;
    font-size: 16px;
    margin-left: 8px;
}
.t-select-view{
    height:300px !important;
}
.count_bgc{
    background: #E8E8E8;
}
.btn-primary{
    color: #fff !important;
    background-color: #337ab7 !important;
    background: #2e6da4 !important;
    border-color: #2e6da4 !important;
}
.btn-primary:hover{
    border-color: #0087a7 !important;
    border-bottom-color: #0087a7 !important;
    background: #0087a7 !important;
    background-image: -webkit-linear-gradient(top,#1c2233 0,#0087a7 100%) !important;
    background-image: linear-gradient(to bottom,#0087a7 0,#0087a7 100%) !important;
    background-repeat: repeat-x !important;
}
</style>
</head>
<body style="background-color: white;">
    <div class="panel-body no-padding regulatory-model">
        <div class="col-md-12 no-padding-hr">
            <div class="sub-title">IPO在审项目数据查询</div>
        </div>
        <div class="col-md-12" style="padding-left: 0px;">
            <form:form modelAttribute="statisticsParamDto" id="queryForm">
                <form:hidden path="ipoOrder"/>
                <div class="col-md-12" style="padding: 10px 0px;">
                    <div class="col-md-3 no-padding-r">
                        <input id="belongsPlate" json-data='${belongsPlateList}' type="text" class="form-control t-select" placeholder="请选择板块" style="background-color: #fff;"/> 
                        <input type="hidden" name="belongsPlate" />
                    </div>
                    <div class="col-md-3 no-padding-r">
                        <input id="registAddr" json-data='${areaList}' type="text" class="form-control t-select" placeholder="请选择注册地" style="background-color: #fff;"/> 
                        <input type="hidden" name="registAddr" />
                    </div>
                    <div class="col-md-3 no-padding-r">
                        <%-- <input id="industry" json-data='${industryList}' type="text" class="form-control t-select" placeholder="请选择行业" style="background-color: #fff;"/> 
                        <input type="hidden" name="industry" /> --%>
                    </div>
                    
                    <div class="col-md-3 no-padding-r" align="right">
                     <button type="button" class="btn btn-primary" id="download" title="导出excel" onclick="excelImport()">导出excel</button>
                        <span id="clearAllOptions" class="btn btn-4 btn-bny">清空条件</span>
                        <!-- <span id="btnQuery" class="btn btn-primary">查询</span> -->
                    </div>
                </div>
            </form:form>
        </div>
        <div class="col-md-12 cart-bottom-table">
            <table id="ipoAreaDetail" class="dataTable">
                <thead>
                    <tr role="row" style="background: #a0adb5;">
                        <th class="text-center" rowspan="2" style="width: 4%; border-right: 1px solid #fff !important;">序号</th>
                        <th class="text-center" rowspan="2" style="width: 6%;border-right: 1px solid #fff !important;">注册地</th>
                        <th class="text-center" colspan="9" style="width: 73%;border-bottom: 1px solid #fff !important;border-right: 1px solid #fff !important;">在审情况</th>
                        <th class="text-center" colspan="2" style="width: 16%;border-bottom: 1px solid #fff !important;">终止审查<span style="color:#0099cc">(终止)</span></th>
                    </tr>
                    <tr role="row" style="background: #a0adb5;">
                            <th class="sorting" data-sort-name="applied" style="width: 10%; text-align: center;border-right: 1px solid #fff !important;">已受理<span style="color:#0099cc">(已受理)</span></th>
                            <th class="sorting" data-sort-name="reviewed" style="width: 10%; text-align: center;border-right: 1px solid #fff !important;">已反馈<span style="color:#0099cc">(已问询)</span></th>
                            <th class="sorting" data-sort-name="preUpdate" style="width: 10%; text-align: center;border-right: 1px solid #fff !important;">预先披露更新<span style="color:#0099cc">(已回复)</span></th>
                            <th class="sorting" data-sort-name="endYet" style="width: 10%; text-align: center;border-right: 1px solid #fff !important;">中止审查<span style="color:#0099cc">(中止)</span></th>
                            <th class="sorting" data-sort-name="processing" style="width: 17%; text-align: center;border-right: 1px solid #fff !important;">已提交发审会讨论，暂缓表决(已提交上市委会议，暂缓审议)</th>
                            <th class="sorting" data-sort-name="passed" style="width: 6%; text-align: center;border-right: 1px solid #fff !important;">已通过发审会<span style="color:#0099cc">(已通过上市委会议)</span></th>
                            <th class="sorting" data-sort-name="submited" style="width: 8%; text-align: center;border-right: 1px solid #fff !important;"><span style="color:#0099cc">提交注册</span></th>
                            <th class="sorting" data-sort-name="zc" style="width: 8%; text-align: center;border-right: 1px solid #fff !important;"><span style="color:#0099cc">注册生效</span></th>
                            <th class="sorting" data-sort-name="areaCount" style="width: 5%; text-align: center;border-right: 1px solid #fff !important;">合计</th>
                            <th class="sorting" data-sort-name="weekStopYet" style="width: 9%; text-align: center;border-right: 1px solid #fff !important;">最近一周</th>
                            <th class="sorting" data-sort-name="stopYet" style="width: 10%; text-align: center;border-right: 0px solid #fff !important;"></th>
                        </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
<!--         <div class="col-md-12 no-padding" style="margin-left: 20px;"> -->
<!--             <p id="updateTime" style="font-size: 12px; color: #909090;"></p> -->
<!--         </div> -->
<input type="hidden" id="tokenValue" value="${access_token}">
    </div>
</body>
</html>
