<template>
    <div class='firstModule'>
        <a style="color:yellow">我是第三个</a>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        
        <!-- <h3 v-html="item.name" class='seo' @dblclick="look(index,null,'fa')"></h3>
        <p class="off">
            {{item.date}} 距离上个进程{{item.interval}}天
        </p>
        <div class="children" v-if="flag[index].fa==1">
            <p class="small">
                <a :href="item.href" v-html="item.title" target="_blank" ></a>
            </p>
            <div class="contentText" v-if="item.textArry.length>0" v-for="(org,idx) in item.textArry" :key="org.id">
                <p class="text" v-if="flag[index].ch[idx]==1">
                    {{org.text}}
                    <span class='text-child' title="查看全部" @click="look(index,idx,'ch')">
                        点击收起<i class="el-icon-more"></i>
                    </span>
                </p>
                <p class="text-lend" v-else>
                    {{org.text}}
                    <span class='text-child' title="查看全部" @click="look(index,idx,'ch')">
                        点击展开<i class="el-icon-more"></i>
                    </span>
                </p>
            </div>
            
            <a @click="moreNotice" class="off href">点击查看更多公告</a>
        </div> -->
    </div>
    
</template>
<script>
    export default {
        name:'firstModule',
        props:{
            boxData:{
                type:Array,
                default:()=>[]
            }
        },
        methods:{
            
        },
        mounted(){
            //console.log(this.boxData)
        },
        computed:{
        },
        watch:{
        }
    }
</script>
<style scoped>
    
</style>

