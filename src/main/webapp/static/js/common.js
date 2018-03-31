// 上下文
var contextPath = null;
//定时器
var timersHeight;
// 提交flag
var submitFlag = false;
// 迁移前保存提示flag
var saveBeforeMovePage = "";
//显示中的iframe
var iframeShow;
//警告标志位
var warning = false;
//访问令牌
var accessToken = null;
// 所有页面初始化事件
$(document).ready(function() {
	var queryStringValue = getQueryString("access_token");
	if (queryStringValue == null) {
		queryStringValue = getQueryString("accessToken");
	}
	accessToken = queryStringValue;
	//禁止右键
    $(document).bind("contextmenu", function () { return false; });
	$(document).on("click", "input[input-type='datetimepicker']", function() {// 1165需求
		$(this).datetimepicker('show');
	});
	$(document).on("click", ".add-on i", function() {// 1165需求
		$(this).parents('.dataBox').find('input[type="text"]').datetimepicker('show');
	})
	$(document).on("click", ".add-on-garden i", function() {// 1165需求
		$(this).parents('.dataBox').find('input').datetimepicker('show');
	})
	// 验证组件初始化
	$.validator.setDefaults({
		focusCleanup : true,
		focusInvalid : true,
		highlight : function(element) {
			return $(element).parent().addClass('has-error');
		},
		unhighlight : function(element) {
			return $(element).parent().removeClass('has-error');
		}
	});
	//数字校验
    $.validator.addMethod("isDecimal", function(value, element) {
        if (value != "") {
            var isValid = this.optional(element) || /^\d+(\.\d{1,2})?$/.test(value);
            if (isValid) {
                if (value < 0) {
                    isValid = false;
                }
            }
            return isValid;
        } else {
            return true;
        }
    }, "请输入数字");
	// 弹出层样式
	layer.config({
		skin : "layer-ext-moon",
		extend : "skin/moon/style.css",
		shade : [0.7, "#393D49"]
	});
	//屏蔽个浏览器的backspace的各种问题
	if(navigator.userAgent.indexOf('Firefox') >= 0){
		if (window.history && window.history.pushState) {
			$(window).on('popstate', function () {
				window.history.pushState('forward', null, '#');
				window.history.forward(1);
			});
		}
		window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
		window.history.forward(1);
	}
	if(window.navigator.userAgent.indexOf('AppleWebKit') != -1) {
		if (window.history && window.history.pushState) {
			$(window).on('popstate', function () {
				window.history.pushState('forward', null, '');
				window.history.forward(1);
			});
		}
		window.history.pushState('forward', null, ''); //在IE中必须得有这两行
		window.history.forward(1);
	}

	if (window.navigator.userAgent.indexOf('Trident') != -1) {
		if (window.history && window.history.pushState) {
			$(window).on('popstate', function () {
				window.history.pushState('forward', null, '#');
				window.history.forward(1);
			});
		}
		var isIE11 = (window.navigator.userAgent.toLowerCase().indexOf("trident") > -1 && window.navigator.userAgent.indexOf("rv") > -1);
		if(isIE11){
			window.history.pushState('forward', null, '#');
			window.history.forward(1);
		}
	}
	if (!!window.ActiveXObject || "ActiveXObject" in window){
		if (window.history && window.history.pushState) {
			$(window).on('popstate', function () {
				//IE 11 必有这两行
				window.history.pushState('forward', null, '#');
				window.history.forward(1);
			});
		}
		//window.history.pushState('forward', null, '#');
		window.history.forward(1);
	} 
	//禁止后退键 作用于Firefox、Opera
	document.onkeypress = banBackSpace;
	//禁止后退键 作用于IE、Chrome
	document.onkeydown = banBackSpace;
	// 屏蔽backspace end
	
	//IE点击没光标
	if(!!window.ActiveXObject || "ActiveXObject" in window){
		$(document).mouseup(getFocusIE);
	}
	//防止页面后退
	/*if (window.history && window.history.pushState) {
		$(window).on('popstate', function () {
		window.history.pushState('forward', null, '#');
		window.history.forward(1);
		});
	}
	window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
	window.history.forward(1);*/

	//初始化超出文字两行...
//	$.extend($.fn.readyEllipsis.defaults,{//初始化方法
//		maxLine: 2
//	})
	//$('[data-ellipsis="true"]').Ellipsis()
    //点击回调函数
//	$('[data-ellipsis="true"]').on('click.zui.Ellipsis',function(e){
//		alert(1)
//	})
	//阻止空格默认事件
	$(document).on('keyup',function(e){
		var arr = e.target.className.split(' '),result=true;
		if(e.keyCode==32){
			arr.map(function(obj,idx){
				if(obj.trim()=='btn'){
					result = false;
				}
			})
		}
		return result;
	})
	/**
	 * 监听每个带有独立window的页面message变化
	 * */
	window.addEventListener('message',function(e){
		if(e.data['type']=='times'){//type==times表示计算高度的方法
			setIframeHeightAndStyle(e)
		}
		if(e.data['type']=='function'){//type==function表示要调用其他域iframe的js方法
			messageEvent(e)
		}
		
	});
	/**
	 * 监听每个带有独立window的页面message变化
	 * */
	if (self == top) { //判断在顶层window
		timersHeight = setInterval(function(){//算高定时器
			if(window.frames.length>=0&&(!!selectIframe())){
				selectIframe().contentWindow.postMessage({bodyClass:document.body.className,type:'times'},'*');
			}
		},1000)
	}
	setTimeout(function(){
		if(!!selectIframe()){
			if(!(!!selectIframe().style.height)){
				console.warn('No message from a subpage,by:yanchangkun')
			}
		}
	},5000)
});
/**
 * 查找显示的iframe
 * */
function selectIframe(win){
	var dom,rule=[];
	if(window.frames.length>0){//判断是否存在iframe
		$('.tab-pane',win).each(function(idx,obj){
			if($(obj).css('display')!=='none'){
				if(rule[0]!=obj){
					rule.push(obj)
				}
			}
		})
		if(rule.length>1){
			if(warning==false){
				warning = true;
				console.warn('The layout of the page is not reasonable,by:yanchangkun')
			}
		}
		if(rule.length>0&&rule.length<2){
			if(self==top){
				if(typeof timers!='undefined'){//清楚一些定时器
	                clearInterval(timers)
	            }
				if(!!reHeightTimer){//清楚模块的定时器
	                clearInterval(reHeightTimer);
	                reHeightTimer = false;
	            }
			}
			return $(rule[0]).find('iframe')[0];
		}
	}
}
/**
 * iframe算高及传递风格样式
 * */
function setIframeHeightAndStyle(e){
	if (self != top){//第二层和第三层
		if(self.parent==top){//第二层
			if(document.body.className != e.data['bodyClass']){
				document.body.className = e.data['bodyClass'];
			}
			if(window.frames.length>=0&&!!selectIframe()){
				selectIframe().contentWindow.postMessage({bodyClass:document.body.className,type:'times'},'*');
				selectIframe().style.height = e.data['height']+'px';
			}
			window.parent.postMessage({height:document.body.offsetHeight,type:'times'},'*');
		}else{//第三层只监听来的值传到父级，并设置bodyclass
			if(document.body.className != e.data['bodyClass']){
				document.body.className = e.data['bodyClass'];
			}
			window.parent.postMessage({height:document.body.offsetHeight,type:'times'},'*');
		}
	}else{//第一层设置高度
		selectIframe().style.height = (e.data['height']>500?e.data['height']:500)+'px';
	}
}
/**
 * iframe传值
 * 
 * @param win <Dom>
 * 		iframe.contentWindow or window.parent 对象
 * @param func <Function>
 * 		要执行的方法名
 * @param param <Array>
 * 		传给方法的参数
 * */
function iframeDoMessage(win,func,param){
	win.postMessage({
		type:'function',
		func:func,
		param:param
	},'*')
}
/**
 * message监听传来的值
 * 
 * @param e 
 * 		message监听默认参数
 * */
function messageEvent(e){
	window[e.data['func']].apply(this,e.data['param'])
}
function getAjaxGlobalSettings() {
	// 设置ajax全局请求参数
	var ajaxSettings = {
		url : "",
		type : "POST",
		contentType : "application/x-www-form-urlencoded",
		dataType : "json",
		async : true,
		cache : false,
		crossDomain : false,
		global : false,
		beforeSend : globalAjaxBeforeSend,
		success : globalAjaxSuccess,
		error : globalAjaxError,
		complete : globalAjaxComplete
	}
	return ajaxSettings;
}
/**
 * ajax提交前回调函数
 * 
 * @param xhr
 *            XMLHttpRqeust对象
 */
function globalAjaxBeforeSend(xhr) {
	xhr.setRequestHeader("Authorization", accessToken);
	// 加遮罩
	var index = addLoading();
	xhr.layerIndex = index;
}
/**
 * ajax提交成功回调函数
 * 
 * @param response
 *            服务器返回数据
 * @param textStatus
 *            状态码
 * @param jqXHR
 *            jquery的XMLHttpRqeust对象
 * @returns
 */
function globalAjaxSuccess(response, textStatus, jqXHR) {
	if (response.success == false) {
		if (response.httpStatus == '401' || response.httpStatus == '402' || response.httpStatus == '403') {
			alert(response.errorMsg);
			top.window.location.reload();
			return;
		} else {
			popAlert(response.errorMsg);
			return;
		}
	} else if (textStatus != "success") {
		popAlert("系统出现未知错误，请联系公司董秘办。<br>技术支持：价值在线400-880-3388");
		return;
	}
	if (this.dataType == "html") {
		if (this.htmlArea != null && this.htmlArea != undefined) {
			this.htmlArea.html(response);
			return;
		} else if (this.callBackFunc != null && this.callBackFunc != undefined) {
			this.callBackFunc(response);
		}
	} else if (this.dataType == "json") {
		if (this.callBackFunc != null && this.callBackFunc != undefined) {
			this.callBackFunc(response.result);
		}
	}
}
/**
 * ajax提交出错回调函数
 * 
 * @param xhr
 *            XMLHttpRqeust对象
 * @param textStatus
 *            状态码
 * @param errorThrown
 *            异常对象
 */
function globalAjaxError(xhr, textStatus, errorThrown) {
	//console.log(xhr.url + "|" + textStatus + "|" + errorThrown);
	popAlert("系统出现未知错误，请联系公司董秘办。<br>技术支持：价值在线400-880-3388");
}
/**
 * ajax提交完成回调函数
 * 
 * @param xhr
 *            XMLHttpRqeust对象
 * @param textStatus
 *            状态码
 */
function globalAjaxComplete(xhr, textStatus) {
	// 删除遮罩
	removeLoading(xhr.layerIndex);
	// 重置提交flag
	submitFlag = false;
}
/**
 * 画面刷新提交
 * 
 * @param sUrl
 *            提交URL
 * @param sConfMsg
 *            确认消息
 * @param iFormIndex
 *            提交画面form的索引
 * @returns {Boolean}
 */
function submitForm(sUrl, sConfMsg, iFormIndex) {
	if (submitFlag) {
		return false;
	}
	if (iFormIndex == undefined) {
		iFormIndex = 0;
	}
	if (sConfMsg) {
		layer.confirm(sConfMsg, {
			icon : 3
		}, function(index) {
			layer.close(index);
			submitFlag = true;
			if (self.frameElement) {
				submitFormCallback(sUrl, iFormIndex);
			} else {
				ajaxSubmitFormCallback(sUrl, iFormIndex, "html");
			}
		});
	} else {
		submitFlag = true;
		if (self.frameElement) {
			submitFormCallback(sUrl, iFormIndex);
		} else {
			ajaxSubmitFormCallback(sUrl, iFormIndex, "html");
		}
	}
	return true;
}
/**
 * 画面普通提交回调
 * 
 * @param sUrl
 *            提交URL
 * @param iFormIndex
 *            提交画面form的索引
 */
function submitFormCallback(sUrl, iFormIndex, sDataType, fSuccess) {
	document.forms[iFormIndex].action = contextPath + sUrl;
	document.forms[iFormIndex].submit();
	addLoading();
}
/**
 * 画面ajax提交回调
 * 
 * @param sUrl
 *            提交URL
 * @param iFormIndex
 *            提交画面form的索引
 * @param sDataType
 *            返回数据类型
 * @param fSuccess
 *            提交成功回调函数
 */
function ajaxSubmitFormCallback(sUrl, iFormIndex, sDataType, fSuccess, fCustomAjaxSetting) {
	var ajaxSettings = getAjaxGlobalSettings();
	if (fCustomAjaxSetting != null && fCustomAjaxSetting != undefined) {
		window[fCustomAjaxSetting(ajaxSettings)];
	}
	ajaxSettings.url = contextPath + sUrl;
	ajaxSettings.dataType = sDataType;
	var context = new Object();
	context.dataType = ajaxSettings.dataType;
	if ("html" == sDataType) {
		context.htmlArea = $("#main");
		ajaxSettings.context = context;
	} else {
		context.callBackFunc = fSuccess;
		ajaxSettings.context = context;
	}
	$(document.forms[iFormIndex]).ajaxSubmit(ajaxSettings);
}
/**
 * 画面不刷新提交
 * 
 * @param sUrl
 *            提交URL
 * @param sConfMsg
 *            确认消息
 * @param fSuccess
 *            提交成功回调函数
 * @param iFormIndex
 *            提交画面form的索引
 * @returns {Boolean}
 */
function ajaxSubmitForm(sUrl, sConfMsg, fSuccess, iFormIndex, fCustomAjaxSetting) {
	if (submitFlag) {
		return false;
	}
	if (iFormIndex == undefined) {
		iFormIndex = 0;
	}
	if (sConfMsg) {
		layer.confirm(sConfMsg, {
			icon : 3
		}, function(index) {
			submitFlag = true;
			layer.close(index);
			ajaxSubmitFormCallback(sUrl, iFormIndex, "json", fSuccess, fCustomAjaxSetting);
		});
	} else {
		submitFlag = true;
		ajaxSubmitFormCallback(sUrl, iFormIndex, "json", fSuccess, fCustomAjaxSetting);
	}
	return true;
}
var reHeightTimer;
/**
 * 页面跳转
 * 
 * @param sUrl
 *            跳转URL
 * @param parameters
 *            跳转参数
 */
function movePage(sUrl, parameters) {
	if (self.frameElement) {
		window.location.href = contextPath + sUrl;
	} else {
		if (saveBeforeMovePage != "") {
			var btnFlag = false;
			layer.open({
				cancel : function(index, layero) {
					if (btnFlag == true) {
						btnFlag = false;
						layer.closeAll();
						return false;
					}
					activeMenu(lastActiveMenuIndex);
				},
				content : saveBeforeMovePage,
				btn : ['保存并离开', '强制离开', '点错了'],
				btn1 : function(index, layero) {
					window.moveCallBackUrl = sUrl;
					window.moveCallBackParameters = parameters;
					window[moveCallBack()];
				},
				btn2 : function(index) {
					movePageCallBack(sUrl, parameters);
					btnFlag = true;
				},
				btn3 : function(index, layero) {
					layer.closeAll();
					activeMenu(lastActiveMenuIndex);
				}
			});
		} else {
			$("#main-menu li").each(function(index) {
				if ($(this).hasClass("active")) {
					lastActiveMenuIndex = index;
				}
			});
			movePageCallBack(sUrl, parameters);
		}
	}
}


/**
 * 页面跳转回调
 * 
 * @param sUrl
 *            跳转URL
 */
function movePageCallBack(sUrl, parameters) {
	saveBeforeMovePage = "";
	var ajaxSettings = getAjaxGlobalSettings();
	ajaxSettings.url = contextPath + sUrl;
	ajaxSettings.dataType = "html";
	ajaxSettings.data = parameters;
	var context = new Object();
	context.dataType = ajaxSettings.dataType;
	context.htmlArea = $("#main");
	ajaxSettings.context = context;
	$.ajax(ajaxSettings);
}

/**
 * 监管端首页跳转
 * by yanchangkun
 * @param sUrl
 *            跳转URL
 */
function movePageCallBackForSup(sUrl, parameters) {
	saveBeforeMovePage = "";
	var ajaxSettings = getAjaxGlobalSettings();
	ajaxSettings.url = contextPath + sUrl;
	ajaxSettings.dataType = "html";
	ajaxSettings.data = parameters;
	var context = new Object();
	context.dataType = ajaxSettings.dataType;
	context.htmlArea = window.parent.$("#main");
	ajaxSettings.context = context;
	$.ajax(ajaxSettings);
}

/**
 * ajax请求数据
 * 
 * @param sUrl
 *            请求URL
 * @param oParameters
 *            请求参数
 * @param fSuccess
 *            请求成功回调函数
 * @param sContentType
 *            请求类型(默认from类型)
 * @param sDataType
 *            响应类型(默认json类型)
 */
function ajaxData(sUrl, oParameters, fSuccess, sContentType, sDataType, fCustomAjaxSetting) {
	var ajaxSettings = getAjaxGlobalSettings();
	if (fCustomAjaxSetting != null && fCustomAjaxSetting != undefined) {
		window[fCustomAjaxSetting(ajaxSettings)];
	}
	ajaxSettings.url = contextPath + sUrl;
	ajaxSettings.data = oParameters;
	if (sContentType != null && sContentType != undefined) {
		ajaxSettings.contentType = sContentType;
	}
	if (sDataType != null && sDataType != undefined) {
		ajaxSettings.dataType = sDataType;
	}
	var context = new Object();
	context.dataType = ajaxSettings.dataType
	context.callBackFunc = fSuccess;
	ajaxSettings.context = context;
	$.ajax(ajaxSettings);
}
/**
 * 下载文件
 * 
 * @param sFileId
 *            文件ID
 */
function downloadFile(sFileId, fromCloud) {
	if (getValue(sFileId) != '') {
		var map = new MapCollection();
		map.put("fileId", sFileId);
		map.put("fromCloud", fromCloud);
		openPostWindow(contextPath + "/filedownload", map);
		// window.open(contextPath + "/filedownload?fileId=" + sFileId +
		// "&fromCloud=" + fromCloud, "_blank");
	}
}

function openPostWindow(url, mapCollection) {
	if(!top.document.getElementById("downIframe")) {
        var tempIframe = top.document.createElement("iframe");
        tempIframe.name = "downIframe";
        tempIframe.id = "downIframe";
        tempIframe.style.display = "none"
        top.document.body.appendChild(tempIframe);
    }

    var tempForm = top.document.createElement("form");
    tempForm.id = "tempForm1";
    tempForm.method = "post";
    tempForm.action = url;
    tempForm.target = "downIframe";
    mapCollection.each(function(key, value, index) {
        var hideInput = top.document.createElement("input");
        hideInput.type = "hidden";
        hideInput.name = key; // 后台要接受这个参数来取值
        hideInput.value = value; // 后台实际取到的值
        tempForm.appendChild(hideInput);
    });
	top.document.body.appendChild(tempForm);
    tempForm.submit();//bug3890
	top.document.body.removeChild(tempForm);
}

function MapCollection() {
	/** 存放键的数组(遍历用到) */
	this.keys = new Array();
	/** 存放数据 */
	this.data = new Object();
}
/**
 * 自定义Map（兼容IE9）
 * 
 * @type {{constructer: MapCollection, put: MapCollection.put, get:
 *       MapCollection.get, remove: MapCollection.remove, each:
 *       MapCollection.each, entrys: MapCollection.entrys, isEmpty:
 *       MapCollection.isEmpty, size: MapCollection.size, toString:
 *       MapCollection.toString}}
 */
MapCollection.prototype = {
	constructer : MapCollection,
	put : function(key, value) {
		if (this.data[key] == null) {
			this.keys.push(key);
		}
		this.data[key] = value;

	},
	get : function(key) {
		return this.data[key];
	},
	remove : function(key) {
		this.keys.remove(key);
		this.data[key] = null;
	},
	each : function(fn) {
		if (typeof fn != 'function') {
			return;
		}
		var len = this.keys.length;
		for (var i = 0; i < len; i++) {
			var k = this.keys[i];
			fn(k, this.data[k], i);
		}
	},
	entrys : function() {
		var len = this.keys.length;
		var entrys = new Array(len);
		for (var i = 0; i < len; i++) {
			entrys[i] = {
				key : this.keys[i],
				value : this.data[i]
			};
		}
		return entrys;
	},
	isEmpty : function() {
		return this.keys.length == 0;
	},
	size : function() {
		return this.keys.length;
	},
	toString : function() {
		var s = "{";
		for (var i = 0; i < this.keys.length; i++, s += ',') {
			var k = this.keys[i];
			s += k + "=" + this.data[k];
		}
		s += "}";
		return s;
	}
}
/**
 * 查看分享文件
 * 
 * @param sFileId
 *            文件ID
 */
function viewShareFile(sFileId) {
	var url = contextPath + "/viewSharefile/" + sFileId;
	$("#popwinform").remove();
	var popWinForm = "<form id='popwinform' method='post' action='" + url + "' target='' style='display:none;'></form>";
	$("body").append(popWinForm);
	var index = layer.open({
		type : 2,
		title : "文件浏览&nbsp;<font style='color:#f00;font-size:12px;'>文件处理于预览模式不能进行编辑</font>",
		content : url,
		maxmin : false,
		success : function(layero, index) {
			$("#popwinform").remove();
		}
	});
	layer.full(index);
}
/**
 * 查看文件
 * 
 * @param sFileId
 *            文件ID
 * @param sFromCloud
 *            从云端取文件
 */
function viewFile(sFileId, sFromCloud, sFavouriteFlag,pathKey) {
	var url = contextPath + "/fileview?fileId=" + sFileId;
	if (sFromCloud) {
		url = url + "&fromCloud=" + sFromCloud;
	}
	if (sFavouriteFlag) {
		url = url + "&favouriteFlag=" + sFavouriteFlag;
	}
	if (pathKey) {
		url = url + "&pathKey=" + pathKey;
	}
	$("#popwinform").remove();
	var popWinForm = "<form id='popwinform' method='post' action='" + url + "' target='' style='display:none;'></form>";
	$("body").append(popWinForm);
	var index = layer.open({
		type : 2,
		title : "文件浏览&nbsp;<font style='color:#f00;font-size:12px;'>文件处理于预览模式不能进行编辑</font>",
		content : url,
		maxmin : false,
		success : function(layero, index) {
			$("#popwinform").remove();
		}
	});
	layer.full(index);
}

/**
 * 查看ppt播放
 * 
 * @param sFileId
 *            文件ID
 * @param sFromCloud
 *            从云端取文件
 */
function pptviewFile(sFileId, id,releaseStatus,sFromCloud, sFavouriteFlag) {
	if (sFileId == null || sFileId == undefined || sFileId == 'null') {
		popMsg("文件不存在");
		return;
	}
	if(releaseStatus=="1"){
		var url = contextPath + "/pptfileview?fileId=" + sFileId + "&id=" + id + "&releaseStatus=" + releaseStatus;;
		if (sFromCloud) {
			url = url + "&fromCloud=" + sFromCloud;
		}
		if (sFavouriteFlag) {
			url = url + "&favouriteFlag=" + sFavouriteFlag;
		}
		$("#popwinform").remove();
		var popWinForm = "<form id='popwinform' method='post' action='" + url + "' target='' style='display:none;'></form>";
		$("body").append(popWinForm);
		var index = layer.open({
			type : 2,
			title : "文件浏览&nbsp;<font style='color:#f00;font-size:12px;'>文件处理于预览模式不能进行编辑</font>",
			content : url,
			maxmin : false,
			success : function(layero, index) {
				$("#popwinform").remove();
			}
		});
		layer.full(index);
	}else{
		var url = contextPath + "/pptfileview?fileId=" + sFileId + "&id=" + id + "&releaseStatus=" + releaseStatus;;
		if (sFromCloud) {
			url = url + "&fromCloud=" + sFromCloud;
		}
		if (sFavouriteFlag) {
			url = url + "&favouriteFlag=" + sFavouriteFlag;
		}
		var title = window.open(url, '_blank');
		title.document.title = '案例解析';
	}
}

/**
 * 查看文件
 * 
 * @param sFileId
 *            文件ID
 * @param callBack
 *            回调函数
 * @param paraCallBack
 *            回调参数
 * @param sFromCloud
 *            从云端取文件
 */
function readOnlineFile(sFileId, callBack, paraCallBack, sFromCloud) {
	var url = contextPath + "/fileview?fileId=" + sFileId;
	if (sFromCloud) {
		url = url + "&fromCloud=" + sFromCloud;
	}
	$("#popwinform").remove();
	var popWinForm = "<form id='popwinform' method='post' action='" + url + "' target='' style='display:none;'></form>";
	$("body").append(popWinForm);
	var index = layer.open({
		type : 2,
		title : "文件浏览&nbsp;<font style='color:#f00;font-size:12px;'>文件处理于预览模式不能进行编辑</font>",
		content : url,
		maxmin : false,
		success : function(layero, index) {
			o = layero;
			var iframeWin = window[layero.find('iframe')[0]['name']];
			if ($(iframeWin)) {
				iframeWin.popCallBack = callBack;
				iframeWin.paraCallBack = paraCallBack;
			}
			$("#popwinform").remove();
		},
		cancel : function(index) {
			var iframeWin = window[o.find('iframe')[0]['name']];
			if (sFileId.indexOf("-") == -1 && getValue(iframeWin.lastId) != '' && getValue(iframeWin.lastName) != '') {
				iframeWin.setLastId();
				var data = {
					'lastId' : iframeWin.lastId,
					'lastName' : iframeWin.lastName,
				}
				window[callBack(data, paraCallBack)];
			}
			$("#popwinform").remove();
		}
	});
	layer.full(index);
}

/**
 * 编辑文件
 * 
 * @param sFileId
 *            文件ID
 * @param callBack
 *            回调函数
 * @param paraCallBack
 *            回调参数
 * @param sFromCloud
 *            从云端取文件
 */
function editFile(sFileId, callBack, paraCallBack, sFromCloud,pathKey) {
	var url = contextPath + "/fileedit?fileId=" + sFileId;
	if (sFromCloud) {
		url = url + "&fromCloud=" + sFromCloud;
	}
	if (pathKey) {
		url = url + "&pathKey=" + pathKey;
	}
	$("#popwinform").remove();
	var popWinForm = "<form id='popwinform' method='post' action='" + url + "' target='' style='display:none;'></form>";
	$("body").append(popWinForm);
	var o;
	var index = layer.open({
		type : 2,
		title : "文件编辑&nbsp;<font style='color:#f00;font-size:12px;'>文件可正常编辑</font>",
		content : url,
		maxmin : false,
		success : function(layero, index) {
			o = layero;
			var iframeWin = window[layero.find('iframe')[0]['name']];
			if ($(iframeWin)) {
				iframeWin.popCallBack = callBack;
				iframeWin.paraCallBack = paraCallBack;
			}
			$("#popwinform").remove();
		},
		cancel : function(index) {
			var iframeWin = window[o.find('iframe')[0]['name']];
			if (sFileId.indexOf("-") == -1 && getValue(iframeWin.lastId) != '' && getValue(iframeWin.lastName) != '') {
				iframeWin.setLastId();
				var data = {
					'lastId' : iframeWin.lastId,
					'lastName' : iframeWin.lastName,
				}
				window[callBack(data, paraCallBack)];
			}
			$("#popwinform").remove();
		}
	});
	layer.full(index);
}
/**
 * 删除文件
 * 
 * @param sFileIds
 *            文件ID(可是多个文件ID:fileIds=1&fileIds=2）
 */
function delFile(sFileIds) {
	ajaxData("/filedel", sFileIds, function() {
		popMsg("删除成功");
	});
}
/**
 * 增加loading遮罩
 */
function addLoading() {
	return layer.load(2);
}
/**
 * 删除loading遮罩
 */
function removeLoading(index) {
	if (index != undefined) {
		layer.close(index);
	} else {
		layer.closeAll('loading');
	}
}
/**
 * 弹出消息
 * 
 * @param msg
 *            提示信息
 * @param position
 *            显示位置
 */
function popMsg(msg, position) {
	if (position == undefined) {
		layer.msg(msg);
	} else {
		layer.msg(msg, {
			offset : position
		});
	}
}
/**
 * 弹出警告
 * 
 * @param info
 *            提示信息
 */
function popAlert(info) {
	layer.alert(info);
}
/**
 * confirm确认画面 sConfMsg：内容 confirmCallBack 回调函数 paraCallBack 回调参数
 */
function popConfirm(sConfMsg, confirmCallBack, paraCallBack, cancelCallBack, cancelParaCallBack) {
	layer.confirm(sConfMsg, {
		icon : 3,
		title : "系统确认"
	}, function(index) {
		layer.close(index);
		confirmCallBack(paraCallBack);
	}, function(index) {
		layer.close(index);
		if (cancelCallBack != undefined) {
			cancelCallBack(cancelParaCallBack);
		}
	});
}
/**
 * 弹出页面
 * 
 * @param title
 *            标题
 * @param url
 *            画面地址
 * @param data
 *            画面参数
 * @param width
 *            宽
 * @param height
 *            高
 * @param callBack
 *            回调函数
 * @param paraCallBack
 *            回调参数
 */
function popWin(title, url, data, width, height, callBack, paraCallBack, cancelCallBack) {
	height = setPopWinHeight(height);// 设置弹出窗口高度 by hanwei
	var pUrl = contextPath + url;
	$("#popwinform").remove();
	var popWinForm = "<form id='popwinform' method='post' action='" + pUrl + "' target='' style='display:none;'>";
	if (data != null && data != undefined && data != "") {
		$.each(data, function(name, value) {
			popWinForm = popWinForm + "<input type='hidden' name='" + name + "' value='" + value + "'>";
		});
	}
	popWinForm = popWinForm + "</form>";
	$("body").append(popWinForm);
	var index = layer.open({
		type : 2,
		title : title,
		area : [width, height],
		content : pUrl,
		success : function(layero, index) {
			var iframeWin = window[layero.find('iframe')[0]['name']];
			iframeWin.popCallBack = callBack;
			iframeWin.paraCallBack = paraCallBack;
			$("#popwinform").remove();

		},
		cancel : cancelCallBack
	});
	return index;
}
//  demand 2491 lanyuxin 2017-12-14 begin
/**
 * 弹出页面无关闭窗口
 * 
 * @param title
 *            标题
 * @param url
 *            画面地址
 * @param data
 *            画面参数
 * @param width
 *            宽
 * @param height
 *            高
 * @param callBack
 *            回调函数
 * @param paraCallBack
 *            回调参数
 *            
 */
function popWinHideClose(title, url, data, width, height, callBack, paraCallBack, cancelCallBack) {
	height = setPopWinHeight(height);// 设置弹出窗口高度 by hanwei
	var pUrl = contextPath + url;
	$("#popwinform").remove();
	var popWinForm = "<form id='popwinform' method='post' action='" + pUrl + "' target='' style='display:none;'>";
	if (data != null && data != undefined && data != "") {
		$.each(data, function(name, value) {
			popWinForm = popWinForm + "<input type='hidden' name='" + name + "' value='" + value + "'>";
		});
	}
	popWinForm = popWinForm + "</form>";
	$("body").append(popWinForm);
	
	var index = layer.open({
		type : 2,
		title : title,
		area : [width, height],
		content : pUrl,
		closeBtn: false,
		success : function(layero, index) {
			var iframeWin = window[layero.find('iframe')[0]['name']];
			iframeWin.popCallBack = callBack;
			iframeWin.paraCallBack = paraCallBack;
			$("#popwinform").remove();
//			$(".layui-layer-setwin").hide();
		}
//	,
//		cancel : cancelCallBack
	});
	return index;
}
//demand 2491 lanyuxin 2017-12-14 end
/**
 * 弹出全屏窗口
 * 
 * @param title
 *            标题
 * @param url
 *            画面地址
 * @param data
 *            画面参数
 * @param callBack
 *            回调函数
 * @param paraCallBack
 *            回调参数
 */
function popFullScreen(title, url, data, callBack, paraCallBack) {
	var index = popWin(title, url, data, "100%", "100%", callBack, paraCallBack);
	layer.full(index);
}
/**
 * 指定当前画面的HTML到弹出页面
 * 
 * @param areaId
 *            需要弹出的html内容区域的ID
 * @param title
 *            标题
 * @param width
 *            宽
 * @param height
 *            高
 */
function popWinWithHtml(areaId, title, width, height) {
	layer.open({
		type : 1,
		title : title,
		area : [width, height],
		content : $("#" + areaId).html()
	});
}
/**
 * 弹出窗口回调父窗口的函数
 * 
 * @param paraWin
 *            弹出窗口返回的参数
 */
function closeWinCallBack(paraWin) {
	var callBack = window.popCallBack;
	var paraCallBack = window.paraCallBack;
	parent.window[callBack(paraWin, paraCallBack)];
	closeWin();
}
/**
 * 关闭当前iframe层
 */
function closeWin() {
	// 先得到当前iframe层的索引
	var index = parent.layer.getFrameIndex(window.name);
	// 执行关闭
	parent.layer.close(index);
}
/**
 * 重置区域
 * 
 * @param areaId
 *            重置区域ID
 */
function clearArea(areaId) {
	$("input[type='text'],input[type='hidden'],select:not([multiple='multiple']),textarea", $("#" + areaId)).each(function() {
		$(this).val("");
	});
	$("select[multiple='multiple']", $("#" + areaId)).each(function() {
		$("option", $(this)).remove();
	});
	$("input[type='checkbox']:checked", $("#" + areaId)).each(function() {
		$(this).attr("checked", false);
	});
}

/**
 * 刷新table的行index避免提交后台有空行
 * 
 * @param tableId
 * @returns {Number}
 */
function refreshTableIndex(tableId) {
	var rowIndex = 0;
	$(("tr"), $("#" + tableId)).each(function(index) {
		if (index == 0) {
			return true;
		}
		// 取得原有index
		var orgIndex = $(this).data("index");
		$("input,checkbox,radio,select,textarea", $(this)).each(function() {
			var name = $(this).prop("name")
			var newName = name.replace(new RegExp("\\[" + orgIndex + "\\]", "g"), "[" + rowIndex + "]");
			$(this).prop("name", newName);
		});
		// 设置新的index
		$(this).data("index", rowIndex);
		rowIndex++;
	});
	// 返回最大的index
	return rowIndex;
}

// 写入cookie
function SetCookie(name, value) {
	var Days = 10; // 此 cookie 将被保存 30 天
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	var isSsl = 'https:' == document.location.protocol ? true : false;
	if (isSsl) {
		document.cookie = name + "=" + encodeURI(value) + ";expires=" + exp.toGMTString() + ";path=/;secure;";
	} else {
		document.cookie = name + "=" + encodeURI(value) + ";expires=" + exp.toGMTString() + ";path=/;";
	}
}
// /删除cookie
// /删除cookie
function delCookie(name) {
	setCookie2(name, "", -1);
}
// 设置cookie
function setCookie2(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	var isSsl = 'https:' == document.location.protocol ? true : false;
	if (isSsl) {
		document.cookie = cname + "=" + cvalue + "; " + expires + ";path=/;secure;";
	} else {
		document.cookie = cname + "=" + cvalue + "; " + expires + ";path=/;";
	}
}
// 读取cookie
function getCookie(name) {
	var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
	if (arr != null)
		return arr[2];
	return null;
}

function base64_encode(str) {
	var c1, c2, c3;
	var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	var i = 0, len = str.length, string = '';

	while (i < len) {
		c1 = str.charCodeAt(i++) & 0xff;
		if (i == len) {
			string += base64EncodeChars.charAt(c1 >> 2);
			string += base64EncodeChars.charAt((c1 & 0x3) << 4);
			string += "==";
			break;
		}
		c2 = str.charCodeAt(i++);
		if (i == len) {
			string += base64EncodeChars.charAt(c1 >> 2);
			string += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
			string += base64EncodeChars.charAt((c2 & 0xF) << 2);
			string += "=";
			break;
		}
		c3 = str.charCodeAt(i++);
		string += base64EncodeChars.charAt(c1 >> 2);
		string += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
		string += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6));
		string += base64EncodeChars.charAt(c3 & 0x3F)
	}
	return string
}
/*
 * Javascript base64_decode() base64解密函数 用于解密base64加密的字符串 吴先成 www.51-n.com
 * ohcc@163.com QQ:229256237 @param string str base64加密字符串 @return string
 * 解密后的字符串
 */
function base64_decode(str) {
	if (str == null) {
		return;
	}
	var c1, c2, c3, c4;
	var base64DecodeChars = new Array(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
			24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1);
	var i = 0, len = str.length, string = '';

	while (i < len) {
		do {
			c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff]
		} while (i < len && c1 == -1);

		if (c1 == -1)
			break;

		do {
			c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff]
		} while (i < len && c2 == -1);

		if (c2 == -1)
			break;

		string += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));

		do {
			c3 = str.charCodeAt(i++) & 0xff;
			if (c3 == 61)
				return string;

			c3 = base64DecodeChars[c3]
		} while (i < len && c3 == -1);

		if (c3 == -1)
			break;

		string += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));

		do {
			c4 = str.charCodeAt(i++) & 0xff;
			if (c4 == 61)
				return string;
			c4 = base64DecodeChars[c4]
		} while (i < len && c4 == -1);

		if (c4 == -1)
			break;

		string += String.fromCharCode(((c3 & 0x03) << 6) | c4)
	}
	return string;
}
// 格式化值
function getValue(val) {
	if (val == null || val == "null" || val == undefined || val == "undefined") {
		val = "";
	}
	return val
}

var winWidthwin = 0;
var winHeightwin = 0;
// var winHeithtMax=620;
function setPopWinHeight(heightpx) {// 计算弹出窗口高度
	heightpx = heightpx.toLowerCase();
	var highttemp = heightpx.substr(0, heightpx.indexOf("px"));
	if (heightpx.indexOf("px") == -1)
		return heightpx;
	var winWidth = 0;
	var winHeight = parseInt(highttemp);
	findDimensions();
	if (winHeight > winHeightwin) {
		winHeight = winHeightwin;
	}
	if (winHeightwin == 0) {
		return heightpx;
	}
	return winHeight + "px";
}
function findDimensions(heightpx) // 函数：获取当前IE窗口尺寸
{
	var winWidth = 0;
	var winHeight = 0;

	// 获取窗口宽度
	if (window.innerWidth)
		winWidth = window.innerWidth;
	else if ((document.body) && (document.body.clientWidth))
		winWidth = document.body.clientWidth;
	// 获取窗口高度
	if (window.innerHeight)
		winHeight = window.innerHeight;
	else if ((document.body) && (document.body.clientHeight))
		winHeight = document.body.clientHeight;
	// 通过深入Document内部对body进行检测，获取窗口大小
	if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) {
		winHeight = document.documentElement.clientHeight;
		winWidth = document.documentElement.clientWidth;
	}
	// 结果输出至两个文本框
	winWidthwin = winWidth
	winHeightwin = winHeight
}

function preLoadJs(script) {
	var s = document.createElement('script');
	s.setAttribute('type', 'text/javascript');
	s.setAttribute('src', script);
	s.setAttribute('defer', 'defer');
	var head = document.getElementsByTagName('head');
	head[0].appendChild(s);

}
function preLoadImg(url) {
	var img = new Image();
	img.src = url;
}
// 需求396
// 2016/06/17 需求398 by zhao.hf START
// 此处代码merge时需要注意，只需留一个即可
function checkFileSuffix(fileName) {
	var checkFile = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length);
	var approveFiles = $("#approveFilesSuffix").val();
	if (approveFiles == "") {
		return false;
	}
	var arrayFiles = new Array();
	var arrayFiles = approveFiles.split(";");
	for (var i = 0; i < arrayFiles.length; i++) {
		if (checkFile.toLowerCase() == arrayFiles[i].toLowerCase()) {
			return true;
		}
	}
	return false;
}
// 2016/06/17 需求398 by zhao.hf START
/**
 * 校验以startStr开始 fan.gy
 */
String.prototype.startWith = function(startStr) {
	var reg = new RegExp("^" + startStr);
	return reg.test(this);
}
/**
 * 校验以endStr结尾 fan.gy
 */
String.prototype.endWith = function(endStr) {
	var reg = new RegExp(endStr + "$");
	return reg.test(this);
}
/*function refuseBackHome(e) {
	var ev = e || window.event;// 获取event对象
	var obj = ev.target || ev.srcElement;// 获取事件源
	var t = obj.type || obj.getAttribute('type');// 获取事件源类型
	if (ev.keyCode == 8 && ((t != "password" && t != "text" && t != "textarea" && checkDivEditTable(obj)) || ev.srcElement.readOnly == true)) {
		return false;
	}
}*/
function banBackSpace(e){  
    //alert(event.keyCode)  
    var ev = e || window.event;//获取event对象     
    var obj = ev.target || ev.srcElement;//获取事件源       
    var t = obj.type || obj.getAttribute('type');//获取事件源类型       
    //获取作为判断条件的事件类型   
    var vReadOnly = obj.readOnly;  
    var vDisabled = obj.disabled;  
    //处理undefined值情况   
    vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;  
    vDisabled = (vDisabled == undefined) ? true : vDisabled;  
    //当敲Backspace键时，事件源类型为密码或单行、多行文本的，    
    //并且readOnly属性为true或disabled属性为true的，则退格键失效    
    var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vDisabled == true);  
    //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效      
    var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea";    
    //判断会议管理   div没有disable属性
    var flag3 = ev.keyCode == 8 && obj.getAttribute('contenteditable') == null;
    if ((flag2 || flag1) && flag3) {
		if (event.preventDefault) {
			event.preventDefault();
	    } else {
	    	event.returnValue = false;
	    }
    }
        //event.returnValue = false;//这里如果写 return false 无法实现效果   
}



function checkDivEditTable(obj) {
	var returnFlag = true;
	var arr = obj.className.split(' ');
	for (var j = 0; j < arr.length; j++) {
		if (arr[j] == 'billNameDiv') {
			returnFlag = false;
		}
	}
	return returnFlag;
}
/**
 * 获取table行数
 * 
 * @param tableId
 *            只传tableid 不要带#
 * @param findObj
 *            查找区域 比如 tbody等等
 * @param findAttr
 *            查找的数据 tr、td等等
 */
function getTableRowCount(tableId, findObj, findAttr) {
	var jqTableId = '#' + tableId;
	var tBody = $(jqTableId).find(findObj);
	if (tBody == undefined || tBody == null) {
		return 0
	} else {
		var rows = tBody.find(findAttr);
		if (rows == undefined || rows == null) {
			return 0;
		} else {
			return rows.length;
		}
	}
}
/**
 * 是否为空
 * 
 * @param obj
 * @returns {Boolean}
 */
function isNotEmpty(obj) {
	if (obj == undefined || obj == null || obj.length == 0) {
		return false;
	}
	return true;
}
/**
 * 获取url参数
 */
function GetRequestParam(resUrl) {
	var paramObj = new Object();
	if (isNotEmpty(resUrl)) {
		var TransUrl = decodeURI(resUrl);
		var idex = TransUrl.indexOf("?");
		if (idex != -1) {
			var str = TransUrl.substr(idex);
			if (str != null && str.length > 1) {
				str = str.substr(1);
				var params = str.split("&");
				var paramChild = null;
				for (var i = 0; i < params.length; i++) {
					paramChild = params[i].split("=");
					if (isNotEmpty(paramChild)) {
						paramObj[paramChild[0]] = unescape(paramChild[1]);
					}
				}
			}
		}
	}
	return paramObj;
}
// dataRangePicker初始化
function dataRangePickerInit(dateRange, startDate, endDate, sumbitFun, clearFun, params) {
	dateRange.prop('readonly','true')
	var dateRangeParent = dateRange.parent();
	//Bug5848 给startDate加val,否则获取不到日期,而是得到object类型
	var startDateTmp = startDate;
	if(typeof startDate == "object" && getValue(startDate) != ''){
		startDateTmp = startDate.val();
	}
	if (!startDateTmp) {
		startDateTmp = moment();
	}
	//Bug5848 给endDate加val
	var endDateTmp = endDate;
	if(typeof endDate == "object" && getValue(endDate) != ''){
		endDateTmp = endDate.val();
	}
	if (!endDateTmp) {
		endDateTmp = moment();
	}
	var opens = 'center';
	if (params) {
		if (params.opens) {
			opens = params.opens;
		}
	}
	
	//本会计年度&&上一会计年度
	var nows = new Date();
	nows.setDate(1);
	nows.setMonth(0);
	
	var options = {
		//autoUpdateInput : true,
		startDate : startDateTmp,
		endDate : endDateTmp,
		// minDate: '01/01/2012', //最小时间
		// maxDate : moment(), // 最大时间
		// dateLimit : {
		// days : 30
		// }, // 起止时间的最大间隔
		autoApply : true, // 选择日期不用点确定
		showDropdowns : true,
		linkedCalendars : false,
		showWeekNumbers : false, // 是否显示第几周
		timePicker : false, // 是否显示小时和分钟
		timePickerIncrement : 60, // 时间的增量，单位为分钟
		timePicker12Hour : false, // 是否使用12小时制来显示时间
		//autoUpdateInput : false,// 自动更新input值
		
		
		
		ranges : {
			'今天' : [moment().startOf('day'), moment()],
			'明天' : [moment().add('days', 1).startOf('day'), moment().add('days', 1).endOf('day')],
			'最近3天' : [moment().subtract('days', 2), moment()],
			'最近一周' : [moment().subtract('days', 6), moment()],
			'最近一月' : [moment().subtract('months', 1), moment()],
			'最近一年' : [moment().subtract('years', 1), moment()],
			'本会计年度':[moment(nows), moment()],
			'上一会计年度':[moment(nows).subtract('years', 1), moment(nows).subtract('days',1)]
		},
		opens : opens, // 日期选择框的弹出位置
		alwaysShowCalendars : true,
		buttonClasses : ['btn'],
		applyClass : 'btn-primary',
		cancelClass : '',
		locale : {
			format : 'YYYY-MM-DD',
			separator : " 至 ",
			applyLabel : '确定',
			cancelLabel : '取消',
			fromLabel : '起始时间',
			toLabel : '结束时间',
			customRangeLabel : '自定义',
			daysOfWeek : ['日', '一', '二', '三', '四', '五', '六'],
			monthNames : ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
			firstDay : 1
		}
	};
	// 如果有input日历图标，则删除
	if (dateRangeParent.find('.daterangetag').length > 0) {
		dateRangeParent.find('.daterangetag').remove();
	}
	// 增加input日历图标
	var daterangetag = document.createElement('i');
	daterangetag.className = "glyphicon glyphicon-calendar fa fa-calendar daterangetag";
	var rightVal = parseInt(dateRange.css('padding-right')) + parseInt(dateRange.parent().css('padding-right'));
	$(daterangetag).css('right', rightVal + 'px');
	$(daterangetag).css('top', '7px');
	dateRange.after(daterangetag);
	$(daterangetag).click(function() {
		dateRange.click();
	});
	// 如果有input清空图标，则删除
	/*if (dateRangeParent.find('.daterangeclear').length > 0) {
		dateRangeParent.find('.daterangeclear').remove();
	}*/
	// input清空图标
	var daterangeclear = document.createElement('i');
	daterangeclear.className = "glyphicon glyphicon-calendar fa fa-remove daterangeclear";
	var rightVal = 20 + parseInt(dateRange.css('padding-right')) + parseInt(dateRange.parent().css('padding-right'));
	$(daterangeclear).css('right', rightVal + 'px');
	$(daterangeclear).css('top', '7px');
	dateRange.after(daterangeclear);
	if (!startDate && !endDate) {
		$(daterangeclear).hide();
	}
	// 日期选择框-选择后回调方法
	function dateRangeSubmitCallBack(start, end, label) {
		dateRange.val(start.format('YYYY-MM-DD') + ' 至 ' + end.format('YYYY-MM-DD'));
		var timeValueStart = start.format('YYYY-MM-DD');
		var timeValueEnd = end.format('YYYY-MM-DD');
		dateRange.attr('startTimes', timeValueStart);
		dateRange.attr('endtTimes', timeValueEnd);
		$(daterangeclear).show();
		if (sumbitFun) {
			sumbitFun();
		}
	}
	// 初始化控件
	dateRange.daterangepicker(options, dateRangeSubmitCallBack);
	// 清空图标点击事件
	$(daterangeclear).click(function() {
		dateRange.attr('startTimes', '');
		dateRange.attr('endtTimes', '');
		dateRange.val('');
		$(daterangeclear).hide();
		// 清空后重置日期选择框
		/*var optionsTmp = options;
		optionsTmp.startDate = moment();
		optionsTmp.endDate = moment();
		dateRange.daterangepicker(optionsTmp, dateRangeSubmitCallBack);*/
		styleSetting();
		// 清空回调方法
		if (clearFun) {
			clearFun(daterangeclear);
		}
	});
	
	// input变更事件
	dateRange.change(function() {
		if (dateRange.val().trim() != '') {
			$(daterangeclear).show();
		} else {
			$(daterangeclear).hide();
		}
	});

	var eqx = $('.daterangepicker').eq($('.ranges ul li[data-range-key="今天"]').index(this));
	$('.ranges ul li[data-range-key="今天"]',eqx).click(function(){
		if($('input[name=daterangepicker_start]',eqx).val() === moment().startOf('day').format('YYYY-MM-DD')
				&& $('input[name=daterangepicker_end]',eqx).val() === moment().endOf('day').format('YYYY-MM-DD')){
			dateRange.attr('startTimes', $('input[name=daterangepicker_start]',eqx).val());
			dateRange.attr('endtTimes', $('input[name=daterangepicker_end]',eqx).val());
			dateRange.val(dateRange.attr('startTimes')+' 至 '+dateRange.attr('endtTimes'));
			$(daterangeclear).show();
			
			if (sumbitFun) {
				sumbitFun();
			}			
		}
	})
	
	styleSetting();
	// 设置样式及事件
	function styleSetting() {
		var t = $('.daterangepicker .ranges');
		t.width(470);
		t.find('ul').width(470);
		if (!startDate && !endDate) {
			// 清空默认日期
			dateRange.val('');
		}
	}
}

// 日期控件初始化//1165需求
function dateRangeDataInit(id, star, end) {// id:调用的input，star：隐藏的开始时间输入框，end:隐藏的结束时间输入框
	var stars = star.val(), ends = end.val();
	if (stars && ends != '' || undefined) {
		id.val(stars + ' 至 ' + ends);
		id.attr('starttimes', stars);
		id.attr('endttimes', ends);
	}
	dataRangePickerInit(id, star, end, function() {
		ajaxTableQuerySubmit(id, star, end);
	})
}

function ajaxTableQuerySubmit(id, star, end) {// 1165需求
	star.val(id.attr('startTimes'));
	end.val(id.attr('endtTimes'));
}

// 1165需求 .classCheckBox 点击模拟checkbox事件
function setSelFileto(obj, callback, disabledCallback) {
	var check = $(obj).find('input[type="checkbox"]');
	if ((check.attr('disabled') !== 'disabled' && true) && (check.attr('readonly') !== 'readonly' && true) ) {
		if (!check.get(0).checked) {
			check.get(0).checked = true;
		} else if (check.get(0).checked) {
			check.get(0).checked = false;
		}
		callback && callback(obj);
	} else {
		check.addClass('disableds')
		disabledCallback && disabledCallback();
	}
	check.change();
}
// 1165需求 全选功能
function selectAllto(obj) {
	var tdCheck = $(obj).parents('table').find("input[type='checkbox']");
	//alert($(obj).find('input[type="checkbox"]').get(0).checked)
	tdCheck.prop('checked', $(obj).find('input[type="checkbox"]').get(0).checked);
}
// 1165需求 全选功能for two tables
function selectAllforInfo(obj){
	var tdcheck = $('table#files').find("input[type='checkbox']");
	tdcheck.prop('checked', $(obj).find("input[type='checkbox']").get(0).checked);
}

/**
 * 替换全部
 * 
 * @param reallyDo
 *            替换目标
 * @param replaceWith
 *            替换结果
 * @param ignoreCase
 *            是否忽略大小写
 */
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
	if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")), replaceWith);
	} else {
		return this.replace(reallyDo, replaceWith);
	}
}

/**
 * 字符串如果为null则替换为blankStr
 * 
 * @param str
 *            字符串
 * @param blankStr
 *            替换字符串
 */
function trim2Str(str, blankStr) {
	if (!str) {
		return blankStr || '';
	}
	return str.trim();
}

//需求1510 文章内追加关键字检索高亮显示 bywangyu 2017/04/08 start
/**
 * 高亮文字替换
 * @param contextHtml:被替换 html对象
 * @param keyWords 关键词lst
 */	
function setContextHtmlFormat(contextHtml, keyWords){
	 $.each(keyWords, function(i, item){
		 if($.trim(item) != ""){
			 query = unicode($.trim(item));
			 var regex = new RegExp("(<[^>]*>)|("+ query +")", "gi");       
			 $(contextHtml).html($(contextHtml).html().replace(regex, function(a, b, c){
				 return (a.charAt(0) == "<") ? a : "<font style='background-color:#FFFF00'>" + c + "</font>";
			 }));
		 }
	 });
}

function  unicode(s){ 
	var len=s.length; 
	var rs=""; 
	s = s.replace(/([-.*+?^${}()|[\]\/\\])/g,"\\$1");
	for(var i=0;i<len;i++){
		if(s.charCodeAt(i) > 255)
			rs+="\\u"+ s.charCodeAt(i).toString(16);
		else rs +=  s.charAt(i);
	}   
	return rs; 
}

//需求1510 文章内追加关键字检索高亮显示 bywangyu 2017/04/08 end

function getFocusIE(e) {
	var target = e.target;
	if((target.getAttribute('contenteditable') !=null || target.type == "text") && e.button === 0){
        var a = target.createTextRange().duplicate();//创建文本范围对象a
        if(!$(target).val()) {
            a.moveStart('character', 0); //更改范围起始位置/*如果count改为0就把光标放在text中的字符的最前面*/
        }else{
            var start = target.selectionStart;
            if(start) {
                a.moveStart('character', start);
            }else{
                a.moveStart('character', 0);
			}
        }
        a.collapse(true);
        a.select();
	}
}

/**
 * 消息弹窗数据初始化
 * @param data
 * @returns
 */
function popoverHot(flag){
	var content = "";
	$(".showBoxPrompt").parents('li').popover('destroy');
	ajaxData("/message/getMessageForBox", null, function(data){
		if(isNotEmpty(data)){
			var length = data.length-1;
			var title ='<div class ="row" style="width:400px;height:240px;margin-top:-20px;margin-left:9px"><p style="color:black"><div class="list" style ="height:190px"><table>';
			data.map(function(obj,idx){
				title += '<tr><td>'+'<span style="font-size:14px;font-weight:normal;" title="'+ obj.title +'"><a class="titleHand" href="javascript:detail(\'' + obj.id +'\',\'' + obj.businessUrl + '\',\''+ obj.businessParam +'\',\'' + obj.checkValidUrl + '\',\'' + obj.checkValidParam+'\',\'' + obj.opCode+'\')" style="display:block;width:377px;color:black;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">'+obj.title+'</a></span>'+'</td></tr>'
				if(idx == length){
					title+='</p></table></div><div class="lines"></div><div class="popoverBottom" style="margin-top:28px"><a class="changeHandOther" href="javascript:goInToTask()">查看全部待办</a><a class="changeHand" href="javascript:javascript:goInToMessage();" style="color:black;float:right;margin-right:20px">查看全部消息</a><a class="changeHandOther" href="javascript:closeBox()" style="float:right;margin-right:20px">忽略全部</a></div></div>';
				}
			})
			document.getElementById("boxPromptProp").innerHTML =title;
			content = title;
		}else{
			document.getElementById("boxPromptProp").innerHTML ="";
		}
		$($(".showBoxPrompt").parents('li')).popover('destroy');
		if(content != ""){
			$(".showBoxPrompt").parents('li').popover({
				title:temp, //标题的模板
				html:true,//是否允许title和content插入html
				container:'li.dropdown.nav-icon-btn-success', //将此弹框结构插入dom节点中，参数为css选择器
				content:content, //主体内容模板
				placement:"bottom",//朝向
				trigger:'hover'//触发事件，多事件空格分割
			});
			if(flag == '1'){
				$($(".showBoxPrompt").parents('li')).popover('show');
			}
			
		}else{
			$($(".showBoxPrompt").parents('li')).popover('destroy');
		}
		
	})
	return content;
}

//页面加载回掉函数
var reHeightTimer;
var componentDidMount = false;
function paperLoad(){
	if(!componentDidMount){
		$('#myTab>li').eq(0).addClass('active').siblings().removeClass('active');
		$($('#myTab>li').eq(0).find('a').attr('href')).addClass('active').addClass('in').siblings().removeClass('active').removeClass('in');
		return
	}else{
		return componentDidMount()
	}
	
}

//url中search解析
function getQueryString() {   
    var url = window.location.search; 
    var theRequest = new Object();   
    if (url.indexOf("?") != -1) {   
       var str = url.substr(1);   
       strs = str.split("&");   
       for(var i = 0; i < strs.length; i ++) {
          theRequest[strs[i].split("=")[0]]=decodeURI(strs[i].split("=")[1]); 
       }   
    }   
    return theRequest;   
}

/**
 * 获取url中的参数
 * @param paramName 参数名
 * @returns
 */
function getQueryString(paramName) {
  var reg = new RegExp("(^|&)" + paramName + "=([^&]*)(&|$)", "i");
  var result = window.location.search.substr(1).match(reg);
  if (result != null) {
    return decodeURIComponent(result[2]);
  } else {
    return null;
  }
}


