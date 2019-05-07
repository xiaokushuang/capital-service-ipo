<template>
  <div class="industryTechnology">
    <!-- 公司简介 -->
    <div class="industryStatus">
      <div class="clear">
        <img  src="../../../assets/images/status.png" alt="" style="width:20px;height:20px;float:left;margin-right:10px">
        <p style="font-size:14px;font-weight:bold;color:#333;float:left;margin-top:0px">{{companyProfileList.companyName}}的行业地位</p>
      </div>
      <div style="font-size:16px;color:#333;">中国行业内<span style="color:#FE5461">总资产</span>排名第<span style="color:#FE5461;font-weight:bold">37</span>名，2018年度全球<span style="color:#FE5461">市场份额占比</span>排名第<span style="color:#FE5461;font-weight:bold">3</span>名</div>
      <p style="font-size:14px;color:#666;margin-top:12px;">根据中国证券业协会发布的《2017年证券公司经营业绩排名情况》，公司多项财务和业务数据均排名靠前。截至2017年（末），公司总资产在行业内排名第37名，净资产排名第30名，净资本排名第38名，营业收入排名第35名，净利润排名第28名。融资融券业务利息收入、并购重组财务顾问业务收入等指标排名较2016年均有上升。</p>
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
            <span class="titleText" id="comparison">招股书列示同行业上市公司综合毛利率对比</span>
        </div>
        <div v-if="maoChartTableData&&maoChartTableData.length>0" >
            <div class="chartTable" v-for="item,index in maoChartTableData" :key="item.id">
                <p style="font-family:'PingFang-SC-Regular', 'PingFang SC';font-weight:400;color:#666666;font-size:14px; margin-top: 30px;margin-bottom:0px">{{item.remark}}</p>
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
    <div class="patentSituation">
       <div class="title" >
            <span class="littleRectangle"></span>
            <span class="titleText" id="patentSituation">专利情况</span>
        </div>
        <el-table
          :data="patentSituationTableData"
          border
          style="width: 100%">
          <el-table-column
            prop="country"
            label=""
            width="180">
          </el-table-column>
          <el-table-column
            prop="fm"
            label="发明专利"
            width="180">
          </el-table-column>
          <el-table-column
            prop="sy"
            label="实用新型专利">
          </el-table-column>
          <el-table-column
            prop="wg"
            label="外观设计专利">
          </el-table-column>
          <el-table-column
            prop="hj"
            label="合计">
          </el-table-column>
          <el-table-column
            prop="zb"
            label="占比">
          </el-table-column>
        </el-table>
    </div>
    <!-- 研发投入 -->
    <div class="yfSpending">
        <div class="title" >
            <span class="littleRectangle"></span>
            <span class="titleText" id="yfSpending">研发投入</span>
        </div>
         <span  class="clear">
                    <span style="float: right;font-size: 12px;color: #666666;display:inline-block;">
                       单位：万元
                    </span>
                </span>
         <el-table :data="yfSpendingTableData" border style="width: 100%;margin-top: 20px">
            <el-table-column align="left" class-name="table_cell" label="项目" width="184">
              <template slot-scope="scope">
                  <span>{{isNotEmpty(scope.row.itemName ) ? scope.row.itemName  : '- -'}}</span>
              </template>
            </el-table-column>
            <el-table-column align="right" :label="yfSpendingTitle.forthYearDate">
                <template slot-scope="scope">
                            <span v-if="scope.row.forthYearValue"> 
                              {{scope.row.forthYearValue | dataInThRule}}
                              <span v-if="scope.row.itemName === '研发费用占营业收入的比例'">%</span>
                              </span>
                            <span v-else> - - </span>
                </template>
            </el-table-column>
            <el-table-column align="right"  :label="yfSpendingTitle.thirdYearDate" header-align="right">
              <template slot-scope="scope">
                  <span v-if="scope.row.thirdYearValue"> {{scope.row.thirdYearValue | dataInThRule}}
                    <span v-if="scope.row.itemName === '研发费用占营业收入的比例'">%</span>
                  </span>
                  <span v-else> - - </span>
              </template>
            </el-table-column>
            <el-table-column align="right"  :label="yfSpendingTitle.secondYearDate" header-align="right">
                <template slot-scope="scope">
                  <span v-if="scope.row.secondYearValue"> {{scope.row.secondYearValue | dataInThRule}}
                   <span v-if="scope.row.itemName === '研发费用占营业收入的比例'">%</span>
                  </span>
                  <span v-else> - - </span>
              </template>
            </el-table-column>
            <el-table-column align="right"  :label="yfSpendingTitle.firstYearDate" header-align="right">
                <template slot-scope="scope">
                  <span v-if="scope.row.firstYearValue"> {{scope.row.firstYearValue | dataInThRule}}
                   <span v-if="scope.row.itemName === '研发费用占营业收入的比例'">%</span>
                  </span>
                  <span v-else> - - </span>
                </template>
            </el-table-column>
            <el-table-column
              prop=""
              label="合计"
              align="center">
              <template slot-scope="scope">          
              </template>
            </el-table-column>
         </el-table>
    </div>
    <!-- 核心技术及研发技术人员 -->
     <div class="coreTechnology">
        <div class="title" >
            <span class="littleRectangle"></span>
            <span class="titleText" id="coreTechnology">核心技术及研发技术人员</span>
        </div>
    </div>
  </div>
</template>
<script>
import $ from "jquery";
import echarts from 'echarts'
import zxChart from "@/components/Charts/zxChart";
import { getCompetitorData } from "@/api/ipoCase/companyProfile";
import { getMaoChartTableData } from '@/api/ipoCase/tableDemo';
export default {
  name: "industryTechnology",
  components: {
    zxChart,
  },

  data() {
    return {
      caseId:this.$store.state.app.caseId,
      // 主要竞争者
      MajorCompetitors: [],
      // 同行业毛利率对比
      maoChartTableData:[],
      // 研发投入
      yfSpendingTitle:{
        firstYearDate : "2015-12-31",
        forthYearDate : "2018-06-30",
        secondYearDate : "2016-12-31",
        thirdYearDate : "2017-12-31"
      },
      yfSpendingTableData:[
        {
          firstYearValue: 468753.43,
          forthYearValue: 650551.2,
          itemName: "资产总计",
          secondYearValue: 446662.68,
          thirdYearValue: 595554.31,
        },
        {
          firstYearValue: 468753.43,
          forthYearValue: 650551.2,
          itemName: "资产总计",
          secondYearValue: 446662.68,
          thirdYearValue: 595554.31,
        },
        {
          firstYearValue: 468753.43,
          forthYearValue: 650551.2,
          itemName: "资产总计",
          secondYearValue: 446662.68,
          thirdYearValue: 595554.31,
        },
        {
          firstYearValue: 468753.43,
          forthYearValue: 650551.2,
          itemName: "资产总计",
          secondYearValue: 446662.68,
          thirdYearValue: 595554.31,
        }
      ],
      // 核心技术及研发技术人员
      coreTechnologyTableData:[],
      // 专利情况
      patentSituationTableData: [
        {
          country: '国内专利',
          fm: '23',
          sy: ' 1518 ',
          wg:'4',
          hj:'34',
          zb:'67'
        }, {
          country: '国外专利',
          fm: '22',
          sy: ' 1518 ',
          wg:'4',
          hj:'34',
          zb:'67'
        },
         {
          country: '合计',
          fm: '23',
          sy: ' 1518 ',
          wg:'4',
          hj:'34',
          zb:'67'
        }, {
          country: '占比',
          fm: '22',
          sy: ' 1518 ',
          wg:'4',
          hj:'34',
          zb:''
        },
      ]
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
    },
  
    //返回父组件用于锚点定位头
    getPosition() {
          let titleList = [];
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
                    noClick: false
          } 
           let yfSpending = {
                    id: 'yfSpending',
                    name: '研发投入',
                    notes: '',
                    important: false,
                    tabId: 'tab-sixth',
                    noClick: false
          } 
           let coreTechnology = {
                    id: 'coreTechnology',
                    name: '核心技术及研发技术人员',
                    notes: '',
                    important: false,
                    tabId: 'tab-sixth',
                    noClick: false
          } 
          if(this.maoChartTableData&&this.maoChartTableData.length>0){
            comparison.noClick = false;
          }             
          if(this.MajorCompetitors&&this.MajorCompetitors.length>0){
            mainCompetitors.noClick = false;
          }
          titleList.push(mainCompetitors)
          titleList.push(comparison)
          titleList.push(patentSituation)
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
