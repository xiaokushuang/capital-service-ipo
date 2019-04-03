<template>
  <div class="finance">
    <!-- 标题 -->
    <el-row :gutter="20" class="no-margin-tb">
        <el-col :span="14">
            <h3 class="no-margin">债券发行图</h3>
        </el-col>
        <el-col :span="6">
        </el-col>
    </el-row>
    <!-- 时间选项 -->
    <el-row class="list">
        <el-col :span="2">
            <span>时间选择：</span>
        </el-col>
        <el-col :span="12" id="listA">
            <a @click="activeFun(0)" :class="{active:flag==0}" href="javascript:void(0)">近半年</a>
            <a @click="activeFun(1)" :class="{active:flag==1}" href="javascript:void(0)">近一年</a>
            <a @click="activeFun(2)" :class="{active:flag==2}" href="javascript:void(0)">近三年</a>
            <a @click="activeFun(3)" :class="{active:flag==3}" href="javascript:void(0)">近五年</a>
            <a @click="activeFun(4)" :class="{active:flag==4}" href="javascript:void(0)">近十年</a>
        </el-col>
        <!-- 双日历 -->
        <el-col :span="6">
            <el-date-picker
                v-model="value5"
                type="daterange"
                align="right"
                unlink-panels
                size="small"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :picker-options="pickerOptions2">
            </el-date-picker>
        </el-col>
    </el-row>
    <!-- 图表部分 -->
    <el-row :gutter="20" class="h100">
        <!-- 图表 -->
        <el-col :span="16" class="chart">
            <div class="fullDiv_border">
                <chart height='100%' width='100%' :chartData = "getBondIpo1"></chart>
            </div>
        </el-col>
        <!-- 右侧选项卡和table -->
        <el-col :span="8" class="chart">
            <div class="fullDiv_border">
                <div rightTable>
                    <el-table
                        ref="table0"
                        :data="tableData"
                        max-height="440"
                        style="width: 100%">
                        <el-table-column align="center" label="日期" min-width="190px">
                            <template slot-scope="scope">
                                <span>{{scope.row.date}}</span>
                            </template>
                        </el-table-column>

                        <el-table-column align="center" label="金额（亿元）"  min-width="120px">
                            <template slot-scope="scope">
                                <span v-if="scope.row.dataSum.length==0">0.0000</span>
                                <span v-else v-for='(o,i) in scope.row.dataSum' :key="i">
                                    <span v-if="o.name=='004'">{{o.value}}</span>
                                </span>
                            </template>
                        </el-table-column>

                        <el-table-column align="center" label="数量"  min-width="60px">
                            <template slot-scope="scope">
                                <span v-if="scope.row.dataSum.length==0">0</span>
                                <span v-else v-for='(o,i) in scope.row.dataSum' :key="i">
                                    <span v-if="o.name=='004'">{{o.num}}</span>
                                </span>
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
import Chart from "@/components/Charts/securitiesBondChart";
import datepicker from "@/mixins/datepicker";
import { mapGetters } from "vuex";
import { GetDateDiff } from "@/utils";
import chartBondOne from "./chartBondOne";
import common from '@/mixins/common'
export default {
  name: "chartBoxOne",
  mixins: [datepicker,common],
  components: { Chart },
  data() {
    return {
      value5: "",
      flag: 1,
      activeName: "first",
      param: {
        countType: "",
        chartType: "",
        type: "",
        dateSelect: ""
      },
      tableData: []
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
    //点击年度选项事件
    activeFun(data) {
      this.flag = data; //选中样式
      //给chart换数据
      this.param.countType = this.flag;
      this.param.dateSelect = "";
      this.value5 = "";
      this.chartOne(false);
    },
    //选项卡点击触发事件
    handleClick(tab, event) {
      //console.log(tab.index, event);
      this.$nextTick(() => {
        //选项卡动作结束后，从新计算table列宽
        this.$refs[`table${tab.index}`].doLayout();
      });
    },
    //ajax请求数据
    chartOne(flag) {
        if (this.$route.query != "undefined") {
            if (flag) {
                this.param.countType = 1;
                this.param.chartType = 1;
                this.param.type = 'ipoBondData1';
            }
            this.$store.dispatch("ipoBondGet", this.param).then(() => {});
        }
    }
  },
  computed: {
    ...mapGetters(["getBondIpo1"]),
    // dataAll() {
    //   //全部选项过滤掉不带有003的选项
    //   var datas = [];
    //   this.tableData.map((obj, idx) => {
    //     if (obj.dataSum.length != 0) {
    //       var a = false;
    //       obj.dataSum.map((o, i) => {
    //         if (o.name == "003" && o.value && o.num) {
    //           a = true;
    //         }
    //       });
    //       if (a) {
    //         datas.push(obj);
    //       }
    //     }
    //   });
    //   return datas;
    // }
  },
  mounted() {
    //页面加载完成时刷新echart图表
    this.chartOne(true);
  },
  watch: {
    value5(n, o) {
      //依照操作取数据
      if (this.getValue(n) == '') {//清空时间
        if(this.flag == 7) {
          this.flag = 1;
          this.dateSelect = "";
          this.chartOne(true);
          for(let i =0; i< document.getElementById('listA').getElementsByTagName('a').length;i++) {
            if (document.getElementById('listA').getElementsByTagName('a')[i].classList.contains("active") === false) {
              document.getElementById('listA').getElementsByTagName('a')[1].classList.add("active")
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
        if (flg >= 31) {
          this.param.dateSelect = start + " 至 " + end;
          // console.log(this.param)
          this.chartOne(false);
          for(let i =0; i< document.getElementById('listA').getElementsByTagName('a').length;i++) {
            if (document.getElementById('listA').getElementsByTagName('a')[i].classList.contains("active") === true) {
              document.getElementById('listA').getElementsByTagName('a')[i].classList.remove("active")
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
    getBondIpo1(n, o) {
      //   console.log('getIpo变了')
      //   console.log(n)
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
</style>
