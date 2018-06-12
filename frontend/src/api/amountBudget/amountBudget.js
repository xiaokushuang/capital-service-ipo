import request from '@/utils/request'

export function moneyDataGet(query) {
    return request({
        url: '/htrt/transactionMoney/transactionMoneyData',
        method: 'post',
        //controller接收用 @requestbody
        data: query
    })
}

export function amountBudgetInsert(query) {
    return request({
        url: '/htrt/transactionMoney/amountBudgetInsert',
        method: 'post',
        //后台正常接收参数
        data: query
    })
}

export function amountBudgetUpdate(query) {
    return request({
        url: '/htrt/transactionMoney/amountBudgetUpdate',
        method: 'post',
        data: query
    })
}

export function amountBudgetDelete(query) {
    return request({
        url: '/htrt/transactionMoney/amountBudgetDelete',
        method: 'post',
        data: query
    })
}
export function getconditionRecords(query) {
    return request({
        url: '/htrt/transactionMoney/getconditionRecords',
        method: 'post',
        params: query
    })
}