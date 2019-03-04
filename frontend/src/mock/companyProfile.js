import Mock from 'mockjs'
import { param2Obj } from '@/utils'

export default {
  getGqList: () => ({
    gqjgName:[
    'VIE架构',
    '国有股东',
    '三类股东',
    '以类股东',
    'VIE架构',
    '国有股东',
    '三类股东',],
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
    incomeCompositionTable1: [
    {
      yewu: '宽带移动通信设备',
      money: '26,232.99',
      roportion: '76.65%',
      years:'2018'
     }, 
     {
      yewu: '集成业务',
      money: '26,232.99',
      roportion: '76.65%',
      years:'2018'
     }, 
     {
      yewu: '技术开发业务',
      money: '26,232.99',
      roportion: '76.65%',
      years:'2018'
     }, 
     {
      yewu: '工程业务',
      money: '26,232.99',
      roportion: '76.65%',
      years:'2018'
     }, 
   ]
  ,
  incomeCompositionTable2: [
    {
      yewu: '宽带移动通信设备',
      money: '66666666',
      roportion: '76.65%',
      years:'2019'
     }, 
     {
      yewu: '集成业务',
      money: '26,232.99',
      roportion: '76.65%',
      years:'2019'
     }, 
     {
      yewu: '技术开发业务',
      money: '26,232.99',
      roportion: '76.65%',
      years:'2019'
     }, 
     {
      yewu: '工程业务',
      money: '26,232.99',
      roportion: '76.65%',
      years:'2019'
     }, 
   ]
  ,
  incomeCompositionTable3: [
    {
      yewu: '宽带移动通信设备',
      money: '7777777777',
      roportion: '76.65%',
      years:'2015'
     }, 
     {
      yewu: '集成业务',
      money: '26,232.99',
      roportion: '76.65%',
      years:'2015'
     }, 
     {
      yewu: '技术开发业务',
      money: '26,232.99',
      roportion: '76.65%',
      years:'2015'
     }, 
     {
      yewu: '工程业务',
      money: '26,232.99',
      roportion: '76.65%',
      years:'2015'
     }, 
   ]
  ,
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
  // 柱状图数据
  zhuxing:{
     // 柱状图横坐标数据
  xAxis:["2014年7月", "2015年2月", "2016年3月", "2017年6月"],
  // 柱状图纵坐标数据
  // yAxis: [
    dataOne:{
      name: "宽带移动通信设备",
      data: [35000, 27000, 25200, 15000]
    },
    dataTwo:{
      name: "集成业务",
      data: [1620, 1532, 0, 400]
    },
    dataThree:{
      name: "技术开发业务",
      data: [1500, 1800, 1000, 1500]
    },
    dataFour:{ 
      name: "工程业务",
      data: [0, 0, 0, 954]
    },
      // yAxis: [

  // ]
  },
  dataOne:[35000, 27000, 25200, 15000],
  dataTwo:[1620, 1532, 0, 400],
  dataThree: [1500, 1800, 1000, 1500],
  dataFour: [0, 0, 0, 954],

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

  }),

  
}
