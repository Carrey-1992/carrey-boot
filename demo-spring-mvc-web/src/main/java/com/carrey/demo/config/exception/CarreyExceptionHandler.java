package com.carrey.demo.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Conway
 * @className CarreyExceptionHandler
 * @description
 * @date 2020/12/3 上午11:43
 */
@Slf4j
@ControllerAdvice
public class CarreyExceptionHandler {


    /**
     * 业务异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler({CarreyRefusedException.class})
    @ResponseStatus(HttpStatus.OK)
    public CarreyRefusedInfo refused(CarreyRefusedException ex) {
        log.warn("ServiceException:"+ex.getMessage(),ex);
        return new CarreyRefusedInfo(ExceptionConst.REJECT_ERROR_CODE,ex.getMessage());
    }

    /**
     * 未知异常
     * @param throwable
     * @param request
     * @return
     */
    @ResponseBody
    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CarreyRefusedInfo error(Throwable throwable, HttpServletRequest request) {
        log.error("uri:"+request.getRequestURI()+",errorMsg:"+throwable.getMessage(), throwable);
        return new CarreyRefusedInfo(ExceptionConst.UNKNOWN_ERROR_CODE,throwable.toString());
    }
}
