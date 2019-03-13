import Mock from 'mockjs'

export default{
    getRightModuleData: () => ({
       "errorCode": null,
        "errorMsg": null,
        "result": {
          "treeList": [
            {
              "treeTypeCode": "01",//进程类型编码
              "proList": [//进程列表
                {
                  "sort":"1",
                  "progressType": "07",//进程编码
                  "progressName": "发审会审核",//进程名称
                  "processTime": "2012-03-16",//进程时间
                  "iecResult": "02",//审核结果
                  "lastDay": 12,//距离上次进程时间
                  "relaList": [//文件列表
                    {
                      "id": "1106725448775241697",//id
                      "relaId": null,//关联文件ID
                      "relationFileTitle": null,//文件名
                      "publishTime": "2012-03-16",//公告发布时间
                      "tableSort": 1,//文件排序
                      "suffix": null//文件后缀
                    }
                  ]
                },
                {
                  "sort":"2",
                  "progressType": "07",
                  "progressName": "发审会审核",
                  "processTime": "2012-04-18",
                  "iecResult": "01",
                  "lastDay": "33",
                  "relaList": [
                    {
                      "id": "1106725448775241696",
                      "relaId": null,
                      "relationFileTitle": null,
                      "publishTime": "2012-04-18",
                      "tableSort": 1,
                      "suffix": null
                    }
                  ]
                },
                {
                  "sort":"3",
                  "progressType": "04",
                  "progressName": "预先披露",
                  "processTime": "2015-06-11",
                  "iecResult": 12,
                  "lastDay": "1149",
                  "relaList": [
                    {
                      "id": "1106725448775241692",
                      "relaId": null,
                      "relationFileTitle": null,
                      "publishTime": "2015-06-11",
                      "tableSort": 1,
                      "suffix": null
                    }
                  ]
                },
                {
                  "sort":"4",
                  "progressType": "05",
                  "progressName": "反馈意见",
                  "processTime": "2015-11-12",
                  "iecResult": null,
                  "lastDay": "154",
                  "relaList": [
                    {
                      "id": "1106725448775241693",
                      "relaId": null,
                      "relationFileTitle": null,
                      "publishTime": "2015-11-12",
                      "tableSort": 1,
                      "suffix": null
                    }
                  ]
                },
                {
                  "sort":"5",
                  "progressType": "09",
                  "progressName": "中止审查",
                  "processTime": "2017-11-02",
                  "iecResult": null,
                  "lastDay": "721",
                  "relaList": [
                    {
                      "id": "1106725448775241695",
                      "relaId": null,
                      "relationFileTitle": null,
                      "publishTime": "2017-11-02",
                      "tableSort": 1,
                      "suffix": null
                    }
                  ]
                },
                {
                  "sort":"6",
                  "progressType": "06",
                  "progressName": "预披露更新",
                  "processTime": "2017-12-28",
                  "iecResult": null,
                  "lastDay": "56",
                  "relaList": [
                    {
                      "id": "1106725448775241694",
                      "relaId": null,
                      "relationFileTitle": null,
                      "publishTime": "2017-12-28",
                      "tableSort": 1,
                      "suffix": null
                    }
                  ]
                },
                {
                  "sort":"7",
                  "progressType": "10",
                  "progressName": "核准批复",
                  "processTime": "2018-05-04",
                  "iecResult": null,
                  "lastDay": "127",
                  "relaList": [
                    {
                      "id": "1106725448775241699",
                      "relaId": null,
                      "relationFileTitle": null,
                      "publishTime": "2018-05-04",
                      "tableSort": 1,
                      "suffix": null
                    }
                  ]
                },
                {
                  "sort":"8",
                  "progressType": "07",
                  "progressName": "发审会审核",
                  "processTime": "2018-05-08",
                  "iecResult": "00",
                  "lastDay": "4",
                  "relaList": [
                    {
                      "id": "1106725448775241698",
                      "relaId": null,
                      "relationFileTitle": null,
                      "publishTime": "2018-05-08",
                      "tableSort": 1,
                      "suffix": null
                    }
                  ]
                }
              ]
            },
            {
              "treeTypeCode": "02",
              "proList": [
                {
                  "progressType": "11",
                  "progressName": "招股公告",
                  "processTime": "2018-05-08",
                  "iecResult": null,
                  "lastDay": "0",
                  "relaList": [
                    {
                      "id": "1106725448775241684",
                      "relaId": null,
                      "relationFileTitle": null,
                      "publishTime": "2018-05-08",
                      "tableSort": 1,
                      "suffix": null
                    }
                  ]
                },
                {
                  "progressType": "12",
                  "progressName": "询价",
                  "processTime": "2018-05-08",
                  "iecResult": null,
                  "lastDay": "0",
                  "relaList": [
                    {
                      "id": "1106725448775241685",
                      "relaId": null,
                      "relationFileTitle": null,
                      "publishTime": "2018-05-08",
                      "tableSort": 1,
                      "suffix": null
                    }
                  ]
                },
                {
                  "progressType": "13",
                  "progressName": "路演",
                  "processTime": "2018-05-15",
                  "iecResult": null,
                  "lastDay": "7",
                  "relaList": [
                    {
                      "id": "1106725448775241686",
                      "relaId": null,
                      "relationFileTitle": null,
                      "publishTime": "2018-05-15",
                      "tableSort": 1,
                      "suffix": null
                    }
                  ]
                },
                {
                  "progressType": "14",
                  "progressName": "发行公告",
                  "processTime": "2018-05-15",
                  "iecResult": null,
                  "lastDay": "0",
                  "relaList": [
                    {
                      "id": "1106725448775241687",
                      "relaId": null,
                      "relationFileTitle": null,
                      "publishTime": "2018-05-15",
                      "tableSort": 1,
                      "suffix": null
                    }
                  ]
                },
                {
                  "progressType": "16",
                  "progressName": "申购",
                  "processTime": "2018-05-16",
                  "iecResult": null,
                  "lastDay": "1",
                  "relaList": [
                    {
                      "id": "1106725448775241688",
                      "relaId": null,
                      "relationFileTitle": null,
                      "publishTime": "2018-05-16",
                      "tableSort": 1,
                      "suffix": null
                    }
                  ]
                },
                {
                  "progressType": "17",
                  "progressName": "公布中签率",
                  "processTime": "2018-05-17",
                  "iecResult": null,
                  "lastDay": "1",
                  "relaList": [
                    {
                      "id": "1106725448775241689",
                      "relaId": null,
                      "relationFileTitle": null,
                      "publishTime": "2018-05-17",
                      "tableSort": 1,
                      "suffix": null
                    }
                  ]
                },
                {
                  "progressType": "18",
                  "progressName": "公布中签号码",
                  "processTime": "2018-05-18",
                  "iecResult": null,
                  "lastDay": "1",
                  "relaList": [
                    {
                      "id": "1106725448775241690",
                      "relaId": null,
                      "relationFileTitle": null,
                      "publishTime": "2018-05-18",
                      "tableSort": 1,
                      "suffix": null
                    }
                  ]
                },
                {
                  "progressType": "19",
                  "progressName": "上市",
                  "processTime": "2018-05-24",
                  "iecResult": null,
                  "lastDay": "6",
                  "relaList": [
                    {
                      "id": "1106725448775241691",
                      "relaId": null,
                      "relationFileTitle": null,
                      "publishTime": "2018-05-24",
                      "tableSort": 1,
                      "suffix": null
                    }
                  ]
                }
              ]
            }
          ],
          "caseId": "97952444248450232"
        },
        "success": true
  })

}

 