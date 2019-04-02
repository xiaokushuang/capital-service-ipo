<template>
    <div>
       <div class="IntermediaryInstitutions">
            <!-- 筛选 -->
            <el-select v-model="agentState" placeholder="请选择" style="margin-left:16px;width:120px">
                <el-option v-for="item in options" :label="item.label" :value="item.value" :key="item.value">
                </el-option>
            </el-select>
            <div class="InstitutionsDetail">
                <ul>
                     <li class="clear ">
                        <p v-show="sponsors&&sponsors.length>0" class="institutionTitle">联席保荐人（联席主承销商）</p>
                        <!-- <div class="InstitutionsDetailLi clear" v-for="item in sponsors"> -->
                            <div :class="item.validFlag == 0 ?'failureBackground InstitutionsDetailLi clear':'InstitutionsDetailLi clear'" v-for="item in sponsors">
                            <div class="image l">
                                <img src="../../../../assets/images/coSponsors.png" alt>
                            </div>
                            <div class="text l">
                                <div>
                                    <span style="font-family: '微软雅黑 Bold', '微软雅黑 Regular', 微软雅黑;font-weight: 700;
                                    font-style: normal; font-size: 16px; color: #363636">{{item.orgName}}</span>
                                     <!-- 已失效标志 -->
                                    <span class="failure" v-show="item.validFlag == 0" style="float:right;    border-width: 0px;
                                        width: 56px;
                                        height: 15px;
                                        padding-left: 10px;
                                        font-size:14px;
                                        position:relative;
                                        left: 60px;
                                        top: -9px;
                                        background: inherit;
                                        background-color: rgba(188, 188, 188, 0.517647058823529);
                                        border: none;
                                        border-right: 0px;
                                        border-radius: 20px;
                                        border-top-right-radius: 0px;
                                        border-bottom-right-radius: 0px;
                                        -moz-box-shadow: none;
                                        -webkit-box-shadow: none;
                                        box-shadow: none;
                                        color: #FFFFFF;">
                                        已失效
                                    </span>
                                </div>
                                <ul>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">保荐代表人：</span>
                                        <span style="font-size:14px;color:black">{{item.representPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">2018年过会率：</span>
                                        <span style="font-size:14px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">项目协办人：</span>
                                        <span style="font-size:14px;color:black">{{item.assistPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">2018年业务量：</span>
                                        <span style="font-size:14px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">项目经办人：</span>
                                        <span style="font-size:14px;color:black">{{item.agentPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">2018年市场占比：</span>
                                        <span style="font-size:14px;color:black">郑西林、陈光耀</span>
                                    </li>
                                </ul>
                            </div>                               
                        </div>`
                    </li>
                     <li class="clear ">
                        <p v-show="lawyers&&lawyers.length>0" class="institutionTitle">律师事务所</p>
                        <!-- <div class="InstitutionsDetailLi clear" v-for="item in lawyers"> -->
                            <div :class="item.validFlag == 0 ?'failureBackground InstitutionsDetailLi clear':'InstitutionsDetailLi clear'"  v-for="item in lawyers">
                            <div class="image l">
                                <img src="../../../../assets/images/lvshi.png" alt>
                            </div>
                            <div class="text l">
                                <div>
                                    <span style="font-family: '微软雅黑 Bold', '微软雅黑 Regular', 微软雅黑;font-weight: 700;
                                    font-style: normal; font-size: 16px; color:#363636;">{{item.orgName}}</span>
                                     <!-- 已失效标志 -->
                                    <span class="failure" v-show="item.validFlag == 0" style="float:right;    border-width: 0px;
                                        width: 56px;
                                        height: 15px;
                                        padding-left: 10px;
                                        font-size:14px;
                                        position:relative;
                                        left: 60px;
                                        top: -9px;
                                        background: inherit;
                                        background-color: rgba(188, 188, 188, 0.517647058823529);
                                        border: none;
                                        border-right: 0px;
                                        border-radius: 20px;
                                        border-top-right-radius: 0px;
                                        border-bottom-right-radius: 0px;
                                        -moz-box-shadow: none;
                                        -webkit-box-shadow: none;
                                        box-shadow: none;
                                        color: #FFFFFF;">
                                        已失效
                                    </span>
                                </div>
                                <ul>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">保荐代表人：</span>
                                        <span style="font-size:14px;color:black">{{item.representPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">2018年过会率：</span>
                                        <span style="font-size:14px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">项目协办人：</span>
                                        <span style="font-size:14px;color:black">{{item.assistPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">2018年业务量：</span>
                                        <span style="font-size:14px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">项目经办人：</span>
                                        <span style="font-size:14px;color:black">{{item.agentPerson}}</span>
                                    </li>
                                
                                
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">2018年市场占比：</span>
                                        <span style="font-size:14px;color:black">郑西林、陈光耀</span>
                                    </li>
                                </ul>
                            </div>                               
                        </div>
                    </li>
                    <li class="clear ">
                        <p v-show="accounts&&accounts.length>0" class="institutionTitle">会计师事务所</p>
                        <!-- <div class="InstitutionsDetailLi clear" v-for="item in accounts"> -->
                            <div :class="item.validFlag == 0 ?'failureBackground InstitutionsDetailLi clear':'InstitutionsDetailLi clear'" v-for="item in accounts">
                            <div class="image l">
                                <img src="../../../../assets/images/kuaiji.png" alt>
                            </div>
                            <div class="text l">
                                <div>
                                    <span style="font-family: '微软雅黑 Bold', '微软雅黑 Regular', 微软雅黑;font-weight: 700;
                                    font-style: normal; font-size: 16px; color: #363636">{{item.orgName}}</span>
                                     <!-- 已失效标志 -->
                                    <span class="failure" v-show="item.validFlag == 0" style="float:right;    border-width: 0px;
                                        width: 56px;
                                        height: 15px;
                                        padding-left: 10px;
                                        font-size:14px;
                                        position:relative;
                                        left: 60px;
                                        top: -9px;
                                        background: inherit;
                                        background-color: rgba(188, 188, 188, 0.517647058823529);
                                        border: none;
                                        border-right: 0px;
                                        border-radius: 20px;
                                        border-top-right-radius: 0px;
                                        border-bottom-right-radius: 0px;
                                        -moz-box-shadow: none;
                                        -webkit-box-shadow: none;
                                        box-shadow: none;
                                        color: #FFFFFF;">
                                        已失效
                                    </span>
                                </div>
                                <ul>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">保荐代表人：</span>
                                        <span style="font-size:14px;color:black">{{item.representPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">2018年过会率：</span>
                                        <span style="font-size:14px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">项目协办人：</span>
                                        <span style="font-size:14px;color:black">{{item.assistPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">2018年业务量：</span>
                                        <span style="font-size:14px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">项目经办人：</span>
                                        <span style="font-size:14px;color:black">{{item.agentPerson}}</span>
                                    </li>
                                
                                
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">2018年市场占比：</span>
                                        <span style="font-size:14px;color:black">郑西林、陈光耀</span>
                                    </li>
                                </ul>
                            </div>                               
                        </div>
                    </li>
                    <li class="clear " v-show="showMoreType">
                        <p v-show="assets&&assets.length>0" class="institutionTitle">资产评估机构</p>
                        <!-- <div class="InstitutionsDetailLi clear" v-for="item in assets"> -->
                            <div :class="item.validFlag == 0 ?'failureBackground InstitutionsDetailLi clear':'InstitutionsDetailLi clear'" v-for="item in assets">
                            <div class="image l">
                                <img src="../../../../assets/images/assets.png" alt>
                            </div>
                            <div class="text l">
                                <div>
                                    <span style="font-family: '微软雅黑 Bold', '微软雅黑 Regular', 微软雅黑;font-weight: 700;
                                    font-style: normal; font-size: 16px; color: #363636;">{{item.orgName}}</span>
                                     <!-- 已失效标志 -->
                                    <span class="failure" v-show="item.validFlag == 0" style="float:right;    border-width: 0px;
                                        width: 56px;
                                        height: 15px;
                                        padding-left: 10px;
                                        font-size:14px;
                                        position:relative;
                                        left: 60px;
                                        top: -9px;
                                        background: inherit;
                                        background-color: rgba(188, 188, 188, 0.517647058823529);
                                        border: none;
                                        border-right: 0px;
                                        border-radius: 20px;
                                        border-top-right-radius: 0px;
                                        border-bottom-right-radius: 0px;
                                        -moz-box-shadow: none;
                                        -webkit-box-shadow: none;
                                        box-shadow: none;
                                        color: #FFFFFF;">
                                        已失效
                                    </span>
                                    <!-- <span style="background:yellow;color:white;font-size: 12px;">已认证</span>
                                    <span style="color: #6633FF;font-size: 12px;">联系他</span> -->
                                </div>
                                <ul>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">保荐代表人：</span>
                                        <span style="font-size:14px;color:black">{{item.representPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">2018年过会率：</span>
                                        <span style="font-size:14px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">项目协办人：</span>
                                        <span style="font-size:14px;color:black">{{item.assistPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">2018年业务量：</span>
                                        <span style="font-size:14px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">项目经办人：</span>
                                        <span style="font-size:14px;color:black">{{item.agentPerson}}</span>
                                    </li>
                                
                                
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">2018年市场占比：</span>
                                        <span style="font-size:14px;color:black">郑西林、陈光耀</span>
                                    </li>
                                </ul>
                            </div>                               
                        </div>
                    </li>
                    <li class="clear "  v-show="showMoreType">
                        <p v-show="securitys&&securitys.length>0" class="institutionTitle">证券公司</p>
                        <div :class="item.validFlag == 0 ?'failureBackground InstitutionsDetailLi clear':'InstitutionsDetailLi clear'" v-for="item in securitys">
                            <div class="image l">
                                <img src="../../../../assets/images/coSponsors.png" alt>
                            </div>
                            <div class="text l">
                                <div>
                                    <span style="font-family: '微软雅黑 Bold', '微软雅黑 Regular', 微软雅黑;font-weight: 700;
                                    font-style: normal; font-size: 16px; color: #363636;">{{item.orgName}}</span>
                                     <!-- 已失效标志 -->
                                    <span class="failure" v-show="item.validFlag == 0" style="float:right;    border-width: 0px;
                                        width: 56px;
                                        height: 15px;
                                        padding-left: 10px;
                                        font-size:14px;
                                        position:relative;
                                        left: 60px;
                                        top: -9px;
                                        background: inherit;
                                        background-color: rgba(188, 188, 188, 0.517647058823529);
                                        border: none;
                                        border-right: 0px;
                                        border-radius: 20px;
                                        border-top-right-radius: 0px;
                                        border-bottom-right-radius: 0px;
                                        -moz-box-shadow: none;
                                        -webkit-box-shadow: none;
                                        box-shadow: none;
                                        color: #FFFFFF;">
                                        已失效
                                    </span>
                                </div>
                                <ul>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">保荐代表人：</span>
                                        <span style="font-size:14px;color:black">{{item.representPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">2018年过会率：</span>
                                        <span style="font-size:14px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">项目协办人：</span>
                                        <span style="font-size:14px;color:black">{{item.assistPerson}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">2018年业务量：</span>
                                        <span style="font-size:14px;color:black">郑西林、陈光耀</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">项目经办人：</span>
                                        <span style="font-size:14px;color:black">{{item.agentPerson}}</span>
                                    </li>
                                
                                
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">2018年市场占比：</span>
                                        <span style="font-size:14px;color:black">郑西林、陈光耀</span>
                                    </li>
                                </ul>
                               
                            </div>                               
                        </div>
                    </li>
                </ul>
                 <div style=" text-align: center;">
                    <span v-show="(securitys&&securitys.length>0) || (assets&&assets.length>0)" style="color: rgb(25, 144, 254);font-size:14px;text-align:center;cursor:pointer" class="packUp" v-if="showMoreType" @click="packUpMoreType()">收起 <i class="el-icon-arrow-up"></i></span>
                    <span v-show="(securitys&&securitys.length>0) || (assets&&assets.length>0)" style="color: rgb(25, 144, 254);font-size:14px;text-align:center;cursor:pointer" class="spread" v-else @click="spreadMoreType()">更多中介机构 <i class="el-icon-arrow-down"></i></span>
                 </div>
            </div>
        </div>
    </div>
</template>
<script>
import { getIntermediaryOrgDataList } from '@/api/ipoCase/companyProfile'
export default {
    name:'IntermediaryInstitutions',
    data(){
        return{
            caseId:this.$store.state.app.caseId,
            allStitutionList:[],
            accounts:[],//会计事务所
            accountsTotal:[],
            accountsValid:[],
            accountsUnValid:[],
            sponsors:[],//保荐机构
            sponsorsTotal:[],
            sponsorsValid:[],
            sponsorsUnValid:[],
            securitys:[],//证券公司
            securitysTotal:[],
            securitysValid:[],
            securitysUnValid:[],
            lawyers:[],//律师事务所
            lawyersTotal:[],
            lawyersValid:[],
            lawyersUnValid:[],
            assets:[],//资产评估机构
            assetsTotal:[],
            assetsValid:[],
            assetsUnValid:[],
            agentState: "当前有效",
            showMoreType:false,//点击查看更多机构
            options: 
                [
                    {
                        label: "当前有效",
                        value: 1
                    },
                    {
                        label: "当前失效",
                        value: 0
                    },
                    {
                        label: "全部",
                        value: ''
                    }
              ],
        }
    },
      watch: {
        agentState(n, o) {
            switch (n) {
                case 0:
                // 失效
                    this.accounts = this.accountsUnValid;
                    this.sponsors = this.sponsorsUnValid;
                    this.securitys = this.securitysUnValid;
                    this.lawyers = this.lawyersUnValid;
                    this.assets = this.assetsUnValid;
                    break;
                case 1:
                // 有效
                    this.accounts = this.accountsValid;
                    this.sponsors = this.sponsorsValid;
                    this.securitys = this.securitysValid;
                    this.lawyers = this.lawyersValid;
                    this.assets = this.assetsValid;
                    break;
                 default:
                //  全部
                    this.accounts = this.accountsTotal;
                    this.sponsors = this.sponsorsTotal;
                    this.securitys = this.securitysTotal;
                    this.lawyers = this.lawyersTotal;
                    this.assets = this.assetsTotal;
                    break;
            }
        }

    },
    created() {
     this.initTableData()
    },
  methods: {
    initTableData() {
      // 动态传id
      const param = {
        id:this.caseId
      }
      getIntermediaryOrgDataList(param).then(response => {
          if(response.data.result){
              console.log('中介机构',response.data.result)
              this.allStitutionList = response.data.result
              this.accountsTotal = response.data.result.accounts
              this.sponsorsTotal = response.data.result.sponsors
              this.securitysTotal = response.data.result.securitys
              this.lawyersTotal = response.data.result.lawyers
              this.assetsTotal = response.data.result.assets
          }
        // 筛选有效
         for (let m = 0; m < this.accountsTotal.length; m++) {
            if (this.accountsTotal[m].validFlag == 1) {
                this.accountsValid.push(this.accountsTotal[m]);
            } else {
                this.accountsUnValid.push(this.accountsTotal[m]);
            }
        }
         for (let m = 0; m < this.sponsorsTotal.length; m++) {
            if (this.sponsorsTotal[m].validFlag == 1) {
                this.sponsorsValid.push(this.sponsorsTotal[m]);
            } else {
                this.sponsorsUnValid.push(this.sponsorsTotal[m]);
            }
        }
         for (let m = 0; m < this.securitysTotal.length; m++) {
            if (this.securitysTotal[m].validFlag == 1) {
                this.securitysValid.push(this.securitysTotal[m]);
            } else {
                this.securitysUnValid.push(this.securitysTotal[m]);
            }
        }
         for (let m = 0; m < this.lawyersTotal.length; m++) {
            if (this.lawyersTotal[m].validFlag == 1) {
                this.lawyersValid.push(this.lawyersTotal[m]);
            } else {
                this.lawyersUnValid.push(this.lawyersTotal[m]);
            }
        }
         for (let m = 0; m < this.assetsTotal.length; m++) {
            if (this.assetsTotal[m].validFlag == 1) {
                this.assetsValid.push(this.assetsTotal[m]);
            } else {
                this.assetsUnValid.push(this.assetsTotal[m]);
            }
        }
        // 刷新后赋值
        this.accounts = this.accountsValid;
        this.sponsors = this.sponsorsValid;
        this.securitys = this.securitysValid;
        this.lawyers = this.lawyersValid;
        this.assets = this.assetsValid;
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
    },
    // 点击查看更多机构类型
    spreadMoreType(){
        this.showMoreType = true
    },
    // 收起更多类型
    packUpMoreType(){
        this.showMoreType = false
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
// 已失效类
.failureBackground{
     background:url('../../../../assets/images/failureBackground.png') no-repeat;
     background-size: cover;
}
.InstitutionsDetail {
  ul {
    padding-left: 0;
    width: 100%;
    .InstitutionsDetailLi {
      padding: 20px;
      margin-left: 1%;
      width: 98%;
    //   height: 130px;
    
      .image {
            width: 15%;
            height: 100%;
            position: relative;
            top: 15px;
            left: 23px;
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


