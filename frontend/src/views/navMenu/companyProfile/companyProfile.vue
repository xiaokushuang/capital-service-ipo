<template>
  <div class="companyProfile" v-loading="listLoading" element-loading-text="给我一点时间">
    <!-- 公司简介 -->
    <div class="companey">
      <div class="briefIntroduction">
        <p style="color: #333; font-weight: bold;font-size: 14px;">
          {{this.headList.title}}
        </p>
        <ul style="display:flex;margin-bottom: 0;">
          <li>
            <span>拟上市板块</span>&nbsp;&nbsp;&nbsp;
            <span style="color:#333333">{{this.ipoPlate}}</span>
          </li>
          <li>
            <span>所属证监局</span>&nbsp;&nbsp;
            <span style="color:#333333">{{this.industryCsrc}}</span>
          </li>
          <li>
            <span>注册资本</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="color:#333333">{{this.registeredAssets}}万元</span>
          </li>
          <li>
            <span>公司简称</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="color:#333333">{{this.companyName}}</span>
          </li>
          <li>
            <span>证券代码</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="color:#333333">{{this.zhengquanCode}}</span>
          </li>
          <li>
            <span>企业性质</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="color:#333333">{{this.companyNature}}</span>
          </li>
        </ul>
        <div>

          <li style="margin-bottom:10px">
            <span>注册地址</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="color:#333333">{{this.addrProv}}{{this.addrCity}}{{this.addrArea}}</span>
          </li>


          <li style="margin-bottom:10px">
            <span>控股股东</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="color:#333333">{{this.controlShareholder}}</span>
          </li>
          <li style="margin-bottom:10px">
            <span>实际控制人</span>&nbsp;&nbsp;&nbsp;
            <span style="color:#333333">{{this.actualController}}</span>
          </li>
          <li style="margin-bottom:10px;position:relative" >
            <span>主营业务</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <div class="majorBusinesses">{{this.majorBusinesses}}</div>
          </li>
        </div>

      </div>
      <div class="others" v-if="this.otherMarketInfoList&&this.otherMarketInfoList.length>0">
        <p style="color:black">登录其他资本市场</p>
        <ul v-for="item in otherMarketInfoList">
          <li style=" width: 30%;">
            <span>资本市场</span>&nbsp;&nbsp;
            <span style="color:black">{{item.marketType}}</span>
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
    <div class="ownershipStructure" style="margin-top:40px">
      <div class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="ownershipStructureChart">股权结构图</span>
        <span v-for="(item,index) in structureLabel" class="hongkuang">{{item}}</span>
      </div>
      <!-- 图片 -->
      <div class="img">
         <img :src="structureUrl" alt>
      </div>
      <!-- 股权股东表格 -->
      <el-table :data="gqTableList" style="width: 100%" stripe border>
        <el-table-column type="index" label="序号" align='center' width="72px">
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
        <el-table-column prop="shareNum" label="持股数量（万股）" align='right'>
           <template slot-scope="scope">
                <span v-if="scope.row.shareNum"> {{scope.row.shareNum | dataInThRule}}</span>
                <span v-else> - - </span>
           </template>
        </el-table-column>
        <el-table-column prop="shareRatio" label="持股比例" align='right'>
           <template slot-scope="scope">
              <!-- <span>{{isNotEmpty(scope.row.shareRatio) ? scope.row.shareRatio+'%' : '- -'}}</span> -->
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
        <barOrPieChart v-if="this.mainTableList.firstYearForIncome" :mainTableList = "this.mainTableList"></barOrPieChart>
      </div>
      <!-- table表格 -->
      <div class="incomeCompositionTable">
         <mainTable :mainTableList = "this.mainTableList"></mainTable>
      </div>
    </div>
    <!-- 主要竞争对手简介 -->
    <div class="MajorCompetitors">
      <div class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="profileOfMajorCompetitors">主要竞争对手简介</span>
      </div>
      <div class="competitorContent">
        <ul class="competitorUl" style="padding-left:0">
          <li class="clear competitorLi" v-for="data in MajorCompetitors">
            <div class="leftTitle" style="width: 16%;margin:auto;
              display: inline-block;">
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
                margin:auto;
                width: 84%;
                margin-left: 39px;">
             <p class="moreText">{{data.situation}}</p>
            </div>
          </li>
        </ul>
      </div>
    </div>
    <!-- 报告期主要供应商及客户情况 -->
    <div class="theTopFive">
       <div class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="majorSuppliers">报告期主要供应商及客户情况</span>
      </div>
      <div class="theTopFiveSupplier">
        <p>报告期前五名供应商情况</p>
        <div v-for="item in supplierMainList" :key="item.id" >
            <p style="font-size:14px;color:#666">{{item.remark}}：</p>
             <el-table :data="item.supplierCustomerInfoList" border style="width: 100%;margin-top: 20px">
              <el-table-column fixed align="center" class-name="table_cell" label="排名" width="50">
                <template slot-scope="scope">
                  {{scope.$index+1}}
                  </template>
              </el-table-column>
              <el-table-column fixed prop="companyName" align="center" class-name="table_cell" label="公司" width="150"></el-table-column>
              <el-table-column :label="item.reportPeriod" header-align="center">
                <el-table-column align="right"  class-name="table_cell" label="采购内容" width="117">
                  <template slot-scope="scope">
                    <span>{{isNotEmpty(scope.row.onePeriodContent) ? scope.row.onePeriodContent : '- -'}}</span>
                  </template>
                </el-table-column>
                <el-table-column align="right"  class-name="table_cell" label="金额(万元)" width="117">
                  <template slot-scope="scope">
                      <span v-if="scope.row.onePeriodAmount"> {{scope.row.onePeriodAmount | dataInThRule}}</span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
                <el-table-column align="right"  class-name="table_cell" label="占比" width="117">
                  <template slot-scope="scope">
                      <span v-if="scope.row.onePeriodRatio"> {{scope.row.onePeriodRatio | dataInThRule}}%</span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
              </el-table-column>
              <el-table-column :label="item.thirdYearForSupplier" header-align="center">
                <el-table-column align="right"  class-name="table_cell" label="采购内容" width="117">
                  <template slot-scope="scope">
                    <span>{{isNotEmpty(scope.row.thirdYearContent) ? scope.row.thirdYearContent : '- -'}}</span>
                  </template>
                </el-table-column>
                <el-table-column align="right"  class-name="table_cell" label="金额(万元)" width="117">
                  <template slot-scope="scope">
                      <span v-if="scope.row.thirdYearAmount"> {{scope.row.thirdYearAmount | dataInThRule}}</span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
                <el-table-column align="right"  class-name="table_cell" label="占比" width="117">
                  <template slot-scope="scope">
                      <span v-if="scope.row.thirdYearRatio"> {{scope.row.thirdYearRatio | dataInThRule}}%</span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
              </el-table-column>
              <el-table-column :label="item.secondYearForSupplier" header-align="center">
                <el-table-column align="right"  class-name="table_cell" label="采购内容" width="117">
                  <template slot-scope="scope">
                    <span>{{isNotEmpty(scope.row.secondYearContent ) ? scope.row.secondYearContent  : '- -'}}</span>
                  </template>
                </el-table-column>
                <el-table-column align="right"  class-name="table_cell" label="金额(万元)" width="117">
                  <template slot-scope="scope">
                      <span v-if="scope.row.secondYearAmount"> {{scope.row.secondYearAmount | dataInThRule}}</span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
                <el-table-column align="right"  class-name="table_cell" label="占比" width="117">
                  <template slot-scope="scope">
                      <span v-if="scope.row.secondYearRatio"> {{scope.row.secondYearRatio | dataInThRule}}%</span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
              </el-table-column>
              <el-table-column :label="item.firstYearForSupplier" header-align="center">
                <el-table-column align="right"  class-name="table_cell" label="采购内容" width="117">
                  <template slot-scope="scope">
                    <span>{{isNotEmpty(scope.row.firstYearContent ) ? scope.row.firstYearContent  : '- -'}}</span>
                  </template>
                </el-table-column>
                <el-table-column align="right"  class-name="table_cell" label="金额(万元)" width="117">
                  <template slot-scope="scope">
                      <span v-if="scope.row.firstYearAmount"> {{scope.row.firstYearAmount | dataInThRule}}</span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
                <el-table-column align="right"  class-name="table_cell" label="占比" width="117">
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
        <p>报告期前五名客户情况</p>
          <div v-for="item in customerMainList" :key="item.id" >
              <p style="font-size:14px;color:#666">{{item.remark}}：</p>
              <el-table :data="item.supplierCustomerInfoList" border style="width: 100%;margin-top: 20px">
                <el-table-column fixed align="center" class-name="table_cell" label="排名" width="50">
                  <template slot-scope="scope">
                    {{scope.$index+1}}
                    </template>
                </el-table-column>
                <el-table-column fixed prop="companyName" align="center" class-name="table_cell" label="公司" width="150"></el-table-column>
                <el-table-column :label="item.reportPeriod" header-align="center">
                  <el-table-column align="right"  class-name="table_cell" label="采购内容" width="117">
                    <template slot-scope="scope">
                      <span>{{isNotEmpty(scope.row.onePeriodContent) ? scope.row.onePeriodContent : '- -'}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column align="right"  class-name="table_cell" label="金额(万元)" width="117">
                    <template slot-scope="scope">
                      <span v-if="scope.row.onePeriodAmount"> {{scope.row.onePeriodAmount | dataInThRule}}</span>
                      <span v-else> - - </span>
                    </template>
                  </el-table-column>
                  <el-table-column align="right"  class-name="table_cell" label="占比" width="117">
                    <template slot-scope="scope">
                      <span v-if="scope.row.onePeriodRatio"> {{scope.row.onePeriodRatio | dataInThRule}}%</span>
                      <span v-else> - - </span>
                    </template>
                  </el-table-column>
                </el-table-column>
                <el-table-column :label="item.thirdYearForCustomer" header-align="center">
                  <el-table-column align="right"  class-name="table_cell" label="采购内容" width="117">
                    <template slot-scope="scope">
                      <span>{{isNotEmpty(scope.row.thirdYearContent) ? scope.row.thirdYearContent : '- -'}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column align="right"  class-name="table_cell" label="金额(万元)" width="117">
                    <template slot-scope="scope">
                      <span v-if="scope.row.thirdYearAmount"> {{scope.row.thirdYearAmount | dataInThRule}}</span>
                      <span v-else> - - </span>
                    </template>
                  </el-table-column>
                  <el-table-column align="right"  class-name="table_cell" label="占比" width="117">
                    <template slot-scope="scope">
                      <span v-if="scope.row.thirdYearRatio"> {{scope.row.thirdYearRatio | dataInThRule}}%</span>
                      <span v-else> - - </span>
                  </template>
                  </el-table-column>
                </el-table-column>
                <el-table-column :label="item.secondYearForCustomer" header-align="center">
                  <el-table-column align="right"  class-name="table_cell" label="采购内容" width="117">
                    <template slot-scope="scope">
                      <span>{{isNotEmpty(scope.row.secondYearContent ) ? scope.row.secondYearContent  : '- -'}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column align="right"  class-name="table_cell" label="金额(万元)" width="117">
                    <template slot-scope="scope">
                      <span v-if="scope.row.secondYearAmount"> {{scope.row.secondYearAmount | dataInThRule}}</span>
                      <span v-else> - - </span>
                    </template>
                  </el-table-column>
                  <el-table-column align="right"  class-name="table_cell" label="占比" width="117">
                    <template slot-scope="scope">
                      <span v-if="scope.row.secondYearRatio"> {{scope.row.secondYearRatio | dataInThRule}}%</span>
                      <span v-else> - - </span>
                  </template>
                  </el-table-column>
                </el-table-column>
                <el-table-column :label="item.firstYearForCustomer" header-align="center">
                  <el-table-column align="right"  class-name="table_cell" label="采购内容" width="117">
                    <template slot-scope="scope">
                      <span>{{isNotEmpty(scope.row.firstYearContent ) ? scope.row.firstYearContent  : '- -'}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column align="right"  class-name="table_cell" label="金额(万元)" width="117">
                    <template slot-scope="scope">
                      <span v-if="scope.row.firstYearAmount"> {{scope.row.firstYearAmount | dataInThRule}}</span>
                      <span v-else> - - </span>
                  </template>
                  </el-table-column>
                  <el-table-column align="right"  class-name="table_cell" label="占比" width="117">
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
          <el-table :data="raiseMoneyTableList" border style="width:100%;">
            <el-table-column label="项目名称" align="left">
                <template slot-scope="scope">
                    <span v-if="scope.row.itemName">{{scope.row.itemName}}</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
            <el-table-column label="项目类型" align="center">
                <template slot-scope="scope">
                      <span v-if="scope.row.itemTypeStr">{{scope.row.itemTypeStr}}</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
            <el-table-column label="项目总投资(万元)" align="right" prop="investTotal">
                <template slot-scope="scope">
                      <span v-if="scope.row.investTotal">{{scope.row.investTotal | dataInThRule}}</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
            <el-table-column label="拟投入募集资金金额（万元）" align="right" prop="investPlan">
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
            <el-table-column label="前期已投入资金金额（万元）" align="right" prop="investPre">
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
import { getCaseDetail } from "@/api/ipoCase/companyProfile";
import { getMarketData } from "@/api/ipoCase/companyProfile";
import { getShareHolderData } from "@/api/ipoCase/companyProfile";
import { getCompetitorData } from "@/api/ipoCase/companyProfile";
import { getRaiseMoneyTableList } from "@/api/ipoCase/companyProfile";
import { getSupplierCustomerData } from '@/api/ipoCase/tableDemo';
import { getTableData } from '@/api/ipoCase/tableDemo';
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
      companyZhName:'',//公司名称
      ipoPlate:'',//上市板块
      industryCsrc:'',//所属行业(证监会) ,
      companyName:'',//公司简称
      zhengquanCode:'',//证券代码
      addrProv:'',//注册地（省）
      addrCity:'',//注册地（市）
      addrArea:'',//注册地（区）
      registeredAssets:'',//注册资本（万元）
      actualController:'',//实际控制人
      controlShareholder:'',//控股股东
      companyNature:'',//企业性质
      majorBusinesses:'',//主营业务
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
    };
  },
  props:["headList"],
  created() {
    this.getData();
    this.getPosition();
  },
  mounted() {
  },
  methods: {
    getData() {
      // 动态传id
      const param = {
        id:this.caseId
      }
      getCaseDetail(param).then(res => {
          if(res.data.result.structureLabel){
           this.structureLabel = res.data.result.structureLabel.split(',');
          }
          if(res.data.result){
            this.id = res.data.result.id//公司id
            this.structureUrl = res.data.result.structureUrl
            this.companyZhName = res.data.result.companyZhName//公司名称
            this.ipoPlate = res.data.result.ipoPlate//上市板块
            this.industryCsrc = res.data.result.industryCsrc//所属行业(证监会) ,
            this.companyName = res.data.result.companyName//证券简称
            this.zhengquanCode = res.data.result.companyCode//证券代码
            this.addrProv = res.data.result.addrProv//注册地（省）
            this.addrCity = res.data.result.addrCity//注册地（市）
            this.addrArea = res.data.result.addrArea//注册地（区）
            this.registeredAssets = res.data.result.registeredAssets//注册资本（万元）
            this.actualController = res.data.result.actualController//实际控制人
            this.controlShareholder = res.data.result.controlShareholder//控股股东
            this.companyNature = res.data.result.companyNature//企业性质
            this.majorBusinesses = res.data.result.majorBusinesses//主营业务  
          }
      });
      getMarketData(param).then(res=>{
        if(res.data.result&&res.data.result.length>0){
          this.otherMarketInfoList = res.data.result//其他登录市场
        }
      });
      getShareHolderData(param).then(res=>{
        if(res.data.result&&res.data.result.length>0){
          this.gqTableList = res.data.result
        }
      });
      getCompetitorData(param).then(res=>{
        if(res.data.result&&res.data.result.length>0){
          this.MajorCompetitors = res.data.result
        }
      });
      getRaiseMoneyTableList(param).then(res=>{
        if(res.data.result&&res.data.result.length>0){
          this.raiseMoneyTableList = res.data.result
        }
      });
      // 供应商
      getSupplierCustomerData(param).then(response => {
        if(response.data.result.supplierMainList&&response.data.result.supplierMainList.length>0){
          this.supplierMainList = response.data.result.supplierMainList
        }
         if(response.data.result.customerMainList&&response.data.result.customerMainList.length>0){
           this.customerMainList = response.data.result.customerMainList
        }
      })
      // 主营业务收入构成
      getTableData(param).then(response => {
        if( response.data.result){
          this.mainTableList = response.data.result
        }
      })

    },
    //返回父组件用于锚点定位头
    getPosition() {
          let titleList = [];
          let ownershipStructureChart = {
              id: 'ownershipStructureChart',
              name: '股权结构图',
              notes: '',
              important: false,
              tabId: 'tab-first',
              noClick: false
          }
          let mainBusinessIncomeComposition = {
              id: 'mainBusinessIncomeComposition',
              name: '主营业务收入构成',
              notes: '',
              important: false,
              tabId: 'tab-first',
              noClick: false
          }
          let majorSuppliers = {
              id: 'majorSuppliers',
              name: '前五名供应商及用户',
              notes: '',
              important: false,
              tabId: 'tab-first',
              noClick: false
          }
          let utilizationOfRaisedFunds = {
              id: 'utilizationOfRaisedFunds',
              name: '募集资金运用',
              notes: '',
              important: false,
              tabId: 'tab-first',
              noClick: false
          }
          let intermediaryInstitutions = {
              id: 'intermediaryInstitutions',
              name: '中介机构',
              notes: '',
              important: false,
              tabId: 'tab-first',
              noClick: false
          }

          titleList.push(ownershipStructureChart)
          titleList.push(mainBusinessIncomeComposition)
          titleList.push(majorSuppliers)
          titleList.push(utilizationOfRaisedFunds)
          titleList.push(intermediaryInstitutions)
          this.$emit('headCallBack', titleList);
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
    margin-top: 30px;
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
  margin-top: -2%;
  line-height: 20px;
}
</style>
