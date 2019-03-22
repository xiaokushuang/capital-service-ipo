<template>
    <div class="feedback">
       <div class="label">
          <!-- 有多级标签选择 -->
           <div v-if="this.tabList.length>1" class="clear">
                <div style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共两轮反馈：</div>
                <el-tabs v-model="activeName" @tab-click="handleTabClick" >
                        <el-tab-pane v-for="item in tabList" :key="item.id" :label="item.name"  :name="item.name" style="font-size: 14px;color: #777777;">
                            <div style="background-color: rgba(250, 250, 250, 1);font-size: 14px;color: #777777;">
                                <div class="firstLabel" >
                                    <ul class="clear" style="padding:15px 25px 0 25px;margin-top:0px;padding-left:10px;">
                                        <el-radio-group v-model="radio" size="small" style="border-bottom: 1px solid rgb(235, 235, 235);padding-bottom:10px;">
                                            <el-radio-button  v-for="item in feedbackFirtLabelList" class="l firstLabelFocus" style="margin-right:10px;margin-bottom:10px;font-size: 12px; color: rgba(0, 0, 0, 0.647058823529412);" :label="item">{{item.name}}{{item.num}}</el-radio-button>
                                        </el-radio-group>
                                    </ul>
                                    <ul class="clear" style="padding:0px 25px;margin-top:0px;padding-bottom:10px">
                                        <span style="float: left;
                                          display: inline-block;
                                          margin-right: 23px;
                                          margin-top: 4px;">全部<span>23</span>
                                        </span>
                                        <el-checkbox-group v-model="checkboxGroup" size="mini">
                                            <el-checkbox-button class="checkbox" v-for="item in feedbackSecondLabelList" :label="item.name">{{item.name}}{{item.num}}</el-checkbox-button>
                                        </el-checkbox-group>
                                      </ul>
                                        <div class="kaiguan" style="text-align:left;font-size: 12px;
                                              margin-left:25px;
                                              color: #999999;
                                              text-align: left;
                                              line-height: 14px;">
                                            <span>共计</span>
                                            <span>48</span>
                                            <span>个问题，</span>
                                            <span>15</span>
                                            <span>个回复</span>
                                            <el-checkbox v-model="onlyShowAnswer" style="margin-left:20px;margin-right:15px">只展示回复问题</el-checkbox>
                                            <el-button @click="toggleSelection()" class="reset" type="primary" plain>重置</el-button>
                                        </div>
                                    
                                </div>
                            </div>
                        </el-tab-pane>
                </el-tabs>
           </div>
           <!-- 只有一级标签 -->
           <div v-else>
              <div v-for="tabItem in tabList" :key="tabItem.letterId">
                    <div style="background-color: rgba(250, 250, 250, 1);font-size: 14px;color: #777777;">
                        <div class="firstLabel" >
                            <ul class="clear" style="padding:15px 25px 0 25px;margin-top:0px;padding-left:10px;">
                                <el-radio-group v-model="radio" size="small" style="border-bottom: 1px solid rgb(235, 235, 235);padding-bottom:10px;">
                                    <el-radio-button :key="item.labelCode" v-for="item in tabItem.questionLabelList" class="l firstLabelFocus" style="margin-right:10px;margin-bottom:10px;font-size: 12px; color: rgba(0, 0, 0, 0.647058823529412);" :label="item.labelName">{{item.labelName}}{{item.labelCount}}</el-radio-button>
                                </el-radio-group>
                            </ul>
                            <ul class="clear" style="padding:0px 25px;margin-top:0px;padding-bottom:10px">
                                <span v-if="false" style="float: left;
                                  display: inline-block;
                                  margin-right: 23px;
                                  margin-top: 4px;">全部<span>23</span></span>
                                <el-checkbox-group v-model="checkboxGroup" size="mini">
                                    <el-checkbox-button class="checkbox" v-for="item in feedbackduoxuanList" :label="item.labelName">{{item.labelName}}{{item.labelCount}}</el-checkbox-button>
                                </el-checkbox-group> 
                            </ul>
                            <div class="kaiguan" style="text-align:left;font-size: 12px;
                                  margin-left:25px;
                                  color: #999999;
                                  text-align: left;
                                  line-height: 14px;">
                                <span>共计</span>
                                <span>{{questionCount}}</span>
                                <span>个问题，</span>
                                <span>{{answerCount}}</span>
                                <span>个回复</span>
                                <el-checkbox v-model="onlyShowAnswer" style="margin-left:20px;margin-right:15px">只展示回复问题</el-checkbox>
                                <el-button @click="toggleSelection()" class="reset" type="primary" plain>重置</el-button>
                            </div>
                        </div>
                    </div>
                    <div class="question" id="titleLength">
                        <p style="font-weight: 400;font-style: normal;font-size: 16px; color: #3399ff;">
                          一、规范性问题       
                        </p>          
                        <ul v-if="questionList.length>0" style="padding-left:0">
                            <li v-for="(data,index) in questionList" :key="data.questionId" style="border-bottom:1px solid #e1e1e1;padding-bottom:15px;margin-bottom:30px">
                                <div class="text" style="background:rgba(250, 250, 250, 1); padding: 10px 24px;margin-bottom:10px;position:relative">
                                    <!-- 问 -->
                                    <div class="wen">问</div>
                                    <div  style="font-size:14px;color:#333;line-height:22px">                                    
                                        <p style="width:100%;" v-if="!data.isSpread || (data.isSpread && data.isSpread !== 2)">&nbsp;&nbsp;{{getContent(data,data.question,index,'answer')}}</p>
                                        <p style="width:100%;"  v-if="data.isSpread && data.isSpread === 2">&nbsp;&nbsp;{{data.question}}</p>
                                    </div>
                                    <!-- 收起展开 -->
                                    <div class="btn" style="color: #4F91D1;font-size:14px">
                                        <span v-if="data.isSpread && data.isSpread === 2" class="packUp" @click="packUp(data)">收起 <i style="font-size:14px" class="el-icon-arrow-up"></i></span>
                                        <span v-if="data.isSpread && data.isSpread === 1" class="spread" @click="spread(data)">展开 <i style="font-size:14px" class="el-icon-arrow-down"></i></span>
                                    </div>
                                    <!-- 答 -->
                                    <div class="da">答</div>
                                    <div style="font-size:14px;color:#333;line-height:22px">
                                        <p style="width:100%;" v-if="!data.isSpreada || (data.isSpreada && data.isSpreada !== 2)">&nbsp;&nbsp;{{getContent(data,data.question,index,'question')}}</p>
                                        <p style="width:100%;"  v-if="data.isSpreada && data.isSpreada === 2">&nbsp;&nbsp;{{data.question}}</p>
                                    </div>
                                    <!-- 收起展开 -->
                                    <div class="btn" style="color: #4F91D1;font-size:14px">
                                        <span  v-if="data.isSpread && data.isSpreada === 2" class="packUp" @click="daPackUp(data)">收起 <i style="font-size:14px" class="el-icon-arrow-up"></i></span>
                                        <span  v-if="data.isSpread && data.isSpreada === 1" class="spread" @click="daSpread(data)">展开 <i style="font-size:14px" class="el-icon-arrow-down"></i></span>
                                    </div>
                                </div>
                                <div>
                                    <span v-for="biaoqian in data.labelList" class="biaoqian" style="margin-right:2px">{{biaoqian}}</span>
                                </div>
                            </li>
                        </ul>
                        <ul v-else style="padding-left:0">
                            <li v-for="(data,index) in tabItem.questionList" :key="data.questionId" style="border-bottom:1px solid lightgray;padding-bottom:15px;margin-bottom:30px">
                                
                            </li>
                        </ul>
                      <!-- 加载更多 -->
                      <div class="more">加载更多</div>
                    </div>
              </div>
           </div>
       </div>
        
   </div>

</template>
<script>
import {getSelectFeedbackList} from '@/api/companyProfile'
import {getSelectSecondLabelList} from '@/api/companyProfile'
import {getSelectQuestionListByLetterId} from '@/api/companyProfile'
import $ from "jquery";
export default {
  name: "feedback",
  data() {
    return {
      caseId1:this.$store.state.caseId,
      // 总共数据
      tabList:[],
      // 单选按钮数据
      feedbackdanxuanList: [],
      // 多选按钮数据
      feedbackduoxuanList: [],
      flag: false,
      // 单选按钮绑定值
      radio: "",
      // 单选按钮选中的值
      radioVal:'',
      // 多选按钮绑定值
      checkboxGroup: [],
      onlyShowAnswer:false,
      // 默认展示第一个label页
      activeName: 'first',
      // 函件id,通过这个id区分不同tab页
      letterId:'',
      // 所有问题列表
      questionList:[],
      // 回复个数
      answerCount:'',
      // 问题个数
      questionCount:'',
      // 是否展示全部问题
      isShowAll:true,
      // 默认有moreText类
      isMoreText:true
    };
  },
    created(){
         //   请求数据
       this.initTableData()
       this.isShowAll = true
     },
    mounted() {
      setTimeout(()=>{
        this.$nextTick(()=>{
          this.isShowAll = true
        })
      },1000)
    },
  methods: {
    // 获取单选按钮数据
     initTableData() {
        // 动态传id
        const param = {
          id:this.caseId,
        }
        getSelectFeedbackList(param).then(res => {
          this.tabList = res.data.result
          this.letterId = res.data.result[0].letterId
          this.feedbackdanxuanList = res.data.result[0].questionLabelList
          this.questionCount = res.data.result[0].questionCount
          this.answerCount = res.data.result[0].answerCount
        })
      },
      // 获取多选按钮数据
      initcheckBoxData(letterId,parentId) {
        // debugger
        // 动态传id
        const param = {
          letterId:letterId,
          parentId:parentId
        }
        getSelectSecondLabelList(param).then(res => {
          
          this.feedbackduoxuanList = res.data.result
          // console.log(this.feedbackduoxuanList)
        })
      },
      // 获取筛选问题列表
      initQuestionData(letterId,firstLabelId,secondLabelId,onlyShowAnswerFlag) {
        // debugger
        // 动态传id
        const param = {
          letterId:letterId,
          firstLabelId:firstLabelId,
          secondLabelId:secondLabelId,
          onlyShowAnswerFlag:onlyShowAnswerFlag
        }
        getSelectQuestionListByLetterId(param).then(res => {

          this.questionList = res.data.result[0].questionList
          // console.log('wen')
          // console.log(this.questionList)
        })
      },
      
    // 点击tab页
    handleTabClick(tab, event) {
        // console.log(tab);
    },
    // 点击重置按钮
    toggleSelection(){
      this.checkboxGroup = []
      this.radio = ''
      // console.log(this.radio)
      this.onlyShowAnswer = false;
      this.feedbackduoxuanList = []
      this.questionList = []

    },
    // 问【收起展开】
    spread(item) {
     this.$set(item,'isSpread',2)
    //  console.log(item)
    },
    packUp(item) {
      this.$set(item,'isSpread',1)
    },
    // 答【收起展开】
    daSpread(item) {
     this.$set(item,'isSpreada',2)
    },
    daPackUp(item) {
      this.$nextTick(()=>{
        this.$set(item,'isSpreada',1)
      })
    },
    getContent(data,title,index,type) { //
      let width = (document.getElementById('titleLength').offsetWidth - 48) * 5
      let titleLength = title.length * 14
      let length = 0;
      if(titleLength > width) {
         for(let i =0;i<title.length;i++) {
         if(length > width) {
          //  console.log(type)
            if(type === 'answer') {
              if(!data.isSpread || data.isSpread === 0) {
                this.$set(data,'isSpread',1)
              }
             
           }else {
             if(!data.isSpreada || data.isSpreada === 0) {
                this.$set(data,'isSpreada',1)
              }
           }
           return title.substring(0,(i-4)) + '...'
         }
           length += 14;
      }
      }else {
          if(type === 'answer') {
              if(!data.isSpread || data.isSpread === 0) {
                this.$set(data,'isSpread',0)
              }
             
           }else {
             if(!data.isSpreada || data.isSpreada === 0) {
                this.$set(data,'isSpreada',0)
              }
           }
        return title
      }
    }
  
  },
  // 动态监听单选和多选按钮的状态
watch: {
      // 单选按钮
      "radio"(val,oldVal){
          for(var i = 0;i<this.tabList.length;i++){
              for(let j = 0;j<this.tabList[i].questionLabelList.length;j++){
                  if(this.tabList[i].questionLabelList[j].labelName == val){
                    this.radioVal = val
                    // console.log(this.tabList[i].letterId,this.tabList[i].questionLabelList[j].labelCode)
                    this.initcheckBoxData(this.tabList[i].letterId,this.tabList[i].questionLabelList[j].labelCode)
                  }
            }
          }
          
      },
      // 多选按钮
      "checkboxGroup"(val,oldVal){
        this.checkboxGroup = val
        for(var i = 0;i<this.tabList.length;i++){
              for(let j = 0;j<this.tabList[i].questionLabelList.length;j++){
                 if(this.tabList[i].questionLabelList[j].labelName == this.radioVal){
                     for(var k = 0;k<val.length;k++){
                        for(var m = 0;m<this.feedbackduoxuanList.length;m++){
                          if(val[k] == this.feedbackduoxuanList[m].labelName ){
                            console.log(this.tabList[i].letterId,this.tabList[i].questionLabelList[j].labelCode,this.feedbackduoxuanList[m].labelCode)
                             this.initQuestionData(this.tabList[i].letterId,this.tabList[i].questionLabelList[j].labelCode,this.feedbackduoxuanList[m].labelCode,'')
                          }
                        } 
                     }
                 }
            }
          }
      },
      // 只展示回复问题
      "onlyShowAnswer"(val,oldVal){
          if(val == true){
            for(var i = 0;i<this.tabList.length;i++){
              for(let j = 0;j<this.tabList[i].questionLabelList.length;j++){
                if(this.tabList[i].questionLabelList[j].labelName == this.radioVal){
                     for(var k = 0;k<this.checkboxGroup.length;k++){
                        for(var m = 0;m<this.feedbackduoxuanList.length;m++){
                          if(this.checkboxGroup[k] == this.feedbackduoxuanList[m].labelName ){
                            if(val == true){
                                // console.log(val)
                             this.initQuestionData(this.tabList[i].letterId,this.tabList[i].questionLabelList[j].labelCode,this.feedbackduoxuanList[m].labelCode,'1')
                            // console.log(this.tabList[i].letterId,this.tabList[i].questionLabelList[j].labelCode,this.feedbackduoxuanList[m].labelCode,'1')
                            }
                          }
                        } 
                     }
                 }
            }
          }
          }
        // for(var i = 0;i<this.tabList.length;i++){
        //       for(let j = 0;j<this.tabList[i].questionLabelList.length;j++){
        //          if(this.tabList[i].questionLabelList[j].labelName == this.radioVal){
        //              for(var k = 0;k<this.checkboxGroup.length;k++){
        //                 for(var m = 0;m<this.feedbackduoxuanList.length;m++){
        //                   if(val[k] == this.feedbackduoxuanList[m].labelName ){
        //                     if(val == true){
        //                     //  this.initQuestionData(this.tabList[i].letterId,this.tabList[i].questionLabelList[j].labelCode,this.feedbackduoxuanList[m].labelCode,'1')
        //                     console.log(this.tabList[i].letterId,this.tabList[i].questionLabelList[j].labelCode,this.feedbackduoxuanList[m].labelCode,'1')
        //                     }
        //                   }
        //                 } 
        //              }
        //          }
        //     }
        //   }
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
  top: 38px;
  left: -23px;
}
.quan {
  background-size: contain;
  background: url("../../../assets/images/quan.png") no-repeat;
}
.reset {
    background: inherit;
    background-color: #14bcf5;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    border-width: 1px;
    border-style: solid;
    border-color: #cccccc;
    border-radius: 3px;
    -webkit-box-shadow: none;
    box-shadow: none;
    font-family: "Microsoft YaHei Regular", "Microsoft YaHei";
    font-weight: 400;
    font-style: normal;
    color: white;
    text-align: center;
    line-height: 12px;
    position: relative;
    left: 5%;
    top: -2px;
    cursor: pointer;
    
}
// 展开
.spread {
  position: relative;
  left: 85%;
}
.spread:hover {
  text-decoration: underline;
}

// 收起
.packUp {
  position: relative;
  left: 85%;
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
  cursor:pointer;
}
.checkbox {
  font-size:12px;
  color:#14bcf5;
  background: #fafafa;
  margin-right: 10px;
  margin-bottom: 10px;
  border-width: 0.1px;
  border-style: solid;
  border-color: rgba(217, 217, 217, 1);
  border-radius: 3px;

}
.moreText {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 5;
  -webkit-box-orient: vertical;
}
</style>


