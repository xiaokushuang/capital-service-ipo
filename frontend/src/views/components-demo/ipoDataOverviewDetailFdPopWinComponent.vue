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
        <el-table-column align="left" prop="companyName" label="辅导企业" min-width="20%">
          <template slot-scope="scope">
            <span class="spanClass" @click="openDetail(scope.row.id)" v-html="scope.row.companyName"></span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="registAddr" label="注册地" min-width="15%">
          <template slot-scope="scope">
            <span v-html="getApproveStatus(scope.row.registAddr)"></span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="processTime" label="辅导备案时间" min-width="15%">

        </el-table-column>
        <el-table-column align="center" prop="processType" label="辅导进程" min-width="15%"></el-table-column>
        <el-table-column align="center" prop="newTime" label="最新进程时间"  min-width="15%"></el-table-column>
        <el-table-column align="center" prop="time" label="辅导历时（天）"  min-width="15%"></el-table-column>
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
  import {
    getIpoDataOverviewFdDetail
  } from '@/api/ipoCase/ipoCaseListApi'
  export default {
    mixins: [common],
    name:'ipoDataOverviewDetailFdPopWin',
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
        let param = {}
        if (this.$route.query.registAddr){
          param = {
            registAddr : this.$route.query.registAddr,
            approveStatus : this.$route.query.approveStatus,
          }
        }else {
          param = {
            label : this.$route.query.label,
            intermediaryType : this.$route.query.intermediaryType,
            labelCode:this.$route.query.labelCode,
          }
        }
        getIpoDataOverviewFdDetail(param).then(response => {
          if (this.$route.query.intermediaryType){
            if (this.$route.query.intermediaryType == '7'){
              this.data = response.data.result.recommendOrgSttsList;
            }else if(this.$route.query.intermediaryType == '3'){
              this.data = response.data.result.lawFirmSttsList;
            }else {
              this.data = response.data.result.accountantOfficeSttsList;
            }
          }else {
            this.data = response.data.result.ipoDetailList;
          }
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
        return label;
      },
      exportExcel() {//导出Excel
        let statisticsParamDto = {
          label : this.$route.query.label,
          intermediaryType : this.$route.query.intermediaryType,
          tabFlag:this.$route.query.tabFlag,
        }
        exportExcelPostWindow1("/ipo/regulatory_statistics/ipoDataOverviewFdDetailExport",statisticsParamDto);
      },
      openDetail(id){
        if (id) {
          var caseId = id;
          const _self = this;
          const {
            href
          } = _self.$router.resolve({
            // name: 'tutoringCase',
            name: 'tutoringCase',
            query: {
              caseId: caseId,
              access_token: _self.$route.query.access_token,
              tenant_info: _self.$route.query.tenant_info
            }
          });
          // 日志---------------------头
          let param = {
            recordType: 'open', //跳转页面方式:
            recordTab: "IPO案例详情页" //跳转tab
          }
          this.$store.commit('CREATE_TEMP_MESSAGE', param);
          // 日志---------------------尾
          this.$open(href, '_blank');
        } else {
          let url = window.location.href;
          url = url.replace(this.$route.path, '/ipoPopWin');
          iframeDoMessage(window.parent, 'popWinOut', ['提示', url, '427', '217']);
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
  .spanClass:hover {
    text-decoration: underline;
    cursor: pointer;
  }
  .layui-layer-title{
    background: white !important;
    color: #666666 !important;
    font-weight: 700 !important;
  }
</style>
