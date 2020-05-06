package com.zenon.exception.handler;

import com.zenon.dto.CustomException;
import com.zenon.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class UserNotFoundExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    CustomException userNotFoundException(UserNotFoundException exception){
        log.error("[Exception] [INVALID_LOGIN_ATTEMPT] username [{}] Message[{}] stacktrace[{}]",exception.getUsername(), exception.getLocalizedMessage(), exception.getStackTrace());
        return new CustomException().setCode(HttpStatus.NOT_FOUND.value()).setMessage(exception.getLocalizedMessage());
    }
}