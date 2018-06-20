<template> 
<div>
    <!-- 左侧条件选择栏 -->
    <el-row :gutter="20" class="h100"> 
        <el-col :span="6" class="chart" style="position:relative"> 
            <div class = "innnerbox">
             <el-row style="margin:0;padding:0"> 
             <p style="font-size:18px "> 公告类别选择</p>
             </el-row>
             <!-- 关键词查询 -->
            <el-input style="width:120px;margin-left:5px;margin-right:0;"
            placeholder="关键词"
            v-model="filterText">
            </el-input> 
            <el-button style="height:36px;margin-left:-5px;margin-top:-3px;margin-right:0" icon="el-icon-search" circle @click="searchText"></el-button>  
            <el-select v-model="value" placeholder="请选择" class = "text_search" style="margin-left:15px" @change = "Stataechange">
                <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
            </el-select> 
            <!-- 结构树 -->
            <el-tree
            style=" margin-top:10px"
            class="filter-tree"
            node-key="id"
            :data="data4"
            :props="defaultProps"
            :filter-node-method="filterNode"
            :default-expand-all="treeExpand"
             @node-click="handleNodeClick" 
            ref="tree2">
            </el-tree>
            </div>
        </el-col>
        <!-- 右侧选项卡和table -->
        <el-col :span="18" class="chart">
            <div class="">
                <el-tabs v-model="activeName" @tab-click="handleClick" style="margin:0 0 15px" >
                    <el-tab-pane label="信息披露文件"  name="fourth" >   
                     <el-table ref="multipleTable" :data="tableDataComputed" border style="width: 100%"   sortable="custom" row-key="id" 
                        @sort-change="sortChange" @selection-change="handleSelectionChange" size="medium">
                    <el-table-column prop="mast"   label="选择" width="70">
                      <template slot-scope="scope">
                        <div>
                          <div v-if="scope.row.mast==1">
                              <el-checkbox checked></el-checkbox>
                          </div>
                          <div v-else>
                              <el-checkbox></el-checkbox>
                          </div>
                        </div>
                      </template> 
                    </el-table-column><!--多选，不要删了--> 
                        <el-table-column type="index" label="序号" width="70" ></el-table-column>
                        <el-table-column prop="fileTitle" label="文件" min-width="315"  ></el-table-column>
                        <el-table-column prop="newspaper" label="登报" min-width="70"  >
                            <template slot-scope="scope">
                              <el-checkbox :checked="scope.row.newspaper==1" disabled></el-checkbox> 
                            </template> 
                        </el-table-column>
                        <el-table-column prop="internet" label="上网" min-width="70"  >
                            <template slot-scope="scope">
                              <el-checkbox :checked="scope.row.internet==1"  disabled ></el-checkbox> 
                            </template> 
                        </el-table-column>
                        <el-table-column prop="filing" label="报备" min-width="70"  >
                            <template slot-scope="scope">
                              <el-checkbox :checked="scope.row.filing==1" disabled></el-checkbox> 
                            </template> 
                        </el-table-column>
                        <el-table-column prop="mast" label="必备" min-width="70"  >
                            <template slot-scope="scope">
                              <el-checkbox :checked="scope.row.mast==1" disabled></el-checkbox> 
                            </template>  
                        </el-table-column>
                        <el-table-column prop="typeName" label="所属公告类别" min-width="220"  >
                            <template slot-scope="scope"> 
                              {{scope.row.declareTypeNo}} {{scope.row.typeName}}
                            </template> 
                        </el-table-column> 
                    </el-table> 
                       <input type="button" id="btnExpand" class="btn-primary  btn-label" :value="btnText" @click="btnExpand()" />
                    </el-tab-pane>
                    <el-tab-pane label="披露要点" name="first" style="padding:0;">
                      <div class = "collapse">
                         <el-collapse v-model="activeNames" @change="handleChange" >
                          <el-collapse-item :title="titlename"  name="1"  >
                             <p v-for="(item,index)  in ponder" :key="item" v-html="index+1 +','+item"></p>  
                          </el-collapse-item> 
                         </el-collapse>
                      </div>  
                    </el-tab-pane>
                    <el-tab-pane label="报批材料" name="second">
                           <el-collapse v-model="activeNames" @change="handleChange">
                          <el-collapse-item :title="titlename" name="1">
                            <p v-for="(item,index)  in material" :key="item"  v-html="index+1 +','+item"></p> 
                          </el-collapse-item>
                          </el-collapse>
                    </el-tab-pane>
                    <el-tab-pane label="法律法规" name="third">
                         <el-collapse v-model="activeNames" @change="handleChange">
                          <el-collapse-item   name="1">
                                <template slot="title">
                                 <span>{{titlename}} </span>
                                 <div style="float:right;margin-right:80px;">
                                 <input type="checkbox"  v-model="isLose"> 
                                 包含已失效 
                                 </div>
                                </template>
                            <div class = "lawbox">
                               <el-table ref="multipleTable" :data="lawDataCompute" border style="width: 100%"   sortable="custom" 
                                  @sort-change="sortChangeA" @selection-change="handleSelectionChange" size="medium">
                                  <!-- 序号	法规名称	颁布时间	法律位阶	发文单位	重要性 -->
                                  <el-table-column type="index"  label="序号" width="60"> </el-table-column><!--多选，不要删了--> 
                                  <el-table-column prop="lawName" label="法规名称" min-width="330"  sortable>
                                    <template slot-scope="scope">
                                      <p v-if="scope.row.count ==0">
                                        {{scope.row.lawName}} 
                                        <a href="javascript:void(0)" @click="showLawDetail(scope.row)"><font color="#0000E3">(条文阅读)</font></a>  
                                      </p>
                                      <p v-else> 
                                         {{scope.row.lawName}} 
                                        <a href="javascript:void(0)" @click="showLawDetail(scope.row)"><font color="#0000E3">(精准阅读{{scope.row.count}}条)</font></a>  
                                      </p> 
                                    </template> 
                                  </el-table-column>
                                  <el-table-column prop="published" label="颁布时间" min-width="80"  sortable></el-table-column>
                                  <el-table-column prop="lawStatus" label="法律位阶" min-width="80" sortable></el-table-column>
                                  <el-table-column prop="sourceDepartment" label="发文单位" min-width="100" > </el-table-column>
                                  <el-table-column prop="lawStatus" label="重要性" min-width="80"  sortable>  
                                       <template slot-scope="scope">
                                             <el-rate></el-rate> 
                                      </template> 
                                  </el-table-column>  
                               </el-table> 
                               <papers ref="declearPaper" @searchTable="search" :total="total"></papers>
                             </div>  
                          </el-collapse-item>
                          </el-collapse>
                    </el-tab-pane>
                    <el-tab-pane label="经验总结" name="fiveth">
                         <el-collapse v-model="activeNames" @change="handleChange">
                          <el-collapse-item title="一致性 Consistency" name="1">
                            <div>与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；</div>
                            <div>在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。</div>
                          </el-collapse-item>
                          </el-collapse>
                    </el-tab-pane>
                </el-tabs>
                <span style="font-size:12px;color:#333333;">*信披文件登报、上网、报备、 必备的勾选为易董结合实践提供的参考，实际操作以监管要求为准。</span>
            </div>
        </el-col>
    </el-row> 
</div>
</template>
<script>
import { mapGetters } from "vuex";
import papers from "@/views/components-demo/papers";
export default {
  components: { papers },
  data() {
    return {
      total:0,
      typeId: "",
      isLose: false,
      lawData: [],
      material: "",
      ponder: "",
      btnText: "展开查看更多",
      treeExpand: false,
      titlename: "",
      ofenFlag: 0,
      tableData: [],
      options: [
        {
          value: 0,
          label: "全部"
        },
        {
          value: 1,
          label: "常用"
        }
      ],

      value: 0,
      filterText: "",
      data3: [],
      defaultProps: {
        children: "children",
        label: "label"
      },
      formSubmit: {
        id: "",
        choseClass: 1
      },
      activeName: "fourth",
      activeNames: ["1"]
    };
  },
  watch: {
    // filterText(val) {
    //   this.$refs.tree2.filter(val)
    //       this.treeExpand = false
    // },
    data2(n, o) {
      console.log(n);
    },
    tableData(n, o) {
      console.log(n);
    }
  },
  computed: {
    // total() {
    //   console.log(this.lawDataCompute)
    //   return this.lawDataCompute.length;
    // },
    ...mapGetters(["getTableData1"]),
    ...mapGetters(["getFileDatas"]),
    ...mapGetters(["getDeclareData"]),
    ...mapGetters(["getLawsDataToTab"]), 
    //tab1信息披露文件表格选择查看更多时的数据过滤
    tableDataComputed() {
      if (this.btnText == "展开查看更多") {
        return this.getFileDatas.filter(data => data.mast == "1");
      } else {
        return this.getFileDatas;
      }
    },
    //左侧结构树选择常用和全部时进行数据过滤
    data4() {
      var array = [];
      console.log("递归获取树形结构");
      if (this.value == 0) {
        return fn(this.data3, "96210244698524059", 0);
      } else {
        return fn_2(fn(this.data3, "96210244698524059"));
      }
      //将数据递归为树形结构
      function fn(data, pid, state) {
        var result = [],
          temp;
        for (var i in data) {
          if (data[i].parentId == pid) {
            var obj = {};
            obj.id = data[i].id;
            obj.label = data[i].showTypeName;
            obj.pid = data[i].parentId;
            obj.isofen = data[i].oftenFlag;
            obj.declareTypeName = data[i].declareTypeName;
            obj.declareTypeNo = data[i].declareTypeNo;
            result.push(obj);
            temp = fn(data, data[i].id);
            if (temp.length > 0) {
              obj.children = temp;
            }
          }
        }
        return result;
      }
      //将树状结构的数据按常用类型过滤
      function fn_2(result) {
        var temp = [];
        for (var i in result) {
          //从一级父节点判断是否为常用或存在子节点，符合此条件将子集做递归判断，直至过滤为叶子节点为常用的树形结构
          if (
            (result[i].children && result[i].children.length > 0) ||
            result[i].isofen == 1
          ) {
            var obj = result[i].children;
            result[i].children = fn_2(obj);
            if (
              (result[i].children && result[i].children.length > 0) ||
              result[i].isofen == 1
            ) {
              temp.push(result[i]);
            }
          }
        }
        return temp;
      }
    },
    lawDataCompute() {
      if (this.isLose == false) { 
        // return this.lawData.filter(
        //   law => law.lawStatus != "1" && law.lawInvalid != null
        // );
         return this.lawData;
      } else {
        // console.log(this.lawData);
        return this.lawData;
      }
    }
  },
  methods: {
    searchText() {
      this.$refs.tree2.filter(this.filterText);
      // console.log(this.$refs.tree2.store);
      // var node = this.$refs.tree2.store._getAllNodes().filter(item=>item.expanded == true)
      // console.log(node);
      // this.$refs.tree2.store._getAllNodes().map(
      //   function(x){
      //   x.expanded = false;
      //   return x.id;
      //   }
      // )
      // node.map(function(x){
      //   x.expanded = true;
      //   return x.id;
      //   })
    },
    //表格search
    search(data) {
      console.log("获取table数据", data); 
      let params = {};
      params.fromPaper = data.fromPaper-1
      params.length =  data.length
      params.orderByName = data.orderByName
      params.orderByOrder = data.orderByOrder =='descending'?'DESC':'ASC'
      params.typeId = this.typeId
      console.log(params);
      console.log("获取法规分页");
        this.$store.dispatch("getLawsData", params).then(() => {
        console.log(params.data.data);
        this.lawData = params.data.data;
        this.total = params.data.total;
      });
    },

    //点选发生改变
    handleSelectionChange(val) {
      console.log(val);
      this.multipleSelection = val;
    },
    //table排序
    sortChangeA(column) {
      console.log(column);
      if (column.order != null && column.prop != null) {
        (this.orderByName = column.prop), (this.orderByOrder = column.order);
      } else {
        this.orderByName = "";
        this.orderByOrder = "";
      }
      this.$refs.declearPaper.search(this.orderByName, this.orderByOrder);
    },
    sortChange(column) {
      console.log(column);
      if (column.order != null && column.prop != null) {
        (this.orderByName = column.prop), (this.orderByOrder = column.order);
      } else {
        this.orderByName = "";
        this.orderByOrder = "";
      }
      //this.$refs.declearPaper.search(this.orderByName,this.orderByOrder)
    },
    //Collapse 折叠面板发生改变
    handleChange(val) {
      console.log(val);
    },
    //全部-常用点选发生改变
    Stataechange() {},
    // console.log(fn(data , 0));
    //展开公告
    btnExpand() {
      if (this.btnText == "展开查看更多") {
        //$("#filesN").show();
        this.btnText = "显示必备";
      } else {
        this.btnText = "展开查看更多";
      }
    },
    handleClick() {},
    showLawDetail(a) {
      console.log(a);
      var param = "lawId=" + a.lawId + "&typeId=" + this.typeId;
      if (a.count != 0) {
        param += "&jingdu=1";
      }
      // contextPath
      // window.parent.open('https://999000.valueonline.cn/lawsearch/toLawClause?'+param);
      // window.parent.open('http://www.baidu.com');
    },
    //为叶子节点添加点击事件
    handleNodeClick(data, node, index) {
      if (node.isLeaf) {
        this.ztreeClick(
          node.data.declareTypeName,
          node.data.id,
          node.data.declareTypeNo
        );
      }
    },
    filterNode(value, Treedata) {
      let nodes = this.$refs.tree2.getCheckedKeys();
      if (!value) return true;
      return Treedata.label.indexOf(value) !== -1;
    },
    //点击叶子节点后   调取接口 并更新数据
    ztreeClick(a, b, c) {
      console.log(b);
      this.formSubmit.id = b;
      console.log("单击后获取数据");
      let param = {};
      param.typeId = b;
      param.typeName = a;
      let fileparam = {};
      fileparam.typeId = b;
      fileparam.typeName = a;
      fileparam.declareTypeNo = c;
      this.$store.dispatch("getTableData", param).then(() => {
        this.titlename = param.data.typeName;
        this.ponder = param.data.ponder;
        this.material = param.data.material;
        this.lawData = param.data.lawRule.slice(0,10);
        // console.log( "法律法槼",param.data.lawRule);
        this.total = param.data.lawRule.length;
        this.typeId = param.typeId;
      });
      this.$store.dispatch("getfilesData", fileparam).then(() => {
      //  this.tableData = fileparam.data;
      });
    },
    //ajax请求数据
    chartOne(flag) {
      if (this.$route.query != "undefined") {
        if (flag) {
          this.param.countType = 1;
          this.param.chartType = 1;
          this.param.type = "ipodata1";
        }
        this.$store.dispatch("ipoGet", this.param).then(() => {});
      }
    }
  },
  mounted() {
    //页面加载完成时获取ztree数据
    // console.log(this.data3);
    console.log("获取接口数据");
    let param = {};
    param.declare_tree = [];
    this.$store.dispatch("getDeclare", param).then(() => { 
      this.data3 = param.data.data;
    });
  }
};
</script>

<style >
.h40 {
  height: 40px;
}
.tab_width {
  width: 10%;
}
.fullDiv_border {
  width: 100%;
  height: 100%;
  height: 500px;
  border: 1px solid #eee;
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

.singleBtn,
.areaBtn {
  padding: 5px 10px;
  background: #0085a7;
  color: #fff;
  border: 1px solid #0085a7;
  cursor: pointer;
}

.btnGroup .singleBtn:first-child,
.areaBtn:first-child {
  border-radius: 2px 0px 0px 2px;
}
.btnGroup .singleBtn:nth-child(2),
.areaBtn:nth-child(2) {
  border-right: 1px solid #d5d5d5;
  border-left: 1px solid #d5d5d5;
}
.btnGroup .singleBtn:last-child,
.areaBtn:last-child {
  border-radius: 0px 2px 2px 0px;
}
.text_search {
  height: 30px;
  width: 100px;
}
.clickSpan {
  background: #f2f1ef;
  color: #333333;
  border: 1px solid #d5d5d5;
}
.btn {
  height: 30px;
  padding: 5px 10px;
  font-size: 12px;
  line-height: 1.5;
  border-radius: 3px;
}
.el-tabs__header {
  margin: 0;
}
.el-collapse-item__header {
  background: #e7eaef;
  height: 36px;
  line-height: 36px;
  padding: 0px 15px;
}
.el-tabs__item {
  margin: 0;
  height: 56px;
  line-height: 15px;
  padding: 22px;
}
.el-collapse-item__arrow {
  line-height: 36px;
}
.el-tree-node__label {
  font-size: 12px;
  white-space: normal;
}
.innnerbox {
  width: 90%;
  margin: 0 0 0 20px;
}

.el-table th > .cell {
  text-align: center;
  line-height: 22px;
}
.lawbox .el-table th {
  background: #a0adb5;
  color: #fff;
  font-weight: bolder;
  height: 42px;
}
.lawbox .el-table th > .cell {
  text-align: left;
  line-height: 22px;
}

.lawbox .el-table__header thead tr > th {
  padding: 0px;
}

.lawbox .el-table--medium td {
  padding: 10px;
}
.el-table thead tr th {
  background: #fff;
  color: #333;
}
th {
  font-weight: normal;
}
.el-table .cell {
  text-align: center;
  line-height: 43px;
}
.el-table_1_column_10 .cell,
.el-table_1_column_11 .cell,
.el-table_1_column_12 .cell,
.el-table_1_column_13 .cell,
.el-table_1_column_14 .cell {
  text-align: left;
  line-height: 22px;
}
.btn-primary:hover {
  color: #fff;
  border-color: #43b0d0;
  border-bottom-color: #2d95b3;
  background: #50b5d3 !important;
  background-image: -webkit-linear-gradient(
    top,
    #5cbbd6 0,
    #43b0d0 100%
  ) !important;
  background-image: linear-gradient(
    to bottom,
    #5cbbd6 0,
    #43b0d0 100%
  ) !important;
  background-repeat: repeat-x;
}
.btn-primary {
  color: #fff;
  width: 100%;
  height: 30px;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  border-color: #0087a7;
  border-bottom-color: #0087a7;
  background: #0087a7;
  background-image: -webkit-linear-gradient(top, #1c2233 0, #0087a7 100%);
  background-image: linear-gradient(to bottom, #0087a7 0, #0087a7 100%);
  background-repeat: repeat-x;
}
.table tbody > tr > td {
  vertical-align: middle;
}
.el-collapse-item__content {
  padding-bottom: 10px;
}
</style>