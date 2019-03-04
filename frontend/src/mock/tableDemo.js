import Mock from 'mockjs'
const result = []
export default{
  getTableData: () => {
    result.push(Mock.mock({
      item: '主营业务',
      year1: '2017年1-6月',
      year2: '2016年',
      year3: '2015年',
      year4: '2014年' }))
    for (let i = 0; i < 3; i++) {
      result.push(Mock.mock({
        item: '@ctitle(5,8)',
        count1: '@float(100,10000,2,2)',
        ratio1: /[1-9]{2}\.[0-9]{2}%/,
        count2: '@float(100,10000,2,2)',
        ratio2: /[1-9]{2}\.[0-9]{2}%/,
        count3: '@float(100,10000,2,2)',
        ratio3: /[1-9]{2}\.[0-9]{2}%/,
        count4: '@float(100,10000,2,2)',
        ratio4: /[1-9]{2}\.[0-9]{2}%/ }))
    }
    return { result }
  }
}
