package com.example.custom_exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The Student does not exists in the system")
	@org.springframework.web.bind.annotation.ExceptionHandler
	public void handleUserNotFoundException(StudentNotFoundException exception) {

		LOGGER.error(exception.getMessage(), exception);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		LOGGER.error(exception.getMessage(), exception);
		return new ResponseEntity<String>(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
				HttpStatus.EXPECTATION_FAILED);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Either server or database is down")
	@ExceptionHandler(value = Exception.class)
	public void handleException(Exception exception) {
		LOGGER.error(exception.getMessage(), exception);
	}
}
