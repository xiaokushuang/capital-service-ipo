<template>
  <div class="table-class mainTable">
    <p v-if="mainTableList.mainIncomeInfoList&&mainTableList.mainIncomeInfoList.length>0" style="font-size:12px;color:#666;float:right">单位：万元</p>
    <el-table v-if="mainTableList.mainIncomeInfoList&&mainTableList.mainIncomeInfoList.length>0" :data="mainTableList.mainIncomeInfoList" border style="width: 100%;margin-top: 20px">
      <el-table-column align="left" class-name="table_cell" label="主营业务" width="135">
         <template slot-scope="scope">
            <span style="padding-left:10px;">{{isNotEmpty(scope.row.businessName) ? scope.row.businessName : '- -'}}</span>
          </template>
      </el-table-column>
       <el-table-column :label="mainTableList.onePeriodForIncome" header-align="center">
        <el-table-column prop="onePeriodAmount" align="right"  class-name="table_cell" label="金额" width="100">
          <template slot-scope="scope">
              <span v-if="scope.row.onePeriodAmount"> {{scope.row.onePeriodAmount | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
        <el-table-column prop="onePeriodRatio" align="right"  class-name="table_cell" label="占比" width="81">
            <template slot-scope="scope">
              <span v-if="scope.row.onePeriodRatio"> {{scope.row.onePeriodRatio | dataInThRule}}%</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
      </el-table-column>
      <el-table-column :label="mainTableList.thirdYearForIncome" header-align="center">
        <el-table-column prop="thirdYearAmount" align="right"  class-name="table_cell" label="金额" width="100">
          <template slot-scope="scope">
              <span v-if="scope.row.thirdYearAmount"> {{scope.row.thirdYearAmount | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
        <el-table-column prop="thirdYearRatio" align="right"  class-name="table_cell" label="占比" width="81">
           <template slot-scope="scope">
              <span v-if="scope.row.thirdYearRatio"> {{scope.row.thirdYearRatio | dataInThRule}}%</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
      </el-table-column>
      <el-table-column :label="mainTableList.secondYearForIncome" header-align="center">
        <el-table-column prop="secondYearAmount" align="right"  class-name="table_cell" label="金额" width="100">
           <template slot-scope="scope">
              <span v-if="scope.row.secondYearAmount"> {{scope.row.secondYearAmount | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
        <el-table-column prop="secondYearRatio" align="right"  class-name="table_cell" label="占比" width="81">
           <template slot-scope="scope">
              <span v-if="scope.row.secondYearRatio"> {{scope.row.secondYearRatio | dataInThRule}}%</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
      </el-table-column>
      <el-table-column :label="mainTableList.firstYearForIncome" header-align="center">
        <el-table-column prop="firstYearAmount" align="right"  class-name="table_cell" label="金额" width="100">
           <template slot-scope="scope">
              <span v-if="scope.row.firstYearAmount"> {{scope.row.firstYearAmount | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
        <el-table-column prop="firstYearRatio" align="right"  class-name="table_cell" label="占比" width="81">
         <template slot-scope="scope">
              <span v-if="scope.row.firstYearRatio"> {{scope.row.firstYearRatio | dataInThRule}}%</span>
              <span v-else> - - </span>
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
  .mainTable span{
    display:inline-block
  }
</style>

