export default{
    name:'datepicker',
    data(){
        let _this = this;
        return{
            pickerOptions2: {//可以共通，设置开始时间结束时间快捷，可模仿格式自定义
                shortcuts: [{
                    text: '最近一周',
                    onClick(picker) {
                        const start = _this.$moment().subtract('days', 6)._d;
                        const end = _this.$moment()._d;
                        picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近一个月',
                    onClick(picker) {
                        const start = _this.$moment().subtract('months', 1)._d;
                        const end = _this.$moment()._d;
                        picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近三个月',
                    onClick(picker) {
                        const start = _this.$moment().subtract('months', 3)._d;
                        const end = _this.$moment()._d;
                        picker.$emit('pick', [start, end]);
                    }
                }]
                },
        }
    }
}