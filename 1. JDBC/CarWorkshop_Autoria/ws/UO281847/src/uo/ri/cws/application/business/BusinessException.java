package uo.ri.cws.application.business;

/**
 * Titulo: Clase BusinessException
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class BusinessException extends Exception {
	/**
	 * Constante serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor BusinessException
	 */
	public BusinessException() {
	}

	/**
	 * Constructor BusinessException
	 * 
	 * @param message
	 */
	public BusinessException(String message) {
		super(message);
	}

	/**
	 * Constructor BusinessException
	 * 
	 * @param cause
	 */
	public BusinessException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor BusinessException
	 * 
	 * @param message
	 * @param cause
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

}
