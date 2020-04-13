<template>
  <div class="table-class mainTable">
    <p v-if="mainTableList.mainIncomeInfoList&&mainTableList.mainIncomeInfoList.length>0" style="font-size:12px;color:#666;float:right">单位：万元</p>
    <el-table v-if="mainTableList.mainIncomeInfoList&&mainTableList.mainIncomeInfoList.length>0" :data="mainTableList.mainIncomeInfoList" border style="width: 100%;margin-top: 20px">
      <el-table-column align="left" class-name="table_cell" label="主营业务" width="135">
         <template slot-scope="scope">
            <span style="padding-left:10px;">{{isNotEmpty(scope.row.businessName) ? scope.row.businessName : '- -'}}</span>
          </template>
      </el-table-column>
      <el-table-column  v-if="keyMap.onePeriodForIncome != undefined" :label="mainTableList.onePeriodForIncome" header-align="center">
        <el-table-column prop="onePeriodAmount" align="right"  class-name="table_cell" label="金额" :width="amountWidth">
          <template slot-scope="scope">
              <span v-if="scope.row.onePeriodAmount"> {{scope.row.onePeriodAmount | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
        <el-table-column prop="onePeriodRatio" align="right"  class-name="table_cell" label="占比" :width="ratioWidth">
            <template slot-scope="scope">
              <span v-if="scope.row.onePeriodRatio"> {{scope.row.onePeriodRatio | dataInThRule}}%</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
      </el-table-column>
      <el-table-column  v-if="keyMap.thirdYearForIncome != undefined" :label="mainTableList.thirdYearForIncome" header-align="center">
        <el-table-column prop="thirdYearAmount" align="right"  class-name="table_cell" label="金额" :width="amountWidth">
          <template slot-scope="scope">
              <span v-if="scope.row.thirdYearAmount"> {{scope.row.thirdYearAmount | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
        <el-table-column prop="thirdYearRatio" align="right"  class-name="table_cell" label="占比" :width="ratioWidth">
           <template slot-scope="scope">
              <span v-if="scope.row.thirdYearRatio"> {{scope.row.thirdYearRatio | dataInThRule}}%</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
      </el-table-column>
      <el-table-column  v-if="keyMap.secondYearForIncome != undefined" :label="mainTableList.secondYearForIncome" header-align="center">
        <el-table-column prop="secondYearAmount" align="right"  class-name="table_cell" label="金额" :width="amountWidth">
           <template slot-scope="scope">
              <span v-if="scope.row.secondYearAmount"> {{scope.row.secondYearAmount | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
        <el-table-column prop="secondYearRatio" align="right"  class-name="table_cell" label="占比" :width="ratioWidth">
           <template slot-scope="scope">
              <span v-if="scope.row.secondYearRatio"> {{scope.row.secondYearRatio | dataInThRule}}%</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
      </el-table-column>
      <el-table-column  v-if="keyMap.firstYearForIncome != undefined" :label="mainTableList.firstYearForIncome" header-align="center">
        <el-table-column prop="firstYearAmount" align="right"  class-name="table_cell" label="金额" :width="amountWidth">
           <template slot-scope="scope">
              <span v-if="scope.row.firstYearAmount"> {{scope.row.firstYearAmount | dataInThRule}}</span>
              <span v-else> - - </span>
           </template>
        </el-table-column>
        <el-table-column prop="firstYearRatio" align="right"  class-name="table_cell" label="占比" :width="ratioWidth">
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
      keyMap:{},
      caseId:this.$store.state.app.caseId,
      amountWidth:'100',
      ratioWidth:'81',
    }
  },
  mounted(){
    this.tableColumnInit()
  },
  props:["mainTableList"],
  methods: {
    /***
     * 乘法，获取精确乘法的结果值
     * @param arg1
     * @param arg2
     * @returns
     */
    accMul(arg1,arg2){
      var m=0,s1=arg1.toString(),s2=arg2.toString();
      try{m+=s1.split(".")[1].length}catch(e){};
      try{m+=s2.split(".")[1].length}catch(e){};
      return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
    },
    /***
     * 除法，获取精确乘法的结果值
     * @param arg1
     * @param arg2
     * @returns
     */
    accDivCoupon(arg1,arg2,digit) {
      var t1=0,t2=0,r1,r2;
      try{t1=arg1.toString().split(".")[1].length}catch(e){}
      try{t2=arg2.toString().split(".")[1].length}catch(e){}
      r1=Number(arg1.toString().replace(".",""))
      r2=Number(arg2.toString().replace(".",""))
      //获取小数点后的计算值
      var result= ((r1/r2)*Math.pow(10,t2-t1)).toFixed(digit).toString()
      var result2=result.split(".")[1];
      result2=result2.substring(0,digit>result2.length?result2.length:digit);

      return Number(result.split(".")[0]+"."+result2);
    },
    tableColumnInit(){
      var keyMapTmp = {"firstYearForIncome": undefined,
        "secondYearForIncome": undefined,
        "thirdYearForIncome": undefined,
        "onePeriodForIncome": undefined};
      for (var i = 0; i < this.mainTableList.mainIncomeInfoList.slice(0,-1).length; i++) {
        var a1 = this.mainTableList.mainIncomeInfoList.slice(0,-1)[i].firstYearAmount >0 ? this.mainTableList.mainIncomeInfoList.slice(0,-1)[i].firstYearAmount : undefined;
        var a2 = this.mainTableList.mainIncomeInfoList.slice(0,-1)[i].secondYearAmount >0 ? this.mainTableList.mainIncomeInfoList.slice(0,-1)[i].secondYearAmount : undefined;
        var a3 = this.mainTableList.mainIncomeInfoList.slice(0,-1)[i].thirdYearAmount >0 ? this.mainTableList.mainIncomeInfoList.slice(0,-1)[i].thirdYearAmount : undefined;
        var a4 = this.mainTableList.mainIncomeInfoList.slice(0,-1)[i].onePeriodAmount >0 ? this.mainTableList.mainIncomeInfoList.slice(0,-1)[i].onePeriodAmount : undefined;
        keyMapTmp.firstYearForIncome = keyMapTmp.firstYearForIncome != undefined ? keyMapTmp.firstYearForIncome : a1 > 0 ? this.mainTableList.firstYearForIncome : undefined;
        keyMapTmp.secondYearForIncome = keyMapTmp.secondYearForIncome != undefined ? keyMapTmp.secondYearForIncome : a2 > 0 ? this.mainTableList.secondYearForIncome : undefined;
        keyMapTmp.thirdYearForIncome = keyMapTmp.thirdYearForIncome != undefined ? keyMapTmp.thirdYearForIncome : a3 > 0 ? this.mainTableList.thirdYearForIncome : undefined;
        keyMapTmp.onePeriodForIncome = keyMapTmp.onePeriodForIncome != undefined ? keyMapTmp.onePeriodForIncome : a4 > 0 ? this.mainTableList.onePeriodForIncome : undefined;
      }

      var  count = 0;
      if ( keyMapTmp.firstYearForIncome != undefined){
        count += 1;
      }
      if ( keyMapTmp.secondYearForIncome != undefined){
        count += 1;
      }
      if ( keyMapTmp.thirdYearForIncome != undefined){
        count += 1;
      }
      if ( keyMapTmp.onePeriodForIncome != undefined){
        count += 1;
      }
      var width = this.accDivCoupon(725,count,2);
      var a1 = this.accMul(width,this.accDivCoupon(100,181,4)) +'';
      var a2 = this.accMul(width,this.accDivCoupon(81,181,4)) + '';
      this.amountWidth = a1;
      this.ratioWidth = a2;
      this.keyMap = keyMapTmp;

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
  .mainTable span{
    display:inline-block
  }
</style>

