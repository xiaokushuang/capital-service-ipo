<template>
  <div class="finance">
    <!-- 标题 -->
    <el-row :gutter="20" class="no-margin-tb">
        <el-col :span="14">
            <h3 class="no-margin" style="margin-top:10px !important;">债券发行行业分布图</h3>
        </el-col>
        <el-col :span="6">
        </el-col>
    </el-row>
    <!-- 时间选项 -->
    <el-row class="list">
        <el-col :span="2">
            <span>时间选择：</span>
        </el-col>
        <el-col :span="11" id="listB">
            <a @click="activeFun(5)" :class="{active:flag==5}" href="javascript:void(0)">近一个月</a>
            <a @click="activeFun(6)" :class="{active:flag==6}" href="javascript:void(0)">近三个月</a>
            <a @click="activeFun(0)" :class="{active:flag==0}" href="javascript:void(0)">近半年</a>
            <a @click="activeFun(1)" :class="{active:flag==1}" href="javascript:void(0)">近一年</a>
            <a @click="activeFun(2)" :class="{active:flag==2}" href="javascript:void(0)">近三年</a>
        </el-col>
        <!-- 双日历 -->
        <el-col :span="6" style="padding-right:10px;">
            <el-date-picker
                v-model="value5"
                type="daterange"
                unlink-panels
                size="small"
                value-format="yyyy-MM-dd"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :picker-options="pickerOptions2">
            </el-date-picker>
        </el-col>
        <el-col :span='5' style="padding-left:10px;">
            <el-select v-model="code_value" placeholder="" size='small full' @change="selectClass">
              <template v-for="item in getSFClass">
                <el-option
                    :key="item.code_value"
                    :label="item.code_name"
                    :value="item.code_value">
                </el-option>
              </template>
            </el-select>
        </el-col>
    </el-row>
    <!-- 图表部分 -->
    <el-row :gutter="20" class="h100">
        <!-- 图表 -->
        <el-col :span="16" class="chart" style="position:relative">
            <div class="fullDiv_border">
                <chart height='100%' width='100%' id="two" :chartData = "getBondIpo2"></chart>
            </div>
        </el-col>
        <!-- 右侧选项卡和table -->
        <el-col :span="8" class="chart">
            <div class="fullDiv_border">
              <div rightTable rightTable1>
                  <el-table
                      ref="table0"
                      id="tableLine"
                      :data="data0"
                      max-height="485"
                      style="width: 100%">
                      <el-table-column align="left" label="行业" min-width="40%">
                          <template slot-scope="scope">
                              <span :title="scope.row.name" v-if="scope.row.name.length > 8">{{scope.row.name.substring(0,8) + '...'}}</span>
                              <!-- <span :title="scope.row.name" v-else>{{scope.row.name}}</span> -->
                              <a :title="scope.row.name" v-else @click="companySel(scope.row,'004')">{{scope.row.name}}</a>
                          </template>
                      </el-table-column>

                      <el-table-column align="center" label="金额（亿元）"  min-width="40%">
                          <template slot-scope="scope">
                              <span v-if="scope.row.value.length==0">0.0000</span>
                              <!-- <span>{{scope.row.value}}</span> -->
                              <a @click="companySel(scope.row,'004')">{{scope.row.value}}</a>
                          </template>
                      </el-table-column>
                      
                      <el-table-column align="center" label="数量"  min-width="20%">
                          <template slot-scope="scope">
                              <span v-if="scope.row.num.length==0">0</span>
                              <a @click="companySel(scope.row,'004')">{{scope.row.num}}</a>
                          </template>
                      </el-table-column>
                  </el-table>     
              </div>
            </div>
        </el-col>
    </el-row>
  </div>
</template>

<script>
import Chart from "@/components/Charts/securitiesBondChartTwo";
import datepicker from "@/mixins/datepicker";
import { mapGetters } from "vuex";
import { GetDateDiff } from "@/utils";
import chartBondTwo from "./chartBondTwo";
import common from '@/mixins/common'
export default {
  name: "chartBoxTwo",
  mixins: [datepicker,common],
  components: { Chart },
  data() {
    return {
      value5: "",
      code_value: "008",
      flag: 1,
      activeName: "first",
      param: {
        countType: "",
        chartType: "",
        type: "",
        finaType: "",
        dateSelect: "",
        industrySelect: ""
      },
      tableData: [],
      options: [],
      arr: ["001", "002", "003"],
      titleName:'债券发行'
    };
  },
  props: {
    chose: {
      type: Number,
      default: 0
    }
  },
  filters: {
    andDate(value) {
      //全部选项合计过滤器
      if (!value) return "";
      //console.log(value)
      var str = 0;
      value.map((obj, idx) => {
        str += obj.value;
      });
      return parseFloat(str).toFixed(4);
    }
  },
  methods: {
    companySel(row,finaType) {//打开公司详情页
      this.companyDetailShow("2",this.titleName,finaType,row.name,row.condition,this.code_value,"债券发行");
    },
    selectClass(val) {
      this.param.countType = this.flag;
      this.param.chartType = 2;
      this.param.industrySelect = val;
      this.param.type = "ipoBondData2";
      this.$store.dispatch("ipoBondGet", this.param).then()
    },
    //点击年度选项事件
    activeFun(data) {
      this.flag = data; //选中样式
      // 给chart换数据
      this.param.countType = this.flag;
      this.param.industrySelect = this.code_value;
      this.value5 = "";
      this.chartTwo(false);
    },
    //选项卡点击触发事件
    handleClick(tab, event) {
      // console.log(tab.index, event);
      this.$nextTick(() => {
        //选项卡动作结束后，从新计算table列宽
        this.$refs[`table${tab.index}`].doLayout();
      });
    },
    //ajax请求数据
    chartTwo(flag) {
      if (this.$route.query != "undefined") {
        if (flag) {
          this.param.countType = 1;
          this.param.chartType = 2;
          this.param.industrySelect = this.code_value;
          this.param.type = "ipoBondData2";
        }
        this.$store.dispatch("ipoBondGet", this.param).then(() => {});
      }
    },
    // 分类
    classGet() {
      this.$store.dispatch("ipoSFClassGet").then();
    },
  },
  computed: {
    ...mapGetters(["getBondIpo2", "getSFClass"]),
    data0() {
      return this.tableData.length > 0 ? this.tableData[0].dataSum : [];
    },
    data2() {
      return this.tableData.length > 0 ? this.tableData[2].dataSum : [];
    },
    data3() {
      return this.tableData.length > 0 ? this.tableData[3].dataSum : [];
    },
    data4() {
      return this.tableData.length > 0 ? this.tableData[4].dataSum : [];
    }
  },
  mounted() {
    //页面加载完成时刷新echart图表
    this.chartTwo(true);
    this.classGet(true)
  },
  watch: {
    value5(n, o) {//改变时间时,监听事件,判断搜索日期大于一个月
      //依照操作取数据
      if (this.getValue(n) == '') {//清空时间
        if(this.flag == 7) {
          this.dateSelect = "";
          this.flag = 1;
          this.chartTwo(true);
          for (let i = 0; i < document.getElementById("listB").getElementsByTagName("a").length; i++) {
            if (document.getElementById("listB").getElementsByTagName("a")[i].classList.contains("active") === false) {
              document.getElementById("listB").getElementsByTagName("a")[3].classList.add("active");
            }
          }
          return false;
        }
      } else {
        var d = new Date(n[0]);
        const f = new Date(n[1]);
        const start =
          d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate(); // + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
        const end =
          f.getFullYear() + "-" + (f.getMonth() + 1) + "-" + f.getDate(); // + ' ' + f.getHours() + ':' + f.getMinutes() + ':' + f.getSeconds();
        const flg = GetDateDiff(start, end, "day");
        this.param.countType = 7;
        this.flag = 7;
        // this.param.industrySelect = "001";
        if (flg >= 31) {
          this.param.dateSelect = start + " 至 " + end;
          // console.log(this.param)
          this.chartTwo(false);
          for (
            let i = 0;
            i <
            document.getElementById("listB").getElementsByTagName("a").length;
            i++
          ) {
            if (
              document
                .getElementById("listB")
                .getElementsByTagName("a")
                [i].classList.contains("active") === true
            ) {
              document
                .getElementById("listB")
                .getElementsByTagName("a")
                [i].classList.remove("active");
            }
          }
        } else {
          // this.$message({
          //   message: `统计范围应大于一个月,您现在的时间范围为${flg}天`,
          //   type: "warning"
          // });
          this.popAlert('统计范围应大于一个月');
        }
      }
    },
    getBondIpo2(n, o) {
      //数据变化时更新chart
      this.tableData = n;
    }
  }
};
</script>

<style scoped>
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
  border: 1px solid #eee;
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

.singleBtn,
.areaBtn {
  padding: 5px 10px;
  background: #0085a7;
  color: #fff;
  border: 1px solid #0085a7;
  cursor: pointer;
}

.btnGroup .singleBtn:first-child,
.areaBtn:first-child {
  border-radius: 2px 0px 0px 2px;
}
.btnGroup .singleBtn:nth-child(2),
.areaBtn:nth-child(2) {
  border-right: 1px solid #d5d5d5;
  border-left: 1px solid #d5d5d5;
}
.btnGroup .singleBtn:last-child,
.areaBtn:last-child {
  border-radius: 0px 2px 2px 0px;
}

.clickSpan {
  background: #f2f1ef;
  color: #333333;
  border: 1px solid #d5d5d5;
}
</style>
<style scoped lang="scss">
#tableLine tr:hover{
  .cell a{
    text-decoration: underline;
  }
}
</style>
