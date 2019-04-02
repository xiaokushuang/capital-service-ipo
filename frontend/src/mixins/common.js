export default{
    name:'common',
    data(){
        return{
            condition_copy:{},
        }
    },
    methods:{
        /**分页查询 method start */
        setPage(){//设置分页为第一页可抽共通，方法加ref参数
			//设置table为第一页
			let limit = this.queryParam.pageSize;
			//设置分页参数
			this.$refs.paper.setPageParam(1,limit);
			this.queryParam.startRow = 1;
        },
        confirmSearch(){
            this.setPage();
            this.condition_copy = Object.assign({},this.queryParam.condition);
            this.pageSearch();
            // this.typeTreeSearch();
        },
        /**分页查询 method end */
        getValue(val){
            if (val == null || val == "null" || val == undefined || val == "undefined") {
              val = "";
            }
            return val
        },
    }
}