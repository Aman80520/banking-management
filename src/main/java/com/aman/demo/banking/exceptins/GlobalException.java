package com.aman.demo.banking.exceptins;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalException {

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> resourcenotExcep(ResourceNotFoundException ex,WebRequest web){
		ErrorResponse er=new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ErrorResponse>(er,HttpStatus.NOT_FOUND);
		
	}
}
