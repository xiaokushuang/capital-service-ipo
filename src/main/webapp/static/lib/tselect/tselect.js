/*
 * jQuery tselect plugin 1.0
 *
 * Copyright (c) 2017 NiTaoge
 *
 * For ValueOnline on 2017-3-10.
 * 
 * jQuery tselect plugin 2.0
 * 
 * search data from tselect
 * 
 * Modify Author: Wang Guili & Gerry 
 * 
 * Modify Date: 2017-12-05
 * 
 */
//$(document).ready(function() {
//	// 点击其他地方收起下拉菜单
//
//	
//	$(document).click(function(e) {
//		//clickOtherEvent($(e.target))
//	});
//});
//拦截器
var nowTime = 0;
//下拉框失去焦点事件
function blurUp(obj){
	if(nowTime==0){
		nowTime++;
		if(typeof $(obj).attr('id')!='undefined'){
			var select = $(obj).parent();
		}else{
			var select = $(obj).parents('.t-select-content');
		}
		var result = [];
		setTimeout(function(){
			result.push(child(select))
			if(result[0]!=1){
				$('.t-select').removeClass('active');
				$('.t-select-table').slideUp('fast');
				$.tSelectBlur(select.find('.t-select'));
			}
		},50)
	}else{
		return
	}
	setTimeout(function(){
		nowTime=0;
	},50)
	
}
//递归下拉框元素
function child(select){
	var end = 0;
	if(select.children().length!=0){
		for(var i = 0;i<select.children().length;i++){
			if($(select.children()[i]).is(':focus')){
				end = 1;
				return end;
			}
		}
		if(end==0){
			return child(select.children())?1:0
		}
	}else{
		return select.is(':focus')?1:0
	}
}
$(document).click(function(e){
	var t = $(e.target);
	// 点击其他地方隐藏分享栏(暂时放这，有时间封装控件)
	if (t.parents('.shareToolBar').length > 0) {
		$('.lawShare').each(function(idx, obj) {
			if ($(obj) != t && $(obj).parent().find(t).length == 0) {
				$(obj).hide('fast');
			}
		});
	} else {
		$('.lawShare').hide('fast');
	}
	$('.lawShare').each(function(idx, obj) {
		var flag = true;
		$(obj).find("img").each(function(idx, obj){
			if($(obj)[0] == t[0]){
				flag = false;
			}
		})
		if(flag){
			if($(obj).find(".erweima_share").attr("style") != 'display:none'){
				$(obj).find(".erweima_share").remove();
				var str = '<div style="display:none" class="erweima_share" id="share"></div>';
				$(str).appendTo($(obj));
			}
			if($(obj).find(".erweima_share1").attr("style") != 'display:none'){
				$(obj).find(".erweima_share1").remove();
				var str = '<div style="display:none" class="erweima_share1" id="share"></div>';
				$(str).appendTo($(obj));
			}
		}
	})
	if (!t.parents('.Spandl').length > 0 && !(t.hasClass("wxShareAll")||t.hasClass("sharingdl"))) {
		$('.Spandl').remove();
	}
	
});
//function clickOtherEvent(t) {
//	if (t.parents('.t-select-content').length > 0) {
//		$('.t-select-table').each(function(idx, obj) {
//			if ($(obj) != t && $(obj).parent().find(t).length == 0) {
//				$(obj).slideUp('fast');
//			}
//		});
//	} else if (t.parents('.layui-layer').length > 0 || t.hasClass('layui-layer-shade') || t.parents('.layui-layer-setwin').length > 0 || t.parents('.layui-layer-btn').length > 0) {
//		// 弹出确认框遮罩是否执行下拉菜单收起
//		$('.t-select-table').slideUp('fast');
//	} else {
//		if ($('.t-select-table:visible').length > 0) {
//			$.tSelectBlur($('.t-select-table:visible').parent().find('.t-select'));
//		}
//		$('.t-select').removeClass('active');
//		$('.t-select-table').slideUp('fast');
//	}
//
	// 点击其他地方隐藏分享栏(暂时放这，有时间封装控件)
//	if (t.parents('.shareToolBar').length > 0) {
//		$('.lawShare').each(function(idx, obj) {
//			if ($(obj) != t && $(obj).parent().find(t).length == 0) {
//				$(obj).hide('fast');
//			}
//		});
//	} else {
//		$('.lawShare').hide('fast');
//	}
//}
(function($) {

	$.fn.extend({
		tselectInit : function(data, options) {

			if (!isValid(options)) {
				var id = this.attr('id');
				return;
			}

			// 合并参数集合
			options = $.extend({}, $.tSelectDefaults, options);
			options.style = $.extend({}, $.tSelectDefaults.style, options.style);

			// 设置默认参数
			if (options.inputType == 'radio') {
				options.grade = 1;
			}
			if (parseInt(options.grade) == 1) {
				options.resultType = 'all';
			}

			// 多个对象
			return this.each(function() {
				$.tselectInit(this, data, options);
			});
		},
		tselectClear : function(fun) {

			return this.each(function() {
				$.tselectClear(this, fun);
			});
		},
		tselectReload : function(data, type, selectedIds, fun) {

			return this.each(function() {
				$.tselectReload(this, data, type, selectedIds, fun);
			});
		},
		tselectJsonData : function() {

			var result = {};
			this.each(function() {
				result[$(this).attr('id')] = $.tselectJsonData(this);
			});
			return result;
		},
		tselectOptions : function() {

			var result = {};
			this.each(function() {
				result[$(this).attr('id')] = $.tselectOptions(this);
			});
			return result;
		}
	});

	/**
	 * 下拉菜单默认参数
	 */
	$.tSelectDefaults = {
		inputType : 'checkbox',
		dataType : 'common',
		customCallBack : null,
		submitCallBack : null,
		id : 'id',
		pid : 'pid',
		name : 'name',
		value : 'id',
		grade : 1,
		resultType : 'grade',
		selectedIds : null,
		style : {
			isInline : false,
			tWidth : null,
			tMaxHeight : 300,
			tTop : null,
			tLeft : null,
			tButtonMargin : 20
		},
		openDown:true,
		inputSearch:false,
		//是否显示全选按钮
		allCheck:false
	};

	/**
	 * 下拉菜单初始化
	 */
	$.tselectInit = function(item, data, opts) {
		var checkBoxType = 'checkbox';
		if (opts.inputType == 'radio') {
			checkBoxType = 'radio';
		}
		var tSelect = $(item);
		tSelect.attr({"tabindex":0,"onblur":"blurUp(this)"});
		var tSelectId = $(item).attr('id');
		var activeClass = 'active';
		var activeClassPoint = '.active';
		// 追加下拉菜单
		var tableStr = '';
		if(opts.inputSearch){
			tableStr = '<div onblur="blurUp(this)" tabindex=0 class="t-select-table"><div onblur="blurUp(this)" tabindex=0 class="t-select-input"><input onblur="blurUp(this)" type="text" class="form-control t-select-search"/><i onblur="blurUp(this)" tabindex=0 class="fa fa-search searchIcon" aria-hidden="true"></i></div><div onblur="blurUp(this)" tabindex=0 class="t-select-view"><div onblur="blurUp(this)" tabindex=0 class="t-select-cell"></div></div></div>';
		} else{
			tableStr = '<div onblur="blurUp(this)" tabindex=0 class="t-select-table"><div onblur="blurUp(this)" tabindex=0 class="t-select-view"><div onblur="blurUp(this)" tabindex=0 class="t-select-cell"></div></div></div>';
		}
		
		// 修正二次加载导致下拉列表对象重复的问题 bywangyu 2017/5/6 start
		if(!tSelect.next().hasClass("t-select-table")){
			tSelect.after(tableStr);
		}
		// 修正二次加载导致下拉列表对象重复的问题 bywangyu 2017/5/6 end
		// 设置下拉菜单内容
		$.tselectTableSetting(tSelect, data, opts,true);
		
		//tselect实现前端搜索功能     Wang Guili & Gerry
		var tSearch = tSelect.parent().find('.t-select-search');
		// wangyu add
		//var selIds = $('#' + tSelectId + '_selected_cup').val();
		tSearch.on('input',function(){
			var searchData = $(this).val().trim();
			var jsonData = tSelect.attr('json-data');
			
			if (isNotEmpty(jsonData)) {
				jsonData = JSON.parse(jsonData);
				data = jsonData;
			}
			
			if (opts.dataType == 'tree') {
				data = $.tselectDataTreeToRow(data);
			}
			//遍历data数组
			var newData = [];
			data.map(function(obj){
				if(parseInt(opts.grade) == 1){
					if(obj[opts.name].indexOf(searchData) >= 0){
						newData.push(obj);
					}
				}else{
					//处理具有子父层关系的下拉列表
					if(obj[opts.name].indexOf(searchData) >= 0){
						//把匹配到的放入数组
						newData.push(obj);
						findParent(obj[opts.pid]);
						findChild(obj[opts.id]);
					}
					//核心思想是只要匹配到的数据都放入数组，无论重复与否
				}
			});
			
			function findChild(pid){
				data.map(function(obj,idx){
					if(obj[opts.pid] == pid){
						newData.push(obj);
						findChild(obj[opts.id]);
					}
				})
			}
			function findParent(pid){
				data.map(function(obj,idx){
					if(obj[opts.id] == pid){
						newData.push(obj);
						findParent(obj[opts.pid]);
					}
				})
			}
			
			//筛选json数组，把id重复的数据筛选掉（多级的标识是id，一级的标识为value）
			if(newData.length > 1){
				newData = uniquePay(newData,opts);
			}
			if(searchData.length==0){
				newData = data
			}
			tSelect.attr('selected-ids', $('#' + tSelectId + '_selected_cup').val());
			$.tselectTableSetting(tSelect, newData, opts,false);
			$.tSelectAdjust(tSelect);
			
			
			var tContent = tSelect.parent();
			var tTable = tSelect.parent().find('.t-select-table');
			var tView = tTable.children('.t-select-view');
			var tCell = tView.children('.t-select-cell');
			// 调整下拉菜单高度
			tSelect.attr('maxHeight', tMaxHeight);
//			if (tTable.height() < tMaxHeight) {
//				tView.height(tTable.height());
//				tTable.height(tTable.height() + 50);
//			} else {
//				tTable.height(tMaxHeight);
//				tView.height(tMaxHeight - 50);
//			}
			
			
			
			tCellOperation(opts,tSelect,tCell,checkBoxType,newData);
			
			newData = [];
		});
		
		// 获取DOM
		var tContent = tSelect.parent();
		var tTable = tSelect.parent().find('.t-select-table');
		var tView = tTable.children('.t-select-view');
		var tCell = tView.children('.t-select-cell');
		// 下拉菜单中下三角点击出现隐藏
		tCellOperation(opts,tSelect,tCell,checkBoxType);
		//
		/*tCell.find('i').off('click');
		tCell.find('i').on('click', function() {
			var t = $(this);
			var id = t.attr('tselect_id');
			var target = t.attr('tselect_target');
			var content = t.parents('.t-select-cell').find('.' + target + '[tselect_pid="' + id + '"]');
			content.each(function(idx, obj) {
				if (!$(obj).is(':hidden')) {
					$(obj).hide();
					t.removeClass('fa-caret-down').addClass('fa-caret-right');
				} else {
					$(obj).show();
					t.removeClass('fa-caret-right').addClass('fa-caret-down');
				}
			});
			// 调整下拉菜单宽高
			$.tSelectAdjust(tSelect);
		});
		// 没有子级，隐藏下三角
		tCell.find('.grade').each(function() {
			if ($(this).find('.grade').size() == 0) {
				$(this).children('i').hide();
			}
		});
		// 点击父级全选子孙级
		tCell.find('.grade label').click(function() {
			var father = $(this).find('input[type="' + checkBoxType + '"]');
			var children = $(this).parents('.grade').eq(0).find('.grade').children('.' + checkBoxType).find('input[type="' + checkBoxType + '"]');
			if (father.is(':checked')) {
				children.prop('checked', 'true');
			} else {
				children.removeAttr('checked');
			}
			// 点击子级判断是否选中父级
			var fathers = $(this).parents('.grade');
			fathers.each(function(idx, obj) {
				var grandFather = $(obj);
				if (idx > 0 && grandFather.size() > 0) {
					var grandFatherChk = grandFather.children('.' + checkBoxType).find('input[type="' + checkBoxType + '"]');
					var brotherChks = grandFather.children('.grade').children('.' + checkBoxType).find('input[type="' + checkBoxType + '"]');
					var tag = true;
					brotherChks.each(function(idx, obj) {
						if (!$(obj).is(':checked')) {
							tag = false;
						}
					});
					if (tag) {
						grandFatherChk.prop('checked', 'true');
					} else {
						grandFatherChk.removeAttr('checked');
					}
				}
			});
		});*/
		// 添加图标
		if (tContent.find('.t-select-icon').length == 0) {
			var icon = document.createElement('i');
			icon.className = 'fa fa-caret-down t-select-icon';
			if(typeof tSelect.parents('[version="1.0.1"]')!='undefined'){//改版样式修改
				icon.setAttribute('style', 'font-size:18px;position:absolute;right:15px;top:7px;cursor:pointer;');
			}else{
				icon.setAttribute('style', 'font-size:18px;position:absolute;right:10px;top:7px;cursor:pointer;');
			}
			
			tSelect.before(icon);
			// 添加点击事件
			$(icon).off('click');
			$(icon).click(function() { 
				//2017/5/5 meijf start
//				$('.t-select').removeClass(activeClass);
//				if (tTable.css('display') != 'none') {
//					tSelect.removeClass(activeClass);
//				} else {
//					tSelect.addClass(activeClass);
//				}
//				tTable.slideToggle('fast');

				$('.t-select').removeClass(activeClass);
				if (tTable.css('display') != 'none') {
					//tTable.slideUp('fast');
					//tSelect.removeClass(activeClass);
					tTable.trigger('blur')
				} else {
					tSelect.addClass(activeClass);
                	// 调整下拉菜单宽高
                	$.tSelectAdjust(tSelect);
                	//tTable.slideDown('fast');
                	tTable.trigger('focus')
                	tSearch.val('');
                	tSearch.trigger('input')
				}
				//2017/5/5 meijf end
           });

			// 添加鼠标指向事件
			$(icon).off('mouseover');
			$(icon).mouseover(function() {
				tSelect.addClass(activeClass);
			});
			// 添加鼠标滑出事件
			$(icon).off('mouseout');
			$(icon).mouseout(function() {
				if (tTable.css('display') == 'none') {
					tSelect.removeClass(activeClass);
				}
			});
			
		}
		// 添加footer
		if (tTable.find('.t-select-footer').length == 0) {
			var footer = document.createElement('div');
			footer.className = 't-select-footer';
			//如果allCheck为true，增加全选按钮
			if(opts.allCheck){
				footer.innerHTML = '<span class="btn t-select-clear">清空</span>' + '<span class="btn t-select-allcheck">全选</span>' + '<span class="btn btn-primary t-select-submit">确定</span>';
			}else{
				footer.innerHTML = '<span class="btn t-select-clear">清空</span>' + '<span class="btn btn-primary t-select-submit">确定</span>';	
			}
			tTable.append(footer);
		}
		// 取参数
		var tTop = opts.style.tTop;
		var tLeft = opts.style.tLeft;
		var tWidth = opts.style.tWidth;
		var tButtonMargin = opts.style.tButtonMargin;
		var tMaxHeight = opts.style.tMaxHeight;
		// 两按钮中间间距
		tTable.find('.t-select-clear').css('margin-right', tButtonMargin + 'px');
		tTable.find('.t-select-allcheck').css('margin-right', tButtonMargin + 'px');
		// 调整下拉菜单Y坐标
		if (tTop) {
			tTable.css('top', tTop);
		} else {
			tTable.css('top', tSelect.height() + parseInt(tSelect.css('padding-top')) + parseInt(tSelect.css('padding-bottom')));
		}
		// 调整下拉菜单X坐标
		if (tLeft) {
			tTable.css('left', tLeft);
		}
		// 调整下拉菜单宽度
		if (tWidth) {
			tTable.width(tWidth);
		} else {
			tTable.width(tSelect.width() + parseInt(tSelect.css('padding-left')) + parseInt(tSelect.css('padding-right')));
		}
		// 调整下拉菜单高度
		tSelect.attr('maxHeight', tMaxHeight);
		if ((tTable.height()+50) < tMaxHeight) {
			tView.height(tTable.height());
			tTable.height(tTable.height() + 50);
		} else {
			tTable.height(tMaxHeight);
			tView.height(tMaxHeight - 50);
		}
		// 添加点击事件
		tSelect.off('click');
		tSelect.click(function() {
			tSearch.val('');
			tSearch.trigger('input')
			// 调整下拉菜单宽高
			$.tSelectAdjust(tSelect);
			// 展开下拉菜单
			$('.t-select').removeClass(activeClass);
			tSelect.addClass(activeClass);
			if (tTable.css('display') == 'none') {
				tTable.slideDown('fast');
			}
		});
		// 设置确定按钮点击事件
		var tSubmit = tTable.find('.t-select-submit').eq(0);
		tSubmit.off('click');
		tSubmit.click(function() {
			$.tSelectSubmitClick(tTable, opts.submitCallBack);
		});
		// 点击清空事件
		var tClear = tTable.find('.t-select-clear').eq(0);
		tClear.off("click");
		tClear.click(function() {
			var tTable = $(this).parents('.t-select-table').eq(0);
			var tSelect = tTable.parent().find('.t-select');
			var tContent = tSelect.parent();
			tTable.find("input[type='" + checkBoxType + "']").removeAttr('checked');
		})
		// 点击全选事件
		var tAllCheck = tTable.find('.t-select-allcheck').eq(0);
		tAllCheck.off("click");
		tAllCheck.click(function() {
			var tTable = $(this).parents('.t-select-table').eq(0);
			var tSelect = tTable.parent().find('.t-select');
			var tContent = tSelect.parent();
			tTable.find("input[type='" + checkBoxType + "']").prop("checked",true);
		})
		// 自定义函数
		if (opts.customCallBack) {
			opts.customCallBack(tSelect);
		}
	}
	
	/**
	 * 下拉菜单手动清空选中
	 */
	$.tselectClear = function(item, fun) {
		var tSelect = $(item);
		var tSelectId = $(item).attr('id');
		// 获取DOM
		var tContent = tSelect.parent();
		var tTable = tSelect.parent().find('.t-select-table');
		var tView = tTable.children('.t-select-view');
		var tCell = tView.children('.t-select-cell');
		// 清空
		tSelect.val('');
		tSelect.removeAttr('param');
		$('#' + tSelectId + '_selected_cup').val('');
		tTable.find('input[type="checkbox"]').removeAttr('checked');
		tTable.find('input[type="radio"]').removeAttr('checked');
		//tTable.find('.t-select-search').val('');
		//tTable.find('.t-select-search').trigger("input");
		if (fun) {
			fun(tSelect);
		}
	}

	/**
	 * 下拉菜单确定按钮点击事件
	 */
	$.tSelectSubmitClick = function(tTable, submitCallBack) {
		var nameStr = '';
		var allNameStr = '';
		var valStr = '';
		var tSelect = tTable.parent().find('.t-select');
		var tSelectId = tSelect.attr('id');
		var nodes = 0;
		var tselecRealWidth = tSelect.width() - parseInt(tSelect.css('padding-left')) - parseInt(tSelect.css('padding-right')) - 20;
		var spanWidth = 0;
		var opts = $.tselectOptions(tSelect);
		var resultType = opts.resultType;
		var checkBoxType = 'checkbox';
		if (opts.inputType == 'radio') {
			checkBoxType = 'radio';
		}
		// 设置所有选中值存放备用
		var chks = tTable.find("input[type='" + checkBoxType + "']:checked");
		chks.each(function(idx, obj) {
			nameStr += $(obj).next().text().replace(/\s+/g, "") + ',';
			valStr += $(obj).val().replace(/\s+/g, "") + ',';
			//spanWidth += $(obj).next().width();
			spanWidth += $(obj).next().text().length*14;
			if ($(obj).parents('.grade').eq(0).find('.grade').size() == 0) {
				nodes++;
			}
		});
		if (valStr.length > 0) {
			valStr = valStr.substring(0, valStr.length - 1);
		}
		$('#' + tSelectId + '_selected_cup').val(valStr);
		// 设置tSelect标签选中值
		if (resultType == 'all') {
			// 取所有选中节点
			valStr += ',';
		} else {
			valStr = '';
			if (resultType == 'children') {
				// 取所有叶子节点
				var chks = tTable.find("input[type='" + checkBoxType + "']:checked");
				chks.each(function(idx, obj) {
					//if ($(obj).parents('.grade').eq(0).find('.grade').size() == 0) {
						valStr += $(obj).val().replace(/\s+/g, "") + ',';
					//}
				});
			} else {
				// 默认都为取层级。父级选中只取父级；父级未选中，取选中子级。
				var gradeRoot = tTable.find(".grade-one");
				getCheckedId(gradeRoot);
				function getCheckedId(node) {
					if (node.size() > 0) {
						var chks = node.children('.' + checkBoxType).find('input[type="' + checkBoxType + '"]');
						chks.each(function(idx, obj) {
							if ($(obj).is(':checked')) {
								valStr += $(obj).val().replace(/\s+/g, "") + ',';
							} else {
								var gradeNode = $(obj).parents('.grade').eq(0).children('.grade');
								getCheckedId(gradeNode);
							}
						});
					}
				}
			}
		}
		
		if (nameStr.length > 0) {
			allNameStr = nameStr.substring(0, nameStr.length - 1);
		}
		// 超过下拉菜单宽度，显示格式：分类(10), 否则：分类A,分类B,分类C
		if (spanWidth > tselecRealWidth) {
			nameStr = tSelect.attr('placeholder') + '(' + nodes + ')';
		} else if (nameStr.length > 0) {
			nameStr = nameStr.substring(0, nameStr.length - 1);
		}
		if (valStr.length > 0) {
			valStr = valStr.substring(0, valStr.length - 1);
		}
		tSelect.val(nameStr);
		tSelect.attr('param', valStr);
		tTable.slideUp('fast');
		
		/*var tView = tTable.children('.t-select-view');
		var tCell = tView.children('.t-select-cell');
		if (parseInt(opts.grade) == 1) {
			tCell.find('div.checkbox').show();	
		} else {
			tCell.find('div.grade-0').show();
			tCell.find('div.grade-0').find('.notparent').show();
		}*/
		//tTable.find('.t-select-search').val('');
		//tTable.find('.t-select-search').trigger("input");
	
		
		var data = {
			value : valStr,
			name : nameStr,
			allName : allNameStr
		};
		if (submitCallBack) {
			// 提交回调
			submitCallBack(tSelect, data);
		}
	}

	/**
	 * 下拉菜单失去焦点复位选项
	 */
	$.tSelectBlur = function(item) {
		var tSelect = $(item);
		var tSelectId = $(item).attr('id');
		var tTable = tSelect.parent().find('.t-select-table');
		var tSelectedCup = $('#' + tSelectId + '_selected_cup');
		var ids = (tSelectedCup.val() || '').split(',');
		var opts = $.tselectOptions(tSelect);
		
		
		var checkBoxType = 'checkbox';
		if(opts != undefined){
			if (opts.inputType == 'radio') {
				checkBoxType = 'radio';
			}
		}
		
		// 清空选中
		tTable.find('input[type="' + checkBoxType + '"]:checked').removeAttr('checked');
		// 设置选中
		
		// when tselect was cleared, so that paramater 'ids' was '[""]' and all options were selected  Wang Guili & Gerry
		if(ids.length == 1 && ids[0] == ""){
			//tTable.find('input[type="' + checkBoxType + '"]:checked').removeAttr('checked');
		}else{
			for (var i = 0; i < ids.length; i++) {
				tTable.find('input[type="' + checkBoxType + '"][value="' + ids[i] + '"]').prop('checked', 'true');
			}
		}
		//失去焦点刷新下拉列表，清空筛选
//		var searchInput = tSelect.parent().find('.t-select-search');
//		if(searchInput){
//			searchInput.val('');
//			searchInput.trigger('input')
//		}
	}

	/**
	 * 调整下拉菜单宽高
	 */
	$.tSelectAdjust = function(item) {
		var tSelect = $(item);
		var tTable = tSelect.parent().find('.t-select-table');
		var tView = tTable.children('.t-select-view');
		var tCell = tView.children('.t-select-cell');
		// 调整下拉菜单宽度
		tTable.width(tSelect.width() + parseInt(tSelect.css('padding-left')) + parseInt(tSelect.css('padding-right')));
		// 调整下拉菜单高度
		var maxHeight = tSelect.attr('maxHeight');
		tTable.show();
		var heightTotal = 0;
		$.each(tCell, function(idx, cell) {
			heightTotal += $(cell).height();
		});
		heightTotal += 50;
		if (heightTotal < maxHeight) {
			tView.height(heightTotal);
			tTable.height(heightTotal + 100);
		} else {
			tView.height(maxHeight - 50);
			tTable.height(parseInt(maxHeight) + 50);
		}
		
	}

	/**
	 * 设置下拉菜单内容
	 */
	$.tselectTableSetting = function(item, data, opts,flag) {
		var gradeInlineClass = '';
		var checkBoxType = 'checkbox';
		var checkBoxClass = 'checkbox';
		if (opts.inputType == 'radio') {
			checkBoxType = 'radio';
			checkBoxClass = 'radio';
		}
		if (opts.style.isInline) {
			checkBoxClass += '-inline';
			gradeInlineClass = 'inline';
		}
		var tSelect = $(item);
		var tSelectId = $(item).attr('id');
		var tContent = tSelect.parent();
		var tTable = tSelect.parent().find('.t-select-table');
		tSelect.prop('readonly', 'true');
		if (!tSelect.hasClass('t-select')) {
			tSelect.addClass('t-select');
		}
		if (!tContent.hasClass('t-select-content')) {
			tContent.addClass('t-select-content');
		}
		// 用于存放初始化参数
		if (tContent.find('#' + tSelectId + '_options_cup').size() == 0) {
			var str = '<input id="' + tSelectId + '_options_cup" type="hidden" value=\'' + JSON.stringify(opts) + '\'/>';
			tContent.append(str);
		}
		// 用于存放选中值
		if (tContent.find('#' + tSelectId + '_selected_cup').size() == 0) {
			var str = '<input id="' + tSelectId + '_selected_cup" type="hidden"/>';
			tContent.append(str);
		}
		
		// 用于存放 在search时默认选中的值
		if(tContent.find('#' + tSelectId + '_selected_searchCheckedTemp_cup').size() == 0){
			var str = '<input id="' + tSelectId + '_selected_searchCheckedTemp_cup" type="hidden"/>';
			tContent.append(str);
		}
		
		// 取标签上的selectedIds,优先级小于js传入的selectedIds
		var selectedIds = opts.selectedIds || tSelect.attr('selected-ids');

		$('#' + tSelectId + '_selected_cup').val(selectedIds);
		
		
		// 用于存放jsondata值
		if (tContent.find('#' + tSelectId + '_jsondata_cup').size() == 0) {
			var str = '<input id="' + tSelectId + '_jsondata_cup" type="hidden"/>';
			tContent.append(str);
		}
		// 取标签上的jsonStr,优先级小于js传入的data
		var jsonData = tSelect.attr('json-data');
		if (!data && isNotEmpty(jsonData)) {
			jsonData = JSON.parse(jsonData);
			data = data || jsonData;
		}
		// 解析数据类型
		if (opts.dataType == 'tree') {
			data = $.tselectDataTreeToRow(data);
		}
		// 设置数据
		if (data) {
			// 将数据解析成树结构
			if (parseInt(opts.grade) > 1) {
				data = $.tselectDataRowToTree(data, opts);
			}
			// 保存jsondata
			$('#' + tSelectId + '_jsondata_cup').val(JSON.stringify(data));
			// 获取配置
			var id = opts.id;
			var pid = opts.pid;
			var name = opts.name;
			var value = opts.value;
			var grade = opts.grade;
			
			if(flag){
				// 删除原table
				tTable.find('.t-select-cell').remove();
				// 设置新table
				var cellStr = '<div onblur="blurUp(this)" tabindex=0 class="t-select-cell"></div>';
				tTable.find('.t-select-view').append(cellStr);
				var cell = tTable.find('.t-select-cell');
				// 设置内容
				if (parseInt(opts.grade) == 1) {
					if(data.length > 0){
						for (var i = 0; i < data.length; i++) {
							var str = '<div onblur="blurUp(this)" tabindex=0 class="' + checkBoxClass + '">';
							str += '<label onblur="blurUp(this)" tabindex=0>';
							str += '<input onblur="blurUp(this)" type="' + checkBoxType + '" name="' + tSelectId + '_check_0" value="' + data[i][value] + '"><span onblur="blurUp(this)" tabindex=0>' + data[i][name] + '</span>';
							str += '</label>';
							str += '</div>';
							cell.append(str);
						}
					}
				} else {
					domSetting(data, tTable,opts);
					function domSetting(data, table,opts) {

						var cell = table.find('.t-select-cell');
						if(data.length > 0){
							var open = opts.openDown?'block':'none';
							var icon = opts.openDown?'fa fa-caret-down':'fa fa-caret-right';
							
							for (var i = 0; i < data.length; i++) {
								var level = data[i].level;
								if (!data[i].children) {
									level==0?open='block':open;
									var str = '<div onblur="blurUp(this)" tabindex=0  style="display:'+open+'" class="grade grade-' + level + ' ' + gradeInlineClass + ' notparent" tselect_id="' + data[i][id] + '" tselect_pid="' + data[i][pid] + '">';
									str += '<div onblur="blurUp(this)" tabindex=0 class="' + checkBoxType + '">';
									str += '<label onblur="blurUp(this)" tabindex=0>';
									str += '<input onblur="blurUp(this)" type="checkbox" name="' + tSelectId + '_check_' + level + '" value="' + data[i][value] + '" /> <span onblur="blurUp(this)" tabindex=0>' + data[i][name] + '</span>';
									str += '</label>';
									str += '</div>';
									str += '</div>';
									if (level == 0) {
									
										//open = 'block';
										cell.append(str);
									} else {
										table.find('.t-select-cell .grade-' + (level - 1) + '[tselect_id="' + data[i][pid] + '"]').append(str);
									}
								} else {
									var str = '<div onblur="blurUp(this)" tabindex=0 class="grade grade-' + level + '" tselect_id="' + data[i][id] + '" tselect_pid="' + data[i][pid] + '">';
									str += '<i onblur="blurUp(this)" tabindex=0 class="'+icon+'" tselect_target="grade-' + (level + 1) + '" tselect_id="' + data[i][id] + '"></i>';
									str += '<div onblur="blurUp(this)" tabindex=0 class="' + checkBoxType + '">';
									str += '<label onblur="blurUp(this)" tabindex=0>';
									str += '<input onblur="blurUp(this)" type="checkbox" name="' + tSelectId + '_check_' + level + '" value="' + data[i][value] + '" /><span onblur="blurUp(this)" tabindex=0>' + data[i][name] + '</span>';
									str += '</label>';
									str += '</div>';
									str += '</div>';
									if (level == 0) {
										cell.append(str);
									} else {
										table.find('.t-select-cell .grade-' + (level - 1) + '[tselect_id="' + data[i][pid] + '"]').append(str);
									}
									domSetting(data[i].children, table,opts);
								}
							}
						}
					}
				}
			}else{
				var cell = tTable.find('.t-select-cell');
				if (parseInt(opts.grade) == 1) {
					if(data.length > 0){
						cell.find('div.checkbox').hide();
						for(var i = 0; i<data.length; i++){
							cell.find('input[value="'+data[i][opts.id]+'"]').parents('div.checkbox').show();
						}
					}else{
						cell.find('div.checkbox').hide();
					}
				} else {
					cell.find('div.grade-0').hide();
					for(var i = 0; i<data.length; i++){
						cell.find('div[tselect_id="'+data[i][opts.id]+'"]').show();
						var children = data[i].children || '';
						if(children != '' && children.length > 0){
							findChildStyle(children,data[i][opts.id]);
						}
						
						/*if(children.length != '' && children.length > 0){
							cell.find('div[tselect_id="'+data[i][opts.id]+'"]').find('.notparent').hide();
							for(var j = 0; j<children.length; j++){
								cell.find('div[tselect_id="'+data[i][opts.id]+'"]').find('div[tselect_id="'+children[j][opts.id]+'"]').show();
							}
						}*/
						
					}
					
					function findChildStyle(arr,id){
							cell.find('div[tselect_id="'+id+'"]').find('.grade').hide();
							for(var j = 0; j<arr.length; j++){
								cell.find('div[tselect_id="'+id+'"]').find('div[tselect_id="'+arr[j][opts.id]+'"]').show();
								var children = arr[j].children || '';
								if(children != '' && children.length > 0){
									findChildStyle(children,arr[j][opts.id]);
								}
							}
					}
					
				}
			}
		}
		
		// 设置默认选中项
		if (selectedIds) {
			var tTable = tSelect.parent().find('.t-select-table');
			var ids = selectedIds.split(',');
			// 设置选中
			for (var i = 0; i < ids.length; i++) {
				tTable.find('input[type="' + checkBoxType + '"][value="' + ids[i] + '"]').prop('checked', 'true');
			}
			// 选中子孙级
			tTable.find("input[type='" + checkBoxType + "']:checked").each(function(idx, obj) {
				$(obj).parents('.grade').eq(0).find('input[type="' + checkBoxType + '"]').prop('checked', 'true');
			});
			// 设置数据
			var nameStr = '';
			var valStr = '';
			var nodes = 0;
			var spanWidth = 0;
			var tselecRealWidth = tSelect.width() - parseInt(tSelect.css('padding-left')) - parseInt(tSelect.css('padding-right')) - 20;
			tTable.show();
			tTable.find("input[type='" + checkBoxType + "']:checked").each(function(idx, obj) {
				nameStr = nameStr + $(obj).next().text().replace(/\s+/g, "") + ',';
				valStr = valStr + $(obj).val().replace(/\s+/g, "") + ',';
				spanWidth += $(obj).next().width();
				//spanWidth += $(obj).next().text().length*14;
				if ($(obj).parents('.grade').eq(0).find('.grade').size() == 0) {
					nodes++;
				}
			});
			tTable.hide();
			
			if (spanWidth > tselecRealWidth) {
				nameStr = tSelect.attr('placeholder') + '(' + nodes + ')';
			} else if (nameStr.length > 0) {
				nameStr = nameStr.substring(0, nameStr.length - 1)
			}

			if(flag){
				tSelect.val(nameStr);
			}
			
			if (valStr.length > 0) {
				valStr = valStr.substring(0, valStr.length - 1);
			}
			//wangyu del
			//tSelect.val(nameStr);
			//tSelect.attr('param', valStr);
			//$('#' + tSelectId + '_selected_cup').val(valStr);
			
			// 点击子级判断是否选中父级
			var fathers = tTable.find('.grade');
			fathers.each(function(idx, obj) {
				var grandFather = $(obj);
				if (grandFather.size() > 0) {
					var grandFatherChkedCnt = grandFather.children('.grade').find('input[type="' + checkBoxType + '"]:checked').length;
					var grandFatherChkCnt = grandFather.children('.grade').find('input[type="' + checkBoxType + '"]').length;
					if (grandFatherChkedCnt == grandFatherChkCnt && grandFatherChkedCnt != 0) {
						grandFather.find('input[type="' + checkBoxType + '"]').eq(0).prop('checked', 'true');
					}
				}
			});
		}
		
		
//		/setDefaultChecked(tSelect,$('#' + tSelectId + '_selected_searchCheckedTemp_cup').val(),checkBoxType,flag);
		// 删除属性
		//tSelect.removeAttr('json-data');
		tSelect.removeAttr('selected-ids');
	}

	/**
	 * 解析数据类型-行转树
	 */
	$.tselectDataRowToTree = function(data, opts) {
		//debugger;
		return MultidimensionalData(data,opts.id,opts.pid)
//		var fathers = {};
//		var fathersIds = [];
//		var notRootIds = [];
//		var id = opts.id;
//		var pid = opts.pid;
//		var name = opts.name;
//		var value = opts.value;
//		// 设置共同父ID数组
//		for ( var i in data) {
//			if (!fathers[data[i][pid]]) {
//				fathers[data[i][pid]] = [];
//			}
//			fathers[data[i][pid]].push(data[i]);
//		}
//		// 识别非叶子节点
//		for ( var name in fathers) {
//			fathersIds.push(name);
//		}
//		// 设置根节点的子孙节点
//		for (var i = 0; i < data.length; i++) {
//			if (fathers[data[i][id]] != undefined) {
//				data[i].children = fathers[data[i][id]];
//			}
//		}
//		// 过滤叶子节点
//		for (var i = 0; i < data.length; i++) {
//			var tag = true;
//			for (var j = 0; j < fathersIds.length; j++) {
//				if (data[i][pid] == fathersIds[j]) {
//					tag = false;
//					break;
//				}
//			}
//			if (tag) {
//				data.splice(i, 1);
//				i--;
//			}
//		}
//		// 识别非根节点
//		for (var i = 0; i < data.length; i++) {
//			var tag = false;
//			for (var j = 0; j < data.length; j++) {
//				if (data[i][pid] == data[j][id]) {
//					tag = true;
//					break;
//				}
//			}
//			if (tag) {
//				notRootIds.push(data[i][id]);
//			}
//		}
//		// 删除非根节点
//		for (var i = 0; i < data.length; i++) {
//			var tag = false;
//			for (var j = 0; j < notRootIds.length; j++) {
//				if (data[i][id] == notRootIds[j]) {
//					tag = true;
//					break;
//				}
//			}
//			if (tag) {
//				data.splice(i, 1);
//				i--;
//			}
//		}
//		// 设置层级
//		levelSetting(data, 0);
//		function levelSetting(data, level) {
//			for ( var i in data) {
//				data[i].level = level;
//				if (data[i].children != undefined) {
//					levelSetting(data[i].children, level + 1);
//				}
//			}
//		}
//		return data;
	}

	/**
	 * 解析数据类型-树转行
	 */
	$.tselectDataTreeToRow = function(data) {
		var result = [];
		transTreeNode(data, result);
		function transTreeNode(tree, arr) {
			for ( var idx in tree) {
				var t = tree[idx];
				if (t.children) {
					transTreeNode(t.children, arr);
				}
				delete t.children;
				arr.push(t);
			}
		}
		return result;
	}

	/**
	 * 重新加载下拉列表
	 */
	$.tselectReload = function(item, data, type, selectedIds, fun) {
		if (data) {
			var tSelect = $(item);
			var tSelectId = $(item).attr('id');
			// 获取配置参数
			var opts = $.tselectOptions(tSelect);
			opts.selectedIds = selectedIds;
			var id = opts.id;
			var pid = opts.pid;
			var name = opts.name;
			var value = opts.value;
			var jsondata;
			if (!type) {
				jsondata = data;
			} else {
				// 获取原jsondata
				jsondata = $.tselectJsonData(tSelect);
				// 原数据类型为树，则转成行
				if (parseInt(opts.grade) > 1) {
					jsondata = $.tselectDataTreeToRow(jsondata);
				}
				// 增删处理
				if (type == 'add') {
					for ( var i in data) {
						jsondata.push(data[i]);
					}
				} else if (type == 'del' || type == 'delete') {
					for (var i = 0; i < jsondata.length; i++) {
						for ( var j in data) {
							if (jsondata[i][id] == data[j][id]) {
								jsondata.splice(i, 1);
								i--;
								break;
							}
						}
					}
				}
			}
			// 设置下拉菜单内容
			$.tselectTableSetting(tSelect, jsondata, opts);
			// 清空选中
			$.tselectClear(tSelect, fun);
		}
	}

	/**
	 * 获取jsonData
	 */
	$.tselectJsonData = function(item) {
		var tSelect = $(item);
		var tSelectId = $(item).attr('id');
		var jsonStr = $('#' + tSelectId + '_jsondata_cup').val();
		return JSON.parse(jsonStr);
	}

	/**
	 * 获取options
	 */
	$.tselectOptions = function(item) {
		var tSelect = $(item);
		var tSelectId = $(item).attr('id');
		var optionsStr = $('#' + tSelectId + '_options_cup').val();
		if(optionsStr != undefined){
			return JSON.parse(optionsStr);
		}
	}

	function isValid(options) {
		return (options && typeof options === "object") ? true : false;
	}
	function isNotEmpty(str) {
		return (typeof str != "undefined" && str != 'undefined' && str != 'null' && str.trim() != '') ? true : false;
	}
	function uniquePay(arr,opts) {
		var payArr = [ arr[0] ];
		for (var i = 1; i < arr.length; i++) {
			var payItem = arr[i];
			var repeat = false;
			for (var j = 0; j < payArr.length; j++) {
				if(parseInt(opts.grade) > 1){
					if (payItem.id != undefined && payItem.id == payArr[j].id) {
						repeat = true;
						break;
					}
				} else{
					if (payItem.value != undefined && payItem.value == payArr[j].value) {
						repeat = true;
						break;
					}
				}
			}
			if (!repeat) {
				payArr.push(payItem);
			}
		}
		return payArr;
	}
	
	function uniqueStr(arr) {
		var payArr = [ arr[0] ];
		for (var i = 1; i < arr.length; i++) {
			var payItem = arr[i];
			var repeat = false;
			for (var j = 0; j < payArr.length; j++) {
				if (payItem != '' && payItem != null && payItem == payArr[j]) {
					repeat = true;
					break;
				}
			}
			if (!repeat) {
				payArr.push(payItem);
			}
		}
		return payArr;
	}
	
	function tCellOperation(opts,tSelect,tCell,checkBoxType,data){
		if(data != undefined){
			tCell.find('i').off('click');
			tCell.find('i').removeClass('fa-caret-right').addClass('fa-caret-down');
			tCell.find('i').on('click', function() {
				var t = $(this);
				var id = t.attr('tselect_id');
				var target = t.attr('tselect_target');
				
				data = $.tselectDataTreeToRow(data);
				if(data.length > 0){
					for(var i = 0 ;i< data.length;i++){
						if(data[i][opts.pid] == id){
							var content = t.parents('.t-select-cell').find('div[tselect_id="' + data[i][opts.id] + '"]');
							if (!content.is(':hidden')) {
								content.hide();
								t.removeClass('fa-caret-down').addClass('fa-caret-right');
							} else {
								content.show();
								t.removeClass('fa-caret-right').addClass('fa-caret-down');
							}
						}
					}
				}
				$.tSelectAdjust(tSelect);
			});		
		}else{
			tCell.find('i').off('click');
			tCell.find('i').on('click', function() {
				var t = $(this);
				var id = t.attr('tselect_id');
				var target = t.attr('tselect_target');
				var content = t.parents('.t-select-cell').find('.' + target + '[tselect_pid="' + id + '"]');
				content.each(function(idx, obj) {
					if (!$(obj).is(':hidden')) {
						$(obj).hide();
						t.removeClass('fa-caret-down').addClass('fa-caret-right');
					} else {
						$(obj).show();
						t.removeClass('fa-caret-right').addClass('fa-caret-down');
					}
				});
				$.tSelectAdjust(tSelect);
			});
			
		}
		
		// 没有子级，隐藏下三角
		tCell.find('.grade').each(function() {
			if ($(this).find('.grade').size() == 0) {
				$(this).children('i').hide();
			}
		});
		// 点击父级全选子孙级
		tCell.find('.grade label').click(function() {
			var father = $(this).find('input[type="' + checkBoxType + '"]');
			var children = $(this).parents('.grade').eq(0).find('.grade').children('.' + checkBoxType).find('input[type="' + checkBoxType + '"]');
			if (father.is(':checked')) {
				children.prop('checked', 'true');
			} else {
				children.removeAttr('checked');
			}
			// 点击子级判断是否选中父级
			var fathers = $(this).parents('.grade');
			fathers.each(function(idx, obj) {
				var grandFather = $(obj);
				if (idx > 0 && grandFather.size() > 0) {
					var grandFatherChk = grandFather.children('.' + checkBoxType).find('input[type="' + checkBoxType + '"]');
					var brotherChks = grandFather.children('.grade').children('.' + checkBoxType).find('input[type="' + checkBoxType + '"]');
					var tag = true;
					brotherChks.each(function(idx, obj) {
						if (!$(obj).is(':checked')) {
							tag = false;
						}
					});
					if (tag) {
						grandFatherChk.prop('checked', 'true');
					} else {
						grandFatherChk.removeAttr('checked');
					}
				}
			});
		});
	}
	
	
	//深复制对象方法    
	var cloneObj = function (obj) {
	  var newObj = {};
	  if (obj instanceof Array) {
	    newObj = [];
	  }
	  for (var key in obj) {
	    var val = obj[key];
	    //newObj[key] = typeof val === 'object' ? arguments.callee(val) : val; //arguments.callee 在哪一个函数中运行，它就代表哪个函数, 一般用在匿名函数中。  
	    newObj[key] = typeof val === 'object' ? cloneObj(val) : val;
	  }
	  return newObj;
	};


	/**
	 * 1维数据改成多维数据
	 * ${datalist} 数据
	 */
	function MultidimensionalData(datalist,id,parentId) {
	  // 拿到第一层方法
	  function getOne(datas) {
	    var one = [],list
	    datas.map(function (obj, idx) {
	      var flag = 0;
	      datas.map(function (o, i) {
	        if (obj[parentId] == o[id]) {
				  flag++
	        }
	      })
	      if (flag == 0) {
	        list = cloneObj(obj)
	        list.children = [];
	        list.level = 0;
	        one.push(list)
	      }
	      return obj
	    })
	    return one;
	  }
	  // 递归方法
	  function fns(one, data2,num) {
	    one.map(function (obj, idx) {
	      data2.map(function (o, i) {
	        if (obj[id] == o[parentId]) {
	          var list = cloneObj(o);
	          list.children = [];
	          list.level = num;
	          var fg = true;
	          obj.children.map(function(x,y){
	        	  if(x[id]==list[id]){
	        		  fg=false;
	        	  }
	          })
	          if(fg){
	        	  obj.children.push(list)
	          }
	          fns(obj.children, data2,num+1)
	        }
	      })
	    })
	  }
	  var middle = [].concat(datalist)
	  var one = getOne(middle);
	  fns(one, middle,1)
	  return one
	}
	
})(jQuery);
