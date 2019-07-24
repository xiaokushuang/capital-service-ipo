<template>
    <div class="container">
        <!-- 标题 -->
        <el-row :gutter="20" class="no-margin-tb">
            <el-col :span="14">
                <h3 class="no-margin" style="line-height:32px;padding-left:0px !important;">IPO在审项目数据查询</h3>
            </el-col>
            <el-col :span="6">
            </el-col>
        </el-row>
        <!-- 板块 -->
        <el-row :gutter="20">
            <el-col :span="6">
              <el-multiple-selection
                ref="belongsPlateTree"
                id="belongsPlateTree"
                placeholder="所属板块"
                @sure-click="sure"
                size="small full"
                :search-menu="true"
                node-key="value"
                :tree-data="belongsPlateList"
                :default-props="defaultProps"
                :default-all-show="false"
                :all-select="allSelect" 
              ></el-multiple-selection>
            </el-col>
            <!-- 注册地 -->
            <el-col :span="6">
              <el-multiple-selection
                ref="areaTree"
                id="areaTree"
                placeholder="注册地"
                @sure-click="sure"
                size="small full"
                :search-menu="true"
                node-key="originContent"
                :tree-data="areaList"
                :default-props="defaultProps1"
                :default-all-show="false"
                :all-select="allSelect" 
              ></el-multiple-selection>
            </el-col>
            <el-col :span="12" align="right">
                <el-button type="primary" size="small" @click="exportExcel">导出excel</el-button>
                <el-button size="mini" @click="clearAll">清空条件</el-button>
            </el-col>
        </el-row>
        <!-- table -->
        <el-row>
          <el-col :span="24">
            <el-table :data="data" style="width: 100%" class="paddingControl" border tooltip-effect="dark" ref="multipleSelection"
            :row-class-name="tableRowClassName" :header-cell-class-name="tableHeaderColor" @sort-change="sortChange">
                <el-table-column label="序号" align="center" :index="indexMethod" type="index" width="60"  style="border-right:1px solid #fff"></el-table-column>
                <el-table-column label="注册地" align="center" >
                    <template slot-scope="scope">
                        <span>{{changeAreaResult(scope.row.registAddr)}}</span>
                    </template>
                </el-table-column>
                <el-table-column label="在审情况" align="center"  prop="registAddr" >
                    <el-table-column align="center" :render-header="renderHeader" style="height:54px;" prop="applied" border="true" label="已受理" sortable="custom">
                        <template slot-scope="scope">
                          <span class="spanClass" v-if="getValue(scope.row.applied) != 0" @click="openDetail(scope.row.registAddr,scope.row.lastUpadteTime,'00','unit')">{{scope.row.applied}}</span>
                          <span v-else>{{scope.row.applied}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column align="center" :render-header="renderHeader" prop="reviewed" label="已反馈" sortable="custom" >
                        <template slot-scope="scope">
                          <span class="spanClass" v-if="getValue(scope.row.reviewed) != 0" @click="openDetail(scope.row.registAddr,scope.row.lastUpadteTime,'01','unit')">{{scope.row.reviewed}}</span>
                          <span v-else>{{scope.row.reviewed}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column align="center" prop="preUpdate" label="预先披露更新" sortable="custom" >
                        <template slot-scope="scope">
                          <span class="spanClass" v-if="getValue(scope.row.preUpdate) != 0" @click="openDetail(scope.row.registAddr,scope.row.lastUpadteTime,'02','unit')">{{scope.row.preUpdate}}</span>
                          <span v-else>{{scope.row.preUpdate}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column align="center" :render-header="renderHeader" prop="endYet" label="中止审查" sortable="custom" >
                        <template slot-scope="scope">
                          <span class="spanClass" v-if="getValue(scope.row.endYet) != 0" @click="openDetail(scope.row.registAddr,scope.row.lastUpadteTime,'04','unit')">{{scope.row.endYet}}</span>
                          <span v-else>{{scope.row.endYet}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column align="center" prop="processing" label="已提交发审会讨论，暂缓表决" sortable="custom" >
                        <template slot-scope="scope">
                          <span class="spanClass" v-if="getValue(scope.row.processing) != 0" @click="openDetail(scope.row.registAddr,scope.row.lastUpadteTime,'06','unit')">{{scope.row.processing}}</span>
                          <span v-else>{{scope.row.processing}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column align="center" :render-header="renderHeader" prop="passed" label="已通过发审会" sortable="custom" >
                        <template slot-scope="scope">
                          <span class="spanClass" v-if="getValue(scope.row.passed) != 0" @click="openDetail(scope.row.registAddr,scope.row.lastUpadteTime,'03','unit')">{{scope.row.passed}}</span>
                          <span v-else>{{scope.row.passed}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column align="center" prop="submited" label="提交注册" sortable="custom">
                        <template slot-scope="scope">
                          <span class="spanClass" v-if="getValue(scope.row.submited) != 0" @click="openDetail(scope.row.registAddr,scope.row.lastUpadteTime,'10','unit')">{{scope.row.submited}}</span>
                          <span v-else>{{scope.row.submited}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column align="center" prop="zc" label="注册生效" sortable="custom" >
                        <template slot-scope="scope">
                          <span class="spanClass" v-if="getValue(scope.row.zc) != 0" @click="openDetail(scope.row.registAddr,scope.row.lastUpadteTime,'09','unit')">{{scope.row.zc}}</span>
                          <span v-else>{{scope.row.zc}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column align="center" prop="areaCount" label="合计" sortable="custom"  :class-name="heightStyle">
                        <template slot-scope="scope">
                          <span class="spanClass" v-if="getValue(scope.row.areaCount) != 0" @click="openDetail(scope.row.registAddr,scope.row.lastUpadteTime,'','area')">{{scope.row.areaCount}}</span>
                          <span v-else>{{scope.row.areaCount}}</span>
                        </template>
                    </el-table-column>
                </el-table-column>
                <el-table-column label="终止审查" :render-header="renderHeader" align="center" prop="registAddr" >
                    <el-table-column align="center" prop="weekStopYet" label="最近一周" sortable="custom" >
                        <template slot-scope="scope">
                          <span class="spanClass" v-if="getValue(scope.row.weekStopYet) != 0" @click="openDetail(scope.row.registAddr,scope.row.lastUpadteTime,'99','unit')">{{scope.row.weekStopYet}}</span>
                          <span v-else>{{scope.row.weekStopYet}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column align="center" prop="stopYet" :label="yearShow" sortable="custom">
                        <template slot-scope="scope">
                          <span class="spanClass" v-if="getValue(scope.row.stopYet) != 0" @click="openDetail(scope.row.registAddr,scope.row.lastUpadteTime,'05','unit')">{{scope.row.stopYet}}</span>
                          <span v-else>{{scope.row.stopYet}}</span>
                        </template>
                    </el-table-column>
                </el-table-column>
            </el-table>
            <div class="bottomHeight"></div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import { mapGetters } from "vuex";
import { GetDateDiff } from "@/utils";
import ipoDetailed from "./ipoDetailed";
import common from '@/mixins/common'
import {exportExcelPostWindow1} from '@/utils'
import {iframeDoMessage} from '@/utils/auth'
export default {
  name: "ipoDetailed",
  mixins: [common],
  data() {
    return {
      belongsPlateList:[],//板块下拉列表
      areaList:[],//地区下拉列表
      defaultProps: {
        label: 'label',
      },
      defaultProps1: {
        label: 'label',
      },
      data:[],
      queryParam: {
          orderByName: "",
          orderByOrder: "",
          condition: {
              belongsPlate: "",
              registAddr: "",
              classly: ""
          }
      },
      heightStyle: "heightStyle",
      yearShow:'',//年份
      allSelect:{colloge: false,},
    };
  },
  created() {//加载前默认调用
    this.getAllDropDownList();
    this.getYear();
  },
  mounted() {
    this.search();
  },
  computed: {
    // ...mapGetters(["getIpoAreaData", "getIpoQuery"]),
  },
  methods: {
    indexMethod(index) {//序号列显示
      if(index == this.data.length -1) {
        return '';
      } else {
        return index+1;
      }
    },
    getAllDropDownList() {//获取下拉列表
      this.$store.dispatch('getAllDropDownList', '').then((data) => {//(方法名，参数)
        this.belongsPlateList = data.belongsPlateList;//所属板块
        this.areaList = data.areaList;//所属地区
      });
    },
    getYear() {//获取年份
      var date = new Date;
      var year= date.getFullYear();
      this.yearShow = year + '年'
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {// 修改table header的背景色
      if ((rowIndex === 1 && columnIndex === 6) || (rowIndex === 1 && columnIndex === 7)) {
        return 'row'
      } 
    },
    renderHeader(h, { column, $index }) {//表格个别字体颜色替换
      return (
        h('span',[
          h('span',column.label),
          h('span',{style:{color:'#0099cc!important'}},this.getLabel(column.label))
        ])
      );
    },
    getLabel(name) {//表头名称修改
      var label = ''
      if(name == '已受理') {
        label = '(已受理)'
      } else if(name == '已反馈') {
        label = '(已问询)'
      } else if(name == '中止审查') {
        label = '(中止)'
      } else if(name == '已通过发审会') {
        label = '(上市委会议通过)'
      } else if(name == '终止审查') {
        label = '(终止)'
      }
      return label;
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === this.data.length - 1) {
        return "warning-row"
      }
    },
    changeAreaResult(name){//地区返回结果处理
      var resultName = '';
      if(this.getValue(name) != ''){
        if(name == "广东"){
          resultName = "广东(不含深圳)";
        } else if(name == "辽宁"){
          resultName = "辽宁(不含大连)";
        } else if(name == "浙江"){
          resultName = "浙江(不含宁波)";
        } else if(name == "福建"){
          resultName = "福建(不含厦门)";
        } else if(name == "山东"){
          resultName = "山东(不含青岛)";
        } else{
          resultName = name;
        }
      } else {
        resultName = '合计'
      }
      return resultName;
    },
    sortChange(column){//排序查询
			//设置排序
			if (column.order != null && column.prop != null) {
			  (this.queryParam.orderByName = column.prop), (this.queryParam.orderByOrder = column.order);
			} else {
			  this.queryParam.orderByName = "";
			  this.queryParam.orderByOrder = "";
      }
      this.search()
    },
    search(){
      this.$store.dispatch('ipoItemDataQuery', this.queryParam).then((data) => {//(方法名，参数)
        if(data.ipoItemDataList != null && data.ipoItemDataList.length > 1) {
          this.data = data.ipoItemDataList;
        } else {
          this.data = [];
        }
      });
    },
    sure(childArr,allArr,nodekey,id){// 下拉确定
			let arr ='';
			childArr.map((obj, index)=>{
				arr += obj[nodekey] + ',';
			})
			if(id == 'areaTree') {
				this.queryParam.condition.registAddr = arr;
			} else {
				this.queryParam.condition.belongsPlate = arr;
      } 
      this.search();
		},
    clearAll() {
      this.$refs.areaTree.setCheckedKeys([]); //清空选中
      this.$refs.belongsPlateTree.setCheckedKeys([]); //清空选中
      //清除排序
      this.$refs.multipleSelection.clearSort();
      //清空参数
      this.queryParam = {
        condition : {
          belongsPlate:'',
          registAddr:''
        }
      }
      this.search();
    },
    exportExcel() {//导出Excel
      exportExcelPostWindow1("/ipo/regulatory_statistics/ipoItemDataExport",this.queryParam);
    },
    openDetail(registAddr,lastUpadteTime,approveStatus,viewType){
      if(this.getValue(registAddr) == '') {//当点击合计时,传下拉列表选中的地区
        registAddr = this.queryParam.condition.registAddr;
      }
      let url = window.location.href;
      url = url.replace(this.$route.path,'/ipoItemDataDetailPopWin');
      url = url + '&registAddr=' + registAddr + '&lastUpadteTime=' + lastUpadteTime + '&approveStatus=' + approveStatus
        + '&belongsPlate=' + this.queryParam.condition.belongsPlate;

      var title = '在审项目数据明细';
      var appLabel = '';
      if(approveStatus != null && approveStatus != ''){
        switch (approveStatus) {
              case '00': appLabel = approveStatus.replace("00", "已受理<span style='color:#0099cc'>(已受理)</span>");
              break;
              case '01': appLabel = approveStatus.replace("01", "已反馈<span style='color:#0099cc'>(已问询)</span>");
              break;
              case '02': appLabel = approveStatus.replace("02", "预先披露更新");
              break;
              case '04': appLabel = approveStatus.replace("04", "中止审查<span style='color:#0099cc'>(中止)</span>");
              break;
              case '06': appLabel = approveStatus.replace("06", "已提交发审会讨论，暂缓表决");
              break;
              case '09': appLabel = approveStatus.replace("09", "<span style='color:#0099cc'>注册生效</span>");
              break;
              case '10': appLabel = approveStatus.replace("10", "<span style='color:#0099cc'>提交注册</span>");
              break;
              case '03': appLabel = approveStatus.replace("03", "已通过发审会<span style='color:#0099cc'>(上市委会议通过)</span>");
              break;
              case '05': appLabel = approveStatus.replace("05", this.yearShow + "年终止审查<span style='color:#0099cc'>(终止)</span>");
              break;
              case '99': appLabel = approveStatus.replace("99", "最近一周终止审查<span style='color:#0099cc'>(终止)</span>");
              break;
        }
      }
      if(viewType == 'area' && this.getValue(registAddr) == '') {
        viewType = 'all'
      }
      if('unit' == viewType){
        title = title + ' ( ' + registAddr + ' ' + appLabel + ' ) ';
      }else if('area' == viewType){
        title = title + ' ( ' + registAddr + ' ) ';
      }else if('approve' == viewType){
        title = title + ' ( ' + appLabel + ' ) ';
      }else if('all' == viewType){
        title = title;
      }
			//参数意义：nameSpace：命名空间；action：store中set方法；prompt：提示语
			iframeDoMessage(window.parent,'popWinOut',[title,url,'1200','650']);

    }
  }
};
</script>

<style>
.no-margin {
  padding-left: 20px;
}
.h100 {
  height: 100%;
  min-height: 500px;
}
.h100 > div.chart {
  height: 100%;
  min-height: 500px;
}
.fullDiv_border {
  width: 100%;
  height: 100%;
  height: 500px;
}
.list {
  line-height: 38px;
}
.list a {
  padding: 5px 20px;
  font-size: 14px;
}
.list a.active {
  color: #0285ac;
}
.el-table thead.is-group th{
  background: #a0adb5 !important;
  padding: 1px;
  border-right: 1px solid #fff;
}
.el-table:first-child tr{
  height: 33px !important;
}
.el-table--border td {
  height: 30px;
  line-height: 30px;
  border-right: 1px solid #ddd;
  border-bottom:  1px solid #ddd;
}
.el-table .cell,
.el-table th div,
.el-table--border td:first-child .cell,
.el-table--border th:first-child .cell {
  padding: 0px;
}
.el-table .warning-row {
  background: #e8e8e8 !important;
}
.el-table thead tr th {
    font-weight: normal;
}
.heightStyle{background: #e8e8e8}
.greyBg td div span{ float:none !important}
/* .table thead th{padding:6px !important;} */

.container .el-table__header .row .cell {
  color:#0099cc!important;
}
.container {
  padding:0 10px 0 10px!important;
}
.spanClass {
    cursor: pointer;
}
.spanClass:hover {
    text-decoration: underline;
}
.el-select-dropdown .el-input__inner, .el-select-dropdown .el-input__suffix:hover {
    cursor: pointer;
}
</style>
