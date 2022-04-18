package com.liqian.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
* 异常处理
* */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler
    public Result exception(Exception e){
        log.error("异常信息：{}",e.getMessage());
        System.out.println("哈哈哈");
        return Result.getFailure().setData(e.getMessage());
    }
}
