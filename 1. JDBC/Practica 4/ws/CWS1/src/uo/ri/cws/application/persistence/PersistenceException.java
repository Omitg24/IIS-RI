package uo.ri.cws.application.persistence;

/**
 * Titulo: Clase PersistenceException
 *
 * @author Omar Teixeira González, UO281847
 * @version 14 oct 2022
 */
public class PersistenceException extends RuntimeException {
	/**
	 * Constante serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor PersistenceException
	 */
	public PersistenceException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor PersistenceException
	 * @param message
	 */
	public PersistenceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor PersistenceException
	 * @param cause
	 */
	public PersistenceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor PersistenceException	
	 * @param message
	 * @param cause
	 */
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor PersistenceException
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public PersistenceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
}
