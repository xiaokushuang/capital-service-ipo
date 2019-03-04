<template>
    <div class="financialInformation">
        <!-- 财务报表数据 -->
        <div class="financialData">
            <div class="title">
                <span class="littleRectangle"></span>
                <span class="titleText" id="reorganizationIntro">财务报表数据</span>
            </div>
        </div>
        <!-- 同行业毛利率对比 -->
        <div class="compare">
            <div class="title">
                <span class="littleRectangle"></span>
                <span class="titleText" id="reorganizationIntro">同行业毛利率对比</span>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name:'financialInformation'
}
</script>
<style scoped lang="scss">
    .title {
    border-bottom: 1px solid;
    border-bottom-color: #ebebeb;
    height: 42px;
    line-height: 42px;
    background-color: #fafafa;
    display: flex;
    align-items: center;
    margin-top: 40px;
    margin-bottom: 16px;
    .littleRectangle {
      width: 3px;
      height: 18px;
      background-color: #999999;
      display: inline-block;
      margin-right: 12px;
      margin-top: 0px;
      .titleText {
        font-size: 18px;
        color: #333333;
      }
    }
    }
</style>


