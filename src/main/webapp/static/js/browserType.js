/* 
 * �������ж��������Ϣ 
 * ��д��LittleQiang_w 
 * ���ڣ�2016.1.5 
 * �汾��V1.1 
 */

//�жϵ�ǰ�������  
function BrowserType() {
	var userAgent = navigator.userAgent; //ȡ���������userAgent�ַ���  
	var isOpera = userAgent.indexOf("Opera") > -1; //�ж��Ƿ�Opera�����  
	var isIE = userAgent.indexOf("compatible") > -1
			&& userAgent.indexOf("MSIE") > -1 && !isOpera; //�ж��Ƿ�IE�����  
	var isEdge = userAgent.indexOf("Windows NT 6.1; Trident/7.0;") > -1
			&& !isIE; //�ж��Ƿ�IE��Edge�����  
	var isFF = userAgent.indexOf("Firefox") > -1; //�ж��Ƿ�Firefox�����  
	var isSafari = userAgent.indexOf("Safari") > -1
			&& userAgent.indexOf("Chrome") == -1; //�ж��Ƿ�Safari�����  
	var isChrome = userAgent.indexOf("Chrome") > -1
			&& userAgent.indexOf("Safari") > -1; //�ж�Chrome�����  

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
		}//IE�汾����  
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

//�ж��Ƿ���IE�����  
function isIE() {
	var userAgent = navigator.userAgent; //ȡ���������userAgent�ַ���  
	var isIE = userAgent.indexOf("compatible") > -1
			&& userAgent.indexOf("MSIE") > -1 && !isOpera; //�ж��Ƿ�IE�����  
	if (isIE) {
		return "1";
	} else {
		return "-1";
	}
}

//�ж��Ƿ���IE�����������Edge�����  
function IEVersion() {
	var userAgent = navigator.userAgent; //ȡ���������userAgent�ַ���  
	var isIE = userAgent.indexOf("compatible") > -1
			&& userAgent.indexOf("MSIE") > -1 && !isOpera; //�ж��Ƿ�IE�����  
	var isEdge = userAgent.indexOf("Windows NT 6.1; Trident/7.0;") > -1
			&& !isIE; //�ж��Ƿ�IE��Edge�����  
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
		}//IE�汾����  
	} else if (isEdge) {
		return "Edge";
	} else {
		return "-1";//��IE  
	}
}