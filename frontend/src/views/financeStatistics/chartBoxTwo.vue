<template>
  <div class="finance">
    <!-- 标题 -->
    <el-row :gutter="20" class="no-margin-tb">
        <el-col :span="14">
            <h3 class="no-margin" style="margin-top:10px !important;">证券发行行业分布图</h3>
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
                align="right"
                unlink-panels
                size="small"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :picker-options="pickerOptions2">
            </el-date-picker>
        </el-col>
        <el-col :span='5' style="padding-left:10px;">
            <el-select v-model="code_value" placeholder="" size='small full' @change="selectClass">
              <el-option
                v-for="item in getSFClass"
                :key="item.code_value"
                :label="item.code_name"
                :value="item.code_value">
              </el-option>
            </el-select>
        </el-col>
    </el-row>
    <!-- 图表部分 -->
    <el-row :gutter="20" class="h100">
        <!-- 图表 -->
        <el-col :span="16" class="chart" style="position:relative">
            <div class="row btnGroup" style="position:absolute;right:40px;top:20px;z-index:2" id="refresh">
                <span class="singleBtn" @click="clickClass('001',$event)">IPO</span><span class="singleBtn" @click="clickClass('002',$event)">增发</span><span class="singleBtn" @click="clickClass('003',$event)">配股</span>
            </div>
            <div class="fullDiv_border">
                <chart height='100%' width='100%' id="two" :chartData = "getIpo2"></chart>
            </div>
        </el-col>
        <!-- 右侧选项卡和table -->
        <el-col :span="8" class="chart">
            <div class="fullDiv_border">
                <el-tabs v-model="activeName" @tab-click="handleClick" id="tableLine">
                    <el-tab-pane label="全部" class="tabName" name="first">
                        <div rightTable>
                           <el-table
                                ref="table0"
                                :data="data0"
                                max-height="445"
                                style="width: 100%;">
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
                    </el-tab-pane>
                    <el-tab-pane label="IPO" name="second" >
                        <div rightTable>
                           <el-table
                                ref="table1"
                                :data="data2"
                                max-height="445"
                                style="width: 100%">
                                <el-table-column align="left" label="行业" min-width="40%">
                                    <template slot-scope="scope">
                                        <span :title="scope.row.name" v-if="scope.row.name.length > 8">{{scope.row.name.substring(0,8) + '...'}}</span>
                                        <!-- <span :title="scope.row.name" v-else>{{scope.row.name}}</span> -->
                                        <a :title="scope.row.name" v-else @click="companySel(scope.row,'001')">{{scope.row.name}}</a>
                                    </template>
                                </el-table-column>

                                <el-table-column align="center" label="金额（亿元）"  min-width="40%">
                                    <template slot-scope="scope">
                                        <span v-if="scope.row.value.length==0">0.0000</span>
                                        <!-- <span>{{scope.row.value}}</span> -->
                                        <a @click="companySel(scope.row,'001')">{{scope.row.value}}</a>
                                    </template>
                                </el-table-column>

                                <el-table-column align="center" label="数量"  min-width="20%">
                                    <template slot-scope="scope">
                                        <span v-if="scope.row.num.length==0">0</span>
                                        <a @click="companySel(scope.row,'001')">{{scope.row.num}}</a>
                                    </template>
                                </el-table-column>
                            </el-table>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="增发" name="third">
                        <div rightTable>
                           <el-table
                                ref="table2"
                                :data="data3"
                                max-height="445"
                                style="width: 100%">
                                <el-table-column align="left" label="行业" min-width="40%">
                                    <template slot-scope="scope">
                                        <span :title="scope.row.name" v-if="scope.row.name.length > 8">{{scope.row.name.substring(0,8) + '...'}}</span>
                                        <!-- <span :title="scope.row.name" v-else>{{scope.row.name}}</span> -->
                                        <a :title="scope.row.name" v-else @click="companySel(scope.row,'002')">{{scope.row.name}}</a>
                                    </template>
                                </el-table-column>

                                <el-table-column align="center" label="金额（亿元）"  min-width="40%">
                                    <template slot-scope="scope">
                                        <span v-if="scope.row.value.length==0">0.0000</span>
                                        <!-- <span>{{scope.row.value}}</span> -->
                                        <a @click="companySel(scope.row,'002')">{{scope.row.value}}</a>
                                    </template>
                                </el-table-column>

                                <el-table-column align="center" label="数量"  min-width="20%">
                                    <template slot-scope="scope">
                                        <span v-if="scope.row.num.length==0">0</span>
                                        <a @click="companySel(scope.row,'002')">{{scope.row.num}}</a>
                                    </template>
                                </el-table-column>
                            </el-table>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="配股" name="fourth">
                        <div rightTable>
                           <el-table
                                ref="table3"
                                :data="data4"
                                max-height="445"
                                style="width: 100%">
                                <el-table-column align="left" label="行业" min-width="40%">
                                    <template slot-scope="scope">
                                        <span :title="scope.row.name" v-if="scope.row.name.length > 8">{{scope.row.name.substring(0,8) + '...'}}</span>
                                        <!-- <span :title="scope.row.name" v-else>{{scope.row.name}}</span> -->
                                        <a :title="scope.row.name" v-else @click="companySel(scope.row,'003')">{{scope.row.name}}</a>
                                    </template>
                                </el-table-column>

                                <el-table-column align="center" label="金额（亿元）"  min-width="40%">
                                    <template slot-scope="scope">
                                        <span v-if="scope.row.value.length==0">0.0000</span>
                                        <!-- <span>{{scope.row.value}}</span> -->
                                        <a @click="companySel(scope.row,'003')">{{scope.row.value}}</a>
                                    </template>
                                </el-table-column>

                                <el-table-column align="center" label="数量"  min-width="20%">
                                    <template slot-scope="scope">
                                        <span v-if="scope.row.num.length==0">0</span>
                                        <a @click="companySel(scope.row,'003')">{{scope.row.num}}</a>
                                    </template>
                                </el-table-column>
                            </el-table>
                        </div>
                    </el-tab-pane>
                </el-tabs>
            </div>
        </el-col>
    </el-row>
  </div>
</template>

<script>
import Chart from "@/components/Charts/securitiesChartTwo";
import datepicker from "@/mixins/datepicker";
import { mapGetters } from "vuex";
import { GetDateDiff } from "@/utils";
import chartBoxTwo from "./chartBoxTwo";
import common from '@/mixins/common'
export default {
  name: "chartBoxTwo",
  mixins: [datepicker,common],
  components: { Chart },
  data() {
    return {
      value5: "",
      code_value: "008",
      flag: 1,//记录点击的时间选择
      activeName: "first",
      param: {
        countType: "",//时间选择
        chartType: "",
        type: "",
        finaType: "",
        dateSelect: "",
        industrySelect: ""//分类选择
      },
      tableData: [],
      options: [],
      arr: ["001", "002", "003"],
      refreshFlag:false,//点击按钮不刷新右边表格
      titleName:'证券发行'
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
      this.companyDetailShow("2",this.titleName,finaType,row.name,row.condition,this.code_value);
    },
    //点击年度选项事件
    activeFun(data) {
      this.flag = data; //选中样式
      // 给chart换数据
      this.param.countType = this.flag;
      // this.param.industrySelect = "001";
      this.param.industrySelect = this.code_value;
      this.param.finaType = "001,002,003";
      var refresChildIds= document.getElementById("refresh").childNodes;
           for(var i=0;i<refresChildIds.length;i++){
              refresChildIds[i].className ='singleBtn';
           }
         this.arr.splice(0,this.arr.length);
        this.arr.push("001");
          this.arr.push("002");
            this.arr.push("003");
      this.value5 = "";
      this.chartTwo(false);
    },
    selectClass(val) {//改变分类
      this.param.countType = this.flag;
      this.param.chartType = 2;
      this.param.industrySelect = val;
      this.param.type = "ipodata2";
      this.param.finaType = "001,002,003";
      this.refreshFlag = true;
      this.$store.dispatch("ipoGet", this.param).then()
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
          this.param.type = "ipodata2";
        }
        this.refreshFlag = true;
        this.$store.dispatch("ipoGet", this.param).then(() => {});
      }
    },
    // 分类
    classGet() {
      this.$store.dispatch("ipoSFClassGet").then();
    },
    //饼状图点击事件 IPO 增发 配股
    clickClass(value, $event) {
      if ($event.target.classList.contains("clickSpan") === false) {
        $event.target.classList.add("clickSpan");
        this.param.countType = this.flag;
        this.param.chartType = 2;
        // this.param.industrySelect = "001";
        this.param.industrySelect = this.code_value;
        for (let i = 0; i < this.arr.length; i++) {
          if (this.arr[i] === value) {
            this.arr.splice(i, 1);
          }
        }
        this.param.finaType = this.arr.join(",");
        this.param.type = "ipodata2";
      } else {
        $event.target.classList.remove("clickSpan");
        this.param.countType = this.flag;
        this.param.chartType = 2;
        // this.param.industrySelect = "001";
        this.param.industrySelect = this.code_value;
        this.arr.push(value);
        this.param.finaType = this.arr.join(",");
        this.param.type = "ipodata2";
      }
      this.refreshFlag = false;
      this.$store.dispatch("ipoGet", this.param).then(() => {});
    }
  },
  computed: {
    ...mapGetters(["getIpo2","getSFClass"]),
    data0(){
        return this.tableData.length>0?this.tableData[0].dataSum:[]
    },
    data2(){
        return this.tableData.length>0?this.tableData[2].dataSum:[]
    },
    data3(){
        return this.tableData.length>0?this.tableData[3].dataSum:[]
    },
    data4(){
        return this.tableData.length>0?this.tableData[4].dataSum:[]
    }
  },
  mounted() {
    //页面加载完成时刷新echart图表
    this.chartTwo(true);
    this.classGet(true);
  },
  watch: {
    value5(n, o) {//改变时间时,监听事件,判断搜索日期大于一个月
      //依照操作取数据
      if (this.getValue(n) == '') {//清空时间
        if(this.flag == 7) {
          this.dateSelect = "";
          this.flag = 1;
          this.chartTwo(true);
          for(let i =0; i< document.getElementById('listB').getElementsByTagName('a').length;i++) {
            if (document.getElementById('listB').getElementsByTagName('a')[i].classList.contains("active") === false) {
              document.getElementById('listB').getElementsByTagName('a')[3].classList.add("active")
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
        // this.param.industrySelect= '001'
        if (flg >= 31) {
          this.param.dateSelect = start + " 至 " + end;
          // console.log(this.param)
          this.chartTwo(false);
          for(let i =0; i< document.getElementById('listB').getElementsByTagName('a').length;i++) {
            if (document.getElementById('listB').getElementsByTagName('a')[i].classList.contains("active") === true) {
              document.getElementById('listB').getElementsByTagName('a')[i].classList.remove("active")
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
    getIpo2(n, o) {
      //数据变化时更新chart
      if(this.refreshFlag) {//更改时间或者分类时,刷新右侧数据
        this.tableData = n;
      }
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

.singleBtn {
  font-size:14px;
  font-family:'Microsoft YaHei'!important;
  line-height:1.42857143;
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
