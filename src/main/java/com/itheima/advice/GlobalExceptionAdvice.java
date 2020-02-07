package com.itheima.advice;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    public String deniedExceptionAdvice(AccessDeniedException e){
        //return "forward:/403.jps";
        return "redirect:/403.jsp";
    }
}
