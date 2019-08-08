<template>
    <div>
        <div class="el-message-box__content" style="padding:10px;">
            <div class="el-message-box__message">
                    <i class="iconfont" style="position: absolute;color:#0087a7;font-size:40px;">&#xeb72;</i>
                    <p style="padding: 10px 0 0 42px;">{{routeParam.prompt}}</p>
            </div>
        </div>
        <div class="el-message-box__btns" style="text-align: center;padding:17px 0">
            <el-button class="el-button--default el-button--small el-button--primary" @click.native="sure">确定</el-button>
            <el-button class="el-button--default el-button--small" @click.native="noSure">取消</el-button>
        </div>
    </div>
</template>
<script>
import { mapGetters } from 'vuex'
import {iframeDoMessage} from '@/utils/auth'
    export default {
        name:'confirmPopWin',
        data(){
            return {
                routeParam:{
                    nameSpace:'',
                    action:'',
                    prompt:'',
                }
            }
        },
        computed:{
            
        },
        created(){
            this.routeParam = this.$route.query;
        },
        methods:{
            sure(){
                iframeDoMessage(window.parent,'setStage',[this.routeParam.nameSpace, this.routeParam.action, true, 'commit'])
            },
            noSure(){
                iframeDoMessage(window.parent,'setStage',[this.routeParam.nameSpace, this.routeParam.action, false, 'commit'])
            }
        }
    }
</script>
