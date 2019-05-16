<template>
  <div>
    <el-table
      border
      style="width: 100%">
      <el-table-column
        prop=""
        label="项目"
        width="184">
      </el-table-column>
      <el-table-column
        prop=""
       :label="assetsOrDeptTableList[0].forthYearDate?assetsOrDeptTableList[0].forthYearDate:''"
       align="right">
      </el-table-column>
      <el-table-column
        prop=""
        :label="assetsOrDeptTableList[0].thirdYearDate?assetsOrDeptTableList[0].thirdYearDate:''"
        align="right">
      </el-table-column>
      <el-table-column
        prop=""
        :label="assetsOrDeptTableList[0].secondYearDate?assetsOrDeptTableList[0].secondYearDate:''"
        align="right">
      </el-table-column>
       <el-table-column
        prop=""
        :label="assetsOrDeptTableList[0].firstYearDate?assetsOrDeptTableList[0].firstYearDate:''"
        align="right">
      </el-table-column>
      <el-table-column
        prop=""
        label="趋势"
        align="center">
      </el-table-column>
    </el-table>
    <p v-if="assetsOrDeptTableList[1]&&assetsOrDeptTableList[1].length>0" class="sortProject">资产类项目：</p>
    <el-table 
      class="table2"
     :data="assetsOrDeptTableList[1]"
      border
      style="width: 100%">
       <el-table-column prop="itemName" align="left"  class-name="table_cell" label="占比" width="184">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.itemName) ? scope.row.itemName : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="forthYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
            <span v-if="scope.row.forthYearValue"> {{scope.row.forthYearValue | dataInThRule}}</span>
            <span v-else> - - </span>
          </template>
        </el-table-column>
         <el-table-column prop="thirdYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
            <span v-if="scope.row.thirdYearValue"> {{scope.row.thirdYearValue | dataInThRule}}</span>
            <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="secondYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
            <span v-if="scope.row.secondYearValue"> {{scope.row.secondYearValue | dataInThRule}}</span>
            <span v-else> - - </span>
          </template>
        </el-table-column>
         <el-table-column prop="firstYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
            <span v-if="scope.row.firstYearValue"> {{scope.row.firstYearValue | dataInThRule}}</span>
            <span v-else> - - </span>
          </template>
        </el-table-column>
         <el-table-column prop="" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span @click="handleShowChart(scope.$index, scope.row)" style="color:#1990fe;cursor:pointer">查看</span>
          </template>
        </el-table-column>
     
    </el-table>
     <p v-if="assetsOrDeptTableList[2]&&assetsOrDeptTableList[2].length>0" class="sortProject">负债类项目：</p>
    <el-table
      class="table2"
     :data="assetsOrDeptTableList[2]"
      border
      style="width: 100%">
       <el-table-column prop="itemName" align="left"  class-name="table_cell" label="占比" width="184">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.itemName) ? scope.row.itemName : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="forthYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
              <span v-if="scope.row.forthYearValue"> {{scope.row.forthYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="thirdYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
            <span v-if="scope.row.thirdYearValue"> {{scope.row.thirdYearValue | dataInThRule}}</span>
            <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="secondYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
              <span v-if="scope.row.secondYearValue"> {{scope.row.secondYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="firstYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
              <span v-if="scope.row.firstYearValue"> {{scope.row.firstYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span @click="handleShowChart(scope.$index, scope.row)" style="color:#1990fe;cursor:pointer">查看</span>             
          </template>
        </el-table-column>
     
    </el-table>
     <p v-if="assetsOrDeptTableList[3]&&assetsOrDeptTableList[3].length>0" class="sortProject"> 权益类项目：</p>
     <el-table
      class="table2"
     :data="assetsOrDeptTableList[3]"
      border
      style="width: 100%">
       <el-table-column prop="itemName" align="left"  class-name="table_cell" label="占比" width="184">
          <template slot-scope="scope">
            <span>{{isNotEmpty(scope.row.itemName) ? scope.row.itemName : '- -'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="forthYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
              <span v-if="scope.row.forthYearValue"> {{scope.row.forthYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="thirdYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
              <span v-if="scope.row.thirdYearValue"> {{scope.row.thirdYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="secondYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
              <span v-if="scope.row.secondYearValue"> {{scope.row.secondYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="firstYearValue" align="right"  class-name="table_cell" label="占比" width="135">
           <template slot-scope="scope">
              <span v-if="scope.row.firstYearValue"> {{scope.row.firstYearValue | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
         <el-table-column prop="" align="center"  class-name="table_cell" label="占比" width="135">
          <template slot-scope="scope">
            <span @click="handleShowChart(scope.$index, scope.row)" style="color:#1990fe;cursor:pointer">查看</span>           
          </template>
        </el-table-column>
     
    </el-table>
    <!-- 点击放大镜弹出的折线图 -->
    <el-dialog :title="this.zxChartData?'公司最近三年'  +this.zxChartData.itemName+'_趋势':''" :visible.sync="dialogChartVisible">
       <!-- <tanZxChart :zxChartData = "this.zxChartData"></tanZxChart> -->
       <tanZxChart3 :zxChartData = "this.zxChartData" :zxChartTitle = "assetsOrDeptTableList[0]"></tanZxChart3>
    </el-dialog>

  </div>
</template>

<script>
// 引入点击放大镜弹出来的表头年限数据
import { getAssetsOrDebtData } from '@/api/ipoCase/tableDemo'
import echarts from 'echarts'
import tanZxChart3  from '@/components/Charts/tanZxChart'
  export default {
    data() {
    return {
      // 弹窗
        tanZxChart:null,
         assetsOrDeptTableTitle: {
        forthYearDate:'',
        thirdYearValue:'',
        secondYearValue:'',
        firstYearValue:''
      },
        ipoAssetItemList:[],//资产类项目列表
        ipoDebtItemList:[],//负债类项目列表
        ipoEquityItemList:[],//权益类项目列表
        tableContent: null,
      // 控制弹窗是否展示
        dialogChartVisible: false,
        zxChartData:null,
        // id:'97952444248599350',
        caseId:this.$store.state.app.caseId,
    }
  },
    components:{
      tanZxChart3
    },
    props:[ "assetsOrDeptTableList"],
    created(){
      // this.initTableData()
       
    },
    beforeDestroy () {
    },
    mounted() {
    },
    updated(){
    },
    methods: {
      // 初始化数据
      initTableData() {
            // 动态传id
        const param = {
          id:this.caseId
        }
        getAssetsOrDebtData(param).then(res => {
          this.assetsOrDeptTableTitle = res.data.result.dateList
          this.ipoAssetItemList = res.data.result.ipoAssetItemList//资产类项目列表
          this.ipoDebtItemList = res.data.result.ipoDebtItemList//负债类项目列表
          this.ipoEquityItemList = res.data.result.ipoEquityItemList//权益类项目列表
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
      },
      // 点击放大镜弹出折线图
      handleShowChart(i,r){
        this.zxChartData = r
        this.dialogChartVisible = true;
      }
    }
    }
</script>
<style scoped lang="scss">
.table2{
  background: #f5f7fa;
}
.sortProject{
    background: #f2f2f2;
    font-weight: 700;
    line-height: 11px;
    font-style: normal;
    font-size: 14px;
    text-align: left;
    margin: 0px;
    padding-top: 14px;
    padding-bottom: 14px;
    padding-left: 12px;
}
</style>