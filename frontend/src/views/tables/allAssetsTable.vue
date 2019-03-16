<template>
  <div class="table-class">
    <el-table :data="tableContent" border style="width: 100%;margin-top: 20px" show-summary>
      <el-table-column align="left" class-name="table_cell" label="项目" width="156">
         <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.itemName ) ? scope.row.itemName  : '- -'}}</span>
          </template>
      </el-table-column>
      <el-table-column align="right" :label="tableTitle.forthYearDate">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.forthYearValue) ? scope.row.forthYearValue : '- -'}}</span>
          </template>       
      </el-table-column>
      <el-table-column align="right"  :label="tableTitle.thirdYearDate" header-align="center">
        <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.thirdYearValue) ? scope.row.thirdYearValue : '- -'}}</span>
        </template>  
      </el-table-column>
      <el-table-column align="right"  :label="tableTitle.secondYearDate" header-align="center">
         <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.secondYearValue) ? scope.row.secondYearValue : '- -'}}</span>
         </template>  
      </el-table-column>
      <el-table-column align="right"  :label="tableTitle.firstYearDate" header-align="center">
         <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.firstYearValue) ? scope.row.firstYearValue : '- -'}}</span>
         </template>  
      </el-table-column>
       <el-table-column
        prop=""
        label="趋势"
        align="center">
        <template slot-scope="scope">
            <span @click="handleShowChart(scope.$index, scope.row)" style="color:#1990fe">查看</span>           
            <!-- <span>{{isNotEmpty(scope.row.count4) ? scope.row.count4 : '- -'}}</span> -->
          </template>
      </el-table-column>
    </el-table>
       <!-- 点击放大镜弹出的折线图 -->
    <el-dialog :title="this.zxChartData?'公司最近三年'  +this.zxChartData.itemName+'_趋势':''" :visible.sync="dialogChartVisible">
       <tanZxChart1 :zxChartData = "this.zxChartData"></tanZxChart1>
    </el-dialog>

  </div>
</template>
<script>
// Mock.mock(/\/finance\/selectFinanceOverList/, 'get', tableDemo.getSelectFinanceOverList)
// import { getAssetsTableData } from '@/api/tableDemo'
import { getSelectFinanceOverList } from '@/api/tableDemo'
import tanZxChart1  from '@/components/Charts/tanZxChart1'
export default {
  name: 'mainTable',
  data() {
    return {
      tableTitle: null,
      tableContent: null,
      zxChartData:null,
      // 控制弹窗是否展示
      dialogChartVisible: false,
    }
  },
  components:{
    tanZxChart1
  },
  created() {
    this.initTableData()
  },
  mounted(){
    console.log('aaaa')
  },
  methods: {
    initTableData() {
      getSelectFinanceOverList().then(response => {
        console.log(response)
        this.tableTitle = response.data.result.dateList
        this.tableContent = response.data.result.ipoFinanceOverList 
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
    }
  },
  computed: {

  }
}
</script>

<style scoped lang="scss">

</style>

