<template>
  <div>
    <el-table
      class="incomeTable"
      border
      style="width: 100%">
      <el-table-column
        prop=""
        label="项目"
        width="180">
      </el-table-column>
      <el-table-column
        prop=""
       :label="tableTitle.forthYearDate?tableTitle.forthYearDate:''"
       align="center">
      </el-table-column>
      <el-table-column
        prop=""
        :label="tableTitle.thirdYearDate?tableTitle.thirdYearDate:''"
        align="center">
      </el-table-column>
      <el-table-column
        prop=""
        :label="tableTitle.secondYearDate?tableTitle.secondYearDate:''"
        align="center">
      </el-table-column>
       <el-table-column
        prop=""
        :label="tableTitle.firstYearDate?tableTitle.firstYearDate:''"
        align="center">
      </el-table-column>
      <el-table-column
        prop=""
        label="趋势"
        align="center">
      </el-table-column>
    </el-table>
    <p v-if="ipoProfitItemList&&ipoProfitItemList.length>0" class="sortProject">收入类项目：</p>
    <el-table
      class="table2"
     :data="ipoProfitItemList"
      border
      style="width: 100%">
       <el-table-column prop="itemName" align="left"  class-name="table_cell" label="占比" width="184">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.itemName) ? scope.row.itemName : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="forthYearValue" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.forthYearValue) ? scope.row.forthYearValue : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="thirdYearValue" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.thirdYearValue) ? scope.row.thirdYearValue : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="secondYearValue" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.secondYearValue) ? scope.row.secondYearValue : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="firstYearValue" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.firstYearValue) ? scope.row.firstYearValue : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span @click="handleShowChart(scope.$index, scope.row)" style="color:#1990fe">查看</span>
          </template>
        </el-table-column>
     
    </el-table>
     <p v-if="ipoCostItemList&&ipoCostItemList.length>0" class="sortProject">成本类项目：</p>
    <el-table
      class="table2"
     :data="ipoCostItemList"
      border
      style="width: 100%">
       <el-table-column prop="itemName" align="left"  class-name="table_cell" label="占比" width="184">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.itemName) ? scope.row.itemName : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="forthYearValue" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.forthYearValue) ? scope.row.forthYearValue : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="thirdYearValue" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.thirdYearValue) ? scope.row.thirdYearValue : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="secondYearValue" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.secondYearValue) ? scope.row.secondYearValue : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="firstYearValue" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.firstYearValue) ? scope.row.firstYearValue : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span @click="handleShowChart(scope.$index, scope.row)" style="color:#1990fe">查看</span>             
          </template>
        </el-table-column>
     
    </el-table>
     <p v-if="ipoReturnOverList&&ipoReturnOverList.length>0" class="sortProject"> 利润类项目：</p>
     <el-table
      class="table2"
     :data="ipoReturnOverList"
      border
      style="width: 100%">
       <el-table-column prop="itemName" align="left"  class-name="table_cell" label="占比" width="184">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.itemName) ? scope.row.itemName : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="forthYearValue" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.forthYearValue) ? scope.row.forthYearValue : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="thirdYearValue" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.thirdYearValue) ? scope.row.thirdYearValue : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="secondYearValue" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.secondYearValue) ? scope.row.secondYearValue : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="firstYearValue" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.firstYearValue) ? scope.row.firstYearValue : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span @click="handleShowChart(scope.$index, scope.row)" style="color:#1990fe">查看</span>           
          </template>
        </el-table-column>
     
    </el-table>
    <!-- 点击放大镜弹出的折线图 -->
    <el-dialog :title="this.zxChartData?'公司最近三年'  +this.zxChartData.itemName+'_趋势':''" :visible.sync="dialogChartVisible">
       <tanZxChart2 :zxChartData = "this.zxChartData"></tanZxChart2>
    </el-dialog>

  </div>
</template>

<script>
// 引入点击放大镜弹出来的表头年限数据
import { getSelectFinanceProfitList } from '@/api/ipoCase/tableDemo'
import echarts from 'echarts'
import tanZxChart2  from '@/components/Charts/tanZxChart2'
  export default {
    name:"incomeTable",
    data() {
    return {
        caseId:this.$store.state.app.caseId,
      // 弹窗
        tanZxChart2:null,
         tableTitle: {
          forthYearDate:'',
          thirdYearValue:'',
          secondYearValue:'',
          firstYearValue:''
        },
        ipoReturnOverList :[],//利润类项目列表
        ipoCostItemList :[],//成本类项目列表
        ipoProfitItemList :[],//收益类项目列表
        tableContent: null,
      // 控制弹窗是否展示
        dialogChartVisible: false,
        zxChartData:null
    }
  },
    components:{
      tanZxChart2
    },
    created(){
      this.initTableData()
       
    },
    beforeDestroy () {
    
    },
    mounted() {
    },
    updated(){
    },
    methods: {
      // 初始化数据
      initTableData() {
          // 动态传id
        const param = {
          id:this.caseId
        }
        getSelectFinanceProfitList(param).then(res => {
          this.tableTitle = res.data.result.dateList
          this.ipoProfitItemList = res.data.result.ipoProfitItemList//收益类项目列表
          this.ipoCostItemList = res.data.result.ipoCostItemList//成本类项目列表
          this.ipoReturnOverList = res.data.result.ipoReturnOverList//利润类项目列表
        })
      },
      // 非空判断
      isNotEmpty(param) {
        // debugger
        if (param != null && param !== undefined && param !== '' && param !== 'null' && param !== 'undefined') {
          return true
        } else {
          return false
        }
      },
      // 点击放大镜弹出折线图
      handleShowChart(i,r){
        this.zxChartData = r
        this.dialogChartVisible = true;
      }
    }
    }
</script>
<style scoped lang="scss">
.table2{
  background: #f5f7fa;
}
.el-table__empty-block{
  display:none
}
.sortProject{
    background: #f2f2f2;
    font-weight: 700;
    line-height: 11px;
    font-style: normal;
    font-size: 14px;
    text-align: left;
    margin: 0px;
    padding-top: 14px;
    padding-bottom: 14px;
    padding-left: 12px;
}

</style>