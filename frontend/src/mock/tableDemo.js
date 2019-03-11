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
        "thirdYearRatio": 38.89,//3占比
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
        "thirdYearRatio": 30.89,
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
        "thirdYearRatio": 38.89,
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


}

 