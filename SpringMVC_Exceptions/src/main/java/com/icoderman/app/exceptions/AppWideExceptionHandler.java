package com.icoderman.app.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppWideExceptionHandler {

	@ExceptionHandler(IncorrectUserException.class)
	public String incorrectSpittleHandler() {
		return "error/incorrect";
	}

}
