<template>
    <div class="issue">
        <!-- 发行数据 -->
        <div class="issueData">
             <div class="title">
                <span class="littleRectangle"></span>
                <span class="titleText" id="financialStatementData">发行数据</span>
            </div>
            <!-- table -->
             <el-row>
                <el-col :span="24">
                    <div class="table1">
                        <el-row :gutter="24" class="h30">
                            <el-col :span="8" class="align1">
                                <span>每股面值</span>
                            </el-col>
                            <el-col :span="16" class="align">
                                <span>1234</span>
                                <!-- <span  v-if="this.tockRadioData.tbMaaTarget" :title="getLabTextLTitle(this.tockRadioData.tbMaaTarget,'MAA_TARGET')" >{{getLabTextL(this.tockRadioData.tbMaaTarget,'MAA_TARGET')}}</span> -->
                                <!-- <span v-else>- -</span> -->
                            </el-col>
                        </el-row>
                        <el-row :gutter="24" class="h30">
                            <el-col :span="8" class="align1">
                                <span style="">发行数量总计</span>
                            </el-col>
                            <el-col :span="16" class="align">
                                <span>1234</span>
                                <!-- <span v-if="this.tockRadioData.tbMaaType" :title="getLabTextLTitle(this.tockRadioData.tbMaaType,'MAA_TYPE')" style="">{{getLabTextL(this.tockRadioData.tbMaaType,'MAA_TYPE')}}</span> -->
                                <!-- <span v-else>- -</span> -->
                            </el-col>
                        
                        </el-row>
                        <el-row :gutter="24" class="h30">
                            <el-col :span="8" class="align1">
                                <span style="">对价支付方式</span>
                            </el-col>
                            <el-col :span="16" class="align">
                                <span>1234</span>
                                <!-- <span v-if="this.tockRadioData.tbMaaWay" :title="getLabTextLTitle(this.tockRadioData.tbMaaWay,'DISCOUNT_RATE')" style="">{{getLabTextL(this.tockRadioData.tbMaaWay,'DISCOUNT_RATE')}}</span>
                                <span v-else>- -</span> -->
                            </el-col>
                        
                        </el-row>
                    
                    
                    </div>
                </el-col>
             </el-row>
        </div>
        <!-- 发行费用 -->
        <div class="issueMoney">
             <div class="title">
                <span class="littleRectangle"></span>
                <span class="titleText" id="financialStatementData">发行费用</span>
            </div>
            <!-- table2 -->
             <el-table :data="issueMoneyTableList" style="width: 100%" stripe border>
                <el-table-column type="index" label="序号" align='center' width="117">
                <template slot-scope="scope">
                            {{scope.$index+1}}
                    </template>
                </el-table-column>
                <el-table-column style="width:30%" prop="name" label="费用明细" align='left'></el-table-column>
                <el-table-column style="width:30%" prop="nature" label="金额（人民币/万元）" align='right'></el-table-column>
                <el-table-column style="width:30%" prop="num" label="占募集资金总额比例" align='right'></el-table-column>
            </el-table>
        </div>
    </div>
</template>
<script>
import { getAssetsTableData } from '@/api/tableDemo'
export default {
    name:'issue',
    data(){
        issueMoneyTableList:[]
    },
    components: {
    },

}
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
    margin-top: 40px;
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
</style>


