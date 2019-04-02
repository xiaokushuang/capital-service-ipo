<template>
<div>
     <!-- IPO标题头部 -->
    <div  ref="titleHeader"
      id="titleHeader"
      style="width: 100%;height: 140px;padding-top: 18px;">
      <div id="titleBody" ref="titleBody" style="{'width':'1200px','margin':' 0 auto'}">
        <div class="bottomContent" style="position:relative">
          <div v-if="headList.iecResult&&headList.iecResult == '00'" style="position: absolute;left: 79px; top: -35px;">
            <img src="../../assets/images/htg1.png" alt="">
          </div>
           <div v-if="headList.iecResult&&headList.iecResult == '01'" style="position: absolute;left: 79px; top: -35px;">
            <img src="../../assets/images/whtg1.png" alt="">
          </div>
          <div v-if="headList.iecResult&&headList.iecResult == '02'" style="position: absolute;left: 79px; top: -35px;">
            <img src="../../assets/images/zhbj1.png" alt="">
          </div>
          <div v-if="headList.iecResult&&headList.iecResult == '03'" style="position: absolute;left: 79px; top: -35px;">
            <img src="../../assets/images/qxsh1.png" alt="">
          </div>
          <div class="text">
            <p>{{headList.title}}</p>
            <span>股份公司建设时间：</span>
            <span>{{headList.establishDate}}</span>&nbsp;&nbsp;
            <span>辅导工作历时：</span>
            <span>{{headList.supportDuration?headList.supportDuration:'- -'}}</span>&nbsp;&nbsp;
            <span>申报审核历时：</span>
            <span>{{headList.auditDuration?headList.auditDuration:'- -'}}</span>&nbsp;&nbsp;
            <span>最新进程：</span>
            <span style="color:#fff;opacity:1">{{headList.processLabel}}</span>
          </div>
          <div class="btn">
            <span v-if="headList.greenPassage">{{headList.greenPassage}}</span>
          </div>
        </div>
      </div>
    </div>
    <div id="concentBody" style="width:1200px;margin: 0 auto;">
        <el-row :gutter="24" class="h100">
            <el-col :span="18" class="chart" style="padding:0 40px 0 0">
                <div class='chart-container'>
                    <div class="el-tabs el-tabs--top" style="margin: 0 0 15px;width:100%">
                        <div id="headerTop" :class="['is-top title-fix', {'is-fixed': isFixed}]" :style="isFixed ? 'position: fixed; left: 0px; top: 0px;' : ''">
                          <!-- 吸顶效果 -->
                            <div v-if="isFixed" :style="{ 'width': '62px','height': '40px', 'margin-top': 'auto','margin-right': '40px', 'margin-bottom': 'auto', 'margin-left': clientLeft}">
                                <span style="font-size: 20px;color: #333;display: block;margin-bottom: 10px;">IPO</span>
                                <span style="font-size: 14px;color: #333;">IPO进程</span>
                            </div>
                            <div style="width:100%">
                              <!-- 菜单导航栏 -->
                                <div class="el-tabs__nav-wrap is-top">
                                    <div class="el-tabs__nav-scroll">
                                        <div class="el-tabs__nav">
                                            <div class="el-tabs__active-bar is-top" :style="{width: tabBarWidth + 'px', transform: 'translateX(' + tabBarOffset + 'px)'}"></div>
                                            <div id="tab-first"  ref="tab-first"  aria-controls="pane-first"  :class="['el-tabs__item is-top', {'is-active': isActive === '1'}]" @click="onTabClick('1', $event)" style="padding-left: 0">公司概览</div>
                                            <div id="tab-second" ref="tab-second" aria-controls="pane-second" :class="['el-tabs__item is-top', {'is-active': isActive === '2'}]" @click="onTabClick('2', $event)">财务信息</div>
                                            <div v-if="headList.haveFeedback=='2' || headList.haveFeedback=='1'" id="tab-third"  ref="tab-third" class="el-tabs__item1" aria-controls="pane-third" style="cursor:default;color:gray">反馈意见</div>
                                            <div v-if="headList.haveFeedback=='0'" id="tab-third"  ref="tab-third" aria-controls="pane-third"  :class="['el-tabs__item is-top', {'is-active': isActive === '3'}]" @click="onTabClick('3', $event)">反馈意见</div>
                                            <div v-if="headList.haveExamine=='1'" id="tab-fourth" ref="tab-fourth" aria-controls="pane-fourth" class="el-tabs__item1"  style="padding-right: 0;cursor:default;color:gray">审核结果及关注问题</div>
                                            <div v-if="headList.haveExamine=='0'" id="tab-fourth" ref="tab-fourth" aria-controls="pane-fourth" :class="['el-tabs__item is-top', {'is-active': isActive === '4'}]" @click="onTabClick('4', $event)" style="padding-right: 0">审核结果及关注问题</div>
                                            <div id="tab-fifth"  ref="tab-fifth"  aria-controls="pane-fifth"  :class="['el-tabs__item is-top', {'is-active': isActive === '5'}]" @click="onTabClick('5', $event)">发行概况</div>
                                        </div>
                                    </div>
                                </div>
                                <!-- 菜单栏下面的小分类 -->
                                <!-- 1 -->
                                <div id="title-first" class="title-body" v-show="isActive == '1'">
                                    <span v-for="(item, index) in tabFirstList">
                                        <a :id="item.id + 'caseDetails'" href="javascript:void(0)" :class="['title-list',{'item-active': itemActiveFirst === item.id}, {'disabled': item.noClick}]" :title="item.notes" :style="{'font-weight': item.important ? 'bold' : 'normal '}" @click="jump(item.id, 1)">{{item.name}}</a>
                                        <span style="margin: 0 8px;color: #e4e4e4;" v-if="index < tabFirstList.length - 1">|</span>
                                    </span>
                                </div>
                                <!-- 2 -->
                                <div id="title-second" class="title-body" v-show="isActive == '2'">
                                  <span v-for="(item, index) in tabSecondList">
                                        <a :id="item.id + 'caseDetails'" href="javascript:void(0)" :class="['title-list',{'item-active': itemActiveSecond === item.id}, {'disabled': item.noClick}]" :title="item.notes" :style="{'font-weight': item.important ? 'bold' : 'normal '}" @click="jump(item.id, 2)">{{item.name}}</a>
                                        <span style="margin: 0 8px;color: #e4e4e4;" v-if="index < tabSecondList.length - 1">|</span>
                                  </span>
                                </div>
                                <!-- 3 -->
                                <div id="title-third" class="title-body" v-show="isActive == '3'"><br/>
                                </div>
                                <!-- 4 -->
                                <div id="title-fourth" class="title-body" v-show="isActive == '4'"><br/>
                                </div>
                                <!-- 5 -->
                                <div id="title-fifth" class="title-body" v-show="isActive == '5'">
                                    <span v-for="(item, index) in tabFifthList">
                                    <a :id="item.id + 'caseDetails'" href="javascript:void(0)" :class="['title-list',{'item-active': itemActiveFifth === item.id}, {'disabled': item.noClick}]" :title="item.notes" :style="{'font-weight': item.important ? 'bold' : 'normal '}" @click="jump(item.id, 5)">{{item.name}}</a>
                                    <span style="margin: 0 8px;color: #e4e4e4;" v-if="index < tabFifthList.length - 1">|</span>
                                    </span>
                                </div>
                                </div>
                            </div>
                            <!-- 点击不同菜单展示不同下面内容 -->
                            <div class="el-tabs__content">
                                <!-- 动态加载tab -->
                              <keep-alive>
                                <component :is = "showComponent" id="componentId" v-on:headCallBack="headCall" :headList="this.headList"></component>
                              </keep-alive>
                            </div>
                        </div>
                    </div>
            </el-col>
            <el-col :span="6" class="chart" style="position:relative;padding-top: 6px;">
                <div class="headClass">
                    <el-row>
                        <el-col :span="14">
                            <span>IPO进程</span>
                        </el-col>
                        <span v-if="statusButtonFlag == '1'">
                            <el-col :span="5" style="text-align: right;">
                              <span class="mmpClass" v-if="expandAllflag" @click="expandAll(false)">收起全部</span>
                              <span class="mmpClass" v-else @click="expandAll(true)">展开全部</span>
                            </el-col>
                            <el-col :span="1">
                                <span style="text-align: center;color: #14bcf5;padding-left: 4px;">|</span>
                            </el-col>
                            <el-col :span="4">
                                <span class="mmpClass" v-if="flag =='1'" @click="sortTime('02')">排序 ↓</span>
                                <span class="mmpClass" v-else @click="sortTime('01')">排序 ↑</span>
                            </el-col>
                        </span>
                     </el-row>
                 </div>
                    <span style="padding: 0px;">
                        <processTree ref="rightModule" @statusButtonClick="statusButtonClick" :caseId="caseId"
                        ></processTree>
                    </span>
                </el-col>
    <el-col :span="24">
        <!-- <a href="#top1"  title="回到顶部">11111</a> -->
        <!-- <a href="javascript:scroll(0, 0)" title="返回顶部">返回顶部</a> -->
    </el-col>
    </el-row>
</div>
</div>
</template>

<script>
import { getHeadData } from "@/api/ipoCase/companyProfile";
// 导入导航栏五个组件
import companyProfile from "../navMenu/companyProfile/companyProfile.vue";
import financialInformation from "../navMenu/financialInformation/financialInformation";
import feedback from "../navMenu/feedback/feedback";
import result from "../navMenu/result/result";
import issue from "../navMenu/issue/issue";
import processTree from "../navMenu/processTree";
export default {
  name: "ipo",
  components: {
  // 导入导航栏五个组件
    companyProfile,
    financialInformation,
    feedback,
    result,
    issue,
    processTree
  },
  data() {
    return {
      caseId2:this.$store.state.app.caseId,
      // 动态加载组件
      companyProfile:companyProfile,
      financialInformation:financialInformation,
      feedback:feedback,
      result:result,
      processTree:processTree,
      showComponent:companyProfile,

      fixBody: "",
      headerFix: false,
      scrollTop: "",
      isFixed: false,
      itemActiveFirst: "",
      itemActiveSecond: "",
      itemActiveThird: "",
      itemActiveFourth: "",
      itemActiveFifth: "",
      tabFirstList: [
        {
          id:'1',
          name:'股权结构图',
          // tabId: 'tab-first',
          // noClick: false
        },
         {
          id:'2',
          name:'主营业务收入构成'
        },
         {
          id:'3',
          name:'前五名供应商及用户'
        },
         {
          id:'4',
          name:'募集资金运用'
        },
         {
          id:'5',
          name:'中介机构'
        },
      ],
      tabSecondList:[
         {
          id:'1',
          name:'主要财务数据',
          // tabId: 'tab-first',
          // noClick: false
        },
         {
          id:'2',
          name:'同行业毛利率对比'
        },
         
      ],
      tabThreeList: [
       
      ],
      tabFourthList: [
        
      ],
      tabFifthList: [
         {
          id:'1',
          name:'发行数据'
        },
         {
          id:'2',
          name:'发行费用'
        },
       
      ],
      isActive: "1",
      tabBarWidth: "",
      tabBarOffset: "",
      statusButtonFlag: "1",
      expandAllflag: false,
      flag: "1",
      activeName: "first",
      titleName: "",
      shortName: "",
      lableName: "",
      caseId: "",
      allTime: "",
      time: "",
      clientLeft: "",
      topHeight: "",
      headList:[]
    };
  },
  methods: {
    initTableData() {
       // 动态传id
      const param = {
        id:this.caseId2
      }
       getHeadData(param).then(res => {
         this.headList = res.data.result
      }) 
    },
    statusButtonClick(data) {
      this.statusButtonFlag = data;
    },
    // 展开全部
    expandAll(flag) {
      this.expandAllflag = !this.expandAllflag
      this.$refs.rightModule.expandAlltoC(flag);
    },
    sortTime(sortType) {
      if(sortType == '02'){
        this.flag = "0";
        sortType = '01';
      }else {
        this.flag = "1";
        sortType = '02';
      }
      this.$refs.rightModule.orderByProcess(sortType)
    },
    handleClick() {
      if (this.activeName == "fourth") {
        this.$refs.comshow.changeCart();
      }
    },
      onTabClick(isActive, event) {
            let that = this
            this.isActive = isActive;
            if (isActive === '1' || isActive === '4') {
                this.tabBarWidth = event.currentTarget.offsetWidth - 20;
            } else {
                this.tabBarWidth = event.currentTarget.offsetWidth - 40;
            }
            if (isActive != '1') {
                this.tabBarOffset = event.currentTarget.offsetLeft + 20;
            } else {
                this.tabBarOffset = event.currentTarget.offsetLeft;
            }
            let targetList
            setTimeout(function () {
                switch (isActive) {
                    case '1':
                        that.$refs.rightModule.treeListMethods(false);
                        targetList = document.getElementById('title-first').children;
                        let firstFlag = 0;
                        that.showComponent = companyProfile
                        for (let i = 0; i < targetList.length; i++) {
                            if ((that.itemActiveFirst + 'caseDetails') === targetList[i].children[0].getAttribute('id')) {
                                document.documentElement.scrollTop = document.getElementById(that.itemActiveFirst).offsetTop + document.getElementById('titleHeader').offsetHeight + 56;
                                firstFlag = 1;
                            }
                        }
                        if (firstFlag === 0) {
                            var scrollhight = document.getElementById('titleHeader').offsetHeight + 56;
                            if (document.documentElement.scrollTop > scrollhight) {
                                document.documentElement.scrollTop = scrollhight;
                            }
                        }
                        break
                    case '2':
                        that.$refs.rightModule.treeListMethods(false);
                        targetList = document.getElementById('title-second').children;
                        let secondFlag = 0;
                        that.showComponent = financialInformation
                        // that.$refs.finance.creatEchart()
                        // alert("111")
                        for (let i = 0; i< targetList.length; i ++) {
                          if ((that.itemActiveSecond + 'caseDetails') === targetList[i].children[0].getAttribute('id')) {
                            document.documentElement.scrollTop = document.getElementById(that.itemActiveSecond).offsetTop + document.getElementById('titleHeader').offsetHeight + 56;
                            secondFlag = 1;
                          }
                        }
                        if (secondFlag === 0) {
                            var scrollhight = document.getElementById('titleHeader').offsetHeight + 56;
                            if (document.documentElement.scrollTop > scrollhight) {
                                document.documentElement.scrollTop = scrollhight;
                            }
                        }
                        break
                    case '3':
                        that.$refs.rightModule.treeListMethods(false);
                        targetList = document.getElementById('title-third').children;
                        let thirdFlag = 0
                        that.showComponent = feedback
                        
                        // 第三tab页暂不需要锚点定位先注掉
                        // for (let i = 0; i < targetList.length; i++) {
                        //     if ((that.itemActiveThird + 'caseDetails') === targetList[i].children[0].getAttribute('id')) {
                        //         document.documentElement.scrollTop = document.getElementById(that.itemActiveThird).offsetTop + document.getElementById('titleHeader').offsetHeight + 56;
                        //         thirdFlag = 1;
                        //     }
                        // }
                        if (thirdFlag === 0) {
                            var scrollhight = document.getElementById('titleHeader').offsetHeight + 56;
                            if (document.documentElement.scrollTop > scrollhight) {
                                document.documentElement.scrollTop = scrollhight;
                            }
                        }
                        break
                    case '4':
                        that.$refs.rightModule.treeListMethods(false);
                        targetList = document.getElementById('title-fourth').children;
                        let fourthFlag = 0
                        that.showComponent = result

                        // 第四tab页暂不需要锚点定位先注掉
                        // for (let i = 0; i < targetList.length; i++) {
                        //     if ((that.itemActiveFourth + 'caseDetails') === targetList[i].children[0].getAttribute('id')) {
                        //         document.documentElement.scrollTop = document.getElementById(that.itemActiveFourth).offsetTop + document.getElementById('titleHeader').offsetHeight + 56;
                        //         fourthFlag = 1
                        //     }
                        // }
                        if (fourthFlag === 0) {
                            var scrollhight = document.getElementById('titleHeader').offsetHeight + 56;
                            if (document.documentElement.scrollTop > scrollhight) {
                                document.documentElement.scrollTop = scrollhight;
                            }
                        }
                        break
                         case '5':
                        //  最后tab页形态不一样
                        that.$refs.rightModule.treeListMethods(true);
                        // console.log('tab999',that.showComponent)
                        targetList = document.getElementById('title-fifth').children;
                        let fifthFlag = 0;
                                               
                        that.showComponent = issue
                        // 点击最后tab页，进程树展示不同内容
                        
                        for (let i = 0; i < targetList.length; i++) {
                            if ((that.itemActiveFifth + 'caseDetails') === targetList[i].children[0].getAttribute('id')) {
                                document.documentElement.scrollTop = document.getElementById(that.itemActiveFifth).offsetTop + document.getElementById('titleHeader').offsetHeight + 56;
                                fifthFlag = 1;
                            }
                        }
                        if (fifthFlag === 0) {
                            var scrollhight = document.getElementById('titleHeader').offsetHeight + 56;
                            if (document.documentElement.scrollTop > scrollhight) {
                                document.documentElement.scrollTop = scrollhight;
                            }
                        }
                        break
                    default:
                        break
                }
            }, 100);

        },
    handleScroll() {
      let scrollTop =
        window.parent.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop;
      let offsetTop = document.querySelector("#titleHeader").offsetHeight + 10;
      let fixBody = document.querySelector("#headerTop").offsetHeight + 10;
      let clientLeft = window.getComputedStyle(
        document.querySelector("#concentBody"),
        null
      )["margin-left"];
      this.scrollTop = scrollTop;
      if (scrollTop > offsetTop) {
        this.isFixed = true;
        this.headerFix = true;
        this.fixBody = fixBody;
        this.clientLeft = clientLeft;
      } else {
        this.isFixed = false;
        this.headerFix = false;
      }
    },
    headCall(param) {
      if (param != null && param != undefined && param.length > 0) {
        switch (param[0].tabId) {
          case "tab-first":
            this.tabFirstList = param;
            break;
          case "tab-second":
            this.tabSecondList = param;
            break;
          case "tab-fifth":
            this.tabFifthList = param;
            break;
          default:
            break;
        }
      }
    },
    jump(param, num) {
            document.documentElement.scrollTop = document.getElementById(param).offsetTop + document.getElementById('titleHeader').offsetHeight - 50 ;
            switch (num) {
                case 1:
                    this.itemActiveFirst = param;
                    break
                case 2:
                    this.itemActiveSecond = param;
                    break
                case 5:
                    this.itemActiveFifth = param;
                    break
                default:
                    break
            }
        },

    styleInit() {
      var h1 = this.$refs.titleHeader.offsetHeight;
      var h2 = this.$refs.titleBody.offsetHeight;
      this.topHeight =
        (parseInt(String(h1).split("px")[0]) -
          parseInt(String(h2).split("px")[0])) /
        2;
    }
  },
  computed: {
  },

  props: {
  },
  watch: {
    caseId(n, o) {}
  },
  created() {
    this.initTableData()
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll);
    this.styleInit();
    if (this.isActive === "1" || this.isActive === "4") {
      if (this.isActive === "1") {
        this.tabBarWidth =
          document.getElementById("tab-first").offsetWidth - 20;
      } else {
        this.tabBarWidth =
          document.getElementById("tab-fourth").offsetWidth - 20;
      }
    } else {
      if (this.isActive === "2") {
        this.tabBarWidth =
          document.getElementById("tab-second").offsetWidth - 40;
      } else {
        this.tabBarWidth =
          document.getElementById("tab-third").offsetWidth - 40;
      }
    }
    if (this.isActive != "1") {
      if (this.isActive === "2") {
        this.tabBarOffset =
          document.getElementById("tab-second").offsetLeft + 20;
      } else if (this.isActive === "3") {
        this.tabBarOffset =
          document.getElementById("tab-third").offsetLeft + 20;
      } else {
        this.tabBarOffset =
          document.getElementById("tab-fourth").offsetLeft + 20;
      }
    } else {
      // this.tabBarOffset = document.getElementById$("tab-first").offsetLeft;
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
// footer样式
.commonFooter {
  width: 100%;
  height: 40px;
  background: inherit;
  background-color: rgba(55, 61, 65, 1);
  padding-left: 20px;
  span {
    margin-left: 10px;
    font-weight: 500;
    font-style: normal;
    font-size: 12px;
    color: rgba(153, 153, 153, 0.647058823529412);
    line-height: 40px;
  }
}
// 头部背景图
#titleHeader {
  // background:blue;
  background-image: url("../../assets/images/IpoHeader.png");
}
/* 头部文字标题 */
.bottomContent {
  width: 1200px;
  color: #fff;
  padding-left: 165px;
  p {
    font-size: 24px;
    line-height: 0;
  }
  .text {
    margin-bottom: 8px;
    margin-left:7px;
    position:relative;
    z-index:10;
    span {
      font-size: 14px;
      opacity: 0.6;
      position:relative;
      z-index:10;
      
    }
  }
  .btn span { 
    display: inline-block;
    background: #ff9900;
    font-size: 12px;
    color: #fff;
    border-radius: 2px;
    padding: 4.5px;
    margin-left:7px;
    position:relative;
    z-index:10;
  }
}
.chart-container {
  position: relative;
  padding: 10px 0px 0px;
  width: 100%;
  /* height:85vh; */
}

.dialogtitle {
  font-family: "MicrosoftYaHei", "Microsoft YaHei";
  font-weight: 400;
  font-style: normal;
  font-size: 20px;
  width: 75%;
  color: #333;
  /* margin-left: 10px; */
  padding-right: 12px;
}

.dialogtitle > .spanLable {
  /* border: 1px #FF4040 solid !important; */
  color: #ffffff !important;
  background: #ff9900 !important;
  height: 22px;
  border-radius: 2px !important;
  font-size: 12px !important;
  padding: 0px 4px;
  position: relative;
  left: 10px;
}

#toTop {
  /* display: none; */
  position: fixed;
  right: 10px;
  bottom: 30px;
  background-color: #b7b7b7;
  height: 46px;
  line-height: 40px;
  width: 40px;
  transition: all 0.4s ease 0.1s;
}

#toTop:hover {
  background-color: #424242;
}

#toTop span {
  position: relative;
  /* 相对定位，以便其伪元素绝对定位 */
  top: 5px;
  left: 15px;
  /* 变换为顺时针旋转 30°，通过数学角度计算后适当调整位置 */
  display: inline-block;
  width: 3px;
  height: 20px;
  background-color: #fff;
  border-radius: 3px;
  -webkit-transform: rotate(30deg);
  -ms-transform: rotate(30deg);
  transform: rotate(30deg);
}

#toTop span:after {
  content: "";
  position: absolute;
  top: -5px;
  left: 8px;
  display: inline-block;
  width: 3px;
  height: 20px;
  background-color: #fff;
  border-radius: 3px;
  -webkit-transform: rotate(-60deg);
  -ms-transform: rotate(-60deg);
  transform: rotate(-60deg);
}

.chart-container .el-tabs__item.is-active {
  color: #0086a7;
}

.chart-container .el-tabs__item:hover {
  color: #0086a7;
  cursor: pointer;
}

.chart-container .el-tabs__item:hover {
  color: #0086a7;
  cursor: pointer;
}

.chart-container .el-tabs__active-bar {
  background-color: #0086a7;
}

.chart-container .el-tabs__active-bar {
  color: #0086a7;
}

.chart-container .el-tabs__header {
  padding-right: 12px;
}

/* ('el-tabs__active-bar')[0].style = 'width: 56px; transform: translateX(0px);' */
.el-tabs__active-bar {
  width: 56px;
  transform: translateX(0px);
}

.h100 {
  margin-left: 0px !important;
  margin-right: 0px !important;
}

.headClass {
  margin-top: 18px;
  color: #333;
  font-size: 16px;
  text-rendering: optimizeLegibility;
  border-bottom: 2px solid #e4e7ed;
  padding-bottom: 8px;
}

.mmpClass {
  font-size: 12px;
  color: #14bcf5;
}

.mmpClass:hover {
  cursor: pointer;
  text-decoration: underline;
  text-decoration-color: #14bcf5;
}

.el-tabs__item {
  color: #333;
}
.el-tabs__item1 {
    padding: 0 20px;
    height: 40px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    line-height: 40px;
    display: inline-block;
    list-style: none;
    font-size: 14px;
    font-weight: 500;
    position: relative;
}

.el-tabs__active-bar {
  background-color: #14bcf5 !important;
}

.el-tabs__item:hover {
  color: #14bcf5 !important;
}

.el-tabs__item.is-active {
  color: #14bcf5 !important;
}
.el-tabs__item1:hover {
  color: gray !important;
}

.el-tabs__item1.is-active {
  color: gray !important;
}

.title-body {
  margin: 12px 0 0;
}

.title-list {
  font-size: 12px;
  color: #666;
}

.title-list:hover {
  color: #14bcf5;
}

.item-active {
  color: #14bcf5 !important;
}

.is-fixed {
  position: fixed;
  left: 0px;
  top: 0px;
  z-index: 999;
  box-shadow: 0 1px 4px 0 rgba(0, 0, 0, 0.12);
  background-color: #fff;
  width: 100%;
  padding: 0 0 10px;
  transform: translateZ(0);
  -webkit-transform: translateZ(0);
}

.disabled {
  color: #c1c1c1 !important;
  cursor: default !important;
  pointer-events: none;
}

.title-fix {
  display: flex;
}
#title-fourth{
  display:none
}
#title-third{
  display:none
}
</style>
