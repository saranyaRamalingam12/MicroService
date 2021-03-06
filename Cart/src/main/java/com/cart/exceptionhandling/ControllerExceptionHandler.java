package com.cart.exceptionhandling;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@ControllerAdvice(basePackages = "com.cart")
public class ControllerExceptionHandler 
{
	 @ExceptionHandler({RuntimeException.class,CartNotFoundException.class,ItemNotFoundException.class,UserNotFoundException.class,DataReceivedErrorException.class})
	  public ResponseEntity<ErrorMessage> resourceNotFoundException(RuntimeException ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(
	        HttpStatus.NOT_FOUND.value(),
	        new Date(),
	        ex.getMessage(),
	        request.getDescription(false));	    
	    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

	  }
	 
	 @ExceptionHandler(Exception.class)
	    public final ResponseEntity<ErrorMessage>globalExceptions(Exception ex, WebRequest request) {
		 ErrorMessage message = new ErrorMessage(
			        HttpStatus.INTERNAL_SERVER_ERROR.value(),
			        new Date(),
			        ex.getMessage(),
			        request.getDescription(false));	    
	        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

}


