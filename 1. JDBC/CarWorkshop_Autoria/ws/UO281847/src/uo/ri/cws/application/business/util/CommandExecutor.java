package uo.ri.cws.application.business.util;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.Jdbc;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.persistence.PersistenceException;

/**
 * Titulo: Clase CommandExecutor
 *
 * @author Omar Teixeira González, UO281847
 * @version 14 oct 2022
 */
public class CommandExecutor {
	/**
	 * Método execute
	 * 
	 * @param <T>
	 * @param cmd
	 * @return T
	 * @throws BusinessException
	 */
	public <T> T execute(Command<T> cmd) throws BusinessException {
		Connection c = null;
		try {
			try {
				c = Jdbc.createThreadConnection();
				c.setAutoCommit(false);

				T res = cmd.execute();

				c.commit();

				return res;

			} catch (BusinessException e) {
				c.rollback();
				throw e;
			}
		} catch (SQLException | PersistenceException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}

}
