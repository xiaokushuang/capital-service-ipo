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
                <div v-if="tabList.length==2" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共两轮反馈：</div>
                <div v-if="tabList.length==3" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共三轮反馈：</div>
                <div>
                    <el-tabs v-model="activeName" @tab-click="handleTabClick">
                      <el-tab-pane label="第一次反馈意见" :name="tabList[0].letterId">
                          <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[0].questionLabelList,checkbox:feedbackduoxuanList,answerCount:answerCount,questionCount:questionCount,questionList:questionList,o_letterId:this.o_letterId,showMore:showMore,allQuestionList:allQuestionList}></singleAndMultiple>
                      </el-tab-pane>
                       <el-tab-pane label="第二次反馈意见" :name="tabList[1].letterId">
                          <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[1].questionLabelList,checkbox:feedbackduoxuanList2,answerCount:answerCount2,questionCount:questionCount2,questionList:questionList2,o_letterId:this.o_letterId,showMore:showMore2,allQuestionList:allQuestionList2}></singleAndMultiple>
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
  data() {
    return {
      caseId: this.$store.state.app.caseId,
      // 总共数据
      tabList: [],
      // 多选按钮数据
      feedbackduoxuanList: [],
      feedbackduoxuanList2: [],
      feedbackduoxuanList3: [],
      flag: false,
      // 单选按钮绑定值
      radio: "",
      radio2: "",
      radio3: "",
      // 单选按钮选中的值
      radioVal: "",
      radioVal2: "",
      radioVal3: "",
      // 多选按钮绑定值
      checkboxGroup: [],
      checkboxGroup2: [],
      checkboxGroup3: [],
      onlyShowAnswer: false,
      onlyShowAnswer2: false,
      onlyShowAnswer3: false,
      onlyShowAnswerFlag: "",
      onlyShowAnswerFlag2: "",
      onlyShowAnswerFlag3: "",
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
      // // 回复个数
      answerCount: "",
      answerCount2: "",
      answerCount3: "",
      // // 问题个数
      questionCount: "",
      questionCount2: "",
      questionCount3: "",
      // 是否展示全部问题
      isShowAll: true,
      // 默认有moreText类
      isMoreText: true,
      showLength: 15,
      showLength2: 15,
      showLength3: 15,
      allQuestionList: [],
      allQuestionList2: [],
      allQuestionList3: [],
      showMore: false,
      showMore2: false,
      showMore3: false,
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
  // 滑轮滚到底部懒加载
  mounted() {
    // let _this = this;
    // // 注册scroll事件并监听
    // window.addEventListener("scroll", function() {
    //   let scrollTop =
    //     window.pageYOffset ||
    //     document.documentElement.scrollTop ||
    //     document.body.scrollTop; //滚动高度
    //   // console.log(document.documentElement.clientHeight+'-----------'+window.innerHeight); // 可视区域高度
    //   // console.log('222',document.body.offsetHeight); // 文档高度
    //   // 判断是否滚动到底部
    //   if (scrollTop + window.innerHeight + 2 >= document.body.offsetHeight) {
    //     this.flagLoading = true;
    //     _this.showMoreMethods();
    //   }
    // });
  },
  methods: {
    // // 单选按钮
    // handelChange(val) {
    //   this.radioVal = val;
    //   this.checkboxGroup = [];
    //   if (val == null) {
    //     this.initQuestionData(
    //       this.o_letterId,
    //       "",
    //       this.checkboxGroup,
    //       this.onlyShowAnswerFlag
    //     );
    //   } else {
    //     this.initQuestionData(
    //       this.o_letterId,
    //       val,
    //       this.checkboxGroup,
    //       this.onlyShowAnswerFlag
    //     );
    //   }
    // },
    // // 单选按钮
    // handelChange2(val) {
    //   this.radioVal2 = val;
    //   this.checkboxGroup2 = [];
    //   if (val == null) {
    //     this.initQuestionData(
    //       this.o_letterId,
    //       "",
    //       this.checkboxGroup2,
    //       this.onlyShowAnswerFlag2
    //     );
    //   } else {
    //     this.initQuestionData(
    //       this.o_letterId,
    //       val,
    //       this.checkboxGroup2,
    //       this.onlyShowAnswerFlag2
    //     );
    //   }
    // },
    // // 单选按钮
    // handelChange3(val) {
    //   this.radioVal3 = val;
    //   this.checkboxGroup3 = [];
    //   if (val == null) {
    //     this.initQuestionData(
    //       this.o_letterId,
    //       "",
    //       this.checkboxGroup3,
    //       this.onlyShowAnswerFlag3
    //     );
    //   } else {
    //     this.initQuestionData(
    //       this.o_letterId,
    //       val,
    //       this.checkboxGroup3,
    //       this.onlyShowAnswerFlag3
    //     );
    //   }
    // },
    // // 多选按钮
    // handelMoreChange(val) {
    //   //  this.checkboxGroup = val
    //   console.log("checkgroup", this.checkboxGroup);
    //   for (let i = 0; i < val.length; i++) {
    //     //  如果点击了多选按钮‘全部’,就将绑定的数组变成【null】,然后重新请求数据，传空数组[]
    //     if (val[i] == null) {
    //       this.checkboxGroup = [null];
    //       if (this.radioVal == null) {
    //         this.initOnlyQuestionData(
    //           this.o_letterId,
    //           "",
    //           [],
    //           this.onlyShowAnswerFlag
    //         );
    //       } else {
    //         this.initOnlyQuestionData(
    //           this.o_letterId,
    //           this.radioVal,
    //           [],
    //           this.onlyShowAnswerFlag
    //         );
    //       }
    //     } else {
    //       // 当点击其他的多选按钮时候，之前有点击过‘全部’按钮，需要将这个全部的null去掉，
    //       if (val.indexOf(null) != -1) {
    //         this.checkboxGroup = val.slice(1);
    //       } else {
    //         this.checkboxGroup = val;
    //       }
    //       if (this.radioVal == null) {
    //         this.initOnlyQuestionData(
    //           this.o_letterId,
    //           "",
    //           this.checkboxGroup,
    //           this.onlyShowAnswerFlag
    //         );
    //       } else {
    //         this.initOnlyQuestionData(
    //           this.o_letterId,
    //           this.radioVal,
    //           this.checkboxGroup,
    //           this.onlyShowAnswerFlag
    //         );
    //       }
    //     }
    //   }
    // },
    // // 多选按钮
    // handelMoreChange2(val) {
    //   this.checkboxGroup2 = val;
    //   for (let i = 0; i < val.length; i++) {
    //     if (val[i] == null) {
    //       this.checkboxGroup2 = [];
    //       document.querySelector(
    //         ".el-checkbox-button__inner:nth-of-type(1)"
    //       ).style.backgroundColor =
    //         "white";
    //     }
    //   }
    //   if (this.radioVal2 == null) {
    //     this.initOnlyQuestionData(
    //       this.o_letterId,
    //       "",
    //       this.checkboxGroup2,
    //       this.onlyShowAnswerFlag2
    //     );
    //   } else {
    //     this.initOnlyQuestionData(
    //       this.o_letterId,
    //       this.radioVal2,
    //       this.checkboxGroup2,
    //       this.onlyShowAnswerFlag2
    //     );
    //   }
    // },
    // // 多选按钮
    // handelMoreChange3(val) {
    //   this.checkboxGroup3 = val;
    //   for (let i = 0; i < val.length; i++) {
    //     if (val[i] == null) {
    //       this.checkboxGroup3 = [];
    //       document.querySelector(
    //         ".el-checkbox-button__inner:nth-of-type(1)"
    //       ).style.backgroundColor =
    //         "white";
    //     }
    //   }
    //   if (this.radioVal3 == null) {
    //     this.initOnlyQuestionData(
    //       this.o_letterId,
    //       "",
    //       this.checkboxGroup3,
    //       this.onlyShowAnswerFlag3
    //     );
    //   } else {
    //     this.initOnlyQuestionData(
    //       this.o_letterId,
    //       this.radioVal3,
    //       this.checkboxGroup3,
    //       this.onlyShowAnswerFlag3
    //     );
    //   }
    // },
    // // 是否只展示回复问题
    // handleOnlyChange(val) {
    //   if (val == true) {
    //     this.onlyShowAnswerFlag = "1";
    //   } else {
    //     this.onlyShowAnswerFlag = "";
    //   }
    //   if (this.radioVal == null) {
    //     if (this.checkboxGroup[0] == null) {
    //       this.initOnlyQuestionData(
    //         this.o_letterId,
    //         "",
    //         [],
    //         this.onlyShowAnswerFlag
    //       );
    //     } else {
    //       this.initOnlyQuestionData(
    //         this.o_letterId,
    //         "",
    //         this.checkboxGroup,
    //         this.onlyShowAnswerFlag
    //       );
    //     }
    //   } else {
    //     if (this.checkboxGroup[0] == null) {
    //       this.initOnlyQuestionData(
    //         this.o_letterId,
    //         this.radioVal,
    //         [],
    //         this.onlyShowAnswerFlag
    //       );
    //     } else {
    //       this.initOnlyQuestionData(
    //         this.o_letterId,
    //         this.radioVal,
    //         this.checkboxGroup,
    //         this.onlyShowAnswerFlag
    //       );
    //     }
    //   }
    // },
    // // 是否只展示回复问题
    // handleOnlyChange2(val) {
    //   if (val == true) {
    //     this.onlyShowAnswerFlag2 = "1";
    //   } else {
    //     this.onlyShowAnswerFlag2 = "";
    //   }
    //   if (this.radioVal2 == null) {
    //     this.initOnlyQuestionData(
    //       this.o_letterId,
    //       "",
    //       this.checkboxGroup2,
    //       this.onlyShowAnswerFlag2
    //     );
    //   } else {
    //     this.initOnlyQuestionData(
    //       this.o_letterId,
    //       this.radioVal2,
    //       this.checkboxGroup2,
    //       this.onlyShowAnswerFlag2
    //     );
    //   }
    // },
    // // 是否只展示回复问题
    // handleOnlyChange3(val) {
    //   if (val == true) {
    //     this.onlyShowAnswerFlag3 = "1";
    //   } else {
    //     this.onlyShowAnswerFlag3 = "";
    //   }
    //   if (this.radioVal3 == null) {
    //     this.initOnlyQuestionData(
    //       this.o_letterId,
    //       "",
    //       this.checkboxGroup3,
    //       this.onlyShowAnswerFlag3
    //     );
    //   } else {
    //     this.initOnlyQuestionData(
    //       this.o_letterId,
    //       this.radioVal3,
    //       this.checkboxGroup3,
    //       this.onlyShowAnswerFlag3
    //     );
    //   }
    // },
    // // 点击加载更多
    // showMoreMethods() {
    //   if (this.tabList.length == 1) {
    //     this.showLength += 15;
    //     if (this.allQuestionList.length > this.showLength) {
    //       this.flagLoading = false;
    //       this.showMore = true;
    //       this.questionList = this.allQuestionList.slice(0, this.showLength);
    //     } else {
    //       this.showMore = false;
    //       this.questionList = this.allQuestionList;
    //     }
    //   }
    //   if (this.tabList.length == 2) {
    //     this.showLength += 15;
    //     if (this.allQuestionList.length > this.showLength) {
    //       this.showMore = true;
    //       this.questionList = this.allQuestionList.slice(0, this.showLength);
    //     } else {
    //       this.showMore = false;
    //       this.questionList = this.allQuestionList;
    //     }
    //     this.showLength2 += 15;
    //     if (this.allQuestionList2.length > this.showLength2) {
    //       this.showMore2 = true;
    //       this.questionList2 = this.allQuestionList2.slice(0, this.showLength2);
    //     } else {
    //       this.showMore2 = false;
    //       this.questionList2 = this.allQuestionList2;
    //     }
    //   }
    //   if (this.tabList.length == 3) {
    //     this.showLength += 15;
    //     if (this.allQuestionList.length > this.showLength) {
    //       this.showMore = true;
    //       this.questionList = this.allQuestionList.slice(0, this.showLength);
    //     } else {
    //       this.showMore = false;
    //       this.questionList = this.allQuestionList;
    //     }
    //     this.showLength2 += 15;
    //     if (this.allQuestionList2.length > this.showLength2) {
    //       this.showMore2 = true;
    //       this.questionList2 = this.allQuestionList2.slice(0, this.showLength2);
    //     } else {
    //       this.showMore2 = false;
    //       this.questionList2 = this.allQuestionList2;
    //     }
    //     this.showLength3 += 15;
    //     if (this.allQuestionList3.length > this.showLength3) {
    //       this.showMore3 = true;
    //       this.questionList3 = this.allQuestionList3.slice(0, this.showLength3);
    //     } else {
    //       this.showMore3 = false;
    //       this.questionList3 = this.allQuestionList3;
    //     }
    //   }
    // },
    // 获取单选按钮数据
    initTableData() {
      // 动态传id
      const param = {
        id: this.caseId
      };
      getSelectFeedbackList(param).then(res => {
         // 第一个tab
        if (res.data.result && res.data.result.length > 0) {
          this.o_letterId = res.data.result[0].letterId;
          this.tabList = res.data.result;
          this.activeName = this.tabList[0].letterId;
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
            this.answerCount = res.data.result[0].answerCount;
            this.questionCount1 = res.data.result[1].questionCount;
            this.answerCount1 = res.data.result[1].answerCount;
            this.questionCount2 = res.data.result[2].questionCount;
            this.answerCount2 = res.data.result[2].answerCount;
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
      console.log('子组件调用父组件方法',param)
      getSelectQuestionList(param).then(res => {
        // 当只有一个tab页时
        if (this.tabList.length == 1) {
          if (res.data.result.length > 0) {
            this.allQuestionList = res.data.result[0].questionList;
            this.questionCount = res.data.result[0].questionCount;
            this.answerCount = res.data.result[0].answerCount;
            if (ifReset != "0") {
              this.feedbackduoxuanList = res.data.result[0].questionLabelList;
              console.log('多选数据',this.feedbackduoxuanList)
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
      });
    },
    // 点击二级菜单过滤出问题列表
    initOnlyQuestionData(letterId, firstLabelId, secondLabelId, onlyResponse) {
      console.log("多选按钮参数", secondLabelId);
      // debugger
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
      console.log("param", param);
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
      });
    },
    // 点击tab页
    handleTabClick(tab, event) {
      this.o_letterId = tab.name;
    },
   
    // // 问【收起展开】
    // spread(item) {
    //   this.$set(item, "isSpread", 2);
    // },
    // packUp(item) {
    //   this.$set(item, "isSpread", 1);
    // },
    // // 答【收起展开】
    // daSpread(item) {
    //   this.$set(item, "isSpreada", 2);
    // },
    // daPackUp(item) {
    //   this.$nextTick(() => {
    //     this.$set(item, "isSpreada", 1);
    //   });
    // },
    // getContent(data, title, index, type) {
    //   // 5行的宽度和
    //   let width = (document.getElementById("componentId").offsetWidth - 48) * 5;
    //   // 所有字数的宽度【14是字体大小】
    //   let titleLength = title.length * 14;
    //   let length = 0;
    //   if (titleLength > width) {
    //     for (let i = 0; i < title.length; i++) {
    //       length += 14;
    //       if (length > width) {
    //         if (type === "answer") {
    //           if (!data.isSpread || data.isSpread === 0) {
    //             this.$set(data, "isSpread", 1);
    //           }
    //         } else {
    //           if (!data.isSpreada || data.isSpreada === 0) {
    //             this.$set(data, "isSpreada", 1);
    //           }
    //         }
    //         return title.substring(0, i - 4) + "...";
    //       }
    //     }
    //   } else {
    //     if (type === "answer") {
    //       if (!data.isSpread || data.isSpread === 0) {
    //         this.$set(data, "isSpread", 0);
    //       }
    //     } else {
    //       if (!data.isSpreada || data.isSpreada === 0) {
    //         this.$set(data, "isSpreada", 0);
    //       }
    //     }
    //     return title;
    //   }
    // }
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


