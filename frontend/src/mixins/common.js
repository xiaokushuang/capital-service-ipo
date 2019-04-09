import {iframeDoMessage} from '@/utils/auth'
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
        },
        /**分页查询 method end */
        getValue(val){
            if (val == null || val == "null" || val == undefined || val == "undefined") {
              val = "";
            }
            return val
        },
        popAlert(msg){//提示弹窗
			iframeDoMessage(window.parent,'popAlert',[msg]);
        },
        //公司详情页
        //chartType：图像类型1：柱状图；2：饼状图；3地图
        //finaType：选择的模块，selCondition：点击选择的条件用于查询
        //condition：索引查询条件；financeIndustry：饼状图下拉列表选择
        //typeFlag：区分tab页
        companyDetailShow(chartType,titleName,finaType,selCondition,condition,financeIndustry,typeFlag) {
            let title = ''
            if(finaType == '001') {
                title = titleName + '-IPO'
            } else if(finaType == '002') {
                title = titleName + '-增发'
            } else if(finaType == '003') {
                title = titleName + '-配股'
            } else if(finaType == '004') {
                title = titleName + '-全部'
            }
            let url = window.location.href;
            url = url.replace(this.$route.path,'/companyDetailPopWin');
            url = url + '&chartType=' + chartType + '&finaType=' + finaType + '&selCondition=' + selCondition + '&typeFlag=' + typeFlag;
            if(chartType == '2') {//饼状图
                url = url + '&condition=' + condition + '&financeIndustry=' + financeIndustry;
            } else if(chartType == '3') {
                url = url + '&condition=' + condition;
            }
			//参数意义：nameSpace：命名空间；action：store中set方法；prompt：提示语
			iframeDoMessage(window.parent,'popWinOut',[title,url,'1100','560']);
        }
    }
}