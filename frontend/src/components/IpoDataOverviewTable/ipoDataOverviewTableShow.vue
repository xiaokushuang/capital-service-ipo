<template>
    <div class="favorite-table">
        <el-table :data="data" style="width: 100%;border-right:2px solid #DDDDDD!important;" class="paddingControl" border tooltip-effect="dark" :cell-class-name="tdStyle"
          @sort-change="sortChange" ref="multipleSelection">
           <el-table-column align="center" label="序号" type="index" width="60"></el-table-column>
                <el-table-column v-if="id == 'first'" align="left" label="保荐机构" prop="label" min-width="24%"></el-table-column>
                <el-table-column v-else-if="id == 'second'" align="left" label="律师事务所" prop="label" min-width="24%"></el-table-column>
                <el-table-column v-else align="left" label="会计事务所" prop="label" min-width="24%"></el-table-column>
                <el-table-column align="center" label="沪主板" prop="hzbCount" min-width="9%" sortable="custom">
                    <template slot-scope="scope">
                        <span class="spanClass" v-if="getValue(scope.row.hzbCount) != 0 " @click="openDetail('00',scope.row.label,scope.row.labelCode)">{{scope.row.hzbCount}}</span>
                        <span v-else class="spanClassNone">{{scope.row.hzbCount}}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="中小板" prop="zxbCount" min-width="9%" sortable="custom">
                    <template slot-scope="scope">
                        <span class="spanClass" v-if="getValue(scope.row.zxbCount) != 0 " @click="openDetail('02',scope.row.label,scope.row.labelCode)">{{scope.row.zxbCount}}</span>
                        <span v-else class="spanClassNone">{{scope.row.zxbCount}}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="创业板" prop="cybCount" min-width="9%" sortable="custom">
                    <template slot-scope="scope">
                        <span class="spanClass" v-if="getValue(scope.row.cybCount) != 0 " @click="openDetail('03',scope.row.label,scope.row.labelCode)">{{scope.row.cybCount}}</span>
                        <span v-else class="spanClassNone">{{scope.row.cybCount}}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="科创板" prop="kcCount" min-width="9%" sortable="custom" >
                    <template slot-scope="scope">
                        <span class="spanClass" v-if="getValue(scope.row.kcCount) != 0" @click="openDetail('04',scope.row.label,scope.row.labelCode)">{{scope.row.kcCount}}</span>
                        <span v-else class="spanClassNone">{{scope.row.kcCount}}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="合计"  prop="totalCount" min-width="9%" sortable="custom">
                    <template slot-scope="scope">
                        <span class="spanClass" v-if="getValue(scope.row.totalCount) != 0 " @click="openDetail('',scope.row.label,scope.row.labelCode)">{{scope.row.totalCount}}</span>
                        <span v-else class="spanClassNone">{{scope.row.totalCount}}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="市场占比" min-width="9%" prop="percent" sortable="custom">
                    <template slot-scope="scope">
                        <span>{{scope.row.percent}}%</span>
                    </template>
                </el-table-column>
          <el-table-column align="center" label="辅导企业"  prop="fdCount" min-width="9%" sortable="custom">
            <template slot-scope="scope">
              <span class="spanClass" v-if="getValue(scope.row.fdCount) != 0 " @click="openFdDetail('',scope.row.label,scope.row.labelCode)">{{scope.row.fdCount}}</span>
              <span v-else class="spanClassNone">{{scope.row.fdCount}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="市场占比" min-width="9%" prop="percentFd" sortable="custom">
            <template slot-scope="scope">
              <span>{{scope.row.percentFd}}%</span>
            </template>
          </el-table-column>
        </el-table>
        <papers ref="paper" :sdefault="condition_copy" :tabFlag="ipoDataOverview" :length1="20" :total="totalCount" @searchTable="search"></papers>
        <div class="bottomHeight"></div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import {iframeDoMessage} from '@/utils/auth'
import {openPostWindow} from '@/utils'
import common from '@/mixins/common'
import papers from "@/views/components-demo/newPapers";
export default {
	name: 'IpoDataOverviewTableShow',//主体身份
	props: {
        id:{
            type:String,
            default:'first',
        },
        industrySelect:{//选中的行业
            type:String,
            default:'',
        },
        areaSelect:{//选中的地区
            type:String,
            default:'',
        }
	},
    created() {//加载前默认调用
    },
    mounted(){
     // 获取fromFlag 判断是否是独立项目
     var fromFlag = this.$route.query['fromFlag'];
     // 判断是否是独立收费模块
     if(fromFlag){
         this.fromFlag = true;
         this.$store.dispatch('getFeePaymentFlag', '').then((data) => {//(方法名，参数)
            this.feeFlag = data.feeFlag;//判断是否独立收费模块
             this.signSymbol = data.signSymbol;
         });
     }else{
       this.feeFlag = true;
     }
        this.confirmSearch();//分页查询
    },
	data() {
		return {
            data:[],
            totalCount:0,
            queryParam: {
                startRow: 1, //page
                pageSize: 20, //limit
                orderByName: "",
                orderByOrder: "",
                condition: {
                    industry:'',//行业
                    registAddr:'',//地区
                }
            },
            ipoDataOverview:'ipoDataOverview',
            feeFlag : true,
            fromFlag : false,
            signSymbol:true,
		}
    },
    mixins:[
        common //引用JS
    ],
    components: {
        papers
    },
    computed:{//获取getters中方法

    },
    methods : {//正常调用方法
        tdStyle({row, column, rowIndex, columnIndex}) {//设置单元格样式
            if(columnIndex == 5 || columnIndex == 6) {
                return 'tdStyle'
            }
        },
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
      var arr = document.getElementsByClassName("orderClass")
      for (var i=0;i<arr.length;){
        arr[i].classList.remove("orderClass")
      }
			if (column.order){
        var arr = document.getElementsByClassName(column.column.id)
        for (var i=0;i<arr.length;i++){
          arr[i].classList.add("orderClass")
        }
      }
		},
        pageSearch(){//分页查询调用
            this.$refs.paper.search(this.queryParam.orderByName,this.queryParam.orderByOrder);
        },
        search(data){//通过给定条件查询数据
            this.queryParam.pageSize = data.pageSize;
            if(this.id == 'first') {
                this.$store.dispatch('sponsorInstitutionGet', data).then((data) => {//(方法名，参数)
                    this.data = data.recommendOrgSttsList;
                    this.totalCount = data.total;
                });
            } else if(this.id == 'second') {
                this.$store.dispatch('lawOfficeGet', data).then((data) => {//(方法名，参数)
                    this.data = data.lawFirmSttsList;
                    this.totalCount = data.total;
                });
            } else if(this.id == 'third') {
                this.$store.dispatch('accountFirmGet', data).then((data) => {//(方法名，参数)
                    this.data = data.accountantOfficeSttsList;
                    this.totalCount = data.total;
                });
            }
        },
        openDetail(quasiListedLand,label,labelCode) {//打开详情页
         if (this.fromFlag && !this.signSymbol) {
           let url = window.location.href;
           url = url.replace(this.$route.path, '/ipoPopWin');
           iframeDoMessage(window.parent, 'popWinOut', ['提示', url, '427', '217']);
         
         } else if (this.fromFlag && !this.feeFlag) {
           let url = window.location.href;
           url = url.replace(this.$route.path, '/ipoOverduePopWin');
           iframeDoMessage(window.parent, 'popWinOut', ['提示', url, '427', '217']);
         }else {
              let url = window.location.href;
              url = url.replace(this.$route.path,'/ipoDataOverviewDetailPopWin');
              url = url + '&label=' + label + '&quasiListedLand=' + quasiListedLand + '&industry=' + this.industrySelect
               + '&registAddr=' + this.areaSelect + '&tabFlag=' + this.id + '&labelCode='+labelCode;
            //参数意义：nameSpace：命名空间；action：store中set方法；prompt：提示语
            iframeDoMessage(window.parent,'popWinOut',[label,url,'1200','580']);
           }
        },
      openFdDetail(quasiListedLand,label,labelCode) {//打开详情页
       if (this.fromFlag && !this.signSymbol) {
         let url = window.location.href;
         url = url.replace(this.$route.path, '/ipoPopWin');
         iframeDoMessage(window.parent, 'popWinOut', ['提示', url, '427', '217']);

       } else if (this.fromFlag && !this.feeFlag) {
         let url = window.location.href;
         url = url.replace(this.$route.path, '/ipoOverduePopWin');
         iframeDoMessage(window.parent, 'popWinOut', ['提示', url, '427', '217']);
       }else {
          var intermediaryType = '7'
          if(this.id == 'first'){
            intermediaryType = '7'
          } else if (this.id == 'second'){
            intermediaryType = '3'
          }else {
            intermediaryType = '4'
          }
          let url = window.location.href;
          url = url.replace(this.$route.path,'/ipoDataOverviewDetailFdPopWin');
          url = url + '&label=' + label + '&intermediaryType=' + intermediaryType + '&tabFlag=' + this.id + '&labelCode='+labelCode;
          //参数意义：nameSpace：命名空间；action：store中set方法；prompt：提示语
          iframeDoMessage(window.parent,'popWinOut',[label,url,'1200','580']);
        }
      }

    },
    watch : {
        "industrySelect" : function(val, oldVal){//行业下拉列表改变
            this.queryParam.condition.industry = this.industrySelect;
            //执行取消全部收藏
            this.confirmSearch();
        },
        "areaSelect" : function(val, oldVal){//地区下拉列表改变
            this.queryParam.condition.registAddr = this.areaSelect;
            //执行取消全部收藏
            this.confirmSearch();
        },
    },
}
</script>
<style>
.spanClass {
    cursor: pointer;
    color: #14bdf5;
    font-size: 14px;
}
.spanClassNone{
  color: #999999;
  font-size: 14px;
}
.spanClass:hover {
    text-decoration: underline;
}
.tdStyle {
  border-left:1px solid #14bcf5!important;
  border-right:1px solid #14bcf5!important;
}
.favorite-table {
    padding:0 0 0 10px;
}

.el-table--border td, .el-table--border th {
    border-right:1px solid #DDDDDD!important;
    border-bottom:1px solid #DDDDDD!important;
  }
  .orderClass{
    background-color: #F9F9F9;
  }
</style>
