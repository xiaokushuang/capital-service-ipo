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
        <span class="titleText" id="reorganizationIntro">股权结构图</span>
        <span v-for="(item,index) in gqNameList" class="hongkuang">{{item}}</span>
      </div>
      <!-- 图片 -->
      <div class="img">
        <img src="../../../assets/images/structure.png" alt>
      </div>
      <!-- 股权股东表格 -->
      <el-table :data="gqTableList" style="width: 100%" stripe border>
        <el-table-column prop="id" type="index" label="序号" align='center'></el-table-column>
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
        <span class="titleText" id="reorganizationIntro">主营业务收入构成</span>
      </div>
      <div class="echart clear">
        <!-- 柱形图 -->
        <div id="barChart" class="l" :style="{width: '400px', height: '300px'}"></div>
        <!-- 饼图 -->
        <div id="pieChart" class="l" :style="{width: '400px', height: '300px'}"></div>
      </div>
      <!-- table表格 -->
      <div class="incomeCompositionTable">
          <el-table
            show-summary
            :summary-method="getSummaries"
            :data="incomeCompositionTableList"
            style="width: 100%;margin-top: 20px">
            <el-table-column
              prop="yewu"
              label="主营业务"
              width="123">
            </el-table-column>
            <!-- <el-table-column label="股东名称" prop="controlsPosition" min-width="14%" align="left">
                    <template slot-scope="scope">
                        <span v-if="scope.row.name">{{scope.row.name}}</span>
                        <span v-else>- -</span>
                    </template>
            </el-table-column> -->
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
      </div>
    </div>
    <!-- 主要竞争对手简介 -->
    <div class="MajorCompetitors">
      <div class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="reorganizationIntro">主要竞争对手简介</span>
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
             <!-- <p class="moreText">{{data.companyIntroduce}}</p> -->
             <p class="moreText">{{sliceText}}</p>
            </div>
          </li>
        </ul>
      </div>
    </div>
    <!-- 报告期前五名供应商情况 -->
    <div class="theTopFive">
       <div class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="reorganizationIntro">报告期前五名供应商情况</span>
      </div>
      <div class="theTopFiveSupplier">
          <el-table
            show-summary
            :data="incomeCompositionTableList"
            style="width: 100%;margin-top: 20px">
            <el-table-column
              fixed
              prop="yewu"
              label="排名"
              width="70">
            </el-table-column>
            <el-table-column
               fixed
              prop="yewu"
              label="公司"
              width="130">
            </el-table-column>
              <el-table-column :label="yearsOne">
                <el-table-column
                  prop="money"
                  label="采购内容"
                  width="100">
                </el-table-column>
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
                  label="采购内容"
                  width="100">
                </el-table-column>
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
              <el-table-column :label="yearsThree">
                <el-table-column
                  prop="money"
                  label="采购内容"
                  width="100">
                </el-table-column>
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
      </div>
    </div>
    <!-- 募集资金运用 -->
    <div class="raiseMoney">
      <div class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="reorganizationIntro">募集资金运用</span>
      </div>
      <div class="raiseMoneyTable">
        <!-- 募集资金运用表格 -->
          <el-table :data="raiseMoneyTableList" border show-summary style="width:100%;">
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
            <el-table-column label="项目总投资(万元)" align="right">
                <template slot-scope="scope">
                      <span v-if="scope.row.xmz">{{scope.row.xmz}}</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
            <el-table-column label="拟投入募集资金金额（万元）" align="right">
                <template slot-scope="scope">
                      <span v-if="scope.row.ntr">{{scope.row.ntr}}</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
            <el-table-column label="占拟募集资金净额比例" align="right">
                <template slot-scope="scope">
                      <span v-if="scope.row.znm">{{scope.row.znm}}</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
            <el-table-column label="前期已投入资金金额（万元）" align="right">
                <template slot-scope="scope">
                      <span v-if="scope.row.qqy">{{scope.row.qqy}}</span>
                    <span v-else>- -</span>
                </template>
            </el-table-column>
        </el-table>
      </div>
    </div>
    <!-- 中介机构 -->
    <div class="IntermediaryInstitutions">
      <div class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="reorganizationIntro">中介机构</span>
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

export default {
  name: "companyProfile",
  components: {},

  data() {
    return {
      yearsOne:"",
      yearsTwo:"",
      yearsThree:"",      
      isLogin: true,
      listLoading: false,
      gqNameList: [],
      gqTableList: [],
      incomeCompositionTableList: [],
      MajorCompetitors: [],
      raiseMoneyTableList: [],
      // 柱形图坐标数据
      xAxis:[],
      yAxis:{},
  
      // zhudataList:[]
      // 柱状图数据
      zhudataList:[
        {
          name: '宽带移动通信设备',
          type: 'bar',
          barWidth:'40%',
          stack: '总量',
          label: {
              normal: {
                  show: true,
                  position: 'insideRight'
              }
          },
          data: [220, 182, 191, 234]
      },
      {
          name: '宽信设备',
          type: 'bar',
          stack: '总量',
          label: {
              normal: {
                  show: true,
                  position: 'insideRight'
              }
          },
          data: [220, 182, 191, 234]
      },
      ]
        
      
      // dataOne:{
      //   name: "宽带移动通信设备",
      //   // data: [35000, 27000, 25200, 15000]
      //       name: '宽带移动通信设备',
      //       type: 'bar',
      //       stack: '总量',
      //       label: {
      //           normal: {
      //               show: true,
      //               position: 'insideRight'
      //           }
      //       },
      //       data: [220, 182, 191, 234, 290, 330, 310]
      // },
      // dataTwo:{
      //   name: "集成业务",
      //   data: [1620, 1532, 0, 400]
      // },
      // dataThree:{
      //   name: "技术开发业务",
      //   data: [1500, 1800, 1000, 1500]
      // },
      // dataFour:{ 
      //   name: "工程业务",
      //   data: [0, 0, 0, 954]
      // },
     };
  },
  created() {
    this.getData();
  },
  mounted() {
    this.drawBarChart();
    this.drawPieChart();
  },
  computed: {
    // 计算属性方法实现超过三行字数用...表示
    sliceText() {
      var moreLengthP = this.MajorCompetitors.length;
      for (let i = 0; i < moreLengthP; i++) {
        var moreP = this.MajorCompetitors[i].companyIntroduce;
        var moreTextHtml = moreP.slice(0, 300) + "......";
        // console.log(moreTextHtml);
      }
      return moreTextHtml;
    }
  },
  methods: {
    //   moke模拟请求的数据
    getData() {
      getGqList("/companyProfile/gqList").then(res => {
        this.gqNameList = res.data.gqjgName;
        this.gqTableList = res.data.gqTable;
        this.incomeCompositionTableList = res.data.incomeCompositionTable;
        this. incomeCompositionTableList2 = res.data.incomeCompositionTable2;
        this. incomeCompositionTableList3 = res.data.incomeCompositionTable3;
        this.MajorCompetitors = res.data.MajorCompetitors;
        this.raiseMoneyTableList = res.data.raiseMoneyTableList;
        this.xAxis = res.data.zhuxing.xAxis;
        this.yAxis = res.data.zhuxing.yAxis;
        // 柱图数据
        this.zhudataList = res.data.zhudataList
        console.log(this.zhudataList)
        // console.log(this.yAxis.length)

        //循环不同年份  
        for(let i=0 ; i<this.incomeCompositionTableList.length;i++){
         this.yearsOne =  this.incomeCompositionTableList[i].years
        }
         for(let i=0 ; i<this.incomeCompositionTableList2.length;i++){
         this.yearsTwo =  this.incomeCompositionTableList2[i].years
         
        }
         for(let i=0 ; i<this.incomeCompositionTableList3.length;i++){
         this.yearsThree =  this.incomeCompositionTableList3[i].years
         
        }
        // 动态获取柱图数据
        for(let i = 0 ;i<this.zhudataList.length;i++){
          const projectdata = {           
            name: '',
            type: 'bar',
            
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
            data: []};

         projectdata.name =  zhudataList[i].yListName
         projectdata.data =  zhudataList[i].yList
         this. zhudataList.push(projectdata);
        }
      });
    },
    // 柱形图
    drawBarChart() {
      let barChart = this.$echarts.init(document.getElementById("barChart"));
      // 绘制图表
      barChart.setOption({
        title: {
           text: "最近3年主营业务趋势",
            textStyle:{
              color: 'red',
              fontWeight:'normal',
              fontSize:16,
            },
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        legend: {
          padding: [
            200, // 上
            0, // 右
            5, // 下
            0 // 左
          ],
          orient: "vertical",
          // x: "center", // 'center' | 'left' | {number},
          // y: 'bottom', // 'center' | 'bottom' | {number}
          data: ["宽带移动通信设备", "集成业务", "技术开发业务", "工程业务"],
          itemWidth: 10, // 图例图形宽度
          itemHeight: 10, // 图例图形高度
          textStyle: {
            color: "#333" // 图例文字颜色
          }
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true
        },
        xAxis: {
          type: "category",
          data:this.xAxis
          // data: this.xAxis
        },
        yAxis: {
          type: "value",
          // data:this.yAxis
        },
        series:this.zhudataList
        // [

          
        //   this.dataOne,
        //   // this.dataTwo,
        //   // this.dataThree,
        //   // this.dataFour
        //   // {
        //     // 根据名字对应到相应的系列
        //     // type: "bar",
        //     // barWidth: "40%",
        //     // stack: "总量",
        //     // name: this.yAxis.name,
        //     // data: this.yAxis
            
        //   //  }
        //   // data: this.yAxis
        // //    {
        // //     name: '直接访问',
        // //     type: 'bar',
        // //     stack: '总量',
        // //     label: {
        // //         normal: {
        // //             show: true,
        // //             position: 'insideRight'
        // //         }
        // //     },
        // //     data: this.dataOne
        // // },
        // // {
        // //     name: '邮件营销',
        // //     type: 'bar',
        // //     stack: '总量',
        // //     label: {
        // //         normal: {
        // //             show: true,
        // //             position: 'insideRight'
        // //         }
        // //     },
        // //     data: [120, 132, 101, 134, 90, 230, 210]
        // // },
        // // {
        // //     name: '联盟广告',
        // //     type: 'bar',
        // //     stack: '总量',
        // //     label: {
        // //         normal: {
        // //             show: true,
        // //             position: 'insideRight'
        // //         }
        // //     },
        // //     data: [220, 182, 191, 234, 290, 330, 310]
        // // },
        // // {
        // //     name: '视频广告',
        // //     type: 'bar',
        // //     stack: '总量',
        // //     label: {
        // //         normal: {
        // //             show: true,
        // //             position: 'insideRight'
        // //         }
        // //     },
        // //     data: [150, 212, 201, 154, 190, 330, 410]
        // // },
        // // {
        // //     name: '搜索引擎',
        // //     type: 'bar',
        // //     stack: '总量',
        // //     label: {
        // //         normal: {
        // //             show: true,
        // //             position: 'insideRight'
        // //         }
        // //     },
        // //     data: [820, 832, 901, 934, 1290, 1330, 1320]
        // // }
        // ]
      });
    },
    // 饼形图
    drawPieChart() {
      let pieChart = this.$echarts.init(document.getElementById("pieChart"));
      pieChart.setOption({
        title: { text: "2017年1-6月 _ 主营业务分布" },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [
          {
            name: "访问来源",
            type: "pie",
            radius: "55%",
            center: ["50%", "60%"],
            data: [
              { value: 335, name: "49%" },
              { value: 310, name: "23%" },
              { value: 234, name: "67%" },
              { value: 135, name: "54%" }
            ],
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)"
              }
            }
          }
        ]
      });
    },
    // 表格合计
    getSummaries() {}
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
  box-shadow: darkgrey 0px 0px 6px 2px;
}
</style>