<template>
<div class="stepComponent" >
    <div class="stepsTitle">
        <p style="font-size: 14px; color: #999999;">上市</p>
    </div>
    <div class="approvalProcess" >
        <el-steps :active="active" finish-status="success" direction="vertical" >
           <el-step :title="item.label"  v-for="item in approvalProcessProject" :id="item.id" :key="item.id">
            <template slot="description" >
                <div class="step-row">
                    <span style="font-size: 12px; color: #999999;margin-right:10px;">{{item.date}}</span>
                    <span style="font-size: 12px; color: #999999;">距离上个进程{{item.chajitian}}</span>
                    <p style="font-size:12px;color:#1990FE;cursor:pointer" @click="handleViewgg">查看公告 > </p>
                </div>
            </template>
           </el-step>
        </el-steps>
         <!-- <el-button style="margin-top: 12px;" @click="next">下一步</el-button> -->
 
  </div>
 
</div>
</template>
<script>
 
export default {
   components: {
  },
  props: ['data', 'defaultActive'],
  data() {
    return {
       active: 0,
       approvalProcessProject:[
           {id:'0',label: "方案制定",date:'2018-08-07',chajitian:'21'},
           { id:'1',label: "割接方案会审",date:'2018-08-07',chajitian:'21'},
           { id:'2',label: "割接审批",date:'2018-08-07',chajitian:'21'},
           { id:'3',label: "审批成功",date:'2018-08-07',chajitian:'21'},
       ],
    };
  },
   watch: {
  },
  mounted() {
  },
  computed: {
  },
  methods: {
     next() {
        if (this.active++ > 2) this.active = 0;
      },
    //点击查看更多
    handleViewgg(){

    }
  }
};
</script>
<style scoped lang="scss">

  .stepComponent{
      width: 100%;
  }
  
</style>