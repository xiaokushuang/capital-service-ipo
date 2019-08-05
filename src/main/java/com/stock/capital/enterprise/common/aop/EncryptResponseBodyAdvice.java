package com.stock.capital.enterprise.common.aop;

import com.google.common.base.Charsets;
import com.google.common.collect.Sets;
import com.stock.capital.enterprise.common.controller.ContentNegotiatingErrorController;
import com.stock.capital.enterprise.ipoInterfaceH5.controller.IpoInterfaceController;
import com.stock.core.util.CryptoUtil;
import com.stock.core.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.Set;

@ControllerAdvice
public class EncryptResponseBodyAdvice extends AbstractMappingJacksonResponseBodyAdvice {

    private static final Logger logger = LoggerFactory.getLogger(EncryptResponseBodyAdvice.class);


    private static final String ENCRYPT_KEY = "hNkYmsBUvrTd3C3o";
    private static final String IV = "mLZT7OIx1qOHZaPX";
    private static final int MOD = 13;
    private static final Set<String> WHITE_LIST = Sets.newHashSet();

    static {
        WHITE_LIST.add("/health");
    }


    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
                                           MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
        Method method = returnType.getMethod();
        Class<?> declaringClass = method.getDeclaringClass();
        try {
            logger.info("调用的类:"+declaringClass+" ipoh5使用的类："+IpoInterfaceController.class +"通过forname创建："+Class.forName("com.stock.capital.enterprise.ipoInterfaceH5.controller.IpoInterfaceController"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        非这个Controller都加密
        if (!declaringClass.equals(IpoInterfaceController.class)) {
            if (canEncrypt(request)) {
                String body = JsonUtil.toJson(bodyContainer.getValue());
                String iv = Base64Utils.encodeToString(IV.getBytes(Charsets.UTF_8));
                String encryptKey = Base64Utils.encodeToString(ENCRYPT_KEY.getBytes(Charsets.UTF_8));
                String encryptData = CryptoUtil.encrypt(body, encryptKey, CryptoUtil.AES_CIPHER_ALGORITHM_CBC, iv);
                int start = (encryptData.length() + 32) % MOD;
                encryptData = encryptData.substring(0, start + 1) + ENCRYPT_KEY + IV + encryptData.substring(start + 1);
                bodyContainer.setValue(encryptData);
            }
        }else{
            try {
                logger.info("调用的类:"+declaringClass+" ipoh5使用的类："+IpoInterfaceController.class +"通过forname创建："+Class.forName("com.stock.capital.enterprise.ipoInterfaceH5.controller.IpoInterfaceController"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean canEncrypt(ServerHttpRequest request) {
        for (String uri : WHITE_LIST) {
            AntPathRequestMatcher matcher = new AntPathRequestMatcher(uri);
            ServletServerHttpRequest httpRequest = (ServletServerHttpRequest) request;
            if (matcher.matches(httpRequest.getServletRequest())) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

}
