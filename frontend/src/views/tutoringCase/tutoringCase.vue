<!--
 * @Description: In User Settings Edit
 * @Author: your name
 * @Date: 2019-04-25 16:51:54
 * @LastEditTime: 2019-08-09 11:00:12
 * @LastEditors: Please set LastEditors
 -->
<template>
	<div style="overflow-x:hidden">
		<!-- 易董头条 -->
		<div style="width: 100%;height: 46px;background: #343b4a;line-height: 46px;">
			<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB8AAAAaCAYAAABPY4eKAAABS2lUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4KPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNS42LWMxNDIgNzkuMTYwOTI0LCAyMDE3LzA3LzEzLTAxOjA2OjM5ICAgICAgICAiPgogPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4KICA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIi8+CiA8L3JkZjpSREY+CjwveDp4bXBtZXRhPgo8P3hwYWNrZXQgZW5kPSJyIj8+nhxg7wAAAexJREFUSInFlj1rVFEQhp9JYiSia0RELURBEMTKiKJFRNBCLGxErf0DQgptxEJMsBBU8gsUUlgZBBsLxUJtouBXodGAxMJCQkIQiZHNY3EneBGMe7PL5m2GOWfeeeZyOYcT6nngCHAUqFHoJfAdmMm8BqwGeoENwDfgPfAcGI2ISZqRul2ds9DGJepWqX3qBXVcXVDvqTubHeBVwnsbrO9Wr6VnVj3eDPxJFXjJdyl9P9R97YaH+ji979TOtsHTu98/OtWIp6PyhP9QRIwBbzI93VZ46mHGAysBf5txy0rAv2bsUf/bu9VwM85HxEK74Ys345dGilsN78v4YiXgxzLer+xs8pLpS++k2t2IpyVfrgZwI9OBiJhfTpPl3u2D6bteGVpq8jSbbG6wvku9pdbVy1V5XX/lWzNeVGeATmAa+ACMRcRUQjuAk8AgMAccjohnVeGRzdYBV4CBXJ8GRoAJYA2wg+IY9eZeDRgDbkfEo6rQRXWpN4FNwFrgTmmvBuwFFoBZiuPzGdgFnAW6gboaESHtktqjDqnz6oR6Vd3TtgFyiN3qg9Ij4pM6rJ5o5MREi4boB4aA/vIyxfP6NTAOTFI8xesUv3RbK9jlIQ6qd9WfLq26eqal8NIQ69Vz6oj6Uf2V0Cl1VD0E8BuLL6cTC+UXbQAAAABJRU5ErkJggg=="
			 style="height: 20px;padding-left: 16px;vertical-align: middle;">
			<p style="display:inline-block;margin:0px;">
				<span style="color:#ffffff;margin-left:8px;font-size: 14px;opacity:0.8">易董</span><span style="font-size:16px;color:#ffffff;opacity:0.18;margin-left: 16px">|</span><span
				 style="color:#ffffff;opacity:0.8;font-size: 14px;margin-left: 16px">IPO详情</span>
			</p>
		</div>
		<!-- IPO标题头部 -->
		<div ref="titleHeader" id="titleHeader" style=" width: 100%;height: 140px;position: relative">
			<div style="width:1200px;position:absolute;left:50%;top:50%;transform:translate(-50%,-50%)">
				<div class="text" style="position:relative;z-index: 4">
					<p class="dialogtitle" style="margin:0px 0px 8px;font-size:22px;color:#ffffff;" @mouseenter="mouseOverSpreadTitle(companyProfileList.companyName)">{{getTitle(companyProfileList.companyName)}}</p>
					<span style="font-size: 14px;opacity: 0.6;">股份公司设立时间：</span>
					<span style="font-size: 14px;opacity: 0.6;">{{companyProfileList.establishTime}}</span>&nbsp;&nbsp;&nbsp;&nbsp;
					<span style="font-size: 14px;opacity: 0.6;">&nbsp;&nbsp;&nbsp;&nbsp;辅导工作历时：</span>
					<span style="font-size: 14px;opacity: 0.6;" v-if="companyProfileList.establishTime">{{companyProfileList.establishTime}}天</span>
					<span style="font-size: 14px;opacity: 0.6;" v-else>- -</span>
					<span style="font-size: 14px;opacity: 0.6;">&nbsp;&nbsp;&nbsp;&nbsp;最新公告日期：</span>
					<span style="font-size: 14px;color:#fff;opacity:1" v-if="companyProfileList.establishTime">{{companyProfileList.establishTime}}</span>
          <span style="font-size: 14px;opacity: 0.6;" v-else>- -</span>
				</div>
			</div>
		</div>
		<div id="concentBody" style="width:1200px;margin: 0 auto;" class="ipoContainer">
			<el-row :gutter="24" class="h100">
				<el-col :span="18" class="chart" style="padding:0 40px 0 0">
					<div class='chart-container'>
						<div class="el-tabs el-tabs--top" style="margin: 0 0 15px;width:100%">
							<div id="headerTop" :class="['is-top title-fix', {'is-fixed': isFixed}]" :style="isFixed ? 'position: fixed; left: 0px; top: 0px;' : ''">
								<!-- 吸顶效果 -->
								<div v-if="isFixed" :style="{ 'width': '70px','height': '40px', 'margin-top': 'auto','margin-right': '40px', 'margin-bottom': 'auto', 'margin-left': clientLeft}">
									<span style="font-size: 20px;color: #333;display: block;margin-bottom: 10px;">IPO</span>
									<span v-show="this.companyProfileList&&this.companyProfileList.companyName" style="font-size: 14px;color: #333;">{{this.companyProfileList.companyName}}</span>
								</div>
								<div :style="{width:isFixed?'740px':'100%'}">
									<!-- 菜单导航栏 -->
									<div class="el-tabs__nav-wrap is-top">
										<div class="el-tabs__nav-scroll">
											<div class="el-tabs__nav">
												<div class="el-tabs__active-bar is-top" :style="{width: tabBarWidth + 'px', transform: 'translateX(' + tabBarOffset + 'px)'}"></div>
												  <div id="tab-first" ref="tab-first" aria-controls="pane-first" :class="['el-tabs__item is-top', {'is-active': isActive === '1'}]"
												    @click="onTabClick('1', $event)" style="padding-left: 0">
                            公司概览
                          </div>
											  </div>
										</div>
									</div>
									<!-- 菜单栏下面的小分类 -->
									<!-- 1 -->
									<div id="title-first" class="title-body" v-show="isActive == '1'" style="position: relative;top: -40px;left: 70px;">
										<span v-for="(item, index) in tabFirstList">
                      <span style="margin: 0 8px;color: #e4e4e4;" v-if="index == tabFirstList.length - 1">|</span>
											<a :id="item.id + 'caseDetails'" href="javascript:void(0)" :class="['title-list',{'item-active': itemActiveFirst === item.id}, {'disabled': item.noClick}]"
											 :title="item.notes" :style="{'font-weight': item.important ? 'bold' : 'normal '}" @click="jump(item.id, 1)">{{item.name}}</a>
											<!--<span style="margin: 0 8px;color: #e4e4e4;" v-if="index < tabFirstList.length - 1">|</span>-->
										</span>
									</div>
								</div>
							</div>
							<!-- 点击不同菜单展示不同下面内容 -->
							<div class="el-tabs__content">
								<!-- 动态加载tab -->
								<keep-alive>
									<component :is="showComponent" id="componentId" v-on:headCallBack="headCall" :companyProfileList='{companyProfileList:this.companyProfileList}'></component>
								</keep-alive>
							</div>
						</div>
					</div>
				</el-col>
				<el-col style="width:295px;padding:0">
					<el-col class="chart" style="position:relative;padding-top: 6px;padding: 0px;">
						<div class="headClass">
							<el-row>
								<el-col :span="14">
									<span>IPO进程</span>
								</el-col>
								<span v-if="statusButtonFlag == '1'">
									<el-col :span="5" style="text-align: right;">
										<span class="mmpClass" v-if="expandAllflag" @click="expandAll(false)">收起全部</span>
										<span class="mmpClass" v-else @click="expandAll(true)">展开全部</span>
									</el-col>
									<el-col :span="1">
										<span style="text-align: center;color: #14bcf5;padding-left: 4px;">|</span>
									</el-col>
									<el-col :span="4">
										<span class="mmpClass" v-if="flag =='1'" @click="sortTime('02')">排序 ↓</span>
										<span class="mmpClass" v-else @click="sortTime('01')">排序 ↑</span>
									</el-col>
								</span>
							</el-row>
						</div>
						<span style="padding: 0px;">
							<processTree ref="rightModule" :caseId="caseId2"></processTree>
						</span>
					</el-col>
				</el-col>
			</el-row>
		</div>
	</div>
</template>
<script>
  import { getCompanyInfo } from "@/api/tutoringCase/companyProfile";
	// 导入导航栏五个组件
	import companyProfile from "./companyProfile/companyProfile.vue";
	import processTree from "./processTree";
	import $ from "jquery";
	import {
		iframeDoMessage
	} from '@/utils/auth'
	export default {
		name: "tutoringCase",
		components: {
			// 导入导航栏五个组件
			companyProfile,
      processTree,
		},
		data() {
			return {
        caseId2: this.$store.state.app.caseId,
				// 动态加载组件
				showComponent: companyProfile,
				companyProfile: companyProfile,
				processTree: processTree,
				fixBody: "",
				headerFix: false,
				scrollTop: "",
				isFixed: false,
				itemActiveFirst: "",
				tabFirstList: [{
						id: '1',
						name: '辅导机构',
					},
				],

				isActive: "1",
				tabBarWidth: "",
				tabBarOffset: "",
				statusButtonFlag: "1",
				expandAllflag: false,
				flag: "1",
				activeName: "first",
				time: "",
				clientLeft: "",
        clientRight : "",
				companyProfileList: {
          address: '',
          allTime: '',
          businessArea: '',
          businessCity: '',
          businessProvience: '',
          businessScope: '',
          companyName: '',
          companyShortName: '',
          corporateRepresentative: '',
          createTime: '',
          createUser: '',
          establishTime: '',
          fdKeyword: '',
          fdProcess: '',
          foundingTime: '',
          id: '',
          ifRelevance: '',
          intermediaryOrgList: '',
          lastDate: '',
          mainBusiness: '',
          openFlag: '',
          otherMarketInfoList: '',
          phone: '',
          plate: '',
          registerArea: '',
          registerCity: '',
          registerProvience: '',
          securitiesRegulatory: '',
          serialNum: '',
          title: '',
          treeList: '',
          updateTime: '',
          updateUser: '',
				},
			};
		},
		created() {
			// 日志--------------------功能头
			let param = {
				client_type: 'pc', //手机或pc
				recordType: 'menu', //跳转页面方式:
				recordModule: 'IPO案例', //跳转模块
				recordTab: "ipo案例详情页", //跳转tab
				recordTabChild: null, //跳转子集tab
				popTitle: null //弹窗title
			}
			// this.$store.commit('CREATE_MESSAGE',param)
			// 日志------------------功能尾
		},
		mounted() {

		},
		methods: {
			// 初始化数据
			initTableData() {
				// 动态传id
				const param = {
					id: this.caseId2
				}
        getCompanyInfo(param).then(res=>{
          if (res.data.result) {
              this.companyProfileList = res.data.result
               console.log('公司概览',this.companyProfileList)
              if (res.data.result.ipoPlate == '上交所科创板') {
                this.ipoplatetype = true;
              }
          }
        });
			},
      isNotEmpty(param) {
      	return param != null && param !== undefined && param !== '' && param !== 'null' && param !== 'undefined'
      },
			// 展开全部
			expandAll(flag) {
				this.expandAllflag = !this.expandAllflag
				this.$refs.rightModule.expandAlltoC(flag);
			},
			// 排序
			sortTime(sortType) {
				if (sortType == '02') {
					this.flag = "0";
					sortType = '01';
				} else {
					this.flag = "1";
					sortType = '02';
				}
				this.$refs.rightModule.orderByProcess(sortType)
			},
			// 点击tab导航栏
			onTabClick(isActive, event) {
				let that = this
				// tabs标签页焦点
				this.isActive = isActive;
				// 控制tabs标签的下边边框长度和位置
				if (isActive === '1' || isActive === '4') {
					this.tabBarWidth = event.currentTarget.offsetWidth - 20;
				} else {
					this.tabBarWidth = event.currentTarget.offsetWidth - 40;
				}
				if (isActive != '1') {
					this.tabBarOffset = event.currentTarget.offsetLeft + 20;
				} else {
					this.tabBarOffset = event.currentTarget.offsetLeft;
				}
				// 切换tab页，锚点定位
				let targetList
				setTimeout(function() {
					switch (isActive) {
						case '1':
							// 公司概览
							that.showComponent = companyProfile
							that.$refs.rightModule.treeListMethods(false);
							targetList = document.getElementById('title-first').children;

							let firstFlag = 0;
							for (let i = 0; i < targetList.length; i++) {
								if ((that.itemActiveFirst + 'caseDetails') === targetList[i].children[0].getAttribute('id')) {
									that.$nextTick(() => {
										document.documentElement.scrollTop = document.getElementById(that.itemActiveFirst).offsetTop + document.getElementById(
											'titleHeader').offsetHeight - 50;
									})
									firstFlag = 1;
								}
							}
							if (firstFlag === 0) {
								var scrollhight = document.getElementById('titleHeader').offsetHeight + 56;
								if (document.documentElement.scrollTop > scrollhight) {
									document.documentElement.scrollTop = scrollhight;
								}
							}
							break
						default:
							break
					}
				}, 0);

			},
			// 滑轮滚动，顶部固定定位
			handleScroll() {
				let scrollTop = window.parent.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
				let offsetTop = document.querySelector("#titleHeader").offsetHeight + 10;
				let fixBody = document.querySelector("#headerTop").offsetHeight + 10;
				let clientLeft = window.getComputedStyle(document.querySelector("#concentBody"), null)["margin-left"];
        let clientRight = window.getComputedStyle(document.querySelector("#concentBody"), null)["margin-right"];
				this.scrollTop = scrollTop;
				if (scrollTop > offsetTop) {
					this.isFixed = true;
					this.headerFix = true;
					this.fixBody = fixBody;
					this.clientLeft = clientLeft;
          this.clientRight = clientRight;
				} else {
					this.isFixed = false;
					this.headerFix = false;
				}
			  if(this.isFixed){
				if(this.titleNoteFlag){
					setTimeout(() => {
					this.scrollNoteFlag = true;
					}, 100);
					this.titleNoteFlag = false;
				}
			  }else{
					if(this.scrollNoteFlag){
						this.titleNoteFlag = true;
						this.scrollNoteFlag = false;
					}
				}

			},
			// 用于锚点定位【从子组件里传过来的值】[子传父]
			headCall(param) {
				if (param != null && param != undefined && param.length > 0) {
					switch (param[0].tabId) {
						case "tab-first":
							this.tabFirstList = param;
							break;

						default:
							break;
					}
				}
			},
			// 点击锚点跳转
			jump(param, num) {
				document.documentElement.scrollTop = document.getElementById(param).offsetTop + document.getElementById(
					'titleHeader').offsetHeight - 25;
				switch (num) {
					case 1:
						this.itemActiveFirst = param;
						break
					default:
						break
				}
			},
			// 鼠标移入大标题展示全部内容
			mouseOverSpreadTitle(title) {
				if (title.length > 73) {
					$(".dialogtitle").attr("title", title)
				} else {
					$(".dialogtitle").removeAttr("title", title)
				}
			},
			// 标题最多只展示2行字
			getTitle(title) {
				if (title.length > 73) {
					return title.substring(0, 72) + '...'
				} else {
					return title
				}
			},
		},
		watch: {
		},
		created() {
      this.initTableData()
		},
		mounted() {
			window.addEventListener("scroll", this.handleScroll);
		}
	};
</script>

<style scoped lang="scss">
	.l {
		float: left;
	}

	.r {
		float: right;
	}

	.clear:after {
		display: block;
		content: "";
		clear: both;
	}

	// footer样式
	.commonFooter {
		width: 100%;
		height: 40px;
		background: inherit;
		background-color: rgba(55, 61, 65, 1);
		padding-left: 20px;

		span {
			margin-left: 10px;
			font-weight: 500;
			font-style: normal;
			font-size: 12px;
			color: rgba(153, 153, 153, 0.647058823529412);
			line-height: 40px;
		}
	}

	// 头部背景图
	#titleHeader {
		background-image: url("../../assets/images/IpoHeader.png"), url("../../assets/images/IpoHeaderBj.png");
		background-repeat: no-repeat, no-repeat;
    background-size:100% 100%;
		background-position: center, 0 0;
		color: #fff;
	}

	/* 头部文字标题 */
	.bottomContent {
		color: #fff;

		.text {
			margin-bottom: 8px;
			margin-left: 7px;
			position: relative;
			z-index: 10;

			span {
				font-size: 14px;
				opacity: 0.6;
				position: relative;
				z-index: 10;

			}
		}

		.btn {
			display: inline-block;
			background: #ff9900;
			font-size: 12px;
			color: #fff;
			border-radius: 2px;
			padding: 4.5px;
			margin-left: 7px;
			position: relative;
			z-index: 10;
		}
	}

	.chart-container {
		position: relative;
		padding: 10px 0px 0px;
		width: 100%;
	}

	.dialogtitle {
		font-family: 'MicrosoftYaHei', 'Microsoft YaHei';
		font-weight: 400;
		font-style: normal;
		font-size: 20px;
		width: 75%;
		color: #333;
		padding-right: 12px;
	}

	.chart-container .el-tabs__item.is-active {
		color: #0086a7;
	}

	.chart-container .el-tabs__item:hover {
		color: #0086a7;
		cursor: pointer;
	}

	.chart-container .el-tabs__item:hover {
		color: #0086a7;
		cursor: pointer;
	}

	.chart-container .el-tabs__active-bar {
		background-color: #0086a7;
	}

	.chart-container .el-tabs__active-bar {
		color: #0086a7;
	}

	.chart-container .el-tabs__header {
		padding-right: 12px;
	}

	.el-tabs__active-bar {
		width: 56px;
		transform: translateX(0px);
	}

	.h100 {
		margin-left: 0px !important;
		margin-right: 0px !important;
	}

	.headClass {
		margin-top: 22px;
		color: #333;
		font-size: 16px;
		text-rendering: optimizeLegibility;
		border-bottom: 2px solid #e4e7ed;
		padding-bottom: 8px;
	}

	.mmpClass {
		font-size: 12px;
		color: #1990fe;
	}

	.mmpClass:hover {
		cursor: pointer;
		text-decoration: underline;
		text-decoration-color: #1990fe;
	}

	.el-tabs__item {
		color: #333;
	}

	.el-tabs__item1 {
		padding: 0 20px;
		height: 40px;
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
		line-height: 40px;
		display: inline-block;
		list-style: none;
		font-size: 14px;
		font-weight: 500;
		position: relative;
	}

	.el-tabs__active-bar {
		background-color: #14bcf5 !important;
	}

	.el-tabs__item:hover {
		color: #14bcf5 !important;
	}

	.el-tabs__item.is-active {
		color: #14bcf5 !important;
	}

	.el-tabs__item1:hover {
		color: #adadad !important;
	}

	.el-tabs__item1.is-active {
		color: #adadad !important;
	}

	.title-body {
		margin: 12px 0 0;
	}

	.title-list {
		font-size: 12px;
		color: #666;
	}

	.title-list:hover {
		color: #14bcf5;
	}

	.item-active {
		color: #14bcf5 !important;
	}

	.is-fixed {
		position: fixed;
		left: 0px;
		top: 0px;
		z-index: 999;
		box-shadow: 0 1px 4px 0 rgba(0, 0, 0, 0.12);
		background-color: #fff;
		width: 100%;
		padding: 0 0 10px;
		transform: translateZ(0);
		-webkit-transform: translateZ(0);
	}

	.disabled {
		color: #c1c1c1 !important;
		cursor: default !important;
		pointer-events: none;
	}

	.title-fix {
		display: flex;
	}

	#title-fourth {
		display: none
	}

	#title-third {
		display: none
	}

	.btnClass {
		padding-left: 0;
		padding-right: 0;
		background: white;
		border: none;
		cursor: default;
		color: #adadad !important;
		font-size: 14px;
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

	textarea {
		word-break: break-all;
	}

	.textarea-height textarea {
		min-height: 144px !important;
	}

	.center-textarea-height {
		height: 64vh;
	}

	.center-textarea-height textarea {
		height: calc(100%) !important;
	}

	.el-dialog {
		transform: none;
		left: 0;
		position: relative;
		margin: 0 auto;
		width: 60%;
		border-radius: 2px;
		box-shadow: 0 1px 3px rgba(0, 0, 0, .3);
		box-sizing: border-box;
		height: calc(80%);
	}

	.el-dialog__headerbtn {
		top: auto;
	}

	.el-textarea__inner {
		color: #999999;
		font-size: 14px;
		line-height: 22px;
		border: none;
		border-radius: 0px;
		border-top: 1px #e2e2e2 solid;
		border-bottom: 1px #e2e2e2 solid;
		word-break: break-all;
	}

	.el-textarea__inner:focus {
		border-color: #e2e2e2;
	}

	.el-dialog__body {
		padding: 0px;
		color: #555;
		line-height: 24px;
		font-size: 14px;
		height: calc(80%);
	}

	.el-dialog__footer {
		padding: 10px;
	}

	.el-dialog__header {
		padding: 12px 21px 11px;
	}

	// .el-message.is-closable .el-message__content {
	// 	background-color: #ecf2ff;
	// 	border-color: #ecf2ff;
	// 	color: #4680ff;
	// 	// padding: 10px 15px;
	// 	font-size: 14px;
	// }

	// .el-message .el-icon-success {
	// 	color: #4680ff;
	// 	margin-right: 10px;
	// 	margin-left: 1px;
	// }

	// .custom-info-message {
	// 	background-color: #f2f2f2;
	// 	border-color: #f2f2f2;
	// 	color: #999999;
	// 	padding: 10px 15px;
	// 	font-size: 14px;
	// }

	// .custom-info-message .el-icon-info {
	// 	color: #999999;
	// 	margin-right: 10px;
	// 	margin-left: 1px;
	// }

	.qrCode {
		width: 0;
		height: 0;
		border: 29px solid;
		border-color: #fff #fff transparent transparent;
	}
	.miniProCode {
		// border: #F1F1F1 0.5px solid;
		-webkit-box-shadow: 0 0 6px  rgba(0,0,0,0.08);
    -moz-box-shadow:  0 0 6px 0 rgba(0,0,0,0.08);
    box-shadow:  0 0 6px 0 rgba(0,0,0,0.08);
	}
  .el-close-size{
    font-size: 21px !important;
   }
</style>
