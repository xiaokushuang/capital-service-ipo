package com.stock.capital.enterprise.common.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.WebUtils;

import com.google.common.base.Throwables;
import com.stock.core.dto.JsonResponse;
import com.stock.core.exception.ApplicationException;
import com.stock.core.exception.BaseException;
import com.stock.core.util.JsonUtil;

/**
 * 
 * @ClassName: ContentNegotiatingErrorController
 * @Description: boot全局异常
 * @author zhaoyang
 * @date 2018年6月4日
 *
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ContentNegotiatingErrorController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ContentNegotiatingErrorController.class);

    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    private ErrorAttributes errorAttributes;

    @Autowired
    private ErrorViewResolver errorViewResolver;

    @Autowired
    private MessageSource appMessageSource;

    @Override
    public String getErrorPath() {
        return this.serverProperties.getError().getPath();
    }

    /**
     * 
     * HTML请求的异常处理
     *
     * @param request
     * @param response
     * @return
     *
     */
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView error(HttpServletRequest request, HttpServletResponse response) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Throwable exception = this.errorAttributes.getError(requestAttributes);
        HttpStatus status = getStatus(request);
        Map<String, Object> model = this.errorAttributes.getErrorAttributes(requestAttributes, false);
        ModelAndView modelAndView = errorViewResolver.resolveErrorView(request, status, model);
        logException(requestAttributes, exception, request);
        return modelAndView;
    }

    /**
     * 
     * 其他请求的异常处理
     *
     * @param request
     * @param response
     * @return
     *
     */
    @RequestMapping
    @ResponseBody
    public JsonResponse<Object> error(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Throwable exception = this.errorAttributes.getError(requestAttributes);
        logException(requestAttributes, exception, request);
        String errorCode = getErrorCode(exception);
        String errorMessage = getErrorMessage(exception, errorCode, request.getLocale());
        JsonResponse<Object> response = new JsonResponse<>();
        response.setErrorCode(errorCode);
        response.setErrorMsg(errorMessage);
        return response;
    }

    /**
     * 
     * 取得状态码
     *
     * @param request
     * @return
     *
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE);
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    /**
     * 打印错误日志
     */
    private void logException(RequestAttributes requestAttributes, Throwable exception, HttpServletRequest request) {
        if (exception == null) {
            return;
        }
        String errorCode = getErrorCode(exception);
        String errorMessage = getErrorMessage(exception, errorCode, request.getLocale());
        String requestUrl = (String) requestAttributes.getAttribute(WebUtils.ERROR_REQUEST_URI_ATTRIBUTE,
                RequestAttributes.SCOPE_REQUEST);
        String arguments = null;
        try {
            arguments = JsonUtil.toJson(request.getParameterMap());
        } catch (Exception e) {
            // ignore this exception
        }
        if (exception instanceof ApplicationException) {
            logger.warn("Process request url '{}' warn, arguments=[{}], errorCode=[{}], errorMsg=[{}]", requestUrl,
                    arguments, errorCode, errorMessage);
        } else {
            logger.error("Process request url '{}' error, arguments=[{}], errorCode=[{}], errorMsg=[{}], caused by :{}",
                    requestUrl, arguments, errorCode, errorMessage, Throwables.getStackTraceAsString(exception));
        }
    }

    /**
     * 取得异常信息
     *
     * @param exception
     *            异常
     * @param locale
     *            语言环境
     * @return String
     */
    private String getErrorMessage(Throwable exception, String errorCode, Locale locale) {
        String msg;
        if (exception instanceof BaseException) {
            BaseException ex = (BaseException) exception;
            msg = appMessageSource.getMessage(errorCode, ex.getParams(), locale);
            if (errorCode.equals(msg)) {
                msg = ex.getDefaultMessage();
            }
        } else {
            msg = appMessageSource.getMessage(errorCode, null, locale);
        }
        return msg;
    }

    /**
     * 取得异常代码
     *
     * @param exception
     *            异常
     * @return String
     */
    private String getErrorCode(Throwable exception) {
        String errorCode = "error.500";
        if (exception instanceof BaseException) {
            BaseException ex = (BaseException) exception;
            errorCode = ex.getErrorCode();
        } else if (exception instanceof HttpRequestMethodNotSupportedException
                || exception instanceof NoHandlerFoundException) {
            errorCode = "error.404";
        } else if (exception instanceof MaxUploadSizeExceededException) {
            errorCode = "error.file.upload";
        }
        return errorCode;
    }

}
