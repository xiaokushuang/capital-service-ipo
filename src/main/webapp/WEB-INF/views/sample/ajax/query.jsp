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
<e:base/>
<e:js/>
</head>
<body>
    <div class="row">
        <div class="col-md-12">
            <form:form action="/sample/query" cssClass="panel form-horizontal" modelAttribute="sampleDto">
                <div class="panel-heading">
                    <span class="panel-title">查询</span>
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <div class="col-md-4">
                            <label class="col-md-4 control-label">文本输入</label>
                            <div class="col-md-8 no-padding">
                                <form:input cssClass="form-control" path="name" />
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
                </div>
            </form:form>

            <div class="panel">
                <div class="panel-heading">
                    <i class="icon icon-list-ul"></i> 查询结果
                </div>
                <div class="panel-body">
                    <div class="table-primary">
                    <e:grid id="table_id" action="/sample/ajaxQuery" cssClass="table table-striped table-hover">
                        <e:gridColumn label="名称" renderColumn="renderColumnOperation2" orderColumn="name" cssClass="text-left" cssStyle="color:red;font-size:2px"/>
                        <e:gridColumn label="日期" cssClass="text-center" displayColumn="testDate" orderColumn="test_date"/>
                        <e:gridColumn label="时间" cssClass="text-center" displayColumn="testTime" orderColumn="test_time"/>
                        <e:gridColumn label="下拉框" cssClass="text-center" displayColumn="typeName" orderColumn="type"/>
                        <e:gridColumn label="操作" cssClass="text-center" renderColumn="renderColumnOperation" orderable="false"/>
                    </e:grid>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
