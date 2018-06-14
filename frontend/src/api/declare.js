import request from '@/utils/request'

export function getZtree(param) {
    return request({
      url: `/ipo/declare/getZtree`,//`/ipo/financeStatistics/getZtree`,
      method: 'post' ,
      data:param  
      // params: {access_token}
    })
}
export function getData(param) {
  return request({
    url: `/ipo/declare/getHelperData`,//`/ipo/financeStatistics/getZtree`,
    method: 'post' ,
    data:param  
    // params: {access_token}
  })
}
export function getFileData(param) {
  return request({
    url: `/ipo/declare/getTempalteFile`,//`/ipo/financeStatistics/getZtree`,
    method: 'post' , 
    params: {
      typeId : param.typeId,
      typeName:param.typeName,
      declareTypeNo : param.declareTypeNo
    }
  })
}
export function TableData(param) {
  return request({
    url: `/ipo/declare/TableData`, 
    method: 'post' ,
    data:param   
  })
}