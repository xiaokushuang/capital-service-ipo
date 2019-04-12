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
                            <span  :class="{'htgResult' : baseList[0].iecResult == '00','whtgResult' : baseList[0].iecResult == '01','zhbjResult' : baseList[0].iecResult == '02','qxshResult' : baseList[0].iecResult == '03'}">{{ baseList[0].iecResultStr}}</span>
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
                        width="73.6%"
                        :before-close="handleClose">
                        <ul  v-show="memberList&&memberList.length>0"  class="clear" style="width:100%;padding-left:2px;overflow-x: hidden;overflow-y:auto;max-height:65vh;">
                            <li v-for="data in memberList" class="l positionLi" style="width:31.1%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">{{data.memberName}}</span>
                                    <span style="color: #666;margin-left:32px">{{data.memberGender }}</span>
                                </div>
                                <span class="position"  @mouseenter="mouseOverSpreadText(data.memberPost)" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">{{getContent(data.memberPost)}}</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span  class="xueli" style="color: #666;"  @mouseenter="mouseOverSpreadText(data.memberEducation+'/'+data.memberMajor)">{{getContent1(data.memberEducation+'/'+data.memberMajor)}}</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span class="danwei" style="color: #666;" @mouseenter="mouseOverSpreadText(data.memberCompany)">{{getContent1(data.memberCompany)}}</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span class="xingzhi" style="color: #666;" @mouseenter="mouseOverSpreadText(data.memberType)">{{getContent1(data.memberType)}}</span>
                                </div>
                            </li>
                        </ul>
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
                            <span  :class="{'htgResult' : baseList[0].iecResult == '00','whtgResult' : baseList[0].iecResult == '01','zhbjResult' : baseList[0].iecResult == '02','qxshResult' : baseList[0].iecResult == '03'}">{{ baseList[0].iecResultStr}}</span>
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
                        width="73.6%"
                        :before-close="handleClose">
                        <ul  v-show="memberList&&memberList.length>0"  class="clear" style="width:100%;padding-left:2px;overflow-x: hidden;overflow-y:auto;max-height:65vh;">
                            <li v-for="data in memberList" class="l positionLi" style="width:31.1%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">{{data.memberName}}</span>
                                    <span style="color: #666;margin-left:32px">{{data.memberGender }}</span>
                                </div>
                                <span class="position"  @mouseenter="mouseOverSpreadText(data.memberPost)" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">{{getContent(data.memberPost)}}</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span  class="xueli" style="color: #666;"  @mouseenter="mouseOverSpreadText(data.memberEducation+'/'+data.memberMajor)">{{getContent1(data.memberEducation+'/'+data.memberMajor)}}</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span class="danwei" style="color: #666;" @mouseenter="mouseOverSpreadText(data.memberCompany)">{{getContent1(data.memberCompany)}}</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span class="xingzhi" style="color: #666;" @mouseenter="mouseOverSpreadText(data.memberType)">{{getContent1(data.memberType)}}</span>
                                </div>
                            </li>
                        </ul>
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
                            <span  :class="{'htgResult' : baseList[1].iecResult == '00','whtgResult' : baseList[1].iecResult == '01','zhbjResult' : baseList[1].iecResult == '02','qxshResult' : baseList[1].iecResult == '03'}">{{ baseList[1].iecResultStr}}</span>
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
                        width="73.6%"
                        :before-close="handleClose">
                        <ul  v-show="memberList&&memberList.length>0"  class="clear" style="width:100%;padding-left:2px;overflow-x: hidden;overflow-y:auto;max-height:65vh;">
                            <li v-for="data in memberList" class="l positionLi" style="width:31.1%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">{{data.memberName}}</span>
                                    <span style="color: #666;margin-left:32px">{{data.memberGender }}</span>
                                </div>
                                <span class="position"  @mouseenter="mouseOverSpreadText(data.memberPost)" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    
                                    margin-bottom: 10px;">{{getContent(data.memberPost)}}</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span  class="xueli" style="color: #666;"  @mouseenter="mouseOverSpreadText(data.memberEducation+'/'+data.memberMajor)">{{getContent1(data.memberEducation+'/'+data.memberMajor)}}</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span class="danwei" style="color: #666;" @mouseenter="mouseOverSpreadText(data.memberCompany)">{{getContent1(data.memberCompany)}}</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span class="xingzhi" style="color: #666;" @mouseenter="mouseOverSpreadText(data.memberType)">{{getContent1(data.memberType)}}</span>
                                </div>
                            </li>
                        </ul>
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
                            <span  :class="{'htgResult' : baseList[0].iecResult == '00','whtgResult' : baseList[0].iecResult == '01','zhbjResult' : baseList[0].iecResult == '02','qxshResult' : baseList[0].iecResult == '03'}">{{ baseList[0].iecResultStr}}</span>
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
                             <!-- <span v-for="name in baseList[0].member" style="color:#333;">{{name}}</span>
                             <span style="color:#6B6B6B;"> </span>
                             <span style="color:#6699FF;cursor:pointer" @click="handleMemberDetail()">详情 &gt;</span> -->
                        </p>
                    </div>
                   <!-- 委员弹窗 -->
                    <el-dialog
                        title="发审会委员"
                        :visible.sync="dialogVisible"
                        width="73.6%"
                        :before-close="handleClose">
                        <ul  v-show="memberList&&memberList.length>0"  class="clear" style="width:100%;padding-left:2px;overflow-x: hidden;overflow-y:auto;max-height:65vh;">
                            <li v-for="data in memberList" class="l positionLi" style="width:31.1%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">{{data.memberName}}</span>
                                    <span style="color: #666;margin-left:32px">{{data.memberGender }}</span>
                                </div>
                                <span class="position"  @mouseenter="mouseOverSpreadText(data.memberPost)" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    
                                    margin-bottom: 10px;">{{getContent(data.memberPost)}}</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span  class="xueli" style="color: #666;"  @mouseenter="mouseOverSpreadText(data.memberEducation+'/'+data.memberMajor)">{{getContent1(data.memberEducation+'/'+data.memberMajor)}}</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span class="danwei" style="color: #666;" @mouseenter="mouseOverSpreadText(data.memberCompany)">{{getContent1(data.memberCompany)}}</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span class="xingzhi" style="color: #666;" @mouseenter="mouseOverSpreadText(data.memberType)">{{getContent1(data.memberType)}}</span>
                                </div>
                            </li>
                        </ul>
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
                            <span  :class="{'htgResult' : baseList[1].iecResult == '00','whtgResult' : baseList[1].iecResult == '01','zhbjResult' : baseList[1].iecResult == '02','qxshResult' : baseList[1].iecResult == '03'}">{{ baseList[1].iecResultStr}}</span>
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
                             <!-- <span v-for="name in baseList[1].member" style="color:#333;">{{name}}</span>
                             <span style="color:#6B6B6B;"> </span>
                             <span style="color:#6699FF;cursor:pointer" @click="handleMemberDetail()">详情 &gt;</span> -->
                        </p>
                    </div>
                    <!-- 委员弹窗 -->
                    <el-dialog
                        title="发审会委员"
                        :visible.sync="dialogVisible"
                        width="73.6%"
                        :before-close="handleClose">
                        <ul  v-show="memberList&&memberList.length>0"  class="clear" style="width:100%;padding-left:2px;overflow-x: hidden;overflow-y:auto;max-height:65vh;">
                            <li v-for="data in memberList" class="l positionLi" style="width:31.1%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">{{data.memberName}}</span>
                                    <span style="color: #666;margin-left:32px">{{data.memberGender }}</span>
                                </div>
                                <span class="position"  @mouseenter="mouseOverSpreadText(data.memberPost)" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    
                                    margin-bottom: 10px;">{{getContent(data.memberPost)}}</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span  class="xueli" style="color: #666;"  @mouseenter="mouseOverSpreadText(data.memberEducation+'/'+data.memberMajor)">{{getContent1(data.memberEducation+'/'+data.memberMajor)}}</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span class="danwei" style="color: #666;" @mouseenter="mouseOverSpreadText(data.memberCompany)">{{getContent1(data.memberCompany)}}</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span class="xingzhi" style="color: #666;" @mouseenter="mouseOverSpreadText(data.memberType)">{{getContent1(data.memberType)}}</span>
                                </div>
                            </li>
                        </ul>
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
                            <span  :class="{'htgResult' : baseList[2].iecResult == '00','whtgResult' : baseList[2].iecResult == '01','zhbjResult' : baseList[2].iecResult == '02','qxshResult' : baseList[2].iecResult == '03'}">{{ baseList[2].iecResultStr}}</span>
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
                             <!-- <span v-for="name in baseList[2].member" style="color:#333;">{{name}}</span>
                             <span style="color:#6B6B6B;"> </span>
                             <span style="color:#6699FF;cursor:pointer" @click="handleMemberDetail()">详情 &gt;</span> -->
                        </p>
                    </div>
                   <!-- 委员弹窗 -->
                    <el-dialog
                        title="发审会委员"
                        :visible.sync="dialogVisible"
                        width="73.6%"
                        :before-close="handleClose">
                        <ul  v-show="memberList&&memberList.length>0"  class="clear" style="width:100%;padding-left:2px;overflow-x: hidden;overflow-y:auto;max-height:65vh;">
                            <li v-for="data in memberList" class="l positionLi" style="width:31.1%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">{{data.memberName}}</span>
                                    <span style="color: #666;margin-left:32px">{{data.memberGender }}</span>
                                </div>
                                <span class="position"  @mouseenter="mouseOverSpreadText(data.memberPost)" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    
                                    margin-bottom: 10px;">{{getContent(data.memberPost)}}</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span  class="xueli" style="color: #666;"  @mouseenter="mouseOverSpreadText(data.memberEducation+'/'+data.memberMajor)">{{getContent1(data.memberEducation+'/'+data.memberMajor)}}</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span class="danwei" style="color: #666;" @mouseenter="mouseOverSpreadText(data.memberCompany)">{{getContent1(data.memberCompany)}}</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span class="xingzhi" style="color: #666;" @mouseenter="mouseOverSpreadText(data.memberType)">{{getContent1(data.memberType)}}</span>
                                </div>
                            </li>
                        </ul>
                    </el-dialog>
                </li>
            </ul>
        </div> 
        <!-- 下 -->
        <div class="two">
             <div class="title">
                <span class="littleRectangle"></span>
                <span class="titleText" id="result">发审会关注问题</span>
                <!-- <span v-if="baseList.length==2" class="titleText" id="result">第二次发审会关注问题</span>
                <span v-if="baseList.length==3" class="titleText" id="result">第三次发审会关注问题</span> -->
            </div>
            <div>
                <ul style="padding-left:0">
                    <li v-for="data in questionList" :key="data.questionId" style="border-bottom:1px solid #e1e1e1;padding-bottom:15px;margin-bottom:17px">
                        <div class="text" style="position:relative">
                            <div style="background:rgba(250, 250, 250, 1); padding: 10px 24px;margin-bottom:12px;position:relative">
                                <div v-if="data.question&&data.question.length>0" class="wen">问</div>
                                <p v-if="data.question&&data.question.length>0" style="font-size: 14px;color: rgb(51, 51, 51);line-height: 22px;">{{data.question}}</p>
                                <div v-if="data.answer&&data.answer.length>0" class="da">答</div>
                                <p v-if="data.answer&&data.answer.length>0" style="font-size: 14px;color: rgb(51, 51, 51);line-height: 22px;">{{data.answer}}</p>
                            </div>
                            
                            <div>
                              <span v-for="item in data.labelList" class="biaoqian" style="margin-right:2px">{{item}}</span>
                            </div>
                        </div>
                        
                    </li>
                </ul>
            </div> 
        </div>
       <!-- 加载更多 -->
        <div v-loading="flagLoading" element-loading-text="给我一点时间" v-if="showMore" @click="showMoreMethods()" class="more">加载更多</div>
        <!-- 已经阅读完了 -->
        <p v-if="questionList.length>0&&!showMore" class="finishRead">已经阅读完了</p>
        <!-- 暂无数据 -->
        <p v-if="questionList.length==0&&!showMore" class="finishRead">暂无相关数据</p>
        
</div>
</template>
<script>
import {geSelectFeedbackList } from '@/api/ipoCase/companyProfile'
import {geSelectMemberList } from '@/api/ipoCase/companyProfile'
import $ from "jquery";
export default {
  name: "result",
  data() {
    return {
         caseId:this.$store.state.app.caseId,
         dialogVisible:false,
        //  第几次会议列表
         baseList:[],
        //  问题列表
         questionList:[],
        //  委员列表
        memberList:[],
        showMore:false,
        allQuestionList:[]
    };
  },
  created() {
    this.initTableData();
  },
  methods: {
    //   初始化所有数据
    initTableData() { 
    // 动态传id
        const param = {
            id:this.caseId,
        }
        geSelectFeedbackList(param).then(res => {
            if(res.data.result&&res.data.result.baseList&&res.data.result.baseList.length>0){
                this.baseList = res.data.result.baseList
            }
            if(res.data.result&&res.data.result.questionList&&res.data.result.questionList.length>0){
                this.allQuestionList = res.data.result.questionList
            }
            if(this.allQuestionList.length > 15){
              this.showMore = true;
              this.questionList = this.allQuestionList.slice(0,15);
            }else{
              this.showMore = false;
              this.questionList = this.allQuestionList;
            }
        //    this.questionList = res.data.result.questionList
        })
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
        // 点击加载更多
    showMoreMethods(){
        this.showLength+=15
        if(this.allQuestionList.length > this.showLength){
          this.showMore = true;
          this.questionList = this.allQuestionList.slice(0, this.showLength);
        }else{
          this.showMore = false;
          this.questionList = this.allQuestionList;
        }
    },
    // 鼠标移入委员详情弹出窗的列
    mouseOverSpreadText(title){ 
        for(var a = 0;a< $(".position").length;a++){
            if(title.length>15){
                $(".position").eq(a).attr("title",title)
                $(".position").eq(a).css({"cursor":"pointer"})
            }else{
                $(".position").eq(a).removeAttr("title",title)
                $(".position").eq(a).css({"cursor":"auto"})
            }
        }
         for(let a = 0;a< $(".xueli").length;a++){
             if(title.length>9){
                $(".xueli").eq(a).attr("title",title)
                $(".xueli").eq(a).css({"cursor":"pointer"})
            }else{
                $(".xueli").eq(a).removeAttr("title",title)
                $(".xueli").eq(a).css({"cursor":"auto"})
            }
        }
        for(let a = 0;a< $(".danwei").length;a++){
             if(title.length>9){
                $(".danwei").eq(a).attr("title",title)
                $(".danwei").eq(a).css({"cursor":"pointer"})
            }else{
                $(".danwei").eq(a).removeAttr("title",title)
                $(".danwei").eq(a).css({"cursor":"auto"})
            }
        }
        for(let a = 0;a< $(".xingzhi").length;a++){
             if(title.length>9){
                $(".xingzhi").eq(a).attr("title",title)
                $(".xingzhi").eq(a).css({"cursor":"pointer"})
            }else{
                $(".xingzhi").eq(a).removeAttr("title",title)
                $(".xingzhi").eq(a).css({"cursor":"auto"})
            }
        }
    },
   getContent(title){
     if(title.length>15){
       return title.substring(0,15) + '...'
     }else{
       return title
     }
   },
   getContent1(title){
     if(title.length>9){
       return title.substring(0,9) + '...'
     }else{
       return title
     }
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
  components: {}
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
     font-size: 14px;
     color: #FF9900;
     padding:5px;
     line-height:10px;
     display:inline-block;
      background-size:cover;
     background:url('../../../assets/images/zhbj.png') no-repeat;
}
.whtgResult{
     font-size: 14px;
     color: #FE5461;
     padding:5px;
     line-height:10px;
     display:inline-block;
      background-size:cover;
     background:url('../../../assets/images/whtg.png') no-repeat;
}
.htgResult{
     font-size: 14px;
     color: #14BCF5;
     padding:5px;
     line-height:10px;
     display:inline-block;
      background-size:cover;
     background:url('../../../assets/images/htg.png') no-repeat;
}
.qxshResult{
     font-size: 14px;
     color: #94A3B4;
     padding:5px;
     line-height:10px;
     display:inline-block;
      background-size:cover;
     background:url('../../../assets/images/qxsh.png') no-repeat;
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

</style>


