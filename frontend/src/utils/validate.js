/**
 * Created by jiachenpan on 16/11/18.
 */

export function isvalidUsername(str) {
  const valid_map = ['admin', 'editor']
  //return valid_map.indexOf(str.trim()) >= 0
  return true;
}

/* 合法uri*/
export function validateURL(textval) {
  const urlregex = /^(https?|ftp):\/\/([a-zA-Z0-9.-]+(:[a-zA-Z0-9.&%$-]+)*@)*((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]?)(\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}|([a-zA-Z0-9-]+\.)*[a-zA-Z0-9-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(:[0-9]+)*(\/($|[a-zA-Z0-9.,?'\\+&%$#=~_-]+))*$/
  return urlregex.test(textval)
}

/* 小写字母*/
export function validateLowerCase(str) {
  const reg = /^[a-z]+$/
  return reg.test(str)
}

/* 大写字母*/
export function validateUpperCase(str) {
  const reg = /^[A-Z]+$/
  return reg.test(str)
}

/* 大小写字母*/
export function validateAlphabets(str) {
  const reg = /^[A-Za-z]+$/
  return reg.test(str)
}

/**
 * validate email.
 *
 * @param email
 * @returns {boolean}
 */
export function _validateEmail(email) {
  const re = /^[^@\s]+@(?:[^@\s.]+)(?:\.[^@\s.]+)+$/;
  return re.test(email)
}

/**
 * validate cellphone
 * @param cellphone
 * @returns {boolean}
 */
export function _validatePhone(cellphone) {
  const regex = /^(1[3578][0-9]{9}|(\d{3,4}-)\d{7,8}(-\d{1,4})?)$/;
  return regex.test(cellphone);
}

/**
 * validate excel
 * @param fileName
 * @returns {boolean}
 */
export function _validateExcel(fileName) {
  const regex = /(.xls|.xlsx)$/;
  return regex.test(fileName.toLowerCase());
}

/**
 * validate word
 * @param fileName
 * @returns {boolean}
 */
export function _validateWord(fileName) {
  const regex = /(.doc|.docx)$/;
  return regex.test(fileName.toLowerCase());
}