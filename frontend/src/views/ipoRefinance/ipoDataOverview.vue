<template>
    <div>
        <!-- 标题 -->
        <el-row :gutter="20" class="no-margin-tb">
            <el-col :span="14">
                <h3 class="no-margin" style="line-height:32px;">IPO在审项目数据统计</h3>
            </el-col>
            <el-col :span="6">
            </el-col>
        </el-row>
        <!-- echart table -->
        <el-row :gutter="20">
            <el-col :span="14">
                <div class="fullDiv_border">
                    <chart height='100%' width='100%' id="datasetChart" :chartData = "getDataOverInfo"></chart>
                </div>
            </el-col>
            <el-col :span="10">
                <div id="table1" style="height:330px">
                    <el-table :data="getDataOverInfo" border style="width: 100%" max-height="400" size="medium" :row-class-name="tableRowClassName">
                        <el-table-column align="left" label="" min-width="200px">
                            <template slot-scope="scope">
                                <span>{{scope.row.label}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" label="沪主板" min-width="80px">
                            <template slot-scope="scope">
                                <span>{{scope.row.hzbCount}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" label="中小板" min-width="80px">
                            <template slot-scope="scope">
                                <span>{{scope.row.zxbCount}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" label="创业板" min-width="80px">
                            <template slot-scope="scope">
                                <span>{{scope.row.cybCount}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" label="合计" min-width="e">
                            <template slot-scope="scope">
                                <span>{{scope.row.totalAll}}</span>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
                <div style="height:250px;">
                    <chart2 height='100%' width='100%' id="largeScaleChart" :chartData2 = "getDataHistory"></chart2>
                </div>
            </el-col>
        </el-row>
        <el-row><div style="width:98%;border-bottom:1px solid #ddd;margin:0 auto;margin-bottom:10px;"></div></el-row>
        <el-row :gutter="20">
            <el-col :span="3" style="line-height:30px"><span style="margin-left:20px;">项目所属行业</span></el-col>
            <el-col :span="6">
                <el-select ref="selectCheckbox1" v-model="selectValue.industry" placeholder="请选择行业" size='small full'>
                    <el-option :label="selectValue.industry" :value="selectValue.industry">
                        <el-tree :data="getIndustry" show-checkbox node-key="id" highlight-current :props="defaultPropsIndustry"
                        @check-change="handleNodeClick1" ref="treeIndustry"></el-tree>
                    </el-option>
                    <el-col :span="24" class='selectFull'>
                        <el-button size="mini" @click="clear">清空</el-button>
                        <el-button size="mini">全选</el-button>
                        <el-button type="primary" size="mini" @click="sure">确定</el-button>        
                    </el-col>
                </el-select>
            </el-col>
            <el-col :span="3" style="line-height:30px"><span style="margin-left:20px;">项目公司注册地</span></el-col>
            <el-col :span="6">
                <el-select ref="selectCheckbox2" v-model="selectValue.areaList" placeholder="请选择注册地" size='small full'>
                    <el-option :label="selectValue.areaList" :value="selectValue.areaList">
                        <el-tree :data="getIpoQuery.areaList" show-checkbox node-key="value" highlight-current :props="defaultPropsCity"
                        @check-change="handleNodeClick2" ref="treeArea"></el-tree>
                    </el-option>
                    <el-col :span="24" class='selectFull'>
                        <el-button size="mini" @click="clear">清空</el-button>
                        <el-button size="mini">全选</el-button>
                        <el-button type="primary" size="mini" @click="sure">确定</el-button>
                    </el-col>
                </el-select>
            </el-col>
        </el-row>
        <el-col class="chart">
            <div id="table2">
                <el-tabs v-model="activeName">
                    <el-tab-pane label="保荐机构" name="first">
                        <div rightTable>
                           <el-table
                                ref="table0"
                                :data="getSponsorInstitution.data"
                                border
                                style="width:98%;margin:0 auto; border-right:1px solid #ddd;border-bottom:1px solid #ddd;">
                                <el-table-column align="center" label="序号"  type="index"  width="130px" :class-name="borderStyle"></el-table-column>
                                <el-table-column align="left" label="保荐机构" prop="label" width="512px"  :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="沪主板" prop="hzbCount" width="130px" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="中小板" prop="zxbCount" width="130px" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="创业板" prop="cybCount" width="130px" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="合计"  prop="totalCount" width="130px" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="市场比" width="130px" sortable>
                                    <template slot-scope="scope">
                                        <span>{{scope.row.percent}}%</span>
                                    </template>
                                </el-table-column>
                            </el-table>
                        </div>
                        <papers ref="declearPaper" @searchTable="search1" :total="total" :length1="length"></papers>
                    </el-tab-pane>
                    <el-tab-pane label="律师事务所" name="second">
                        <div rightTable>
                           <el-table
                                ref="table1"
                                :data="getLowOffice.data"
                                border
                                style="width:98%;margin:0 auto; border-right:1px solid #ddd;border-bottom:1px solid #ddd;">
                                <el-table-column align="center" label="序号" width="130px" type="index" :class-name="borderStyle"></el-table-column>
                                <el-table-column label="律师事务所" width="512px" prop="label" align="left" :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="沪主板" width="130px" prop="hzbCount" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="中小板" width="130px" prop="zxbCount" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="创业板" width="130px" prop="cybCount" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="合计" width="130px" prop="totalCount" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="市场比" width="130px" sortable>
                                    <template slot-scope="scope">
                                        <span>{{scope.row.percent}}%</span>
                                    </template>
                                </el-table-column>
                            </el-table>
                        </div>
                        <papers ref="declearPaper" @searchTable="search2" :total="total" :length1="length"></papers>
                    </el-tab-pane>
                    <el-tab-pane label="会计事务所" name="third">
                        <div rightTable>
                           <el-table
                                ref="table2"
                                :data="getAccountFirm.data"
                                border
                                style="width:98%;margin:0 auto; border-right:1px solid #ddd;border-bottom:1px solid #ddd;">
                                <el-table-column align="center" label="序号" width="130px" type="index" :class-name="borderStyle"></el-table-column>
                                <el-table-column label="会计事务所" width="512px" prop="label" align="left" :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="沪主板" width="130px" prop="hzbCount" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="中小板" width="130px" prop="zxbCount" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="创业板" width="130px" prop="cybCount" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="合计" width="130px" prop="totalCount" sortable :class-name="borderStyle"></el-table-column>
                                <el-table-column align="center" label="市场比" width="130px" sortable>
                                    <template slot-scope="scope">
                                        <span>{{scope.row.percent}}%</span>
                                    </template>
                                </el-table-column>
                            </el-table>
                        </div>
                        <papers ref="declearPaper" @searchTable="search3" :total="total" :length1="length"></papers>
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
      typeId: "",
      lawData: "",
      length:20,
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
      borderStyle: "borderStyle"
    };
  },
  mounted() {
    //页面加载完成时刷新echart图表
    this.ipoDataPort(true);
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
      "declearPaper"
    ]),
    getAreaList(getProjectBelong) {
      let arr = this.getProjectBelong;
      let city = MultidimensionalData(arr.areaList);
      return city;
    },
    getIndustry(getProjectBelong) {
      let arr = this.getProjectBelong;
      let industry = MultidimensionalData(arr.industrySelectList);
      return industry;
    }
  },
  watch: {},
  methods: {
    search1(data) {
      // console.log("获取table数据", data);
      this.param1 = {
        startRow: data.fromPaper - 1,
        pageSize: data.length,
        orderColumn: "percent",
        orderByOrder: "desc",
        condition: {
          industry: this.selectValue.industry,
          registAddr: this.selectValue.areaList
        }
      };
      this.$store.dispatch("sponsorInstitutionGet", this.param1).then(() => {
        this.lawData = this.getSponsorInstitution.data
        this.total = this.getSponsorInstitution.total
      }).catch(err => {});
    },
    search2(data) {
      // console.log("获取table数据", data);
      this.param2 = {
        startRow: data.fromPaper - 1,
        pageSize: data.length,
        orderColumn: "percent",
        orderByOrder: "desc",
        condition: {
          industry: this.selectValue.industry,
          registAddr: this.selectValue.areaList
        }
      };
      this.$store.dispatch("lawOfficeGet", this.param2).then(() => {
        this.lawData = this.lawOfficeGet.data
        this.total = this.lawOfficeGet.total
      }).catch(err => {});
    },
    search3(data) {
      // console.log("获取table数据", data);
      this.param3 = {
        startRow: data.fromPaper - 1,
        pageSize: data.length,
        orderColumn: "percent",
        orderByOrder: "desc",
        condition: {
          industry: this.selectValue.industry,
          registAddr: this.selectValue.areaList
        }
      };
      this.$store.dispatch("accountFirmGet", this.param3).then(() => {
        this.lawData = this.accountFirmGet.data
        this.total = this.accountFirmGet.total
      }).catch(err => {});
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
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === this.getDataOverInfo.length - 1) {
        return "hjRow";
      }
      return "";
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
        //   console.log(this.getSponsorInstitution.total)
        // this.lawData = this.param1.data.lawRule.slice(0, 10);
        this.total = this.getSponsorInstitution.total
        // this.typeId = param.typeId;
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
  background: #e8e8e8;
  color: #333;
  border-right: 1px solid #ddd;
}
#table1 .el-table--enable-row-transition .el-table__body td {
  height: 30px;
  line-height: 30px;
  border-right: 1px solid #ddd;
}
.el-table .hjRow {
  background: #e8e8e8 !important;
  text-align: center !important;
}
.el-table thead.is-group th {
  background: #a0adb5 !important;
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
.el-tabs__active-bar {
  width: 146px !important;
}
.el-tabs__header {
  width: 438px !important;
}
</style>

