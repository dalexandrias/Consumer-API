package com.application.exception.advice;

import com.application.dto.ErrorDTO;
import com.application.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ControllerAdvice(basePackages = "com.application.controller")
public class UserControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDTO handlerUserNotFound(UserNotFoundException userNotFoundException) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setMessage("Usuário não encontrado.");
        errorDTO.setTimestamp(new Date());
        return errorDTO;
    }
}
