package br.com.korturl.exception;

public class ConstantConstructException extends RuntimeException {

	private static final long serialVersionUID = 7265202866980320085L;
	
	public ConstantConstructException(String message) {
        super(message);
    }

}
