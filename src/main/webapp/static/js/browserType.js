/* 
 * ÃèÊö£ºÅÐ¶Ïä¯ÀÀÆ÷ÐÅÏ¢ 
 * ±àÐ´£ºLittleQiang_w 
 * ÈÕÆÚ£º2016.1.5 
 * °æ±¾£ºV1.1 
 */

//ÅÐ¶Ïµ±Ç°ä¯ÀÀÀàÐÍ  
function BrowserType() {
	var userAgent = navigator.userAgent; //È¡µÃä¯ÀÀÆ÷µÄuserAgent×Ö·û´®  
	var isOpera = userAgent.indexOf("Opera") > -1; //ÅÐ¶ÏÊÇ·ñOperaä¯ÀÀÆ÷  
	var isIE = userAgent.indexOf("compatible") > -1
			&& userAgent.indexOf("MSIE") > -1 && !isOpera; //ÅÐ¶ÏÊÇ·ñIEä¯ÀÀÆ÷  
	var isEdge = userAgent.indexOf("Windows NT 6.1; Trident/7.0;") > -1
			&& !isIE; //ÅÐ¶ÏÊÇ·ñIEµÄEdgeä¯ÀÀÆ÷  
	var isFF = userAgent.indexOf("Firefox") > -1; //ÅÐ¶ÏÊÇ·ñFirefoxä¯ÀÀÆ÷  
	var isSafari = userAgent.indexOf("Safari") > -1
			&& userAgent.indexOf("Chrome") == -1; //ÅÐ¶ÏÊÇ·ñSafariä¯ÀÀÆ÷  
	var isChrome = userAgent.indexOf("Chrome") > -1
			&& userAgent.indexOf("Safari") > -1; //ÅÐ¶ÏChromeä¯ÀÀÆ÷  

	if (isIE) {
		var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
		reIE.test(userAgent);
		var fIEVersion = parseFloat(RegExp["$1"]);
		if (fIEVersion == 7) {
			return "IE7";
		} else if (fIEVersion == 8) {
			return "IE8";
		} else if (fIEVersion == 9) {
			return "IE9";
		} else if (fIEVersion == 10) {
			return "IE10";
		} else if (fIEVersion == 11) {
			return "IE11";
		} else {
			return "0"
		}//IE°æ±¾¹ýµÍ  
	}//isIE end  

	if (isFF) {
		return "FF";
	}
	if (isOpera) {
		return "Opera";
	}
	if (isSafari) {
		return "Safari";
	}
	if (isChrome) {
		return "Chrome";
	}
	if (isEdge) {
		return "Edge";
	}
}//myBrowser() end  

//ÅÐ¶ÏÊÇ·ñÊÇIEä¯ÀÀÆ÷  
function isIE() {
	var userAgent = navigator.userAgent; //È¡µÃä¯ÀÀÆ÷µÄuserAgent×Ö·û´®  
	var isIE = userAgent.indexOf("compatible") > -1
			&& userAgent.indexOf("MSIE") > -1 && !isOpera; //ÅÐ¶ÏÊÇ·ñIEä¯ÀÀÆ÷  
	if (isIE) {
		return "1";
	} else {
		return "-1";
	}
}

//ÅÐ¶ÏÊÇ·ñÊÇIEä¯ÀÀÆ÷£¬°üÀ¨Edgeä¯ÀÀÆ÷  
function IEVersion() {
	var userAgent = navigator.userAgent; //È¡µÃä¯ÀÀÆ÷µÄuserAgent×Ö·û´®  
	var isIE = userAgent.indexOf("compatible") > -1
			&& userAgent.indexOf("MSIE") > -1 && !isOpera; //ÅÐ¶ÏÊÇ·ñIEä¯ÀÀÆ÷  
	var isEdge = userAgent.indexOf("Windows NT 6.1; Trident/7.0;") > -1
			&& !isIE; //ÅÐ¶ÏÊÇ·ñIEµÄEdgeä¯ÀÀÆ÷  
	if (isIE) {
		var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
		reIE.test(userAgent);
		var fIEVersion = parseFloat(RegExp["$1"]);
		if (fIEVersion == 7) {
			return "IE7";
		} else if (fIEVersion == 8) {
			return "IE8";
		} else if (fIEVersion == 9) {
			return "IE9";
		} else if (fIEVersion == 10) {
			return "IE10";
		} else if (fIEVersion == 11) {
			return "IE11";
		} else {
			return "0"
		}//IE°æ±¾¹ýµÍ  
	} else if (isEdge) {
		return "Edge";
	} else {
		return "-1";//·ÇIE  
	}
}