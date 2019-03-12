<template>
  <div class="table-class">
    <el-table :data="supplierCustomerList.supplierInfoList" border style="width: 100%;margin-top: 20px" show-summary>
      <el-table-column fixed align="center" class-name="table_cell" label="排名" width="50">
         <template slot-scope="scope">
           {{scope.$index+1}}
          </template>
      </el-table-column>
      <el-table-column fixed prop="companyName" align="center" class-name="table_cell" label="公司" width="150"></el-table-column>
      <el-table-column :label="supplierCustomerList.firstYearForSupplier" header-align="center">
        <el-table-column align="right"  class-name="table_cell" label="采购内容" width="117">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.firstYearContent) ? scope.row.firstYearContent : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column align="right"  class-name="table_cell" label="金额(万元)" width="117">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.firstYearAmount) ? scope.row.firstYearAmount : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column align="right"  class-name="table_cell" label="占比" width="117">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.firstYearRatio) ? scope.row.firstYearRatio : '- -'}}</span>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column :label="supplierCustomerList.secondYearForSupplier" header-align="center">
        <el-table-column align="right"  class-name="table_cell" label="采购内容" width="117">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.secondYearContent) ? scope.row.secondYearContent : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column align="right"  class-name="table_cell" label="金额(万元)" width="117">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.secondYearAmount) ? scope.row.secondYearAmount : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column align="right"  class-name="table_cell" label="占比" width="117">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.secondYearRatio ) ? scope.row.secondYearRatio  : '- -'}}</span>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column :label="supplierCustomerList.thirdYearForSupplier" header-align="center">
        <el-table-column align="right"  class-name="table_cell" label="采购内容" width="117">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.thirdYearContent ) ? scope.row.thirdYearContent  : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column align="right"  class-name="table_cell" label="金额(万元)" width="117">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.thirdYearAmount) ? scope.row.thirdYearAmount : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column align="right"  class-name="table_cell" label="占比" width="117">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.thirdYearRatio) ? scope.row.thirdYearRatio : '- -'}}</span>
          </template>
        </el-table-column>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import { getSupplierCustomerData } from '@/api/tableDemo'

export default {
  name: 'fifthGysTable',
  data() {
    return {
      supplierCustomerList:null
    }
  },
  created() {
    this.initTableData()
  },
  methods: {
    initTableData() {
      getSupplierCustomerData().then(response => {
        this.supplierCustomerList = response.data.result
          console.log(this.supplierCustomerList)
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

