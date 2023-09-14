package uo.ri.cws.application.business.util;

import uo.ri.cws.application.business.BusinessException;

/**
 * Titulo: Interfaz Command
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 14 oct 2022
 * @param <T>
 */
public interface Command<T> {
	/**
	 * M�todo execute
	 * 
	 * @return T
	 * @throws BusinessException
	 */
	T execute() throws BusinessException;
}
