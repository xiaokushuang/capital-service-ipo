<template>
  <div class="table-class">
    <el-table :data="tableContent" border style="width: 100%;margin-top: 20px" show-summary>
      <el-table-column align="center" class-name="table_cell" label="项目" width="156">
         <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.yewu) ? scope.row.yewu : '- -'}}</span>
          </template>
      </el-table-column>
      <el-table-column :label="tableTitle.year1" header-align="center">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.ratio1) ? scope.row.ratio1 : '- -'}}</span>
          </template>       
      </el-table-column>
      <el-table-column :label="tableTitle.year2" header-align="center">
        <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.ratio1) ? scope.row.ratio1 : '- -'}}</span>
        </template>  
      </el-table-column>
      <el-table-column :label="tableTitle.year3" header-align="center">
         <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.ratio1) ? scope.row.ratio1 : '- -'}}</span>
         </template>  
      </el-table-column>
      <el-table-column :label="tableTitle.year4" header-align="center">
         <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.ratio1) ? scope.row.ratio1 : '- -'}}</span>
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
  created() {
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

