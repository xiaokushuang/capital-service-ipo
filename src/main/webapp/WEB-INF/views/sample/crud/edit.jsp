<%--
/***************************************************************
* 程序名 :  edit.jsp
* 日期  :  2015-7-7
* 作者  :  zhaoyang
* 模块  :  测试
* 描述  :  测试新建/修改页面
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
<e:js />
</head>
<body>
    <form:form action="/sample/save" class="panel form-horizontal" modelAttribute="sampleDto" id="testForm" enctype="multipart/form-data">
        <div class="panel-heading">
            <span class="panel-title">信息</span>
        </div>
        <div class="panel-body">
                <form:hidden path="id" />
                <form:hidden path="createUser" />
                <form:hidden path="createTime" />
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
                            <e:date cssClass="form-control" path="testTime" format="yyyy-mm-dd hh:ii" />
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
                    <div class="col-md-4">
                        <label class="col-md-4 control-label">整数</label>
                        <div class="col-md-8 no-padding">
                            <form:input cssClass="form-control" path="numInt" />
                        </div>
                    </div>
                    <div class="col-md-4">
                        <label class="col-md-4 control-label"><span class="red_star">*</span>小数</label>
                        <div class="col-md-8 no-padding">
                            <form:input cssClass="form-control" path="numDouble" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4">
                        <label class="col-md-4 control-label">单选</label>
                        <div class="col-md-8 no-padding">
                            <label class='radio-inline'><form:radiobutton path="radiobutton" value="1" cssClass="px" /><span class="lbl">单选1</span></label>
                            <label class='radio-inline'><form:radiobutton path="radiobutton" value="2" cssClass="px" /><span class="lbl">单选2</span></label>
                            <label class='radio-inline'><form:radiobutton path="radiobutton" value="3" cssClass="px" /><span class="lbl">单选3</span></label>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <label class="col-md-4 control-label">复选</label>
                        <div class="col-md-8 no-padding">
                            <label class='checkbox-inline'><form:checkbox path="checkbox1" value="1" cssClass="px" /><span class="lbl">复选1</span></label>
                            <label class='checkbox-inline'><form:checkbox path="checkbox1" value="2" cssClass="px" /><span class="lbl">复选2</span></label>
                            <label class='checkbox-inline'><form:checkbox path="checkbox1" value="3" cssClass="px" /><span class="lbl">复选3</span></label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12 text-right">
                        <button type="button" class="btn btn-default" id="btnAdd">新增</button>
                    </div>
                </div>
                <div class="table-primary">
                <table id="items" class="table table-hover table-striped">
                    <thead>
                        <tr>
                            <th width="10%" align="center">名称</th>
                            <th width="10%" align="center">下拉框</th>
                            <th width="10%" align="center">整数</th>
                            <th width="10%" align="center">小数</th>
                            <th width="10%" align="center">日期</th>
                            <th width="10%" align="center">时间</th>
                            <th width="5%" align="center">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${sampleDto.items}" varStatus="status">
                        <tr>
                            <td>
                                <form:hidden path="items[${status.index}].id" />
                                <form:hidden path="items[${status.index}].createUser" />
                                <form:hidden path="items[${status.index}].createTime" />
                                <form:input path="items[${status.index}].itemName" cssClass="form-control" />
                            </td>
                            <td><e:codelist codeno="YES_OR_NO" path="items[${status.index}].itemType" cssClass="form-control" /></td>
                            <td><form:input path="items[${status.index}].itemNumInt" cssClass="form-control" /></td>
                            <td><form:input path="items[${status.index}].itemNumDouble" cssClass="form-control" /></td>
                            <td><e:date path="items[${status.index}].itemDate" cssClass="form-control" /></td>
                            <td><e:date path="items[${status.index}].itemTime" type="yyyy-mm-dd hh:ii" cssClass="form-control" /></td>
                            <td><i class="fa fa-trash" onclick="removeRow(this)"></i></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </div>
                <div class="form-group">
                    <div class="col-md-12 text-right">
                        <span class="btn btn-default fileinput-button"><span>上传</span><input id="file" type="file" name="files" multiple></span>
                    </div>
                </div>
                <div class="form-group">
                    <!-- The global progress bar -->
                    <div id="uploadProcess" style="display:none;">
                    <div id="progressBarId" class="progress">
                        <div id="progressId" class="progress-bar" style="text-align: right;">
                            <span id="progressTextId" style="color:#fff;"></span>
                        </div>
                    </div>
                    <span id="uploadfileName"></span><i class="fa fa-close fa-lg" style="float:right;" onclick="cancelFileUpload();" title="取消上传"></i>
                    </div>

                    <!-- The container for the uploaded files -->
                    <div id="fileList" class="files col-md-12"></div>
                </div>
        </div>
        <div class="panel-footer text-right">
            <button type="button" class="btn btn-primary" id="btnSave">保存</button>
            <button type="button" class="btn btn-default" id="btnBack">返回</button>
        </div>
    </form:form>
    <form:form action="" modelAttribute="emptySampleDto">
        <table style="display: none;" id="template">
            <tr>
                <td><form:hidden path="items[0].id" /> <form:hidden path="items[0].createUser" /> <form:hidden path="items[0].createTime" /> <form:input path="items[0].itemName"
                        cssClass="form-control" /></td>
                <td><e:codelist codeno="YES_OR_NO" path="items[0].itemType" cssClass="form-control" /></td>
                <td><form:input path="items[0].itemNumInt" cssClass="form-control" /></td>
                <td><form:input path="items[0].itemNumDouble" cssClass="form-control" /></td>
                <td><e:date path="items[0].itemDate" cssClass="form-control" /></td>
                <td><e:date path="items[0].itemTime" format="yyyy-mm-dd hh:ii" cssClass="form-control" /></td>
                <td><i class="fa fa-trash" onclick="removeRow(this)"></i></td>
            </tr>
        </table>
    </form:form>
</body>
</html>