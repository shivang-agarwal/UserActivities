package com.zenon.exception.handler;

import com.zenon.dto.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.hibernate.exception.ConstraintViolationException;

import java.util.Objects;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    CustomException constraintViolationException(ConstraintViolationException exception){
        return new CustomException().setCode(HttpStatus.BAD_GATEWAY.value()).setMessage(exception.getConstraintName());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    CustomException exception(Exception exception){
        CustomException standardException = new CustomException().setCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).setMessage(exception.getLocalizedMessage());
        Exception nestedException = (Exception) exception.getCause();
        log.error("[Exception] Message[{}] stacktrace[{}]", exception.getLocalizedMessage(), exception.getStackTrace());
        if(Objects.nonNull(nestedException)){
            standardException.setNestedErrorMessage(nestedException.getLocalizedMessage());
        }
        return standardException;
    }
}
