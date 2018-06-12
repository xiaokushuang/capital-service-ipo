
<template>
  <div class='chart-container'>
      <company-oto></company-oto>
  </div>
</template>

<script>
import companyOto from './companyOto'
export default {
  name: 'companyContrast',
  components: { companyOto }

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
