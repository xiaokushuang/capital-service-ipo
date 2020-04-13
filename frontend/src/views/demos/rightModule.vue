<template>
    <div class='rightModuleBox'>
        <div style="height:200px;"></div>
        <el-row>
            <el-col :span="18">
                主体
            </el-col>
            <el-col :span="6">
                <right-module
                    
                ></right-module>
            </el-col>
        </el-row>
    </div>
</template>
<script>
    import rightModule from '@/components/rightModule'
    // console.log(rightModule)
    export default {
        name:'rightModuleDemo',
        components:{
            rightModule
        }
    }
</script>
<style scoped>

</style>
