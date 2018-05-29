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
<!--需求4399 2018/5/24 by liuh Start-->
    <div class="panel-body no-padding regulatory-model">
        <div class="col-md-12 no-padding-hr">
            <div class="sub-title">IPO在审项目数据统计</div>
        </div>
        <div id="ipoChart" class="col-md-7 no-padding-vr chart-content" align="center"></div>
        <div class="col-md-5">
            <table id="review">
                <thead>
                    <tr style="background: #E8E8E8;">
                        <th style="width: 40%;"></th>
                        <th style="width: 15%;">沪主板</th>
                        <th style="width: 15%;">中小板</th>
                        <th style="width: 15%;">创业板</th>
                        <th style="width: 15%;">合计</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <div id="ipoHistoryChart" class="col-md-5 no-padding-vr" style="height: 250px;" align="center"></div>
        <div class="col-md-12 no-padding">
            <hr style="margin: 20px 15px;" />
        </div>
<!--         <div class="col-md-12 no-padding-hr"> -->
<!--             <div class="sub-title">保荐机构统计</div> -->
<!--         </div> -->
<!--         <div class="col-md-12 cart-bottom-table" style="margin-top: 10px;"> -->
<!--             <table id="recommendOrg"> -->
<!--                 <thead> -->
<!--                     <tr style="background: #E8E8E8;"> -->
<!--                         <th style="width: 5%;">序号</th> -->
<!--                         <th style="width: 35%;">保荐机构</th> -->
<!--                         <th style="width: 12%;">沪主板</th> -->
<!--                         <th style="width: 12%;">中小板</th> -->
<!--                         <th style="width: 12%;">创业板</th> -->
<!--                         <th style="width: 12%;">合计</th> -->
<!--                         <th style="width: 12%;">比例</th> -->
<!--                     </tr> -->
<!--                 </thead> -->
<!--                 <tbody> -->
<!--                 </tbody> -->
<!--             </table> -->
<!--         </div> -->
<!--         <div class="col-md-12 no-padding"> -->
<!--             <hr style="margin: 20px 15px;" /> -->
<!--         </div> -->
<!--         <div class="col-md-12 no-padding-hr"> -->
<!--             <div class="sub-title">会计师事务所统计</div> -->
<!--         </div> -->
<!--         <div class="col-md-12 cart-bottom-table" style="margin-top: 10px;"> -->
<!--             <table id="accountantOffice"> -->
<!--                 <thead> -->
<!--                     <tr style="background: #E8E8E8;"> -->
<!--                         <th style="width: 5%;">序号</th> -->
<!--                         <th style="width: 35%;">会计师事务所</th> -->
<!--                         <th style="width: 12%;">沪主板</th> -->
<!--                         <th style="width: 12%;">中小板</th> -->
<!--                         <th style="width: 12%;">创业板</th> -->
<!--                         <th style="width: 12%;">合计</th> -->
<!--                         <th style="width: 12%;">比例</th> -->
<!--                     </tr> -->
<!--                 </thead> -->
<!--                 <tbody> -->
<!--                 </tbody> -->
<!--             </table> -->
<!--         </div> -->
<!--         <div class="col-md-12 no-padding"> -->
<!--             <hr style="margin: 20px 15px;" /> -->
<!--         </div> -->
<!--         <div class="col-md-12 no-padding-hr"> -->
<!--             <div class="sub-title">律师事务所统计</div> -->
<!--         </div> -->
<!--         <div class="col-md-12 cart-bottom-table" style="margin-top: 10px;"> -->
<!--             <table id="lawsfirm"> -->
<!--                 <thead> -->
<!--                     <tr style="background: #E8E8E8;"> -->
<!--                         <th style="width: 5%;">序号</th> -->
<!--                         <th style="width: 35%;">律师事务所</th> -->
<!--                         <th style="width: 12%;">沪主板</th> -->
<!--                         <th style="width: 12%;">中小板</th> -->
<!--                         <th style="width: 12%;">创业板</th> -->
<!--                         <th style="width: 12%;">合计</th> -->
<!--                         <th style="width: 12%;">比例</th> -->
<!--                     </tr> -->
<!--                 </thead> -->
<!--                 <tbody> -->
<!--                 </tbody> -->
<!--             </table> -->
<!--         </div> -->
        <div class="col-md-12" style="padding-left: 10px;">
            <form:form action="" modelAttribute="statisticsParamDto" id="queryForm">
                <div class="row" style="padding: 10px 10px;">
                    <label class="control-label col-md-1">项目公司所属行业</label>
                    <div class="col-md-3 no-padding-r">
                        <input id="industry" json-data='${industrySelectList}' type="text" class="form-control t-select" placeholder="请选择行业" style="background-color: #fff;"/> 
                        <input type="hidden" name="industry" />
                    </div>
                    <label class="control-label col-md-1">项目公司注册地</label>
                    <div class="col-md-3 no-padding-r">
                        <input id="registAddr" json-data='${areaList}' type="text" class="form-control t-select" placeholder="请选择注册地" style="background-color: #fff;"/> 
                        <input type="hidden" name="registAddr" />
                    </div>
                </div>
            </form:form>
        </div>
        <div class="col-md-4 form-group-margin no-padding">
            <ul id="myTab" class="nav nav-tabs nav-tabs-simple nav-tabs-sm nav-justified" >
                <li class="active"><a href="#tab1" data-toggle="tab">保荐机构</a></li>
                <li><a href="#tab2" data-toggle="tab">律师事务所</a></li>
                <li><a href="#tab3" data-toggle="tab">会计师事务所</a></li>
            </ul>
        </div>
        <div class="panel-body" >
            <div class="tab-content">
                <div  class="tab-pane fade in active" id="tab1">
                    <div class="table-primary">
                        <e:grid id="recommendOrg" action="/regulatory_statistics/getIPORecommendOrgStts?access_token=${access_token}"
                             cssClass="table table-striped table-hover" defaultPageSize="20"> 
                             <e:gridColumn label="序号" renderColumn="renderColumnIndex" 
                                       orderable="false" cssClass="text-center" cssStyle="width:10%"/> 
                             <e:gridColumn label="保荐机构" displayColumn="label" 
                                           orderable="false" cssClass="text-left" cssStyle="width:40%"/> 
                             <e:gridColumn label="沪主板" displayColumn="hzbCount" orderColumn="1" 
                                           cssClass="text-center" cssStyle="width:10%"/> 
                             <e:gridColumn label="中小板" displayColumn="zxbCount" orderColumn="2" 
                                          cssClass="text-center" cssStyle="width:10%"/> 
                             <e:gridColumn label="创业板" displayColumn="cybCount" orderColumn="3" 
                                           cssClass="text-center" cssStyle="width:10%"/> 
                             <e:gridColumn label="合计" displayColumn="totalCount" orderColumn="4" 
                                          cssClass="text-center" cssStyle="width:10%"/> 
                             <e:gridColumn label="市场占比" displayColumn="percent" orderColumn="5"
                                          cssClass="text-center" cssStyle="width:10%"/>
                        </e:grid>
                   </div>
                </div>
                <div class="tab-pane fade" id="tab2">
                    <div class="table-primary">
                        <e:grid id="lawsfirm" action="/regulatory_statistics/getIPOLawFirmStts?access_token=${access_token}"
                            cssClass="table table-striped table-hover" defaultPageSize="20">
                            <e:gridColumn label="序号" renderColumn="renderColumnIndex"
                                      orderable="false" cssClass="text-center" cssStyle="width:10%"/>
                            <e:gridColumn label="律师事务所" displayColumn="label"
                                          orderable="false" cssClass="text-left" cssStyle="width:40%"/>
                            <e:gridColumn label="沪主板" displayColumn="hzbCount" orderColumn="1"
                                          cssClass="text-center" cssStyle="width:10%"/>
                            <e:gridColumn label="中小板" displayColumn="zxbCount" orderColumn="2"
                                          cssClass="text-center" cssStyle="width:10%"/>
                            <e:gridColumn label="创业板" displayColumn="cybCount" orderColumn="3"
                                          cssClass="text-center" cssStyle="width:10%"/>
                            <e:gridColumn label="合计" displayColumn="totalCount" orderColumn="4"
                                          cssClass="text-center" cssStyle="width:10%"/>
                            <e:gridColumn label="市场占比" displayColumn="percent" orderColumn="5"
                                          cssClass="text-center" cssStyle="width:10%"/>
                        </e:grid>
                   </div>
                </div>
                <div class="tab-pane fade" id="tab3">
                    <div class="table-primary">
                        <e:grid id="accountantOffice" action="/regulatory_statistics/getIPOAccountantOfficeStts?access_token=${access_token}"
                            cssClass="table table-striped table-hover" defaultPageSize="20">
                            <e:gridColumn label="序号" renderColumn="renderColumnIndex"
                                      orderable="false" cssClass="text-center" cssStyle="width:10%"/>
                            <e:gridColumn label="会计师事务所" displayColumn="label"
                                          orderable="false" cssClass="text-left" cssStyle="width:40%"/>
                            <e:gridColumn label="沪主板" displayColumn="hzbCount" orderColumn="1"
                                          cssClass="text-center" cssStyle="width:10%"/>
                            <e:gridColumn label="中小板" displayColumn="zxbCount" orderColumn="2"
                                          cssClass="text-center" cssStyle="width:10%"/>
                            <e:gridColumn label="创业板" displayColumn="cybCount" orderColumn="3"
                                          cssClass="text-center" cssStyle="width:10%"/>
                            <e:gridColumn label="合计" displayColumn="totalCount" orderColumn="4"
                                          cssClass="text-center" cssStyle="width:10%"/>
                            <e:gridColumn label="市场占比" displayColumn="percent" orderColumn="5"
                                          cssClass="text-center" cssStyle="width:10%"/>
                        </e:grid>
                    </div>
                </div>
            </div>
        </div>
        <input type="hidden" id="tokenValue" value="${access_token}">
<!--         <div class="col-md-12 no-padding"> -->
<!--             <hr style="margin: 20px 15px;" /> -->
<!--         </div> -->
<!--         <div class="col-md-12 no-padding" style="margin-left: 20px;"> -->
<!--             <p id="updateTime" style="font-size: 12px; color: #909090;"></p> -->
<!--         </div> -->
<!--需求4399 2018/5/24 by liuh end-->
    </div>
</body>
</html>
