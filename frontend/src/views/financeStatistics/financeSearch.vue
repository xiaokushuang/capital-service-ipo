
<template>
  <div class="container">
    <el-form class="el-form" v-model="queryParam" :label-position="labelPosition" >
      <el-row :gutter="24">
        <el-col :span='8'>
            <el-select v-model="queryParam.condition.financeIndustry" placeholder="请选择" size='small full' @change="changeFinanceIndustry">
              <template v-for="item in getSFClass">
              <el-option
                  class="autoComplate"
                  :key="item.code_value"
                  :label="item.code_name"
                  :value="item.code_value">
              </el-option>
              </template>
            </el-select>
        </el-col>
        <el-col :span='8'>
            <el-multiple-selection 
                ref="areaTree"
                id="areaTree"
                placeholder="所在地区"
                @sure-click="sure" 
                size="small full"
                :search-menu="true"
                node-key="type"
                :tree-data="getSFCRegion"
                :default-props="defaultProps"
                :default-all-show="false"
            ></el-multiple-selection>
        </el-col>
        <el-col :span='8'>
          <el-multiple-selection 
              ref="stockBoardTree"
              id="stockBoardTree"
              placeholder="所属板块"
              @sure-click="sure" 
              size="small full"
              :search-menu="true"
              node-key="value"
              :tree-data="getPlateInfo"
              :default-props="defaultPropss"
              :default-all-show="false"
          ></el-multiple-selection>
        </el-col>
      </el-row>
      <el-row :gutter="24">
        <el-col :span="8">
            <el-input v-model="queryParam.condition.companyName" @keyup.enter.native="confirmSearch" placeholder="请输入公司名称" size="small"></el-input>
        </el-col>
        <el-col :span="8">
            <el-input v-model="queryParam.condition.securityCode" @keyup.enter.native="confirmSearch" placeholder="请输入证券(债券)代码" size="small"></el-input>
        </el-col>
        <el-col :span="8">
            <el-input v-model="queryParam.condition.securityShortName" @keyup.enter.native="confirmSearch" placeholder="请输入证券(债券)简称" size="small"></el-input>
        </el-col>			
      </el-row>
      <el-row :gutter="24">
        <el-col :span='8'>
            <el-multiple-selection 
              ref="financingTree"
              id="financingTree"
              placeholder="融资统计"
              @sure-click="sure" 
              size="small full"
              :search-menu="true"
              node-key="value"
              :tree-data="listData"
              :default-props="defaultPropss"
              :default-all-show="false"
          ></el-multiple-selection>
        </el-col>
        <el-col :span='8'>
            <el-date-picker v-model="queryParam.condition.financeDate" placeholder="活动日期" value-format="yyyy-MM-dd" size="small" @change="dateChange"
                type="daterange" unlink-panels range-separator="至" start-placeholder="活动开始日期" end-placeholder="活动结束日期" :picker-options="pickerOptions2">
            </el-date-picker>
        </el-col>
      </el-row>
      <el-row :gutter="24" style="margin-bottom:10px!important;">
          <el-col align="right" :span="24">
             <el-button size="small" class="searchBtn" @click="emptyAndSearch">清空条件</el-button>
            <el-button size="small" type="primary" class="searchBtn" @click="confirmSearch">查询</el-button>
          </el-col>
      </el-row>
      </el-form>
      <!-- table开始 -->
        <div class="favorite-table">
          <el-table :data="financeSearchData" style="width: 100%" class="paddingControl" border tooltip-effect="dark"
          @sort-change="sortChange" ref="multipleSelection">
            <el-table-column align="center" type="index" label="序号" width="60"></el-table-column>
            <el-table-column align="left" prop="companyName" label="公司" min-width="20%" sortable="custom">
              <template slot-scope="scope">
                <span v-html="scope.row.companyName"></span>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="financeDate" label="日期" min-width="10%" sortable="custom"></el-table-column>
            <el-table-column align="center" prop="securityCode" label="证券(债券)代码" min-width="13%" sortable="custom"></el-table-column>
            <el-table-column align="center" prop="securityShortName" label="证券(债券)简称" min-width="13%" sortable="custom"></el-table-column>
            <el-table-column align="center" prop="financeIndustry" label="所属行业" min-width="8%">
                <template slot-scope="scope">
                  <span v-if="getValue(scope.row[`pIndName${selected}`] ) == ''">--</span>
                  <span>{{scope.row[`pIndName${selected}`]}}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="cityName" label="所属地区" min-width="8%">
                <template slot-scope="scope">
                  <span v-if="getValue(scope.row.cityName) != ''">{{scope.row.cityName}}</span>
                  <span v-else>--</span>
                </template> 
            </el-table-column>
            <el-table-column align="center" prop="address" label="融资方式" min-width="8%">
                <template slot-scope="scope">
                  <span v-if="getValue(scope.row.finaType) == '001'">IPO</span>
                  <span v-else-if="getValue(scope.row.finaType) == '002'">增发</span>
                  <span v-else-if="getValue(scope.row.finaType) == '003'">配股</span>
                  <span v-else>债券</span>
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
        <div class="bottomHeight"></div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import papers from "@/views/components-demo/newPapers";
import datepicker from "@/mixins/datepicker";
import moment from "moment";
import common from '@/mixins/common'
export default {
  name: "financeSearch",
  mixins: [datepicker,common],
  components: {
    papers
  },
  data() {
    return {
      totalCount:0,//总数
      queryParam: {
        startRow: 1, //page
        pageSize: 20, //limit
        orderByName: "",
        orderByOrder: "",
        condition: {
          financeIndustry:'001',//所选行业分类
          areaSelect:'',//地区
          stockBoardSelect:'',//板块
          companyName:'',//公司名称
          securityCode:'',//证券代码
          securityShortName:'',//证券简称
          financingMode:'',//融资方式
          financeDate:[],//活动日期
        }
      },
      labelPosition:'right',//设置form中label对齐方式
      financeSearchData:[],//列表数据
      selected:'001',//分类no,用于拼接行业名称
      defaultProps: {
        children: "children",
        label: "name"
      },
      defaultPropss: {
        label: "label"
      },
      listData: [
        {
          value: "001",
          label: "IPO"
        },
        {
          value: "002",
          label: "增发"
        },
        {
          value: "003",
          label: "配股"
        },
        {
          value: "004",
          label: "债券"
        }
      ],
    };
  },
  mounted() {
    // this.dataGet(true);
    this.classGet(true);
    this.regionGet(true);
    this.plateGet(true);
    this.confirmSearch();//分页查询
  },
  methods: {
    changeFinanceIndustry() {//改变分类
      this.selected = this.queryParam.condition.financeIndustry;
      this.confirmSearch();
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
		},
    pageSearch(){//分页查询调用
				this.$refs.paper.search(this.queryParam.orderByName,this.queryParam.orderByOrder);	
		},
    search(data){//通过给定条件查询数据
      this.$store.dispatch('ipoSearchGet', data).then((data) => {//(方法名，参数)
          this.financeSearchData = data.data;
          this.totalCount = data.total;
      });
    },
    emptyAndSearch(){//清空条件方法
			//清除排序
			this.$refs.multipleSelection.clearSort();
			//设置分页参数
			this.queryParam = {
				startRow : 1,
				pageSize : 20,
				condition : {
          financeIndustry:'001'
				}
      }
      this.selected = '001';
			this.clearAllDropDownList();
			//分页查询调用
			this.confirmSearch();
		},
		clearAllDropDownList() {//清空下拉列表
			//清空下拉列表
			this.$refs.areaTree.setCheckedKeys([]);
      this.$refs.stockBoardTree.setCheckedKeys([]);
      this.$refs.financingTree.setCheckedKeys([]);
		},
    sure(childArr,allArr,nodekey,id){// 下拉确定
			let arr ='';
			childArr.map((obj, index)=>{
				arr += obj[nodekey] + ',';
			})
			if(id == 'areaTree') {
				this.queryParam.condition.areaSelect = arr;
			} else if(id == 'stockBoardTree') {
				this.queryParam.condition.stockBoardSelect = arr;
      } else if(id == 'financingTree') {
				this.queryParam.condition.financingMode = arr;
      } 
			this.confirmSearch();
		},
    dateChange(){//选中日期后查询
      this.confirmSearch();
    },
    // 分类
    classGet() {
      this.$store.dispatch("ipoSFClassGet");
    },
    // 地区
    regionGet() {
      this.$store.dispatch("ipoSFCRegionGet");
    },
    // 板块信息
    plateGet() {
      this.$store.dispatch("ipoPlateInfoGet");
    },
  },
  computed: {
    ...mapGetters([
      // "getSearchIpo",
      "getSFClass",
      "getSFCRegion",
      "getPlateInfo",
      // "getCompanyByCode"
    ])
  },
  watch: {},

  created() {}
};
</script>
<style>
.container {
    padding-top: 0;
}
.el-table--border{
    border: none;
}
.el-input--small .el-input__inner {
  height:32px!important;
  cursor: pointer;
}
</style>

