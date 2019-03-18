<template>
<div style="">
    <div style="width: 100%;height: 46px;background: #343b4a;line-height: 46px;">
        <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB8AAAAaCAYAAABPY4eKAAABS2lUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4KPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNS42LWMxNDIgNzkuMTYwOTI0LCAyMDE3LzA3LzEzLTAxOjA2OjM5ICAgICAgICAiPgogPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4KICA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIi8+CiA8L3JkZjpSREY+CjwveDp4bXBtZXRhPgo8P3hwYWNrZXQgZW5kPSJyIj8+nhxg7wAAAexJREFUSInFlj1rVFEQhp9JYiSia0RELURBEMTKiKJFRNBCLGxErf0DQgptxEJMsBBU8gsUUlgZBBsLxUJtouBXodGAxMJCQkIQiZHNY3EneBGMe7PL5m2GOWfeeeZyOYcT6nngCHAUqFHoJfAdmMm8BqwGeoENwDfgPfAcGI2ISZqRul2ds9DGJepWqX3qBXVcXVDvqTubHeBVwnsbrO9Wr6VnVj3eDPxJFXjJdyl9P9R97YaH+ji979TOtsHTu98/OtWIp6PyhP9QRIwBbzI93VZ46mHGAysBf5txy0rAv2bsUf/bu9VwM85HxEK74Ys345dGilsN78v4YiXgxzLer+xs8pLpS++k2t2IpyVfrgZwI9OBiJhfTpPl3u2D6bteGVpq8jSbbG6wvku9pdbVy1V5XX/lWzNeVGeATmAa+ACMRcRUQjuAk8AgMAccjohnVeGRzdYBV4CBXJ8GRoAJYA2wg+IY9eZeDRgDbkfEo6rQRXWpN4FNwFrgTmmvBuwFFoBZiuPzGdgFnAW6gboaESHtktqjDqnz6oR6Vd3TtgFyiN3qg9Ij4pM6rJ5o5MREi4boB4aA/vIyxfP6NTAOTFI8xesUv3RbK9jlIQ6qd9WfLq26eqal8NIQ69Vz6oj6Uf2V0Cl1VD0E8BuLL6cTC+UXbQAAAABJRU5ErkJggg=="
           style="height: 20px;padding-left: 16px;vertical-align: middle;">
        <p style="display:inline-block;margin:0px;">
            <span style="color:#ffffff;margin-left:8px;font-size: 14px;opacity:0.8">易董</span><span style="font-size:16px;color:#ffffff;opacity:0.18;margin-left: 16px">|</span><span style="color:#ffffff;opacity:0.8;font-size: 14px;margin-left: 16px">资产重组详情</span>
        </p>
    </div>
     <!-- IPO标题头部 -->
    <div  ref="titleHeader"
      id="titleHeader"
      style="width: 100%;height: 140px;padding-top: 18px;">
      <div id="titleBody" ref="titleBody" style="{'width':'1200px','margin':' 0 auto'}">
        <div class="bottomContent">
          <div class="text">
            <p>百花悅邦（创业板）第一轮首次公开发行</p>
            <span>股份公司建设时间：</span>
            <span>2014年6月1日</span>
            <span>辅导工作历时：</span>
            <span>276天</span>
            <span>申报审核历时：</span>
            <span>136天</span>
            <span>最新进程</span>
            <span>上市</span>
          </div>
          <div class="btn">
            <span>享受扶贫攻坚政策</span>
            <span>试点企业</span>
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
                                            <div id="tab-third"  ref="tab-third"  aria-controls="pane-third"  :class="['el-tabs__item is-top', {'is-active': isActive === '3'}]" @click="onTabClick('3', $event)">反馈意见</div>
                                            <div id="tab-fourth" ref="tab-fourth" aria-controls="pane-fourth" :class="['el-tabs__item is-top', {'is-active': isActive === '4'}]" @click="onTabClick('4', $event)" style="padding-right: 0">审核结果及关注问题</div>
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
                                <!-- <div :is="showTab"></div> -->
                                <div id="pane-first" aria-labelledby="tab-first" class="el-tab-pane" v-show="isActive == '1'">
                                    <companyProfile :caseId="caseId" v-on:headCallBack="headCall"></companyProfile>
                                </div>
                                <div id="pane-second" aria-labelledby="tab-second" class="el-tab-pane" v-show="isActive == '2'">
                                    <financialInformation :caseId="caseId" v-on:headCallBack="headCall" ref="finance"></financialInformation>
                                </div>
                                <div id="pane-third" aria-labelledby="tab-third" class="el-tab-pane" v-show="isActive == '3'">
                                    <feedback :caseId="caseId" v-on:headCallBack="headCall"></feedback>
                                </div>
                                <div id="pane-fourth" aria-labelledby="tab-fourth" class="el-tab-pane" v-show="isActive == '4'">
                                    <result :caseId="caseId" v-on:headCallBack="headCall"></result>
                                </div>
                                <div id="pane-fifth" aria-labelledby="tab-fifth" class="el-tab-pane" v-show="isActive == '5'">
                                    <issue :caseId="caseId" v-on:headCallBack="headCall"></issue>
                                </div>
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
                              <span class="mmpClass" v-if="expandAllflag =='1'" @click="expandAll('0')">收起全部</span>
                              <span class="mmpClass" v-else @click="expandAll('1')">展开全部</span>
                            </el-col>
                            <el-col :span="1">
                                <span style="text-align: center;color: #14bcf5;padding-left: 4px;">|</span>
                            </el-col>
                            <el-col :span="4">
                                <span class="mmpClass" v-if="flag =='1'" @click="sortTime('asc')">排序 ↓</span>
                                <span class="mmpClass" v-else @click="sortTime('desc')">排序 ↑</span>
                            </el-col>
                        </span>
                     </el-row>
                 </div>
                    <span style="padding: 0px;">
                        <right-module ref="rightModule" @statusButtonClick="statusButtonClick" :caseId="caseId"></right-module>
                    </span>
                </el-col>
    <el-col :span="24">
        <!-- <a href="#top1"  title="回到顶部">11111</a> -->
        <!-- <a href="javascript:scroll(0, 0)" title="返回顶部">返回顶部</a> -->
    </el-col>
    </el-row>
</div>
 <!-- footer尾部 -->
    <div class="commonFooter clear">
      <span>技术支持：深圳价值在线信息科技股份有限公司</span>
      <span> | </span>
      <span>建议使用1280*800以上分辨率</span>
      <div class="footerImage r" style="margin-right:10px">
         <img src="../../assets/images/footer-1.gif" alt="" class="l" style="margin-right:15px;margin-top:12px">
         <img src="../../assets/images/footer-2.gif" alt="" class="l" style="margin-right:15px;margin-top:12px">
         <img src="../../assets/images/footer-3.gif" alt="" class="l" style="margin-right:15px;margin-top:12px">
      </div>
    </div>
</div>
</template>

<script>
// 导入导航栏五个组件
import companyProfile from "../navMenu/companyProfile/companyProfile.vue";
import financialInformation from "../navMenu/financialInformation/financialInformation";
import feedback from "../navMenu/feedback/feedback";
import result from "../navMenu/result/result";
import issue from "../navMenu/issue/issue";
import rightModule from "@/components/rightModule";
export default {
  name: "securitiesInit",
  components: {
  // 导入导航栏五个组件
    companyProfile,
    financialInformation,
    feedback,
    result,
    issue,
    rightModule
  },
  data() {
    return {
      // 动态加载组件
      // companyProfile:companyProfile,
      // financialInformation:financialInformation,
      // feedback:feedback,
      // result:result,
      // rightModule:rightModule,
      // showTab:companyProfile,
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
      expandAllflag: "0",
      flag: "1",
      activeName: "first",
      titleName: "",
      shortName: "",
      lableName: "",
      caseId: "",
      allTime: "",
      time: "",
      clientLeft: "",
      topHeight: ""
    };
  },
  created(){
        this.caseId = this.$route.query.caseId
        this.allTime = this.$route.query.allTime
        if(this.$route.query.isActive){
          this.isActive = this.$route.query.isActive
        }
        let param = {
            caseId: this.caseId
        }
  },
  methods: {
    statusButtonClick(data) {
      this.statusButtonFlag = data;
    },
    expandAll(flag) {
      this.expandAllflag = flag;
      this.$refs.rightModule.expandAlltoC(flag);
    },
    sortTime(sort) {
      if (sort == "asc") {
        this.flag = "0";
      } else {
        this.flag = "1";
      }
      //时间排序
      // if(sortType === '02'){
      //   sortType = '01';
      // }else {
      //   sortType = '02';
      // }
      // this.$refs.rightModule.initTableData("id",sortType);
      
      this.$refs.rightModule.sortTime("publishTime", sort);
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
                        targetList = document.getElementById('title-first').children;
                        let firstFlag = 0;
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
                        targetList = document.getElementById('title-second').children;
                        let secondFlag = 0;
                        that.$refs.finance.creatEchart()
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
                        targetList = document.getElementById('title-third').children;
                        let thirdFlag = 0
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
                        targetList = document.getElementById('title-fourth').children;
                        let fourthFlag = 0
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
                        targetList = document.getElementById('title-fifth').children;
                        let fifthFlag = 0;
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
    // jump(param, num) {
    //         console.log(document.getElementById(param+ 'caseDetails').offsetTop)
    //         document.documentElement.scrollTop = document.getElementById(param+ 'caseDetails').offsetTop + document.getElementById('titleHeader').offsetHeight + 56;
    //         switch (num) {
    //             case 1:
    //                 this.itemActiveFirst = param;
    //                 break
    //             case 3:
    //                 this.itemActiveThird = param;
    //                 break
    //             case 4:
    //                 this.itemActiveFourth = param;
    //                 break
    //             default:
    //                 break
    //         }
    //     },
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
  // created() {
  //   this.caseId = this.$route.query.caseId;
  //   this.allTime = this.$route.query.allTime;
  //   if (this.$route.query.isActive) {
  //     this.isActive = this.$route.query.isActive;
  //   }
  //   let param = {
  //     caseId: this.caseId
  //   };
  //   this.$store.dispatch("timeMethod", param).then(data => {
  //     if (data.data.result.timeD.time) {
  //       this.time = data.data.result.timeD.time;
  //     }
  //     if (data.data.result.labelTong.lableName) {
  //       this.lableName = data.data.result.labelTong.lableName;
  //     }
  //   });
  //   this.titleName = this.$route.query.titleName;
  //   this.shortName = this.$route.query.shortName;
  // },
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
    span {
      font-size: 14px;
      opacity: 0.6;
    }
  }
  .btn span {
    display: inline-block;
    background: #ff9900;
    font-size: 12px;
    color: #fff;
    border-radius: 2px;
    padding: 4.5px;
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

.el-tabs__active-bar {
  background-color: #14bcf5 !important;
}

.el-tabs__item:hover {
  color: #14bcf5 !important;
}

.el-tabs__item.is-active {
  color: #14bcf5 !important;
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
</style>
