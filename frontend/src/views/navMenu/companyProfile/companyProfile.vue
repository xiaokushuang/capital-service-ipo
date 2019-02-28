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
        <el-table-column prop="id" label="序号"></el-table-column>
        <el-table-column prop="name" label="股东名称"></el-table-column>
        <el-table-column prop="nature" label="股东性质"></el-table-column>
        <el-table-column prop="num" label="持股数量（万股）"></el-table-column>
        <el-table-column prop="proportion" label="持股比例"></el-table-column>
      </el-table>
    </div>
    <!-- 主营业务收入构成 -->
    <div class="incomeComposition">
      <div class="title">
        <span class="littleRectangle"></span>
        <span class="titleText" id="reorganizationIntro">主营业务收入构成</span>
      </div>
      <div class="echart clear">
        <div id="barChart" class="l" :style="{width: '400px', height: '300px'}"></div>
        <div id="pieChart" class="l" :style="{width: '400px', height: '300px'}"></div>
      </div>
      <!-- table表格 -->
      <div class="incomeCompositionTable">
          <el-table
    :data="incomeCompositionTableList"
    style="width: 100%">
    <el-table-column
      prop="date"
      label="日期"
      width="150">
    </el-table-column>
    <el-table-column label="配送信息">
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
      </el-table-column>
      <el-table-column label="地址">
        <el-table-column
          prop="province"
          label="省份"
          width="120">
        </el-table-column>
        <el-table-column
          prop="city"
          label="市区"
          width="120">
        </el-table-column>
        <el-table-column
          prop="address"
          label="地址"
          width="300">
        </el-table-column>
        <el-table-column
          prop="zip"
          label="邮编"
          width="120">
        </el-table-column>
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
              <p>{{data.companyIntroduce}}</p>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { getGqList } from "@/api/companyProfile";

export default {
  name: "companyProfile",
  components: {},

  data() {
    return {
      isLogin: true,
      listLoading: false,
      gqNameList: [],
      gqTableList: [],
      incomeCompositionTableList: [],
      MajorCompetitors: []
    };
  },
  created() {
    this.getData();
  },
  mounted() {
    this.drawBarChart();
    this.drawPieChart();
  },
  methods: {
    //   moke模拟请求的数据
    getData() {
      getGqList("/companyProfile/gqList").then(res => {
        this.gqNameList = res.data.gqjgName;
        this.gqTableList = res.data.gqTable;
        this.incomeCompositionTableList = res.data.incomeCompositionTable;
        this.MajorCompetitors = res.data.MajorCompetitors;
        console.log(res.data);
      });
    },
    // 柱形图
    drawBarChart() {
      let barChart = this.$echarts.init(document.getElementById("barChart"));
      // 绘制图表
      barChart.setOption({
        title: { text: "最近3年主营业务趋势" },
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
          x: "center", // 'center' | 'left' | {number},
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
          data: ["2014年7月", "2015年2月", "2016年3月", "2017年6月"]
        },
        yAxis: {
          type: "value"
        },
        series: [
          {
            name: "宽带移动通信设备",
            type: "bar",
            barWidth: "40%",
            stack: "总量",
            label: {},
            data: [35000, 27000, 25200, 15000]
          },   
          {
            name: "集成业务",
            type: "bar",
            barWidth: "60%",
            stack: "总量",
            label: {},
            data: [1620, 1532, 0, 400]
          },
          {
            name: "技术开发业务",
            type: "bar",
            barWidth: "60%",
            stack: "总量",
            label: {},
            data: [1500, 1800, 1000, 1500]
          },
          {
            name: "工程业务",
            type: "bar",
            barWidth: "60%",
            stack: "总量",
            label: {},
            data: [0, 0, 0, 954]
          }
        ]
      });
    },
    // 饼形图
    drawPieChart() {
      let pieChart = this.$echarts.init(document.getElementById("pieChart"));
      pieChart.setOption({
        title: { text: "2017年1-6月 _ 主营业务分布" },
        tooltip: {
          // trigger: 'item',
          // formatter: "{a} <br/>{b} : {c} ({d}%)"
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
    }
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
</style>