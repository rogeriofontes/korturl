package br.com.korturl.constants;

import br.com.korturl.exception.ConstantConstructException;

public class Contants {

	public static final String CLASS_CANNOT_BE_INSTANTIATED = "Class cannot be instantiated";
	
	public static final String URL_IS_NOT_VALID = "Url is not valid!";
	
	public static final String URL_IS_NOT_FOUND = "Url not found!";
	
	public static final String CONT_IS_NOT_FOUND = "Count not found!";

	private Contants() {
		throw new ConstantConstructException(CLASS_CANNOT_BE_INSTANTIATED);
	}
}
