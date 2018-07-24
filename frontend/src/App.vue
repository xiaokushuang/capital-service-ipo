<template>
	<div id="app" :class="getBodyClass">
		<router-view></router-view>
	</div>
</template>

<script>
import {mapGetters} from 'vuex'
  export default{
    name: 'APP',
    computed:{
      ...mapGetters([
        'getBodyClass'
      ])
    }
  }
</script>
