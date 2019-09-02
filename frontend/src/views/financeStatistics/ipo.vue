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
			<!-- <svg-icon icon-class="qrCode" class-name="card-panel-icon" /> -->
			<div v-show="ipoplatetype" @click="wxcodeBig" class="qrCode" style="position: absolute;right: 0;top: 0;" @mouseover="mouseOverQR()"
			 @mouseout="mouseOutQR()">
				<img v-show="!currentQrCodeImg" src="../../assets/images/qrCode.svg" width="42px" class="qrImg"  style="z-index: 10;margin-top: -25px;margin-left: -17px;" />
				<img v-show="currentQrCodeImg" src="../../assets/images/qrCodeBlue.svg" width="42px" class="qrImg"  style="z-index: 10; margin-top: -25px;margin-left: -17px;" />
			</div>
			<div @click="wxcodeBig" @mouseover="mouseOverQR()" @mouseleave="mouseOutQR()" class="miniProCode" style="transform: translate(-50%, -50%);z-index: 20;width: 140px;height: 160px;background: #fff;position: fixed; right: -22px;top: 140px;border-radius: 4px;"
			 v-show="ipoplatetype && mouseOverShow">
				<img :src="wxcodeUrl" style="margin-left: 10px;margin-top: 10px; width: 120px;cursor: pointer;align-items: center;" >
				<div style="font-size: 12px;color: #999999;text-align: center;margin-top: 10px;">手机扫码可视化查看</div>
			</div>
			<div :style="{'padding-left':(headList.labelResult == '' || headList.labelResult == null)?'0px':'97px','width':'1200px','position':'absolute','left': '50%','top':'50%', 'transform': 'translate(-50%,-50%)'}">
				<div class="imgMark" style="position: absolute; z-index: 2;left: 5%;top:50%; transform: translate(-50%,-50%);z-index: 2">
					<!-- 注册制是否是科创版的标签显示 -->
					<div>
						<div v-if="headList.labelResult&&headList.labelResult == '00'">
							<img src="../../assets/images/htg1.png" alt="">
						</div>
						<div v-if="headList.labelResult&&headList.labelResult == '01'">
							<img src="../../assets/images/whtg1.png" alt="">
						</div>
						<div v-if="headList.labelResult&&headList.labelResult == '02'">
							<img src="../../assets/images/zhbj1.png" alt="">
						</div>
						<div v-if="headList.labelResult&&headList.labelResult == '03'">
							<img src="../../assets/images/qxsh1.png" alt="">
						</div>
						<div v-if="headList.labelResult&&headList.labelResult == '04'">
							<img src="../../assets/images/dsh1.png" alt="">
						</div>
						<div v-if="headList.labelResult&&headList.labelResult == '05'">
							<img src="../../assets/images/kctg.png" alt="">
						</div>
						<div v-if="headList.labelResult&&headList.labelResult == '06'">
							<img src="../../assets/images/kcwtg.png" alt="">
						</div>
						<div v-if="headList.labelResult&&headList.labelResult == '07'">
							<img src="../../assets/images/kczcsx.png" alt="">
						</div>
						<div v-if="headList.labelResult&&headList.labelResult == '08'">
							<img src="../../assets/images/kcbyzc.png" alt="">
						</div>
						<div v-if="headList.labelResult&&headList.labelResult == '09'">
							<img src="../../assets/images/dsh1.png" alt="">
						</div>
						<div v-if="headList.labelResult&&headList.labelResult == '10'">
							<img src="../../assets/images/qxsy1.png" alt="">
						</div>
					</div>
				</div>
				<div class="text" style="position:relative;z-index: 4">
					<p class="dialogtitle" style="margin:0px 0px 8px;font-size:22px;color:#ffffff;" @mouseenter="mouseOverSpreadTitle(headList.title)">{{getTitle(headList.title)}}</p>
					<span style="font-size: 14px;opacity: 0.6;">股份公司设立时间：</span>
					<span style="font-size: 14px;opacity: 0.6;">{{headList.establishDate}}</span>&nbsp;&nbsp;&nbsp;&nbsp;
					<span style="font-size: 14px;opacity: 0.6;">&nbsp;&nbsp;&nbsp;&nbsp;辅导历时：</span>
					<span style="font-size: 14px;opacity: 0.6;" v-if="headList.supportDuration">{{headList.supportDuration}}天</span>
					<span style="font-size: 14px;opacity: 0.6;" v-else>- -</span>
					<span style="font-size: 14px;opacity: 0.6;">&nbsp;&nbsp;&nbsp;&nbsp;审核历时：</span>
					<span style="font-size: 14px;opacity: 0.6;" v-if="headList.auditDuration">{{headList.auditDuration}}天</span>
					<span style="font-size: 14px;opacity: 0.6;" v-else>- -</span>
					<span style="font-size: 14px;opacity: 0.6;">&nbsp;&nbsp;&nbsp;&nbsp;最新进程：</span>
					<span style="font-size: 14px;color:#fff;opacity:1">{{headList.processLabel}}</span>
				</div>
				<div class="btn" style="position:relative;z-index:4">
					<span v-if="headList.greenPassage" style=" display: inline-block;margin-right: 5px;margin-top:8px;background: #ff9900; font-size: 12px; color: #fff;border-radius: 2px;padding: 4.5px; position:relative;z-index:10;">{{headList.greenPassage}}
					</span>
					<span v-if="specialArrange&&specialArrange.length>0">
						<span v-for="data in specialArrange" style=" display: inline-block;margin-top:8px;background: #ff9900; font-size: 12px; color: #fff;border-radius: 2px;padding: 4.5px; position:relative;z-index:10;margin-right:10px">{{data}}
						</span>
					</span>
				</div>
				<div style="color:#fff;position: absolute;right: 2%;top: 20%;z-index: 999;font-size: 14px; z-index: 999;" class="collectionsAndNotes" v-show="collectionAndNoteShow">
					<span v-if="favoriteFlag" @click="clickFavorite(true)" style="cursor:pointer;" title="收藏">
						<i class="fa fa-star-o favorite_note_icon"></i><span style="margin-left: 5px">收藏</span>
					</span>
					<span v-else @click="clickFavorite(false)" style="cursor:pointer;" title="取消收藏">
						<i class="fa fa-star favorite_note_icon"></i><span style="margin-left: 5px">已收藏</span>
					</span>
					<span style="padding: 0px 5px;vertical-align: 5%;">|</span>
					<el-popover placement="bottom" title="" width="540" trigger="manual" v-model="titleNoteFlag" popper-class="customer_popper">
						<div class="bigDialog">
							<div style="height: 28px;padding:0px 12px">
								<span style="font-size: 14px;color: #333;">{{noteTitle}}</span>
								<span style="float: right;color: #C1C1C1">
									<i class="fa fa-square-o fa-lg" @click="openCenterNote()" title="放大" style="cursor:pointer;margin-right: 4px;"></i>
									<i class="el-icon-close el-close-size" @click="noteCancellation('1')" title="关闭" style="cursor:pointer;vertical-align: -10%;"></i>
								</span>
							</div>
							<el-input  type="textarea" class="textarea-height" :maxlength='2000' show-word-limit :rows="6" resize="none" placeholder="请在这里输入笔记内容..." v-model="note">
							</el-input>
							<div style="float: right;margin-right: 20px;padding-top: 12px;">
								<button class="small_btn_common cancel_btn" @click="noteCancellation('1')">取消</button>
								<button class="small_btn_common determine_btn" @click="NoteDetermination()" style="margin-left: 10px">保存
								</button>
							</div>
						</div>
						<span slot="reference" aria-hidden="true" @click="titleNoteFlag = !titleNoteFlag" style="cursor:pointer;">
							<i class="fa fa-pencil favorite_note_icon"></i><span style="margin-left: 5px">笔记</span>
						</span>
					</el-popover>
				</div>

			</div>
		</div>

		<!-- 屏幕中间的笔记 -->
		<el-dialog class="bigDialog" title="" :visible.sync="centerNoteFlag" :close-on-click-modal="false" width="80%"
		 close="noteCancellation('3')">
			<span style="font-size: 14px;color: #333;" slot="title">{{centerNoteTitle}}</span>
			<el-input   type="textarea" class="center-textarea-height" resize="none" placeholder="请在这里输入笔记内容..." v-model="note" :maxlength='2000' show-word-limit>
			</el-input>
			<div slot="footer">
				<button class="small_btn_common cancel_btn" @click="noteCancellation('3')">取消</button>
				<button class="small_btn_common determine_btn" @click="NoteDetermination()" style="margin-left: 10px">保存</button>
			</div>
		</el-dialog>

		<el-dialog align="left" :visible.sync="wxcode" width="470px">
			<img :src="wxcodeUrl">
		</el-dialog>
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
												 @click="onTabClick('1', $event)" style="padding-left: 0">公司概览</div>
												<div v-if="headList.isGray=='0'" id="tab-sixth" ref="tab-sixth" aria-controls="pane-sixth" :class="['el-tabs__item is-top', {'is-active': isActive === '6'}]"
												 @click="onTabClick('6', $event)">行业与技术</div>
												<div v-if="headList.isGray=='1'" id="tab-sixth" ref="tab-sixth" class="el-tabs__item1" aria-controls="pane-third"
												 style="cursor:default;color:#adadad">
													<el-tooltip style="color:#666" class="ipoTip" content="提示：当前暂无行业与技术信息" placement="top" effect="light">
														<el-button class="btnClass">行业与技术</el-button>
													</el-tooltip>
												</div>
												<div id="tab-second" ref="tab-second" aria-controls="pane-second" :class="['el-tabs__item is-top', {'is-active': isActive === '2'}]"
												 @click="onTabClick('2', $event)">财务信息</div>
												<div v-if="headList.haveFeedback=='1'&&headList.isTechBoard =='0'" id="tab-third" ref="tab-third" class="el-tabs__item1"
												 aria-controls="pane-third" style="cursor:default;color:#adadad">
													<el-tooltip style="color:#666" class="ipoTip" content="提示：当前暂无反馈意见信息" placement="top" effect="light">
														<el-button class="btnClass">反馈意见</el-button>
													</el-tooltip>
												</div>
												<!-- 1是科创版 -->
												<div v-if="headList.haveFeedback=='1'&&headList.isTechBoard =='1'" id="tab-third" ref="tab-third" class="el-tabs__item1"
												 aria-controls="pane-third" style="cursor:default;color:#adadad">
													<el-tooltip style="color:#666" class="ipoTip" content="提示：当前暂无问询与回复信息" placement="top" effect="light">
														<el-button class="btnClass">问询与回复</el-button>
													</el-tooltip>
												</div>
												<!-- 1是科创版 -->
												<div v-if="headList.haveFeedback=='0'&&headList.isTechBoard =='1'" id="tab-third" ref="tab-third"
												 aria-controls="pane-third" :class="['el-tabs__item is-top', {'is-active': isActive === '3'}]" @click="onTabClick('3', $event)">问询与回复</div>
												<div v-if="headList.haveFeedback=='0'&&headList.isTechBoard =='0'" id="tab-third" ref="tab-third"
												 aria-controls="pane-third" :class="['el-tabs__item is-top', {'is-active': isActive === '3'}]" @click="onTabClick('3', $event)">反馈意见</div>
												<div v-if="headList.haveExamine=='1'" id="tab-fourth" ref="tab-fourth" aria-controls="pane-fourth" class="el-tabs__item1"
												 style="padding-right: 0;cursor:default;color:#adadad">
													<el-tooltip style="color:#666" class="ipoTip" content="提示：当前暂无审核结果及关注问题信息" placement="top" effect="light">
														<el-button class="btnClass">审核结果及关注问题</el-button>
													</el-tooltip>
												</div>
												<div v-if="headList.haveExamine=='0'" id="tab-fourth" ref="tab-fourth" aria-controls="pane-fourth" :class="['el-tabs__item is-top', {'is-active': isActive === '4'}]"
												 @click="onTabClick('4', $event)" style="padding-right: 0">审核结果及关注问题</div>
												<div v-if="headList.havePublic=='1'" id="tab-fifth" ref="tab-fifth" aria-controls="pane-fifth" class="el-tabs__item1"
												 style="padding-right: 0;cursor:default;color:#adadad">
													<el-tooltip style="color:#666" class="ipoTip" content="提示：当前暂无发行概况信息" placement="top" effect="light">
														<el-button class="btnClass">发行概况</el-button>
													</el-tooltip>
												</div>
												<div v-if="headList.havePublic=='0'" id="tab-fifth" ref="tab-fifth" aria-controls="pane-fifth" :class="['el-tabs__item is-top', {'is-active': isActive === '5'}]"
												 @click="onTabClick('5', $event)">发行概况</div>
											</div>
										</div>
									</div>
									<!-- 菜单栏下面的小分类 -->
									<!-- 1 -->
									<div id="title-first" class="title-body" v-show="isActive == '1'">
										<span v-for="(item, index) in tabFirstList">
											<a :id="item.id + 'caseDetails'" href="javascript:void(0)" :class="['title-list',{'item-active': itemActiveFirst === item.id}, {'disabled': item.noClick}]"
											 :title="item.notes" :style="{'font-weight': item.important ? 'bold' : 'normal '}" @click="jump(item.id, 1)">{{item.name}}</a>
											<span style="margin: 0 8px;color: #e4e4e4;" v-if="index < tabFirstList.length - 1">|</span>
										</span>
									</div>
									<!-- 2 -->
									<div id="title-second" class="title-body" v-show="isActive == '2'">
										<span v-for="(item, index) in tabSecondList">
											<a :id="item.id + 'caseDetails'" href="javascript:void(0)" :class="['title-list',{'item-active': itemActiveSecond === item.id}, {'disabled': item.noClick}]"
											 :title="item.notes" :style="{'font-weight': item.important ? 'bold' : 'normal '}" @click="jump(item.id, 2)">{{item.name}}</a>
											<span style="margin: 0 8px;color: #e4e4e4;" v-if="index < tabSecondList.length - 1">|</span>
										</span>
									</div>
									<!-- 3 -->
									<div id="title-third" class="title-body" v-show="isActive == '3'"><br />
									</div>
									<!-- 4 -->
									<div id="title-fourth" class="title-body" v-show="isActive == '4'"><br />
									</div>
									<!-- 5 -->
									<div id="title-fifth" class="title-body" v-show="isActive == '5'">
										<span v-for="(item, index) in tabFifthList">
											<a :id="item.id + 'caseDetails'" href="javascript:void(0)" :class="['title-list',{'item-active': itemActiveFifth === item.id}, {'disabled': item.noClick}]"
											 :title="item.notes" :style="{'font-weight': item.important ? 'bold' : 'normal '}" @click="jump(item.id, 5)">{{item.name}}</a>
											<span style="margin: 0 8px;color: #e4e4e4;" v-if="index < tabFifthList.length - 1">|</span>
										</span>
									</div>
									<!-- 6 -->
									<div id="title-sixth" class="title-body" v-show="isActive == '6'">
										<span v-for="(item, index) in tabSixthList">
											<a :id="item.id + 'caseDetails'" href="javascript:void(0)" :class="['title-list',{'item-active': itemActiveSixth === item.id}, {'disabled': item.noClick}]"
											 :title="item.notes" :style="{'font-weight': item.important ? 'bold' : 'normal '}" @click="jump(item.id, 6)">{{item.name}}</a>
											<span style="margin: 0 8px;color: #e4e4e4;" v-if="index < tabSixthList.length - 1">|</span>
										</span>
									</div>
								</div>
								<div v-if="isFixed" :style="{'color':'#4c4c4c','font-size': '14px','top':'30px','position':'absolute','right':clientRight,'padding-right':'12px'}" class="collectionsAndNotes">
									<span v-if="favoriteFlag" @click="clickFavorite(true)" style="cursor:pointer;" title="收藏">
										<i class="fa fa-star-o favorite_note_icon"></i><span style="margin-left: 5px">收藏</span>
									</span>
									<span v-else @click="clickFavorite(false)" style="cursor:pointer;" title="取消收藏">
										<i class="fa fa-star favorite_note_icon"></i><span style="margin-left: 5px">已收藏</span>
									</span>
									<span style="padding: 0px 5px;vertical-align: 5%;">|</span>
									<el-popover placement="bottom" title="" width="540" trigger="manual" v-model="scrollNoteFlag" popper-class="customer_popper">
										<div class="bigDialog">
											<div style="height: 28px;padding:0px 12px">
												<span style="font-size: 14px;color: #333;">{{noteTitle}}</span>
												<span style="float: right;color: #C1C1C1">
													<i class="fa fa-square-o fa-lg" @click="openCenterNote()" title="放大" style="cursor:pointer;margin-right: 4px;"></i>
													<i class="el-icon-close" @click="noteCancellation('1')" title="关闭" style="cursor:pointer;vertical-align: -10%;font-size: 21px"></i>
												</span>
											</div>
											<el-input type="textarea" class="textarea-height" :rows="6" resize="none" placeholder="请在这里输入笔记内容..." v-model="note">
											</el-input>
											<div style="float: right;margin-right: 20px;padding-top: 12px;">
												<button class="small_btn_common cancel_btn" @click="noteCancellation('1')">取消</button>
												<button class="small_btn_common determine_btn" @click="NoteDetermination()" style="margin-left: 10px">保存
												</button>
											</div>
										</div>
										<span slot="reference" aria-hidden="true" @click="scrollNoteFlag = !scrollNoteFlag" style="cursor:pointer;">
											<i class="fa fa-pencil favorite_note_icon"></i><span style="margin-left: 5px">笔记</span>
										</span>
									</el-popover>
								</div>
							</div>
							<!-- 点击不同菜单展示不同下面内容 -->
							<div class="el-tabs__content">
								<!-- 动态加载tab -->
								<keep-alive>
									<component :is="showComponent" id="componentId" v-on:headCallBack="headCall" :companyProfileList='{companyProfileList:this.companyProfileList,headList:this.headList}'></component>
								</keep-alive>
							</div>
						</div>
					</div>
				</el-col>
				<!-- <el-col :span="6"> -->
				<el-col style="width:295px;padding:0">
					<el-col v-if="proList&&proList.length>0" class="chart" style="position:relative;padding-top: 6px;padding:0px">
						<div class="headClass">
							<el-row>
								<el-col :span="14">
									<span>关联案例</span>
								</el-col>
							</el-row>
						</div>
						<span style="padding: 0px;">
							<relatedCase :proList="proList" @noOpenFlag="handleNoOpenFlag($event)"></relatedCase>
						</span>
					</el-col>
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
							<processTree ref="rightModule" :caseId="caseId"></processTree>
						</span>
					</el-col>
				</el-col>
			</el-row>
		</div>
		<!-- 未开放用户弹窗 -->
		<el-dialog class="noOpen" align="left" :visible.sync="noOpenFlag" width="429px" title="提示" margin-top="31vh">
			<div style="padding:0px 32px 0px 32px;">
				<div class="el-message-box__content" style="margin-top: 0px;">
					<div class="el-message-box__message">
						<div style="color: #555;font-size: 14px;margin-top: 20px;line-height: 17px;">
							<a style="margin-bottom: 10px;display: inline-block;">您当前为试用用户，仅可查看有限案例，若需要查看更多案例，获得更好体验，可升级为正式用户。</a>
							<a style="font-size: 13px">来电咨询：400-880-3388</a>
						</div>
						<div class="el-message-box__btns" style="text-align: center;margin-top: 28px;padding:1px 15px 9px">
							<el-button type="primary" size="small" @click.native="sure" style="margin-left: 0px;">
								在线咨询
							</el-button>
						</div>
					</div>
				</div>
			</div>
		</el-dialog>
	</div>
</template>
<script>
	import {
		getHeadData
	} from "@/api/ipoCase/companyProfile";
	import {
		getCaseDetail
	} from "@/api/ipoCase/companyProfile";
	import {
		getRelatedCaseData
	} from '@/api/ipoCase/companyProfile';
	import {
		clickFavorite
	} from '@/api/ipoCase/companyProfile';
	import {
		NoteDetermination
	} from '@/api/ipoCase/companyProfile';
	// 导入导航栏五个组件
	import companyProfile from "../navMenu/companyProfile/companyProfile.vue";
	import financialInformation from "../navMenu/financialInformation/financialInformation";
	import feedback from "../navMenu/feedback/feedback";
	import result from "../navMenu/result/result";
	import issue from "../navMenu/issue/issue";
	import industryTechnology from "../navMenu/industryTechnology/industryTechnology";
	import processTree from "../navMenu/processTree";
	import relatedCase from "../navMenu/processTree/relatedCase";
	import $ from "jquery";
	import {
		iframeDoMessage
	} from '@/utils/auth'
	export default {
		name: "ipo",
		components: {
			// 导入导航栏五个组件
			companyProfile,
			financialInformation,
			feedback,
			result,
			issue,
			industryTechnology,
			processTree,
			relatedCase
		},
		data() {
			return {
				collectionAndNoteShow : false,
				currentQrCodeImg : false,
				ipoplatetype: false,
				wxcodeimgload: false,
				mouseOverShow: false, //鼠标悬浮展示
				wxcode: false,
				wxcodeUrl: '',
				tenantInfo: '', //日志
				caseId2: this.$store.state.app.caseId,
				// companyId:this.$store.state.app.companyId,
				companyId: '999600',
				proList: [], //关联案例数据
				// 动态加载组件
				companyProfile: companyProfile,
				financialInformation: financialInformation,
				feedback: feedback,
				result: result,
				issue: issue,
				processTree: processTree,
				industryTechnology: industryTechnology,
				showComponent: companyProfile,

				fixBody: "",
				headerFix: false,
				scrollTop: "",
				isFixed: false,
				itemActiveFirst: "",
				itemActiveSecond: "",
				itemActiveThird: "",
				itemActiveFourth: "",
				itemActiveFifth: "",
				itemActiveSixth: "",
				tabFirstList: [{
						id: '1',
						name: '最后一次估值情况',
					},
					{
						id: '2',
						name: '股权结构图',
					},
					{
						id: '3',
						name: '主营业务收入构成'
					},
					{
						id: '4',
						name: '前五名供应商'
					},
					{
						id: '5',
						name: '前五名客户'
					},
					{
						id: '6',
						name: '募集资金运用'
					},
					{
						id: '7',
						name: '中介机构'
					},
				],
				tabSecondList: [{
						id: '1',
						name: '主要财务数据',
					},
					{
						id: '2',
						name: '同行业毛利率对比'
					},
				],
				tabThreeList: [],
				tabFourthList: [],
				tabFifthList: [{
						id: '1',
						name: '发行数据',
					},
					{
						id: '2',
						name: '发行费用'
					},
				],
				tabSixthList: [{
						id: '1',
						name: '主要竞争对手简介'
					},
					{
						id: '2',
						name: '同行业毛利率对比'
					},
					//  {
					//   id:'3',
					//   name:'专利情况'
					// },
					{
						id: '4',
						name: '研发投入'
					},
					{
						id: '5',
						name: '核心技术及研发技术人员'
					},
				],
				isActive: "1",
				tabBarWidth: "",
				tabBarOffset: "",
				statusButtonFlag: "1",
				expandAllflag: false,
				flag: "1",
				activeName: "first",
				titleName: "",
				shortName: "",
				lableName: "",
				caseId: "",
				tenant_info: "",
				access_token: "",
				allTime: "",
				time: "",
				clientLeft: "",
        clientRight : "",
				topHeight: "",
				headList: {
					auditDuration: '',
					establishDate: '',
					greenPassage: '',
					haveExamine: '',
					haveFeedback: '',
					iecResult: '',
					processLabel: '',
					supportDuration: '',
					title: ''
				},
				companyProfileList: {
					actualController: "",
					addrArea: "",
					addrCity: "",
					addrProv: "",
          addrCountry : "",
					companyCode: "",
					companyName: "",
					companyNature: "",
					companyZhName: "",
					controlShareholder: "",
					id: "",
					registeredAssets: "",
					structureLabel: "",
					structureUrl: "",
				},
				specialArrange: '',
				noOpenFlag: false, //判断公司是否开放
				noteSave: '', //保存的笔记内容 为了取消之后还原笔记内容
				note: '', //笔记内容
				titleNoteFlag: false, //标题上的笔记展示标识
				centerNoteFlag: false, //中间的笔记展示标识
				scrollNoteFlag: false, //上面漂浮的展示标识
				noteTitle: '编辑笔记',
				centerNoteTitle: '编辑笔记', //中间笔记title
				favoriteFlag: true, //判断收藏
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
			// 鼠标展示二维码
			mouseOverQR() {
				this.currentQrCodeImg = true;
				this.mouseOverShow = true;
			},
			mouseOutQR() {
				this.currentQrCodeImg = false;
				this.mouseOverShow = false;
			},
			// 未开放公司
			handleNoOpenFlag(data) {
				this.noOpenFlag = data
			},
			sure() {
				let url = "https://vs.rainbowred.com/visitor/pc/chat.html?companyId=488&amp;echatTag=网站咨询";
				window.open(url, "", "toolbar=0,scrollbars=0,location=0,menubar=0,resizable=1,width=854,height=600");
			},
			wxcodeLoad() {
				this.wxcodeimgload = true;
			},
			wxcodeBig() {
				this.wxcode = true;
			},
			// 初始化数据
			initTableData() {
				// console.log('companyId22222222222',this.$store.state.app.companyId)
				// console.log('companyId233333333333',this.companyId)
				// 动态传id
				const param = {
					id: this.caseId2
				}
				// const query = {
				//   id:this.caseId2,
				//   companyId:this.companyId
				// }
				getHeadData(param).then(res => {
					if (res.data.result) {
						this.headList = res.data.result
					}
					if (res.data.result && res.data.result.specialArrange != null) {
						this.specialArrange = res.data.result.specialArrange.split(',');
					}

					if (res.data.result.favoriteId == '' || res.data.result.favoriteId == null) {
						this.favoriteFlag = true
					} else {
						this.favoriteFlag = false
					}

					if (res.data.result.caseNote == '' || res.data.result.caseNote == null) {
						this.note = ''
					} else {
						this.note = res.data.result.caseNote;
						this.noteSave = res.data.result.caseNote;
					}
					this.collectionAndNoteShow = true;

					if(this.isNotEmpty(this.headList.title) == ''){
					  this.noteTitle = '编辑笔记'
					  this.centerNoteTitle = '编辑笔记'
					}else{
					  let title = '编辑笔记-'+ this.getTitle(this.headList.title)
					  if(title.length > 35){
					    this.noteTitle = title.substring(0,35) + '...';
					  }else{
					    this.noteTitle = title;
					  }
					  if(title.length > 45){
					    this.centerNoteTitle = title.substring(0,45) + '...';
					  }else{
					    this.centerNoteTitle = title;
					  }
					}
					this.titleNoteFlag = this.isNotEmpty(this.$route.query.notOpenFlag) ? true : false;

				})

				getCaseDetail(param).then(res => {
					if (res.data.result) {
						this.companyProfileList = res.data.result
						if (res.data.result.ipoPlate == '上交所科创板') {
							this.ipoplatetype = true;
						}
						//this.ipoplatetype = false;
						/*if (res.data.result.launchcompanycode == '999830'){
						  this.ipoplatetype = true;
						}else {
						  this.ipoplatetype = false;
						}*/
					}
				});
				// 获取关联案例数据
				getRelatedCaseData(param).then(res => {
					this.flagLoading = false;
					console.log('关联案例接口数据', res.data.result)
					if (res.data.result) {
						this.proList = res.data.result
					}
				})
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
						case '2':
							// 财务信息
							that.showComponent = financialInformation
							that.$refs.rightModule.treeListMethods(false);
							targetList = document.getElementById('title-second').children;
							let secondFlag = 0;
							for (let i = 0; i < targetList.length; i++) {
								if ((that.itemActiveSecond + 'caseDetails') === targetList[i].children[0].getAttribute('id')) {
									that.$nextTick(() => {
										document.documentElement.scrollTop = document.getElementById(that.itemActiveSecond).offsetTop + document.getElementById(
											'titleHeader').offsetHeight - 50;
									})
									secondFlag = 1;
								}
							}
							if (secondFlag === 0) {
								var scrollhight = document.getElementById('titleHeader').offsetHeight + 56;
								if (document.documentElement.scrollTop > scrollhight) {
									document.documentElement.scrollTop = scrollhight;
								}
							}
							break
						case '3':
							// 反馈意见【没有锚点定位】
							that.showComponent = feedback
							that.$refs.rightModule.treeListMethods(false);
							targetList = document.getElementById('title-third').children;
							let thirdFlag = 0
							if (thirdFlag === 0) {
								var scrollhight = document.getElementById('titleHeader').offsetHeight + 56;
								if (document.documentElement.scrollTop > scrollhight) {
									document.documentElement.scrollTop = scrollhight;
								}
							}
							break
						case '4':
							// 审核结果及关注问题【没有锚点定位】
							that.showComponent = result
							that.$refs.rightModule.treeListMethods(false);
							targetList = document.getElementById('title-fourth').children;
							let fourthFlag = 0
							if (fourthFlag === 0) {
								var scrollhight = document.getElementById('titleHeader').offsetHeight + 56;
								if (document.documentElement.scrollTop > scrollhight) {
									document.documentElement.scrollTop = scrollhight;
								}
							}
							break
						case '5':
							//  发行概况
							that.showComponent = issue
							//  最后tab页形态不一样
							that.$refs.rightModule.treeListMethods(true);
							targetList = document.getElementById('title-fifth').children;
							let fifthFlag = 0;
							// 点击最后tab页，进程树展示不同内容
							for (let i = 0; i < targetList.length; i++) {
								if ((that.itemActiveFifth + 'caseDetails') === targetList[i].children[0].getAttribute('id')) {
									that.$nextTick(() => {
										document.documentElement.scrollTop = document.getElementById(that.itemActiveFifth).offsetTop + document.getElementById(
											'titleHeader').offsetHeight - 50;
									})
									fifthFlag = 1;
								}
							}
							if (fifthFlag === 0) {
								var scrollhight = document.getElementById('titleHeader').offsetHeight + 56;
								if (document.documentElement.scrollTop > scrollhight) {
									document.documentElement.scrollTop = scrollhight;
								}
							}
							break
						case '6':
							// 行业与技术
							that.showComponent = industryTechnology
							that.$refs.rightModule.treeListMethods(false);
							targetList = document.getElementById('title-sixth').children;
							let sixthFlag = 0;
							for (let i = 0; i < targetList.length; i++) {
								if ((that.itemActiveSixth + 'caseDetails') === targetList[i].children[0].getAttribute('id')) {
									that.$nextTick(() => {
										document.documentElement.scrollTop = document.getElementById(that.itemActiveSixth).offsetTop + document.getElementById(
											'titleHeader').offsetHeight - 50;
									})
									sixthFlag = 1;
								}
							}
							if (sixthFlag === 0) {
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
						case "tab-second":
							this.tabSecondList = param;
							break;
						case "tab-fifth":
							this.tabFifthList = param;
							break;
						case "tab-sixth":
							this.tabSixthList = param;
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
					case 2:
						this.itemActiveSecond = param;
						break
					case 5:
						this.itemActiveFifth = param;
						break
					case 6:
						this.itemActiveSixth = param;
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
			//收藏
			clickFavorite(favoriteFlag) {
				let param = {
					caseId: this.caseId2,
					favoriteFlag: this.favoriteFlag
				}
				clickFavorite(param).then(res => {
					// this.companyId = param.data.companyId;
					if (favoriteFlag == true) {
						if (res.data == 1) {
							this.favoriteFlag = false
							this.$message({
								message: '收藏成功',
								duration: 5000,
								iconClass: 'el-icon-success',
								customClass: 'custom-success-message',
								showClose: true
							});
						} else {
							this.$message({
								message: '收藏失败',
								type: 'error',
								duration: 5000,
								showClose: true
							})
						}
					} else {
						if (res.data == 1) {
							this.favoriteFlag = true
							this.$message({
								duration: 5000,
								message: '已取消收藏',
								iconClass: 'el-icon-info',
								customClass: 'custom-info-message',
								showClose: true
							})
						} else {
							this.$message({
								message: '取消收藏失败',
								type: 'error',
								duration: 5000,
								showClose: true
							})
						}
					}

				})
			},
			noteCancellation(type) { //笔记点击取消
				this.note = this.noteSave;
				if (type == '1') {
					this.titleNoteFlag = false;
				} else if (type == '2') {
					this.scrollNoteFlag = false;
				} else if (type == '3') {
					this.centerNoteFlag = false;
				}
			},
			NoteDetermination() { //笔记点击确定
				this.titleNoteFlag = false;
				this.centerNoteFlag = false;
				this.scrollNoteFlag = false;
				let param = {
					caseId: this.caseId2,
					note: this.note,
				};
				// this.$store.dispatch("repCase/getJudgementNoteDetermination", param).then((data) => {
				NoteDetermination(param).then(data => {
					if (data.data.result == 1 || data.data.result == 0) {
						this.$message({
							message: '保存成功',
							duration: 5000,
							iconClass: 'el-icon-success',
							customClass: 'custom-success-message',
							showClose: true
						});
						this.noteSave = this.note;
					} else {
						this.$message({
							message: '保存失败',
							type: 'error'
						})
					}
				})
			},
			openCenterNote() {
				this.titleNoteFlag = false;
				this.centerNoteFlag = true;
				this.scrollNoteFlag = false;
			},
		},
		watch: {
			caseId(n, o) {}
		},
		created() {
			// 获取二维码图片流
			this.wxcodeUrl = "/ipo/ipoInterfaceH5/getQrCode?id=" + this.$store.state.app.caseId + "&access_token=" + this.$store
				.state.app.token
			this.initTableData()
			console.log('companyId', this.$store.state.app.companyId)
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
		border-color: #fff #ffff transparent transparent;
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
