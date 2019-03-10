<template>
    <div>
       <div class="IntermediaryInstitutions">
            <div class="title">
                <span class="littleRectangle"></span>
                <span class="titleText" id="intermediaryInstitutions">中介机构</span>
            </div>
            <!-- 筛选 -->
            <el-select v-model="agentState" placeholder="请选择" style="margin-left:16px;width:120px">
                <el-option v-for="item in options" :label="item.label" :value="item.value">
                </el-option>
            </el-select>
            <div class="InstitutionsDetail">
                <ul>
                    <li class="clear ">
                        <p class="institutionTitle">会计师事务所</p>
                        <div class="InstitutionsDetailLi" v-for="item in accounts">
                            <div class="image l">
                                <img src="@/assets/images/huotonghuo.png" alt>
                            </div>
                            <div class="text l">
                                <div>
                                    <span style="font-family: '微软雅黑 Bold', '微软雅黑 Regular', 微软雅黑;font-weight: 700;
                                    font-style: normal; font-size: 16px; color: rgb(101, 106, 177);">{{item.orgName}}</span>
                                    <span style="background:yellow;color:white;font-size: 12px;">已认证</span>
                                    <span style="color: #6633FF;font-size: 12px;">联系他</span>
                                </div>
                                <ul>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">保荐代表人：</span>
                                        <span style="font-size:12px;color:black">{{item.representPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">2018年过会率：</span>
                                        <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">项目协办人：</span>
                                        <span style="font-size:12px;color:black">{{item.assistPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">2018年业务量：</span>
                                        <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">项目经办人：</span>
                                        <span style="font-size:12px;color:black">{{item.agentPerson}}</span>
                                    </li>
                                
                                
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">2018年市场占比：</span>
                                        <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                                    </li>
                                </ul>
                            </div>                               
                        </div>
                    </li>
                    <li class="clear ">
                        <p class="institutionTitle">资产评估机构</p>
                        <div class="InstitutionsDetailLi" v-for="item in assets">
                            <div class="image l">
                                <img src="@/assets/images/huotonghuo.png" alt>
                            </div>
                            <div class="text l">
                                <div>
                                    <span style="font-family: '微软雅黑 Bold', '微软雅黑 Regular', 微软雅黑;font-weight: 700;
                                    font-style: normal; font-size: 16px; color: rgb(101, 106, 177);">{{item.orgName}}</span>
                                    <span style="background:yellow;color:white;font-size: 12px;">已认证</span>
                                    <span style="color: #6633FF;font-size: 12px;">联系他</span>
                                </div>
                                <ul>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">保荐代表人：</span>
                                        <span style="font-size:12px;color:black">{{item.representPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">2018年过会率：</span>
                                        <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">项目协办人：</span>
                                        <span style="font-size:12px;color:black">{{item.assistPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">2018年业务量：</span>
                                        <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">项目经办人：</span>
                                        <span style="font-size:12px;color:black">{{item.agentPerson}}</span>
                                    </li>
                                
                                
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">2018年市场占比：</span>
                                        <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                                    </li>
                                </ul>
                            </div>                               
                        </div>
                    </li>
                    <li class="clear ">
                        <p class="institutionTitle">律师事务所</p>
                        <div class="InstitutionsDetailLi" v-for="item in lawyers">
                            <div class="image l">
                                <img src="@/assets/images/huotonghuo.png" alt>
                            </div>
                            <div class="text l">
                                <div>
                                    <span style="font-family: '微软雅黑 Bold', '微软雅黑 Regular', 微软雅黑;font-weight: 700;
                                    font-style: normal; font-size: 16px; color: rgb(101, 106, 177);">{{item.orgName}}</span>
                                    <span style="background:yellow;color:white;font-size: 12px;">已认证</span>
                                    <span style="color: #6633FF;font-size: 12px;">联系他</span>
                                </div>
                                <ul>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">保荐代表人：</span>
                                        <span style="font-size:12px;color:black">{{item.representPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">2018年过会率：</span>
                                        <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">项目协办人：</span>
                                        <span style="font-size:12px;color:black">{{item.assistPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">2018年业务量：</span>
                                        <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">项目经办人：</span>
                                        <span style="font-size:12px;color:black">{{item.agentPerson}}</span>
                                    </li>
                                
                                
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">2018年市场占比：</span>
                                        <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                                    </li>
                                </ul>
                            </div>                               
                        </div>
                    </li>
                    <li class="clear ">
                        <p class="institutionTitle">证券公司</p>
                        <div class="InstitutionsDetailLi" v-for="item in securitys">
                            <div class="image l">
                                <img src="@/assets/images/huotonghuo.png" alt>
                            </div>
                            <div class="text l">
                                <div>
                                    <span style="font-family: '微软雅黑 Bold', '微软雅黑 Regular', 微软雅黑;font-weight: 700;
                                    font-style: normal; font-size: 16px; color: rgb(101, 106, 177);">{{item.orgName}}</span>
                                    <span style="background:yellow;color:white;font-size: 12px;">已认证</span>
                                    <span style="color: #6633FF;font-size: 12px;">联系他</span>
                                </div>
                                <ul>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">保荐代表人：</span>
                                        <span style="font-size:12px;color:black">{{item.representPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">2018年过会率：</span>
                                        <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">项目协办人：</span>
                                        <span style="font-size:12px;color:black">{{item.assistPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">2018年业务量：</span>
                                        <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">项目经办人：</span>
                                        <span style="font-size:12px;color:black">{{item.agentPerson}}</span>
                                    </li>
                                
                                
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">2018年市场占比：</span>
                                        <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                                    </li>
                                </ul>
                            </div>                               
                        </div>
                    </li>
                    <li class="clear ">
                        <p class="institutionTitle">联席保荐人（联席主承销商）</p>
                        <div class="InstitutionsDetailLi" v-for="item in sponsors">
                            <div class="image l">
                                <img src="@/assets/images/huotonghuo.png" alt>
                            </div>
                            <div class="text l">
                                <div>
                                    <span style="font-family: '微软雅黑 Bold', '微软雅黑 Regular', 微软雅黑;font-weight: 700;
                                    font-style: normal; font-size: 16px; color: rgb(101, 106, 177);">{{item.orgName}}</span>
                                    <span style="background:yellow;color:white;font-size: 12px;">已认证</span>
                                    <span style="color: #6633FF;font-size: 12px;">联系他</span>
                                </div>
                                <ul>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">保荐代表人：</span>
                                        <span style="font-size:12px;color:black">{{item.representPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">2018年过会率：</span>
                                        <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">项目协办人：</span>
                                        <span style="font-size:12px;color:black">{{item.assistPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">2018年业务量：</span>
                                        <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">项目经办人：</span>
                                        <span style="font-size:12px;color:black">{{item.agentPerson}}</span>
                                    </li>
                                
                                
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 12px; color: #999999;">2018年市场占比：</span>
                                        <span style="font-size:12px;color:black">郑西林、陈光耀</span>
                                    </li>
                                </ul>
                            </div>                               
                        </div>
                    </li>

                </ul>
                <p class="more" style="color: rgb(25, 144, 254);font-size:16px;text-align:center;cursor:pointer">更多中介机构<i class="el-icon-d-arrow-right"></i></p>
            </div>
        </div>
    </div>
</template>
<script>
import { getIntermediaryOrgDataList } from '@/api/companyProfile'
export default {
    name:'IntermediaryInstitutions',
    data(){
        return{
            allStitutionList:[],
            accounts:[],//会计事务所
            accountsValid:[],
            accountsUnValid:[],
            sponsors:[],//保荐机构
            securitys:[],//证券公司
            lawyers:[],//律师事务所
            assets:[],//资产评估机构
            agentState: "当前有效",

            options: 
                [
                    {
                        label: "当前有效",
                        value: 0
                    },
                    {
                        label: "当前失效",
                        value: 1
                    },
                    {
                        label: "全部",
                        value: 2
                    }
              ],
        }
    },
      watch: {
        //数据变化重新赋值
        getAllLabsData(n, o) {
            this.allLabs = n;
        },
        agentState(n, o) {
            switch (n) {
                case 0:
                    this.accounts = this.accountsValid;
                    break;
                case 1:
                    this.accounts = this.accountsUnValid;
                    break;
                case 2:
                    this.accounts = this.accounts;
                    break;
            }
        }

    },
    created() {
     this.initTableData()
    },
  methods: {
    initTableData() {
      getIntermediaryOrgDataList().then(response => {
        this.allStitutionList = response.data.result
        this.accounts = response.data.result.accounts
        this.sponsors = response.data.result.sponsors
        this.securitys = response.data.result.securitys
        this.lawyers = response.data.result.lawyers
        this.assets = response.data.result.assets
        console.log(this.allStitutionList)
        // 筛选有效
         for (var m = 0; m < this.accounts.length; m++) {
            if (this.accounts[m].validFlag == 1) {
                this.accountsValid.push(this.accounts[m]);
            } else {
                this.accountsUnValid.push(this.accounts[m]);
            }
        }
      })
    },
    // 非空判断
    isNotEmpty(param) {
      // debugger
      if (param != null && param !== undefined && param !== '' && param !== 'null' && param !== 'undefined') {
        return true
      } else {
        return false
      }
    }
  },
}
</script>
<style scoped lang="scss">
.l {
  float: left;
}
.r {
  float: right;
}
.clear:after {
  display: block;
  content: "";
  clear: both;
}
.title {
    border-bottom: 1px solid;
    border-bottom-color: #ebebeb;
    height: 42px;
    line-height: 42px;
    background-color: #fafafa;
    display: flex;
    align-items: center;
    margin-top: 40px;
    margin-bottom: 16px;
    .littleRectangle {
      width: 3px;
      height: 18px;
      background-color: #999999;
      display: inline-block;
      margin-right: 12px;
      margin-top: 0px;
      .titleText {
        font-size: 18px;
        color: #333333;
      }
    }
}
// 中介机构
.institutionTitle{
    color: #999999;font-size:14px;padding-left:10px;
}
.InstitutionsDetail {
  ul {
    padding-left: 0;
    width: 100%;
    .InstitutionsDetailLi {
      padding: 20px;
      margin-left: 1%;
      width: 98%;
      height: 130px;
    //   background: #f0f0f0;
      .image {
            width: 15%;
            height: 100%;
      }
      .text {
        width: 80%;
        ul {
          width: 100%;
          margin-top: 10px;
          // background:yellow;
          display: flex;
          flex-wrap: wrap;
          li {
            line-height: 20px;
            margin-right: 30px;
            width: 45%;
            // background:green;
          }
        }
      }
    }
  }
}
.InstitutionsDetailLi:hover {
  cursor: pointer;
  // box-shadow: darkgrey 0px 0px 6px 2px;
  box-shadow: 0 0px 28px -5px #ccc;
}
</style>


