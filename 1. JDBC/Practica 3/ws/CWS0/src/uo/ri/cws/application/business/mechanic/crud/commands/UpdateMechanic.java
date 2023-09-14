package uo.ri.cws.application.business.mechanic.crud.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;

/**
 * Titulo: Clase UpdateMechanic
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class UpdateMechanic {
	/**
	 * Constante SQL_UPDATE
	 */
	private static String SQL_UPDATE = "update TMechanics " + "set name = ?, surname = ?, version = version+1 "
			+ "where id = ?";
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
	 * Atributo c
	 */
	private Connection c = null;
	/**
	 * Atributo mechanic 
	 */
	private MechanicBLDto mechanic;
	
	/**
	 * Constructor UpdateMechanic
	 * @param mechanic
	 */
	public UpdateMechanic(MechanicBLDto mechanic) {
		this.mechanic=mechanic;
	}
	
	/**
	 * Método execute
	 * @throws BusinessException
	 */
	public void execute() throws BusinessException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);
			pst = c.prepareStatement(SQL_UPDATE);
			pst.setString(1, mechanic.name);
			pst.setString(2, mechanic.surname);
			pst.setString(3, mechanic.id);

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
