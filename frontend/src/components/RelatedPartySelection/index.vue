<template>
	<el-dialog title="请选择需要恢复的关联方" :visible.sync="btns" :close-on-click-modal="false" @close="dialogFormVisibleCloseCallBack">
		<el-row>
			<p style="display:inline-block"><span>选择的人员：</span>{{personNames}}</p>
			<el-button style="margin-top:10px;float:right" @click="toggleSelection()" size="small">清空</el-button>
		</el-row>
		<el-row>
			<el-table :data="relatedPartyList" v-loading="selectListLoading" border fit ref="multipleTable"
				highlight-current-row style="width: 100%" element-loading-text="加载中" 
				tooltip-effect="light" @selection-change="handleSelectionChange">
				<el-table-column align="center" width="40" type="selection"></el-table-column>
				<el-table-column align="center" label="姓名" prop="name"></el-table-column>
				<el-table-column align="center" label="身份" prop="relationshipName"></el-table-column>
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
	name: 'RelatedPartySelection',//关联方选择
	props: {//外部入参
		subjectId : null,//主体Id
	},
	created() {//加载前默认调用

	},
	data(){//参数
		return {
			selectListLoading : false,//查询等待
			btns : false,
			multipleSelection : [],
			personNames : null,//展示的选择人员
			personIds : null,//最后返回的人员id集
		}
	},
	methods : {//正常调用方法
		dialogFormVisibleCloseCallBack(){//关闭回掉
			this.$store.commit('SET_RELATED_PARTY_SELECTION_FLAG',false);
		},
		cancelSelect(){//取消
			this.$store.commit('SET_RELATED_PARTY_SELECTION_FLAG',false);
		},
		confirmSelect(){//确定
			let param = {
				personIds : this.personIds,
				personNames : this.personNames
			}
			this.$emit('submitRelatedPartySelection',param);
		},
		getPersonList(){//table查询方法
			this.selectListLoading = true;
			this.$store.dispatch('searchRelatedParty', {subjectId : this.subjectId}).then((data) => {//(方法名，参数)
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
			'relatedPartyList',
			'relatedPartySelectionFlag'
		])
	},
	watch : {//监听
		'btns' : function (val, oldVal){
			if(val){
				this.getPersonList();
			}
		},
		'relatedPartySelectionFlag' : function (val, oldVal) {//通过监听该字段来改变选择器（显示/隐藏）
			this.btns = this.relatedPartySelectionFlag;
		},
		'multipleSelection' : function (val, oldVal) {
			this.personNames = null;//展示的选择人员
			this.personIds = null;//最后返回的人员id集
			let str = '';
			let str2 = '';
			for(let i=0;i<val.length;i++){
				str += val[i]['name'] + '；';
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