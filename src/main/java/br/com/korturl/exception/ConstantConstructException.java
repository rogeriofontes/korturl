/*
 * 
 */
package br.com.korturl.exception;


/**
 * The Class ConstantConstructException.
 */
public class ConstantConstructException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7265202866980320085L;
	
	/**
	 * Instantiates a new constant construct exception.
	 *
	 * @param message the message
	 */
	public ConstantConstructException(String message) {
        super(message);
    }

}
