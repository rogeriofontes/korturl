/*
 * 
 */
package br.com.korturl.constants;

import br.com.korturl.exception.ConstantConstructException;


/**
 * The Class Contants.
 */
public class Contants {

	/** The Constant CLASS_CANNOT_BE_INSTANTIATED. */
	public static final String CLASS_CANNOT_BE_INSTANTIATED = "Class cannot be instantiated";
	
	/** The Constant URL_IS_NOT_VALID. */
	public static final String URL_IS_NOT_VALID = "Url is not valid!";
	
	/** The Constant URL_FOUNDED. */
	public static final String URL_FOUNDED = "Url is exists!";
	
	/** The Constant URL_IS_NOT_FOUND. */
	public static final String URL_IS_NOT_FOUND = "Url not found!";
	
	/** The Constant CONT_IS_NOT_FOUND. */
	public static final String CONT_IS_NOT_FOUND = "Count not found!";

	/**
	 * Instantiates a new contants.
	 */
	private Contants() {
		throw new ConstantConstructException(CLASS_CANNOT_BE_INSTANTIATED);
	}
}
