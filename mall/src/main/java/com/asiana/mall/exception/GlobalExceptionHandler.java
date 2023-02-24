package com.asiana.mall.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
 
    // 404예외처리 핸들러
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404(NoHandlerFoundException e){
        System.out.println("error!!!!! = " );
        System.out.println(e);
        return "error";
    }
    // 데이터베이스오류
    @ExceptionHandler(DataAccessException.class)
    public String handleDataAccessException(DataAccessException e) {
        System.out.println("error!!!!! = " );
        System.out.println(e);
        return "error";
    }
    // 500에러처리
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        System.out.println("error!!!!! = " );
        System.out.println(e);
        return "error";
    }
}
