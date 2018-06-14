<template>
  <div>
    <!-- 标题 -->
    <el-row :gutter="20" class="no-margin-tb">
        <h2>多公司对比</h2>
    </el-row>
    <!-- 时间选项 -->
    <el-row class="list">
        <el-col :span="2">
            <span>时间选择：</span>
        </el-col>
        <el-col :span="12">
            <a @click="activeFun(0)" :class="{active:flag==0}" href="javascript:void(0)">近半年</a>
            <a @click="activeFun(1)" :class="{active:flag==1}" href="javascript:void(0)">近一年</a>
            <a @click="activeFun(2)" :class="{active:flag==2}" href="javascript:void(0)">近三年</a>
            <a @click="activeFun(3)" :class="{active:flag==3}" href="javascript:void(0)">近五年</a>
            <a @click="activeFun(4)" :class="{active:flag==4}" href="javascript:void(0)">近十年</a>
        </el-col>
        <!-- 双日历 -->
        <el-col :span="6">
            <!-- <el-date-picker
                v-model="value5"
                type="daterange"
                align="right"
                unlink-panels
                size="small"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :picker-options="pickerOptions2">
            </el-date-picker> -->
        </el-col>
    </el-row>
    <el-button @click.native="abc">添加公司</el-button>
    <!-- 图表部分 -->
    <el-row :gutter="20" class="h100">
        <el-col :span="24" class="chart">
            
            <el-tabs v-model="activeName2" @tab-click="handleClick" tab-position="right" :span-method="objectSpanMethod">
                <el-tab-pane label="图表模式"  name="first">
                    <div class="fullDiv_border">
                        <!-- 图表 -->
                    <chart-and-table-demo id="companyOto" height='100%' width='100%' :chartData = "tableData"></chart-and-table-demo>
                    <!-- {{chose}} -->
                    <!-- <chart-two v-if="chose==1" height='100%' width='100%' id="two" :chartData = "getIpo"></chart-two> -->
                </div>
                </el-tab-pane>
                <el-tab-pane label="表格模式" name="second">
                    <el-table ref="multipleTable" max-height="600" border :span-method="objectSpanMethod" :row-class-name="tableRowClassName"
                        @cell-mouse-leave="cellMouseLeave" @cell-mouse-enter="cellMouseEnter" :data="tableData" style="width: 100%;">
                        <el-table-column prop="order_number" label="业务类别" align="center" min-width="100"></el-table-column>
                        <el-table-column label="具体业务" align="left" min-width="100">
                            <template slot-scope="scope">
                                <div >
                                <div class="info">
                                    <div>
                                        <div>{{scope.row.clothing_name}}</div>
                                    </div>
                                </div>
                                </div>
                            </template>
                        </el-table-column>
                        </el-table-column>
                        <el-table-column label="00001公司" align="left"  min-width="100">
                            <template slot-scope="scope">
                                <p>{{scope.row.price}}</p>
                            </template>
                        </el-table-column>
                        <el-table-column v-for="(col,idx) in for_abckefgAll" :label="col.name" :key="col.name+idx" min-width="200" align="left" >
                            <template slot-scope="scope">
                                <p>{{col.data[scope.row.rowIndex]}}</p><!--[scope.row.rowIndex]-->
                            </template>
                        </el-table-column>
                    </el-table>
                    <papers ref="paper" @searchTable="search" ></papers>
                </el-tab-pane>
                
            </el-tabs>
        
            
        </el-col>
        <!-- 右侧选项卡和table -->
        <el-col :span="24">
            <peity :type="'pie'" :options="{ 'fill': ['#91c7ae', '#eeeeee'], width: 14, height:14,  'innerRadius': 5, 'radius': 5 }" :data="pieData"></peity>
            <peity :type="'pie'" :options="{ 'fill': ['#91c7ae', '#eeeeee'], width: 14, height:14,  'innerRadius': 0, 'radius': 40 }" data="0.75,0.22"></peity>
            <peity :type="'line'" :options="{ 'fill': '#91c7ae', width: 32, height:15,  'innerRadius': 0, 'radius': 40 }" data="21.5,19.1,19.2,19.7,22.8"></peity>
            <peity :type="'bar'" :options="{ 'fill': ['#91c7ae'], width: 32, height:16,  'innerRadius': 0, 'radius': 40 }" data="5,3,9,-2,2,3,5,2"></peity>
            <peity :type="'donut'" :options="{ 'fill': ['#91c7ae', '#eeeeee'], width: 20, height:20,  'innerRadius': 0, 'radius': 40 }" data="2/7"></peity>
        </el-col>
        
    </el-row>
  </div>
</template>

<script>
import papers from "@/views/components-demo/papers"
import Chart from '@/components/Charts/securitiesChart'
import datepicker from "@/mixins/datepicker"
import {mapGetters} from 'vuex'
import { GetDateDiff } from '@/utils'
import chartBoxOne from './chartBoxOne'
import chartTwo from '@/components/Charts/securitiesChartTwo'
import chartAndTableDemo from '@/components/Charts/chartAndTableDemo'
import Peity from 'vue-peity'
export default {
  name: 'companyOto',
  mixins:[datepicker],
  components: { chartAndTableDemo,papers,Peity },
  data(){
      return {
          value5:'',
          flag:1,
          param:{
              countType:'',
              chartType:'',
              type:'',
              dateSelect:''
          },
          //tableData:[],
          activeName2: 'second',
          data: [1, 2, 3, 2, 2],


          tableData: [{
            finish_time: "0",
            id: 1054,
            order_id: 688,
            order_number: "营运能力",
            price: 123,
            amount: 2,
            clothing_id: 45,
            status: "4",
            total: "72.00",
            address_id: 119,
            user_id: 46,
            addtime: "2018-04-17 15:08:02",
            order_type: "2",
            username: 17621509577,
            clothing_name: "应收款项周转率(次/年)",
            classify_name: "负债占资产比率(%)"
            },
            {
            finish_time: "0",
            id: 1038,
            order_id: 660,
            order_number: "营运能力",
            price:23,
            amount: 2,
            clothing_id: 45,
            status: "4",
            total: "72.00",
            address_id: 109,
            user_id: 46,
            addtime: "2018-04-16 14:26:22",
            order_type: "2",
            username: 17621509577,
            clothing_name: "应收款项周转天数(天)",
            classify_name: "内容"
            },
            {
            finish_time: "0",
            id: 1030,
            order_id: 635,
            order_number: "营运能力",
            price: 123123,
            amount: 20,
            clothing_id: 45,
            status: "2",
            total: "792.00",
            address_id: 140,
            user_id: 33,
            addtime: "2018-04-13 15:06:29",
            order_type: "2",
            username: 17737536671,
            clothing_name: "存货周转率(次/年)",
            classify_name: "长期资金占不动产及设备比率(%)"
            },
            {
            finish_time: "2018-04-13 15:07:42",
            id: 1029,
            order_id: 635,
            order_number: "营运能力",
            price: 12312313,
            amount: 2,
            clothing_id: 45,
            status: "7",
            total: "792.00",
            address_id: 140,
            user_id: 33,
            addtime: "2018-04-13 15:06:29",
            order_type: "2",
            username: 17737536671,
            clothing_name: "存货周转天数(天)",
            classify_name: "内容"
            },
            {
            finish_time: "0",
            id: 1005,
            order_id: 606,
            order_number: "营运能力",
            price: 45,
            amount: 2,
            clothing_id: 45,
            status: "4",
            total: "72.00",
            address_id: 109,
            user_id: 46,
            addtime: "2018-04-12 17:18:29",
            order_type: "1",
            username: 17621509577,
            clothing_name: "固定资产周转率(次/年)",
            classify_name: "内容"
            },
            {
            finish_time: "0",
            id: 1001,
            order_id: 602,
            order_number: "营运能力",
            price: 453,
            amount: 2,
            clothing_id: 45,
            status: "4",
            total: "72.00",
            address_id: 109,
            user_id: 46,
            addtime: "2018-04-12 15:57:34",
            order_type: "2",
            username: 17621509577,
            clothing_name: "完整生意周期(天)",
            classify_name: "内容"
            },
            {
            finish_time: "0",
            id: 988,
            order_id: 545,
            order_number: "营运能力",
            price: 345345,
            amount: 1,
            clothing_id: 45,
            status: "4",
            total: "36.00",
            address_id: 140,
            user_id: 33,
            addtime: "2018-04-10 10:13:59",
            order_type: "2",
            username: 17737536671,
            clothing_name: "应付款项周转天数(天)",
            classify_name: "内容"
            },
            {
            finish_time: "0",
            id: 986,
            order_id: 543,
            order_number: "营运能力",
            price: 232323,
            amount: 3,
            clothing_id: 45,
            status: "4",
            total: "108.00",
            address_id: 109,
            user_id: 46,
            addtime: "2018-04-09 23:06:03",
            order_type: "2",
            username: 17621509577,
            clothing_name: "缺钱天数(天)",
            classify_name: "内容"
            },
            {
            finish_time: "2018-04-17 11:01:42",
            id: 970,
            order_id: 530,
            order_number: "别的业务A",
            price: 232323,
            amount: 1,
            clothing_id: 45,
            status: "5",
            total: "108.00",
            address_id: 140,
            user_id: 33,
            addtime: "2018-04-09 11:51:54",
            order_type: "2",
            username: 17737536671,
            clothing_name: "内容",
            classify_name: "内容"
            },
            {
            finish_time: "2018-04-17 11:07:07",
            id: 969,
            order_id: 530,
            order_number: "别的业务A",
            price: 234234,
            amount: 2,
            clothing_id: 45,
            status: "5",
            total: "108.00",
            address_id: 140,
            user_id: 33,
            addtime: "2018-04-09 11:51:54",
            order_type: "2",
            username: 17737536671,
            clothing_name: "内容",
            classify_name: "内容"
            },
            {
            finish_time: "0",
            id: 907,
            order_id: 428,
            order_number: "别的业务B",
            price: 234234,
            amount: 1,
            clothing_id: 45,
            status: "10",
            total: "36.00",
            address_id: 140,
            user_id: 33,
            addtime: "2018-04-03 19:42:16",
            order_type: "2",
            username: 17737536671,
            clothing_name: "内容",
            classify_name: "内容"
            },
            {
            finish_time: "0",
            id: 884,
            order_id: 405,
            order_number: "别的业务B",
            price: 123123,
            amount: 1,
            clothing_id: 45,
            status: "10",
            total: "36.00",
            address_id: 140,
            user_id: 33,
            addtime: "2018-04-03 17:55:20",
            order_type: "2",
            username: 17737536671,
            clothing_name: "内容",
            classify_name: "内容"
            },
            {
            finish_time: "0",
            id: 823,
            order_id: 366,
            order_number: "别的业务B",
            price: 234234,
            amount: 2,
            clothing_id: 45,
            status: "4",
            total: "2104.00",
            address_id: 137,
            user_id: 63,
            addtime: "2018-04-02 16:51:04",
            order_type: "2",
            username: 17737536677,
            clothing_name: "内容",
            classify_name: "内容"
            },
            {
            finish_time: "0",
            id: 820,
            order_id: 365,
            order_number: "别的业务B",
            price: 56456,
            amount: 3,
            clothing_id: 45,
            status: "4",
            total: "2260.00",
            address_id: 137,
            user_id: 63,
            addtime: "2018-04-02 16:41:45",
            order_type: "2",
            username: 17737536677,
            clothing_name: "内容",
            classify_name: "内容"
            }
        ],
        rowIndex: '-1',
        OrderIndexArr: [],
        hoverOrderArr: [],
        addTable:[],
        for_abckefgAll:[
            {
                name:'00003公司',
                data:[12,32,12,32,12,312313,123123,4324,5656,3123,123,3434,212312,123123]
            },
        ]
      }
      
  },
  props:{
    chose:{
        type:Number,
        default:0
    },
  },
  filters:{
      andDate(value){//全部选项合计过滤器
          if(!value) return ''
          //console.log(value)
          var str = 0;
          value.map((obj,idx)=>{
              str+=obj.value
          })
          return parseFloat(str).toFixed(4)
      }
  },
  methods:{
      abc(){
          this.for_abckefgAll.push(
            {
                name:'00004公司',
                data:[12,32,12,32,12,312313,123123,4324,5656,3123,123,3434,212312,123123]
            })
      },
      getOrderNumber: function () {
        var OrderObj = {}
        this.tableData.forEach(function (element, index) {
          element.rowIndex = index
          if (OrderObj[element.order_number]) {
            OrderObj[element.order_number].push(index)
          } else {
            OrderObj[element.order_number] = []
            OrderObj[element.order_number].push(index)
          }
        }, this);


        for (var k in OrderObj) {
          if (OrderObj[k].length > 1) {
            this.OrderIndexArr.push(OrderObj[k])
          }
        }
      },


      objectSpanMethod: function ({row,column,rowIndex,columnIndex}) {
          //console.log(columnIndex)
        if (columnIndex === 0 /*|| columnIndex === 3 || columnIndex === 4 || columnIndex === 7 || columnIndex ===8 || columnIndex === 9*/) {
          for (var i = 0; i < this.OrderIndexArr.length; i++) {
            var element = this.OrderIndexArr[i]
            for (var j = 0; j < element.length; j++) {
              var item = element[j]
              if (rowIndex == item) {
                if (j == 0) {
                  return {
                    rowspan: element.length,
                    colspan: 1
                  }
                } else if (j != 0) {
                  return {
                    rowspan: 0,
                    colspan: 0
                  }
                }
              }
            }
          }
        }
      },


      tableRowClassName: function ({row,rowIndex}) {
        var arr = this.hoverOrderArr
        for (var i = 0; i < arr.length; i++) {
          if (rowIndex == arr[i]) {
            return 'success-row'
          }
        }
      },


      cellMouseEnter: function (row, column, cell, event) {
        this.rowIndex = row.rowIndex
        this.hoverOrderArr = []
        this.OrderIndexArr.forEach(function (element) {
          if (element.indexOf(this.rowIndex) >= 0) {
            this.hoverOrderArr = element
          }
        }, this);
      },

      cellMouseLeave: function (row, column, cell, event) {
        this.rowIndex = '-1'
      },
      //点击年度选项事件
      activeFun(data){
          this.flag = data;//选中样式
          //给chart换数据
          console.log(this.flag)
          this.param.countType = this.flag;
          this.param.dateSelect = '';
          this.chartOne(false)
      },
      //选项卡点击触发事件
      handleClick(tab, event) {
        //console.log(tab.index, event);
        this.$nextTick(()=>{//选项卡动作结束后，从新计算table列宽
            this.$refs[`table${tab.index}`].doLayout();
        })
      },
      //ajax请求数据
      chartOne(){
        if(this.$route.query!="undefined"){
                // if(flag){
                //     this.param.countType = 1;
                //     this.param.chartType = 2;
                //     this.param.industrySelect = '008';
                //     this.param.type = 'ipodata';
                // }
                // this.$store.dispatch('ipoGet',this.param).then((data)=>{
                //     console.log('chartAndTable')
                //     console.log(data)
                // })
                this.tableData = [
                            {"num":"000011","value":2056.6440,"companyName":"A公司","business":"应收款项周转率(次/年)","business_all":1002},
                            {"num":"000011","value":178.7281,"companyName":"A公司","business":"应收款项周转天数(天)","business_all":1002},
                            {"num":"000011","value":551.5312,"companyName":"A公司","business":"存货周转率(次/年)","business_all":1002},
                            {"num":"000011","value":173.2967,"companyName":"A公司","business":"存货周转天数(天)","business_all":1002},
                            {"num":"000011","value":284.2543,"companyName":"A公司","business":"固定资产周转率(次/年)","business_all":1002},
                            {"num":"000011","value":165.3194,"companyName":"A公司","business":"完整生意周期(天)","business_all":1002},
                            {"num":"000011","value":167.8827,"companyName":"A公司","business":"应付款项周转天数(天)","business_all":1002},
                            {"num":"000011","value":130.1096,"companyName":"A公司","business":"缺钱天数(天)","business_all":1002},
                            {"num":"000011","value":130.1096,"companyName":"A公司","business":"总资产周转率(次/年)","business_all":1002}
                            
                        ]
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
      },
      //tab点击
      handleClick(tab, event) {
        console.log(tab, event);
      }
  },
  computed:{
    pieData () {
        return this.data.toString()
    },
    getAddTable(){
        console.log(this.for_abckefgAll)
        var data = this.addTable.push(this.for_abckefgAll)
        console.log(data)
        return data
    }
  },
  mounted(){
    //页面加载完成时刷新echart图表
    //this.chartOne()
    this.getOrderNumber()
    setInterval(() => {
      // https://vuejs.org/guide/list.html#Mutation-Methods
      this.data.unshift(this.data.pop())
    }, 377)
  },
  watch:{
      value5(n,o){
        //依照操作取数据
        if(n==null){
            this.dateSelect = '';
            this.chartOne(true);
            return false
        }else{
            var d = new Date(n[0]);
            const f = new Date(n[1]);
            const start=d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate()// + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds(); 
            const end = f.getFullYear() + '-' + (f.getMonth() + 1) + '-' + f.getDate()// + ' ' + f.getHours() + ':' + f.getMinutes() + ':' + f.getSeconds(); 
            const flg = GetDateDiff(start,end,'day');
            this.param.countType = 7;
            if(flg>=30){
                this.param.dateSelect = start+' 至 '+end;
                console.log(this.param)
                this.chartOne(false)
            }else{
                this.$message({
                    message: `统计范围应大于一个月,您现在的时间范围为${flg}天`,
                    type: 'warning'
                });
            }
        }
          
      },
      getIpo(n,o){
          console.log('getIpo变了')
          console.log(n)
          //数据变化时更新chart
          //this.tableData = n;
      }
  }
}
</script>

<style scoped>
.h100{
    height:100%;
    min-height:500px;
}
.h100>div.chart{
    height:100%;
    min-height:500px;
}
.fullDiv_border{
    width:100%;
    height:100%;
    height:500px;
    border:1px solid #eee
}
.list{
    line-height:38px;
}
.list a{
    padding:5px 20px;
    font-size:14px;
}
.list a.active{
    color:#0285AC;
}



</style>
<style>
    .el-table .success-row {
      background: #f5f7fa;
    }


    /* .el-table__header tr>th {
      background: #f8f8f8 !important;
    } */


    .el-checkbox__input.is-checked .el-checkbox__inner {
      background-color: #dcdfe6;
      border-color: #dcdfe6;
    }


    .el-checkbox__input.is-focus .el-checkbox__inner {
      background-color: #dcdfe6;
      border-color: #dcdfe6;
    }
  </style>