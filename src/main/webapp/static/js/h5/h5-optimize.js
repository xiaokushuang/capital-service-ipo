/**
 * h5页面优化专用公共js
 */
// 设置整体页面标准
var handlerOrientationChange = function() {
	var width = (window.innerWidth <= 320) ? 320
			: ((window.innerWidth >= 640) ? 640 : window.innerWidth);
	var fontSize = 100 * (width / 320);
	document.documentElement.style.fontSize = fontSize + "px";
};
window.onresize = handlerOrientationChange;
setTimeout(function() {
	handlerOrientationChange();
}, 0);