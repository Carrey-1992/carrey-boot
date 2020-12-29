package com.carrey.demo.config;

import com.carrey.demo.config.exception.CarreyErrorInfo;
import com.carrey.demo.config.exception.CarreyRefusedInfo;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

/**
 * @author Conway
 * @className CarreyResponseBodyAdvice
 * @description
 * @date 2020/12/2 下午5:08
 */
@ControllerAdvice
public class CarreyResponseBodyAdvice extends AbstractMappingJacksonResponseBodyAdvice {

    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer,
                                           MediaType contentType,
                                           MethodParameter returnType,
                                           ServerHttpRequest request,
                                           ServerHttpResponse response) {
        Object value = bodyContainer.getValue();
        if (value instanceof CarreyRefusedInfo) {
            value = CarreyResult.refused((CarreyRefusedInfo)value);
        } else if (value instanceof CarreyErrorInfo) {
            value = CarreyResult.failed((CarreyErrorInfo)value);
        } else {
            value = CarreyResult.success(value);
        }
        bodyContainer.setValue(value);
    }
}
