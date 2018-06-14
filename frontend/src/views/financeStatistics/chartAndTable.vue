<template>
  <div>
    <!-- 标题 -->
    <el-row :gutter="20" class="no-margin-tb">
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
    <!-- 图表部分 -->
    <el-row :gutter="20" class="h100">
        <el-col :span="24" class="chart">
            
            <el-tabs v-model="activeName2" @tab-click="handleClick" tab-position="right">
                <el-tab-pane label="图表模式"  name="first">
                    <div class="fullDiv_border">
                        <!-- 图表 -->
                    <chart-and-table-demo id="chartAndTable" height='100%' width='100%' :chartData = "tableData"></chart-and-table-demo>
                    <!-- {{chose}} -->
                    <!-- <chart-two v-if="chose==1" height='100%' width='100%' id="two" :chartData = "getIpo"></chart-two> -->
                </div>
                </el-tab-pane>
                <el-tab-pane label="表格模式" name="second">
                    <el-table :data="tableData" border style="width: 100%" max-height="400" sortable="custom" 
                        @sort-change="sortChange" @selection-change="handleSelectionChange" size="medium">
                        <el-table-column type="selection" width="50"></el-table-column><!--多选，不要删了-->
                        <el-table-column fixed prop="num" label="num" min-width="150" sortable></el-table-column>
                        <el-table-column prop="name" label="name" min-width="120" sortable></el-table-column>
                        <el-table-column prop="value" label="value" min-width="120" sortable></el-table-column>
                        <el-table-column prop="firstName" label="firstName" min-width="120" sortable></el-table-column>
                        <el-table-column prop="lastName" label="lastName" min-width="120" sortable></el-table-column>
                        <el-table-column prop="totalName" label="totalName" min-width="120" sortable></el-table-column>
                        <el-table-column prop="date" label="date" min-width="120" sortable></el-table-column>
                        <el-table-column fixed="right" label="操作" min-width="150">
                        <template slot-scope="scope">
                            <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                            <el-button type="text" size="small">编辑</el-button>
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
  name: 'chartAndTable',
  mixins:[datepicker],
  components: { chartAndTableDemo,papers,Peity },
  data(){
      return {
          value5:'',
          flag:1,
          activeName: 'first',
          param:{
              countType:'',
              chartType:'',
              type:'',
              dateSelect:''
          },
          tableData:[],
          activeName2: 'first',
          data: [1, 2, 3, 2, 2]
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
                var data = {
                    "errorCode":null,
                    "errorMsg":null,
                    "result":[
                        {"dataSum":[
                            {"num":1644,"name":"制造业","value":6732.7421,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"},
                            {"num":517,"name":"信息传输、软件和信息技术服务业","value":2056.6446,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"},
                            {"num":134,"name":"科学研究和技术服务业","value":178.7281,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"},
                            {"num":113,"name":"批发和零售业","value":551.5312,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"},
                            {"num":106,"name":"租赁和商务服务业","value":173.2967,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"},
                            {"num":94,"name":"建筑业","value":284.2543,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"},
                            {"num":67,"name":"文化、体育和娱乐业","value":165.3194,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"},
                            {"num":67,"name":"水利、环境和公共设施管理业","value":167.8827,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"},
                            {"num":56,"name":"农、林、牧、渔业","value":130.1096,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"},
                            {"num":54,"name":"交通运输、仓储和邮政业","value":258.6591,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"},
                            {"num":37,"name":"电力、热力、燃气及水生产和供应业","value":277.1692,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"},
                            {"num":28,"name":"金融业","value":1054.9249,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"},
                            {"num":27,"name":"房地产业","value":401.419,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"},
                            {"num":24,"name":"教育","value":17.0837,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"},
                            {"num":18,"name":"居民服务、修理和其他服务业","value":7.8658,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"},
                            {"num":15,"name":"采矿业","value":455.8821,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"},
                            {"num":14,"name":"卫生和社会工作","value":55.799,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"},
                            {"num":7,"name":"住宿和餐饮业","value":2.309,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"},
                            {"num":1,"name":"综合","value":81.653,"firstName":"yan","lastName":"changkun","totalName":"yan chang kun","date":"2018-05-30"}
                            ]
                        }
                    ],
                    "success":true
                }
                this.tableData = data.result[0].dataSum;
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
    }
  },
  mounted(){
    //页面加载完成时刷新echart图表
    this.chartOne()
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
