<template>
  <div>
      <div id="c1"></div>
  </div>
</template>

<script>
// import G2 from '@antv/g2';
// import {DataSet} from '@antv/data-set'
// var _DataSet = DataSet,
//   DataView = _DataSet.DataView;
// const data = [{gender:"男",count:40,"class":"一班",grade:"一年级"},{gender:"女",count:30,"class":"一班",grade:"一年级"},{gender:"男",count:35,"class":"二班",grade:"一年级"},{gender:"女",count:45,"class":"二班",grade:"一年级"},{gender:"男",count:20,"class":"三班",grade:"一年级"},{gender:"女",count:35,"class":"三班",grade:"一年级"},{gender:"男",count:30,"class":"一班",grade:"二年级"},{gender:"女",count:40,"class":"一班",grade:"二年级"},{gender:"男",count:25,"class":"二班",grade:"二年级"},{gender:"女",count:32,"class":"二班",grade:"二年级"},{gender:"男",count:28,"class":"三班",grade:"二年级"},{gender:"女",count:36,"class":"三班",grade:"二年级"}];const scale=[{dataKey:"value",min:0,},{dataKey:"year",min:0,max:1,}];

export default {
    // name:'mutiple',
    // data() {
    //     return {
    //     data,
    //     scale,
    //     height: 400,
    //     };
    // },
    // mounted(){
    //     var chart = new G2.Chart({
    //     container: 'c1',
    //     forceFit: true,
    //     height: window.innerHeight,
    //     padding: [60, 90, 80, 80]
    //     });
    //     chart.source(data);
    //     chart.coord('theta');
    //     chart.tooltip({
    //     showTitle: false
    //     });
    //     chart.facet('tree', {
    //     fields: ['grade', 'class'],
    //     line: {
    //         stroke: '#00a3d7'
    //     },
    //     lineSmooth: true,
    //     eachView: function eachView(view, facet) {
    //         var data = facet.data;
    //         var dv = new DataView();
    //         dv.source(data).transform({
    //         type: 'percent',
    //         field: 'count',
    //         dimension: 'gender',
    //         as: 'percent'
    //         });
    //         view.source(dv, {
    //         percent: {
    //             formatter: function formatter(val) {
    //             return (val * 100).toFixed(2) + '%';
    //             }
    //         }
    //         });
    //         view.intervalStack().position('percent').color('gender');
    //     }
    //     });
    //     chart.render();
    // }
};
</script>