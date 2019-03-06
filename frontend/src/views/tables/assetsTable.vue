<template>
  <div>
    <!-- table1 -->
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
       :label="tableTitle.year1?tableTitle.year1:''"
       align="center">
      </el-table-column>
      <el-table-column
        prop=""
        :label="tableTitle.year2?tableTitle.year2:''"
        align="center">
      </el-table-column>
      <el-table-column
        prop=""
        :label="tableTitle.year3?tableTitle.year3:''"
        align="center">
      </el-table-column>
       <el-table-column
        prop=""
        :label="tableTitle.year4?tableTitle.year4:''"
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
     :data="tableContent"
      border
      style="width: 100%">
       <el-table-column prop="project" align="left"  class-name="table_cell" label="占比" width="184">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.project) ? scope.row.project : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="count1" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count1) ? scope.row.count1 : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="count2" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count2) ? scope.row.count2 : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="count3" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count3) ? scope.row.count3 : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="count4" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count4) ? scope.row.count4 : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span @click="handleShowChart(scope.$index, scope.row)"><i class="el-icon-search"></i></span>
            <!-- <span>{{isNotEmpty(scope.row.count4) ? scope.row.count4 : '- -'}}</span> -->
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
     :data="tableContent"
      border
      style="width: 100%">
       <el-table-column prop="project" align="left"  class-name="table_cell" label="占比" width="184">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.project) ? scope.row.project : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="count1" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count1) ? scope.row.count1 : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="count2" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count2) ? scope.row.count2 : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="count3" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count3) ? scope.row.count3 : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="count4" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count4) ? scope.row.count4 : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span @click="handleShowChart(scope.$index, scope.row)"><i class="el-icon-search"></i></span>             
           
            <!-- <span>{{isNotEmpty(scope.row.count4) ? scope.row.count4 : '- -'}}</span> -->
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
     :data="tableContent"
      border
      style="width: 100%">
       <el-table-column prop="project" align="left"  class-name="table_cell" label="占比" width="184">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.project) ? scope.row.project : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="count1" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count1) ? scope.row.count1 : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="count2" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count2) ? scope.row.count2 : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="count3" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count3) ? scope.row.count3 : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="count4" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count4) ? scope.row.count4 : '- -'}}</span>
          </template>
        </el-table-column>
         <el-table-column prop="" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span @click="handleShowChart(scope.$index, scope.row)"><i class="el-icon-search"></i></span>           
            <!-- <span>{{isNotEmpty(scope.row.count4) ? scope.row.count4 : '- -'}}</span> -->
          </template>
        </el-table-column>
     
    </el-table>
    <!-- 点击放大镜弹出的折线图 -->
    <el-dialog :title="this.zxChartData?'公司最近三年'  +this.zxChartData.project+'_趋势':''" :visible.sync="dialogChartVisible">
       <tanZxChart :zxChartData = "this.zxChartData"></tanZxChart>
    </el-dialog>

  </div>
</template>

<script>
import { getAssetsTableData } from '@/api/tableDemo'
import tanZxChart  from '@/components/Charts/tanZxChart'
  export default {
    data() {
    return {
      tableTitle: null,
      tableContent: null,
      // 控制弹窗是否展示
      dialogChartVisible: false,
      zxChartData:null
    }
  },
    components:{
      tanZxChart
    },
    creatsd(){
      this.initTableData()
    },
    mounted() {
    },
    updated(){
    },
    methods: {
      // 初始化数据
      initTableData() {
        getAssetsTableData().then(response => {
          this.tableTitle = response.data.assetsList[0]
          this.tableContent = response.data.assetsList.slice(1)
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
</style>