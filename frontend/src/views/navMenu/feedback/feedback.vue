<template>
    <div class="feedback">
       <div class="label">
           <div v-if="status===1" class="clear">
                <div style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共两轮反馈：</div>
                <el-tabs v-model="activeName" @tab-click="handleClick" >
                        <el-tab-pane label="第一次反馈意见" name="first" style="font-size: 14px;color: #777777;">
                            <!-- <div style="background-color: rgba(250, 250, 250, 1);">
                                <div class="firstLabel" >
                                    <ul class="clear" style="padding:15px 25px 0 25px;margin-top:0px">
                                        <li v-for="item in feedbackFirtLabelList" class="l firstLabelFocus" style="background-color: rgba(250, 250, 250, 1);margin-right:10px;margin-bottom:10px;font-size: 12px; color: rgba(0, 0, 0, 0.647058823529412);border:none">
                                            <span>{{item.name}}</span>
                                            <span>({{item.num}})</span>
                                        </li>
                                    </ul>
                                   <ul class="clear" style="padding:0px 25px;margin-top:0px">
                                        <li v-for="item in feedbackSecondLabelList" class="l secondLabelFocus" style="margin-right:10px;margin-bottom:10px;background: inherit;
                                            background-color: rgba(245, 245, 245, 1);
                                            box-sizing: border-box;
                                            border-width: 1px;
                                            border-style: solid;
                                            border-color: rgba(217, 217, 217, 1);
                                            border-radius: 3px;
                                            -moz-box-shadow: none;
                                            -webkit-box-shadow: none;
                                            box-shadow: none;
                                            color: rgba(0, 0, 0, 0.647058823529412);
                                            padding:3px">
                                            <span>{{item.name}}</span>
                                            <span>({{item.num}})</span>
                                        </li>
                                    </ul>
                                </div>
                            </div> -->
                            <div style="background-color: rgba(250, 250, 250, 1);font-size: 14px;color: #777777;">
                                <div class="firstLabel" >
                                    <ul class="clear" style="padding:15px 25px 0 25px;margin-top:0px">
                                        <el-radio-group v-model="radio" size="small">
                                            <el-radio-button v-for="item in feedbackFirtLabelList" class="l firstLabelFocus" style="margin-right:0px;margin-bottom:0px;font-size: 12px; color: rgba(0, 0, 0, 0.647058823529412);" :label="item">{{item.name}}{{item.num}}</el-radio-button>
                                        </el-radio-group>
                                    </ul>
                                    <ul class="clear" style="padding:0px 25px;margin-top:0px">
                                        <el-checkbox-group v-model="checkboxGroup" size="mini">
                                            <el-checkbox-button class="checkbox" v-for="item in feedbackSecondLabelList" :label="item">{{item.name}}{{item.num}}</el-checkbox-button>
                                        </el-checkbox-group>
                                    </ul>
                                    <div class="kaiguan" style="text-align:center;color:#3399ff">
                                        <span>共计</span>
                                        <span>48</span>
                                        <span>个问题，</span>
                                        <span>15</span>
                                        <span>个回复</span>
                                        <span style="margin-left:25px;margin-right:15px">只展示回复问题</span>
                                        <el-switch
                                            v-model="value"
                                            active-color="#3399ff"
                                            inactive-color="gray">
                                        </el-switch>
                                    </div>
                                </div>
                                <div class="reset">重置</div>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="第二次反馈意见" name="second">
                            <div style="background-color: rgba(250, 250, 250, 1);font-size: 14px;color: #777777;">
                                <div class="firstLabel" >
                                    <ul class="clear" style="padding:15px 25px 0 25px;margin-top:0px">
                                        <el-radio-group v-model="radio" size="small">
                                            <el-radio-button v-for="item in feedbackFirtLabelList" class="l firstLabelFocus" style="margin-right:0px;margin-bottom:0px;font-size: 12px; color: rgba(0, 0, 0, 0.647058823529412);" :label="item">{{item.name}}{{item.num}}</el-radio-button>
                                        </el-radio-group>
                                    </ul>
                                    <ul class="clear" style="padding:0px 25px;margin-top:0px">
                                        <el-checkbox-group v-model="checkboxGroup" size="mini">
                                            <el-checkbox-button class="checkbox" v-for="item in feedbackSecondLabelList" :label="item">{{item.name}}{{item.num}}</el-checkbox-button>
                                        </el-checkbox-group>
                                    </ul>
                                    <div class="kaiguan" style="text-align:center;color:#3399ff">
                                        <span>共计</span>
                                        <span>48</span>
                                        <span>个问题，</span>
                                        <span>15</span>
                                        <span>个回复</span>
                                        <span style="margin-left:25px;margin-right:15px">只展示回复问题</span>
                                        <el-switch
                                            v-model="value"
                                            active-color="#3399ff"
                                            inactive-color="gray">
                                        </el-switch>
                                    </div>
                                </div>
                                <div class="reset">重置</div>
                            </div>
                        </el-tab-pane>
                </el-tabs>
           </div>
           <div v-else>
                <div style="background-color: rgba(250, 250, 250, 1);font-size: 14px;color: #777777;">
                    <div class="firstLabel" >
                        <ul class="clear" style="padding:15px 25px 0 25px;margin-top:0px">
                            <!-- <li v-for="item in feedbackFirtLabelList" class="l firstLabelFocus" style="margin-right:30px;margin-bottom:20px;font-size: 12px; color: rgba(0, 0, 0, 0.647058823529412);">
                                <span>{{item.name}}</span>
                                <span>({{item.num}})</span>
                            </li> -->
                            <el-radio-group v-model="radio" size="small">
                                <el-radio-button v-for="item in feedbackFirtLabelList" class="l firstLabelFocus" style="margin-right:0px;margin-bottom:0px;font-size: 12px; color: rgba(0, 0, 0, 0.647058823529412);" :label="item">{{item.name}}{{item.num}}</el-radio-button>
                            </el-radio-group>
                        </ul>
                        <ul class="clear" style="padding:0px 25px;margin-top:0px">
                             <el-checkbox-group v-model="checkboxGroup" size="mini">
                                <el-checkbox-button class="checkbox" v-for="item in feedbackSecondLabelList" :label="item">{{item.name}}{{item.num}}</el-checkbox-button>
                             </el-checkbox-group>
                        </ul>
                        <div class="kaiguan" style="text-align:center;color:#3399ff">
                            <span>共计</span>
                            <span>48</span>
                            <span>个问题，</span>
                            <span>15</span>
                            <span>个回复</span>
                            <span style="margin-left:25px;margin-right:15px">只展示回复问题</span>
                            <el-switch
                                v-model="value"
                                active-color="#3399ff"
                                inactive-color="gray">
                            </el-switch>
                        </div>
                    </div>
                    <div class="reset">重置</div>
                </div>
           </div>
       </div>
         <div class="question">
             <p style="font-weight: 400;
                        font-style: normal;
                        font-size: 16px;
                        color: #3399ff;">
                一、规范性问题</p>
            <ul style="padding-left:0">
                    <li style="border-bottom:1px solid lightgray;padding-bottom:15px;margin-bottom:30px">
                        <div class="text" style="background:rgba(250, 250, 250, 1); padding: 10px 24px;margin-bottom:10px;position:relative">
                            <div class="wen">问</div>
                            <!-- 问 -->
                            <p style="font-size:18px;">
                                <span class="quan" style="font-family:'Microsoft Tai Le Negreta', 'Microsoft Tai Le';font-weight:700;width:23px;height:23px;display:inline-block;text-align: center;line-height: 23px;">1</span>
                                <span style="font-family:'Microsoft Tai Le Normal', 'Microsoft Tai Le';font-weight:400;font-size:14px;">  &nbsp;</span>
                                <span style="font-family:'Microsoft Tai Le Negreta', 'Microsoft Tai Le';font-weight:700;"> </span>
                                <span style="font-family:'Microsoft Tai Le Normal', 'Microsoft Tai Le';font-weight:400;font-size:14px;">请发行人代表说明：</span>
                            </p>
                            <p style="font-size:14px;">
                                <span style="font-family:'Microsoft Tai Le Normal', 'Microsoft Tai Le';font-weight:400;"><span>1）</span>报告期是否已取得所从事各项业务所必需的全部经验资质、备案或许可等，是否存在生产经营业务范围超资质许可的情形；</span>
                            </p>
                            <p style="font-size:14px;">
                                <span style="font-family:'Microsoft Tai Le Normal', 'Microsoft Tai Le';font-weight:400;"><span>2）</span>部分股东与境外主体存在VIE结构安排，是否对发行人开展相关业务构成重大不确定性影响，相关信息披露和风险提示是否有足够充分。</span>
                            </p>
                             <p style="font-size:14px;">
                                <span style="font-family:'Microsoft Tai Le Normal', 'Microsoft Tai Le';font-weight:400;"><span>3）</span>报告期是否已取得所从事各项业务所必需的全部经验资质、备案或许可等，是否存在生产经营业务范围超资质许可的情形；</span>
                            </p>
                            <p style="font-size:14px;">
                                <span style="font-family:'Microsoft Tai Le Normal', 'Microsoft Tai Le';font-weight:400;"><span>4）</span>部分股东与境外主体存在VIE结构安排，是否对发行人开展相关业务构成重大不确定性影响，相关信息披露和风险提示是否有足够充分。</span>
                            </p>
                            <p style="font-size:14px;">
                                <span style="font-family:'Microsoft Tai Le Normal', 'Microsoft Tai Le';font-weight:400;"><span>5）</span>部分股东与境外主体存在VIE结构安排，是否对发行人开展相关业务构成重大不确定性影响，相关信息披露和风险提示是否有足够充分。</span>
                            </p>
                            <p style="font-size:14px;">
                                <span style="font-family:'Microsoft Tai Le Normal', 'Microsoft Tai Le';font-weight:400;">&nbsp;&nbsp; &nbsp;&nbsp; 请保荐代表人说明核查依据、过程并发表明确核查意见。</span>
                            </p>
                            <!-- 答 -->
                            <div class="answer" v-show="flag" style="color:#3399ff;font-size:14px;line-height: 22px;">
                                <div class="da" style="line-height:15px">答</div>
                                <p>【律师回复】：</p>
                                <p>(一)发行人历次增资及股权转让的定价依据、定价是否合理、公允，对应的PE倍数、股权转让原因（包括同批次股价不同的原因）和必要性，履行程序是否符合相关法律法规的规定、是否存在股份代持、委托持股或其他利益安排
　                                  　就该问题，本所律师通过查阅相关主体签署的增资协议和股权转让协议，查阅工商登记资料，对相关增资方和股权转受让方进行访谈，取得公司的说明和相关主体出具的确认函等方式进行了核查。
                                    1、发行人历次增资及股权转让情况、定价情况
                                </p>
                            </div>
                            <!-- 收起展开 -->
                            <div style="color: #4F91D1;font-size:14px">
                                <span class="packUp" v-if="flag" @click="packUp()">收起 <i class="el-icon-arrow-up"></i></span>
                                <span class="spread" v-else @click="spread()">展开 <i class="el-icon-arrow-down"></i></span>
                            </div>
                           
                        </div>
                        <div>
                            <span class="biaoqian" style="margin-right:2px">经营资质</span>
                            <span class="biaoqian" style="margin-right:2px">经营资质从v人</span>
                        </div>
                    </li>
            </ul>
            <!-- 加载更多 -->
            <div class="more">加载更多</div>
        </div>
   </div>

</template>
<script>
export default {
  name: "feedback",
  data() {
    return {
      status: 1,
      flag: false,
      radio: "",
      checkboxGroup: [],
      cities: [],
      feedbackFirtLabelList: [
        {
          name: "财务处理及分红",
          num: "24"
        },
        {
          name: "财务处理及分财务处红",
          num: "24"
        },
        {
          name: "财及分红",
          num: "24"
        },
        {
          name: "超额完成财务处理及分红",
          num: "24"
        },
        {
          name: "财务处理及",
          num: "24"
        },
        {
          name: "财务处理及红",
          num: "24"
        },
        {
          name: "财务处理及vfd分红",
          num: "24"
        },
        {
          name: "财及发分红",
          num: "24"
        },
        {
          name: "财务处理及",
          num: "24"
        },
        {
          name: "财务处理及分吃顿饭v额红",
          num: "24"
        },
        {
          name: "财务处理及分红",
          num: "24"
        },
        {
          name: "财务处理vce及分红",
          num: "24"
        },
        {
          name: "财务处理及分红",
          num: "24"
        }
      ],
      feedbackSecondLabelList: [
        {
          name: "财务处理及分红",
          num: "24"
        },
        {
          name: "财务处理及分财务处红",
          num: "24"
        },
        {
          name: "财及分红",
          num: "24"
        },
        {
          name: "超额完成财务处理及分红",
          num: "24"
        },
        {
          name: "财务处理及",
          num: "24"
        },
        {
          name: "财务处理及红",
          num: "24"
        },
        {
          name: "财务处理及vfd分红",
          num: "24"
        },
        {
          name: "财及发分红",
          num: "24"
        },
        {
          name: "财务处理及",
          num: "24"
        },
        {
          name: "财务处理及分吃顿饭v额红",
          num: "24"
        },
        {
          name: "财务处理及分红",
          num: "24"
        },
        {
          name: "财务处理vce及分红",
          num: "24"
        },
        {
          name: "财务处理及分红",
          num: "24"
        }
      ],
      value: false
    };
  },
  methods: {
    packUp() {
      this.flag = false;
    },
    spread() {
      this.flag = true;
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
.firstLabelFocus:focus {
  //   background: red;
  //   font-size: 12px;
  //   color: #14bcf5 !important;
  //   cursor: pointer;
  //   padding: 3px;
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
  width: 26px;
  height: 26px;
  padding: 5px;
  padding-bottom: 7px;
  color: #ffffff;
  position: absolute;
  top: 0;
  left: 0;
  font-size: 14px;
  background: url("../../../assets/images/wen.png") no-repeat;
  background-size: contain;
}
.da {
  width: 26px;
  height: 26px;
  padding: 5px;
  padding-bottom: 7px;
  color: #ffffff;
  font-size: 14px;
  background: url("../../../assets/images/wen.png") no-repeat;
  background-size: contain;
  position: relative;
  top: 38px;
  left: -23px;
}
.quan {
  background-size: contain;
  background: url("../../../assets/images/quan.png") no-repeat;
}
.reset {
  width: 45px;
  height: 23px;
  background: inherit;
  background-color: rgba(255, 255, 255, 1);
  box-sizing: border-box;
  border-width: 1px;
  border-style: solid;
  border-color: rgba(204, 204, 204, 1);
  border-radius: 3px;
  -moz-box-shadow: none;
  -webkit-box-shadow: none;
  box-shadow: none;
  font-family: "Microsoft YaHei Regular", "Microsoft YaHei";
  font-weight: 400;
  font-style: normal;
  color: #3399ff;
  text-align: center;
  line-height: 22px;
  position: relative;
  left: 85%;
  top: -20px;
  cursor: pointer;
}
.spread {
  position: relative;
  left: 94%;
}
.spread:hover {
  text-decoration: underline;
}
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
}
.checkbox {
  background: #fafafa;
  margin-right: 10px;
  margin-bottom: 10px;
  border-width: 0.1px;
  border-style: solid;
  border-color: rgba(217, 217, 217, 1);
  border-radius: 3px;
}
</style>


