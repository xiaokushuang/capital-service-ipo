
<template>
  <div class="container">
      <!-- 搜索框开始 -->
      <el-row :gutter="16">
        <el-col :span='8'>
            <el-select ref="selectTreeIndex" v-model="formLabelAlign.type" placeholder="下拉多选框"  
            size="small full" @visible-change="calls">
              <el-option :label="formLabelAlign.type" :value="formLabelAlign.type" >
                <el-tree :data="data2" show-checkbox node-key="id" ref="tree" highlight-current 
                @check-change="handleNodeClick2" :props="defaultProps"></el-tree>  
              </el-option>
              <el-col :span="24" class='selectFull'>
                <el-button type="primary" size="mini" @click="sure">确定</el-button>
                <el-button  size="mini" @click="clear">清空</el-button>
              </el-col>
            </el-select>
        </el-col>
        <el-col :span='8'>
            <el-date-picker size='small' v-model="formLabelAlign.date" type="datetimerange" 
            :picker-options="pickerOptions2" range-separator="至" start-placeholder="开始日期" 
            end-placeholder="结束日期" align="right">
            </el-date-picker>
        </el-col>
        <el-col :span='8'>
            <el-select size="small full" v-model="formLabelAlign.autocomplate" filterable remote reserve-keyword 
            placeholder="autocomplate异步搜索下拉" :remote-method="remoteMethod" :loading="loading">
              <el-option v-for="item in options4" :key="item.value" :label="item.label" 
              :value="item.value"></el-option>
            </el-select>
        </el-col>
      </el-row>

      <!-- 输入框类第二行 -->
      <el-row :gutter="16">
        <el-col :span='8'>
            <el-select v-model="value" placeholder="单选下拉" size='small full'>
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
        </el-col>
        <el-col :span='8'>
            <el-date-picker size='small' v-model="formLabelAlign.date2" type="datetimerange" 
            :picker-options="pickerOptions2" range-separator="至" start-placeholder="开始日期" 
            end-placeholder="结束日期" align="right">
            </el-date-picker>
        </el-col>
        <el-col :span='8'>
        </el-col>
      </el-row>

      <el-row :gutter="16">
          <el-col class='text-right'>
            <el-button type="primary">立即创建</el-button>
            <el-button>取消</el-button>
          </el-col>
      </el-row>


      <!-- table开始 -->
      <el-row :gutter="20">
        <el-col :span="24">
          <el-table :data="tableData" border style="width: 100%" max-height="400" sortable="custom" 
          @sort-change="sortChange" @selection-change="handleSelectionChange" size="medium">
            <el-table-column type="selection" width="50"></el-table-column><!--多选，不要删了-->
            <el-table-column fixed prop="date" label="日期" min-width="150" sortable></el-table-column>
            <el-table-column prop="name" label="姓名" min-width="120" sortable></el-table-column>
            <el-table-column prop="province" label="省份" min-width="120" sortable></el-table-column>
            <el-table-column prop="city" label="市区" min-width="120" sortable></el-table-column>
            <el-table-column prop="address" label="地址" min-width="300" sortable></el-table-column>
            <el-table-column prop="zip" label="邮编" min-width="120" sortable></el-table-column>
            <el-table-column prop="zip" label="邮编" min-width="120" sortable></el-table-column>
            <el-table-column fixed="right" label="操作" min-width="150">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                <el-button type="text" size="small">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>
          <papers ref="paper" @searchTable="search" ></papers>
        </el-col>
      </el-row>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import papers from "@/views/components-demo/papers"
import datepicker from "@/mixins/datepicker"
export default {
  name:'financeSearch',
  mixins:[datepicker],
  components: {
    papers
  },
  data() {
    return {
        //下拉菜单数据
       data2: [
            {id: 1,label: '一级 1',children: [
                  {id: 4,label: '二级 1-1',children: [
                      {id: 9,label: '三级 1-1-1'}, 
                      {id: 10,label: '三级 1-1-2'}
                    ]
                  }
              ]
            }, 
            {id: 2,label: '一级 2',children: [
                {id: 5,label: '二级 2-1'}, 
                {id: 6,label: '二级 2-2'}
              ]
            }, 
            {id: 3,label: '一级 3',children: [
                {id: 7,label: '二级 3-1'}, 
                {id: 8,label: '二级 3-2'}
              ]
            }
        ],
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        formLabelAlign: {
		        relatedParty: '',
            type:'',
            autocomplate:[],
						date:'',
						date2:''
		    },
        selectSpace:[],




        //双日历数据







        //autocomplate的数据
        options4: [],//autocomplate的数据list
        loading: false,

        //单选下拉数据
        options: [{
          value: '选项1',
          label: '黄金糕'
        }, {
          value: '选项2',
          label: '双皮奶'
        }, {
          value: '选项3',
          label: '蚵仔煎'
        }, {
          value: '选项4',
          label: '龙须面'
        }, {
          value: '选项5',
          label: '北京烤鸭'
        }],
        value: '',


        //table数据
        tableData: [
          {date: '2016-05-03',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333}, 
          {date: '2016-05-02',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333},
          {date: '2016-05-04',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333},
          {date: '2016-05-04',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333},
          {date: '2016-05-04',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333},
          {date: '2016-05-04',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333},
          {date: '2016-05-04',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333},
          {date: '2016-05-04',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333},
          {date: '2016-05-04',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333},
          {date: '2016-05-04',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333},
          {date: '2016-05-03',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333}, 
          {date: '2016-05-02',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333},
          {date: '2016-05-04',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333},
          {date: '2016-05-04',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333},
          {date: '2016-05-04',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333},
          {date: '2016-05-04',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333},
          {date: '2016-05-04',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333},
          {date: '2016-05-04',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333},
          {date: '2016-05-04',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333},
          {date: '2016-05-04',name: '王小虎',province: '上海',city: '普陀区',address: '上海市普陀区金沙江路 1518 弄',zip: 200333},
        ],
        multipleSelection: [],//table选中数据
        orderByName:'',//排序字段
        orderByOrder:''//排序方式
    };
  },

  methods: {
      ceshi(a,b){
        console.log(a.target)
        console.log(b)
      },
      //下拉菜单method
      handleNodeClick2(data, node, component){
        //共三个参数，依次为：传递给 data 属性的数组中该节点所对应的对象、节点本身是否被选中、节点的子树中是否有被选中的节点
        
        const nodeCheck = this.$refs.tree.getCheckedNodes(true);//通过 node 获取(光子节点)
        let middle = '';
        nodeCheck.map((obj,idx)=>{//拼接字符串
          middle+=`,${obj.label}`
        })
        this.formLabelAlign.type = middle.substr(1)//设置input里显示的文字，可扩展
        //console.log(this.$refs.tree.getCheckedKeys(true));//通过 key 获取(光子节点)
        //this.$refs.tree.setCheckedKeys([3]);//通过 key 设置
      },
      sure(){//下拉确定
          //查询
          this.selectSpace = this.$refs.tree.getCheckedNodes();
          this.$refs.selectTreeIndex.handleClose()//关闭下拉框
      },
      clear(){//下拉清空
        this.$refs.tree.setCheckedKeys([]);//清空选中
      },
      calls(data){//下拉回复上次选项
        if(!data){
          this.$refs.tree.setCheckedNodes(this.selectSpace)//通过node值设置默认选中
        }
      },


      //autocomplate钩子
      remoteMethod(query) {//input拿到的value
        const _this = this;
        if (query !== '') {
          //console.log(query)
          this.loading = true;
          this.$store.dispatch('complate',query).then((data)=>{//请求
            _this.options4 = data.map(function(obj,idx){
                return {label:obj.anchor,value:obj.writer}//做数据
            })
            this.loading = false;
          })
        } else {
          this.options4 = [];
        }
      },


      //table点击
      handleClick(row) {
        console.log(row);
      },
      //table排序
      sortChange(column){
        console.log(column)
        if(column.order!=null&&column.prop!=null){
          this.orderByName=column.prop,
          this.orderByOrder=column.order
        }else{
          this.orderByName='';
          this.orderByOrder='';
        }
        this.$refs.paper.search(this.orderByName,this.orderByOrder)
      },
      //table选中事件
      handleSelectionChange(val){//val 选中数据
        console.log(val)
        this.multipleSelection = val;
      },
      search(val){
        console.log(val)
      }


  },
  computed: {
  },

  created() {
    let param = {
      "financeIndustry":"001",
      "areaSelect":null,
      "stockBoardSelect":null,
      "financingMode":null,
      "companyCodeSearch":null,
      "financeDate":null,
      "tableAll_length":"20"
    }
    //param = '';
    //"financeIndustry=001&areaSelect=&stockBoardSelect=&financingMode=&companyCodeSearch=&financeDate=&tableAll_length=20"
    this.$store.dispatch('ipoSearch',{'start':0,"length":20,"orderByName":"finance_startdate_dt,finance_sumfina_d","orderByOrder":"DESC,DESC"})
  }
};
</script>
<style>

</style>

