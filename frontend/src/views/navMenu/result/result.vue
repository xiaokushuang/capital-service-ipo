<template>
    <div class="result">
        <!-- 上 -->
        <div class="one">
            <ul v-if="baseList.length == 1" style="padding-left: 0;">
                 <li v-show="baseList.length>0" style="background:rgba(250, 250, 250, 1);padding-left: 17px; padding-top: 10px; padding-bottom: 10px;margin-top: 32px;">
                    <div class="text">
                        <p style="font-size:14px;">
                            <span style="font-family:'微软雅黑';font-weight:400;color:#999;">审核会议: </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[0].relationFileTitle}} _ </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[0].examineDate}}</span>
                        </p>
                    </div>
                    <div class="text ">
                        <p style="font-size:14px;">
                            <span style="font-size:14px;color:#999;">审核结果: </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[0].companyName}}</span>
                             <span>
                                <span class="htgResult"  v-if="baseList[0].iecResult=='00'">获通过</span>
                                <span class="whtgResult" v-if="baseList[0].iecResult=='01'">未获通过</span>
                                <span class="zhbjResult" v-if="baseList[0].iecResult=='02'">暂缓表决</span>
                                <span class="qxshResult" v-if="baseList[0].iecResult=='03'">取消审核</span>
                                <span class="dshResult"  v-if="baseList[0].iecResult=='04'">待审核</span>
                                <span class="tgResult"  v-if="baseList[0].iecResult=='05'">通过</span>
                                <span class="whtgResult" v-if="baseList[0].iecResult=='06'">未通过</span>
                                <span class="htgResult"  v-if="baseList[0].iecResult=='07'">注册生效</span>
                                <span class="whtgResult" v-if="baseList[0].iecResult=='08'">不予注册</span>
                                <span class="dshResult"  v-if="baseList[0].iecResult=='09'">待审核</span>
                            </span>
                        </p>
                    </div>
                    <div class="text ">
                         <p style="font-size:14px;">
                             <span style="color:#999;">发审会委员: </span>
                              <span v-if="baseList[0].member&&baseList[0].member.length>0">
                               <span style="color:#333;">{{baseList[0].member}}</span>
                                <span  style="color:#333;"> ;</span>
                                <span style="color:#1990FE;cursor:pointer" @click="handleMemberDetail(baseList[0].examineDate)">详情 &gt;</span>
                             </span>
                             <span v-else>- -</span>
                        </p>
                    </div>
                    <!-- 委员弹窗 -->
                    <el-dialog
                        title="发审会委员"
                        :visible.sync="dialogVisible"
                        width="1004px"
                        :before-close="handleClose">
                        <member :memberData={memberList:memberList}></member>
                    </el-dialog>
                </li>
            </ul>
            <ul v-if="baseList.length == 2" style="padding-left: 0;">
                <li style="background:rgba(250, 250, 250, 1);padding-left: 17px; padding-top: 10px; padding-bottom: 10px;margin-top: 32px;">
                    <div class="text">
                        <p style="font-size:14px;">
                            <span style="font-family:'微软雅黑';font-weight:400;color:#999;">第一次审核会议: </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[0].relationFileTitle}} _ </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[0].examineDateStr}}</span>
                        </p>
                    </div>
                    <div class="text ">
                        <p style="font-size:14px;">
                            <span style="font-size:14px;color:#999;">第一次审核结果: </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[0].companyName}}</span>
                            <span>
                                <span class="htgResult"  v-if="baseList[0].iecResult=='00'">获通过</span>
                                <span class="whtgResult" v-if="baseList[0].iecResult=='01'">未获通过</span>
                                <span class="zhbjResult" v-if="baseList[0].iecResult=='02'">暂缓表决</span>
                                <span class="qxshResult" v-if="baseList[0].iecResult=='03'">取消审核</span>
                                <span class="dshResult"  v-if="baseList[0].iecResult=='04'">待审核</span>
                                <span class="tgResult"   v-if="baseList[0].iecResult=='05'">通过</span>
                                <span class="whtgResult" v-if="baseList[0].iecResult=='06'">未通过</span>
                                <span class="htgResult"  v-if="baseList[0].iecResult=='07'">注册生效</span>
                                <span class="whtgResult" v-if="baseList[0].iecResult=='08'">不予注册</span>
                                <span class="dshResult"  v-if="baseList[0].iecResult=='09'">待审核</span>
                            </span>
                            <!-- <span  :class="{'htgResult' : baseList[0].iecResult == '00','whtgResult' : baseList[0].iecResult == '01','zhbjResult' : baseList[0].iecResult == '02','qxshResult' : baseList[0].iecResult == '03','dshResult' : baseList[0].iecResult == '04'}">{{ baseList[0].iecResultStr}}</span> -->
                        </p>
                    </div>
                    <div class="text ">
                         <p style="font-size:14px;">
                             <span style="color:#999;">发审会委员: </span>
                             <span v-if="baseList[0].member&&baseList[0].member.length>0">
                                <span style="color:#333;">{{baseList[0].member}}</span>
                                <span  style="color:#333;"> ;</span>
                                <span style="color:#1990FE;cursor:pointer" @click="handleMemberDetail(baseList[0].examineDate)">详情 &gt;</span>
                             </span>
                             <span v-else>- -</span>
                        </p>
                    </div>
                    <!-- 委员弹窗 -->
                    <el-dialog
                        title="发审会委员"
                        :visible.sync="dialogVisible"
                        width="1004px"
                        :before-close="handleClose">
                        <member :memberData={memberList:memberList}></member>
                    </el-dialog>
                </li>
                 <li style="background:rgba(250, 250, 250, 1);padding-left: 17px; padding-top: 10px; padding-bottom: 10px;margin-top: 32px;">
                    <div class="text">
                        <p style="font-size:14px;">
                            <span style="font-family:'微软雅黑';font-weight:400;color:#999;">第二次审核会议: </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[1].relationFileTitle}} _ </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[1].examineDateStr}}</span>
                        </p>
                    </div>
                    <div class="text ">
                        <p style="font-size:14px;">
                            <span style="font-size:14px;color:#999;">第二次审核结果: </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[1].companyName}}</span>
                            <span>
                                <span class="htgResult"  v-if="baseList[1].iecResult=='00'">获通过</span>
                                <span class="whtgResult" v-if="baseList[1].iecResult=='01'">未获通过</span>
                                <span class="zhbjResult" v-if="baseList[1].iecResult=='02'">暂缓表决</span>
                                <span class="qxshResult" v-if="baseList[1].iecResult=='03'">取消审核</span>
                                <span class="dshResult"  v-if="baseList[1].iecResult=='04'">待审核</span>
                                <span class="tgResult"  v-if="baseList[1].iecResult=='05'">通过</span>
                                <span class="whtgResult" v-if="baseList[1].iecResult=='06'">未通过</span>
                                <span class="htgResult"  v-if="baseList[1].iecResult=='07'">注册生效</span>
                                <span class="whtgResult" v-if="baseList[1].iecResult=='08'">不予注册</span>
                                <span class="dshResult"  v-if="baseList[1].iecResult=='09'">待审核</span>
                            </span>
                            <!-- <span  :class="{'htgResult' : baseList[1].iecResult == '00','whtgResult' : baseList[1].iecResult == '01','zhbjResult' : baseList[1].iecResult == '02','qxshResult' : baseList[1].iecResult == '03','dshResult' : baseList[1].iecResult == '04'}">{{ baseList[1].iecResultStr}}</span> -->
                        </p>
                    </div>
                    <div class="text ">
                         <p style="font-size:14px;">
                             <span style="color:#999;">发审会委员: </span>
                              <span v-if="baseList[1].member&&baseList[1].member.length>0">
                                <span style="color:#333;">{{baseList[1].member}}</span>
                                <span  style="color:#333;"> ;</span>
                                <span style="color:#1990FE;cursor:pointer" @click="handleMemberDetail(baseList[1].examineDate)">详情 &gt;</span>
                             </span>
                             <span v-else>- -</span>
                        </p>
                    </div>
                    <!-- 委员弹窗 -->
                    <el-dialog
                        title="发审会委员"
                        :visible.sync="dialogVisible"
                        width="1004px"
                        :before-close="handleClose">
                        <member :memberData={memberList:memberList}></member>
                    </el-dialog>
                </li>
            </ul>
            <ul v-if="baseList.length == 3" style="padding-left: 0;">
                <li style="background:rgba(250, 250, 250, 1);padding-left: 17px; padding-top: 10px; padding-bottom: 10px;margin-top: 32px;">
                    <div class="text">
                        <p style="font-size:14px;">
                            <span style="font-family:'微软雅黑';font-weight:400;color:#999;">第一次审核会议: </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[0].relationFileTitle}} _ </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[0].examineDateStr}}</span>
                        </p>
                    </div>
                    <div class="text ">
                        <p style="font-size:14px;">
                            <span style="font-size:14px;color:#999;">第一次审核结果: </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[0].companyName}}</span>
                            <span>
                                <span class="htgResult"  v-if="baseList[0].iecResult=='00'">获通过</span>
                                <span class="whtgResult" v-if="baseList[0].iecResult=='01'">未获通过</span>
                                <span class="zhbjResult" v-if="baseList[0].iecResult=='02'">暂缓表决</span>
                                <span class="qxshResult" v-if="baseList[0].iecResult=='03'">取消审核</span>
                                <span class="dshResult"  v-if="baseList[0].iecResult=='04'">待审核</span>
                                <span class="tgResult"  v-if="baseList[0].iecResult=='05'">通过</span>
                                <span class="whtgResult" v-if="baseList[0].iecResult=='06'">未通过</span>
                                <span class="htgResult"  v-if="baseList[0].iecResult=='07'">注册生效</span>
                                <span class="whtgResult" v-if="baseList[0].iecResult=='08'">不予注册</span>
                                <span class="dshResult"  v-if="baseList[0].iecResult=='09'">待审核</span>
                            </span>
                            <!-- <span  :class="{'htgResult' : baseList[0].iecResult == '00','whtgResult' : baseList[0].iecResult == '01','zhbjResult' : baseList[0].iecResult == '02','qxshResult' : baseList[0].iecResult == '03','dshResult' : baseList[0].iecResult == '04'}">{{ baseList[0].iecResultStr}}</span> -->
                        </p>
                    </div>
                    <div class="text ">
                         <p style="font-size:14px;">
                             <span style="color:#999;">发审会委员: </span>
                              <span v-if="baseList[0].member&&baseList[0].member.length>0">
                                <span style="color:#333;">{{baseList[0].member}}</span>
                                <span  style="color:#333;"> ;</span>
                                <span style="color:#1990FE;cursor:pointer" @click="handleMemberDetail(baseList[0].examineDate)">详情 &gt;</span>
                             </span>
                             <span v-else>- -</span>
                        </p>
                    </div>
                    <!-- 委员弹窗 -->
                    <el-dialog
                        title="发审会委员"
                        :visible.sync="dialogVisible"
                        width="1004px"
                        :before-close="handleClose">
                       <member :memberData={memberList:memberList}></member>
                    </el-dialog>
                </li>
                 <li  style="background:rgba(250, 250, 250, 1);padding-left: 17px; padding-top: 10px; padding-bottom: 10px;margin-top: 32px;">
                    <div class="text">
                        <p style="font-size:14px;">
                            <span style="font-family:'微软雅黑';font-weight:400;color:#999;">第二次审核会议: </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[1].relationFileTitle}} _ </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[1].examineDateStr}}</span>
                        </p>
                    </div>
                    <div class="text ">
                        <p style="font-size:14px;">
                            <span style="font-size:14px;color:#999;">第二次审核结果: </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[1].companyName}}</span>
                            <span>
                                <span class="htgResult"  v-if="baseList[1].iecResult=='00'">获通过</span>
                                <span class="whtgResult" v-if="baseList[1].iecResult=='01'">未获通过</span>
                                <span class="zhbjResult" v-if="baseList[1].iecResult=='02'">暂缓表决</span>
                                <span class="qxshResult" v-if="baseList[1].iecResult=='03'">取消审核</span>
                                <span class="dshResult"  v-if="baseList[1].iecResult=='04'">待审核</span>
                                <span class="tgResult"  v-if="baseList[1].iecResult=='05'">通过</span>
                                <span class="whtgResult" v-if="baseList[1].iecResult=='06'">未通过</span>
                                <span class="htgResult"  v-if="baseList[1].iecResult=='07'">注册生效</span>
                                <span class="whtgResult" v-if="baseList[1].iecResult=='08'">不予注册</span>
                                <span class="dshResult"  v-if="baseList[1].iecResult=='09'">待审核</span>
                            </span>
                            <!-- <span  :class="{'htgResult' : baseList[1].iecResult == '00','whtgResult' : baseList[1].iecResult == '01','zhbjResult' : baseList[1].iecResult == '02','qxshResult' : baseList[1].iecResult == '03','dshResult' : baseList[1].iecResult == '04'}">{{ baseList[1].iecResultStr}}</span> -->
                        </p>
                    </div>
                    <div class="text ">
                         <p style="font-size:14px;">
                             <span style="color:#999;">发审会委员: </span>
                              <span v-if="baseList[1].member&&baseList[1].member.length>0">
                                <span style="color:#333;">{{baseList[1].member}}</span>
                                <span  style="color:#333;"> ;</span>
                                <span style="color:#1990FE;cursor:pointer" @click="handleMemberDetail(baseList[1].examineDate)">详情 &gt;</span>
                             </span>
                             <span v-else>- -</span>
                        </p>
                    </div>
                    <!-- 委员弹窗 -->
                    <el-dialog
                        title="发审会委员"
                        :visible.sync="dialogVisible"
                        width="1004px"
                        :before-close="handleClose">
                       <member :memberData={memberList:memberList}></member>
                    </el-dialog>
                </li>
                 <li  style="background:rgba(250, 250, 250, 1);padding-left: 17px; padding-top: 10px; padding-bottom: 10px;margin-top: 32px;">
                    <div class="text">
                        <p style="font-size:14px;">
                            <span style="font-family:'微软雅黑';font-weight:400;color:#999;">第三次审核会议: </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[2].relationFileTitle}} _ </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[2].examineDateStr}}</span>
                        </p>
                    </div>
                    <div class="text ">
                        <p style="font-size:14px;">
                            <span style="font-size:14px;color:#999;">第三次审核结果: </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[2].companyName}}</span>
                            <span>
                                <span class="htgResult"  v-if="baseList[2].iecResult=='00'">获通过</span>
                                <span class="whtgResult" v-if="baseList[2].iecResult=='01'">未获通过</span>
                                <span class="zhbjResult" v-if="baseList[2].iecResult=='02'">暂缓表决</span>
                                <span class="qxshResult" v-if="baseList[2].iecResult=='03'">取消审核</span>
                                <span class="dshResult"  v-if="baseList[2].iecResult=='04'">待审核</span>
                                <span class="tgResult"  v-if="baseList[2].iecResult=='05'">通过</span>
                                <span class="whtgResult" v-if="baseList[2].iecResult=='06'">未通过</span>
                                <span class="htgResult"  v-if="baseList[2].iecResult=='07'">注册生效</span>
                                <span class="whtgResult" v-if="baseList[2].iecResult=='08'">不予注册</span>
                                <span class="dshResult"  v-if="baseList[2].iecResult=='09'">待审核</span>
                            </span>
                            <!-- <span  :class="{'htgResult' : baseList[2].iecResult == '00','whtgResult' : baseList[2].iecResult == '01','zhbjResult' : baseList[2].iecResult == '02','qxshResult' : baseList[2].iecResult == '03','dshResult' : baseList[2].iecResult == '04'}">{{ baseList[2].iecResultStr}}</span> -->
                        </p>
                    </div>
                    <div class="text ">
                         <p style="font-size:14px;">
                             <span style="color:#999;">发审会委员: </span>
                              <span v-if="baseList[2].member&&baseList[2].member.length>0">
                                <span style="color:#333;">{{baseList[2].member}}</span>
                                <span  style="color:#333;"> ;</span>
                                <span style="color:#1990FE;cursor:pointer" @click="handleMemberDetail(baseList[2].examineDate)">详情 &gt;</span>
                             </span>
                             <span v-else>- -</span>
                        </p>
                    </div>
                   <!-- 委员弹窗 -->
                    <el-dialog
                        title="发审会委员"
                        :visible.sync="dialogVisible"
                        width="1004px"
                        :before-close="handleClose">
                       <member :memberData={memberList:memberList}></member>
                    </el-dialog>
                </li>
            </ul>
        </div> 
       <!-- 筛选问题列表 -->
         <div v-if="tabList&&tabList.length>0&&tabList[0].questionList&&tabList[0].questionList.length>0" class="title">
                <span class="littleRectangle"></span>
                <span class="titleText" id="result">上市会关注问题</span>
         </div>
        <div class="feedback" id="componentId">
            <div class="label">
                    <!-- 导入的组件 -->
                    <!-- 只有一级标签 -->
            <div v-if="tabList&&tabList.length==1&&tabList[0].questionList&&tabList[0].questionList.length>0">
              <div  v-if="tabList&&tabList.length==1" class="clear"> 
                <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[0].questionLabelList,checkbox:feedbackduoxuanList,answerCount:answerCount,questionCount:questionCount,questionList:questionList,o_letterId:this.o_letterId,showMore:showMore,allQuestionList:allQuestionList}></singleAndMultiple>
              </div>
            </div>
            <!-- 有多级标签选择 -->
             <div v-if="tabList&&tabList.length > 1" class="clear">
                <div v-if="tabList.length==2" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共两轮关注：</div>
                <div v-if="tabList.length==3" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共三轮关注：</div>
                <div v-if="tabList.length==4" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共四轮关注：</div>
                <div v-if="tabList.length==5" style="float:left;position:relative;top: 12px;font-family: 'PingFangSC-Thin', 'PingFang SC Thin', 'PingFang SC';font-weight: 200;font-style: normal;font-size: 14px;color: #A1A1A1;">共五轮关注：</div>
                <div>
                    <el-tabs v-model="activeName" @tab-click="handleTabClick">
                       <el-tab-pane label="第一次审核关注问题" :name="tabList[0].letterId">
                          <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[0].questionLabelList,checkbox:feedbackduoxuanList,answerCount:answerCount,questionCount:questionCount,questionList:questionList,o_letterId:this.o_letterId,showMore:showMore,allQuestionList:allQuestionList}></singleAndMultiple>
                       </el-tab-pane>
                       <el-tab-pane label="第二次审核关注问题" :name="tabList[1].letterId">
                          <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[1].questionLabelList,checkbox:feedbackduoxuanList2,answerCount:answerCount2,questionCount:questionCount2,questionList:questionList2,o_letterId:this.o_letterId,showMore:showMore2,allQuestionList:allQuestionList2}></singleAndMultiple>
                       </el-tab-pane>
                        <el-tab-pane v-if="tabList&&tabList.length>2" label="第三次审核关注问题" :name="tabList[2].letterId">
                          <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[2].questionLabelList,checkbox:feedbackduoxuanList3,answerCount:answerCount3,questionCount:questionCount3,questionList:questionList3,o_letterId:this.o_letterId,showMore:showMore3,allQuestionList:allQuestionList3}></singleAndMultiple>
                       </el-tab-pane>
                        <el-tab-pane v-if="this.tabList&&this.tabList.length>3" label="第四次审核关注问题" :name="tabList[3].letterId">
                          <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[3].questionLabelList,checkbox:feedbackduoxuanList4,answerCount:answerCount4,questionCount:questionCount4,questionList:questionList4,o_letterId:this.o_letterId,showMore:showMore4,allQuestionList:allQuestionList4}></singleAndMultiple>
                       </el-tab-pane>
                        <el-tab-pane v-if="this.tabList&&this.tabList.length>4" label="第五次审核关注问题" :name="tabList[4].letterId">
                          <singleAndMultiple :singleAndMultiplDdata={tabList:tabList,radio:tabList[4].questionLabelList,checkbox:feedbackduoxuanList5,answerCount:answerCount5,questionCount:questionCount5,questionList:questionList5,o_letterId:this.o_letterId,showMore:showMore5,allQuestionList:allQuestionList5}></singleAndMultiple>
                       </el-tab-pane>
                    </el-tabs>
                </div>
             </div>
            </div>
        </div>
        
    </div>
</template>
<script>
// 审核结果数据
import {getReviewMeeting } from '@/api/ipoCase/companyProfile'
import {geSelectMemberList } from '@/api/ipoCase/companyProfile'
// 引用反馈意见数据
import { getResultInitializeList } from "@/api/ipoCase/companyProfile";
import { getResultQuestionList } from "@/api/ipoCase/companyProfile";
// 导入筛选问题列表组件
import singleAndMultiple from "@/views/navMenu/singleAndMultiple/singleAndMultiple"
// 导入委员详情组件
import member from "@/views/navMenu/result/member"
import $ from "jquery";
export default {
  name: "result",
  props:["companyProfileList"],
  components: {
      singleAndMultiple,
      member
  },
  data() {
    return {
         caseId:this.$store.state.app.caseId,
         dialogVisible:false,
        //  第几次会议列表
         baseList:[],
        //  委员列表
        memberList:[],
        // 筛选问题列表
         // 总共数据
      tabList: [],
      // 多选按钮数据
      feedbackduoxuanList: [],
      feedbackduoxuanList2: [],
      feedbackduoxuanList3: [],
      feedbackduoxuanList4: [],
      feedbackduoxuanList5: [],
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
      // // 回复个数
      answerCount: "",
      answerCount2: "",
      answerCount3: "",
      answerCount4: "",
      answerCount5: "",
      // // 问题个数
      questionCount: "",
      questionCount2: "",
      questionCount3: "",
      questionCount4: "",
      questionCount5: "",
      // 是否展示全部问题
      isShowAll: true,
      // 默认有moreText类
      isMoreText: true,
      showLength: 15,
      showLength2: 15,
      showLength3: 15,
      showLength4: 15,
      showLength5: 15,
      allQuestionList: [],
      allQuestionList2: [],
      allQuestionList3: [],
      allQuestionList4: [],
      allQuestionList5: [],
      showMore: false,
      showMore2: false,
      showMore3: false,
      showMore4: false,
      showMore5: false,
      // 多选按钮’全部‘
      showAll: false,
      firstClick: true,
      flagLoading: false
    };
  },
  created() {
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
    },
    //   初始化所有数据
    initTableData() { 
    // 动态传id
        const param = {
            id:this.caseId,
        }
        // 获取会议数据
        getReviewMeeting(param).then(res => {
            if(res.data.result&&res.data.result.baseList&&res.data.result.baseList.length>0){
                this.baseList = res.data.result.baseList
            }
        })
        // 获取筛选问题数据
        getResultInitializeList(param).then(res => {
            if (res.data.result && res.data.result.length > 0) {
            this.o_letterId = res.data.result[0].letterId;
            this.tabList = res.data.result;
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
      getResultQuestionList(param).then(res => {
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
      getResultQuestionList(param).then(res => {
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
      });
    },
    // 点击tab页
    handleTabClick(tab, event) {
      this.o_letterId = tab.name;
    },
    // 初始化委员数据
    initMemberData(examineDate) { 
    // 动态传id
        const param = {
            id:this.caseId,
            examineDate:examineDate
        }
        geSelectMemberList(param).then(res => {
            if(res.data.result&&res.data.result.length>0){
                this.memberList = res.data.result
                
            }
        })
    },
    handleClose(){
        this.dialogVisible = false
    },
    // 点击弹出详情方法
    handleMemberDetail(examineDate){
        this.dialogVisible = true;
        this.initMemberData(examineDate)
    },
    // 非空判断
    isNotEmpty(param) {
      if (
        param != null &&
        param !== undefined &&
        param !== "" &&
        param !== "null" &&
        param !== "undefined"
      ) {
        return true;
      } else {
        return false;
      }
    }
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
.quan{
     background-size:contain;
    background:url('../../../assets/images/quan.png') no-repeat;
}
.zhbjResult{
     font-size: 12px;
     color: #FF9900;
     padding:5px;
     line-height:10px;
    padding-left: 10px;
    padding-right: 7px;
     display:inline-block;
      background-size:cover;
     background:url('../../../assets/images/zhbj.png') no-repeat;
}
.whtgResult{
     font-size: 12px;
     color: #FE5461;
     padding:5px;
     line-height:10px;
    padding-left: 10px;
    padding-right: 7px;
     display:inline-block;
     background-size:cover;
     background:url('../../../assets/images/whtg.png') no-repeat;
}
.htgResult{
     font-size: 12px;
     color: #14BCF5;
     padding:5px;
     padding-left: 10px;
    padding-right: 7px;
     line-height:10px;
     display:inline-block;
      background-size:cover;
     background:url('../../../assets/images/htg.png') no-repeat;
}
.tgResult{
     font-size: 12px;
     color: #14BCF5;
     padding:5px;
     line-height:10px;
     display:inline-block;
     background-size:cover;
    padding-left: 10px;
    padding-right: 7px;
     background:url('../../../assets/images/tg.png') no-repeat;
}
.qxshResult{
     font-size: 12px;
     color: #94A3B4;
     padding:5px;
     padding-left: 10px;
    padding-right: 7px;
     line-height:10px;
     display:inline-block;
      background-size:cover;
     background:url('../../../assets/images/qxsh.png') no-repeat;
}
.dshResult{
     font-size: 12px;
     color: #ffd800;
     padding:5px;
     padding-left: 10px;
    padding-right: 7px;
     line-height:10px;
     display:inline-block;
     background-size:cover;
     background:url('../../../assets/images/dsh.png') no-repeat;
}
.positionLi{
    margin-right:16px;
    margin-bottom:16px;
    border-radius:5px;
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
.finishRead{
  font-family: 'PingFang-SC-Regular', 'PingFang SC';
  font-weight: 400;
  font-style: normal;
  color: #888888;
  line-height: 24px;
  font-size: 14px;
  text-align: center;
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
</style>


