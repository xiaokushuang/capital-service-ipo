<!--
 * @Description: In User Settings Edit
 * @Author: your name
 * @Date: 2019-08-08 11:39:50
 * @LastEditTime: 2019-08-09 14:50:46
 * @LastEditors: Please set LastEditors
 -->
<template>
  <div class="ipoCaseList">
    <el-row :gutter="24">
      <el-col style="padding:0 0 0 19px !important;width:100%;">
        <div class="innnerbox1" style="padding-top: 24px">
          <el-row :gutter="24">
            <el-col :span='8'>
              <el-input size='small full' v-model="title" @keyup.enter.native="querySearch" placeholder="标题关键字（包含全部以空格断开）"></el-input>
            </el-col>
            <el-col :span='8'>
              <el-input size='small full' v-model="codeOrName" @keyup.enter.native="querySearch" placeholder="公司名称/代码"></el-input>
            </el-col>
            <el-col :span='8' class="repuramountlimitPan-class">
              <el-autocomplete
                class="inline-input"
                size='small full'
                v-model="intermediary"
                @keyup.enter.native="querySearch"
                :fetch-suggestions="queryIntermediary"
                placeholder="中介机构"
                :trigger-on-focus="false"
                @select="handleSelect"
              ></el-autocomplete>
            </el-col>
          </el-row>
          <el-row :gutter="24">
            <el-col :span='8'>
              <el-select ref="selectProcess" v-model="caseStatus" title="IPO进程" placeholder="IPO进程"
                         size="small full" :tselect=true @visible-change="calls()"
                         @sure-click="sure('selectProcess')"
                         @keydown.enter.native="querySearch"
                         @clear-click="clearLocal('treeProcess')">
                <el-option :label="caseStatus" :value="caseStatusValue">
                  <el-tree :data="processList" class="filter-tree" show-checkbox node-key="id" ref="treeProcess" highlight-current
                           :props="default_tree" check-on-click-node="true" @check-change="selectHandleNodeClick('caseStatus','treeProcess')"></el-tree>
                </el-option>
              </el-select>
            </el-col>
            <el-col :span='8'>
              <el-select ref="selectVerifyResult" v-model="iecResult" title="审核/注册结果" placeholder="审核/注册结果"
                         size="small full" :tselect=true @visible-change="calls()"
                         @sure-click="sure('selectVerifyResult')"
                         @keydown.enter.native="querySearch"
                         @clear-click="clearLocal('treeVerifyResult')">
                <el-option :label="iecResult" :value="iecResultValue">
                  <el-tree :data="verifyResultList" default-expand-all show-checkbox node-key="id" ref="treeVerifyResult" highlight-current
                           :props="default_tree" check-on-click-node="true" @check-change="selectHandleNodeClick('iecResult','treeVerifyResult')"></el-tree>
                </el-option>
              </el-select>
            </el-col>
            <el-col :span='7'>
              <el-select ref="selectIssueCondition" v-model="issueCondition" title="发行人选择的上市条件" placeholder="发行人选择的上市条件"
                         size="small full" :tselect=true @visible-change="calls()"
                         @sure-click="sure('selectIssueCondition')"
                         @keydown.enter.native="querySearch"
                         @clear-click="clearLocal('treeIssueCondition')">
                <el-option :label="issueCondition" :value="issueConditionValue">
                  <el-tree :data="issueConditionList" default-expand-all show-checkbox node-key="id" ref="treeIssueCondition" highlight-current
                           :props="default_tree" check-on-click-node="true" @check-change="selectHandleNodeClick('issueCondition','treeIssueCondition')"></el-tree>
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
            </el-col>
          </el-row>
          <el-row :gutter="24">
            <el-col :span='12'>
              <span style="font-size: 12px;color: #666">为您检索到相关结果{{totalCount}}条，默认以</span>
              <span style="font-size: 12px;color: #333">发审会审核时间↓</span>
              <span style="font-size: 12px;color: #666">排序。</span>
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
                <el-table :cell-class-name="cellStyle" @sort-change="sortChange" :data="tableData" style="width: 100%" v-loading="tableLoading" ref="tables" class="case" element-loading-text="给我一点时间">
                  <el-table-column align="left" width="100" label="公司">
                    <template slot-scope="scope" >
											<span @click="itemClickHandler(scope.row)">
                      {{scope.row.companyCode}}
                      <br/>
                      {{scope.row.companyName}}
											</span>
                    </template>
                  </el-table-column>
                  <el-table-column align="left" label="案例标题" min-width="22%">
                    <template slot-scope="scope">
											<span @click="itemClickHandler(scope.row)" class="title_span">
                      {{scope.row.titleStr}}
											</span>
                    </template>
                  </el-table-column>
                  <el-table-column align="left" prop="ipo_label_result_t" label="进程" sortable="custom" min-width="11%">
                    <template slot-scope="scope">
                      {{scope.row.processLabel}}
                      <br/>
                      <span>
                          <span class="htgResult"  v-if="scope.row.labelResult==='00'">获通过</span>
                          <span class="whtgResult" v-if="scope.row.labelResult==='01'">未获通过</span>
                          <span class="zhbjResult" v-if="scope.row.labelResult==='02'">暂缓表决</span>
                          <span class="qxshResult" v-if="scope.row.labelResult==='03'">取消审核</span>
                          <span class="dshResult"  v-if="scope.row.labelResult==='04'">待上会</span>
                          <span class="tgResult"   v-if="scope.row.labelResult==='05'">通过</span>
                          <span class="wtgResult"  v-if="scope.row.labelResult==='06'">未通过</span>
                          <span class="zcsxResult" v-if="scope.row.labelResult==='07'">注册生效</span>
                          <span class="whtgResult" v-if="scope.row.labelResult==='08'">不予注册</span>
                          <span class="dshResult"  v-if="scope.row.labelResult==='09'">待上会</span>
                          <span class="qxshResult"  v-if="scope.row.labelResult==='10'">取消审议</span>
                        <!-- <span v-if="scope.row.reviewResult!=null">
                          <span class="tgResult"  v-if="scope.row.reviewResult==='05'">通过</span>
                          <span class="wtgResult" v-if="scope.row.reviewResult==='06'">未通过</span>
                          <span class="dshResult"  v-if="scope.row.reviewResult==='09'">待上会</span>
                        </span>
                        <span v-if="scope.row.reviewResult==null&&scope.row.registerResult!=null">
                          <span class="zcsxResult"  v-if="scope.row.registerResult==='07'">注册生效</span>
                          <span class="whtgResult" v-if="scope.row.registerResult==='08'">不予注册</span>
                        </span>
                        <span v-if="scope.row.reviewResult==null&&scope.row.registerResult==null&&scope.row.iecResult!=null">
                          <span class="htgResult"  v-if="scope.row.iecResult==='00'">获通过</span>
                          <span class="whtgResult" v-if="scope.row.iecResult==='01'">未获通过</span>
                          <span class="zhbjResult" v-if="scope.row.iecResult==='02'">暂缓表决</span>
                          <span class="qxshResult" v-if="scope.row.iecResult==='03'">取消审核</span>
                          <span class="dshResult"  v-if="scope.row.iecResult==='04'">待上会</span>
                          <span class="tgResult"   v-if="scope.row.iecResult==='05'">通过</span>
                          <span class="wtgResult"  v-if="scope.row.iecResult==='06'">未通过</span>
                          <span class="dshResult"  v-if="scope.row.iecResult==='09'">待上会</span>
                        </span> -->
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
                  <el-table-column align="right" prop="ipo_sum_asset_d" label="总资产" sortable="custom" min-width="12%">
                    <template slot-scope="scope">
                      <span v-if="scope.row.sunAsset">{{scope.row.sunAsset | dataInThRule}}亿元</span>
                      <span v-else>--</span>
                    </template>
                  </el-table-column>
                  <!-- <el-table-column align="right" prop="ipo_sum_asset_d" label="最近一次估值" sortable="custom" min-width="10%">
                   <template slot-scope="scope">
                      <span v-if="scope.row.valuationValue ">{{scope.row.valuationValue/10000 | dataInThRule}}亿元</span>
                     <span v-else>--</span>
                   </template>
                 </el-table-column> -->
                  <el-table-column align="left" label="拟上市板块" width="110">
                    <template slot-scope="scope">
                      {{scope.row.ipoPlateName}}
                    </template>
                  </el-table-column>
                  <el-table-column align="right" prop="ipo_final_time_dt" label="审核时间" sortable="custom" width="100">
                    <template slot-scope="scope">
                      <span v-if="scope.row.finalTime">{{getLocalTime(scope.row.finalTime)}}</span>
                      <span v-else>--</span>
                      <!-- <span v-if="scope.row.reviewTime">{{getLocalTime(scope.row.reviewTime)}}</span>
                      <span v-if="scope.row.reviewTime==null&&scope.row.reMeetingTime">{{scope.row.reMeetingTime}}</span>
                      <span v-if="scope.row.reviewTime==null&&scope.row.reMeetingTime==null">--</span> -->
                    </template>
                  </el-table-column>
                  <el-table-column align="right" prop="ipo_audit_duration_i" label="审核历时" sortable="custom" width="100">
                    <template slot-scope="scope">
                      <span v-if="scope.row.auditDuration">{{scope.row.auditDuration}}天</span>
                      <span v-else>--</span>
                    </template>
                  </el-table-column>
									<el-table-column align="center" prop="ipo_audit_duration_i" label="操作" min-width="6%">
										<template slot-scope="scope">
											<i class="el-icon-star-on" style="cursor: pointer;" @click="removeFavorite(scope.row.id)"></i>
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
		import {
		clickFavorite
	} from '@/api/ipoCase/companyProfile';
  import {_queryIpoFavoriteList, _getSelectData, _queryIntermediary} from '@/api/ipoCase/ipoCaseListApi'
  import papers from "@/views/components-demo/papersNew";
  import {iframeDoMessage} from '@/utils/auth'
	import {
		mapGetters
	} from "vuex";

  export default {
    data() {
      return {
        issueLawId:'',//上市条件法规id
        tenantInfo:'',//日志
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
        strageticIndustries: '',//战略新兴
        strageticIndustriesValue: '',//战略新兴
        issueCondition: '',
        issueConditionValue: '',
        companyNature: '',
        companyNatureValue: '',
        ipoNum: '',
        ipoNumValue: '',
        placingMechanism: '',//配售机制
        placingMechanismValue: '',//配售机制
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
        strageticIndustriesList: [],//战略新兴
        treeStrageticIndustries: '',//战略新兴
        issueConditionList: [],
        treeIssueCondition: '',
        companyNatureList: [],
        treeCompanyNature: '',
        ipoNumList: [],
        ipoMechanismList: [],//配售机制
        treeIpoNum: '',
        treePlacingMechanism:'',//配售机制
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
        valuationValue:[],
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
        //招股书最近一次估值
        optionProspectus: [
          {id: 1, value: '0-10'},
          {id: 2, value: '10-20'},
          {id: 3, value: '20-30'},
          {id: 4, value: '30-40'},
          {id: 5, value: '40-50'},
          {id: 6, value: '50-100'},
          {id: 7, value: '100'}
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
        prospectusShow:true,//招股书最近一次估值
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
    created(){
      // 日志
      let param = {
        client_type:'pc',//手机或pc
        recordType:'menu',//跳转页面方式:
        recordModule:'IPO案例',//跳转模块
        recordTab:"ipo案例列表页",//跳转tab
        recordTabChild:null,//跳转子集tab
        popTitle:null//弹窗title
      }
      this.$store.commit('CREATE_MESSAGE',param)
    },
    mounted() {
      // 日志
      this.tenantInfo = this.$route.query['tenant_info'];
      this.tableLoading = true;
      const _data = {
        startRow: 0,
        pageSize: 20
      };
      this.search(_data);
      this.getSelectData();
    },
		computed: { //获取getters中方法
			...mapGetters('ipoCase',[
				'destroyIpoFavorite', //销毁收藏页面
			])
		},
    watch: {
			"destroyIpoFavorite"(val, oldVal) {
				if (val) {
					this.$store.commit('ipoCase/SET_IPO_DESTROY_FAVORITE', false);
					this.$destroy();
				}
			},
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
    updated(){
      console.log('aaa',this.caseStatus,this.caseStatusValue)
    },
    methods: {
			// 取消点赞
			removeFavorite(id){
				if (id) {
				  var caseId = id.substring(3, id.length);
					let param = {
						caseId: caseId,
						favoriteFlag : false,
					};
					clickFavorite(param).then(res => {
							iframeDoMessage(window.parent, 'popMsg', ['取消收藏']);
							 const _data = {
							  startRow: 0,
							  pageSize: 20
							};
							this.search(_data);
					})
				}
			},
      getLocalTime(time) {
        let date = new Date(time);
        let Y = date.getFullYear() + '-';
        let M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        let D = (date.getDate() < 10 ? '0'+(date.getDate()) : date.getDate()) + '';
        return Y+M+D
      },
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
            strageticIndustries: _self.strageticIndustriesValue,//发行人行业（战略新兴）
            issueCondition: _self.issueConditionValue,//发行人选择的上市条件
            companyNature: _self.companyNatureValue,//企业性质
            ipoNum: _self.ipoNumValue,//申报次数
            placingMechanism: _self.placingMechanismValue,//配售机制
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
            valuationValue: _self.valuationValue,//招股书最近一次估值
            timeDiff: _self.timeDiff,//申报审核历时（天）
          },
          orderByName: _self.orderByName,
          orderByOrder: _self.orderByOrder
        };
        _queryIpoFavoriteList(_data).then(response => {
           _self.tableLoading = false;
          if (response.data && response.data.success && response.data.result) {
            _self.issueLawId = response.data.result.issueLawId;
            _self.totalCount = response.data.result.total;
            _self.tableData = response.data.result.data;
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
      selectHandleNodeClick(val, treeVal) {
        // debugger;
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
        _self.strageticIndustriesValue = '';//战略新兴
        _self.strageticIndustries = '';
        _self.issueConditionValue = '';//发行人选择的上市条件
        _self.issueCondition = '';
        _self.companyNatureValue = '';//企业性质
        _self.companyNature = '';
        _self.ipoNum = '';//ipo次数
        _self.ipoNumValue = '';
        _self.placingMechanism = '';//配售机制
        _self.placingMechanismValue = '';
        _self.caseStatus = '';//IPO进程
        _self.caseStatusValue = '';
        _self.$refs.treeProcess.setCheckedKeys([]);
        _self.$refs.treeVerifyResult.setCheckedKeys([]);
        _self.$refs.treeIssueCondition.setCheckedKeys([]);
        _self.iecResult = '';//审核结果
        _self.iecResultValue = '';
        _self.codeOrName = '';//公司简称/代码
        _self.intermediary = '';//中介机构
        _self.intermediaryCode = '';
        _self.ypProcessTime = [];//预先披露时间
        _self.fsProcessTime = [];//发审会审核时间
        _self.orderByName = '';//排序
        _self.orderByOrder = '';
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
        _self.valuationValue = [];//招股书最近一次估值
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
        _self.prospectusShow = false;//招股书最近一次估值
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
          _self.prospectusShow = true;//招股书最近一次估值
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
        // debugger;
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
            if (response.data.result.strageticIndustriesList && response.data.result.strageticIndustriesList.length > 0) {
              _self.strageticIndustriesList = response.data.result.strageticIndustriesList;
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
            if (response.data.result.placingMechanism && response.data.result.placingMechanism.length > 0) {
              _self.ipoMechanismList = response.data.result.placingMechanism;
            }
            if (response.data.result.verifyResultList && response.data.result.verifyResultList.length > 0) {
              _self.verifyResultList = response.data.result.verifyResultList;
            }
            if (response.data.result.processList && response.data.result.processList.length > 0) {
              _self.processList = response.data.result.processList;
              for(var i = 0;i<_self.processList.length;i++){
                _self.processList[i].id = "3106911158549214310"+i
              }
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
      // 招股书最近一次估值
      rangeCallProspectus(data) {
        this.valuationValue = [data[0]*10000,data[1]*10000];
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
         var data=[];
         for(var i=0;i<response.data.result.length;i++){
           var d = response.data.result[i];
           var param = {
             labelValue:d.companyCode,
             value:d.companyName
           };
           data.push(param);
         }
         // 调用 callback 返回建议列表的数据
         cb(data);
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
            query: {caseId: caseId, access_token: _self.$route.query.access_token,tenant_info:_self.$route.query.tenant_info}
          });
          // 日志---------------------头
          let param = {
            recordType:'open',//跳转页面方式:
            recordTab:"IPO案例详情页"//跳转tab
          }
          this.$store.commit('CREATE_TEMP_MESSAGE',param);
          // 日志---------------------尾
          this.$open(href, '_blank');
        } else {
          let url = window.location.href;
          url = url.replace(this.$route.path, '/ipoPopWin');
          console.log('列表页跳转弹窗',url)
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
        const href = window.location.origin + '/ui/laws/laws/lawsDetail?lawId='+_self.issueLawId+'&access_token=' + _self.$store.state.app.token + '&tenant_info=' + _self.$store.state.app.info;
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
  @media screen and (max-width: 3840px) and (min-width: 1921px) {
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
    color:  #f9b162;
    padding:5px;
    padding-left: 10px;
    line-height:10px;
    display:inline-block;
    background-size:cover;
    background:url('../../assets/images/zhbj.png') no-repeat;
  }
  .whtgResult{
    font-size: 12px;
    color: #ea5365;
    padding:5px;
    padding-left: 10px;
    line-height:10px;
    display:inline-block;
    background-size:cover;
    background:url('../../assets/images/whtg.png') no-repeat;
  }
  .wtgResult{
    font-size: 12px;
    color: #ea5365;
    padding: 5px;
    padding-left: 10px;
    line-height: 10px;
    display: inline-block;
    background-size: cover;
    background:url('../../assets/images/wtg.png') no-repeat;

  }
  .htgResult{
    font-size: 12px;
    color: #4ec8e5;
    padding:5px;
    padding-left: 10px;
    line-height:10px;
    display:inline-block;
    background-size:cover;
    background:url('../../assets/images/htg.png') no-repeat;
  }
  .zcsxResult{
    font-size: 12px;
    color: #4ec8e5;
    padding:5px;
    padding-left: 10px;
    line-height:10px;
    display:inline-block;
    background-size:cover;
    background:url('../../assets/images/zcsx.png') no-repeat;
  }
  .tgResult{
    font-size: 13px;
    color: #4ec8e5;
    padding:5px;
    padding-left: 10px;
    line-height:10px;
    display:inline-block;
    background-size:cover;
    background:url('../../assets/images/tg.png') no-repeat;
  }
  .qxshResult{
    font-size: 12px;
    color: #aabfe2;
    padding:5px;
    padding-left: 10px;
    line-height:10px;
    display:inline-block;
    background-size:cover;
    background:url('../../assets/images/qxsh.png') no-repeat;
  }
  .dshResult{
    font-size: 12px;
    color: #f98962;
    padding:5px;
    padding-left: 10px;
    padding-right: 6px;
    line-height:10px;
    display:inline-block;
    background-size:cover;
    background:url('../../assets/images/dsh.png') no-repeat;
  }
  .ipoCaseTable .el-table--border td, .el-table--border th {
    border-right: none !important;
  }
  /* 招股书样式 */
  .prospectus li {
    width: 30%;
    text-align:center;
  }
  .title_span:hover {
  	text-decoration: underline;
  	cursor: pointer;
  }
  /* 受理时间及审核时间日期控件样式 */
  /* .acceptTime .el-range-separator{
    display:none
  }
  .acceptTime .el-range-editor--small .el-range__close-icon, .el-range-editor--small .el-range__icon {
      position: relative;
      left: 97%;
      line-height: 24px;
  } */
  /* ipocaseList受理时间及审核时间日期控件样式 */
  /* @media screen and (max-width: 1680px) and (min-width: 1359px) {
    .acceptTime.el-date-editor .el-range-input {
        width: 26%;
        position: relative;
        left: -17px;
    }
  }
  @media screen and (max-width: 1920px) and (min-width: 1681px) {
      .acceptTime.el-date-editor .el-range-input {
        width: 16%;
        position: relative;
        left: -17px;
      }
  }
    @media screen and (max-width: 3840px) and (min-width: 1921px) {
    .acceptTime.el-date-editor .el-range-input {
        width: 6%;
        position: relative;
        left: -17px;
      }
  } */

</style>

