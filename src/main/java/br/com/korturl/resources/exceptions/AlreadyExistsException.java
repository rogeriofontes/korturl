/*
 * 
 */
package br.com.korturl.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class AlreadyExistsException.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Resource not found.")
public class AlreadyExistsException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -147985746273359861L;
	
	/**
	 * Instantiates a new URL exception.
	 *
	 * @param message the message
	 */
	public AlreadyExistsException(String message) {
        super(message);
    }
}
