package exception;


public class RegraNegocioException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6263054196938250940L;
	

	public RegraNegocioException() {
		super();
		
	}

	public RegraNegocioException(String message) {
		super(message);
		
	}

	public RegraNegocioException(Throwable cause) {
		super(cause);
	
	}

	public RegraNegocioException(String message, Throwable cause) {
		super(message, cause);
	
	}

}
