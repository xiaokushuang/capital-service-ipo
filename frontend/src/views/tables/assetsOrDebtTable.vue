<template>
  <div>
    <!-- table1 -->
    <!-- <el-table
      border
      style="width: 100%">
      <el-table-column
        prop=""
        label="项目"
        width="180">
      </el-table-column>
      <el-table-column
        prop=""
       :label="tableTitle[0]"
       align="center">
      </el-table-column>
      <el-table-column
        prop=""
        :label="tableTitle[1]"
        align="center">
      </el-table-column>
      <el-table-column
        prop=""
        :label="tableTitle[2]"
        align="center">
      </el-table-column>
       <el-table-column
        prop=""
        :label="tableTitle[3]"
        align="center">
      </el-table-column>
      <el-table-column
        prop=""
        label="趋势"
        align="center">
      </el-table-column>
    </el-table> -->
        <el-table
      border
      style="width: 100%">
      <el-table-column
        prop=""
        label="项目"
        width="180">
      </el-table-column>
      <el-table-column
        prop=""
       :label="tableTitle.year4?tableTitle.year4:''"
       align="center">
      </el-table-column>
      <el-table-column
        prop=""
        :label="tableTitle.year3?tableTitle.year3:''"
        align="center">
      </el-table-column>
      <el-table-column
        prop=""
        :label="tableTitle.year2?tableTitle.year2:''"
        align="center">
      </el-table-column>
       <el-table-column
        prop=""
        :label="tableTitle.year1?tableTitle.year1:''"
        align="center">
      </el-table-column>
      <el-table-column
        prop=""
        label="趋势"
        align="center">
      </el-table-column>
    </el-table>
    <p style=" 
    font-weight: 700;
    line-height: 11px;
    font-style: normal;
    font-size: 14px;
    text-align: left">资产类项目：</p>
    <el-table
      class="table2"
     :data="ipoAssetItemList"
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
            <span @click="handleShowChart(scope.$index, scope.row)"><i class="el-icon-search"></i></span>
          </template>
        </el-table-column>
     
    </el-table>
     <p style=" 
    font-weight: 700;
    line-height: 11px;
    font-style: normal;
    font-size: 14px;
    text-align: left">负债类项目：</p>
    <el-table
      class="table2"
     :data="ipoDebtItemList"
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
            <span @click="handleShowChart(scope.$index, scope.row)"><i class="el-icon-search"></i></span>             
          </template>
        </el-table-column>
     
    </el-table>
     <p style=" 
    font-weight: 700;
    line-height: 11px;
    font-style: normal;
    font-size: 14px;
    text-align: left"> 权益类项目：</p>
     <el-table
      class="table2"
     :data="ipoEquityItemList"
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
            <span @click="handleShowChart(scope.$index, scope.row)"><i class="el-icon-search"></i></span>           
          </template>
        </el-table-column>
     
    </el-table>
    <!-- 点击放大镜弹出的折线图 -->
    <el-dialog :title="this.zxChartData?'公司最近三年'  +this.zxChartData.itemName+'_趋势':''" :visible.sync="dialogChartVisible">
       <tanZxChart :zxChartData = "this.zxChartData"></tanZxChart>
    </el-dialog>

  </div>
</template>

<script>
// 引入点击放大镜弹出来的表头年限数据
import { getAssetsOrDebtData } from '@/api/tableDemo'
import echarts from 'echarts'
import tanZxChart  from '@/components/Charts/tanZxChart'
  export default {
    data() {
    return {
      // 弹窗
        tanZxChart:null,
        tableTitle: null,
        ipoAssetItemList:[],//资产类项目列表
        ipoDebtItemList:[],//负债类项目列表
        ipoEquityItemList:[],//权益类项目列表
        tableContent: null,
      // 控制弹窗是否展示
        dialogChartVisible: false,
        zxChartData:null
    }
  },
    components:{
      tanZxChart
    },
    created(){
      this.initTableData()
       
    },
    beforeDestroy () {
      // this.zxChartData = null
    },
    mounted() {

    },
    updated(){
    },
    methods: {
      // 初始化数据
      initTableData() {
        getAssetsOrDebtData().then(res => {
          console.log(res.data.result)
          this.tableTitle = res.data.result.dateList
          // console.log(this.tableTitle[0])
          this.ipoAssetItemList = res.data.result.ipoAssetItemList//资产类项目列表
          this.ipoDebtItemList = res.data.result.ipoDebtItemList//负债类项目列表
          this.ipoEquityItemList = res.data.result.ipoEquityItemList//权益类项目列表
          // this.tableContent = response.data.assetsList.slice(1)
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
        console.log(this.zxChartData)
        this.dialogChartVisible = true;
      }
    }
    }
</script>
<style scoped lang="scss">
.table2{
  background: #f5f7fa;
}
</style>