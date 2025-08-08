package com.example.springboot3demo.advice;


import com.example.springboot3demo.ApiScope;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

@ControllerAdvice
@Slf4j
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true; // 对所有响应生效
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        //如果是商户的请求，响应头中需增加签名
        if ( returnType.hasMethodAnnotation(ApiScope.class)) {
            String target = Objects.requireNonNull(returnType.getMethodAnnotation(ApiScope.class)).target();
            if("manage".equals(target)) {
                response.getHeaders().add("X-permission", "admin");

            }else{
                response.getHeaders().add("X-permission", "common user");
            }
        }
        return body;
    }
}
