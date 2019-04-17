import axios from 'axios';
import {clearAllCookie, getToken} from '@/utils/auth';
import {Message} from 'element-ui';

// create an axios instance
const service = axios.create({
  baseURL: '',// process.env.BASE_API, // api的base_url
  timeout: 5000 // request timeout
});

//console.log(process.env.BASE_API);

// request interceptor
service.interceptors.request.use(
    config => {
      if(config.responseType == 'blob'){
        config.timeout = 180000;
      }
      // Do something before request is sent
      // set accessToken with request header
      config.headers['Authorization'] = getToken();
      // fixed GET request method caching problem
      config.headers['Cache-Control'] = 'no-cache';
      config.headers['Pragma'] = 'no-cache';
      return config
    }, error => {
      // Do something with request error
      console.log(error);// for debug
      Promise.reject(error)
    });

// respone interceptor
service.interceptors.response.use(
    (response) => {
      if(response.config.responseType == 'blob'){
        if(!response.data){
          return;
        }
        let blob = new Blob([response.data]);
        let fileName = decodeURIComponent(response.headers["filename"]);
        if ('download' in document.createElement('a')) { // 非IE下载
          let url = window.URL.createObjectURL(blob)
          let link = document.createElement('a')
          link.style.display = 'none';
          link.href = url;
          link.setAttribute('download', fileName)
          document.body.appendChild(link)
          link.click();
          document.body.removeChild(link); //下载完成移除元素
          window.URL.revokeObjectURL(url); //释放掉blob对象
          return;
        } else { // IE10+下载
          window.navigator.msSaveOrOpenBlob(blob, fileName)
          return;
        }
      }
      return response
    },
    (error) => {

      let tipError = false;
      // TODO Reservation processing error response
      if (error && error.response) {
        switch (error.response.status) {
          case 400:
            console.log(error);
            tipError = false;
            break;
          case 401:
            console.log(error);
            // authorization require
            clearAllCookie();
            location.reload();
            tipError = false;
            break;
          case 403:
            console.log(error);
            tipError = false;
            break;
          case 404:
            console.log(error);
            tipError = false;
            break;
          case 405:
            console.log(error);
            tipError = false;
            break;
          case 409:
            console.log(error);
            tipError = false;
            break;
          case 422:
            console.log(error);
            tipError = false;
            break;
          case 500:
            error.message = '服务器内部错误';
            console.log(error);
            tipError = true;
            break;
          default:
            error.message = '服务器内部错误';
            console.log(error);
            tipError = true;
            break;
        }
      }
      if (tipError) {
        Message({
          message: error.message,
          type: 'error',
          duration: 5 * 1000
        });
      }

      return Promise.reject(error)
    });

export default service
