package uo.ri.cws.application.business.util;

import uo.ri.cws.application.business.BusinessException;

/**
 * Titulo: Interfaz Command
 *
 * @author Omar Teixeira González, UO281847
 * @version 14 oct 2022
 * @param <T>
 */
public interface Command<T> {
	/**
	 * Método execute
	 * 
	 * @return T
	 * @throws BusinessException
	 */
	T execute() throws BusinessException;
}
