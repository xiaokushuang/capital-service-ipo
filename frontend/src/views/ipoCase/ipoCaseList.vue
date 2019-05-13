<template>
  <div class="ipoCaseList">
    <el-row :gutter="24">
      <el-col class="chart" style="position:relative;width:285px;padding-left: 24px !important;
       padding-right: 0px !important; !important;background-color: #f7f7f7">
        <div class="innnerbox">
          <el-tree
            style="margin-top:24px;padding: 0 0 0 0"
            class="filter-tree"
            node-key="id"
            :data="plateTreeTag"
            :props="left_tree"
            :default-expand-all="true"
            @node-click="handleNodeClickForSearch"
            ref="plateTreeTagRef">
          </el-tree>
          <el-tree
            style="margin-top:24px;padding: 0 0 0 0"
            class="filter-tree"
            node-key="id"
            :data="marketTreeTag"
            :props="left_tree"
            :default-expand-all="true"
            @node-click="handleNodeClickForSearch"
            ref="marketTreeTagRef">
          </el-tree>
          <el-tree
            style="margin-top:24px;padding: 0 0 0 0"
            class="filter-tree"
            node-key="id"
            :data="greenTreeTag"
            :props="left_tree"
            :default-expand-all="true"
            @node-click="handleNodeClickForSearch"
            ref="greenTreeTagRef">
          </el-tree>
          <el-tree
            style="margin-top:24px;padding: 0 0 0 0"
            class="filter-tree"
            node-key="id"
            :data="specialArrangeTag"
            :props="left_tree"
            :default-expand-all="true"
            @node-click="handleNodeClickForSearch"
            ref="specialArrangeTagRef">
          </el-tree>
          <el-tree
            style="margin-top:24px;padding: 0 0 0 0"
            class="filter-tree"
            node-key="id"
            :data="sfcTreeTag"
            :props="left_tree"
            :default-expand-all="true"
            @node-click="handleNodeClickForSearch"
            ref="sfcTreeTagRef">
          </el-tree>
        </div>
      </el-col>
      <el-col style="padding:0 0 0 19px !important;width:calc(100% - 300px);">
        <div class="innnerbox1" style="padding-top: 24px">
          <el-row :gutter="24">
            <el-col :span='8'>
              <el-input size='small full' v-model="title" placeholder="标题关键字（包含全部以空格断开）"></el-input>
            </el-col>
             <el-col :span='8'>
              <el-input size='small full' v-model="codeOrName" placeholder="公司名称/代码"></el-input>
            </el-col>
             <el-col :span='8' class="repuramountlimitPan-class">
              <el-autocomplete
                class="inline-input"
                size='small full'
                v-model="intermediary"
                :fetch-suggestions="queryIntermediary"
                placeholder="中介机构"
                :trigger-on-focus="false"
                @select="handleSelect"
              ></el-autocomplete>
            </el-col>
          </el-row>

          <el-row :gutter="24">
            <el-col :span='8'>
              <el-select ref="selectCompanyNature" v-model="companyNature" title="企业性质" placeholder="企业性质"
                         size="small full" :tselect=true @visible-change="calls()"
                         @sure-click="sure('selectCompanyNature')"
                         @clear-click="clearLocal('treeCompanyNature')">
                <el-option :label="companyNature" :value="companyNatureValue">
                  <el-tree :data="companyNatureList" show-checkbox node-key="id" ref="treeCompanyNature" highlight-current
                           :props="default_tree" @check-change="selectHandleNodeClick('companyNature','treeCompanyNature')"></el-tree>
                </el-option>
              </el-select>
            </el-col>
            <el-col :span='4'>
              <el-select ref="selectIndustryCsrc" v-model="industryCsrc" title="发行人行业（证监会）" placeholder="发行人行业（证监会）"
                         size="small full" :tselect=true @visible-change="calls()"
                         @sure-click="sure('selectIndustryCsrc')"
                         @clear-click="clearLocal('treeIndustryCsrc')">
                <el-option :label="industryCsrc" :value="industryCsrcValue">
                  <el-tree :data="industryCrscList" show-checkbox node-key="id" ref="treeIndustryCsrc" highlight-current
                           :props="default_tree" @check-change="selectHandleNodeClick('industryCsrc','treeIndustryCsrc')"></el-tree>
                </el-option>
              </el-select>
            </el-col>
            <el-col :span='4'>
              <el-select ref="selectIndustryCsrc" v-model="industryCsrc" title="发行人行业（战略新兴）" placeholder="发行人行业（证监会）"
                         size="small full" :tselect=true @visible-change="calls()"
                         @sure-click="sure('selectIndustryCsrc')"
                         @clear-click="clearLocal('treeIndustryCsrc')">
                <el-option :label="industryCsrc" :value="industryCsrcValue">
                  <el-tree :data="industryCrscList" show-checkbox node-key="id" ref="treeIndustryCsrc" highlight-current
                           :props="default_tree" @check-change="selectHandleNodeClick('industryCsrc','treeIndustryCsrc')"></el-tree>
                </el-option>
              </el-select>
            </el-col>
            <el-col :span='4'>
              <el-select ref="selectIpoNum" v-model="ipoNum" title="申报次数" placeholder="申报次数"
                         size="small full" :tselect=true @visible-change="calls()"
                         @sure-click="sure('selectIpoNum')"
                         @clear-click="clearLocal('treeIpoNum')">
                <el-option :label="ipoNum" :value="ipoNumValue">
                  <el-tree :data="ipoNumList" show-checkbox node-key="id" ref="treeIpoNum" highlight-current
                           :props="default_tree" @check-change="selectHandleNodeClick('ipoNum','treeIpoNum')"></el-tree>
                </el-option>
              </el-select>
            </el-col>
             <el-col :span='4'>
              <el-select ref="selectIpoNum" v-model="ipoNum" title="配售机制" placeholder="配售机制"
                         size="small full" :tselect=true @visible-change="calls()"
                         @sure-click="sure('selectIpoNum')"
                         @clear-click="clearLocal('treeIpoNum')">
                <el-option :label="ipoNum" :value="ipoNumValue">
                  <el-tree :data="ipoNumList" show-checkbox node-key="id" ref="treeIpoNum" highlight-current
                           :props="default_tree" @check-change="selectHandleNodeClick('ipoNum','treeIpoNum')"></el-tree>
                </el-option>
              </el-select>
            </el-col>
          </el-row>

          <el-row :gutter="24">
             <el-col :span='8'>
              <el-select ref="selectProcess" v-model="caseStatus" title="IPO进程" placeholder="IPO进程"
                         size="small full" :tselect=true @visible-change="calls()"
                         @sure-click="sure('selectProcess')"
                         @clear-click="clearLocal('treeProcess')">
                <el-option :label="caseStatus" :value="caseStatusValue">
                  <el-tree :data="processList" show-checkbox node-key="id" ref="treeProcess" highlight-current
                           :props="default_tree" @check-change="selectHandleNodeClick('caseStatus','treeProcess')"></el-tree>
                </el-option>
              </el-select>
            </el-col>
            <el-col :span='8'>
              <el-select ref="selectVerifyResult" v-model="iecResult" title="审核结果" placeholder="审核结果"
                         size="small full" :tselect=true @visible-change="calls()"
                         @sure-click="sure('selectVerifyResult')"
                         @clear-click="clearLocal('treeVerifyResult')">
                <el-option :label="iecResult" :value="iecResultValue">
                  <el-tree :data="verifyResultList" show-checkbox node-key="id" ref="treeVerifyResult" highlight-current
                           :props="default_tree" @check-change="selectHandleNodeClick('iecResult','treeVerifyResult')"></el-tree>
                </el-option>
              </el-select>
            </el-col>
            <el-col :span='8'>
              <el-select ref="selectCompanyNature" v-model="companyNature" title="发行人选择的上市条件" placeholder="发行人选择的上市条件"
                         size="small full" :tselect=true @visible-change="calls()"
                         @sure-click="sure('selectCompanyNature')"
                         @clear-click="clearLocal('treeCompanyNature')">
                <el-option :label="companyNature" :value="companyNatureValue">
                  <el-tree :data="companyNatureList" show-checkbox node-key="id" ref="treeCompanyNature" highlight-current
                           :props="default_tree" @check-change="selectHandleNodeClick('companyNature','treeCompanyNature')"></el-tree>
                </el-option>
              </el-select>
            </el-col>
          </el-row>

          <el-row :gutter="24">
            <el-col :span='8' class="stockIncreasePan-class">
              <el-multiple-selection v-if="issueShow" :range="true" :tree-data="optionPeIssueA" placeholder="发行后市盈率" size="small full" :multiple="false"
                                     unit="%" :ran="optionDto" @sure-click="rangeCallPeIssueA">
              </el-multiple-selection>
            </el-col>
            <el-col :span='8' class="stockIncreasePan-class">
              <el-multiple-selection v-if="issueFeeShow" :range="true" :tree-data="optionIssueFee" placeholder="发行费用" size="small full" :multiple="false"
                                     unit="万元" :ran="optionDto" @sure-click="rangeCallIssueFee">
              </el-multiple-selection>
            </el-col>
            <el-col :span='8'>
              <el-select ref="selectCompanyNature" v-model="companyNature" title="招股书最近一次估值" placeholder="招股书最近一次估值"
                         size="small full" :tselect=true @visible-change="calls()"
                         @sure-click="sure('selectCompanyNature')"
                         @clear-click="clearLocal('treeCompanyNature')">
                <el-option :label="companyNature" :value="companyNatureValue">
                  <el-tree :data="companyNatureList" show-checkbox node-key="id" ref="treeCompanyNature" highlight-current
                           :props="default_tree" @check-change="selectHandleNodeClick('companyNature','treeCompanyNature')"></el-tree>
                </el-option>
              </el-select>
            </el-col>
             <!-- <el-col :span='8'>
              <el-date-picker size='small' v-model="ypProcessTime" type="daterange" value-format="yyyy-MM-dd" unlink-panels
                              start-placeholder="预先披露时间" end-placeholder="预先披露时间" align="center">
              </el-date-picker>
            </el-col> -->
            <!-- <el-col :span='8'>
              <el-date-picker size='small' v-model="fsProcessTime" type="daterange" value-format="yyyy-MM-dd" unlink-panels
                              start-placeholder="审核时间" end-placeholder="审核时间" align="center">
              </el-date-picker>
            </el-col>
            <el-col :span='8'>
              <el-multiple-selection v-if="durationShow" :range="true" :tree-data="optionAuditDuration" placeholder="审核历时" size="small full" :multiple="false"
                                     unit="天" :ran="optionDto" @sure-click="rangeAuditDuration">
              </el-multiple-selection>
            </el-col>
            <el-col :span='7'>
              <el-select ref="selectIssueCondition" v-model="issueCondition" title="注册制上市条件检索" placeholder="注册制上市条件检索"
                         size="small full" :tselect=true @visible-change="calls()"
                         @sure-click="sure('selectIssueCondition')"
                         @clear-click="clearLocal('treeIssueCondition')">
                <el-option :label="issueCondition" :value="issueConditionValue">
                  <el-tree :data="issueConditionList" show-checkbox node-key="id" ref="treeIssueCondition" highlight-current
                           :props="default_tree" @check-change="selectHandleNodeClick('issueCondition','treeIssueCondition')"></el-tree>
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="1">
              <el-popover
                placement="bottom"
                width="600"
                trigger="hover">
                <div style="font-size: 12px">
                  <div>
                    <a style="color: #1990FE;margin-left: -5px" @click="openNewRule()">《上海证券交易所科创板股票上市规则》：</a>
                  </div>
                  <div style="margin-top: 5px">2.1.2</div>
                  <div>发行人申请在本所科创板上市，市值及财务指标应当至少符合下列标准中的一项：</div>
                  <div>
                    （一）预计市值不低于人民币10亿元，最近两年净利润均为正且累计净利润不低于人民币5000万元，或者预计市值不低于人民币10亿元，最近一年净利润为正且营业收入不低于人民币1亿元；
                  </div>
                  <div>（二）预计市值不低于人民币15亿元，最近一年营业收入不低于人民币2亿元，且最近三年累计研发投入占最近三年累计营业收入的比例不低于15%；</div>
                  <div>（三）预计市值不低于人民币20亿元，最近一年营业收入不低于人民币3亿元，且最近三年经营活动产生的现金流量净额累计不低于人民币1亿元；</div>
                  <div>（四）预计市值不低于人民币30亿元，且最近一年营业收入不低于人民币3亿元；</div>
                  <div>
                    （五）预计市值不低于人民币40亿元，主要业务或产品需经国家有关部门批准，市场空间大，目前已取得阶段性成果。医药行业企业需至少有一项核心产品获准开展二期临床试验，其他符合科创板定位的企业需具备明显的技术优势并满足相应条件。
                  </div>
                  <div>
                    本条所称净利润以扣除非经常性损益前后的孰低者为准，所称净利润、营业收入、经营活动产生的现金流量净额均指经审计的数值。
                  </div>
                  <div style="margin-top: 5px">2.1.3</div>
                  <div>
                    符合《国务院办公厅转发证监会关于开展创新企业境内发行股票或存托凭证试点若干意见的通知》（国办发﹝2018﹞21号）相关规定的红筹企业，可以申请发行股票或存托凭证并在科创板上市。
                  </div>
                  <div>
                    营业收入快速增长，拥有自主研发、国际领先技术，同行业竞争中处于相对优势地位的尚未在境外上市红筹企业，申请在科创板上市的，市值及财务指标应当至少符合下列标准之一：
                  </div>
                  <div>（一）预计市值不低于人民币100亿元；</div>
                  <div>（二）预计市值不低于人民币50亿元，且最近一年营业收入不低于人民币5亿元。</div>
                  <div style="margin-top: 5px">2.1.4</div>
                  <div>
                    发行人具有表决权差异安排的，市值及财务指标应当至少符合下列标准中的一项：
                  </div>
                  <div>
                    （一）预计市值不低于人民币100亿元；
                  </div>
                  <div>
                    （二）预计市值不低于人民币50亿元，且最近一年营业收入不低于人民币5亿元。
                  </div>
                  <div>
                    发行人特别表决权股份的持有人资格、公司章程关于表决权差异安排的具体规定，应当符合本规则第四章第五节的规定。
                  </div>
                  <div>
                    本规则所称表决权差异安排，是指发行人依照《公司法》第一百三十一条的规定，在一般规定的普通股份之外，发行拥有特别表决权的股份（以下简称特别表决权股份）。每一特别表决权股份拥有的表决权数量大于每一普通股份拥有的表决权数量，其他股东权利与普通股份相同。
                  </div>
                </div>
                <span slot="reference" class="home_new" style="margin-top: 5px;background-position:-69px -20px;cursor: pointer"></span>
              </el-popover>
            </el-col> -->
          </el-row>
          <el-row :gutter="24">
            <el-col :span='8'>
              <el-date-picker size='small' v-model="fsProcessTime" type="daterange" value-format="yyyy-MM-dd" unlink-panels
                              start-placeholder="审核时间" end-placeholder="审核时间" align="center">
              </el-date-picker>
            </el-col>
          </el-row>
          <div v-show="searchFlag" style="display:flex">
            <div class="left" style="width:43%;margin-right:24px">
              <el-row :gutter="24" style="background:#f9f9f9;margin-bottom:0px;padding-top:10px">
                <el-radio-group v-model="yearRadio" style="width:100%">
                  <el-radio style="width:30%" :label="1">最近一个会计年度累计</el-radio>
                  <el-radio style="width:30%" :label="2">最近两个会计年度累计</el-radio>
                  <el-radio style="width:30%" :label="3">最近三个会计年度累计</el-radio>
                </el-radio-group>
              </el-row>
              <div style="background:#f9f9f9;margin-bottom:10px;padding:10px 5px 5px 5px">
                <el-row :gutter="24">
                  <el-col :span='12'>
                    <el-multiple-selection v-if="yearRadio===1&&profitOneShow" style="width: 205.5%" :range="true" :tree-data="optionNetProfitOne" placeholder="招股书最近一个会计年度净利润" size="small full" :multiple="false"
                                           unit="亿元" :ran="optionDto" @sure-click="rangeCallProfitOne">
                    </el-multiple-selection>
                    <el-multiple-selection v-if="yearRadio===2&&profitTwoShow" style="width: 205.5%" :range="true" :tree-data="optionNetProfitTwo" placeholder="招股书最近两个会计年度净利润" size="small full" :multiple="false"
                                           unit="亿元" :ran="optionDto" @sure-click="rangeCallProfitTwo">
                    </el-multiple-selection>
                    <el-multiple-selection v-if="yearRadio===3&&profitThreeShow" style="width: 205.5%" :range="true" :tree-data="optionNetProfitThree" placeholder="招股书最近三个会计年度净利润" size="small full" :multiple="false"
                                           unit="亿元" :ran="optionDto" @sure-click="rangeCallProfitThree">
                    </el-multiple-selection>
                  </el-col>
                </el-row>
                <el-row :gutter="24">
                  <el-col :span='12'>
                    <el-multiple-selection v-if="yearRadio===1&&reveOneShow" style="width: 205.5%" :range="true" :tree-data="optionOperateReveOne" placeholder="招股书最近一个会计年度营业收入" size="small full" :multiple="false"
                                           unit="亿元" :ran="optionDto" @sure-click="rangeCallReveOne">
                    </el-multiple-selection>
                    <el-multiple-selection v-if="yearRadio===2&&reveTwoShow" style="width: 205.5%" :range="true" :tree-data="optionOperateReveTwo" placeholder="招股书最近两个会计年度营业收入" size="small full" :multiple="false"
                                           unit="亿元" :ran="optionDto" @sure-click="rangeCallReveTwo">
                    </el-multiple-selection>
                    <el-multiple-selection v-if="yearRadio===3&&reveThreeShow" style="width: 205.5%" :range="true" :tree-data="optionOperateReveThree" placeholder="招股书最近三个会计年度营业收入" size="small full" :multiple="false"
                                           unit="亿元" :ran="optionDto" @sure-click="rangeCallReveThree">
                    </el-multiple-selection>
                  </el-col>
                </el-row>
                <el-row :gutter="24">
                  <el-col :span='12'>
                    <el-multiple-selection v-if="yearRadio===1&&cashOneShow" style="width: 205.5%" :range="true" :tree-data="optionOperateCashFlowOne" placeholder="招股书最近一个会计年度经营活动现金流量净额" size="small full" :multiple="false"
                                           unit="亿元" :ran="optionDto" @sure-click="rangeCallCashFlowOne">
                    </el-multiple-selection>
                    <el-multiple-selection v-if="yearRadio===2&&cashTwoShow" style="width: 205.5%" :range="true" :tree-data="optionOperateCashFlowTwo" placeholder="招股书最近两个会计年度经营活动现金流量净额" size="small full" :multiple="false"
                                           unit="亿元" :ran="optionDto" @sure-click="rangeCallCashFlowTwo">
                    </el-multiple-selection>
                    <el-multiple-selection v-if="yearRadio===3&&cashThreeShow" style="width: 205.5%" :range="true" :tree-data="optionOperateCashFlowThree" placeholder="招股书最近三个会计年度经营活动现金流量净额" size="small full" :multiple="false"
                                           unit="亿元" :ran="optionDto" @sure-click="rangeCallCashFlowThree">
                    </el-multiple-selection>
                  </el-col>
                </el-row>
              </div>
              <div style="background:#f9f9f9;margin-bottom:10px;padding:10px 5px 5px 5px">
                <el-row :gutter="24">
                  <el-col :span='12'>
                    <el-multiple-selection v-if="sunAssetShow" style="width: 205.5%" :range="true" :tree-data="optionSunAsset" placeholder="招股书最近一期末总资产" size="small full" :multiple="false"
                                           unit="万元" :ran="optionDto" @sure-click="rangeCallSunAsset">
                    </el-multiple-selection>
                  </el-col>
                </el-row>
                <el-row :gutter="24">
                  <el-col :span='12'>
                    <el-multiple-selection v-if="sunQuityShow" style="width: 205.5%" :range="true" :tree-data="optionSumShareQuity" placeholder="招股书最近一期末净资产" size="small full" :multiple="false"
                                           unit="万元" :ran="optionDto" @sure-click="rangeCallSumShareQuity">
                    </el-multiple-selection>
                  </el-col>
                </el-row>
                <el-row :gutter="24">
                  <el-col :span='12'>
                    <el-multiple-selection v-if="assetRatioShow" style="width: 205.5%" :range="true" :tree-data="optionAssetRatio" placeholder="招股书最近一期末无形资产占净资产的比例" size="small full" :multiple="false"
                                           unit="%" :ran="optionDto" @sure-click="rangeCallAssetRatio">
                    </el-multiple-selection>
                  </el-col>
                </el-row>
                <el-row :gutter="24">
                  <el-col :span='12'>
                    <el-multiple-selection v-if="shareIssueBShow" style="width: 205.5%" :range="true" :tree-data="optionShareIssueB" placeholder="发行前股本总额" size="small full" :multiple="false"
                                           unit="万股" :ran="optionDto" @sure-click="rangeCallShareIssueB">
                    </el-multiple-selection>
                  </el-col>
                </el-row>
                <el-row :gutter="24">
                  <el-col :span='12'>
                    <el-multiple-selection v-if="shareIssueAShow" style="width: 205.5%" :range="true" :tree-data="optionShareIssueA" placeholder="发行后股本总额" size="small full" :multiple="false"
                                           unit="万股" :ran="optionDto" @sure-click="rangeCallShareIssueA">
                    </el-multiple-selection>
                  </el-col>
                </el-row>
              </div>
            </div>
            <div class="right" style="flex:1;">
              <div style="background:#f9f9f9;padding:10px">
                <div>
                  <div class="clear" style="margin-bottom:5px">
                    <span class="l" style="color: #333333;font-size:14px;">主板上市条件</span>
                    <span class="r" style="color: #999999;font-size:12px">试点企业，可不适用第<span class="quan">1</span>项、第<span class="quan">5</span>项规定。</span>
                  </div>
                  <div>
                    <div style="color: #666666;font-size: 12px;margin-top:0px;margin-bottom:6px">
                      <span class="quan">1</span>
                      <div class="topOne">
                        最近<span style="color:#14BCF5">3</span>个会计年度净利润均为正数且<span style="color:#14BCF5">累计超过3000万元</span>（人民币），净利润以扣除非经常性损益前后较低者为计算依据；
                      </div>
                    </div>
                    <!-- <div style="color: #666666;font-size: 12px;margin-top:0px;margin-bottom:7px">
                      <span class="quan" style="position: relative; top: -17px;;">2</span>
                      <div style="display: inline-block;margin-left: 0.5%;">
                        最近<span style="color:#14BCF5">3</span>个会计年度营业收入<span style="color: #14BCF5">累计超过3亿元</span>(人民币)；<br>
                        <span><span style="color: #000000;">或</span>:最近<span style="color:#14BCF5">3</span>个会计年度经营活动产生的现金流量净额<span style="color:#14BCF5">累计超过5000万元</span>(人民币)；</span>
                      </div>
                    </div> -->
                     <div class="clear" style="color: #666666;font-size: 12px;margin-top:0px;margin-bottom:7px">
                      <span class="quan" style="float:left">2</span>
                      <div style="display: inline-block;margin-left: 0.5%;float:left">
                        最近<span style="color:#14BCF5">3</span>个会计年度营业收入<span style="color: #14BCF5">累计超过3亿元</span>(人民币)；<br>
                        <span><span style="color: #000000;">或</span>:最近<span style="color:#14BCF5">3</span>个会计年度经营活动产生的现金流量净额<span style="color:#14BCF5">累计超过5000万元</span>(人民币)；</span>
                      </div>
                    </div>
                    <p style="color: #666666;font-size: 12px;margin-top:0px;margin-bottom:6px">
                      <span class="quan">3</span>
                      发行前股本总额<span style="color:#14BCF5">不少于3000万元</span> (人民币)；
                    </p>
                    <p class="clear" style="color: #666666;font-size: 12px;margin-top:0px;margin-bottom:6px">
                      <span style="float:left" class="quan">4</span>
                      <span style="float:left;display:inline-block;width: 93%; margin-left: 4px;">最近一期末无形资产（扣除土地使用权、水面养殖权和采矿权等后）占净资产的比例<span style="color:#333333;">不高于20％；</span></span>
                    </p>
                    <p style="color: #666666;font-size: 12px;margin-top:0px;margin-bottom:6px">
                      <span class="quan">5</span>
                      最近一期末不存在未弥补亏损。
                    </p>
                  </div>
                </div>
                <div>
                  <div class="clear" style="margin-bottom:5px;margin-top: 10px;">
                    <span class="l" style="color: #333333;font-size:14px;">创业板上市条件</span>
                    <span class="r" style="color: #999999;font-size:12px">试点企业，可不适用第<span class="quan">2</span>项规定和第<span class="quan">3</span>项“不存在未弥补亏损”的规定。</span>
                  </div>
                  <div>
                    <div style="color: #666666;font-size: 12px;margin-top:0px;margin-bottom:6px">
                      <span class="quan">1</span>
                      <div class="bottomOne">
                        发行人是依法设立且持续经营三年以上的股份有限公司。有限责任公司按原账面净资产值折股整体变更为股份有限公司的，持续经营时间可以从有限责任公司成立之日起计算；
                      </div>
                    </div>
                    <!-- <div style="color: #666666;font-size: 12px;margin-top:0px;margin-bottom:6px">
                      <span class="quan bottomQuan">2</span>
                      <div class="bottomTwo">
                        最近<span style="color: #14BCF5">2</span>年连续盈利，最近两年净利润<span style="color: #14BCF5">累计不少于1000万元</span>;<br>
                        <span style="color: #000000;">或</span>:最近<span style="color:#14BCF5">1</span>年盈利，最近一年营业收入<span style="color:#14BCF5">不少于5000万元。</span>(
                        净利润以扣除非经常性损益前后孰低者为计算依据)
                      </div>
                    </div> -->
                     <div class="clear" style="color: #666666;font-size: 12px;margin-top:0px;margin-bottom:6px">
                      <span class="quan" style="float:left">2</span>
                      <div  style="display: inline-block;margin-left: 0.5%;float:left;width: 95%;">
                        最近<span style="color: #14BCF5">2</span>年连续盈利，最近两年净利润<span style="color: #14BCF5">累计不少于1000万元</span>;<br>
                        <span style="color: #000000;">或</span>:最近<span style="color:#14BCF5">1</span>年盈利，最近一年营业收入<span style="color:#14BCF5">不少于5000万元。</span>(
                        净利润以扣除非经常性损益前后孰低者为计算依据)
                      </div>
                    </div>
                    <p style="color: #666666;font-size: 12px;margin-top:0px;margin-bottom:6px">
                      <span class="quan">3</span>
                      最近一期末净资产<span style="color:#14BCF5">不少于2000万元</span>，且不存在未弥补亏损；
                    </p>
                    <p style="color: #666666;font-size: 12px;margin-top:0px;margin-bottom:6px">
                      <span class="quan">4</span>

                      发行后股本总额<span style="color:#14BCF5">不少于3000万元。</span>
                    </p>
                  </div>
                </div>
              </div>
              <div>
                <p style="color: #888888;font-size:12px;margin-top: 0px;">
                  中国证监会根据<a style="color: #1990FE;text-decoration:underline" @click="openNew()">《关于开展创新企业境内发行股票或存托凭证试点的若干意见》</a>等规定认定的试点企业可不适用部分上市条件中的规定。
                </p>
              </div>
            </div>
          </div>
          <el-row :gutter="24">
            <el-col :span='12'>
              <span style="font-size: 12px;color: #666">为您检索到相关结果{{totalCount}}条，默认以</span>
              <span style="font-size: 12px;color: #333">发审会审核时间↓</span>
              <span style="font-size: 12px;color: #666">排序。</span>
              <a style="color:#1990fe;font-size: 12px" @click="searchFlag == false ? searchFlag=true : searchFlag=false">
                <a v-if="searchFlag">收起核准制上市条件检索</a>
                <a v-else>展开核准制上市条件检索</a>
              </a>
            </el-col>
            <el-col :span="12" style="text-align: right">
              <div class="smallbutton">
                <el-button @click="conditionClear" style="position:absolute; right :78px;height: 30px;">
                  清空条件
                </el-button>
                <el-button type="primary" @click="querySearch" style="position:absolute; right :8px;height: 30px;">
                  查询
                </el-button>
              </div>
            </el-col>
          </el-row>
          <!-- table开始 -->
          <el-row :gutter="20">
            <el-col :span="24" style="padding-left: 8px; padding-right: 8px;">
              <div class="table">
                <el-table :cell-class-name="cellStyle" @sort-change="sortChange" :data="tableData" style="width: 100%" v-loading="tableLoading" ref="tables" @row-click="itemClickHandler" class="case" element-loading-text="给我一点时间">
                  <el-table-column align="left" width="100" label="公司">
                    <template slot-scope="scope">
                      {{scope.row.companyCode}}
                      <br/>
                      {{scope.row.companyName}}
                    </template>
                  </el-table-column>
                  <el-table-column align="left" label="案例标题" min-width="22%">
                    <template slot-scope="scope">
                      {{scope.row.titleStr}}
                    </template>
                  </el-table-column>
                  <el-table-column align="left" prop="ipo_process_t" label="进程" sortable="custom" min-width="10%">
                    <template slot-scope="scope">
                      {{scope.row.processLabel}}
                      <br/>
                      <span>
                        <span class="htgResult" v-if="scope.row.iecResult==='00'">获通过</span>
                        <span class="whtgResult" v-if="scope.row.iecResult==='01'">未获通过</span>
                        <span class="zhbjResult" v-if="scope.row.iecResult==='02'">暂缓表决</span>
                        <span class="qxshResult" v-if="scope.row.iecResult==='03'">取消审核</span>
                        <span class="dshResult" v-if="scope.row.iecResult==='04'">待审核</span>
                      <!-- <svg-icon v-if="scope.row.iecResult==='00'" icon-class="ipoPass" class="svg-style"></svg-icon>
                      <svg-icon v-if="scope.row.iecResult==='01'" icon-class="ipoNoPass" class="svg-style"></svg-icon>
                      <svg-icon v-if="scope.row.iecResult==='02'" icon-class="ipoSuspendVote" class="svg-style"></svg-icon>
                      <svg-icon v-if="scope.row.iecResult==='03'" icon-class="ipoCancelReview" class="svg-style"></svg-icon> -->
                        </span>
                    </template>
                  </el-table-column>
                  <el-table-column :label="yearLabel" header-align="center">
                    <el-table-column align="right" :prop="profit" label="净利润" sortable="custom" min-width="10%">
                      <template slot-scope="scope">
                        <span v-if="yearRadio===1">
                          <span v-if="scope.row.netProfitOne">{{scope.row.netProfitOne | dataInThRule}}亿元</span>
                          <span v-else>--</span>
                        </span>
                        <span v-if="yearRadio===2">
                          <span v-if="scope.row.netProfitTwo">{{scope.row.netProfitTwo | dataInThRule}}亿元</span>
                          <span v-else>--</span>
                        </span>
                        <span v-if="yearRadio===3">
                          <span v-if="scope.row.netProfitThree">{{scope.row.netProfitThree | dataInThRule}}亿元</span>
                          <span v-else>--</span>
                        </span>
                      </template>
                    </el-table-column>
                    <el-table-column align="right" :prop="reve" label="营业收入" sortable="custom" min-width="11%">
                      <template slot-scope="scope">
                        <span v-if="yearRadio===1">
                          <span v-if="scope.row.operateReveOne">{{scope.row.operateReveOne | dataInThRule}}亿元</span>
                          <span v-else>--</span>
                        </span>
                        <span v-if="yearRadio===2">
                          <span v-if="scope.row.operateReveTwo">{{scope.row.operateReveTwo | dataInThRule}}亿元</span>
                          <span v-else>--</span>
                        </span>
                        <span v-if="yearRadio===3">
                          <span v-if="scope.row.operateReveThree">{{scope.row.operateReveThree | dataInThRule}}亿元</span>
                          <span v-else>--</span>
                        </span>
                      </template>
                    </el-table-column>
                  </el-table-column>
                  <el-table-column align="right" prop="ipo_sum_asset_d" label="总资产" sortable="custom" min-width="10%">
                    <template slot-scope="scope">
                      <span v-if="scope.row.sunAsset">{{scope.row.sunAsset | dataInThRule}}亿元</span>
                      <span v-else>--</span>
                    </template>
                  </el-table-column>
                  <el-table-column align="left" label="拟上市板块" width="110">
                    <template slot-scope="scope">
                      {{scope.row.ipoPlateName}}
                    </template>
                  </el-table-column>
                  <el-table-column align="right" prop="ipo_review_meeting_time_dt" label="审核时间" sortable="custom" width="100">
                    <template slot-scope="scope">
                      <span v-if="scope.row.reMeetingTime">{{scope.row.reMeetingTime}}</span>
                      <span v-else>--</span>
                    </template>
                  </el-table-column>
                  <el-table-column align="right" prop="ipo_audit_duration_i" label="审核历时" sortable="custom" width="100">
                    <template slot-scope="scope">
                      <span v-if="scope.row.auditDuration">{{scope.row.auditDuration}}天</span>
                      <span v-else>--</span>
                    </template>
                  </el-table-column>
                </el-table>

                <papers style="margin-bottom: 2%" ref="paper" :length1="20" :total="totalCount" @searchTable="search"></papers>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>
  </div>

</template>

<script>
  import {_getIpoCaseList, _getSelectData, _queryIntermediary} from '@/api/ipoCase/ipoCaseListApi'
  import papers from '@/views/components-demo/papersNew'
  import {iframeDoMessage} from '@/utils/auth'

  export default {
    data() {
      return {
        tableData: [],
        tableLoading: false,
        searchFlag: false,
        yearRadio: 1,
        profit: 'ipo_profit_one_d',
        reve: 'ipo_operate_reve_one_d',
        yearLabel: '最近一个会计年度累计',
        totalCount: 0,
        orderByName: '',
        orderByOrder: '',
        plateTreeTag: [],
        plateTreeTagRef: '',
        marketTreeTag: [],
        marketTreeTagRef: '',
        greenTreeTag: [],
        greenTreeTagRef: '',
        specialArrangeTag: [],
        specialArrangeTagRef: '',
        sfcTreeTag: [],
        sfcTreeTagRef: '',
        default_tree: {
          children: "children",
          label: "labelName"
        },
        left_tree: {
          children: "children",
          label: "name"
        },
        title: '',
        industryCsrc: '',
        industryCsrcValue: '',
        issueCondition: '',
        issueConditionValue: '',
        companyNature: '',
        companyNatureValue: '',
        ipoNum: '',
        ipoNumValue: '',
        caseStatus: '',
        caseStatusValue: '',
        iecResult: '',
        iecResultValue: '',
        codeOrName: '',
        ypProcessTime: [],
        fsProcessTime: [],
        intermediary: '',
        intermediaryCode: '',
        industryCrscList: [],
        treeIndustryCsrc: '',
        issueConditionList: [],
        treeIssueCondition: '',
        companyNatureList: [],
        treeCompanyNature: '',
        ipoNumList: [],
        treeIpoNum: '',
        verifyResultList: [],
        treeVerifyResult: '',
        processList: [],
        treeProcess: '',
        profitOne: [],
        profitTwo: [],
        profitThree: [],
        reveOne: [],
        reveTwo: [],
        reveThree: [],
        cashFlowOne: [],
        cashFlowTwo: [],
        cashFlowThree: [],
        sunAsset: [],
        sumShareQuity: [],
        intangibleAssetRatio: [],
        totalShareIssueB: [],
        totalShareIssueA: [],
        peIssueA: [],
        issueFee: [],
        timeDiff: [],
        optionPeIssueA: [
          {id: 1, value: '0-10'},
          {id: 2, value: '10-20'},
          {id: 3, value: '20-30'},
          {id: 4, value: '30'}
        ],
        optionIssueFee: [
          {id: 1, value: '0-3000'},
          {id: 2, value: '3000-5000'},
          {id: 3, value: '5000-8000'},
          {id: 4, value: '10000'}
        ],
        optionAuditDuration: [
          {id: 1, value: '0-90'},
          {id: 2, value: '90-180'},
          {id: 3, value: '180-365'},
          {id: 4, value: '365-730'},
          {id: 5, value: '730'}
        ],
        optionNetProfitOne: [
          {id: 1, value: '0.1-0.5'},
          {id: 2, value: '0.5-1'},
          {id: 3, value: '1-5'},
          {id: 4, value: '5'}
        ],
        optionNetProfitTwo: [
          {id: 1, value: '0.1-0.5'},
          {id: 2, value: '0.5-1'},
          {id: 3, value: '1-5'},
          {id: 4, value: '5-10'},
          {id: 5, value: '10'}
        ],
        optionNetProfitThree: [
          {id: 1, value: '0.3-1'},
          {id: 2, value: '1-2'},
          {id: 3, value: '2-5'},
          {id: 4, value: '5-10'},
          {id: 5, value: '10'}
        ],
        optionOperateReveOne: [
          {id: 1, value: '0.5-1'},
          {id: 2, value: '1-3'},
          {id: 3, value: '3-5'},
          {id: 4, value: '5-10'},
          {id: 5, value: '10-20'},
          {id: 6, value: '20'}
        ],
        optionOperateReveTwo: [
          {id: 1, value: '1-3'},
          {id: 2, value: '3-5'},
          {id: 3, value: '5-10'},
          {id: 4, value: '10-20'},
          {id: 5, value: '20-50'},
          {id: 6, value: '50'}
        ],
        optionOperateReveThree: [
          {id: 1, value: '1-3'},
          {id: 2, value: '3-10'},
          {id: 3, value: '10-20'},
          {id: 4, value: '20-50'},
          {id: 5, value: '50-100'},
          {id: 6, value: '100'}
        ],
        optionOperateCashFlowOne: [
          {id: 1, value: '-0'},
          {id: 2, value: '0-0.5'},
          {id: 3, value: '0.5-1'},
          {id: 4, value: '1-2'},
          {id: 5, value: '2'}
        ],
        optionOperateCashFlowTwo: [
          {id: 1, value: '-0'},
          {id: 2, value: '0-0.5'},
          {id: 3, value: '0.5-1'},
          {id: 4, value: '1-2'},
          {id: 5, value: '2-5'},
          {id: 6, value: '5'}
        ],
        optionOperateCashFlowThree: [
          {id: 1, value: '-0'},
          {id: 2, value: '0-0.5'},
          {id: 3, value: '0.5-1'},
          {id: 4, value: '1-2'},
          {id: 5, value: '2-5'},
          {id: 6, value: '5'}
        ],
        optionSunAsset: [
          {id: 1, value: '0-3000'},
          {id: 2, value: '3000-5000'},
          {id: 3, value: '5000-8000'},
          {id: 4, value: '10000'}
        ],
        optionSumShareQuity: [
          {id: 1, value: '0-3000'},
          {id: 2, value: '3000-5000'},
          {id: 3, value: '5000-8000'},
          {id: 4, value: '10000'}
        ],
        optionAssetRatio: [
          {id: 1, value: '-0'},
          {id: 2, value: '0-2'},
          {id: 3, value: '2-5'},
          {id: 4, value: '5-10'},
          {id: 5, value: '10-20'},
          {id: 6, value: '20'}
        ],
        optionShareIssueB: [
          {id: 1, value: '0-3000'},
          {id: 2, value: '3000-5000'},
          {id: 3, value: '5000-8000'},
          {id: 4, value: '10000'}
        ],
        optionShareIssueA: [
          {id: 1, value: '0-3000'},
          {id: 2, value: '3000-5000'},
          {id: 3, value: '5000-8000'},
          {id: 4, value: '10000'}
        ],
        optionDto: {
          id: 'id',
          value: 'value'
        },
        ipoPlate: '',
        marketType: '',
        greenPassage: '',
        specialArrange: '',
        belongsBureau: '',
        issueShow: true,
        issueFeeShow: true,
        durationShow: true,
        profitOneShow: true,
        profitTwoShow: true,
        profitThreeShow: true,
        reveOneShow: true,
        reveTwoShow: true,
        reveThreeShow: true,
        cashOneShow: true,
        cashTwoShow: true,
        cashThreeShow: true,
        sunAssetShow: true,
        sunQuityShow: true,
        assetRatioShow: true,
        shareIssueBShow: true,
        shareIssueAShow: true
      }
    },
    mounted() {
      this.tableLoading = true;
      const _data = {
        startRow: 0,
        pageSize: 20
      };
      this.search(_data);
      this.getSelectData();
    },
    watch: {
      'yearRadio'() {
        const _self = this;
        if (_self.yearRadio === 1) {
          _self.profit = 'ipo_profit_one_d';
          _self.reve = 'ipo_operate_reve_one_d';
          _self.yearLabel = '最近一个会计年度累计';
        } else if (_self.yearRadio === 2) {
          _self.profit = 'ipo_profit_two_d';
          _self.reve = 'ipo_operate_reve_two_d';
          _self.yearLabel = '最近二个会计年度累计';
        } else {
          _self.profit = 'ipo_profit_three_d';
          _self.reve = 'ipo_operate_reve_three_d';
          _self.yearLabel = '最近三个会计年度累计';
        }
      }
    },
    methods: {
      pageSearch() {
        this.$refs.paper.search()
      },
      search(data) {
        const _self = this;
        _self.tableLoading = true;
        _self.tableData = [];
        if (_self.yearRadio === 1) {
          _self.profitTwo = [];
          _self.profitThree = [];
          _self.reveTwo = [];
          _self.reveThree = [];
          _self.cashFlowTwo = [];
          _self.cashFlowThree = [];
        } else if (_self.yearRadio === 2) {
          _self.profitOne = [];
          _self.profitThree = [];
          _self.reveOne = [];
          _self.reveThree = [];
          _self.cashFlowOne = [];
          _self.cashFlowThree = [];
        } else {
          _self.profitTwo = [];
          _self.profitOne = [];
          _self.reveTwo = [];
          _self.reveOne = [];
          _self.cashFlowTwo = [];
          _self.cashFlowOne = [];
        }
        if (_self.intermediary === '') {
          _self.intermediaryCode = '';
        }
        if (_self.intermediaryCode === '') {
          _self.intermediary = '';
        }
        const _data = {
          startRow: data.startRow,
          pageSize: data.pageSize,
          condition: {
            companyId: _self.$store.state.app.companyId,
            title: _self.title,//标题关键字（包含全部以空格断开）
            industryCsrc: _self.industryCsrcValue,//发行人行业（证监会）
            issueCondition: _self.issueConditionValue,//发行人选择的上市条件
            companyNature: _self.companyNatureValue,//企业性质
            ipoNum: _self.ipoNumValue,//申报次数
            caseStatus: _self.caseStatusValue,//IPO进程
            iecResult: _self.iecResultValue,//审核结果
            codeOrName: _self.codeOrName,//公司名称/代码
            fsProcessTime: _self.fsProcessTime,//发审会审核时间范围
            ypProcessTime: _self.ypProcessTime,//预先披露时间范围
            intermediaryCode: _self.intermediaryCode,//中介机构
            profitOne: _self.profitOne,//最近一个会计年度净利润
            profitTwo: _self.profitTwo,//最近两个会计年度净利润
            profitThree: _self.profitThree,//最近三个会计年度净利润
            reveOne: _self.reveOne,//最近一个会计年度营业收入
            reveTwo: _self.reveTwo,//最近两个会计年度营业收入
            reveThree: _self.reveThree,//最近三个会计年度营业收入
            cashFlowOne: _self.cashFlowOne,//最近一个会计年度经营活动现金流量净额
            cashFlowTwo: _self.cashFlowTwo,//最近两个会计年度经营活动现金流量净额
            cashFlowThree: _self.cashFlowThree,//最近三个会计年度经营活动现金流量净额
            sunAsset: _self.sunAsset,//最近一期末总资产
            sumShareQuity: _self.sumShareQuity,//最近一期末净资产
            intangibleAssetRatio: _self.intangibleAssetRatio,//最近一期末无形资产占净资产的比例
            totalShareIssueB: _self.totalShareIssueB,//发行前股本总额
            totalShareIssueA: _self.totalShareIssueA,//发行后股本总额
            peIssueA: _self.peIssueA,//发行后市盈率
            issueFee: _self.issueFee,//发行费用
            timeDiff: _self.timeDiff,//申报审核历时（天）
            ipoPlate: _self.$refs.plateTreeTagRef.getCheckedNodes().map((item) => {
              return item.labelValue
            }).join(','),//拟上市板块
            marketType: _self.$refs.marketTreeTagRef.getCheckedNodes().map((item) => {
              return item.labelValue
            }).join(','),//登录其他资本市场
            greenPassage: _self.$refs.greenTreeTagRef.getCheckedNodes().map((item) => {
              return item.labelValue
            }).join(','),//绿色通道
            specialArrange: _self.$refs.specialArrangeTagRef.getCheckedNodes().map((item) => {
              return item.labelValue
            }).join(','),//公司治理特殊安排
            belongsBureau: _self.$refs.sfcTreeTagRef.getCheckedNodes().map((item) => {
              return item.labelValue
            }).join(','),//所属证监局
          },
          orderByName: _self.orderByName,
          orderByOrder: _self.orderByOrder
        };
        _getIpoCaseList(_data).then(response => {
          _self.tableLoading = false;
          if (response.data && response.data.success && response.data.result) {
            _self.totalCount = response.data.result.total;
            _self.tableData = response.data.result.data;
            _self.plateTreeTag = [{
              label_sort: 1,
              name: "拟上市板块(" + response.data.result.plateTreeNum + ')',
              id: "0",
              label_name: "拟上市板块",
              label_level: 0,
              label_code: "IPO_PLATE",
              children: response.data.result.plateTreeTag
            }];
            _self.marketTreeTag = [{
              label_sort: 1,
              name: "登陆其他资本市场(" + response.data.result.marketTreeNum + ')',
              id: "0",
              label_name: "登陆其他资本市场",
              label_level: 0,
              label_code: "IPO_CAPITAL_MARKET",
              children: response.data.result.marketTreeTag
            }];
            _self.greenTreeTag = [{
              label_sort: 1,
              name: "绿色通道(" + response.data.result.greenTreeNum + ')',
              id: "0",
              label_name: "绿色通道",
              label_level: 0,
              label_code: "IPO_GREEN_PASSAGE",
              children: response.data.result.greenTreeTag
            }];
            _self.specialArrangeTag = [{
              label_sort: 1,
              name: "公司治理特殊安排(" + response.data.result.arrangeTreeNum + ')',
              id: "0",
              label_name: "公司治理特殊安排",
              label_level: 0,
              label_code: "IPO_SPECIAL_ARRANGE",
              children: response.data.result.specialArrangeTag
            }];
            _self.sfcTreeTag = [{
              label_sort: 1,
              name: "所属证监局(" + response.data.result.sfcTreeNum + ')',
              id: "0",
              label_name: "所属证监局",
              label_level: 0,
              label_code: "SFC",
              children: response.data.result.sfcTreeTag
            }];
          }
        })
      },
      //排序方法
      sortChange(column) {
        const _self = this;
        if (column.order != null && column.prop != null) {
          _self.orderByName = column.prop;
          _self.orderByOrder = column.order;
        } else {
          _self.orderByName = '';
          _self.orderByOrder = '';
        }
        _self.pageSearch();
      },
      handleNodeClickForSearch(data, node, index) {
        if (node.isLeaf || node.level == 1) {
          for (var obj of index.tree.$el.querySelectorAll('.el-tree-node__label')) {
            obj.className = 'el-tree-node__label'
          }
          index.$el.querySelector('.el-tree-node__label').className += ' blue-text'
          if (node.level == 1) {
            this.treeSearch(node.data.label_code, node);
          } else {
            this.treeSearch(node.parent.data.label_code, node);
          }
        }
      },
      treeSearch(code, node) {
        switch (code) {
          case 'IPO_PLATE':
            this.$refs.plateTreeTagRef.setCheckedKeys([node.data.id]);
            break;
          case 'IPO_CAPITAL_MARKET':
            this.$refs.marketTreeTagRef.setCheckedKeys([node.data.id]);
            break;
          case 'IPO_GREEN_PASSAGE':
            this.$refs.greenTreeTagRef.setCheckedKeys([node.data.id]);
            break;
          case 'IPO_SPECIAL_ARRANGE':
            this.$refs.specialArrangeTagRef.setCheckedKeys([node.data.id]);
            break;
          case 'SFC':
            this.$refs.sfcTreeTagRef.setCheckedKeys([node.data.id]);
            break;
        }
        this.querySearch();
      },
      selectHandleNodeClick(val, treeVal) {
        const nodeCheck = this.$refs[treeVal].getCheckedNodes(true); //通过 node 获取(光子节点)
        let middle = "";
        let middleValue = "";
        nodeCheck.map((obj, idx) => {
          middle += `,${obj.labelName}`;
          middleValue += `,${obj.labelValue}`;
        });
        this[val] = middle.substr(1);
        this[val + 'Value'] = middleValue.substr(1);
      },
      //清空
      conditionClear() {
        const _self = this;
        _self.title = '';//标题
        _self.industryCsrcValue = '';//行业
        _self.industryCsrc = '';
        _self.issueConditionValue = '';//发行人选择的上市条件
        _self.issueCondition = '';
        _self.companyNatureValue = '';//企业性质
        _self.companyNature = '';
        _self.ipoNum = '';//ipo次数
        _self.ipoNumValue = '';
        _self.caseStatus = '';//IPO进程
        _self.caseStatusValue = '';
        _self.iecResult = '';//审核结果
        _self.iecResultValue = '';
        _self.codeOrName = '';//公司简称/代码
        _self.intermediary = '';//中介机构
        _self.intermediaryCode = '';
        _self.ypProcessTime = [];//预先披露时间
        _self.fsProcessTime = [];//发审会审核时间
        _self.orderByName = '';//排序
        _self.orderByOrder = '';
        _self.$refs.plateTreeTagRef.setCheckedKeys([]);
        _self.$refs.marketTreeTagRef.setCheckedKeys([]);
        _self.$refs.greenTreeTagRef.setCheckedKeys([]);
        _self.$refs.specialArrangeTagRef.setCheckedKeys([]);
        _self.$refs.sfcTreeTagRef.setCheckedKeys([]);
        _self.$refs.treeIndustryCsrc.setCheckedKeys([]);
        _self.$refs.treeIssueCondition.setCheckedKeys([]);
        _self.$refs.treeCompanyNature.setCheckedKeys([]);
        _self.$refs.treeIpoNum.setCheckedKeys([]);
        _self.$refs.treeVerifyResult.setCheckedKeys([]);
        _self.$refs.treeProcess.setCheckedKeys([]);
        _self.profitOne = [];
        _self.profitTwo = [];
        _self.profitThree = [];
        _self.reveOne = [];
        _self.reveTwo = [];
        _self.reveThree = [];
        _self.cashFlowOne = [];
        _self.cashFlowTwo = [];
        _self.cashFlowThree = [];
        _self.sunAsset = [];
        _self.sumShareQuity = [];
        _self.intangibleAssetRatio = [];
        _self.totalShareIssueB = [];
        _self.totalShareIssueA = [];
        _self.peIssueA = [];
        _self.issueFee = [];
        _self.timeDiff = [];
        _self.searchFlag = false;
        _self.yearRadio = 1;
        for (var tree of document.getElementsByClassName('filter-tree')) {
          for (var obj of tree.querySelectorAll('.el-tree-node__label')) {
            obj.className = 'el-tree-node__label'
          }
        }
        _self.issueShow = false;
        _self.issueFeeShow = false;
        _self.durationShow = false;
        _self.profitOneShow = false;
        _self.profitTwoShow = false;
        _self.profitThreeShow = false;
        _self.reveOneShow = false;
        _self.reveTwoShow = false;
        _self.reveThreeShow = false;
        _self.cashOneShow = false;
        _self.cashTwoShow = false;
        _self.cashThreeShow = false;
        _self.sunAssetShow = false;
        _self.sunQuityShow = false;
        _self.assetRatioShow = false;
        _self.shareIssueBShow = false;
        _self.shareIssueAShow = false;
        _self.$nextTick(() => {
          _self.issueShow = true;
          _self.issueFeeShow = true;
          _self.durationShow = true;
          _self.profitOneShow = true;
          _self.profitTwoShow = true;
          _self.profitThreeShow = true;
          _self.reveOneShow = true;
          _self.reveTwoShow = true;
          _self.reveThreeShow = true;
          _self.cashOneShow = true;
          _self.cashTwoShow = true;
          _self.cashThreeShow = true;
          _self.sunAssetShow = true;
          _self.sunQuityShow = true;
          _self.assetRatioShow = true;
          _self.shareIssueBShow = true;
          _self.shareIssueAShow = true;
        });
        _self.$refs.tables.clearSort();
        _self.querySearch();
      },
      //查询
      querySearch() {
        this.$refs.paper.search();
      },
      //下拉框数据
      getSelectData() {
        const _self = this;
        _getSelectData().then(response => {
          if (response.data.result) {
            if (response.data.result.industryCrscList && response.data.result.industryCrscList.length > 0) {
              _self.industryCrscList = response.data.result.industryCrscList;
            }
            if (response.data.result.issueConditionList && response.data.result.issueConditionList.length > 0) {
              _self.issueConditionList = response.data.result.issueConditionList;
            }
            if (response.data.result.companyNatureList && response.data.result.companyNatureList.length > 0) {
              _self.companyNatureList = response.data.result.companyNatureList;
            }
            if (response.data.result.ipoNumList && response.data.result.ipoNumList.length > 0) {
              _self.ipoNumList = response.data.result.ipoNumList;
            }
            if (response.data.result.verifyResultList && response.data.result.verifyResultList.length > 0) {
              _self.verifyResultList = response.data.result.verifyResultList;
            }
            if (response.data.result.processList && response.data.result.processList.length > 0) {
              _self.processList = response.data.result.processList;
            }
          }
        })
      },
      sure(event) {
        this.$refs[event].handleClose();
      },
      //局部清空
      clearLocal(name) {
        this.$refs[name].setCheckedKeys([]);
      },
      calls() {
        var selectObjArr = document.getElementsByClassName('el-select-dropdown__wrap el-scrollbar__wrap');
        setTimeout(() => {
          for (var i = 0; i < selectObjArr.length; i++) {
            selectObjArr[i].scrollTop = 0;
          }
        }, 200)
      },
      //数据控件选择方法
      rangeCallPeIssueA(data) {
        this.peIssueA = data;
      },
      rangeCallIssueFee(data) {
        this.issueFee = data;
      },
      rangeCallProfitOne(data) {
        this.profitOne = data;
      },
      rangeCallProfitTwo(data) {
        this.profitTwo = data;
      },
      rangeCallProfitThree(data) {
        this.profitThree = data;
      },
      rangeCallReveOne(data) {
        this.reveOne = data;
      },
      rangeCallReveTwo(data) {
        this.reveTwo = data;
      },
      rangeCallReveThree(data) {
        this.reveThree = data;
      },
      rangeCallCashFlowOne(data) {
        this.cashFlowOne = data;
      },
      rangeCallCashFlowTwo(data) {
        this.cashFlowTwo = data;
      },
      rangeCallCashFlowThree(data) {
        this.cashFlowThree = data;
      },
      rangeAuditDuration(data) {
        this.timeDiff = data;
      },
      rangeCallSunAsset(data) {
        this.sunAsset = data;
      },
      rangeCallSumShareQuity(data) {
        this.sumShareQuity = data;
      },
      rangeCallAssetRatio(data) {
        this.intangibleAssetRatio = data;
      },
      rangeCallShareIssueB(data) {
        this.totalShareIssueB = data;
      },
      rangeCallShareIssueA(data) {
        this.totalShareIssueA = data;
      },
      //中介机构根据名称或者别名进行联想查询
      queryIntermediary(queryString, cb) {
        const param = {
          intermediaryName: queryString
        };
        _queryIntermediary(param).then(response => {
          const results = response.data.result;
          // 调用 callback 返回建议列表的数据
          cb(results);
        })
      },
      //中介机构联想下拉框选择方法
      handleSelect(item) {
        this.intermediaryCode = item.labelValue;
      },
      itemClickHandler(row) {
        let id = row.id;
        if (id) {
          var caseId = id.substring(3, id.length);
          const _self = this;
          const {href} = _self.$router.resolve({
            name: 'caseDetail',
            query: {caseId: caseId, access_token: _self.$store.state.app.token}
          });
          window.open(href, '_blank');
        } else {
          let url = window.location.href;
          url = url.replace(this.$route.path, '/ipoPopWin');
          iframeDoMessage(window.parent, 'popWinOut', ['提示', url, '427', '217']);
        }
      },
      openNew() {
        const _self = this;
        const href = window.location.origin + '/ui/laws/laws/lawsDetail?lawId=745777672757626842&access_token=' + _self.$store.state.app.token + '&tenant_info=' + _self.$store.state.app.info;
        window.open(href, '_blank');
      },
      openNewRule() {
        const _self = this;
        const href = window.location.origin + '/ui/laws/laws/lawsDetail?lawId=746412002825257522&access_token=' + _self.$store.state.app.token + '&tenant_info=' + _self.$store.state.app.info;
        window.open(href, '_blank');
      },
      //没有权限数据背景色
      cellStyle(row,column,rowIndex,columnIndex){
        if(!row.row.id){
          return 'no_authority'
        }
      }
    },
    components: {
      papers
    }
  }
</script>

<style>
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

  .ipoCaseList {
    font-family: Microsoft YaHei, "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-size: 14px;
    line-height: 1.42857143;
    color: #333;
    background-color: #fff;
    padding: 0 0;
  }

  .ipoCaseList .innnerbox {
    margin-left: 0px;
    /*border: #e4e4e4 solid 1px*/
  }

  .ipoCaseList .innnerbox1 {
    margin: 0 10 0 0;
    padding: 0;
  }

  .ipoCaseList .bluetext {
    text-decoration: none !important;
    color: inherit !important
  }

  .ipoCaseList a.bluetext:hover {
    text-decoration: underline !important;
  }

  .ipoCaseList .filter-tree {
    white-space: normal;
  }

  .ipoCaseList .el-tree-node {
    outline: 0;
    white-space: normal;
    /* height:100%; */
  }

  .ipoCaseList .el-tree-node__label {
    font-size: 12px !important;
    color: #333;
    display: inline-block;
    max-width: 100%;
    cursor: pointer;
    padding: 0 3px;
  }
.ipoCaseList .has-gutter .cell {
    font-size: 14px !important;
    color: #333333 !important;
    font-weight: 400 !important;
}
.ipoCaseList .has-gutter th {
    background-color: #f2f2f2 !important;
}
  .ipoCaseList .el-tree-node__content {
    height: 23px;
  }

  .ipoCaseList .el-tree-node__label:visited {
    color: #0000FF
  }

  .el-tree-node__content {
    display: flex;
    align-items: center;
    cursor: pointer;
    height: 26px;
  }

  .ipoCaseList .el-dialog__header {
    padding: 0;
  }

  .ipoCaseList .el-dialog__body {
    PADDING: 10PX;
    padding-left: 0;
  }

  .ipoCaseList .table .el-table .cell {
    padding: 10px;
  }

  .ipoCaseList .table .el-table th .cell {
    padding: 10px;
    margin: 0;
    border-color: #A0ADB5 !important;
    color: #fff;
    font-size: 14px;
    font-weight: 600;
    font-family: 'Microsoft YaHei';
  }

  .ipoCaseList .table .el-table__header thead tr > th {
    padding: 0
  }

  .ipoCaseList .table .el-table .cell p {
    margin: 0;
  }

  .ipoCaseList .table .el-table .cell {
    color: #333;
  }

  .ipoCaseList .table .el-table thead tr > th .cell {
    padding: 10px;
  }

  .ipoCaseList .el-tree > .el-tree-node > .el-tree-node__content span {
    font-family: Microsoft YaHei, "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-size: 14px !important;
    line-height: 1.42857143;
    display: inline-block;
    max-width: 100%;
    cursor: pointer;
    padding: 0 3px;
    font-weight: 300;
    color: #333;
  }

  .ipoCaseList .el-input--small .el-input__inner {
    height: 30px !important;
  }

  .ipoCaseList .el-row {
    margin-bottom: 10px;
    margin-left: 0 !important;
    margin-right: 0 !important;
  }

  .ipoCaseList .el-range-editor--small.el-input__inner {
    height: 30px;
  }

  .ipoCaseList .smallbutton .el-button {
    line-height: 0;
  }

  .ipoCaseList .el-col {
    padding-left: 5px !important;
    padding-right: 5px !important;
  }

  .ipoCaseList .el-row {
    height: 30px;
  }

  .ipoCaseList .el-table tr:hover {
    background-color: #f5f5f5 !important;
  }

  .ipoCaseList .el-table th, .el-table tr {
    background-color: #fff;
    height: 67px;
  }

  .dialogtitle {
    font-family: 'MicrosoftYaHei', 'Microsoft YaHei';
    font-weight: 400;
    font-style: normal;
    font-size: 20px;
    width: 75%;
    color: #333;
    margin-left: 10px;
    padding-right: 12px;
  }

  #moreNoticeDailog .el-dialog__header {
    background-color: #A0ADB5;
    padding: 10px;
    padding-left: 20px;

  }

  #moreNoticeDailog .el-dialog__title {
    color: #fff;
    font-size: 16px;
    font-weight: bold;
  }

  #moreNoticeDailog i {
    color: #fff;
  }

  #moreNoticeDailog .el-dialog__headerbtn {
    top: 12.5px;
    background-color: #bfc6cc;
    padding: 1px;
  }

  .dialogtitle > .spanLable {
    border: 1px #FF4040 solid !important;
    color: #FF4040 !important;
    background: #FFF3F2 !important;
    border-radius: 2px !important;
    font-size: 14px !important;
    padding: 0px 4px;
  }

  .ipoCaseList .blue-text {
    color: #0099cc !important;
  }

  .el-button {
    height: 30px;
    line-height: 0;
  }

  .inpsel {
    background-color: #ffffff;
    border: 1px solid #e1e2e2;
    margin-top: 12px;
    position: absolute;
    z-index: 9;
    width: 32.4%;
  }

  .hoverClass {
    font-size: 12px;
    color: #333;
  }

  .hoverClass:hover {
    color: #14bcf5;
    cursor: pointer;
  }

  .inline-input {
    width: 100%;
  }

  .innnerbox .el-tree {
    background-color: #f7f7f7;
  }

  .has-gutter th {
    background-color: #f2f2f2 !important;
  }

  .has-gutter .cell {
    font-size: 14px !important;
    color: #333333 !important;
    font-weight: 400 !important;
  }

  .svg-style {
    width: 4em !important;
    font-size: 18px;
    vertical-align: middle;
  }

  .el-radio__input.is-checked + .el-radio__label {
    color: #1990fe;
  }

  .el-radio__input.is-checked .el-radio__inner {
    border-color: #1990fe;
    background: #1990fe;
  }

  .el-radio + .el-radio {
    margin-left: 7px;
  }

  .el-radio__label {
    font-size: 12px;
    padding-left: 5px;
  }

  .quan {
    display: inline-block;
    background: url("../../assets/images/quan.png") no-repeat;
    background-size: cover;
    width: 15px;
    height: 15px;
    text-align: center;
    line-height: 15px;
    font-size: 12px;
    color: #FFFFFF
  }

  .topOne {
    display: inline-block;
    margin-top: -4%;
    margin-left: 4%;
  }
    @media screen and (max-width: 1680px) and (min-width: 1359px) {
    .topOne {
      display: inline-block;
      margin-top: -4%;
      margin-left: 3.1%;
    }
  }
    @media screen and (max-width: 1920px) and (min-width: 1681px) {
    .topOne {
      display: inline-block;
      margin-top: -4%;
      margin-left: 0.1%;
    }
  }
    @media screen and (max-width: 3840px) and (min-width: 1919px) {
    .topOne {
      display: inline-block;
      margin-top: -4%;
      margin-left: 0.2%;
    }
  }

  .bottomOne {
    display: inline-block;
    margin-top: -4%;
    margin-left: 4%;
  }

  @media screen and (max-width: 1920px) and (min-width: 1400px) {
    .bottomOne {
      display: inline-block;
      margin-top: -4%;
      margin-left: 2.5%;
    }
  }
  @media screen and (max-width: 3840px) and (min-width: 1921px) {
    .bottomOne {
      display: inline-block;
      margin-top: -4%;
      margin-left: 0.2%;
    }
  }
  .bottomTwo {
    display: inline-block;
    margin-top: -4%;
    margin-left: 4%;
  }

  @media screen and (max-width: 1920px) and (min-width: 1400px) {
    .bottomTwo {
      display: inline-block;
      margin-top: 0%;
      margin-left: 0.5%;
    }
  }

  .bottomQuan {
    position: relative;
    top: -5px;
  }

  @media screen and (max-width: 1920px) and (min-width: 1400px) {
    .bottomQuan {
      position: relative;
      top: -17px;
    }
  }

  .ipoCaseList .case .el-table__body tr:hover {
    cursor: pointer;
  }

  .ipoCaseList .case .el-table__row td {
    border-right: none;
  }

  .ipoCaseList .case .el-table__header th {
    /* border-right: none; */
  }

  .ipoCaseList .case .el-table__header th:nth-child(4) {
    border-bottom-color: #fff;
  }

  .ipoCaseList .el-table th, .el-table tr {
    height: 40px !important;
  }

  .home_new {
    display: inline-block;
    width: 20px;
    height: 20px;
    background-image: url(../../assets/images/home_new.png);
  }
  .ipoCaseList .el-table th, .el-table tr {
    height: 40px !important;
  }

  .no_authority{
    background: #F9FAFE;
  }
  .no_authority .cell{
    color: #999999 !important;
  }
  /* 是否通过 */
  .zhbjResult{
     font-size: 12px;
     color: #FF9900;
     padding:5px;
     padding-left: 8px;
     line-height:10px;
     display:inline-block;
      background-size:cover;
     background:url('../../assets/images/zhbj.png') no-repeat;
}
.whtgResult{
     font-size: 12px;
     color: #FE5461;
     padding:5px;
     padding-left: 8px;
     line-height:10px;
     display:inline-block;
     background-size:cover;
     background:url('../../assets/images/whtg.png') no-repeat;
}
.htgResult{
     font-size: 12px;
     color: #14BCF5;
     padding:5px;
     padding-left: 8px;
     line-height:10px;
     display:inline-block;
      background-size:cover;
     background:url('../../assets/images/htg.png') no-repeat;
}
.qxshResult{
     font-size: 12px;
     color: #94A3B4;
     padding:5px;
     padding-left: 8px;
     line-height:10px;
     display:inline-block;
      background-size:cover;
     background:url('../../assets/images/qxsh.png') no-repeat;
}
.dshResult{
     font-size: 12px;
     color: #ffd800;
     padding:5px;
     padding-left: 8px;
     padding-right: 6px;
     line-height:10px;
     display:inline-block;
     background-size:cover;
     background:url('../../assets/images/dsh.png') no-repeat;
}
 .ipoCaseTable .el-table--border td, .el-table--border th {
  border-right: none !important;
}

</style>

