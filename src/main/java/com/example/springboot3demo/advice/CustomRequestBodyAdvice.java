package com.example.springboot3demo.advice;


import com.example.springboot3demo.ApiScope;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class CustomRequestBodyAdvice extends RequestBodyAdviceAdapter {



    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }


    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        byte[] payload = org.springframework.util.StreamUtils.copyToByteArray(inputMessage.getBody());
        log.debug("收到请求：{}， JSON：{}", parameter.getMethodAnnotation(PostMapping.class).value()[0], new String(payload));


        //如果是管理接口，header必须有admin头，值必须是admin
        if ( parameter.hasMethodAnnotation(ApiScope.class)) {
            String target = Objects.requireNonNull(parameter.getMethodAnnotation(ApiScope.class)).target();
            if("manage".equals(target)){
                String admin = inputMessage.getHeaders().getFirst("admin");
                if(!"admin".equals(admin)){
                    throw new RuntimeException("user has no permission");
                }
            }
        }

        return new HttpInputMessage() {
            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }
            @Override
            public InputStream getBody() throws IOException {
                // 使用原始数据构建为 ByteArrayInputStream
                return new ByteArrayInputStream(payload);
            }
        };

    }
}
