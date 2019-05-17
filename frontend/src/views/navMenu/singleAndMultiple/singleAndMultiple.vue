<template>
    <div class="singleAndMultiple feedback" id="componentId">
      <div style="font-size: 14px;color: #777777;padding-bottom:12px;margin-bottom: 30px;">
          <div class="firstLabel" >
              <div style="padding:15px 25px 0 25px;margin-top:0px;padding-left:10px;">
                <div class="clear" style="border-bottom: 1px solid rgb(235, 235, 235);position:relative">
                  <p style="float:left;margin-left: 8px;margin-top:3px;">问题类型：</p>
                  <el-radio-group  @change="handelChange(radio)" v-model="radio" size="small" style="padding-bottom:10px;float:left;width:88%">
                      <el-radio-button :key="item.labelCode" v-for="item in singleAndMultiplDdata.radio" class="l firstLabelFocus" style="margin-right:10px;margin-bottom:10px;font-size: 12px; color: rgba(0, 0, 0, 0.647058823529412);" :label="item.labelCode">{{item.labelName}}({{item.labelCount}})</el-radio-button>
                  </el-radio-group>
                </div>
              </div>
                <div class="clear" style="padding:0px 25px;margin-top:12px;padding-bottom:10px">
                  <p style="float: left; margin-left: -6px; margin-top: 2px;margin-right: 15px;" v-if="singleAndMultiplDdata.checkbox&&singleAndMultiplDdata.checkbox.length>0">子集分类:</p>
                  <el-checkbox-group  @change="handelMoreChange(checkboxGroup)" v-model="checkboxGroup" size="mini" style="float:left;width:88%">
                      <el-checkbox :key="item.labelCode" class="checkbox" v-for="item in singleAndMultiplDdata.checkbox" :label="item.labelCode">{{item.labelName}}({{item.labelCount}})</el-checkbox>
                  </el-checkbox-group>
                </div>
              <div class="kaiguan" style="text-align:left;font-size: 12px;
                    background: #f9f9f9;
                    padding: 14px;
                    margin-left:4px;
                    color: #999999;
                    text-align: left;
                    line-height: 14px;">
                  <span>共计</span>
                  <span style="color:black;font-weight:bold">{{singleAndMultiplDdata.questionCount}}</span>
                  <span>个问题，</span>
                  <span style="color:black;font-weight:bold">{{singleAndMultiplDdata.answerCount}}</span>
                  <span>个回复</span>
                  <el-checkbox  @change="handleOnlyChange(onlyShowAnswer)" v-model="onlyShowAnswer" style="margin-left:20px;margin-right:15px">只展示回复问题</el-checkbox>
              </div>
          </div>
      </div>
      <div class="question" id="titleLength">
          <ul style="padding-left:0">
              <li v-for="(data,index) in singleAndMultiplDdata.questionList" :key="data.questionId" style="border-bottom:1px solid #e1e1e1;padding-bottom:15px;margin-bottom:30px">
                  <div class="text" style="background:rgba(250, 250, 250, 1); padding: 10px 24px;margin-bottom:10px;position:relative">
                    <!-- 问 -->
                      <div  v-if="data.question&&data.question.length>0">
                        <div class="wen">问</div>
                        <div  style="font-size:14px;color:#333;line-height:22px">
                            <p style="width:100%;" v-if="!data.isSpread || (data.isSpread && data.isSpread !== 2)">&nbsp;&nbsp;{{getContent(data,data.question,index,'answer')}}</p>
                            <p style="width:100%;"  v-if="data.isSpread && data.isSpread === 2">&nbsp;&nbsp;{{data.question}}</p>
                        </div>
                        <!-- 收起展开 -->
                        <div class="btn" style="color: #4F91D1;font-size:14px">
                            <span style="cursor:pointer" v-if="data.isSpread && data.isSpread === 2" class="packUp" @click="packUp(data)">收起 <i style="font-size:12px;cursor:pointer" class="el-icon-arrow-up"></i></span>
                            <span style="cursor:pointer" v-if="data.isSpread && data.isSpread === 1" class="spread" @click="spread(data)">展开 <i style="font-size:12px;cursor:pointer" class="el-icon-arrow-down"></i></span>
                        </div>
                      </div>
                      <!-- 答 -->
                      <div  v-if="data.answer&&data.answer.length>0">
                        <div class="da">答</div>
                        <div style="font-size:14px;color:#333;line-height:22px">
                            <p style="width:100%;" v-if="!data.isSpreada || (data.isSpreada && data.isSpreada !== 2)">&nbsp;&nbsp;{{getContent(data,data.answer,index,'question')}}</p>
                            <p class="daImg" v-html="data.formatAnswer" style="width:100%;"  v-if="data.isSpreada && data.isSpreada === 2">&nbsp;&nbsp;{{data.formatAnswer}}</p>
                        </div>
                        <!-- 收起展开 -->
                          <div class="btn" style="color: #4F91D1;font-size:14px">
                            <span style="cursor:pointer" v-if="data.isSpreada && data.isSpreada === 2" class="packUp" @click="daPackUp(data)">收起 <i style="font-size:12px" class="el-icon-arrow-up"></i></span>
                            <span style="cursor:pointer" v-if="data.isSpreada && data.isSpreada === 1" class="spread" @click="daSpread(data)">展开 <i style="font-size:12px" class="el-icon-arrow-down"></i></span>
                        </div>
                      </div>
                  </div>
                  <div>
                      <span v-for="biaoqian in data.labelList" class="biaoqian" style="margin-right:2px;margin-bottom:12px">{{biaoqian}}</span>
                  </div>
              </li>
          </ul>

        <!-- 加载更多 -->
        <div  v-if="singleAndMultiplDdata.showMore" @click="showMoreMethods()" class="more">加载更多</div>
        <!-- 已经阅读完了 -->
        <p v-if="!singleAndMultiplDdata.showMore&&singleAndMultiplDdata.questionList.length>0" class="finishRead">已经阅读完了</p>
          <!-- 暂无更多数据 -->
        <p v-if="!singleAndMultiplDdata.showMore&&singleAndMultiplDdata.questionList.length==0" class="finishRead">暂无相关数据</p>
      </div>
   </div>
</template>
<script>
import $ from "jquery";
export default {
  name: "singleAndMultiple",
  props:["singleAndMultiplDdata"],
  data() {
    return {
      caseId: this.$store.state.app.caseId,
      // 总共数据
      tabList: [],
      // 多选按钮数据
      feedbackduoxuanList: [],
      flag: false,
      // 单选按钮绑定值
      radio: "",
      // 单选按钮选中的值
      radioVal: "",
      // 多选按钮绑定值
      checkboxGroup: [],
      onlyShowAnswer: false,
      onlyShowAnswerFlag: "",
      // 默认展示第一个label页
      activeName: "",
      // 函件id,通过这个id区分不同tab页
      letterId: "",
      // 自己定义的
      o_letterId: "",
      // 多选
      o_secondtLabelId: "",
      // // 回复个数
      answerCount: "",
      // // 问题个数
      questionCount: "",
      // 是否展示全部问题
      isShowAll: true,
      // 默认有moreText类
      isMoreText: true,
      allQuestionList: [],
    };
  },
  created() {
    this.isShowAll = true;
  },
  // 滑轮滚到底部懒加载
  mounted() {
    let _this = this;
    // 注册scroll事件并监听
    window.addEventListener("scroll", function() {
      let scrollTop =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop; 
      if (scrollTop + window.innerHeight + 2 >= document.body.offsetHeight) {
        _this.showMoreMethods();
      }
    });
  },
  methods: {
    // 单选按钮
    handelChange(val) {
      this.radioVal = val;
      this.checkboxGroup = [];
      // 判断点击的是否是单选按钮的’全部‘按钮
      if (val == null) {
        //   当tab只有一个时，只有一轮反馈意见【this.$parent】
          if(this.singleAndMultiplDdata.tabList.length==1){
              this.$parent.initQuestionData(
               this.singleAndMultiplDdata.o_letterId,
               '',
               this.checkboxGroup,
               this.onlyShowAnswerFlag
            );
          }
          //   当tab有多个时，多轮反馈意见【this.$parent.$parent.$parent】
          if(this.singleAndMultiplDdata.tabList.length>1){
              this.$parent.$parent.$parent.initQuestionData(
                this.singleAndMultiplDdata.o_letterId,
                '',
                this.checkboxGroup,
                this.onlyShowAnswerFlag
              );
          }
      } else {
          if(this.singleAndMultiplDdata.tabList.length==1){
              this.$parent.initQuestionData(
               this.singleAndMultiplDdata.o_letterId,
               val,
               this.checkboxGroup,
               this.onlyShowAnswerFlag
            );
          }
          if(this.singleAndMultiplDdata.tabList.length > 1){
              this.$parent.$parent.$parent.initQuestionData(
                this.singleAndMultiplDdata.o_letterId,
                val,
                this.checkboxGroup,
                this.onlyShowAnswerFlag
              );
          }
      }
    },
    // 多选按钮
    handelMoreChange(val) {
      for (let i = 0; i < val.length; i++) {
        //  如果点击了多选按钮‘全部’,就将绑定的数组变成【null】,然后重新请求数据，传空数组[]
        if (val[i] == null) {
          this.checkboxGroup = [null];
          if (this.radioVal == null) {
              if(this.singleAndMultiplDdata.tabList.length==1){
                  this.$parent.initOnlyQuestionData(
                    this.singleAndMultiplDdata.o_letterId,
                    "",
                    [],
                    this.onlyShowAnswerFlag
                  );
              }
              if(this.singleAndMultiplDdata.tabList.length>1){
                    this.$parent.$parent.$parent.initOnlyQuestionData(
                    this.singleAndMultiplDdata.o_letterId,
                    "",
                    [],
                    this.onlyShowAnswerFlag
                  );
              }
          } else {
              if(this.singleAndMultiplDdata.tabList.length==1){
                  this.$parent.initOnlyQuestionData(
                    this.singleAndMultiplDdata.o_letterId,
                    this.radioVal,
                    [],
                    this.onlyShowAnswerFlag
                  );
              }
              if(this.singleAndMultiplDdata.tabList.length>1){
                    this.$parent.$parent.$parent.initOnlyQuestionData(
                    this.singleAndMultiplDdata.o_letterId,
                    this.radioVal,
                    [],
                    this.onlyShowAnswerFlag
                  );
              }
          }
        } else {
          // 当点击其他的多选按钮时候，之前有点击过‘全部’按钮，需要将这个全部的null去掉，
          if (val.indexOf(null) != -1) {
            this.checkboxGroup = val.slice(1);
          } else {
            this.checkboxGroup = val;
          }
          if (this.radioVal == null) {
              if(this.singleAndMultiplDdata.tabList.length==1){
                  this.$parent.initOnlyQuestionData(
                    this.singleAndMultiplDdata.o_letterId,
                     "",
                     this.checkboxGroup,
                    this.onlyShowAnswerFlag
                  );
              }
              if(this.singleAndMultiplDdata.tabList.length>1){
                    this.$parent.$parent.$parent.initOnlyQuestionData(
                    this.singleAndMultiplDdata.o_letterId,
                     "",
                    this.checkboxGroup,
                    this.onlyShowAnswerFlag
                  );
              }
          } else {
              if(this.singleAndMultiplDdata.tabList.length==1){
                  this.$parent.initOnlyQuestionData(
                    this.singleAndMultiplDdata.o_letterId,
                    this.radioVal,
                    this.checkboxGroup,
                    this.onlyShowAnswerFlag
                  );
              }
              if(this.singleAndMultiplDdata.tabList.length>1){
                    this.$parent.$parent.$parent.initOnlyQuestionData(
                    this.singleAndMultiplDdata.o_letterId,
                    this.radioVal,
                    this.checkboxGroup,
                    this.onlyShowAnswerFlag
                  );
              }
          }
        }
      }
    },
    // 是否只展示回复问题
    handleOnlyChange(val) {
      if (val == true) {
        this.onlyShowAnswerFlag = "1";
      } else {
        this.onlyShowAnswerFlag = "";
      }
      if (this.radioVal == null) {
        if (this.checkboxGroup[0] == null) {
            if(this.singleAndMultiplDdata.tabList.length==1){
                  this.$parent.initOnlyQuestionData(
                    this.singleAndMultiplDdata.o_letterId,
                     "",
                    [],
                    this.onlyShowAnswerFlag
                  );
              }
              if(this.singleAndMultiplDdata.tabList.length>1){
                    this.$parent.$parent.$parent.initOnlyQuestionData(
                    this.singleAndMultiplDdata.o_letterId,
                     "",
                    [],
                    this.onlyShowAnswerFlag
                  );
              }
        } else {
            if(this.singleAndMultiplDdata.tabList.length==1){
                  this.$parent.initOnlyQuestionData(
                    this.singleAndMultiplDdata.o_letterId,
                     "",
                     this.checkboxGroup,
                    this.onlyShowAnswerFlag
                  );
              }
              if(this.singleAndMultiplDdata.tabList.length>1){
                    this.$parent.$parent.$parent.initOnlyQuestionData(
                    this.singleAndMultiplDdata.o_letterId,
                     "",
                     this.checkboxGroup,
                    this.onlyShowAnswerFlag
                  );
              }
        }
      } else {
        if (this.checkboxGroup[0] == null) {
            if(this.singleAndMultiplDdata.tabList.length==1){
                  this.$parent.initOnlyQuestionData(
                    this.singleAndMultiplDdata.o_letterId,
                     this.radioVal,
                    [],
                    this.onlyShowAnswerFlag
                  );
              }
              if(this.singleAndMultiplDdata.tabList.length>1){
                    this.$parent.$parent.$parent.initOnlyQuestionData(
                    this.singleAndMultiplDdata.o_letterId,
                     this.radioVal,
                    [],
                    this.onlyShowAnswerFlag
                  );
              }
        } else {
            if(this.singleAndMultiplDdata.tabList.length==1){
                  this.$parent.initOnlyQuestionData(
                     this.singleAndMultiplDdata.o_letterId,
                     this.radioVal,
                     this.checkboxGroup,
                     this.onlyShowAnswerFlag
                  );
              }
              if(this.singleAndMultiplDdata.tabList.length>1){
                    this.$parent.$parent.$parent.initOnlyQuestionData(
                     this.singleAndMultiplDdata.o_letterId,
                     this.radioVal,
                     this.checkboxGroup,
                     this.onlyShowAnswerFlag
                  );
              }
        }
      }
    },
    // 点击加载更多
    showMoreMethods() {
      if(this.singleAndMultiplDdata.tabList.length==1){
        this.$parent.showMoreMethods()
      }
      if(this.singleAndMultiplDdata.tabList.length>1){
        this.$parent.$parent.$parent.showMoreMethods()
      }
    },
    
    // 问【收起展开】
    spread(item) {
      this.$set(item, "isSpread", 2);
    },
    packUp(item) {
      this.$set(item, "isSpread", 1);
    },
    // 答【收起展开】
    daSpread(item) {
      this.$set(item, "isSpreada", 2);
    },
    daPackUp(item) {
      this.$nextTick(() => {
        this.$set(item, "isSpreada", 1);
      });
    },
    getContent(data, title, index, type) {
      // 5行的宽度和
      let width = (document.getElementById("componentId").offsetWidth - 48) * 5;
      // 所有字数的宽度【14是字体大小】
      let titleLength = title.length * 14;
      let length = 0;
      if (titleLength > width) {
        for (let i = 0; i < title.length; i++) {
          length += 14;
          if (length > width) {
            if (type === "answer") {
              if (!data.isSpread || data.isSpread === 0) {
                this.$set(data, "isSpread", 1);
              }
            } else {
              if (!data.isSpreada || data.isSpreada === 0) {
                this.$set(data, "isSpreada", 1);
              }
            }
            return title.substring(0, i - 4) + "...";
          }
        }
      } else {
        if (type === "answer") {
          if (!data.isSpread || data.isSpread === 0) {
            this.$set(data, "isSpread", 0);
          }
        } else {
          if (!data.isSpreada || data.isSpreada === 0) {
            this.$set(data, "isSpreada", 0);
          }
        }
        return title;
      }
    }
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


