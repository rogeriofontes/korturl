package br.com.korturl.resources.exceptions;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionsHandlerAdvice {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(ExceptionsHandlerAdvice.class);
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleCustomerAlreadyExistsException(AlreadyExistsException e) {
		LOGGER.info("RestController Exception: " + e.getMessage());
		return e.getMessage();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { EmptyResultDataAccessException.class, EntityNotFoundException.class })
	public void handleNotFound() {
	}

}
