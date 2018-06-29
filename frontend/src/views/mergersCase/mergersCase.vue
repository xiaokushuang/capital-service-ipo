
<template>
  <div class="container">
      <el-row :gutter="24">
        <!-- <el-col :span='8'>
            <el-select v-model="code_value" placeholder="" size='small full'>
              <el-option
                v-for="item in getSFClass"
                :key="item.code_value"
                :label="item.code_name"
                :value="item.code_value">
              </el-option>
            </el-select>
        </el-col> -->
      <el-col :span="4" class="chart" style="position:relative"> 
            <div class = "innnerbox panel" style="padding: 10px; border: 1px solid #E4E4E4;">  
            <!-- 结构树 -->
            <el-tree
            style=" margin-top:10px"
            class="filter-tree"
            node-key="id"
            :data="Todata2"
            :props="defaultProps_tree"  
            :default-expand-all="treeExpand" 
             @node-click="handleNodeClick" 
            ref="tree2">
            </el-tree> 
            </div>
        </el-col> 
    <el-col :span='20'>
      <div class = "innnerbox panel " style="padding: 10px; border: 1px solid #E4E4E4;">  
        <el-row :gutter="24">
        <el-col :span='8'>
            <!-- <el-select ref="selectTreeIndex" v-model="formLabelAlign.pointAll" placeholder="标题关键字（包含全部）"  
            size="small full" @visible-change="calls">
              <el-option :label="formLabelAlign.pointAll" :value="formLabelAlign.pointAll" >
                <el-tree :data="getSFCRegion" show-checkbox node-key="id" ref="tree" highlight-current 
                @check-change="handleNodeClick2" :props="defaultProps">
                </el-tree>  
              </el-option>
              <el-col :span="24" class='selectFull'>
                <el-button type="primary" size="mini" @click="sure">确定</el-button>
                <el-button  size="mini" @click="clear">清空</el-button>
              </el-col>
            </el-select> -->
             <el-input size='small full' v-model="formLabelAlign.pointAll"  placeholder="标题关键字（包含全部）"></el-input>
        </el-col>  
        <el-col :span='8'>
            <!-- <el-select ref="selectTreeIndex" v-model="formLabelAlign.pointOne" placeholder="标题关键字（任一包含）"  
            size="small full" @visible-change="calls">
              <el-option :label="formLabelAlign.pointOne" :value="formLabelAlign.pointOne" >
                <el-tree :data="getSFCRegion" show-checkbox node-key="id" ref="tree3" highlight-current 
                @check-change="handleNodeClick2" :props="defaultProps"></el-tree>  
              </el-option>
              <el-col :span="24" class='selectFull'>
                <el-button type="primary" size="mini" @click="sure">确定</el-button>
                <el-button  size="mini" @click="clear">清空</el-button>
              </el-col>
            </el-select> -->
            <el-input size='small full' v-model="formLabelAlign.pointOne"  placeholder="标题关键字（任一包含）"></el-input>
        </el-col>
        <el-col :span='8'> 
            <el-input size='small full' v-model="formLabelAlign.pointNot"  placeholder="标题关键字（全不包含）"></el-input>
        </el-col>
      </el-row>
      <el-row :gutter="24">
        <el-col :span='8'> 
             <el-input size='small full' v-model="formLabelAlign.securitiesCode"  placeholder="证券简称/代码"></el-input>
        </el-col>
        <el-col :span='8'>  
          <el-select ref="selectCheckbox"  filterable  placeholder="重组进程" size='small full' v-model="formLabelAlign.process" @visible-change="calls">
            <el-option  v-for="item in getProcess" :label="item.label" :key="item.value" :value="item.value"></el-option> 
          </el-select> 
        </el-col>
         <el-col :span='8'>  
           <el-select ref="selectCheckbox"  filterable  placeholder="证监会行业" size='small full' v-model="formLabelAlign.SFC" @visible-change="calls">
            <el-option  v-for="item in getPlateInfo" :label="item.label" :key="item.value" :value="item.value"></el-option> 
          </el-select>
        </el-col> 
      </el-row>
      <el-row :gutter="24">
          <el-col :span='8'>
            <el-date-picker size='small' v-model="formLabelAlign.date2" type="datetimerange" 
            :picker-options="pickerOptions2" range-separator="至" start-placeholder="发生时间" 
            end-placeholder="结束日期" align="right">
            </el-date-picker>
          </el-col>
          <el-col :span='8'>
            <el-date-picker size='small' v-model="formLabelAlign.date3" type="datetimerange" 
            :picker-options="pickerOptions2" range-separator="至" start-placeholder="更新时间" 
            end-placeholder="结束日期" align="right">
            </el-date-picker>
          </el-col>
 
          <el-col  :span='8' class='text-right'>
            <el-button @click="clear">清空条件</el-button>
            <el-button type="primary" @click="seaarch">查询</el-button>
          </el-col> 
      </el-row>
     
      <!-- table开始 -->
      <el-row :gutter="24">
        <el-col :span="24">  
          <!-- :data="getSearchIpo.data" -->
          <el-table :data="data1" border style="width: 100%"   sortable="custom" 
          @sort-change="sortChange" @selection-change="handleSelectionChange" size="medium">
             <el-table-column align="center" type="index" label="序号" width="80px"></el-table-column>
             <el-table-column align="center" prop="companyName" label="公司" min-width="120" sortable></el-table-column>
             <el-table-column align="center" prop="securityName" label="案例标题" min-width="340" sortable>
                <template slot-scope="scope" >
                  <p style="text-align: left"> {{scope.row.securityName}}</p>
                </template>
             </el-table-column>
             <el-table-column align="center" prop="financeDate" label="重组进程" min-width="140" sortable>
                <template slot-scope="scope">
                  <p v-if="scope.row.financeDate =='交易所问询'" style="color:#3399FF"> 
                   {{scope.row.financeDate}} 
                  </p>
                   <p v-else-if="scope.row.financeDate =='重大资产重组停牌'" style="color:#FF6600">  
                  {{scope.row.financeDate}}
                  </p>  
                   <p v-else-if="scope.row.financeDate =='复牌'" style="color:#CC3366">  
                  {{scope.row.financeDate}}
                  </p>  
                  <p v-else>
                     {{scope.row.financeDate}}
                  </p>
                </template> 
            </el-table-column>
            <el-table-column align="center" prop="pIndName004" label="重组类型" min-width="120"></el-table-column>
            <el-table-column align="center" prop="cityName" label="重组方式" min-width="120"></el-table-column>
            <el-table-column align="center" prop="address" label="重组目的" min-width="120"></el-table-column>
            <el-table-column align="center" prop="sumFina" label="更新时间" min-width="120" sortable></el-table-column>
            <el-table-column fixed="right" label="操作" min-width="150">
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)" type="text" size="small">收藏</el-button>
              <el-button @click="shareClick(scope.row)"  type="text" size="small">分享</el-button>
            </template>
             </el-table-column>
          </el-table>
          <papers ref="paper" @searchTable="search" ></papers>
        </el-col>  
      </el-row> 
      </div>
    </el-col>
   </el-row>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import papers from "@/views/components-demo/papers";
import datepicker from "@/mixins/datepicker";
export default {
  name: "financeSearch",
  mixins: [datepicker],
  components: {
    papers
  },
  data() {
    return {
      treeExpand: true,
      defaultProps: {
        children: "children",
        label: "name"
      },
       defaultProps_tree: {
        children: "children",
        label: "label"
      },
      formLabelAlign: {
        pointAll:"",
        pointOne:"",
        pointNot:"",
        securitiesCode:"",
        process:"",
        SFC:"", 
        date: "",
        date2: "",
        date3:"", 
        type:""
      },
      selectSpace: [],
      data1:[
        {
          id:'122312',
          companyName : '600896 \n *ST海投',
          securityName : '*ST海投（600897）重大资产出售暨关联交易报告书（草案）',
          financeDate :'交易所问询',
          pIndName004 :'类型1、类型2',
          cityName : '方式1、方式2',
          address :'目的1、目的2',
          sumFina: '2018-05-23'
        },
        {
          id:'122312',
          companyName : '600896 *ST海投',
          securityName : '*ST海投（600898）重大资产出售暨关联交易报告书（草案）',
          financeDate :'重大资产重组停牌',
          pIndName004 :'类型1、类型2',
          cityName : '方式1、方式2',
          address :'目的1、目的2',
          sumFina: '2018-05-23'
        },
        {
          id:'122312',
          companyName : '600896 *ST海投',
          securityName : '*ST海投（600899）重大资产出售暨关联交易报告书（草案）',
          financeDate :'复牌',
          pIndName004 :'类型1、类型2',
          cityName : '方式1、方式2',
          address :'目的1、目的2',
          sumFina: '2018-05-23'
        },
        {
          id:'122312',
          companyName : '600892 *ST海投',
          securityName : '*ST海投（600892）重大资产出售暨关联交易报告书（草案）',
          financeDate :'董事会预案',
          pIndName004 :'类型1、类型2',
          cityName : '方式1、方式2',
          address :'目的1、目的2',
          sumFina: '2018-05-23'
        },
        {
          companyName : '600896 *ST海投',
          securityName : '*ST海投（600896）重大资产出售暨关联交易报告书（草案）',
          financeDate :'交易所问询',
          pIndName004 :'类型1、类型2',
          cityName : '方式1、方式2',
          address :'目的1、目的2',
          sumFina: '2018-05-23'
        },
        {
          companyName : '600896 *ST海投',
          securityName : '*ST海投（600896）重大资产出售暨关联交易报告书（草案）',
          financeDate :'交易所问询',
          pIndName004 :'类型1、类型2',
          cityName : '方式1、方式2',
          address :'目的1、目的2',
          sumFina: '2018-05-23'
        },
        {
          companyName : '600896 *ST海投',
          securityName : '*ST海投（600896）重大资产出售暨关联交易报告书（草案）',
          financeDate :'交易所问询',
          pIndName004 :'类型1、类型2',
          cityName : '方式1、方式2',
          address :'目的1、目的2',
          sumFina: '2018-05-23'
        },
        {
          companyName : '600896 *ST海投',
          securityName : '*ST海投（600896）重大资产出售暨关联交易报告书（草案）',
          financeDate :'交易所问询',
          pIndName004 :'类型1、类型2',
          cityName : '方式1、方式2',
          address :'目的1、目的2',
          sumFina: '2018-05-23'
        },
        {
          companyName : '600896 *ST海投',
          securityName : '*ST海投（600896）重大资产出售暨关联交易报告书（草案）',
          financeDate :'交易所问询',
          pIndName004 :'类型1、类型2',
          cityName : '方式1、方式2',
          address :'目的1、目的2',
          sumFina: '2018-05-23'
        },
        {
          companyName : '600896 *ST海投',
          securityName : '*ST海投（600896）重大资产出售暨关联交易报告书（草案）',
          financeDate :'交易所问询',
          pIndName004 :'类型1、类型2',
          cityName : '方式1、方式2',
          address :'目的1、目的2',
          sumFina: '2018-05-23'
        },
        {
          companyName : '600896 *ST海投',
          securityName : '*ST海投（600896）重大资产出售暨关联交易报告书（草案）',
          financeDate :'交易所问询',
          pIndName004 :'类型1、类型2',
          cityName : '方式1、方式2',
          address :'目的1、目的2',
          sumFina: '2018-05-23'
        }
      ],
      //双日历数据
      data2: [
        {
          id: 1,
          name: "重组目的",
          children: [
            {
              id:'',
              name:'整体上市'
            },
            {
              id:'',
              name:'借壳上市'
            },
            {
              id: 4,
              name: "产业并购",
              children: [
                {
                  id: 9,
                  name: "业务转型"
                },
                {
                  id: 10,
                  name: "多元化战略"
                }
              ]
            }
          ]
        },
        {
          id: 2,
          name: "重组类型",
          children: [
            {
              id: 5,
              name: "资产出售"
            },
            {
              id: 6,
              name: "资产收购"
            },
            {
              id: '',
              name: "资产重组",
              children: [
                {
                  id: 9,
                  name: "业务转型"
                },
                {
                  id: 10,
                  name: "多元化战略"
                }
              ]
            }
          ]
        },
        {
          id: 3,
          name: "重组方式",
          children: [
            {
              id: 7,
              name: "资产重组"
            },
            {
              id: 8,
              name: "负债重组"
            }
          ]
        },
         {
          id: 11,
          name: "所属证监局",
          children: [
            {
              id: 12,
              name: "安徽证监局",
              children: [
                {
                  id: 13,
                  name: "中心区证监局"
                },
                {
                  id: 14,
                  name: "北城区证监局"
                }
              ]
            },
            {
              id: 12,
              name: "北京证监局",
              children: [
                {
                  id: 13,
                  name: "海淀区证监局"
                },
                {
                  id: 14,
                  name: "北海区证监局"
                }
              ]
            }
          ]
        }
      ],
      getProcess:[
        {
          label:'达成意向',
          value : 1
        },{
          label:'签署协议',
          value : 2
        },{
          label:'商务部批准',
          value : 3
        }
      ],
      //autocomplate的数据
      options4: [], //autocomplate的数据list
      loading: false,
      code_value: "001",
      plate_value: "",
      selectPlate: "",
      //单选下拉数据
      //table数据
      tableData: [],
      multipleSelection: [], //table选中数据
      orderByName: "", //排序字段
      orderByOrder: "" //排序方式
    };
  },
  mounted() {
    this.dataGet(true);
    this.classGet(true);
    this.regionGet(true);
    this.plateGet(true);
  },
  methods: {
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
    ztreeClick(a, b, c) {},
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    }, 
    seaarch() {
      // this.formLabelAlign = {
      //   code_value: this.code_value,
      //   date2: this.formLabelAlign.date2,
      //   input1: this.formLabelAlign.input1,
      //   input2: this.formLabelAlign.input2,
      //   plate: this.formLabelAlign.plate
      // }; 
      console.log(this.formLabelAlign);
    },
    // 表格接口
    dataGet() {
      let param = {
        start: 0,
        length: 20,
        orderByName: "financeDate",
        orderByOrder: "asc",
        condition: {
          financeIndustry: "001"
        }
      };
      this.$store.dispatch("ipoSearchGet", param).then();
    },
    // 分类
    classGet() {
      this.$store.dispatch("ipoSFClassGet").then();
    },
    // 地区
    regionGet() {
      this.$store.dispatch("ipoSFCRegionGet").then(() => {
        this.regionTree(this.getSFCRegion);
      });
    },
    // 板块信息
    plateGet() {
      let param = {
        param1: "123"
      };
      this.$store.dispatch("ipoPlateInfoGet", param).then();
    },
    // 地区数据递归树形结构
    regionTree(list) {
      var city = [];
      var k = 0;
      for (var i = 0; i < list.length; i++) {
        for (var j = 0; j < list.length; j++) {
          if (list[i].id == list[j].parentId) {
            list[i][j] = city[k];
            k++;
          }
        }
      }
      console.log(city);
    },
    //下拉菜单method
    handleNodeClick2(data, node, component) {
      //共三个参数，依次为：传递给 data 属性的数组中该节点所对应的对象、节点本身是否被选中、节点的子树中是否有被选中的节点
      //  console.log(node);
      //  console.log(component);
      //  console.log(data);
      const nodeCheck = this.$refs.tree.getCheckedNodes(true); //通过 node 获取(光子节点)
      let middle = "";
      nodeCheck.map((obj, idx) => {
        //拼接字符串
        middle += `,${obj.name}`;
      });
      this.formLabelAlign.pointAll = middle.substr(1); //设置input里显示的文字，可扩展
      //console.log(this.$refs.tree.getCheckedKeys(true));//通过 key 获取(光子节点)
      //this.$refs.tree.setCheckedKeys([3]);//通过 key 设置
    },
    sure() {
      //下拉确定
      //查询
      debugger;
      this.selectSpace = this.$refs.tree.getCheckedNodes();
      this.$refs.selectTreeIndex.handleClose(); //关闭下拉框
      this.$refs.selectCheckbox.handleClose(); //关闭下拉框
    },
    clear() {
      //下拉清空
      //this.$refs.tree.setCheckedKeys([]); //清空选中
      this.formLabelAlign.SFC = "";
      for(let key in this.formLabelAlign){
          this.formLabelAlign[key] = ""
      }

    },
    calls(data) {
      console.log(data);
      //下拉回复上次选项
      // if (!data) {
      //   this.$refs.tree.setCheckedNodes(this.selectSpace); //通过node值设置默认选中
      // }
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
    shareClick(row){
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
      "getPlateInfo"
    ]),
    Todata2(){  
       fn(this.data2); 
      function fn(data){ 
        for(var i in data){ 
           var num = 0
          if(data[i].children){  
             num += (data[i].children.length + fn(data[i].children))
            data[i].label =  data[i].name +"("+num +")";
          // fn(data[i].children);
          }else{
             data[i].label =  data[i].name  
          }
        } 
        return num
      }  
      
      return this.data2;
    }
  },
  watch: {},

  created() {}
};
</script>
<style>
.innnerbox{
  border-color: #e4e4e4; 
  border: 1px solid transparent;
}
.panel { 
    background-color: #fff;
    border: 1px solid transparent;
    -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
    box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
}
</style>

