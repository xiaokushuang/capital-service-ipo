
<template>
	<div id="app" :class="getBodyClass">
		<router-view ></router-view>
	</div>
</template>

<script>
import {mapGetters} from 'vuex'
  export default{
    name: 'APP',
  methods: {
  },
    computed:{
      ...mapGetters([
        'getBodyClass'
      ])
    }
  }
</script>
