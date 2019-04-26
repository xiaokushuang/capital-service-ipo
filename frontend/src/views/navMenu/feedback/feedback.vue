<template>
    <div class="feedback" id="componentId">
       <div class="label">
           <!-- 有多级标签选择 -->
           <div v-if="this.tabList&&this.tabList.length>1" class="clear">
                <div v-if="this.tabList.length==2" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共两轮反馈：</div>
                <div v-if="this.tabList.length==3" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共三轮反馈：</div>
                <div>
                    <el-tabs v-model="activeName" @tab-click="handleTabClick">
                      <el-tab-pane label="第一次反馈意见" :name="tabList[0].letterId">
                          <div>
                              <div style="background-color: rgba(250, 250, 250, 1);font-size: 14px;color: #777777;padding-bottom:12px; margin-bottom: 30px;">
                                  <div class="firstLabel" >
                                      <ul class="clear" style="padding:15px 25px 0 25px;margin-top:0px;padding-left:10px;">
                                        <div style="border-bottom: 1px solid rgb(235, 235, 235);">
                                          <el-radio-group  @change="handelChange(radio)" v-model="radio" size="small" style="padding-bottom:10px;">
                                              <el-radio-button :key="item.labelCode" v-for="item in tabList[0].questionLabelList" class="l firstLabelFocus" style="margin-right:10px;margin-bottom:10px;font-size: 12px; color: rgba(0, 0, 0, 0.647058823529412);" :label="item.labelCode">{{item.labelName}}({{item.labelCount}})</el-radio-button>
                                          </el-radio-group>
                                        </div>
                                      </ul>
                                      <ul class="clear" style="padding:0px 25px;margin-top:0px;padding-bottom:10px">
                                          <el-checkbox-group  @change="handelMoreChange(checkboxGroup)" v-model="checkboxGroup" size="mini">
                                              <el-checkbox-button  class="checkbox" v-for="item in feedbackduoxuanList" :label="item.labelCode">{{item.labelName}}({{item.labelCount}})</el-checkbox-button>
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
                                          <el-checkbox  @change="handleOnlyChange(onlyShowAnswer)" v-model="onlyShowAnswer" style="margin-left:20px;margin-right:15px">只展示回复问题</el-checkbox>
                                          <!-- <el-button @click="toggleSelection()" class="reset" type="primary" plain>重置</el-button> -->
                                      </div>
                                  </div>
                              </div>
                              <div class="question" id="titleLength">

                                  <ul style="padding-left:0">
                                      <li v-for="(data,index) in questionList" :key="data.questionId" style="border-bottom:1px solid #e1e1e1;padding-bottom:15px;margin-bottom:30px">
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
                                                    <span style="cursor:pointer" v-if="data.isSpreada && data.isSpreada === 2" class="packUp" @click="daPackUp(data)">收起 <i style="font-size:12px;cursor:pointer" class="el-icon-arrow-up"></i></span>
                                                    <span style="cursor:pointer" v-if="data.isSpreada && data.isSpreada === 1" class="spread" @click="daSpread(data)">展开 <i style="font-size:12px;cursor:pointer" class="el-icon-arrow-down"></i></span>
                                                </div>
                                              </div>
                                          </div>
                                          <div>
                                              <span v-for="biaoqian in data.labelList" class="biaoqian" style="margin-right:2px;margin-bottom:12px">{{biaoqian}}</span>
                                          </div>
                                      </li>
                                  </ul>

                                    <!-- 加载更多 -->
                                    <div  v-if="showMore" @click="showMoreMethods()" class="more">加载更多</div>
                                    <!-- 已经阅读完了 -->
                                    <p v-if="!showMore&&questionList.length>0" class="finishRead">已经阅读完了</p>
                                    <!-- 暂无更多数据 -->
                                    <p v-if="!showMore&&questionList.length==0" class="finishRead">暂无相关数据</p>
                              </div>
                          </div>
                      </el-tab-pane>
                      <el-tab-pane label="第二次反馈意见" :name="tabList[1].letterId">
                            <div>
                              <div style="background-color: rgba(250, 250, 250, 1);font-size: 14px;color: #777777;padding-bottom:12px;    margin-bottom: 30px;">
                                  <div class="firstLabel" >
                                      <ul class="clear" style="padding:15px 25px 0 25px;margin-top:0px;padding-left:10px;">
                                        <div style="border-bottom: 1px solid rgb(235, 235, 235);">
                                          <el-radio-group  @change="handelChange2(radio2)" v-model="radio2" size="small" style="padding-bottom:10px;">
                                              <el-radio-button :key="item.labelCode" v-for="item in tabList[1].questionLabelList" class="l firstLabelFocus" style="margin-right:10px;margin-bottom:10px;font-size: 12px; color: rgba(0, 0, 0, 0.647058823529412);" :label="item.labelCode">{{item.labelName}}({{item.labelCount}})</el-radio-button>
                                          </el-radio-group>
                                        </div>
                                      </ul>
                                      <ul class="clear" style="padding:0px 25px;margin-top:0px;padding-bottom:10px">
                                          <el-checkbox-group  @change="handelMoreChange2(checkboxGroup2)" v-model="checkboxGroup2" size="mini">
                                              <el-checkbox-button  class="checkbox" v-for="item in feedbackduoxuanList2" :label="item.labelCode">{{item.labelName}}({{item.labelCount}})</el-checkbox-button>
                                          </el-checkbox-group>
                                      </ul>
                                      <div class="kaiguan" style="text-align:left;font-size: 12px;
                                            margin-left:25px;
                                            color: #999999;
                                            text-align: left;
                                            line-height: 14px;">
                                          <span>共计</span>
                                          <span>{{questionCount2}}</span>
                                          <span>个问题，</span>
                                          <span>{{answerCount2}}</span>
                                          <span>个回复</span>
                                          <el-checkbox  @change="handleOnlyChange2(onlyShowAnswer2)" v-model="onlyShowAnswer2" style="margin-left:20px;margin-right:15px">只展示回复问题</el-checkbox>
                                          <!-- <el-button @click="toggleSelection2()" class="reset" type="primary" plain>重置</el-button> -->
                                      </div>
                                  </div>
                              </div>
                              <div class="question" id="titleLength">
                                  <ul style="padding-left:0">
                                      <li v-for="(data,index) in questionList2" :key="data.questionId" style="border-bottom:1px solid #e1e1e1;padding-bottom:15px;margin-bottom:30px">
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
                                    <div  v-if="showMore2" @click="showMoreMethods()" class="more">加载更多</div>
                                    <!-- 已经阅读完了 -->
                                    <p v-if="!showMore2&&questionList2.length>0" class="finishRead">已经阅读完了</p>
                                    <!-- 暂无更多数据 -->
                                    <p v-if="!showMore2&&questionList2.length==0" class="finishRead">暂无相关数据</p>
                              </div>
                          </div>
                      </el-tab-pane>
                      <el-tab-pane v-if="this.tabList&&this.tabList.length>2" label="第三次反馈意见" :name="tabList[2].letterId">
                          <div>
                              <div style="background-color: rgba(250, 250, 250, 1);font-size: 14px;color: #777777;padding-bottom:12px;    margin-bottom: 30px;">
                                  <div class="firstLabel" >
                                      <ul class="clear" style="padding:15px 25px 0 25px;margin-top:0px;padding-left:10px;">
                                        <div style="border-bottom: 1px solid rgb(235, 235, 235);">
                                          <el-radio-group  @change="handelChange3(radio3)" v-model="radio3" size="small" style="padding-bottom:10px;">
                                              <el-radio-button :key="item.labelCode" v-for="item in tabList[2].questionLabelList" class="l firstLabelFocus" style="margin-right:10px;margin-bottom:10px;font-size: 12px; color: rgba(0, 0, 0, 0.647058823529412);" :label="item.labelCode">{{item.labelName}}({{item.labelCount}})</el-radio-button>
                                          </el-radio-group>
                                        </div>
                                      </ul>
                                      <ul class="clear" style="padding:0px 25px;margin-top:0px;padding-bottom:10px">
                                          <el-checkbox-group  @change="handelMoreChange3(checkboxGroup3)" v-model="checkboxGroup3" size="mini">
                                              <el-checkbox-button  class="checkbox" v-for="item in feedbackduoxuanList3" :label="item.labelCode">{{item.labelName}}({{item.labelCount}})</el-checkbox-button>
                                          </el-checkbox-group>
                                      </ul>
                                      <div class="kaiguan" style="text-align:left;font-size: 12px;
                                            margin-left:25px;
                                            color: #999999;
                                            text-align: left;
                                            line-height: 14px;">
                                          <span>共计</span>
                                          <span>{{questionCount3}}</span>
                                          <span>个问题，</span>
                                          <span>{{answerCount3}}</span>
                                          <span>个回复</span>
                                          <el-checkbox  @change="handleOnlyChange3(onlyShowAnswer3)" v-model="onlyShowAnswer3" style="margin-left:20px;margin-right:15px">只展示回复问题</el-checkbox>
                                          <!-- <el-button @click="toggleSelection3()" class="reset" type="primary" plain>重置</el-button> -->
                                      </div>
                                  </div>
                              </div>
                              <div class="question" id="titleLength">
                                  <ul style="padding-left:0">
                                      <li v-for="(data,index) in questionList3" :key="data.questionId" style="border-bottom:1px solid #e1e1e1;padding-bottom:15px;margin-bottom:30px">
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
                                                    <span style="cursor:pointer"  v-if="data.isSpreada && data.isSpreada === 2" class="packUp" @click="daPackUp(data)">收起 <i style="font-size:12px" class="el-icon-arrow-up"></i></span>
                                                    <span  style="cursor:pointer" v-if="data.isSpreada && data.isSpreada === 1" class="spread" @click="daSpread(data)">展开 <i style="font-size:12px" class="el-icon-arrow-down"></i></span>
                                                </div>
                                              </div>
                                          </div>
                                          <div>
                                              <span v-for="biaoqian in data.labelList" class="biaoqian" style="margin-right:2px;margin-bottom:12px">{{biaoqian}}</span>
                                          </div>
                                      </li>
                                  </ul>

                                <!-- 加载更多 -->
                                    <div  v-if="showMore3" @click="showMoreMethods()" class="more">加载更多</div>
                                    <!-- 已经阅读完了 -->
                                    <p v-if="!showMore3&&questionList3.length>0" class="finishRead">已经阅读完了</p>
                                    <!-- 暂无更多数据 -->
                                    <p v-if="!showMore3&&questionList3.length==0" class="finishRead">暂无相关数据</p>

                              </div>
                          </div>
                      </el-tab-pane>
                    </el-tabs>
                </div>
           </div>
           <!-- 只有一级标签 -->
            <div v-if="tabList&&tabList.length==1" class="clear">
                <div>
                    <div style="background-color: rgba(250, 250, 250, 1);font-size: 14px;color: #777777;padding-bottom:12px;margin-bottom: 30px;">
                        <div class="firstLabel" >
                            <ul class="clear" style="padding:15px 25px 0 25px;margin-top:0px;padding-left:10px;">
                              <div style="border-bottom: 1px solid rgb(235, 235, 235);">
                                <el-radio-group  @change="handelChange(radio)" v-model="radio" size="small" style="padding-bottom:10px;">
                                    <el-radio-button :key="item.labelCode" v-for="item in tabList[0].questionLabelList" class="l firstLabelFocus" style="margin-right:10px;margin-bottom:10px;font-size: 12px; color: rgba(0, 0, 0, 0.647058823529412);" :label="item.labelCode">{{item.labelName}}({{item.labelCount}})</el-radio-button>
                                </el-radio-group>
                              </div>
                            </ul>
                            <ul class="clear" style="padding:0px 25px;margin-top:0px;padding-bottom:10px">
                                <el-checkbox-group  @change="handelMoreChange(checkboxGroup)" v-model="checkboxGroup" size="mini">
                                    <el-checkbox-button  class="checkbox" v-for="item in feedbackduoxuanList" :label="item.labelCode">{{item.labelName}}({{item.labelCount}})</el-checkbox-button>
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
                                <el-checkbox  @change="handleOnlyChange(onlyShowAnswer)" v-model="onlyShowAnswer" style="margin-left:20px;margin-right:15px">只展示回复问题</el-checkbox>
                                <!-- <el-button @click="toggleSelection()" class="reset" type="primary" plain>重置</el-button> -->
                            </div>
                        </div>
                    </div>
                    <div class="question" id="titleLength">
                        <ul style="padding-left:0">
                            <li v-loading="flagLoading" element-loading-text="给我一点时间" v-for="(data,index) in questionList" :key="data.questionId" style="border-bottom:1px solid #e1e1e1;padding-bottom:15px;margin-bottom:30px">
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
                      <div  v-if="showMore" @click="showMoreMethods()" class="more">加载更多</div>
                      <!-- 已经阅读完了 -->
                      <p v-if="!showMore&&questionList.length>0" class="finishRead">已经阅读完了</p>
                       <!-- 暂无更多数据 -->
                      <p v-if="!showMore&&questionList.length==0" class="finishRead">暂无相关数据</p>
                    </div>
                </div>
           </div>
       </div>
   </div>
</template>
<script>
import {getSelectFeedbackList} from '@/api/ipoCase/companyProfile'
import {getSelectQuestionList} from '@/api/ipoCase/companyProfile'
// import {getSelectSecondLabelList} from '@/api/ipoCase/companyProfile'
import $ from "jquery";
export default {
  name: "feedback",
  data() {
    return {
      caseId:this.$store.state.app.caseId,
      // 总共数据
      tabList:[],
      // 多选按钮数据
      feedbackduoxuanList: [],
      feedbackduoxuanList2: [],
      feedbackduoxuanList3: [],
      flag: false,
      // 单选按钮绑定值
      radio: "",
      radio2:"",
      radio3:"",
      // 单选按钮选中的值
      radioVal:'',
      radioVal2:'',
      radioVal3:'',
      // 多选按钮绑定值
      checkboxGroup: [],
      checkboxGroup2: [],
      checkboxGroup3: [],
      onlyShowAnswer:false,
      onlyShowAnswer2:false,
      onlyShowAnswer3:false,
      onlyShowAnswerFlag:'',
      onlyShowAnswerFlag2:'',
      onlyShowAnswerFlag3:'',
      // 默认展示第一个label页
      activeName: '',
      // 函件id,通过这个id区分不同tab页
      letterId:'',
      // 自己定义的
      o_letterId:'',
      // 多选
      o_secondtLabelId:'',
      // 所有问题列表
      questionList:[],
      questionList2:[],
      questionList3:[],
      // // 回复个数
      answerCount:'',
      answerCount2:'',
      answerCount3:'',
      // // 问题个数
      questionCount:'',
      questionCount2:'',
      questionCount3:'',
      // 是否展示全部问题
      isShowAll:true,
      // 默认有moreText类
      isMoreText:true,
      showLength:15,
      showLength2:15,
      showLength3:15,
      allQuestionList:[],
      allQuestionList2:[],
      allQuestionList3:[],
      showMore:false,
      showMore2:false,
      showMore3:false,
      // 多选按钮’全部‘
      showAll:false,
      firstClick:true,
      flagLoading: false,
    };
  },
    created(){
         //   请求数据
       this.initTableData()
       this.isShowAll = true
     },
    // 滑轮滚到底部懒加载
     mounted(){
            let _this = this;
            // 注册scroll事件并监听
            window.addEventListener('scroll',function(){
              let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop //滚动高度
                // console.log(document.documentElement.clientHeight+'-----------'+window.innerHeight); // 可视区域高度
                // console.log('222',document.body.offsetHeight); // 文档高度
                // 判断是否滚动到底部
                if(scrollTop + window.innerHeight + 2 >= document.body.offsetHeight) {
                   this.flagLoading = true;
                  _this.showMoreMethods()
                }
            });
        },
  methods: {
    // 单选按钮
    handelChange(val){
       this.radioVal = val
       this.checkboxGroup = []
      if(val == null){
        this.initQuestionData(this.o_letterId,'',this.checkboxGroup,this.onlyShowAnswerFlag)
      }
      else{
        this.initQuestionData(this.o_letterId,val,this.checkboxGroup,this.onlyShowAnswerFlag)
      }
    },
    // 单选按钮
     handelChange2(val){
      this.radioVal2 = val
      this.checkboxGroup2 = []
      if(val == null){
        this.initQuestionData(this.o_letterId,'',this.checkboxGroup2,this.onlyShowAnswerFlag2)
      }
      else{
        this.initQuestionData(this.o_letterId,val,this.checkboxGroup2,this.onlyShowAnswerFlag2)
      }
    },
        // 单选按钮
     handelChange3(val){
      this.radioVal3 = val
      this.checkboxGroup3 = []
      if(val == null){
        this.initQuestionData(this.o_letterId,'',this.checkboxGroup3,this.onlyShowAnswerFlag3)
      }
      else{
        this.initQuestionData(this.o_letterId,val,this.checkboxGroup3,this.onlyShowAnswerFlag3)
      }
    },
    // 多选按钮
    handelMoreChange(val){
       this.checkboxGroup = val
       console.log('11111',this.checkboxGroup)
       for(let i = 0;i<val.length;i++){
         if(val[i] == null){
           this.checkboxGroup = []
          //  document.querySelector('.el-checkbox-button__inner:nth-of-type(1)').style.backgroundColor="white";
         }
       }
       if(this.radioVal == null ){
          this.initOnlyQuestionData(this.o_letterId,'', this.checkboxGroup,this.onlyShowAnswerFlag)
       }
       else{
         this.initOnlyQuestionData(this.o_letterId,this.radioVal, this.checkboxGroup,this.onlyShowAnswerFlag)
       }
    },
        // 多选按钮
    handelMoreChange2(val){
       this.checkboxGroup2 = val
       console.log('22222222',this.checkboxGroup2)
       for(let i = 0;i<val.length;i++){
         if(val[i] == null){
           this.checkboxGroup2 = []
           document.querySelector('.el-checkbox-button__inner:nth-of-type(1)').style.backgroundColor="white";
         }
       }
       if(this.radioVal2 == null ){
          this.initOnlyQuestionData(this.o_letterId,'', this.checkboxGroup2,this.onlyShowAnswerFlag2)
       }
       else{
         this.initOnlyQuestionData(this.o_letterId,this.radioVal2, this.checkboxGroup2,this.onlyShowAnswerFlag2)
       }
    },
            // 多选按钮
    handelMoreChange3(val){
       this.checkboxGroup3 = val
       for(let i = 0;i<val.length;i++){
         if(val[i] == null){
           this.checkboxGroup3 = []
           document.querySelector('.el-checkbox-button__inner:nth-of-type(1)').style.backgroundColor="white";
         }
       }
        if(this.radioVal3 == null ){
          this.initOnlyQuestionData(this.o_letterId,'', this.checkboxGroup3,this.onlyShowAnswerFlag3)
        }
        else{
          this.initOnlyQuestionData(this.o_letterId,this.radioVal3, this.checkboxGroup3,this.onlyShowAnswerFlag3)
        }
    },
    // 是否只展示回复问题
    handleOnlyChange(val){
      if(val == true){
        this.onlyShowAnswerFlag = '1'
      }else{
        this.onlyShowAnswerFlag = ''
      }
       if(this.radioVal == null ){
          this.initOnlyQuestionData(this.o_letterId,'', this.checkboxGroup,this.onlyShowAnswerFlag)
       }
       else{
         this.initOnlyQuestionData(this.o_letterId,this.radioVal,this.checkboxGroup,this.onlyShowAnswerFlag)
       }
    },
       // 是否只展示回复问题
    handleOnlyChange2(val){
      if(val == true){
        this.onlyShowAnswerFlag2 = '1'
      }else{
        this.onlyShowAnswerFlag2 = ''
      }
       if(this.radioVal2 == null ){
          this.initOnlyQuestionData(this.o_letterId,'', this.checkboxGroup2,this.onlyShowAnswerFlag2)
       }
       else{
         this.initOnlyQuestionData(this.o_letterId,this.radioVal2,this.checkboxGroup2,this.onlyShowAnswerFlag2)
       }

    },
     // 是否只展示回复问题
    handleOnlyChange3(val){
      if(val == true){
        this.onlyShowAnswerFlag3 = '1'
      }else{
        this.onlyShowAnswerFlag3 = ''
      }
      if(this.radioVal3 == null ){
          this.initOnlyQuestionData(this.o_letterId,'', this.checkboxGroup3,this.onlyShowAnswerFlag3)
       }
       else{
         this.initOnlyQuestionData(this.o_letterId,this.radioVal3,this.checkboxGroup3,this.onlyShowAnswerFlag3)
       }

    },
    // 点击加载更多
    showMoreMethods(){
      // debugger;
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
    },
      // 获取单选按钮数据
     initTableData() {
        // 动态传id
        const param = {
          id:this.caseId,
        }
        getSelectFeedbackList(param).then(res => {
          if(res.data.result && res.data.result.length > 0){
            this.o_letterId = res.data.result[0].letterId
            this.tabList = res.data.result
            this.activeName =  this.tabList[0].letterId
            if(this.tabList&&this.tabList.length==1){
              this.allQuestionList = res.data.result[0].questionList
              this.questionCount = res.data.result[0].questionCount
              this.answerCount = res.data.result[0].answerCount
              if(this.allQuestionList.length > 15){
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0,15);
              }else{
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            }
            // 第二个tab
            if(this.tabList&&this.tabList.length==2){
              this.allQuestionList = res.data.result[0].questionList
              this.allQuestionList2 = res.data.result[1].questionList
              this.questionCount = res.data.result[0].questionCount
              this.answerCount = res.data.result[0].answerCount
              this.questionCount2 = res.data.result[1].questionCount
              this.answerCount2 = res.data.result[1].answerCount
              if(this.allQuestionList.length > 15){
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0,15);
              }else{
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
              if(this.allQuestionList2.length > 15){
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0,15);
              }else{
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
            }
            // 第三个tab
            if(this.tabList&&this.tabList.length==3){
              this.allQuestionList = res.data.result[0].questionList
              this.allQuestionList2 = res.data.result[1].questionList
              this.allQuestionList3 = res.data.result[2].questionList
              this.questionCount = res.data.result[0].questionCount
              this.answerCount = res.data.result[0].answerCount
              this.questionCount1 = res.data.result[1].questionCount
              this.answerCount1 = res.data.result[1].answerCount
              this.questionCount2 = res.data.result[2].questionCount
              this.answerCount2 = res.data.result[2].answerCount
              if(this.allQuestionList.length > 15){
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0,15);
              }else{
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
              if(this.allQuestionList2.length > 15){
                this.showMore2 = true;
                this.questionList2 = this.allQuestionList2.slice(0,15);
              }else{
                this.showMore2 = false;
                this.questionList2 = this.allQuestionList2;
              }
              if(this.allQuestionList3.length > 15){
                this.showMore3 = true;
                this.questionList3 = this.allQuestionList3.slice(0,15);
              }else{
                this.showMore3 = false;
                this.questionList3 = this.allQuestionList3;
              }
            }
          }
        })
      },
      // 获取筛选二级标签和问题列表
      initQuestionData(letterId,firstLabelId,secondLabelId,onlyResponse,ifReset) {
        // debugger
        // 动态传id
        // 将second多选按钮参数用字符串，隔开
        let secondLabel = '';
        for(let i = 0;i<secondLabelId.length;i++){
            secondLabel =  secondLabelId[i]+ "," + secondLabel ;
        }
        // 将second多选按钮参数最后一个  ，号去掉
        if(secondLabel && secondLabel.length >0){
            secondLabel = secondLabel.substring(0,secondLabel.length-1);
        }
        const param = {
          letterId:letterId,
          firstLabelId:firstLabelId,
          secondLabelId:secondLabel,
          onlyResponse:onlyResponse,
        }
        console.log('获取二级菜单及问题id',param)
        getSelectQuestionList(param).then(res => {
          // 当只有一个tab页时
          // debugger;
          console.log('点击一级菜单获取结果',res.data.result)
          if(this.tabList.length==1){
            if(res.data.result.length  > 0){
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount
              this.answerCount = res.data.result[0].answerCount
              if(ifReset != '0'){
                this. feedbackduoxuanList = res.data.result[0].questionLabelList
              }

              if(this.allQuestionList.length > 15){
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0,15);
              }else{
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            }else {
                 this.showMore = false;
                 this.questionList = [];
            }
          }
          // 当有2个tab页时
          if(this.tabList.length==2){
              if(param.letterId == this.tabList[0].letterId){
                if(res.data.result.length  > 0){
                  this.allQuestionList = res.data.result[0].questionList;
                  this.questionCount = res.data.result[0].questionCount
                  this.answerCount = res.data.result[0].answerCount
                  if(ifReset != '0'){
                    this. feedbackduoxuanList = res.data.result[0].questionLabelList
                  }
                  if(this.allQuestionList.length > 15){
                    this.showMore = true;
                    this.questionList = this.allQuestionList.slice(0,15);
                  }else{
                    this.showMore = false;
                    this.questionList = this.allQuestionList;
                  }
                }else {
                      this.showMore = false;
                      this.questionList = [];
                }
              }
              if(param.letterId == this.tabList[1].letterId){
                if(res.data.result.length  > 0){
                  this.allQuestionList2 = res.data.result[0].questionList;
                  this.questionCount2 = res.data.result[0].questionCount
                  this.answerCount2 = res.data.result[0].answerCount
                  if(ifReset != '0'){
                    this. feedbackduoxuanList2 = res.data.result[0].questionLabelList
                  }
                  if(this.allQuestionList2.length > 15){
                    this.showMore2 = true;
                    this.questionList2 = this.allQuestionList2.slice(0,15);
                  }else{
                    this.showMore2 = false;
                    this.questionList2 = this.allQuestionList2;
                  }
                }else {
                      this.showMore2 = false;
                      this.questionList2 = [];
                }
              }
          }
           // 当有3个tab页时
          if(this.tabList.length==3){
              if(param.letterId == this.tabList[0].letterId){
                if(res.data.result.length  > 0){
                  this.allQuestionList = res.data.result[0].questionList;
                  this.questionCount = res.data.result[0].questionCount
                  this.answerCount = res.data.result[0].answerCount
                  if(ifReset != '0'){
                    this. feedbackduoxuanList = res.data.result[0].questionLabelList
                  }
                  if(this.allQuestionList.length > 15){
                    this.showMore = true;
                    this.questionList = this.allQuestionList.slice(0,15);
                  }else{
                    this.showMore = false;
                    this.questionList = this.allQuestionList;
                  }
                }else {
                      this.showMore = false;
                      this.questionList = [];
                }
              }
              if(param.letterId == this.tabList[1].letterId){
                if(res.data.result.length  > 0){
                  this.allQuestionList2 = res.data.result[0].questionList;
                  this.questionCount2 = res.data.result[0].questionCount
                  this.answerCount2 = res.data.result[0].answerCount
                  if(ifReset != '0'){
                    this. feedbackduoxuanList2 = res.data.result[0].questionLabelList
                  }
                  if(this.allQuestionList2.length > 15){
                    this.showMore2 = true;
                    this.questionList2 = this.allQuestionList2.slice(0,15);
                  }else{
                    this.showMore2 = false;
                    this.questionList2 = this.allQuestionList2;
                  }
                }else {
                      this.showMore2 = false;
                      this.questionList2 = [];
                }
              }
              if(param.letterId == this.tabList[2].letterId){
                if(res.data.result.length  > 0){
                  this.allQuestionList3 = res.data.result[0].questionList;
                  this.questionCount3 = res.data.result[0].questionCount
                  this.answerCount3 = res.data.result[0].answerCount
                  if(ifReset != '0'){
                    this. feedbackduoxuanList3 = res.data.result[0].questionLabelList
                  }
                  if(this.allQuestionList3.length > 15){
                    this.showMore3 = true;
                    this.questionList3 = this.allQuestionList3.slice(0,15);
                  }else{
                    this.showMore3 = false;
                    this.questionList3 = this.allQuestionList3;
                  }
                }else {
                      this.showMore3 = false;
                      this.questionList3 = [];
                }
              }
          }
        })
      },
      // 点击二级菜单过滤出问题列表
      initOnlyQuestionData(letterId,firstLabelId,secondLabelId,onlyResponse) {
        // debugger
        // 动态传id
        // 将second多选按钮参数用字符串，隔开
        let secondLabel = '';
        for(let i = 0;i<secondLabelId.length;i++){
            secondLabel =  secondLabelId[i]+ "," + secondLabel ;
        }
        // 将second多选按钮参数最后一个  ，号去掉
        if(secondLabel && secondLabel.length >0){
            secondLabel = secondLabel.substring(0,secondLabel.length-1);
        }
        const param = {
          letterId:letterId,
          firstLabelId:firstLabelId,
          secondLabelId:secondLabel,
          onlyResponse:onlyResponse,
        }
        console.log('点击二级菜单获取问题id',param)
        getSelectQuestionList(param).then(res => {
          console.log('点击二级菜单获取问题结果',res.data.result)
          // 当只有一个tab页时
          if(this.tabList.length==1){
            if(res.data.result.length  > 0){
              this.allQuestionList = res.data.result[0].questionList;
              this.questionCount = res.data.result[0].questionCount
              this.answerCount = res.data.result[0].answerCount
              if(this.allQuestionList.length > 15){
                this.showMore = true;
                this.questionList = this.allQuestionList.slice(0,15);
              }else{
                this.showMore = false;
                this.questionList = this.allQuestionList;
              }
            }else {
                 this.showMore = false;
                 this.questionList = [];
            }
          }
          // 当有2个tab页时
          if(this.tabList.length==2){
              if(param.letterId == this.tabList[0].letterId){
                if(res.data.result.length  > 0){
                  this.allQuestionList = res.data.result[0].questionList;
                  this.questionCount = res.data.result[0].questionCount
                  this.answerCount = res.data.result[0].answerCount
                  if(this.allQuestionList.length > 15){
                    this.showMore = true;
                    this.questionList = this.allQuestionList.slice(0,15);
                  }else{
                    this.showMore = false;
                    this.questionList = this.allQuestionList;
                  }
                }else {
                      this.showMore = false;
                      this.questionList = [];
                }
              }
              if(param.letterId == this.tabList[1].letterId){
                if(res.data.result.length  > 0){
                  this.allQuestionList2 = res.data.result[0].questionList;
                  this.questionCount2 = res.data.result[0].questionCount
                  this.answerCount2 = res.data.result[0].answerCount
                  if(this.allQuestionList2.length > 15){
                    this.showMore2 = true;
                    this.questionList2 = this.allQuestionList2.slice(0,15);
                  }else{
                    this.showMore2 = false;
                    this.questionList2 = this.allQuestionList2;
                  }
                }else {
                      this.showMore2 = false;
                      this.questionList2 = [];
                }
              }
          }
           // 当有3个tab页时
          if(this.tabList.length==3){
              if(param.letterId == this.tabList[0].letterId){
                if(res.data.result.length  > 0){
                  this.allQuestionList = res.data.result[0].questionList;
                   this.questionCount = res.data.result[0].questionCount
                  this.answerCount = res.data.result[0].answerCount
                  if(this.allQuestionList.length > 15){
                    this.showMore = true;
                    this.questionList = this.allQuestionList.slice(0,15);
                  }else{
                    this.showMore = false;
                    this.questionList = this.allQuestionList;
                  }
                }else {
                      this.showMore = false;
                      this.questionList = [];
                }
              }
              if(param.letterId == this.tabList[1].letterId){
                if(res.data.result.length  > 0){
                  this.allQuestionList2 = res.data.result[0].questionList;
                   this.questionCount2 = res.data.result[0].questionCount
                  this.answerCount2 = res.data.result[0].answerCount
                  if(this.allQuestionList2.length > 15){
                    this.showMore2 = true;
                    this.questionList2 = this.allQuestionList2.slice(0,15);
                  }else{
                    this.showMore2 = false;
                    this.questionList2 = this.allQuestionList2;
                  }
                }else {
                      this.showMore2 = false;
                      this.questionList2 = [];
                }
              }
              if(param.letterId == this.tabList[2].letterId){
                if(res.data.result.length  > 0){
                  this.allQuestionList3 = res.data.result[0].questionList;
                   this.questionCount3 = res.data.result[0].questionCount
                  this.answerCount3 = res.data.result[0].answerCount
                  if(this.allQuestionList3.length > 15){
                    this.showMore3 = true;
                    this.questionList3 = this.allQuestionList3.slice(0,15);
                  }else{
                    this.showMore3 = false;
                    this.questionList3 = this.allQuestionList3;
                  }
                }else {
                      this.showMore3 = false;
                      this.questionList3 = [];
                }
              }
          }
        })
      },
    // 点击tab页
    handleTabClick(tab, event) {
        this.o_letterId = tab.name
    },
    // 点击重置按钮
    // toggleSelection(){
    //   this.checkboxGroup = []
    //   this.radio = ''
    //   this.radioVal = ''
    //   this.feedbackduoxuanList = [],
    //   this.onlyShowAnswerFlag = ''
    //   this.onlyShowAnswer = false;
    //   this.showAll = true;
    //   this.initQuestionData(this.o_letterId,'','','',"0")
    // },
    //     // 点击重置按钮
    // toggleSelection2(){
    //   this.checkboxGroup2 = []
    //    this.radio2 = ''
    //   this.radioVal2 = ''
    //   this.feedbackduoxuanList2 = [],
    //   this.onlyShowAnswerFlag2 = ''
    //   this.onlyShowAnswer2 = false;
    //   this.showAll = true;
    //   this.initQuestionData(this.o_letterId,'','','',"0")
    // },
    //         // 点击重置按钮
    // toggleSelection3(){
    //   this.checkboxGroup3 = []
    //    this.radio3 = ''
    //   this.radioVal3 = ''
    //   this.feedbackduoxuanList3 = [],
    //   this.onlyShowAnswerFlag3 = ''
    //   this.onlyShowAnswer3 = false;
    //   this.showAll = true;
    //   this.initQuestionData(this.o_letterId,'','','',"0")
    // },
    // 问【收起展开】
    spread(item) {
     this.$set(item,'isSpread',2)
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
    getContent(data,title,index,type) {
      // 5行的宽度和
      let width = (document.getElementById('componentId').offsetWidth - 48) * 5
      // 所有字数的宽度【14是字体大小】
      let titleLength = title.length * 14
      let length = 0;
      if(titleLength > width) {
         for(let i =0;i<title.length;i++) {
          length += 14;
            if(length > width) {
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
    },
},
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
.finishRead{
  font-family: 'PingFang-SC-Regular', 'PingFang SC';
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


