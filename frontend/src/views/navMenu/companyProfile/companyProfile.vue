<template>
  <div class="companyProfile" v-loading="listLoading" element-loading-text="给我一点时间">
    <!-- 公司简介 -->
    <div class="companey">
      <div class="briefIntroduction">
        <p style="color:black">北京花月帮科技股份有限公司</p>
        <ul style="display:flex">
          <li>
            <span>拟上市板块</span>&nbsp;&nbsp;
            <span style="color:black">创业板</span>
          </li>
          <li>
            <span>所属证监会</span>&nbsp;&nbsp;
            <span style="color:black">-</span>
          </li>
          <li>
            <span>证券简称</span>&nbsp;&nbsp;
            <span style="color:black">百邦科技</span>
          </li>
          <li>
            <span>证券代码</span>&nbsp;&nbsp;
            <span style="color:black">300736</span>
          </li>
          <li>
            <span>注册地址</span>&nbsp;&nbsp;
            <span style="color:black">北京市朝阳区</span>
          </li>
          <li>
            <span>注册资本</span>&nbsp;&nbsp;
            <span style="color:black">4073.15万元</span>
          </li>
          <li>
            <span>实际控制人</span>&nbsp;&nbsp;
            <span style="color:black">刘铁峰</span>
          </li>
          <li>
            <span>控股股东</span>&nbsp;&nbsp;
            <span style="color:black">达安世纪.月华忠诚</span>
          </li>
          <li>
            <span>企业性质</span>&nbsp;&nbsp;
            <span style="color:black">民营企业</span>
          </li>
          <li>
            <span>主营业务</span>&nbsp;&nbsp;
            <span style="color:black">手机售后服务.手机</span>
          </li>
        </ul>
      </div>
      <div class="others" v-show="isLogin">
        <p style="color:black">登录其他资本市场</p>
        <ul>
          <li>
            <span>资本市场</span>&nbsp;&nbsp;
            <span style="color:black">刘铁峰</span>
          </li>
          <li>
            <span>公司代码</span>&nbsp;&nbsp;
            <span style="color:black">达安世纪</span>
          </li>
          <li>
            <span>上市日/挂牌日</span>&nbsp;&nbsp;
            <span style="color:black">2018-02-02</span>
          </li>
          <li>
            <span>退市日/摘牌日</span>&nbsp;&nbsp;
            <span style="color:black">---</span>
          </li>
        </ul>
      </div>
    </div>
    <!-- 股权结构图 -->
    <div class="ownershipStructure" style="margin-top:40px">
      <div class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="ownershipStructureChart">股权结构图</span>
        <span v-for="(item,index) in gqNameList" class="hongkuang">{{item}}</span>
      </div>
      <!-- 图片 -->
      <div class="img">
        <img src="../../../assets/images/structure.png" alt>
      </div>
      <!-- 股权股东表格 -->
      <el-table :data="gqTableList" style="width: 100%" stripe border>
        <el-table-column type="index" label="序号" align='center'>
           <template slot-scope="scope">
                    {{scope.$index+1}}
            </template>
        </el-table-column>
        <el-table-column prop="name" label="股东名称"></el-table-column>
        <el-table-column prop="nature" label="股东性质"></el-table-column>
        <el-table-column prop="num" label="持股数量（万股）" align='right'></el-table-column>
        <el-table-column prop="proportion" label="持股比例" align='right'></el-table-column>
      </el-table>
    </div>
    <!-- 主营业务收入构成 -->
    <div class="incomeComposition">
      <div class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="mainBusinessIncomeComposition">主营业务收入构成</span>
      </div>
      <div class="echart clear">
        <barOrPieChart></barOrPieChart>
        <!-- 柱形图 -->
        <!-- <div id="barChart" class="l" :style="{width: '400px', height: '300px'}"></div> -->
        <!-- 饼图 -->
        <!-- <div id="pieChart" class="l" :style="{width: '400px', height: '300px'}"></div> -->
      </div>
      <!-- table表格 -->
      <div class="incomeCompositionTable">
         <mainTable></mainTable>
      </div>
      <!-- <div class="incomeCompositionTable">
          <el-table
            show-summary
            :data="incomeCompositionTableList1"
            style="width: 100%;margin-top: 20px">
            <el-table-column
              prop="yewu"
              label="主营业务"
              width="123">
            </el-table-column>
              <el-table-column :label="yearsOne">
                <el-table-column
                  prop="money"
                  label="金额(万元)"
                  width="100">
                </el-table-column>
                <el-table-column
                  prop="roportion"
                  label="占比"
                  width="84">
                </el-table-column>
              </el-table-column>
               <el-table-column :label="yearsTwo"> 
                 <el-table-column
                  prop="money"
                  label="金额(万元)"
                  width="100">
                </el-table-column>
                <el-table-column
                  prop="roportion"
                  label="占比"
                  width="84">
                  <template slot-scope="scope">
                    {{scope.$index}}
                  </template>
                </el-table-column>
              </el-table-column>
              <el-table-column :label="yearsThree">
                <el-table-column
                  prop="money"
                  label="金额(万元)"
                  width="100">
                </el-table-column>
                <el-table-column
                  prop="roportion"
                  label="占比"
                  width="84">
                </el-table-column> 
               </el-table-column> 
          </el-table>
      </div> -->
    </div>
    <!-- 主要竞争对手简介 -->
    <div class="MajorCompetitors">
      <div class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="profileOfMajorCompetitors">主要竞争对手简介</span>
      </div>
      <div class="competitorContent">
        <ul class="competitorUl">
          <li class="clear competitorLi" v-for="data in MajorCompetitors">
            <div class="leftTitle l" style="">
              <p style="font-family: 'PingFang-SC-Bold', 'PingFang SC Bold', 'PingFang SC';
                font-weight: 700;
                font-style: normal;
                font-size: 14px;
                color: #363636;
                text-align: center;
                line-height: 14px;"
              >{{data.companyName}}</p>
              <p style="color: #999999;font-size: 12px;text-align: center;">{{data.companyId}}</p>
            </div>
            <div class="rightContent l" style=" font-family: 'PingFang-SC-Regular', 'PingFang SC';
                font-weight: 400;
                font-style: normal;
                font-size: 14px;
                color: #555555;
                margin-left:30px;
                line-height: 22px;
                width:70%">
             <p class="moreText">{{data.companyIntroduce}}</p>
             <!-- <p class="moreText">{{sliceText}}</p> -->
             
            </div>
          </li>
        </ul>
      </div>
    </div>
    <!-- 报告期主要供应商及客户情况 -->
    <div class="theTopFive">
       <div class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="majorSuppliers">报告期主要供应商及客户情况</span>
      </div>
      <div class="theTopFiveSupplier">
        <p>报告期前五名供应商情况</p>
        <fifthGysTable></fifthGysTable>
      </div>
       <div class="theTopFiveKh">
        <p>报告期前五名客户情况</p>
        <fifthKhTable></fifthKhTable>
      </div>
    </div>
    <!-- 募集资金运用 -->
    <div class="raiseMoney">
      <div class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="utilizationOfRaisedFunds">募集资金运用</span>
      </div>
      <div class="raiseMoneyTable">
        <!-- 募集资金运用表格 -->
          <el-table :data="raiseMoneyTableList" border style="width:100%;" show-summary :summary-method="getSummaries">
            <el-table-column label="项目名称" align="left">
                <template slot-scope="scope">
                    <span v-if="scope.row.projectName">{{scope.row.projectName}}</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
            <el-table-column label="项目类型" align="center">
                <template slot-scope="scope">
                      <span v-if="scope.row.projectType">{{scope.row.projectType}}</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
            <el-table-column label="项目总投资(万元)" align="right" prop="xmz">
                <template slot-scope="scope">
                      <span v-if="scope.row.xmz">{{scope.row.xmz}}</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
            <el-table-column label="拟投入募集资金金额（万元）" align="right" prop="ntr">
                <template slot-scope="scope">
                      <span v-if="scope.row.ntr">{{scope.row.ntr}}</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
            <el-table-column label="占拟募集资金净额比例" align="right" prop="znm">
                <template slot-scope="scope">
                      <span v-if="scope.row.znm">{{scope.row.znm}}</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
            <el-table-column label="前期已投入资金金额（万元）" align="right" prop="qqy">
                <template slot-scope="scope">
                      <span v-if="scope.row.qqy">{{scope.row.qqy}}</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
        </el-table>
        <p class="shuoming" style="font-family: 'PingFang-SC-Regular', 'PingFang SC';
            font-weight: 400;
            font-style: normal;
            color: #666666;
            font-size:14px;
            line-height:20px; 
            text-align: left;">
            说明：本次募集资金到位前，公司拟以自筹资金和银行借款先行实施；募集资金到位后，公司将用募集资金置换已投入的资金。如果实际募集资金净额不足以完成上述投资项目，不足部分公司将自筹解决。
        </p>
      </div>
    </div>
    <!-- 中介机构 -->
    <div class="IntermediaryInstitutions">
      <div class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="intermediaryInstitutions">中介机构</span>
      </div>
      <div class="InstitutionsDetail">
        <ul>
          <li class="clear InstitutionsDetailLi">
              <div class="image l">
                <img src="../../../assets/images/zhonjie.png" alt>
              </div>
              <div class="text l">
                <div>
                    <span style="font-family: '微软雅黑 Bold', '微软雅黑 Regular', 微软雅黑;font-weight: 700;
                      font-style: normal; font-size: 16px; color: rgb(101, 106, 177);">立信会计师事务所(特殊普通合伙)</span>
                    <span style="background:yellow;color:white;font-size: 12px;">已认证</span>
                    <span style="color: #6633FF;font-size: 12px;">联系他</span>
                </div>
                 <ul>
                    <li class="people">
                      <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                      font-style: normal;  font-size: 12px; color: #999999;">保荐代表人：</span>
                      <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                    </li>
                    <li class="people">
                      <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                      font-style: normal;  font-size: 12px; color: #999999;">保荐代表人：</span>
                      <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                    </li>
                    <li class="people">
                      <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                      font-style: normal;  font-size: 12px; color: #999999;">保荐代表人：</span>
                      <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                    </li>
                    <li class="people">
                      <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                      font-style: normal;  font-size: 12px; color: #999999;">保荐代表人：</span>
                      <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                    </li>
                    <li class="people">
                      <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                      font-style: normal;  font-size: 12px; color: #999999;">保荐代表人：</span>
                      <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                    </li>
                    <li class="people">
                      <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                      font-style: normal;  font-size: 12px; color: #999999;">保荐代表人：</span>
                      <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                    </li>
                 </ul>
              </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import { getGqList } from "@/api/companyProfile";
// 导入主营业务收入构成表格
import mainTable from "@/views/tables/mainTable";
import fifthGysTable from "@/views/tables/fifthGysTable";
import fifthKhTable from "@/views/tables/fifthKhTable";
// 导入柱形图和饼图
import barOrPieChart from "@/components/Charts/barOrPieChart";

export default {
  name: "companyProfile",
  components: {
    mainTable,
    fifthGysTable,
    fifthKhTable,
    barOrPieChart
  },

  data() {
    return {
      dianjizhuzi:false,
      tableData6: [
         {
      projectName:'A',
      projectType:'就卡及出口商的警察都是军事科技吃烧烤',
      xmz:'130',
      ntr:'200',
      znm:'52%',
      qqy:'320'
    },
    {
      projectName:'B',
      projectType:'就卡及出口商的警察都是军事科技吃烧烤',
      xmz:'200',
      ntr:'400',
      znm:'43%',
      qqy:'200'
    },
    {
      projectName:'C',
      projectType:'就卡及出口商的警察都是军事科技吃烧烤',
      xmz:'100',
      ntr:'2300',
      znm:'42%',
      qqy:'2000'
    },
    {
      projectName:'D',
      projectType:'就卡及出口商的警察都是军事科技吃烧烤',
      xmz:'160',
      ntr:'700',
      znm:'52%',
      qqy:'430'
    },
      ],
      yearsOne: "",
      yearsTwo: "",
      yearsThree: "",
      isLogin: true,
      listLoading: false,
      gqNameList: [],
      gqTableList: [],
      incomeCompositionTableList1: [],
      MajorCompetitors: [],
      raiseMoneyTableList: [],
      // 饼状图数据
      // 饼状图标题
      pieChartTitle:'',
      pieChartData: [
        { value: 335, name: "49%" },
        { value: 310, name: "23%" },
        { value: 234, name: "67%" },
        { value: 135, name: "54%" }
      ],
      // 柱状图数据x轴
      zhudataListX: ["2014年7月", "2015年2月", "2016年3月", "2017年6月"],
      // 柱状图数据y轴
      zhudataListY: [
        {
          name: "宽带移动通信设备",
          type: "bar",
          barWidth: "40%",
          stack: "总量",
          label: {
            normal: {
              show: true,
              position: "insideRight"
            }
          },
          data: [220, 182, 191, 234]
        },
        {
          name: "宽信设备",
          type: "bar",
          stack: "总量",
          label: {
            normal: {
              show: true,
              position: "insideRight"
            }
          },
          data: [120, 182, 191, 234]
        },
        {
          name: "集成业务",
          type: "bar",
          barWidth: "20%",
          stack: "总量",
          label: {
            normal: {
              show: true,
              position: "insideRight"
            }
          },
          data: [200, 82, 191, 234]
        },
        {
          name: "工程业务",
          type: "bar",
          stack: "总量",
          label: {
            normal: {
              show: true,
              position: "insideRight"
            }
          },
          data: [220, 182, 191, 534]
        }
      ]
    };
  },
  created() {
    this.getData();
  },
  mounted() {
    // this.drawBarChart();
    // this.drawPieChart();
  },
  methods: {
    // 合计表格函数
    getSummaries(param) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = "总计";
          return;
        }
        if(index === 4){
          // console.log(column)
        }
        const values = data.map(item => Number(item[column.property]));
        if (!values.every(value => isNaN(value))) {
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr);
            if (!isNaN(value)) {
              return prev + curr;
            } else {
              return prev;
            }
          }, 0);
        }
      });

      return sums;
    },
    //   moke模拟请求的数据
    getData() {
      getGqList("/companyProfile/gqList").then(res => {
        this.gqNameList = res.data.gqjgName;
        this.gqTableList = res.data.gqTable;
        this.incomeCompositionTableList1 = res.data.incomeCompositionTable1;
        this.incomeCompositionTableList2 = res.data.incomeCompositionTable2;
        this.incomeCompositionTableList3 = res.data.incomeCompositionTable3;
        this.MajorCompetitors = res.data.MajorCompetitors;
        this.raiseMoneyTableList = res.data.raiseMoneyTableList;
        this.xAxis = res.data.zhuxing.xAxis;
        this.yAxis = res.data.zhuxing.yAxis;
        // 柱图数据
        this.zhudataList = res.data.zhudataList;
        //循环不同年份
        // for (let i = 0; i < this.incomeCompositionTableList1.length; i++) {
        //   this.yearsOne = this.incomeCompositionTableList1[i].years;
        // }
        // for (let i = 0; i < this.incomeCompositionTableList2.length; i++) {
        //   this.yearsTwo = this.incomeCompositionTableList2[i].years;
        // }
        // for (let i = 0; i < this.incomeCompositionTableList3.length; i++) {
        //   this.yearsThree = this.incomeCompositionTableList3[i].years;
        // }
        // debugger
        // 动态获取柱图数据
        // for(let i = 0 ;i<this.zhudataList.length;i++){
        //   const projectdata = {
        //     name: '',
        //     type: 'bar',

        //     stack: '总量',
        //     label: {
        //         normal: {
        //             show: true,
        //             position: 'insideRight'
        //         }
        //     },
        //     data: []};

        //  projectdata.name =  zhudataList[i].yListName
        //  projectdata.data =  zhudataList[i].yList
        //  this. zhudataList.push(projectdata);
        // }
      });
    },
    // // 柱形图
    // drawBarChart() {
    //   var barChart = this.$echarts.init(document.getElementById("barChart"));
    //   // 点击柱状图获取相应数据
    //   barChart.on("click", function(params) {
    //     // console.log(params.name);
    //     this.dianjizhuzi = true
    //     this.pieChartTitle = params.name
    //     console.log(this.pieChartTitle);
    //   });
    //   // 绘制图表
    //   barChart.setOption({
    //     title: {
    //       text: "最近3年主营业务趋势",
    //       textStyle: {
    //         color: "black",
    //         fontWeight: "normal",
    //         fontSize: 16
    //       }
    //     },
    //     tooltip: {
    //       trigger: "axis",
    //       axisPointer: {
    //         // 坐标轴指示器，坐标轴触发有效
    //         type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
    //       }
    //     },
    //     grid: {
    //       left: "3%",
    //       right: "4%",
    //       bottom: "3%",
    //       containLabel: true
    //     },
    //     xAxis: {
    //       type: "category",
    //       // data:["2014年7月", "2015年2月", "2016年3月", "2017年6月"],
    //       data: this.zhudataListX
    //     },
    //     yAxis: {
    //       type: "value"
    //       // data:this.yAxis
    //     },
    //     series: this.zhudataListY
    //     //   series: {
    //     //     name: '宽带移动通信设备',
    //     //     type: 'bar',
    //     //     barWidth:'40%',
    //     //     stack: '总量',
    //     //     label: {
    //     //         normal: {
    //     //             show: true,
    //     //             position: 'insideRight'
    //     //         }
    //     //     },
    //     //     data: [220, 182, 191, 234]
    //     // },
    //   });
    // },
    // // 饼形图
    // drawPieChart() {
    //   let pieChart = this.$echarts.init(document.getElementById("pieChart"));
    //   pieChart.setOption({
    //     // title: { text: this.pieChartTitle+" _ 主营业务分布" },
    //     title: { text: this.dianjizhuzi?this.pieChartTitle:this.zhudataListX[3]+" _ 主营业务分布" },
        
    //     tooltip: {
    //       trigger: "item",
    //       formatter: "{a} <br/>{b} : {c} ({d}%)"
    //     },
    //     legend: {
    //       // padding: [
    //       //   200, // 上
    //       //   0, // 右
    //       //   5, // 下
    //       //   0 // 左
    //       // ],
    //       orient: "vertical",
    //       x: "300", // 'center' | 'left' | {number},
    //       y: "100", // 'center' | 'bottom' | {number}
    //       // data: ["宽带移动通信设备", "集成业务", "技术开发业务", "工程业务"],
    //       itemWidth: 10, // 图例图形宽度
    //       itemHeight: 10, // 图例图形高度
    //       textStyle: {
    //         color: "#333" // 图例文字颜色
    //       }
    //     },
    //     series: [
    //       {
    //         name: "访问来源",
    //         type: "pie",
    //         radius: "55%",
    //         center: ["50%", "60%"],
    //         data: this.pieChartData,
    //         //  [

    //         // { value: 335, name: "49%" },
    //         // { value: 310, name: "23%" },
    //         // { value: 234, name: "67%" },
    //         // { value: 135, name: "54%" }
    //         // ],
    //         itemStyle: {
    //           emphasis: {
    //             shadowBlur: 10,
    //             shadowOffsetX: 0,
    //             shadowColor: "rgba(0, 0, 0, 0.5)"
    //           }
    //         }
    //       }
    //     ]
    //   });
    // }
  }
};
</script>

<style scoped lang="scss">
.l {
  float: left;
}
.r {
  float: right;
}
.clear:after {
  display: block;
  content: "";
  clear: both;
}
.companyProfile {
  padding: 0px 0px;
  .title {
    border-bottom: 1px solid;
    border-bottom-color: #ebebeb;
    height: 42px;
    line-height: 42px;
    background-color: #fafafa;
    display: flex;
    align-items: center;
    margin-top: 40px;
    margin-bottom: 16px;
    .littleRectangle {
      width: 3px;
      height: 18px;
      background-color: #999999;
      display: inline-block;
      margin-right: 12px;
      margin-top: 0px;
      .titleText {
        font-size: 18px;
        color: #333333;
      }
    }
    .hongkuang {
      display: inline-block;
      // width: 52px;
      height: 22px;
      text-align: center;
      border: 1px solid rgba(255, 176, 173, 1);
      font-family: "MicrosoftYaHei", "Microsoft YaHei";
      font-weight: 400;
      font-style: normal;
      font-size: 12px;
      color: #fe5461;
      line-height: 18px;
      margin-left: 10px;
      padding: 1px;
      border-radius: 2px;
    }
  }
  .companey {
    width: 100%;
    background: #fafafa;
    font-family: "Arial Normal", "Arial";
    font-weight: 400;
    font-style: normal;
    font-size: 13px;
    color: #999999;
    text-align: left;
    line-height: normal;
    padding: 5px 10px;
    margin-top: 35px;
    .briefIntroduction {
      border-bottom: 1px solid #ebebeb;
      ul {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        padding-left: 0;
        li {
          width: 25%;
          margin-bottom: 10px;
        }
      }
    }
    .others {
      ul {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        padding-left: 0;
        li {
          width: 25%;
        }
      }
    }
  }
}
//表格样式
.el-table thead tr th {
  background: #f2f2f2;
  color: black;
}
.el-table th > .cell {
  position: relative;
  word-wrap: normal;
  text-overflow: ellipsis;
  vertical-align: middle;
  width: 100%;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  text-align: left;
}
// 主要竞争对手样式
.competitorContent {
  .competitorUl {
    li {
      padding: 20px 40px;
    }
    li:nth-of-type(odd) {
      background: #f9f9f9;
    } //奇数
    li:nth-of-type(even) {
      background: white;
    } //偶数
  }
}
// 中介机构
.InstitutionsDetail {
  ul {
    padding-left: 0;
    width: 100%;
    .InstitutionsDetailLi {
      padding: 20px;
      margin-left: 1%;
      width: 98%;
      height: 130px;
      background: #f0f0f0;
      .image {
        width: 10%;
      }
      .text {
        width: 80%;
        ul {
          width: 100%;
          margin-top: 10px;
          // background:yellow;
          display: flex;
          flex-wrap: wrap;
          li {
            line-height: 20px;
            margin-right: 30px;
            width: 45%;
            // background:green;
          }
        }
      }
    }
  }
}
.InstitutionsDetailLi:hover {
  cursor: pointer;
  // box-shadow: darkgrey 0px 0px 6px 2px;
  box-shadow: 0 0px 28px -5px #ccc;
}
// 多行省略号
.moreText {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}
</style>