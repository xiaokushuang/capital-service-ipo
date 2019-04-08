<template>
  <div class="table-class allAssetsTable">
    <el-table :data="tableContent" border style="width: 100%;margin-top: 20px">
      <el-table-column align="left" class-name="table_cell" label="项目" width="184">
         <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.itemName ) ? scope.row.itemName  : '- -'}}</span>
         </template>
      </el-table-column>
      <el-table-column align="right" :label="tableTitle.forthYearDate">
          <template slot-scope="scope">
                      <span v-if="scope.row.forthYearValue"> 
                        {{scope.row.forthYearValue | dataInThRule}}
                        <span v-if="scope.row.itemName === '资产负债率'">%</span>
                        </span>
                      <span v-else> - - </span>
          </template>
      </el-table-column>
      <el-table-column align="right"  :label="tableTitle.thirdYearDate" header-align="right">
        <template slot-scope="scope">
            <span v-if="scope.row.thirdYearValue"> {{scope.row.thirdYearValue | dataInThRule}}
               <span v-if="scope.row.itemName === '资产负债率'">%</span>
            </span>
            <span v-else> - - </span>
        </template>
      </el-table-column>
      <el-table-column align="right"  :label="tableTitle.secondYearDate" header-align="right">
          <template slot-scope="scope">
            <span v-if="scope.row.secondYearValue"> {{scope.row.secondYearValue | dataInThRule}}
               <span v-if="scope.row.itemName === '资产负债率'">%</span>
            </span>
            <span v-else> - - </span>
        </template>
      </el-table-column>
      <el-table-column align="right"  :label="tableTitle.firstYearDate" header-align="right">
          <template slot-scope="scope">
            <span v-if="scope.row.firstYearValue"> {{scope.row.firstYearValue | dataInThRule}}
               <span v-if="scope.row.itemName === '资产负债率'">%</span>
            </span>
            <span v-else> - - </span>
          </template>
      </el-table-column>
       <el-table-column
        prop=""
        label="趋势"
        align="center">
        <template slot-scope="scope">
            <span @click="handleShowChart(scope.$index, scope.row)" style="color:#1990fe;cursor:pointer">查看</span>           
          </template>
      </el-table-column>
    </el-table>
       <!-- 点击放大镜弹出的折线图 -->
    <el-dialog :title="this.zxChartData?'公司最近三年'  +this.zxChartData.itemName+'_趋势':''" :visible.sync="dialogChartVisible" :before-close="handleClose">
       <tanZxChart1 :zxChartData = "this.zxChartData"></tanZxChart1>
    </el-dialog>

  </div>
</template>
<script>
import { getSelectFinanceOverList } from '@/api/ipoCase/tableDemo'
import tanZxChart1  from '@/components/Charts/tanZxChart1'
export default {
  name: 'mainTable',
  data() {
    return {
      tableTitle: {
        forthYearDate:'',
        thirdYearValue:'',
        secondYearValue:'',
        firstYearValue:''
      },
      tableContent: null,
      zxChartData:null,
      // 控制弹窗是否展示
      dialogChartVisible: false,
      caseId:this.$store.state.app.caseId,
    }
  },
  components:{
    tanZxChart1
  },
  created() {
    this.initTableData()
  },
  methods: {
    initTableData() {
       // 动态传id
      const param = {
        id:this.caseId
      }
      getSelectFinanceOverList(param).then(response => {
          this.tableTitle = response.data.result.dateList
          this.tableContent = response.data.result.ipoFinanceOverList 
      })
    },
    handleClose(){
        this.dialogChartVisible = false
        // this.zxChartData = null
    },
    // 非空判断
    isNotEmpty(param) {
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
  },
  computed: {

  }
}
</script>

<style scoped lang="scss">

</style>

