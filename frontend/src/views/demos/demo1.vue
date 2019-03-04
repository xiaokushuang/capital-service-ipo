<template>
  <div class="table-class">
    <el-table :data="tableContent" border style="width:100%;margin-bottom: 12px;">
      <el-table-column prop="item" align="center" class-name="table_cell" :label="tableTitle.item" width="52"></el-table-column>
      <el-table-column :label="tableTitle.year1" header-align="center">
        <el-table-column prop="count1" align="right"  class-name="table_cell" label="金额(万元)" width="100">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count1) ? scope.row.count1 : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="ratio1" align="right"  class-name="table_cell" label="占比" width="100">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.ratio1) ? scope.row.ratio1 : '- -'}}</span>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column :label="tableTitle.year2" header-align="center">
        <el-table-column prop="count2" align="right"  class-name="table_cell" label="金额(万元)" width="100">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count2) ? scope.row.count2 : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="ratio2" align="right"  class-name="table_cell" label="占比" width="100">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.ratio2) ? scope.row.ratio2 : '- -'}}</span>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column :label="tableTitle.year3" header-align="center">
        <el-table-column prop="count3" align="right"  class-name="table_cell" label="金额(万元)" width="100">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count3) ? scope.row.count3 : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="ratio3" align="right"  class-name="table_cell" label="占比" width="100">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.ratio3) ? scope.row.ratio3 : '- -'}}</span>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column :label="tableTitle.year4" header-align="center">
        <el-table-column prop="count4" align="right"  class-name="table_cell" label="金额(万元)" width="100">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.count4) ? scope.row.count4 : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="ratio4" align="right"  class-name="table_cell" label="占比" width="100">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.ratio4) ? scope.row.ratio4 : '- -'}}</span>
          </template>
        </el-table-column>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import { getTableData } from '@/api/tableDemo'

export default {
  name: 'demo1',
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
      debugger
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

<style>
  .table-class .el-table thead tr th {
    background: #f2f2f2;
    color: #333;
    border-color: black
  }
</style>

