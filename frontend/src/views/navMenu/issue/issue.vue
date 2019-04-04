<template>
    <div class="issue">
        <!-- 发行数据 -->
        <div class="issueData">
             <div class="title">
                <span class="littleRectangle"></span>
                <span class="titleText" id="distributionData">发行数据</span>
            </div>
            <!-- table1 -->
             <el-row style="margin-bottom: 30px;padding: 0 12px;">
                <el-col :span="24" :offset="0">
                  <el-row :gutter="24" class="simulation_table">
                      <el-col :span="6" class="label"><span>每股面值</span></el-col>
                      <el-col :span="6" class="value">
                        <span v-if="issueData.parValue"> {{issueData.parValue | dataInThRule}} 元/股</span>
                        <span v-else> - - </span>
                      </el-col>
                      <el-col :span="6" class="label"><span>发行价格</span></el-col>
                      <el-col :span="6" class="value"> 
                           <span v-if="issueData.issuePrice"> {{issueData.issuePrice | dataInThRule}} 万元</span>
                           <span v-else> - - </span>
                       </el-col>
                  </el-row>
                  <el-row :gutter="24" class="simulation_table">
                      <el-col :span="6" class="label"><span>发行数量总计</span></el-col>
                      <el-col :span="6" class="value">
                           <span v-if="issueData.shareIssued"> {{issueData.shareIssued | dataInThRule}} 万股</span>
                           <span v-else> - - </span>
                      </el-col>
                      <el-col :span="6" class="label"><span>占发行后总股本的比例</span></el-col>
                      <el-col :span="6" class="value">
                         <span v-if="issueData.issuedRatio"> {{issueData.issuedRatio | dataInThRule}} %</span>
                         <span v-else> - - </span>
                      </el-col>
                  </el-row>
                  <el-row :gutter="24" class="simulation_table">
                      <el-col :span="6" class="label"><span>募集资金总额</span></el-col>
                      <el-col :span="6" class="value">
                           <span v-if="issueData.sumFina"> {{issueData.sumFina | dataInThRule}} 万元</span>
                           <span v-else> - - </span>
                      </el-col>
                      <el-col :span="6" class="label"><span>募集资金净额</span></el-col>
                      <el-col :span="6" class="value">
                         <span v-if="issueData.netSumFina"> {{issueData.netSumFina | dataInThRule}} 万元</span>
                         <span v-else> - - </span>
                      </el-col>
                  </el-row>
                    <el-row :gutter="24" class="simulation_table">
                      <el-col :span="6" class="label"><span>网上发行数量</span></el-col>
                      <el-col :span="6" class="value">
                        <span v-if="issueData.shareIssueOn"> {{issueData.shareIssueOn | dataInThRule}} 万元</span>
                        <span v-else> - - </span>
                      </el-col>
                      <el-col :span="6" class="label"><span>网下配售数量</span></el-col>
                      <el-col :span="6" class="value"> 
                        <span v-if="issueData.sharePlaceOff"> {{issueData.sharePlaceOff | dataInThRule}} 万股</span>
                        <span v-else> - - </span>
                      </el-col>
                  </el-row>
                    <el-row :gutter="24" class="simulation_table">
                      <el-col :span="6" class="label"><span>老股东公开发售股份</span></el-col>
                      <el-col :span="6" class="value">
                        <span v-if="issueData.exseNumBse"> {{issueData.exseNumBse | dataInThRule}} 万股</span>
                        <span v-else> - - </span>
                      </el-col>
                      <el-col :span="6" class="label"><span>发行后市盈率</span></el-col>
                      <el-col :span="6" class="value">
                         <span v-if="issueData.peIssueA"> {{issueData.peIssueA | dataInThRule}} %</span>
                        <span v-else> - - </span>
                      </el-col>
                  </el-row>  
                  <el-row :gutter="24" class="simulation_table">
                      <el-col :span="6" class="label"><span>发行前每股收益</span></el-col>
                      <el-col :span="6" class="value">
                      <span v-if="issueData.epsIssueB"> {{issueData.epsIssueB | dataInThRule}} 万/股</span>
                      <span v-else> - - </span>
                      </el-col>
                      <el-col :span="6" class="label"><span>发行后每股收益</span></el-col>
                      <el-col :span="6" class="value">
                        <span v-if="issueData.epsIssueA"> {{issueData.epsIssueA | dataInThRule}} 万/股</span>
                        <span v-else> - - </span>
                       </el-col>
                  </el-row>
                    <el-row :gutter="24" class="simulation_table">
                      <el-col :span="6" class="label"><span>发行方式</span></el-col>
                      <el-col :span="6" class="value">
                       <span v-if="issueData.issueMethod" @mouseenter="mouseOverSpreadText(issueData.issueMethod)" class="distribution" style="cursor:pointer">
                          {{getContent(issueData.issueMethod)}}
                      </span> 
                      <span v-else> - - </span>
                      </el-col>
                      <el-col :span="6" class="label"><span>承销方式</span></el-col>
                      <el-col :span="6" class="value">
                        <span>
                          {{isNotEmpty(issueData.uwMethod) ? issueData.uwMethod : '- -' + '&nbsp;'}}
                        </span>
                      </el-col>
                  </el-row>
                </el-col>
            </el-row>
        </div>
        <!-- 发行费用 -->
        <div class="issueMoney">
             <div class="title">
                <span class="littleRectangle"></span>
                <span class="titleText" id="distributionCosts">发行费用</span>
            </div>
            <!-- table2 -->
             <el-table :data="issueFeeData" style="width: 100%" stripe border show-summary>
                <el-table-column type="index" label="序号" align='center' width="117">
                <template slot-scope="scope">
                            {{scope.$index+1}}
                </template>
                </el-table-column>
                <el-table-column style="width:30%" prop="feeType" label="费用明细" align='left'>
                </el-table-column>
                <el-table-column style="width:30%" prop="feeAmount" label="金额（人民币/万元）" align='right'>
                   <template slot-scope="scope">
                      <span v-if="scope.row.feeAmount"> {{scope.row.feeAmount | dataInThRule}}</span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
                <el-table-column style="width:30%" prop="feeRatio" label="占募集资金总额比例(%)" align='right'>
                   <template slot-scope="scope">
                      <span v-if="scope.row.feeRatio"> {{scope.row.feeRatio | dataInThRule}}</span>
                      <span v-else> - - </span>
                  </template>
                </el-table-column>
            </el-table>
        </div>
        <!-- 占位取高度 -->
        <div id="placeholderHeight" style="visibility:hidden">11111</div>
    </div>
</template>
<script>
import { getIssueData } from "@/api/ipoCase/tableDemo";
import { getIssueFeeData } from "@/api/ipoCase/tableDemo";
import $ from "jquery";

export default {
  name: "issue",
  data() {
      return{
          issueFeeData:[],
          issueData:{
            epsIssueA:'',
            epsIssueB:'',
            exseNumBse:'',
            issueMethod:'',
            issuePrice:'',
            issuedRatio:'',
            netSumFina:'',
            parValue:'',
            peIssueA:'',
            shareIssueOn:'',
            shareIssued:'',
            sharePlaceOff:'',
            sumFina:'',
            uwMethod:'',
          },
          caseId:this.$store.state.app.caseId,
      }
  },
   created() {
    this.initTableData()
    this.getPosition()
  },
  mounted(){
  },
  methods: {
     initTableData() {
       // 动态传id
      const param = {
        id:this.caseId
      }
       getIssueData(param).then(res => {
         if(res.data.result){
           this.issueData = res.data.result
         }
      }) 
      getIssueFeeData(param).then(res=>{
        if(res.data.result&&res.data.result.length>0){
          this.issueFeeData = res.data.result
          
        }
      })
    },
     getPosition(){
           //返回父组件用于锚点定位头
                let titleList = [];
                let distributionData = {
                    id: 'distributionData',
                    name: '发行数据',
                    notes: '',
                    important: false,
                    tabId: 'tab-fifth',
                    noClick: false
                }
                let distributionCosts = {
                    id: 'distributionCosts',
                    name: '发行费用',
                    notes: '',
                    important: false,
                    tabId: 'tab-fifth',
                    noClick: false
                }              
                titleList.push(distributionData)
                titleList.push(distributionCosts)
                this.$emit('headCallBack', titleList);
            //返回父组件用于锚点定位尾
     },
    //  
     // 鼠标移入表格内容加title
    mouseOverSpreadText(title){
        $(".distribution").attr("title",title)
    },
   getContent(title){
      return title.substring(0,13) + '...'
   },
    // 非空判断
    isNotEmpty(param) {
      if (param != null && param !== undefined && param !== '' && param !== 'null' && param !== 'undefined') {
        return true
      } else {
        return false
      }
    },
  },
  components: {}
};
</script>
<style scoped lang="scss">
.title {
  border-bottom: 1px solid;
  border-bottom-color: #ebebeb;
  height: 42px;
  line-height: 42px;
  background-color: #fafafa;
  display: flex;
  align-items: center;
  margin-top: 30px;
  margin-bottom: 16px;
  .littleRectangle {
    width: 3px;
    height: 18px;
    background-color: #999999;
    display: inline-block;
    margin-right: 12px;
    margin-top: 0px;
    .titleText {
      font-size: 18px;
      color: #333333;
    }
  }
}
.simulation_table .label {
  font-size: 13px;
  color: #333333;
  text-align: right;
  border: #e1e1e1 1px solid;
  height: 40px;
  line-height: 40px;
  margin: -1px -1px 0 0;
  background-color: #fafafa;
}
.simulation_table .value {
  font-size: 13px;
  color: #333333;
  text-align: right;
  border: #e1e1e1 1px solid;
  height: 40px;
  line-height: 40px;
  margin: -1px -1px 0 0;
}
.el-row {
  margin-bottom: 0px;
}
</style>


