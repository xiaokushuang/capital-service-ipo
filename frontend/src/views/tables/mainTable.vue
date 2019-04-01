<template>
  <div class="table-class mainTable">
    <el-table :data="mainTableList.mainIncomeInfoList" border style="width: 100%;margin-top: 20px" show-summary>
      <el-table-column align="left" class-name="table_cell" label="主营业务" width="135">
         <template slot-scope="scope">
            <span style="padding-left:10px;">{{isNotEmpty(scope.row.businessName) ? scope.row.businessName : '- -'}}</span>
          </template>
      </el-table-column>
       <el-table-column :label="mainTableList.onePeriodForIncome" header-align="center">
        <el-table-column prop="onePeriodAmount" align="right"  class-name="table_cell" label="金额(万元)" width="100">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.onePeriodAmount) ? scope.row.onePeriodAmount : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="onePeriodRatio" align="right"  class-name="table_cell" label="占比(%)" width="81">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.onePeriodRatio) ? scope.row.onePeriodRatio : '- -'}}</span>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column :label="mainTableList.thirdYearForIncome" header-align="center">
        <el-table-column prop="thirdYearAmount" align="right"  class-name="table_cell" label="金额(万元)" width="100">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.thirdYearAmount) ? scope.row.thirdYearAmount : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="thirdYearRatio" align="right"  class-name="table_cell" label="占比(%)" width="81">
          <template slot-scope="scope">·
            <span>{{isNotEmpty(scope.row.thirdYearRatio) ? scope.row.thirdYearRatio : '- -'}}</span>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column :label="mainTableList.secondYearForIncome" header-align="center">
        <el-table-column prop="secondYearAmount" align="right"  class-name="table_cell" label="金额(万元)" width="100">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.secondYearAmount) ? scope.row.secondYearAmount : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="secondYearRatio" align="right"  class-name="table_cell" label="占比(%)" width="81">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.secondYearRatio) ? scope.row.secondYearRatio : '- -'}}</span>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column :label="mainTableList.firstYearForIncome" header-align="center">
        <el-table-column prop="firstYearAmount" align="right"  class-name="table_cell" label="金额(万元)" width="100">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.firstYearAmount) ? scope.row.firstYearAmount : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="firstYearRatio" align="right"  class-name="table_cell" label="占比(%)" width="81">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.firstYearRatio) ? scope.row.firstYearRatio : '- -'}}</span>
          </template>
        </el-table-column>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
export default {
  name: 'mainTable',
  data() {
    return {
      caseId:this.$store.state.app.caseId,
    }
  },
  props:["mainTableList"],
  methods: {
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

