import Mock from 'mockjs'
const result = []
const dataList = []
const khDataList = []
const assetsList = []
const compareList = []
const issueMoneyList = []
export default{
  getTableData: () => {
    result.push(Mock.mock({
      yewu: '主营业务',
      year1: '2017年1-6月',
      year2: '2016年',
      year3: '2015年',
       }))
    for (let i = 0; i < 2; i++) {
      result.push(Mock.mock({
        yewu: '@ctitle(5,8)',
        count1: '@float(100,10000,2,2)',
        ratio1: /[1-9]{2}\.[0-9]{2}%/,
        count2: '@float(100,10000,2,2)',
        ratio2: /[1-9]{2}\.[0-9]{2}%/,
        count3: '@float(100,10000,2,2)',
        ratio3: /[1-9]{2}\.[0-9]{2}%/,
        }))
    }
    return { result }
  },
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

 