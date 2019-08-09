<!--
 * @Description: In User Settings Edit
 * @Author: your name
 * @Date: 2019-02-28 16:50:02
 * @LastEditTime: 2019-08-09 11:38:37
 * @LastEditors: Please set LastEditors
 -->
<template>
	<div id="app" :class="getBodyClass">
		<router-view v-if="isRouterAlive"></router-view>
	</div>
</template>

<script>
import {mapGetters} from 'vuex'
  export default{
    name: 'APP',
     provide () {
    return {
      reload: this.reload
    }
  },
  data () {
    return {
      isRouterAlive: true
    }
  },
  methods: {
    reload () {
      this.isRouterAlive = false
      this.$nextTick(function () {
        this.isRouterAlive = true
      })
    }
  },
    computed:{
      ...mapGetters([
        'getBodyClass'
      ])
    }
  }
</script>
