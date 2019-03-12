<template>
  <div class="table-class">
    <el-table :data="tableContent" border style="width: 100%;margin-top: 20px">
      <el-table-column prop="company" align="left" class-name="table_cell" label="企业名称" width="156" ></el-table-column>
      <el-table-column align="center" :label="tableTitle.year1" header-align="center">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.proportion1) ? scope.row.proportion1 : '- -'}}</span>
          </template>
      </el-table-column>
      <el-table-column align="center" :label="tableTitle.year2" header-align="center" >
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.proportion2) ? scope.row.proportion2 : '- -'}}</span>
          </template>
      </el-table-column>
      <el-table-column align="center" :label="tableTitle.year3" header-align="center" >
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.proportion3) ? scope.row.proportion3 : '- -'}}</span>
          </template>
      </el-table-column>
     
     
    </el-table>
  </div>
</template>
<script>
import { getCompareTableData } from '@/api/tableDemo'

export default {
  name: 'compareTable',
  data() {
    return {
      tableTitle: null,
      tableContent: null
    }
  },
  mounted() {
    this.initTableData()
  },
  methods: {
    initTableData() {
      getCompareTableData().then(response => {
       this.tableTitle = response.data.compareList[0]
        this.tableContent = response.data.compareList.slice(1)
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

