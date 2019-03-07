<template>
    <div class="financialInformation">
        <!-- 主要财务数据 -->
        <div class="financialData">
            <div class="title">
                <span class="littleRectangle"></span>
                <span class="titleText" id="financialStatementData">主要财务数据</span>
                <span style="position: relative;left: 79%;top: 10px;font-size: 12px;color: #666666;">
                    单位：万元
                </span>
            </div>
            <div class="allAssets">
                <p>财务总体情况</p>
                <div class="allAssetsTable">
                    <allAssetsTable></allAssetsTable>
                </div>
            </div>
            <div class="assets">
                <p>资产与负债情况</p>
                <div class="assetsTable1">
                    <assetsTable></assetsTable>
                </div>
            </div>
            <div class="income">
                <p>收入与利润情况</p>
                <div class="assetsTable2">
                    <incomeTable></incomeTable>
                </div>
            </div>
        </div>
        <!-- 招股书列示同行业上市公司综合毛利率对比 -->
        <div class="compare">
            <div class="title" >
                <span class="littleRectangle"></span>
                <span class="titleText" id="comparison">招股书列示同行业上市公司综合毛利率对比</span>
            </div>
            <div class="chartTable">
                <p style="font-family:'PingFang-SC-Regular', 'PingFang SC';font-weight:400;color:#666666;">报告期内，本公司与可比上市公司<span style="font-family:'PingFang-SC-Medium', 'PingFang SC Medium', 'PingFang SC';font-weight:500;color:'black'"> 工程施工业务</span> 毛利率比较 :</p>
                <div class="zxChart" style="height:300px;width:100%">
                    <zxChart></zxChart>
                </div>
                <div class="table">
                    <compareTable></compareTable>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import assetsTable from "@/views/tables/assetsTable";
import allAssetsTable from "@/views/tables/allAssetsTable";
import incomeTable from "@/views/tables/incomeTable";
import zxChart from "@/components/Charts/zxChart";
import compareTable from "@/views/tables/compareTable";
// zxChart代码
import echarts from 'echarts'
import { getAssetsTableData } from '@/api/tableDemo'
export default {
    name:'financialInformation',
    components: {
        allAssetsTable,
        assetsTable,
        incomeTable,
        zxChart,
        compareTable
    },

}
</script>
<style scoped lang="scss">
.l {
  float: left;
}
.r {
  float: right;
}
.clear:after {
  display: block;
  content: "";
  clear: both;
}
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


