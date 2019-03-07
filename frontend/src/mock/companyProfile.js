import Mock from 'mockjs'
import { param2Obj } from '@/utils'

export default {
  getGqList: () => ({
    "errorCode": null,
    "errorMsg": null,
    "result": {
      "id": "97952444247174457",
      "companyZhName": "安徽扬子地板股份有限公司安",
      "companyName": "扬子地板",
      "companyCode": "A16263",
      "addrProv": "北京",
      "addrCity": "北京市",
      "addrArea": "朝阳区",
      "registeredAssets": 12121212,
      "actualController": "雷响,张俊娥",
      "controlShareholder": "雷响",
      "companyNature": "国有企业",
      "majorBusinesses": "从事强化复合地板",
      "otherMarketInfoList": [
        {
          "id": "1106725448773162051",
          "bid": "97952444247174457",
          "marketType": "全国中小企业股份统",
          "companyCode": "00000122222",
          "companyName": "平安银行111111111111111",
          "listTime": "2019-02-03",
          "delistTime": "2019-03-02"
        },
        {
          "id": "1106725448773162052",
          "bid": "97952444247174457",
          "marketType": "香港联合交易所",
          "companyCode": "000002",
          "companyName": "万科A",
          "listTime": "2019-01-30",
          "delistTime": "2019-03-10"
        },
        {
          "id": "1106725448773162053",
          "bid": "97952444247174457",
          "marketType": "全国中小企业统",
          "companyCode": "",
          "companyName": "",
          "listTime": "2019-03-05",
          "delistTime": "2019-03-28"
        },
        {
          "id": "1106725448773162054",
          "bid": "97952444247174457",
          "marketType": "测试资本市场",
          "companyCode": "",
          "companyName": "",
          "listTime": null,
          "delistTime": null
        },
        {
          "id": "1106725448773162055",
          "bid": "97952444247174457",
          "marketType": "美国纽约证所",
          "companyCode": "",
          "companyName": "",
          "listTime": "2019-03-05",
          "delistTime": "2019-03-24"
        }
      ],
      "structureLabel": "测试1,测试101,测试2",
      "structureUrl": "../../../assets/images/structure.png",
      "shareHolderList": [
        {
          "id": "1106725448773162057",
          "bid": "97952444247174457",
          "personType": null,
          "personName": "小陈1",
          "shareHolderNature": "员工持股平台",
          "shareNum": 1212,
          "shareRatio": 99.02
        },
        {
          "id": "1106725448773162058",
          "bid": "97952444247174457",
          "personType": null,
          "personName": "小王1111",
          "shareHolderNature": "信托计划",
          "shareNum": 12,
          "shareRatio": 0.98
        }
      ],
      "onePeriodForIncome": null,
      "thirdYearForIncome": null,
      "secondYearForIncome": null,
      "firstYearForIncome": null,
      "mainIncomeInfoList": null,
      "mainCompetitorInfoList": [
        {
          "id": "1106725448771726811",
          "bid": "97952444247174457",
          "companyCode": "000002",
          "companyName": "万科111111111111111111111111111111111111111111111111",
          "situation": "测试11"
        },
        {
          "id": "1106725448771726812",
          "bid": "97952444247174457",
          "companyCode": "6000036",
          "companyName": "招商银行1111111111111111111111111111111111111111111111",
          "situation": ""
        },
        {
          "id": "1106725448771726813",
          "bid": "97952444247174457",
          "companyCode": "300104",
          "companyName": "乐视网11111111111111111111111111111111111111111111111",
          "situation": ""
        },
        {
          "id": "1106725448771726814",
          "bid": "97952444247174457",
          "companyCode": "000001000001000001000001000001",
          "companyName": "平安银行1111111111111111111111111111111111111111111111",
          "situation": ""
        }
      ],
      "firstYearForSupplier": "2016-12-31",
      "secondYearForSupplier": "2017-12-31",
      "thirdYearForSupplier": "2018-12-31",
      "onePeriodForSupplier": "2019-03-01",
      "supplierInfoList": [
        {
          "id": "1106725448771726822",
          "bid": "97952444247174457",
          "companyCode": null,
          "companyName": "测试111111111111111111111111111111111111111111111111",
          "firstYearContent": "",
          "firstYearAmount": null,
          "firstYearRatio": null,
          "secondYearContent": "",
          "secondYearAmount": null,
          "secondYearRatio": null,
          "thirdYearContent": "111111111111111111111111111",
          "thirdYearAmount": null,
          "thirdYearRatio": null,
          "onePeriodContent": "测试222",
          "onePeriodAmount": null,
          "onePeriodRatio": null,
          "reportPeriod": "2019-03-01",
          "mainType": "1"
        },
        {
          "id": "1106725448771726823",
          "bid": "97952444247174457",
          "companyCode": null,
          "companyName": "1",
          "firstYearContent": "",
          "firstYearAmount": null,
          "firstYearRatio": null,
          "secondYearContent": "",
          "secondYearAmount": null,
          "secondYearRatio": null,
          "thirdYearContent": "",
          "thirdYearAmount": null,
          "thirdYearRatio": null,
          "onePeriodContent": "1",
          "onePeriodAmount": null,
          "onePeriodRatio": null,
          "reportPeriod": "2019-03-01",
          "mainType": "1"
        }
      ],
      "firstYearForCustomer": "2016-12-31",
      "secondYearForCustomer": "2017-12-31",
      "thirdYearForCustomer": "2018-12-31",
      "onePeriodForCustomer": "2019-02-27",
      "customerInfoList": [
        {
          "id": "1106725448771726824",
          "bid": "97952444247174457",
          "companyCode": null,
          "companyName": "大公司",
          "firstYearContent": "嗯嗯",
          "firstYearAmount": 1000,
          "firstYearRatio": 22,
          "secondYearContent": "好呀",
          "secondYearAmount": 3,
          "secondYearRatio": 3,
          "thirdYearContent": "呀呀",
          "thirdYearAmount": 2,
          "thirdYearRatio": 12,
          "onePeriodContent": "啊啊",
          "onePeriodAmount": 1,
          "onePeriodRatio": 22,
          "reportPeriod": "2019-02-27",
          "mainType": "2"
        }
      ],
      "investItemList": null,
      "intermediaryOrgList": null
    },
    "success": true,

    gqTable:[
        {
          id: '1',
          name: '王小虎',
          nature: '上海市1518 弄',
          num:'3',
          proportion:'40'
          
        },
         {
          id: '2',
          name: '王小虎',
          nature: '上海江路 1518 弄',
          num:'3',
          proportion:'40'
        },
         {
          id: '3',
          name: '王小虎',
          nature: '上海市普路 1518 弄',
          num:'3',
          proportion:'40'
        }, 
        { 
        id: '4',
        name: '王小虎',
        nature: '上海市普陀区金',
        num:'3',
        proportion:'40'
        },
        { 
          id: '5',
          name: '王小虎',
          nature: '上海市普陀18 弄',
          num:'3',
          proportion:'40'
          }
    ],

  MajorCompetitors:[
    {
      companyId:'112233',
      companyName:'温室股份',
      companyIntroduce:'就冯绍峰四季数据的窗库的v库的聚集地鞠诗词鉴赏冯绍峰四季花城假数据的窗库的v库的聚集地鞠诗词鉴赏冯绍峰四季花城假数据的和进程就窗库的v库的聚集地鞠诗词鉴赏冯绍峰四季花城假数据的窗库的v库的聚集地鞠诗词鉴赏冯绍峰四季花城假数据的窗库的v库的聚集地鞠诗词鉴赏冯绍峰四季花城假数据的窗库的v库的聚集地鞠诗词鉴赏冯绍峰四季花城假数据的和进程'
    },
    {
      companyId:'134123',
      companyName:'河南牧原',
      companyIntroduce:'就四季花四季花城假数据的窗库的v库的v库的聚集地鞠诗词鉴赏冯绍峰四季花城v库的聚集地鞠诗词鉴赏冯绍峰四季花城v库的聚集地鞠诗词鉴赏冯绍峰四季花城库的v库峰四季花城假数据的窗库的v库的聚集地鞠诗词鉴赏冯绍峰四季花城假数据的窗库的v库的聚集地鞠诗词鉴赏冯绍峰四季花城假数据的窗库的v库的聚集地鞠诗词鉴赏冯城假数据的和进程'
    },
    {
      companyId:'3322554',
      companyName:'公司简称',
      companyIntroduce:'就窗库据的窗库的v库的聚集地鞠v库的聚集地鞠诗词鉴赏冯绍峰四季花城v库的聚集地鞠诗词鉴赏冯绍峰四季花城v库的聚集地鞠诗词鉴赏冯绍峰四季花城v库的聚集地鞠诗词鉴赏冯绍峰四季花城v库的聚集地鞠诗词鉴赏冯绍峰四季花城诗词鉴赏冯绍峰四季花城假数的',

    }
  ],
  raiseMoneyTableList:[
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
 
  }),

  
}
