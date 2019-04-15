<template>
  <div class="companyProfile" v-loading="listLoading" element-loading-text="给我一点时间">
    <!-- 公司简介 -->
    <div class="companey">
      <div class="briefIntroduction">
        <p v-if="companyProfileList&&companyProfileList.companyZhName" style="color: #333; font-weight: bold;font-size: 14px;">
          {{companyProfileList.companyZhName}}
        </p>
        <ul style="display:flex;margin-bottom: 0;">
          <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">拟上市板块</span>
            <div v-if="companyProfileList&&companyProfileList.ipoPlate"  style="color: #333333;float:left;display:inline-block;width: 55%;margin-left: 13px;">{{companyProfileList.ipoPlate}}</div>
            <div v-else  style="color: #333333;float:left;display:inline-block;width: 55%;margin-left: 13px;">- -</div>
          </li>
          <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">所属行业(证监会)</span>
            <div v-if="companyProfileList&&companyProfileList.industryCsrc"  style="color: #333333;float:left;display:inline-block;width: 53%;margin-left: 27px;">{{companyProfileList.industryCsrc}}</div>
            <div v-else  style="color: #333333;float:left;display:inline-block;width: 53%;margin-left: 27px;">- -</div>
          </li>
          <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">注册资本</span>
            <div v-if="companyProfileList&&companyProfileList.registeredAssets" style="color: #333333;float:left;display:inline-block;width: 60%;margin-left: 27px;">{{companyProfileList.registeredAssets | dataInThRule}}万元</div>
            <div v-else style="color: #333333;float:left;display:inline-block;width: 60%;margin-left: 27px;">- -</div>
          </li>
          <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">公司简称</span>
            <div v-if="companyProfileList&&companyProfileList.companyName" style="color: #333333;float:left;display:inline-block;width: 60%;margin-left: 27px;">{{companyProfileList.companyName}}</div>
            <div v-else style="color: #333333;float:left;display:inline-block;width: 60%;margin-left: 27px;">- -</div>
          </li>
          <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">证券代码</span>
            <div  v-if="companyProfileList&&companyProfileList.companyCode"  style="color: #333333;float:left;display:inline-block;width: 60%;line-height:19px;margin-left: 27px;">{{companyProfileList.companyCode}}</div>
            <div v-else  style="color: #333333;float:left;display:inline-block;width: 60%;line-height:19px;margin-left: 27px;">- -</div>
          </li>
          <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">企业性质</span>
            <div v-if="companyProfileList&&companyProfileList.companyNature"  style="color: #333333;float:left;display:inline-block;width: 60%;margin-left: 27px;">{{companyProfileList.companyNature}}</div>
            <div v-else  style="color: #333333;float:left;display:inline-block;width: 60%;margin-left: 27px;">- -</div>
          </li>
        </ul>
        <div>
          <li class="clear" style="margin-bottom:10px;position:relative" >
            <span class="l">注册地址</span>
            <div style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 27px;" v-if="companyProfileList&&(companyProfileList.addrProv || companyProfileList.addrCity || companyProfileList.addrArea)" >{{companyProfileList.addrProv}}{{companyProfileList.addrCity}}{{companyProfileList.addrArea}}</div>
            <div style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 27px;" v-else >- -</div>
          </li>
          <li class="clear" style="margin-bottom:10px;position:relative" >
            <span class="l">控股股东</span>
            <div  style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 27px;" v-if="companyProfileList&&companyProfileList.controlShareholder">{{companyProfileList.controlShareholder}}</div>
            <div style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 27px;" v-else>- -</div>
          </li>
          <li  class="clear" style="margin-bottom:10px;position:relative" >
            <span  class="l">实际控制人</span>
            <div  style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 14px;" v-if="companyProfileList&&companyProfileList.actualController" >{{companyProfileList.actualController}}</div>
            <div style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 14px;"  v-else >- -</div>
          </li>
          <!-- 发行人选择的上市标准 -->
          <li class="clear" v-if="companyProfileList&&companyProfileList.issueCondition !=''" style="margin-bottom:10px;position:relative" >
            <span v-if="companyProfileList.issueCondition" style="display: inline-block;width: 65px;float:left">发行人选择的上市标准</span>
            <div v-if="companyProfileList&&companyProfileList.issueCondition ==''" style="color: #333333;margin-left: 9.4%;margin-top: -4.8%;">- -</div>
            <div v-else style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 1.6%;">
              <div v-if="companyProfileList.issueCondition&&companyProfileList.issueCondition.indexOf('101')>-1">预计市值不低于人民币<span style="color:red">10亿元</span>，最近两年净利润均为正且累计净利润不低于人民币<span style="color:red">5000万元</span>，或者预计市值不低于人民币<span style="color:red">10亿元</span>，最近一年净利润为正且营业收入不低于人民币<span style="color:red">1亿元</span></div>
              <div v-if="companyProfileList.issueCondition&&companyProfileList.issueCondition.indexOf('102')>-1">预计市值不低于人民币<span style="color:red">15亿元</span>，最近一年营业收入不低于人民币<span style="color:red">2亿元</span>，且最近三年累计研发投入占最近三年累计营业收入的比例不低于<span style="color:red">15%</span></div>
              <div v-if="companyProfileList.issueCondition&&companyProfileList.issueCondition.indexOf('103')>-1">预计市值不低于人民币<span style="color:red">20亿元</span>，最近一年营业收入不低于人民币<span style="color:red">3亿元</span>，且最近三年经营活动产生的现金流量净额累计不低于人民币<span style="color:red">1亿元</span></div>
              <div v-if="companyProfileList.issueCondition&&companyProfileList.issueCondition.indexOf('104')>-1">预计市值不低于人民币<span style="color:red">30亿元</span>，且最近一年营业收入不低于人民币<span style="color:red">3亿元</span></div>
              <div v-if="companyProfileList.issueCondition&&companyProfileList.issueCondition.indexOf('105')>-1">预计市值不低于人民币<span style="color:red">40亿元</span>，主要业务或产品需经国家有关部门批准，市场空间大，目前已取得阶段性成果。医药行业企业需至少有一项核心产品获准开展二期临床试验，其他符合科创板定位的企业需具备明显的技术优势并满足相应条件</div>
              <div v-if="companyProfileList.issueCondition&&companyProfileList.issueCondition.indexOf('201')>-1">预计市值不低于人民币<span style="color:red">100亿元</span></div>
              <div v-if="companyProfileList.issueCondition&&companyProfileList.issueCondition.indexOf('202')>-1">预计市值不低于人民币<span style="color:red">50亿元</span>，且最近一年营业收入不低于人民币<span style="color:red">5亿元</span></div>
              <div v-if="companyProfileList.issueCondition&&companyProfileList.issueCondition.indexOf('301')>-1">预计市值不低于人民币<span style="color:red">100亿元</span></div>
              <div v-if="companyProfileList.issueCondition&&companyProfileList.issueCondition.indexOf('302')>-1">预计市值不低于人民币<span style="color:red">50亿元</span>，且最近一年营业收入不低于人民币<span style="color:red">5亿元</span></div>
            </div>
          </li>
          <li class="clear" style="position:relative;margin-top:0%;margin-bottom:16px">
            <span class="l" >主营业务</span>
            <div  style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 27px;" v-if="companyProfileList&&companyProfileList.majorBusinesses">{{companyProfileList.majorBusinesses}}</div>
            <div  style="color: #333333;float:left;display:inline-block;width: 80%;margin-left: 27px;" v-else >- -</div>
          </li>
        </div>
      </div>
      <div class="others" v-if="this.otherMarketInfoList&&this.otherMarketInfoList.length>0">
        <p style="color:black">登录其他资本市场</p>
        <ul v-for="item in otherMarketInfoList">
          <li style=" width: 30%;position:relative">
            <span>资本市场</span>&nbsp;&nbsp;
            <div style="color:black;margin-left: 25%;margin-top: -17.5px;">{{item.marketType}}</div>
          </li>
          <li style=" width: 20%;">
            <span>公司代码</span>&nbsp;&nbsp;
            <span style="color:black">{{item.companyCode}}</span>
          </li>
          <li style=" width: 25%;">
            <span>上市日/挂牌日</span>&nbsp;&nbsp;
            <span style="color:black">{{item.listTime}}</span>
          </li>
          <li style=" width: 25%;">
            <span>退市日/摘牌日</span>&nbsp;&nbsp;
            <span style="color:black">{{item.delistTime }}</span>
          </li>
        </ul>
      </div>
    </div>
    <!-- 股权结构图 -->
    <div class="ownershipStructure" style="margin-top:32px">
      <div class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="ownershipStructureChart">股权结构图</span>
        <span v-if="companyProfileList&&companyProfileList.structureLabel">
           <span v-for="(item,index) in companyProfileList.structureLabel.split(',')" class="hongkuang">{{item}}</span>
        </span>
      </div>
      <!-- 图片 -->
      <div class="img" v-if="companyProfileList&&companyProfileList.structureUrl">
         <img :src="companyProfileList.structureUrl" class="imgOrg"  width="100%" style="padding:0px;margin:0px;cursor: pointer;" @click="imgchange($event)">
         <!-- <img preview="1"  :src="companyProfileList.structureUrl" class="zoom-in zoom-out" style="cursor:zoom-in"  width="100%"> -->
      </div>
      <!-- 股权股东表格 -->
      <p v-if="gqTableList&&gqTableList.length>0" style="font-size:12px;color:#666;float:right">单位：万股</p>
      <el-table v-if="gqTableList&&gqTableList.length>0" :data="gqTableList" style="width: 100%" stripe border>
        <el-table-column type="index" label="序号" align='center' width="75px">
           <template slot-scope="scope">
                    {{scope.$index+1}}
            </template>
        </el-table-column>
        <el-table-column prop="personName" label="股东名称">
           <template slot-scope="scope">
              <span>{{isNotEmpty(scope.row.personName) ? scope.row.personName : '- -'}}</span>
           </template>
        </el-table-column>
        <el-table-column prop="shareHolderNature" label="股东性质">
           <template slot-scope="scope">
              <span>{{isNotEmpty(scope.row.shareHolderNature) ? scope.row.shareHolderNature : '- -'}}</span>
           </template>
        </el-table-column>
        <el-table-column prop="shareNum" label="持股数量" align='right'>
           <template slot-scope="scope">
                <span v-if="scope.row.shareNum"> {{scope.row.shareNum | dataInThRule}}</span>
                <span v-else> - - </span>
           </template>
        </el-table-column>
        <el-table-column prop="shareRatio" label="持股比例" align='right'>
           <template slot-scope="scope">
              <span v-if="scope.row.shareRatio"> {{scope.row.shareRatio | dataInThRule}}%</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 主营业务收入构成 -->
    <div class="incomeComposition">
      <div class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="mainBusinessIncomeComposition">主营业务收入构成</span>
      </div>
      <div class="echart clear">
        <barOrPieChart v-if="mainTableList.firstYearForIncome" :mainTableList = "mainTableList"></barOrPieChart>
      </div>
      <!-- table表格 -->
      <div class="incomeCompositionTable">
         <mainTable :mainTableList = "mainTableList"></mainTable>
      </div>
    </div>
    <!-- 主要竞争对手简介 -->
    <div class="MajorCompetitors">
      <div v-if="MajorCompetitors&&MajorCompetitors.length>0" class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="mainCompetitors">主要竞争对手简介</span>
      </div>
      <div class="competitorContent">
        <ul class="competitorUl" style="padding-left:0">
          <li class="clear competitorLi" v-for="data in MajorCompetitors" style="display: table;	width: 100%;height: 100%;*position: relative;">
            <div class="leftTitle" style="width: 16%;display: table-cell;vertical-align: middle;*position: absolute;*top: 50%;">
              <p style="font-family: 'PingFang-SC-Bold', 'PingFang SC Bold', 'PingFang SC';
                font-weight: 700;
                font-style: normal;
                font-size: 14px;
                color: #363636;
                text-align: center;
                line-height: 14px;"
              >{{data.companyName}}</p>
              <p style="color: #999999;font-size: 12px;text-align: center;">{{data.companyCode}}</p>
            </div>
            <div class="rightContent right" style=" font-family: 'PingFang-SC-Regular', 'PingFang SC';
                font-weight: 400;
                font-style: normal;
                font-size: 14px;
                color: #555555;
                line-height: 22px;
                float: right;
                width: 84%;
                margin-left: 39px;
                display: table-cell;vertical-align: middle;*position: absolute;*top: 50%;">
             <p class="moreText">{{data.situation}}</p>
            </div>
          </li>
        </ul>
      </div>
    </div>
    <!-- 报告期主要供应商及客户情况 -->
    <div class="theTopFive">
       <div  class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="majorSuppliers" style="font-size:18px">报告期主要供应商及客户情况</span>
      </div>
      <div class="theTopFiveSupplier">
        <p style="font-size:16px" v-if="supplierMainList&&supplierMainList.length>0">报告期前五名供应商情况</p>
        <div v-for="item in supplierMainList" :key="item.id" >
            <p v-if="item.remark" style="font-size:14px;color:#666">{{item.remark}}</p>
            <span v-if="item.supplierCustomerInfoList&&item.supplierCustomerInfoList.length>0" style="font-size:12px;color:#666;float:right;margin-bottom: 5px;">单位：万元</span>
             <el-table :data="item.supplierCustomerInfoList" border style="width: 100%;margin-top: 20px">
              <el-table-column fixed align="center" class-name="table_cell" label="排名" width="52">
                <template slot-scope="scope">
                  {{scope.$index+1}}
                  </template>
              </el-table-column>
              <el-table-column fixed prop="companyName" align="left" class-name="table_cell" width="180" label="公司" >
                   <template slot-scope="scope">
                      <span class="companyNameClass" v-if="scope.row.companyName" @mouseenter="mouseOverCompanyName(scope.row.companyName)" >
                          {{getCompanyName(scope.row.companyName)}}
                      </span>
                      <span v-else> - - </span>
                  </template>
              </el-table-column>
              <el-table-column :label="item.reportPeriod" header-align="center">
                <el-table-column align="left"  class-name="table_cell" label="采购内容" width="136">
                  <template slot-scope="scope">
                      <span class="contentClass" v-if="scope.row.onePeriodContent" @mouseenter="mouseOverContent(scope.row.onePeriodContent)" >
                          {{getContent(scope.row.onePeriodContent)}}
                      </span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
                <el-table-column align="right"  class-name="table_cell" label="金额" width="96">
                  <template slot-scope="scope">
                      <span v-if="scope.row.onePeriodAmount"> {{scope.row.onePeriodAmount | dataInThRule}}</span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
                <el-table-column align="right"  class-name="table_cell" label="占比" width="82">
                  <template slot-scope="scope">
                      <span v-if="scope.row.onePeriodRatio"> {{scope.row.onePeriodRatio | dataInThRule}}%</span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
              </el-table-column>
              <el-table-column :label="item.thirdYearForSupplier" header-align="center">
                <el-table-column align="left"  class-name="table_cell" label="采购内容" width="136">
                   <template slot-scope="scope">
                        <span class="contentClass" v-if="scope.row.thirdYearContent" @mouseenter="mouseOverContent(scope.row.thirdYearContent)" >
                            {{getContent(scope.row.thirdYearContent)}}
                        </span>
                        <span v-else> - - </span>
                    </template>
                  <!-- <template slot-scope="scope">
                    <span>{{isNotEmpty(scope.row.thirdYearContent) ? scope.row.thirdYearContent : '- -'}}</span>
                  </template> -->
                </el-table-column>
                <el-table-column align="right"  class-name="table_cell" label="金额" width="96">
                  <template slot-scope="scope">
                      <span v-if="scope.row.thirdYearAmount"> {{scope.row.thirdYearAmount | dataInThRule}}</span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
                <el-table-column align="right"  class-name="table_cell" label="占比" width="82">
                  <template slot-scope="scope">
                      <span v-if="scope.row.thirdYearRatio"> {{scope.row.thirdYearRatio | dataInThRule}}%</span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
              </el-table-column>
              <el-table-column :label="item.secondYearForSupplier" header-align="center">
                <el-table-column align="left"  class-name="table_cell" label="采购内容" width="136">
                   <template slot-scope="scope">
                        <span class="contentClass" v-if="scope.row.secondYearContent" @mouseenter="mouseOverContent(scope.row.secondYearContent)" >
                            {{getContent(scope.row.secondYearContent)}}
                        </span>
                        <span v-else> - - </span>
                    </template>
                  <!-- <template slot-scope="scope">
                    <span>{{isNotEmpty(scope.row.secondYearContent ) ? scope.row.secondYearContent  : '- -'}}</span>
                  </template> -->
                </el-table-column>
                <el-table-column align="right"  class-name="table_cell" label="金额" width="96">
                  <template slot-scope="scope">
                      <span v-if="scope.row.secondYearAmount"> {{scope.row.secondYearAmount | dataInThRule}}</span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
                <el-table-column align="right"  class-name="table_cell" label="占比" width="82">
                  <template slot-scope="scope">
                      <span v-if="scope.row.secondYearRatio"> {{scope.row.secondYearRatio | dataInThRule}}%</span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
              </el-table-column>
              <el-table-column :label="item.firstYearForSupplier" header-align="center">
                <el-table-column align="left"  class-name="table_cell" label="采购内容" width="136">
                   <template slot-scope="scope">
                        <span class="contentClass" v-if="scope.row.firstYearContent" @mouseenter="mouseOverContent(scope.row.firstYearContent)" >
                            {{getContent(scope.row.firstYearContent)}}
                        </span>
                        <span v-else> - - </span>
                    </template>
                  <!-- <template slot-scope="scope">
                    <span>{{isNotEmpty(scope.row.firstYearContent ) ? scope.row.firstYearContent  : '- -'}}</span>
                  </template> -->
                </el-table-column>
                <el-table-column align="right"  class-name="table_cell" label="金额" width="96">
                  <template slot-scope="scope">
                      <span v-if="scope.row.firstYearAmount"> {{scope.row.firstYearAmount | dataInThRule}}</span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
                <el-table-column align="right"  class-name="table_cell" label="占比" width="82">
                  <template slot-scope="scope">
                      <span v-if="scope.row.firstYearRatio"> {{scope.row.firstYearRatio | dataInThRule}}%</span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
              </el-table-column>
             </el-table>
        </div>
      </div>
       <div class="theTopFiveKh">
        <p v-if="customerMainList&&customerMainList.length>0" style="font-size:16px">报告期前五名客户情况</p>
          <div v-for="item in customerMainList" :key="item.id" >
              <p style="font-size:14px;color:#666">{{item.remark}}</p>
              <span v-if="item.supplierCustomerInfoList&&item.supplierCustomerInfoList.length>0" style="font-size:12px;color:#666;float:right;margin-bottom: 5px;">单位：万元</span>
              <el-table :data="item.supplierCustomerInfoList" border style="width: 100%;margin-top: 20px">
                <el-table-column fixed align="center" class-name="table_cell" label="排名" width="52">
                  <template slot-scope="scope">
                    {{scope.$index+1}}
                    </template>
                </el-table-column>
                <el-table-column fixed prop="companyName" align="left" class-name="table_cell" label="公司" width="185">
                   <template slot-scope="scope">
                      <span class="companyNameClass" v-if="scope.row.companyName" @mouseenter="mouseOverCompanyName(scope.row.companyName)" >
                          {{getCompanyName(scope.row.companyName)}}
                      </span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
                <el-table-column :label="item.reportPeriod" header-align="center">
                  <el-table-column align="left"  class-name="table_cell" label="采购内容" width="136">
                     <template slot-scope="scope">
                        <span class="contentClass" v-if="scope.row.onePeriodContent" @mouseenter="mouseOverContent(scope.row.onePeriodContent)" >
                            {{getContent(scope.row.onePeriodContent)}}
                        </span>
                        <span v-else> - - </span>
                    </template>
                    <!-- <template slot-scope="scope">
                      <span>{{isNotEmpty(scope.row.onePeriodContent) ? scope.row.onePeriodContent : '- -'}}</span>
                    </template> -->
                  </el-table-column>
                  <el-table-column align="right"  class-name="table_cell" label="金额" width="96">
                    <template slot-scope="scope">
                      <span v-if="scope.row.onePeriodAmount"> {{scope.row.onePeriodAmount | dataInThRule}}</span>
                      <span v-else> - - </span>
                    </template>
                  </el-table-column>
                  <el-table-column align="right"  class-name="table_cell" label="占比" width="82">
                    <template slot-scope="scope">
                      <span v-if="scope.row.onePeriodRatio"> {{scope.row.onePeriodRatio | dataInThRule}}%</span>
                      <span v-else> - - </span>
                    </template>
                  </el-table-column>
                </el-table-column>
                <el-table-column :label="item.thirdYearForCustomer" header-align="center">
                  <el-table-column align="left"  class-name="table_cell" label="采购内容" width="136">
                    <!-- <template slot-scope="scope">
                      <span>{{isNotEmpty(scope.row.thirdYearContent) ? scope.row.thirdYearContent : '- -'}}</span>
                    </template> -->
                     <template slot-scope="scope">
                        <span class="contentClass" v-if="scope.row.thirdYearContent" @mouseenter="mouseOverContent(scope.row.thirdYearContent)" >
                            {{getContent(scope.row.thirdYearContent)}}
                        </span>
                        <span v-else> - - </span>
                    </template>
                  </el-table-column>
                  <el-table-column align="right"  class-name="table_cell" label="金额" width="96">
                    <template slot-scope="scope">
                      <span v-if="scope.row.thirdYearAmount"> {{scope.row.thirdYearAmount | dataInThRule}}</span>
                      <span v-else> - - </span>
                    </template>
                  </el-table-column>
                  <el-table-column align="right"  class-name="table_cell" label="占比" width="82">
                    <template slot-scope="scope">
                      <span v-if="scope.row.thirdYearRatio"> {{scope.row.thirdYearRatio | dataInThRule}}%</span>
                      <span v-else> - - </span>
                  </template>
                  </el-table-column>
                </el-table-column>
                <el-table-column :label="item.secondYearForCustomer" header-align="center">
                  <el-table-column align="left"  class-name="table_cell" label="采购内容" width="136">
                     <template slot-scope="scope">
                        <span class="contentClass" v-if="scope.row.secondYearContent" @mouseenter="mouseOverContent(scope.row.secondYearContent)" >
                            {{getContent(scope.row.secondYearContent)}}
                        </span>
                        <span v-else> - - </span>
                    </template>
                    <!-- <template slot-scope="scope">
                      <span>{{isNotEmpty(scope.row.secondYearContent ) ? scope.row.secondYearContent  : '- -'}}</span>
                    </template> -->
                  </el-table-column>
                  <el-table-column align="right"  class-name="table_cell" label="金额" width="96">
                    <template slot-scope="scope">
                      <span v-if="scope.row.secondYearAmount"> {{scope.row.secondYearAmount | dataInThRule}}</span>
                      <span v-else> - - </span>
                    </template>
                  </el-table-column>
                  <el-table-column align="right"  class-name="table_cell" label="占比" width="82">
                    <template slot-scope="scope">
                      <span v-if="scope.row.secondYearRatio"> {{scope.row.secondYearRatio | dataInThRule}}%</span>
                      <span v-else> - - </span>
                  </template>
                  </el-table-column>
                </el-table-column>
                <el-table-column :label="item.firstYearForCustomer" header-align="center">
                  <el-table-column align="left"  class-name="table_cell" label="采购内容" width="136">
                     <template slot-scope="scope">
                        <span class="contentClass" v-if="scope.row.firstYearContent" @mouseenter="mouseOverContent(scope.row.firstYearContent)" >
                            {{getContent(scope.row.firstYearContent)}}
                        </span>
                        <span v-else> - - </span>
                    </template>
                    <!-- <template slot-scope="scope">
                      <span>{{isNotEmpty(scope.row.firstYearContent ) ? scope.row.firstYearContent  : '- -'}}</span>
                    </template> -->
                  </el-table-column>
                  <el-table-column align="right"  class-name="table_cell" label="金额" width="96">
                    <template slot-scope="scope">
                      <span v-if="scope.row.firstYearAmount"> {{scope.row.firstYearAmount | dataInThRule}}</span>
                      <span v-else> - - </span>
                  </template>
                  </el-table-column>
                  <el-table-column align="right"  class-name="table_cell" label="占比" width="82">
                    <template slot-scope="scope">
                      <span v-if="scope.row.firstYearRatio"> {{scope.row.firstYearRatio | dataInThRule}}%</span>
                      <span v-else> - - </span>
                    </template>
                  </el-table-column>
                </el-table-column>
             </el-table>
          </div>
      </div>
    </div>
    <!-- 募集资金运用 -->
    <div class="raiseMoney">
      <div class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="utilizationOfRaisedFunds">募集资金运用</span>
      </div>
      <div class="raiseMoneyTable">
        <!-- 募集资金运用表格 -->
        <p v-if="raiseMoneyTableList&&raiseMoneyTableList.length>0" style="font-size:12px;color:#666;float:right">单位：万元</p>
          <el-table v-if="raiseMoneyTableList&&raiseMoneyTableList.length>0" :data="raiseMoneyTableList" class="raiseMoneyTable" border style="width:100%;">
            <el-table-column label="项目名称" align="left">
                <template slot-scope="scope">
                    <span v-if="scope.row.itemName">{{scope.row.itemName}}</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
            <el-table-column label="项目类型" align="left">
                <template slot-scope="scope">
                      <span v-if="scope.row.itemTypeStr">{{scope.row.itemTypeStr}}</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
            <el-table-column label="项目总投资" align="right" prop="investTotal">
                <template slot-scope="scope">
                      <span v-if="scope.row.investTotal">{{scope.row.investTotal | dataInThRule}}</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
            <el-table-column label="拟投入募集资金金额" align="right" prop="investPlan">
                <template slot-scope="scope">
                      <span v-if="scope.row.investPlan">{{scope.row.investPlan | dataInThRule}}</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
            <el-table-column label="占拟募集资金净额比例" align="right" prop="investRateStr">
                <template slot-scope="scope">
                      <span v-if="scope.row.investRateStr">{{scope.row.investRateStr | dataInThRule}}%</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
            <el-table-column label="前期已投入资金金额" align="right" prop="investPre">
                <template slot-scope="scope">
                      <span v-if="scope.row.investPre">{{scope.row.investPre | dataInThRule}}</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
        </el-table>
        <p class="shuoming" style="font-family: 'PingFang-SC-Regular', 'PingFang SC';
            font-weight: 400;
            font-style: normal;
            color: #666666;
            font-size:14px;
            line-height:20px;
            text-align: left;">
            说明：本次募集资金到位前，公司拟以自筹资金和银行借款先行实施；募集资金到位后，公司将用募集资金置换已投入的资金。如果实际募集资金净额不足以完成上述投资项目，不足部分公司将自筹解决。
        </p>
      </div>
    </div>
    <!-- 中介机构 -->
    <div class="IntermediaryInstitutions">
        <div class="title">
          <span class="littleRectangle"></span>
          <span class="titleText" id="intermediaryInstitutions">中介机构</span>
      </div>
      <IntermediaryInstitutions></IntermediaryInstitutions>
    </div>
  </div>
</template>

<script>


import $ from "jquery";
// import { getCaseDetail } from "@/api/ipoCase/companyProfile";
import { getMarketData } from "@/api/ipoCase/companyProfile";
import { getShareHolderData } from "@/api/ipoCase/companyProfile";
import { getCompetitorData } from "@/api/ipoCase/companyProfile";
import { getRaiseMoneyTableList } from "@/api/ipoCase/companyProfile";
import { getSupplierCustomerData } from '@/api/ipoCase/tableDemo';
import { getTableData } from '@/api/ipoCase/tableDemo';
// 中介机构数据
import { getIntermediaryOrgDataList } from '@/api/ipoCase/companyProfile'
// 导入主营业务收入构成表格
import mainTable from "@/views/tables/mainTable";
// 导入柱形图和饼图
import barOrPieChart from "@/components/Charts/barOrPieChart";
// 导入中介机构
import IntermediaryInstitutions from "@/views/navMenu/companyProfile/components/IntermediaryInstitutions"
export default {
  name: "companyProfile",
  components: {
    mainTable,
    barOrPieChart,
    IntermediaryInstitutions
  },

  data() {
    return {
      isLogin: true,
      listLoading: false,
      gqTableList: [],
      incomeCompositionTableList1: [],

      raiseMoneyTableList: [],
      // 接口
      structureLabel: [],
      structureUrl:'',
      // 公司简介
      caseId:this.$store.state.app.caseId,
      //其他资本市场
      otherMarketInfoList:[],//其他登陆市场
      // 主要竞争者
      MajorCompetitors: [],
      // 前五名供应商数据
      supplierMainList:[],
      // 前五名客户
      customerMainList:[],
      // 主营业务收入
      mainTableList:{
        onePeriodForIncome:'',
        thirdYearForIncome:'',
        secondYearForIncome:'',
        firstYearForIncome:''
      },
      // 中介机构
      moreList:[],
      mainList:[],
      dataFlag:false,
    };
  },
  props:["companyProfileList"],
  created() {
    this.getData();
  },
  mounted() {
  },
  methods: {
    getData() {
      // 动态传id
      const param = {
        id:this.caseId,
      }
      getMarketData(param).then(res=>{
        if(res.data.result&&res.data.result.length>0){
          this.otherMarketInfoList = res.data.result//其他登录市场
        }
          this.getPosition()
      });
      // 股权结构图表格
      getShareHolderData(param).then(res=>{
        if(res.data.result&&res.data.result.length>0){
          this.gqTableList = res.data.result
        }
          this.getPosition()
      });
      getCompetitorData(param).then(res=>{
        if(res.data.result&&res.data.result.length>0){
          this.MajorCompetitors = res.data.result
        }
          this.getPosition()
      });
      getRaiseMoneyTableList(param).then(res=>{
        if(res.data.result&&res.data.result.length>0){
          this.raiseMoneyTableList = res.data.result
        }
          this.getPosition()
      });
      // 供应商
      getSupplierCustomerData(param).then(response => {
        if(response.data.result&&response.data.result.supplierMainList&&response.data.result.supplierMainList.length>0){
          this.supplierMainList = response.data.result.supplierMainList
          this.getPosition()
        }
         if(response.data.result&&response.data.result.customerMainList&&response.data.result.customerMainList.length>0){
           this.customerMainList = response.data.result.customerMainList
        }
           this.getPosition()
      })
      // 主营业务收入构成
      getTableData(param).then(response => {
        if( response.data.result){
          this.mainTableList = response.data.result
        }
          this.getPosition()
      })
      // 中介机构
       getIntermediaryOrgDataList(param).then(response => {
         if((response.data.result&&response.data.result.mainList&&response.data.result.mainList.length>0 ||(response.data.result&&response.data.result.moreList&&response.data.result.moreList.length>0))){
            this.dataFlag = true
          }
              this.getPosition()
      })


    },
    // 鼠标移入公司名
    mouseOverCompanyName(title){
      if(title.length>20){
        $(".companyNameClass").attr("title",title)
        $(".companyNameClass").css({"cursor":"pointer"})
      }else{
        $(".companyNameClass").removeAttr("title",title)
         $(".companyNameClass").css({"cursor":"auto"})
      }
    },
    // 鼠标移入采购内容
    mouseOverContent(title){
      if(title.length>16){
        $(".contentClass").attr("title",title)
        $(".contentClass").css({"cursor":"pointer"})
      }else{
         $(".contentClass").removeAttr("title",title)
         $(".contentClass").css({"cursor":"auto"})
      }
    },
     getCompanyName(title){
      if(title.length>20){
        return title.substring(0,20) + '...'
      }else{
        return title
      }
    },
    getContent(title){
      if(title.length>16){
        return title.substring(0,16) + '...'
      }else{
        return title
      }
    },
    //返回父组件用于锚点定位头
    getPosition() {
          var titleList = [];
          var ownershipStructureChart = {
              id: 'ownershipStructureChart',
              name: '股权结构图',
              notes: '',
              important: false,
              tabId: 'tab-first',
              noClick: true
          }
          var mainBusinessIncomeComposition = {
              id: 'mainBusinessIncomeComposition',
              name: '主营业务收入构成',
              notes: '',
              important: false,
              tabId: 'tab-first',
              noClick: true
          }
          var mainCompetitors = {
              id: 'mainCompetitors',
              name: '主要竞争对手简介',
              notes: '',
              important: false,
              tabId: 'tab-first',
              noClick: true
          }
          var majorSuppliers = {
              id: 'majorSuppliers',
              name: '前五名供应商及用户',
              notes: '',
              important: false,
              tabId: 'tab-first',
              noClick: true
          }
          var utilizationOfRaisedFunds = {
              id: 'utilizationOfRaisedFunds',
              name: '募集资金运用',
              notes: '',
              important: false,
              tabId: 'tab-first',
              noClick: true
          }
          var intermediaryInstitutions = {
              id: 'intermediaryInstitutions',
              name: '中介机构',
              notes: '',
              important: false,
              tabId: 'tab-first',
              noClick: true
          }
          if((this.companyProfileList&&this.companyProfileList.structureUrl) || (this.gqTableList&&this.gqTableList.length>0)){
             ownershipStructureChart.noClick = false;
          }
          if(this.mainTableList.firstYearForIncome){
            mainBusinessIncomeComposition.noClick = false;
          }
          if(this.MajorCompetitors&&this.MajorCompetitors.length>0){
             mainCompetitors.noClick = false;
          }
          if((this.supplierMainList&&this.supplierMainList.length>0) || (this.customerMainList&&this.customerMainList.length>0)){
           majorSuppliers.noClick = false;
          }
          if(this.raiseMoneyTableList&&this.raiseMoneyTableList.length>0){
            utilizationOfRaisedFunds.noClick = false;
          }
          if(this.dataFlag){
            intermediaryInstitutions.noClick = false;
          }
          titleList.push(ownershipStructureChart)
          titleList.push(mainBusinessIncomeComposition)
          titleList.push(mainCompetitors)
          titleList.push(majorSuppliers)
          titleList.push(utilizationOfRaisedFunds)
          titleList.push(intermediaryInstitutions)
          this.$emit('headCallBack', titleList);
    },
     //点击图片弹出大图
        imgchange(event) {
          window.open(event.currentTarget.src);
       },
      // 非空判断
    isNotEmpty(param) {
      if (param != null && param !== undefined && param !== '' && param !== 'null' && param !== 'undefined') {
        return true
      } else {
        return false
      }
    },
  }
};
</script>

<style scoped lang="scss">
.l {
  float: left;
}
.r {
  float: right;
}
.clear:after {
  display: block;
  content: "";
  clear: both;
}
.companyProfile {
  padding: 0px 0px;
  .title {
    border-bottom: 1px solid;
    border-bottom-color: #ebebeb;
    height: 42px;
    line-height: 42px;
    background-color: #fafafa;
    display: flex;
    align-items: center;
    margin-top: 32px;
    margin-bottom: 16px;
    .littleRectangle {
      width: 3px;
      height: 18px;
      background-color: #999999;
      display: inline-block;
      margin-right: 12px;
      margin-top: 0px;
      .titleText {
        font-size: 18px;
        color: #333333;
      }
    }
    .hongkuang {
      display: inline-block;
      // width: 52px;
      height: 22px;
      text-align: center;
      border: 1px solid rgba(255, 176, 173, 1);
      font-family: "MicrosoftYaHei", "Microsoft YaHei";
      font-weight: 400;
      font-style: normal;
      font-size: 12px;
      color: #fe5461;
      line-height: 18px;
      margin-left: 10px;
      padding: 1px;
      border-radius: 2px;
    }
  }
  .companey {
    width: 100%;
    background: #f7f7f7;
    font-family: "Arial Normal", "Arial";
    font-weight: 400;
    font-style: normal;
    font-size: 13px;
    color: #999999;
    text-align: left;
    line-height: normal;
    padding: 5px 10px;
    margin-top: 32px;
    .briefIntroduction {
      border-bottom: 1px solid #ebebeb;
      ul {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        padding-left: 0;
        li {
          width: 33.3%;
          margin-bottom: 10px;
        }
      }
    }
    .others {
      ul {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        padding-left: 0;
      }
    }
  }
}
//表格样式
.el-table thead tr th {
  background: #f2f2f2;
  color: black;
}
.el-table th > .cell {
  position: relative;
  word-wrap: normal;
  text-overflow: ellipsis;
  vertical-align: middle;
  width: 100%;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  text-align: left;
}
// 主要竞争对手样式
.competitorContent {
  .competitorUl {
    li {
       padding: 10px 18px;
       display: flex;
    }
    li:nth-of-type(odd) {
      background: #f9f9f9;
    } //奇数
    li:nth-of-type(even) {
      background: white;
    } //偶数
  }
}
// 中介机构
.InstitutionsDetail {
  ul {
    padding-left: 0;
    width: 100%;
    .InstitutionsDetailLi {
      padding: 20px;
      margin-left: 1%;
      width: 98%;
      height: 130px;
      background: #f0f0f0;
      .image {
        width: 10%;
      }
      .text {
        width: 80%;
        ul {
          width: 100%;
          margin-top: 10px;
          display: flex;
          flex-wrap: wrap;
          li {
            line-height: 20px;
            margin-right: 30px;
            width: 45%;
          }
        }
      }
    }
  }
}
.InstitutionsDetailLi:hover {
  cursor: pointer;
  box-shadow: 0 0px 28px -5px #ccc;
}
.majorBusinesses{
  color:#333333;
  margin-left: 9.4%;
  margin-top: -18.5px;
  line-height: 20px;
}
.industryCsrc{
    color: #333333;
    margin-left: 40.4%;
    margin-top: -7%;
    line-height: 20px;
}
.companyNature{
    color: #333333;
    margin-left: 27.4%;
    margin-top: -7%;
    line-height: 20px;
}
// 放大镜和放小镜样式
.zoom-in{
    cursor: -moz-zoom-in;
    cursor: -webkit-zoom-in;
    cursor: zoom-in;
}
.zoom-out{
    cursor: -moz-zoom-out;
    cursor: -webkit-zoom-out;
    cursor: zoom-out;
}
</style>
