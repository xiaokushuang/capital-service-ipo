<template>
    <div class="container ipoOverview">
        <!-- 标题 -->
        <!--<el-row :gutter="20" style="margin-left:0px; margin-right:0px;height:0;">-->
            <!--<el-col :span="14">-->
                <!--<span class="no-margin" style="line-height:32px;">IPO在审项目数据统计</span>-->
            <!--</el-col>-->
            <!--<el-col :span="6">-->
            <!--</el-col>-->
        <!--</el-row>-->
        <!-- echart table -->
        <el-row :gutter="20" style="margin-left:0px; margin-right:0px;">
            <el-col :span="12">
                <div class="fullDiv_border">
                  <div style="margin-top:10px;margin-bottom:16px;">
                    <span style="display: inline-block;width:3px;height:18px;background:gray;"></span>
                    <span style="display: inline-block;font-size:18px;">IPO在审项目数据统计</span>
                  </div>
                    <chart height='100%' width='100%' id="datasetChart" :chartData = "getDataOverInfo"></chart>
                </div>
              <!-- ipo再审企业合计数变化情况 -->
              <div style="height:250px;marign-top:10px;">
                <div style="margin-top:76px;margin-bottom:16px;">
                  <span style="display: inline-block;width:3px;height:18px;background:gray;"></span>
                  <span style="display: inline-block;font-size:18px;">IPO在审企业合计数变化情况</span>
                  <el-tooltip class="ipoTip"  placement="top" effect="light">
                    <div slot="content">在审数据统计口径：<br/>
                      1、核准制：起始于拟上市企业在证监会官网预先披露，后续拟上市企业如果终止审查、<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 发审会审核未通过、获得证监会核准批复，则不在在审数据统计范围。<br/>
                      2、注册制：起始于拟上市企业在上海证券交易所受理，后续拟上市企业如果终止审查、<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 上市委审核未通过、证监会注册生效、证监会不予注册，则不在在审数据统计范围。</div>
                    <i style="cursor:pointer;color: #909399;" class="el-icon-question"></i>
                  </el-tooltip>
                </div>
                <chart2 height='100%' width='100%' id="largeScaleChart" :chartData2 = "getDataHistory"></chart2>
              </div>
            </el-col>
            <!-- 右部表 -->
            <el-col :span="12" style="padding-left:0px;padding-right:0px;">
              <div id="table1">
                <el-table :data="tableTop" border style="width: 100%" :header-cell-class-name="tableHeaderColor" :row-class-name="tableRowClassName">
                  <el-table-column label="" width="210">
                    <template slot-scope="scope">
                        <span v-html="lableTurnName(scope.row.label)"></span>
                    </template>
                  </el-table-column>
                  <el-table-column label="核准制" align="center">
                    <el-table-column align="center" label="沪主板" prop="hzbCount">
                        <template slot-scope="scope">
                            <span>{{scope.row.hzbCount}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column align="center" label="中小板" prop="zxbCount">
                        <template slot-scope="scope">
                            <span>{{scope.row.zxbCount}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column align="center" label="创业板" prop="cybCount">
                        <template slot-scope="scope">
                            <span>{{scope.row.cybCount}}</span>
                        </template>
                    </el-table-column>
                  </el-table-column>
                  <el-table-column label="注册制" align="center">
                    <el-table-column align="center" label="科创板" prop="kcCount" :class-name="tdStyle">
                        <template slot-scope="scope">
                            <span>{{scope.row.kcCount}}</span>
                        </template>
                    </el-table-column>
                  </el-table-column>
                  <el-table-column align="center" label="合计"  prop="totalAll" :class-name="tdStyle">
                    <template slot-scope="scope">
                        <span>{{scope.row.totalAll}}</span>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </el-col>
        </el-row>
      <el-row>
        <el-col :span="24">
          <div style="margin-top:10px;margin-bottom:16px;margin-left: 10px">
            <span style="display: inline-block;width:3px;height:18px;background:gray;"></span>
            <span style="display: inline-block;font-size:18px;">IPO在审企业中介机构情况</span>
          </div>
        </el-col>
      </el-row>
        <el-row :gutter="20" style="margin-left:0px; margin-right:0px;margin-top:50px;">
        <!-- 所属行业 -->
          <el-col :span="6" style="border: 1px solid transparent"></el-col>
            <el-col :span="3" style="height: 30px;line-height: 30px;text-align: right"><span>项目公司所属行业</span></el-col>
            <el-col :span="6">
              <el-multiple-selection
                ref="industryTree"
                id="industryTree"
                placeholder="证监会行业"
                @sure-click="sure"
                size="small full"
                :search-menu="true"
                node-key="name"
                :tree-data="industryList"
                :default-props="defaultProps1"
                :all-show="allShow"
                :default-all-show="false"
                :all-select="allSelect"
              ></el-multiple-selection>
            </el-col>
            <!-- 项目公司注册地 -->
            <el-col :span="3" style="height: 30px;line-height: 30px;text-align: right"><span style="margin-left:20px;">项目公司注册地</span></el-col>
            <el-col :span="6">
              <el-multiple-selection
                ref="areaTree"
                id="areaTree"
                placeholder="注册地"
                @sure-click="sure"
                size="small full"
                :search-menu="true"
                node-key="originContent"
                :tree-data="areaList"
                :default-props="defaultProps"
                :default-all-show="false"
                :all-select="allSelect"
              ></el-multiple-selection>
            </el-col>
        </el-row>
        <!-- 表格 -->
        <el-row>
        <el-col class="chart" style="padding-left: 10px;">
          <div id="table2" style="margin-top: -30px;">
            <!-- 保荐机构 -->
            <el-tabs v-model="activeName">
              <el-tab-pane label="保荐机构" name="first">
                <div>
                  <ipo-data-overview-table-show ref="ipoDataOverviewShow" :id="tabName1" :industrySelect="industrySelect" :areaSelect="areaSelect"></ipo-data-overview-table-show>
                </div>
              </el-tab-pane>
              <!-- 律师事务所 -->
              <el-tab-pane label="律师事务所" name="second">
                <div>
                  <ipo-data-overview-table-show ref="ipoDataOverviewShow" :id="tabName2" :industrySelect="industrySelect" :areaSelect="areaSelect"></ipo-data-overview-table-show>
                </div>
              </el-tab-pane>
              <!-- 会计事务所 -->
              <el-tab-pane label="会计事务所" name="third">
                <div>
                  <ipo-data-overview-table-show ref="ipoDataOverviewShow" :id="tabName3" :industrySelect="industrySelect" :areaSelect="areaSelect"></ipo-data-overview-table-show>
                </div>
              </el-tab-pane>
            </el-tabs>
          </div>
        </el-col>
        </el-row>
    </div>
</template>

<script>
import Chart from "@/components/Charts/datasetChart";
import Chart2 from "@/components/Charts/largeScaleChart";
import datepicker from "@/mixins/datepicker";
import { mapGetters } from "vuex";
import { GetDateDiff } from "@/utils";
import ipoDataOverview from "./ipoDataOverview";
import { MultidimensionalData } from "@/utils/index";
import getters from "@/store/getters";
import papers from "@/views/components-demo/papers";
import IpoDataOverviewTableShow from '@/components/IpoDataOverviewTable/ipoDataOverviewTableShow'
import ElRow from "../../../../../stock-element-ui/packages/row/src/row";
export default {
  name: "ipoDataOverview",
  mixins: [datepicker],
  components: {
    ElRow,
    Chart, Chart2, getters, papers, IpoDataOverviewTableShow },
  data() {
    return {
      activeName:'first',
      areaList:[],//地区下拉列表
      industryList:[],//行业下拉列表
      defaultProps: {
        label: 'label',
      },
      defaultProps1: {
        children: 'children',
				label: 'name'
      },
      allShow: {colloge: false,},//下拉列表全部展开或收起
      tdStyle:'tdStyle',
      tabName1:'first',
      tabName2:'second',
      tabName3:'third',
      industrySelect:'',//所属行业选中
      areaSelect:'',//所属地区选中
      allSelect:{colloge: false,},
    };
  },
  created() {//加载前默认调用
    this.getAllDropDownList();
  },
  mounted() {
    //页面加载完成时刷新echart图表
    this.ipoDataPort(true)
  },
  computed: {
    ...mapGetters([
      "getDataOverInfo",
      "getDataHistory",
    ]),
    // 右表格数据重组
    tableTop() {
      var middle = []
      this.getDataOverInfo.map((obj, idx) => {
        if(obj.label != '终止审查' && obj.label != '预披露更新' && obj.label != '已上发审会，暂缓表决') {
          if(obj.label == "已受理" || obj.label == "已受理(已受理)"){
            middle[0]= obj;
          }
          if(obj.label == "已反馈" || obj.label == "已反馈(已问询)"){
            middle[1]= obj;
          }
          if(obj.label == "预先披露更新"){
            middle[2]= obj;
          }
          if(obj.label == "已通过发审会" || obj.label == "已通过发审会(上市委会议通过)"){
            middle[3]= obj;
          }
          if(obj.label == "中止审查" || obj.label == "中止审查(中止)"){
            middle[4]= obj;
          }
          if(obj.label == "已提交发审会讨论，暂缓表决"){
            middle[5]= obj;
          }
          if(obj.label == "提交注册"){
            middle[6]= obj;
          }
          /*if(obj.label == "注册生效"){
            middle[7]= obj;
          }*/
          if(obj.label == "合计"){
            middle[7]= obj;
          }
          // middle.push(obj)
        }
      })
      return middle
    }
  },
  watch: {},
  methods: {
    // 修改table header的背景色
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if ((rowIndex === 0 && columnIndex === 2)) {
        return 'row1'
      } else if(rowIndex === 1 && columnIndex === 3) {
        return 'row2'
      } else {
        return 'rowElse'
      }
    },
    getAllDropDownList() {//获取下拉列表
      this.$store.dispatch('getAllDropDownList', '').then((data) => {//(方法名，参数)
        this.areaList = data.areaList;//所属地区
        this.industryList = MultidimensionalData(data.industryList);//所属行业
      });
    },
    lableTurnName(lable){//右侧表格名字替换
      if(lable=="已受理(已受理)"){
        lable = "已受理<span style='color:#0099cc'>(已受理)</span>";
      }
      if(lable=="已反馈(已问询)"){
        lable = "已反馈<span style='color:#0099cc'>(已问询)</span>";
      }
      if(lable=="已通过发审会(上市委会议通过)"){
        lable = "已通过发审会<span style='color:#0099cc'>(上市委会议通过)</span>";
      }
      if(lable=="中止审查(中止)"){
        lable = "中止审查<span style='color:#0099cc'>(中止)</span>";
      }
      if(lable=="提交注册"){
        lable = "<span style='color:#0099cc'>提交注册</span>";
      }
      if(lable=="注册生效"){
        lable = "<span style='color:#0099cc'>注册生效</span>";
      }
      return lable;
    },
    sure(childArr,allArr,nodekey,id){// 下拉确定
			let arr ='';
			childArr.map((obj, index)=>{
				arr += obj[nodekey] + ',';
			})
			if(id == 'areaTree') {
				this.areaSelect = arr;
			} else {
				this.industrySelect = arr;
      }
		},
    ipoDataPort() {
      this.$store.dispatch("ipoDataOverviewGet").then();
      this.$store.dispatch("ipoDataHistoryGet").then();
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === this.tableTop.length - 1) {
        return "last-row"
      }
    },
  }
};
</script>

<style>
.no-margin {
  padding-left: 10px;
}
.fullDiv_border {
  width: 100%;
  /*height: 100%;*/
  /*height: 440px;*/
  height:220px;
}
#table1 .el-table__footer-wrapper tbody td, .el-table__header-wrapper tbody td {
    background-color: #E8E8E8;
    color: rgb(51, 51, 51)!important;
}
.container .el-table .el-table__body .cell {
    padding: 5px;
}
.el-table .hjRow {
  background: #e8e8e8 !important;
  text-align: center !important;
}
/*#table1 .el-table th > .cell {
  color: rgb(51, 51, 51);
  background: #E8E8E8!important;
}*/
.container #table1 .el-table__header thead tr>th {
    padding: 5px;
    /*background: #E8E8E8!important;*/
}
/* .ipoOverview .el-table--border td, .el-table--border th {
    border-right:1px solid #DDDDDD!important;
    border-bottom:1px solid #DDDDDD!important;
  } */

.ipoOverview .row1 {
  border-left: 1px solid #14bcf5!important;
  border-top: 1px solid #14bcf5!important;
  border-bottom:none!important;
}
.container .el-table__header .row1 .cell {
  font-weight:normal;
  color:#0099cc!important;
}
.ipoOverview .row2 {
  border-left: 1px solid #14bcf5!important;
}
.container .el-table__header .row2 .cell {
  font-weight:normal;
  color:#0099cc!important;
}
.container .el-table__header .rowElse .cell {
  color:#333333 !important;
}

.el-table thead.is-group th {
  background: #E8E8E8 !important;
}
#table2 .el-table__header thead tr > th {
  height: 45px;
  color: #fff !important;
  background: #f2f2f2 !important;
}
#tab-first {
  width: 146px;
  text-align: center;
  color: #555;
  font-size: 16px;
}
#tab-second {
  width: 146px;
  text-align: center;
  color: #555;
  font-size: 16px;
}
#tab-third {
  width: 146px;
  text-align: center;
  color: #555;
  font-size: 16px;
}
.textLeft{
  text-align: left !important
}
.el-tabs__active-bar {
  width: 146px !important;
  display: none;
}
.el-tabs__header {
  width: 468px !important;
  padding-left: 15px;
  padding-right: 15px;
}

.ipoOverview .tdStyle {
  border-left:1px solid #14bcf5!important;
}
.container {
  padding:0 20px 0 0!important;
}
.el-table .last-row {
  font-weight: bold;
}
.el-select-dropdown .el-input__inner, .el-select-dropdown .el-input__suffix:hover {
    cursor: pointer;
}
[class*=" el-icon-"],[class^=el-icon-] {
    font-size: 13px;
}
  #tab-first {
    border-bottom-left-radius: 4px;
    border-top-left-radius: 4px;
  }
  #tab-third{
    border-bottom-right-radius: 4px;
    border-top-right-radius: 4px;
  }
  #table2 .el-tabs__item{
    margin: 0px;
    border: 1px solid #F5F5F5 !important;
    background: #fff;
    color: #525252;
    line-height: 9px;
    width: 100px;
    text-align: center;
    padding: 10px 5px;
    font-size: 14px !important;
    height: 30px;
  }
  #table2 .is-active{
    border: 1px solid #14bcf5 !important;
    background: #14bcf5;
    color: #fff;
  }
  .el-tabs__nav-wrap{
    height: 30px;
  }
  .el-tabs__nav-wrap::after{
    background-color: transparent;
  }
</style>

