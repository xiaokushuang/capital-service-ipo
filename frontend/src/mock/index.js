import Mock from 'mockjs'
import loginAPI from './login'
import articleAPI from './article'
import remoteSearchAPI from './remoteSearch'
import transactionAPI from './transaction'
import companyProfileAPI from './companyProfile'
import rightModuleAPI from './rightModule'
import tableDemo from './tableDemo'
// 登录相关
Mock.mock(/\/login\/login/, 'post', loginAPI.loginByUsername)
Mock.mock(/\/login\/logout/, 'post', loginAPI.logout)
Mock.mock(/\/user\/info\.*/, 'get', loginAPI.getUserInfo)

// 文章相关
Mock.mock(/\/article\/list/, 'get', articleAPI.getArticle)
Mock.mock(/\/article\/detail/, 'get', articleAPI.getArticle)
Mock.mock(/\/article\/pv/, 'get', articleAPI.getPv)
Mock.mock(/\/article\/create/, 'post', articleAPI.createArticle)
Mock.mock(/\/article\/update/, 'post', articleAPI.updateArticle)

// 搜索相关
Mock.mock(/\/search\/user/, 'get', remoteSearchAPI.searchUser)

// 账单相关
Mock.mock(/\/transaction\/list/, 'get', transactionAPI.getList)

// 公司简介相关
Mock.mock(/\/companyProfile\/gqList/, 'get', companyProfileAPI.getGqList)
Mock.mock(/\/companyProfile\/gqTable/, 'get', companyProfileAPI.getGqTable)

// 表格demo
Mock.mock(/\/demo\/tableData/, 'get', tableDemo.getTableData)
Mock.mock(/\/demo\/fifthGysTableData/, 'get', tableDemo.getFifthGysTableData)
Mock.mock(/\/demo\/fifthKhTableData/, 'get', tableDemo.getFifthKhTableData)
Mock.mock(/\/demo\/assetsTableData/, 'get', tableDemo.getAssetsTableData)
Mock.mock(/\/demo\/compareTableData/, 'get', tableDemo.getCompareTableData)
Mock.mock(/\/demo\/issueMoneyTableData/, 'get', tableDemo.getIssueMoneyTableData)
Mock.mock(/\/demo\/resultList/, 'get', tableDemo.getResultList)
// 中介机构
Mock.mock(/\/companyProfile\/intermediaryOrgDataList/, 'get', companyProfileAPI.getIntermediaryOrgDataList)
// 进程树
Mock.mock(/\/rightModule\/rightModuleDataList/, 'get', companyProfileAPI.getRightModuleData)
export default Mock
