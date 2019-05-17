<template>
  <div class="table-class allAssetsTable">
    <el-table :data="MainIndexTableList.content" border style="width: 100%;margin-top: 20px">
      <el-table-column align="left" class-name="table_cell" label="项目" width="184">
         <template slot-scope="scope">
            <span>
              {{isNotEmpty(scope.row.itemName ) ? scope.row.itemName  : '- -'}}
              <span v-if="scope.row.itemName === '流动比率'">(倍)</span>
              <span v-if="scope.row.itemName === '速动比率'">(倍)</span>
              <span v-if="scope.row.itemName === '资产负债率'">(%)</span>
              <span v-if="scope.row.itemName === '无形资产占净资产的比例'">(%)</span>
              <span v-if="scope.row.itemName === '加权平均净资产收益率'">(%)</span>
              <span v-if="scope.row.itemName === '应收账款周转率'">(次)</span>
              <span v-if="scope.row.itemName === '息税折旧摊销前利润/负债合计'">(万元)</span>
              <span v-if="scope.row.itemName === '基本每股收益'">(元/股)</span>
              <span v-if="scope.row.itemName === '扣除非经常性损益后的基本每股收益'">(元/股)</span>
            </span>
         </template>
      </el-table-column>
      <el-table-column align="right" :label="MainIndexTableList.title.forthYearDate">
          <template slot-scope="scope">
                      <span v-if="scope.row.forthYearValue"> 
                        {{scope.row.forthYearValue | dataInThRule}}
                      </span>
                      <span v-else> - - </span>
          </template>
      </el-table-column>
      <el-table-column align="right"  :label="MainIndexTableList.title.thirdYearDate" header-align="right">
        <template slot-scope="scope">
            <span v-if="scope.row.thirdYearValue"> {{scope.row.thirdYearValue | dataInThRule}}
            </span>
            <span v-else> - - </span>
        </template>
      </el-table-column>
      <el-table-column align="right"  :label="MainIndexTableList.title.secondYearDate" header-align="right">
          <template slot-scope="scope">
            <span v-if="scope.row.secondYearValue"> {{scope.row.secondYearValue | dataInThRule}}
            </span>
            <span v-else> - - </span>
        </template>
      </el-table-column>
      <el-table-column align="right"  :label="MainIndexTableList.title.firstYearDate" header-align="right">
          <template slot-scope="scope">
            <span v-if="scope.row.firstYearValue"> {{scope.row.firstYearValue | dataInThRule}}
            </span>
            <span v-else> - - </span>
          </template>
      </el-table-column>
       <el-table-column
        prop=""
        label="趋势"
        align="center">
        <template slot-scope="scope">
            <span @click="handleShowChart(scope.$index, scope.row)" style="color:#1990fe;cursor:pointer">查看</span>           
          </template>
      </el-table-column>
    </el-table>
       <!-- 点击放大镜弹出的折线图 -->
    <el-dialog :title="this.zxChartData?'公司最近三年'  +this.zxChartData.itemName+'_趋势':''" :visible.sync="dialogChartVisible" :before-close="handleClose">
       <!-- <tanZxChart3 :zxChartData = "this.zxChartData" :zxChartTitle = "MainIndexTableList.title"></tanZxChart3> -->
       <!-- <tanZxChart3 :zxChartData = {zxChartTitle:this.zxChartData,zxChartContent:MainIndexTableList.content}></tanZxChart3> -->
        <tanZxChart3 :zxChartData = "this.zxChartData"></tanZxChart3>
    </el-dialog>

  </div>
</template>
<script>
import tanZxChart3  from '@/components/Charts/tanZxChart3'
export default {
  name: 'mainTable',
  data() {
    return {
      allAssetsTableTitle: {
        forthYearDate:'',
        thirdYearValue:'',
        secondYearValue:'',
        firstYearValue:''
      },
      allAssetsTableContent: null,
      zxChartData:null,
      // 控制弹窗是否展示
      dialogChartVisible: false,
      caseId:this.$store.state.app.caseId,
    }
  },
  props:[ "MainIndexTableList"],

    components:{
    tanZxChart3
  },
  created() {
  },
  mounted () {
  },
  methods: {
    handleClose(){
        this.dialogChartVisible = false
    },
    // 非空判断
    isNotEmpty(param) {
      if (param != null && param !== undefined && param !== '' && param !== 'null' && param !== 'undefined') {
        return true
      } else {
        return false
      }
    },
      // 点击放大镜弹出折线图
    handleShowChart(i,r){
      this.zxChartData = r
      console.log('111', this.zxChartData)
      console.log('222',this.MainIndexTableList.title)
      this.dialogChartVisible = true;
    }
  },
  computed: {

  }
}
</script>

<style scoped lang="scss">

</style>

