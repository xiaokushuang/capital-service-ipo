<template>
    <div class="container">
        <!-- 发行监管部再融资企业审核状态 -->
        <el-row :gutter="20" class="no-margin-tb">
            <el-col :span="14">
                <h3 class="no-margin" style="line-height:32px;padding-left:0px !important;">发行监管部再融资企业审核状态</h3>
                <div class="fullDiv_border" style="height:450px;">
                    <chart height='100%' width='100%' :chartData="getRefinanceApprove"></chart>
                </div>
            </el-col>
            <!-- 再融资申请类型情况 -->
            <el-col :span="10">
                <h3 class="no-margin" style="line-height:32px;padding-left:0px !important;">再融资申请类型情况</h3>
                <el-table id="refinance1" class="refinance" :data="getRefinanceApptype" style="width:100%" border sortable="custom" size="medium" :header-row-class-name="wihteFunction">
                    <el-table-column label="申请类型" align="center" min-width="310px" prop="label"></el-table-column>
                    <el-table-column label="数量" align="center" min-width="205px" prop="totalCount"></el-table-column>
                </el-table>
            </el-col>
        </el-row>
        <el-row class="col-md-12 no-padding theme-silver">
            <hr style="margin: 20px 15px;">
        </el-row>
        <!-- 发行监管部再融资审核保荐机构保荐企业数量 -->
        <el-row :gutter="20" class="no-margin-tb">
            <el-col :span="14">
                <h3 class="no-margin" style="line-height:32px;padding-left:0px !important;">发行监管部再融资审核保荐机构保荐企业数量</h3>
            </el-col>
        </el-row>
        <!-- 发行监管部再融资审核保荐机构保荐企业数量表 -->
        <el-row>
            <el-table id="refinance2" class="refinance greyBg" :data="tableRe" style="width:100%" border sortable="custom" size="medium" :header-row-class-name="bgFunction" :row-class-name="tableRowClassName">
                <el-table-column label="序号" align="center" prop="num" width="65px"></el-table-column>
                <el-table-column label="保荐机构"  min-width="450px">
                    <template slot-scope="scope">
                        <span>{{scope.row.label}}</span>
                    </template>
                </el-table-column>
                <el-table-column label="深主板" align="center" prop="szbCount" min-width="130px"></el-table-column>
                <el-table-column label="沪主板" align="center" prop="hzbCount" min-width="130px"></el-table-column>
                <el-table-column label="中小板" align="center" prop="zxbCount" min-width="130px"></el-table-column>
                <el-table-column label="创业板" align="center" prop="cybCount" min-width="130px"></el-table-column>
                <el-table-column label="合计" align="center" prop="totalCount" min-width="130px"></el-table-column>
                <el-table-column label="比例" align="center" min-width="130px">
                    <template slot-scope="scope">
                        <span>{{scope.row.percent}}%</span>
                    </template>
                </el-table-column>
            </el-table>
        </el-row>
    </div>        
</template>
<script>
import Chart from "@/components/Charts/refinance";
import datepicker from "@/mixins/datepicker";
import { mapGetters } from "vuex";
import { GetDateDiff } from "@/utils";
import refinancing from "./refinancing";
export default {
  name: "refinancing",
  mixins: [datepicker],
  components: { Chart },
  data() {
    return {
      tableData: [],
      greyBg: "greyBg"
    };
  },
  computed: {
    ...mapGetters([
      "getRefinanceApprove",
      "getRefinanceApptype",
      "getRefinanceRecommend"
    ]),
    // 表数据重组，加入Num字段
    tableRe() {
      var middle1 = [];
      if (Object.prototype.toString.call(this.getRefinanceRecommend.data) === '[object Array]') {
        this.getRefinanceRecommend.data.map((obj, idx) => {
          var valuec = {};
          var data1 = Object.keys(obj);
          data1.map((o,i)=>{
            valuec[o]=obj[o]
          });
          valuec.num = idx+1;
          if (idx+1 == this.getRefinanceRecommend.length) {
            valuec.num = ""
          }else {
            valuec.num = idx+1;
          }
        });
        return middle1;
      }
      var middle = [];
      this.getRefinanceRecommend.map((obj, idx) => {
        var valuec = {};
        valuec = [{
          applied: "",
          areaCount: "",
          cybCount: "",
          endYet: "",
          hzbCount: "",
          label: "",
          lastUpadteTime: null,
          passed: "",
          percent: "",
          preUpdate: "",
          processing: "",
          registAddr: null,
          reviewed: "",
          stopYet: null,
          szbCount: "",
          totalCount: "",
          value: null,
          weekStopYet: null,
          zxbCount: "",
          num: ""
        }];
        valuec;
        valuec.applied = obj.applied;
        valuec.areaCount = obj.areaCount;
        valuec.cybCount = obj.cybCount;
        valuec.endYet = obj.endYet;
        valuec.hzbCount = obj.hzbCount;
        valuec.label = obj.label;
        valuec.lastUpadteTime = obj.lastUpadteTime;
        valuec.passed = obj.passed;
        valuec.percent = obj.percent;
        valuec.preUpdate = obj.preUpdate;
        valuec.processing = obj.processing;
        valuec.registAddr = obj.registAddr;
        valuec.reviewed = obj.reviewed;
        valuec.stopYet = obj.stopYet;
        valuec.szbCount = obj.szbCount;
        valuec.totalCount = obj.totalCount;
        valuec.value = obj.value;
        valuec.weekStopYet = obj.weekStopYet;
        valuec.zxbCount = obj.zxbCount;
        if (idx+1 == this.getRefinanceRecommend.length) {
          valuec.num = ""
        }else {
          valuec.num = idx+1;
        }
        middle.push(valuec);
      });
      return middle;
    }
  },
  mounted() {
    //页面加载完成时刷新echart图表
    this.ipoDataPort(true);
  },
  methods: {
    bgFunction(row, rowIndex) {
      if (rowIndex === 0) {
        return "greyBg";
      }
    },
    wihteFunction(row, rowIndex) {
        return "whiteBg"
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === this.getRefinanceRecommend.length - 1) {
        return "greyBg";
      }
      return "";
    },
    ipoDataPort() {
      // 发行监管部再融资企业审核状态 接口
      this.$store.dispatch("refinanceApproveGet");
      // 再融资申请类型情况接口
      this.$store.dispatch("refinanceApptypeGet");
      // table表接口
      this.$store.dispatch("refinanceRecommendGet");
    }
  }
};
</script>
<style>
.refinance {
  margin-top: 15px;
}
table tbody tr td {
  padding: 5px;
  border-right: 1px solid #ebeef5;
}
table tbody tr td {
  padding: 5px;
  border-right: 1px solid #ebeef5;
}
/* #refinance1 .el-table thead tr th {
  background: #fff;
  color: #000;
  border-right: 1px solid #ebeef5;
} */
.theme-silver hr {
  border-top: 1px solid #eee;
}
.el-table .bg {
  background: #e8e8e8 !important;
}
.el-table .greyBg {
  background: #e8e8e8 !important;
  text-align: center !important
}
.el-table--enable-row-transition .el-table__body td {
  border-right: 1px solid #ebeef5;
}
.el-table__header thead tr > th {
  padding: 5px;
}
.el-table .cell,
.el-table th div,
.el-table--border td:first-child .cell,
.el-table--border th:first-child .cell {
  padding: 5px;
}
.el-table__header thead tr > th{padding:0px; background: #e8e8e8 !important;color:#000;text-align: center !important}
</style>

    