<template>
  <div class="table-class">
    <el-table :data="industryCompareRateDetailList" border style="width: 100%;margin-top: 20px">
      <el-table-column prop="companyName" align="left" class-name="table_cell" label="企业名称" width="156" ></el-table-column>
      <el-table-column align="center" :label="year3" header-align="center">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.thirdYearRate) ? scope.row.thirdYearRate : '- -'}}</span>
          </template>
      </el-table-column>
      <el-table-column align="center" :label="year2" header-align="center" >
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.secondYearRate) ? scope.row.secondYearRate : '- -'}}</span>
          </template>
      </el-table-column>
      <el-table-column align="center" :label="year1" header-align="center" >
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.firstYearRate) ? scope.row.firstYearRate : '- -'}}</span>
          </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import { getMaoChartTableData } from '@/api/ipoCase/tableDemo'
export default {
  name: 'compareTable',
  data() {
    return {
      tableTitle: null,
      tableContent: null,
      year1:'',
      year2:'',
      year3:'',
      industryCompareRateDetailList:[]
    }
  },
  created() {
    this.initTableData()
  },
  mounted(){
  },
  methods: {
    initTableData() {
      getMaoChartTableData().then(res => {
          console.log(res.data.result)
          for(let i = 0;i<res.data.result.length;i++){
            this.year1 = res.data.result[i].firstYear
            this.year2 = res.data.result[i].secondYear
            this.year3 = res.data.result[i].thirdYear
            this.industryCompareRateDetailList = res.data.result[i].industryCompareRateDetailList
          }
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
    }
  },
  computed: {

  }
}
</script>

<style scoped lang="scss">

</style>

