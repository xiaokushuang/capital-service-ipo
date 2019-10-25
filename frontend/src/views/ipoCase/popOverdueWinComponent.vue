<template>
  <div style="padding:18px 32px 0px 32px;">
    <div class="el-message-box__content" style="margin-top: 0px;">
      <div class="el-message-box__message">
        <div>
          <a>本功能为独立收费模块，若想查看更多数据，请与我们联系。</a>
          <br/>
          <br/>
          <a style="font-size: 13px">来电咨询：400-880-3388</a>
        </div>
        <div class="el-message-box__btns" style="text-align: center;margin-top: 24px">
          <el-button type="primary" size="small" @click.native="sure" style="margin-left: 0px;">
            在线咨询
          </el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import { iframeDoMessage } from "@/utils/auth";
  export default {
    data() {
      return {
      }
    },
    created() {
    },
    methods: {
      sure(){
          setTimeout(function(){ iframeDoMessage(window.parent,'setStage',['', '', '', '']); }, 500);
          let url =  "https://vs.rainbowred.com/visitor/pc/chat.html?companyId=488&amp;echatTag=网站咨询";
          window.open(url,"","toolbar=0,scrollbars=0,location=0,menubar=0,resizable=1,width=854,height=600");
        }
    },
    components: {}
  }
</script>
