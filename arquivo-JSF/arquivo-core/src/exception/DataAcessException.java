package exception;

import org.apache.log4j.Logger;

public class DataAcessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6683707284036027679L;
	private Logger logger = Logger.getLogger(getClass());

	public DataAcessException() {
		super();

		logger.error(super.getCause());
	}

	public DataAcessException(String message, Throwable cause) {
		super(message, cause);
		logger.error(message, cause);
	}

	public DataAcessException(String message) {
		super(message);
		logger.error(message);
	}

	public DataAcessException(Throwable cause) {
		super(cause);
		logger.error(cause);

	}

}
