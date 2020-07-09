<template>
    <div class="container">
      <el-row style="height: 30px;padding-right: 5px">
        <el-col align="right" :span="24">
          <span @click="exportExcel" style="border: 1px solid;color: #5CDCF9;padding: 5px 12px;cursor: pointer;">导出excel</span>

        </el-col>
      </el-row>
        <div class="favorite-table">
          <el-table :data="data" style="width: 100%" class="paddingControl" border tooltip-effect="dark" ref="multipleSelection">
            <el-table-column align="center" type="index" label="序号" width="60"></el-table-column>
            <el-table-column align="left" prop="appCompany" label="申报企业" min-width="13%">
                <template slot-scope="scope">
                    <span class="spanClass" @click="openDetail(scope.row.companyCode)" v-html="scope.row.appCompany"></span>
                </template>
            </el-table-column>
            <el-table-column align="center" prop="registAddr" label="注册地" min-width="8%">
                <template slot-scope="scope">
                    <span v-html="getApproveStatus(scope.row.registAddr)"></span>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="industry" label="所属行业" min-width="10%">
                <template slot-scope="scope">
                    <span>{{checkEmpty(scope.row.industry)}}</span>
                </template>
            </el-table-column>
            <el-table-column align="center" prop="ipoAreaLabel" label="拟上市地" min-width="10%"></el-table-column>
            <el-table-column align="center" prop="recommendOrg" label="保荐机构"  min-width="10%"></el-table-column>
            <el-table-column align="center" prop="accOffice" label="会计师事务所"  min-width="14%"></el-table-column>
            <el-table-column align="center" prop="lawFirm" label="律师事务所"  min-width="10%"></el-table-column>
            <el-table-column align="center" prop="statusLabel" label="审核状态"  min-width="10%">
              <template slot-scope="scope">
                <span  style="white-space: pre-line">{{checkEmpty(scope.row.statusLabel)}}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="attendLabel" label="是否已参加抽查抽签或现场检查"  min-width="11%">
                <template slot-scope="scope">
                    <span>{{checkEmpty(scope.row.attendLabel)}}</span>
                </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="bottomHeight"></div>
    </div>
</template>
<script>
import { mapGetters } from 'vuex'
import {iframeDoMessage} from '@/utils/auth'
import common from '@/mixins/common'
import {exportExcelPostWindow1} from '@/utils'
    export default {
        mixins: [common],
        name:'ipoItemDataDetailPopWin',
        data(){
            return {
                data:[],
            }
        },
        computed:{

        },
        created(){
        },
        mounted() {
            //页面加载完成时查询数据
            this.search();
        },
        methods:{
            search(data){//通过给定条件查询数据
                // document.getElementsByClassName("is-scrolling-none")[0].scrollTop = 0;
                let param = {
                    registAddr : this.$route.query.registAddr,
                    lastUpadteTime : this.$route.query.lastUpadteTime,
                    approveStatus : this.$route.query.approveStatus,
                    belongsPlate : this.$route.query.belongsPlate,
                }
                this.$store.dispatch('getIpoItemDataDetail', param).then((data) => {//(方法名，参数)
                    this.data = data.ipoItemDataDetailList;
                });
            },
            getApproveStatus(name) {//地区特殊处理
                if(name == '广东') {
                    name = '广东<br/>(不含深圳)';
                } else if(name == '辽宁') {
                    name = '辽宁<br/>(不含大连)';
                } else if(name == '浙江') {
                    name = '浙江<br/>(不含宁波)';
                } else if(name == '福建') {
                    name = '福建<br/>(不含厦门)';
                } else if(name == '山东') {
                    name = '山东<br/>(不含青岛)';
                }
                return name;
            },
            checkEmpty(label) {//判断空值
                if(this.getValue(label) == '') {
                    label = '--'
                }
              if((this.getValue(label) == '上市委会议' || this.getValue(label) == '发审会审核') && this.$route.query.approveStatus == '02'){
                label = label+' \n 待上会'
              }
                return label;
            },
            exportExcel() {//导出Excel
                let statisticsParamDto = {
                    registAddr : this.$route.query.registAddr,
                    lastUpadteTime : this.$route.query.lastUpadteTime,
                    approveStatus : this.$route.query.approveStatus,
                    belongsPlate : this.$route.query.belongsPlate,
                }
                exportExcelPostWindow1("/ipo/regulatory_statistics/ipoItemDataDetailExport",statisticsParamDto);
            },
            openDetail(data1){
                //data1 ="10932245";
                let param = {
                    stockCode :data1
                }
                try{
                    var nmasOpen = window.open();
                    this.$store.dispatch('getIpoItemByCompanyCodeSelectId', param).then((data) => {//(方法名，参数)
                        console.log(data);
                        if(data != null &&  data.flag == "1" ){
                            if (data.id) {
                                const _self = this;
                              let platform = ''
                              if (this.$route.query.platform){
                                platform = this.$route.query.platform
                              }
                                const { href } = _self.$router.resolve({
                                    name: 'caseDetail',
                                    query: {
                                        caseId: data.id,
                                        platform:platform,
                                        access_token: _self.$route.query.access_token,
                                        tenant_info: _self.$route.query.tenant_info
                                    }
                                });
                                let param = {
                                    recordType: 'open', //跳转页面方式:
                                    recordTab: "IPO案例详情页" //跳转tab
                                }
                                this.$store.commit('CREATE_TEMP_MESSAGE', param);
                                //this.$open(href, '_blank');
                                nmasOpen.location = href;
                            }
                        }else{
                            nmasOpen.close();
                            this.popAlert('暂无案例');
                        }
                    });
                }catch (err){

                }
            }
        }
    }
</script>
<style>
.container .el-table th>.cell {
    line-height: normal!important;
}
.favorite-table {
    padding: 0px 0px 0px 10px;
}
.container {
    min-height:500px!important;
}
.spanClass {
    cursor: pointer;
}
.spanClass:hover {
    text-decoration: underline;
}
</style>
