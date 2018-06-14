<template>

    <div>
      <!-- <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>{{get_routeTitle}}</span>
        </div> -->
        <transition name="fade" mode="out-in">
          <keep-alive :include="cachedViews">
            <router-view></router-view>
          </keep-alive>
        </transition>
      <!-- </el-card> -->
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'AppMain',
  computed: {
    cachedViews() {
      return this.$store.state.tagsView.cachedViews
    },
    ...mapGetters([
      'get_routeTitle'
    ])
    // key() {
    //   return this.$route.name !== undefined ? this.$route.name + +new Date() : this.$route + +new Date()
    // }
  }
}
</script>
