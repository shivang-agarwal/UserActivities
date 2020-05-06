package com.zenon.exception.handler;

import com.zenon.dto.CustomException;
import com.zenon.exception.ActivityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ActivityNotFoundExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ActivityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    CustomException activityNotFoundException(ActivityNotFoundException exception){
        log.error("[Exception] [ACTIVITY_NOT_FOUND] Message[{}] stacktrace[{}]", exception.getLocalizedMessage(), exception.getStackTrace());
        return new CustomException().setCode(HttpStatus.NOT_FOUND.value()).setMessage(exception.getLocalizedMessage());
    }
}
