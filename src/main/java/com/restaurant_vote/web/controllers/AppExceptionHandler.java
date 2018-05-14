package com.restaurant_vote.web.controllers;

import com.restaurant_vote.model.ExceptionInfo;
import com.restaurant_vote.util.exception.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public void handleUnpredictableExceptions(){

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionInfo> handleDataBaseException(RuntimeException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionInfo(request.getRequestURL().toString(),
                ex.getMessage()));
    }

    @ExceptionHandler(value = {NotFoundException.class,IllegalArgumentException.class})
    public ResponseEntity<ExceptionInfo> handleAppExceptions(RuntimeException ex, HttpServletRequest request){
        ExceptionInfo exceptionInfo=new ExceptionInfo();
        exceptionInfo.setUrl(request.getRequestURL().toString());
        exceptionInfo.setMessage(ex.getMessage());
        ResponseEntity.BodyBuilder bodyBuilder;
        if (ex instanceof NotFoundException){
            bodyBuilder=ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
        else {
            bodyBuilder=ResponseEntity.status(HttpStatus.BAD_REQUEST);
            if (exceptionInfo.getMessage()==null) exceptionInfo.setMessage("Bad argument");
        }
        return bodyBuilder.body(exceptionInfo);
    }

    @ExceptionHandler({BadCredentialsException.class,AccessDeniedException.class})
    public ResponseEntity<ExceptionInfo> handleSecurityExceptions(RuntimeException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionInfo(request.getRequestURL().toString(),
                ex.getMessage()));
    }

}
