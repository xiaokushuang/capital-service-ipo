import Mock from 'mockjs'
const result = []
const dataList = []
const khDataList = []
const assetsList = []
const compareList = []
const issueMoneyList = []
export default{
  // getTableData: () => {
  //   result.push(Mock.mock({
  //     yewu: '主营业务',
  //     year1: '2017年1-6月',
  //     year2: '2016年',
  //     year3: '2015年',
  //      }))
  //   for (let i = 0; i < 2; i++) {
  //     result.push(Mock.mock({
  //       yewu: '@ctitle(5,8)',
  //       count1: '@float(100,10000,2,2)',
  //       ratio1: /[1-9]{2}\.[0-9]{2}%/,
  //       count2: '@float(100,10000,2,2)',
  //       ratio2: /[1-9]{2}\.[0-9]{2}%/,
  //       count3: '@float(100,10000,2,2)',
  //       ratio3: /[1-9]{2}\.[0-9]{2}%/,
  //       }))
  //   }
  //   return { result }
  // },
  getTableData: () => ({
  "errorCode": null,
  "errorMsg": null,
  "result": {
    "onePeriodForIncome": "2019-03-14",
    "thirdYearForIncome": "2018-12-31",
    "secondYearForIncome": "2017-12-31",
    "firstYearForIncome": "2016-12-31",
    "mainIncomeInfoList": [
      {
        "id": "1106725448773859387",
        "bid": "97952444247174457",
        "businessName": "手机11",
        "firstYearAmount": 1,//1金额
        "firstYearRatio": 4.55,//1占比
        "secondYearAmount": 31,//2金额
        "secondYearRatio": 10,//2占比
        "thirdYearAmount": 21,//3金额
        "thirdYearRatio": 18.9,//3占比
        "onePeriodAmount": 12,
        "onePeriodRatio": 33.33,
        "reportPeriod": "2019-03-14"
      },
      {
        "id": "1106725448773859388",
        "bid": "97952444247174457",
        "businessName": "电脑2",
        "firstYearAmount": 21,
        "firstYearRatio": 95.45,
        "secondYearAmount": 23,
        "secondYearRatio": 10,
        "thirdYearAmount": 11,
        "thirdYearRatio": 20.89,
        "onePeriodAmount": 12,
        "onePeriodRatio": 3.33,
        "reportPeriod": "2019-03-14"
      },
      {
        "id": "1106725448773859389",
        "bid": "97952444247174457",
        "businessName": "耳机",
        "firstYearAmount": 21,
        "firstYearRatio": 5.55,
        "secondYearAmount": 3,
        "secondYearRatio": 10,
        "thirdYearAmount": 21,
        "thirdYearRatio": 8.89,
        "onePeriodAmount": 12,
        "onePeriodRatio": 33.33,
        "reportPeriod": "2019-03-14"
      },
      {
        "id": "1106725448773859390",
        "bid": "97952444247174457",
        "businessName": "吃撒v",
        "firstYearAmount": 11,
        "firstYearRatio": 14.55,
        "secondYearAmount": 4,
        "secondYearRatio": 40,
        "thirdYearAmount": 21,
        "thirdYearRatio": 38.89,
        "onePeriodAmount": 12,
        "onePeriodRatio": 33.33,
        "reportPeriod": "2019-03-14"
      },
    ]
  },
  "success": true
  }),
   getFifthGysTableData: () => {
    dataList.push(Mock.mock({
      // rank: '排名',
      // company:'公司',
      year1: '2017年1-6月',
      year2: '2016年1-6月',
      year3: '2015年1-6月',
      year4: '2015年1-6月',
       }))
    for (let i = 0; i < 3; i++) {
      dataList.push(Mock.mock({
        rank:'@increment',//渐增
        company: '@ctitle(5,8)',
        content1:'@ctitle(5,8)',
        count1: '@float(100,10000,2,2)',
        ratio1: /[1-9]{2}\.[0-9]{2}%/,
        content2:'@ctitle(5,8)',
        count2: '@float(100,10000,2,2)',
        ratio2: /[1-9]{2}\.[0-9]{2}%/,
        content3:'@ctitle(5,8)',
        count3: '@float(100,10000,2,2)',
        ratio3: /[1-9]{2}\.[0-9]{2}%/,
        content4:'@ctitle(5,8)',
        count4: '@float(100,10000,2,2)',
        ratio4: /[1-9]{2}\.[0-9]{2}%/,
        content5:'@ctitle(5,8)',
        count5: '@float(100,10000,2,2)',
        ratio5: /[1-9]{2}\.[0-9]{2}%/,
        content6:'@ctitle(5,8)',
        count6: '@float(100,10000,2,2)',
        ratio6: /[1-9]{2}\.[0-9]{2}%/,
        }))
    }
    return { dataList }
  },
  getFifthKhTableData: () => {
    khDataList.push(Mock.mock({
      // rank: '排名',
      // company:'公司',
      year1: '2017年1-6月',
      year2: '2016年1-6月',
      year3: '2015年1-6月',
      year4: '2015年1-6月',
       }))
    for (let i = 0; i < 3; i++) {
      khDataList.push(Mock.mock({
        rank:'@increment',//渐增
        company: '@ctitle(5,8)',
        content1:'@ctitle(5,8)',
        count1: '@float(100,10000,2,2)',
        ratio1: /[1-9]{2}\.[0-9]{2}%/,
        content2:'@ctitle(5,8)',
        count2: '@float(100,10000,2,2)',
        ratio2: /[1-9]{2}\.[0-9]{2}%/,
        content3:'@ctitle(5,8)',
        count3: '@float(100,10000,2,2)',
        ratio3: /[1-9]{2}\.[0-9]{2}%/,
        content4:'@ctitle(5,8)',
        count4: '@float(100,10000,2,2)',
        ratio4: /[1-9]{2}\.[0-9]{2}%/,
        content5:'@ctitle(5,8)',
        count5: '@float(100,10000,2,2)',
        ratio5: /[1-9]{2}\.[0-9]{2}%/,
        content6:'@ctitle(5,8)',
        count6: '@float(100,10000,2,2)',
        ratio6: /[1-9]{2}\.[0-9]{2}%/,
        }))
    }
    return { khDataList }
  },
  // 财务信息表格接口
  getAssetsTableData: () => {
    assetsList.push(Mock.mock({
      // rank: '排名',
      // company:'公司',
      year1: '2017年1-6月',
      year2: '2016年1-6月',
      year3: '2015年1-6月',
      year4: '2014年1-6月',
       }))
    for (let i = 0; i < 3; i++) {
      assetsList.push(Mock.mock({
        project: '@ctitle(5,8)',
        count1: '@float(100,10000,2,2)',
        count2: '@float(100,10000,2,2)',
        count3: '@float(100,10000,2,2)',
        count4: '@float(100,10000,2,2)',
        count5: '@float(100,10000,2,2)',
       
        }))
    }
    return { assetsList }
  },
  getCompareTableData: () => {
    compareList.push(Mock.mock({
      // rank: '排名',
      // company:'公司',
      year1: '2017年1-6月',
      year2: '2016年1-6月',
      year3: '2015年1-6月',
       }))
    for (let i = 0; i < 3; i++) {
      compareList.push(Mock.mock({
        company: '@ctitle(5,8)',
        proportion1: /[1-9]{2}\.[0-9]{2}%/,
        proportion2: /[1-9]{2}\.[0-9]{2}%/,
        proportion3: /[1-9]{2}\.[0-9]{2}%/,
        }))
    }
    return { compareList }
  },
  getIssueMoneyTableData: () => {
    for (let i = 0; i < 3; i++) {
      issueMoneyList.push(Mock.mock({
        costDetail: '@ctitle(5,8)',
        money: /[1-9]{2}\.[0-9]{2}/,
        proportion: /[1-9]{2}\.[0-9]{2}/,
        }))
    }
    return { issueMoneyList }
  },
  getIssueData:()=>({
      errorCode: null,
      errorMsg: null,
      result: {
        parValue: 0.25,
        issuePrice: 2,
        shareIssued: 150000000,
        issuedRatio: 7.69,
        sumFina: 300000000,
        netSumFina: 254384951,
        exseNumBse: null,
        issueMethod: "网上定价发行,市值申购,网下询价配售",
        epsIssueB: null,
        epsIssueA: 0.21,
        peIssueA: 9.56,
        shareIssueOn: 135000000,
        sharePlaceOff: 15000000,
        uwMethod: "余额包销"
      },
  success: true
  }),
  getIssueFeeData:()=>(
    {
      "errorCode": null,
      "errorMsg": null,
      "result": [
        {
          "id": "1106725448772811863",
          "bid": "97952444247876378",
          "feeType": "承销及保荐费用",
          "feeAmount": 1,
          "feeRatio": 10
        },
        {
          "id": "1106725448772811864",
          "bid": "97952444247876378",
          "feeType": "承销费用",
          "feeAmount": 1,
          "feeRatio": 10
        },
        {
          "id": "1106725448772811865",
          "bid": "97952444247876378",
          "feeType": "保荐费用",
          "feeAmount": 1,
          "feeRatio": 10
        },
        {
          "id": "1106725448772811866",
          "bid": "97952444247876378",
          "feeType": "原股东承销费用",
          "feeAmount": 1,
          "feeRatio": 10
        },
        {
          "id": "1106725448772811867",
          "bid": "97952444247876378",
          "feeType": "律师费用",
          "feeAmount": 1,
          "feeRatio": 10
        },
        {
          "id": "1106725448772811868",
          "bid": "97952444247876378",
          "feeType": "审计费用",
          "feeAmount": 1,
          "feeRatio": 10
        },
        {
          "id": "1106725448772811869",
          "bid": "97952444247876378",
          "feeType": "验资费用",
          "feeAmount": 1,
          "feeRatio": 10
        },
        {
          "id": "1106725448772811870",
          "bid": "97952444247876378",
          "feeType": "评估费用",
          "feeAmount": 1,
          "feeRatio": 10
        },
        {
          "id": "1106725448772811871",
          "bid": "97952444247876378",
          "feeType": "用于本次发行的信息披露费用",
          "feeAmount": 1,
          "feeRatio": 10
        }
      ],
      "success": true
  }),
  // 资产与负债情况财务信息接口 
  getAssetsOrDebtData:()=>(
    {
      "errorCode": null,
      "errorMsg": null,
      "result": {
        // "dateList": [
        //   "2018-06-30",
        //   "2017-12-31",
        //   "2016-12-31",
        //   "2015-12-31"
        // ],
        "dateList":{
          year1: "2015-12-31",
          year2: "2016-12-31",
          year3: "2017-12-31",
          year4: "2018-06-30",
        },
        "ipoDebtItemList": [
          {
            "itemName": "短期借款",
            "firstYearValue": null,
            "secondYearValue": "1301185.0000",
            "thirdYearValue": null,
            "forthYearValue": null
          },
          {
            "itemName": "应付账款",
            "firstYearValue": "10817184.5300",
            "secondYearValue": "19983407.0600",
            "thirdYearValue": null,
            "forthYearValue": "23674387.0900"
          },
          {
            "itemName": "应交税费",
            "firstYearValue": "20783389.0900",
            "secondYearValue": "29851986.7000",
            "thirdYearValue": "36662794.9800",
            "forthYearValue": "20826643.1800"
          },
          {
            "itemName": "应付职工薪酬",
            "firstYearValue": "101792442.3000",
            "secondYearValue": "120922667.4300",
            "thirdYearValue": "121408957.5300",
            "forthYearValue": "122617379.3700"
          }
        ],
        "ipoEquityItemList": [
          {
            "itemName": "实收资本（或股本）",
            "firstYearValue": "157800000.0000",
            "secondYearValue": "157800000.0000",
            "thirdYearValue": "157800000.0000",
            "forthYearValue": "210400000.0000"
          },
          {
            "itemName": "资本公积",
            "firstYearValue": "106801261.7400",
            "secondYearValue": "106801261.7400",
            "thirdYearValue": "106801261.7400",
            "forthYearValue": "292085525.8900"
          },
          {
            "itemName": "未分配利润",
            "firstYearValue": "184506417.0100",
            "secondYearValue": "227608940.2100",
            "thirdYearValue": "282301466.2000",
            "forthYearValue": "313858142.5000"
          },
          {
            "itemName": "归属于母公司股东权益合计",
            "firstYearValue": "490488604.3300",
            "secondYearValue": "538172389.5300",
            "thirdYearValue": "598657724.1100",
            "forthYearValue": "868098664.5600"
          }
        ],
        "ipoFinanceOverList": null,
        "ipoProfitItemList": null,
        "ipoCostItemList": null,
        "ipoReturnOverList": null,
        "ipoAssetItemList": [
          {
            "itemName": "货币资金",
            "firstYearValue": "65950519.4300",
            "secondYearValue": "102600325.3200",
            "thirdYearValue": "173521390.0800",
            "forthYearValue": "328584341.3200"
          },
          {
            "itemName": "应收账款",
            "firstYearValue": "255299815.3100",
            "secondYearValue": "290992223.1100",
            "thirdYearValue": "303240423.1600",
            "forthYearValue": "291891890.0700"
          },
          {
            "itemName": "其他应收款",
            "firstYearValue": "12111262.0300",
            "secondYearValue": "13509106.2600",
            "thirdYearValue": "18866889.0400",
            "forthYearValue": "20449870.6200"
          },
          {
            "itemName": "存货",
            "firstYearValue": null,
            "secondYearValue": null,
            "thirdYearValue": "893826.7200",
            "forthYearValue": "675937.1700"
          },
          {
            "itemName": "固定资产",
            "firstYearValue": "212254627.3100",
            "secondYearValue": "179100289.3000",
            "thirdYearValue": "162870936.7400",
            "forthYearValue": "156286606.9600"
          },
          {
            "itemName": "无形资产",
            "firstYearValue": "6717938.5100",
            "secondYearValue": "8540940.6000",
            "thirdYearValue": "8024967.7400",
            "forthYearValue": "8770045.6800"
          }
        ]
      },
      "success": true,
      "timestamp": 1552630782742
    }
  )
  


}

 