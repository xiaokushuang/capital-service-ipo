<template>
    <div>
        <div class="el-dialog__header" style="font-size: 14px;color: #333;" slot="title">{{centerNoteTitle}}
          <i class="el-icon-close" style="float: right;cursor: pointer" title="关闭" @click="cancel"></i>
        </div>
        <div class="el-dialog__body">
        <el-input type="textarea" :rows="textareaRow" placeholder="请在这里输入笔记内容..." v-model="note" >
        </el-input>
        </div>
        <div class="el-dialog__footer" style="float: right">
          <button class="small_btn_common cancel_btn" @click="cancel">取消</button>
          <button class="small_btn_common determine_btn" @click="noteDetermination" style="margin-left: 10px">保存</button>
        </div>
    </div>
</template>

<script>
  import {iframeDoMessage} from "@/utils/auth";
  export default {
      data() {
          return {
            centerNoteFlag: true,
            centerNoteTitle:'',
            caseId:'',
            note:'',
            routeParam:{
              nameSpace:'',
              action:'',
              prompt:'',
            },
            textareaRow: 10,
          }
      },
    created(){
      this.routeParam = this.$route.query;
      this.centerNoteTitle = this.$route.query.centerNoteTitle;
      this.caseId = this.$route.query.caseId;
      //this.note = this.$route.query.note;
    },
    mounted(){
        this.getCaseNote();
        this.setInputHeight();
    },
    methods:{
      setInputHeight(){
        this.textareaRow = parseInt(((window.screen.height - 140)*0.8 - 100)/22);
        //document.getElementsByTagName('textarea')[0].style.height = ((window.screen.height - 100)*0.8 - 100) + 'px';
        //document.getElementsByTagName('textarea')[0].style.height =  '500px';
      },
      getCaseNote(){
				debugger
        let param = {caseId:this.caseId,type: '1'};
        this.$store.dispatch("getIpoCaseFavoriteAndNotes", param).then((data) => {
          this.note = param.data.caseNote;
        })
      },
      cancel(){
        iframeDoMessage(window.parent,'setStage',[ this.routeParam.nameSpace,  this.routeParam.action, {noteIpoEditFlag:false}, 'commit'])
      },
      noteDetermination(){
        iframeDoMessage(window.parent,'setStage',[ this.routeParam.nameSpace,  this.routeParam.action, {noteIpoCaseId:this.caseId,caseIpoNote:this.note,noteIpoEditFlag:true}, 'commit'])
      }
    }
  }
</script>


<style>
  .el-dialog__header {
    padding: 15px 20px 15px;
  }
  .el-dialog__body {
    padding: 0px;
    color: #555;
    line-height: 24px;
    font-size: 14px;
  }
  .el-dialog__footer {
    padding: 8px 15px 3px;/*上下差5px是因为弹出页的时候common里减了5px*/
  }
  .small_btn_common{
    height: 30px;
    padding-left: 12px;
    padding-right: 12px;
    font-size: 14px;
    cursor:pointer;
    border-radius: 2px;
  }
  .cancel_btn{
    background-color: #fff;
    border: 1px solid #cacaca;
    color: #666666;
  }
  .determine_btn{
    background-color: #14bcf5;
    border: 1px solid #14bcf5;
    color: #fff;
  }
  .el-textarea__inner{
    color: #999999;
    font-size: 14px;
    line-height: 22px;
    border: none;
    border-radius: 0px;
    border-top: 1px #e2e2e2 solid;
    border-bottom: 1px #e2e2e2 solid;
    word-break: break-all;
    white-space: pre-wrap;
    resize:none;
  }
  .el-textarea__inner:focus{
    border-color: #e2e2e2;
  }
</style>
