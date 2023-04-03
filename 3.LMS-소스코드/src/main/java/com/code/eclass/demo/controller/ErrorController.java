package com.code.eclass.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorController {
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e,Model model) {
		
		model.addAttribute("message", "에러가 발생하였습니다 잠시후에 다시 시도해주시기 바랍니다.");
		System.out.println("에러메세지 : "+ e.getMessage());
		return "error";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public String handle404Exception(Model model) {
		
		model.addAttribute("message", "해당 페이지를 찾을 수 없습니다 잠시후에 다시 시도해주시기 바랍니다.");
		return "error";
	}
}
