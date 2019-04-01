import axios from 'axios';
import {clearAllCookie, getToken} from '@/utils/auth';
import {Message} from 'element-ui';
import store from '../store'

// create an axios instance
const service = axios.create({
  baseURL: '',// process.env.BASE_API, // api的base_url
  timeout: 5000 // request timeout
});

//console.log(process.env.BASE_API);

// request interceptor
service.interceptors.request.use( 
    config => {
      // Do something before request is sent
      // set accessToken with request header
      config.headers['Authorization'] = store.state.app.token;
      config.headers['X-Tenant-Info'] = store.state.app.info;
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
