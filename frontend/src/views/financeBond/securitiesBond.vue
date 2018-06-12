
<template>
  <div class='chart-container'>
    <chart-bond-one></chart-bond-one>
    <chart-bond-two></chart-bond-two>
    <chart-bond-three></chart-bond-three>
  </div>
</template>

<script>
import chartBondOne from './chartBondOne'
import chartBondTwo from './chartBondTwo'
import chartBondThree from './chartBondThree'
export default {
  name: 'securitiesBond',
  components: { chartBondOne, chartBondTwo, chartBondThree}

}
</script>
<style scoped>
.chart-container{
  position: relative;
  padding: 10px 20px;
  width: 100%;
  /* height:85vh; */
}
</style>
