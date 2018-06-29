
<template>
  <div class='chart-container'>
      <company-otp></company-otp>
  </div>
</template>

<script>
import companyOtp from './companyOtp'
export default {
  name: 'companyContrast',
  components: { companyOtp }

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
