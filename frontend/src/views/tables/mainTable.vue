<template>
  <div class="table-class">
    <el-table :data="tableContent" border style="width: 100%;margin-top: 20px" show-summary>
      <el-table-column align="center" class-name="table_cell" :label="tableTitle.yewu" width="156">
         <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.yewu) ? scope.row.yewu : '- -'}}</span>
          </template>
      </el-table-column>
      <el-table-column :label="tableTitle.year1" header-align="center">
        <el-table-column prop="count1" align="right"  class-name="table_cell" label="金额(万元)" width="117">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count1) ? scope.row.count1 : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="ratio1" align="right"  class-name="table_cell" label="占比" width="117">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.ratio1) ? scope.row.ratio1 : '- -'}}</span>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column :label="tableTitle.year2" header-align="center">
        <el-table-column prop="count2" align="right"  class-name="table_cell" label="金额(万元)" width="117">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count2) ? scope.row.count2 : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="ratio2" align="right"  class-name="table_cell" label="占比" width="117">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.ratio2) ? scope.row.ratio2 : '- -'}}</span>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column :label="tableTitle.year3" header-align="center">
        <el-table-column prop="count3" align="right"  class-name="table_cell" label="金额(万元)" width="117">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count3) ? scope.row.count3 : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="ratio3" align="right"  class-name="table_cell" label="占比" width="117">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.ratio3) ? scope.row.ratio3 : '- -'}}</span>
          </template>
        </el-table-column>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import { getTableData } from '@/api/tableDemo'

export default {
  name: 'mainTable',
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
      getTableData().then(response => {
        this.tableTitle = response.data.result[0]
        this.tableContent = response.data.result.slice(1)
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

