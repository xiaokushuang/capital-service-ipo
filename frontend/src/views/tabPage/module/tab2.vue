<template>
	<div>
		<el-input v-model="to"></el-input>
		<p>{{a}}</p>
	</div>
</template>

<script type="text/babel">
//import Bts from './btns'
import {mapGetters} from 'vuex'
  export default {
	name:'menu2',
  	data(){
		return {
			a:1,
			b:'保存'
		}
	},
	computed:{
		to:{
			get () {
				return parseInt(this.a)+5
			},
			set (value) {
				this.a = value;
			}
		},
		...mapGetters([
			'getIpo'
		])
	},
	methods:{
		abc(){
			this.$router.push('/tabpage/list/menu1')
		}
	},
	created(){
		this.$store.dispatch('ipoGet')
		console.log('别来烦我')
		console.log(this.getIpo)
	}
  }
</script>