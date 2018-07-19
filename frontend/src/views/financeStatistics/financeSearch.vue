
<template>
  <div class="container">
      <el-row :gutter="24">
        <el-col :span='8'>
            <el-select v-model="formLabelAlign.code_value" placeholder="请选择" size='small full' @change="selectCodeValue">
              <template v-for="item in getSFClass">
              <el-option
                  :key="item.code_value"
                  :label="item.code_name"
                  :value="item.code_value">
              </el-option>
              </template>
            </el-select>
        </el-col>
        <el-col :span='8'>
            <el-select ref="selectTreeIndex" v-model="formLabelAlign.city" placeholder="所在地区"  
            size="small full" @visible-change="calls">
              <el-option :label="formLabelAlign.city" :value="formLabelAlign.city" >
                <el-tree :data="getSFCRegion" show-checkbox node-key="id" ref="tree" highlight-current 
                @check-change="handleNodeClick2" :props="defaultProps"></el-tree>
              </el-option>
              <el-col :span="24" class='selectFull'>
                <el-button  size="mini" @click="clear">清空</el-button>
                <el-button  size="mini" type="primary" @click="sure">确定</el-button>
              </el-col>
            </el-select>
        </el-col>
        <el-col :span='8'>
          <el-select ref="selectCheckbox" v-model="formLabelAlign.plate" placeholder="市场板块" 
          size='small full' @visible-change="calls">
            <el-option :label="formLabelAlign.plate" :value="formLabelAlign.plate">
                <el-tree :data="getPlateInfo" show-checkbox node-key="value" ref="treePlate" highlight-current 
                @check-change="handleNodeClick3" :props="defaultPropss"></el-tree>
            </el-option>
            <el-col :span="24" class='selectFull'>
              <el-button size="mini" @click="clear">清空</el-button>
              <el-button  size="mini" type="primary"  @click="sure">确定</el-button>
            </el-col>
          </el-select>
        </el-col>
      </el-row>
      <el-row :gutter="24">
        <el-col :span='8'>
            <el-select ref="selectCheckbox1" v-model="formLabelAlign.treeWay" placeholder="融资方式" size='small full' @change="selectCodeValue">
                <el-option :label="formLabelAlign.treeWay" :value="formLabelAlign.treeWay">
                  <el-tree :data="listData" show-checkbox node-key="value" ref="treeWay" highlight-current 
                  @check-change="handleNodeClick4" :props="defaultPropss"></el-tree>
              </el-option>
                <el-col :span="24" class='selectFull'>
                  <el-button size="mini" @click="clear">清空</el-button>
                  <el-button  size="mini" type="primary"  @click="sure">确定</el-button>
                </el-col>
            </el-select>
        </el-col>
        <el-col :span='8'>
            <!-- <el-input size='small full' v-model="formLabelAlign.input2"  placeholder="请输入公司代码"></el-input> -->
            <el-autocomplete size='small full' class="inline-input" v-model="state2" 
            :fetch-suggestions="querySearch" 
            placeholder="请输入公司代码、简称、拼音" :trigger-on-focus="false" 
            @select="handleSelect">
              </el-autocomplete>
        </el-col>
        <el-col :span='8'>
            <el-date-picker size='small' v-model="formLabelAlign.date2" type="daterange" 
            :picker-options="pickerOptions2" range-separator="至" start-placeholder="开始日期" 
            end-placeholder="结束日期" align="right" ref="date2">
            </el-date-picker>
        </el-col>
      </el-row>
      <el-row :gutter="16">
          <el-col class='text-right'>
             <el-button  size="mini" @click="clearAll">清空条件</el-button>
            <el-button type="primary" @click="seaarch">查询</el-button>
          </el-col>
      </el-row>
      <!-- table开始 -->
      <el-row :gutter="20">
        <el-col :span="24">
          <el-table :data="getSearchIpo.data" border style="width: 100%" sortable="custom" 
          @sort-change="sortChange" @selection-change="handleSelectionChange" size="medium">
            <el-table-column align="center" type="index" label="序号" width="70px"></el-table-column>
            <el-table-column align="center" prop="securityName" label="公司" min-width="140" sortable></el-table-column>
            <el-table-column align="center" prop="financeDate" label="日期" min-width="225" sortable></el-table-column>
            <el-table-column align="center" prop="pIndName004" label="所属行业" min-width="140"></el-table-column>
            <el-table-column align="center" prop="cityName" label="所属地区" min-width="300"></el-table-column>
            <el-table-column align="center" prop="address" label="融资方式" min-width="140"></el-table-column>
            <el-table-column align="center" prop="sumFina" label="融资金额(亿元)" min-width="200" sortable></el-table-column>
          </el-table>
          <papers ref="paper" @searchTable="searchTable" :total="total" :length1="length"></papers>
        </el-col>
      </el-row>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import papers from "@/views/components-demo/papers";
import datepicker from "@/mixins/datepicker";
import moment from "moment";
export default {
  name: "financeSearch",
  mixins: [datepicker],
  components: {
    papers
  },
  data() {
    return {
      total: 0,
      typeId: "",
      lawData: "",
      length: 20,
      defaultProps: {
        children: "children",
        label: "name"
      },
      defaultPropss: {
        label: "label"
      },
      formLabelAlign: {
        plate: "",
        data1: "",
        date2: "",
        treeWay: "",
        input1: "",
        input2: "",
        code_value: "001",
        code_name: "",
        city: "",
      },
      selectSpace: [],
      selectPlate: [],
      listData: [{
        value : '001',
        label : 'IPO'
      }, {
        value : '002',
        label : '增发'
      }, {
        value : '003',
        label : '配股'
      }, {
        value : '004',
        label : '债券'
      }],

      //双日历数据

      //autocomplate的数据
      options4: [], //autocomplate的数据list
      loading: false,
      plate_value: "",
      //单选下拉数据
      //table数据
      tableData: [],
      multipleSelection: [], //table选中数据
      orderByName: "", //排序字段
      orderByOrder: "", //排序方式
      state2: "",
      restaurants: []
    };
  },
  mounted() {
    this.dataGet(true);
    this.classGet(true);
    this.regionGet(true);
    this.plateGet(true);
    this.restaurants = this.companyByCode();
  },
  methods: {
    handleSelect(item) {
      console.log(item);
    },
    querySearch(queryString, cb) {
      var restaurants = this.restaurants;
      var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      return (restaurant) => {
        console.log(restaurant)
        return (restaurant.companyCode.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    selectCodeValue() {
      let param = {
        startRow: 0,
        pageSize: 20,
        orderByName: "financeDate",
        orderByOrder: "asc",
        condition: {
          financeIndustry: this.formLabelAlign.code_value,
          areaSelect: this.formLabelAlign.city,
          stockBoardSelect: this.formLabelAlign.plate,
          financingMode: this.formLabelAlign.treeWay,
          companyCodeSearch: this.formLabelAlign.input2,
          financeDate: this.formLabelAlign.date2
        }
      };
      this.$store.dispatch("ipoSearchGet", param).then();
    },
    searchTable(data) {
      // data = this.getSearchIpo.data;
      // console.log("获取table数据", getSearchIpo);
      let params = {
        startRow: data.fromPaper - 1,
        // start: 0,
        pageSize: data.length,
        orderByName: "financeDate",
        orderByOrder: "asc",
        condition: {
          financeIndustry: "001"
        }
      };
      this.$store.dispatch("ipoSearchGet", params).then(() => {
        this.lawData = this.getSearchIpo;
        this.total = this.getSearchIpo.total;
      });
    },
    seaarch(data) {
      this.formLabelAlign = {
        code_value: this.formLabelAlign.code_value,
        date1: moment(new Date(this.formLabelAlign.date2[0])).format(
          "YYYY-MM-DD"
        ),
        date2: moment(new Date(this.formLabelAlign.date2[1])).format(
          "YYYY-MM-DD"
        ),
        input1: this.formLabelAlign.input1,
        input2: this.formLabelAlign.input2,
        plate: this.formLabelAlign.value,
        city: this.formLabelAlign.type
      };
      let params = {
        startRow: data.startRow - 1,
        pageSize: data.length,
        orderByName: "financeDate",
        orderByOrder: "asc",
        condition: {
          financeIndustry: "001",
          code_value: this.formLabelAlign.code_value,
          financeDate:
            this.formLabelAlign.date1 +
            " " +
            "至" +
            " " +
            this.formLabelAlign.date2,
          financingMode: this.formLabelAlign.treeWay,
          input2: this.formLabelAlign.input2,
          plate: this.formLabelAlign.value,
          city: this.formLabelAlign.type
        }
      };
      this.$store.dispatch("ipoSearchGet", params);
    },
    // 表格接口
    dataGet() {
      let param = {
        startRow: 0,
        pageSize: 20,
        orderByName: "financeDate",
        orderByOrder: "asc",
        condition: {
          financeIndustry: "001"
        }
      };
      this.$store.dispatch("ipoSearchGet", param);
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
      let param = {
        param1: "123"
      };
      this.$store.dispatch("ipoPlateInfoGet", param);
    },
    companyByCode() {
      let param = {
        q: 0,
        limit: 1000,
        timestamp: 1531814522431,
        companyCode: 0
      }
      this.$store.dispatch("refinanceRecommendGet", param);
    },
    // 下拉菜单市场板块
    handleNodeClick4(data, node, component) {
      //共三个参数，依次为：传递给 data 属性的数组中该节点所对应的对象、节点本身是否被选中、节点的子树中是否有被选中的节点
      const nodeCheck = this.$refs.treeWay.getCheckedNodes(true); //通过 node 获取(光子节点)
      let middle = "";
      let middle1 = "";
      nodeCheck.map((obj, idx) => {
        //拼接字符串
        middle += `,${obj.value}`;
        middle1 += `,${obj.label}`
      });
      this.formLabelAlign.treeWay = middle1.substr(1); //设置input里显示的文字，可扩展
      this.formLabelAlign.input1 = middle.substr(1);
    },
    // 下拉菜单市场板块
    handleNodeClick3(data, node, component) {
      //共三个参数，依次为：传递给 data 属性的数组中该节点所对应的对象、节点本身是否被选中、节点的子树中是否有被选中的节点
      const nodeCheck = this.$refs.treePlate.getCheckedNodes(true); //通过 node 获取(光子节点)
      let middle = "";
      nodeCheck.map((obj, idx) => {
        //拼接字符串
        middle += `,${obj.label}`;
      });
      this.formLabelAlign.plate = middle.substr(1); //设置input里显示的文字，可扩展
    },
    //下拉菜单城市
    handleNodeClick2(data, node, component) {
      //共三个参数，依次为：传递给 data 属性的数组中该节点所对应的对象、节点本身是否被选中、节点的子树中是否有被选中的节点
      const nodeCheck = this.$refs.tree.getCheckedNodes(true); //通过 node 获取(光子节点)
      let middle = "";
      nodeCheck.map((obj, idx) => {
        //拼接字符串
        middle += `,${obj.name}`;
      });
      this.formLabelAlign.city = middle.substr(1); //设置input里显示的文字，可扩展
    },
    sure() {
      //下拉确定
      //查询
      this.selectSpace = this.$refs.tree.getCheckedNodes();
      this.selectPlate = this.$refs.treePlate.getCheckedNodes();
      this.selectSpace = this.$refs.treeWay.getCheckedNodes();
      this.$refs.selectTreeIndex.handleClose(); //关闭下拉框
      this.$refs.selectCheckbox.handleClose(); //关闭下拉框
      this.$refs.selectCheckbox1.handleClose();
      let param = {
        startRow: 0,
        pageSize: 20,
        orderByName: "financeDate",
        orderByOrder: "asc",
        condition: {
          financeIndustry: this.formLabelAlign.code_value,
          areaSelect: this.formLabelAlign.city,
          stockBoardSelect: this.formLabelAlign.plate,
          financingMode: this.formLabelAlign.input1,
          companyCodeSearch: this.formLabelAlign.input2,
          financeDate: this.formLabelAlign.date2
        }
      };
      this.$store.dispatch("ipoSearchGet", param).then();
    },
    clear() {
      //下拉清空
      this.$refs.tree.setCheckedKeys([]); //清空选中
      this.$refs.treePlate.setCheckedKeys([]); //清空选中
      this.$refs.treeWay.setCheckedKeys([]);
    },
    clearAll() {
      this.$confirm("您确认要清空吗？", {
        cancelButtonText: "取消",
        confirmButtonText: "确定",
        type: "warning"
      })
        .then(() => {
          let param = {
            startRow: 0,
            pageSize: 20,
            orderByName: "financeDate",
            orderByOrder: "asc",
            condition: {
              financeIndustry: "",
              code_value: "",
              financeDate: "",
              input1: "",
              financingMode: "",
              input2: "",
              plate: "",
              city: ""
            }
          };
          this.$store.dispatch("ipoSearchGet", param).then(() => {
            this.$refs.tree.setCheckedKeys([]); //清空选中
            this.$refs.treePlate.setCheckedKeys([]); //清空选中
            this.$refs.date2.setCheckedKeys([]);
            this.$refs.treeWay.setCheckedKeys([]);
          });
          this.formLabelAlign.code_name = "请选择";
        })
        .catch(err => {});
    },
    calls(data) {
      //下拉回复上次选项
      if (!data) {
        this.$refs.tree.setCheckedNodes(this.selectSpace); //通过node值设置默认选中
        this.$refs.treePlate.setCheckedNodes(this.selectPlate); //通过node值设置默认选中
      }
    },
    //autocomplate钩子
    remoteMethod(query) {
      //input拿到的value
      const _this = this;
      if (query !== "") {
        //console.log(query)
        this.loading = true;
        this.$store.dispatch("complate", query).then(data => {
          //请求
          _this.options4 = data.map(function(obj, idx) {
            return { label: obj.anchor, value: obj.writer }; //做数据
          });
          this.loading = false;
        });
      } else {
        this.options4 = [];
      }
    },

    //table点击
    handleClick(row) {
      console.log(row);
    },
    //table排序
    sortChange(column) {
      console.log(column);
      if (column.order != null && column.prop != null) {
        (this.orderByName = column.prop), (this.orderByOrder = column.order);
      } else {
        this.orderByName = "";
        this.orderByOrder = "";
      }
      this.$refs.paper.search(this.orderByName, this.orderByOrder);
    },
    //table选中事件
    handleSelectionChange(val) {
      //val 选中数据
      console.log(val);
      this.multipleSelection = val;
    },
    search(val) {
      console.log(val);
    }
  },
  computed: {
    ...mapGetters([
      "getSearchIpo",
      "getSFClass",
      "getSFCRegion",
      "getPlateInfo",
      "getCompanyByCode"
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
.el-table__row {
  height: 40px;
}
</style>

