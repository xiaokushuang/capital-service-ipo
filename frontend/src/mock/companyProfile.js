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
    incomeCompositionTable: [
    {
      yewu: '宽带移动通信设备',
      money: '26,232.99',
      roportion: '76.65%',
     }, 
     {
      yewu: '集成业务',
      money: '26,232.99',
      roportion: '76.65%',
     }, 
     {
      yewu: '技术开发业务',
      money: '26,232.99',
      roportion: '76.65%',
     }, 
     {
      yewu: '工程业务',
      money: '26,232.99',
      roportion: '76.65%',
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
      companyIntroduce:'就四季花四季花城假数据的窗库的v库的库的v库峰四季花城假数据的窗库的v库的聚集地鞠诗词鉴赏冯绍峰四季花城假数据的窗库的v库的聚集地鞠诗词鉴赏冯绍峰四季花城假数据的窗库的v库的聚集地鞠诗词鉴赏冯城假数据的和进程'
    },
    {
      companyId:'3322554',
      companyName:'公司简称',
      companyIntroduce:'就窗库据的窗库的v库的聚集地鞠诗词鉴赏冯绍峰四季花城假数的'
    }
  ]
  }),
}
