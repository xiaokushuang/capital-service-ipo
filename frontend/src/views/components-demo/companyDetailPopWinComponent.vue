<template>
    <div class="container">
        <el-row>
            <el-col align="right" :span="24">
                <el-button size="small" class="searchBtn" @click="exportExcel">导出excel</el-button>
            </el-col>
        </el-row>
        <div class="favorite-table">
          <el-table :data="companyDetailData" style="width: 100%" class="paddingControl" border tooltip-effect="dark"
            @sort-change="sortChange" ref="multipleSelection">
            <el-table-column align="center" type="index" label="序号" width="60"></el-table-column>
            <el-table-column align="left" prop="companyName" label="公司" min-width="17%" sortable="custom">
              <template slot-scope="scope">
                <span v-html="scope.row.companyName"></span>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="financeDate" label="上市日期" min-width="11%" sortable="custom"></el-table-column>
            <el-table-column align="center" prop="securityCode" label="证券代码" min-width="11%" sortable="custom"></el-table-column>
            <el-table-column align="center" prop="securityShortName" label="证券简称" min-width="11%" sortable="custom"></el-table-column>
            <el-table-column align="center" prop="financeIndustry" label="所属行业" sortable="custom" min-width="11%">
                <template slot-scope="scope">
                    <div v-if="chartType == '2'">
                        <span v-if="getValue(scope.row[`pIndName${financeIndustry}`] ) == ''">--</span>
                        <span>{{scope.row[`pIndName${financeIndustry}`]}}</span>
                    </div>
                    <div v-else>
                        <span v-if="getValue(scope.row[`pIndName001`] ) == ''">--</span>
                        <span>{{scope.row[`pIndName001`]}}</span>
                    </div>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="cityName" label="所属地区" sortable="custom" min-width="11%">
                <template slot-scope="scope">
                    <span v-if="getValue(scope.row.cityName) != ''">{{scope.row.cityName}}</span>
                    <span v-else>--</span>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="belongPlate" label="所属板块" sortable="custom" min-width="11%">
                <template slot-scope="scope">
                  <span v-if="getValue(scope.row.belongPlate) == '00'">深圳主板</span>
                  <span v-else-if="getValue(scope.row.belongPlate) == '01'">深圳中小板</span>
                  <span v-else-if="getValue(scope.row.belongPlate) == '02'">深圳创业板</span>
                  <span v-else-if="getValue(scope.row.belongPlate) == '04'">上交所主板</span>
                  <span v-else-if="getValue(scope.row.belongPlate) == '05'">新三板</span>
                  <span v-else-if="getValue(scope.row.belongPlate) == '07'">上交所科创板</span>
                  <span v-else>--</span>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="sumFina" label="融资金额(亿元)" min-width="15%" sortable="custom">
              <template slot-scope="scope">
                <span v-if="getValue(scope.row.finaType) == '004'">{{parseFloat(scope.row.sumFina).toFixed(4)}}</span>
                <span v-else>{{(parseFloat(scope.row.sumFina) / 100000000).toFixed(4)}}</span>
              </template>
            </el-table-column> 
          </el-table>
          <papers ref="paper" :sdefault="condition_copy" :length1="20" :total="totalCount" @searchTable="search"></papers>
        </div>
    </div>
</template>
<script>
import { mapGetters } from 'vuex'
import {iframeDoMessage} from '@/utils/auth'
import papers from "@/views/components-demo/newPapers";
import common from '@/mixins/common'
import {exportExcelPostWindow} from '@/utils'
    export default {
        mixins: [common],
        components: {
            papers
        },
        name:'companyDetailPopWin',
        data(){
            return {
                queryParam: {
                    startRow: 1, //page
                    pageSize: 20, //limit
                    orderByName: "",
                    orderByOrder: "",
                    condition: {
                        chartType: '',//图型
                        selCondition:'',//选中的查询条件
                        finaType:'',//模块
                        conditionStr:'',//索引查询条件
                        financeIndustry:'',//下拉列表选中,用于饼状图
                        typeFlag:'',//区分tab页
                        exportTotal:0,//下载数量
                    }
                },
                companyDetailData:[],//列表数据
                totalCount:0,//总数
                selected:'',
                chartType:'',
                financeIndustry:'',
                queryParam_copy:{},//导出Excel查询条件
            }
        },
        computed:{
            
        },
        created(){
            this.chartType = this.$route.query.chartType;
            this.financeIndustry = this.$route.query.financeIndustry;
        },
        mounted() {
            //页面加载完成时刷新echart图表
            this.confirmSearch();
        },
        methods:{
            sortChange(column){//排序查询
			//设置排序
                if (column.order != null && column.prop != null) {
                    (this.queryParam.orderByName = column.prop), (this.queryParam.orderByOrder = column.order);
                } else {
                    this.queryParam.orderByName = "";
                    this.queryParam.orderByOrder = "";
                }
                this.setPage();
                //分页查询
                this.pageSearch();
            },
            pageSearch(){//分页查询调用
                this.$refs.paper.search(this.queryParam.orderByName,this.queryParam.orderByOrder);	
            },
            search(data){//通过给定条件查询数据
                data.condition.chartType = this.$route.query.chartType;
                data.condition.selCondition = this.$route.query.selCondition;
                data.condition.finaType = this.$route.query.finaType;
                data.condition.conditionStr = this.$route.query.condition;
                data.condition.financeIndustry = this.$route.query.financeIndustry;
                data.condition.typeFlag = this.$route.query.typeFlag;
                this.queryParam_copy = data;
                this.$store.dispatch('searchCompanyDetail', data).then((data) => {//(方法名，参数)
                    this.companyDetailData = data.data;
                    this.totalCount = data.total;
                });
            },
            exportExcel() {//导出Excel
                this.queryParam_copy.condition.exportTotal = this.totalCount;
                exportExcelPostWindow("/ipo/financeStatistics/exportExcel",this.queryParam_copy);
            },
        }
    }
</script>
<style>
.favorite-table {
    padding: 0px 0px 0px 10px;
}
.container {
    min-height:500px!important;
}
</style>
