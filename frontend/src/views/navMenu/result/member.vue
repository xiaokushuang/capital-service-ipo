<template>
    <div class="member">
       <ul  v-show="memberData.memberList&&memberData.memberList.length>0"  class="clear" style="width:100%;padding-left:2px;overflow-x: hidden;overflow-y:auto;max-height:450px;">
            <li v-for="data in memberData.memberList" class="l positionLi" style="width:300px;border:1px solid lightgray;padding:20px">
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
   </div>
</template>
<script>
import $ from "jquery";
export default {
  name: "member",
  props: ["memberData"],
  data() {
    return {};
  },

  methods: {
    // 鼠标移入委员详情弹出窗的列
    mouseOverSpreadText(title) {
      for (var a = 0; a < $(".position").length; a++) {
        if (title.length > 19) {
          $(".position")
            .eq(a)
            .attr("title", title);
        } else {
          $(".position")
            .eq(a)
            .removeAttr("title", title);
        }
      }
      for (let a = 0; a < $(".xueli").length; a++) {
        if (title.length > 12) {
          $(".xueli")
            .eq(a)
            .attr("title", title);
        } else {
          $(".xueli")
            .eq(a)
            .removeAttr("title", title);
        }
      }
      for (let a = 0; a < $(".danwei").length; a++) {
        if (title.length > 12) {
          $(".danwei")
            .eq(a)
            .attr("title", title);
        } else {
          $(".danwei")
            .eq(a)
            .removeAttr("title", title);
        }
      }
      for (let a = 0; a < $(".xingzhi").length; a++) {
        if (title.length > 12) {
          $(".xingzhi")
            .eq(a)
            .attr("title", title);
        } else {
          $(".xingzhi")
            .eq(a)
            .removeAttr("title", title);
        }
      }
    },
    getContent(title) {
      if (title.length > 20) {
        return title.substring(0, 19) + "...";
      } else {
        return title;
      }
    },
    getContent1(title) {
      if (title.length > 13) {
        return title.substring(0, 12) + "...";
      } else {
        return title;
      }
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
.positionLi {
  margin-right: 16px;
  margin-bottom: 16px;
  border-radius: 5px;
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



