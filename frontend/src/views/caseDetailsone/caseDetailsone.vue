<template>
    <div>
        <el-row>
            <el-col :span="18">
                <h3>陕西金叶(000812)发行股份及支付现金购买资产暨关联交易报告书(草案)</h3><br>
                <span> 2018-03-03 用时X个月X天</span><br>
                <el-row>
                    <span class="spanClass">111 </span>
                    <span class="spanClass">111 </span>
                    <span class="spanClass">111 </span>
                    <span class="spanClass">111 </span>
                    <span class="spanClass">111 </span>
                </el-row>
                <el-tabs v-model="activeName2" type="card" @tab-click="handleClick">
                    <el-tab-pane label="上市及标的公司" name="first">
                        <span>创维数字股份有限公司</span><br>
                        <el-button type="primary">昆明瑞丰 </el-button>&nbsp;&nbsp;&nbsp;
                        <el-button type="primary">美菱电器 </el-button>
                        
                        <el-table :data="tableData" border style="width: 100%">
                            <el-table-column prop="date" label="日期" width="180">
                            </el-table-column>
                            <el-table-column prop="name" label="姓名" width="180">
                            </el-table-column>
                            <el-table-column prop="address" label="地址">
                            </el-table-column>
                        </el-table>

                        <h3>置换情况</h3>

                        <el-table :data="tableData" border style="width: 100%">
                            <el-table-column prop="date" label="日期" width="180">
                            </el-table-column>
                            <el-table-column prop="name" label="姓名" width="180">
                            </el-table-column>
                            <el-table-column prop="address" label="地址">
                            </el-table-column>
                        </el-table>

                        <h4>产权控制关系</h4>

                        <div style="min-height: 100px;border: 1px solid;">
                            <p>dadada</p>
                        </div>
                        <h4>交易主体</h4>

                        <el-table :data="tableData" border style="width: 100%">
                            <el-table-column prop="date" label="日期" width="180">
                            </el-table-column>
                            <el-table-column prop="name" label="姓名" width="180">
                            </el-table-column>
                            <el-table-column prop="address" label="地址">
                            </el-table-column>
                        </el-table>

                        <h4>财务数据</h4>

                        <el-table :data="tableData" border style="width: 100%">
                            <el-table-column prop="date" label="日期" width="180">
                            </el-table-column>
                            <el-table-column prop="name" label="姓名" width="180">
                            </el-table-column>
                            <el-table-column prop="address" label="地址">
                            </el-table-column>
                        </el-table>

                    </el-tab-pane>



                    <el-tab-pane label="交易详情" name="second">
                        <h4>交易基本情况</h4>
                        <p>list循环 循环之后每条100字 截取</p>
                        <span></span>
                        <h4>交易结构图</h4>

                        <div style="min-height: 100px;border: 1px solid;">
                            <span>是否构成重大资产重组？</span>
                            <p>此处是一张图片</p>
                        </div>

                        <div style="min-height: 100px;border: 1px solid;">
                            <p>是否借壳上市？</p>
                            <p>此处是一张图片</p>
                        </div>

                        <div style="min-height: 100px;border: 1px solid;">
                            <p>是否构成关联交易？</p>
                            <el-table :data="tableData" border style="width: 100%">
                                <el-table-column prop="date" label="日期" width="180">
                                </el-table-column>
                                <el-table-column prop="name" label="姓名" width="180">
                                </el-table-column>
                                <el-table-column prop="address" label="地址">
                                </el-table-column>
                            </el-table>
                            <span>备注：XXXXXXXXXXXXXXXXXXX</span>
                        </div>
                    </el-tab-pane>



                    <el-tab-pane label="发行方案" name="third">
                        <h3>置换情况</h3>
                        <span>1111 </span>
                        <span>1111 </span><br>
                        <span>发行股数：22222229（万股）   基准日：董事会决议公告日</span>
                        <el-tabs v-model="activeName" @tab-click="handleClick">
                            <el-tab-pane label="用户管理" name="first">
                                <el-table :data="tableData" border style="width: 100%">
                                    <el-table-column prop="date" label="日期" width="180">
                                    </el-table-column>
                                    <el-table-column prop="name" label="姓名" width="180">
                                    </el-table-column>
                                    <el-table-column prop="address" label="地址">
                                    </el-table-column>
                                </el-table>
                            </el-tab-pane>

                            <el-tab-pane label="配置管理" name="second">
                                <div style="min-height: 100px;border: 1px solid;">
                                    <p>此处是一张图片</p>
                                </div>
                            </el-tab-pane>


                            <el-tab-pane label="角色管理" name="third">
                                <el-table :data="tableData" border style="width: 100%">
                                    <el-table-column prop="date" label="日期" width="180">
                                    </el-table-column>
                                    <el-table-column prop="name" label="姓名" width="180">
                                    </el-table-column>
                                    <el-table-column prop="address" label="地址">
                                    </el-table-column>
                                </el-table>
                            </el-tab-pane>

                        </el-tabs>
                        <h3>置换情况</h3>
                        <span>1111 </span>
                        <span>1111 </span><br>
                        <span>发行股数：22222229（万股）   基准日：董事会决议公告日</span>
                        <el-tabs v-model="activeName1" @tab-click="handleClick">
                            <el-tab-pane label="用户管理" name="first">
                                <div style="min-height: 100px;border: 1px solid;">

                                    <p>此处是一张图片</p>
                                </div>
                            </el-tab-pane>

                            <el-tab-pane label="配置管理" name="second">
                                <div style="min-height: 100px;border: 1px solid;">
                                    <p>此处是一张图片</p>
                                </div>
                            </el-tab-pane>

                            <el-tab-pane label="角色管理" name="third">
                                <el-table :data="tableData" border style="width: 100%">
                                    <el-table-column prop="date" label="日期" width="180">
                                    </el-table-column>
                                    <el-table-column prop="name" label="姓名" width="180">
                                    </el-table-column>
                                    <el-table-column prop="address" label="地址">
                                    </el-table-column>
                                </el-table>
                            </el-tab-pane>

                        </el-tabs>
                    </el-tab-pane>
                    <el-tab-pane label="中介机构" name="fourth">
                        <h3>上市公司</h3>
                        <el-button>1</el-button>
                        <el-button>2</el-button>
                        <el-button>3</el-button>
                        <el-button>4</el-button>
                        <el-table :data="tableData" style="width: 100%">
                            <el-table-column prop="date" label="" width="180">
                            </el-table-column>
                            <el-table-column prop="name" label="" width="180">
                            </el-table-column>
                            <el-table-column prop="address" label="">
                            </el-table-column>
                        </el-table>
                        <h3>上市公司</h3>
                        <el-button>5</el-button>
                        <el-button>6</el-button>
                        <el-button>7</el-button>
                        <el-button>8</el-button>

                        <el-table :data="tableData" style="width: 100%">
                            <el-table-column prop="date" label="" width="180">
                            </el-table-column>
                            <el-table-column prop="name" label="" width="180">
                            </el-table-column>
                            <el-table-column prop="address" label="">
                            </el-table-column>
                        </el-table>
                    </el-tab-pane>
                </el-tabs>
            </el-col>
            <el-col :span="6">
                <p>
                    {{a}} {{b}}
                </p>
            </el-col>
        </el-row>
    </div>
</template>
<script>
export default {
    name: "demo1",
    data() {
        return {
            activeName:'first',
            activeName1:'first',
            activeName2: "first",
            a: "aaa",
            b: "aaa",

            tableData: [
                {
                    date: "2016-05-02",
                    name: "王小虎",
                    address: "上海市普陀区金沙江路 1518 弄"
                },
                {
                    date: "2016-05-04",
                    name: "王小虎",
                    address: "上海市普陀区金沙江路 1517 弄"
                },
                {
                    date: "2016-05-01",
                    name: "王小虎",
                    address: "上海市普陀区金沙江路 1519 弄"
                },
                {
                    date: "2016-05-03",
                    name: "王小虎",
                    address: "上海市普陀区金沙江路 1516 弄"
                }
            ]
        };
    },
    methods: {
        handleClick(tab, event) {
            console.log(tab, event);
        }
    },
    computed: {}
};
</script>
<style>
.spanClass {
    list-style-type: none;
    background: #ff9900;
    color: #fff;
    font-size: 14px;
    padding: 4px;
    float: left;
    margin: 0 5px;
    cursor: pointer;
}
</style>

