
export const checked =  {
    data(){
        /**
         * 常规
         * 错误信息一览表
         */
        const message_C = {
            required:'必填项',
            number:'必须填写数字',
            email:'必须填写正确的邮箱'
        }
        
        var checkNull = (message)=>{
            return (rule, value, callback) => {//自定义校验规则-手机
                        if(value){
                            if(value.trim()){
                                callback();
                            }else{
                                return callback(new Error(message));
                            }
                        }else{
                            return callback(new Error(message));
                        }
                    };
        };
        
    },
   
} 