/**
 * Created by jiachenpan on 16/11/18.
 */

export function parseTime(time, cFormat) {
  if (arguments.length === 0) {
    return null
  }
  const format = cFormat || '{y}-{m}-{d} {h}:{i}:{s}'
  let date
  if (typeof time === 'object') {
    date = time
  } else {
    if (('' + time).length === 10) time = parseInt(time) * 1000
    date = new Date(time)
  }
  const formatObj = {
    y: date.getFullYear(),
    m: date.getMonth() + 1,
    d: date.getDate(),
    h: date.getHours(),
    i: date.getMinutes(),
    s: date.getSeconds(),
    a: date.getDay()
  }
  const time_str = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
    let value = formatObj[key]
    if (key === 'a') return ['一', '二', '三', '四', '五', '六', '日'][value - 1]
    if (result.length > 0 && value < 10) {
      value = '0' + value
    }
    return value || 0
  })
  return time_str
}

export function formatTime(time, option) {
  time = +time * 1000
  const d = new Date(time)
  const now = Date.now()

  const diff = (now - d) / 1000

  if (diff < 30) {
    return '刚刚'
  } else if (diff < 3600) { // less 1 hour
    return Math.ceil(diff / 60) + '分钟前'
  } else if (diff < 3600 * 24) {
    return Math.ceil(diff / 3600) + '小时前'
  } else if (diff < 3600 * 24 * 2) {
    return '1天前'
  }
  if (option) {
    return parseTime(time, option)
  } else {
    return d.getMonth() + 1 + '月' + d.getDate() + '日' + d.getHours() + '时' + d.getMinutes() + '分'
  }
}

// 格式化时间
export function getQueryObject(url) {
  url = url == null ? window.location.href : url
  const search = url.substring(url.lastIndexOf('?') + 1)
  const obj = {}
  const reg = /([^?&=]+)=([^?&=]*)/g
  search.replace(reg, (rs, $1, $2) => {
    const name = decodeURIComponent($1)
    let val = decodeURIComponent($2)
    val = String(val)
    obj[name] = val
    return rs
  })
  return obj
}

/**
 *get getByteLen
 * @param {Sting} val input value
 * @returns {number} output value
 */
export function getByteLen(val) {
  let len = 0
  for (let i = 0; i < val.length; i++) {
    if (val[i].match(/[^\x00-\xff]/ig) != null) {
      len += 1
    } else {
      len += 0.5
    }
  }
  return Math.floor(len)
}

export function cleanArray(actual) {
  const newArray = []
  for (let i = 0; i < actual.length; i++) {
    if (actual[i]) {
      newArray.push(actual[i])
    }
  }
  return newArray
}

export function param(json) {
  if (!json) return ''
  return cleanArray(Object.keys(json).map(key => {
    if (json[key] === undefined) return ''
    return encodeURIComponent(key) + '=' +
      encodeURIComponent(json[key])
  })).join('&')
}

export function param2Obj(url) {
  const search = url.split('?')[1]
  if (!search) {
    return {}
  }
  return JSON.parse('{"' + decodeURIComponent(search).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g, '":"') + '"}')
}

export function html2Text(val) {
  const div = document.createElement('div')
  div.innerHTML = val
  return div.textContent || div.innerText
}

export function objectMerge(target, source) {
  /* Merges two  objects,
     giving the last one precedence */

  if (typeof target !== 'object') {
    target = {}
  }
  if (Array.isArray(source)) {
    return source.slice()
  }
  for (const property in source) {
    if (source.hasOwnProperty(property)) {
      const sourceProperty = source[property]
      if (typeof sourceProperty === 'object') {
        target[property] = objectMerge(target[property], sourceProperty)
        continue
      }
      target[property] = sourceProperty
    }
  }
  return target
}

export function scrollTo(element, to, duration) {
  if (duration <= 0) return
  const difference = to - element.scrollTop
  const perTick = difference / duration * 10
  setTimeout(() => {
    console.log(new Date())
    element.scrollTop = element.scrollTop + perTick
    if (element.scrollTop === to) return
    scrollTo(element, to, duration - 10)
  }, 10)
}

export function toggleClass(element, className) {
  if (!element || !className) {
    return
  }
  let classString = element.className
  const nameIndex = classString.indexOf(className)
  if (nameIndex === -1) {
    classString += '' + className
  } else {
    classString = classString.substr(0, nameIndex) + classString.substr(nameIndex + className.length)
  }
  element.className = classString
}

export const pickerOptions = [{
  text: '今天',
  onClick(picker) {
    const end = new Date()
    const start = new Date(new Date().toDateString())
    end.setTime(start.getTime())
    picker.$emit('pick', [start, end])
  }
}, {
  text: '最近一周',
  onClick(picker) {
    const end = new Date(new Date().toDateString())
    const start = new Date()
    start.setTime(end.getTime() - 3600 * 1000 * 24 * 7)
    picker.$emit('pick', [start, end])
  }
}, {
  text: '最近一个月',
  onClick(picker) {
    const end = new Date(new Date().toDateString())
    const start = new Date()
    start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
    picker.$emit('pick', [start, end])
  }
}, {
  text: '最近三个月',
  onClick(picker) {
    const end = new Date(new Date().toDateString())
    const start = new Date()
    start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
    picker.$emit('pick', [start, end])
  }
}]

export function getTime(type) {
  if (type === 'start') {
    return new Date().getTime() - 3600 * 1000 * 24 * 90
  } else {
    return new Date(new Date().toDateString())
  }
}

export function debounce(func, wait, immediate) {
  let timeout, args, context, timestamp, result

  const later = function () {
    // 据上一次触发时间间隔
    const last = +new Date() - timestamp

    // 上次被包装函数被调用时间间隔last小于设定时间间隔wait
    if (last < wait && last > 0) {
      timeout = setTimeout(later, wait - last)
    } else {
      timeout = null
      // 如果设定为immediate===true，因为开始边界已经调用过了此处无需调用
      if (!immediate) {
        result = func.apply(context, args)
        if (!timeout) context = args = null
      }
    }
  }

  return function (...args) {
    context = this
    timestamp = +new Date()
    const callNow = immediate && !timeout
    // 如果延时不存在，重新设定延时
    if (!timeout) timeout = setTimeout(later, wait)
    if (callNow) {
      result = func.apply(context, args)
      context = args = null
    }

    return result
  }
}

export function deepClone(source) {
  if (!source && typeof source !== 'object') {
    throw new Error('error arguments', 'shallowClone')
  }
  const targetObj = source.constructor === Array ? [] : {}
  for (const keys in source) {
    if (source.hasOwnProperty(keys)) {
      if (source[keys] && typeof source[keys] === 'object') {
        targetObj[keys] = source[keys].constructor === Array ? [] : {}
        targetObj[keys] = deepClone(source[keys])
      } else {
        targetObj[keys] = source[keys]
      }
    }
  }
  return targetObj
}

export function guid() {
  function S4() {
    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
  }

  return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
}

export function GetDateDiff(startTime, endTime, diffType) {
  //将xxxx-xx-xx的时间格式，转换为 xxxx/xx/xx的格式
  startTime = startTime.replace(/\-/g, "/");
  endTime = endTime.replace(/\-/g, "/");
  //将计算间隔类性字符转换为小写
  diffType = diffType.toLowerCase();
  var sTime = new Date(startTime); //开始时间
  var eTime = new Date(endTime); //结束时间
  //作为除数的数字
  var timeType = 1;
  switch (diffType) {
    case "second":
      timeType = 1000;
      break;
    case "minute":
      timeType = 1000 * 60;
      break;
    case "hour":
      timeType = 1000 * 3600;
      break;
    case "day":
      timeType = 1000 * 3600 * 24;
      break;
    default:
      break;
  }
  return parseInt((eTime.getTime() - sTime.getTime()) / parseInt(timeType));
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
export function MultidimensionalData(datalist) {
  // 拿到第一层方法
  function getOne(data) {
    var one = [],list
    data.map(function(obj, idx) {
      var flag = 0
      data.map(function(o, i) {
        if (obj.parentId === o.id) {
          flag++
        }
        return o
      })
      if (flag === 0) {
        list = cloneObj(obj)
        list.children = []
        one.push(list)
      }
      return obj
    })
    return one
  }
  // 递归方法
  function fns(one, data) {
    one.map(function(obj, idx) {
      data.map(function(o, i) {
        if (obj.id == o.parentId) {
          var list = cloneObj(o)
          list.children = []
          obj.children.push(list)
          fns(obj.children, data)
        }
      })
    })
  }
  try {
    var middle = [].concat(datalist)
    var one = getOne(middle)
    fns(one, middle)
  } catch (e) {
    console.warn('no data for exit')
  }

  return one
}

export function OneDimensionalDataVariableMultidimensionalData(datalist){
// 拿到第一层方法
function getOne(data){
  var one = [],list;
  data.map(function(obj,idx){
      var flag = 0;
      data.map(function(o,i){
          if(obj.parentId==o.id){
              flag++
          }
          return o
      })
      if(flag==0){
          list = cloneObj(obj)
          list.children = [];
          one.push(list)
      }
      return obj
  })
  return one;
}
//递归方法
function fns(one,data){ 
  one.map(function(obj,idx){ 
      data.map(function(o,i){ 
          if(obj.id==o.parentId){ 
              var list = cloneObj(o);
              list.children = [];
              // obj.name = obj.name.substr(0,obj.name.indexOf('(')==-1?obj.name.length:obj.name.indexOf('(')) 
              obj.children.push(list) 
          }
      })
      fns(obj.children,data)
  })
}
var middle = [].concat(datalist)
var one = getOne(middle); 
fns(one,middle)
return one
}
