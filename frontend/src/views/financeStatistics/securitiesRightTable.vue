<template>
    <div rightTable>
        <div>
            <el-table
                :data="tableData"
                style="width: 100%">
                <el-table-column
                    align="center"
                    v-for="item in tableColumn"
                    :key="item.label"
                    :prop="item.prop"
                    :label="item.label">
                </el-table-column>
            </el-table>
        </div>
        
    </div>
  </template>

  <script>
    export default {
        name:'rightTable',
        props:{
            tableData:{
                type:Array,
                default:()=>[{
                    date: '2016-05-02',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-04',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1517 弄'
                }, {
                    date: '2016-05-01',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1519 弄'
                }, {
                    date: '2016-05-03',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1516 弄'
                }]
            },
            tableColumn:{
                type:Array,
                default:()=>[//table头部设置 prop为对应列的字段名，label为表头显示的名字
                    {prop:'date',label:'日期'},
                    {prop:'name',label:'姓名'},
                    {prop:'address',label:'地址'},
                ]
            },
            fuza:{
                type:Boolean,
                default:false
            }
        }
    }
  </script>
  <style>
        
  </style>
  