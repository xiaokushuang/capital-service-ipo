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
        <!-- <div class="col-md-12">
            <div class="sub-title">IPO地域详情</div>
        </div> -->
        <div class="col-md-12 cart-bottom-table">
            <table id="ipoAreaDetail" class="dataTable">
                <thead>
                    <tr role="row" style="background: #E8E8E8;">
                            <th style="width: 4%; text-align: center;">序号</th>
                            <th style="width: 15%; text-align: center;">申报企业</th>
                            <th style="width: 5%; text-align: center;">注册地</th>
                            <th style="width: 10%; text-align: center;">所属行业</th>
                            <th style="width: 10%; text-align: center;">拟上市地</th>
                            <th style="width: 10%; text-align: center;">保荐机构</th>
                            <th style="width: 16%; text-align: center;">会计师事务所</th>
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
                                <td>${cl.registAddr}</td>
                                <td>${cl.industry}</td>
                                <td>${cl.ipoAreaLabel}</td>
                                <td>${cl.recommendOrg}</td>
                                <td>${cl.accOffice}</td>
                                <td>${cl.lawFirm}</td>
                                <td>${cl.statusLabel}</td>
                                <td>${cl.attendLabel}</td>
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