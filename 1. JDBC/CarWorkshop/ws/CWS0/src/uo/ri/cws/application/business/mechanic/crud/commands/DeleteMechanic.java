package uo.ri.cws.application.business.mechanic.crud.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uo.ri.cws.application.business.BusinessException;

/**
 * Titulo: Clase DeleteMechanic
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class DeleteMechanic {
	/**
	 * Constante SQL
	 */
	private static final String SQL = "delete from TMechanics where id = ?";
	/**
	 * Constante URL
	 */
	private static final String URL = "jdbc:hsqldb:hsql://localhost";
	/**
	 * Constante USER
	 */
	private static final String USER = "sa";
	/**
	 * Constante PASSWORD
	 */
	private static final String PASSWORD = "";
	
	/**
	 * Atributo idMechanic
	 */
	private String idMechanic;

	/**
	 * Constructor idMechanic
	 * 
	 * @param idMechanic
	 */
	public DeleteMechanic(String idMechanic) {
		this.idMechanic = idMechanic;
	}

	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	public void execute() throws BusinessException {		
		Connection c = null;
		PreparedStatement pst = null;		
		ResultSet rs = null;
		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);

			pst = c.prepareStatement(SQL);
			pst.setString(1, idMechanic);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignore */ }
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					/* ignore */ }
			if (c != null)
				try {
					c.close();
				} catch (SQLException e) {
					/* ignore */ }
		}
	}
}
