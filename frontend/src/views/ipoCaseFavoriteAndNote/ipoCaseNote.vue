<template>
	<div class="container">
		<el-row :gutter="24">
			<el-col :span='8'>
				<el-input size='small full' v-model="companycodename" placeholder="公司简称/代码"></el-input>
			</el-col>
			<el-col :span='8'>
				<el-input size='small full' v-model="caseTitle" placeholder="标题关键字（任一关键词以空格隔开）"></el-input>
			</el-col>
			<el-col :span='8'>
				<el-input size='small full' v-model="noteTitle" placeholder="笔记关键字"></el-input>
			</el-col>
		</el-row>
		<el-row :gutter="24">
			<el-col :span='12'>
				<span style="font-size: 12px;color: #666">已添加笔记{{total}}条，默认以</span>
				<span style="font-size: 12px;color: #333">最后编辑日期↓</span>
				<span style="font-size: 12px;color: #666">排序</span>
			</el-col>
			<el-col :span="12" style="text-align: right;">
				<div class="smallbutton">
					<el-button @click="clear" style="height: 30px;">清空条件</el-button>
					<el-button type="primary" @click="search" style="height: 30px;">查询</el-button>
				</div>
			</el-col>
		</el-row>
		<el-row :gutter="24">
			<el-col :span="24" style="padding-left: 26px; padding-right: 26px;padding-bottom: 20px">
				<div class="table">
					<el-table :data="tableData" border style="width: 100%;" :cell-class-name="getCellStyle" @sort-change="sortChange">
						<el-table-column align="center" prop="companyCode" label="公司" min-width="9%">
							<template slot-scope="scope">
								<span @click="openDetail(scope.row.caseId,scope.row.openFlag)">{{scope.row.companyCode}}</span></br>
								<span @click="openDetail(scope.row.caseId,scope.row.openFlag)">{{scope.row.companyName}}</span>
							</template>

						</el-table-column>
						<el-table-column align="center" prop="caseTitle" label="案例标题" min-width="18%">
							<template slot-scope="scope">
								<span style="font-size:14px;float: left;text-align: left;" @click="openDetail(scope.row.caseId,scope.row.openFlag)"
								 class="title_span" title="案例详情">{{scope.row.caseTitle}}</span>
							</template>

						</el-table-column>
						<el-table-column align="center" prop="note" label="笔记" min-width="40%">
							<template slot-scope="scope">
								<span style="line-height:22px;font-size:14px;float: left;text-align: left;color:#333333;cursor: pointer;/*white-space: pre-wrap;word-wrap: break-word;*/"
								 class="title_span" title="编辑笔记" @click="openEditNoteEnt(scope.row.caseId,scope.row.caseTitle)">{{scope.row.note | substr(250)}}</span></br>
							</template>

						</el-table-column>

						<!--						<el-table-column align="center" prop="publishTime" label="首次公告日" min-width="10%" sortable="custom">-->
						<!--							<template slot-scope="scope">-->
						<!--								<span>{{scope.row.publishTime}}</span>-->
						<!--							</template>-->
						<!--						</el-table-column>-->
						<el-table-column align="center" prop="updateTime" label="最后编辑日期" min-width="10%" sortable="custom">
							<template slot-scope="scope">
								<span>{{scope.row.updateTime}}</span>
							</template>
						</el-table-column>

						<el-table-column align="center" prop="operate" label="操作" min-width="8%">
							<template slot-scope="scope">
								<i class="el-icon-edit-outline" style="color: black;cursor: pointer;" title="编辑笔记" @click="openEditNoteEnt(scope.row.caseId,scope.row.caseTitle)"></i>
								<i class="el-icon-delete" style="color: black;cursor: pointer;margin-left: 10px;" title="删除笔记" @click="deleteRow(scope.row.caseId)"></i>
							</template>
						</el-table-column>
					</el-table>
					<papers ref="paper" @searchTable="paperSearch" :total="total" :length1="20"></papers>
				</div>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import {
		mapGetters
	} from "vuex";
	import {
		iframeDoMessage
	} from "@/utils/auth";
	import papers from "@/views/components-demo/papersNew";
	import {
		_getCassNote
	} from '@/api/ipoCase/ipoCaseListApi'
	import {
		NoteDetermination
	} from '@/api/ipoCase/companyProfile';
	export default {
		data() {
			return {
				Token: '',
				baseUrl: '',
				tenantInfo: '',
				signStatus: '',
				echatStr: '', //客服加密参数
				//table数据
				tableData: [],
				total: '',
				caseTitle: '',
				noteTitle: '',
				orderByName: 'updateTime',
				orderByOrder: 'desc',
				companycodename: '',
				startRow: "",
				pageSize: "",

			}
		},
		mounted() {
			this.paperSearch();
		},
		computed: { //获取getters中方法
			...mapGetters('ipoCase', [
				'removeIpoNote',
				'destroyIpoNote', //销毁笔记页面
				'noteIpoEditFlag', 'noteIpoCaseId', 'caseIpoNote' //修改笔记
			])
		},
		methods: {
			getCellStyle({
				row,
				column,
				rowIndex,
				columnIndex
			}) {
				if (row.openFlag == '0' && this.signStatus == '0') {
					return 'not-open'
				}
			},
			openEditNoteEnt(caseId, caseTitle) {
				if (caseTitle.length > 10) {
					caseTitle = caseTitle.substr(0, 12);
				}
				this.centerNoteTitle = '编辑笔记-' + caseTitle;
				let url = window.location.href;
				url = url.replace(this.$route.path, '/editIpoCaseNote');
				url = url + "&caseId=" + caseId + "&centerNoteTitle=" + this.centerNoteTitle +
					"&nameSpace=ipoCase&action=SET_IPO_CASE_NOTE";
				//根据屏幕宽度高度获取编辑笔记页面的高度宽度
				let width = document.documentElement.clientWidth * 0.8;
				let height = (window.screen.height - 140) * 0.8;
				iframeDoMessage(window.parent, 'popWinOutNoTitleIpo', [url, width, height]);
			},
			//table排序
			sortChange(column) {
				if (column.order != null && column.prop != null) {
					this.orderByName = column.prop;
					if (column.order == 'ascending') {
						this.orderByOrder = 'asc';
					} else {
						this.orderByOrder = 'desc';
					}
				} else {
					this.orderByName = "";
					this.orderByOrder = "";
				}
				this.search();
			},
			removeNotes() { //删除笔记
				let param = {
					caseId: this.removeNoteId,
					note: "",
					type: "4",
				};
				// this.$store.dispatch("repCase/getJudgementNoteDetermination", param).then((data) => {
				NoteDetermination(param).then(data => {
					this.popMsg('删除笔记');
					this.removeNoteId = '';
					//分页查询调用
					this.querySearch();
				})
			},
			popMsg(msg) { //消息弹窗
				iframeDoMessage(window.parent, 'popMsg', [msg]);
			},

			paperSearch() {
				var form = this.$refs.paper.submitData;
				this.startRow = form.start - 1;
				this.pageSize = form.pageSize;
				this.querySearch();
			},
			openDetail(caseId, openFlag) {
				debugger;
				if (openFlag == '0' && this.signStatus == '0') {
					this.notOpenCase();
				} else {
					if (caseId) {
						// caseId = caseId.substring(3, caseId.length);
						const _self = this;
						const {
							href
						} = _self.$router.resolve({
							name: 'caseDetail',
							query: {
								caseId: caseId,
								access_token: _self.$route.query.access_token,
								tenant_info: _self.$route.query.tenant_info
							}
						});
						// 日志---------------------头
						let param = {
							recordType: 'open', //跳转页面方式:
							recordTab: "IPO案例详情页" //跳转tab
						}
						this.$store.commit('CREATE_TEMP_MESSAGE', param);
						// 日志---------------------尾
						this.$open(href, '_blank');
					} else {
						let url = window.location.href;
						url = url.replace(this.$route.path, '/ipoPopWin');
						console.log('列表页跳转弹窗', url)
						iframeDoMessage(window.parent, 'popWinOut', ['提示', url, '427', '217']);
					}
				}
			},
			saveNote() { //笔记点击确定
				let param = {
					caseId: this.$store.state.maaCase.noteMaaCaseId,
					note: this.$store.state.maaCase.caseMaaNote,
					type: "4"
				};
				NoteDetermination(param).then(data => {
					if (data.result == 1) {
						this.popMsg('保存成功');
					} else {
						this.popMsg('保存失败');
					}
					this.querySearch();
				})
			},
			querySearch() {
				let param = {
					caseTitle: this.caseTitle,
					noteTitle: this.notetitle,
					companycodename: this.companycodename,
					startRow: this.startRow < 0 ? 0 : this.startRow,
					pageSize: this.pageSize,
					orderByName: this.orderByName,
					orderByOrder: this.orderByOrder,
				};
				_getCassNote(param).then(response => {
					debugger;
					if (response.data) {
						this.tableData = response.data.maaCasesList;
						this.total = response.data.recordsTotal;
						this.signStatus = response.data.signStatus;
						this.echatStr = response.data.echatStr;
					}
				})
				// this.$store.dispatch("getMaaCaseNoteDataList", param).then(() => {
				// 	this.tableData = param.data.tableData;
				// 	this.total = param.data.total;
				// 	this.signStatus = param.data.signStatus;
				// 	this.echatStr = param.data.echatStr;
				// });
			},
			notOpenCase() {
				let url = window.location.href;
				url = url.replace(this.$route.path, '/notOpenCaseInit');
				url = url + "&echatStr=" + this.echatStr;
				iframeDoMessage(window.parent, 'popWinOut', ['系统提示', url, '430', '220']);
			},
			search() {
				this.$refs.paper.submitData.start = 1;
				let form = this.$refs.paper.submitData;
				this.startRow = 0;
				this.pageSize = form.pageSize;
				let param = {
					caseTitle: this.caseTitle,
					noteTitle: this.notetitle,
					companycodename: this.companycodename,
					startRow: this.startRow,
					pageSize: this.pageSize,
					orderByName: this.orderByName,
					orderByOrder: this.orderByOrder,
				};
				_getCassNote(param).then(response => {
					if (response.data) {
						this.tableData = response.data.maaCasesList;
						this.total = response.data.recordsTotal;
						this.signStatus = response.data.signStatus;
						this.echatStr = response.data.echatStr;
					}
				})
				// this.$store.dispatch("getMaaCaseNoteDataList", param).then(() => {
				// 	debugger
				// 	this.tableData = param.data.maaCasesList;
				// 	this.total = param.data.recordsTotal;
				// 	this.signStatus = param.data.signStatus;
				// 	this.echatStr = param.data.echatStr;
				// });
			},
			clear() {
				this.caseTitle = "",
					this.notetitle = "",
					this.companycodename = "",
					this.orderByName = "updateTime",
					this.orderByOrder = "desc",
					this.search()
			},
			deleteRow(id) {
				this.removeNoteId = id;
				let url = window.location.href;
				url = url.replace(this.$route.path, '/confirmPopWin');
				//参数意义：nameSpace：命名空间；action：store中set方法；prompt：提示语
				url = url + "&nameSpace=ipoCase&action=SET_IPO_REMOVE_NOTE&prompt=是否删除该笔记？";
				// data = new Object();
				iframeDoMessage(window.parent, 'popWinOut', ['系统确认', url, '320', '186']);
			},
		},
		components: {
			papers
		},
		filters: {
			substr(paramStr, len) {
				if (paramStr) {
					if (paramStr.length >= len) {
						return paramStr.slice(0, len - 3) + "...";
					} else {
						return paramStr;
					}
				}
				return paramStr;
			},
		},
		watch: {
			"destroyIpoNote"(val, oldVal) {
				if (val) {
					this.$store.commit('ipoCase/SET_IPO_DESTROY_NOTE', false);
					this.$destroy();
				}
			},
			'removeIpoNote'(val, oldVal) { //判断如果val为true时执行清空检索
				debugger
				if (val) {
					//执行清空检索
					this.$store.commit('ipoCase/SET_IPO_REMOVE_NOTE', false);
					this.removeNotes();
				}
			},
			'noteIpoEditFlag'(val, oldVal) { //判断如果val为true时执行清空检索
				if (val) {
					this.$store.commit('ipoCase/SET_IPO_CASE_NOTE', {
						noteIpoEditFlag: false
					});
					this.saveNote();
				}
			},

			caseTitle(n, o) {
				this.caseTitle = n
			},
			notetitle(n, o) {
				this.notetitle = n
			},
			companycodename(n, o) {
				this.companycodename = n
			},
		},
		created() {
			this.Token = this.$route.query.access_token;
			this.baseUrl = this.$route.query.baseUrl;
			this.tenantInfo = this.$route.query.tenant_info;
			let param = {
				client_type: 'pc', //手机或pc
				recordType: 'menu', //跳转页面方式:
				recordModule: 'ipo', //跳转模块
				recordTab: "ipo评论列表", //跳转tab
				recordTabChild: null, //跳转子集tab
				popTitle: null //弹窗title
			}
			this.$store.commit('CREATE_MESSAGE', param)
		},

	}
</script>

<style>
	.container {
		font-family: Microsoft YaHei, "Helvetica Neue", Helvetica, Arial, sans-serif;
		font-size: 14px;
		line-height: 1.42857143;
		color: #333;
		background-color: #fff;
		padding: 20px !important;
		width: calc(100%);
		margin: 0px 10px 0px 0px;
	}

	.container .table .el-table .cell {
		padding: 10px;
	}

	.container .table .el-table th .cell {
		padding: 10px;
		margin: 0;
		border-color: #A0ADB5 !important;
		color: #fff;
		font-size: 14px;
		font-weight: 600;
		font-family: 'Microsoft YaHei';
	}

	.el-textarea__inner {
		color: #999999;
		font-size: 14px;
		line-height: 22px;
		border: none;
		border-radius: 0px;
		border-top: 1px #e2e2e2 solid;
		border-bottom: 1px #e2e2e2 solid;
	}

	.el-dialog__body {
		padding: 0px;
	}

	.el-dialog__footer {
		padding: 10px;
	}

	.el-dialog__header {
		padding: 15px 20px;
	}

	.small_btn_common {
		height: 30px;
		padding-left: 12px;
		padding-right: 12px;
		font-size: 14px;
		cursor: pointer;
		border-radius: 2px;
	}

	.cancel_btn {
		background-color: #fff;
		border: 1px solid #cacaca;
		color: #666666;
	}

	.determine_btn {
		background-color: #14bcf5;
		border: 1px solid #14bcf5;
		color: #fff;
	}

	.not-open {
		background: #F9FAFE;
	}

	.not-open .cell {
		color: #999999 !important;
	}

	textarea {
		word-break: break-all;
	}
</style>
