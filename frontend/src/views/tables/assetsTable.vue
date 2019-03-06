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
        <!-- <div class="className" id="tanZxChart" style="height:300px;width:100%"></div> -->
       <tanZxChart :zxChartData = "this.zxChartData"></tanZxChart>
    </el-dialog>

  </div>
</template>

<script>
// 引入点击放大镜弹出来的表头年限数据
import { getAssetsTableData } from '@/api/tableDemo'
import echarts from 'echarts'
import tanZxChart  from '@/components/Charts/tanZxChart'
  export default {
    data() {
    return {
      // 弹窗
        tableTitle:null,
        zxChartX:[],
        zxChartY:[],
        tanZxChart:null,
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
    created(){
      this.initTableData()
       
    },
    beforeDestroy () {
      // this.zxChartData = null
    },
    mounted() {
      console.log(document.getElementById('tanZxChart'))
    },
    updated(){
    },
    methods: {
      // 初始化数据
      initTableData() {
        getAssetsTableData().then(response => {
          this.tableTitle = response.data.assetsList[0]
          this.tableContent = response.data.assetsList.slice(1)
           this.initChart()
        })
      },
      initChart() {
       this.tanZxChart = echarts.init(document.getElementById('tanZxChart'))
       this.tanZxChart.setOption({
           title: {
                text: 'jkjkjkj'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                 data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                // data:this.zxChartX
            },
            yAxis: {
                      type: 'value'
                    },
            // series:this.zxChartY
             series: [{
                  data: [820, 932, 901, 934, 1290, 1330, 1320],
                  type: 'line'
              }]
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
        // console.log(this.zxChartData)
        this.dialogChartVisible = true;
        this.zxChartX =[this.tableTitle.year1,this.tableTitle.year2,this.tableTitle.year3,this.tableTitle.year4]
        this.zxChartY = [
                       {
                            name:this.zxChartData.project,
                            type:'line',
                            data:[this.zxChartData.count1, this.zxChartData.count2, this.zxChartData.count3,this.zxChartData.count4,this.zxChartData.count5]
                        },
                    ]
           console.log(this.zxChartY)
           console.log(this.zxChartX)
          //  this.initChart()
      }
    }
    }
</script>
<style scoped lang="scss">
.table2{
  background: #f5f7fa;
}
</style>