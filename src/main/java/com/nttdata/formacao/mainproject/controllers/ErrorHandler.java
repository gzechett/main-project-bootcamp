package com.nttdata.formacao.mainproject.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView error(Exception e){
        ModelAndView mav = new ModelAndView("error");
        System.out.println("\n\n Error: " + e.getMessage());
        return mav;
    }
}
