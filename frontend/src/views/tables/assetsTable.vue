<template>
  <div>
    <el-table
      border
      style="width: 100%">
      <el-table-column
        prop=""
        label="项目"
        width="180">
      </el-table-column>
      <el-table-column
        prop=""
        label="year 1">
      </el-table-column>
      <el-table-column
        prop=""
        label="year 2">
      </el-table-column>
      <el-table-column
        prop=""
        label="year 3">
      </el-table-column>
      <el-table-column
        prop=""
        label="趋势">
      </el-table-column>
    </el-table>

    <!-- <el-table
      :data="assetsList"
      :span-method="arraySpanMethod"
      border
      style="width: 100%">
      <el-table-column
        prop="id"
        label="项目"
        width="180">
      </el-table-column>
      <el-table-column
        prop="amount1"
        label="year 1">
      </el-table-column>
      <el-table-column
        prop="amount2"
        label="year 2">
      </el-table-column>
      <el-table-column
        prop="amount3"
        label="year 3">
      </el-table-column>
      <el-table-column
        prop="name"
        label="趋势">
      </el-table-column>
    </el-table> -->
  </div>
</template>

<script>
import { getAssetsTableData } from '@/api/tableDemo'
  export default {
    data() {
      return {
        assetsList: [
            
        {
          id: '12987122',
          name: '王小虎',
          amount1: '234',
          amount2: '3.2',
          amount3: 10
        }, {
          id: '12987123',
          name: '王小虎',
          amount1: '165',
          amount2: '4.43',
          amount3: 12
        }, {
          id: '12987124',
          name: '王小虎',
          amount1: '324',
          amount2: '1.9',
          amount3: 9
        }, {
          id: '12987125',
          name: '王小虎',
          amount1: '621',
          amount2: '2.2',
          amount3: 17
        }, {
          id: '12987126',
          name: '王小虎',
          amount1: '539',
          amount2: '4.1',
          amount3: 15
        }]
      };
    },
    methods: {
      // arraySpanMethod({ row, column, rowIndex, columnIndex }) {
      //   // if (rowIndex % 2 === 0) {
      //   //   if (columnIndex === 0) {
      //   //     return [1, 5];
      //   //   } else if (columnIndex === 1) {
      //   //     return [0, 0];
      //   //   }
      //   // }
      //    if (columnIndex === 0) {
      //     if (rowIndex % 2 === 0) {
      //       return {
      //         rowspan: 2,
      //         colspan: 1
      //       };
      //     } else {
      //       return {
      //         rowspan: 0,
      //         colspan: 0
      //       };
      //     }
      //   }
      // }
      }
    }
</script>