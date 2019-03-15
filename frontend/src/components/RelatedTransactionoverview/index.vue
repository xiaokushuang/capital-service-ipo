<template>
<div>
    <el-row class="titleStyle">
        <el-col :span="20">最近一笔交易</el-col>
        <el-col :span="4" style="text-align:right;">
         <el-button class="filter-item" size="mini" title = "最近一笔交易"  @click="handleCreateOne" type="primary" icon="el-icon-edit">查看标准</el-button>
        </el-col>
    </el-row>
    <div class="paddingValue">
        <el-table border style="width: 100%; text-align: center; " height="250" :data="myDoneListRecordOne" v-loading="listLoading" element-loading-text="给我一点时间">
            <el-table-column align="center" prop="taskId" label="交易类型"></el-table-column>
            <el-table-column align="center" prop="taskType" label="交易对象"></el-table-column>
            <el-table-column align="center" prop="taskName" label="金额（万元）"></el-table-column>
            <el-table-column align="center" prop="reporterName" label="发生日期"></el-table-column>
            <el-table-column align="center" prop="createTime" label="是否需要披露"></el-table-column>
            <el-table-column align="center" prop="reportDepartmentName" label="是否需要股东大会审议"></el-table-column>
        </el-table>
    </div>
    <el-row class="titleStyle">
        <el-col :span="24">十二个月累计情况</el-col>
        <!-- <el-col :span="4" style="text-align:right;">
        </el-col> -->
    </el-row>
    <div class="paddingValue">
        <el-table border style="width: 100%;text-align: center;" height="250" :data="myDoneListRecordTow" v-loading="listLoading" element-loading-text="给我一点时间">
            <el-table-column align="center" prop="subjectName" label="主体名称"></el-table-column>
            <el-table-column align="center" prop="transactionCount" label="累计金额（万元）"></el-table-column>
            <el-table-column align="center" prop="timeLimt" label="累计时间范围"></el-table-column>
            <el-table-column align="center" prop="boardDeliberation" label="是否需要审议"></el-table-column>
            <el-table-column align="center" prop="reportDepartmentName" label="详情">
            <template slot-scope="scope">
                <el-button class="filter-item" size="mini" title = "十二个月累计情况"  @click="handleCreatesTwo" type="primary" icon="el-icon-edit">查看标准</el-button>
            </template>
            </el-table-column>
        </el-table>
    </div>
     <el-row class="titleStyle">
        <el-col :span="20">本年度累计情况-按交易类型累计</el-col>
        <el-col :span="4" style="text-align:right;">
         <el-button class="filter-item" size="mini" title = "本年度累计情况-按交易类型累计"  @click="handleCreatesThree" type="primary" icon="el-icon-edit">查看标准</el-button>
        </el-col>
    </el-row>
    <div class="paddingValue">
        <el-table border style="width: 100%;text-align: center;" height="250" :data="myDoneListRecordThree" v-loading="listLoading" element-loading-text="给我一点时间">
            <el-table-column align="center" prop="taskId" label="交易类型"></el-table-column>
            <el-table-column align="center" prop="taskType" label="交易类型"></el-table-column>
            <el-table-column align="center" prop="taskName" label="累计金额（万元）"></el-table-column>
            <el-table-column align="center" prop="createTime" label="累计时间范围"></el-table-column>
            <el-table-column align="center" prop="reportDepartmentName" label="是否需要审议"></el-table-column>
        </el-table>
    </div>
    <el-row class="titleStyle">
        <el-col :span="20">本年度累计情况-按交易对象累计</el-col>
        <el-col :span="4" style="text-align:right;">
         <el-button class="filter-item" size="mini" title = "本年度累计情况-按交易对象累计"  @click="handleCreatesThree" type="primary" icon="el-icon-edit">查看标准</el-button>
        </el-col>
    </el-row>
    <div class="paddingValue">
        <el-table border style="width: 100%;text-align: center;" height="250" :data="myDoneListRecordFour" v-loading="listLoading" element-loading-text="给我一点时间">
            <el-table-column align="center" prop="taskId" label="交易类型"></el-table-column>
            <el-table-column align="center" prop="taskType" label="交易对象"></el-table-column>
            <el-table-column align="center" prop="taskName" label="累计金额（万元）"></el-table-column>
            <el-table-column align="center" prop="createTime" label="累计时间范围"></el-table-column>
            <el-table-column align="center" prop="reportDepartmentName" label="是否需要审议"></el-table-column>
        </el-table>
    </div>
    <el-dialog title="最近一笔交易" :visible.sync="dialogFormVisibleOne" :close-on-click-modal="false" width="70%" style="text-align:left;">
        <div class="paddingValue">
        <el-table border style="width: 100%;text-align: center;" :data="myDoneListRecordFive" v-loading="listLoading" element-loading-text="给我一点时间">
            <el-table-column align="center" prop="taskId" label="交易类型"></el-table-column>
            <el-table-column align="center" prop="taskType" label="计算指标分子"></el-table-column>
            <el-table-column align="center" prop="taskName" label="计算指标分母"></el-table-column>
            <el-table-column align="center" prop="createTime" label="分母"></el-table-column>
            <el-table-column align="center" prop="createTime" label="披露要求">
                <el-table-column align="center" prop="taskName" label="金额（万元）"></el-table-column>
                <el-table-column align="center" prop="createTime" label="占比（%）"></el-table-column>
            </el-table-column>
            <el-table-column align="center" prop="reportDepartmentName" label="股东大会审议">
                <el-table-column align="center" prop="taskName" label="金额（万元）"></el-table-column>
                <el-table-column align="center" prop="createTime" label="占比（%）"></el-table-column>
            </el-table-column>
        </el-table>
        </div>
    </el-dialog>
    <el-dialog title="十二个月累计" :visible.sync="dialogFormVisibleTow" :close-on-click-modal="false" width="70%" style="text-align:left;">
        <div class="paddingValue">
        <el-table border style="width: 100%;text-align: center;" :data="myDoneListRecordSix" v-loading="listLoading" element-loading-text="给我一点时间">
            <el-table-column align="center" prop="taskId" label="交易类型"></el-table-column>
            <el-table-column align="center" prop="taskType" label="计算指标分子"></el-table-column>
            <el-table-column align="center" prop="taskName" label="计算指标分母"></el-table-column>
            <el-table-column align="center" prop="createTime" label="分母"></el-table-column>
            <el-table-column align="center" prop="createTime" label="披露要求">
                <el-table-column align="center" prop="taskName" label="金额（万元）"></el-table-column>
                <el-table-column align="center" prop="createTime" label="占比（%）"></el-table-column>
            </el-table-column>
            <el-table-column align="center" prop="reportDepartmentName" label="股东大会审议">
                <el-table-column align="center" prop="taskName" label="金额（万元）"></el-table-column>
                <el-table-column align="center" prop="createTime" label="占比（%）"></el-table-column>
            </el-table-column>
        </el-table>
        </div>
    </el-dialog>
    <el-dialog title="日常关联交易计算" :visible.sync="dialogFormVisibleThree" :close-on-click-modal="false" width="70%" style="text-align:left;">
        <div class="paddingValue">
        <el-table border style="width: 100%;text-align: center;" :span-method="objectSpanMethod" :data="tableData" v-loading="listLoading" element-loading-text="给我一点时间">
            <el-table-column align="center" prop="taskId" label="交易类型"></el-table-column>
            <el-table-column align="center" prop="taskType" label="交易对象"></el-table-column>
            <el-table-column align="center" prop="taskName" label="年度预算金额（万元）"></el-table-column>
        </el-table>
    </div>
    </el-dialog>
</div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
    name : 'RelatedTransactionoverview',
  data() {
    return {
       listLoading: false, //table请求等待
       dialogFormVisibleOne: false,
       dialogFormVisibleTow: false,
       dialogFormVisibleThree: false,
       tableData: [{
          taskId: '12987122',
          taskType: '王小虎',
          taskName: '234'
        }, {
          taskId: '12987122',
          taskType: '王小虎',
          taskName: '234'
        }, {
          taskId: '12987122',
          taskType: '王小虎',
          taskName: '234'
        }, {
          taskId: '12987122',
          taskType: '王小虎',
          taskName: '234'
        }, {
          taskId: '12987122',
          taskType: '王小虎',
          taskName: '234'
        }]
    };
  },
  created() {
    //加载前默认调用
    
  },
  computed: {
    //获取getters中方法
    ...mapGetters([
      'myDoneListRecordOne',
      'myDoneListRecordTow',
      'myDoneListRecordThree',
      'myDoneListRecordFour',
      'myDoneListRecordFive',
      'myDoneListRecordSix',
    //   "",
    //   ""
      
    ])
  },
  methods: {
      tableList(){
          // debugger
        //  let param = {
        // }
        this.$store.dispatch('getTableDataRequestRelated').then((data) => {
        }).catch(err => { })
      },
      handleCreateOne(){
          console.log(1);
        this.dialogFormVisibleOne = true
            let param = {
                        }
        this.$store.dispatch('getDataRequestRelated', param).then((data) => {
        this.listLoading = false;
                }).catch(err => { })
      },
      handleCreatesTwo(){
          console.log(2);
        this.dialogFormVisibleTow = true  
      },
      handleCreatesThree(){
          console.log(3);
        this.dialogFormVisibleThree =true
      },
      objectSpanMethod({ row, column, rowIndex, columnIndex }) {
        if (columnIndex === 0) {
          if (rowIndex % 3 === 0) {
            return {
              rowspan: 3,
              colspan: 1
            };
          } else {
            return {
              rowspan: 0,
              colspan: 0
            };
          }
        }
      },
    },
 created(){
    // debugger
    this.tableList();
},
watch: {
     'myDoneListRecordTow': function (val,oldVal){
         console.log(val)
         console.log(oldVal)
     }
}
};
</script>

