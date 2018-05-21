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
<%-- <script src="${pageContext.request.contextPath}/static/lib/echarts/echarts-all.js"></script> --%>
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

table thead th {
    text-align: center;
    border-right: 1px solid #DDDDDD;
    border-bottom: 1px solid #DDDDDD;
    padding: 5px;
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
</style>
</head>
<body style="background-color: white;">
    <div class="panel-body no-padding regulatory-model">
        <div class="col-md-12 no-padding-hr">
            <div class="sub-title">IPO在审项目数据统计</div>
        </div>
        <div id="ipoChart" class="col-md-7 no-padding-vr chart-content" align="center"></div>
        <div class="col-md-5">
            <table id="review">
                <thead>
                    <tr>
                        <th style="width: 40%;"></th>
                        <th style="width: 20%;">沪主板</th>
                        <th style="width: 20%;">中小板</th>
                        <th style="width: 20%;">创业板</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <div id="ipoHistoryChart" class="col-md-5 no-padding-vr" style="height: 220px;" align="center"></div>
        <div class="col-md-12 no-padding">
            <hr style="margin: 20px 15px;" />
        </div>
        <div class="col-md-12 no-padding-hr">
            <div class="sub-title">保荐机构统计</div>
        </div>
        <div class="col-md-12 cart-bottom-table" style="margin-top: 10px;">
            <table id="recommendOrg">
                <thead>
                    <tr style="background: #E8E8E8;">
                        <th style="width: 5%;">序号</th>
                        <th style="width: 35%;">保荐机构</th>
                        <th style="width: 12%;">沪主板</th>
                        <th style="width: 12%;">中小板</th>
                        <th style="width: 12%;">创业板</th>
                        <th style="width: 12%;">合计</th>
                        <th style="width: 12%;">比例</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <div class="col-md-12 no-padding">
            <hr style="margin: 20px 15px;" />
        </div>
        <div class="col-md-12 no-padding-hr">
            <div class="sub-title">会计师事务所统计</div>
        </div>
        <div class="col-md-12 cart-bottom-table" style="margin-top: 10px;">
            <table id="accountantOffice">
                <thead>
                    <tr style="background: #E8E8E8;">
                        <th style="width: 5%;">序号</th>
                        <th style="width: 35%;">会计师事务所</th>
                        <th style="width: 12%;">沪主板</th>
                        <th style="width: 12%;">中小板</th>
                        <th style="width: 12%;">创业板</th>
                        <th style="width: 12%;">合计</th>
                        <th style="width: 12%;">比例</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <div class="col-md-12 no-padding">
            <hr style="margin: 20px 15px;" />
        </div>
        <div class="col-md-12 no-padding-hr">
            <div class="sub-title">律师事务所统计</div>
        </div>
        <div class="col-md-12 cart-bottom-table" style="margin-top: 10px;">
            <table id="lawsfirm">
                <thead>
                    <tr style="background: #E8E8E8;">
                        <th style="width: 5%;">序号</th>
                        <th style="width: 35%;">律师事务所</th>
                        <th style="width: 12%;">沪主板</th>
                        <th style="width: 12%;">中小板</th>
                        <th style="width: 12%;">创业板</th>
                        <th style="width: 12%;">合计</th>
                        <th style="width: 12%;">比例</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <div class="col-md-12 no-padding">
            <hr style="margin: 20px 15px;" />
        </div>
        <div class="col-md-12 no-padding" style="margin-left: 20px;">
            <p id="updateTime" style="font-size: 12px; color: #909090;"></p>
        </div>
    </div>
</body>
</html>