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
.panel-body{
    padding: 15px 0;
}
</style>
</head>
<body style="background-color: white;">
    <div class="panel-body regulatory-model">
        <div class="col-md-12" style="text-align: right;padding-bottom: 5px;">
            <input type="button" id="exportDetail" class="btn btn-6 btn-default" style="margin-top: -5px;" value="导出excel">
        </div>
        <form:form modelAttribute="statisticsParamDto" id="statisticsParamForm">
            <form:hidden path="registAddr"/>
            <form:hidden path="industry"/>
            <form:hidden path="quasiListedLand"/>
            <form:hidden path="label"/>
        </form:form>
        <div class="col-md-12 cart-bottom-table">
            <table id="ipoAreaDetail" class="dataTable">
                <thead>
                    <tr role="row" style="background: #a0adb5; color: #fff;">
                            <th style="width: 4%; text-align: center;">序号</th>
                            <th style="width: 15%; text-align: center;">申报企业</th>
                            <th style="width: 8%; text-align: center;">注册地</th>
                            <th style="width: 10%; text-align: center;">所属行业</th>
                            <th style="width: 10%; text-align: center;">拟上市地</th>
                            <th style="width: 10%; text-align: center;">保荐机构</th>
                            <th style="width: 13%; text-align: center;">会计师事务所</th>
                            <th style="width: 10%; text-align: center;">律师事务所</th>
                            <th style="width: 10%; text-align: center;">审核状态</th>
                            <th style="width: 10%; text-align: center;">是否已参加抽查<br>抽签或现场检查</th>
                        </tr>
                </thead>
                <tbody>
                    <c:if test="${!empty companyList }">
                        <c:forEach items="${companyList}" var="cl" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>${cl.appCompany}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${cl.registAddr == '广东'}">广东<br>(不含深圳)</c:when>
                                        <c:when test="${cl.registAddr == '辽宁'}">辽宁<br>(不含大连)</c:when>
                                        <c:when test="${cl.registAddr == '浙江'}">浙江<br>(不含宁波)</c:when>
                                        <c:when test="${cl.registAddr == '福建'}">福建<br>(不含厦门)</c:when>
                                        <c:when test="${cl.registAddr == '山东'}">山东<br>(不含青岛)</c:when>
                                        <c:otherwise>${cl.registAddr}</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${cl.belongTrade}</td>
                                <td>
                                	${cl.quasiListedLand}
                                </td>
                                <td>${cl.recommendOrganization}</td>
                                <td>${cl.accountantOffice}</td>
                                <td>${cl.lawFirm}</td>
                                <td>
                                <c:choose>
                                        <c:when test="${quasiListedLand=='04'}">
                                        
                                        	<c:choose>
                                        		<c:when test="${cl.approveStatus=='已反馈'}">已问询</c:when>
                                        		<c:when test="${cl.approveStatus=='已通过发审会'}">上市委会议通过</c:when>
                                        		<c:when test="${cl.approveStatus=='中止审查'}">中止</c:when>
                                        		<c:otherwise>${cl.approveStatus}</c:otherwise>
                                        	</c:choose>
                                        
                                        </c:when>
                                        <c:otherwise>${cl.approveStatus}</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                	<c:choose>
                                        <c:when test="${cl.hasedRandomInspection == '1'}">是</c:when>
                                        <c:when test="${cl.hasedRandomInspection == '0'}">否</c:when>
                                        <c:otherwise>${cl.hasedRandomInspection}</c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty companyList }">
                        <tr><td colspan="10">暂无数据</td></tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>