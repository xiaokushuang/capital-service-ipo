
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
            <!-- <el-select ref="selectTreeIndex" v-model="formLabelAlign.city" placeholder="所在地区"  
            size="small full" @visible-change="calls">
              <el-option :label="formLabelAlign.city" :value="formLabelAlign.city" >
                <el-tree :data="getSFCRegion" show-checkbox node-key="id" ref="tree" highlight-current 
                @check-change="handleNodeClick2" :props="defaultProps"></el-tree>
              </el-option>
              <el-col :span="24" class='selectFull'>
                <el-button  size="mini" @click="clear">清空</el-button>
                <el-button  size="mini" type="primary" @click="sure">确定</el-button>
              </el-col>
            </el-select> -->
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
          <!-- <el-select class="Hselect" ref="selectCheckbox" v-model="formLabelAlign.plate" placeholder="市场板块" 
          size='small full' @visible-change="calls">
            <el-option :label="formLabelAlign.plate" :value="formLabelAlign.plate" class="hselect">
                <el-tree id="teSelect" :data="getPlateInfo" show-checkbox node-key="value" ref="treePlate" highlight-current 
                @check-change="handleNodeClick3" :props="defaultPropss"></el-tree>
            </el-option>
            <el-col :span="24" class='selectFull'>
              <el-button size="mini" @click="clear">清空</el-button>
              <el-button  size="mini" type="primary"  @click="sure">确定</el-button>
            </el-col>
          </el-select> -->
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
            <!-- <el-select ref="selectCheckbox1"  v-model="formLabelAlign.treeWay" placeholder="融资方式" size='small full' @change="selectCodeValue">
                <el-option :label="formLabelAlign.treeWay" :value="formLabelAlign.treeWay" class="hselect">
                  <el-tree  id="teSelect"  :data="listData" show-checkbox node-key="value" ref="treeWay" highlight-current 
                  @check-change="handleNodeClick4" :props="defaultPropss"></el-tree>
              </el-option>
                <el-col :span="24" class='selectFull'>
                  <el-button size="mini" @click="clear">清空</el-button>
                  <el-button  size="mini" type="primary"  @click="sure">确定</el-button>
                </el-col>
            </el-select> -->
            <!-- <el-select ref="invalidSelectIndex" v-model="queryParam.condition.financingMode" placeholder="融资方式"  
              size="small full" @visible-change="calls($event,'invalidSelect',selectInvalidSpace)" :tselect=true
              @sure-click="sure('invalidSelect','invalidSelectIndex')" @clear-click = "clear('invalidSelect')">
                  <el-option :label="formLabelAlign.treeWay" :value="formLabelAlign.treeWay">
                  <el-tree :data="listData" show-checkbox node-key="codeValue" ref="invalidSelect" highlight-current  
                  @check-change="handleNodeClick('invalidSelect','invaildSelectName')"  :props="defaultPropss" :check-on-click-node=true></el-tree>
                  
                  </el-option>
              </el-select> -->
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
            <!-- <el-date-picker size='small' v-model="formLabelAlign.date" type="daterange" 
            :picker-options="pickerOptions2" range-separator="至" start-placeholder="开始日期" 
            end-placeholder="结束日期" align="right" @blur="loseBlur">
            </el-date-picker> -->
            <el-date-picker v-model="queryParam.condition.financeDate" placeholder="活动日期" value-format="yyyy-MM-dd" size="small" @change="dateChange"
                type="daterange" unlink-panels range-separator="至" start-placeholder="活动开始日期" end-placeholder="活动结束日期" :picker-options="pickerOptions2">
            </el-date-picker>
        </el-col>
      </el-row>
      <el-row :gutter="24" style="margin-bottom:16px!important;">
          <el-col align="right" :span="24">
             <el-button size="small" class="searchBtn" @click="emptyAndSearch">清空条件</el-button>
            <el-button size="small" type="primary" class="searchBtn" @click="confirmSearch">查询</el-button>
          </el-col>
      </el-row>
      </el-form>
      <!-- table开始 -->
        <div>

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
            <el-table-column align="center" prop="cityName" label="所属地区" min-width="8%"></el-table-column>
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
        </div>
          <!-- <papers ref="paper" @searchTable="searchTable" :total="total" :pageSize1="pageSize"></papers> -->
          <papers ref="paper" :sdefault="condition_copy" :length1="20" :total="totalCount" @searchTable="search"></papers>
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
      total: 0,
      typeId: "",
      lawData: "",
      pageSize: 20,
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
      selected:'001',
      defaultProps: {
        children: "children",
        label: "name"
      },
      defaultPropss: {
        label: "label"
      },
      formLabelAlign: {
        plate: "",
        date: "",
        data1: "",
        date2: "",
        treeWay: "",
        input1: "",
        input2: "",
        code_value: "001",
        code_name: "",
        city: "",
        autocomplate: ""
      },
      loading: false,
      options4: [], //autocomplate的数据list
      selectSpace: [],
      selectPlate: [],
      selectWay: [],
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

      //双日历数据

      //autocomplate的数据
      options4: [], //autocomplate的数据list
      loading: false,
      plate_value: "",
      //单选下拉数据
      //table数据
      tableData: [],
      multipleSelection: [], //table选中数据
      // orderByName: "", //排序字段
      // orderByOrder: "", //排序方式
      state2: "",
      restaurants: []
    };
  },
  mounted() {
    // this.dataGet(true);
    this.classGet(true);
    this.regionGet(true);
    this.plateGet(true);
    this.confirmSearch();//分页查询
    // this.companyByCode(true)
    // this.restaurants = this.companyByCode()||[];
  },
  methods: {
    changeFinanceIndustry() {
      debugger;
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
    debugger;
      this.$store.dispatch('ipoSearchGet', data).then((data) => {//(方法名，参数)
      debugger;
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
    debugger;
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
    getValue(val){
      if (val == null || val == "null" || val == undefined || val == "undefined") {
        val = "";
      }
      return val
    },
    // loseBlur(e) {
    //   this.formLabelAlign = {
    //     code_value: this.formLabelAlign.code_value,
    //     date: this.formLabelAlign.date,
    //     date1: moment(new Date(this.formLabelAlign.date[0])).format(
    //       "YYYY-MM-DD"
    //     ),
    //     date2: moment(new Date(this.formLabelAlign.date[1])).format(
    //       "YYYY-MM-DD"
    //     ),
    //     input1: this.formLabelAlign.input1,
    //     input2: this.formLabelAlign.input2,
    //     plate: this.formLabelAlign.value,
    //     city: this.formLabelAlign.type,
    //     autocomplate: this.formLabelAlign.autocomplate
    //   };
    //   let params = {
    //     startRow: 0,
    //     pageSize: 20,
    //     orderByName: "financeDate",
    //     orderByOrder: "desc",
    //     condition: {
    //       financeIndustry: "001",
    //       financeIndustry: this.formLabelAlign.code_value,
    //       financeDate:
    //         this.formLabelAlign.date1 +
    //         " " +
    //         "至" +
    //         " " +
    //         this.formLabelAlign.date2,
    //       financingMode: this.formLabelAlign.treeWay,
    //       input2: this.formLabelAlign.input2,
    //       stockBoardSelect: this.formLabelAlign.value,
    //       areaSelect: this.formLabelAlign.type,
    //       companyCodeSearch: this.formLabelAlign.autocomplate
    //     }
    //   };
    //   this.$store.dispatch("ipoSearchGet", params);
    // },
    // handleSelect(item) {
    //   console.log(item);
    // },
    // remoteMethod(query) {
    //   //input拿到的value
    //   const _this = this;
    //   if (query !== "") {
    //     this.formLabelAlign.autocomplate = query;
    //     this.loading = true;
    //     let param = {
    //       companyCode: query
    //     };
    //     this.$store.dispatch("companyByCodeGet", param).then(data => {
    //       _this.options4 = data.map(function(obj, idx) {
    //         return { code: obj.companyCode, name: obj.zhSortName }; //做数据
    //       });
    //       this.loading = false;
    //     });
        
    //   } else {
    //     this.options4 = [];
    //     this.formLabelAlign.autocomplate = ''
    //   }
    // },
    // selectCodeValue() {
    //   let param = {
    //     startRow: 0,
    //     pageSize: 20,
    //     orderByName: "financeDate",
    //     orderByOrder: "desc",
    //     condition: {
    //       financeIndustry: this.formLabelAlign.code_value,
    //       areaSelect: this.formLabelAlign.city,
    //       stockBoardSelect: this.formLabelAlign.plate,
    //       financingMode: this.formLabelAlign.treeWay,
    //       companyCodeSearch: this.formLabelAlign.input2,
    //       financeDate: this.formLabelAlign.date
    //     }
    //   };
    //   this.$store.dispatch("ipoSearchGet", param).then();
    // },
    // selectAChange(e) {
    //   // console.log(window.event.target.innerText)
    //   this.formLabelAlign.autocomplate = window.event.target.innerText
    // },
    // searchTable(data) {
    //   // data = this.getSearchIpo.data;
    //   // console.log("获取table数据", getSearchIpo);
    //   let params = {
    //     startRow: data.startRow - 1,
    //     // start: 0,
    //     pageSize: data.pageSize,
    //     orderByName: "financeDate",
    //     orderByOrder: "desc",
    //     condition: {
    //       financeIndustry: "001"
    //     }
    //   };
    //   this.$store.dispatch("ipoSearchGet", params).then(() => {
    //     this.lawData = this.getSearchIpo;
    //     this.total = this.getSearchIpo.total;
    //   });
    // },
    // seaarch(data) {
    //   if(this.formLabelAlign.date == null) {
    //     this.formLabelAlign.date = []
    //   }
    //   this.formLabelAlign = {
    //     code_value: this.formLabelAlign.code_value,
    //     date: this.formLabelAlign.date,
    //     date1: moment(new Date(this.formLabelAlign.date[0])).format(
    //       "YYYY-MM-DD"
    //     ),
    //     date2: moment(new Date(this.formLabelAlign.date[1])).format(
    //       "YYYY-MM-DD"
    //     ),
    //     input1: this.formLabelAlign.input1,
    //     input2: this.formLabelAlign.input2,
    //     plate: this.formLabelAlign.value,
    //     city: this.formLabelAlign.type,
    //     autocomplate: this.formLabelAlign.autocomplate
    //   };
    //   let params = {
    //     startRow: 0,
    //     pageSize: 20,
    //     orderByName: "financeDate",
    //     orderByOrder: "desc",
    //     condition: {
    //       // financeIndustry: "001",
    //       financeIndustry: this.formLabelAlign.code_value,
    //       financeDate:
    //         this.formLabelAlign.date1 +
    //         " " +
    //         "至" +
    //         " " +
    //         this.formLabelAlign.date2,
    //       financingMode: this.formLabelAlign.treeWay,
    //       input2: this.formLabelAlign.input2,
    //       stockBoardSelect: this.formLabelAlign.value,
    //       areaSelect: this.formLabelAlign.type,
    //       companyCodeSearch: this.formLabelAlign.autocomplate
    //     }
    //   }
    //   this.$store.dispatch("ipoSearchGet", params)
    // },
    // 表格接口
    // dataGet() {
    //   let param = {
    //     startRow: 0,
    //     pageSize: 20,
    //     orderByName: "financeDate",
    //     orderByOrder: "desc",
    //     condition: {
    //       financeIndustry: "001"
    //     }
    //   };
    //   this.$store.dispatch("ipoSearchGet", param);
    // },
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
    // 下拉菜单市场板块
    // handleNodeClick4(data, node, component) {
    //   //共三个参数，依次为：传递给 data 属性的数组中该节点所对应的对象、节点本身是否被选中、节点的子树中是否有被选中的节点
    //   const nodeCheck = this.$refs.treeWay.getCheckedNodes(true); //通过 node 获取(光子节点)
    //   let middle = "";
    //   let middle1 = "";
    //   nodeCheck.map((obj, idx) => {
    //     //拼接字符串
    //     middle += `,${obj.value}`;
    //     middle1 += `,${obj.label}`;
    //   });
    //   this.formLabelAlign.treeWay = middle1.substr(1); //设置input里显示的文字，可扩展
    //   this.formLabelAlign.input1 = middle.substr(1);
    // },
    
    // // 下拉菜单市场板块
    // handleNodeClick3(data, node, component) {
    //   //共三个参数，依次为：传递给 data 属性的数组中该节点所对应的对象、节点本身是否被选中、节点的子树中是否有被选中的节点
    //   const nodeCheck = this.$refs.treePlate.getCheckedNodes(true); //通过 node 获取(光子节点)
    //   let middle = "";
    //   nodeCheck.map((obj, idx) => {
    //     //拼接字符串
    //     middle += `,${obj.label}`;
    //   });
    //   this.formLabelAlign.plate = middle.substr(1); //设置input里显示的文字，可扩展
    // },
    // //下拉菜单城市
    // handleNodeClick2(data, node, component) {
    //   //共三个参数，依次为：传递给 data 属性的数组中该节点所对应的对象、节点本身是否被选中、节点的子树中是否有被选中的节点
    //   const nodeCheck = this.$refs.tree.getCheckedNodes(true); //通过 node 获取(光子节点)
    //   let middle = "";
    //   nodeCheck.map((obj, idx) => {
    //     //拼接字符串
    //     middle += `,${obj.name}`;
    //   });
    //   console.log(middle);
    //   this.formLabelAlign.city = middle.substr(1); //设置input里显示的文字，可扩展
    // },
    // sure() {
    //   //下拉确定
    //   //查询
    //   this.selectSpace = this.$refs.tree.getCheckedNodes();
    //   this.selectPlate = this.$refs.treePlate.getCheckedNodes();
    //   this.selectWay = this.$refs.treeWay.getCheckedNodes();
    //   this.$refs.selectTreeIndex.handleClose(); //关闭下拉框
    //   this.$refs.selectCheckbox.handleClose(); //关闭下拉框
    //   this.$refs.selectCheckbox1.handleClose();
    //   let param = {
    //     startRow: 0,
    //     pageSize: 20,
    //     orderByName: "financeDate",
    //     orderByOrder: "desc",
    //     condition: {
    //       financeIndustry: this.formLabelAlign.code_value,
    //       areaSelect: this.formLabelAlign.city,
    //       stockBoardSelect: this.formLabelAlign.plate,
    //       financingMode: this.formLabelAlign.input1,
    //       companyCodeSearch: this.formLabelAlign.input2,
    //       financeDate: this.formLabelAlign.date
    //     }
    //   };
    //   this.$store.dispatch("ipoSearchGet", param).then();
    // },
    // clear() {
    //   //下拉清空
    //   this.$refs.tree.setCheckedKeys([]); //清空选中
    //   this.$refs.treePlate.setCheckedKeys([]); //清空选中
    //   this.$refs.treeWay.setCheckedKeys([]);
    // },
    // clearAll() {
    //   this.$confirm("您确认要清空吗？", {
    //     cancelButtonText: "取消",
    //     confirmButtonText: "确定",
    //     type: "warning"
    //   })
    //     .then(() => {
    //       this.formLabelAlign = {
    //         // code_value: "请选择",
    //         autocomplate: "",
    //         date: ""
    //       };
    //       let param = {
    //         startRow: 0,
    //         pageSize: 20,
    //         orderByName: "financeDate",
    //         orderByOrder: "desc",
    //         condition: {
    //           financeIndustry: "",
    //           code_value: "",
    //           financeDate: "",
    //           input1: "",
    //           financingMode: "",
    //           input2: "",
    //           plate: "",
    //           city: "",
    //           companyCodeSearch: ""
    //         }
    //       };
    //       this.$store.dispatch("ipoSearchGet", param).then(() => {
    //         this.$refs.tree.setCheckedKeys([]);
    //         this.$refs.treePlate.setCheckedKeys([]);
    //         // console.log(this.$refs.treeDate)
    //         // this.$refs.treeDate.setCheckedKeys([]);
    //         this.$refs.treeWay.setCheckedKeys([]);
    //       });
    //     })
    //     .catch(err => {});
    // },
    // calls(data) {
    //   //下拉回复上次选项
    //   if (!data) {
    //     this.$refs.tree.setCheckedNodes(this.selectSpace); //通过node值设置默认选中
    //     this.$refs.treePlate.setCheckedNodes(this.selectPlate); //通过node值设置默认选中
    //   }
    // },
    //table点击
    // handleClick(row) {
    //   console.log(row);
    // },
    //table排序
    // sortChange(column) {
    //   console.log(column);
    //   if (column.order != null && column.prop != null) {
    //     (this.orderByName = column.prop), (this.orderByOrder = column.order);
    //   } else {
    //     this.orderByName = "";
    //     this.orderByOrder = "";
    //   }
    //   this.$refs.paper.search(this.orderByName, this.orderByOrder);
    // },
    //table选中事件
    // handleSelectionChange(val) {
    //   //val 选中数据
    //   console.log(val);
    //   this.multipleSelection = val;
    // },
    // search(val) {
    //   console.log(val);
    // }
  },
  computed: {
    ...mapGetters([
      // "getSearchIpo",
      "getSFClass",
      "getSFCRegion",
      "getPlateInfo",
      // "getCompanyByCode"
    ])
    // getPlateInfoMate(getSFClass) {
    //   let arr = []
    //   let con = {}
    //   con = [{ code_name: "", code_value: ""}]
    //   con["code_value"] = "010"
    //   con["code_name"] = "请选择"
    //   arr.push(con)
    //   console.log(arr)
    //   return this.getSFClass
    // }
    // 地区数据递归树形结构
    // regionTree(getSFCRegion) {
    //   var arr = this.getSFCRegion;
    //   var province = [];
    //   var cityAll = [];
    //   arr.map((c, p) => {
    //     if (c.parentId == 0) {
    //       province.push(c);
    //     }
    //   });
    //   province.map((obj, idx) => {
    //     var list = [];
    //     arr.map((o, i) => {
    //       if (obj.id == o.parentId) {
    //         list.push(o);
    //         obj.children = list;
    //       }
    //     });
    //     cityAll.push(obj);
    //   });
    //   return cityAll;
    // }
  },
  watch: {},

  created() {}
};
</script>
<style>
/* .el-table__row {
  height: 40px;
}
.el-table__header thead tr > th {
  padding: 0px;
  height: 42px;
}
#teSelect .el-tree-node__content {
  float: left !important;
}
.Hselect .el-scrollbar__wrap {
  min-height: 205px;
}
.hselect{
  height: 140px;
} */
/* .el-input--small .el-input__inner {
  height:32px!important;
}
.container .el-table .el-table__body .cell {
    position: relative;
    padding: 10px;
    overflow: visible;
}
.container .el-table__header thead tr > th {
    line-height: 23px;
    padding: 5px;
}
.bottomHeight{
    height: 35px;
} */
/* .container .el-table {
    position: relative;
    overflow: visible !important;
    box-sizing: border-box;
    -ms-flex: 1;
    flex: 1;
    width: 100%;
    max-width: 100%;
    font-size: 14px;
    color: #606266;
} */
.el-input--small .el-input__inner {
  height:32px!important;
  cursor: pointer;
}
</style>

