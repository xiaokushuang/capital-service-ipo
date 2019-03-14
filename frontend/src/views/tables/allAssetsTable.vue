<template>
  <div class="table-class">
    <el-table :data="tableContent" border style="width: 100%;margin-top: 20px" show-summary>
      <el-table-column align="center" class-name="table_cell" label="项目" width="156">
         <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.project) ? scope.row.project : '- -'}}</span>
          </template>
      </el-table-column>
      <el-table-column :label="tableTitle.year1" header-align="center">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count1) ? scope.row.count1 : '- -'}}</span>
          </template>       
      </el-table-column>
      <el-table-column :label="tableTitle.year2" header-align="center">
        <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count2) ? scope.row.count2 : '- -'}}</span>
        </template>  
      </el-table-column>
      <el-table-column :label="tableTitle.year3" header-align="center">
         <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count3) ? scope.row.count3 : '- -'}}</span>
         </template>  
      </el-table-column>
      <el-table-column :label="tableTitle.year4" header-align="center">
         <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count4) ? scope.row.count4 : '- -'}}</span>
         </template>  
      </el-table-column>
       <el-table-column
        prop=""
        label="趋势"
        align="center">
        <template slot-scope="scope">
            <span @click="handleShowChart(scope.$index, scope.row)"><i class="el-icon-search"></i></span>           
            <!-- <span>{{isNotEmpty(scope.row.count4) ? scope.row.count4 : '- -'}}</span> -->
          </template>
      </el-table-column>
    </el-table>
       <!-- 点击放大镜弹出的折线图 -->
    <el-dialog :title="this.zxChartData?'公司最近三年'  +this.zxChartData.project+'_趋势':''" :visible.sync="dialogChartVisible">
       <tanZxChart1 :zxChartData = "this.zxChartData"></tanZxChart1>
    </el-dialog>

  </div>
</template>
<script>
import { getAssetsTableData } from '@/api/tableDemo'
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
      getAssetsTableData().then(response => {
        console.log(response)
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
      console.log(this.zxChartData)
      this.dialogChartVisible = true;
    }
  },
  computed: {

  }
}
</script>

<style scoped lang="scss">

</style>

