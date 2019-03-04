import Mock from 'mockjs'
const result = []
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
    result.push(Mock.mock({
      rank: '排名',
      company:'公司',
      year1: '2017年1-6月',
      year2: '2016年1-6月',
      year3: '2015年1-6月',
      year4: '2015年1-6月',
       }))
    for (let i = 0; i < 3; i++) {
      result.push(Mock.mock({
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
        }))
    }
    return { result }
  }
}
