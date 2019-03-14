<template>
    <div class="result">
        <!-- 上 -->
        <div class="one">
            <ul style="padding-left: 0;">
                <li style="background:rgba(250, 250, 250, 1);padding-left: 17px; padding-top: 10px; padding-bottom: 10px;margin-top: 40px;">
                    <div class="text">
                        <p style="font-size:14px;">
                            <span style="font-family:'微软雅黑';font-weight:400;color:#868686;">第一次审核会议: </span>
                            <span style="font-family:'微软雅黑';font-weight:400;color:#555555;"> 第十七届发审委2018年第189次工作会议 _2018年12月7日</span>
                        </p>
                    </div>
                    <div class="text ">
                        <p style="font-size:14px;">
                            <span style="font-size:14px;color:#868686;">第一次审核结果: </span>
                            <span style="color:#555555;">浙江三美化工股份有限公司（首发）</span>
                            <span class="bqResult">暂缓表决</span>
                        </p>
                    </div>
                    <div class="text ">
                         <p style="font-size:14px;">
                             <span style="color:#868686;">发审会委员: </span>
                             <span style="color:#555555;">张三，李四，王五，陈雪莲，赵六，姓名1，姓名2；</span>
                             <span style="color:#6B6B6B;"> </span>
                             <span style="color:#6699FF;" @click="handleMemberDetail()">详情 &gt;</span>
                        </p>
                    </div>
                    <!-- 委员弹窗 -->
                    <el-dialog
                        title="发审会委员"
                        :visible.sync="dialogVisible"
                        width="80%"
                        :before-close="handleClose">
                        <ul class="clear" style="width:100%;padding-left:20px">
                              <li class="l positionLi" style="width:23%;height:240px;border:1px solid lightgray;text-align:center">
                                <p style="text-align:center;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color: rgba(0, 0, 0, 0.847058823529412); line-height: 22px;">王童萱</p>
                                <span class="position" style="background:#6699ff;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le';font-weight: 400;font-style: normal;font-size: 12px; color: rgba(255, 255, 255, 0.647058823529412);padding:3px;position:relative;top:-22px;">抽查处处长</span>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">性别：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">男</span>
                                </div>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">学历/专业：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">工作单位：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">工作性质：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">兼职</span>
                                </div>
                              </li>
                               <li class="l positionLi" style="width:23%;height:240px;border:1px solid lightgray;text-align:center">
                                <p style="text-align:center;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color: rgba(0, 0, 0, 0.847058823529412); line-height: 22px;">王童萱</p>
                                <span class="position" style="background:#6699ff;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le';font-weight: 400;font-style: normal;font-size: 12px; color: rgba(255, 255, 255, 0.647058823529412);padding:3px;position:relative;top:-22px;">抽查处处长</span>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">性别：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">男</span>
                                </div>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">学历/专业：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">工作单位：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">工作性质：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">兼职</span>
                                </div>
                            </li>
                             <li class="l positionLi" style="width:23%;height:240px;border:1px solid lightgray;text-align:center">
                                <p style="text-align:center;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color: rgba(0, 0, 0, 0.847058823529412); line-height: 22px;">王童萱</p>
                                <span class="position" style="background:#6699ff;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le';font-weight: 400;font-style: normal;font-size: 12px; color: rgba(255, 255, 255, 0.647058823529412);padding:3px;position:relative;top:-22px;">抽查处处长</span>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">性别：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">男</span>
                                </div>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">学历/专业：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">工作单位：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">工作性质：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">兼职</span>
                                </div>
                            </li>
                             <li class="l positionLi" style="width:23%;height:240px;border:1px solid lightgray;text-align:center">
                                <p style="text-align:center;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color: rgba(0, 0, 0, 0.847058823529412); line-height: 22px;">王童萱</p>
                                <span class="position" style="background:#6699ff;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le';font-weight: 400;font-style: normal;font-size: 12px; color: rgba(255, 255, 255, 0.647058823529412);padding:3px;position:relative;top:-22px;">抽查处处长</span>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">性别：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">男</span>
                                </div>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">学历/专业：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">工作单位：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">工作性质：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">兼职</span>
                                </div>
                            </li>
                             <li class="l positionLi" style="width:23%;height:240px;border:1px solid lightgray;text-align:center">
                                <p style="text-align:center;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color: rgba(0, 0, 0, 0.847058823529412); line-height: 22px;">王童萱</p>
                                <span class="position" style="background:#6699ff;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le';font-weight: 400;font-style: normal;font-size: 12px; color: rgba(255, 255, 255, 0.647058823529412);padding:3px;position:relative;top:-22px;">抽查处处长</span>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">性别：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">男</span>
                                </div>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">学历/专业：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">工作单位：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">工作性质：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">兼职</span>
                                </div>
                            </li>
                             <li class="l positionLi" style="width:23%;height:240px;border:1px solid lightgray;text-align:center">
                                <p style="text-align:center;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le Regular', 'Microsoft Tai Le'; font-weight: 400;font-style: normal;font-size: 28px;color: rgba(0, 0, 0, 0.847058823529412); line-height: 22px;">王童萱</p>
                                <span class="position" style="background:#6699ff;font-family: 'Microsoft Tai Le Normal', 'Microsoft Tai Le';font-weight: 400;font-style: normal;font-size: 12px; color: rgba(255, 255, 255, 0.647058823529412);padding:3px;position:relative;top:-22px;">抽查处处长</span>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">性别：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">男</span>
                                </div>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">学历/专业：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">硕士/法律</span>
                                </div>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">工作单位：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">深圳证监局</span>
                                </div>
                                <div>
                                    <span style="color: rgba(0, 0, 0, 0.847058823529412);">工作性质：</span>
                                    <span style="color: rgba(0, 0, 0, 0.647058823529412);">兼职</span>
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
                <span class="titleText" id="result">第二次发审会关注问题</span>
            </div>
            <div>
                <ul style="padding-left:0">
                    <li style="border-bottom:1px solid lightgray;padding-bottom:15px;margin-bottom:30px">
                        <div class="text" style="background:rgba(250, 250, 250, 1); padding: 10px 24px;margin-bottom:10px;position:relative">
                            <div class="wen">问</div>
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
                        </div>
                        <div>
                            <span class="biaoqian" style="margin-right:2px">经营资质</span>
                            <span class="biaoqian" style="margin-right:2px">经营资质从v人</span>
                        </div>
                    </li>
                    <li style="border-bottom:1px solid lightgray;padding-bottom:15px;margin-bottom:30px">
                        <div class="text" style="background:rgba(250, 250, 250, 1); padding: 10px 24px;margin-bottom:10px;position:relative">
                            <div class="wen">问</div>
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
                        </div>
                        <div>
                            <span class="biaoqian" style="margin-right:2px">经营资质</span>
                            <span class="biaoqian" style="margin-right:2px">经营资质从v人</span>
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
import { getResultList } from "@/api/result";
export default {
  name: "result",
  data() {
    return {
        dialogVisible:false
    };
  },
  created() {
    this.initTableData();
  },
  methods: {
    handleClose(){},
    // 点击弹出详情方法
    handleMemberDetail(){
        this.dialogVisible = true
    },
    initTableData() {
      getResultList().then(res => {
        // console.log(res);
      });
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
  margin-top: 40px;
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
    margin-right:20px;
    margin-bottom:20px;
    border-radius:5px;
}

</style>


