<template>
  <div class="table-class">
    <el-table :data="tableContent" border style="width: 100%;margin-top: 20px" show-summary>
      <el-table-column fixed prop="rank" align="center" class-name="table_cell" label="排名" width="50"></el-table-column>
      <el-table-column fixed prop="company" align="center" class-name="table_cell" label="公司" width="150"></el-table-column>
      <el-table-column :label="tableTitle.year1" header-align="center">
        <el-table-column prop="content1" align="right"  class-name="table_cell" label="采购内容" width="117">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.content1) ? scope.row.content1 : '- -'}}</span>
          </template>
        </el-table-column>
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
        <el-table-column prop="content2" align="right"  class-name="table_cell" label="采购内容" width="117">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.content2) ? scope.row.content2 : '- -'}}</span>
          </template>
        </el-table-column>
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
        <el-table-column prop="content3" align="right"  class-name="table_cell" label="采购内容" width="117">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.content3) ? scope.row.content3 : '- -'}}</span>
          </template>
        </el-table-column>
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
import { getFifthGysTableData } from '@/api/tableDemo'

export default {
  name: 'fifthGysTable',
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
      getFifthGysTableData().then(response => {
          // console.log(response)
        this.tableTitle = response.data.dataList[0]
        this.tableContent = response.data.dataList.slice(1)
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

