<template>
  <div class="holdUpHeight">
    <el-col :span="24" class="table-footer clearfix" style="padding: 6px 10px 0 10px;">
        <el-col :span="8" class="DT-label">
            显示第 {{submitData.start +' 至 '+ toPaper}} 项数据，共 {{total}} 项
        </el-col>
        <el-col :span="5" class="DT-label secondDiv" style="text-align:right;padding-right:15px;">
            <label>
                每页显示
                <select v-model="submitData.pageSize" @change="paper_chose" name="tableLength" class="form-control input-sm">
                    <option value="10">10</option>
                    <option value="20">20</option>
                    <option value="50">50</option>
                    <option value="100">100</option>
                </select>
                项数据
            </label>
        </el-col>
        <el-col :span="11" class="lastDiv">
            <div class="dataTables_paginate paging_input text-right">
                <span style="border-right: none;" class="first undefined btn btn-default" :class="{disabled:disableRules}"  id="firstPage"  @click="firstPage()">首页</span><span style="border-right: none;" class="previous paginate_button btn btn-default" :class="{disabled:disableRules}" @click="pre()">上页</span><span style="border-right: none;" class="next paginate_button btn btn-default" :class="{disabled:disableRulesEnd}" @click="next()">下页</span><span class="last paginate_button btn btn-default" :class="{disabled:disableRulesEnd}" @click="lastPage()">末页</span>
                <span class="paginate_of"> 第 </span>
                <input id="nowpage" class="paginate_input form-control" v-model="now_paper_number" @input="paperInput($event)" type="text">
                <span class="paginate_of"> 页，共 {{Math.ceil(total/Math.ceil(this.submitData.pageSize))}} 页 </span>
            </div>
        </el-col>
    </el-col>
  </div>
</template>

<script>

export default {
  name: 'papers',
  components: {  },
  data(){
      return {
          //total:388,//总条数
          //now_paper_number:1,//当前地多少页
          submitData:{//需要提交的数据
            start:1,//开始数据条数
            pageSize:10,//selected选择每页显示条数
            orderByName:"",
            orderByOrder:"",
          },
          orgob:{},
      }
  },
  props:{
    total:{
        type:Number,
        default:10
    },
     length1:{
        type:Number,
        default:10
    },
    sdefault:{
        type:Object,
        default:()=>{}
    },
    test:{
        type:String,
        default:''
    }
  },
  mounted(){
      this.submitData.pageSize = this.length1;
      if(this.total==0){
          this.submitData.start = 0
      }else{
          this.submitData.start = 1
      }
  },
  computed:{
        fromPaper(){//从多少条开始
            //console.log(this.total+'-----------------------------total')
            if(this.total == 0 ) {
                if(this.submitData.start != 0){
                    this.submitData.start = 0
                }
                return 0;
            }
            let mid = this.now_paper_number;
            if(mid == 0) mid = 1
            let star = (Math.ceil(this.submitData.pageSize)*mid-Math.ceil(this.submitData.pageSize))+1
            // debugger;
            if(star>this.total){
                //console.log(star + '> '+this.total)
                if(this.total!=0){
                     //console.log('_____________toPaper = '+this.submitData.pageSize)
                    //  this.submitData.start = this.total-Math.ceil(this.submitData.pageSize)+1
                    this.submitData.start = this.total-(this.total%this.submitData.pageSize==0?this.submitData.pageSize:this.total%this.submitData.pageSize) +1
                    //  this.toPaper = this.submitData.pageSize
                    if(this.total>this.submitData.pageSize){
                        return this.total-Math.ceil(this.submitData.pageSize)+1
                    }else{
                        return 1
                    }
                }else{
                    return 0
                } 
            }else{
                //console.log(star + '《 '+this.total)
                return star
            }
        },
        toPaper(){//查到多少条
            //console.log(this.total)
            if(this.total ==0){
                return 0
            }
            if(this.submitData.start+Math.ceil(this.submitData.pageSize) > this.total){
                if(this.total!=0){
                        return this.total
                }else{
                        // this.total = this.submitData.pageSize
                        // this.submitData.start = 0;
                        // return  this.total
                        return this.submitData.pageSize;
                }
               
            }else{
                return this.submitData.start+Math.ceil(this.submitData.pageSize)-1
            }
        },
        disableRules(){//首页，上一页disable
            return (this.total<=Math.ceil(this.submitData.pageSize)||this.now_paper_number==1)
        },
        disableRulesEnd(){//末页，下一页disable
            return (this.total<=Math.ceil(this.submitData.pageSize)||this.now_paper_number==Math.ceil(this.total/Math.ceil(this.submitData.pageSize)))
        },
        now_paper_number:{//计算出来的页数
            get:function(){
                // debugger
                if(this.total == 0){
                    return 0
                }
                let mid = this.submitData.start;
                if(mid == 0) mid = 1;
                // debugger;
                return Math.ceil(mid/Math.ceil(this.submitData.pageSize))
            },
            set:function(val){
                if(this.total == 0){ 
                    this.submitData.start = 0;
                    return
                }
                if(val<=Math.ceil(this.total/Math.ceil(this.submitData.pageSize))){
                    this.submitData.start = (val-1)*Math.ceil(this.submitData.pageSize)+1
                }else{
                    this.submitData.start = this.lastPage()
                    this.submitData.start ++;
                    this.submitData.start --;
                    // this.now_paper_number = Math.ceil(this.total/Math.ceil(this.submitData.pageSize))
                }
                
            }
        }
  },
  methods: {
    resize() {
      //console.log('resize')
    },
    setPageParam(start,length){//重新设置分页参数
        this.submitData.start = start;
        this.submitData.pageSize = length;
    },
    search(a,b){//搜索
        if(arguments.length!=0){
            this.submitData.orderByName = a;
            this.submitData.orderByOrder = b;
        }
        let data = this.submitData;
        data.startRow = this.fromPaper-1;
        if(this.total==0){
            data.startRow = this.fromPaper
        }
        // console.log(`从${data.startRow}多少条开始查`)
        // console.log(`要查${data.length}条`)
        // console.log(`按照${data.orderByName}字段排序`)
        // console.log(`排序方式：${data.orderByOrder}`)
        // console.log(data)
        //console.log(this.sdefault)
        let _this = this;
        setTimeout(()=>{
            let orgob = Object.assign(_this.orgob,_this.sdefault)
            let queryParam  = {condition:orgob};
            queryParam = Object.assign(queryParam,data)
            //console.log(queryParam);
            _this.$emit('searchTable',queryParam)
        },100)
    },
    firstPage(){//首页
        if(this.total==0) return
        this.submitData.start = 1;
    },
    lastPage(){//最后一页
        if(this.total==0) return
        var now;
        if(this.total%Math.ceil(this.submitData.pageSize)!=0){
            now = (Math.ceil(this.total/Math.ceil(this.submitData.pageSize))-1)*Math.ceil(this.submitData.pageSize)
        }else{
            now = this.total-Math.ceil(this.submitData.pageSize)
        }
        this.submitData.start = now+1
        return this.submitData.start
    },
    pre(){//上一页
        if(this.total==0) return
        if(this.now_paper_number>1){
            this.submitData.start = this.submitData.start-Math.ceil(this.submitData.pageSize)
        }
    },
    next(){//下一页
        if(this.total==0) return
        if(this.now_paper_number<Math.ceil(this.total/Math.ceil(this.submitData.pageSize))){
            this.submitData.start = this.submitData.start+Math.ceil(this.submitData.pageSize)
        }
    },
    paper_chose(){//选择每页显示多少条
        //console.log(typeof Math.ceil(this.submitData.pageSize))
        if((this.submitData.start+parseInt(Math.ceil(this.submitData.pageSize)))==this.total){
            if(this.total == 0){
                this.submitData.start = 0;
                return
            }
            this.submitData.start = this.total-Math.ceil(this.submitData.pageSize)
        }
        this.search();
    },
    paperInput(e){
        // debugger
        if(this.total==0){
            this.submitData.start = 0
            return
        }
        if(this.now_paper_number*Math.ceil(this.submitData.pageSize)>=this.total){
            this.submitData.start = (this.now_paper_number-1)*Math.ceil(this.submitData.pageSize) +1
            // this.now_paper_number = Math.ceil(this.total/Math.ceil(this.submitData.pageSize))
        }
    }
  },
  watch:{
      fromPaper(n,o){
          if(n!=o && n!=0 && o!=0){
            this.search(); 
          } 
      },
      now_paper_number(n,o){
        if(this.now_paper_number*Math.ceil(this.submitData.pageSize)>=this.total){
            if(this.total==0){
                this.submitData.start = 0
                return
            }
            this.submitData.start = (this.now_paper_number-1)*Math.ceil(this.submitData.pageSize) +1
            // this.now_paper_number = Math.ceil(this.total/Math.ceil(this.submitData.pageSize))
        }
        if(n ==0&&this.total==0){
            this.now_paper_number = 0
        }
        if(n ==1&&this.total!=0){
            this.now_paper_number = 1;
            this.submitData.start = 1
        }
        if(n ==0&&this.total!=0){
            this.now_paper_number = 1;
            this.submitData.start = 1
        }
      },
      sdefault(n,o){
          this.orgob = Object.assign({},n)
      }
  }
}
</script>

<style  scoped>
    .table-footer {
        padding-right: 10px;
    }
    .table-footer {
        min-height: 39px;
        position: relative;
        border-bottom: 1px solid #e4e4e4;
        border-top: 1px solid #e4e4e4;
        line-height: 20px;
        padding: 6px 15px 0 15px;
            padding-right: 15px;
        background: #fafafa;
        border-bottom-right-radius: 2px;
        border-bottom-left-radius: 2px;
    }
    .DT-label {
        color: #888;
        line-height: 29px;
        font-size: 13px;
    }
    div.dataTables_paginate > span {
        cursor:pointer;
        position: relative;
        display: inline-block;
        padding: 5px 12px;
        line-height: 1.53846154;
        text-decoration: none;
        background-color: #fff;
        border: 1px solid #ddd;
        border-left-width: 1px;
        border-left-style: solid;
        border-top-color: rgb(221, 221, 221);
        border-right-color: rgb(221, 221, 221);
        border-bottom-color: rgb(221, 221, 221);
        border-left-color: rgb(221, 221, 221);
        -webkit-transition: all .3s cubic-bezier(.175, .885, .32, 1);
        -o-transition: all .3s cubic-bezier(.175, .885, .32, 1);
        transition: all .3s cubic-bezier(.175, .885, .32, 1);
        margin-bottom: 4px;
        font-size: 12px;
        background: #fff;
    }
    .btn, .btn-default, .btn-default:focus, .btn:focus {
        border-color: #e2e2e2;
        border-bottom-color: #c6c6c6;
        background: #f4f4f4;
        background-image: -webkit-linear-gradient(top,#f4f4f4 0,#eaeaea 100%);
        background-image: linear-gradient(to bottom,#f4f4f4 0,#eaeaea 100%);
        background-repeat: repeat-x;
    }
    .btn, .btn-default {
        color: #555;
    }
    div.dataTables_paginate > span.disabled {
        color: #ddd;
        cursor: not-allowed;
        background-color: #fff;
        border-color: #ddd;
    }
    div.dataTables_paginate > span.paginate_of {
        border: 0;
        border-left-width: 0px;
        border-left-style: none;
        border-left-color: currentcolor;
        background: none;
    }
    div.dataTables_paginate > input.paginate_input {
        display: inline-block;
        margin-top: 0;
        margin-bottom: 4px;
        width: 50px;
        font-size: 12px;
    }
    div.dataTables_paginate > input.paginate_input {
        padding-left: 5px;
        padding-right: 5px;
    }
    .form-control {
        height: 30px;
        padding: 5px 10px;
        padding-right: 10px;
        padding-left: 10px;
        font-size: 14px;
        line-height: 1.42857143;
        color: #555;
        background-color: #fff;
        background-image: none;
        border: 1px solid #e1e1e1;
        -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
        box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
        -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
        -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
        transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
    }
    .btn-default:hover, .btn:hover {
        border-bottom-color: #b2b2b2;
        color: #555;
        border-color: #e2e2e2;
        border-bottom-color: #b2b2b2;
        background: #eaeaea !important;
            background-image: none;
        background-image: -webkit-linear-gradient(top,#efefef 0,#e5e5e5 100%) !important;
        background-image: linear-gradient(to bottom,#efefef 0,#e5e5e5 100%) !important;
        background-repeat: repeat-x;
    }
    .btn-default:hover {
        color: #333;
        background-color: #e6e6e6;
        border-color: #adadad;
    }
    .DT-label label{
        font-weight:normal;
    }
    select:hover{
        cursor: pointer;
    }
    .paginate_input:hover{
        cursor: pointer;
    }
    select{
        font-size:12px !important;
        color:#888 !important;
    }
    .holdUpHeight{
        overflow: auto;
    }
</style>
