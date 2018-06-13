<template> 
<div>
    <!-- 图表部分 -->
    <el-row :gutter="20" class="h100">
        <!-- 图表 -->
        <el-col :span="6" class="chart" style="position:relative"> 
            <div class = "innnerbox">
             <el-row style="margin:0;padding:0"> 
             <p style="font-size:18px "> 公告类别选择</p>
             </el-row>
            <el-input style="width:120px;margin-left:5px;margin-right:0;"
            placeholder="关键词"
            v-model="filterText">
            </el-input> 
            <el-button style="height:36px;margin-left:-5px;margin-top:-3px;margin-right:0" icon="el-icon-search" circle></el-button>  
            <el-select v-model="value" placeholder="请选择" class = "text_search" style="margin-left:15px" @change = "Stataechange">
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
            </div>
        </el-col>
        <!-- 右侧选项卡和table -->
        <el-col :span="17" class="chart">
            <div class="">
                <el-tabs v-model="activeName" @tab-click="handleClick" style="margin:0 0 15px" >
                    <el-tab-pane label="信息披露文件" name="fourth" > 
                     <el-table ref="multipleTable" :data="tableDataComputed" border style="width: 100%" max-height="400" sortable="custom" 
                        @select="sortChange" @selection-change="handleSelectionChange" size="medium">
                    <el-table-column prop="isNess"   label="选择" width="70" checked = true>
                      <template slot-scope="scope">
                              <el-checkbox :checked="scope.row.isNess==1"></el-checkbox> 
                            </template> 
                    </el-table-column><!--多选，不要删了--> 
                        <el-table-column type="index" label="序号" width="70" ></el-table-column>
                        <el-table-column prop="name" label="文件" min-width="315"  ></el-table-column>
                        <el-table-column prop="firstName" label="登报" min-width="70"  >
                            <template slot-scope="scope">
                              <el-checkbox :checked="scope.row.firstName" disabled></el-checkbox> 
                            </template> 
                        </el-table-column>
                        <el-table-column prop="lastName" label="上网" min-width="70"  >
                            <template slot-scope="scope">
                              <el-checkbox :checked="scope.row.lastName"  disabled ></el-checkbox> 
                            </template> 
                        </el-table-column>
                        <el-table-column prop="qweq" label="报备" min-width="70"  >
                            <template slot-scope="scope">
                              <el-checkbox :checked="scope.row.qweq" disabled></el-checkbox> 
                            </template> 
                        </el-table-column>
                        <el-table-column prop="qwe" label="必备" min-width="70"  >
                            <template slot-scope="scope">
                              <el-checkbox :checked="scope.row.qwe" disabled></el-checkbox> 
                            </template>  
                        </el-table-column>
                        <el-table-column prop="totalName" label="所属公告类别" min-width="220"  ></el-table-column> 
                    </el-table> 
                       <input type="button" id="btnExpand" class="btn-primary  form-control" :value="btnText" @click="btnExpand()" />
                    </el-tab-pane>
                    <el-tab-pane label="披露要点" name="first" style="padding:0;">
                      <div class = "collapse">
                         <el-collapse v-model="activeNames" @change="handleChange" >
                          <el-collapse-item :title="titlename"  name="1"  >
                           <p>1、如有个别董事对半年度报告内容的真实性、准确性和完整性无法保证或存在异议，应完整披露有关董事声明和理由</p>

                            <p>2、存在控股股东或者其关联方非经营性占用上市公司资金的，应在半年报中详细披露。情节严重的（标准见《股票上市规则》第13.3.2条），应当按照《股票上市规则》第13.3.4条的规定向深交所报告、提交董事会意见并公告，同时披露股票交易可能被实行其他风险警示情形的风险提示公告</p>

                            <p>3、存在会计差错更正的，需同时选择会计差错更正公告类别。存在重大会计差错更正追溯调整情形的，应按照证监会《编报规则第19号―财务信息的更正及相关披露》的要求，在半年度报告披露之前或同时，以临时公告的形式予以披露，并提交董事会、监事会和独立董事的书面意见和会计师事务所的专项说明，主要内容应包括会计差错更正的性质、原因、合规性、会计处理方法及对本年度财务状况和经营成果的影响金额，涉及追溯调整的，还应说明对以往各年度财务状况和经营成果的影响金额等</p>

                            <p>4、关注分红、追送、业绩补偿等承诺的履行情况</p>

                            <p>5、商业银行、保险公司、证券公司、从事房地产开发业务的公司，应执行中国证监会制定的特殊行业（业务）信息披露特别规定</p>

                           <p> 6、半年度报告中如有下一期业绩预告的，请选择相应业绩预告公告类别</p>
                          </el-collapse-item> 
                         </el-collapse>
                      </div>  
                    </el-tab-pane>
                    <el-tab-pane label="报批材料" name="second">
                           <el-collapse v-model="activeNames" @change="handleChange">
                          <el-collapse-item :title="titlename" name="1">
                           <p> 1、应提交的文件请见《主板信息披露业务备忘录第1号——定期报告披露相关事宜》之附件4</p>

                            <p>2、半年报全文及摘要</p>

                            <p>3、董事会决议及其公告稿（如董事会仅审议本次半年报一项议案且无投反对票或弃权票情形的，可免于公告）</p>

                            <p>4、董事、高管人员签署的书面确认文件</p>

                            <p>5、独立董事关于控股股东及其他关联方占用公司资金、公司对外担保情况的专项说明和独立意见</p>

                           <p>6、监事会以决议方式形成的书面审核意见及公告稿（如监事会决议仅含审议本次半年报一项议案且无投反对票或弃权票情形的，可免于公告）</p>

                            <p>7、由公司法定代表人、主管会计工作的负责人、会计机构负责人（会计主管人员）签字并盖章的财务报告或审计报告（如经审计）</p>

                            <p>8、内幕信息知情人登记档案</p>

                            <p>9、载有本次半年报的Word文件、PDF文件和财务数据的报送系统生成文件（公司应当使用“深交所上市公司定期报告制作系统全文版X.X”或以上版本的软件制作该文件，如有更新版本须及时升级）</p>
                          </el-collapse-item>
                          </el-collapse>
                    </el-tab-pane>
                    <el-tab-pane label="法律法规" name="third">
                         <el-collapse v-model="activeNames" @change="handleChange">
                          <el-collapse-item :title="titlename" name="1">
                            <div class = "lawbox">
                               <el-table ref="multipleTable" :data="tableDataComputed" border style="width: 100%" max-height="400" sortable="custom" 
                                  @select="sortChange" @selection-change="handleSelectionChange" size="medium">
                                  <!-- 序号	法规名称	颁布时间	法律位阶	发文单位	重要性 -->
                                  <el-table-column type="index"  label="序号" width="70"> </el-table-column><!--多选，不要删了--> 
                                  <el-table-column prop="name" label="法规名称" width="350"  sortable></el-table-column>
                                  <el-table-column prop="name" label="颁布时间" min-width="70"  sortable></el-table-column>
                                  <el-table-column prop="firstName" label="法律位阶" min-width="70" sortable></el-table-column>
                                  <el-table-column prop="lastName" label="发文单位" min-width="70"  sortable> </el-table-column>
                                  <el-table-column prop="qweq" label="重要性" min-width="100"  sortable> </el-table-column> 
                               </el-table> 
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
export default {
    data() {
    return {
      btnText : "展开查看更多",
      titlename:"半年度报告",
      ofenFlag:0,  
      tableData:[{ 
        name:"年中报告",
        firstName :true,
        totalName:"010501 半年度报告",
        isNess:1
      },{ 
        name:"年中报告",
        firstName :false,
        totalName:"010501 半年度报告",
        isNess:1
      },{ 
        name:"年中报告111",
        firstName :true,
        totalName:"010501 半年度报告",
         isNess:0
      },{ 
        name:"年中报告111",
        firstName :true,
        totalName:"010501 半年度报告",
         isNess:0
      }],
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
      data3:[],
      defaultProps: {
        children: "children",
        label: "label"
      },
      formSubmit: {
        id: "",
        choseClass: 1
      },
      activeName: "fourth",
      activeNames: ['1']
    };
  },
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
      tableDataComputed(){
        if(this.btnText == '展开查看更多'){ 
          return this.tableData.filter(data =>data.isNess ==1);
        }else{
          return this.tableData;
        }
        
       },
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
    sortChange(){
          console.log("123");
           console.log(this.$refs.multipleTable);
    },
    handleSelectionChange(){

    },
    handleChange(val) {
        console.log(val);
    },
    Stataechange(){

    },
// console.log(fn(data , 0));
    //展开公告
    btnExpand(){ 
      debugger;
      if(this.btnText == '展开查看更多'){
    	//$("#filesN").show(); 
      this.btnText = '显示必备';
      }else{
      this.btnText = '展开查看更多';
      }  
    }, 
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
.el-tabs__header{
  margin: 0 ;
}
.el-collapse-item__header { 
      background:#e7eaef ; 
      height: 36px;
      line-height: 36px;
      padding: 0px 15px;
}
 .el-tabs__item{
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
.innnerbox{
  width: 90%;
  margin: 0 0 0 20px;
  
}

.el-table th>.cell { 
  text-align: center;
  line-height: 22px; 
}
.lawbox .el-table th{  
    background: #A0ADB5;
    color: #fff; 
    font-weight:bolder;
    height: 42px;
} 
.lawbox .el-table th>.cell{
  text-align: left;
  line-height: 22px;  
} 
.lawbox  .el-table__header thead tr>th {
    padding: 0px;
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
.el-table_1_column_10 .cell,.el-table_1_column_11 .cell,.el-table_1_column_12 .cell,.el-table_1_column_13 .cell,.el-table_1_column_14 .cell{
   text-align: left;
  line-height: 22px; 
}
.form-control {
  display: block;
  width: 100%;
  height: 34px;
  padding: 6px 12px;
  font-size: 14px;
  line-height: 1.42857143;
 
  color: #fff;
  background-color: #fff;
  background-image: none;
  border: 1px solid #ccc; 
  border-radius: 4px;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
          box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
  -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
       -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
          transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}
.btn-primary:hover {
    color: #fff;
    border-color: #43b0d0;
    border-bottom-color: #2d95b3;
    background: #50b5d3!important;
    background-image: -webkit-linear-gradient(top,#5cbbd6 0,#43b0d0 100%)!important;
    background-image: linear-gradient(to bottom,#5cbbd6 0,#43b0d0 100%)!important;
    background-repeat: repeat-x;
}
.btn-primary{
    border-color: #0087a7;
    border-bottom-color: #0087a7;
    background: #0087a7;
    background-image: -webkit-linear-gradient(top,#1c2233 0,#0087a7 100%);
    background-image: linear-gradient(to bottom,#0087a7 0,#0087a7 100%);
    background-repeat: repeat-x;
}
.table tbody>tr>td { 
    vertical-align: middle;
}
</style>