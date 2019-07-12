<template>
    <div class="feedback" id="componentId">
       <div class="label">
            <!-- 导入的组件 -->
            <!-- 只有一级标签 -->
            <div v-if="tabList&&tabList.length==1" class="clear">
              <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[0].questionLabelList,checkbox:feedbackduoxuanList,answerCount:answerCount,questionCount:questionCount,questionList:questionList,o_letterId:this.o_letterId,showMore:showMore,allQuestionList:allQuestionList}></singleAndMultiple>
            </div>
            <!-- 有多级标签选择 -->
             <div v-if="tabList&&tabList.length > 1" class="clear">
                <div v-if="tabList.length==2&&companyProfileList.headList.isTechBoard==0" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共两轮反馈：</div>
                <div v-if="tabList.length==3&&companyProfileList.headList.isTechBoard==0" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共三轮反馈：</div>
                <div v-if="tabList.length==4&&companyProfileList.headList.isTechBoard==0" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共四轮反馈：</div>
                <div v-if="tabList.length==5&&companyProfileList.headList.isTechBoard==0" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共五轮反馈：</div>
                <div v-if="tabList.length==6&&companyProfileList.headList.isTechBoard==0" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共六轮反馈：</div>
                <div v-if="tabList.length==7&&companyProfileList.headList.isTechBoard==0" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共七轮反馈：</div>
                <div v-if="tabList.length==8&&companyProfileList.headList.isTechBoard==0" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共八轮反馈：</div>
                <div v-if="tabList.length==9&&companyProfileList.headList.isTechBoard==0" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共九轮反馈：</div>
                <div v-if="tabList.length==10&&companyProfileList.headList.isTechBoard==0" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共十轮反馈：</div>
                <div v-if="tabList.length==2&&companyProfileList.headList.isTechBoard==1" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 21;font-style: normal;font-size: 14px;color: #A1A1A1;">共两轮问询：</div>
                <div v-if="tabList.length==3&&companyProfileList.headList.isTechBoard==1" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共三轮问询：</div>
                <div v-if="tabList.length==4&&companyProfileList.headList.isTechBoard==1" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共四轮问询：</div>
                <div v-if="tabList.length==5&&companyProfileList.headList.isTechBoard==1" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共五轮问询：</div>
                <div v-if="tabList.length==6&&companyProfileList.headList.isTechBoard==1" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共六轮问询：</div>
                <div v-if="tabList.length==7&&companyProfileList.headList.isTechBoard==1" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共七轮问询：</div>
                <div v-if="tabList.length==8&&companyProfileList.headList.isTechBoard==1" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共八轮问询：</div>
                <div v-if="tabList.length==9&&companyProfileList.headList.isTechBoard==1" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共九轮问询：</div>
                <div v-if="tabList.length==10&&companyProfileList.headList.isTechBoard==1" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共十轮问询：</div>
                <div>
                    <el-tabs v-model="activeName" @tab-click="handleTabClick">
                       <el-tab-pane :label="tabList[0].letterName" :name="tabList[0].letterId">
                          <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[0].questionLabelList,checkbox:feedbackduoxuanList,answerCount:answerCount,questionCount:questionCount,questionList:questionList,o_letterId:this.o_letterId,showMore:showMore,allQuestionList:allQuestionList}></singleAndMultiple>
                       </el-tab-pane>
                       <el-tab-pane :label="tabList[1].letterName" :name="tabList[1].letterId">
                          <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[1].questionLabelList,checkbox:feedbackduoxuanList2,answerCount:answerCount2,questionCount:questionCount2,questionList:questionList2,o_letterId:this.o_letterId,showMore:showMore2,allQuestionList:allQuestionList2}></singleAndMultiple>
                       </el-tab-pane>
                        <el-tab-pane v-if="tabList&&tabList.length>2" :label="tabList[2].letterName" :name="tabList[2].letterId">
                          <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[2].questionLabelList,checkbox:feedbackduoxuanList3,answerCount:answerCount3,questionCount:questionCount3,questionList:questionList3,o_letterId:this.o_letterId,showMore:showMore3,allQuestionList:allQuestionList3}></singleAndMultiple>
                       </el-tab-pane>
                        <el-tab-pane v-if="this.tabList&&this.tabList.length>3" :label="tabList[3].letterName" :name="tabList[3].letterId">
                          <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[3].questionLabelList,checkbox:feedbackduoxuanList4,answerCount:answerCount4,questionCount:questionCount4,questionList:questionList4,o_letterId:this.o_letterId,showMore:showMore4,allQuestionList:allQuestionList4}></singleAndMultiple>
                       </el-tab-pane>
                        <el-tab-pane v-if="this.tabList&&this.tabList.length>4" :label="tabList[4].letterName" :name="tabList[4].letterId">
                          <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[4].questionLabelList,checkbox:feedbackduoxuanList5,answerCount:answerCount5,questionCount:questionCount5,questionList:questionList5,o_letterId:this.o_letterId,showMore:showMore5,allQuestionList:allQuestionList5}></singleAndMultiple>
                       </el-tab-pane>
                        <el-tab-pane v-if="this.tabList&&this.tabList.length>5" :label="tabList[5].letterName" :name="tabList[5].letterId">
                          <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[5].questionLabelList,checkbox:feedbackduoxuanList6,answerCount:answerCount6,questionCount:questionCount6,questionList:questionList6,o_letterId:this.o_letterId,showMore:showMore6,allQuestionList:allQuestionList6}></singleAndMultiple>
                       </el-tab-pane>
                       <el-tab-pane v-if="this.tabList&&this.tabList.length>6" :label="tabList[6].letterName" :name="tabList[6].letterId">
                          <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[6].questionLabelList,checkbox:feedbackduoxuanList7,answerCount:answerCount7,questionCount:questionCount7,questionList:questionList7,o_letterId:this.o_letterId,showMore:showMore7,allQuestionList:allQuestionList7}></singleAndMultiple>
                       </el-tab-pane>
                        <el-tab-pane v-if="this.tabList&&this.tabList.length>7" :label="tabList[7].letterName" :name="tabList[7].letterId">
                          <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[7].questionLabelList,checkbox:feedbackduoxuanList8,answerCount:answerCount8,questionCount:questionCount8,questionList:questionList8,o_letterId:this.o_letterId,showMore:showMore8,allQuestionList:allQuestionList8}></singleAndMultiple>
                       </el-tab-pane>
                       <el-tab-pane v-if="this.tabList&&this.tabList.length>8" :label="tabList[8].letterName" :name="tabList[8].letterId">
                          <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[8].questionLabelList,checkbox:feedbackduoxuanList9,answerCount:answerCount9,questionCount:questionCount9,questionList:questionList9,o_letterId:this.o_letterId,showMore:showMore9,allQuestionList:allQuestionList9}></singleAndMultiple>
                       </el-tab-pane>
                       <el-tab-pane v-if="this.tabList&&this.tabList.length>9" :label="tabList[9].letterName" :name="tabList[9].letterId">
                          <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[9].questionLabelList,checkbox:feedbackduoxuanList10,answerCount:answerCount10,questionCount:questionCount10,questionList:questionList10,o_letterId:this.o_letterId,showMore:showMore10,allQuestionList:allQuestionList10}></singleAndMultiple>
                       </el-tab-pane>
                    </el-tabs>
                </div>
             </div>
       </div>
   </div>
</template>
<script>
import { getSelectFeedbackList } from "@/api/ipoCase/companyProfile";
import { getSelectQuestionList } from "@/api/ipoCase/companyProfile";
// 导入筛选问题列表组件
import singleAndMultiple from "@/views/navMenu/singleAndMultiple/singleAndMultiple"
import $ from "jquery";
export default {
  name: "feedback",
  components: {
      singleAndMultiple
  },
  props:["companyProfileList"],
  data() {
    return {
      caseId: this.$store.state.app.caseId,
      // 总共数据
      tabList: [],
      // 多选按钮数据
      feedbackduoxuanList: [],
      feedbackduoxuanList2: [],
      feedbackduoxuanList3: [],
      feedbackduoxuanList4: [],
      feedbackduoxuanList5: [],
      feedbackduoxuanList6: [],
      feedbackduoxuanList7: [],
      feedbackduoxuanList8: [],
      feedbackduoxuanList9: [],
      feedbackduoxuanList10: [],
      flag: false,
      // 默认展示第一个label页
      activeName: "",
      // 函件id,通过这个id区分不同tab页
      letterId: "",
      // 自己定义的
      o_letterId: "",
      // 多选
      o_secondtLabelId: "",
      // 所有问题列表
      questionList: [],
      questionList2: [],
      questionList3: [],
      questionList4: [],
      questionList5: [],
      questionList6: [],
      questionList7: [],
      questionList8: [],
      questionList9: [],
      questionList10: [],
      // // 回复个数
      answerCount: "",
      answerCount2: "",
      answerCount3: "",
      answerCount4: "",
      answerCount5: "",
      answerCount6: "",
      answerCount7: "",
      answerCount8: "",
      answerCount9: "",
      answerCount10: "",
      // // 问题个数
      questionCount: "",
      questionCount2: "",
      questionCount3: "",
      questionCount4: "",
      questionCount5: "",
      questionCount6: "",
      questionCount7: "",
      questionCount8: "",
      questionCount9: "",
      questionCount10: "",
      // 是否展示全部问题
      isShowAll: true,
      // 默认有moreText类
      isMoreText: true,
      showLength: 15,
      showLength2: 15,
      showLength3: 15,
      showLength4: 15,
      showLength5: 15,
      showLength6: 15,
      showLength7: 15,
      showLength8: 15,
      showLength9: 15,
      showLength10: 15,
      allQuestionList: [],
      allQuestionList2: [],
      allQuestionList3: [],
      allQuestionList4: [],
      allQuestionList5: [],
      allQuestionList6: [],
      allQuestionList7: [],
      allQuestionList8: [],
      allQuestionList9: [],
      allQuestionList10: [],
      showMore: false,
      showMore2: false,
      showMore3: false,
      showMore4: false,
      showMore5: false,
      showMore6: false,
      showMore7: false,
      showMore8: false,
      showMore9: false,
      showMore10: false,
      // 多选按钮’全部‘
      showAll: false,
      firstClick: true,
      flagLoading: false
    };
  },
  created() {
    //   请求数据
    this.initTableData();
    this.isShowAll = true;
  },
  methods: {
    showMoreMethods(){
      if(this.tabList.length==1){
        this.showLength+=15
        if(this.allQuestionList.length > this.showLength){
          this.flagLoading = false;
          this.showMore = true;
          this.questionList = this.allQuestionList.slice(0, this.showLength);
        }else{
          this.showMore = false;
          this.questionList = this.allQuestionList;
        }
      }
      if(this.tabList.length==2){
        this.showLength+=15
        if(this.allQuestionList.length > this.showLength){
          this.showMore = true;
          this.questionList = this.allQuestionList.slice(0, this.showLength);
        }else{
          this.showMore = false;
          this.questionList = this.allQuestionList;
        }
        this.showLength2+=15
        if(this.allQuestionList2.length > this.showLength2){
          this.showMore2 = true;
          this.questionList2 = this.allQuestionList2.slice(0, this.showLength2);
        }else{
          this.showMore2 = false;
          this.questionList2 = this.allQuestionList2;
        }
      }
      if(this.tabList.length==3){
        this.showLength+=15
        if(this.allQuestionList.length > this.showLength){
          this.showMore = true;
          this.questionList = this.allQuestionList.slice(0, this.showLength);
        }else{
          this.showMore = false;
          this.questionList = this.allQuestionList;
        }
        this.showLength2+=15
        if(this.allQuestionList2.length > this.showLength2){
          this.showMore2 = true;
          this.questionList2 = this.allQuestionList2.slice(0, this.showLength2);
        }else{
          this.showMore2 = false;
          this.questionList2 = this.allQuestionList2;
        }
         this.showLength3+=15
        if(this.allQuestionList3.length > this.showLength3){
          this.showMore3 = true;
          this.questionList3 = this.allQuestionList3.slice(0, this.showLength3);
        }else{
          this.showMore3 = false;
          this.questionList3 = this.allQuestionList3;
        }
      }
      if(this.tabList.length==4){
        this.showLength+=15
        if(this.allQuestionList.length > this.showLength){
          this.showMore = true;
          this.questionList = this.allQuestionList.slice(0, this.showLength);
        }else{
          this.showMore = false;
          this.questionList = this.allQuestionList;
        }
        this.showLength2+=15
        if(this.allQuestionList2.length > this.showLength2){
          this.showMore2 = true;
          this.questionList2 = this.allQuestionList2.slice(0, this.showLength2);
        }else{
          this.showMore2 = false;
          this.questionList2 = this.allQuestionList2;
        }
         this.showLength3+=15
        if(this.allQuestionList3.length > this.showLength3){
          this.showMore3 = true;
          this.questionList3 = this.allQuestionList3.slice(0, this.showLength3);
        }else{
          this.showMore3 = false;
          this.questionList3 = this.allQuestionList3;
        }
         this.showLength4+=15
        if(this.allQuestionList4.length > this.showLength4){
          this.showMore4 = true;
          this.questionList4 = this.allQuestionList4.slice(0, this.showLength4);
        }else{
          this.showMore4 = false;
          this.questionList4 = this.allQuestionList4;
        }
      }
      if(this.tabList.length==5){
        this.showLength+=15
        if(this.allQuestionList.length > this.showLength){
          this.showMore = true;
          this.questionList = this.allQuestionList.slice(0, this.showLength);
        }else{
          this.showMore = false;
          this.questionList = this.allQuestionList;
        }
        this.showLength2+=15
        if(this.allQuestionList2.length > this.showLength2){
          this.showMore2 = true;
          this.questionList2 = this.allQuestionList2.slice(0, this.showLength2);
        }else{
          this.showMore2 = false;
          this.questionList2 = this.allQuestionList2;
        }
         this.showLength3+=15
        if(this.allQuestionList3.length > this.showLength3){
          this.showMore3 = true;
          this.questionList3 = this.allQuestionList3.slice(0, this.showLength3);
        }else{
          this.showMore3 = false;
          this.questionList3 = this.allQuestionList3;
        }
         this.showLength4+=15
        if(this.allQuestionList4.length > this.showLength4){
          this.showMore4 = true;
          this.questionList4 = this.allQuestionList4.slice(0, this.showLength4);
        }else{
          this.showMore4 = false;
          this.questionList4 = this.allQuestionList4;
        }
         this.showLength5+=15
        if(this.allQuestionList5.length > this.showLength5){
          this.showMore5 = true;
          this.questionList5 = this.allQuestionList5.slice(0, this.showLength5);
        }else{
          this.showMore5 = false;
          this.questionList5 = this.allQuestionList5;
        }
      }
      if(this.tabList.length==6){
        this.showLength+=15
        if(this.allQuestionList.length > this.showLength){
          this.showMore = true;
          this.questionList = this.allQuestionList.slice(0, this.showLength);
        }else{
          this.showMore = false;
          this.questionList = this.allQuestionList;
        }
        this.showLength2+=15
        if(this.allQuestionList2.length > this.showLength2){
          this.showMore2 = true;
          this.questionList2 = this.allQuestionList2.slice(0, this.showLength2);
        }else{
          this.showMore2 = false;
          this.questionList2 = this.allQuestionList2;
        }
         this.showLength3+=15
        if(this.allQuestionList3.length > this.showLength3){
          this.showMore3 = true;
          this.questionList3 = this.allQuestionList3.slice(0, this.showLength3);
        }else{
          this.showMore3 = false;
          this.questionList3 = this.allQuestionList3;
        }
         this.showLength4+=15
        if(this.allQuestionList4.length > this.showLength4){
          this.showMore4 = true;
          this.questionList4 = this.allQuestionList4.slice(0, this.showLength4);
        }else{
          this.showMore4 = false;
          this.questionList4 = this.allQuestionList4;
        }
         this.showLength5+=15
        if(this.allQuestionList5.length > this.showLength5){
          this.showMore5 = true;
          this.questionList5 = this.allQuestionList5.slice(0, this.showLength5);
        }else{
          this.showMore5 = false;
          this.questionList5 = this.allQuestionList5;
        }
         this.showLength6+=15
        if(this.allQuestionList6.length > this.showLength6){
          this.showMore6 = true;
          this.questionList6 = this.allQuestionList6.slice(0, this.showLength6);
        }else{
          this.showMore6 = false;
          this.questionList6 = this.allQuestionList6;
        }
      }
      if(this.tabList.length==7){
        this.showLength+=15
        if(this.allQuestionList.length > this.showLength){
          this.showMore = true;
          this.questionList = this.allQuestionList.slice(0, this.showLength);
        }else{
          this.showMore = false;
          this.questionList = this.allQuestionList;
        }
        this.showLength2+=15
        if(this.allQuestionList2.length > this.showLength2){
          this.showMore2 = true;
          this.questionList2 = this.allQuestionList2.slice(0, this.showLength2);
        }else{
          this.showMore2 = false;
          this.questionList2 = this.allQuestionList2;
        }
         this.showLength3+=15
        if(this.allQuestionList3.length > this.showLength3){
          this.showMore3 = true;
          this.questionList3 = this.allQuestionList3.slice(0, this.showLength3);
        }else{
          this.showMore3 = false;
          this.questionList3 = this.allQuestionList3;
        }
         this.showLength4+=15
        if(this.allQuestionList4.length > this.showLength4){
          this.showMore4 = true;
          this.questionList4 = this.allQuestionList4.slice(0, this.showLength4);
        }else{
          this.showMore4 = false;
          this.questionList4 = this.allQuestionList4;
        }
         this.showLength5+=15
        if(this.allQuestionList5.length > this.showLength5){
          this.showMore5 = true;
          this.questionList5 = this.allQuestionList5.slice(0, this.showLength5);
        }else{
          this.showMore5 = false;
          this.questionList5 = this.allQuestionList5;
        }
         this.showLength6+=15
        if(this.allQuestionList6.length > this.showLength6){
          this.showMore6 = true;
          this.questionList6 = this.allQuestionList6.slice(0, this.showLength6);
        }else{
          this.showMore6 = false;
          this.questionList6 = this.allQuestionList6;
        }
        this.showLength7+=15
        if(this.allQuestionList7.length > this.showLength7){
          this.showMore7 = true;
          this.questionList7 = this.allQuestionList7.slice(0, this.showLength7);
        }else{
          this.showMore7 = false;
          this.questionList7 = this.allQuestionList7;
        }
      }
      if(this.tabList.length==8){
        this.showLength+=15
        if(this.allQuestionList.length > this.showLength){
          this.showMore = true;
          this.questionList = this.allQuestionList.slice(0, this.showLength);
        }else{
          this.showMore = false;
          this.questionList = this.allQuestionList;
        }
        this.showLength2+=15
        if(this.allQuestionList2.length > this.showLength2){
          this.showMore2 = true;
          this.questionList2 = this.allQuestionList2.slice(0, this.showLength2);
        }else{
          this.showMore2 = false;
          this.questionList2 = this.allQuestionList2;
        }
         this.showLength3+=15
        if(this.allQuestionList3.length > this.showLength3){
          this.showMore3 = true;
          this.questionList3 = this.allQuestionList3.slice(0, this.showLength3);
        }else{
          this.showMore3 = false;
          this.questionList3 = this.allQuestionList3;
        }
         this.showLength4+=15
        if(this.allQuestionList4.length > this.showLength4){
          this.showMore4 = true;
          this.questionList4 = this.allQuestionList4.slice(0, this.showLength4);
        }else{
          this.showMore4 = false;
          this.questionList4 = this.allQuestionList4;
        }
         this.showLength5+=15
        if(this.allQuestionList5.length > this.showLength5){
          this.showMore5 = true;
          this.questionList5 = this.allQuestionList5.slice(0, this.showLength5);
        }else{
          this.showMore5 = false;
          this.questionList5 = this.allQuestionList5;
        }
         this.showLength6+=15
        if(this.allQuestionList6.length > this.showLength6){
          this.showMore6 = true;
          this.questionList6 = this.allQuestionList6.slice(0, this.showLength6);
        }else{
          this.showMore6 = false;
          this.questionList6 = this.allQuestionList6;
        }
        this.showLength7+=15
        if(this.allQuestionList7.length > this.showLength7){
          this.showMore7 = true;
          this.questionList7 = this.allQuestionList7.slice(0, this.showLength7);
        }else{
          this.showMore7 = false;
          this.questionList7 = this.allQuestionList7;
        }
        this.showLength8+=15
        if(this.allQuestionList8.length > this.showLength8){
          this.showMore8 = true;
          this.questionList8 = this.allQuestionList8.slice(0, this.showLength8);
        }else{
          this.showMore8 = false;
          this.questionList8 = this.allQuestionList8;
        }
      }
      if(this.tabList.length==9){
        this.showLength+=15
        if(this.allQuestionList.length > this.showLength){
          this.showMore = true;
          this.questionList = this.allQuestionList.slice(0, this.showLength);
        }else{
          this.showMore = false;
          this.questionList = this.allQuestionList;
        }
        this.showLength2+=15
        if(this.allQuestionList2.length > this.showLength2){
          this.showMore2 = true;
          this.questionList2 = this.allQuestionList2.slice(0, this.showLength2);
        }else{
          this.showMore2 = false;
          this.questionList2 = this.allQuestionList2;
        }
         this.showLength3+=15
        if(this.allQuestionList3.length > this.showLength3){
          this.showMore3 = true;
          this.questionList3 = this.allQuestionList3.slice(0, this.showLength3);
        }else{
          this.showMore3 = false;
          this.questionList3 = this.allQuestionList3;
        }
         this.showLength4+=15
        if(this.allQuestionList4.length > this.showLength4){
          this.showMore4 = true;
          this.questionList4 = this.allQuestionList4.slice(0, this.showLength4);
        }else{
          this.showMore4 = false;
          this.questionList4 = this.allQuestionList4;
        }
         this.showLength5+=15
        if(this.allQuestionList5.length > this.showLength5){
          this.showMore5 = true;
          this.questionList5 = this.allQuestionList5.slice(0, this.showLength5);
        }else{
          this.showMore5 = false;
          this.questionList5 = this.allQuestionList5;
        }
         this.showLength6+=15
        if(this.allQuestionList6.length > this.showLength6){
          this.showMore6 = true;
          this.questionList6 = this.allQuestionList6.slice(0, this.showLength6);
        }else{
          this.showMore6 = false;
          this.questionList6 = this.allQuestionList6;
        }
        this.showLength7+=15
        if(this.allQuestionList7.length > this.showLength7){
          this.showMore7 = true;
          this.questionList7 = this.allQuestionList7.slice(0, this.showLength7);
        }else{
          this.showMore7 = false;
          this.questionList7 = this.allQuestionList7;
        }
        this.showLength8+=15
        if(this.allQuestionList8.length > this.showLength8){
          this.showMore8 = true;
          this.questionList8 = this.allQuestionList8.slice(0, this.showLength8);
        }else{
          this.showMore8 = false;
          this.questionList8 = this.allQuestionList8;
        }
        this.showLength9+=15
        if(this.allQuestionList9.length > this.showLength9){
          this.showMore9 = true;
          this.questionList9 = this.allQuestionList9.slice(0, this.showLength9);
        }else{
          this.showMore9 = false;
          this.questionList9 = this.allQuestionList9;
        }
      }
      if(this.tabList.length==10){
        this.showLength+=15
        if(this.allQuestionList.length > this.showLength){
          this.showMore = true;
          this.questionList = this.allQuestionList.slice(0, this.showLength);
        }else{
          this.showMore = false;
          this.questionList = this.allQuestionList;
        }
        this.showLength2+=15
        if(this.allQuestionList2.length > this.showLength2){
          this.showMore2 = true;
          this.questionList2 = this.allQuestionList2.slice(0, this.showLength2);
        }else{
          this.showMore2 = false;
          this.questionList2 = this.allQuestionList2;
        }
         this.showLength3+=15
        if(this.allQuestionList3.length > this.showLength3){
          this.showMore3 = true;
          this.questionList3 = this.allQuestionList3.slice(0, this.showLength3);
        }else{
          this.showMore3 = false;
          this.questionList3 = this.allQuestionList3;
        }
         this.showLength4+=15
        if(this.allQuestionList4.length > this.showLength4){
          this.showMore4 = true;
          this.questionList4 = this.allQuestionList4.slice(0, this.showLength4);
        }else{
          this.showMore4 = false;
          this.questionList4 = this.allQuestionList4;
        }
         this.showLength5+=15
        if(this.allQuestionList5.length > this.showLength5){
          this.showMore5 = true;
          this.questionList5 = this.allQuestionList5.slice(0, this.showLength5);
        }else{
          this.showMore5 = false;
          this.questionList5 = this.allQuestionList5;
        }
         this.showLength6+=15
        if(this.allQuestionList6.length > this.showLength6){
          this.showMore6 = true;
          this.questionList6 = this.allQuestionList6.slice(0, this.showLength6);
        }else{
          this.showMore6 = false;
          this.questionList6 = this.allQuestionList6;
        }
        this.showLength7+=15
        if(this.allQuestionList7.length > this.showLength7){
          this.showMore7 = true;
          this.questionList7 = this.allQuestionList7.slice(0, this.showLength7);
        }else{
          this.showMore7 = false;
          this.questionList7 = this.allQuestionList7;
        }
        this.showLength8+=15
        if(this.allQuestionList8.length > this.showLength8){
          this.showMore8 = true;
          this.questionList8 = this.allQuestionList8.slice(0, this.showLength8);
        }else{
          this.showMore8 = false;
          this.questionList8 = this.allQuestionList8;
        }
        this.showLength9+=15
        if(this.allQuestionList9.length > this.showLength9){
          this.showMore9 = true;
          this.questionList9 = this.allQuestionList9.slice(0, this.showLength9);
        }else{
          this.showMore9 = false;
          this.questionList9 = this.allQuestionList9;
        }
        this.showLength10+=15
        if(this.allQuestionList10.length > this.showLength10){
          this.showMore10 = true;
          this.questionList10 = this.allQuestionList10.slice(0, this.showLength10);
        }else{
          this.showMore10 = false;
          this.questionList10 = this.allQuestionList10;
        }
      }
    },
    // 获取单选按钮数据
    initTableData() {
      // 动态传id
      const param = {
        id: this.caseId
      };
      getSelectFeedbackList(param).then(res => {

        if (res.data.result && res.data.result.length > 0) {
          this.o_letterId = res.data.result[0].letterId;
          this.tabList = res.data.result;
          console.log('反馈意见', this.tabList)
          this.activeName = this.tabList[0].letterId;
          // 第一个tab
          if (this.tabList && this.tabList.length == 1) {
            this.allQuestionList = res.data.result[0].questionList;
            this.questionCount = res.data.result[0].questionCount;
            this.answerCount = res.data.result[0].answerCount;
            if (this.allQuestionList.length > 15) {
              this.showMore = true;
              this.questionList = this.allQuestionList.slice(0, 15);
            } else {
              this.showMore = false;
              this.questionList = this.allQuestionList;
            }
          }
          // 第二个tab
          if (this.tabList && this.tabList.length == 2) {
            this.allQuestionList = res.data.result[0].questionList;
            this.allQuestionList2 = res.data.result[1].questionList;
            this.questionCount = res.data.result[0].questionCount;
            this.answerCount = res.data.result[0].answerCount;
            this.questionCount2 = res.data.result[1].questionCount;
            this.answerCount2 = res.data.result[1].answerCount;
            if (this.allQuestionList.length > 15) {
              this.showMore = true;
              this.questionList = this.allQuestionList.slice(0, 15);
            } else {
              this.showMore = false;
              this.questionList = this.allQuestionList;
            }
            if (this.allQuestionList2.length > 15) {
              this.showMore2 = true;
              this.questionList2 = this.allQuestionList2.slice(0, 15);
            } else {
              this.showMore2 = false;
              this.questionList2 = this.allQuestionList2;
            }
          }
          // 第三个tab
          if (this.tabList && this.tabList.length == 3) {
            this.allQuestionList = res.data.result[0].questionList;
            this.allQuestionList2 = res.data.result[1].questionList;
            this.allQuestionList3 = res.data.result[2].questionList;
            this.questionCount = res.data.result[0].questionCount;
            this.questionCount2 = res.data.result[1].questionCount;
            this.questionCount3 = res.data.result[2].questionCount;
            this.answerCount = res.data.result[0].answerCount;
            this.answerCount2 = res.data.result[1].answerCount;
            this.answerCount3 = res.data.result[2].answerCount;
            if (this.allQuestionList.length > 15) {
              this.showMore = true;
              this.questionList = this.allQuestionList.slice(0, 15);
            } else {
              this.showMore = false;
              this.questionList = this.allQuestionList;
            }
            if (this.allQuestionList2.length > 15) {
              this.showMore2 = true;
              this.questionList2 = this.allQuestionList2.slice(0, 15);
            } else {
              this.showMore2 = false;
              this.questionList2 = this.allQuestionList2;
            }
            if (this.allQuestionList3.length > 15) {
              this.showMore3 = true;
              this.questionList3 = this.allQuestionList3.slice(0, 15);
            } else {
              this.showMore3 = false;
              this.questionList3 = this.allQuestionList3;
            }
          }
          // 第四个tab
          if (this.tabList && this.tabList.length == 4) {
            this.allQuestionList = res.data.result[0].questionList;
            this.allQuestionList2 = res.data.result[1].questionList;
            this.allQuestionList3 = res.data.result[2].questionList;
            this.allQuestionList4 = res.data.result[3].questionList;
            this.questionCount = res.data.result[0].questionCount;
            this.questionCount2 = res.data.result[1].questionCount;
            this.questionCount3 = res.data.result[2].questionCount;
            this.questionCount4 = res.data.result[3].questionCount;
            this.answerCount = res.data.result[0].answerCount;
            this.answerCount2 = res.data.result[1].answerCount;
            this.answerCount3 = res.data.result[2].answerCount;
            this.answerCount4 = res.data.result[3].answerCount;
            if (this.allQuestionList.length > 15) {
              this.showMore = true;
              this.questionList = this.allQuestionList.slice(0, 15);
            } else {
              this.showMore = false;
              this.questionList = this.allQuestionList;
            }
            if (this.allQuestionList2.length > 15) {
              this.showMore2 = true;
              this.questionList2 = this.allQuestionList2.slice(0, 15);
            } else {
              this.showMore2 = false;
              this.questionList2 = this.allQuestionList2;
            }
            if (this.allQuestionList3.length > 15) {
              this.showMore3 = true;
              this.questionList3 = this.allQuestionList3.slice(0, 15);
            } else {
              this.showMore3 = false;
              this.questionList3 = this.allQuestionList3;
            }
            if (this.allQuestionList4.length > 15) {
              this.showMore4 = true;
              this.questionList4 = this.allQuestionList4.slice(0, 15);
            } else {
              this.showMore4 = false;
              this.questionList4 = this.allQuestionList4;
            }
          }
           // 第五个tab
          if (this.tabList && this.tabList.length == 5) {
            this.allQuestionList = res.data.result[0].questionList;
            this.allQuestionList2 = res.data.result[1].questionList;
            this.allQuestionList3 = res.data.result[2].questionList;
            this.allQuestionList4 = res.data.result[3].questionList;
            this.allQuestionList5 = res.data.result[4].questionList;
            this.questionCount = res.data.result[0].questionCount;
            this.questionCount2 = res.data.result[1].questionCount;
            this.questionCount3 = res.data.result[2].questionCount;
            this.questionCount4 = res.data.result[3].questionCount;
            this.questionCount5 = res.data.result[4].questionCount;
            this.answerCount = res.data.result[0].answerCount;
            this.answerCount2 = res.data.result[1].answerCount;
            this.answerCount3 = res.data.result[2].answerCount;
            this.answerCount4 = res.data.result[3].answerCount;
            this.answerCount5 = res.data.result[4].answerCount;
            if (this.allQuestionList.length > 15) {
              this.showMore = true;
              this.questionList = this.allQuestionList.slice(0, 15);
            } else {
              this.showMore = false;
              this.questionList = this.allQuestionList;
            }
            if (this.allQuestionList2.length > 15) {
              this.showMore2 = true;
              this.questionList2 = this.allQuestionList2.slice(0, 15);
            } else {
              this.showMore2 = false;
              this.questionList2 = this.allQuestionList2;
            }
            if (this.allQuestionList3.length > 15) {
              this.showMore3 = true;
              this.questionList3 = this.allQuestionList3.slice(0, 15);
            } else {
              this.showMore3 = false;
              this.questionList3 = this.allQuestionList3;
            }
            if (this.allQuestionList4.length > 15) {
              this.showMore4 = true;
              this.questionList4 = this.allQuestionList4.slice(0, 15);
            } else {
              this.showMore4 = false;
              this.questionList4 = this.allQuestionList4;
            }
            if (this.allQuestionList5.length > 15) {
              this.showMore5 = true;
              this.questionList5 = this.allQuestionList5.slice(0, 15);
            } else {
              this.showMore5 = false;
              this.questionList5 = this.allQuestionList5;
            }
          }
           // 第六个tab
          if (this.tabList && this.tabList.length == 6) {
            this.allQuestionList = res.data.result[0].questionList;
            this.allQuestionList2 = res.data.result[1].questionList;
            this.allQuestionList3 = res.data.result[2].questionList;
            this.allQuestionList4 = res.data.result[3].questionList;
            this.allQuestionList5 = res.data.result[4].questionList;
            this.allQuestionList6 = res.data.result[5].questionList;
            this.questionCount = res.data.result[0].questionCount;
            this.questionCount2 = res.data.result[1].questionCount;
            this.questionCount3 = res.data.result[2].questionCount;
            this.questionCount4 = res.data.result[3].questionCount;
            this.questionCount5 = res.data.result[4].questionCount;
            this.questionCount6 = res.data.result[5].questionCount;
            this.answerCount = res.data.result[0].answerCount;
            this.answerCount2 = res.data.result[1].answerCount;
            this.answerCount3 = res.data.result[2].answerCount;
            this.answerCount4 = res.data.result[3].answerCount;
            this.answerCount5 = res.data.result[4].answerCount;
            this.answerCount6 = res.data.result[5].answerCount;
            if (this.allQuestionList.length > 15) {
              this.showMore = true;
              this.questionList = this.allQuestionList.slice(0, 15);
            } else {
              this.showMore = false;
              this.questionList = this.allQuestionList;
            }
            if (this.allQuestionList2.length > 15) {
              this.showMore2 = true;
              this.questionList2 = this.allQuestionList2.slice(0, 15);
            } else {
              this.showMore2 = false;
              this.questionList2 = this.allQuestionList2;
            }
            if (this.allQuestionList3.length > 15) {
              this.showMore3 = true;
              this.questionList3 = this.allQuestionList3.slice(0, 15);
            } else {
              this.showMore3 = false;
              this.questionList3 = this.allQuestionList3;
            }
            if (this.allQuestionList4.length > 15) {
              this.showMore4 = true;
              this.questionList4 = this.allQuestionList4.slice(0, 15);
            } else {
              this.showMore4 = false;
              this.questionList4 = this.allQuestionList4;
            }
            if (this.allQuestionList5.length > 15) {
              this.showMore5 = true;
              this.questionList5 = this.allQuestionList5.slice(0, 15);
            } else {
              this.showMore5 = false;
              this.questionList5 = this.allQuestionList5;
            }
            if (this.allQuestionList6.length > 15) {
              this.showMore6 = true;
              this.questionList6 = this.allQuestionList6.slice(0, 15);
            } else {
              this.showMore6 = false;
              this.questionList6 = this.allQuestionList6;
            }
          }
           // 第七个tab
          if (this.tabList && this.tabList.length == 7) {
            this.allQuestionList = res.data.result[0].questionList;
            this.allQuestionList2 = res.data.result[1].questionList;
            this.allQuestionList3 = res.data.result[2].questionList;
            this.allQuestionList4 = res.data.result[3].questionList;
            this.allQuestionList5 = res.data.result[4].questionList;
            this.allQuestionList6 = res.data.result[5].questionList;
            this.allQuestionList7 = res.data.result[6].questionList;
            this.questionCount = res.data.result[0].questionCount;
            this.questionCount2 = res.data.result[1].questionCount;
            this.questionCount3 = res.data.result[2].questionCount;
            this.questionCount4 = res.data.result[3].questionCount;
            this.questionCount5 = res.data.result[4].questionCount;
            this.questionCount6 = res.data.result[5].questionCount;
            this.questionCount7 = res.data.result[6].questionCount;
            this.answerCount = res.data.result[0].answerCount;
            this.answerCount2 = res.data.result[1].answerCount;
            this.answerCount3 = res.data.result[2].answerCount;
            this.answerCount4 = res.data.result[3].answerCount;
            this.answerCount5 = res.data.result[4].answerCount;
            this.answerCount6 = res.data.result[5].answerCount;
            this.answerCount7 = res.data.result[6].answerCount;
            if (this.allQuestionList.length > 15) {
              this.showMore = true;
              this.questionList = this.allQuestionList.slice(0, 15);
            } else {
              this.showMore = false;
              this.questionList = this.allQuestionList;
            }
            if (this.allQuestionList2.length > 15) {
              this.showMore2 = true;
              this.questionList2 = this.allQuestionList2.slice(0, 15);
            } else {
              this.showMore2 = false;
              this.questionList2 = this.allQuestionList2;
            }
            if (this.allQuestionList3.length > 15) {
              this.showMore3 = true;
              this.questionList3 = this.allQuestionList3.slice(0, 15);
            } else {
              this.showMore3 = false;
              this.questionList3 = this.allQuestionList3;
            }
            if (this.allQuestionList4.length > 15) {
              this.showMore4 = true;
              this.questionList4 = this.allQuestionList4.slice(0, 15);
            } else {
              this.showMore4 = false;
              this.questionList4 = this.allQuestionList4;
            }
            if (this.allQuestionList5.length > 15) {
              this.showMore5 = true;
              this.questionList5 = this.allQuestionList5.slice(0, 15);
            } else {
              this.showMore5 = false;
              this.questionList5 = this.allQuestionList5;
            }
            if (this.allQuestionList6.length > 15) {
              this.showMore6 = true;
              this.questionList6 = this.allQuestionList6.slice(0, 15);
            } else {
              this.showMore6 = false;
              this.questionList6 = this.allQuestionList6;
            }
            if (this.allQuestionList7.length > 15) {
              this.showMore7 = true;
              this.questionList7 = this.allQuestionList7.slice(0, 15);
            } else {
              this.showMore7 = false;
              this.questionList7 = this.allQuestionList7;
            }
          }
          // 第八个tab
          if (this.tabList && this.tabList.length == 8) {
            this.allQuestionList = res.data.result[0].questionList;
            this.allQuestionList2 = res.data.result[1].questionList;
            this.allQuestionList3 = res.data.result[2].questionList;
            this.allQuestionList4 = res.data.result[3].questionList;
            this.allQuestionList5 = res.data.result[4].questionList;
            this.allQuestionList6 = res.data.result[5].questionList;
            this.allQuestionList7 = res.data.result[6].questionList;
             this.allQuestionList8 = res.data.result[7].questionList;
            this.questionCount = res.data.result[0].questionCount;
            this.questionCount2 = res.data.result[1].questionCount;
            this.questionCount3 = res.data.result[2].questionCount;
            this.questionCount4 = res.data.result[3].questionCount;
            this.questionCount5 = res.data.result[4].questionCount;
            this.questionCount6 = res.data.result[5].questionCount;
            this.questionCount7 = res.data.result[6].questionCount;
            this.questionCount8 = res.data.result[7].questionCount;
            this.answerCount = res.data.result[0].answerCount;
            this.answerCount2 = res.data.result[1].answerCount;
            this.answerCount3 = res.data.result[2].answerCount;
            this.answerCount4 = res.data.result[3].answerCount;
            this.answerCount5 = res.data.result[4].answerCount;
            this.answerCount6 = res.data.result[5].answerCount;
            this.answerCount7 = res.data.result[6].answerCount;
            this.answerCount8 = res.data.result[7].answerCount;
            if (this.allQuestionList.length > 15) {
              this.showMore = true;
              this.questionList = this.allQuestionList.slice(0, 15);
            } else {
              this.showMore = false;
              this.questionList = this.allQuestionList;
            }
            if (this.allQuestionList2.length > 15) {
              this.showMore2 = true;
              this.questionList2 = this.allQuestionList2.slice(0, 15);
            } else {
              this.showMore2 = false;
              this.questionList2 = this.allQuestionList2;
            }
            if (this.allQuestionList3.length > 15) {
              this.showMore3 = true;
              this.questionList3 = this.allQuestionList3.slice(0, 15);
            } else {
              this.showMore3 = false;
              this.questionList3 = this.allQuestionList3;
            }
            if (this.allQuestionList4.length > 15) {
              this.showMore4 = true;
              this.questionList4 = this.allQuestionList4.slice(0, 15);
            } else {
              this.showMore4 = false;
              this.questionList4 = this.allQuestionList4;
            }
            if (this.allQuestionList5.length > 15) {
              this.showMore5 = true;
              this.questionList5 = this.allQuestionList5.slice(0, 15);
            } else {
              this.showMore5 = false;
              this.questionList5 = this.allQuestionList5;
            }
            if (this.allQuestionList6.length > 15) {
              this.showMore6 = true;
              this.questionList6 = this.allQuestionList6.slice(0, 15);
            } else {
              this.showMore6 = false;
              this.questionList6 = this.allQuestionList6;
            }
            if (this.allQuestionList7.length > 15) {
              this.showMore7 = true;
              this.questionList7 = this.allQuestionList7.slice(0, 15);
            } else {
              this.showMore7 = false;
              this.questionList7 = this.allQuestionList7;
            }
            if (this.allQuestionList8.length > 15) {
              this.showMore8 = true;
              this.questionList8 = this.allQuestionList8.slice(0, 15);
            } else {
              this.showMore8 = false;
              this.questionList8 = this.allQuestionList8;
            }
          }
            // 第九个tab
          if (this.tabList && this.tabList.length == 9) {
            this.allQuestionList = res.data.result[0].questionList;
            this.allQuestionList2 = res.data.result[1].questionList;
            this.allQuestionList3 = res.data.result[2].questionList;
            this.allQuestionList4 = res.data.result[3].questionList;
            this.allQuestionList5 = res.data.result[4].questionList;
            this.allQuestionList6 = res.data.result[5].questionList;
            this.allQuestionList7 = res.data.result[6].questionList;
            this.allQuestionList8 = res.data.result[7].questionList;
            this.allQuestionList9 = res.data.result[8].questionList;
            this.questionCount = res.data.result[0].questionCount;
            this.questionCount2 = res.data.result[1].questionCount;
            this.questionCount3 = res.data.result[2].questionCount;
            this.questionCount4 = res.data.result[3].questionCount;
            this.questionCount5 = res.data.result[4].questionCount;
            this.questionCount6 = res.data.result[5].questionCount;
            this.questionCount7 = res.data.result[6].questionCount;
            this.questionCount8 = res.data.result[7].questionCount;
            this.questionCount9 = res.data.result[8].questionCount;
            this.answerCount = res.data.result[0].answerCount;
            this.answerCount2 = res.data.result[1].answerCount;
            this.answerCount3 = res.data.result[2].answerCount;
            this.answerCount4 = res.data.result[3].answerCount;
            this.answerCount5 = res.data.result[4].answerCount;
            this.answerCount6 = res.data.result[5].answerCount;
            this.answerCount7 = res.data.result[6].answerCount;
            this.answerCount8 = res.data.result[7].answerCount;
            this.answerCount9 = res.data.result[8].answerCount;
            if (this.allQuestionList.length > 15) {
              this.showMore = true;
              this.questionList = this.allQuestionList.slice(0, 15);
            } else {
              this.showMore = false;
              this.questionList = this.allQuestionList;
            }
            if (this.allQuestionList2.length > 15) {
              this.showMore2 = true;
              this.questionList2 = this.allQuestionList2.slice(0, 15);
            } else {
              this.showMore2 = false;
              this.questionList2 = this.allQuestionList2;
            }
            if (this.allQuestionList3.length > 15) {
              this.showMore3 = true;
              this.questionList3 = this.allQuestionList3.slice(0, 15);
            } else {
              this.showMore3 = false;
              this.questionList3 = this.allQuestionList3;
            }
            if (this.allQuestionList4.length > 15) {
              this.showMore4 = true;
              this.questionList4 = this.allQuestionList4.slice(0, 15);
            } else {
              this.showMore4 = false;
              this.questionList4 = this.allQuestionList4;
            }
            if (this.allQuestionList5.length > 15) {
              this.showMore5 = true;
              this.questionList5 = this.allQuestionList5.slice(0, 15);
            } else {
              this.showMore5 = false;
              this.questionList5 = this.allQuestionList5;
            }
            if (this.allQuestionList6.length > 15) {
              this.showMore6 = true;
              this.questionList6 = this.allQuestionList6.slice(0, 15);
            } else {
              this.showMore6 = false;
              this.questionList6 = this.allQuestionList6;
            }
            if (this.allQuestionList7.length > 15) {
              this.showMore7 = true;
              this.questionList7 = this.allQuestionList7.slice(0, 15);
            } else {
              this.showMore7 = false;
              this.questionList7 = this.allQuestionList7;
            }
            if (this.allQuestionList8.length > 15) {
              this.showMore8 = true;
              this.questionList8 = this.allQuestionList8.slice(0, 15);
            } else {
              this.showMore8 = false;
              this.questionList8 = this.allQuestionList8;
            }
            if (this.allQuestionList9.length > 15) {
              this.showMore9 = true;
              this.questionList9 = this.allQuestionList9.slice(0, 15);
            } else {
              this.showMore9 = false;
              this.questionList9 = this.allQuestionList9;
            }
          }
             // 第十个tab
          if (this.tabList && this.tabList.length == 10) {
            this.allQuestionList = res.data.result[0].questionList;
            this.allQuestionList2 = res.data.result[1].questionList;
            this.allQuestionList3 = res.data.result[2].questionList;
            this.allQuestionList4 = res.data.result[3].questionList;
            this.allQuestionList5 = res.data.result[4].questionList;
            this.allQuestionList6 = res.data.result[5].questionList;
            this.allQuestionList7 = res.data.result[6].questionList;
            this.allQuestionList8 = res.data.result[7].questionList;
            this.allQuestionList9 = res.data.result[8].questionList;
            this.allQuestionList10 = res.data.result[9].questionList;
            this.questionCount = res.data.result[0].questionCount;
            this.questionCount2 = res.data.result[1].questionCount;
            this.questionCount3 = res.data.result[2].questionCount;
            this.questionCount4 = res.data.result[3].questionCount;
            this.questionCount5 = res.data.result[4].questionCount;
            this.questionCount6 = res.data.result[5].questionCount;
            this.questionCount7 = res.data.result[6].questionCount;
            this.questionCount8 = res.data.result[7].questionCount;
            this.questionCount9 = res.data.result[8].questionCount;
            this.questionCount10 = res.data.result[9].questionCount;
            this.answerCount = res.data.result[0].answerCount;
            this.answerCount2 = res.data.result[1].answerCount;
            this.answerCount3 = res.data.result[2].answerCount;
            this.answerCount4 = res.data.result[3].answerCount;
            this.answerCount5 = res.data.result[4].answerCount;
            this.answerCount6 = res.data.result[5].answerCount;
            this.answerCount7 = res.data.result[6].answerCount;
            this.answerCount8 = res.data.result[7].answerCount;
            this.answerCount9 = res.data.result[8].answerCount;
            this.answerCount10 = res.data.result[9].answerCount;
            if (this.allQuestionList.length > 15) {
              this.showMore = true;
              this.questionList = this.allQuestionList.slice(0, 15);
            } else {
              this.showMore = false;
              this.questionList = this.allQuestionList;
            }
            if (this.allQuestionList2.length > 15) {
              this.showMore2 = true;
              this.questionList2 = this.allQuestionList2.slice(0, 15);
            } else {
              this.showMore2 = false;
              this.questionList2 = this.allQuestionList2;
            }
            if (this.allQuestionList3.length > 15) {
              this.showMore3 = true;
              this.questionList3 = this.allQuestionList3.slice(0, 15);
            } else {
              this.showMore3 = false;
              this.questionList3 = this.allQuestionList3;
            }
            if (this.allQuestionList4.length > 15) {
              this.showMore4 = true;
              this.questionList4 = this.allQuestionList4.slice(0, 15);
            } else {
              this.showMore4 = false;
              this.questionList4 = this.allQuestionList4;
            }
            if (this.allQuestionList5.length > 15) {
              this.showMore5 = true;
              this.questionList5 = this.allQuestionList5.slice(0, 15);
            } else {
              this.showMore5 = false;
              this.questionList5 = this.allQuestionList5;
            }
            if (this.allQuestionList6.length > 15) {
              this.showMore6 = true;
              this.questionList6 = this.allQuestionList6.slice(0, 15);
            } else {
              this.showMore6 = false;
              this.questionList6 = this.allQuestionList6;
            }
            if (this.allQuestionList7.length > 15) {
              this.showMore7 = true;
              this.questionList7 = this.allQuestionList7.slice(0, 15);
            } else {
              this.showMore7 = false;
              this.questionList7 = this.allQuestionList7;
            }
            if (this.allQuestionList8.length > 15) {
              this.showMore8 = true;
              this.questionList8 = this.allQuestionList8.slice(0, 15);
            } else {
              this.showMore8 = false;
              this.questionList8 = this.allQuestionList8;
            }
            if (this.allQuestionList9.length > 15) {
              this.showMore9 = true;
              this.questionList9 = this.allQuestionList9.slice(0, 15);
            } else {
              this.showMore9 = false;
              this.questionList9 = this.allQuestionList9;
            }
             if (this.allQuestionList10.length > 15) {
              this.showMore10 = true;
              this.questionList10 = this.allQuestionList10.slice(0, 15);
            } else {
              this.showMore10 = false;
              this.questionList10 = this.allQuestionList10;
            }
          }
        }
      });
    },
    // 获取筛选二级标签和问题列表
    initQuestionData(
      letterId,
      firstLabelId,
      secondLabelId,
      onlyResponse,
      ifReset
    ) {
      // 动态传id
      // 将second多选按钮参数用字符串，隔开
      let secondLabel = "";
      for (let i = 0; i < secondLabelId.length; i++) {
        secondLabel = secondLabelId[i] + "," + secondLabel;
      }
      // 将second多选按钮参数最后一个  ，号去掉
      if (secondLabel && secondLabel.length > 0) {
        secondLabel = secondLabel.substring(0, secondLabel.length - 1);
      }
      const param = {
        letterId: letterId,
        firstLabelId: firstLabelId,
        secondLabelId: secondLabel,
        onlyResponse: onlyResponse
      };
      getSelectQuestionList(param).then(res => {
        // 当只有一个tab页时
        if (this.tabList.length == 1) {
          if (res.data.result.length > 0) {
            this.allQuestionList = res.data.result[0].questionList;
            this.questionCount = res.data.result[0].questionCount;
            this.answerCount = res.data.result[0].answerCount;
            if (ifReset != "0") {
              this.feedbackduoxuanList = res.data.result[0].questionLabelList;
            }
            if (this.allQuestionList.length > 15) {
              this.showMore = true;
              this.questionList = this.allQuestionList.slice(0, 15);
            } else {
              this.showMore = false;
              this.questionList = this.allQuestionList;
            }
          } else {
            this.showMore = false;
            this.questionList = [];
          }
        }
        // 当有2个tab页时
        if (this.tabList.length == 2) {
          if (param.letterId == this.tabList[0].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount;
              this.answerCount = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList = res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList.length > 15) {
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0, 15);
              } else {
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            } else {
              this.showMore = false;
              this.questionList = [];
            }
          }
          if (param.letterId == this.tabList[1].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList2 = res.data.result[0].questionList;
              this.questionCount2 = res.data.result[0].questionCount;
              this.answerCount2 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList2 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList2.length > 15) {
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0, 15);
              } else {
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            } else {
              this.showMore2 = false;
              this.questionList2 = [];
            }
          }
        }
        // 当有3个tab页时
        if (this.tabList.length == 3) {
          if (param.letterId == this.tabList[0].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount;
              this.answerCount = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList = res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList.length > 15) {
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0, 15);
              } else {
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            } else {
              this.showMore = false;
              this.questionList = [];
            }
          }
          if (param.letterId == this.tabList[1].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList2 = res.data.result[0].questionList;
              this.questionCount2 = res.data.result[0].questionCount;
              this.answerCount2 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList2 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList2.length > 15) {
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0, 15);
              } else {
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            } else {
              this.showMore2 = false;
              this.questionList2 = [];
            }
          }
          if (param.letterId == this.tabList[2].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList3 = res.data.result[0].questionList;
              this.questionCount3 = res.data.result[0].questionCount;
              this.answerCount3 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList3 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList3.length > 15) {
                this.showMore3 = true;
                this.questionList3 = this.allQuestionList3.slice(0, 15);
              } else {
                this.showMore3 = false;
                this.questionList3 = this.allQuestionList3;
              }
            } else {
              this.showMore3 = false;
              this.questionList3 = [];
            }
          }
        }
                // 当有4个tab页时
        if (this.tabList.length == 4) {
          if (param.letterId == this.tabList[0].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount;
              this.answerCount = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList = res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList.length > 15) {
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0, 15);
              } else {
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            } else {
              this.showMore = false;
              this.questionList = [];
            }
          }
          if (param.letterId == this.tabList[1].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList2 = res.data.result[0].questionList;
              this.questionCount2 = res.data.result[0].questionCount;
              this.answerCount2 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList2 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList2.length > 15) {
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0, 15);
              } else {
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            } else {
              this.showMore2 = false;
              this.questionList2 = [];
            }
          }
          if (param.letterId == this.tabList[2].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList3 = res.data.result[0].questionList;
              this.questionCount3 = res.data.result[0].questionCount;
              this.answerCount3 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList3 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList3.length > 15) {
                this.showMore3 = true;
                this.questionList3 = this.allQuestionList3.slice(0, 15);
              } else {
                this.showMore3 = false;
                this.questionList3 = this.allQuestionList3;
              }
            } else {
              this.showMore3 = false;
              this.questionList3 = [];
            }
          }
          if (param.letterId == this.tabList[3].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList4 = res.data.result[0].questionList;
              this.questionCount4 = res.data.result[0].questionCount;
              this.answerCount4 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList4 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList4.length > 15) {
                this.showMore4 = true;
                this.questionList4 = this.allQuestionList4.slice(0, 15);
              } else {
                this.showMore4 = false;
                this.questionList4 = this.allQuestionList4;
              }
            } else {
              this.showMore4 = false;
              this.questionList4 = [];
            }
          }
        }
             // 当有5个tab页时
        if (this.tabList.length == 5) {
          if (param.letterId == this.tabList[0].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount;
              this.answerCount = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList = res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList.length > 15) {
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0, 15);
              } else {
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            } else {
              this.showMore = false;
              this.questionList = [];
            }
          }
          if (param.letterId == this.tabList[1].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList2 = res.data.result[0].questionList;
              this.questionCount2 = res.data.result[0].questionCount;
              this.answerCount2 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList2 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList2.length > 15) {
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0, 15);
              } else {
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            } else {
              this.showMore2 = false;
              this.questionList2 = [];
            }
          }
          if (param.letterId == this.tabList[2].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList3 = res.data.result[0].questionList;
              this.questionCount3 = res.data.result[0].questionCount;
              this.answerCount3 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList3 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList3.length > 15) {
                this.showMore3 = true;
                this.questionList3 = this.allQuestionList3.slice(0, 15);
              } else {
                this.showMore3 = false;
                this.questionList3 = this.allQuestionList3;
              }
            } else {
              this.showMore3 = false;
              this.questionList3 = [];
            }
          }
          if (param.letterId == this.tabList[3].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList4 = res.data.result[0].questionList;
              this.questionCount4 = res.data.result[0].questionCount;
              this.answerCount4 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList4 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList4.length > 15) {
                this.showMore4 = true;
                this.questionList4 = this.allQuestionList4.slice(0, 15);
              } else {
                this.showMore4 = false;
                this.questionList4 = this.allQuestionList4;
              }
            } else {
              this.showMore4 = false;
              this.questionList4 = [];
            }
          }
          if (param.letterId == this.tabList[4].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList5 = res.data.result[0].questionList;
              this.questionCount5 = res.data.result[0].questionCount;
              this.answerCount5 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList5 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList5.length > 15) {
                this.showMore5 = true;
                this.questionList5 = this.allQuestionList5.slice(0, 15);
              } else {
                this.showMore5 = false;
                this.questionList5 = this.allQuestionList5;
              }
            } else {
              this.showMore5 = false;
              this.questionList5 = [];
            }
          }
        }
            // 当有6个tab页时
        if (this.tabList.length == 6) {
          if (param.letterId == this.tabList[0].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount;
              this.answerCount = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList = res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList.length > 15) {
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0, 15);
              } else {
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            } else {
              this.showMore = false;
              this.questionList = [];
            }
          }
          if (param.letterId == this.tabList[1].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList2 = res.data.result[0].questionList;
              this.questionCount2 = res.data.result[0].questionCount;
              this.answerCount2 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList2 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList2.length > 15) {
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0, 15);
              } else {
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            } else {
              this.showMore2 = false;
              this.questionList2 = [];
            }
          }
          if (param.letterId == this.tabList[2].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList3 = res.data.result[0].questionList;
              this.questionCount3 = res.data.result[0].questionCount;
              this.answerCount3 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList3 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList3.length > 15) {
                this.showMore3 = true;
                this.questionList3 = this.allQuestionList3.slice(0, 15);
              } else {
                this.showMore3 = false;
                this.questionList3 = this.allQuestionList3;
              }
            } else {
              this.showMore3 = false;
              this.questionList3 = [];
            }
          }
          if (param.letterId == this.tabList[3].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList4 = res.data.result[0].questionList;
              this.questionCount4 = res.data.result[0].questionCount;
              this.answerCount4 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList4 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList4.length > 15) {
                this.showMore4 = true;
                this.questionList4 = this.allQuestionList4.slice(0, 15);
              } else {
                this.showMore4 = false;
                this.questionList4 = this.allQuestionList4;
              }
            } else {
              this.showMore4 = false;
              this.questionList4 = [];
            }
          }
          if (param.letterId == this.tabList[4].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList5 = res.data.result[0].questionList;
              this.questionCount5 = res.data.result[0].questionCount;
              this.answerCount5 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList5 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList5.length > 15) {
                this.showMore5 = true;
                this.questionList5 = this.allQuestionList5.slice(0, 15);
              } else {
                this.showMore5 = false;
                this.questionList5 = this.allQuestionList5;
              }
            } else {
              this.showMore5 = false;
              this.questionList5 = [];
            }
          }
          if (param.letterId == this.tabList[5].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList6 = res.data.result[0].questionList;
              this.questionCount6 = res.data.result[0].questionCount;
              this.answerCount6 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList6 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList6.length > 15) {
                this.showMore6 = true;
                this.questionList6 = this.allQuestionList6.slice(0, 15);
              } else {
                this.showMore6 = false;
                this.questionList6 = this.allQuestionList6;
              }
            } else {
              this.showMore6 = false;
              this.questionList6 = [];
            }
          }
        }
            // 当有7个tab页时
        if (this.tabList.length == 7) {
          if (param.letterId == this.tabList[0].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount;
              this.answerCount = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList = res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList.length > 15) {
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0, 15);
              } else {
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            } else {
              this.showMore = false;
              this.questionList = [];
            }
          }
          if (param.letterId == this.tabList[1].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList2 = res.data.result[0].questionList;
              this.questionCount2 = res.data.result[0].questionCount;
              this.answerCount2 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList2 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList2.length > 15) {
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0, 15);
              } else {
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            } else {
              this.showMore2 = false;
              this.questionList2 = [];
            }
          }
          if (param.letterId == this.tabList[2].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList3 = res.data.result[0].questionList;
              this.questionCount3 = res.data.result[0].questionCount;
              this.answerCount3 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList3 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList3.length > 15) {
                this.showMore3 = true;
                this.questionList3 = this.allQuestionList3.slice(0, 15);
              } else {
                this.showMore3 = false;
                this.questionList3 = this.allQuestionList3;
              }
            } else {
              this.showMore3 = false;
              this.questionList3 = [];
            }
          }
          if (param.letterId == this.tabList[3].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList4 = res.data.result[0].questionList;
              this.questionCount4 = res.data.result[0].questionCount;
              this.answerCount4 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList4 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList4.length > 15) {
                this.showMore4 = true;
                this.questionList4 = this.allQuestionList4.slice(0, 15);
              } else {
                this.showMore4 = false;
                this.questionList4 = this.allQuestionList4;
              }
            } else {
              this.showMore4 = false;
              this.questionList4 = [];
            }
          }
          if (param.letterId == this.tabList[4].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList5 = res.data.result[0].questionList;
              this.questionCount5 = res.data.result[0].questionCount;
              this.answerCount5 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList5 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList5.length > 15) {
                this.showMore5 = true;
                this.questionList5 = this.allQuestionList5.slice(0, 15);
              } else {
                this.showMore5 = false;
                this.questionList5 = this.allQuestionList5;
              }
            } else {
              this.showMore5 = false;
              this.questionList5 = [];
            }
          }
          if (param.letterId == this.tabList[5].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList6 = res.data.result[0].questionList;
              this.questionCount6 = res.data.result[0].questionCount;
              this.answerCount6 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList6 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList6.length > 15) {
                this.showMore6 = true;
                this.questionList6 = this.allQuestionList6.slice(0, 15);
              } else {
                this.showMore6 = false;
                this.questionList6 = this.allQuestionList6;
              }
            } else {
              this.showMore6 = false;
              this.questionList6 = [];
            }
          }
          if (param.letterId == this.tabList[6].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList7 = res.data.result[0].questionList;
              this.questionCount7 = res.data.result[0].questionCount;
              this.answerCount7 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList7 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList7.length > 15) {
                this.showMore7 = true;
                this.questionList7 = this.allQuestionList7.slice(0, 15);
              } else {
                this.showMore7 = false;
                this.questionList7 = this.allQuestionList7;
              }
            } else {
              this.showMore7 = false;
              this.questionList7 = [];
            }
          }
        }
           // 当有8个tab页时
        if (this.tabList.length == 8) {
          if (param.letterId == this.tabList[0].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount;
              this.answerCount = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList = res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList.length > 15) {
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0, 15);
              } else {
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            } else {
              this.showMore = false;
              this.questionList = [];
            }
          }
          if (param.letterId == this.tabList[1].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList2 = res.data.result[0].questionList;
              this.questionCount2 = res.data.result[0].questionCount;
              this.answerCount2 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList2 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList2.length > 15) {
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0, 15);
              } else {
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            } else {
              this.showMore2 = false;
              this.questionList2 = [];
            }
          }
          if (param.letterId == this.tabList[2].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList3 = res.data.result[0].questionList;
              this.questionCount3 = res.data.result[0].questionCount;
              this.answerCount3 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList3 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList3.length > 15) {
                this.showMore3 = true;
                this.questionList3 = this.allQuestionList3.slice(0, 15);
              } else {
                this.showMore3 = false;
                this.questionList3 = this.allQuestionList3;
              }
            } else {
              this.showMore3 = false;
              this.questionList3 = [];
            }
          }
          if (param.letterId == this.tabList[3].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList4 = res.data.result[0].questionList;
              this.questionCount4 = res.data.result[0].questionCount;
              this.answerCount4 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList4 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList4.length > 15) {
                this.showMore4 = true;
                this.questionList4 = this.allQuestionList4.slice(0, 15);
              } else {
                this.showMore4 = false;
                this.questionList4 = this.allQuestionList4;
              }
            } else {
              this.showMore4 = false;
              this.questionList4 = [];
            }
          }
          if (param.letterId == this.tabList[4].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList5 = res.data.result[0].questionList;
              this.questionCount5 = res.data.result[0].questionCount;
              this.answerCount5 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList5 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList5.length > 15) {
                this.showMore5 = true;
                this.questionList5 = this.allQuestionList5.slice(0, 15);
              } else {
                this.showMore5 = false;
                this.questionList5 = this.allQuestionList5;
              }
            } else {
              this.showMore5 = false;
              this.questionList5 = [];
            }
          }
          if (param.letterId == this.tabList[5].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList6 = res.data.result[0].questionList;
              this.questionCount6 = res.data.result[0].questionCount;
              this.answerCount6 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList6 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList6.length > 15) {
                this.showMore6 = true;
                this.questionList6 = this.allQuestionList6.slice(0, 15);
              } else {
                this.showMore6 = false;
                this.questionList6 = this.allQuestionList6;
              }
            } else {
              this.showMore6 = false;
              this.questionList6 = [];
            }
          }
          if (param.letterId == this.tabList[6].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList7 = res.data.result[0].questionList;
              this.questionCount7 = res.data.result[0].questionCount;
              this.answerCount7 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList7 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList7.length > 15) {
                this.showMore7 = true;
                this.questionList7 = this.allQuestionList7.slice(0, 15);
              } else {
                this.showMore7 = false;
                this.questionList7 = this.allQuestionList7;
              }
            } else {
              this.showMore7 = false;
              this.questionList7 = [];
            }
          }
          if (param.letterId == this.tabList[7].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList8 = res.data.result[0].questionList;
              this.questionCount8 = res.data.result[0].questionCount;
              this.answerCount8 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList8 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList8.length > 15) {
                this.showMore8 = true;
                this.questionList8 = this.allQuestionList8.slice(0, 15);
              } else {
                this.showMore8 = false;
                this.questionList8 = this.allQuestionList8;
              }
            } else {
              this.showMore8 = false;
              this.questionList8 = [];
            }
          }
        }
         // 当有9个tab页时
        if (this.tabList.length == 9) {
          if (param.letterId == this.tabList[0].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount;
              this.answerCount = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList = res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList.length > 15) {
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0, 15);
              } else {
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            } else {
              this.showMore = false;
              this.questionList = [];
            }
          }
          if (param.letterId == this.tabList[1].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList2 = res.data.result[0].questionList;
              this.questionCount2 = res.data.result[0].questionCount;
              this.answerCount2 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList2 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList2.length > 15) {
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0, 15);
              } else {
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            } else {
              this.showMore2 = false;
              this.questionList2 = [];
            }
          }
          if (param.letterId == this.tabList[2].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList3 = res.data.result[0].questionList;
              this.questionCount3 = res.data.result[0].questionCount;
              this.answerCount3 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList3 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList3.length > 15) {
                this.showMore3 = true;
                this.questionList3 = this.allQuestionList3.slice(0, 15);
              } else {
                this.showMore3 = false;
                this.questionList3 = this.allQuestionList3;
              }
            } else {
              this.showMore3 = false;
              this.questionList3 = [];
            }
          }
          if (param.letterId == this.tabList[3].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList4 = res.data.result[0].questionList;
              this.questionCount4 = res.data.result[0].questionCount;
              this.answerCount4 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList4 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList4.length > 15) {
                this.showMore4 = true;
                this.questionList4 = this.allQuestionList4.slice(0, 15);
              } else {
                this.showMore4 = false;
                this.questionList4 = this.allQuestionList4;
              }
            } else {
              this.showMore4 = false;
              this.questionList4 = [];
            }
          }
          if (param.letterId == this.tabList[4].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList5 = res.data.result[0].questionList;
              this.questionCount5 = res.data.result[0].questionCount;
              this.answerCount5 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList5 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList5.length > 15) {
                this.showMore5 = true;
                this.questionList5 = this.allQuestionList5.slice(0, 15);
              } else {
                this.showMore5 = false;
                this.questionList5 = this.allQuestionList5;
              }
            } else {
              this.showMore5 = false;
              this.questionList5 = [];
            }
          }
          if (param.letterId == this.tabList[5].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList6 = res.data.result[0].questionList;
              this.questionCount6 = res.data.result[0].questionCount;
              this.answerCount6 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList6 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList6.length > 15) {
                this.showMore6 = true;
                this.questionList6 = this.allQuestionList6.slice(0, 15);
              } else {
                this.showMore6 = false;
                this.questionList6 = this.allQuestionList6;
              }
            } else {
              this.showMore6 = false;
              this.questionList6 = [];
            }
          }
          if (param.letterId == this.tabList[6].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList7 = res.data.result[0].questionList;
              this.questionCount7 = res.data.result[0].questionCount;
              this.answerCount7 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList7 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList7.length > 15) {
                this.showMore7 = true;
                this.questionList7 = this.allQuestionList7.slice(0, 15);
              } else {
                this.showMore7 = false;
                this.questionList7 = this.allQuestionList7;
              }
            } else {
              this.showMore7 = false;
              this.questionList7 = [];
            }
          }
          if (param.letterId == this.tabList[7].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList8 = res.data.result[0].questionList;
              this.questionCount8 = res.data.result[0].questionCount;
              this.answerCount8 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList8 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList8.length > 15) {
                this.showMore8 = true;
                this.questionList8 = this.allQuestionList8.slice(0, 15);
              } else {
                this.showMore8 = false;
                this.questionList8 = this.allQuestionList8;
              }
            } else {
              this.showMore8 = false;
              this.questionList8 = [];
            }
          }
          if (param.letterId == this.tabList[8].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList9 = res.data.result[0].questionList;
              this.questionCount9 = res.data.result[0].questionCount;
              this.answerCount9 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList9 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList9.length > 15) {
                this.showMore9 = true;
                this.questionList9 = this.allQuestionList9.slice(0, 15);
              } else {
                this.showMore9 = false;
                this.questionList9 = this.allQuestionList9;
              }
            } else {
              this.showMore9 = false;
              this.questionList9 = [];
            }
          }
        }
         // 当有10个tab页时
        if (this.tabList.length == 10) {
          if (param.letterId == this.tabList[0].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount;
              this.answerCount = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList = res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList.length > 15) {
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0, 15);
              } else {
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            } else {
              this.showMore = false;
              this.questionList = [];
            }
          }
          if (param.letterId == this.tabList[1].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList2 = res.data.result[0].questionList;
              this.questionCount2 = res.data.result[0].questionCount;
              this.answerCount2 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList2 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList2.length > 15) {
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0, 15);
              } else {
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            } else {
              this.showMore2 = false;
              this.questionList2 = [];
            }
          }
          if (param.letterId == this.tabList[2].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList3 = res.data.result[0].questionList;
              this.questionCount3 = res.data.result[0].questionCount;
              this.answerCount3 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList3 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList3.length > 15) {
                this.showMore3 = true;
                this.questionList3 = this.allQuestionList3.slice(0, 15);
              } else {
                this.showMore3 = false;
                this.questionList3 = this.allQuestionList3;
              }
            } else {
              this.showMore3 = false;
              this.questionList3 = [];
            }
          }
          if (param.letterId == this.tabList[3].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList4 = res.data.result[0].questionList;
              this.questionCount4 = res.data.result[0].questionCount;
              this.answerCount4 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList4 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList4.length > 15) {
                this.showMore4 = true;
                this.questionList4 = this.allQuestionList4.slice(0, 15);
              } else {
                this.showMore4 = false;
                this.questionList4 = this.allQuestionList4;
              }
            } else {
              this.showMore4 = false;
              this.questionList4 = [];
            }
          }
          if (param.letterId == this.tabList[4].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList5 = res.data.result[0].questionList;
              this.questionCount5 = res.data.result[0].questionCount;
              this.answerCount5 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList5 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList5.length > 15) {
                this.showMore5 = true;
                this.questionList5 = this.allQuestionList5.slice(0, 15);
              } else {
                this.showMore5 = false;
                this.questionList5 = this.allQuestionList5;
              }
            } else {
              this.showMore5 = false;
              this.questionList5 = [];
            }
          }
          if (param.letterId == this.tabList[5].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList6 = res.data.result[0].questionList;
              this.questionCount6 = res.data.result[0].questionCount;
              this.answerCount6 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList6 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList6.length > 15) {
                this.showMore6 = true;
                this.questionList6 = this.allQuestionList6.slice(0, 15);
              } else {
                this.showMore6 = false;
                this.questionList6 = this.allQuestionList6;
              }
            } else {
              this.showMore6 = false;
              this.questionList6 = [];
            }
          }
          if (param.letterId == this.tabList[6].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList7 = res.data.result[0].questionList;
              this.questionCount7 = res.data.result[0].questionCount;
              this.answerCount7 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList7 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList7.length > 15) {
                this.showMore7 = true;
                this.questionList7 = this.allQuestionList7.slice(0, 15);
              } else {
                this.showMore7 = false;
                this.questionList7 = this.allQuestionList7;
              }
            } else {
              this.showMore7 = false;
              this.questionList7 = [];
            }
          }
          if (param.letterId == this.tabList[7].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList8 = res.data.result[0].questionList;
              this.questionCount8 = res.data.result[0].questionCount;
              this.answerCount8 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList8 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList8.length > 15) {
                this.showMore8 = true;
                this.questionList8 = this.allQuestionList8.slice(0, 15);
              } else {
                this.showMore8 = false;
                this.questionList8 = this.allQuestionList8;
              }
            } else {
              this.showMore8 = false;
              this.questionList8 = [];
            }
          }
          if (param.letterId == this.tabList[8].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList9 = res.data.result[0].questionList;
              this.questionCount9 = res.data.result[0].questionCount;
              this.answerCount9 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList9 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList9.length > 15) {
                this.showMore9 = true;
                this.questionList9 = this.allQuestionList9.slice(0, 15);
              } else {
                this.showMore9 = false;
                this.questionList9 = this.allQuestionList9;
              }
            } else {
              this.showMore9 = false;
              this.questionList9 = [];
            }
          }
          if (param.letterId == this.tabList[9].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList10 = res.data.result[0].questionList;
              this.questionCount10 = res.data.result[0].questionCount;
              this.answerCount10 = res.data.result[0].answerCount;
              if (ifReset != "0") {
                this.feedbackduoxuanList10 =
                  res.data.result[0].questionLabelList;
              }
              if (this.allQuestionList10.length > 15) {
                this.showMore10 = true;
                this.questionList10 = this.allQuestionList10.slice(0, 15);
              } else {
                this.showMore10 = false;
                this.questionList10 = this.allQuestionList10;
              }
            } else {
              this.showMore10 = false;
              this.questionList10 = [];
            }
          }
        }
      });
    },
    // 点击二级菜单过滤出问题列表
    initOnlyQuestionData(letterId, firstLabelId, secondLabelId, onlyResponse) {

      // 动态传id
      // 将second多选按钮参数用字符串，隔开
      let secondLabel = "";
      for (let i = 0; i < secondLabelId.length; i++) {
        secondLabel = secondLabelId[i] + "," + secondLabel;
      }
      // 将second多选按钮参数最后一个  ，号去掉
      if (secondLabel && secondLabel.length > 0) {
        secondLabel = secondLabel.substring(0, secondLabel.length - 1);
      }

      const param = {
        letterId: letterId,
        firstLabelId: firstLabelId,
        secondLabelId: secondLabel,
        onlyResponse: onlyResponse
      };
      getSelectQuestionList(param).then(res => {
        // 当只有一个tab页时
        if (this.tabList.length == 1) {
          if (res.data.result.length > 0) {
            this.allQuestionList = res.data.result[0].questionList;
            this.questionCount = res.data.result[0].questionCount;
            this.answerCount = res.data.result[0].answerCount;
            if (this.allQuestionList.length > 15) {
              this.showMore = true;
              this.questionList = this.allQuestionList.slice(0, 15);
            } else {
              this.showMore = false;
              this.questionList = this.allQuestionList;
            }
          } else {
            this.showMore = false;
            this.questionList = [];
          }
        }
        // 当有2个tab页时
        if (this.tabList.length == 2) {
          if (param.letterId == this.tabList[0].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount;
              this.answerCount = res.data.result[0].answerCount;
              if (this.allQuestionList.length > 15) {
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0, 15);
              } else {
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            } else {
              this.showMore = false;
              this.questionList = [];
            }
          }
          if (param.letterId == this.tabList[1].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList2 = res.data.result[0].questionList;
              this.questionCount2 = res.data.result[0].questionCount;
              this.answerCount2 = res.data.result[0].answerCount;
              if (this.allQuestionList2.length > 15) {
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0, 15);
              } else {
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            } else {
              this.showMore2 = false;
              this.questionList2 = [];
            }
          }
        }
        // 当有3个tab页时
        if (this.tabList.length == 3) {
          if (param.letterId == this.tabList[0].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount;
              this.answerCount = res.data.result[0].answerCount;
              if (this.allQuestionList.length > 15) {
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0, 15);
              } else {
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            } else {
              this.showMore = false;
              this.questionList = [];
            }
          }
          if (param.letterId == this.tabList[1].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList2 = res.data.result[0].questionList;
              this.questionCount2 = res.data.result[0].questionCount;
              this.answerCount2 = res.data.result[0].answerCount;
              if (this.allQuestionList2.length > 15) {
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0, 15);
              } else {
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            } else {
              this.showMore2 = false;
              this.questionList2 = [];
            }
          }
          if (param.letterId == this.tabList[2].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList3 = res.data.result[0].questionList;
              this.questionCount3 = res.data.result[0].questionCount;
              this.answerCount3 = res.data.result[0].answerCount;
              if (this.allQuestionList3.length > 15) {
                this.showMore3 = true;
                this.questionList3 = this.allQuestionList3.slice(0, 15);
              } else {
                this.showMore3 = false;
                this.questionList3 = this.allQuestionList3;
              }
            } else {
              this.showMore3 = false;
              this.questionList3 = [];
            }
          }
        }
         // 当有4个tab页时
        if (this.tabList.length == 4) {
          if (param.letterId == this.tabList[0].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount;
              this.answerCount = res.data.result[0].answerCount;
              if (this.allQuestionList.length > 15) {
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0, 15);
              } else {
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            } else {
              this.showMore = false;
              this.questionList = [];
            }
          }
          if (param.letterId == this.tabList[1].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList2 = res.data.result[0].questionList;
              this.questionCount2 = res.data.result[0].questionCount;
              this.answerCount2 = res.data.result[0].answerCount;
              if (this.allQuestionList2.length > 15) {
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0, 15);
              } else {
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            } else {
              this.showMore2 = false;
              this.questionList2 = [];
            }
          }
          if (param.letterId == this.tabList[2].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList3 = res.data.result[0].questionList;
              this.questionCount3 = res.data.result[0].questionCount;
              this.answerCount3 = res.data.result[0].answerCount;
              if (this.allQuestionList3.length > 15) {
                this.showMore3 = true;
                this.questionList3 = this.allQuestionList3.slice(0, 15);
              } else {
                this.showMore3 = false;
                this.questionList3 = this.allQuestionList3;
              }
            } else {
              this.showMore3 = false;
              this.questionList3 = [];
            }
          }
          if (param.letterId == this.tabList[3].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList4 = res.data.result[0].questionList;
              this.questionCount4 = res.data.result[0].questionCount;
              this.answerCount4 = res.data.result[0].answerCount;
              if (this.allQuestionList4.length > 15) {
                this.showMore4 = true;
                this.questionList4 = this.allQuestionList4.slice(0, 15);
              } else {
                this.showMore4 = false;
                this.questionList4 = this.allQuestionList4;
              }
            } else {
              this.showMore4 = false;
              this.questionList4 = [];
            }
          }
        }
         // 当有5个tab页时
        if (this.tabList.length == 5) {
          if (param.letterId == this.tabList[0].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount;
              this.answerCount = res.data.result[0].answerCount;
              if (this.allQuestionList.length > 15) {
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0, 15);
              } else {
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            } else {
              this.showMore = false;
              this.questionList = [];
            }
          }
          if (param.letterId == this.tabList[1].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList2 = res.data.result[0].questionList;
              this.questionCount2 = res.data.result[0].questionCount;
              this.answerCount2 = res.data.result[0].answerCount;
              if (this.allQuestionList2.length > 15) {
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0, 15);
              } else {
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            } else {
              this.showMore2 = false;
              this.questionList2 = [];
            }
          }
          if (param.letterId == this.tabList[2].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList3 = res.data.result[0].questionList;
              this.questionCount3 = res.data.result[0].questionCount;
              this.answerCount3 = res.data.result[0].answerCount;
              if (this.allQuestionList3.length > 15) {
                this.showMore3 = true;
                this.questionList3 = this.allQuestionList3.slice(0, 15);
              } else {
                this.showMore3 = false;
                this.questionList3 = this.allQuestionList3;
              }
            } else {
              this.showMore3 = false;
              this.questionList3 = [];
            }
          }
          if (param.letterId == this.tabList[3].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList4 = res.data.result[0].questionList;
              this.questionCount4 = res.data.result[0].questionCount;
              this.answerCount4 = res.data.result[0].answerCount;
              if (this.allQuestionList4.length > 15) {
                this.showMore4 = true;
                this.questionList4 = this.allQuestionList4.slice(0, 15);
              } else {
                this.showMore4 = false;
                this.questionList4 = this.allQuestionList4;
              }
            } else {
              this.showMore4 = false;
              this.questionList4 = [];
            }
          }
          if (param.letterId == this.tabList[4].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList5 = res.data.result[0].questionList;
              this.questionCount5 = res.data.result[0].questionCount;
              this.answerCount5 = res.data.result[0].answerCount;
              if (this.allQuestionList5.length > 15) {
                this.showMore5 = true;
                this.questionList5 = this.allQuestionList5.slice(0, 15);
              } else {
                this.showMore5 = false;
                this.questionList5 = this.allQuestionList5;
              }
            } else {
              this.showMore5 = false;
              this.questionList5 = [];
            }
          }
        }
         // 当有6个tab页时
        if (this.tabList.length == 6) {
          if (param.letterId == this.tabList[0].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount;
              this.answerCount = res.data.result[0].answerCount;
              if (this.allQuestionList.length > 15) {
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0, 15);
              } else {
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            } else {
              this.showMore = false;
              this.questionList = [];
            }
          }
          if (param.letterId == this.tabList[1].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList2 = res.data.result[0].questionList;
              this.questionCount2 = res.data.result[0].questionCount;
              this.answerCount2 = res.data.result[0].answerCount;
              if (this.allQuestionList2.length > 15) {
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0, 15);
              } else {
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            } else {
              this.showMore2 = false;
              this.questionList2 = [];
            }
          }
          if (param.letterId == this.tabList[2].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList3 = res.data.result[0].questionList;
              this.questionCount3 = res.data.result[0].questionCount;
              this.answerCount3 = res.data.result[0].answerCount;
              if (this.allQuestionList3.length > 15) {
                this.showMore3 = true;
                this.questionList3 = this.allQuestionList3.slice(0, 15);
              } else {
                this.showMore3 = false;
                this.questionList3 = this.allQuestionList3;
              }
            } else {
              this.showMore3 = false;
              this.questionList3 = [];
            }
          }
          if (param.letterId == this.tabList[3].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList4 = res.data.result[0].questionList;
              this.questionCount4 = res.data.result[0].questionCount;
              this.answerCount4 = res.data.result[0].answerCount;
              if (this.allQuestionList4.length > 15) {
                this.showMore4 = true;
                this.questionList4 = this.allQuestionList4.slice(0, 15);
              } else {
                this.showMore4 = false;
                this.questionList4 = this.allQuestionList4;
              }
            } else {
              this.showMore4 = false;
              this.questionList4 = [];
            }
          }
          if (param.letterId == this.tabList[4].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList5 = res.data.result[0].questionList;
              this.questionCount5 = res.data.result[0].questionCount;
              this.answerCount5 = res.data.result[0].answerCount;
              if (this.allQuestionList5.length > 15) {
                this.showMore5 = true;
                this.questionList5 = this.allQuestionList5.slice(0, 15);
              } else {
                this.showMore5 = false;
                this.questionList5 = this.allQuestionList5;
              }
            } else {
              this.showMore5 = false;
              this.questionList5 = [];
            }
          }
          if (param.letterId == this.tabList[5].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList6 = res.data.result[0].questionList;
              this.questionCount6 = res.data.result[0].questionCount;
              this.answerCount6 = res.data.result[0].answerCount;
              if (this.allQuestionList6.length > 15) {
                this.showMore6 = true;
                this.questionList6 = this.allQuestionList6.slice(0, 15);
              } else {
                this.showMore6 = false;
                this.questionList6 = this.allQuestionList6;
              }
            } else {
              this.showMore6 = false;
              this.questionList6 = [];
            }
          }
        }
         // 当有7个tab页时
        if (this.tabList.length == 7) {
          if (param.letterId == this.tabList[0].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount;
              this.answerCount = res.data.result[0].answerCount;
              if (this.allQuestionList.length > 15) {
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0, 15);
              } else {
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            } else {
              this.showMore = false;
              this.questionList = [];
            }
          }
          if (param.letterId == this.tabList[1].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList2 = res.data.result[0].questionList;
              this.questionCount2 = res.data.result[0].questionCount;
              this.answerCount2 = res.data.result[0].answerCount;
              if (this.allQuestionList2.length > 15) {
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0, 15);
              } else {
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            } else {
              this.showMore2 = false;
              this.questionList2 = [];
            }
          }
          if (param.letterId == this.tabList[2].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList3 = res.data.result[0].questionList;
              this.questionCount3 = res.data.result[0].questionCount;
              this.answerCount3 = res.data.result[0].answerCount;
              if (this.allQuestionList3.length > 15) {
                this.showMore3 = true;
                this.questionList3 = this.allQuestionList3.slice(0, 15);
              } else {
                this.showMore3 = false;
                this.questionList3 = this.allQuestionList3;
              }
            } else {
              this.showMore3 = false;
              this.questionList3 = [];
            }
          }
          if (param.letterId == this.tabList[3].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList4 = res.data.result[0].questionList;
              this.questionCount4 = res.data.result[0].questionCount;
              this.answerCount4 = res.data.result[0].answerCount;
              if (this.allQuestionList4.length > 15) {
                this.showMore4 = true;
                this.questionList4 = this.allQuestionList4.slice(0, 15);
              } else {
                this.showMore4 = false;
                this.questionList4 = this.allQuestionList4;
              }
            } else {
              this.showMore4 = false;
              this.questionList4 = [];
            }
          }
          if (param.letterId == this.tabList[4].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList5 = res.data.result[0].questionList;
              this.questionCount5 = res.data.result[0].questionCount;
              this.answerCount5 = res.data.result[0].answerCount;
              if (this.allQuestionList5.length > 15) {
                this.showMore5 = true;
                this.questionList5 = this.allQuestionList5.slice(0, 15);
              } else {
                this.showMore5 = false;
                this.questionList5 = this.allQuestionList5;
              }
            } else {
              this.showMore5 = false;
              this.questionList5 = [];
            }
          }
          if (param.letterId == this.tabList[5].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList6 = res.data.result[0].questionList;
              this.questionCount6 = res.data.result[0].questionCount;
              this.answerCount6 = res.data.result[0].answerCount;
              if (this.allQuestionList6.length > 15) {
                this.showMore6 = true;
                this.questionList6 = this.allQuestionList6.slice(0, 15);
              } else {
                this.showMore6 = false;
                this.questionList6 = this.allQuestionList6;
              }
            } else {
              this.showMore6 = false;
              this.questionList6 = [];
            }
          }
          if (param.letterId == this.tabList[6].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList7 = res.data.result[0].questionList;
              this.questionCount7 = res.data.result[0].questionCount;
              this.answerCount7 = res.data.result[0].answerCount;
              if (this.allQuestionList7.length > 15) {
                this.showMore7 = true;
                this.questionList7 = this.allQuestionList7.slice(0, 15);
              } else {
                this.showMore7 = false;
                this.questionList7 = this.allQuestionList7;
              }
            } else {
              this.showMore7 = false;
              this.questionList7 = [];
            }
          }
        }
        // 当有8个tab页时
        if (this.tabList.length == 8) {
          if (param.letterId == this.tabList[0].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount;
              this.answerCount = res.data.result[0].answerCount;
              if (this.allQuestionList.length > 15) {
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0, 15);
              } else {
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            } else {
              this.showMore = false;
              this.questionList = [];
            }
          }
          if (param.letterId == this.tabList[1].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList2 = res.data.result[0].questionList;
              this.questionCount2 = res.data.result[0].questionCount;
              this.answerCount2 = res.data.result[0].answerCount;
              if (this.allQuestionList2.length > 15) {
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0, 15);
              } else {
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            } else {
              this.showMore2 = false;
              this.questionList2 = [];
            }
          }
          if (param.letterId == this.tabList[2].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList3 = res.data.result[0].questionList;
              this.questionCount3 = res.data.result[0].questionCount;
              this.answerCount3 = res.data.result[0].answerCount;
              if (this.allQuestionList3.length > 15) {
                this.showMore3 = true;
                this.questionList3 = this.allQuestionList3.slice(0, 15);
              } else {
                this.showMore3 = false;
                this.questionList3 = this.allQuestionList3;
              }
            } else {
              this.showMore3 = false;
              this.questionList3 = [];
            }
          }
          if (param.letterId == this.tabList[3].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList4 = res.data.result[0].questionList;
              this.questionCount4 = res.data.result[0].questionCount;
              this.answerCount4 = res.data.result[0].answerCount;
              if (this.allQuestionList4.length > 15) {
                this.showMore4 = true;
                this.questionList4 = this.allQuestionList4.slice(0, 15);
              } else {
                this.showMore4 = false;
                this.questionList4 = this.allQuestionList4;
              }
            } else {
              this.showMore4 = false;
              this.questionList4 = [];
            }
          }
          if (param.letterId == this.tabList[4].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList5 = res.data.result[0].questionList;
              this.questionCount5 = res.data.result[0].questionCount;
              this.answerCount5 = res.data.result[0].answerCount;
              if (this.allQuestionList5.length > 15) {
                this.showMore5 = true;
                this.questionList5 = this.allQuestionList5.slice(0, 15);
              } else {
                this.showMore5 = false;
                this.questionList5 = this.allQuestionList5;
              }
            } else {
              this.showMore5 = false;
              this.questionList5 = [];
            }
          }
          if (param.letterId == this.tabList[5].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList6 = res.data.result[0].questionList;
              this.questionCount6 = res.data.result[0].questionCount;
              this.answerCount6 = res.data.result[0].answerCount;
              if (this.allQuestionList6.length > 15) {
                this.showMore6 = true;
                this.questionList6 = this.allQuestionList6.slice(0, 15);
              } else {
                this.showMore6 = false;
                this.questionList6 = this.allQuestionList6;
              }
            } else {
              this.showMore6 = false;
              this.questionList6 = [];
            }
          }
          if (param.letterId == this.tabList[6].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList7 = res.data.result[0].questionList;
              this.questionCount7 = res.data.result[0].questionCount;
              this.answerCount7 = res.data.result[0].answerCount;
              if (this.allQuestionList7.length > 15) {
                this.showMore7 = true;
                this.questionList7 = this.allQuestionList7.slice(0, 15);
              } else {
                this.showMore7 = false;
                this.questionList7 = this.allQuestionList7;
              }
            } else {
              this.showMore7 = false;
              this.questionList7 = [];
            }
          }
          if (param.letterId == this.tabList[7].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList8 = res.data.result[0].questionList;
              this.questionCount8 = res.data.result[0].questionCount;
              this.answerCount8 = res.data.result[0].answerCount;
              if (this.allQuestionList8.length > 15) {
                this.showMore8 = true;
                this.questionList8 = this.allQuestionList8.slice(0, 15);
              } else {
                this.showMore8 = false;
                this.questionList8 = this.allQuestionList8;
              }
            } else {
              this.showMore8 = false;
              this.questionList8 = [];
            }
          }
        }
        // 当有9个tab页时
        if (this.tabList.length == 9) {
          if (param.letterId == this.tabList[0].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount;
              this.answerCount = res.data.result[0].answerCount;
              if (this.allQuestionList.length > 15) {
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0, 15);
              } else {
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            } else {
              this.showMore = false;
              this.questionList = [];
            }
          }
          if (param.letterId == this.tabList[1].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList2 = res.data.result[0].questionList;
              this.questionCount2 = res.data.result[0].questionCount;
              this.answerCount2 = res.data.result[0].answerCount;
              if (this.allQuestionList2.length > 15) {
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0, 15);
              } else {
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            } else {
              this.showMore2 = false;
              this.questionList2 = [];
            }
          }
          if (param.letterId == this.tabList[2].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList3 = res.data.result[0].questionList;
              this.questionCount3 = res.data.result[0].questionCount;
              this.answerCount3 = res.data.result[0].answerCount;
              if (this.allQuestionList3.length > 15) {
                this.showMore3 = true;
                this.questionList3 = this.allQuestionList3.slice(0, 15);
              } else {
                this.showMore3 = false;
                this.questionList3 = this.allQuestionList3;
              }
            } else {
              this.showMore3 = false;
              this.questionList3 = [];
            }
          }
          if (param.letterId == this.tabList[3].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList4 = res.data.result[0].questionList;
              this.questionCount4 = res.data.result[0].questionCount;
              this.answerCount4 = res.data.result[0].answerCount;
              if (this.allQuestionList4.length > 15) {
                this.showMore4 = true;
                this.questionList4 = this.allQuestionList4.slice(0, 15);
              } else {
                this.showMore4 = false;
                this.questionList4 = this.allQuestionList4;
              }
            } else {
              this.showMore4 = false;
              this.questionList4 = [];
            }
          }
          if (param.letterId == this.tabList[4].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList5 = res.data.result[0].questionList;
              this.questionCount5 = res.data.result[0].questionCount;
              this.answerCount5 = res.data.result[0].answerCount;
              if (this.allQuestionList5.length > 15) {
                this.showMore5 = true;
                this.questionList5 = this.allQuestionList5.slice(0, 15);
              } else {
                this.showMore5 = false;
                this.questionList5 = this.allQuestionList5;
              }
            } else {
              this.showMore5 = false;
              this.questionList5 = [];
            }
          }
          if (param.letterId == this.tabList[5].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList6 = res.data.result[0].questionList;
              this.questionCount6 = res.data.result[0].questionCount;
              this.answerCount6 = res.data.result[0].answerCount;
              if (this.allQuestionList6.length > 15) {
                this.showMore6 = true;
                this.questionList6 = this.allQuestionList6.slice(0, 15);
              } else {
                this.showMore6 = false;
                this.questionList6 = this.allQuestionList6;
              }
            } else {
              this.showMore6 = false;
              this.questionList6 = [];
            }
          }
          if (param.letterId == this.tabList[6].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList7 = res.data.result[0].questionList;
              this.questionCount7 = res.data.result[0].questionCount;
              this.answerCount7 = res.data.result[0].answerCount;
              if (this.allQuestionList7.length > 15) {
                this.showMore7 = true;
                this.questionList7 = this.allQuestionList7.slice(0, 15);
              } else {
                this.showMore7 = false;
                this.questionList7 = this.allQuestionList7;
              }
            } else {
              this.showMore7 = false;
              this.questionList7 = [];
            }
          }
          if (param.letterId == this.tabList[7].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList8 = res.data.result[0].questionList;
              this.questionCount8 = res.data.result[0].questionCount;
              this.answerCount8 = res.data.result[0].answerCount;
              if (this.allQuestionList8.length > 15) {
                this.showMore8 = true;
                this.questionList8 = this.allQuestionList8.slice(0, 15);
              } else {
                this.showMore8 = false;
                this.questionList8 = this.allQuestionList8;
              }
            } else {
              this.showMore8 = false;
              this.questionList8 = [];
            }
          }
          if (param.letterId == this.tabList[8].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList9 = res.data.result[0].questionList;
              this.questionCount9 = res.data.result[0].questionCount;
              this.answerCount9 = res.data.result[0].answerCount;
              if (this.allQuestionList9.length > 15) {
                this.showMore9 = true;
                this.questionList9 = this.allQuestionList9.slice(0, 15);
              } else {
                this.showMore9 = false;
                this.questionList9 = this.allQuestionList9;
              }
            } else {
              this.showMore9 = false;
              this.questionList9 = [];
            }
          }
        }
        // 当有10个tab页时
        if (this.tabList.length == 10) {
          if (param.letterId == this.tabList[0].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount;
              this.answerCount = res.data.result[0].answerCount;
              if (this.allQuestionList.length > 15) {
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0, 15);
              } else {
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            } else {
              this.showMore = false;
              this.questionList = [];
            }
          }
          if (param.letterId == this.tabList[1].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList2 = res.data.result[0].questionList;
              this.questionCount2 = res.data.result[0].questionCount;
              this.answerCount2 = res.data.result[0].answerCount;
              if (this.allQuestionList2.length > 15) {
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0, 15);
              } else {
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            } else {
              this.showMore2 = false;
              this.questionList2 = [];
            }
          }
          if (param.letterId == this.tabList[2].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList3 = res.data.result[0].questionList;
              this.questionCount3 = res.data.result[0].questionCount;
              this.answerCount3 = res.data.result[0].answerCount;
              if (this.allQuestionList3.length > 15) {
                this.showMore3 = true;
                this.questionList3 = this.allQuestionList3.slice(0, 15);
              } else {
                this.showMore3 = false;
                this.questionList3 = this.allQuestionList3;
              }
            } else {
              this.showMore3 = false;
              this.questionList3 = [];
            }
          }
          if (param.letterId == this.tabList[3].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList4 = res.data.result[0].questionList;
              this.questionCount4 = res.data.result[0].questionCount;
              this.answerCount4 = res.data.result[0].answerCount;
              if (this.allQuestionList4.length > 15) {
                this.showMore4 = true;
                this.questionList4 = this.allQuestionList4.slice(0, 15);
              } else {
                this.showMore4 = false;
                this.questionList4 = this.allQuestionList4;
              }
            } else {
              this.showMore4 = false;
              this.questionList4 = [];
            }
          }
          if (param.letterId == this.tabList[4].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList5 = res.data.result[0].questionList;
              this.questionCount5 = res.data.result[0].questionCount;
              this.answerCount5 = res.data.result[0].answerCount;
              if (this.allQuestionList5.length > 15) {
                this.showMore5 = true;
                this.questionList5 = this.allQuestionList5.slice(0, 15);
              } else {
                this.showMore5 = false;
                this.questionList5 = this.allQuestionList5;
              }
            } else {
              this.showMore5 = false;
              this.questionList5 = [];
            }
          }
          if (param.letterId == this.tabList[5].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList6 = res.data.result[0].questionList;
              this.questionCount6 = res.data.result[0].questionCount;
              this.answerCount6 = res.data.result[0].answerCount;
              if (this.allQuestionList6.length > 15) {
                this.showMore6 = true;
                this.questionList6 = this.allQuestionList6.slice(0, 15);
              } else {
                this.showMore6 = false;
                this.questionList6 = this.allQuestionList6;
              }
            } else {
              this.showMore6 = false;
              this.questionList6 = [];
            }
          }
          if (param.letterId == this.tabList[6].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList7 = res.data.result[0].questionList;
              this.questionCount7 = res.data.result[0].questionCount;
              this.answerCount7 = res.data.result[0].answerCount;
              if (this.allQuestionList7.length > 15) {
                this.showMore7 = true;
                this.questionList7 = this.allQuestionList7.slice(0, 15);
              } else {
                this.showMore7 = false;
                this.questionList7 = this.allQuestionList7;
              }
            } else {
              this.showMore7 = false;
              this.questionList7 = [];
            }
          }
          if (param.letterId == this.tabList[7].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList8 = res.data.result[0].questionList;
              this.questionCount8 = res.data.result[0].questionCount;
              this.answerCount8 = res.data.result[0].answerCount;
              if (this.allQuestionList8.length > 15) {
                this.showMore8 = true;
                this.questionList8 = this.allQuestionList8.slice(0, 15);
              } else {
                this.showMore8 = false;
                this.questionList8 = this.allQuestionList8;
              }
            } else {
              this.showMore8 = false;
              this.questionList8 = [];
            }
          }
          if (param.letterId == this.tabList[8].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList9 = res.data.result[0].questionList;
              this.questionCount9 = res.data.result[0].questionCount;
              this.answerCount9 = res.data.result[0].answerCount;
              if (this.allQuestionList9.length > 15) {
                this.showMore9 = true;
                this.questionList9 = this.allQuestionList9.slice(0, 15);
              } else {
                this.showMore9 = false;
                this.questionList9 = this.allQuestionList9;
              }
            } else {
              this.showMore9 = false;
              this.questionList9 = [];
            }
          }
          if (param.letterId == this.tabList[9].letterId) {
            if (res.data.result.length > 0) {
              this.allQuestionList10 = res.data.result[0].questionList;
              this.questionCount10 = res.data.result[0].questionCount;
              this.answerCount10 = res.data.result[0].answerCount;
              if (this.allQuestionList10.length > 15) {
                this.showMore10 = true;
                this.questionList10 = this.allQuestionList10.slice(0, 15);
              } else {
                this.showMore10 = false;
                this.questionList10 = this.allQuestionList10;
              }
            } else {
              this.showMore10 = false;
              this.questionList10 = [];
            }
          }
        }
      });
    },
    // 点击tab页
    handleTabClick(tab, event) {
      this.o_letterId = tab.name;
    },
  }
};
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
.firstLabelFocus:hover {
  cursor: pointer;
}
.secondLabelFocus:hover {
  cursor: pointer;
  color: #14bcf5 !important;
}
.secondLabelFocus:focus {
  cursor: pointer;
  color: #14bcf5 !important;
  border-color: #14bcf5;
  background: white;
}
.biaoqian {
  display: inline-block;
  text-align: center;
  border: 1px solid rgba(235, 235, 235, 1);
  font-family: "MicrosoftYaHei", "Microsoft YaHei";
  margin-left: 10px;
  padding: 4px;
  border-radius: 2px;
  font-weight: 400;
  font-style: normal;
  font-size: 14px;
  color: rgba(0, 0, 0, 0.647058823529412);
}
.wen {
  width: 24px;
  height: 24px;
  padding-top: 6px;
  padding-left: 6px;
  color: #ffffff;
  position: absolute;
  top: 0;
  left: 0;
  font-size: 12px;
  background: url("../../../assets/images/wen.png") no-repeat;
  background-size: contain;
}
.da {
  width: 24px;
  height: 24px;
  padding-top: 6px;
  padding-left: 6px;
  color: #ffffff;
  font-size: 12px;
  background: url("../../../assets/images/da.png") no-repeat;
  background-size: contain;
  position: relative;
  top: 15px;
  left: -23px;
}
.quan {
  background-size: contain;
  background: url("../../../assets/images/quan.png") no-repeat;
}
.reset {
  background: inherit;
  background-color: #fff;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  border-width: 1px;
  border-style: solid;
  border-color: #cacaca;
  box-shadow: none;
  font-family: "Microsoft YaHei Regular", "Microsoft YaHei";
  font-weight: 400;
  font-style: normal;
  color: #666;
  text-align: center;
  line-height: 12px;
  position: relative;
  left: 5%;
  top: -2px;
  cursor: pointer;
  height: 28px;
}
// 展开
.spread {
  position: relative;
  left: 94%;
}
.spread:hover {
  text-decoration: underline;
}

// 收起
.packUp {
  position: relative;
  left: 94%;
}
.packUp:hover {
  text-decoration: underline;
}
.more {
  width: 100%;
  height: 46px;
  font-family: "Microsoft Tai Le Normal", "Microsoft Tai Le";
  font-weight: 400;
  font-size: 14px;
  color: #252b3f;
  line-height: 46px;
  background: #f7f7f7;
  text-align: center;
  cursor: pointer;
}
.checkbox {
  font-size: 12px !important;
  color: #555555;
  background: #fafafa;
  margin-right: 20px;
  margin-bottom: 10px;
  border-width: 0.1px;
  border-style: solid;
  border-color: #ebebeb;
  border-radius: 3px;
}
.moreText {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 5;
  -webkit-box-orient: vertical;
}
.title {
  border-bottom: 1px solid;
  border-bottom-color: #ebebeb;
  height: 42px;
  line-height: 42px;
  background-color: #fafafa;
  display: flex;
  align-items: center;
  margin-top: 30px;
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
.finishRead {
  font-family: "PingFang-SC-Regular", "PingFang SC";
  font-weight: 400;
  font-style: normal;
  color: #888888;
  line-height: 24px;
  font-size: 14px;
  text-align: center;
}
</style>
<style>
.daImg img {
  width: 100%;
}
</style>


