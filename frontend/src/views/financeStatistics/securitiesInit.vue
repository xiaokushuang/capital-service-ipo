
<template>
  <div class='chart-container'>
    <chart-box-one></chart-box-one>
    <chart-box-two></chart-box-two>
    <chart-box-three></chart-box-three>
  </div>
</template>

<script>
import chartBoxOne from './chartBoxOne'
import chartBoxTwo from './chartBoxTwo'
import chartBoxThree from './chartBoxThree'
import chartAndTable from './chartAndTable'
export default {
  name: 'securitiesInit',
  components: { chartBoxOne, chartBoxTwo,chartAndTable,chartBoxThree }

}
</script>
<style scoped>
.chart-container{
  position: relative;
  padding: 10px 20px;
  width: 100%;
  /* height:85vh; */
}
.el-tabs__header .is-top{
  margin-bottom: 5px!important;
}
</style>
