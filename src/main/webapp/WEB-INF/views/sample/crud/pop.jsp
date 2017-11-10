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
<!--[if IE 8]>
<html class="ie8"> <![endif]-->
<!--[if IE 9]>
<html class="ie9 gt-ie8"> <![endif]-->
<!--[if gt IE 9]><!-->
<html class="gt-ie8 gt-ie9 not-ie">
<!--<![endif]-->
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>易董平台</title>
<e:base />
<!--[if lt IE 9]>
    <script src="static/js/ie.min.js"></script>
<![endif]-->
<e:js />
</head>
<body>
    <form:form action="/sample/query" cssClass="panel hor" modelAttribute="sampleDto">
        <div class="panel-heading">
            <i class="fa fa-list"></i> 查询结果
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-xs-4 form-group-margin">
                    <label class="col-xs-4 control-label">文本输入</label>
                    <div class="col-xs-8 no-padding input-group">
                        <form:input cssClass="form-control" path="name" />
                        <span class="input-group-addon"><i class="fa fa-search"></i></span>
                    </div>
                </div>
                <div class="col-xs-4 form-group-margin">
                    <label class="col-xs-4 control-label">日期输入</label>
                    <div class="col-xs-8 no-padding">
                        <e:date cssClass="form-control" path="testDate" />
                    </div>
                </div>
                <div class="col-xs-4 form-group-margin">
                    <label class="col-xs-4 control-label">时间输入</label>
                    <div class="col-xs-8 no-padding">
                        <e:date cssClass="form-control" path="testTime" format="yyyy-mm-dd hh:ii" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-4 form-group-margin">
                    <label class="col-xs-4 control-label">下拉框</label>
                    <div class="col-xs-8 no-padding">
                        <e:codelist cssClass="form-control" codeno="YES_OR_NO" path="type" />
                    </div>
                </div>
            </div>
            <div class="panel-footer text-right">
                <input type="button" id="btnConfirm" class="btn btn-primary" value="确定" /> <input type="button" id="btnClose" class="btn btn-default" value="关闭" />
            </div>
        </div>
    </form:form>
    <div class="panel">
        <div class="panel-heading">
            <i class="icon icon-list-ul"></i> 查询结果
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>名称</th>
                        <th>日期</th>
                        <th>时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>auditTest23</td>
                        <td>2015-07-10</td>
                        <td>3:24:03</td>
                        <td><a data-toggle="modal" href="#mySmModal1" class="del"><i class="icon icon-trash"></i></a></td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>auditTest23</td>
                        <td>2015-07-10</td>
                        <td>3:24:03</td>
                        <td><a data-toggle="modal" href="#mySmModal1" class="del"><i class="icon icon-trash"></i></a></td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>auditTest23</td>
                        <td>2015-07-10</td>
                        <td>3:24:03</td>
                        <td><a data-toggle="modal" href="#mySmModal1" class="del"><i class="icon icon-trash"></i></a></td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>auditTest23</td>
                        <td>2015-07-10</td>
                        <td>3:24:03</td>
                        <td><a data-toggle="modal" href="#mySmModal1" class="del"><i class="icon icon-trash"></i></a></td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td>auditTest23</td>
                        <td>2015-07-10</td>
                        <td>3:24:03</td>
                        <td><a data-toggle="modal" href="#mySmModal1" class="del"><i class="icon icon-trash"></i></a></td>
                    </tr>
                    <tr>
                        <td>6</td>
                        <td>auditTest23</td>
                        <td>2015-07-10</td>
                        <td>3:24:03</td>
                        <td><a data-toggle="modal" href="#mySmModal1" class="del"><i class="icon icon-trash"></i></a></td>
                    </tr>
                    <tr>
                        <td>7</td>
                        <td>auditTest23</td>
                        <td>2015-07-10</td>
                        <td>3:24:03</td>
                        <td><a data-toggle="modal" href="#mySmModal1" class="del"><i class="icon icon-trash"></i></a></td>
                    </tr>
                    <tr>
                        <td>8</td>
                        <td>auditTest23</td>
                        <td>2015-07-10</td>
                        <td>3:24:03</td>
                        <td><a data-toggle="modal" data-target="#mySmModal2" href="#" class="del"><i class="icon icon-trash"></i></a></td>
                    </tr>
                </tbody>
            </table>
            <div class="paperframe">
                <ul class="pager pager-loose pager-pills">
                    <li class="previous disabled"><a href="#">«</a></li>
                    <li class="active"><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li class="next"><a href="#">»</a></li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>
