<template>
    <div>
        a{{a | add}}
        <p @click="abc($event)">b{{b}}</p>
        <p>{{addvalue}}</p>
        <input v-model="a"></input>
    </div>
</template>
<script>
export default {
    name:'demos',
    data(){
        return {
            a:1,
            b:'demos'
            
        }
    },
    methods:{
        abc(e){
            console.log(e.target)
        }
    },
    computed:{
        addvalue(){
            return this.a+3
        }
    },
    filters:{
        add(val){
            return val+2
        },
    }
}
</script>
<style scoped>

</style>


