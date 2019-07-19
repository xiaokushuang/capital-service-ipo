import Cookies from 'js-cookie'

const Authorization = 'Authorization';
const refreshToken = 'refreshToken';
const PermissionsKey = 'permissions';
export function getToken() {
  return Cookies.get(Authorization)
}

export function setToken(token) {
  return Cookies.set(Authorization, token)
}

export function getPermissions() {
  return Cookies.get(PermissionsKey)
}

export function setPermissions(permissions) {
  return Cookies.set(PermissionsKey, permissions)
}

export function removePermissions() {
  return Cookies.remove(PermissionsKey)
}


export function removeToken() {
  return Cookies.remove(Authorization)
}

export function getrefreshToken() {
  return Cookies.get(refreshToken)
}

export function setrefreshToken(token) {
  return Cookies.set(refreshToken, token)
}

export function removerefreshToken() {
  return Cookies.remove(refreshToken)
}

export function clearAllCookie() {
  removeToken();
  removerefreshToken();
  removePermissions();
}

//清空dialog 所用方法，（需要被清空的对象，对象中string值所需替换，对象中boolean值所需替换）
export const objectKeyToEmpty = (temParam,str,bo)=>{
  let temParamKey = Object.keys(temParam);
  temParamKey.map(function(obj,idx){
      if(typeof temParam[obj]=='string'){
          temParam[obj] =str
      }
      if(typeof temParam[obj]=='boolean'){
          temParam[obj] =bo
      }
  })
}

/**
 * iframe传值
 *
 * @param win <Dom>
 * 		iframe.contentWindow or window.parent 对象
 * @param func <String>
 * 		要执行的方法名
 * @param param <Array>
 * 		传给方法的参数
 * */
export function iframeDoMessage(win,func,param){
  debugger;
	win.postMessage({
		type:'function',
		func:func,
		param:param
	},'*')
}
