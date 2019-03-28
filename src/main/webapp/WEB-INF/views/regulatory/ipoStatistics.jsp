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
/* .x-th-ltr,.x-grid .x-grid-htable th.x-th-ltr {
  border-right: 1px solid #dddddd;
  border-left: 1px solid #dddddd;
} */
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
.t-select-view{
    height:300px !important;
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
                	<tr style="background: #E8E8E8;">
                	 	<td colspan="1"></td>
                	 	<td colspan="3" style="border-right-color: #14bcf5;">标准制</td>
                	 	<td colspan="1" style="border-top-width:0.3px;;border-top-style:solid;color:#0099cc;border-bottom: none;border-right-color: #14bcf5;border-left-color: #14bcf5;border-top-color: #14bcf5;">注册制</td>
                	 	<td colspan="1" rowspan="2">合计</td>
     				</tr>
                    <tr style="background: #E8E8E8;">
                        <td style="width: 25%;" colspan="1"></td>
                        <td style="width: 15%;" colspan="1">沪主板</td>
                        <td style="width: 15%;" colspan="1">中小板</td>
                        <td style="width: 15%;border-right-color: #14bcf5;border-top-color: #14bcf5;" colspan="1">创业板</td>
                        <td style="width: 15%;color:#0099cc;border-bottom: none;border-right-color: #14bcf5;" colspan="1" >科创板</td>
                        <!-- <td style="width: 15%;" colspan="1" >合计</td> -->
                    </tr>
            </table>
            <div id="ipoHistoryChart" class="col-md-12 no-padding-vr" style="height: 250px;" align="center"></div>
        </div>
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
                    <label class="control-label col-md-2 no-padding-r">项目公司所属行业</label>
                    <div class="col-md-3 no-padding-r" style="margin-left:-50px;">
                        <input id="industry" json-data='${industrySelectList}' type="text" class="form-control t-select" placeholder="请选择行业" style="background-color: #fff;"/> 
                        <input type="hidden" name="industry" id="industrySelect"/>
                    </div>
                    <label class="control-label col-md-2 no-padding-r">项目公司注册地</label>
                    <div class="col-md-3 no-padding-r" style="margin-left:-60px;position:ralative;z-index:9999999" >
                        <input id="registAddr" json-data='${areaList}' type="text" class="form-control t-select" placeholder="请选择注册地" style="background-color: #fff;"  /> 
                        <input type="hidden" name="registAddr" id="registAddrSelect"/>
                    </div>
<!--                     <div class="col-md-3 no-padding-r" align="right"> -->
<!--                         <span id="clearAllOption" class="btn btn-4 btn-bny">清空条件</span> -->
<!--                     </div> -->
                </div>
            </form:form>
        </div>
        <div class="col-md-4 form-group-margin no-padding">
            <ul id="myTab" class="nav nav-tabs nav-tabs-simple nav-tabs-sm nav-justified" >
                <li class="active"><a href="#tab1" data-toggle="tab" onclick="borderShow()">保荐机构</a></li>
                <li><a href="#tab2" data-toggle="tab" onclick="borderShow()">律师事务所</a></li>
                <li><a href="#tab3" data-toggle="tab" onclick="borderShow()">会计师事务所</a></li>
            </ul>
        </div>
        <!--  <div id="borderShow"
			 style="display:none;border:1px solid;width: 9.7%;height: 3%;border-right-color: #14bcf5;border-left-color: #14bcf5;border-top-color: #14bcf5;border-bottom:none;position:absolute;top:44%;left: 69%;z-index:999000;margin-left: 0.40%;margin-top: -0.28%;">
		</div> -->
        <div class="panel-body">
            <div class="tab-content">
                <div  class="tab-pane fade in active" id="tab1">
                    <div class="table-primary" style="min-height:900px">
                        <e:grid id="recommendOrg" action="/regulatory_statistics/getIPORecommendOrgStts?access_token=${access_token}"
                             cssClass="table table-striped table-hover" defaultPageSize="20" > 
                             <e:gridColumn label="序号" renderColumn="renderColumnIndex" 
                                       orderable="false" cssClass="text-center" cssStyle="width:10%" /> 
                             <e:gridColumn label="保荐机构" displayColumn="label" 
                                           orderable="false" cssClass="text-left" cssStyle="width:30%"/> 
                             <e:gridColumn label="沪主板" renderColumn="renderColumnhzbCount" orderColumn="1" 
                                           cssClass="text-center" cssStyle="width:10%"/> 
                             <e:gridColumn label="中小板" renderColumn="renderColumnzxbCount"  orderColumn="2" 
                                          cssClass="text-center" cssStyle="width:10%"/> 
                             <e:gridColumn label="创业板" renderColumn="renderColumncybCount"  orderColumn="3" 
                                           cssClass="text-center" cssStyle="width:10%;border-right-color: #14bcf5;"/> 
                             <e:gridColumn label="科创板" renderColumn="renderColumnByKC1"  orderColumn="6" 
                                           cssClass="text-center" cssStyle="width:10%;border-right-color: #14bcf5;"/> 
                             <e:gridColumn label="合计" renderColumn="renderColumnTotalCount" orderColumn="4" 
                                          cssClass="text-center" cssStyle="width:10%"/> 
                             <e:gridColumn label="市场占比" renderColumn="renderColumnPercent" orderColumn="5"
                                          cssClass="text-center" cssStyle="width:10%"/>
                        </e:grid>
                   </div>
                </div>
                <div class="tab-pane fade" id="tab2">
                    <div class="table-primary" style="min-height:900px">
                        <e:grid id="lawsfirm" action="/regulatory_statistics/getIPOLawFirmStts?access_token=${access_token}"
                            cssClass="table table-striped table-hover" defaultPageSize="20">
                            <e:gridColumn label="序号" renderColumn="renderColumnIndex"
                                      orderable="false" cssClass="text-center" cssStyle="width:10%"/>
                            <e:gridColumn label="律师事务所" displayColumn="label"
                                          orderable="false" cssClass="text-left" cssStyle="width:30%"/>
                            <e:gridColumn label="沪主板" renderColumn="renderColumnLawhzbCount" orderColumn="1"
                                          cssClass="text-center" cssStyle="width:10%"/>
                            <e:gridColumn label="中小板" renderColumn="renderColumnLawzxbCount" orderColumn="2"
                                          cssClass="text-center" cssStyle="width:10%"/>
                            <e:gridColumn label="创业板" renderColumn="renderColumnLawcybCount" orderColumn="3"
                                          cssClass="text-center" cssStyle="width:10%;border-right-color: #14bcf5;"/>
                            <e:gridColumn label="科创板" renderColumn="renderColumnByKC2"  orderColumn="3" 
                                           cssClass="text-center" cssStyle="width:10%;border-right-color: #14bcf5;"/> 
                            <e:gridColumn label="合计" renderColumn="renderColumnLawTotalCount" orderColumn="4"
                                          cssClass="text-center" cssStyle="width:10%"/>
                            <e:gridColumn label="市场占比" renderColumn="renderColumnPercent" orderColumn="5"
                                          cssClass="text-center" cssStyle="width:10%"/>
                        </e:grid>
                   </div>
                </div>
                <div class="tab-pane fade" id="tab3">
                    <div class="table-primary" style="min-height:900px">
                        <e:grid id="accountantOffice" action="/regulatory_statistics/getIPOAccountantOfficeStts?access_token=${access_token}"
                            cssClass="table table-striped table-hover" defaultPageSize="20">
                            <e:gridColumn label="序号" renderColumn="renderColumnIndex"
                                      orderable="false" cssClass="text-center" cssStyle="width:10%"/>
                            <e:gridColumn label="会计师事务所" displayColumn="label"
                                          orderable="false" cssClass="text-left" cssStyle="width:30%"/>
                            <e:gridColumn label="沪主板" renderColumn="renderColumnAccounthzbCount" orderColumn="1"
                                          cssClass="text-center" cssStyle="width:10%"/>
                            <e:gridColumn label="中小板" renderColumn="renderColumnAccountzxbCount" orderColumn="2"
                                          cssClass="text-center" cssStyle="width:10%"/>
                            <e:gridColumn label="创业板" renderColumn="renderColumnAccountcybCount" orderColumn="3"
                                          cssClass="text-center" cssStyle="width:10%;border-right-color: #14bcf5;"/>
                             <e:gridColumn label="科创板" renderColumn="renderColumnByKC3"  orderColumn="3" 
                                           cssClass="text-center" cssStyle="width:10%;border-right-color: #14bcf5;"/> 
                            <e:gridColumn label="合计" renderColumn="renderColumnAccountTotalCount" orderColumn="4"
                                          cssClass="text-center" cssStyle="width:10%"/>
                            <e:gridColumn label="市场占比" renderColumn="renderColumnPercent" orderColumn="5"
                                          cssClass="text-center" cssStyle="width:10%"/>
                        </e:grid>
                    </div>
                </div>
            </div>
        </div>
<!--         <div id="hiddenDiv" style="height:90px;"> -->
<!--          </div> -->
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
