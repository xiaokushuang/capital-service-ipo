<template>
  <div class="industryTechnology">
    <!-- 公司简介 -->
    <div v-if="(industryStatus&&industryStatus.length>0) || companyProfileList.companyProfileList.industryStatusOverview" class="industryStatus">
      <span id="industryStatus"></span>
      <div class="clear">
        <img  src="../../../assets/images/status.png" alt="" style="width:20px;height:20px;float:left;margin-right:10px;margin-top: 7px;">
        <p style="font-size:14px;font-weight:bold;color:#333;float:left;margin-top:10px">{{companyProfileList.companyProfileList.companyName}}的行业地位</p>
      </div>
      <div v-for="data in industryStatus" :key="data.id" style="font-size:15px;color:#333;margin-bottom:8px">
        <span>{{data.rankingRange}}</span>
        <span style="color:#FE5461; position: relative; left: -3px;">
         {{data.rankingIndicator}}</span>排名第<span style="color:#FE5461;font-weight:bold">{{data.ranking }}</span>名
      </div>
       <p v-html="industryStatusOverview" v-if="companyProfileList.companyProfileList.industryStatusOverview" style="font-size:14px;color:#666;margin-top:12px;"></p>
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
   <!-- 招股书列示同行业上市公司综合毛利率对比 -->
    <div class="compare">
        <div v-if="maoChartTableData&&maoChartTableData.length>0" class="title" >
            <span class="littleRectangle"></span>
            <span class="titleText" id="comparison">招股书列示同行业上市公司毛利率对比</span>
        </div>
        <div v-if="maoChartTableData&&maoChartTableData.length>0" >
            <div class="chartTable" v-for="item,index in maoChartTableData" :key="item.id">
                <p style="font-family:'PingFang-SC-Regular', 'PingFang SC';font-weight:400;color:#333;font-size:16px; margin-top: 25px;margin-bottom:0px">{{item.title}}</p>
                <p style="font-family:'PingFang-SC-Regular', 'PingFang SC';font-weight:400;color:#666666;font-size:14px; margin-top: 12px;margin-bottom:0px">{{item.remark}}</p>
                <div class="zxChart" style="height:300px;width:100%; ">
                    <zxChart v-if="maoChartTableData&&maoChartTableData.length>0" ref="zxChart" :zxIndex = "index"></zxChart>
                </div>
                <div class="table">
                    <!-- 其他公司对比 -->
                    <el-table class="otherCompany" stripe :data="item.industryCompareRateDetailList.slice(0,-2)" border style="width: 100%;margin-top: 20px">
                        <el-table-column prop="companyName" align="left" class-name="table_cell" label="企业名称" width="156" ></el-table-column>
                        <el-table-column align="center" :label="item.thirdYear" header-align="center">
                            <template slot-scope="scope">
                                <span v-if="scope.row.thirdYearRate"> {{scope.row.thirdYearRate | dataInThRule}}%</span>
                                <span v-else> - - </span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.secondYear" header-align="center" >
                            <template slot-scope="scope">
                                <span v-if="scope.row.secondYearRate"> {{scope.row.secondYearRate | dataInThRule}}%</span>
                                <span v-else> - - </span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.firstYear" header-align="center" >
                            <template slot-scope="scope">
                                <span v-if="scope.row.firstYearRate"> {{scope.row.firstYearRate | dataInThRule}}%</span>
                                <span v-else> - - </span>
                            </template>
                        </el-table-column>
                    </el-table>
                    <!-- 平均值 -->
                    <el-table class="average averageOrMyself" stripe :data="item.industryCompareRateDetailList.slice(-2,-1)" border style="width: 100%;margin-top: 20px">
                        <el-table-column align="left" class-name="table_cell" label="企业名称" width="156" >
                            <template slot-scope="scope">
                                <span style="font-weight:bold;color:black">{{isNotEmpty(scope.row.companyName) ? scope.row.companyName: '- -'}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.thirdYear" header-align="center">
                            <template slot-scope="scope">
                                <span style="font-weight:bold;color:black" v-if="scope.row.thirdYearRate"> {{scope.row.thirdYearRate | dataInThRule}}%</span>
                                <span style="font-weight:bold;color:black" v-else> - - </span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.secondYear" header-align="center" >
                            <template slot-scope="scope">
                                <span style="font-weight:bold;color:black" v-if="scope.row.secondYearRate"> {{scope.row.secondYearRate | dataInThRule}}%</span>
                                <span style="font-weight:bold;color:black" v-else> - - </span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.firstYear" header-align="center" >
                            <template slot-scope="scope">
                                <span style="font-weight:bold;color:black" v-if="scope.row.firstYearRate"> {{scope.row.firstYearRate | dataInThRule}}%</span>
                                <span style="font-weight:bold;color:black" v-else> - - </span>
                            </template>
                        </el-table-column>
                    </el-table>
                    <!-- 自己公司 -->
                    <el-table class="averageOrMyself myself" stripe :data="item.industryCompareRateDetailList.slice(-1)" border style="width: 100%;margin-top: 20px">
                        <el-table-column prop="companyName" align="left" class-name="table_cell" label="企业名称" width="156" ></el-table-column>
                        <el-table-column align="center" :label="item.thirdYear" header-align="center">
                            <template slot-scope="scope">
                                <span v-if="scope.row.thirdYearRate"> {{scope.row.thirdYearRate | dataInThRule}}%</span>
                                <span v-else> - - </span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.secondYear" header-align="center" >
                            <template slot-scope="scope">
                                <span v-if="scope.row.secondYearRate"> {{scope.row.secondYearRate | dataInThRule}}%</span>
                                <span v-else> - - </span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" :label="item.firstYear" header-align="center" >
                            <template slot-scope="scope">
                                <span v-if="scope.row.firstYearRate"> {{scope.row.firstYearRate | dataInThRule}}%</span>
                                <span v-else> - - </span>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </div>
        </div>
    </div>
    <!-- 专利情况 -->
    <div  class="patentSituation" v-if="companyProfileList.headList.isTechBoard==1">
       <div v-if="patentSituationTableData&&patentSituationTableData.length>0"  class="title" >
            <span class="littleRectangle"></span>
            <span class="titleText" id="patentSituation">专利情况</span>
        </div>
        <p v-if="remarksData!=null&&remarksData.patentRemarks" style="font-size:14px;color:#666">{{remarksData.patentRemarks}}</p>
         <span v-if="patentSituationTableData&&patentSituationTableData.length>0"  class="clear">
            <span style="float: right;font-size: 12px;color: #666666;display:inline-block;margin-bottom:12px">
                单位：项
            </span>
         </span>
        <el-table
         v-if="patentSituationTableData&&patentSituationTableData.length>0"
          class="patentSituationTable"
          :data="patentSituationTableData"
          border
          style="width: 100%">
          <el-table-column
            prop="labelName"
            label=""
            width="180">
            <template slot-scope="scope">
              <span v-if="scope.row.labelName"> {{scope.row.labelName}}</span>
              <span v-else> - - </span>
           </template>
          </el-table-column>
          <el-table-column
            align="right"
            prop="fm"
            label="发明专利"
            width="180">
            <template slot-scope="scope">
              <span v-if="scope.row.fm"> {{scope.row.fm}}
              </span>
              <span v-else> - - </span>
           </template>
          </el-table-column>
          <el-table-column
            align="right"
            prop="sy"
            label="实用新型专利">
            <template slot-scope="scope">
              <span v-if="scope.row.sy"> {{scope.row.sy}}
              </span>
              <span v-else> - - </span>
           </template>
          </el-table-column>
          <el-table-column
            align="right"
            prop="wg"
            label="外观设计专利">
            <template slot-scope="scope">
              <span v-if="scope.row.wg"> {{scope.row.wg}}
              </span>
              <span v-else> - - </span>
           </template>
          </el-table-column>
           <el-table-column
            align="right"
            prop="gw"
            label="国际专利">
             <template slot-scope="scope">
              <span v-if="scope.row.gw"> {{scope.row.gw}}
              </span>
              <span v-else> - - </span>
           </template>
          </el-table-column>
          <el-table-column
            align="right"
            prop="hj"
            label="合计">
            <template slot-scope="scope">
              <span v-if="scope.row.hj"> {{scope.row.hj}}
              </span>
              <span v-else> - - </span>
           </template>
          </el-table-column>
        </el-table>
    </div>
    <!-- 研发投入 -->
    <div class="yfSpending">
        <div v-if="yfSpendingTableData&&yfSpendingTableData.length>0"  class="title" >
            <span class="littleRectangle"></span>
            <span class="titleText" id="yfSpending">研发投入</span>
        </div>
         <p v-if="remarksData!=null&&remarksData.devRemarks" style="font-size:14px;color:#666;margin-bottom:5px">{{remarksData.devRemarks}}</p>
         <span  class="clear">
            <span v-if="yfSpendingTableData&&yfSpendingTableData.length>0"  style="float: right;font-size: 12px;color: #666666;display:inline-block;">
                单位：万元
            </span>
        </span>
         <el-table v-if="yfSpendingTableData&&yfSpendingTableData.length>0" :data="yfSpendingTableData" border style="width: 100%;margin-top: 20px">
            <el-table-column align="left" class-name="table_cell" label="项目" width="190">
              <template slot-scope="scope">
                  <span>{{isNotEmpty(scope.row.labelName ) ? scope.row.labelName  : '- -'}}</span>
              </template>
            </el-table-column>
            <el-table-column align="right"  :label="yfSpendingTitle.firstYearDate" header-align="right">
                <template slot-scope="scope">
                  <span v-if="scope.row.firstYearValue"> {{scope.row.firstYearValue | dataInThRule}}
                   <span v-if="scope.row.labelName === '研发投入占营业收入的比例'">%</span>
                  </span>
                  <span v-else> - - </span>
                </template>
            </el-table-column>
             <el-table-column align="right"  :label="yfSpendingTitle.secondYearDate" header-align="right">
                <template slot-scope="scope">
                  <span v-if="scope.row.secondYearValue"> {{scope.row.secondYearValue | dataInThRule}}
                   <span v-if="scope.row.labelName === '研发投入占营业收入的比例'">%</span>
                  </span>
                  <span v-else> - - </span>
              </template>
            </el-table-column>
            <el-table-column align="right"  :label="yfSpendingTitle.thirdYearDate" header-align="right">
              <template slot-scope="scope">
                  <span v-if="scope.row.thirdYearValue"> {{scope.row.thirdYearValue | dataInThRule}}
                    <span v-if="scope.row.labelName === '研发投入占营业收入的比例'">%</span>
                  </span>
                  <span v-else> - - </span>
              </template>
            </el-table-column>
            <el-table-column align="right" :label="yfSpendingTitle.forthYearDate">
                <template slot-scope="scope">
                            <span v-if="scope.row.forthYearValue"> 
                              {{scope.row.forthYearValue | dataInThRule}}
                              <span v-if="scope.row.labelName === '研发投入占营业收入的比例'">%</span>
                            </span>
                            <span v-else> - - </span>
                </template>
            </el-table-column>
            <el-table-column
              prop=""
              label="合计"
              align="right">
              <template slot-scope="scope"> 
                  <span v-if="scope.row.allNumValue"> {{scope.row.allNumValue | dataInThRule}}
                    <span v-if="scope.row.labelName === '研发投入占营业收入的比例'">%</span>
                  </span>       
              </template>
            </el-table-column>
         </el-table>
    </div>
    <!-- 核心技术及研发技术人员 -->
     <div class="coreTechnology">
        <div v-if="coreTechnologyTableData&&coreTechnologyTableData.length>0"  class="title" >
            <span class="littleRectangle"></span>
            <span class="titleText" id="coreTechnology">核心技术及研发技术人员</span>
        </div>
         <p v-if="remarksData!=null&&remarksData.coreRemarks" style="font-size:14px;color:#666;margin-bottom:5px">{{remarksData.coreRemarks}}</p>
         <span  class="clear">
            <span v-if="coreTechnologyTableData&&coreTechnologyTableData.length>0" style="float: right;font-size: 12px;color: #666666;display:inline-block;">
                单位：人
            </span>
        </span>
         <el-table v-if="coreTechnologyTableData&&coreTechnologyTableData.length>0" :data="coreTechnologyTableData" border style="width: 100%;margin-top: 20px">
            <el-table-column align="left" class-name="table_cell" label="类别" min-width="100">
              <template slot-scope="scope">
                  <span>{{isNotEmpty(scope.row.labelName ) ? scope.row.labelName  : '- -'}}</span>
              </template>
            </el-table-column>
            <el-table-column align="right"  :label="coreTechnologyTitle.firstYearDate" header-align="right">
                <template slot-scope="scope">
                  <span v-if="scope.row.firstYearValue"> {{scope.row.firstYearValue}}
                  </span>
                  <span v-else> - - </span>
                </template>
            </el-table-column>
             <el-table-column align="right" label="占比">
                <template slot-scope="scope">
                              <span v-if="scope.row.firstYearPro"> 
                                {{scope.row.firstYearPro | dataInThRule}}%
                              </span>
                            <span v-else> - - </span>
                </template>
            </el-table-column>
            <el-table-column align="right"  :label="coreTechnologyTitle.secondYearDate" header-align="right">
                <template slot-scope="scope">
                  <span v-if="scope.row.secondYearValue"> {{scope.row.secondYearValue}}
                  </span>
                  <span v-else> - - </span>
              </template>
            </el-table-column>
             <el-table-column align="right" label="占比">
                <template slot-scope="scope">
                              <span v-if="scope.row.secondYearPro"> 
                                {{scope.row.secondYearPro | dataInThRule}}%
                              </span>
                            <span v-else> - - </span>
                </template>
            </el-table-column>
            <el-table-column align="right"  :label="coreTechnologyTitle.thirdYearDate" header-align="right">
              <template slot-scope="scope">
                  <span v-if="scope.row.thirdYearValue"> {{scope.row.thirdYearValue}}
                  </span>
                  <span v-else> - - </span>
              </template>
            </el-table-column>
             <el-table-column align="right" label="占比">
                <template slot-scope="scope">
                              <span v-if="scope.row.thirdYearPro"> 
                                {{scope.row.thirdYearPro | dataInThRule}}%
                              </span>
                            <span v-else> - - </span>
                </template>
            </el-table-column>
            <el-table-column align="right" :label="coreTechnologyTitle.forthYearDate">
                <template slot-scope="scope">
                            <span v-if="scope.row.forthYearValue"> 
                              {{scope.row.forthYearValue}}
                              </span>
                            <span v-else> - - </span>
                </template>
            </el-table-column>
             <el-table-column align="right" label="占比">
                <template slot-scope="scope">
                              <span v-if="scope.row.forthYearPro"> 
                                {{scope.row.forthYearPro | dataInThRule}}%
                              </span>
                            <span v-else> - - </span>
                </template>
            </el-table-column>
         </el-table>
    </div>
    <!-- 已经到底了 -->
    <div style="text-align: center;
        font-size: 14px;
        color: rgb(136, 136, 136);
        margin-top: 32px;
        padding-top: 37px;
        border-top: 1px solid #ebeef5">
        已经到底了
    </div>
  </div>
</template>
<script>
import $ from "jquery";
import echarts from 'echarts'
import zxChart from "@/components/Charts/zxChart";
import { getIndustryStatus } from "@/api/ipoCase/companyProfile";
import { getCompetitorData } from "@/api/ipoCase/companyProfile";
import { getTechnologyInnovation } from "@/api/ipoCase/companyProfile";
import { getMaoChartTableData } from '@/api/ipoCase/tableDemo';
export default {
  name: "industryTechnology",
  components: {
    zxChart,
  },

  data() {
    return {
      caseId:this.$store.state.app.caseId,
      // 行业地位
      industryStatus:[],
      // 主要竞争者
      MajorCompetitors: [],
      // 同行业毛利率对比
      maoChartTableData:[],
      industryStatusOverview:'',
      // 研发投入标题
       yfSpendingTitle:{
        firstYearDate : "",
        forthYearDate : "",
        secondYearDate : "",
        thirdYearDate : "",
      },
      // 研发投入表格内容
      yfSpendingTableData:[],
      // 核心技术及研发技术人员标题
       coreTechnologyTitle:{
        firstYearDate : "",
        secondYearDate : "",
        thirdYearDate : "",
        forthYearDate : "",
      },
      // 核心技术及研发技术人员内容
      coreTechnologyTableData:[],
      // 专利情况
      patentSituationTableData: [],
      // 科技创新表格备注框
      remarksData:{
        patentRemarks:'',//专利
        devRemarks:'',//研发
        coreRemarks:'',//核心技术
      },
    };
  },
  props:["companyProfileList"],
  created() {
     // 日志--------------------功能头
      let param = {
      client_type:'pc',//手机或pc
      recordType:'menu',//跳转页面方式:
      recordModule:'IPO案例',//跳转模块
      recordTab:"ipo案例详情页",//跳转tab
      recordTabChild:null,//跳转子集tab
      popTitle:null//弹窗title
      }
      // this.$store.commit('CREATE_MESSAGE',param)
      // 日志------------------功能尾
    this.getData();
  },
  mounted() {
    console.log(this.companyProfileList.companyProfileList.industryStatusOverview)
  },
  methods: {
    getData() {
      // 动态传id
      const param = {
        id:this.caseId,
      }
      // 行业地位 接口
      getIndustryStatus(param).then(res=>{
        if(res.data.result&&res.data.result.length>0){
          this.industryStatus = res.data.result
          this.industryStatusOverview = this.companyProfileList.companyProfileList.industryStatusOverview.replace(/\n|\r\n/g,"<br/>")
        }
      })
    //   主要竞争对手简介接口
      getCompetitorData(param).then(res=>{
        if(res.data.result&&res.data.result.length>0){
           this.MajorCompetitors = res.data.result
        }
          this.getPosition()
      });
      //毛利率接口
      getMaoChartTableData(param).then(res => {
          if(res.data.result&&res.data.result.length>0){
              this.maoChartTableData = res.data.result              
          }
              this.getPosition()
      }) 
    // 专利情况，研发投入，核心技术及研发技术人员接口
          getTechnologyInnovation(param).then(res => {
            if(res.data.result&&res.data.result.patentData.length>0){
              // 专利情况
              this.patentSituationTableData = res.data.result.patentData
              console.log('专利情况',this.patentSituationTableData)
              this.getPosition()
              }
               if(res.data.result&&res.data.result.devDate!=null){
                  // 研发投入时间
                 this.yfSpendingTitle = res.data.result.devDate
              }
              if(res.data.result&&res.data.result.devData.length>0){
                  // 研发投入内容
                 this.yfSpendingTableData = res.data.result.devData
                 this.getPosition()
              }
               if(res.data.result&&res.data.result.coreDate!=null){
                  // 核心技术时间
                 this.coreTechnologyTitle = res.data.result.coreDate
              }
              if(res.data.result&&res.data.result.coreData.length>0){
                  // 核心技术内容
                 this.coreTechnologyTableData = res.data.result.coreData
                 this.getPosition()
              }
              // 备注框
                if(res.data.result&&res.data.result.remarksData!=null){
                 this.remarksData = res.data.result.remarksData
              }
              this.getPosition()
          }) 
    },
    
  
    //返回父组件用于锚点定位头
    getPosition() {
          let titleList = [];
          let industryStatus = {
              id: 'industryStatus',
              name: '行业地位',
              notes: '',
              important: false,
              tabId: 'tab-sixth',
              noClick: true
          }
          let mainCompetitors = {
              id: 'mainCompetitors',
              name: '主要竞争对手简介',
              notes: '',
              important: false,
              tabId: 'tab-sixth',
              noClick: true
          }
          let comparison = {
                    id: 'comparison',
                    name: '同行业毛利率对比',
                    notes: '',
                    important: false,
                    tabId: 'tab-sixth',
                    noClick: true
          } 
           let patentSituation = {
                    id: 'patentSituation',
                    name: '专利情况',
                    notes: '',
                    important: false,
                    tabId: 'tab-sixth',
                    noClick: true
          } 
           let yfSpending = {
                    id: 'yfSpending',
                    name: '研发投入',
                    notes: '',
                    important: false,
                    tabId: 'tab-sixth',
                    noClick: true
          } 
           let coreTechnology = {
                    id: 'coreTechnology',
                    name: '核心技术及研发技术人员',
                    notes: '',
                    important: false,
                    tabId: 'tab-sixth',
                    noClick: true
          } 
          if((this.industryStatus&&this.industryStatus.length>0) || this.companyProfileList.companyProfileList.industryStatusOverview){
            industryStatus.noClick = false; 
          }  
          if(this.maoChartTableData&&this.maoChartTableData.length>0){
            comparison.noClick = false;
          }             
          if(this.MajorCompetitors&&this.MajorCompetitors.length>0){
            mainCompetitors.noClick = false;
          }
          // 专利情况
           if(this.patentSituationTableData&&this.patentSituationTableData.length>0){
            patentSituation.noClick = false;
          }             
          if(this.yfSpendingTableData&&this.yfSpendingTableData.length>0){
            yfSpending.noClick = false;
          }
          if(this.coreTechnologyTableData&&this.coreTechnologyTableData.length>0){
            coreTechnology.noClick = false;
          }
          titleList.push(industryStatus)
          titleList.push(mainCompetitors)
          titleList.push(comparison)
          if(this.companyProfileList.headList.isTechBoard==1){
            titleList.push(patentSituation)
          }
          titleList.push(yfSpending)
          titleList.push(coreTechnology)
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
// 发行人的行业地位样式
.industryStatus{
  margin-top:32px;
  padding:18px;
  padding-top:10px;
  background:#f7f7f7
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

</style>
