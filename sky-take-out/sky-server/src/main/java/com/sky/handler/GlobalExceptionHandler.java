package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Global Exception Handler
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Exception Handler
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("Error Message：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * SQL error
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error("Error Message：{}", ex.getMessage());

        String message = ex.getMessage();

        if(message.contains("Duplicate entry")){
            return Result.error("Duplicate entry");
        }
        else{
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }

    }

}
