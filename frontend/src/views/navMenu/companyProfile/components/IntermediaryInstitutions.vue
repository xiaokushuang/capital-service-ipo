<template>
    <div>
       <div class="IntermediaryInstitutions">
            <!-- 筛选 -->
            <el-select v-if="dataFlag" v-model="agentState" placeholder="请选择" style="margin-left:16px;width:120px">
                <el-option v-for="item in options" :label="item.label" :value="item.value" :key="item.value">
                </el-option>
            </el-select>
            <div class="InstitutionsDetail">
                <ul>
                    <li class="clear " v-for="item in mainList">
                        <p class="institutionTitle">{{item.intermediaryTypeName}}</p>
                        <div :class="item.validFlag == 0 ?'failureBackground InstitutionsDetailLi clear':'InstitutionsDetailLi clear'">
                             <div v-if="item.intermediaryType=='1'" class="image l" style="margin-top:24px">
                                 <img src="../../../../assets/images/coSponsors.png" alt>
                            </div>
                            <div v-if="item.intermediaryType=='2'" class="image l">
                                  <img src="../../../../assets/images/coSponsors.png" alt>
                            </div>
                            <div v-if="item.intermediaryType=='3'" class="image l" >
                                 <img src="../../../../assets/images/lvshi.png" alt>
                            </div>
                            <div v-if="item.intermediaryType=='4'" class="image l" >
                                 <img src="../../../../assets/images/kuaiji.png" alt>
                            </div>
                            <div v-if="item.intermediaryType=='5'" class="image l" >
                                 <img src="../../../../assets/images/assets.png" alt>
                            </div>
                            <div v-if="item.intermediaryType=='6'" class="image l" >
                                 <img src="../../../../assets/images/cuntuo.png" alt>
                            </div>
                            <div class="text l">
                                <div>
                                    <span style="font-family: '微软雅黑 Bold', '微软雅黑 Regular', 微软雅黑;font-weight: 700;
                                    font-style: normal; font-size: 16px; color: #363636">{{item.orgName}}</span>
                                </div>
                                <!-- 保荐机构 -->
                                <ul v-if="item.intermediaryType=='1'">
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">保荐代表人：</span>
                                        <span class="bj" style="font-size:14px;color:black"  @mouseenter="mouseOverSpreadText(item.representPerson)">{{getContent(item.representPerson)}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">项目协办人：</span>
                                        <span style="font-size:14px;color:black" class="xb" @mouseenter="mouseOverSpreadText(item.assistPerson )">{{getContent(item.assistPerson)}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">项目经办人：</span>
                                        <span style="font-size:14px;color:black" class="jb" @mouseenter="mouseOverSpreadText(item.agentPerson )">{{getContent(item.agentPerson)}}</span>
                                    </li>
                                </ul>
                                <!-- 律师事务所 -->
                                <ul v-if="item.intermediaryType=='3'">
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">经办律师：</span>
                                        <span style="font-size:14px;color:black" class="ls" @mouseenter="mouseOverSpreadText(item.agentPerson )">{{getContent(item.agentPerson)}}</span>
                                    </li>
                                </ul>
                                <!-- 会计事务所 -->
                                <ul v-if="item.intermediaryType=='4'">
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">注册会计师：</span>
                                        <span style="font-size:14px;color:black" class="kj" @mouseenter="mouseOverSpreadText(item.agentPerson )">{{getContent(item.agentPerson)}}</span>
                                    </li>
                                </ul>
                                 <!-- 资产评估机构 -->
                                <ul v-if="item.intermediaryType=='5'">
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">注册资产评估师：</span>
                                        <span style="font-size:14px;color:black" class="pg" @mouseenter="mouseOverSpreadText(item.agentPerson )">{{getContent(item.agentPerson)}}</span>
                                    </li>
                                </ul>
                                <!-- 证券公司 -->
                                <ul v-if="item.intermediaryType=='2'">
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">项目经办人：</span>
                                        <span style="font-size:14px;color:black" class="jb" @mouseenter="mouseOverSpreadText(item.agentPerson )">{{getContent(item.agentPerson)}}</span>
                                    </li>
                                </ul>
                            </div> 
                            <!-- 已失效标志 -->
                            <span class="failure" v-show="item.validFlag == 0">
                                已失效
                            </span>                              
                        </div>
                    </li>
                    <li v-show="showMoreType" class="clear " v-for="item in moreList">
                        <p class="institutionTitle">{{item.intermediaryTypeName}}</p>
                        <div :class="item.validFlag == 0 ?'failureBackground InstitutionsDetailLi clear':'InstitutionsDetailLi clear'">
                            <div v-if="item.intermediaryType=='1'" class="image l" style="margin-top:24px" >
                                 <img src="../../../../assets/images/coSponsors.png" alt>
                            </div>
                            <div v-if="item.intermediaryType=='2'" class="image l">
                                  <img src="../../../../assets/images/coSponsors.png" alt>
                            </div>
                            <div v-if="item.intermediaryType=='3'" class="image l" >
                                 <img src="../../../../assets/images/lvshi.png" alt>
                            </div>
                            <div v-if="item.intermediaryType=='4'" class="image l" >
                                 <img src="../../../../assets/images/kuaiji.png" alt>
                            </div>
                            <div v-if="item.intermediaryType=='5'" class="image l" >
                                 <img src="../../../../assets/images/assets.png" alt>
                            </div>
                             <div v-if="item.intermediaryType=='6'" class="image l" >
                                 <img src="../../../../assets/images/cuntuo.png" alt>
                            </div>
                            <div class="text l">
                                <div>
                                    <span style="font-family: '微软雅黑 Bold', '微软雅黑 Regular', 微软雅黑;font-weight: 700;
                                    font-style: normal; font-size: 16px; color: #363636">{{item.orgName}}</span>
                                </div>
                                
                               <!-- 保荐机构 -->
                                <ul v-if="item.intermediaryType=='1'">
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">保荐代表人：</span>
                                        <span class="bj" style="font-size:14px;color:black"  @mouseenter="mouseOverSpreadText(item.representPerson )">{{getContent(item.representPerson)}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">项目协办人：</span>
                                        <span style="font-size:14px;color:black" class="xb" @mouseenter="mouseOverSpreadText(item.assistPerson )">{{getContent(item.assistPerson)}}</span>
                                    </li>
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">项目经办人：</span>
                                        <span style="font-size:14px;color:black" class="jb" @mouseenter="mouseOverSpreadText(item.agentPerson )">{{getContent(item.agentPerson)}}</span>
                                    </li>
                                </ul>
                                <!-- 律师事务所 -->
                                <ul v-if="item.intermediaryType=='3'">
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">经办律师：</span>
                                        <span style="font-size:14px;color:black" class="ls" @mouseenter="mouseOverSpreadText(item.agentPerson )">{{getContent(item.agentPerson)}}</span>
                                    </li>
                                </ul>
                                <!-- 会计事务所 -->
                                <ul v-if="item.intermediaryType=='4'">
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">注册会计师：</span>
                                        <span style="font-size:14px;color:black" class="kj" @mouseenter="mouseOverSpreadText(item.agentPerson )">{{getContent(item.agentPerson)}}</span>
                                    </li>
                                </ul>
                                 <!-- 资产评估机构 -->
                                <ul v-if="item.intermediaryType=='5'">
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">注册资产评估师：</span>
                                        <span style="font-size:14px;color:black" class="pg" @mouseenter="mouseOverSpreadText(item.agentPerson )">{{getContent(item.agentPerson)}}</span>
                                    </li>
                                </ul>
                                <!-- 证券公司 -->
                                <ul v-if="item.intermediaryType=='2'">
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">项目经办人：</span>
                                        <span style="font-size:14px;color:black" class="jb" @mouseenter="mouseOverSpreadText(item.agentPerson )">{{getContent(item.agentPerson)}}</span>
                                    </li>
                                </ul>
                                <!-- 存托机构 -->
                                 <ul v-if="item.intermediaryType=='6'">
                                    <li class="people">
                                        <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                                        font-style: normal;  font-size: 14px; color: #999999;">经办人：</span>
                                        <span style="font-size:14px;color:black" class="jb" @mouseenter="mouseOverSpreadText(item.agentPerson )">{{getContent(item.agentPerson)}}</span>
                                    </li>
                                </ul>
                            </div> 
                            <!-- 已失效标志 -->
                            <span class="failure" v-show="item.validFlag == 0">
                                已失效
                            </span>                              
                        </div>
                    </li>                    
                </ul>
                 <div style=" text-align: center;">
                    <span v-show="moreList&&moreList.length>0" style="color: rgb(25, 144, 254);font-size:14px;text-align:center;cursor:pointer" class="packUp" v-if="showMoreType" @click="packUpMoreType()">收起 <i class="el-icon-arrow-up"></i></span>
                    <span v-show="moreList&&moreList.length>0" style="color: rgb(25, 144, 254);font-size:14px;text-align:center;cursor:pointer" class="spread" v-else @click="spreadMoreType()">更多中介机构 <i class="el-icon-arrow-down"></i></span>
                 </div>
            </div>
        </div>
    </div>
</template>
<script>
import $ from "jquery";
import { getIntermediaryOrgDataList } from '@/api/ipoCase/companyProfile'
export default {
    name:'IntermediaryInstitutions',
    data(){
        return{
            caseId:this.$store.state.app.caseId,
            mainList:[],//主要机构
            moreList:[],//更多机构
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
            dataFlag:false,
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
                this.initTableData(0)
                    break;
                case 1:
                this.initTableData(1)
                    break;
                 default:
                 this.initTableData('')
                    break;
            }
        }

    },
    created() {
     this.initTableData(1)
     this.initTableAllData()
    },
  methods: {
    initTableData(validFlag) {
      // 动态传id
      const param = {
        id:this.caseId,
        validFlag:validFlag
      }
      getIntermediaryOrgDataList(param).then(response => {
          if(response.data.result){
              this.allStitutionList = response.data.result
              this.mainList = response.data.result.mainList
              this.moreList = response.data.result.moreList
          }
      })
    },
    initTableAllData() {
      // 动态传id
      const param = {
        id:this.caseId,
      }
      getIntermediaryOrgDataList(param).then(response => {
          if((response.data.result&&response.data.result.mainList&&response.data.result.mainList.length>0) ||(response.data.result&&response.data.result.moreList&&response.data.result.moreList.length>0)){
              this.dataFlag = true
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
    },
    // 点击查看更多机构类型
    spreadMoreType(){
        this.showMoreType = true
    },
    // 收起更多类型
    packUpMoreType(){
        this.showMoreType = false
    },
    // 鼠标移入委员详情弹出窗的列
    mouseOverSpreadText(title){ 
        for(let a = 0;a< $(".bj").length;a++){
            if(title.length>38){
                $(".bj").eq(a).attr("title",title)
                // $(".bj").eq(a).css({"cursor":"pointer"})
            }else{
                $(".bj").eq(a).removeAttr("title",title)
                // $(".bj").eq(a).css({"cursor":"auto"})
            }
        }
         for(let a = 0;a< $(".xb").length;a++){
           if(title.length>38){
                $(".xb").eq(a).attr("title",title)
                // $(".xb").eq(a).css({"cursor":"pointer"})
            }else{
                $(".xb").eq(a).removeAttr("title",title)
                // $(".xb").eq(a).css({"cursor":"auto"})
            }
        }
        for(let a = 0;a< $(".jb").length;a++){
            if(title.length>38){
                $(".jb").eq(a).attr("title",title)
                // $(".jb").eq(a).css({"cursor":"pointer"})
            }else{
                $(".jb").eq(a).removeAttr("title",title)
                // $(".jb").eq(a).css({"cursor":"auto"})
            }
        }
        for(let a = 0;a< $(".ls").length;a++){
            if(title.length>38){
                $(".ls").eq(a).attr("title",title)
                // $(".ls").eq(a).css({"cursor":"pointer"})
            }else{
                $(".ls").eq(a).removeAttr("title",title)
                // $(".ls").eq(a).css({"cursor":"auto"})
            }
        }
         for(let a = 0;a< $(".kj").length;a++){
             if(title.length>38){
                $(".kj").eq(a).attr("title",title)
                // $(".kj").eq(a).css({"cursor":"pointer"})
            }else{
                $(".kj").eq(a).removeAttr("title",title)
                // $(".kj").eq(a).css({"cursor":"auto"})
            }
        }
        for(let a = 0;a< $(".pg").length;a++){
            if(title.length>38){
                $(".pg").eq(a).attr("title",title)
                // $(".pg").eq(a).css({"cursor":"pointer"})
            }else{
                $(".pg").eq(a).removeAttr("title",title)
                // $(".pg").eq(a).css({"cursor":"auto"})
            }
        }
    },
   getContent(title){
     if(title.length>38){
       return title.substring(0,37) + '...'
     }else{
       return title
     }
   },
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
.failure{
    border-width: 0px;
    width: 63px;
    height: 20px;
    padding-left: 10px;
    font-size: 14px;
    position: absolute;
    left: 92.5%;
    background: #e4e4e4;
    border: none;
    border-right: 0px;
    border-radius: 20px;
    border-top-right-radius: 0px;
    border-bottom-right-radius: 0px;
    color: #999;
    padding-top: 2px;
    padding-bottom: 2px;
}
.failureBackground{
     background:url('../../../../assets/images/failureBackground.png') no-repeat;
     background-size: cover;
}
.InstitutionsDetail {
  ul {
    padding-left: 0;
    width: 100%;
    .InstitutionsDetailLi {
        position:relative;
      padding: 20px;
      margin-left: 1%;
      width: 98%;
      .image {
        width: 12%;
        margin: auto;
        margin-left: 3%;
        margin-right: 0;
      }
     .text {
        ul {
          width: 100%;
          margin-top: 8px;
          li {
            line-height: 20px;
            margin-top:8px;
          }
        }
      }
    }
  }
}
.InstitutionsDetailLi:hover {
//   cursor: pointer;
  box-shadow: 0 0px 28px -5px #ccc;
}
.el-select-dropdown__item.selected {
  color: #14bcf5 !important;
}
</style>


