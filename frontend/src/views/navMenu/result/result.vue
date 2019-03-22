<template>
    <div class="result">
        <!-- 上 -->
        <div class="one">
            <ul style="padding-left: 0;">
                <li v-show="baseList.length>0" style="background:rgba(250, 250, 250, 1);padding-left: 17px; padding-top: 10px; padding-bottom: 10px;margin-top: 32px;">
                    <div class="text">
                        <p style="font-size:14px;">
                            <span style="font-family:'微软雅黑';font-weight:400;color:#999;">第一次审核会议: </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[0].relationFileTitle}}</span>
                        </p>
                    </div>
                    <div class="text ">
                        <p style="font-size:14px;">
                            <span style="font-size:14px;color:#999;">第一次审核结果: </span>
                            <span class="bqResult">{{ baseList[0].iecResultStr}}</span>
                        </p>
                    </div>
                    <div class="text ">
                         <p style="font-size:14px;">
                             <span style="color:#999;">发审会委员: </span>
                             <span v-for="name in baseList[0].member" style="color:#333;">{{name}}</span>
                             <span style="color:#6B6B6B;"> </span>
                             <span style="color:#6699FF;" @click="handleMemberDetail()">详情 &gt;</span>
                        </p>
                    </div>
                    <!-- 委员弹窗 -->
                    <el-dialog
                        title="发审会委员"
                        :visible.sync="dialogVisible"
                        width="73.6%"
                        :before-close="handleClose">
                        <ul class="clear" style="width:100%;padding-left:2px;height: 434px;overflow-y: scroll;">
                            <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
                                </div>
                            </li>
                            <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
                                </div>
                            </li>
                            <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
                                </div>
                            </li>
                            <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
                                </div>
                            </li>
                            <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
                                </div>
                            </li>
                            <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
                                </div>
                            </li>
                             <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
                                </div>
                            </li>
                            <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
                                </div>
                            </li>
                            <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
                                </div>
                            </li>
                             <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
                                </div>
                            </li>
                            <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
                                </div>
                            </li>
                            <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
                                </div>
                            </li>
                            <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
                                </div>
                            </li>
                            <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
                                </div>
                            </li>
                            <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
                                </div>
                            </li>
                             <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
                                </div>
                            </li>
                            <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
                                </div>
                            </li>
                            <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
                                </div>
                            </li>
                        </ul>
                    </el-dialog>
                </li>
                 <li v-show="baseList[1]" style="background:rgba(250, 250, 250, 1);padding-left: 17px; padding-top: 10px; padding-bottom: 10px;margin-top: 32px;">
                    <div class="text">
                        <p style="font-size:14px;">
                            <span style="font-family:'微软雅黑';font-weight:400;color:#999;">第二次审核会议: </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#333;"> {{baseList[1].relationFileTitle}}</span>
                        </p>
                    </div>
                    <div class="text ">
                        <p style="font-size:14px;">
                            <span style="font-size:14px;color:#999;">第二次审核结果: </span>
                            <span class="bqResult">{{ baseList[1].iecResultStr}}</span>
                        </p>
                    </div>
                    <div class="text ">
                         <p style="font-size:14px;">
                             <span style="color:#999;">发审会委员: </span>
                             <span v-for="name in baseList[1].member" style="color:#333;">{{name}}</span>
                             <span style="color:#6B6B6B;"> </span>
                             <span style="color:#6699FF;" @click="handleMemberDetail()">详情 &gt;</span>
                        </p>
                    </div>
                    <!-- 委员弹窗 -->
                    <el-dialog
                        title="发审会委员"
                        :visible.sync="dialogVisible"
                        width="73.6%"
                        :before-close="handleClose">
                        <ul class="clear" style="width:100%;padding-left:2px;height: 434px;overflow-y: scroll;">
                            <li class="l positionLi" style="width:31.6%;border:1px solid lightgray;padding:20px">
                                <div>
                                    <span style="text-align:left;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color:#333; line-height: 22px;">王童萱</span>
                                    <span style="color: #666;margin-left:32px">男</span>
                                </div>
                                <span class="position" style="background: #14bcf5;
                                    font-weight: 400;
                                    font-style: normal;
                                    font-size: 12px;
                                    line-height: 18px;
                                    color: #fff;
                                    display:inline-block;
                                    padding-left: 7px;
                                    padding-right:7px;
                                    margin-top: 6px;
                                    margin-bottom: 10px;">抽查处处长</span>
                                <div>
                                    <span style="color: #666;">学历/专业：</span>
                                    <span style="color: #666">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作单位：</span>
                                    <span style="color: #666">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: #666;">工作性质：</span>
                                    <span style="color: #666">兼职</span>
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
                <span v-if="baseList.length==1" class="titleText" id="result">第一次发审会关注问题</span>
                <span v-if="baseList.length==2" class="titleText" id="result">第二次发审会关注问题</span>
                <span v-if="baseList.length==3" class="titleText" id="result">第三次发审会关注问题</span>
            </div>
            <div>
                <ul style="padding-left:0">
                    <li v-for="data in questionList" :key="data.questionId" style="border-bottom:1px solid #e1e1e1;padding-bottom:15px;margin-bottom:17px">
                        <div class="text" style="position:relative">
                            <div style="background:rgba(250, 250, 250, 1); padding: 10px 24px;margin-bottom:12px;position:relative">
                                <div class="wen">问</div>
                                <p style="font-size: 14px;color: rgb(51, 51, 51);line-height: 22px;">{{data.question}}</p>
                            </div>
                            
                            <div>
                              <span v-for="item in data.labelList" class="biaoqian" style="margin-right:2px">{{item}}</span>
                            </div>
                        </div>
                        
                    </li>
                </ul>
            </div> 
        </div>
        <div class="three">
            <p style="font-family: 'PingFang-SC-Regular', 'PingFang SC';
                font-weight: 400;
                font-style: normal;
                color: #888888;
                line-height: 24px;
                font-size: 14px;
                text-align: center;">已经阅读完了
            </p>
        </div>
    </div>
</template>
<script>
import {geSelectFeedbackList } from '@/api/companyProfile'
export default {
  name: "result",
  data() {
    return {
         caseId:this.$store.state.caseId,
         dialogVisible:false,
        //  第几次会议列表
         baseList:[],
        //  问题列表
         questionList:[]
    };
  },
  created() {
    this.initTableData();
  },
  methods: {
    initTableData() { 
    // 动态传id
        const param = {
            id:this.caseId,
        }
        geSelectFeedbackList(param).then(res => {
        //    console.log(res)
           this.baseList = res.data.result.baseList
           this.questionList = res.data.result.questionList
        })
    },
    handleClose(){
        this.dialogVisible = false
    },
    // 点击弹出详情方法
    handleMemberDetail(){
        this.dialogVisible = true
    },
    // 非空判断
    isNotEmpty(param) {
      // debugger
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
.wen{
    width:26px;
    height:26px;
    padding:5px;
    padding-bottom:7px;
    color: #FFFFFF;
    position:absolute;
    top:0;
    left:0;
    font-size:14px;
    background:url('../../../assets/images/wen.png') no-repeat;
    background-size:contain;
}
.quan{
     background-size:contain;
    background:url('../../../assets/images/quan.png') no-repeat;
}
.bqResult{
     font-size: 14px;
     color: #FF9900;
     padding:5px;
     line-height:10px;
     display:inline-block;
      background-size:cover;
     background:url('../../../assets/images/zhbj.png') no-repeat;
}
.positionLi{
    margin-right:16px;
    margin-bottom:16px;
    border-radius:5px;
}

</style>


