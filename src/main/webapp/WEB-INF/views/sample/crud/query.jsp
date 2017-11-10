<%--
/***************************************************************
* 程序名 : query.jsp
* 日期  :  2015-7-7
* 作者  :  zhaoyang
* 模块  :  测试
* 描述  :  分页查询页面
* 备注  : 
***************************************************************/
--%>
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
<title>易董平台</title>
<e:base />
<e:js />
</head>
<body>
    <form:form action="/sample/query" cssClass="panel form-horizontal" modelAttribute="sampleDto">
        <div class="panel-heading">
            <span class="panel-title">查询</span>
        </div>
        <div class="panel-body">
            <div class="form-group">
                <div class="col-md-4">
                    <label class="col-md-4 control-label">文本输入</label>
                    <div class="col-md-8 input-group">
                        <form:input cssClass="form-control" path="name" />
                        <span class="input-group-addon"><i class="fa fa-search"></i></span>
                    </div>
                </div>
                <div class="col-md-4">
                    <label class="col-md-4 control-label">日期输入</label>
                    <div class="col-md-8 no-padding">
                        <e:date cssClass="form-control" path="testDate" />
                    </div>
                </div>
                <div class="col-md-4">
                    <label class="col-md-4 control-label">时间输入</label>
                    <div class="col-md-8 no-padding">
                        <e:date cssClass="form-control" path="testTime" format="yyyy-mm-dd hh:ii"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-4">
                    <label class="col-md-4 control-label">下拉框</label>
                    <div class="col-md-8 no-padding">
                        <e:codelist cssClass="form-control" codeno="YES_OR_NO" path="type" />
                    </div>
                </div>
            </div>
        </div>
        <div class="panel-footer text-right">
            <button type="button" id="btnQuery" class="btn btn-primary">查询</button>
            <button type="button" id="btnAdd" class="btn btn-default">新增</button>
            <button type="button" id="btnAlert" class="btn btn-default">提示</button>
            <button type="button" id="btnConfirm" class="btn btn-default">确认框</button>
            <button type="button" id="btnPopWin" class="btn btn-default">弹出iframe窗口</button>
    <!--                     <button type="button" id="btnPopWinWithHtml" class="btn btn-default">弹出自定义内容窗口</button> -->
            <button type="button" id="btnDownload" class="btn btn-default">下载文件</button>
            <button type="button" id="btnFileView" class="btn btn-default">查看文件</button>
            <button type="button" id="btnFileEdit" class="btn btn-default">编辑文件</button>
            <button type="button" id="btnAjaxData" class="btn btn-default">ajax请求数据</button>
            <button type="button" id="btnThrowException" class="btn btn-default">抛业务异常</button>
            <button type="button" id="btnWebSign" class="btn btn-default">WEB签章</button>
<!--             <button type="button" id="btnOfficeSign" class="btn btn-default">office签章</button> -->
        </div>
    </form:form>
    <div class="panel">
        <div class="panel-heading">
            <i class="fa fa-list-ul"></i><span class="panel-title">查询结果</span>
        </div>
        <div class="panel-body">
            <div class="table-primary">
            <e:table action="/sample/query" col="5" cssClass="table table-hover table-striped">
                <tr>
                    <th width="5%" align="center">序号</th>
                    <th width="20%" align="center"><e:orderColumn column="name" label="名称"/></th>
                    <th width="20%" align="center"><e:orderColumn column="test_date" label="日期"/></th>
                    <th width="20%" align="center"><e:orderColumn column="test_time" label="时间"/></th>
                    <th width="5%" align="center">操作</th>
                </tr>
                <c:forEach var="item" items="${queryList}" varStatus="status">
                <tr>
                    <td align="center"><c:out value="${status.count}" /></td>
                    <td align="left"><c:out value="${item.name}" /></td>
                    <td align="center"><fmt:formatDate value="${item.testDate}" pattern="yyyy-MM-dd"/></td>
                    <td align="center"><fmt:formatDate value="${item.testTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td align="center">
                    <span class="widget-icon">
                    <a href="javascript:movePage('/sample/modify?id=<c:out value="${item.id}" />')"><i class="fa fa-edit"></i></a>
                    <a href="javascript:movePage('/sample/del?id=<c:out value="${item.id}" />')"><i class="fa fa-trash"></i></a>
                    </span>
                    </td>
                </tr>
                </c:forEach>
            </e:table>
            </div>
        </div>
    </div>
</body>
</html>