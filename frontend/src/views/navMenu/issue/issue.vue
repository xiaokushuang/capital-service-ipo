<template>
  <div class="issue">
    <!-- 发行数据 -->
    <div class="issueData">
      <div v-if="dataFlag" class="title" style="margin-top:36px">
        <span class="littleRectangle"></span>
        <span class="titleText" id="distributionData">发行数据</span>
      </div>
      <!-- table1 -->
      <el-row v-if="dataFlag" style="margin-bottom: 30px;padding: 0 12px;">
        <el-col :span="24" :offset="0">
          <el-row :gutter="24" class="simulation_table">
            <el-col :span="6" class="label"><span>每股面值</span></el-col>
            <el-col :span="6" class="value">
              <span v-if="issueData.parValue"> {{issueData.parValue | dataInThRule}} 元/股</span>
              <span v-else> - - </span>
            </el-col>
            <el-col :span="6" class="label"><span>发行价格</span></el-col>
            <el-col :span="6" class="value">
              <span v-if="issueData.issuePrice"> {{issueData.issuePrice | dataInThRule}} 元/股</span>
              <span v-else> - - </span>
            </el-col>
          </el-row>
          <el-row :gutter="24" class="simulation_table">
            <el-col :span="6" class="label"><span>发行数量总计</span></el-col>
            <el-col :span="6" class="value">
              <span v-if="issueData.shareIssued"> {{issueData.shareIssued | dataInThRule}} 万股</span>
              <span v-else> - - </span>
            </el-col>
            <el-col :span="6" class="label"><span>占发行后总股本的比例</span></el-col>
            <el-col :span="6" class="value">
              <span v-if="issueData.issuedRatio"> {{issueData.issuedRatio | dataInThRule}} %</span>
              <span v-else> - - </span>
            </el-col>
          </el-row>
          <el-row :gutter="24" class="simulation_table">
            <el-col :span="6" class="label"><span>募集资金总额</span></el-col>
            <el-col :span="6" class="value">
              <span v-if="issueData.sumFina"> {{issueData.sumFina | dataInThRule}} 万元</span>
              <span v-else> - - </span>
            </el-col>
            <el-col :span="6" class="label"><span>募集资金净额</span></el-col>
            <el-col :span="6" class="value">
              <span v-if="issueData.netSumFina"> {{issueData.netSumFina | dataInThRule}} 万元</span>
              <span v-else> - - </span>
            </el-col>
          </el-row>
          <el-row :gutter="24" class="simulation_table">
            <el-col :span="6" class="label"><span>网上发行数量</span></el-col>
            <el-col :span="6" class="value">
              <span v-if="issueData.shareIssueOn"> {{issueData.shareIssueOn | dataInThRule}} 万股</span>
              <span v-else> - - </span>
            </el-col>
            <el-col :span="6" class="label"><span>网下配售数量</span></el-col>
            <el-col :span="6" class="value">
              <span v-if="issueData.sharePlaceOff"> {{issueData.sharePlaceOff | dataInThRule}} 万股</span>
              <span v-else> - - </span>
            </el-col>
          </el-row>
          <el-row :gutter="24" class="simulation_table">
            <el-col :span="6" class="label"><span>战略配售数量</span></el-col>
            <el-col :span="6" class="value">
              <span v-if="issueData.sharesTrategicPlace"> {{issueData.sharesTrategicPlace | dataInThRule}} 万股</span>
              <span v-else> - - </span>
            </el-col>
            <el-col :span="6" class="label"><span>超额配售数量</span></el-col>
            <el-col :span="6" class="value">
              <span v-if="issueData.shareOverPlace"> {{issueData.shareOverPlace | dataInThRule}} 万股</span>
              <span v-else> - - </span>
            </el-col>
          </el-row>
          <el-row :gutter="24" class="simulation_table">
            <el-col :span="6" class="label"><span>老股东公开发售股份</span></el-col>
            <el-col :span="6" class="value">
              <span v-if="issueData.exseNumBse"> {{issueData.exseNumBse | dataInThRule}} 万股</span>
              <span v-else> - - </span>
            </el-col>
            <el-col :span="6" class="label"><span>发行后市盈率</span></el-col>
            <el-col :span="6" class="value">
              <span v-if="issueData.peIssueA"> {{issueData.peIssueA | dataInThRule}} 倍</span>
              <span v-else> - - </span>
            </el-col>
          </el-row>
          <el-row :gutter="24" class="simulation_table">
            <el-col :span="6" class="label"><span>发行前每股收益</span></el-col>
            <el-col :span="6" class="value">
              <span v-if="issueData.epsIssueB"> {{issueData.epsIssueB | dataInThRule}} 元/股</span>
              <span v-else> - - </span>
            </el-col>
            <el-col :span="6" class="label"><span>发行后每股收益</span></el-col>
            <el-col :span="6" class="value">
              <span v-if="issueData.epsIssueA"> {{issueData.epsIssueA | dataInThRule}} 元/股</span>
              <span v-else> - - </span>
            </el-col>
          </el-row>
          <el-row :gutter="24" class="simulation_table">
            <el-col :span="6" class="label"><span>发行方式</span></el-col>
            <el-col :span="6" class="value">
              <span v-if="issueData.issueMethod" @mouseenter="mouseOverSpreadText(issueData.issueMethod)" class="distribution">
                {{getContent(issueData.issueMethod)}}
              </span>
              <span v-else> - - </span>
            </el-col>
            <el-col :span="6" class="label"><span>承销方式</span></el-col>
            <el-col :span="6" class="value">
              <span>
                {{isNotEmpty(issueData.uwMethod) ? issueData.uwMethod : '- -' + '&nbsp;'}}
              </span>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
    </div>
    <!-- 发行费用 -->
    <div class="issueMoney">
      <div v-if="issueFeeData&&issueFeeData.length>0" class="title" style="margin-bottom:0px">
        <span class="littleRectangle"></span>
        <span class="titleText" id="distributionCosts">发行费用</span>
        <span v-if="estimateType == 1" class="hongkuang">估算</span>
      </div>
      <!-- table2 -->
      <p v-if="issueFeeData&&issueFeeData.length>0" style="font-size:12px;color:#666;float:right">人民币/万元</p>
      <el-table v-if="issueFeeData&&issueFeeData.length>0" :data="issueFeeData" style="width: 100%" stripe border>
        <el-table-column type="index" label="序号" align='center' width="117">
          <template slot-scope="scope">
            {{scope.$index+1}}
          </template>
        </el-table-column>
        <el-table-column style="width:30%" prop="feeType" label="费用明细" align='left'>
        </el-table-column>
        <el-table-column style="width:30%" prop="feeAmount" label="金额" align='right'>
          <template slot-scope="scope">
            <span v-if="scope.row.feeAmount"> {{scope.row.feeAmount | dataInThRule}}</span>
            <span v-else> - - </span>
          </template>
        </el-table-column>
        <el-table-column style="width:30%" prop="feeRatio" label="占募集资金总额比例" align='right'>
          <template slot-scope="scope">
            <span v-if="scope.row.feeRatio"> {{scope.row.feeRatio | dataInThRule}}%</span>
            <span v-else> - - </span>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--  发行后走势及战略配售模块 -->
    <div class="placement" v-show="placementData.subs && placementData.subs.length > 0">
      <div class="title" style="margin-bottom:0px">
        <span class="littleRectangle"></span>
        <span class="titleText" id="issuePlacement">发行后走势及战略配售情况</span>
      </div>
      <p v-if="echartData && echartData.length > 0" style="font-size:16px;color:#333;font-weight:400;margin-top:12px">发行后股价走势</p>
      <!-- 用于实例化EChart图的div  给定id -->
      <div v-show="echartData && echartData.length > 0" id="issueChart" style="height:600px;width:850px;align-items: center;"></div>
      <!-- 交易日股价情况表格 -->
      <el-table v-if="echartData && echartData.length > 0" :data="tableData" style="width: 100%;margin-top: -60px;"
        stripe border>
        <el-table-column type="index" label="序号" align='center' width="107" style="font-weight: normal;">
          <template slot-scope="scope">
            {{scope.$index+1}}
          </template>
        </el-table-column>
        <el-table-column min-width="20%" prop="title" label="" align='left'>
        </el-table-column>
        <el-table-column min-width="20%" prop="trade_date" label="日期" align='right'>
        </el-table-column>
        <el-table-column min-width="25%" prop="NEW" label="收盘价" align='right'>
        </el-table-column>
        <el-table-column min-width="20%" prop="CHG" label="对比发行价涨跌幅" align='right'>
          <template slot-scope="scope">
            <span v-if="scope.row.CHG"> {{scope.row.CHG}}%</span>
            <span v-else> - - </span>
          </template>
        </el-table-column>
      </el-table>
      <!-- 战略配售表格 -->
      <p v-if="placementData.subs && placementData.subs.length > 0 " style="font-size:16px;color:#333;font-weight:500;margin-top:24px">战略配售情况</p>
      <el-table v-if="placementData.subs && placementData.subs.length > 0" :data="placementData.subs" style="width: 100%"
        stripe border>
        <el-table-column type="index" label="序号" align='center' width="57">
          <template slot-scope="scope">
            {{scope.$index+1}}
          </template>
        </el-table-column>
        <el-table-column min-width="21%" prop="strategicInvestorName" label="战略投资者名称" align='left'>
        </el-table-column>
        <el-table-column min-width="20%" prop="shareholderType" label="股东类型" align='center' :render-header="shareholderRenderHeader">
          <template slot-scope="scope">
            <span v-if="scope.row.shareholderType"> {{scope.row.shareholderType}}</span>
            <span v-else> - - </span>
          </template>
        </el-table-column>
        <el-table-column min-width="12.5%" prop="initialNumberTenThousand" label="初始认购股数（万股）" align='right'>
          <template slot-scope="scope">
            <span v-if="scope.row.initialNumberTenThousand"> {{scope.row.initialNumberTenThousand | dataInThRule}}</span>
            <span v-else> - - </span>
          </template>
        </el-table-column>
        <el-table-column min-width="11%" prop="allottedNumberTenThousand" label="获配股数（万股）" align='right'>
          <template slot-scope="scope">
            <span v-if="scope.row.allottedNumberTenThousand"> {{scope.row.allottedNumberTenThousand | dataInThRule}}</span>
            <span v-else> - - </span>
          </template>
        </el-table-column>
        <el-table-column min-width="9.5%" prop="radio" label="占比" align='right' :render-header="radioRenderHeader">
          <template slot-scope="scope">
            <span v-if="scope.row.radio"> {{scope.row.radio | dataInThRule}}%</span>
            <span v-else> - - </span>
          </template>
        </el-table-column>
        <el-table-column min-width="11%" prop="allottedAmountTenThousand" label="获配金额（万元）" align='right'>
          <template slot-scope="scope">
            <span v-if="scope.row.allottedAmountTenThousand"> {{scope.row.allottedAmountTenThousand | dataInThRule}}</span>
            <span v-else> - - </span>
          </template>
        </el-table-column>
        <el-table-column min-width="8.5%" prop="salesRestrictionPeriodMonth" label="限售期" align='left'>
          <template slot-scope="scope">
            <span v-if="scope.row.salesRestrictionPeriodMonth"> {{scope.row.salesRestrictionPeriodMonth}}个月</span>
            <span v-else> - - </span>
          </template>
        </el-table-column>
      </el-table>

    </div>
    <!-- 已经到底了 -->
    <div style="text-align: center;
            font-size: 14px;
            color: rgb(136, 136, 136);
            margin-top: 32px;
            padding-top: 37px;
            border-top: 1px solid #ebeef5">
      已经到底了
    </div>
  </div>
</template>
<script>
  import {
    getIssueData
  } from "@/api/ipoCase/tableDemo";
  import {
    getPlacementData
  } from "@/api/ipoCase/tableDemo";
  import {
    getPriceAfterIssuance
  } from "@/api/ipoCase/tableDemo";
  import {
    getIssueFeeData
  } from "@/api/ipoCase/tableDemo";
  import {
    getEstimateType
  } from "@/api/ipoCase/tableDemo";
  import $ from "jquery";
  export default {
    name: "issue",
    data() {
      return {
        issueChart: null,
        estimateType: '',
        issueFeeData: [],
        placementData: {},
        echartData: [],
        tableData: [],
        issueData: {
          epsIssueA: '',
          epsIssueB: '',
          exseNumBse: '',
          issueMethod: '',
          issuePrice: '',
          issuedRatio: '',
          netSumFina: '',
          parValue: '',
          peIssueA: '',
          shareIssueOn: '',
          shareIssued: '',
          sharePlaceOff: '',
          sumFina: '',
          uwMethod: '',
          sharesTrategicPlace: '',
          shareOverPlace: '',
        },
        dataFlag: false,
        caseId: this.$store.state.app.caseId,
        tipFlag: false,
      }
    },
    created() {
      // 日志--------------------功能头
      let param = {
        client_type: 'pc', //手机或pc
        recordType: 'menu', //跳转页面方式:
        recordModule: 'IPO案例', //跳转模块
        recordTab: "ipo案例详情页", //跳转tab
        recordTabChild: null, //跳转子集tab
        popTitle: null //弹窗title
      }
      // this.$store.commit('CREATE_MESSAGE',param)
      // 日志------------------功能尾
      this.initTableData()
    },
    mounted() {},
    methods: {
      initTableData() {
        // 动态传id
        const param = {
          id: this.caseId
        }
        getEstimateType(param).then(res => {
          if (res.data.result && res.data.result.length > 0) {
            this.estimateType = res.data.result
          }
        })
        getIssueFeeData(param).then(res => {
          if (res.data.result && res.data.result.length > 0) {
            this.issueFeeData = res.data.result
          }
          this.getPosition()
        })
        getIssueData(param).then(res => {
          if (res.data.result) {
            this.dataFlag = true;
            this.issueData = res.data.result;
          }
          this.getPosition();
        })
        // 获取配售战略数据-后台录入
        getPlacementData(param).then(res => {
          if (res.data.result) {
            this.placementData = res.data.result;
          }
          this.getPosition();
        })
        var self = this;
        // 获取发行后股价走势接口 获取echart数据和交易日表格数据
        getPriceAfterIssuance(param).then(res => {
          if (res.data.result) {
            this.echartData = res.data.result.echartResult;
            this.tableData = res.data.result.tableResult;
            self.initEchart();
          }
        })

      },
      // 切分Echart需要的数据格式
      splitData(rawData) {
        var categoryData = [];
        var values = [];
        var volumes = [];
        for (var i = 0; i < rawData.length; i++) {
          categoryData.push(rawData[i].splice(0, 1)[0]);
          values.push(rawData[i]);
          // 判断开盘价和收盘价大小 进行颜色的修改
          volumes.push([i, rawData[i][4], rawData[i][0] > rawData[i][1] ? 1 : -1]);
        }

        return {
          categoryData: categoryData,
          values: values,
          volumes: volumes
        };
      },
      dataFormat(value) {
        if (!value) return value
        if (value === '') return value
        // if(value < 0) return 0;
        let str = parseFloat(value).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,')
        if(str.length > 3){
          str = str.substr(0,str.length-3);
        }
        return str;
      },
      // 初始化Echart
      initEchart() {
        var self = this;
        var upColor = '#00da3c';
        var downColor = '#ec0000';
        this.issueChart = this.$echarts.init(document.getElementById("issueChart"));
        var rawData = this.echartData;
        var data = this.splitData(rawData);
        var option = {
          backgroundColor: '#fff',
          animation: false,
          tooltip: {
            // 通过x轴触发
            trigger: 'axis',
            axisPointer: {
              type: 'cross'
            },
            backgroundColor: 'rgba(245, 245, 245, 0.8)',
            borderWidth: 1,
            borderColor: '#ccc',
            padding: 10,
            textStyle: {
              color: '#000'
            },
            position: function(pos, params, el, elRect, size) {
              var obj = {
                top: 10
              };
              obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 30;
              return obj;
            },
            // 提示框内容自定义
            formatter: function(params, ticket, callback) {
              var htmlStr = '';
              for (var i = 0; i < params.length; i++) {
                var param = params[i];
                var xName = param.name; //x轴数据名称
                var seriesName = param.seriesName; //图例名称
                var value = param.value; //y轴值
                var color = param.color; //图例颜色
                var spanPoint =
                  '<span style="margin-right:5px;display:inline-block;width:10px;height:10px;border-radius:5px;background-color:' +
                  color + ';"></span>';

                htmlStr += '<br>' + spanPoint + xName + '<br/>'; //x轴的名称

                htmlStr += '<div>';
                if (seriesName.indexOf("成交量") == -1) {
                  htmlStr +=
                    '<div>开盘价: ' + param.data[1] + '</div>' +
                    '<div>收盘价: ' + param.data[2] + '</div>' +
                    '<div>最低价: ' + param.data[3] + '</div>' +
                    '<div>最高价: ' + param.data[4] + '</div>';
                } else {
                  htmlStr += '<div>成交量：' + self.dataFormat(param.data[1]) + '</div>'; //x轴的名称

                }
                htmlStr += '</div>';
              }
              return htmlStr;
            },

          },
          axisPointer: {
            // 链接全部表格通过x轴触发
            link: {
              xAxisIndex: 'all'
            },
            label: {
              backgroundColor: '#777'
            }
          },
          toolbox: {
            // 不展示工具栏组件
            show: false,
          },
          brush: {
            xAxisIndex: 'all',
            brushLink: 'all',
            outOfBrush: {
              colorAlpha: 0.1
            }
          },
          visualMap: {
            show: false,
            seriesIndex: 1,
            dimension: 2,
            pieces: [{
              value: 1,
              color: downColor
            }, {
              value: -1,
              color: upColor
            }]
          },
          grid: [{
              left: '10%',
              right: '8%',
              height: '50%'
            },
            {
              left: '10%',
              right: '8%',
              top: '65%',
              height: '8%'
            }
          ],
          xAxis: [{
              type: 'category',
              data: data.categoryData,
              scale: true,
              boundaryGap: false,
              axisLine: {
                onZero: false
              },
              splitLine: {
                show: false
              },
              axisLabel: {
                show: true,
                interval: "auto"
              },
              axisPointer: {
                z: 100
              }

            },
            {
              type: 'category',
              gridIndex: 1,
              data: data.categoryData,
              scale: true,
              boundaryGap: false,
              axisLine: {
                onZero: false
              },
              axisTick: {
                show: false
              },
              splitLine: {
                show: false
              },
              axisLabel: {
                show: true,
                interval: "auto"
              },
            }
          ],
          yAxis: [{
              scale: true,
              splitArea: {
                show: true
              }
            },
            {
              scale: true,
              gridIndex: 1,
              splitNumber: 2,
              axisLabel: {
                show: false
              },
              axisLine: {
                show: false
              },
              axisTick: {
                show: false
              },
              splitLine: {
                show: false
              }
            }
          ],
          // 数据
          series: [{
              name: '发行股价变化',
              type: 'candlestick',
              data: data.values,
              barWidth:'20',
              barMinHeight:"10",
              itemStyle: {
                normal: {
                  color: upColor,
                  color0: downColor,
                  borderColor: null,
                  borderColor0: null
                }
              }
            },
            {
              name: '成交量',
              type: 'bar',
              barWidth:'20',
              barMinHeight:"5",
              xAxisIndex: 1,
              yAxisIndex: 1,
              data: data.volumes
            }
          ]
        };
        this.issueChart.setOption(option);
      },
      // 读取 表格头部数据展示 带问号？通过h(createElement创建)
      shareholderRenderHeader(h, {
        column
      }) {
        var self = this;
        return h('span', [h('span', column.label), h('el-popover', {
            props: {
              placement: 'top',
              width: '700',
              trigger: 'hover'
            }
          }, [
            h('div', [
              h('p', [
                h('span', '根据'), h('a', {
                  style: 'color: #1990FE;',
                  on: {
                    click() {
                      self.openNewRule();
                    }
                  }

                }, '《上海证券交易所科创板股票发行与承销业务指引》')
              ]), h('p', '第八条'), h('p', '参与发行人战略配售的投资者主要包括：'), h('p', '（一）与发行人经营业务具有战略合作关系或长期合作愿景的大型企业或其下属企业；'),
              h('p', '（二）具有长期投资意愿的大型保险公司或其下属企业、国家级大型投资基金或其下属企业；'), h('p',
                '（三）以公开募集方式设立，主要投资策略包括投资战略配售股票，且以封闭方式运作的证券投资基金；'), h('p', '（四）参与跟投的保荐机构相关子公司；'), h('p',
                '（五）发行人的高级管理人员与核心员工参与本次战略配售设立的专项资产管理计划；'), h('p', '（六）符合法律法规、业务规则规定的其他战略投资者。')
            ]), h('i', {
              class: 'el-icon-question',
              style: 'color:#909399;margin-left:5px;cursor:pointer;font-size:12px !important',
              slot: 'reference'
            })
          ])

        ]);
      },
      radioRenderHeader(h, {
        column
      }) {

        return h('span', [h('span', column.label),
          h('el-tooltip', {
            props: {
              effect: 'light',
              content: `占比：获配股数占本次发行后总股本的比例`,
              placement: 'top'
            },
          }, [
            h('i', {
              class: 'el-icon-question',
              style: 'color:#909399;margin-left:5px;cursor:pointer;font-size:12px'
            })
          ], {
            content: '占比：获配股数占本次发行后总股本的比例'
          })
        ]);
      },
      openTips() {
        this.tipFlag = true;
      },
      closeTips() {
        this.tipFlag = false;
      },
      // 打开法规地址
      openNewRule() {
        // 待修改 TODO 返回lawId
        const _self = this;
        const href = window.location.origin + '/ui/laws/laws/lawsDetail?lawId=746412002832382310&access_token=' + _self
          .$store
          .state.app.token + '&tenant_info=' + _self.$store.state.app.info;
        window.open(href, '_blank');
      },
      getPosition() {
        // //返回父组件用于锚点定位头
        let titleList = [];
        let distributionData = {
          id: 'distributionData',
          name: '发行数据',
          notes: '',
          important: false,
          tabId: 'tab-fifth',
          noClick: true
        }
        let distributionCosts = {
          id: 'distributionCosts',
          name: '发行费用',
          notes: '',
          important: false,
          tabId: 'tab-fifth',
          noClick: true
        }
        // 新增菜单返回 返回战略配售模块 用于锚点定位
        let issuePlacement = {
          id: 'issuePlacement',
          name: '发行后走势及战略配售情况',
          notes: '',
          important: false,
          tabId: 'tab-fifth',
          noClick: true
        }
        if (this.dataFlag) {
          distributionData.noClick = false;
        }
        if (this.issueFeeData && this.issueFeeData.length > 0) {
          distributionCosts.noClick = false;
        }
        if (this.placementData.subs && this.placementData.subs.length > 0) {
          issuePlacement.noClick = false;
        }
        titleList.push(distributionData)
        titleList.push(distributionCosts)
        titleList.push(issuePlacement)
        this.$emit('headCallBack', titleList);
        //         //返回父组件用于锚点定位尾
      },
      //
      // 鼠标移入表格内容加title
      mouseOverSpreadText(title) {
        if (title.length > 13) {
          $(".distribution").attr("title", title)
        }
      },
      getContent(title) {
        if (title.length > 13) {
          return title.substring(0, 12) + '...'
        } else {
          return title
        }
      },
      // 非空判断
      isNotEmpty(param) {
        if (param != null && param !== undefined && param !== '' && param !== 'null' && param !== 'undefined') {
          return true
        } else {
          return false
        }
      },
    },
    components: {}
  };
</script>
<style scoped lang="scss">
  .title {
    border-bottom: 1px solid;
    border-bottom-color: #ebebeb;
    height: 42px;
    line-height: 42px;
    background-color: #fafafa;
    display: flex;
    align-items: center;
    margin-top: 30px;
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
  }

  .simulation_table .label {
    font-size: 13px;
    color: #333333;
    text-align: right;
    border: #e1e1e1 1px solid;
    height: 40px;
    line-height: 40px;
    margin: -1px -1px 0 0;
    background-color: #fafafa;
  }

  .simulation_table .value {
    font-size: 13px;
    color: #333333;
    text-align: right;
    border: #e1e1e1 1px solid;
    height: 40px;
    line-height: 40px;
    margin: -1px -1px 0 0;
  }

  .el-row {
    margin-bottom: 0px;
  }

  .hongkuang {
    display: inline-block;
    height: 22px;
    text-align: center;
    border: 1px solid rgba(255, 176, 173, 1);
    font-family: "MicrosoftYaHei", "Microsoft YaHei";
    font-weight: 400;
    font-style: normal;
    font-size: 12px;
    color: #fe5461;
    line-height: 10px;
    margin-left: 10px;
    padding: 4px;
    border-radius: 2px;
  }
</style>
