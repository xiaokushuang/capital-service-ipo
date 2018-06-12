import request from '@/utils/request'

export function getZtree(param) {
    return request({
      url: `/ipo/declare/getZtree`,//`/ipo/financeStatistics/getZtree`,
      method: 'post' ,
      data:param  
      // params: {access_token}
    })
}

export function TableData(param) {
  return request({
    url: `/ipo/declare/TableData`, 
    method: 'post' ,
    data:param   
  })
}