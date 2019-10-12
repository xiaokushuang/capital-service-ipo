<template>
  <div>
    <div class="IntermediaryInstitutions">
      <!-- 筛选 -->
      <el-select v-if="getAllArrFlag()" v-model="agentState" placeholder="请选择" style="margin-left:16px;width:120px">
        <el-option v-for="item in options" :label="item.label" :value="item.value" :key="item.value">
        </el-option>
      </el-select>
      <div class="InstitutionsDetail">
        <ul>
          <li class="clear " v-for="item in IntermediaryList">
            <p class="institutionTitle">{{item.intermediaryTypeName}}</p>
            <div :class="item.validFlag == 0 ?'failureBackground InstitutionsDetailLi clear':'InstitutionsDetailLi clear'">
              <div class="image l">
                <img src="../../../../assets/images/coSponsors.png" alt>
              </div>
              <div class="text l">
                <div>
                      <span v-if="item.orgName" style="font-family: '微软雅黑 Bold', '微软雅黑 Regular', 微软雅黑;font-weight: 700;
                      font-style: normal; font-size: 16px; color: #363636">{{item.orgName}}</span>
                </div>
                <!-- 经办辅导人员 -->
                <ul>
                  <li class="people">
                    <span style="font-family: 'PingFang-SC-Regular', 'PingFang SC'; font-weight: 400;
                    font-style: normal;  font-size: 14px; color: #999999;">经办辅导人员：</span>
                    <span v-if="item.agentPerson" style="font-size:14px;color:black" class="ls" :title="item.agentPerson.length>38?item.agentPerson:''">{{getContent(item.agentPerson)}}</span>
                    <span v-else>- -</span>
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
      </div>
    </div>
  </div>
</template>
<script>
  import $ from "jquery";
  import { getIntermediaryOrgDataList } from '@/api/ipoCase/companyProfile'
  export default {
    name:'IntermediaryInstitutions',
    props:["intermediaryOrgList"],
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
        agentState: 1,
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
        IntermediaryList:[],
        failureArr:[],
        effectiveArr:[],
        allArr:[],
      }
    },
    watch: {
      agentState(n, o) {
        switch (n) {
          case 0:
            this.getFailureArr()
            break;
          case 1:
            this.getEffectiveArr()
            break;
          default:
            this.getAllArr()
            break;
        }
      }
    },
    created() {
      this.initIntermediaryOrgLista()
      console.log('中介机构----------子',this.intermediaryOrgList)
    },
    mounted(){

    },
    methods: {
      initIntermediaryOrgLista(){
        this.getEffectiveArr()
      },
      getAllArrFlag(){
        let allList = []
        for(let i = 0;i<this.intermediaryOrgList.length;i++){
          if(this.intermediaryOrgList[i].intermediaryType=='7'){
            allList.push(this.intermediaryOrgList[i])
            if(allList&&allList.length>0){
              return true
            }
            return false
          }
        }
      },
      getAllArr(){
        this.IntermediaryList = []
        this.allArr = []
        for(let i = 0;i<this.intermediaryOrgList.length;i++){
          if(this.intermediaryOrgList[i].intermediaryType=='7'){
            this.allArr.push(this.intermediaryOrgList[i])
            this.IntermediaryList = this.allArr
          }
        }
        console.log('全部',this.IntermediaryList)
      },
      getEffectiveArr(){
        this.IntermediaryList = []
        this.effectiveArr = []
        for(let i = 0;i<this.intermediaryOrgList.length;i++){
          if(this.intermediaryOrgList[i].validFlag == '1'&&this.intermediaryOrgList[i].intermediaryType=='7'){
            this.effectiveArr.push(this.intermediaryOrgList[i])
            this.IntermediaryList = this.effectiveArr

          }
        }
        console.log('有效',this.IntermediaryList)
      },
      getFailureArr(){
        this.IntermediaryList = []
        this.failureArr = []
        for(let i = 0;i<this.intermediaryOrgList.length;i++){
          if(this.intermediaryOrgList[i].validFlag == '0'&&this.intermediaryOrgList[i].intermediaryType=='7'){
            this.failureArr.push(this.intermediaryOrgList[i])
            this.IntermediaryList = this.failureArr

          }
        }
        console.log('失效',this.IntermediaryList)
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


