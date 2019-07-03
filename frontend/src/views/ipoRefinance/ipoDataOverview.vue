<template>
    <div class="container">
        <!-- 标题 -->
        <el-row :gutter="20" style="margin-left:0px; margin-right:0px;">
            <el-col :span="14">
                <h3 class="no-margin" style="line-height:32px;">IPO在审项目数据统计</h3>
            </el-col>
            <el-col :span="6">
            </el-col>
        </el-row>
        <!-- echart table -->
        <el-row :gutter="20" style="margin-left:0px; margin-right:0px;">
            <el-col :span="12">
                <div class="fullDiv_border">
                    <chart height='100%' width='100%' id="datasetChart" :chartData = "getDataOverInfo"></chart>
                </div>
            </el-col>
            <!-- 右部表 -->
            <el-col :span="12">
              <div id="table1">
                <el-table :data="tableTop" border show-summary style="width: 100%">
                  <el-table-column label="" min-width="210px">
                    <template slot-scope="scope">
                        <span v-html="lableTurnName(scope.row.label)"></span>
                    </template>
                  </el-table-column>
                  <el-table-column label="核准制" align="center" min-width="210px" :render-header="renderHeader">
                    <el-table-column align="center" label="沪主板" min-width="80px" prop="hzbCount">
                        <template slot-scope="scope">
                            <span>{{scope.row.hzbCount}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column align="center" label="中小板" min-width="80px" prop="zxbCount">
                        <template slot-scope="scope">
                            <span>{{scope.row.zxbCount}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column align="center" label="创业板" min-width="80px" prop="cybCount" :class-name="abc">
                        <template slot-scope="scope">
                            <span>{{scope.row.cybCount}}</span>
                        </template>
                    </el-table-column>
                  </el-table-column>
                  <el-table-column label="注册制" align="center" min-width="210px" :class-name="abc">
                    <el-table-column align="center" label="科创板" min-width="80px" prop="kcCount" :class-name="abc">
                        <template slot-scope="scope">
                            <span>{{scope.row.kcCount}}</span>
                        </template>
                    </el-table-column>
                  </el-table-column>
                  <el-table-column align="center" label="合计" prop="totalAll">
                    <template slot-scope="scope">
                        <span>{{scope.row.totalAll}}</span>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
              <!-- ipo再审企业合计数变化情况 -->
              <div style="height:250px;marign-top:10px;">
                  <chart2 height='100%' width='100%' id="largeScaleChart" :chartData2 = "getDataHistory"></chart2>
              </div>
            </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-left:0px; margin-right:0px;">
            <el-col :span="24">
              <div style="width:98%;border-bottom:1px solid #ddd;margin-top:20px;margin-bottom:30px;"></div>
            </el-col>
        <!-- 所属行业 -->
            <el-col :span="3" style="line-height:30px"><span style="margin-left:20px;">项目所属行业</span></el-col>
            <el-col :span="6">
              <el-multiple-selection
                ref="industryTree"
                id="industryTree"
                placeholder="所属行业"
                @sure-click="sure"
                size="small full"
                :search-menu="true"
                node-key="id"
                :tree-data="industryList"
                :default-props="defaultProps1"
                :all-show="allShow"
                :default-all-show="false"
              ></el-multiple-selection>
            </el-col>
            <!-- 项目公司注册地 -->
            <el-col :span="3" style="line-height:30px"><span style="margin-left:20px;">项目公司注册地</span></el-col>
            <el-col :span="6">
              <el-multiple-selection
                ref="areaTree"
                id="areaTree"
                placeholder="所属地区"
                @sure-click="sure"
                size="small full"
                :search-menu="true"
                node-key="id"
                :tree-data="areaList"
                :default-props="defaultProps"
                :default-all-show="false"
              ></el-multiple-selection>
            </el-col>
        </el-row>
        <!-- 表格 -->
        <el-col class="chart">
            <div id="table2">
              <!-- 保荐机构 -->
                <el-tabs v-model="activeName">
                    <el-tab-pane label="保荐机构" name="first">
                        <div rightTable>
                           <el-table
                                ref="table0"
                                :data="table1"
                                border
                                style="width:98%;margin:0 auto; border-right:1px solid #ddd;border-bottom:1px solid #ddd;">
                                <el-table-column align="center" label="序号" prop="num"  min-width="130px" :class-name="borderStyle"></el-table-column>
                                <el-table-column label="保荐机构" prop="label" min-width="512px"  :class-name="borderStyleText"></el-table-column>
                                <el-table-column align="center" label="沪主板" prop="hzbCount" min-width="130px" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="中小板" prop="zxbCount" min-width="130px" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="创业板" prop="cybCount" min-width="130px" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="合计"  prop="totalCount" min-width="130px" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="市场比" min-width="130px" sortable>
                                    <template slot-scope="scope">
                                        <span>{{scope.row.percent}}%</span>
                                    </template>
                                </el-table-column>
                            </el-table>
                        </div>
                        <papers ref="declearPaper1" @searchTable="search1" :total="getTotalFloor.total1" :pageSize1="pageSize" style="width:100%"></papers>
                    </el-tab-pane>
                    <!-- 律师事务所 -->
                    <el-tab-pane label="律师事务所" name="second">
                        <div rightTable>
                           <el-table
                                ref="table1"
                                :data="table2"
                                border
                                style="width:98%;margin:0 auto; border-right:1px solid #ddd;border-bottom:1px solid #ddd;">
                                <el-table-column align="center" label="序号" min-width="130px" prop="num" :class-name="borderStyle"></el-table-column>
                                <el-table-column label="律师事务所" min-width="512px" prop="label" align="left":class-name="borderStyleText"></el-table-column>
                                <el-table-column align="center" label="沪主板" min-width="130px" prop="hzbCount" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="中小板" min-width="130px" prop="zxbCount" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="创业板" min-width="130px" prop="cybCount" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="合计" min-width="130px" prop="totalCount" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="市场比" min-width="130px" sortable>
                                    <template slot-scope="scope">
                                        <span>{{scope.row.percent}}%</span>
                                    </template>
                                </el-table-column>
                            </el-table>
                        </div>
                        <papers ref="declearPaper2" @searchTable="search2" :total="getTotalFloor.total2" :pageSize1="pageSize" style="width:100%"></papers>
                    </el-tab-pane>
                    <!-- 会计事务所 -->
                    <el-tab-pane label="会计事务所" name="third">
                        <div rightTable>
                           <el-table
                                ref="table2"
                                :data="table3"
                                border
                                style="width:98%;margin:0 auto; border-right:1px solid #ddd;border-bottom:1px solid #ddd;">
                                <el-table-column align="center" label="序号" width="130px" type="index" :class-name="borderStyle"></el-table-column>
                                <el-table-column label="会计事务所" min-width="512px" prop="label" align="left" :class-name="borderStyleText"></el-table-column>
                                <el-table-column align="center" label="沪主板" min-width="130px" prop="hzbCount" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="中小板" min-width="130px" prop="zxbCount" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="创业板" min-width="130px" prop="cybCount" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="合计" min-width="130px" prop="totalCount" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="市场比" min-width="130px" sortable>
                                    <template slot-scope="scope">
                                        <span>{{scope.row.percent}}%</span>
                                    </template>
                                </el-table-column>
                            </el-table>
                        </div>
                        <papers ref="declearPaper3" @searchTable="search3" :total="getTotalFloor.total3" :pageSize1="pageSize" style="width:100%"></papers>
                    </el-tab-pane>
                </el-tabs>
            </div>
        </el-col>
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
export default {
  name: "ipoDataOverview",
  mixins: [datepicker],
  components: { Chart, Chart2, getters, papers },
  data() {
    return {
      total: 0,
      total1: 0,
      total2: 0,
      total3: 0,
      typeId: "",
      lawData: "",
      pageSize: 20,
      activeName: "first",
      defaultPropsIndustry: {
        children: "children",
        label: "name"
      },
      defaultPropsCity: {
        label: "label",
        value: "value"
      },
      tableData: [],
      selectValue: {
        areaList: "",
        industry: "",
        industry_check_0: "",
        industry_check_1: ""
      },
      param1: {
        startRow: 0,
        pageSize: 20,
        condition: {},
        orderColumn: "",
        orderByOrder: ""
      },
      param2: {
        startRow: 0,
        pageSize: 20,
        condition: {},
        orderColumn: "",
        orderByOrder: ""
      },
      param3: {
        startRow: 0,
        pageSize: 20,
        condition: {},
        orderColumn: "",
        orderByOrder: ""
      },
      borderStyle: "borderStyle",
      borderStyleText: "borderStyleText",
      areaList:[],
      industryList:[],
      defaultProps: {
        label: 'label',
      },
      defaultProps1: {
        children: 'children',
				label: 'name'
      },
      allShow: {colloge: false,},//下拉列表全部展开或收起
      abc:'abc',
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
      "getProjectBelong",
      "getSponsorInstitution",
      "getLowOffice",
      "getAccountFirm",
      "getIpoQuery",
      "declearPaper",
      "getTotalFloor"
    ]),
    // 保荐机构数据重组加入字段Num
    table1() {
      var middle1 = [];
      if (Object.prototype.toString.call(this.getSponsorInstitution.data) === '[object Array]') {
        this.getSponsorInstitution.data.map((obj, idx) => {
          var valuec = {};
          var data1 = Object.keys(obj);
          data1.map((o,i)=>{
            valuec[o]=obj[o]
          });
          valuec.num = idx+1;
          middle1.push(valuec);
        });
        return middle1;
      }
    },
    // 律师事务所数据重组加入字段Num
    table2() {
      var middle2 = [];
      if (Object.prototype.toString.call(this.getLowOffice.data) === '[object Array]') {
        console.log(this.getLowOffice.data)
        this.getLowOffice.data.map((obj, idx) => {
          var valued = {};
          var data2 = Object.keys(obj);
          data2.map((o,i)=>{
            valued[o]=obj[o]
          });
          valued.num = idx+1;
          middle2.push(valued);
        });
        return middle2;
      }
    },
    // 会计事务所数据重组加入字段Num
    table3() {
      var middle3 = [];
      if (Object.prototype.toString.call(this.getAccountFirm.data) === '[object Array]') {
        var getAccountFirm = this.getAccountFirm.data;
        getAccountFirm.map((obj, idx) => {
          var valuee = {};
          var data3 = Object.keys(obj);
          data3.map((o,i)=>{
            valuee[o]=obj[o]
          });
          valuee.num = idx+1;
          middle3.push(valuee);
        });
        return middle3;
      }
    },
    // // 所属地区数据重组
    // getAreaList(getProjectBelong) {
    //   let arr = this.getProjectBelong;
    //   let city = MultidimensionalData(arr.areaList);
    //   return city;
    // },
    //  // 所属行业数据重组
    // getIndustry(getProjectBelong) {
    //   let arr = this.getProjectBelong;
    //   let industry = MultidimensionalData(arr.industrySelectList);
    //   return industry;
    // },
    // 右表格数据重组
    tableTop() {
      var middle = []
      this.getDataOverInfo.map((obj, idx) => {
        if(obj.label != '终止审查' && obj.label != '预披露更新' && obj.label != '已上发审会，暂缓表决' && obj.label != '合计') {
          middle.push(obj)
        }
      })
      return middle
    }
  },
  watch: {},
  methods: {
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
      if(lable=="注册生效"){
        lable = "<span style='color:#0099cc'>注册生效</span>";
      }
      if(lable=="提交注册"){
        lable = "<span style='color:#0099cc'>提交注册</span>";
      }
      return lable;
    },
    renderHeader(h,{column}) {
      debugger;
      return h(
   'div',
   [ 
   h('span', column.label , {
    style:'color:#409eff;'
   }),
   ],
  );
  },
    // 保荐机构tab1 数据
    search1(data) {
      // console.log("获取table数据", data);
      this.param1 = {
        startRow: data.startRow - 1,
        pageSize: data.pageSize,
        orderColumn: "percent",
        orderByOrder: "desc",
        condition: {
          industry: this.selectValue.industry,
          registAddr: this.selectValue.areaList
        }
      };
      this.$store.dispatch("sponsorInstitutionGet", this.param1)
    },
    // 律师事务所tab2 数据
    search2(data) {
      // console.log("获取table数据", data);
      this.param2 = {
        startRow: data.startRow - 1,
        pageSize: data.pageSize,
        orderColumn: "percent",
        orderByOrder: "desc",
        condition: {
          industry: this.selectValue.industry,
          registAddr: this.selectValue.areaList
        }
      };
      this.$store.dispatch("lawOfficeGet", this.param2)
    },
    // 会计事务所tab3 数据
    search3(data) {
      // console.log("获取table数据", data);
      this.param3 = {
        startRow: data.startRow - 1,
        pageSize: data.pageSize,
        orderColumn: "percent",
        orderByOrder: "desc",
        condition: {
          industry: this.selectValue.industry,
          registAddr: this.selectValue.areaList
        }
      };
      this.$store.dispatch("accountFirmGet", this.param3)
    },
    clear() {
      //下拉清空
      // console.log(this.$refs.treeIndustry)
      this.$refs.treeIndustry.setCheckedKeys([]); //清空选中
      this.$refs.treeArea.setCheckedKeys([]); //清空选中
    },
    sure() {
      this.selectValue = {
        areaList: this.selectValue.areaList,
        industry: this.selectValue.industry
      };
      // console.log(this.selectValue);
      this.param1 = {
        startRow: 0,
        pageSize: 20,
        orderColumn: "percent",
        orderByOrder: "desc",
        condition: {
          industry: this.selectValue.industry,
          registAddr: this.selectValue.areaList
        }
      };
      this.$store.dispatch("sponsorInstitutionGet", this.param1).then();
      this.param2 = {
        startRow: 0,
        pageSize: 20,
        orderColumn: "percent",
        orderByOrder: "desc",
        condition: {
          industry: this.selectValue.industry,
          registAddr: this.selectValue.areaList
        }
      };
      this.$store.dispatch("lawOfficeGet", this.param2).then();
      this.param3 = {
        startRow: 0,
        pageSize: 20,
        orderColumn: "percent",
        orderByOrder: "desc",
        condition: {
          industry: this.selectValue.industry,
          registAddr: this.selectValue.areaList
        }
      };
      this.$store.dispatch("accountFirmGet", this.param3).then();

      this.$refs.selectCheckbox1.handleClose(); //关闭下拉框
      this.$refs.selectCheckbox2.handleClose(); //关闭下拉框
    },
    tableRowClassName({ row, column, rowIndex, columnIndex }) {
      debugger;
      // if (rowIndex === this.tableTop.length - 1) {
      //   return "hjRow";
      // }
      return "hjRow";
    },
    ipoDataPort() {
      this.$store.dispatch("ipoDataOverviewGet").then();
      this.$store.dispatch("ipoDataHistoryGet").then();
      this.$store.dispatch("projectBelongGet").then();
      this.$store.dispatch("ipoQueryGet").then();
      var param1 = {
        startRow: 0,
        pageSize: 20,
        condition: {}
      };
      this.$store.dispatch("sponsorInstitutionGet", param1).then(() => {
        this.total = this.getSponsorInstitution.total
      });
      var param2 = {
        startRow: 0,
        pageSize: 20,
        condition: {}
      };
      this.$store.dispatch("lawOfficeGet", this.param2).then();
      this.param3 = {
        startRow: 0,
        pageSize: 20,
        condition: {}
      };
      this.$store.dispatch("accountFirmGet", this.param3).then();
    },
    // 下拉菜单注册地
    handleNodeClick2(data, node, component) {
      //共三个参数，依次为：传递给 data 属性的数组中该节点所对应的对象、节点本身是否被选中、节点的子树中是否有被选中的节点
      const nodeCheck = this.$refs.treeArea.getCheckedNodes(true); //通过 node 获取(光子节点)
      //   console.log(nodeCheck)
      let middle = "";
      nodeCheck.map((obj, idx) => {
        //拼接字符串
        middle += `,${obj.label}`;
      });
      this.selectValue.areaList = middle.substr(1); //设置input里显示的文字，可扩展
    },
    handleNodeClick1(data, node, component) {
      //共三个参数，依次为：传递给 data 属性的数组中该节点所对应的对象、节点本身是否被选中、节点的子树中是否有被选中的节点
      const nodeCheck = this.$refs.treeIndustry.getCheckedNodes(true); //通过 node 获取(光子节点)
      let middle = "";
      nodeCheck.map((obj, idx) => {
        //拼接字符串
        middle += `,${obj.name}`;
      });
      this.selectValue.industry = middle.substr(1); //设置input里显示的文字，可扩展
    }
  }
};
</script>

<style>
.no-margin {
  padding-left: 20px;
}
.h100 {
  height: 100%;
  min-height: 500px;
}
.h100 > div.chart {
  height: 100%;
  min-height: 500px;
}
.fullDiv_border {
  width: 100%;
  height: 100%;
  height: 500px;
}
.list {
  line-height: 38px;
}
.list a {
  padding: 5px 20px;
  font-size: 14px;
}
.list a.active {
  color: #0285ac;
}
#table1 .el-table__header thead tr > th {
  padding: 0px;
  height: 30px;
  background: #E8E8E8!important;
  color: #333;
  border-right: 1px solid #DDDDDD;
}
#table1 .el-table--enable-row-transition .el-table__body td {
  height: 31px;
  line-height: 31px;
  border-right: 1px solid #DDDDDD;
  border-bottom: 1px solid #DDDDDD;
}
#table1 .el-table th > .cell {
  color: rgb(51, 51, 51);
  background: #E8E8E8!important;
}
.chart .el-table--enable-row-transition .el-table__body td {
  height: 41px;
  line-height: 41px;
  border-right: 1px solid #ddd;
  border-bottom: 1px solid #ddd !important;
}
.el-table .hjRow {
  background: #e8e8e8 !important;
  text-align: center !important;
}
.el-table thead.is-group th {
  background: #E8E8E8 !important;
}
#table2 .el-table__header thead tr > th {
  height: 45px;
  color: #fff !important;
  background: #a0adb5 !important;
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
.borderStyle {
  border-right: 1px solid #ddd !important;
}
.borderStyleText {
  border-right: 1px solid #ddd !important;
  text-align: left !important;
}
.textLeft{
  text-align: left !important
}
.el-tabs__active-bar {
  width: 146px !important;
}
.el-tabs__header {
  width: 438px !important;
}
.abc {
  border-left-color: #14bcf5!important;
  border-right-color: #14bcf5!important;
}
</style>

