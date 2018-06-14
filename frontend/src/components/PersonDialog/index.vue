<template>
	<el-dialog title="选择人员" :visible.sync="btns" :close-on-click-modal="false" @close="dialogFormVisibleCloseCallBack">
		<el-row>
			<el-col :span="12" :offset="12">
			<el-input size="small" placeholder="请输入姓名" class="ipt_80" v-model="searchParam.personName" clearable></el-input>
			<el-button type="primary" size="small" icon="el-icon-search" @click="conditionSelect">查询</el-button>
			</el-col>
		</el-row>
		<el-row>
			<p style="display:inline-block"><span>选择的人员：</span>{{personNames}}</p>
			<el-button style="margin-top:10px;float:right" @click="toggleSelection()" size="small" icon="el-icon-delete">清空</el-button>
		</el-row>
		<el-row>
			<el-table :data="personList" v-loading="selectListLoading" border fit ref="multipleTable"
				highlight-current-row style="width: 100%" element-loading-text="加载中" 
				tooltip-effect="light" @selection-change="handleSelectionChange">
				<el-table-column align="center" width="40" type="selection"></el-table-column>
				<el-table-column align="center" label="姓名" prop="personName"></el-table-column>
				<el-table-column align="center" label="身份" prop="jobName"></el-table-column>
			</el-table>
		</el-row>
		<el-row style="text-align:center;margin-top: 10px;">
			<el-button @click="cancelSelect" size="small">取消</el-button>
			<el-button type="primary" @click="confirmSelect" size="small">确定</el-button>
		</el-row>
	</el-dialog>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
	name: 'PersonsSelection',//人员选择
	props: {//外部入参
		selectionPersonIds : null,//已选择人员的id【拼逗号字符串】
	},
	created() {//加载前默认调用

	},
	data(){//参数
		return {
			selectListLoading : false,//查询等待
			btns : false,
			searchParam : {//查询参数
				personName : null,
			},
			multipleSelection : [],
			personNames : null,//展示的选择人员
			personIds : null,//最后返回的人员id集
		}
	},
	methods : {//正常调用方法
		dialogFormVisibleCloseCallBack(){//关闭回掉
			this.$store.commit('SET_PERSON_SELECTION_FLAG',false);
			this.searchParam.personName = null;
		},
		conditionSelect(){//查询
			this.getPersonList()
		},
		cancelSelect(){//取消
			this.$store.commit('SET_PERSON_SELECTION_FLAG',false);
		},
		confirmSelect(){//确定
			let param = {
				personIds : this.personIds,
				personNames : this.personNames
			}
			this.$emit('submitPersonSelection',param);
		},
		getPersonList(){//table查询方法
			this.selectListLoading = true;
			this.$store.dispatch('searchPersonListByParam', this.searchParam).then((data) => {//(方法名，参数)
				if(this.selectionPersonIds){//将已选择的人员选中
					let idArr = this.selectionPersonIds.split(",");
					idArr.forEach(id =>{
						this.personList.forEach(person =>{
							if(person['id']==id){
								this.$refs.multipleTable.toggleRowSelection(person);
							}
						})
					})
				}
				this.selectListLoading = false;
			}).catch(err => {})
		},
		handleSelectionChange(val) {//table全选
			this.multipleSelection = val;
		},
		toggleSelection(){
			this.$refs.multipleTable.clearSelection();
		}
	},
	computed:{//获取getters中方法
		...mapGetters([
			'personList',
			'personSelectionFlag'
		])
	},
	watch : {//监听
		'btns' : function (val, oldVal){
			if(val){
				this.getPersonList();
			}
		},
		'personSelectionFlag' : function (val, oldVal) {
			this.btns = this.personSelectionFlag;
		},
		'multipleSelection' : function (val, oldVal) {
			this.personNames = null;//展示的选择人员
			this.personIds = null;//最后返回的人员id集
			let str = '';
			let str2 = '';
			for(let i=0;i<val.length;i++){
				str += val[i]['personName'] + '；';
				str2 += val[i]['id'] + ',';
			}
			if(str){
				str = str.substr(0,str.length-1);
			}
			if(str2){
				str2 = str2.substr(0,str2.length-1);
			}
			this.personNames = str;
			this.personIds = str2;
		},
	}
}
</script>