<template>
  <div>
    <el-table
      class="incomeTable"
      border
      style="width: 100%">
      <el-table-column
        prop=""
        label="项目"
        width="184">
      </el-table-column>
      <el-table-column
        prop=""
       :label="incomeTableList[0].forthYearDate?incomeTableList[0].forthYearDate:''"
       align="right">
      </el-table-column>
      <el-table-column
        prop=""
        :label="incomeTableList[0].thirdYearDate?incomeTableList[0].thirdYearDate:''"
        align="right">
      </el-table-column>
      <el-table-column
        prop=""
        :label="incomeTableList[0].secondYearDate?incomeTableList[0].secondYearDate:''"
        align="right">
      </el-table-column>
       <el-table-column
        prop=""
        :label="incomeTableList[0].firstYearDate?incomeTableList[0].firstYearDate:''"
        align="right">
      </el-table-column>
      <el-table-column
        prop=""
        label="趋势"
        align="center">
      </el-table-column>
    </el-table>
    <p v-if="incomeTableList[1]&&incomeTableList[1].length>0" class="sortProject">收入类项目：</p>
    <el-table
      class="table2"
     :data="incomeTableList[1]"
      border
      style="width: 100%">
       <el-table-column prop="itemName" align="left"  class-name="table_cell" label="占比" width="184">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.itemName) ? scope.row.itemName : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="forthYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
              <span v-if="scope.row.forthYearValue"> {{scope.row.forthYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="thirdYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
              <span v-if="scope.row.thirdYearValue"> {{scope.row.thirdYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="secondYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
              <span v-if="scope.row.secondYearValue"> {{scope.row.secondYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="firstYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
              <span v-if="scope.row.firstYearValue"> {{scope.row.firstYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span @click="handleShowChart(scope.$index, scope.row)" style="color:#1990fe;cursor:pointer">查看</span>
          </template>
        </el-table-column>

    </el-table>
     <p v-if="incomeTableList[2]&&incomeTableList[2].length>0" class="sortProject">成本类项目：</p>
    <el-table
      class="table2"
     :data="incomeTableList[2]"
      border
      style="width: 100%">
       <el-table-column prop="itemName" align="left"  class-name="table_cell" label="占比" width="184">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.itemName) ? scope.row.itemName : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="forthYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
              <span v-if="scope.row.forthYearValue"> {{scope.row.forthYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="thirdYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
              <span v-if="scope.row.thirdYearValue"> {{scope.row.thirdYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="secondYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
              <span v-if="scope.row.secondYearValue"> {{scope.row.secondYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="firstYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
              <span v-if="scope.row.firstYearValue"> {{scope.row.firstYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span @click="handleShowChart(scope.$index, scope.row)" style="color:#1990fe;cursor:pointer">查看</span>
          </template>
        </el-table-column>

    </el-table>
     <p v-if="incomeTableList[3]&&incomeTableList[3].length>0" class="sortProject"> 利润类项目：</p>
     <el-table
      class="table2"
     :data="incomeTableList[3]"
      border
      style="width: 100%">
       <el-table-column prop="itemName" align="left"  class-name="table_cell" label="占比" width="184">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.itemName) ? scope.row.itemName : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="forthYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
              <span v-if="scope.row.forthYearValue"> {{scope.row.forthYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="thirdYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
              <span v-if="scope.row.thirdYearValue"> {{scope.row.thirdYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="secondYearValue" align="right"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
              <span v-if="scope.row.secondYearValue"> {{scope.row.secondYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="firstYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
              <span v-if="scope.row.firstYearValue"> {{scope.row.firstYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span @click="handleShowChart(scope.$index, scope.row)" style="color:#1990fe;cursor:pointer">查看</span>
          </template>
        </el-table-column>

    </el-table>
    <!-- 点击放大镜弹出的折线图 -->
    <el-dialog :title="this.zxChartData?'公司最近三年'  +this.zxChartData.itemName+'_趋势':''" :visible.sync="dialogChartVisible" v-if="dialogChartVisible">
       <tanZxChart3 :zxChartData = "[this.zxChartData,this.incomeTableList[0],this.flag]"></tanZxChart3>
    </el-dialog>

  </div>
</template>

<script>
// 引入点击放大镜弹出来的表头年限数据
import { getSelectFinanceProfitList } from '@/api/ipoCase/tableDemo'
import echarts from 'echarts'
import tanZxChart3  from '@/components/Charts/tanZxChart3'
  export default {
    name:"incomeTable",
    data() {
    return {
       flag:false,//判断是否是主要财务指标
        caseId:this.$store.state.app.caseId,
      // 弹窗
        tanZxChart3:null,
         incomeTableTitle: {
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
      tanZxChart3
    },
    created(){
      // this.initTableData()

    },
    props:[ "incomeTableList"],
    beforeDestroy () {

    },
    mounted() {
    },
    updated(){
    },
    methods: {
      // 初始化数据
      /*initTableData() {
          // 动态传id
        const param = {
          id:this.caseId
        }
        getSelectFinanceProfitList(param).then(res => {
          this.incomeTableTitle = res.data.result.dateList
          this.ipoProfitItemList = res.data.result.ipoProfitItemList//收益类项目列表
          this.ipoCostItemList = res.data.result.ipoCostItemList//成本类项目列表
          this.ipoReturnOverList = res.data.result.ipoReturnOverList//利润类项目列表
        })
      },*/
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
