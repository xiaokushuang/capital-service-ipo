<template>
  	<el-dialog title="选择从属主体" :visible.sync="btns" :close-on-click-modal="false" @close="dialogFormVisibleCloseCallBack">
		<el-row class="mb_15">
			<el-col :span="14" :offset="8" style="float:right;">
				<el-input  placeholder="请输入主体名称" size="small" v-model="searchsubjectName" style="width:80%"></el-input>
				<el-button type="primary" @click="conditionSelect" size="small" icon="el-icon-search">查询</el-button>
			</el-col>
		</el-row>
		<el-row>
			<el-table :data="subjects" v-loading="selectListLoading" element-loading-text="加载中" border fit highlight-current-row style="width: 100%">
				<el-table-column align="center" label="" width="35">
					<template slot-scope="scope">
						<el-radio v-model="radio" :label="scope.row"></el-radio>
					</template>
				</el-table-column>
				<el-table-column align="center" label="主体名称" prop="subjectName"></el-table-column>
				<el-table-column align="center" label="主体身份"  prop="subjectIdentity"></el-table-column>
			</el-table>
		</el-row>
		<el-row style="text-align:center;margin-top: 10px;">
			<el-button size="small" @click="cancelSelect">取消</el-button>
			<el-button size="small" type="primary" @click="confirmSelect">确定</el-button>
		</el-row>
  	</el-dialog>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
	name: 'SubordinateSubjectSelection',//从属主体选择
	props: {
		searchSubjectIdentity : null ,
	},
	created() {//加载前默认调用

	},
	data() {
		return {
		selectListLoading : false,//查询等待
		searchsubjectName : null,
		btns : false,
		radio : {},
		}
	},
	computed:{//获取getters中方法
		...mapGetters([
			'subjects' ,
			'subordinateSubjectSelection',
		])
	},
	methods : {//正常调用方法
		conditionSelect(){//通过参数查询主体
			this.selectListLoading = true;
			let searchParam = {
				searchSubjectIdentity : this.searchSubjectIdentity ,
				searchsubjectName : this.searchsubjectName
			}
			this.$store.dispatch('searchSubjectByParam', searchParam).then((data) => {//(方法名，参数)
				this.selectListLoading = false;
			}).catch(err => { })
		},
		dialogFormVisibleCloseCallBack(){
			this.$store.commit('SET_SUBORDINATE_SUBJECT_SELECTION',false);
			this.searchsubjectName = null;
			this.radio = {};
		},
		cancelSelect(){
			this.$store.commit('SET_SUBORDINATE_SUBJECT_SELECTION',false);
		},
		confirmSelect(){//保存方法
			if(!this.radio.subjectId){//当没有选择的时候提示
				this.$message({
					message: '请选择从属主主体!',
					type: 'error'
				});
				return;
			}
			this.$emit('submitSubjectSelection',this.radio);
		}
	},
	watch : {
		btns (val,oldVal){
			if(val){
				this.conditionSelect();
			}
		},
		subordinateSubjectSelection(o,v){
			this.btns = this.subordinateSubjectSelection;
		}
	},
}
</script>
