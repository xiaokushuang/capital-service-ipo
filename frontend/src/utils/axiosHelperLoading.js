import {Loading} from 'element-ui';
import _ from 'lodash';
import {iframeDoMessage} from '@/utils/auth';
let needLoadingRequestCount = 0;
let loading;

function startLoading () {
    // loading = Loading.service({
    //     // lock: true,
    //     // text: '加载中……',
    //     // background: 'rgba(0, 0, 0, 0.7)'
    //     text:'加载中',
    //     spinner:'el-icon-loading',
    //     background:'rgba(0,0,0,0.1)',
    //     body:true
    // })
    iframeDoMessage(window.parent,'addLoading',[]);
}

function endLoading() {
    // loading.close()
    iframeDoMessage(window.parent,'removeLoading',[]);
}

const tryCloseLoading = () => {
    if (needLoadingRequestCount === 0) {
        endLoading();
    }
};

export function showFullScreenLoading () {
    if (needLoadingRequestCount === 0) {
        startLoading();
    }
    needLoadingRequestCount++;
}

export function tryHideFullScreenLoading () {
    if (needLoadingRequestCount <= 0) return;
    needLoadingRequestCount--;
    if (needLoadingRequestCount === 0) {
        _.debounce(tryCloseLoading, 100)()
    }
}