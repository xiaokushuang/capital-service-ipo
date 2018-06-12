<template> 
<div>
    <!-- 图表部分 -->
    <el-row :gutter="20" class="h100">
        <!-- 图表 -->
        <el-col :span="5" class="chart" style="position:relative"> 
             <el-row style="margin:0;padding:0"> 
             <p style="font-size:18px "> 公告类别选择</p>
             </el-row>
            <el-input style="width:120px;margin-left:5px;margin-right:0;"
            placeholder="关键词"
            v-model="filterText">
            </el-input> 
            <el-button style="height:36px;margin-left:-5px;margin-top:-3px;margin-right:0" icon="el-icon-search" circle></el-button>  
            <el-select v-model="value" placeholder="请选择" class = "text_search" style="margin:0" @change = "Stataechange">
                <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
            </el-select>

            <el-tree
            style=" margin-top:10px"
            class="filter-tree"
            :data="data4"
            :props="defaultProps"
            :filter-node-method="filterNode"
             @node-click="handleNodeClick"
            ref="tree2">
            </el-tree>
        </el-col>
        <!-- 右侧选项卡和table -->
        <el-col :span="18" class="chart">
            <div class="">
                <el-tabs v-model="activeName" @tab-click="handleClick"  >
                    <el-tab-pane label="信息披露文件" name="fourth" >
                        1
                    </el-tab-pane>
                    <el-tab-pane label="披露要点" name="first" >
                        2
                    </el-tab-pane>
                    <el-tab-pane label="报批材料" name="second">
                        3
                    </el-tab-pane>
                    <el-tab-pane label="法律法规" name="third">
                        4
                    </el-tab-pane>
                    <el-tab-pane label="经验总结" name="fiveth">
                        5
                    </el-tab-pane>
                </el-tabs>
            </div>
        </el-col>
    </el-row> 
</div>
</template>
<script>
import { mapGetters } from "vuex";
export default {
  
  watch: {
    filterText(val) {
      this.$refs.tree2.filter(val);
    },
    data2(n, o) {
      console.log(n);
    }
  },
  computed:{
       //...mapGetters(["getDeclare"]),
      data4(){
           var array = [];
           console.log("递归获取树形结构"); 
          if(this.value == 0){
            return fn(this.data3,'96210244698524059',0);
          }else{
            return fn_2(fn(this.data3,'96210244698524059'));
          } 
          //将数据递归为树形结构
          function fn(data,pid,state){
            var result = [] , temp;
             for(var i in data){
                if(data[i].parentId==pid){
                    var obj = {}; 
                    obj.id =data[i].id ;
                    obj.label = data[i].showTypeName;
                    obj.pid = data[i].parentId;
                    obj.isofen = data[i].oftenFlag;
                    result.push(obj);
                    temp = fn(data,data[i].id);           
                    if(temp.length>0){
                        obj.children=temp;
                    }           
                }       
            } 
            return result;
          }
           //将树状结构的数据按常用类型过滤 
          function fn_2(result){ 
                var temp = [];
                for(var i in result){  
                    if((result[i].children&&result[i].children.length>0)||result[i].isofen == 1){
                        var obj = result[i].children;  
                          result[i].children = fn_2(obj);
                         if((result[i].children&&result[i].children.length>0)||result[i].isofen == 1){
                             temp.push(result[i]);
                         } 
                    }          
                } 
                 return temp; 
          }
       
          }
       
  } ,
  methods: { 
    Stataechange(){

    },
// console.log(fn(data , 0));
    handleClick(){

    },
    handleNodeClick(data, node, index) { 
      if (node.isLeaf) {
        this.ztreeClick("", node.data.id);  
      }
    },
    filterNode(value, data) {
        
      let nodes = this.$refs.tree2.getCheckedKeys();
      debugger;
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    ztreeClick(a, b) {
      console.log(b);
      this.formSubmit.id = b; 
       console.log( "单击后获取数据");
     this.$store.dispatch("ipoGet", this.param).then(() => {});
     
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
      //  console.log(param.data.data);
        this.data3 = param.data.data;
    });
   
  },
  data() {
    return {
      ofenFlag:0,  
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
      data2: [
        {
          id: 1,
          label: "一级 1",
          children: [
            {
              id: 4,
              label: "二级 1-1",
              children: [
                {
                  id: 9,
                  label: "三级 1-1-1"
                },
                {
                  id: 10,
                  label: "三级 1-1-2"
                }
              ]
            }
          ]
        },
        {
          id: 2,
          label: "一级 2",
          children: [
            {
              id: 5,
              label: "二级 2-1"
            },
            {
              id: 6,
              label: "二级 2-2"
            }
          ]
        },
        {
          id: 3,
          label: "一级 3",
          children: [
            {
              id: 7,
              label: "二级 3-1"
            },
            {
              id: 8,
              label: "二级 3-2"
            }
          ]
        }
      ], 
      data3:[],
      defaultProps: {
        children: "children",
        label: "label"
      },
      formSubmit: {
        id: "",
        choseClass: 1
      },
      activeName: "fourth"
    };
  }
};
</script>

<style scoped>
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
  width: 80px;
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
</style>